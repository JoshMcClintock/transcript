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

		Label pathLabel = new Label("Choose Directory: ");
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

		Button btnReadTranscripts = new Button("Read Transcripts");
		btnReadTranscripts.setFont(new Font(18));
		btnReadTranscripts.setAlignment(Pos.BASELINE_CENTER);
		btnReadTranscripts.setOnAction(this::readTranscripts);
		
		Label viewLabel = new Label("View:");
		viewLabel.setFont(font);
		
		Label spacer = new Label("");
		spacer.setFont(font);
		
		Button btnViewDistPerArea = new Button("Dist/Area");
		btnViewDistPerArea.setFont(font);
		btnViewDistPerArea.setAlignment(Pos.BASELINE_LEFT);
		btnViewDistPerArea.setOnAction(this::setPath);
		
		Button btnViewDistPerCourse = new Button("Dist/Course");
		btnViewDistPerCourse.setFont(font);
		btnViewDistPerCourse.setAlignment(Pos.BASELINE_LEFT);
		btnViewDistPerCourse.setOnAction(this::setPath);

		Button btnViewMasterList = new Button("Master List");
		btnViewMasterList.setFont(font);
		btnViewMasterList.setAlignment(Pos.BASELINE_LEFT);
		btnViewMasterList.setOnAction(this::setPath);
		
		Button btnViewGPA = new Button("GPA");
		btnViewGPA.setFont(font);
		btnViewGPA.setAlignment(Pos.BASELINE_LEFT);
		btnViewGPA.setOnAction(this::setPath);
		
		setAlignment(Pos.CENTER);
		setHgap(5);
		setVgap(5);

		add(pathLabel, 0, 0);
		add(path, 0, 1);
		add(browse, 1, 1);
		add(btnReadTranscripts, 0, 2);
		add(spacer, 0, 3);
		add(viewLabel, 0, 5);
		add(btnViewMasterList, 0, 6);
		add(btnViewGPA, 1, 6);
		add(btnViewDistPerArea, 0, 7);
		add(btnViewDistPerCourse, 1, 7);
		
	}

	public void setPath(ActionEvent event) {
		DirectoryChooser dc = new DirectoryChooser();
		transcriptDirectory = dc.showDialog(stage);

		if (transcriptDirectory != null) {
			path.setText(transcriptDirectory.getAbsolutePath());
		}
	}

	public void readTranscripts(ActionEvent event) {
		File[] files = transcriptDirectory.listFiles();
		for (File file : files) {
			System.out.println(file.getAbsolutePath());
		}
	}

}
