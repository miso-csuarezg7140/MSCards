package com.novatec.MSCards.domain.dto;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCardRequest {

    @Pattern(regexp = "^[CD]$", message = "Valores viables C - Crédito o D - Débito.")
    private String productType;

    @Pattern(regexp = "^[A-z]+ [A-z]+$", message = "Debe estar presente mínimo un nombre y un apellido.")
    private String cardholder;
}
