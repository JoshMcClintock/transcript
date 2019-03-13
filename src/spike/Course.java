package src.spike;

public class Course {

	private String courseNumber;
	private Section section;
	private double creditHours;

	public Course(String courseNum, Section section, double creditHours){
		this.courseNumber = courseNum;
		this.section = section;
		this.creditHours = creditHours;
	}

	public String getCourseNumber() {
		return courseNumber;
	}

	public void setCourseNumber(String courseNumber) {
		this.courseNumber = courseNumber;
	}

	public Section getSection() {
		return section;
	}

	public void setSection(Section sections) {
		this.section = sections;
	}

	public double getCreditHours() {
		return creditHours;
	}

	public void setCreditHours(double creditHours) {
		this.creditHours = creditHours;
	}

}
