package br.com.book.bookservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class BookDTO {

    private String title;
    private String author;
    private LocalDate date;
    private Double price;

    private String currency;
    private String environment;
}
