package br.com.book.bookservice.proxy;

import br.com.book.bookservice.configuration.http.InternalInterceptorConfiguration;
import br.com.book.bookservice.response.Exchange;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// configuration cria handle para sempre passar por ele
@FeignClient(name = "cambio-service", configuration = InternalInterceptorConfiguration.class)
public interface ExchangeGateway {

    @GetMapping("/cambio/{amount}/{from}/{to}")
    Exchange getCambio(@PathVariable Double amount,
                       @PathVariable String from,
                       @PathVariable String to);
}