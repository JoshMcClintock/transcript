package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import TranscriptAnalyzer.Course;
import TranscriptAnalyzer.Section;

class TestCourse {

	private static Course c;

	@BeforeAll
	static void setUp() throws Exception {
		String courseNum = "CS2043";
		String courseName = "Software Engineering I";
		Section section = new Section("FR01B", "2019/WI");
		Double creditHours = 4.0;
		String grade = "A";
		c = new Course(courseNum, courseName, section, creditHours, grade);
	}

	@Test
	void testGetCourseName() {
		String courseName = "Software Engineering I";

		assertEquals(courseName, c.getCourseName());
	}

	@Test
	void testSetCourseNumber() {
		String courseNumber = "CS1083";
		c.setCourseNumber(courseNumber);

		assertEquals(courseNumber, c.getCourseNumber());
	}

	@Test
	void testSetSection() {
		String sectionId = "SJ02A";
		String term = "2018/FA";
		Section section = new Section(sectionId, term);
		c.setSection(section);

		assertEquals(sectionId, c.getSection().getSectionId());
		assertEquals(term, c.getSection().getTerm());
	}

	@Test
	void testSetCreditHours() {
		double creditHours = 6.0;
		c.setCreditHours(creditHours);

		assertEquals(creditHours, c.getCreditHours());
	}

	@Test
	void testGetGrade() {
		String grade = "A";

		assertEquals(grade, c.getGrade());
	}
}
