package gui;

import javafx.application.Application;
import javafx.application.HostServices;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TranscriptAnalyzer extends Application {

	public void start(Stage primaryStage) {

		HostServices hs = getHostServices();
		
		primaryStage.setTitle("Transcript Analyzer");

		Scene scene = new Scene(new AnalyzerPane(primaryStage, hs), 600, 300);

		primaryStage.setScene(scene);
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}
}