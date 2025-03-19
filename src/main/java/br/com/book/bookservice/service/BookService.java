package br.com.book.bookservice.service;

import br.com.book.bookservice.model.Book;
import br.com.book.bookservice.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository repository;

    public Book findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
    }

}
