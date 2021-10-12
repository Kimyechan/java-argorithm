package com.company.leetcode.length_of_last_word;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Solution58Test {

    @Test
    @DisplayName("문장중 마지막 단어의 길이 구하기")
    public void findLengthOfLastWord() {
        // given
        String s = "abc ccd  ssss   ddddd ";

        // when
        Solution58 solution58 = new Solution58();
        int result = solution58.lengthOfLastWord(s);

        // then
        assertEquals(result, 5);
    }
}