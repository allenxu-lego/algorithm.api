package com.playground.algorithmapi;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.playground.algorithmapi.model.TreeNode;
import com.playground.algorithmapi.service.impl.AlgorithmServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.playground.algorithmapi.service.impl.ListNodeServiceImpl;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		// 检查是否有特殊参数来运行调试功能
		if (args.length > 0 && "--debug".equals(args[0])) {
			mainDebug(new String[0]);
		} else {
			SpringApplication.run(DemoApplication.class, args);
		}
	}

	/**
	 * 用于调试单个功能的main方法
	 * 可以直接运行此方法来测试特定算法，而无需启动整个Spring Boot应用
	 */
	public static void mainDebug(String[] args) {
		ListNodeServiceImpl service = new ListNodeServiceImpl();
        AlgorithmServiceImpl algorithmSvc = new AlgorithmServiceImpl();


		// 测试删除链表倒数第n个节点
//		System.out.println("=== 测试删除链表倒数第n个节点 ===");
//		ListNode head1 = service.initListNode(5);
//		System.out.println("原始链表: " + service.printListNode(head1));
//		ListNode result1 = service.removeNthNode(head1, 2);
//		System.out.println("删除倒数第2个节点后: " + service.printListNode(result1));
		// 测试无重复字符的最长子串
//		System.out.println("\n=== 测试无重复字符的最长子串 ===");
//		String s = "abcabcbb";
//		int length = algorithmSvc.lengthOfLongestSubstring(s);
//		System.out.println("字符串 \"" + s + "\" 的无重复字符最长子串长度: " + length);
//
		// 测试两数之和
//		System.out.println("\n=== 测试两数之和 ===");
//		int[] nums = {2, 7, 11, 15};
//		int target = 9;
//		int[] indices = algorithmSvc.twoSum(nums, target);
//		System.out.println("数组: " + java.util.Arrays.toString(nums));
//		System.out.println("目标值: " + target);
//		System.out.println("索引: " + java.util.Arrays.toString(indices));
//		System.out.println("值: [" + nums[indices[0]] + ", " + nums[indices[1]] + "]");
//
//		// 测试交替合并字符串
//		System.out.println("\n=== 测试交替合并字符串 ===");
//		String word1 = "abc";
//		String word2 = "pqr";
//		String merged = algorithmSvc.mergeAlternately(word1, word2);
//		System.out.println("word1: " + word1);
//		System.out.println("word2: " + word2);
//		System.out.println("合并结果: " + merged);
//
//        // 测试插入排序
//        System.out.println("\n=== 测试插入排序 ===");
//        int[] numInsertionSort ={9, 5, 1, 4, 3, 8, 2, 7, 6};
//        System.out.println("原始数组"+ java.util.Arrays.toString(numInsertionSort));
//        int[] sortedNumInsertionSort = algorithmSvc.insertionSort(numInsertionSort);
//        System.out.println("排序结果"+ java.util.Arrays.toString(sortedNumInsertionSort));

        //algorithmSvc.sortStreamFunction();
        //algorithmSvc.exerciseStreamFunction();
        //algorithmSvc.mapLambdaStreamFunction("world");
        //algorithmSvc.collectorStreamFunction();
        //algorithmSvc.practiseStreamFunction();
        //System.out.println(algorithmSvc.longestPalindrome("cbbd"));

        //Binary tree level order traversal
        TreeNode root1 = TreeNode.builder()
                .val(3)
                .left(TreeNode.builder()
                        .val(9)
                        .build())
                .right(TreeNode.builder()
                        .val(20)
                        .left(TreeNode.builder().val(15).build())
                        .right(TreeNode.builder().val(7).build())
                        .build())
                .build();
        System.out.println(algorithmSvc.levelOrderByRecursion(root1));
        System.out.println(algorithmSvc.levelOrderByQueue(root1));

        //Binary tree pre order traversal
        //Input: root = [1,null,2,3]
        TreeNode root2 = TreeNode.builder()
                .val(1)
                .left(null)
                .right(TreeNode.builder()
                        .val(2).right(null)
                        .left(
                                TreeNode.builder()
                                        .val(3)
                                        .left(null)
                                        .right(null).build()
                        ).build()
                ).build();
        System.out.println(algorithmSvc.preorderTraversal(root2));
	}


}