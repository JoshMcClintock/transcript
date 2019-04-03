package TranscriptAnalyzer;

import java.util.ArrayList;

public class AnalyzeTranscript {

    public static String countNumberOfStudentPerYearPerCourse(int year, String courseNum){
        ArrayList<Transcript> transcripts = ConfigReader.getTranscripts(year);
        int count = 0;
        for (Transcript t : transcripts){
            boolean found = false;
            for (int j = 0; j<t.getCourses().size()&&!found; j++){
                Course c = t.getCourses().get(j);
                if (year == c.getSection().getYear() && courseNum.equalsIgnoreCase(c.getCourseNumber())){
                    count++;
                    found = true;
                }
            }
        }
        return courseNum + " in " + year + ": " + count;
    }

    public static ArrayList<Course> getCoursesPerArea(int year, String area){
        ArrayList<Course> coursesPerArea = new ArrayList<>();
        ArrayList<Transcript> transcripts = ConfigReader.getTranscripts(year);
        for (Transcript transcript : transcripts){
            for (int i=0;i<ConfigReader.getArea().size();i++){
                if (area.equalsIgnoreCase(ConfigReader.getArea().get(i).get(0))){
                    for (String courseNum : ConfigReader.getArea().get(i)){
                        for (Course c : transcript.getCourses()){
                            if (c.getCourseNumber().equalsIgnoreCase(courseNum)){
                                coursesPerArea.add(c);
                            }
                        }
                    }
                }
            }
        }
        return coursesPerArea;
    }

    public static ArrayList<Course> getCoursePerAreaPerTranscript(int year, String area, int index){
        ArrayList<Course> coursesPerAreaPerTrancript = new ArrayList<>();
        Transcript transcript = ConfigReader.getSingleTranscript(year, index);
        for (int i=0;i<ConfigReader.getArea().size();i++){
            if (area.equalsIgnoreCase(ConfigReader.getArea().get(i).get(0))){
                for (String courseNum : ConfigReader.getArea().get(i)){
                    for (Course c : transcript.getCourses()){
                        if (c.getCourseNumber().equalsIgnoreCase(courseNum)){
                            coursesPerAreaPerTrancript.add(c);
                        }
                    }
                }
            }
        }
        return coursesPerAreaPerTrancript;
    }

    public static ArrayList<Integer> getGradeDistributionPerArea(int year, String area){
        ArrayList<Course> coursesPerArea = getCoursesPerArea(year, area);
        ArrayList<Integer> gradeDistribution = new ArrayList<>();

        int exceeds = 0;
        int meets = 0;
        int marginal = 0;
        int fails = 0;
        int others;

        for (int i=0;i<ConfigReader.getLevel().size();i++){
            for (Course c : coursesPerArea){
                for (int j=1;j<ConfigReader.getLevel().get(i).size();j++) {
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
        others = coursesPerArea.size()-exceeds-meets-marginal-fails;

        int[] gradeDistributionPerArea = {exceeds,meets,marginal,fails,others};

        for (int i:gradeDistributionPerArea){
            gradeDistribution.add(i);
        }
        return gradeDistribution;
    }

    public static ArrayList<ArrayList<Integer>> getGradeDistributionForEveryArea(int year){
        ArrayList<ArrayList<Integer>> gradeDistributionForEveryArea = new ArrayList<>();
        for (int i=0;i<ConfigReader.getArea().size();i++){
            gradeDistributionForEveryArea.add(getGradeDistributionPerArea(year,ConfigReader.getArea().get(i).get(0)));
        }
        return gradeDistributionForEveryArea;
    }

    public static ArrayList<Integer> getGradeDistributionPerCourse(int year, String courseNum) {
        int exceeds = 0;
        int meets = 0;
        int marginal = 0;
        int fails = 0;
        int others = 0;

        ArrayList<Integer> gradeDistribution = new ArrayList<>();
        ArrayList<Transcript> transcripts = ConfigReader.getTranscripts(year);
        for (Transcript t : transcripts) {
            for (Course course : t.getCourses()) {
                if (course.getCourseNumber().equals(courseNum)) {
                    others ++;
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
        others = others-exceeds-meets-marginal-fails;

        int[] gradeDistributionPerArea = {exceeds,meets,marginal,fails,others};

        for (int i:gradeDistributionPerArea){
            gradeDistribution.add(i);
        }
        return gradeDistribution;
    }

    public static double gpaPerAreaPerTranscript(int year,String area,int index){
        ArrayList<Course> courses = getCoursePerAreaPerTranscript(year,area,index);
        double gpa = 0;
        for (Course course : courses){
            double total = 0;
            double totalCH = 0;
            String letter = course.getGrade();
            double point = 0;
            double ch = course.getCreditHours();
            switch(letter){
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
            total += point*ch;
            totalCH += ch;
            gpa = total/totalCH;
        }
        return gpa;
    }

    public static ArrayList<Double> getAverageGradePerTranscriptForEachArea(int year, int index){
        ArrayList<Double> averageGradePerTranscript = new ArrayList<>();
        for (ArrayList<String> arrayList : ConfigReader.getArea()) {
            String area = arrayList.get(0);
            averageGradePerTranscript.add(gpaPerAreaPerTranscript(year, area, index));
        }
        return averageGradePerTranscript;
    }

    public static ArrayList<String> createMasterList(int year) {
        ArrayList<String>  masterList = new ArrayList<>();
        ArrayList<Transcript> transcripts = ConfigReader.getTranscripts(year);
        for (Transcript transcript : transcripts) {
            for (Course course : transcript.getCourses()) {
                String courseNum = course.getCourseNumber();
                if (! masterList.contains(courseNum) && !courseNum.isEmpty()) {
                     masterList.add(courseNum);
                }
            }
        }
        return  masterList;
    }

    public static ArrayList<ArrayList<Integer>> getGradeDistributionPerCohort(int year){
        ArrayList<ArrayList<Integer>> gradeDistributionPerCohort = new ArrayList<>();
        for (String courseNum : createMasterList(year)){
            gradeDistributionPerCohort.add(getGradeDistributionPerCourse(year,courseNum));
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