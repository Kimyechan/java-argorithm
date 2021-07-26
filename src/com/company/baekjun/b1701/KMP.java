package com.company.baekjun.b1701;

// youtube link = https://www.youtube.com/watch?v=yWWbLrV4PZ8&ab_channel=%EB%8F%99%EB%B9%88%EB%82%98
public class KMP {
    public static int[] makeTable(String pattern) {
        int patternSize = pattern.length();
        int[] table = new int[patternSize];

        int j = 0;
        for (int i = 1; i < patternSize; i++) {
            while (j > 0 && pattern.charAt(i) != pattern.charAt(j)) {
                j = table[j - 1];
            }
            if (pattern.charAt(i) == pattern.charAt(j)) {
                table[i] = ++j;
            }
        }

        return table;
    }

    public static void kmp(String parent, String pattern) {
        int[] table = makeTable(pattern);
        int parentSize = parent.length();
        int patternSize = pattern.length();
        int j = 0;

        for (int i = 0; i < parentSize; i++) {
            while (j > 0 && parent.charAt(i) != pattern.charAt(j)) {
                j = table[j - 1];
            }
            if (parent.charAt(i) == pattern.charAt(j)) {
                if (j == patternSize - 1) {
                    System.out.printf("%d번째에서 찾았습니다. \n", (i - patternSize + 1) + 1);
                } else {
                    j++;
                }
            }
        }
    }
}
