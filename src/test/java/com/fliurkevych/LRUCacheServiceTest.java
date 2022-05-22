package com.fliurkevych;

import static com.fliurkevych.TestUtils.NAME_1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

/**
 * @author Oleh Fliurkevych
 */
public class LRUCacheServiceTest {
  
  private LRUCacheService lruCacheService;

  @BeforeEach
  void init(){
    lruCacheService = new LRUCacheService();
  }

  @Test
  public void getCacheType() {
    Assertions.assertEquals(lruCacheService.getCacheType().getCacheNumber(),
      CacheType.LRU.getCacheNumber());
  }

  @Test
  public void getWhenExist() {
    var key = lruCacheService.put(new User(NAME_1));
    User user = lruCacheService.get(key);
    Assertions.assertEquals(NAME_1, user.getName());
  }

  @Test
  public void getWhenAbsent() {
    var random = new Random();
    User user = lruCacheService.get(random.nextInt());
    Assertions.assertNull(user);
  }
  
  

}
