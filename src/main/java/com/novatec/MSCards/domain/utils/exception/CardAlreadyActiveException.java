package com.novatec.MSCards.domain.utils.exception;

public class CardAlreadyActiveException extends RuntimeException {
    public CardAlreadyActiveException(String message) {
        super(message);
    }
}