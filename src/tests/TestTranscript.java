package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import spike.Course;
import spike.Section;
import spike.Transcript;

class TestTranscript {

	private static Transcript t;
	private static int tId;

	@BeforeEach
	void setUp() throws Exception {
		tId++;
		t = new Transcript();
	}

	Course setUpCourse() {
		String courseNum = "CS1303";
		String courseName = "Discrete Structures";
		Section section = new Section("FR01A", "2018/FA");
		Double creditHours = 4.0;
		String grade = "B+";
		Course c = new Course(courseNum, courseName, section, creditHours, grade);

		return c;
	}

	@Test
	void testAddCourse() {
		Course c = setUpCourse();
		t.addCourse(c);

		assertTrue(t.getCourses().contains(c));
	}

	@Test
	void testGetTranscriptID() {
		assertEquals(tId, t.getTranscriptID());
	}

	@Test
	void testGetYearOfStudyFirstYear() {
		Course c = setUpCourse();
		c.setCreditHours(16);
		t.addCourse(c);

		int year = 1;

		assertEquals(year, t.getYearOfStudy());
	}

	@Test
	void testGetYearOfStudySecondYear() {
		Course c = setUpCourse();
		c.setCreditHours(46);
		t.addCourse(c);

		int year = 2;

		assertEquals(year, t.getYearOfStudy());
	}

	@Test
	void testGetYearOfStudyThirdYear() {
		Course c = setUpCourse();
		c.setCreditHours(74);
		t.addCourse(c);

		int year = 3;

		assertEquals(year, t.getYearOfStudy());
	}

	@Test
	void testGetYearOfStudyFourthYear() {
		Course c = setUpCourse();
		c.setCreditHours(99);
		t.addCourse(c);

		int year = 4;

		assertEquals(year, t.getYearOfStudy());
	}

}
