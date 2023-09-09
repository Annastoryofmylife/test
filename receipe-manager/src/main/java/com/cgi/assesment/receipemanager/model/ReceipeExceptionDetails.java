package com.cgi.assesment.receipemanager.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ReceipeExceptionDetails {
    private final LocalDateTime timestamp = LocalDateTime.now();

    private final HttpStatus status;

    private final String message;

    private final String path;
}
