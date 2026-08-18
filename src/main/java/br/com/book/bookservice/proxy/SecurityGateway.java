package br.com.book.bookservice.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "security")
public interface SecurityGateway {

    @GetMapping("/token")
    ResponseEntity<String> getToken(@RequestParam String key, @RequestParam String target);

}
