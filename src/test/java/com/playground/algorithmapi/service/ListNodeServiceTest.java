package com.playground.algorithmapi.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.playground.algorithmapi.service.impl.ListNodeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.playground.algorithmapi.model.ListNode;

/**
 * ListNodeService 接口的测试类
 * 展示面向接口编程的好处：可以轻松测试和替换实现
 */
class ListNodeServiceTest {

    private IListNodeService listNodeService;

    @BeforeEach
    void setUp() {
        // 可以轻松替换为不同的实现
        listNodeService = new ListNodeServiceImpl();
    }

    @Test
    void testInitListNode() {
        // 测试初始化链表
        ListNode head = listNodeService.initListNode(5);
        
        assertNotNull(head);
        assertEquals(1, head.getVal());
        
        // 验证链表结构: 1 -> 2 -> 3 -> 4 -> 5
        ListNode current = head;
        for (int i = 1; i <= 5; i++) {
            assertNotNull(current);
            assertEquals(i, current.getVal());
            current = current.getNext();
        }
        
        assertNull(current); // 最后一个节点的next应该为null
    }

    @Test
    void testPrintListNode() {
        // 测试打印链表
        ListNode head = listNodeService.initListNode(3);
        String result = listNodeService.printListNode(head);
        
        assertEquals("1 2 3 ", result);
        
        // 测试空链表
        String emptyResult = listNodeService.printListNode(null);
        assertEquals("", emptyResult);
    }

    @Test
    @DisplayName("测试删除倒数第N个节点")
    void testRemoveNthNode() {
        // 创建链表: 1 -> 2 -> 3 -> 4 -> 5
        ListNode head = listNodeService.initListNode(5);
        
        // 删除倒数第2个节点 (即节点4)
        ListNode newHead = listNodeService.removeNthNode(head, 2);
        String result = listNodeService.printListNode(newHead);
        
        // 预期结果: 1 -> 2 -> 3 -> 5
        assertEquals("1 2 3 5 ", result);
    }

    @Test
    @DisplayName("测试删除头节点")
    void testRemoveFirstNode() {
        // 创建链表: 1 -> 2 -> 3
        ListNode head = listNodeService.initListNode(3);
        
        // 删除倒数第3个节点 (即头节点1)
        ListNode newHead = listNodeService.removeNthNode(head, 3);
        String result = listNodeService.printListNode(newHead);
        
        // 预期结果: 2 -> 3
        assertEquals("2 3 ", result);
    }

    @Test
    @DisplayName("测试删除尾节点")
    void testRemoveLastNode() {
        // 创建链表: 1 -> 2 -> 3
        ListNode head = listNodeService.initListNode(3);
        
        // 删除倒数第1个节点 (即尾节点3)
        ListNode newHead = listNodeService.removeNthNode(head, 1);
        String result = listNodeService.printListNode(newHead);
        
        // 预期结果: 1 -> 2
        assertEquals("1 2 ", result);
    }

}