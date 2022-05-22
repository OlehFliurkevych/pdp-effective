package com.fliurkevych;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * @author Oleh Fliurkevych
 */
public class PdpEffectiveUtilsTest {

  @Test
  void getCacheServiceByCacheType() {
    var services = List.of(new LFUCacheService(), new LRUCacheService());
    var service = PdpEffectiveUtils.getCacheServiceByCacheType(services, 1);
    Assertions.assertEquals(service.getCacheType(), CacheType.LFU);
  }

  @Test
  void getCacheServiceByCacheShouldThrowExceptionType() {
    var services = List.of(new LFUCacheService(), new LRUCacheService());
    Assertions.assertThrows(RuntimeException.class,
      () -> PdpEffectiveUtils.getCacheServiceByCacheType(services, 3));
  }

}
