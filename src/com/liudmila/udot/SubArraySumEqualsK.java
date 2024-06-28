package com.liudmila.udot;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/subarray-sum-equals-k/description/
 * <p>
 * Given an array of integers nums and an integer k, return the total number of subarrays whose sum equals to k.
 * <p>
 * A subarray is a contiguous non-empty sequence of elements within an array.
 */
public class SubArraySumEqualsK {

    public static int subarraySum(int[] nums, int k) {
        // Add all the prefix sums into map <sum,number_of_sums>
        // Verify if map contains k-current_element key
        Map<Integer, Integer> prefixSums = new HashMap<>();
        int ret = 0;

        int prefixSum = 0;
        prefixSums.put(prefixSum, 1);
        for (int i = 0; i <= nums.length - 1; i++) {
            prefixSum = prefixSum + nums[i];

            if (prefixSums.containsKey(prefixSum - k)) {
                // currSum - prefixSum = k
                ret += prefixSums.get(prefixSum - k);
            }
            prefixSums.put(prefixSum, prefixSums.getOrDefault(prefixSum, 0) + 1);
        }

        return ret;
    }

    public static void main(String[] args) {
        // [1,2,3,4]
        // 1
        // 1, 2, 3
        // 1, 2, 3, 3, 5
        // contiguous sum from i to current is difference between current prefix sum and prefixSum[i]
        // k = currentPrefix - prefixSum[i]
        // prefixSum[i] =  currentPrefixSum - k
        int ret = subarraySum(new int[]{16, -16, 16}, 16);
        System.out.println(ret);
    }

}
