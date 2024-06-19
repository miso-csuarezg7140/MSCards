package com.novatec.MSCards.domain.repository;

import com.novatec.MSCards.domain.dto.BalanceResponse;
import com.novatec.MSCards.domain.dto.CardDetailResponse;
import com.novatec.MSCards.domain.entity.CardDomain;

public interface ICardRepository {

    CardDomain findById(Long cardId);

    CardDomain save(CardDomain cardDomain);

    void delete(CardDomain cardDomain);

    BalanceResponse getBalance(Long cardId);

    CardDetailResponse getCardDetail(Long cardId);
}
