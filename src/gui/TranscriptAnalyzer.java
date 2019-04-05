package gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TranscriptAnalyzer extends Application {

	public void start(Stage primaryStage) {

		primaryStage.setTitle("Transcript Analyzer");

		Scene scene = new Scene(new AnalyzerPane(primaryStage), 600, 300);

		primaryStage.setScene(scene);
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}
}