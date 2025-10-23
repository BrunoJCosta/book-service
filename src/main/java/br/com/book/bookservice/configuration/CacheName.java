package br.com.book.bookservice.configuration;

import java.time.Duration;
import java.util.List;

class CacheName {

    public static final String BOOK_ALL = "cambio_all";

    static List<RedisDTO> cache() {
        return List.of(
                new RedisDTO(BOOK_ALL, Duration.ofMinutes(30))
        );
    }
}
