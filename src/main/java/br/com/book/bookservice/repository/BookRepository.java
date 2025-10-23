package br.com.book.bookservice.repository;

import br.com.book.bookservice.configuration.CacheName;
import br.com.book.bookservice.model.Book;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Cacheable(cacheNames = CacheName.BOOK_ID, key = "#id")
    Optional<Book> findByIdAndActiveTrue(Long id);

}
