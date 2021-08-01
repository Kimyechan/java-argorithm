package com.company.baekjun.b1062;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main3 {
    static int n;
    static int k;
    static List<String> wordFragments = new ArrayList<>();
    static int maxReadableWord;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] row = br.readLine().split(" ");
        n = Integer.parseInt(row[0]);
        k = Integer.parseInt(row[1]);

        int flag = 0;
        for (char c : "antic".toCharArray()) {
            flag |= (1 << c - 'a');
        }

        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            s = s.substring(4, s.length() - 4);
            wordFragments.add(s);
        }

        combination(0, flag, 0);
        System.out.println(maxReadableWord);
    }

    private static void combination(int start, int flag, int count) {
        if (count == k - 5) {
            int readableCount = 0;
            for (String wordFragment : wordFragments) {
                boolean isReadable = true;
                for (char c : wordFragment.toCharArray()) {
                    if ((flag & (1 << (c - 'a'))) == 0) {
                        isReadable = false;
                        break;
                    }
                }

                if (isReadable) {
                    readableCount += 1;
                }
            }

            maxReadableWord = Math.max(maxReadableWord, readableCount);
            return;
        }

        for (int i = start; i < 26; i++) {
            if ((flag & (1 << i)) != 0) {
                continue;
            }

            combination(i + 1, flag | (1 << i), count + 1);
        }
    }
}
