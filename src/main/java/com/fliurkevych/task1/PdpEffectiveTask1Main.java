package com.fliurkevych.task1;

import static com.fliurkevych.task1.PdpEffectiveUtils.getCacheServiceByCacheType;

import java.util.List;
import java.util.Scanner;

/**
 * @author Oleh Fliurkevych
 */
public class PdpEffectiveTask1Main {

  private static final Scanner sc = new Scanner(System.in);

  public static void main(String[] args) {
    InputService inputService = new InputService(sc);

    int cacheType;
    while ((cacheType = inputService.chooseCacheType()) > 0) {
      var cacheServices = List.of(new LFUCacheService<Integer, User>(),
        new LRUCacheService<Integer, User>());
      AbstractCacheService cacheService = getCacheServiceByCacheType(cacheServices, cacheType);
      ManagementCacheService managementCacheService = new ManagementCacheService(cacheService,
        inputService);

      int num;
      while ((num = inputService.getStepNumber(cacheService.getCacheType())) > 0) {
        switch (num) {
          case 1:
            managementCacheService.putNewElements();
            break;
          case 2:
            managementCacheService.getElements();
            break;
          case 3:
            managementCacheService.showStats();
            break;
          case 4:
            managementCacheService.show();
            break;
        }
      }
    }
  }

}
