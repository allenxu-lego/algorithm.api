package com.playground.algorithmapi.service;

import com.playground.algorithmapi.model.TreeNode;

import java.util.List;

public interface IAlgorithmService {
     int lengthOfLongestSubstring(String s);

     int[] twoSum(int[] nums, int target);

     String mergeAlternately(String word1, String word2);

     int numTeams(int[] rating);

     int[] insertionSort(int[] nums);

     List<String> commonChars(String[] words);

     List<String> commonCharsDuplicate(String[] words);

     void exerciseStreamFunction();

     void sortStreamFunction();

     void mapLambdaStreamFunction(String appended);

     void collectorStreamFunction();

     void practiseStreamFunction();

     String longestPalindrome(String s);

     List<List<Integer>> levelOrderByQueue(TreeNode root);

     List<List<Integer>> levelOrderByRecursion(TreeNode root);

     List<Integer> preorderTraversal(TreeNode root);

}
