package TranscriptAnalyzer;

import java.util.ArrayList;

public class AnalyzeTranscript {

    static ArrayList<Transcript> transcripts = ConfigReader.getTranscripts();
    static ArrayList<ArrayList<String>> levels = ConfigReader.getLevel();
    static ArrayList<ArrayList<String>> areas = ConfigReader.getArea();

    public static ArrayList<Course> getCoursesPerArea(String area){
        ArrayList<Course> coursesPerArea = new ArrayList<>();
        ArrayList<Transcript> transcripts = ConfigReader.getTranscripts();
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

    public static ArrayList<Course> getCoursePerAreaPerTranscript(String area, int index){
        ArrayList<Course> coursesPerAreaPerTranscript = new ArrayList<>();
        ArrayList<Transcript> transcripts = ConfigReader.getTranscripts();
        if (transcripts.size()>0) {
            Transcript transcript = ConfigReader.getTranscripts().get(index);
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
        return coursesPerAreaPerTranscript;
    }

    public static ArrayList<Integer> getGradeDistributionPerArea(String area){
        ArrayList<Course> coursesPerArea = getCoursesPerArea(area);
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

    public static ArrayList<ArrayList<Integer>> getGradeDistributionForEveryArea(){
        ArrayList<ArrayList<Integer>> gradeDistributionForEveryArea = new ArrayList<>();
        for (int i=0;i<ConfigReader.getArea().size();i++){
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
        for (Transcript t : transcripts) {
            for (Course course : t.getCourses()) {
                if (course.getCourseNumber().equals(courseNum)) {
                    others ++;
                    for (int i = 0; i < levels.size(); i++) {
                        for (int j = 1; j < levels.get(i).size(); j++) {
                            if (course.getGrade().equalsIgnoreCase(levels.get(i).get(j))) {
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

    public static String gpaPerAreaPerTranscript(String area,int index){
        ArrayList<Course> coursePerAreaPerTranscript = getCoursePerAreaPerTranscript(area,index);
        double gpa = 0;
        double total = 0;
        double totalCH = 0;
        for (Course course : coursePerAreaPerTranscript){
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
        String letter = convertToLetter(gpa);
        return letter;
    }

    public static ArrayList<String> getAverageGradePerTranscriptForEachArea(int index){
        ArrayList<String> averageGradePerTranscript = new ArrayList<>();
        for (ArrayList<String> arrayList : areas) {
            String area = arrayList.get(0);
            averageGradePerTranscript.add(gpaPerAreaPerTranscript(area, index-1));
        }
        return averageGradePerTranscript;
    }

    public static ArrayList<Integer> getAverageArea(String area){

        int exceeds = 0;
        int meets = 0;
        int marginal = 0;
        int fails = 0;
        int others = 0;

        for (int index = 0;index<transcripts.size();index++){
            String gpa = gpaPerAreaPerTranscript(area,index);
            for (int i = 0; i < levels.size(); i++) {
                for (int j = 1; j < levels.get(i).size(); j++) {
                    if (gpa.equalsIgnoreCase(levels.get(i).get(j))) {
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
                            default:
                                others++;
                                break;
                        }
                    }
                }
            }
        }

        int[] gradeDistributionPerArea = {exceeds,meets,marginal,fails,others};
        ArrayList<Integer> gradeDistributionPerAreaList = new ArrayList<>();
        for (int i:gradeDistributionPerArea){
            gradeDistributionPerAreaList.add(i);
        }
        return gradeDistributionPerAreaList;
    }

    public static ArrayList<String> createMasterList() {
        ArrayList<String>  masterList = new ArrayList<>();
        for (Transcript transcript : transcripts) {
            for (Course course : transcript.getCourses()) {
                String courseNum = course.getCourseNumber();
                if ((!masterList.contains(courseNum)) && (!courseNum.isEmpty())) {
                     masterList.add(courseNum);
                }
            }
        }
        return  masterList;
    }

    public static ArrayList<ArrayList<Integer>> getGradeDistributionPerCohort(boolean isEq){
        ArrayList<ArrayList<Integer>> gradeDistributionPerCohort = new ArrayList<>();
        ArrayList<String> masterlist;
        if (isEq){
            masterlist = getMasterListWithEquivalence();
        }
        else{
            masterlist = createMasterList();
        }
        for (String courseNum : masterlist){
            gradeDistributionPerCohort.add(getGradeDistributionPerCourse(courseNum));
        }
        return gradeDistributionPerCohort;
    }

    private static String convertToLetter(double gpa){
        String output;
        if (gpa < 1){
            output = "F";
        }
        else if (gpa < 2){
            output = "D";
        }
        else if (gpa < 2.3){
            output = "C";
        }
        else if (gpa < 2.7){
            output = "C+";
        }
        else if (gpa < 3.0){
            output = "B-";
        }
        else if (gpa < 3.3){
            output = "B";
        }
        else if (gpa < 3.7){
            output = "B+";
        }
        else if (gpa < 4.0){
            output = "A-";
        }
        else if (gpa < 4.3){
            output = "A";
        }
        else if (gpa == 4.3){
            output = "A+";
        }
        else {
            output = "not supported";
        }
        return output;
    }

    public static ArrayList<String> getMasterListWithEquivalence() {
        ArrayList<String> masterList = AnalyzeTranscript.createMasterList();
        ArrayList<String> removeList = new ArrayList<>();
        ArrayList<ArrayList<String>> eq = ConfigReader.getEquivalencies();
        if (eq.size()>0){
            for (int i=0;i< eq.size();i++){
                int count = 0;
                for (String course : eq.get(i)){
                    for (String list : masterList){
                        if (course.equalsIgnoreCase(list)){
                            count++;
                            if (count > 1) {
                                count--;
                                removeList.add(list);
                            }
                        }
                    }
                }
            }
            masterList.removeAll(removeList);
        }
        return masterList;
    }

//    public static ArrayList<String> getDistributionWithoutRepeatedCourse(){
//
//    }
}