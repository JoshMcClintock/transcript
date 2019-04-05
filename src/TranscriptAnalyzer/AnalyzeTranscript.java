package TranscriptAnalyzer;

import java.util.ArrayList;

public class AnalyzeTranscript {

	public static int countNumberOfStudentPerYearPerCourse(String courseNum) {
		ArrayList<Transcript> transcripts = ConfigReader.getTranscripts();
		int count = 0;
		for (Transcript t : transcripts) {
			boolean found = false;
			for (int j = 0; j < t.getCourses().size() && !found; j++) {
				Course c = t.getCourses().get(j);
				if (courseNum.equalsIgnoreCase(c.getCourseNumber())) {
					count++;
					found = true;
				}
			}
		}
		return count;
	}

	public static ArrayList<Course> getCoursesPerArea(String area) {
		ArrayList<Course> coursesPerArea = new ArrayList<>();
		ArrayList<Transcript> transcripts = ConfigReader.getTranscripts();
		for (Transcript transcript : transcripts) {
			for (int i = 0; i < ConfigReader.getArea().size(); i++) {
				if (area.equalsIgnoreCase(ConfigReader.getArea().get(i).get(0))) {
					for (String courseNum : ConfigReader.getArea().get(i)) {
						for (Course c : transcript.getCourses()) {
							if (c.getCourseNumber().equalsIgnoreCase(courseNum)) {
								coursesPerArea.add(c);
							}
						}
					}
				}
			}
		}
		return coursesPerArea;
	}

	public static ArrayList<Course> getCoursePerAreaPerTranscript(String area, int index) {
		ArrayList<Course> coursesPerAreaPerTranscript = new ArrayList<>();
		if (ConfigReader.getTranscripts().size() > 0) {
			for (Transcript transcript : ConfigReader.getTranscripts()) {
				if (transcript.getTranscriptID() == index) {
					for (int i = 0; i < ConfigReader.getArea().size(); i++) {
						if (area.equalsIgnoreCase(ConfigReader.getArea().get(i).get(0))) {
							for (String courseNum : ConfigReader.getArea().get(i)) {
								for (Course c : transcript.getCourses()) {
									if (c.getCourseNumber().equalsIgnoreCase(courseNum)) {
										coursesPerAreaPerTranscript.add(c);
									}
								}
							}
						}
					}
				}

			}
		}

		return coursesPerAreaPerTranscript;
	}

	public static ArrayList<Integer> getGradeDistributionPerArea(String area) {
		ArrayList<Course> coursesPerArea = getCoursesPerArea(area);
		ArrayList<Integer> gradeDistribution = new ArrayList<>();

		int exceeds = 0;
		int meets = 0;
		int marginal = 0;
		int fails = 0;
		int others;

		for (int i = 0; i < ConfigReader.getLevel().size(); i++) {
			for (Course c : coursesPerArea) {
				for (int j = 1; j < ConfigReader.getLevel().get(i).size(); j++) {
					if (c.getGrade().equalsIgnoreCase(ConfigReader.getLevel().get(i).get(j))) {
						switch (i) {
						case 0:
							exceeds++;
							break;
						case 1:
							meets++;
							break;
						case 2:
							marginal++;
							break;
						case 3:
							fails++;
							break;
						}
					}
				}
			}
		}
		others = coursesPerArea.size() - exceeds - meets - marginal - fails;

		int[] gradeDistributionPerArea = { exceeds, meets, marginal, fails, others };

		for (int i : gradeDistributionPerArea) {
			gradeDistribution.add(i);
		}
		return gradeDistribution;
	}

	public static ArrayList<ArrayList<Integer>> getGradeDistributionForEveryArea() {
		ArrayList<ArrayList<Integer>> gradeDistributionForEveryArea = new ArrayList<>();
		for (int i = 0; i < ConfigReader.getArea().size(); i++) {
			gradeDistributionForEveryArea.add(getGradeDistributionPerArea(ConfigReader.getArea().get(i).get(0)));
		}
		return gradeDistributionForEveryArea;
	}

	public static ArrayList<Integer> getGradeDistributionPerCourse(String courseNum) {
		int exceeds = 0;
		int meets = 0;
		int marginal = 0;
		int fails = 0;
		int others = 0;

		ArrayList<Integer> gradeDistribution = new ArrayList<>();
		ArrayList<Transcript> transcripts = ConfigReader.getTranscripts();
		for (Transcript t : transcripts) {
			for (Course course : t.getCourses()) {
				if (course.getCourseNumber().equals(courseNum)) {
					others++;
					for (int i = 0; i < ConfigReader.getLevel().size(); i++) {
						for (int j = 1; j < ConfigReader.getLevel().get(i).size(); j++) {
							if (course.getGrade().equalsIgnoreCase(ConfigReader.getLevel().get(i).get(j))) {
								switch (i) {
								case 0:
									exceeds++;
									break;
								case 1:
									meets++;
									break;
								case 2:
									marginal++;
									break;
								case 3:
									fails++;
									break;
								}
							}
						}
					}
				}
			}
		}
		others = others - exceeds - meets - marginal - fails;

		int[] gradeDistributionPerArea = { exceeds, meets, marginal, fails, others };

		for (int i : gradeDistributionPerArea) {
			gradeDistribution.add(i);
		}
		return gradeDistribution;
	}

	public static double gpaPerAreaPerTranscript(String area, int index) {
		ArrayList<Course> coursePerAreaPerTranscript = getCoursePerAreaPerTranscript(area, index);
		double gpa = 0;
		double total = 0;
		double totalCH = 0;
		for (Course course : coursePerAreaPerTranscript) {
			String letter = course.getGrade();
			double point = 0;
			double ch = course.getCreditHours();
			switch (letter) {
			case "A+":
				point = 4.3;
				break;
			case "A":
				point = 4.0;
				break;
			case "A-":
				point = 3.7;
				break;
			case "B+":
				point = 3.3;
				break;
			case "B":
				point = 3.0;
				break;
			case "B-":
				point = 2.7;
				break;
			case "C+":
				point = 2.3;
				break;
			case "C":
				point = 2.0;
				break;
			case "D":
				point = 1.0;
				break;
			case "F":
				point = 0.0;
				break;
			default:
				break;
			}
			total += point * ch;
			totalCH += ch;
			gpa = total / totalCH;
		}
		return gpa;
	}

	public static ArrayList<Double> getAverageGradePerTranscriptForEachArea(int index) {
		ArrayList<Double> averageGradePerTranscript = new ArrayList<>();
		for (ArrayList<String> arrayList : ConfigReader.getArea()) {
			String area = arrayList.get(0);
			averageGradePerTranscript.add(gpaPerAreaPerTranscript(area, index));
		}
		return averageGradePerTranscript;
	}

	public static ArrayList<String> createMasterList() {
		ArrayList<String> masterList = new ArrayList<>();
		ArrayList<Transcript> transcripts = ConfigReader.getTranscripts();
		for (Transcript transcript : transcripts) {
			for (Course course : transcript.getCourses()) {
				String courseNum = course.getCourseNumber();
				if (!masterList.contains(courseNum) && !courseNum.isEmpty()) {
					masterList.add(courseNum);
				}
			}
		}
		return masterList;
	}

	public static ArrayList<ArrayList<Integer>> getGradeDistributionPerCohort() {
		ArrayList<ArrayList<Integer>> gradeDistributionPerCohort = new ArrayList<>();
		for (String courseNum : createMasterList()) {
			gradeDistributionPerCohort.add(getGradeDistributionPerCourse(courseNum));
		}
		return gradeDistributionPerCohort;
	}

//    public static ArrayList<String> getMasterListWithEquilvance(int year) {
//        for (String courseNum : createMasterList(year)){
//            for (int i=0;i< ConfigReader.getEquivalencies().size();i++){
//                if (courseNum.equalsIgnoreCase(ConfigReader.getEquivalencies().get(i).get(0))){
//
//                }
//            }
//        }
//    }
}