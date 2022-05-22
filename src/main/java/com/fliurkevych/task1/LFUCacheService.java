package com.fliurkevych.task1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class LFUCacheService extends AbstractCacheService {

  private static final Logger LOG = LoggerFactory.getLogger(LFUCacheService.class);
  private static final ConcurrentMap<Integer, CacheRecord> cache = new ConcurrentHashMap<>();
  private final int initialCapacity;

  public LFUCacheService(int initialCapacity) {
    this.initialCapacity = initialCapacity;
  }

  public LFUCacheService() {
    this.initialCapacity = PdpEffectiveUtils.MAX_CACHE_SIZE;
  }

  @Override
  public User get(Integer key) {
    if (cache.containsKey(key)) {
      CacheRecord record = cache.get(key);
      record.setFrequency(record.getFrequency() + 1);
      putOldRecordIntoCache(key, record);
      return record.getUser();
    }
    return null;
  }

  @Override
  public CacheType getCacheType() {
    return CacheType.LFU;
  }

  @Override
  public Integer put(User user) {
    if (!isFull()) {
      CacheRecord cacheRecord = new CacheRecord(user);
      return putNewRecordIntoCache(cacheRecord);
    } else {
      Integer keyToBeRemoved = getLFUKey();
      cache.remove(keyToBeRemoved);
      LOG.info("User with key {} is removed", keyToBeRemoved);
      countEvictions.incrementAndGet();

      CacheRecord cacheRecord = new CacheRecord(user);
      return putNewRecordIntoCache(cacheRecord);
    }
  }

  @Override
  public void showValues() {
    cache.forEach((e1, e2) -> System.out.println("key = " + e1 + ", value = " + e2.getUser()));
  }

  private Integer putNewRecordIntoCache(CacheRecord cacheRecord) {
    int key = countOfElements.incrementAndGet();
    countOfLoads.incrementAndGet();
    return putOldRecordIntoCache(key, cacheRecord);
  }

  private Integer putOldRecordIntoCache(Integer key, CacheRecord cacheRecord) {
    long l1 = System.currentTimeMillis();
    cache.put(key, cacheRecord);
    long l2 = System.currentTimeMillis();

    totalTimeLoad = totalTimeLoad + (l2 - l1);
    return key;
  }

  private Integer getLFUKey() {
    int key = 0;
    int minFreq = Integer.MAX_VALUE;

    for (Map.Entry<Integer, CacheRecord> entry : cache.entrySet()) {
      if (minFreq > entry.getValue().getFrequency()) {
        key = entry.getKey();
        minFreq = entry.getValue().getFrequency();
      }
    }

    return key;
  }

  private boolean isFull() {
    return cache.size() == initialCapacity;
  }

  static class CacheRecord {

    private final User user;
    private int frequency;

    protected CacheRecord(User user) {
      this.user = user;
      this.frequency = 0;
    }

    public User getUser() {
      return user;
    }

    public int getFrequency() {
      return frequency;
    }

    public void setFrequency(int frequency) {
      this.frequency = frequency;
    }
  }

}
