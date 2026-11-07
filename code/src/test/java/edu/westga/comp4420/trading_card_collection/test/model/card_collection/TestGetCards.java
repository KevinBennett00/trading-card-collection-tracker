package edu.westga.comp4420.trading_card_collection.test.model.card_collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;

import edu.westga.comp4420.trading_card_collection.model.Card;
import edu.westga.comp4420.trading_card_collection.model.CardCollection;

/**
 * Tests retrieving cards from a CardCollection.
 *
 * @author Kevin Bennett
 * @version Fall 2026
 */
public class TestGetCards {

	/**
	 * Tests that cards are returned in the order they were added.
	 */
	@Test
	public void shouldReturnCardsInOrder() {
		CardCollection collection = new CardCollection();
		Card firstCard = new Card("Charizard", "Pokemon", "Base Set", "4");
		Card secondCard = new Card("Pikachu", "Pokemon", "Jungle", "60");

		collection.addCard(firstCard);
		collection.addCard(secondCard);

		List<Card> cards = collection.getCards();

		assertEquals(2, cards.size());
		assertSame(firstCard, cards.get(0));
		assertSame(secondCard, cards.get(1));
	}

	/**
	 * Tests that the returned list cannot be modified.
	 */
	@Test
	public void shouldReturnUnmodifiableList() {
		CardCollection collection = new CardCollection();
		Card card = new Card("Charizard", "Pokemon", "Base Set", "4");
		collection.addCard(card);

		List<Card> cards = collection.getCards();

		assertThrows(UnsupportedOperationException.class, () -> cards.add(card));
	}

	/**
	 * Test that the returned list is a snapshot of the collection.
	 */
	@Test
	public void shouldReturnCopyOfCards() {
		CardCollection collection = new CardCollection();
		Card firstCard = new Card("Charizard", "Pokemon", "Base Set", "4");
		Card secondCard = new Card("Pikachu", "Pokemon", "Jungle", "60");
		collection.addCard(firstCard);

		List<Card> cards = collection.getCards();
		collection.addCard(secondCard);

		assertEquals(1, cards.size());
		assertEquals(2, collection.getCards().size());
	}
}