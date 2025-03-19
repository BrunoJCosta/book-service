package br.com.book.bookservice.controller;

import br.com.book.bookservice.model.Book;
import br.com.book.bookservice.response.Cambio;
import br.com.book.bookservice.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {

    private final Environment environment;
    private final BookService bookService;

    @GetMapping("/{id}/{currency}")
    public Book findById(@PathVariable(name = "id") String idStr, @PathVariable String currency) {
        long id = Objects.nonNull(idStr) ? Long.parseLong(idStr) : 0L;
        if (id < 0)
            throw new RuntimeException("Id do book está invalido");
        if (currency == null)
            throw new RuntimeException("Currency está invalido");

        String porta = environment.getProperty("local.server.port");

        Book book = bookService.findById(id);
        book.setEnvironment(porta);
        book.setCurrency(currency);

        Map<String, String> param = new HashMap<>();
        param.put("amount", book.getPrice().toString());
        param.put("from", "USD");
        param.put("to", currency);

        ResponseEntity<Cambio> response = new RestTemplate()
                .getForEntity("http://localhost:8000/cambio_server/{amount}/{from}/{to}",
                        Cambio.class,
                        param);

        Cambio cambio = response.getBody();
        if (Objects.isNull(cambio))
            throw new RuntimeException("cambio not found");

        book.setPrice(cambio.getConvertValue());

        return book;
    }

}
