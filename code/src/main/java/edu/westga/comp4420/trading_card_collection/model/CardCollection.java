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
			throw new IllegalArgumentException("Card cannot be null.");
		}

		this.cards.add(card);
	}

	/**
	 * Returns the cards currently in the collection.
	 *
	 * @return an copy of the cards
	 */
	public List<Card> getCards() {
		return Collections.unmodifiableList(new ArrayList<Card>(this.cards));
	}
}