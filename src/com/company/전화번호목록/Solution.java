package com.company.전화번호목록;

public class Solution {
    public static boolean solution(String[] phone_book) {
        boolean answer = true;

        for(int i = 0; i < phone_book.length - 1; i++){
            int sameCount = 0;
            String number1 = phone_book[i];
            for(int j = i + 1; j < phone_book.length; j++){
                String number2 = phone_book[j];
                int stringMinLen = Math.min(number1.length(), number2.length());
                for(int k = 0; k < stringMinLen; k++){
                    if(number1.charAt(k) == number2.charAt(k))
                        sameCount++;
                }
                if(stringMinLen == sameCount){
                    answer = false;
                    return answer;
                }
                sameCount = 0;
            }
        }
        return answer;
    }
    public static void main(String[] args){
        boolean result = solution(new String[]{"119", "97674223", "976", "1095524421"});
        System.out.println(result);
    }
}
