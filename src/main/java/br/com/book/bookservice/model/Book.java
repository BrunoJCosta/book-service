package br.com.book.bookservice.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Book implements Serializable {

    private Long id;
    private String title;
    private String author;
    private LocalDate date;
    private Double price;
    private String currency;
    private String environment;
}
