package br.com.book.bookservice.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Cambio {

    private Long id;
    private String from;
    private String to;
    private Double convertFactor;
    private Double convertValue;
    private String environment;

}