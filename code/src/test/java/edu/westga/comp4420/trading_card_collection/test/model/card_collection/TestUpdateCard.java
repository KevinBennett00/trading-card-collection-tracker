package edu.westga.comp4420.trading_card_collection.test.model.card_collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import edu.westga.comp4420.trading_card_collection.model.Card;
import edu.westga.comp4420.trading_card_collection.model.CardCollection;

/**
 * Tests updating cards in a card collection.
 *
 * @author Kevin Bennett
 * @version Fall 2026
 */
public class TestUpdateCard {

	/**
	 * Tests replacing a card with updated information.
	 */
	@Test
	public void shouldUpdateCard() {
		CardCollection collection = new CardCollection();
		Card currentCard = new Card("Pikachu", "Pokemon", "Base Set", "58");
		Card updatedCard = new Card("Raichu", "Pokemon", "Base Set", "14");
		collection.addCard(currentCard);

		collection.updateCard(currentCard, updatedCard);

		assertEquals(1, collection.getCards().size());
		assertSame(updatedCard, collection.getCards().get(0));
	}

	/**
	 * Tests rejecting a null current card.
	 */
	@Test
	public void shouldNotUpdateNullCurrentCard() {
		CardCollection collection = new CardCollection();
		Card updatedCard = new Card("Raichu", "Pokemon", "Base Set", "14");

		assertThrows(IllegalArgumentException.class, () -> collection.updateCard(null, updatedCard));
	}

	/**
	 * Tests rejecting a null updated card.
	 */
	@Test
	public void shouldNotUpdateWithNullCard() {
		CardCollection collection = new CardCollection();
		Card currentCard = new Card("Pikachu", "Pokemon", "Base Set", "58");
		collection.addCard(currentCard);

		assertThrows(IllegalArgumentException.class, () -> collection.updateCard(currentCard, null));
	}

	/**
	 * Tests rejecting a card that is not in the collection.
	 */
	@Test
	public void shouldNotUpdateMissingCard() {
		CardCollection collection = new CardCollection();
		Card missingCard = new Card("Pikachu", "Pokemon", "Base Set", "58");
		Card updatedCard = new Card("Raichu", "Pokemon", "Base Set", "14");

		assertThrows(IllegalArgumentException.class, () -> collection.updateCard(missingCard, updatedCard));
	}
}