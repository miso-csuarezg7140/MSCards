package com.novatec.MSCards.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.novatec.MSCards.domain.dto.ActivateCardRequest;
import com.novatec.MSCards.domain.dto.BlockCardRequest;
import com.novatec.MSCards.domain.dto.CreateCardRequest;
import com.novatec.MSCards.domain.dto.UpdateBalanceRequest;
import com.novatec.MSCards.domain.service.CardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
@Api(value = "Controlador que contiene los métodos del microservicio que gestiona las tarjetas.")
public class CardController {

    private final CardService cardService;

    @ApiOperation(value = "Método que retorna un número de tarjeta con los primeros 6 dígitos dependiedo del tipo de " +
            "producto y los otros 10 dígitos aleatorios.")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Respuesta exitosa, se retorna el número de tarjeta generado."),
                    @ApiResponse(code = 400, message = "Petición incorrecta."),
                    @ApiResponse(code = 401, message = "Usuario no autorizado."),
                    @ApiResponse(code = 404, message = "No se encuentra el recurso solicitado."),
                    @ApiResponse(code = 500, message = "Error del servidor.")
            }
    )
    @GetMapping("/{productId}/number")
    public ResponseEntity<?> getCardNumber(
            @PathVariable("productId") @Min(value = 100000, message = "El productId debe tener 6 dígitos.")
            @Max(value = 999999, message = "El producto debe tener 6 dígitos.") Long productId) {

        Long response = cardService.getCardNumber(productId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @ApiOperation(value = "Método que permite la creación de una tarjeta dado un objeto de entrada con la " +
            "información correspondiente.")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Respuesta exitosa, se crea la tarjeta correctamente."),
                    @ApiResponse(code = 400, message = "Petición incorrecta."),
                    @ApiResponse(code = 401, message = "Usuario no autorizado."),
                    @ApiResponse(code = 404, message = "No se encuentra el recurso solicitado."),
                    @ApiResponse(code = 500, message = "Error del servidor.")
            }
    )
    @PostMapping("/create")
    public ResponseEntity<?> createCard(@Valid @RequestBody CreateCardRequest createCardRequest)
            throws JsonProcessingException {
        String response = cardService.createCard(createCardRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @ApiOperation(value = "Método que permite realizar la activación de una tarjeta previamente creada.")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Respuesta exitosa, se activa la tarjeta correctamente."),
                    @ApiResponse(code = 400, message = "Petición incorrecta."),
                    @ApiResponse(code = 401, message = "Usuario no autorizado."),
                    @ApiResponse(code = 404, message = "No se encuentra el recurso solicitado."),
                    @ApiResponse(code = 500, message = "Error del servidor.")
            }
    )
    @PostMapping("/enroll")
    public ResponseEntity<?> activateCard(@Valid @RequestBody ActivateCardRequest activateCardRequest)
            throws JsonProcessingException {
        String response = cardService.activateCard(activateCardRequest);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @ApiOperation(value = "Método que ejecuta la eliminación de una tarjeta.")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 204, message = "Respuesta exitosa, se ha eliminado la tarjeta correctamente."),
                    @ApiResponse(code = 400, message = "Petición incorrecta."),
                    @ApiResponse(code = 401, message = "Usuario no autorizado."),
                    @ApiResponse(code = 404, message = "No se encuentra el recurso solicitado."),
                    @ApiResponse(code = 500, message = "Error del servidor.")
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

    @ApiOperation(value = "Método que ejecuta el bloqueo de una tarjeta.")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Respuesta exitosa, se bloquea la tarjeta exitosamente."),
                    @ApiResponse(code = 400, message = "Petición incorrecta."),
                    @ApiResponse(code = 401, message = "Usuario no autorizado."),
                    @ApiResponse(code = 404, message = "No se encuentra el recurso solicitado."),
                    @ApiResponse(code = 500, message = "Error del servidor.")
            }
    )
    @PutMapping("/block")
    public ResponseEntity<?> blockCard(@Valid @RequestBody BlockCardRequest blockCardRequest)
            throws JsonProcessingException {

        String response = cardService.blockCard(blockCardRequest);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @ApiOperation(value = "Método que actualiza el saldo de una tarjeta.")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Respuesta exitosa, se actualiza el saldo de la tarjeta " +
                            "correctamente."),
                    @ApiResponse(code = 400, message = "Petición incorrecta."),
                    @ApiResponse(code = 401, message = "Usuario no autorizado."),
                    @ApiResponse(code = 404, message = "No se encuentra el recurso solicitado."),
                    @ApiResponse(code = 500, message = "Error del servidor.")
            }
    )
    @PostMapping("/balance")
    public ResponseEntity<?> updateBalance(@Valid @RequestBody UpdateBalanceRequest updateBalanceRequest)
            throws JsonProcessingException {

        String response = cardService.updateBalance(updateBalanceRequest);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @ApiOperation(value = "Método que retorna el saldo de una tarjeta.")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Respuesta exitosa, se retorna el saldo de la tarjeta " +
                            "correctamente."),
                    @ApiResponse(code = 400, message = "Petición incorrecta."),
                    @ApiResponse(code = 401, message = "Usuario no autorizado."),
                    @ApiResponse(code = 404, message = "No se encuentra el recurso solicitado."),
                    @ApiResponse(code = 500, message = "Error del servidor.")
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

    @ApiOperation(value = "Método que retorna el detalle de una tarjeta.")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Respuesta exitosa, se retorna el detalle de la tarjeta " +
                            "correctamente."),
                    @ApiResponse(code = 400, message = "Petición incorrecta."),
                    @ApiResponse(code = 401, message = "Usuario no autorizado."),
                    @ApiResponse(code = 404, message = "No se encuentra el recurso solicitado."),
                    @ApiResponse(code = 500, message = "Error del servidor.")
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

    @ApiOperation(value = "Método que verifica la salud del microservicio.")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Respuesta exitosa, el microservicio se ejecuta sin problema."),
                    @ApiResponse(code = 400, message = "Petición incorrecta."),
                    @ApiResponse(code = 401, message = "Usuario no autorizado."),
                    @ApiResponse(code = 404, message = "No se encuentra el recurso solicitado."),
                    @ApiResponse(code = 500, message = "Error del servidor.")
            }
    )
    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }
}
