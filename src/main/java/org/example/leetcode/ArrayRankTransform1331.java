package org.example.leetcode;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 这道题的思想还是领悟到了，就是用map来获取元素的位置，代替list的indexof是性能远优秀的推荐操作
 * 另一个点就是遍历arr比遍历list更加方便，因为get方法毕竟也算是函数调用，肯定比访问内存要速度慢
 */
public class ArrayRankTransform1331 {
    class Solution {
        public int[] arrayRankTransform(int[] arr) {
            int[] res = new int[arr.length];
            List<Integer> list = Arrays.stream(arr).boxed().distinct().sorted().collect(Collectors.toList());
            Map<Integer, Integer> rankMap = new HashMap<>();
            for (int i = 0; i < list.size(); i++) {
                rankMap.put(list.get(0),i);
            }

            for (int i = 0; i < arr.length; i++) {
                int index = rankMap.get(arr[i]);
                res[i] = index + 1;
            }
            return res;
        }
    }

    @Test
    public void test() {
        Solution solution = new Solution();
//        int[] arr = new int[]{437,12,28,9,100,56,80,5,12};
        int[] arr = new int[]{37,12,28,9,100,56,80,5,12};
        int[] res = solution.arrayRankTransform(arr);
        System.out.println(Arrays.toString(res));
    }
}
