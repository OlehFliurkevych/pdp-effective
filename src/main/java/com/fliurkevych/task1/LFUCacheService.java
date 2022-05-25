package com.fliurkevych.task1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class LFUCacheService<K, V> extends AbstractCacheService<K, V> {

  private static final Logger LOG = LoggerFactory.getLogger(LFUCacheService.class);
  private final ConcurrentMap<K, CacheRecord<V>> cache = new ConcurrentHashMap<>();
  private final int initialCapacity;

  public LFUCacheService(int initialCapacity) {
    this.initialCapacity = initialCapacity;
  }

  public LFUCacheService() {
    this.initialCapacity = PdpEffectiveUtils.MAX_CACHE_SIZE;
  }

  @Override
  public V get(K key) {
    if (cache.containsKey(key)) {
      CacheRecord<V> record = cache.get(key);
      record.setFrequency(record.getFrequency() + 1);
      putOldRecordIntoCache(key, record);
      return record.getValue();
    }
    return null;
  }

  @Override
  public CacheType getCacheType() {
    return CacheType.LFU;
  }

  @Override
  public K put(K key,V value) {
    if (!isFull()) {
      CacheRecord<V> cacheRecord = new CacheRecord<V>(value);
      return putNewRecordIntoCache(key, cacheRecord);
    } else {
      K keyToBeRemoved = getLFUKey();
      cache.remove(keyToBeRemoved);
      LOG.info("User with key {} is removed", keyToBeRemoved);
      countEvictions.incrementAndGet();

      CacheRecord<V> cacheRecord = new CacheRecord<V>(value);
      return putNewRecordIntoCache(key, cacheRecord);
    }
  }

  @Override
  public void showValues() {
    cache.forEach((e1, e2) -> System.out.println("key = " + e1 + ", value = " + e2.getValue()));
  }

  private K putNewRecordIntoCache(K key, CacheRecord<V> cacheRecord) {
    countOfElements.incrementAndGet();
    countOfLoads.incrementAndGet();
    return putOldRecordIntoCache(key, cacheRecord);
  }

  private K putOldRecordIntoCache(K key, CacheRecord<V> cacheRecord) {
    long l1 = System.currentTimeMillis();
    cache.put(key, cacheRecord);
    long l2 = System.currentTimeMillis();

    totalTimeLoad = totalTimeLoad + (l2 - l1);
    return key;
  }

  private K getLFUKey() {
    K key = null;
    int minFreq = Integer.MAX_VALUE;

    for (Map.Entry<K, CacheRecord<V>> entry : cache.entrySet()) {
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

  static class CacheRecord<V> {

    private final V value;
    private int frequency;

    protected CacheRecord(V value) {
      this.value = value;
      this.frequency = 0;
    }

    public V getValue() {
      return value;
    }

    public int getFrequency() {
      return frequency;
    }

    public void setFrequency(int frequency) {
      this.frequency = frequency;
    }
  }

}
