package br.com.mmmsieto.tasks.exception;

import br.com.mmmsieto.tasks.controller.dtos.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import reactor.core.publisher.Mono;

@ControllerAdvice
public class CustomException {

    @ExceptionHandler(RuntimeException.class)
    public Mono<ResponseEntity<ErrorResponse>> handleRuntimeException(RuntimeException ex) {
        return Mono.just(ex)
                .map(ErrorResponse::internalError)
                .map(error -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error));
    }

}
