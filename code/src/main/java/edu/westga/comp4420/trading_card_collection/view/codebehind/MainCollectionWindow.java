package edu.westga.comp4420.trading_card_collection.view.codebehind;

import edu.westga.comp4420.trading_card_collection.model.Card;
import edu.westga.comp4420.trading_card_collection.model.CardCollection;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

/**
 * Displays the cards stored in the user's collection.
 *
 * @author Kevin Bennett
 * @version Fall 2026
 */
public class MainCollectionWindow extends BorderPane {

	private static final double PADDING = 20.0;
	private static final double SPACING = 10.0;

	private final CardCollection collection;
	private final TableView<Card> cardTable;
	private final Button addButton;
	private final Button detailsButton;
	private final Button editButton;
	private final Button removeButton;

	/**
	 * Creates the main collection window.
	 *
	 * @param collection the card collection displayed by the window
	 */
	public MainCollectionWindow(CardCollection collection) {
		this.collection = collection;
		this.cardTable = new TableView<Card>();
		this.addButton = new Button("Add Card");
		this.detailsButton = new Button("View Details");
		this.editButton = new Button("Edit Card");
		this.removeButton = new Button("Remove Card");

		this.buildWindow();
	}

	private void buildWindow() {
		Label title = new Label("Trading Card Collection");
		title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

		this.buildTable();

		HBox buttonBar = new HBox(SPACING, this.addButton, this.detailsButton, this.editButton, this.removeButton);
		buttonBar.setAlignment(Pos.CENTER);

		this.detailsButton.disableProperty().bind(this.cardTable.getSelectionModel().selectedItemProperty().isNull());
		this.editButton.disableProperty().bind(this.cardTable.getSelectionModel().selectedItemProperty().isNull());
		this.removeButton.disableProperty().bind(this.cardTable.getSelectionModel().selectedItemProperty().isNull());

		this.setPadding(new Insets(PADDING));
		this.setTop(title);
		this.setCenter(this.cardTable);
		this.setBottom(buttonBar);

		BorderPane.setAlignment(title, Pos.CENTER);
		BorderPane.setMargin(title, new Insets(0.0, 0.0, PADDING, 0.0));
		BorderPane.setMargin(buttonBar, new Insets(PADDING, 0.0, 0.0, 0.0));
	}

	private void buildTable() {
		TableColumn<Card, String> nameColumn = new TableColumn<Card, String>("Name");
		nameColumn.setCellValueFactory(card -> new ReadOnlyStringWrapper(card.getValue().getName()));

		TableColumn<Card, String> gameColumn = new TableColumn<Card, String>("Game");
		gameColumn.setCellValueFactory(card -> new ReadOnlyStringWrapper(card.getValue().getGame()));

		TableColumn<Card, String> setColumn = new TableColumn<Card, String>("Set");
		setColumn.setCellValueFactory(card -> new ReadOnlyStringWrapper(card.getValue().getSetName()));

		TableColumn<Card, String> numberColumn = new TableColumn<Card, String>("Card Number");
		numberColumn.setCellValueFactory(card -> new ReadOnlyStringWrapper(card.getValue().getCardNumber()));

		this.cardTable.getColumns().add(nameColumn);
		this.cardTable.getColumns().add(gameColumn);
		this.cardTable.getColumns().add(setColumn);
		this.cardTable.getColumns().add(numberColumn);

		this.cardTable.setPlaceholder(new Label("No cards have been added yet."));
		this.cardTable.getItems().setAll(this.collection.getCards());
	}
}