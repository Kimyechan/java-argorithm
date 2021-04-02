package com.company.programmers.kakao2021.순위검색;

//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//class Solution {
//    private Map<String, List<Integer>> mapSearch = new HashMap<>();
//
//    public int[] solution(String[] info, String[] query) {
//        List<Integer> answer = new ArrayList<>();
//        createInfoMap(info);
//        for (String queryInfo : query) {
//            String[] queryInfoArray = queryInfo.split(" ");
//            StringBuilder sb = new StringBuilder();
//            for (int i = 0; i < queryInfoArray.length - 1; i++) {
//                sb.append(queryInfoArray[i]);
//            }
//            String key = sb.toString();
//            Integer score = Integer.valueOf(queryInfoArray[queryInfoArray.length - 1]);
//            if (mapSearch.containsKey(key)) {
//                List<Integer> scoreList = mapSearch.get(key);
//                int count = 0;
//                for (Integer personScore : scoreList) {
//                    if (personScore >= score) {
//                        count += 1;
//                    }
//                }
//                answer.add(count);
//            } else {
//                answer.add(0);
//            }
//        }
//
//        int[] answerArray = new int[answer.size()];
//        for (int i = 0; i < answer.size(); i++) {
//            answerArray[i] = answer.get(i);
//        }
//
//        return answerArray;
//    }
//
//    private void createInfoMap(String[] info) {
//        for (String personInfo : info) {
//            String[] personInfoArray = personInfo.split(" ");
//            StringBuilder sb = new StringBuilder("");
//            for (int i = 0; i < 3; i++) {
//                sb.append(personInfoArray[i] + "and");
//            }
//            sb.append(personInfoArray[3]);
//            String key = sb.toString();
//            if (!mapSearch.containsKey(key)) {
//                mapSearch.put(key, new ArrayList<>());
//                List<Integer> scoreList = mapSearch.get(key);
//                scoreList.add(Integer.valueOf(personInfoArray[4]));
//                mapSearch.put(key, scoreList);
//            } else {
//                List<Integer> scoreList = mapSearch.get(key);
//                scoreList.add(Integer.valueOf(personInfoArray[4]));
//                mapSearch.put(key, scoreList);
//            }
//
//        }
//    }
//
//    public static void main(String[] args) {
//        Solution solution = new Solution();
//        solution.solution(new String[]{"java backend junior pizza 150", "python frontend senior chicken 210", "python frontend senior chicken 150", "cpp backend senior pizza 260", "java backend junior chicken 80", "python backend senior chicken 50"},
//                new String[]{"java and backend and junior and pizza 100", "python and frontend and senior and chicken 200", "cpp and - and senior and pizza 250", "- and backend and senior and - 150", "- and - and - and chicken 100", "- and - and - and - 150"});
//    }
//}

//import java.util.ArrayList;
//import java.util.Map;
//import java.util.HashMap;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Collections;
//import java.util.Iterator;
//
//class Solution {
//    static ArrayList<ArrayList<Integer>> list=new ArrayList<>();
//    static Map<String,List<Integer>> map=new HashMap<>();
//    static int[] answer;
//
//    static void dfs(String str,int depth,String[] info){
//        if(depth==4){
//            if(map.containsKey(str)==false){
//                List<Integer> list=new ArrayList<>();
//                list.add(Integer.parseInt(info[4]));
//                map.put(str,list);
//            }else{
//                map.get(str).add(Integer.parseInt(info[4]));
//            }
//            return;
//        }
//
//        dfs(str+"-",depth+1,info);
//        dfs(str+info[depth],depth+1,info);
//    }
//
//    static void setInfo(String[] info){
//        for(int i=0;i<info.length;i++){
//            dfs("",0,info[i].split(" "));
//        }
//
//        Iterator<String> it= map.keySet().iterator();
//        while(it.hasNext()){
//            String key=it.next();
//            List<Integer> li=map.get(key);
//            Collections.sort(li);
//        }
//    }
//
//    static int counting(String str,int score){
//        if(map.containsKey(str)==false) return 0;
//
//        List<Integer> list=map.get(str);
//        int start=0,end=list.size()-1;
//
//        while(start<=end){
//            int mid=(start+end)/2;
//            if(list.get(mid)<score){
//                start=mid+1;
//            }else{
//                end=mid-1;
//            }
//        }
//
//        return list.size()-start;
//    }
//
//    static void makeAnswer(String[] query){
//        for(int i=0;i<query.length;i++){
//            String str="";
//            String[] arr=query[i].split(" ");
//
//            for(int j=0;j<arr.length-1;j++){
//                if(arr[j].equals("and")) continue;
//                str+=arr[j];
//            }
//            answer[i]=counting(str,Integer.parseInt(arr[arr.length-1]));
//        }
//    }
//
//    public int[] solution(String[] info, String[] query) {
//        answer = new int[query.length];
//        setInfo(info);
//        makeAnswer(query);
//        return answer;
//    }
//}


import java.util.*;

public class Solution {
    private Map<String, List<Integer>> map = new HashMap<>();

    public int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];
        createMapInfo(info);
        for (Map.Entry<String, List<Integer>> entry : map.entrySet()) {
            List<Integer> list = entry.getValue();
            Collections.sort(list);
            map.put(entry.getKey(), list);
        }

        for (int i = 0; i < query.length; i++) {
            String newQuery = query[i].replaceAll(" and ", " ");
            String[] queryInfos = newQuery.split(" ");

            StringBuilder key = new StringBuilder();
            for (int j = 0; j < queryInfos.length - 1; j++) {
                key.append(queryInfos[j]);
            }

            if (map.containsKey(key.toString())) {
                List<Integer> scoreList = map.get(key.toString());
                Integer count = calcCountPerson(scoreList, Integer.valueOf(queryInfos[queryInfos.length - 1]));
                answer[i] = count;
            } else {
                answer[i] = 0;
            }
        }

        return answer;
    }

    private Integer calcCountPerson(List<Integer> scoreList, Integer standard) { // lower bound
        int start = 0;
        int end = scoreList.size();

        while (start < end) {
            int mid = (start + end) / 2;

            if (scoreList.get(mid) >= standard) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }

        return (scoreList.size() - 1) - end + 1;
    }

    private void createMapInfo(String[] info) {
        for (String personInfo : info) {
            String[] personInfos = personInfo.split(" ");
            dfs(personInfos, 0, "");
        }
    }

    private void dfs(String[] personInfos, int count, String key) {
        if (count == 4) {
            if (!map.containsKey(key)) {
                List<Integer> scoreList = new ArrayList<>();
                scoreList.add(Integer.valueOf(personInfos[personInfos.length - 1]));
                map.put(key, scoreList);
            } else {
                List<Integer> scoreList = map.get(key);
                scoreList.add(Integer.valueOf(personInfos[personInfos.length - 1]));
                map.put(key, scoreList);
            }
            return;
        }

        dfs(personInfos, count + 1, key + "-");
        dfs(personInfos, count + 1, key + personInfos[count]);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.solution(new String[]{"java backend junior pizza 150", "python frontend senior chicken 210", "python frontend senior chicken 150", "cpp backend senior pizza 260", "java backend junior chicken 80", "python backend senior chicken 50"},
                new String[]{"java and backend and junior and pizza 100", "python and frontend and senior and chicken 200", "cpp and - and senior and pizza 250", "- and backend and senior and - 150", "- and - and - and chicken 100", "- and - and - and - 150"});
    }
}































