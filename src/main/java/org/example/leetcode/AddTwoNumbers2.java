package org.example.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 写的太复杂了，确实用一个while就能写完的，
 * 之前担心一个while不能保证遍历到的都是最后一个元素
 * 但是note：只要当前元素不为空，则即是最后一个元素，两边list同时推进即可保持同步
 */
public class AddTwoNumbers2 {
     class Solution {
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            List<Integer> list1 = new ArrayList<>();
            List<Integer> list2 = new ArrayList<>();
            ListNode res = new ListNode();
            ListNode head = res;
            while (l1 != null) {
                list1.add(l1.val);
                l1 = l1.next;
            }
            while (l2 != null) {
                list2.add(l2.val);
                l2 = l2.next;
            }
            int advance = 0;
            int i = 0;
            for (; i < list1.size() && i < list2.size(); i++) {
                int temp = (list1.get(i) + list2.get(i) + advance) % 10;
                advance = (list1.get(i) + list2.get(i) + advance) / 10;
                res.next = new ListNode();
                res = res.next;
                res.val = temp;
            }
            if (i < list1.size()) {
                for (; i < list1.size(); i++) {
                    res.next = new ListNode();
                    res = res.next;
                    int temp = (list1.get(i) + advance) % 10;
                    advance = (list1.get(i) + advance) / 10;
                    res.val = temp;
                }
            }
            if (i < list2.size()) {
                for (; i < list2.size(); i++) {
                    res.next = new ListNode();
                    res = res.next;
                    int temp = (list2.get(i) + advance) % 10;
                    advance = (list2.get(i) + advance) / 10;
                    res.val = temp;
                }
            }
            if (advance != 0) {
                res.next = new ListNode();
                res = res.next;
                res.val = advance;
            }
            return head.next;
        }
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
    @Test
    public void test() {
        Solution solution = new Solution();
        ListNode listNode1 = new ListNode(2);
        ListNode listNode2 = new ListNode(4);
        ListNode listNode3 = new ListNode(3);
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        ListNode listNode4 = new ListNode(5);
        ListNode listNode5 = new ListNode(6);
        ListNode listNode6 = new ListNode(4);
        listNode4.next = listNode5;
        listNode5.next = listNode6;

        ListNode res = solution.addTwoNumbers(listNode1, listNode4);
        System.out.println(res);
    }
}
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
//class Solution {
//    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
//        ListNode dummyHead = new ListNode(0);
//        ListNode tail = dummyHead;
//        int carry = 0;
//
//        while (l1 != null || l2 != null || carry != 0) {
//            int digit1 = (l1 != null) ? l1.val : 0;
//            int digit2 = (l2 != null) ? l2.val : 0;
//
//            int sum = digit1 + digit2 + carry;
//            int digit = sum % 10;
//            carry = sum / 10;
//
//            ListNode newNode = new ListNode(digit);
//            tail.next = newNode;
//            tail = tail.next;
//
//            l1 = (l1 != null) ? l1.next : null;
//            l2 = (l2 != null) ? l2.next : null;
//        }
//
//        ListNode result = dummyHead.next;
//        dummyHead.next = null;
//        return result;
//    }
//}