package edu.westga.comp4420.trading_card_collection;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Starts the trading card collection tracker.
 *
 * @author Kevin Bennett
 * @version Fall 2026
 */
public class Main extends Application {
	public static final String WINDOW_TITLE = "Trading Card Collection Tracker";
	public static final double WINDOW_WIDTH = 800;
	public static final double WINDOW_HEIGHT = 600;

	/**
	 * Displays the main application window.
	 *
	 * @param primaryStage the application's primary stage
	 */
	@Override
	public void start(Stage primaryStage) {
		StackPane root = new StackPane(new Label(WINDOW_TITLE));
		Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);

		primaryStage.setTitle(WINDOW_TITLE);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	/**
	 * Starts the JavaFX application.
	 *
	 * @param args command-line arguments
	 */
	public static void main(String[] args) {
		Main.launch(args);
	}
}