package com.fliurkevych.task1;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Oleh Fliurkevych
 */
public abstract class AbstractCacheService implements BaseCacheService {

  protected AtomicInteger countOfElements = new AtomicInteger(0);
  protected AtomicInteger countOfLoads = new AtomicInteger(0);
  protected AtomicInteger countEvictions = new AtomicInteger(0);
  protected double totalTimeLoad = 0;

  @Override
  public void showStats() {
    System.out.println("\n__________Stats___________");
    System.out.println("Number of cache evictions: " + countEvictions);
    double avg = totalTimeLoad / (double) countOfLoads.get();
    System.out.println("Average time spent for putting new values into the cache: " + avg + " ms");
    System.out.println("__________________________");
  }

}
