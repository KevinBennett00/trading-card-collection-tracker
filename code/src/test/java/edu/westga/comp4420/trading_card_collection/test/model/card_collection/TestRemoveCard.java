package edu.westga.comp4420.trading_card_collection.test.model.card_collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import edu.westga.comp4420.trading_card_collection.model.Card;
import edu.westga.comp4420.trading_card_collection.model.CardCollection;

/**
 * Tests removing cards from a card collection.
 *
 * @author Kevin Bennett
 * @version Fall 2026
 */
class TestRemoveCard {

	@Test
	void shouldRemoveCardFromCollection() {
		CardCollection collection = new CardCollection();
		Card card = this.createCard();
		collection.addCard(card);

		collection.removeCard(card);

		assertTrue(collection.getCards().isEmpty());
	}

	@Test
	void shouldRemoveOnlySelectedCard() {
		CardCollection collection = new CardCollection();
		Card firstCard = this.createCard();
		Card secondCard = new Card("Black Lotus", "Magic", "Alpha", "232");
		collection.addCard(firstCard);
		collection.addCard(secondCard);

		collection.removeCard(firstCard);

		assertEquals(1, collection.getCards().size());
		assertSame(secondCard, collection.getCards().get(0));
	}

	@Test
	void shouldRejectNullCard() {
		CardCollection collection = new CardCollection();

		assertThrows(IllegalArgumentException.class, () -> collection.removeCard(null));
	}

	@Test
	void shouldRejectCardNotInCollection() {
		CardCollection collection = new CardCollection();
		Card card = this.createCard();

		assertThrows(IllegalArgumentException.class, () -> collection.removeCard(card));
	}

	private Card createCard() {
		return new Card("Charizard", "Pokemon", "Base Set", "4");
	}
}