package br.com.book.bookservice.repository;

import br.com.book.bookservice.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {


}
