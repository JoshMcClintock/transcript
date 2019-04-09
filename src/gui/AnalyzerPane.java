package gui;

import java.io.File;

import TranscriptAnalyzer.ConfigReader;
import TranscriptAnalyzer.OutputWriter;
import javafx.application.HostServices;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

public class AnalyzerPane extends GridPane {

	private HostServices hs;
	
	private Stage stage;

	private Label lblReadTranscripts;
	
	private TextField intputPath;
	private File transcriptDirectory;

	private TextField outputPath;
	private File outputDirectory;
	
	private CheckBox chkEquivalencies;

	public AnalyzerPane(Stage stage, HostServices hs) {

		this.hs = hs;
		this.stage = stage;

		Font font = new Font(14);

		Label intputPathLabel = new Label("Input Directory: ");
		intputPathLabel.setFont(font);
		GridPane.setHalignment(intputPathLabel, HPos.LEFT);

		intputPath = new TextField();
		intputPath.setFont(font);
		intputPath.setPrefWidth(150);
		intputPath.setAlignment(Pos.BASELINE_LEFT);
		intputPath.setText("path/to/transcripts");

		Button browseInput = new Button("Browse");
		browseInput.setFont(font);
		browseInput.setAlignment(Pos.BASELINE_LEFT);
		browseInput.setOnAction(this::setInputPath);

		Label outputPathLabel = new Label("Output Directory: ");
		intputPathLabel.setFont(font);
		GridPane.setHalignment(outputPathLabel, HPos.LEFT);

		outputPath = new TextField();
		outputPath.setFont(font);
		outputPath.setPrefWidth(150);
		outputPath.setAlignment(Pos.BASELINE_LEFT);
		outputPath.setText("path/to/output");

		Button browseOutput = new Button("Browse");
		browseOutput.setFont(font);
		browseOutput.setAlignment(Pos.BASELINE_LEFT);
		browseOutput.setOnAction(this::setOutputPath);

		lblReadTranscripts = new Label();
		lblReadTranscripts.setFont(new Font(20));
		
		Button btnReadTranscripts = new Button("Read Transcripts");
		btnReadTranscripts.setFont(font);
		btnReadTranscripts.setAlignment(Pos.BASELINE_CENTER);
		btnReadTranscripts.setOnAction(this::readTranscripts);

		Label viewLabel = new Label("Retrieve:");
		viewLabel.setFont(font);

		Label spacer1 = new Label(" ");
		spacer1.setFont(font);

		Label spacer2 = new Label(" ");
		spacer2.setFont(font);
		
		Label spacer3 = new Label(" ");
		spacer3.setFont(new Font(6));

		Button btnViewMasterList = new Button("Master List");
		btnViewMasterList.setFont(font);
		btnViewMasterList.setAlignment(Pos.BASELINE_LEFT);
		btnViewMasterList.setOnAction(this::viewMasterList);

		Button btnViewDistPerArea = new Button("Area Dist.");
		btnViewDistPerArea.setFont(font);
		btnViewDistPerArea.setAlignment(Pos.BASELINE_LEFT);
		btnViewDistPerArea.setOnAction(this::viewDistPerArea);

		Button btnViewDistPerCourse = new Button("Raw Dist.");
		btnViewDistPerCourse.setFont(font);
		btnViewDistPerCourse.setAlignment(Pos.BASELINE_LEFT);
		btnViewDistPerCourse.setOnAction(this::vewDistPerCourse);

		chkEquivalencies = new CheckBox("with Equivalencies");
		
		setAlignment(Pos.CENTER);
		setHgap(5);
		setVgap(5);

		add(intputPathLabel, 0, 0);
		add(intputPath, 0, 1);
		add(browseInput, 1, 1);
		add(outputPathLabel, 2, 0);
		add(outputPath, 2, 1);
		add(browseOutput, 3, 1);
		add(spacer1, 0, 2);
		add(btnReadTranscripts, 0, 3);
		add(lblReadTranscripts, 1, 3);
		add(spacer2, 0, 4);
		add(viewLabel, 0, 5);
		add(chkEquivalencies,1, 5);	
		add(spacer3, 0, 6);
		add(btnViewMasterList, 0, 7);
		add(btnViewDistPerCourse, 1, 7);
		add(btnViewDistPerArea, 2, 7);
	}

	public void setInputPath(ActionEvent event) {
		DirectoryChooser dc = new DirectoryChooser();
		transcriptDirectory = dc.showDialog(stage);

		if (transcriptDirectory != null) {
			intputPath.setText(transcriptDirectory.getAbsolutePath());
		}

		ConfigReader.setTranscriptDirectory(transcriptDirectory);
	}

	public void setOutputPath(ActionEvent event) {
		DirectoryChooser dc = new DirectoryChooser();
		outputDirectory = dc.showDialog(stage);

		if (outputDirectory != null) {
			outputPath.setText(outputDirectory.getAbsolutePath());
		}

		OutputWriter.setOutputDirectory(outputDirectory);
	}

	public void readTranscripts(ActionEvent event) {
		ConfigReader.getTranscripts();
		System.out.println("Transcripts Read");
		
		lblReadTranscripts.setTextFill(Color.GREEN);;
		
		lblReadTranscripts.setText("Success!");
	}

	public void viewMasterList(ActionEvent event) {
		
		if (chkEquivalencies.isSelected()) {
			OutputWriter.writeMasterListWithEq();
			System.out.println("Computed Master List with Equivalencies");
			
			hs.showDocument(outputDirectory + "/Master List With Equivalence");
		} else {
			OutputWriter.writeMasterList();
			System.out.println("Computed Master List");
			
			hs.showDocument(outputDirectory + "/Master List");
		}
	}

	public void viewDistPerArea(ActionEvent event) {
		OutputWriter.writeLevelPerArea();
		System.out.println("Computed Dist per Area");

		hs.showDocument(outputDirectory + "/Average Distribution Per Area");
	}

	public void vewDistPerCourse(ActionEvent event) {
		
		if (chkEquivalencies.isSelected()) {
			OutputWriter.writeDistributionPerCourseWithEq();
			System.out.println("Computed Dist per Course with Equivalencies");
			
			hs.showDocument(outputDirectory + "/Distribution Per Course With Equivalence");
		} else {
			OutputWriter.writeDistributionPerCourse();
			System.out.println("Computed Dist per Course");
		
			hs.showDocument(outputDirectory + "/Raw Distribution Per Course");
		}
	}
}
