package com.novatec.MSCards.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "updateBalanceRequest", description = "Estructura de petición para la actualización de saldo de " +
        "una tarjeta.")
public class UpdateBalanceRequest {

    @Schema(description = "Id de la tarjeta. Longitud: 16 dígitos.")
    @Pattern(regexp = "^\\d{16}$", message = "El id de la tarjeta no tiene la longitud requerida.")
    private String cardId;

    @Schema(description = "Monto a actualizar. Si es negativo es por una compra hecha, si es positivo es por " +
            "una recarga o la anulación de una compra.")
    @NotNull(message = "El saldo a actualizar es requerido.")
    private Long balance;
}
