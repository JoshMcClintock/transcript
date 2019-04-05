package gui;

import java.io.File;

import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

public class AnalyzerPane extends GridPane {

	private TextField path;
	private Stage stage;
	private File transcriptDirectory;

	public AnalyzerPane(Stage stage) {

		this.stage = stage;

		Font font = new Font(14);

		Label pathLabel = new Label("Set Path: ");
		pathLabel.setFont(font);
		GridPane.setHalignment(pathLabel, HPos.LEFT);

		path = new TextField();
		path.setFont(font);
		path.setPrefWidth(150);
		path.setAlignment(Pos.BASELINE_LEFT);
		path.setText("path/to/transcripts");

		Button browse = new Button("Browse");
		browse.setFont(font);
		browse.setAlignment(Pos.BASELINE_LEFT);
		browse.setOnAction(this::setPath);

		Button analyze = new Button("Analyze Transcripts");
		analyze.setFont(new Font(18));
		analyze.setAlignment(Pos.BASELINE_CENTER);
		analyze.setOnAction(this::analyzeTranscripts);

		setAlignment(Pos.CENTER);
		setHgap(5);
		setVgap(5);

		add(pathLabel, 0, 0);
		add(path, 1, 0);
		add(browse, 2, 0);
		add(analyze, 1, 1);
	}

	public void setPath(ActionEvent event) {
		DirectoryChooser dc = new DirectoryChooser();
		transcriptDirectory = dc.showDialog(stage);

		if (transcriptDirectory != null) {
			path.setText(transcriptDirectory.getAbsolutePath());
		}
	}

	public void analyzeTranscripts(ActionEvent event) {
		
	}

}
