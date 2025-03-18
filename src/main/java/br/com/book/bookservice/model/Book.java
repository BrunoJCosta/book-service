package br.com.book.bookservice.model;


import jakarta.persistence.*;
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
@Entity
@Table(name = "book", schema = "book_server")
public class Book implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "author")
    private String author;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "price")
    private Double price;

    @Transient
    private String currency;

    @Transient
    private String environment;
}
