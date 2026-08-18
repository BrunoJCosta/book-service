package br.com.book.bookservice.configuration;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@PreAuthorize("@serviceAuthentication.hasPermission(#permission)")
public @interface PreAuthService {

    String permission() default "";
}