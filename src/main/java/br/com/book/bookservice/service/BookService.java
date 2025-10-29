package br.com.book.bookservice.service;

import br.com.book.bookservice.dto.BookDTO;
import br.com.book.bookservice.model.Book;
import br.com.book.bookservice.proxy.CambioProxy;
import br.com.book.bookservice.proxy.EstoqueProxy;
import br.com.book.bookservice.repository.BookRepository;
import br.com.book.bookservice.response.Cambio;
import br.com.book.bookservice.response.Estoque;
import br.com.book.bookservice.util.LongUtils;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository repository;
    private final Environment environment;
    private final CambioProxy cambioProxy;
    private final EstoqueProxy estoqueProxy;

    public BookDTO findByIdAndCurrency(Long id, String currency) throws BookNotFound, CurrencyNotFound {
        if (LongUtils.invalid(id))
            throw new BookNotFound();

        if (StringUtils.isBlank(currency))
            throw new CurrencyNotFound();

        Book book = repository.findByIdAndActiveTrue(id).orElseThrow(() -> new RuntimeException("Book not found"));
        String porta = environment.getProperty("local.server.port");

        Cambio cambio = cambioProxy.getCambio(book.getPrice(), "USD", currency);
        Estoque estoque = estoqueProxy.getReferenciaBook(book.getId());

        BookDTO dto = book.dto();
        dto.setQuantity(estoque.quantidade());
        dto.setCurrency(currency);
        dto.setPrice(cambio.convertValue());

        dto.setEnvironment("book: " + porta + " | cambio: " + cambio.environment());

        return dto;
    }

}
