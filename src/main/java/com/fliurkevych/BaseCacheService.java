package com.fliurkevych;

/**
 * @author Oleh Fliurkevych
 */
public interface BaseCacheService {

  Integer put(User user);

  User get(Integer key);

  CacheType getCacheType();

  void showValues();

  void showStats();
}
