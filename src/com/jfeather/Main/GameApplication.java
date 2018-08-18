package com.jfeather.Main;
/*
 * This probably won't be used, because the game will be run in the command line, as opposed to making our own window
 */
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GameApplication extends Application {

	public static final String TITLE = "Title Goes Here";
	public static final int WIDTH = 620;
	public static final int HEIGHT = 380;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	private Stage stage;
	private TextArea textArea;
	private TextField inputBox;
	
	@Override
	public void start(Stage newStage) throws Exception {
		stage = newStage;
		stage.setTitle(TITLE);
		
		
		VBox vBox = new VBox();
		vBox.setSpacing(0);
		vBox.setPadding(new Insets(0));
		
		textArea = new TextArea();
		textArea.setPrefSize(WIDTH, HEIGHT);
		textArea.setEditable(false);
		textArea.setWrapText(true);
		textArea.setStyle("-fx-focus-color: transparent;"
				+ "-fx-faint-focus-color: transparent;"
				+ "-fx-border-color: transparent;"
				+ "-fx-border-width: 0px;");
		
		// So that the text area can scroll up and down
		ScrollPane sPane = new ScrollPane();
		sPane.setContent(textArea);
		sPane.setHbarPolicy(ScrollBarPolicy.NEVER);
		sPane.setVbarPolicy(ScrollBarPolicy.NEVER);
		
		inputBox = new TextField();
		inputBox.setPrefSize(WIDTH, 35);
		inputBox.setStyle("-fx-focus-color: transparent;"
				+ "-fx-faint-focus-color: transparent;"
				+ "-fx-border-color: transparent;"
				+ "-fx-border-width: 0px;");
		
		inputBox.setOnAction(e -> {
			if (inputBox.getText().length() > 0) {
				textArea.setText(textArea.getText() + "\n[Player] " + inputBox.getText());
				inputBox.setText("");
			}
		});
		
		vBox.getChildren().addAll(sPane, inputBox);
		vBox.requestFocus();
		Scene mainScene = new Scene(vBox, WIDTH, HEIGHT);
		stage.setScene(mainScene);
		stage.show();
	}

}
