package com.playground.algorithmapi.service.impl;
import com.playground.algorithmapi.model.*;
import com.playground.algorithmapi.service.IAlgorithmService;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Service
public class AlgorithmServiceImpl implements IAlgorithmService {

    @Override
    public int lengthOfLongestSubstring(String s) {

        if(s==null || s.isEmpty()){
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
            result.append(word1.charAt(i));
            result.append(word2.charAt(i));
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

    @Override
    public void exerciseStreamFunction(){
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        System.out.println("原始数组" + numbers);
        List<Integer> filtered = numbers.stream().filter(f->f>5).toList();
        System.out.println("排序结果" + filtered);
    }

    @Override
    public void sortStreamFunction(){
        List<Integer> numbers = Arrays.asList(4,3,5,6,1,2,9,7,10,16,12,11);
        System.out.println("原始数组"+ numbers);
        List<Integer> sorted = numbers.stream().sorted().collect(Collectors.toList());
        System.out.println("ASC排序结果" + sorted);
        sorted = numbers.stream().sorted(Comparator.reverseOrder()).toList();
        System.out.println("DES排序结果" + sorted);
    }

    @Override
    public void mapLambdaStreamFunction(String appended){
        List<String> strings = Arrays.asList("hello", "java", "python", "digital");
        System.out.println("原始数组"+ strings.toString());
        System.out.println("追加字符串" +
                strings.stream().map(
                        s-> s + " " + appended
                ).map(
                        s-> s + " another append"
                ).collect(Collectors.toList()));
    }

    @Override
    public void collectorStreamFunction(){
        List<String> words = Arrays.asList("apple", "banana", "cherry", "date", "elderberry","banana");
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // 1. toList
        List<String> toList = words.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        System.out.println("1. toList: " + toList);

        // 2. toSet
        Set<String> toSet = words.stream()
                .collect(Collectors.toSet());
        System.out.println("2. toSet: " + toSet);
        System.out.println("2. toSet Sorted: " + toSet.stream().sorted().collect(Collectors.toList()));

        // 3. joining
        System.out.println("3. joining: " +
                words.stream()
                        .collect(Collectors
                                .joining("\",\"","[\"","\"]")));

        //4. groupingBy
        System.out.println("4. groupingBy: " + words.stream()
                .collect(Collectors.groupingBy(String::length)));
        //4. grouping with downstream collector
        System.out.println("4. groupingBy with downstream collector: " + words.stream()
                .collect(Collectors.groupingBy(String::length, Collectors.toSet())));

        //5. partitioningBy
        System.out.println("5. partitioningBy: " + words.stream()
                .collect(Collectors.partitioningBy(s->s.charAt(0) >'b'))
        );

        //6. toMap
        System.out.println("6. toMap: " + words.stream()
                .collect(Collectors.toMap(
                        s -> s.substring(0, Math.min(3,s.length())),
                        s->new ArrayList<>(Arrays.asList(s)),
                        (existinglist, newlist) -> {
                            existinglist.addAll(newlist);
                            return existinglist;
                        }
                )));

        //7. statistics
        DoubleSummaryStatistics stats = numbers.stream()
                .collect(Collectors.summarizingDouble(Integer::doubleValue));
        System.out.println("7. stats: " + stats);

        // 8. counting
        Long count = words.stream()
                .collect(Collectors.counting());
        System.out.println("8. count: " + count);

        // 9. averagingInt
        Double average = numbers.stream()
                .collect(Collectors.averagingInt(Integer::intValue));
        System.out.println("9. average: " + average);

        //10. custom
        System.out.println("10. custom: "+ words.stream()
                .collect(Collectors.collectingAndThen(Collectors.joining("|"),s->"Custom: " + s)));

        //11. 适用于并行流的三参数形式
        Integer sum = numbers.parallelStream()
                .reduce(0,
                        (a, b) -> a + b,           // 累积器
                        (a, b) -> a + b);          // 组合器
        System.out.println("11. reduce : " + sum);

        //12. distinct
        System.out.println("12. distinct : " +words.stream().distinct().collect(Collectors.toList()));

        //13. anyMatch & allMatch
        System.out.println("13. allMatch : " + words.stream().anyMatch(s->s.length() > 8));
        System.out.println("13. allMatch : " + words.stream().allMatch(s->s instanceof String));

        //14. flatMap
        List<List<String>> listOfLists = Arrays.asList(
                Arrays.asList("apple","orange","pear"),
                Arrays.asList("banana","watermelon","mango"),
                Arrays.asList("apple","pineapple","peach")
        );
        System.out.println("14. flatMap : " +
                listOfLists.stream().flatMap(l->l.stream())
                        .distinct()
                        .sorted()
                        .collect(Collectors.toList()));
    }

    @Override
    public void practiseStreamFunction(){
        //1. 给定一个整数列表，返回其中所有偶数的平方:
        List<Integer> numbers1 = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        System.out.println("1. 给定一个整数列表，返回其中所有偶数的平方:" +
                numbers1.stream()
                        .filter(n->n%2==0)
                        .map(n->n*n)
                        .collect(Collectors.toList())
        );

        //2. 给定一个字符串列表，返回按长度排序且不重复的字符串
        // 预期结果: ["kiwi", "apple", "orange", "banana"] (按长度升序)
        List<String> words2 = Arrays.asList("apple", "banana", "apple", "orange", "banana", "kiwi");
        System.out.println("2. 给定一个字符串列表，返回按长度排序且不重复的字符串" +
                words2.stream()
                        .distinct()
                        .sorted(Comparator.comparing(String::length))
                        .collect(Collectors.toList())
        );

        //3.  计算一个整数列表的平均值、最大值和最小值
        List<Integer> numbers3 = Arrays.asList(10, 20, 30, 40, 50);
        var stats = numbers3.stream()
                .collect(Collectors.summarizingDouble(v->v.doubleValue()));
        System.out.println("3. 最大值：" + stats.getMax() +
                " 最小值：" + stats.getMin() +
                " 平均值：" + stats.getAverage()
        );

        //4. 将单词按首字母分组
        List<String> words4 = Arrays.asList("apple", "banana", "apricot", "blueberry", "cherry", "avocado");
        System.out.println("4. 将单词按首字母分组: " +
                words4.stream()
                        .collect(Collectors.groupingBy(s->String.valueOf(s.charAt(0))))
        );

        //5. 给定多个列表，将其合并并去重
        List<List<Integer>> listOfLists5 = Arrays.asList(
                Arrays.asList(1, 2, 3),
                Arrays.asList(2, 3, 4),
                Arrays.asList(3, 4, 5)
        );
        System.out.println("5. 给定多个列表，将其合并并去重: " +
                listOfLists5.stream()
                        .flatMap(l->l.stream())
                        .distinct()
                        .collect(Collectors.toList())
        );

        //6. 检查列表中是否存在长度大于5的字符串，且所有字符串都不为空
        List<String> words6 = Arrays.asList("hello", "world", "stream", "api");
        System.out.println("6. 是否存在长度大于5的字符串: " +
                words6.stream().anyMatch(s-> s.length()>5) +
                " 所有字符串都不为空: " +
                words6.stream().allMatch(s->s != null)
        );

        //7. 将字符串列表转换为一个Map，键为字符串，值为字符串长度
        List<String> words7 = Arrays.asList("java", "stream", "api", "example");
        var result7 = words7.stream().collect(Collectors.toMap(
                s->s,
                s->s.length(),
                (existing, replacement) -> replacement
        ));
        System.out.println("7. 将字符串列表转换为一个Map，键为字符串，值为字符串长度: " +result7);
        // 预期结果: {java=4, stream=6, api=3, example=7}

        //11. 给定多个流，将它们连接并按自定义规则排序
        Stream<String> stream1 = Stream.of("A", "B", "C");
        Stream<String> stream2 = Stream.of("X", "Y", "Z");
        Stream<String> stream3 = Stream.of("M", "N", "O");
        var listOfLists11 = Arrays.asList(stream1, stream2, stream3);
        System.out.println("11. 给定多个流，将它们连接并按自定义规则排序: " +
                listOfLists11.stream()
                        .map(s->s.sorted(Comparator.reverseOrder()))
                        .flatMap(s->s)
                        .collect(Collectors.toList())
        );

        //12. 将一个大列表分成指定大小的批次
        List<Integer> largeList12 = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14);
        int batchSize = 5;

        List<List<Integer>> batches = IntStream.iterate(0, i->i<largeList12.size(), i->i+batchSize)
                .mapToObj(i->largeList12.subList(i, Math.min(i+batchSize, largeList12.size())))
                .collect(Collectors.toList());
        System.out.println("12. 将一个大列表分成指定大小的批次: " + batches);
    }

    @Override
    public String longestPalindrome(String s){
        List<String> results = new ArrayList<>();
        for(int i=0;i<s.length();i++) {
            StringBuilder candidate = new StringBuilder(String.valueOf(s.charAt(i)));
            for(int j=i+1;j<s.length();j++){
                candidate.append(String.valueOf(s.charAt(j)));

                if(candidate.toString().equals(new StringBuilder(candidate.toString()).reverse().toString())){
                    results.add(candidate.toString());
                }
            }
        }
        if(results.size() > 0){
            return results.stream()
                    .sorted(Comparator.comparing(result->result.length()))
                    .collect(Collectors.toList()).get(0);
        }else {
            return "";
        }
    }

    @Override
    public List<List<Integer>> levelOrderByQueue(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if(root == null){
            return result;
        }

        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        while(!queue.isEmpty()){
            int size = queue.size();
            List<Integer> level = new ArrayList<>();
            for(int i=0; i<size; i++){
                TreeNode node = queue.poll();
                level.add(node.getVal());
                if(node.getLeft() !=null) queue.offer(node.getLeft());
                if(node.getRight() != null) queue.offer(node.getRight());
            }
            result.add(level);
        }
        return result;
    }

    @Override
    public List<List<Integer>> levelOrderByRecursion(TreeNode root){
        List<List<Integer>> results = new ArrayList<>();
        dfs(root, 0, results);
        return results;
    }

    private void dfs(TreeNode node, int depth, List<List<Integer>> result){
        if(node == null) return;
        if(result.size() == depth){
            result.add(new ArrayList<>());
        }
        result.get(depth).add(node.getVal());
        if(node.getLeft()!=null) dfs(node.getLeft(), depth+1, result);
        if(node.getRight()!=null) dfs(node.getRight(),depth+1, result);
    }

    @Override
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        preorder(root, result);
        return result;
    }

    private void preorder(TreeNode node, List<Integer>result){
        if(node == null) return;
        result.add(node.getVal());
        preorder(node.getLeft(), result);
        preorder(node.getRight(), result);
    }
}
