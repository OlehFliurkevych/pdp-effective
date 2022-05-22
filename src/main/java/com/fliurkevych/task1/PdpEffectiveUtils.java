package com.fliurkevych.task1;

import java.util.List;

/**
 * @author Oleh Fliurkevych
 */
public class PdpEffectiveUtils {

  public static final int MAX_CACHE_SIZE = 100_000;
  public static final long EXPIRE_AFTER_TIME_VALUE = 10;

  public static BaseCacheService getCacheServiceByCacheType(List<AbstractCacheService> services,
    int cacheType) {
    return services.stream()
      .filter(baseCacheService ->
        baseCacheService.getCacheType().getCacheNumber() == cacheType)
      .findFirst()
      .orElseThrow(() -> new RuntimeException("Unexpected cache type"));
  }

  public static int getRandomNumber(int min, int max) {
    return (int) ((Math.random() * (max - min)) + min);
  }

}
