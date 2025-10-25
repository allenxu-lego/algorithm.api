package com.playground.algorithmapi.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
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

    @Override
    public int[] twoSum(int[] nums, int target){
         Map<Integer, Integer> resultMap = new HashMap<>();   
         for(int i=0; i<nums.length; i++){
            int remaining = target - nums[i];
            if(resultMap.containsKey(remaining)){
                return new int[]{resultMap.get(remaining),i};
            }
            resultMap.put(nums[i],i);
         }
         

        return new int[]{};
    }

    @Override
    public String mergeAlternately(String word1, String word2) {
        StringBuilder result = new StringBuilder();
        int i=0;
        while(i<word1.length() && i<word2.length()){
            result.append(String.valueOf(word1.charAt(i)));
            result.append(String.valueOf(word2.charAt(i)));
            i++;
        }
        if(i>=word1.length())
            result.append(word2.substring(i));
        else
            result.append(word1.substring(i));
        
        return result.toString();

    }

    @Override
    public int numTeams(int[] rating) { 
        int n = rating.length;
        int totalTeams = 0;

        for(int j=1;j<n-1;j++){
            int leftLess = 0, leftGreater = 0;
            int rightLess = 0, rightGreater = 0;

            for(int i=0;i<j;i++){
                if(rating[i]<rating[j])
                    leftLess++;
                else
                    leftGreater++;
            }

            for(int k=j+1;k<n;k++){
                if(rating[k]<rating[j])
                    rightLess++;
                else
                    rightGreater++;
            }

            totalTeams += leftLess*rightGreater +leftGreater*rightLess;
        }
        return totalTeams;
    }


    @Override
    public int[] insertionSort(int[] nums){
        
        String[] test = new String[]{"a","b","c","d"};
        
        for(int i=1; i<nums.length; i++){
            int key = nums[i];
            int j = i-1;
            while(j>=0 && nums[j] > key ){
                nums[j+1] = nums[j];
                j--;
            }
            nums[j+1] = key;
        }
        return nums;
    }

    @Override
    public List<String> commonChars(String[] words) {
        List<String> result = new ArrayList<>();
        Map<Character,Set<Integer>>countMap = new HashMap<>();
        for(int i=0;i<words.length; i++){
            String word = words[i];
            for(char c : word.toCharArray()){
                countMap.computeIfAbsent(c, k-> new HashSet<>()).add(i);                
            }
        }
        
        for(Character c : countMap.keySet()){
            Set<Integer> set = countMap.get(c);
            if(set.size() == words.length){
                result.add(String.valueOf(c));
            }
        }
        return result;
    }

    @Override
    public List<String> commonCharsDuplicate(String[] words) {
        List<String> result = new ArrayList<>();
        Map<Character,Integer> firstWordCountMap = new HashMap<>();

        char[] firstWordChars = words[0].toCharArray();
        for(char c : firstWordChars){
            firstWordCountMap.put(c, firstWordCountMap.getOrDefault(c, 0) + 1);
        }
        
        for(char c: firstWordCountMap.keySet()){
            int times = firstWordCountMap.get(c);
            for(int i=1;i<words.length;i++){
                int matchTimes = 0;
                for(char c2 : words[i].toCharArray()){
                    if(c2==c){
                        matchTimes++;
                    }
                }
                times = Math.min(times,matchTimes);
                if(times == 0)
                    break;
            }
            while(times > 0){
                result.add(String.valueOf(c));
                times--;
            }
        }
        return result;
    }
}