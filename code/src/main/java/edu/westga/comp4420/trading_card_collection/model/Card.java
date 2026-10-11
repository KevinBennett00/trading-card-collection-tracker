package edu.westga.comp4420.trading_card_collection.model;

/**
 * Stores the identifying information for a trading card.
 *
 * @author Kevin Bennett
 * @version Fall 2026
 */
public final class Card {

	private final String name;
	private final String game;
	private final String setName;
	private final String cardNumber;

	/**
	 * Creates a card with the provided identifying information.
	 *
	 * @precondition name != null && !name.isBlank() && game != null &&
	 *               !game.isBlank() && setName != null && !setName.isBlank() &&
	 *               cardNumber != null && !cardNumber.isBlank()
	 * @postcondition getName().equals(name) && getGame().equals(game) &&
	 *                getSetName().equals(setName) &&
	 *                getCardNumber().equals(cardNumber)
	 *
	 * @param name       the card's name
	 * @param game       the card's game
	 * @param setName    the card's set
	 * @param cardNumber the card's number within its set
	 */
	public Card(String name, String game, String setName, String cardNumber) {
		if (name == null || name.isBlank()) {
			throw new IllegalArgumentException("Card name cannot be blank.");
		}
		if (game == null || game.isBlank()) {
			throw new IllegalArgumentException("Card game cannot be blank.");
		}
		if (setName == null || setName.isBlank()) {
			throw new IllegalArgumentException("Card set cannot be blank.");
		}
		if (cardNumber == null || cardNumber.isBlank()) {
			throw new IllegalArgumentException("Card number cannot be blank.");
		}

		this.name = name;
		this.game = game;
		this.setName = setName;
		this.cardNumber = cardNumber;
	}

	/**
	 * Gets the card's name.
	 *
	 * @return the card's name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Gets the card's game.
	 *
	 * @return the card's game
	 */
	public String getGame() {
		return this.game;
	}

	/**
	 * Gets the card's set.
	 *
	 * @return the card's set
	 */
	public String getSetName() {
		return this.setName;
	}

	/**
	 * Gets the card's number within its set.
	 *
	 * @return the card's number
	 */
	public String getCardNumber() {
		return this.cardNumber;
	}
}