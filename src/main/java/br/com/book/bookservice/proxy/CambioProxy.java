package br.com.book.bookservice.proxy;

import br.com.book.bookservice.response.Cambio;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "cambio", url = "http://localhost:8000/")
public interface CambioProxy {

    @GetMapping("/cambio_server/{amount}/{from}/{to}")
    Cambio getCambio(@PathVariable Double amount,
                     @PathVariable String from,
                     @PathVariable String to);
}
