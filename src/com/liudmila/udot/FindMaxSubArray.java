package com.liudmila.udot;

/**
 * Created by Liuda on 29.07.2017.
 *
 * Class that contains two methods
 * to get a maximum sub array
 * (sub array with max amount).
 * One method is recursive with O(n) = n*log(n).
 * Another method is implementation of Kadane's with O(n) = n.
 * e.g. for - {1,3,-9,8,7}, maximum sub array - {8, 7}
 * It has a sense only for arrays,
 * that contains negative numbers.
 */
public class FindMaxSubArray {

    /**
     * Container for result:
     * <p>
     * start - sub array first index
     * end - sub array last index
     * amount - sub array amount
     */
    class Cortege {
        private int start;
        private int end;
        private int amount;

        public Cortege(int start, int end, int amount) {
            this.start = start;
            this.end = end;
            this.amount = amount;
        }

        @Override
        public String toString() {
            return "{" +
                    "startIndex=" + start +
                    ", endIndex=" + end +
                    ", amount=" + amount +
                    '}';
        }
    }

    public static void main(String[] s) {
        int[] array = {13, -3, -25, 20, -3, -16, -23,
                18, 20, -7, 12, -5, -22, 15, -4, 7};
        FindMaxSubArray findMaxSubArray = new FindMaxSubArray();
        // print result of Kadane's function call
        System.out.println(findMaxSubArray.findMaxSubArrayKadanes(array));
        // print result of recursive function call
        System.out.println(findMaxSubArray.findMaxSubArrayRecursively(array));
    }

    public Cortege findMaxSubArrayRecursively(int[] array) {
        return findMaxSubArrayRecursively(array, 0, array.length - 1);
    }

    /**
     * Described in Introduction to Algorithms
     * (Thomas H. Cormen. Charles E. Leiserson. Ronald L. Rivest. Clifford Stein)
     * chapter 4.1
     * If to divide array for two parts the maximum array will be the array
     * with the biggest amount from arrays that crosses the middle point,
     * or located from left or right side from it.
     *
     * @param array - to search sub array in
     * @param start - index from which search for max sub array starts
     * @param end   - index on which search for max sub array ends
     * @return cortege with max sub array start, end indexes and amount
     */
    public Cortege findMaxSubArrayRecursively(int[] array, int start, int end) {
        // find a middle point of start and end indexes
        if (start == end) {
            return new Cortege(start, end, array[start]);
        }
        int mid = (end + start) / 2;

        // find a max sub array from the right side of mid point
        Cortege cortegeRight = findMaxSubArrayRecursively(array, start, mid);
        // find a max sub array from the left side of mid point
        Cortege cortegeLeft = findMaxSubArrayRecursively(array, mid + 1, end);
        // find a max sub array that crosses mid point
        Cortege crossCortege = findMaxCrossingSubArray(array, mid, start, end);

        // find array with the biggest amount of 3 calculated above
        if (cortegeLeft.amount > cortegeRight.amount && cortegeLeft.amount > crossCortege.amount) {
            return cortegeLeft;
        } else if (cortegeRight.amount > cortegeLeft.amount && cortegeRight.amount > crossCortege.amount) {
            return cortegeRight;
        } else {
            return crossCortege;
        }
    }

    /**
     * Find a max sub array
     * that crosses a crossCoordinate.
     *
     * @param array           - to find max crossing sub array in
     * @param crossCoordinate - cross coordinate in array for max crossing sub array
     * @param start           - index from which search for max sub array starts
     * @param end             - index on which search for max sub array ends
     * @return cortege with max crossing sub array start, end indexes and amount
     */
    public Cortege findMaxCrossingSubArray(int[] array, int crossCoordinate,
                                           int start, int end) {
        int newEnd = crossCoordinate;
        int amountTemp = array[crossCoordinate + 1];
        int amountMax = array[crossCoordinate + 1];

        // find max sub array that a located from the right of cross coordinate and contains it
        for (int i = crossCoordinate + 2; i <= end; i++) {
            amountTemp += array[i];
            if (amountTemp > amountMax) {
                newEnd = i;
                amountMax = amountTemp;
            }
        }

        int newStart = crossCoordinate;
        amountMax = array[crossCoordinate];
        amountTemp = array[crossCoordinate];

        for (int i = crossCoordinate - 1; i >= start; i--) {
            amountTemp += array[i];
            if (amountTemp > amountMax) {
                newStart = i;
                amountMax = amountTemp;
            }
        }

        return new Cortege(newStart, newEnd, subArrayAmount(array, newStart, newEnd));
    }

    public int subArrayAmount(int[] array, int start, int end) {
        int amount = 0;
        for (int i = start; i <= end; i++) {
            amount = amount + array[i];
        }
        return amount;
    }

    /**
     * Find maximum sub array using Kadane's algorithm.
     * https://en.wikipedia.org/wiki/Maximum_subarray_problem
     *
     * @param array - to search sub array in
     * @return cortege with max sub array start, end indexes and amount
     */
    public Cortege findMaxSubArrayKadanes(int[] array) {
        int amountTemp = array[0];
        int amountMax = array[0];
        int end = 0;
        int start = 0;
        int tempStartElement = 0;

        for (int i = 1; i < array.length; i++) {
            // amount of sub array decreases
            // start search for new consequent sub array
            // with potentially greater amount
            if (array[i] > amountTemp + array[i]) {
                // start index for new potentially max sub array
                tempStartElement = i;
            }
            amountTemp = Math.max(array[i], amountTemp + array[i]);

            if (amountTemp > amountMax) {
                // when amountTemp become amountMax
                // update start and and for searched sub array
                start = tempStartElement;
                end = i;
            }
            amountMax = Math.max(amountMax, amountTemp);
        }
        return new Cortege(start, end, amountMax);
    }
}
