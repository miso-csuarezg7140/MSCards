package com.novatec.MSCards.domain.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.novatec.MSCards.domain.dto.ActivateCardRequest;
import com.novatec.MSCards.domain.dto.AddBalanceRequest;
import com.novatec.MSCards.domain.dto.BalanceResponse;
import com.novatec.MSCards.domain.dto.BlockCardRequest;
import com.novatec.MSCards.domain.dto.CardDetailResponse;
import com.novatec.MSCards.domain.dto.CreateCardRequest;
import com.novatec.MSCards.domain.entity.CardDomain;
import com.novatec.MSCards.domain.repository.ICardRepository;
import com.novatec.MSCards.domain.utils.CardState;
import com.novatec.MSCards.domain.utils.ProductType;
import com.novatec.MSCards.domain.utils.exception.CardAlreadyActiveException;
import com.novatec.MSCards.domain.utils.exception.CardAlreadyBlockedException;
import com.novatec.MSCards.domain.utils.exception.CardAlreadyExistsException;
import com.novatec.MSCards.domain.utils.exception.CardBlockedException;
import com.novatec.MSCards.domain.utils.exception.CardNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

@RequiredArgsConstructor
@Service
public class CardService {

    private final ICardRepository cardRepository;

    private final ObjectMapper objectMapper;

    /**
     * <p></p>
     *
     * @param productId
     * @return
     */
    public Long getCardNumber(Long productId) {
        String productIdStr = String.valueOf(productId);
        long min = 1000000000L;
        long max = 9999999999L;
        long randomCardNumber = ThreadLocalRandom.current().nextLong(min, max + 1);
        String randomCardNumberStr = productIdStr + randomCardNumber;
        return Long.parseLong(randomCardNumberStr);
    }

    /**
     * <p></p>
     *
     * @param createCardRequest
     * @return
     * @throws JsonProcessingException
     */
    public String createCard(CreateCardRequest createCardRequest) throws JsonProcessingException {

        Long productId = 0L;

        if ("C".equals(createCardRequest.getProductType())) {
            productId = ProductType.CREDIT.getProductId();
        } else if ("D".equals(createCardRequest.getProductType())) {
            productId = ProductType.DEBIT.getProductId();
        }

        Long cardNumber = getCardNumber(productId);
        CardDomain cardDomain = cardRepository.findById(cardNumber);

        if (cardDomain == null) {
            LocalDateTime today = LocalDateTime.now();
            cardDomain = new CardDomain();
            cardDomain.setCardId(cardNumber);
            cardDomain.setBalance(0L);
            cardDomain.setCardholder(createCardRequest.getCardholder());
            cardDomain.setIssuedDate(today);
            cardDomain.setDueDate(today.plusYears(3));
            cardDomain.setStatus(CardState.INACTIVE.getCardState());

            cardRepository.save(cardDomain);
            cardDomain = cardRepository.findById(cardNumber);
            return objectMapper.writeValueAsString(cardDomain);
        } else {
            throw new CardAlreadyExistsException("Card with ID " + cardNumber + " already exists.");
        }
    }

    /**
     * <p></p>
     *
     * @param activateCardRequest
     * @return
     * @throws JsonProcessingException
     */
    public String activateCard(ActivateCardRequest activateCardRequest) throws JsonProcessingException {

        Long cardNumber = Long.parseLong(activateCardRequest.getCardId());
        CardDomain cardDomain = cardRepository.findById(cardNumber);

        if (cardDomain != null) {
            if (Objects.equals(CardState.INACTIVE.getCardState(), cardDomain.getStatus())) {
                cardDomain.setStatus(1);
                cardRepository.save(cardDomain);
                cardDomain = cardRepository.findById(cardNumber);
                return objectMapper.writeValueAsString(cardDomain);
            } else if (Objects.equals(CardState.ACTIVE.getCardState(), cardDomain.getStatus())) {
                throw new CardAlreadyActiveException("Card with ID " + cardNumber + " is already active.");
            } else {
                throw new CardBlockedException("Card with ID " + cardNumber + " is blocked and doesn't allow the " +
                        "activation process.");
            }
        } else {
            throw new CardNotFoundException("Card with ID " + cardNumber + " doesn't exist.");
        }
    }

    /**
     * <p></p>
     *
     * @param cardNumber
     * @return
     * @throws JsonProcessingException
     */
    public String deleteCard(Long cardNumber) throws JsonProcessingException {

        CardDomain cardDomain = cardRepository.findById(cardNumber);

        if (cardDomain != null) {
            cardRepository.delete(cardDomain);
            cardDomain = cardRepository.findById(cardNumber);
            return objectMapper.writeValueAsString(cardDomain);
        } else {
            throw new CardNotFoundException("Card with ID " + cardNumber + " doesn't exist.");
        }
    }

    /**
     * <p></p>
     *
     * @param blockCardRequest
     * @return
     * @throws JsonProcessingException
     */
    public String blockCard(BlockCardRequest blockCardRequest) throws JsonProcessingException {

        Long cardNumber = Long.parseLong(blockCardRequest.getCardId());
        CardDomain cardDomain = cardRepository.findById(cardNumber);

        if (cardDomain != null) {
            if (Objects.equals(CardState.ACTIVE.getCardState(), cardDomain.getStatus())
                    || Objects.equals(CardState.INACTIVE.getCardState(), cardDomain.getStatus())) {
                cardDomain.setStatus(3);
                cardRepository.save(cardDomain);
                cardDomain = cardRepository.findById(cardNumber);
                return objectMapper.writeValueAsString(cardDomain);
            } else {
                throw new CardAlreadyBlockedException("Card with ID " + cardNumber + " is already blocked.");
            }
        } else {
            throw new CardNotFoundException("Card with ID " + cardNumber + " doesn't exist.");
        }
    }

    /**
     * <p></p>
     *
     * @param addBalanceRequest
     * @return
     * @throws JsonProcessingException
     */
    public String addBalance(AddBalanceRequest addBalanceRequest) throws JsonProcessingException {

        Long cardNumber = Long.parseLong(addBalanceRequest.getCardId());
        CardDomain cardDomain = cardRepository.findById(cardNumber);

        if (cardDomain != null) {
            cardDomain.setBalance(cardDomain.getBalance() + addBalanceRequest.getBalance());
            cardRepository.save(cardDomain);
            cardDomain = cardRepository.findById(cardNumber);
            return objectMapper.writeValueAsString(cardDomain);
        } else {
            throw new CardNotFoundException("Card with ID " + cardNumber + " doesn't exist.");
        }
    }

    /**
     * <p></p>
     *
     * @param cardNumber
     * @return
     * @throws JsonProcessingException
     */
    public String getBalance(Long cardNumber) throws JsonProcessingException {

        CardDomain cardDomain = cardRepository.findById(cardNumber);

        if (cardDomain != null) {
            BalanceResponse balanceResponse = cardRepository.getBalance(cardNumber);
            return objectMapper.writeValueAsString(balanceResponse);
        } else {
            throw new CardNotFoundException("Card with ID " + cardNumber + " doesn't exist.");
        }
    }

    /**
     * <p></p>
     *
     * @param cardNumber
     * @return
     * @throws JsonProcessingException
     */
    public String getCardDetails(Long cardNumber) throws JsonProcessingException {

        CardDomain cardDomain = cardRepository.findById(cardNumber);

        if (cardDomain != null) {
            CardDetailResponse cardDetailResponse = cardRepository.getCardDetail(cardNumber);
            return objectMapper.writeValueAsString(cardDetailResponse);

        } else {
            throw new CardNotFoundException("Card with ID " + cardNumber + " doesn't exist.");
        }
    }
}
