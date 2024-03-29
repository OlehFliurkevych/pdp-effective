package com.fliurkevych.task1;

import static com.fliurkevych.task1.TestUtils.NAME_1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

/**
 * @author Oleh Fliurkevych
 */
public class LRUCacheServiceTest {

  private LRUCacheService<Integer, User> lruCacheService;

  @BeforeEach
  void init() {
    lruCacheService = new LRUCacheService<>();
  }

  @Test
  public void getCacheType() {
    Assertions.assertEquals(lruCacheService.getCacheType().getCacheNumber(),
      CacheType.LRU.getCacheNumber());
  }

  @Test
  public void getWhenExist() {
    var key = lruCacheService.put(1, new User(NAME_1));
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
