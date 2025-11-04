package br.com.book.bookservice.exceptions;

import java.io.Serial;

public class NotFoundException extends Exception {

    public NotFoundException(String message) {
        super(message);
    }

    @Serial
    private static final long serialVersionUID = 7070016714810566328L;
}
