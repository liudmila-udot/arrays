package com.liudmila.udot;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Created by Liuda on 11.09.2017.
 */
public class QuickSort {

    public static int partition(int[] array, int start, int end) {
        int pivot = array[end];
        int pivotIndex = start - 1;

        for (int i = start; i <= end - 1; i++) {
            if (array[i] <= pivot) {
                pivotIndex++;
                swap(array, i, pivotIndex);
            }
        }
        pivotIndex++;
        swap(array, pivotIndex, end);
        System.out.println(pivotIndex);
        return pivotIndex;
    }

    public static void swap(int[] array, int first, int second) {
        int temp = array[first];
        array[first] = array[second];
        array[second] = temp;
    }

    public static void quicksort(int[] array, int start, int end) {
        if (start < end) {
            int partition = partition(array, start, end);
            quicksort(array, start, partition - 1);
            quicksort(array, partition + 1, end);
        }
    }

    public static void main(String[] args) {
        int[] array = new int[]{2, 8, 7, 1, 3, 5, 6, 4};
        quicksort(array, 0, array.length - 1);
        System.out.println(Arrays.stream(array)
                .mapToObj(Integer::toString)
                .collect(Collectors.joining(" ")));
    }
}
