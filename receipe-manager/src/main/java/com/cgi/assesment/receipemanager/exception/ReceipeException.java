package com.cgi.assesment.receipemanager.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReceipeException extends RuntimeException {

    private final ReceipeExceptionCodes exceptionCode;
    private final String message;

    public ReceipeException(ReceipeExceptionCodes exceptionCode, String message) {
        super(message);
        this.exceptionCode = exceptionCode;
        this.message = message;
    }

}

