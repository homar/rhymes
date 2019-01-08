package com.amazon.siri.utils;

public class Utils {

    public static char[] reverse(char[] chars) {
        for(int i = 0; i < chars.length / 2; i++) {
            char tmp = chars[i];
            chars[i] = chars[chars.length - 1 - i];
            chars[chars.length - 1 - i] = tmp;
        }
        return chars;
    }

}
