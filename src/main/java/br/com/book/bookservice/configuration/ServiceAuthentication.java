package br.com.book.bookservice.configuration;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component("serviceAuthentication")
public class ServiceAuthentication {

    public boolean hasPermission(String permission) {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        if (!(authentication instanceof ServiceAuthentication)) {
            return false;
        }

        return authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals(permission));
    }
}