package spike;

import java.util.ArrayList;

public class Transcript {

	private static int transcriptID = 0; //actually student id
	private ArrayList<Course> courseResults = new ArrayList<>();

	public Transcript() {
		transcriptID++;
	}

	public void addCourse(Course c) {
		courseResults.add(c);
	}

	public int getTranscriptID() {
		return transcriptID;
	}

	public int getYearOfStudy(){
		double totalCH = 0;
		for (Course c : courseResults){
			totalCH += c.getCreditHours();
		}
		if (totalCH <= 24){
			return 1;
		}
		else if (totalCH <= 53){
			return 2;
		}
		else if (totalCH <= 83){
			return 3;
		}
		else return 4;
	}

	public ArrayList<Course> getCourseResults(){
		return courseResults;
	}

	@Override
	public String toString() {
		String str = "";
		for (Course c : courseResults) {
			str += c + "\n";
		}
		return str;
	}
}
