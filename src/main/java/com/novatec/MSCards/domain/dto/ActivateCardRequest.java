package com.novatec.MSCards.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "activateCardRequest", description = "Estructura de petición para ejecutar la activación de " +
        "una tarjeta.")
public class ActivateCardRequest {

    @Schema(description = "Id de la tarjeta a activar. Longitud: 16 dígitos.")
    @Pattern(regexp = "^\\d{16}$", message = "El id de la tarjeta no tiene la longitud requerida.")
    private String cardId;
}
