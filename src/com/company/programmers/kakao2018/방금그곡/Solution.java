package com.company.programmers.kakao2018.방금그곡;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    static class Song {
        int order;
        int playTime;
        String title;

        public Song(int order, int playTime, String title) {
            this.order = order;
            this.playTime = playTime;
            this.title = title;
        }
    }

    public String solution(String m, String[] musicinfos) {
        String answer = "";
        String soundListen = changeNewVolume(m);

        List<Song> songList = new ArrayList<>();
        for (String musicInfo : musicinfos) {
            String[] infos = musicInfo.split(",");
            int startMinute = changeTimeToMinute(infos[0]);
            int endMinute = changeTimeToMinute(infos[1]);
            String title = infos[2];
            String volume = infos[3];

            String newVolume = changeNewVolume(volume);
            String volumeExtend = extendVolume(startMinute, endMinute, newVolume);
            if (checkThatSong(soundListen, volumeExtend)) {
                songList.add(new Song(songList.size(), endMinute - startMinute, title));
            }
        }

        songList.sort((s1, s2) -> {
            if (s1.playTime == s2.playTime) {
                return s1.order - s2.order;
            } else {
                return (s1.playTime - s2.playTime) * -1;
            }
        });

        if (songList.size() == 0) {
            answer = "(None)";
        } else {
            answer = songList.get(0).title;
        }

        return answer;
    }

    private boolean checkThatSong(String soundListen, String volumeExtend) {
        int len1 = soundListen.length();
        int len2 = volumeExtend.length();

        for (int i = 0; i < len2 - len1 + 1; i++) {
            int equalCount = 0;
            for (int j = 0; j < len1; j++) {
                if (soundListen.charAt(j) != volumeExtend.charAt(i + j)) {
                    break;
                } else {
                    equalCount++;
                }
            }

            if (equalCount == len1) {
                return true;
            }
        }

        return false;
    }

    private String changeNewVolume(String volume) {
        int idx = 0;
        StringBuilder sb = new StringBuilder();
        while (idx < volume.length()) {
            if (idx + 1 < volume.length() && volume.charAt(idx + 1) == '#') {
                String temp = String.valueOf(volume.charAt(idx)).toLowerCase();
                sb.append(temp);
                idx += 2;
                continue;
            }

            sb.append(volume.charAt(idx));
            idx += 1;
        }

        return sb.toString();
    }

    private String extendVolume(int startMinute, int endMinute, String volume) {
        StringBuilder volumeExtend = new StringBuilder();

        int timeInterval = endMinute - startMinute;
        if (volume.length() > timeInterval) {
            volumeExtend = new StringBuilder(volume.substring(0, timeInterval));
        } else {
            while (volumeExtend.length() < timeInterval) {
                volumeExtend.append(volume);
            }
        }

        return volumeExtend.toString();
    }

    private int changeTimeToMinute(String time) {
        String[] hourMinute = time.split(":");
        return 60 * Integer.parseInt(hourMinute[0]) + Integer.parseInt(hourMinute[1]);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
//        System.out.println(solution.solution("ABCDEFG",	new String[]{"12:00,12:14,WORLD,CDEFGAB", "12:00,12:14,HELLO,CDEFGAB"}));
        System.out.println(solution.solution("CC#BCC#BCC#BCC#B", new String[]{"03:00,03:30,FOO,CC#B", "04:00,04:08,BAR,CC#BCC#BCC#B"}));
    }
}