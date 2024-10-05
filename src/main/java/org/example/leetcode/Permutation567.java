package org.example.leetcode;

import org.junit.Assert;

public class Permutation567 {
    static class Solution {
        public static boolean checkInclusion(String s1, String s2) {

            if (s2.length() < s1.length()) {
                return false;
            }
            for(int i = 0; i <= s2.length() - s1.length(); i++) {
                int[] charMap = new int[26];
                for (int j = 0; j < s1.length(); j++) {
                    charMap[s1.charAt(j) - 'a']++;
                    charMap[s2.charAt(i + j) - 'a']--;
                }
                boolean flag = true;
                for(int k =0; k < 26; k++) {
                    if(charMap[k] != 0) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    return true;
                }
            }

            return false;
        }
    }

    public static void main(String[] args) {
        String s1 = "ab", s2 = "eidbaooo";
        boolean res = Solution.checkInclusion(s1,s2);
        Assert.assertTrue(res);
        System.out.println("pass");
    }
}
