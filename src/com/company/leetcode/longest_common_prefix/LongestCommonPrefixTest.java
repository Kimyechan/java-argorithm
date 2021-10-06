package com.company.leetcode.longest_common_prefix;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LongestCommonPrefixTest {

    @Test
    @DisplayName("접두사중 공통으로 일치하는 가장 긴 수열")
    public void longestCommonPrefix() {
        // given
        String[] strs = new String[]{"flower","flow","flight"};

        // when
        LongestCommonPrefix prefix = new LongestCommonPrefix();
        String result = prefix.longestCommonPrefix(strs);

        // then
        assertEquals(result, "fl");
    }

}