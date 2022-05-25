package com.fliurkevych.task1;

import java.util.Scanner;

/**
 * @author Oleh Fliurkevych
 */
public class InputService {

  private final Scanner sc;

  public InputService(Scanner scanner) {
    this.sc = scanner;
  }

  public int chooseCacheType() {
    System.out.println("\n____ Cache types ____");
    System.out.println("1 - LFU cache (Simple Java)");
    System.out.println("2 - LRU cache (Guava)");
    System.out.println("(any negative number for exit)");
    System.out.println("_____________________");
    System.out.println("\nPlease choose cache type:");
    return sc.nextInt();
  }

  public int getStepNumber(CacheType cacheType) {
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

  public int getCountOfIterations() {
    System.out.println("Please enter count of iterations:");
    return sc.nextInt();
  }

  public int getMaxRangeOfRandomKey() {
    System.out.println("Please enter max range of random key:");
    return sc.nextInt();
  }
  
}
