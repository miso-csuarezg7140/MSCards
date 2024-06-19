package com.novatec.MSCards.persistence.mapper;

import com.novatec.MSCards.domain.entity.CardDomain;
import com.novatec.MSCards.persistence.model.Card;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ICardMapper {

    CardDomain toCardDomain(Card card);

    @InheritInverseConfiguration
    Card fromCardDomain(CardDomain cardDomain);
}
