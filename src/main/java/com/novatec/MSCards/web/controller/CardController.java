package com.novatec.MSCards.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.novatec.MSCards.domain.dto.ActivateCardRequest;
import com.novatec.MSCards.domain.dto.BlockCardRequest;
import com.novatec.MSCards.domain.dto.CreateCardRequest;
import com.novatec.MSCards.domain.dto.UpdateBalanceRequest;
import com.novatec.MSCards.domain.service.CardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/card")
@Tag(name = "cardController", description = "Controlador que contiene los métodos del microservicio que gestiona " +
        "las tarjetas.")
public class CardController {

    private final CardService cardService;

    @Operation(description = "Método que retorna un número de tarjeta con los primeros 6 dígitos dependiedo del " +
            "tipo de producto y los otros 10 dígitos aleatorios.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Respuesta exitosa, se retorna el número de " +
                            "tarjeta generado."),
                    @ApiResponse(responseCode = "400", description = "Petición incorrecta."),
                    @ApiResponse(responseCode = "401", description = "Usuario no autorizado."),
                    @ApiResponse(responseCode = "404", description = "No se encuentra el recurso solicitado."),
                    @ApiResponse(responseCode = "500", description = "Error del servidor.")
            }
    )
    @GetMapping("/{productId}/number")
    public ResponseEntity<?> getCardNumber(
            @PathVariable("productId") @Min(value = 100000, message = "El productId debe tener 6 dígitos.")
            @Max(value = 999999, message = "El producto debe tener 6 dígitos.") Long productId) {

        Long response = cardService.getCardNumber(productId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(description = "Método que permite la creación de una tarjeta dado un objeto de entrada con la " +
            "información correspondiente.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Respuesta exitosa, se crea la tarjeta " +
                            "correctamente."),
                    @ApiResponse(responseCode = "400", description = "Petición incorrecta."),
                    @ApiResponse(responseCode = "401", description = "Usuario no autorizado."),
                    @ApiResponse(responseCode = "404", description = "No se encuentra el recurso solicitado."),
                    @ApiResponse(responseCode = "500", description = "Error del servidor.")
            }
    )
    @PostMapping("/create")
    public ResponseEntity<?> createCard(@Valid @RequestBody CreateCardRequest createCardRequest)
            throws JsonProcessingException {
        String response = cardService.createCard(createCardRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(description = "Método que permite realizar la activación de una tarjeta previamente creada.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Respuesta exitosa, se activa la tarjeta " +
                            "correctamente."),
                    @ApiResponse(responseCode = "400", description = "Petición incorrecta."),
                    @ApiResponse(responseCode = "401", description = "Usuario no autorizado."),
                    @ApiResponse(responseCode = "404", description = "No se encuentra el recurso solicitado."),
                    @ApiResponse(responseCode = "500", description = "Error del servidor.")
            }
    )
    @PostMapping("/enroll")
    public ResponseEntity<?> activateCard(@Valid @RequestBody ActivateCardRequest activateCardRequest)
            throws JsonProcessingException {
        String response = cardService.activateCard(activateCardRequest);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(description = "Método que ejecuta la eliminación de una tarjeta.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "204", description = "Respuesta exitosa, se ha eliminado la tarjeta " +
                            "correctamente."),
                    @ApiResponse(responseCode = "400", description = "Petición incorrecta."),
                    @ApiResponse(responseCode = "401", description = "Usuario no autorizado."),
                    @ApiResponse(responseCode = "404", description = "No se encuentra el recurso solicitado."),
                    @ApiResponse(responseCode = "500", description = "Error del servidor.")
            }
    )
    @DeleteMapping("/{cardId}")
    public ResponseEntity<?> deleteCard(
            @PathVariable("cardId") @Min(value = 1000000000000000L, message = "CardId debe tener 16 dígitos.")
            @Max(value = 9999999999999999L, message = "CardId debe tener 16 dígitos.") Long cardId)
            throws JsonProcessingException {

        String response = cardService.deleteCard(cardId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
    }

    @Operation(description = "Método que ejecuta el bloqueo de una tarjeta.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Respuesta exitosa, se bloquea la tarjeta exitosamente."),
                    @ApiResponse(responseCode = "400", description = "Petición incorrecta."),
                    @ApiResponse(responseCode = "401", description = "Usuario no autorizado."),
                    @ApiResponse(responseCode = "404", description = "No se encuentra el recurso solicitado."),
                    @ApiResponse(responseCode = "500", description = "Error del servidor.")
            }
    )
    @PutMapping("/block")
    public ResponseEntity<?> blockCard(@Valid @RequestBody BlockCardRequest blockCardRequest)
            throws JsonProcessingException {

        String response = cardService.blockCard(blockCardRequest);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(description = "Método que actualiza el saldo de una tarjeta.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Respuesta exitosa, se actualiza el saldo de " +
                            "la tarjeta correctamente."),
                    @ApiResponse(responseCode = "400", description = "Petición incorrecta."),
                    @ApiResponse(responseCode = "401", description = "Usuario no autorizado."),
                    @ApiResponse(responseCode = "404", description = "No se encuentra el recurso solicitado."),
                    @ApiResponse(responseCode = "500", description = "Error del servidor.")
            }
    )
    @PostMapping("/balance")
    public ResponseEntity<?> updateBalance(@Valid @RequestBody UpdateBalanceRequest updateBalanceRequest)
            throws JsonProcessingException {

        String response = cardService.updateBalance(updateBalanceRequest);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(description = "Método que retorna el saldo de una tarjeta.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Respuesta exitosa, se retorna el saldo de la " +
                            "tarjeta correctamente."),
                    @ApiResponse(responseCode = "400", description = "Petición incorrecta."),
                    @ApiResponse(responseCode = "401", description = "Usuario no autorizado."),
                    @ApiResponse(responseCode = "404", description = "No se encuentra el recurso solicitado."),
                    @ApiResponse(responseCode = "500", description = "Error del servidor.")
            }
    )
    @GetMapping("/balance/{cardId}")
    public ResponseEntity<?> getBalance(
            @PathVariable("cardId") @Min(value = 1000000000000000L, message = "CardId debe tener 16 dígitos.")
            @Max(value = 9999999999999999L, message = "CardId debe tener 16 dígitos.") Long cardId)
            throws JsonProcessingException {

        String response = cardService.getBalance(cardId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(description = "Método que retorna el detalle de una tarjeta.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Respuesta exitosa, se retorna el detalle de la tarjeta " +
                            "correctamente."),
                    @ApiResponse(responseCode = "400", description = "Petición incorrecta."),
                    @ApiResponse(responseCode = "401", description = "Usuario no autorizado."),
                    @ApiResponse(responseCode = "404", description = "No se encuentra el recurso solicitado."),
                    @ApiResponse(responseCode = "500", description = "Error del servidor.")
            }
    )
    @GetMapping("/{cardId}")
    public ResponseEntity<?> getCardDetails(
            @PathVariable("cardId") @Min(value = 1000000000000000L, message = "CardId debe tener 16 dígitos.")
            @Max(value = 9999999999999999L, message = "CardId debe tener 16 dígitos.") Long cardId)
            throws JsonProcessingException {

        String response = cardService.getCardDetails(cardId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(description = "Método que verifica la salud del microservicio.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Respuesta exitosa, el microservicio se " +
                            "ejecuta sin problema."),
                    @ApiResponse(responseCode = "400", description = "Petición incorrecta."),
                    @ApiResponse(responseCode = "401", description = "Usuario no autorizado."),
                    @ApiResponse(responseCode = "404", description = "No se encuentra el recurso solicitado."),
                    @ApiResponse(responseCode = "500", description = "Error del servidor.")
            }
    )
    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }
}
