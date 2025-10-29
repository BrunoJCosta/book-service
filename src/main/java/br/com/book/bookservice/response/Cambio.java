package br.com.book.bookservice.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public record Cambio(Long id, String from,
                     String to, Double convertFactor,
                     Double convertValue, String environment) {


}