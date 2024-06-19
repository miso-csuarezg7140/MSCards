package com.novatec.MSCards.persistence.repository;

import com.novatec.MSCards.domain.dto.BalanceResponse;
import com.novatec.MSCards.domain.dto.CardDetailResponse;
import com.novatec.MSCards.domain.entity.CardDomain;
import com.novatec.MSCards.domain.repository.ICardRepository;
import com.novatec.MSCards.persistence.CRUDRepository.CRUDCardRepository;
import com.novatec.MSCards.persistence.mapper.ICardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class CardRepository implements ICardRepository {

    private final CRUDCardRepository crudCardRepository;

    private final ICardMapper cardMapper;

    @Override
    public CardDomain findById(Long cardId) {
        return cardMapper.toCardDomain(crudCardRepository.findById(cardId).orElse(null));
    }

    @Override
    public void save(CardDomain cardDomain) {
        crudCardRepository.save(cardMapper.fromCardDomain(cardDomain));
    }

    @Override
    public void delete(CardDomain cardDomain) {
        crudCardRepository.delete(cardMapper.fromCardDomain(cardDomain));
    }

    @Override
    public BalanceResponse getBalance(Long cardId) {
        return crudCardRepository.getBalance(cardId);
    }

    @Override
    public CardDetailResponse getCardDetail(Long cardId) {
        return crudCardRepository.getCardDetail(cardId);
    }
}
