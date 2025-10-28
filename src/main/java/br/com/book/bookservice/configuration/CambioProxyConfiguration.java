package br.com.book.bookservice.configuration;

import feign.RequestInterceptor;
import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.context.annotation.Bean;

public class CambioProxyConfiguration {

    @Bean
    public RequestInterceptor requestInterceptor() {
        return new BasicAuthRequestInterceptor("bruno_cambio", "bruno_cambio_123");
    }
}
