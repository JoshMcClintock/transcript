package src.spike;

import java.util.ArrayList;

public class Transcript {

	private static int transcriptID = 0;
	private ArrayList<CourseResult> courseResults = new ArrayList<CourseResult>();

	public Transcript() {
		transcriptID++;
	}

	public void addCourseResult(CourseResult cs) {
		courseResults.add(cs);
	}

	public int getTranscriptID() {
		return transcriptID;
	}

	@Override
	public String toString() {
		String str = "";
		for (CourseResult cr : courseResults) {
			str += cr + "\n";
		}
		return str;
	}
}
