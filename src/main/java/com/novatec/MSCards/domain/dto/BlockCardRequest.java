package com.novatec.MSCards.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "blockCardRequest", description = "Estructura de petición para ejecutar el bloqueo de una tarjeta.")
public class BlockCardRequest {

    @Schema(description = "Id de la tarjeta a bloquear. Longitud: 16 dígitos.")
    @Pattern(regexp = "^\\d{16}$", message = "El id de la tarjeta no tiene la longitud requerida.")
    private String cardId;
}
