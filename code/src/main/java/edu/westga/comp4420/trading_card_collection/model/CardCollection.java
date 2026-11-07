package edu.westga.comp4420.trading_card_collection.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Stores the cards in a user's collection.
 *
 * @author Kevin Bennett
 * @version Fall 2026
 */
public class CardCollection {

	private static final String CARD_NULL_ERROR = "Card cannot be null.";
	private static final String CARD_NOT_FOUND_ERROR = "Card is not in the collection.";

	private final List<Card> cards;

	/**
	 * Creates an empty card collection.
	 */
	public CardCollection() {
		this.cards = new ArrayList<Card>();
	}

	/**
	 * Adds a card to the collection.
	 *
	 * @param card the card to add
	 * @throws IllegalArgumentException if the card is null
	 */
	public void addCard(Card card) {
		if (card == null) {
			throw new IllegalArgumentException(CARD_NULL_ERROR);
		}

		this.cards.add(card);
	}

	/**
	 * Replaces an existing card with an updated card.
	 *
	 * @param existingCard the card currently in the collection
	 * @param updatedCard  the card containing the updated information
	 * @throws IllegalArgumentException if either card is null or the existing card
	 *                                  is not in the collection
	 */
	public void updateCard(Card existingCard, Card updatedCard) {
		if (existingCard == null) {
			throw new IllegalArgumentException("Existing card cannot be null.");
		}
		if (updatedCard == null) {
			throw new IllegalArgumentException("Updated card cannot be null.");
		}

		int cardIndex = this.cards.indexOf(existingCard);
		if (cardIndex < 0) {
			throw new IllegalArgumentException(CARD_NOT_FOUND_ERROR);
		}

		this.cards.set(cardIndex, updatedCard);
	}

	/**
	 * Removes a card from the collection.
	 *
	 * @param card the card to remove
	 * @throws IllegalArgumentException if the card is null or is not in the
	 *                                  collection
	 */
	public void removeCard(Card card) {
		if (card == null) {
			throw new IllegalArgumentException(CARD_NULL_ERROR);
		}
		if (!this.cards.remove(card)) {
			throw new IllegalArgumentException(CARD_NOT_FOUND_ERROR);
		}
	}

	/**
	 * Returns the cards currently in the collection.
	 *
	 * @return a copy of the cards
	 */
	public List<Card> getCards() {
		return Collections.unmodifiableList(new ArrayList<Card>(this.cards));
	}
}