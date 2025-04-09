package br.com.book.bookservice.controller;

import br.com.book.bookservice.dto.BookDTO;
import br.com.book.bookservice.service.BookNotFound;
import br.com.book.bookservice.service.BookService;
import br.com.book.bookservice.service.CurrencyNotFound;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/{id}/{currency}")
    public BookDTO findById(@PathVariable(name = "id") String idStr, @PathVariable String currency) throws BookNotFound, CurrencyNotFound {
        long id = Objects.nonNull(idStr) ? Long.parseLong(idStr) : 0L;
        return bookService.findByIdAndCurrency(id, currency);
    }

}
