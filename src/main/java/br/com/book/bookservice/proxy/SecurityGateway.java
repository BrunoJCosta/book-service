package br.com.book.bookservice.proxy;

import org.springframework.stereotype.Component;

@Component
public interface SecurityGateway {

    String getToken(String user, String password, String book);
}
