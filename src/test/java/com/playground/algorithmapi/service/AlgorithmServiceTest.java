package com.playground.algorithmapi.service;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.playground.algorithmapi.service.impl.AlgorithmServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AlgorithmServiceTest {
    private IAlgorithmService algorithmService;

    @BeforeEach
    void SetUp(){
        algorithmService = new AlgorithmServiceImpl();
    }


    @Test
    @DisplayName("测试lengthOfLongestSubstring方法")
    void testLengthOfLongestSubstring() {
        // 测试空字符串
        assertEquals(0, algorithmService.lengthOfLongestSubstring(""));

        // 测试单字符
        assertEquals(1, algorithmService.lengthOfLongestSubstring("a"));

        // 测试无重复字符
        assertEquals(3, algorithmService.lengthOfLongestSubstring("abc"));

        // 测试全部重复字符
        assertEquals(1, algorithmService.lengthOfLongestSubstring("aaaa"));

        // 测试部分重复字符 - 经典例子
        assertEquals(3, algorithmService.lengthOfLongestSubstring("abcabcbb"));
        assertEquals(1, algorithmService.lengthOfLongestSubstring("bbbbb"));
        assertEquals(3, algorithmService.lengthOfLongestSubstring("pwwkew"));

        // 测试包含空格的字符串
        assertEquals(3, algorithmService.lengthOfLongestSubstring("a b c"));
    }

    @Test
    @DisplayName("测试mergeAlternately方法")
    void testMergeAlternately(){
        assertEquals("acbde", algorithmService.mergeAlternately("ab", "cde"));
    }
}
