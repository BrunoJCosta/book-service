package br.com.book.bookservice.service;

public class BookNotFound extends Exception {

    public BookNotFound() {
        super("book not found");
    }
}
