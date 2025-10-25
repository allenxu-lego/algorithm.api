package com.playground.algorithmapi;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.playground.algorithmapi.model.ListNode;
import com.playground.algorithmapi.service.ListNodeServiceImpl;

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
        //sortStreamFunction();
        //exerciseStreamFunction();
        //mapLambdaStreamFunction("world");
        //collectorStreamFunction();
        practiseStreamFunction();

//		// 测试删除链表倒数第n个节点
//		System.out.println("=== 测试删除链表倒数第n个节点 ===");
//		ListNode head1 = service.initListNode(5);
//		System.out.println("原始链表: " + service.printListNode(head1));
//		ListNode result1 = service.removeNthNode(head1, 2);
//		System.out.println("删除倒数第2个节点后: " + service.printListNode(result1));
//
//		// 测试无重复字符的最长子串
//		System.out.println("\n=== 测试无重复字符的最长子串 ===");
//		String s = "abcabcbb";
//		int length = service.lengthOfLongestSubstring(s);
//		System.out.println("字符串 \"" + s + "\" 的无重复字符最长子串长度: " + length);
//
//		// 测试两数之和
//		System.out.println("\n=== 测试两数之和 ===");
//		int[] nums = {2, 7, 11, 15};
//		int target = 9;
//		int[] indices = service.twoSum(nums, target);
//		System.out.println("数组: " + java.util.Arrays.toString(nums));
//		System.out.println("目标值: " + target);
//		System.out.println("索引: " + java.util.Arrays.toString(indices));
//		System.out.println("值: [" + nums[indices[0]] + ", " + nums[indices[1]] + "]");
//
//		// 测试交替合并字符串
//		System.out.println("\n=== 测试交替合并字符串 ===");
//		String word1 = "abc";
//		String word2 = "pqr";
//		String merged = service.mergeAlternately(word1, word2);
//		System.out.println("word1: " + word1);
//		System.out.println("word2: " + word2);
//		System.out.println("合并结果: " + merged);
//
//        // 测试插入排序
//        System.out.println("\n=== 测试插入排序 ===");
//        int[] numInsertionSort ={9, 5, 1, 4, 3, 8, 2, 7, 6};
//        System.out.println("原始数组"+ java.util.Arrays.toString(numInsertionSort));
//        int[] sortedNumInsertionSort = service.insertionSort(numInsertionSort);
//        System.out.println("排序结果"+ java.util.Arrays.toString(sortedNumInsertionSort));


	}

	private static void exerciseStreamFunction(){
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		System.out.println("原始数组" + numbers.toString());
		List<Integer> filtered = numbers.stream().filter(f->f>5).collect(Collectors.toList());
		System.out.println("排序结果" + filtered.toString());
	}

    private static void sortStreamFunction(){
        List<Integer> numbers = Arrays.asList(4,3,5,6,1,2,9,7,10,16,12,11);
        System.out.println("原始数组"+ numbers.toString());
        List<Integer> sorted = numbers.stream().sorted().collect(Collectors.toList());
        System.out.println("ASC排序结果" + sorted.toString());
        sorted = numbers.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        System.out.println("DES排序结果" + sorted.toString());
    }

    private static void mapLambdaStreamFunction(String appended){
        List<String> strings = Arrays.asList("hello", "java", "python", "digital");
        System.out.println("原始数组"+ strings.toString());
        System.out.println("追加字符串" +
                strings.stream().map(
                        s-> s + " " + appended
                ).map(
                        s-> s + " another append"
                ).collect(Collectors.toList()));
    }

    private static void collectorStreamFunction(){
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

    private static void practiseStreamFunction(){
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
}