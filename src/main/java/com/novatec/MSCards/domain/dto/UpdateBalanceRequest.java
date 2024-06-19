package com.novatec.MSCards.domain.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateBalanceRequest {

    @Pattern(regexp = "^\\d{16}$", message = "El id de la tarjeta no tiene la longitud requerida.")
    private String cardId;

    @Min(value = 1, message = "El valor mínimo a adicionar es 1 dólar.")
    private Long balance;
}
