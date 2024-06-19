package com.novatec.MSCards.domain.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ProductType {

    CREDIT('C', 654321L),
    DEBIT('D', 789123L);

    private Character cardType;
    private Long productId;
}
