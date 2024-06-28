package com.liudmila.udot;

/**
 * https://leetcode.com/problems/trapping-rain-water/description/
 *
 * Given n non-negative integers representing an elevation map where the width of each bar is 1,
 * compute how much water it can trap after raining.
 *
 */
public class TrappingRainWater {

    //Time: O(N)
    // Space: O(N)
    public static int trap(int[] height) {
        if (height.length < 3) {
            return 0;
        }

        // find effMin from maxFromRight and maxFromLeft
        // then sum(Math.max(0, effMin - current)) starting from [1, len -2]
        int[] maxFromLeft = new int[height.length];
        int[] maxFromRight = new int[height.length];

        maxFromLeft[1] = height[0];
        for (int i = 1; i <= height.length - 1; i++) {
            maxFromLeft[i] = Math.max(maxFromLeft[i - 1], height[i - 1]);
        }
        maxFromRight[height.length - 1] = height[height.length - 1];
        for (int i = height.length - 2; i >= 0; i--) {
            maxFromRight[i] = Math.max(maxFromRight[i + 1], height[i + 1]);
        }

        int ret = 0;

        for (int i = 1; i < height.length - 1; i++) {
            ret += Math.max(0, (Math.min(maxFromRight[i], maxFromLeft[i]) - height[i]));
        }
        return ret;
    }

    public static void main(String[] args) {
        int[] height = new int[]{4, 2, 0, 3, 2, 5};
        int result = trap(height);
        System.out.println(result);
    }
}
