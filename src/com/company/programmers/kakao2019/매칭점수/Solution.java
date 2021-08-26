package com.company.programmers.kakao2019.매칭점수;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {
    static Map<String, Page> pageMap = new HashMap<>();

    static class Page implements Comparable<Page> {
        int index;
        String html;
        String url;
        int basicScore;
        List<String> linkList = new ArrayList<>();
        double linkScore;

        public Page(int index, String html) {
            this.index = index;
            this.html = html;
        }

        public void setUrl() {
            Pattern pattern = Pattern.compile("<meta property=\"og:url\" content=\"https://(.+?)\"/>");
            Matcher matcher = pattern.matcher(html);

            while (matcher.find()) {
                url = matcher.group(1);
            }
        }

        public void setLinkList() {
            Pattern pattern = Pattern.compile("<a href=\"https://(.+?)\">");
            Matcher matcher = pattern.matcher(html);

            while (matcher.find()) {
                linkList.add(matcher.group(1));
            }
        }

        public void setBasicScore(String word) {
            int idx = html.indexOf(word);
            int count = 0;
            while (idx != -1) {
                if (idx != 0 && Character.isAlphabetic(html.charAt(idx - 1))) {
                    idx = html.indexOf(word, idx + 1);
                    continue;
                }

                if (idx + word.length() != html.length() && Character.isAlphabetic(html.charAt(idx + word.length()))) {
                    idx = html.indexOf(word, idx + 1);
                    continue;
                }

                count += 1;
                idx = html.indexOf(word, idx + 1);
            }

            basicScore = count;
        }

        public void setLinkScore() {
            for (String link : linkList) {
                Page linkPage = pageMap.get(link);
                if (linkPage == null) {
                    continue;
                }
                linkPage.linkScore += (double) basicScore / linkList.size();
            }
        }

        @Override
        public int compareTo(Page otherPage) {
            double thisMatchScore = this.basicScore + this.linkScore;
            double otherMatchScore = otherPage.basicScore + otherPage.linkScore;

            if (thisMatchScore == otherMatchScore) {
                return this.index - otherPage.index;
            } else {
                return Double.compare(thisMatchScore, otherMatchScore) * -1;
            }
        }
    }

    public int solution(String word, String[] pages) {
        int answer = 0;
        for (int i = 0; i < pages.length; i++) {
            Page page = new Page(i, pages[i].toLowerCase());
            page.setUrl();
            page.setLinkList();
            page.setBasicScore(word.toLowerCase());

            pageMap.put(page.url, page);
        }

        for (Page page : pageMap.values()) {
            page.setLinkScore();
        }

        List<Page> pageList = new ArrayList<>(pageMap.values());
        Collections.sort(pageList);

        answer = pageList.get(0).index;

        return answer;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.solution("blind", new String[]{"<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://a.com\"/>\n</head>  \n<body>\nBlind Lorem Blind ipsum dolor Blind test sit amet, consectetur adipiscing elit. \n<a href=\"https://b.com\"> Link to b </a>\n</body>\n</html>", "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://b.com\"/>\n</head>  \n<body>\nSuspendisse potenti. Vivamus venenatis tellus non turpis bibendum, \n<a href=\"https://a.com\"> Link to a </a>\nblind sed congue urna varius. Suspendisse feugiat nisl ligula, quis malesuada felis hendrerit ut.\n<a href=\"https://c.com\"> Link to c </a>\n</body>\n</html>", "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://c.com\"/>\n</head>  \n<body>\nUt condimentum urna at felis sodales rutrum. Sed dapibus cursus diam, non interdum nulla tempor nec. Phasellus rutrum enim at orci consectetu blind\n<a href=\"https://a.com\"> Link to a </a>\n</body>\n</html>"}));
//        System.out.println(solution.solution("Muzi", new String[]{"<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://careers.kakao.com/interview/list\"/>\n</head>  \n<body>\n<a href=\"https://programmers.co.kr/learn/courses/4673\"></a>#!MuziMuzi!)jayg07con&&\n\n</body>\n</html>", "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://www.kakaocorp.com\"/>\n</head>  \n<body>\ncon%\tmuzI92apeach&2<a href=\"https://hashcode.co.kr/tos\"></a>\n\n\t^\n</body>\n</html>"}));
    }
}