package br.com.book.bookservice.proxy;

import br.com.book.bookservice.configuration.http.InternalInterceptorConfiguration;
import br.com.book.bookservice.response.Stock;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "estoque-server", configuration = InternalInterceptorConfiguration.class)
public interface StockGateway {

    @GetMapping("/estoque/book/{referenciaBook}")
    Stock getReferenciaBook(@PathVariable Long referenciaBook);
}
