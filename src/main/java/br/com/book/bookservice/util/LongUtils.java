package br.com.book.bookservice.util;

import java.util.Objects;

public class LongUtils {

    public static boolean empty(Long lg) {
        return Objects.isNull(lg)  || lg == 0L;
    }

    public static boolean invalid(Long lg) {
        return empty(lg) || lg < 0;
    }

    public static Long strToLong(String str) {
        if (Objects.isNull(str)) {
            return null;
        }
        try {
            return Long.parseLong(str);
        } catch (Exception e) {
            return null;
        }
    }
}
