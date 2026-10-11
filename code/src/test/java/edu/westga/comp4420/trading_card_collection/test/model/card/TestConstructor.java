package edu.westga.comp4420.trading_card_collection.test.model.card;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import edu.westga.comp4420.trading_card_collection.model.Card;

/**
 * Tests the Card constructor.
 *
 * @author Kevin Bennett
 * @version Fall 2026
 */
public class TestConstructor {

	/**
	 * Tests creating a card with valid information.
	 */
	@Test
	public void testShouldCreateCardWithValidInformation() {
		Card card = new Card("Charizard", "Pokemon", "Base Set", "4/102");

		assertEquals("Charizard", card.getName());
		assertEquals("Pokemon", card.getGame());
		assertEquals("Base Set", card.getSetName());
		assertEquals("4/102", card.getCardNumber());
	}

	/**
	 * Tests that a card cannot have an invalid name.
	 *
	 * @param name the invalid name
	 */
	@ParameterizedTest
	@NullSource
	@ValueSource(strings = { "", " " })
	public void testShouldNotCreateCardWithInvalidName(String name) {
		assertThrows(IllegalArgumentException.class, () -> new Card(name, "Pokemon", "Base Set", "4/102"));
	}

	/**
	 * Tests that a card cannot have an invalid game.
	 *
	 * @param game the invalid game
	 */
	@ParameterizedTest
	@NullSource
	@ValueSource(strings = { "", " " })
	public void testShouldNotCreateCardWithInvalidGame(String game) {
		assertThrows(IllegalArgumentException.class, () -> new Card("Charizard", game, "Base Set", "4/102"));
	}

	/**
	 * Tests that a card cannot have an invalid set.
	 *
	 * @param setName the invalid set name
	 */
	@ParameterizedTest
	@NullSource
	@ValueSource(strings = { "", " " })
	public void testShouldNotCreateCardWithInvalidSet(String setName) {
		assertThrows(IllegalArgumentException.class, () -> new Card("Charizard", "Pokemon", setName, "4/102"));
	}

	/**
	 * Tests that a card cannot have an invalid card number.
	 *
	 * @param cardNumber the invalid card number
	 */
	@ParameterizedTest
	@NullSource
	@ValueSource(strings = { "", " " })
	public void testShouldNotCreateCardWithInvalidNumber(String cardNumber) {
		assertThrows(IllegalArgumentException.class, () -> new Card("Charizard", "Pokemon", "Base Set", cardNumber));
	}
}