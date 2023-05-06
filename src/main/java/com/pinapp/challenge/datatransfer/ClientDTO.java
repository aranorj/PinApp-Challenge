package com.pinapp.challenge.datatransfer;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class ClientDTO {

    @NotEmpty
    @Schema(example = "Marta")
    String nombre;
    @NotEmpty
    @Schema(example = "Minujín")
    String apellido;
    @NotNull
    @PositiveOrZero
    @Digits(integer = 3, fraction = 0)
    @Schema(example = "80")
    Integer edad;
    @NotNull
    @Pattern(regexp = "\\b\\d{4}-(?:0[1-9]|1[0-2])-(?:0[1-9]|[12][0-9]|3[01])\\b", message = "El formato de fecha debe ser YYYY-MM-DD")
    @Schema(example = "1943-01-30")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    String fechaNacimiento;

}
