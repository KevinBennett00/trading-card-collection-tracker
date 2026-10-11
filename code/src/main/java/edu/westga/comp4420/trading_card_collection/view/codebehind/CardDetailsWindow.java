package edu.westga.comp4420.trading_card_collection.view.codebehind;

import edu.westga.comp4420.trading_card_collection.model.Card;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Displays the details of a selected trading card.
 *
 * @author Kevin Bennett
 * @version Fall 2026
 */
public class CardDetailsWindow extends Stage {

	private static final double PADDING = 20.0;
	private static final double GAP = 10.0;
	private static final double WINDOW_WIDTH = 400.0;
	private static final double WINDOW_HEIGHT = 280.0;

	/**
	 * Creates the card-details window.
	 *
	 * @param card the card whose information will be displayed
	 */
	public CardDetailsWindow(Card card) {
		this.buildWindow(card);
	}

	private void buildWindow(Card card) {
		this.setTitle("Card Details");
		this.initModality(Modality.APPLICATION_MODAL);

		GridPane details = new GridPane();
		details.setPadding(new Insets(PADDING));
		details.setHgap(GAP);
		details.setVgap(GAP);

		details.add(new Label("Name:"), 0, 0);
		details.add(new Label(card.getName()), 1, 0);

		details.add(new Label("Game:"), 0, 1);
		details.add(new Label(card.getGame()), 1, 1);

		details.add(new Label("Set:"), 0, 2);
		details.add(new Label(card.getSetName()), 1, 2);

		details.add(new Label("Card Number:"), 0, 3);
		details.add(new Label(card.getCardNumber()), 1, 3);

		Button backButton = new Button("Back");
		backButton.setOnAction(event -> this.close());

		details.add(backButton, 1, 4);
		GridPane.setHalignment(backButton, Pos.CENTER_RIGHT.getHpos());

		this.setScene(new Scene(details, WINDOW_WIDTH, WINDOW_HEIGHT));
	}
}