package com.company.programmers.kakao2022.s2;

public class Solution {
    public int solution(int n, int k) {
        int answer = 0;
        String kDigit = changeKDigit(n, k);
        int length = kDigit.length();

        for (int i = 1; i <= length; i++) {
            for (int j = 0; j < length - i + 1; j++) {
                String subKDigit = kDigit.substring(j, j + i);

                long kDigitLong = Long.parseLong(subKDigit);
                if (checkPrime(kDigitLong) && checkNotContainZero(subKDigit)) {
                    if (j == 0 && j + i < length && kDigit.charAt(j + i) == '0') {
                        answer++;
                    } else if (j + i == length && j - 1 >= 0 && kDigit.charAt(j - 1) == '0') {
                        answer++;
                    } else if (j + i < length && kDigit.charAt(j + i) == '0' && j - 1 >= 0 && kDigit.charAt(j - 1) == '0') {
                        answer++;
                    } else if (subKDigit.length() == length) {
                        answer++;
                    }
                }
            }
        }

        return answer;
    }

    private boolean checkNotContainZero(String subKDigit) {
        boolean result = true;
        for (int i = 0; i < subKDigit.length(); i++) {
            if (subKDigit.charAt(i) == '0') {
                result = false;
                break;
            }
        }

        return result;
    }

    private boolean checkPrime(long kDigitLong) {
        boolean result = true;
        if (kDigitLong == 1) {
            result = false;
        }

        for (int i = 2; i <= Math.sqrt(kDigitLong); i++) {
            if (kDigitLong % i == 0) {
                result = false;
                break;
            }
        }

        return result;
    }

    private String changeKDigit(int n, int k) {
        StringBuilder sb = new StringBuilder();
        while (n >= k) {
            sb.append(n % k);
            n /= k;
        }
        sb.append(n);

        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.solution(22 , 2));
//        System.out.println(solution.solution(437674 , 3));
//        System.out.println(solution.solution(110011, 10));
    }
}