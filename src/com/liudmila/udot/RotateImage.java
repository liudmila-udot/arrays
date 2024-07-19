package com.liudmila.udot;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/rotate-image/description/
 */
public class RotateImage {

    public static void rotate(int[][] matrix) {
        // 1. transpose the matrix
        transpose(matrix);
        // 2. reverse columns
        reverse(matrix);
    }

    public static void transpose(int[][] matrix) {
        for (int i = 0; i <= matrix.length - 1; i++) {
            // NOTE: j starts from i to skip already transposed elements
            // the length of the specific row - number of columns
            for (int j = i; j <= matrix[0].length - 1; j++) {
                swap(matrix, i, j, j, i);
            }
        }
    }

    public static void reverse(int[][] matrix){
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length / 2; j++) {
                swap(matrix, i, j, i, matrix[0].length - 1 - j);
            }
        }
    }

    public static void swap(int[][] matrix, int row1, int column1, int row2, int column2) {
        int temp = matrix[row1][column1];
        matrix[row1][column1] = matrix[row2][column2];
        matrix[row2][column2] = temp;
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        System.out.println(Arrays.deepToString(matrix));
        rotate(matrix);
        System.out.println("After:");
        System.out.println(Arrays.deepToString(matrix));
    }
}
