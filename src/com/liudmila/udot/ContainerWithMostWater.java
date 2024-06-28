package com.liudmila.udot;

/**
 * https://leetcode.com/problems/container-with-most-water/description/
 *
 * Clarification:
 * All pairs of the n lines define a rectangle with a height given by the shorter line and
 * a width given by the distance between the lines.
 *
 * Return the area of the rectangle with the largest area.
 */
public class ContainerWithMostWater {

    public static int maxArea(int[] height) {
        int start = 0;
        int end = height.length - 1;

        int maxArea = -1;
        while (start < end) {
            maxArea = Math.max(maxArea, (end - start) * Math.min(height[start], height[end]));
            if (height[start] < height[end]) {
                start++;
            } else {
                end--;
            }
        }

        return maxArea;
    }

    public static void main(String[] args) {
        int[] height = new int[]{1,8,6,2,5,4,8,3,7};
        System.out.println(maxArea(height));
    }
}
