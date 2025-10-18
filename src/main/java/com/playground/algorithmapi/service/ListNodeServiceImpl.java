package com.playground.algorithmapi.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.playground.algorithmapi.model.ListNode;

@Service
public class ListNodeServiceImpl implements IListNodeService {

    /**
     * 删除链表倒数第n个节点
     * @param head 链表头节点
     * @param n 倒数第n个位置（从1开始计数）
     * @return 删除节点后的链表头节点
     */
    @Override
    public ListNode removeNthNode(ListNode head, int n) {
        int length = 0;
        ListNode current = head;
        while (current != null) {
            length++;
            current = current.getNext();
        }

        int index = length - n;
        ListNode dummy = ListNode.builder().val(0).next(head).build();

        current = dummy;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }

        current.setNext(current.getNext().getNext());

        return dummy.getNext();
    }

    /**
     * 初始化链表，创建值为1到n的节点
     * @param n 节点数量
     * @return 链表头节点
     */
    @Override
    public ListNode initListNode(int n) {
        ListNode head = ListNode.builder().val(0).build();
        ListNode current = head;
        for (int i = 1; i <= n; i++) {
            current.setNext(ListNode.builder().val(i).build());
            current = current.getNext();
        }
        current.setNext(null);

        return head.getNext();
    }

    /**
     * 将链表转换为字符串表示
     * @param head 链表头节点
     * @return 链表的字符串表示
     */
    @Override
    public String printListNode(ListNode head) {
        StringBuilder sb = new StringBuilder();
        ListNode current = head;
        while (current != null) {
            sb.append(current.getVal()).append(" ");
            current = current.getNext();
        }
        
        return sb.toString();
    }

    @Override
    public int lengthOfLongestSubstring(String s) {
        
        if(s==null || s.length()==0){
            return 0;
        }

        char[] chars = s.toCharArray();
        Set<Character> seen = new HashSet<>();

        int left = 0;
        int max = 0;
        for(int right =0; right<chars.length; right++){
            while(seen.contains(chars[right])){
                seen.remove(chars[left]);
                left++;
            }
            seen.add(chars[right]);

            max = Math.max(max, right-left+1);
        }
        return max; 
    }
}