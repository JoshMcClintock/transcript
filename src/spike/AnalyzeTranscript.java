package spike;

import java.util.ArrayList;

public class AnalyzeTranscript {

    static ArrayList<Transcript> transcripts = ConfigReader.getTranscripts();

    public static String countNumberOfStudentPerYear(int year){
        int count = 0;
        for (int i=0;i<transcripts.size();i++){
            boolean found = false;
            Transcript transcript = transcripts.get(i);
            for (int j = 0; j<transcript.getCourses().size()&&!found; j++){
                if (year == transcript.getCourses().get(j).getSection().getYear()){
                    count++;
                    found = true;
                    //DEBUG LINE
                    System.out.println("FILE: transcript"+ (i+1));
                }
            }
        }
        return year + ": " + count;
    }

    public static String countNumberOfStudentPerYearPerCourse(int year, String courseNum){
        int count = 0;
        for (int i=0;i<transcripts.size();i++){
            boolean found = false;
            Transcript transcript = transcripts.get(i);
            for (int j = 0; j<transcript.getCourses().size()&&!found; j++){
                Course c = transcript.getCourses().get(j);
                if (year == c.getSection().getYear() && courseNum.equalsIgnoreCase(c.getCourseNumber())){
                    count++;
                    found = true;
                    //DEBUG LINE
                    System.out.println("FILE: transcript"+ (i+1));
                }
            }
        }
        return courseNum + " in " + year + ": " + count;
    }

    public static ArrayList<Course> getCoursesPerArea(String area){
        ArrayList<Course> coursesPerArea = new ArrayList<>();
        for (Transcript t : transcripts){
            for (Course c : t.getCourses()){
                for (int i=0;i<ConfigReader.getArea().size();i++){
                    if (area.equalsIgnoreCase(ConfigReader.getArea().get(i).get(0))){
                        for (String courseNum : ConfigReader.getArea().get(i)){
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

}
