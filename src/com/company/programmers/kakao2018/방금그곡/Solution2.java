package com.company.programmers.kakao2018.방금그곡;

import java.util.ArrayList;
import java.util.List;

public class Solution2 {
    List<Song> candidate = new ArrayList<>();

    static class Song {
        int playTime;
        String title;

        public Song(int playTime, String title) {
            this.playTime = playTime;
            this.title = title;
        }
    }

    public String solution(String m, String[] musicinfos) {
        String answer = "";
        m = changeToSound(m);
        for (String musicinfo : musicinfos) {
            String[] infos = musicinfo.split(",");
            int startMinute = changeToMinute(infos[0]);
            int endMinute = changeToMinute(infos[1]);
            int timeLen = endMinute - startMinute;
            String sound = changeToSound(infos[3]);

            String soundFit = fitSound(sound, timeLen);
            if (soundFit.contains(m)) {
                candidate.add(new Song(timeLen, infos[2]));
            }
        }

        candidate.sort((s1, s2) -> (s1.playTime - s2.playTime) * -1);

        if (candidate.size() == 0) {
            answer = "(None)";
        } else {
            answer = candidate.get(0).title;
        }

        return answer;
    }

    private String fitSound(String sound, int timeLen) {
        StringBuilder soundFit = new StringBuilder();
        for (int i = 0; i < timeLen; i++) {
            soundFit.append(sound.charAt(i % sound.length()));
        }

        return soundFit.toString();
    }

    private String changeToSound(String sound) {
        sound = sound.replaceAll("C#", "c");
        sound = sound.replaceAll("D#", "d");
        sound = sound.replaceAll("E#", "e");
        sound = sound.replaceAll("F#", "f");
        sound = sound.replaceAll("G#", "g");
        sound = sound.replaceAll("A#", "a");

        return sound;
    }

    private int changeToMinute(String time) {
        String[] hourMinute = time.split(":");

        return 60 * Integer.parseInt(hourMinute[0]) + Integer.parseInt(hourMinute[1]);
    }

    public static void main(String[] args) {
        Solution2 solution = new Solution2();
//        System.out.println(solution.solution("ABCDEFG", new String[]{"12:00,12:14,HELLO,CDEFGAB", "13:00,13:05,WORLD,ABCDEF"}));
        System.out.println(solution.solution("CC#BCC#BCC#BCC#B", new String[]{"03:00,03:30,FOO,CC#B", "04:00,04:08,BAR,CC#BCC#BCC#B"}));
//        System.out.println(solution.solution("ABC", new String[]{"12:00,12:14,HELLO,C#DEFGAB", "13:00,13:05,WORLD,ABCDEF"}));
    }
}