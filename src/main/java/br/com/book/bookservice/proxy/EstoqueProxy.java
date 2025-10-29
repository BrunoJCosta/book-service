package br.com.book.bookservice.proxy;

import br.com.book.bookservice.configuration.proxy.EstoqueProxyConfiguration;
import br.com.book.bookservice.response.Estoque;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "estoque-server", configuration = EstoqueProxyConfiguration.class)
public interface EstoqueProxy {

    @GetMapping("/estoque/book/{referenciaBook}")
    Estoque getReferenciaBook(@PathVariable Long referenciaBook);
}
