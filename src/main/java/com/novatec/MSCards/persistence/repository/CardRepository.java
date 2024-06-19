package com.novatec.MSCards.persistence.repository;

import com.novatec.MSCards.domain.dto.BalanceResponse;
import com.novatec.MSCards.domain.entity.CardDomain;
import com.novatec.MSCards.domain.repository.ICardRepository;
import com.novatec.MSCards.persistence.CRUDRepository.ICRUDRepositoryCard;
import com.novatec.MSCards.persistence.mapper.ICardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class CardRepository implements ICardRepository {

    private final ICRUDRepositoryCard crudRepositoryCard;

    private final ICardMapper cardMapper;

    @Override
    public CardDomain findById(Long cardId) {
        return cardMapper.toCardDomain(crudRepositoryCard.findById(cardId).orElse(null));
    }

    @Override
    public void save(CardDomain cardDomain) {
        crudRepositoryCard.save(cardMapper.fromCardDomain(cardDomain));
    }

    @Override
    public void delete(CardDomain cardDomain) {
        crudRepositoryCard.delete(cardMapper.fromCardDomain(cardDomain));
    }

    @Override
    public BalanceResponse getBalance(Long cardId) {
        return crudRepositoryCard.getBalance(cardId);
    }
}
