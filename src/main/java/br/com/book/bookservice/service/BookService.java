package br.com.book.bookservice.service;

import br.com.book.bookservice.dto.BookDTO;
import br.com.book.bookservice.model.Book;
import br.com.book.bookservice.proxy.CambioProxy;
import br.com.book.bookservice.repository.BookRepository;
import br.com.book.bookservice.response.Cambio;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository repository;
    private final Environment environment;
    private final CambioProxy cambioProxy;

    public BookDTO findByIdAndCurrency(Long id, String currency) throws BookNotFound, CurrencyNotFound {
        if (Objects.isNull(id) || id < 0)
            throw new BookNotFound();
        if (currency == null || currency.isEmpty() || currency.equalsIgnoreCase("null"))
            throw new CurrencyNotFound();

        Book book = repository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
        BookDTO dto = book.dto();

        String porta = environment.getProperty("local.server.port");

        dto.setCurrency(currency);

        Cambio cambio = cambioProxy.getCambio(book.getPrice(), "USD", currency);
        dto.setCurrency(currency);
        dto.setPrice(cambio.getConvertValue());

        dto.setEnvironment("book: " + porta + " | cambio: " + cambio.getEnvironment());

        return dto;
    }

}
