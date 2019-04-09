package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import TranscriptAnalyzer.Section;

class TestSection {

	private static Section s;

	@BeforeAll
	static void setUp() throws Exception {
		String sectionId = "FR01B";
		String term = "2019/WI";
		s = new Section(sectionId, term);
	}

	@Test
	void testGetYear() {
		int year = 2019;

		assertEquals(year, s.getYear());
	}

	@Test
	void testSetSectionId() {
		String sId = "SJ02A";
		s.setSectionId(sId);

		assertEquals(sId, s.getSectionId());
	}

	@Test
	void testGetCampusFredericton() {
		String campus = "Fredericton";

		assertEquals(campus, s.getCampus());
	}

	@Test
	void testGetCampusSaintJohn() {
		s.setSectionId("SJ01A");
		String campus = "Saint John";

		assertEquals(campus, s.getCampus());
	}

	void testGetCampusUnsupported() {
		s.setSectionId("DI02C");
		String campus = "unsupported campus";

		assertEquals(campus, s.getCampus());
	}
}
