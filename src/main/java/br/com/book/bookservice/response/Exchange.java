package br.com.book.bookservice.response;

public record Exchange(Long id, String from,
                       String to, Double convertFactor,
                       Double convertValue, String environment) {


}