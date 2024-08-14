package com.liudmila.udot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/interval-list-intersections/
 */
public class IntervalIntersection {

    public static int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        int firstListIndex = 0;
        int secondListIndex = 0;

        List<int[]> ret = new ArrayList<>();
        while (firstListIndex <= firstList.length - 1 && secondListIndex <= secondList.length - 1) {
            int[] interval1 = firstList[firstListIndex];
            int[] interval2 = secondList[secondListIndex];

            int intersectionStart = Math.max(interval1[0], interval2[0]);
            int intersectionEnd = Math.min(interval1[1], interval2[1]);

            if (intersectionStart <= intersectionEnd) {
                ret.add(new int[]{intersectionStart, intersectionEnd});
            }

            if (interval1[1] <= interval2[1]) {
                firstListIndex++;
            } else {
                secondListIndex++;
            }
        }
        return ret.toArray(new int[][]{});
    }

    public static void main(String[] args) {
        int[][] firstInterval = new int[][]{{0, 2}, {5, 10}, {13, 23}, {24, 25}};
        int[][] secondInterval = new int[][]{{1, 5}, {8, 12}, {15, 24}, {25, 26}};
        System.out.println(Arrays.deepToString(intervalIntersection(firstInterval, secondInterval)));
    }
}
