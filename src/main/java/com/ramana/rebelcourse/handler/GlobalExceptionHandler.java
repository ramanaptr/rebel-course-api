package com.ramana.rebelcourse.handler;

import com.ramana.rebelcourse.payload.response.BaseResponse;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request
    ) {

        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());


        return new ResponseEntity<>(BaseResponse.setAsFailed("Failed", errors), headers, status);
    }

    @ExceptionHandler({AuthenticationException.class, AccessDeniedException.class})
    protected ResponseEntity<?> handleSpringSecurityException(Exception e) throws Exception {
        throw e;
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<?> handleGlobalException(Exception e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(BaseResponse.setAsFailed(e));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity<?> handleJwtException(Exception e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.OK).body(BaseResponse.setAsFailed(e.getMessage()));
    }

    @ExceptionHandler({JwtException.class, SignatureException.class})
    protected ResponseEntity<?> handleJwtExceptions(Exception e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.OK).body(BaseResponse.setUnauthorizedResponse(e.getMessage()));
    }

}
