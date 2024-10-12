package org.example.leetcode;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * gpt老师优化的好啊
 * 这个问题大致思路肯定是没问题的，用priorityQueue来存出最后的结尾元素，然后根据结尾元素来添加进新元素、
 * 之前我的想法是使区间尽可能契合，这样直观上可以存储更多元素，避免冲突，感觉像是贪心的思想
 * 但是因为这里我已经将区间一开始就排序过了，你后面的元素跟结尾更契合的组结合还是更最小的组结合没有区别，因为后面的元素比当前元素大一定也能结合，
 * 不用担心组间隔大的问题，因为一定能结合上，因此效果上是一样的，只需要考虑堆结构最上层的元素就好了
 */
public class MinGroups2406 {
    class Solution {
//        public int minGroups(int[][] intervals) {
//            int[][] sortedArr = Arrays.stream(intervals).sorted((o1,o2) -> {
//                if (o1[0] < o2[0]) {
//                    return -1;
//                } else if (o1[0] == o2[0]) {
//                    return Integer.compare(o1[1], o2[1]);
//                } else {
//                    return 1;
//                }
//            }).toArray(int[][]::new);
//            PriorityQueue<Integer> groupQueue = new PriorityQueue<>();
//            for (int i = 0; i < sortedArr.length; i++) {
//                if (groupQueue.isEmpty()) {
//                    groupQueue.add(sortedArr[i][1]);
//                    continue;
//                }
//                // find the exact group to update
//                int finalI = i;
//                List<Integer> lowerList = groupQueue.stream().filter((e) -> e < sortedArr[finalI][0]).collect(Collectors.toList());
//                if (lowerList.size() != 0) {
//                    int updateGroup = lowerList.get(lowerList.size() - 1);
//                    groupQueue.remove(updateGroup);
//                    groupQueue.add(sortedArr[i][1]);
//                    continue;
//                }
//                // if no group is available
//                groupQueue.add(sortedArr[i][1]);
//            }
//            return groupQueue.size();
//        }
        public int minGroups(int[][] intervals) {
            // Sort intervals based on start time, and if equal, by end time
            Arrays.sort(intervals, (a, b) -> {
                if (a[0] != b[0]) {
                    return Integer.compare(a[0], b[0]);
                } else {
                    return Integer.compare(a[1], b[1]);
                }
            });

            // PriorityQueue to keep track of the end times of groups
            PriorityQueue<Integer> groupQueue = new PriorityQueue<>();

            for (int[] interval : intervals) {
                // If the earliest ending interval finishes before the current interval starts,
                // we can reuse that group
                if (!groupQueue.isEmpty() && groupQueue.peek() < interval[0]) {
                    groupQueue.poll(); // Remove the group that's ending the earliest
                }
                // Add the current interval's end time to the queue
                groupQueue.add(interval[1]);
            }

            // The size of the queue represents the minimum number of groups needed
            return groupQueue.size();
        }
    }
    @Test
    public void test(){
        Solution solution = new Solution();
        int[][] intervals = {
                {5, 10},
                {6, 8},
                {1, 5},
                {2, 3},
                {1, 10}
        };
//        int[][] intervals = {
//                {1, 3},
//                {5, 6},
//                {8, 10},
//                {11, 13}
//        };
        int res = solution.minGroups(intervals);
        System.out.println(res);
    }
}
