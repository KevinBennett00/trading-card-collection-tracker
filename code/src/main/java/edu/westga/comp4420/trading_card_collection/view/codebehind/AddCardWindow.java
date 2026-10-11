package edu.westga.comp4420.trading_card_collection.view.codebehind;

import edu.westga.comp4420.trading_card_collection.model.Card;
import edu.westga.comp4420.trading_card_collection.model.CardCollection;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Displays a window for adding a card to the collection.
 *
 * @author Kevin Bennett
 * @version Fall 2026
 */
public class AddCardWindow extends Stage {

	private static final double PADDING = 20.0;
	private static final double GAP = 10.0;
	private static final double WINDOW_WIDTH = 400.0;
	private static final double WINDOW_HEIGHT = 280.0;

	private final CardCollection collection;
	private final Runnable refreshAction;
	private final TextField nameField;
	private final TextField gameField;
	private final TextField setField;
	private final TextField cardNumberField;

	/**
	 * Creates the add-card window.
	 *
	 * @param collection    the card collection
	 * @param refreshAction the action used to refresh the collection table
	 */
	public AddCardWindow(CardCollection collection, Runnable refreshAction) {
		this.collection = collection;
		this.refreshAction = refreshAction;
		this.nameField = new TextField();
		this.gameField = new TextField();
		this.setField = new TextField();
		this.cardNumberField = new TextField();

		this.buildWindow();
	}

	private void buildWindow() {
		this.setTitle("Add Card");
		this.initModality(Modality.APPLICATION_MODAL);

		GridPane form = new GridPane();
		form.setPadding(new Insets(PADDING));
		form.setHgap(GAP);
		form.setVgap(GAP);

		form.add(new Label("Name:"), 0, 0);
		form.add(this.nameField, 1, 0);
		form.add(new Label("Game:"), 0, 1);
		form.add(this.gameField, 1, 1);
		form.add(new Label("Set:"), 0, 2);
		form.add(this.setField, 1, 2);
		form.add(new Label("Card Number:"), 0, 3);
		form.add(this.cardNumberField, 1, 3);

		Button saveButton = new Button("Save");
		Button cancelButton = new Button("Cancel");

		saveButton.setOnAction(event -> this.saveCard());
		cancelButton.setOnAction(event -> this.close());

		HBox buttonBar = new HBox(GAP, saveButton, cancelButton);
		buttonBar.setAlignment(Pos.CENTER_RIGHT);
		form.add(buttonBar, 1, 4);

		this.setScene(new Scene(form, WINDOW_WIDTH, WINDOW_HEIGHT));
	}

	private void saveCard() {
		try {
			Card card = new Card(this.nameField.getText(), this.gameField.getText(), this.setField.getText(),
					this.cardNumberField.getText());

			this.collection.addCard(card);
			this.refreshAction.run();
			this.close();
		} catch (IllegalArgumentException exception) {
			this.showError(exception.getMessage());
		}
	}

	private void showError(String message) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.initOwner(this);
		alert.setTitle("Unable to Add Card");
		alert.setHeaderText("Please correct the card information.");
		alert.setContentText(message);
		alert.showAndWait();
	}
}