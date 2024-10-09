package org.example.leetcode;

import org.junit.Test;

import java.util.Queue;
import java.util.Stack;

public class minAddToMakeValid921 {
    /**
     * parentheses match 的问题，基本思路就是想到栈，左括号进站遇到有括号出栈
     */
    class Solution {
        public int minAddToMakeValid(String s) {
            Stack<Character> parentheseStatck = new Stack<>();
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '(') {
                    parentheseStatck.add(s.charAt(i));
                    continue;
                }
                if (s.charAt(i) == ')') {
                    if (parentheseStatck.size() == 0) {
                        parentheseStatck.add(s.charAt(i));
                        continue;
                    }
                    if (parentheseStatck.peek() == '(') {
                        parentheseStatck.pop();
                        continue;
                    }
                    if (parentheseStatck.peek() == ')') {
                        parentheseStatck.add(s.charAt(i));
                    }
                }
            }
            return parentheseStatck.size();
        }
    }

    @Test
    public void test() {
        Solution solution = new Solution();
        String s = "(((";
        int res = solution.minAddToMakeValid(s);
        System.out.println(res);
    }
}
