package com.pinapp.challenge.exception;

import com.pinapp.challenge.datatransfer.BadReqExceptionResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.Optional;

@RestControllerAdvice
@Slf4j
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(value = {MethodArgumentNotValidException.class})
    @PostMapping("/creacliente")
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ApiResponse(content = @Content(mediaType = "application/json"))
    public ResponseEntity<BadReqExceptionResponse> handleBadRequestException(MethodArgumentNotValidException e) {
        Optional<String> defaultMessage = Optional.ofNullable(e.getFieldError().getDefaultMessage());
        String message = defaultMessage.isEmpty()? e.getMessage() : defaultMessage.get();
        BadReqExceptionResponse badReqExceptionResponse = BadReqExceptionResponse.builder()
                .message(message)
                .field(Objects.requireNonNull(e.getFieldError()).getField())
                .timestamp(ZonedDateTime.now(ZoneId.systemDefault()))
                .httpStatus(HttpStatus.BAD_REQUEST)
                .build();

        log.error(e.getMessage(), e);
        return new ResponseEntity<>( badReqExceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = {AgeConflictException.class})
    @RequestMapping("/creacliente")
    @ResponseStatus(HttpStatus.CONFLICT)
    @ApiResponse(content = @Content(mediaType = "application/json"))
    public ResponseEntity<String> handleBadRequestException(Exception e) {
        log.error(e.getMessage(), e);
        return new ResponseEntity<>( e.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
