package com.fliurkevych.task1;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

public class LRUCacheService extends AbstractCacheService {

  private static final Logger LOG = LoggerFactory.getLogger(LRUCacheService.class);
  protected static ConcurrentMap<Integer, User> cache;

  public LRUCacheService(int cacheCapacity) {
    final RemovalListener<Integer, User> CLOSER = removal -> {
      LOG.info("User with key {} is removed due to {}", removal.getKey(), removal.getCause());
      countEvictions.incrementAndGet();
    };

    cache = CacheBuilder.newBuilder()
      .expireAfterAccess(PdpEffectiveUtils.EXPIRE_AFTER_TIME_VALUE, TimeUnit.SECONDS)
      .maximumSize(cacheCapacity).removalListener(CLOSER).build().asMap();
  }

  public LRUCacheService() {
    this(PdpEffectiveUtils.MAX_CACHE_SIZE);
  }

  @Override
  public User get(Integer key) {
    if (cache.containsKey(key)) {
      return cache.get(key);
    }
    return null;
  }

  @Override
  public Integer put(User user) {
    long l1 = System.currentTimeMillis();
    int key = countOfElements.incrementAndGet();
    countOfLoads.incrementAndGet();
    cache.put(key, user);
    long l2 = System.currentTimeMillis();
    totalTimeLoad = totalTimeLoad + (l2 - l1);
    return key;
  }

  @Override
  public CacheType getCacheType() {
    return CacheType.LRU;
  }

  @Override
  public void showValues() {
    cache.forEach((e1, e2) -> System.out.println("key = " + e1 + ", value = " + e2));
  }

}
