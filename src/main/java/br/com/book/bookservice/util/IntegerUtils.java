package br.com.book.bookservice.util;

import java.util.Objects;

public class IntegerUtils {

    public static boolean empty(Integer lg) {
        return Objects.isNull(lg)  || lg == 0;
    }

    public static boolean invalid(Integer lg) {
        return empty(lg) || lg < 0;
    }

}
