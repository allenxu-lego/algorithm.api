package com.playground.algorithmapi.service;

import com.playground.algorithmapi.model.ListNode;

/**
 * 链表操作服务接口
 */
public interface IListNodeService {

    /**
     * 删除链表倒数第n个节点
     * @param head 链表头节点
     * @param n 倒数第n个位置（从1开始计数）
     * @return 删除节点后的链表头节点
     */
    ListNode removeNthNode(ListNode head, int n);

    /**
     * 初始化链表，创建值为1到n的节点
     * @param n 节点数量
     * @return 链表头节点
     */
    ListNode initListNode(int n);

    /**
     * 将链表转换为字符串表示
     * @param head 链表头节点
     * @return 链表的字符串表示
     */
    String printListNode(ListNode head);

    public int lengthOfLongestSubstring(String s);

    public int[] twoSum(int[] nums, int target);

    public String mergeAlternately(String word1, String word2);

    public int numTeams(int[] rating);
}