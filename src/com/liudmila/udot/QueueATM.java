package com.liudmila.udot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://takeitoutamber.medium.com/hackerrank-coding-interview-1-queue-at-atm-b2e0e1859d3d
 */
public class QueueATM {

    public static class Pair {
        private int key;
        private int value;

        public Pair(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    public static int[] order(int[] amounts, int k) {
        // List<Pair<Integer, Integer>>
        List<Pair> pairs = new ArrayList<>();

        for (int i = 0; i <= amounts.length - 1; i++) {
            // the only trick to show diff between 2 and 3 amount when k = 2
            pairs.add(new Pair((amounts[i] - 1 + k) / k, i));
        }

        int[] sorted = pairs.stream().sorted((p1, p2) -> {
                    int compare = Integer.compare(p1.key, p2.key);
                    if (compare == 0) {
                        return Integer.compare(p1.value, p2.value);
                    }
                    return compare;
                }).mapToInt(p -> p.value + 1)
                .toArray();
        return sorted;
    }

    public static void main(String[] args) {
        int[] amounts = new int[]{6, 1, 2, 3, 2, 7};
        int k = 2;
        int[] order = order(amounts, k);
        System.out.println(Arrays.toString(order));
    }
}
