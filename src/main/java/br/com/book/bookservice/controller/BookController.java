package br.com.book.bookservice.controller;

import br.com.book.bookservice.model.Book;
import br.com.book.bookservice.proxy.CambioProxy;
import br.com.book.bookservice.response.Cambio;
import br.com.book.bookservice.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {

    private final Environment environment;
    private final BookService bookService;
    private final CambioProxy cambioProxy;

    @GetMapping("/{id}/{currency}")
    public Book findById(@PathVariable(name = "id") String idStr, @PathVariable String currency) {
        long id = Objects.nonNull(idStr) ? Long.parseLong(idStr) : 0L;
        if (id < 0)
            throw new RuntimeException("Id do book está invalido");
        if (currency == null)
            throw new RuntimeException("Currency está invalido");

        Book book = bookService.findById(id);

        book.setCurrency(currency);

         Cambio cambio = cambioProxy.getCambio(book.getPrice(), "USD", currency);

        if (Objects.isNull(cambio))
            throw new RuntimeException("cambio not found");

        String porta = environment.getProperty("local.server.port");
        book.setEnvironment("book port: " + porta + " | cambio port:" + cambio.getEnvironment());

        book.setPrice(cambio.getConvertValue());

        return book;
    }

}
