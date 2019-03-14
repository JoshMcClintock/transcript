package spike;

public class CourseResult {

	private static int resultID;
	private Course course;
	private String grade;

	public CourseResult(Course course, String grade) {
		this.course = course;
		this.grade = grade;
		resultID++;
	}
	
	public int getResultID() {
		return resultID;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}
	
	public String toString() {
		String str = "";
		str += course.getCourseNumber() + "\t" + course.getSection().getSectionId() + "\t" + grade + "\t" + course.getCreditHours() + "\t" + course.getSection().getTerm();
		return str;
	}
}
