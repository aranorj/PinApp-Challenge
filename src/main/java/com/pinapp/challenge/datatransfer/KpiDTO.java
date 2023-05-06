package com.pinapp.challenge.datatransfer;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class KpiDTO {
    @Schema(example = "40.75")
    Double promedioEdades;
    @Schema(example = "22.47035083541569")
    Double desviacionEstandar;
}
