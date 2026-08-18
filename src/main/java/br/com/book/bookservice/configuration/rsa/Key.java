package br.com.book.bookservice.configuration.rsa;

import lombok.Getter;

import java.util.Objects;
import java.util.stream.Stream;

@Getter
public enum Key {

    BOOK("book-service" ,"book"),
    CAMBIO("cambio-service" ,"cambio"),
    STOCK("estoque-server" ,"stock");

    private final String caminhoProjeto;
    private final String key;

    Key(String caminhoProjeto, String key) {
        this.caminhoProjeto = caminhoProjeto;
        this.key = key;
    }

    public static Key getCaminhoProjeto(String caminhoProjeto) {
        return Stream.of(values())
                .filter(c -> Objects.equals(caminhoProjeto, c.caminhoProjeto))
                .findAny()
                .orElseThrow(() -> new RuntimeException("erro ao comunicação de projeto"));
    }
}
