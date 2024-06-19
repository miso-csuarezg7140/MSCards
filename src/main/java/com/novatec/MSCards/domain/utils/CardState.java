package com.novatec.MSCards.domain.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum CardState {

    INACTIVE(0),
    ACTIVE(1),
    BLOCKED(2);

    private Integer cardState;
}
