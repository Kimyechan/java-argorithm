package com.company.programmers.kakao2021.메뉴리뉴얼;


import java.util.*;

class Solution {

    private List<String> combination = new ArrayList<>();
    private Map<String, Integer> courseMenu = new HashMap<>();

    public String[] solution(String[] orders, int[] course) {
        for (int menuLen : course) {
            for (String order : orders) {
                if (order.length() >= menuLen) {
                    char[] orderArray = order.toCharArray();
                    Arrays.sort(orderArray);

                    calcCombination(orderArray, menuLen, 0, "");
                }
            }
        }

        for (String menu : combination) {
            courseMenu.put(menu, courseMenu.getOrDefault(menu, 0) + 1);
        }

        List<String> result = new ArrayList<>();
        for (int count : course) {
            int maxEat = 2;

            for (Map.Entry<String, Integer> menuCountMap : courseMenu.entrySet()) {
                if (count == menuCountMap.getKey().length() && maxEat <= menuCountMap.getValue()) {
                    maxEat = menuCountMap.getValue();
                }
            }

            for (Map.Entry<String, Integer> menuCountMap : courseMenu.entrySet()) {
                if (count == menuCountMap.getKey().length() && maxEat <= menuCountMap.getValue()) {
                    result.add(menuCountMap.getKey());
                }
            }
        }

        Collections.sort(result);
        String[] answer = new String[result.size()];
        for (int i = 0; i < result.size(); i++) {
            answer[i] = result.get(i);
        }

        return answer;
    }

    private void calcCombination(char[] orderArray, int menuLen, int index, String result) {
        if (result.length() == menuLen) {
            combination.add(result);
            return;
        }

        for (int i = index; i < orderArray.length; i++) {
            calcCombination(orderArray, menuLen, i + 1, result + orderArray[i]);
        }
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.solution(new String[]{"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"}, new int[]{2, 3, 4}));
    }
}


class Permutation{

    static int[] arr;
    static boolean[] visited;
    static StringBuilder sb=new StringBuilder();

    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);

        int n=sc.nextInt();
        int m=sc.nextInt();

        arr=new int[m];
        visited=new boolean[n];

        dfs(n,m,0);
        System.out.println(sb);

    }
    public static void dfs(int n, int m, int depth){
        if(depth==m){
            for(int val:arr){
                sb.append(val).append(' ');
            }
            sb.append('\n');
            return;
        }
        for(int i=0; i<n; i++){
            if(!visited[i]){
                visited[i]=true;
                arr[depth]=i+1;
                dfs(n,m,depth+1);
                visited[i]=false;
            }
        }
    }
}


class Combination{

    static int[] arr;
    static boolean[] visited;
    static StringBuilder sb=new StringBuilder();

    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);

        int n=sc.nextInt();
        int m=sc.nextInt();

        arr=new int[m];
        visited=new boolean[n];

        dfs(n,m,0);
        System.out.println(sb);

    }

    public static void dfs(int n, int m, int depth){
        if(depth==m){
            for(int val:arr){
                sb.append(val).append(' ');
            }
            sb.append('\n');
            return;
        }
        for(int i=0; i<n; i++){
            if(!visited[i]){
                visited[i]=true;
                arr[depth]=i+1;
                dfs(n,m,depth+1);
                visited[i]=false;
            }
        }
    }
}
