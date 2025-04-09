package br.com.book.bookservice.service;

import br.com.book.bookservice.dto.BookDTO;
import br.com.book.bookservice.proxy.CambioProxy;
import br.com.book.bookservice.repository.BookRepository;
import br.com.book.bookservice.response.Cambio;
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

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookServiceTest {

    static final String USD = "USD";
    static final String BRL = "BRL";

    private BookService service;
    @Autowired
    private BookRepository repository;
    @Mock
    private CambioProxy cambioProxy;
    @Autowired
    private Environment environment;

    private AutoCloseable autoCloseable;

    double valueFirstBook = 8.57;
    double valueSecondBook = 7;

    @BeforeEach
    void before() {
        this.autoCloseable = MockitoAnnotations.openMocks(this);
        autoCloseable = MockitoAnnotations.openMocks(this);
        service = new BookService(repository, environment, cambioProxy);

    }

    @AfterEach
    void after() throws Exception {
        autoCloseable.close();
    }

    @Test
    public void testIdIsNull() {
        Assertions.assertThrows(BookNotFound.class, () -> service.findByIdAndCurrency(null, BRL));
    }

    @Test
    public void testCurrencyIsNull() {
        Assertions.assertThrows(CurrencyNotFound.class, () -> service.findByIdAndCurrency(1L, null));
    }

    @Test
    public void testCurrencyBRL() throws BookNotFound, CurrencyNotFound {
        double valueBRL = 5.7;

        Double result = valueBRL * valueFirstBook;

        Mockito.when(cambioProxy.getCambio(valueFirstBook, USD, BRL))
                .thenReturn(new Cambio(1L, USD, BRL, valueFirstBook, result, "8000"));
        BookDTO brl = service.findByIdAndCurrency(1L, BRL);

        Assertions.assertEquals(result, brl.getPrice());
        Assertions.assertEquals(BRL, brl.getCurrency());
    }

}