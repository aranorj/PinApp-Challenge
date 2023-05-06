package com.pinapp.challenge.exception;

import com.pinapp.challenge.datatransfer.BadReqExceptionResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Optional;

@RestControllerAdvice
@Slf4j
public class CustomExceptionHandler {

    @ExceptionHandler(value = {
            MethodArgumentNotValidException.class,
            AgeConflictException.class})
    @PostMapping("/creacliente")
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ApiResponse(content = @Content(mediaType = "application/json"))
    public ResponseEntity<BadReqExceptionResponse> handleBadRequestException(Throwable e) {

        boolean isValidationError = e instanceof MethodArgumentNotValidException;
        Optional<String> defaultMessage = isValidationError
                ? Optional.ofNullable(((MethodArgumentNotValidException) e).getFieldError().getDefaultMessage()):
                Optional.empty();
        String message = defaultMessage.isEmpty()? e.getMessage() : defaultMessage.get();
        String field =  isValidationError ? ((MethodArgumentNotValidException) e).getFieldError().getField() : "Edad/fechaNacimiento";

        BadReqExceptionResponse badReqExceptionResponse = BadReqExceptionResponse.builder()
                .message(message)
                .field(field)
                .timestamp(ZonedDateTime.now(ZoneId.systemDefault()))
                .httpStatus(HttpStatus.BAD_REQUEST)
                .build();

        log.error(e.getMessage(), e);
        return new ResponseEntity<>( badReqExceptionResponse, HttpStatus.BAD_REQUEST);
    }

}
