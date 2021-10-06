package com.company.leetcode.valid_parentheses;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidParenthesesTest {

    @Test
    @DisplayName("괄호들이 올바르게 사용되는지 여부 확인")
    public void isValid1() {
        // given
        String s = "()";

        // when
        ValidParentheses parentheses = new ValidParentheses();
        boolean result = parentheses.isValid(s);

        // then
        assertTrue(result);

    }

    @Test
    @DisplayName("괄호들이 올바르게 사용되는지 여부 확인")
    public void isValid2() {
        // given
        String s = "()[]{}{()[]}({{}})";

        // when
        ValidParentheses parentheses = new ValidParentheses();
        boolean result = parentheses.isValid(s);

        // then
        assertTrue(result);
    }

    @Test
    @DisplayName("괄호들이 올바르게 사용되는지 여부 확인")
    public void isValid3() {
        // given
        String s = "({)}";

        // when
        ValidParentheses parentheses = new ValidParentheses();
        boolean result = parentheses.isValid(s);

        // then
        assertFalse(result);
    }

    @Test
    @DisplayName("괄호들이 올바르게 사용되는지 여부 확인")
    public void isValid4() {
        // given
        String s = "(";

        // when
        ValidParentheses parentheses = new ValidParentheses();
        boolean result = parentheses.isValid(s);

        // then
        assertFalse(result);
    }

    @Test
    @DisplayName("괄호들이 올바르게 사용되는지 여부 확인")
    public void isValid5() {
        // given
        String s = ")";

        // when
        ValidParentheses parentheses = new ValidParentheses();
        boolean result = parentheses.isValid(s);

        // then
        assertFalse(result);
    }
}