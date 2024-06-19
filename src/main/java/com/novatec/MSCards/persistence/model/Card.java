package com.novatec.MSCards.persistence.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "card", schema = "public")
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Card {

    @Id
    @Column(name = "card_id", nullable = false)
    private Long cardId;

    @Column(name = "status", nullable = false)
    private Integer status;

    @Column(name = "cardholder", nullable = false)
    private String cardholder;

    @Column(name = "issued_date", nullable = false)
    private LocalDateTime issuedDate;

    @Column(name = "due_date", nullable = false)
    private LocalDateTime dueDate;

    @Column(name = "balance", nullable = false)
    private Long balance;
}
