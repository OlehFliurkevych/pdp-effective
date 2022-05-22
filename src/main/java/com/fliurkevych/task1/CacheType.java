package com.fliurkevych.task1;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Oleh Fliurkevych
 */
@Getter
@AllArgsConstructor
public enum CacheType {

  LFU(1), LRU(2);

  private final int cacheNumber;

}
