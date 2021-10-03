package com.company.leetcode.roman_to_integer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RomanToIntTest {

    @Test
    @DisplayName("로마 숫자를 10진수로 변환 - III")
    public void romanToIntIII() {
        // given
        String s = "III";

        RomanToInt romanToInt = new RomanToInt();
        int result = romanToInt.romanToInt(s);

        assertEquals(result, 3);
    }

    @Test
    @DisplayName("로마 숫자를 10진수로 변환 - IV")
    public void romanToIntIV() {
        // given
        String s = "IV";

        RomanToInt romanToInt = new RomanToInt();
        int result = romanToInt.romanToInt(s);

        assertEquals(result, 4);
    }

    @Test
    @DisplayName("로마 숫자를 10진수로 변환 - IX")
    public void romanToIntIX() {
        // given
        String s = "IX";

        RomanToInt romanToInt = new RomanToInt();
        int result = romanToInt.romanToInt(s);

        assertEquals(result, 9);
    }

    @Test
    @DisplayName("로마 숫자를 10진수로 변환 - LVIII")
    public void romanToIntLVIII() {
        // given
        String s = "LVIII";

        RomanToInt romanToInt = new RomanToInt();
        int result = romanToInt.romanToInt(s);

        assertEquals(result, 58);
    }

    @Test
    @DisplayName("로마 숫자를 10진수로 변환 - MCMXCIV")
    public void romanToIntLVMCMXCIV() {
        // given
        String s = "MCMXCIV";

        RomanToInt romanToInt = new RomanToInt();
        int result = romanToInt.romanToInt(s);

        assertEquals(result, 1994);
    }
}