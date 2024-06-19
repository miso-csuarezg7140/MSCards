package com.novatec.MSCards.persistence.CRUDRepository;

import com.novatec.MSCards.domain.dto.BalanceResponse;
import com.novatec.MSCards.persistence.entity.Card;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ICRUDRepositoryCard extends CrudRepository<Card, Long> {

    @Query("SELECT DISTINCT new com.novatec.MSCards.domain.dto.BalanceResponse(c.cardId, c.balance) " +
            "FROM Card c WHERE c.cardId = :cardId")
    BalanceResponse getBalance(@Param("cardId") Long cardId);
}
