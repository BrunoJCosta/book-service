package br.com.book.bookservice.repository;

import br.com.book.bookservice.configuration.redis.CacheName;
import br.com.book.bookservice.model.Book;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Cacheable(value = CacheName.BOOK_ID, key = "#id")
    Optional<Book> findByIdAndActiveTrue(Long id);

    @Query("""
            select b from Book b where b.active and
                    (?1 = null or b.id = ?1) and
                    (?2 = null or b.title = ?2) and
                    (?3 = null or b.author = ?3)
        """)
    List<Book> reading(Long id, String name, String author);
}
