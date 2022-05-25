package com.fliurkevych.task1;

import static com.fliurkevych.task1.TestUtils.NAME_1;
import static com.fliurkevych.task1.TestUtils.NAME_2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

/**
 * @author Oleh Fliurkevych
 */
public class LFUCacheServiceTest {

  private LFUCacheService<Integer, User> lfuCacheService;

  @BeforeEach
  void init() {
    lfuCacheService = new LFUCacheService<>();
  }

  @Test
  public void getCacheType() {
    Assertions.assertEquals(lfuCacheService.getCacheType().getCacheNumber(),
      CacheType.LFU.getCacheNumber());
  }

  @Test
  public void getWhenExist() {
    var key = lfuCacheService.put(1, new User(NAME_1));
    User user = lfuCacheService.get(key);
    Assertions.assertEquals(NAME_1, user.getName());
  }

  @Test
  public void getWhenAbsent() {
    var random = new Random();
    User user = lfuCacheService.get(random.nextInt());
    Assertions.assertNull(user);
  }

  @Test
  public void putWhenCacheIsFull() {
    lfuCacheService = new LFUCacheService<Integer, User>(1);
    lfuCacheService.put(1, new User(NAME_1));
    var key = lfuCacheService.put(2, new User(NAME_2));
    Assertions.assertNull(lfuCacheService.get(1));
    Assertions.assertEquals(2, key);
  }


}
