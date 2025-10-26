package com.playground.algorithmapi.service;

import com.playground.algorithmapi.model.TreeNode;

import java.util.List;

public interface IAlgorithmService {
    public int lengthOfLongestSubstring(String s);

    public int[] twoSum(int[] nums, int target);

    public String mergeAlternately(String word1, String word2);

    public int numTeams(int[] rating);

    public int[] insertionSort(int[] nums);

    public List<String> commonChars(String[] words);

    public List<String> commonCharsDuplicate(String[] words);

    public void exerciseStreamFunction();

    public void sortStreamFunction();

    public void mapLambdaStreamFunction(String appended);

    public void collectorStreamFunction();

    public void practiseStreamFunction();

    public String longestPalindrome(String s);

    public List<List<Integer>> levelOrderByQueue(TreeNode root);

    public List<List<Integer>> levelOrderByRecursion(TreeNode root);

    public List<Integer> preorderTraversal(TreeNode root);

}
