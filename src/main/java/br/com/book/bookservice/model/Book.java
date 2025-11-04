package br.com.book.bookservice.model;


import br.com.book.bookservice.dto.BookDTO;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
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

    @Serial
    private static final long serialVersionUID = -4296691095833640418L;

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

    @Column(name = "active", columnDefinition = "boolean default true")
    private boolean active;

    public BookDTO dto() {
        BookDTO dto = new BookDTO();
        dto.setTitle(this.title);
        dto.setAuthor(this.author);
        dto.setDate(this.date);
        return dto;
    }
    @PrePersist
    public void prePersist() {
        this.active = true;
    }
}
