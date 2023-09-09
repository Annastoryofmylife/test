package com.cgi.assesment.receipemanager.exception;

import com.cgi.assesment.receipemanager.model.Receipe;
import com.cgi.assesment.receipemanager.model.ReceipeExceptionDetails;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
@Slf4j
public class ReceipeExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<ReceipeExceptionDetails> exceptionHandler(Exception exception,
                                                                    HttpServletRequest request) {
        log.error("CAUSE : {}", ExceptionUtils.getStackTrace(exception));
        return new ResponseEntity<>(new ReceipeExceptionDetails(HttpStatus.INTERNAL_SERVER_ERROR,
                                                                exception.getMessage(),
                                                                request.getRequestURI()),
                                    HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {ReceipeException.class})
    public ResponseEntity<ReceipeExceptionDetails> exceptionHandler(ReceipeException exception,
                                                                    HttpServletRequest request) {
        log.error("CAUSE : {}", ExceptionUtils.getStackTrace(exception));
        return new ResponseEntity<>(new ReceipeExceptionDetails(exception.getExceptionCode().getHttpStatus(),
                                                                exception.getMessage(),
                                                                request.getRequestURI()),
                                    exception.getExceptionCode().getHttpStatus());
    }
}
