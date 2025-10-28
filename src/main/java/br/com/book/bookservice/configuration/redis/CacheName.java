package br.com.book.bookservice.configuration.redis;

import java.time.Duration;
import java.util.List;

public class CacheName {

    public static final String BOOK_ALL = "book_all";
    public static final String BOOK_ID = "book_id";

    static List<RedisDTO> cache() {
        return List.of(
                new RedisDTO(BOOK_ALL, Duration.ofMinutes(30)),
                new RedisDTO(BOOK_ID, Duration.ofMinutes(30))
        );
    }
}
