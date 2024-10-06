package org.example.leetcode;

import org.junit.Assert;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TwoSum1 {
    static class Solution {
        public static  int[] twoSum(int[] nums, int target) {
            int[] origin = nums.clone();
            // 变成有序数组，便于二分查找，快排一次的时间复杂度也是nlogn，因此只能来优化n**2的算法
            Arrays.parallelSort(nums);
            for (int i = 0; i < nums.length; i++) {
                int start = i + 1,end = nums.length - 1;
                while (start <= end){
                    int mid = (start + end) / 2;
                    if (nums[i] + nums[mid] == target) {
                        List<Integer> intList = Arrays.stream(origin).boxed().collect(Collectors.toList());
                        int i1 = intList.indexOf(nums[i]);
                        int i2 = intList.indexOf(nums[mid]);
                        if (i1 == i2) {
                            intList.remove(i1);
                            i2 = intList.indexOf(nums[i]) + 1;
                        }
                        return new int[]{i1, i2};
                    }
                    else if (nums[i] + nums[mid] < target) {
                        start = mid + 1;
                    }
                    else {
                        end = mid - 1;
                    }
                }
            }
            return new int[]{};
        }
    }

    public static void main(String[] args) {
        int[] sum = new int[]{3,3};
        int target = 6;
        int[] res = Solution.twoSum(sum, target);
        Assert.assertEquals(res[0],0);
        Assert.assertEquals(res[1], 1);
    }

}
