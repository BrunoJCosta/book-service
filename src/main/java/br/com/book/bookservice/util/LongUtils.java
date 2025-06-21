package br.com.book.bookservice.util;

import br.com.book.bookservice.service.BookNotFound;
import br.com.book.bookservice.service.CurrencyNotFound;

import java.util.Objects;

public class LongUtils {

    public static boolean empty(Long lg) {
        return Objects.isNull(lg)  || lg == 0L;
    }

    public static boolean invalid(Long lg) {
        return empty(lg) || lg < 0;
    }

}
