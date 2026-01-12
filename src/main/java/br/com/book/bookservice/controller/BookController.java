package br.com.book.bookservice.controller;

import br.com.book.bookservice.configuration.exception.Response;
import br.com.book.bookservice.dto.BookDTO;
import br.com.book.bookservice.exceptions.BookNotFound;
import br.com.book.bookservice.service.BookService;
import br.com.book.bookservice.exceptions.CurrencyNotFound;
import br.com.book.bookservice.util.LongUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/{id}/{currency}")
    public Response findById(@PathVariable(name = "id") String idStr,
                             @PathVariable String currency)
            throws BookNotFound, CurrencyNotFound {
        long id = Objects.nonNull(idStr) ? Long.parseLong(idStr) : 0L;
        BookDTO book = bookService.findByIdAndCurrency(id, currency);
        return Response.ok(book);
    }

    @GetMapping("/{currency}")
    public Response listagem(@PathVariable String currency,
                             @RequestParam(name = "id") String idStr,
                             @RequestParam(name = "name") String name,
                             @RequestParam(name = "author") String author
    ) throws BookNotFound, CurrencyNotFound {

        Long id = LongUtils.strToLong(idStr);
        List<BookDTO> book = bookService.findAll(currency, id, name, author);
        return Response.ok(book);
    }

}
