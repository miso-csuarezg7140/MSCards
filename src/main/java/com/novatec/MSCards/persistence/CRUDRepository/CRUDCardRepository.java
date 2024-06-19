package com.novatec.MSCards.persistence.CRUDRepository;

import com.novatec.MSCards.domain.dto.BalanceResponse;
import com.novatec.MSCards.domain.dto.CardDetailResponse;
import com.novatec.MSCards.persistence.model.Card;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface CRUDCardRepository extends CrudRepository<Card, Long> {

    @Query("SELECT DISTINCT new com.novatec.MSCards.domain.dto.BalanceResponse(c.cardId, c.balance) " +
            "FROM Card c WHERE c.cardId = :cardId")
    BalanceResponse getBalance(@Param("cardId") Long cardId);

    @Query("SELECT DISTINCT new com.novatec.MSCards.domain.dto.CardDetailResponse(c.cardId, c.dueDate, c.status) " +
            "FROM Card c WHERE c.cardId = :cardId")
    CardDetailResponse getCardDetail(@Param("cardId") Long cardId);
}
