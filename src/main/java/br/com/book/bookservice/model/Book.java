package br.com.book.bookservice.model;


import br.com.book.bookservice.dto.BookDTO;
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
@Table(name = "book", schema = "book")
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

    public BookDTO dto() {
        BookDTO dto = new BookDTO();
        dto.setTitle(this.title);
        dto.setAuthor(this.author);
        dto.setDate(this.date);
        return dto;
    }
}
