package com.novatec.MSCards.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "createCardRequest", description = "Estructura de petición para ejecutar la creación de una tarjeta.")
public class CreateCardRequest {

    @ApiModelProperty(notes = "Tipo de producto de la tarjeta. Posibles valores: 'C' para Crédito / 'D' para Débito.")
    @Pattern(regexp = "^[CD]$", message = "Valores viables C - Crédito o D - Débito.")
    private String productType;

    @ApiModelProperty(notes = "Titular de la tarjeta. Estructura: Primer nombre, espacio y primer apellido.")
    @Pattern(regexp = "^[A-z]+ [A-z]+$", message = "Debe estar presente mínimo un nombre y un apellido.")
    private String cardholder;
}
