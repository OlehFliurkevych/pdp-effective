package com.fliurkevych.task2;

import java.util.Arrays;

/**
 * @author Oleh Fliurkevych
 */
public class PdpEffectiveTask2Main {

  public static void main(String[] args) {
    int[] arrSorted = {12, 14, 23, 43, 53, 90, 123};
    int[] arrUnsorted = {12, 123, 42, 542, 3, 56, 234};

    BinarySearch binarySearch = new BinarySearch(arrSorted);
    int findNum1 = 23;
    int findNum2 = 53;
    var indexOfNumber1 = binarySearch.searchRecursive(findNum1);
    var indexOfNumber2 = binarySearch.searchIteratively(findNum2);
    System.out.println("Index of " + findNum1 + " = " + indexOfNumber1);
    System.out.println("Index of " + findNum2 + " = " + indexOfNumber2);

    MergeSort mergeSort = new MergeSort();
    mergeSort.sort(arrUnsorted, 0, arrUnsorted.length - 1);
    System.out.println(Arrays.toString(arrUnsorted));

    int[] arrUnsorted2 = {12, 2342, 2, 42, 3, 516, 74};
    InsertionSort insertionSort = new InsertionSort();
    insertionSort.sort(arrUnsorted2);
    System.out.println(Arrays.toString(arrUnsorted2));

  }

}
