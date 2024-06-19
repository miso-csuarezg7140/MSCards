package com.novatec.MSCards.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardDetailResponse {

    private Long cardId;
    private LocalDateTime dueDate;
    private Integer status;
}
