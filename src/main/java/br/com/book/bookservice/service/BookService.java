package br.com.book.bookservice.service;

import br.com.book.bookservice.dto.BookDTO;
import br.com.book.bookservice.exceptions.BookNotFound;
import br.com.book.bookservice.exceptions.CurrencyNotFound;
import br.com.book.bookservice.model.Book;
import br.com.book.bookservice.proxy.ExchangeGateway;
import br.com.book.bookservice.proxy.StockGateway;
import br.com.book.bookservice.repository.BookRepository;
import br.com.book.bookservice.response.Exchange;
import br.com.book.bookservice.response.Stock;
import br.com.book.bookservice.util.LongUtils;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository repository;
    private final Environment environment;
    private final ExchangeGateway exchangeGateway;
    private final StockGateway stockGateway;

    public BookDTO findByIdAndCurrency(Long id, String currency) throws BookNotFound, CurrencyNotFound {
        if (LongUtils.invalid(id))
            throw new BookNotFound();

        if (StringUtils.isBlank(currency))
            throw new CurrencyNotFound();

        Book book = repository.findByIdAndActiveTrue(id).orElseThrow(() -> new RuntimeException("Book not found"));
        String porta = environment.getProperty("local.server.port");

        Exchange exchange = exchangeGateway.getCambio(book.getPrice(), "USD", currency);
        Stock stock = stockGateway.getReferenciaBook(book.getId());

        BookDTO dto = book.dto();
        dto.setQuantity(stock.quantidade());
        dto.setCurrency(currency);
        dto.setPrice(exchange.convertValue());

        dto.setEnvironment("book: " + porta + " | cambio: " + exchange.environment() + " | estoque: " + stock.environment());

        return dto;
    }

    public List<BookDTO> findAll(String currency, Long id, String name, String author) {
        List<Book> reading = repository.reading(id, name, author);
        return reading.stream().map(Book::dto).toList();
    }
}
