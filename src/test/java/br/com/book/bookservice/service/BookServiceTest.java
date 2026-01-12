package br.com.book.bookservice.service;

import br.com.book.bookservice.dto.BookDTO;
import br.com.book.bookservice.exceptions.BookNotFound;
import br.com.book.bookservice.exceptions.CurrencyNotFound;
import br.com.book.bookservice.proxy.ExchangeGateway;
import br.com.book.bookservice.proxy.StockGateway;
import br.com.book.bookservice.repository.BookRepository;
import br.com.book.bookservice.response.Exchange;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

@SpringBootTest
class BookServiceTest {

    static final String USD = "USD";
    static final String BRL = "BRL";

    private BookService service;
    @Autowired
    private BookRepository repository;
    @Mock
    private ExchangeGateway exchangeGateway;
    @Mock
    private StockGateway stockGateway;
    @Autowired
    private Environment environment;

    private AutoCloseable autoCloseable;

    double valueFirstBook = 8.57;
    double valueSecondBook = 7;

    @BeforeEach
    void before() {
        this.autoCloseable = MockitoAnnotations.openMocks(this);
        this.service = new BookService(this.repository, this.environment, this.exchangeGateway, this.stockGateway);

    }

    @AfterEach
    void after() throws Exception {
        autoCloseable.close();
    }

    @Test
    public void testIdIsNull() {
        Assertions.assertThrows(BookNotFound.class, () -> this.service.findByIdAndCurrency(null, BRL));
    }

    @Test
    public void testCurrencyIsNull() {
        Assertions.assertThrows(CurrencyNotFound.class, () -> this.service.findByIdAndCurrency(1L, null));
    }

    @Test
    public void testCurrencyBRL() throws BookNotFound, CurrencyNotFound {
        double valueBRL = 5.7;

        Double result = valueBRL * valueFirstBook;

        Mockito.when(this.exchangeGateway.getCambio(this.valueFirstBook, USD, BRL))
                .thenReturn(new Exchange(1L, USD, BRL, this.valueFirstBook, result, "8000"));
        BookDTO brl = this.service.findByIdAndCurrency(1L, BRL);

        Assertions.assertEquals(result, brl.getPrice());
        Assertions.assertEquals(BRL, brl.getCurrency());
    }

}