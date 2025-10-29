package br.com.book.bookservice.configuration.proxy;

import feign.RequestInterceptor;
import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.context.annotation.Bean;

public class EstoqueProxyConfiguration {

    @Bean
    public RequestInterceptor requestInterceptor() {
        return new BasicAuthRequestInterceptor("bruno_estoque", "bruno_estoque_123");
    }
}
