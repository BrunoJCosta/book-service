package br.com.book.bookservice.exceptions;

import java.io.Serial;

public class CurrencyNotFound extends NotFoundException {

    @Serial
    private static final long serialVersionUID = 6066589192567066570L;

    public CurrencyNotFound() {
        super("Currency not found");
    }
}
