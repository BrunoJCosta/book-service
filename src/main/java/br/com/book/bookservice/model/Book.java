package br.com.book.bookservice.model;


import br.com.book.bookservice.dto.BookDTO;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "book", schema = "book_server")
public class Book implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    @Setter(AccessLevel.NONE)
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
