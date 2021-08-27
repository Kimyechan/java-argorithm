package com.company.programmers.kakao2019.매칭점수;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution3 {
    static Map<String, Page> pageMap = new HashMap<>();

    static class Page {
        int idx;
        String url;
        String html;
        List<String> externalLinkList = new ArrayList<>();
        int basicScore;
        double linkScore;

        public Page(int idx, String html) {
            this.idx = idx;
            this.html = html;
        }

        public void setUrl() {
            Pattern pattern = Pattern.compile("<meta property=\"og:url\" content=\"https://(.+?)\"/>");
            Matcher matcher = pattern.matcher(this.html);

            while (matcher.find()) {
                this.url = matcher.group(1);
            }
        }

        public void setExternalLinkList() {
            Pattern pattern = Pattern.compile("<a href=\"https://(.+?)\">");
            Matcher matcher = pattern.matcher(this.html.toLowerCase());

            while (matcher.find()) {
                this.externalLinkList.add(matcher.group(1));
            }
        }

        public void calcBasicScore(String word) {
            String htmlLower = this.html.toLowerCase();
            String wordLower = word.toLowerCase();

            int wordIdx = htmlLower.indexOf(wordLower);
            int score = 0;
            while (wordIdx != -1) {
                int preIdx = wordIdx - 1;
                int postIdx = wordIdx + word.length();

                if (!Character.isAlphabetic(htmlLower.charAt(preIdx)) && !Character.isAlphabetic(htmlLower.charAt(postIdx))) {
                    score += 1;
                }
                wordIdx = htmlLower.indexOf(wordLower, wordIdx + 1);
            }

            this.basicScore = score;
        }

        public void calcLinkScore() {
            for (String link : externalLinkList) {
                if (pageMap.containsKey(link)) {
                    Page linkPage = pageMap.get(link);
                    linkPage.linkScore += (double) this.basicScore / externalLinkList.size();
                }
            }
        }
    }

    public int solution(String word, String[] pages) {
        int answer = 0;
        for (int i = 0; i < pages.length; i++) {
            Page page = new Page(i, pages[i]);
            page.setUrl();
            page.setExternalLinkList();
            page.calcBasicScore(word);
            pageMap.put(page.url, page);
        }

        for (Page page : pageMap.values()) {
            page.calcLinkScore();
        }

        List<Page> pageList = new ArrayList<>(pageMap.values());

        pageList.sort((p1, p2) -> {
            double matchScore1 = p1.basicScore + p1.linkScore;
            double matchScore2 = p2.basicScore + p2.linkScore;

            if (matchScore1 == matchScore2) {
                return p1.idx - p2.idx;
            } else {
                return Double.compare(matchScore2, matchScore1);
            }
        });

        answer = pageList.get(0).idx;

        return answer;
    }

    public static void main(String[] args) {
        Solution3 solution = new Solution3();
        System.out.println(solution.solution("blind", new String[]{"<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://a.com\"/>\n</head>  \n<body>\nBlind Lorem Blind ipsum dolor Blind test sit amet, consectetur adipiscing elit. \n<a href=\"https://b.com\"> Link to b </a>\n</body>\n</html>", "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://b.com\"/>\n</head>  \n<body>\nSuspendisse potenti. Vivamus venenatis tellus non turpis bibendum, \n<a href=\"https://a.com\"> Link to a </a>\nblind sed congue urna varius. Suspendisse feugiat nisl ligula, quis malesuada felis hendrerit ut.\n<a href=\"https://c.com\"> Link to c </a>\n</body>\n</html>", "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://c.com\"/>\n</head>  \n<body>\nUt condimentum urna at felis sodales rutrum. Sed dapibus cursus diam, non interdum nulla tempor nec. Phasellus rutrum enim at orci consectetu blind\n<a href=\"https://a.com\"> Link to a </a>\n</body>\n</html>"}));
//        System.out.println(solution.solution("Muzi", new String[]{"<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://careers.kakao.com/interview/list\"/>\n</head>  \n<body>\n<a href=\"https://programmers.co.kr/learn/courses/4673\"></a>#!MuziMuzi!)jayg07con&&\n\n</body>\n</html>", "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://www.kakaocorp.com\"/>\n</head>  \n<body>\ncon%\tmuzI92apeach&2<a href=\"https://hashcode.co.kr/tos\"></a>\n\n\t^\n</body>\n</html>"}));
    }
}