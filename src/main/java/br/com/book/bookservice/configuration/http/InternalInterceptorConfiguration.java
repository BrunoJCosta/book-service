package br.com.book.bookservice.configuration.http;

import br.com.book.bookservice.configuration.rsa.Key;
import br.com.book.bookservice.proxy.SecurityGateway;
import feign.RequestInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;

@Configuration
@RequiredArgsConstructor
public class InternalInterceptorConfiguration {

    private final SecurityGateway securityGateway;

    @Bean
    public RequestInterceptor requestInterceptor() {

        return template -> {
            String caminhoProjeto = template.feignTarget().name();
            Key target = Key.getCaminhoProjeto(caminhoProjeto);
            String key = Key.BOOK.getKey();
            ResponseEntity<String> token = securityGateway.getToken(key, target.getKey());

            String assinatura = token.getHeaders().getFirst("X-Signature");
            template.header("X-Signature", assinatura);
            template.header("Authorization-security", token.getBody());
        };
    }
}
