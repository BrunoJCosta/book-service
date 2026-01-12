package br.com.book.bookservice.configuration.http;

import br.com.book.bookservice.proxy.SecurityGateway;
import feign.RequestInterceptor;
import feign.auth.BasicAuthRequestInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class InternalInterceptorConfiguration {

    public static final String PASSWORD = "bruno_cambio_123";
    public static final String USER = "bruno_cambio";
    private final SecurityGateway securityGateway;

    @Bean
    public RequestInterceptor requestInterceptor() {
        String token = securityGateway.getToken(USER, PASSWORD, "BOOK");

//        return new BasicAuthRequestInterceptor(USER, PASSWORD);
        return null;
    }
}
