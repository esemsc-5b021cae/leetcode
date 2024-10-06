package org.example.leetcode;

import org.junit.Assert;

import java.util.Objects;

/**
 * Two sentences s1 and s2 are considered similar if it is possible to insert an arbitrary sentence (possibly empty)
 * inside one of these sentences such that the two sentences become equal. Note that the inserted sentence must
 * be separated from existing words by spaces.
 *
 * 考虑比较两个字符串的前缀位置和后缀位置，如果两者之和大于了全部的sentence，则说明true
 */
public class SentenceSimilarityIII1813 {
    static class Solution {
        public static boolean areSentencesSimilar(String sentence1, String sentence2) {
            String[] s1 = sentence1.split(" ");
            String[] s2 = sentence2.split(" ");
            int prefix = 0, suffix = 0;
            while (prefix < s1.length && prefix < s2.length) {
                if (Objects.equals(s1[prefix], s2[prefix])) {
                    prefix++;
                } else {
                    break;
                }
            }
            if (prefix == s1.length || prefix == s2.length) {
                return true;
            }
            while (s1.length - suffix >= 1 && s2.length - suffix >= 1) {
                if (Objects.equals(s1[s1.length - 1 - suffix], s2[s2.length - 1 - suffix])) {
                    suffix++;
                } else {
                    break;
                }
            }
            if (prefix + suffix >= s1.length || prefix + suffix >= s2.length) {
                return true;
            }
            return false;
        }
    }

    public static void main(String[] args) {
        String sentence1 = "A B C D B B", sentence2 = "A B B";
        String sentence3 = "of", sentence4 = "A lot of words";
        boolean res = Solution.areSentencesSimilar(sentence1, sentence2);
        Assert.assertTrue(res);
//        boolean res1 = Solution.areSentencesSimilar(sentence3, sentence4);
//        Assert.assertTrue(res1);
    }
}
