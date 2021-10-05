package com.company.baekjun.b11003;

import java.io.*;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {

    static class Num {
        int idx;
        int num;

        public Num(int idx, int num) {
            this.idx = idx;
            this.num = num;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        Deque<int[]> deque = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            int temp = Integer.parseInt(st.nextToken());;

            while (!deque.isEmpty() && deque.getLast()[1] > temp) {
                deque.removeLast();
            }
            deque.add(new int[]{i, temp});
            if (deque.getFirst()[0] <= i - l) {
                deque.removeFirst();
            }
            bw.write(deque.getFirst()[1] + " "); // deque에는 2개 이하의 값만 남음
        }

        bw.flush();
        bw.close();
        br.close();
    }
}