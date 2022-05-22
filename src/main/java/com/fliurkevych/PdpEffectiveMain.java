package com.fliurkevych;

import static com.fliurkevych.PdpEffectiveUtils.getCacheServiceByCacheType;

import java.util.List;
import java.util.Scanner;

/**
 * @author Oleh Fliurkevych
 */
public class PdpEffectiveMain {

  private static final Scanner sc = new Scanner(System.in);

  public static void main(String[] args) {
    int cacheType;
    while ((cacheType = chooseCacheType()) > 0) {
      var cacheServices = List.of(new LFUCacheService(), new LRUCacheService());
      BaseCacheService cacheService = getCacheServiceByCacheType(cacheServices, cacheType);
      ManagementCacheService managementCacheService = new ManagementCacheService(cacheService, sc);

      int num;
      while ((num = getStepNumber(cacheService.getCacheType())) > 0) {
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

  private static int chooseCacheType() {
    System.out.println("\n____ Cache types ____");
    System.out.println("1 - LFU cache (Simple Java)");
    System.out.println("2 - LRU cache (Guava)");
    System.out.println("(any negative number for exit)");
    System.out.println("_____________________");
    System.out.println("\nPlease choose cache type:");
    return sc.nextInt();
  }

  private static int getStepNumber(CacheType cacheType) {
    System.out.println("\n_____________________");
    System.out.println("Using " + cacheType + " cache type");
    System.out.println("_____________________");
    System.out.println("\n___ Operations with cache ___");
    System.out.println("1 - put new elements into cache");
    System.out.println("2 - simulate get elements from cache");
    System.out.println("3 - get statistics of cache");
    System.out.println("4 - show elements of cache");
    System.out.println("(any negative number for exit)");
    System.out.println("_____________________");
    System.out.println("Please choose operations:");
    return sc.nextInt();
  }

}
