package br.com.book.bookservice.exceptions;

import java.io.Serial;

public class BookNotFound extends NotFoundException {

    @Serial
    private static final long serialVersionUID = -5199959027594650195L;

    public BookNotFound() {
        super("book not found");
    }
}
