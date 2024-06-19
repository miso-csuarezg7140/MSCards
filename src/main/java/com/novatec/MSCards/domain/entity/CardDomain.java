package com.novatec.MSCards.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardDomain {

    private Long cardId;
    private Integer status;
    private String cardholder;
    private LocalDateTime issuedDate;
    private LocalDateTime dueDate;
    private Long balance;
}
