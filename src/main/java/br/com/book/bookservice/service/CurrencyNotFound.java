package br.com.book.bookservice.service;

public class CurrencyNotFound extends Exception {

    public CurrencyNotFound() {
        super("Currency not found");
    }
}
