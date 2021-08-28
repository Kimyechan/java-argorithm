package com.company.programmers.kakao2018.방금그곡;

import java.util.ArrayList;
import java.util.List;

public class Solution3 {

    static class Sound {
        String title;
        int playTime;

        public Sound(String title, int playTime) {
            this.title = title;
            this.playTime = playTime;
        }
    }

    public String solution(String m, String[] musicinfos) {
        String answer = "";
        String mEdit = editSound(m);

        List<Sound> candidates = new ArrayList<>();
        for (String musicinfo : musicinfos) {
            String[] infos = musicinfo.split(",");
            int startMinute = changeToMinute(infos[0]);
            int endMinute = changeToMinute(infos[1]);
            int playTime = endMinute - startMinute;

            String title = infos[2];
            String originalEdit = editOriginalSound(infos[3], playTime);

            if (originalEdit.contains(mEdit)) {
                candidates.add(new Sound(title, playTime));
            }
        }

        candidates.sort((s1, s2) -> s2.playTime - s1.playTime);

        if (candidates.size() == 0) {
            answer = "(None)";
        } else {
            answer = candidates.get(0).title;
        }

        return answer;
    }

    private String editOriginalSound(String original, int playTime) {
        String originalEdit = editSound(original);

        if (originalEdit.length() >= playTime) {
            originalEdit = originalEdit.substring(0, playTime);
        } else {
            StringBuilder sb = new StringBuilder();
            while (sb.length() < playTime) {
                sb.append(originalEdit);
            }
            originalEdit = sb.substring(0, playTime);
        }

        return originalEdit;
    }

    private int changeToMinute(String info) {
        String[] hM = info.split(":");

        int hourMinute = Integer.parseInt(hM[0]) * 60;
        int minute = Integer.parseInt(hM[1]);

        return hourMinute + minute;
    }

    private String editSound(String sound) {
        sound = sound.replaceAll("C#", "c");
        sound = sound.replaceAll("D#", "d");
        sound = sound.replaceAll("F#", "f");
        sound = sound.replaceAll("G#", "g");
        sound = sound.replaceAll("A#", "a");

        return sound;
    }

    public static void main(String[] args) {
        Solution3 solution = new Solution3();
//                System.out.println(solution.solution("ABC",	new String[]{"12:00,12:14,HELLO,C#DEFGAB", "13:00,13:05,WORLD,ABCDEF"}));
//        System.out.println(solution.solution("ABCDEFG",	new String[]{"12:00,12:14,WORLD,CDEFGAB", "12:00,12:14,HELLO,CDEFGAB"}));
        System.out.println(solution.solution("CC#BCC#BCC#BCC#B", new String[]{"03:00,03:30,FOO,CC#B", "04:00,04:08,BAR,CC#BCC#BCC#B"}));
    }
}