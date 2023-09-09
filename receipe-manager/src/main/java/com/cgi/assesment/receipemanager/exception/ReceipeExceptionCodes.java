package com.cgi.assesment.receipemanager.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ReceipeExceptionCodes {

    INVALID_INPUT(HttpStatus.BAD_REQUEST),
    RECEIPE_NOT_FOUND (HttpStatus.NOT_FOUND);

    private final HttpStatus httpStatus;

    ReceipeExceptionCodes(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

}
