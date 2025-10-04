package com.aero.utils;

public final class StringUtils {

    private StringUtils() {}

    public static String removeBrackets(String text) {
        if (text == null) {
            return null;
        }
        return text.replaceAll("\\s*\\(\\d+\\)$", "").trim();
    }
}
