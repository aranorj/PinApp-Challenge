package com.pinapp.challenge.datatransfer;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

@Builder
@Data
@Tag(name = "BAD REQUEST response")
public class BadReqExceptionResponse {
    @Schema(example = "BAD_REQUEST")
    private final HttpStatus httpStatus;
    @Schema(example = "fechaNacimiento")
    private final String field;
    @Schema(example = "El formato de fecha debe ser YYYY-MM-DD")
    private final String message;
    @Schema(example = "2023-05-05T21:28:40.9235015-03:00")
    private final ZonedDateTime timestamp;
}
