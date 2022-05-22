package com.fliurkevych.task2;

/**
 * @author Oleh Fliurkevych
 */
public class BinarySearch {

  private final int[] array;

  public BinarySearch(int[] array) {
    this.array = array;
  }

  public int searchRecursive(int numberToSearch) {
    return searchRecursive(array, 0, array.length + 1, numberToSearch);
  }

  private int searchRecursive(int[] array, int left, int right, int numberToSearch) {
    if (right >= left) {
      int mid = left + (right - left) / 2;
      if (array[mid] == numberToSearch) {
        return mid;
      }
      if (array[mid] > numberToSearch) {
        return searchRecursive(array, left, mid - 1, numberToSearch);
      }
      return searchRecursive(array, mid + 1, right, numberToSearch);
    }
    return -1;
  }

  public int searchIteratively(int numberToSearch) {
    int l = 0, r = array.length - 1;
    while (l <= r) {
      int m = l + (r - l) / 2;
      if (array[m] == numberToSearch) {
        return m;
      }
      if (array[m] < numberToSearch) {
        l = m + 1;
      } else {
        r = m - 1;
      }
    }
    return -1;
  }

}
