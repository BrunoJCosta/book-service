package br.com.book.bookservice.util;

public class StringUtils {

    public static boolean empty(String str) {
        return str == null || str.isEmpty() || str.trim().isBlank() || str.equals("null");
    }

    public static boolean filled(String str) {
        return !empty(str);
    }
}
