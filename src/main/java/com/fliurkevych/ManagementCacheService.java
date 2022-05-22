package com.fliurkevych;

import static com.fliurkevych.PdpEffectiveUtils.getRandomNumber;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class ManagementCacheService {

  private static final Logger LOG = LoggerFactory.getLogger(ManagementCacheService.class);

  private final BaseCacheService baseCacheService;
  private final Scanner sc;

  public ManagementCacheService(BaseCacheService baseCacheService, Scanner sc) {
    this.baseCacheService = baseCacheService;
    this.sc = sc;
  }

  public void putNewElements() {
    int countOfNewElements = getCountOfIterations();
    for (int i = 0; i < countOfNewElements; i++) {
      baseCacheService.put(new User("name" + i));
    }
    LOG.info("Successfully put {} values ", countOfNewElements);
  }

  public void getElements() {
    int countOfElements = getCountOfIterations();
    int maxRangeOfRandomKey = getMaxRangeOfRandomKey();

    for (int i = 0; i < countOfElements; i++) {
      int key = getRandomNumber(0, maxRangeOfRandomKey);
      baseCacheService.get(key);
    }
    LOG.info("Get {} random items", countOfElements);
  }

  public void show() {
    baseCacheService.showValues();
  }

  public void showStats() {
    baseCacheService.showStats();
  }


  private int getCountOfIterations() {
    System.out.println("Please enter count of iterations:");
    return sc.nextInt();
  }

  private int getMaxRangeOfRandomKey() {
    System.out.println("Please enter max range of random key:");
    return sc.nextInt();
  }

}
