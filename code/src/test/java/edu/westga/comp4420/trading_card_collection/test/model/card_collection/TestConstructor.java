package edu.westga.comp4420.trading_card_collection.test.model.card_collection;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import edu.westga.comp4420.trading_card_collection.model.CardCollection;

/**
 * Tests the CardCollection constructor.
 *
 * @author Kevin Bennett
 * @version Fall 2026
 */
public class TestConstructor {

	/**
	 * Verifies that a new collection is empty.
	 */
	@Test
	public void shouldCreateEmptyCollection() {
		CardCollection collection = new CardCollection();

		assertTrue(collection.getCards().isEmpty());
	}
}