package spike;

import java.util.ArrayList;

public class AnalyzeTranscript {

    static ArrayList<Transcript> transcripts = ConfigReader.getTranscripts();
    //count number of student per year
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

}
