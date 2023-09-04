package br.com.math012.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class InvalidTokenJwtException extends AuthenticationException {

    public InvalidTokenJwtException(String error) {
        super(error);
    }
}
