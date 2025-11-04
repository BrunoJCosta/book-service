package br.com.book.bookservice.util;

import java.util.Objects;

public class LongUtils {

    public static boolean empty(Long lg) {
        return Objects.isNull(lg)  || lg == 0L;
    }

    public static boolean invalid(Long lg) {
        return empty(lg) || lg < 0;
    }

}
