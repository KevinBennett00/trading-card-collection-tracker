package edu.westga.comp4420.trading_card_collection.test.model.card_collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import edu.westga.comp4420.trading_card_collection.model.Card;
import edu.westga.comp4420.trading_card_collection.model.CardCollection;

/**
 * Tests adding cards to a CardCollection.
 *
 * @author Kevin Bennett
 * @version Fall 2026
 */
public class TestAddCard {

	/**
	 * Tests that a card can be added to the collection.
	 */
	@Test
	public void shouldAddCardToCollection() {
		CardCollection collection = new CardCollection();
		Card card = new Card("Charizard", "Pokemon", "Base Set", "4");

		collection.addCard(card);

		assertEquals(1, collection.getCards().size());
		assertSame(card, collection.getCards().get(0));
	}

	/**
	 * Tests that a null card cannot be added.
	 */
	@Test
	public void shouldNotAddNullCard() {
		CardCollection collection = new CardCollection();

		assertThrows(IllegalArgumentException.class, () -> collection.addCard(null));
	}
}