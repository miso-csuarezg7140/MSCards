package com.novatec.MSCards.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.novatec.MSCards.domain.dto.ActivateCardRequest;
import com.novatec.MSCards.domain.dto.BlockCardRequest;
import com.novatec.MSCards.domain.dto.CreateCardRequest;
import com.novatec.MSCards.domain.dto.UpdateBalanceRequest;
import com.novatec.MSCards.domain.service.CardService;
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
public class CardController {

    private final CardService cardService;

    @GetMapping("/{productId}/number")
    public ResponseEntity<?> getCardNumber(
            @PathVariable("productId") @Min(value = 100000, message = "El productId debe tener 6 dígitos.")
            @Max(value = 999999, message = "El producto debe tener 6 dígitos.") Long productId) {

        Long response = cardService.getCardNumber(productId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createCard(@Valid @RequestBody CreateCardRequest createCardRequest)
            throws JsonProcessingException {
        String response = cardService.createCard(createCardRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/enroll")
    public ResponseEntity<?> activateCard(@Valid @RequestBody ActivateCardRequest activateCardRequest)
            throws JsonProcessingException {
        String response = cardService.activateCard(activateCardRequest);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{cardId}")
    public ResponseEntity<?> deleteCard(
            @PathVariable("cardId") @Min(value = 1000000000000000L, message = "CardId debe tener 16 dígitos.")
            @Max(value = 9999999999999999L, message = "CardId debe tener 16 dígitos.") Long cardId)
            throws JsonProcessingException {

        String response = cardService.deleteCard(cardId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
    }

    @PutMapping("/block")
    public ResponseEntity<?> blockCard(@Valid @RequestBody BlockCardRequest blockCardRequest)
            throws JsonProcessingException {

        String response = cardService.blockCard(blockCardRequest);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/balance")
    public ResponseEntity<?> updateBalance(@Valid @RequestBody UpdateBalanceRequest updateBalanceRequest)
            throws JsonProcessingException {

        String response = cardService.updateBalance(updateBalanceRequest);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/balance/{cardId}")
    public ResponseEntity<?> getBalance(
            @PathVariable("cardId") @Min(value = 1000000000000000L, message = "CardId debe tener 16 dígitos.")
            @Max(value = 9999999999999999L, message = "CardId debe tener 16 dígitos.") Long cardId)
            throws JsonProcessingException {

        String response = cardService.getBalance(cardId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{cardId}")
    public ResponseEntity<?> getCardDetails(
            @PathVariable("cardId") @Min(value = 1000000000000000L, message = "CardId debe tener 16 dígitos.")
            @Max(value = 9999999999999999L, message = "CardId debe tener 16 dígitos.") Long cardId)
            throws JsonProcessingException {

        String response = cardService.getCardDetails(cardId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }
}
