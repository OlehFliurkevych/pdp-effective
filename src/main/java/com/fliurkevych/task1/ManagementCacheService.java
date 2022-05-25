package com.fliurkevych.task1;

import static com.fliurkevych.task1.PdpEffectiveUtils.getRandomNumber;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ManagementCacheService {

  private static final Logger LOG = LoggerFactory.getLogger(ManagementCacheService.class);

  private final AbstractCacheService cacheService;
  private final InputService inputService;

  public ManagementCacheService(AbstractCacheService cacheService, InputService inputService) {
    this.cacheService = cacheService;
    this.inputService = inputService;
  }

  public void putNewElements() {
    int countOfNewElements = inputService.getCountOfIterations();
    for (int i = 0; i < countOfNewElements; i++) {
      Integer key = cacheService.getCountOfElements().get() + 1;
      cacheService.put(key, new User("name" + i));
    }
    LOG.info("Successfully put {} values ", countOfNewElements);
  }

  public void getElements() {
    int countOfElements = inputService.getCountOfIterations();
    int maxRangeOfRandomKey = inputService.getMaxRangeOfRandomKey();

    for (int i = 0; i < countOfElements; i++) {
      int key = getRandomNumber(0, maxRangeOfRandomKey);
      cacheService.get(key);
    }
    LOG.info("Get {} random items", countOfElements);
  }

  public void show() {
    cacheService.showValues();
  }

  public void showStats() {
    cacheService.showStats();
  }

}
