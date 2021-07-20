package com.company.baekjun.b1086;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main2 {
    public static void main(String args[]) {
        FastScanner sc = new FastScanner();

        int n = sc.nextInt();
        String[] a = new String[n];


        for (int i = 0; i < n; i++) {
            a[i] = sc.next();
        }


        int k = sc.nextInt();

        int[] remain = new int[n];
        for (int i = 0; i < n; i++) {
            remain[i] = getRemain(a[i], k);
        }

        int[] ten = new int[51];
        ten[0] = 1;
        for (int i = 1; i < 51; i++) {
            ten[i] = (ten[i-1] * 10) % k;
        }

        long[][] dp = new long[(1 << n)][k];

        dp[0][0] = 1;

        for (int i = 0; i < (1 << n); i++) {
            for (int c = 0; c < n; c++) {
                if ((i & (1 << c)) == 0) {
                    for (int j = 0; j < k; j++) {
                        int next = j * ten[a[c].length()];
                        next %= k;
                        next += remain[c];
                        next %= k;

                        dp[i | (1 << c)][next % k] += dp[i][j];
                    }
                }
            }
        }

        long q = dp[(1 << n) - 1][0];

        long p = 1;

        for (long i = 2; i <= n; i++) {
            p *= i;
        }

        long gcd = gcd(q, p);

        q /= gcd;
        p /= gcd;

        System.out.println(q + "/" + p);

    }

    public static long gcd(long a1, long a2) {
        if (a2 == 0) return a1;
        return gcd(a2, a1 % a2);
    }

    public static int getRemain(String x, int k) {
        int remain = 0;

        for (int i = 0; i < x.length(); i++) {
            remain = remain * 10 + (x.charAt(i) - '0');
            remain %= k;
        }

        return remain;
    }


    static class FastScanner {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer("");

        String next() {
            while (!st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }

}
