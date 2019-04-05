package TranscriptAnalyzer;

import java.io.*;
import java.util.*;

/**
 * this class reads configuration
 */

public class ConfigReader {

    private static BufferedReader br = null;
    private static FileReader fr = null;

    private static ArrayList<ArrayList<String>> equivalencies;
    private static ArrayList<ArrayList<String>> area;
    private static ArrayList<ArrayList<String>> level;
    private static ArrayList<Transcript> transcripts;
    private static Transcript transcript;

    public static Transcript getSingleTranscript(int year, int i) {
        try {
            transcript = new Transcript();
            String fileName = "data/" + year + "/" + year + "EE_" + i + ".txt";
            fr = new FileReader(fileName);
            br = new BufferedReader(fr);

            String sCurrentLine;

            while ((sCurrentLine = br.readLine()) != null) {
                if (!sCurrentLine.isEmpty()) {
                    String str = sCurrentLine.replaceAll("\\s", ",");
                    String[] array = str.split("\\,");
                    ArrayList<String> arrayList = new ArrayList<>();
                    for (String s : array) {
                        if (!s.isEmpty())
                            arrayList.add(s);
                    }
                    // Create objects
                    String courseNum = arrayList.get(0);
                    String sectionId = arrayList.get(1);
                    String courseName = "";
                    for (int j = 2; j < arrayList.size() - 3; j++) {
                        courseName += arrayList.get(j) + " ";
                    }

                    String grade = arrayList.get(arrayList.size() - 3);
                    String ch = arrayList.get(arrayList.size() - 2);
                    String term = arrayList.get(arrayList.size() - 1);

                    Section s = new Section(sectionId, term);
                    Course c = new Course(courseNum, courseName, s, Double.parseDouble(ch), grade);
                    transcript.addCourse(c);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return transcript;
    }

    private static void readTranscript(int year) {
        transcripts = new ArrayList<>();
        int count = countCohorts(year);
        for (int i=1;i<=count;i++){
            transcripts.add(getSingleTranscript(year,i));
        }
    }

    private static void readConfig(String type){
        ArrayList<String> temp;
        String fileName = "config/" + type + ".txt";
        try {
            fr = new FileReader(fileName);
            br = new BufferedReader(fr);

            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
                temp = new ArrayList<>();
                String[] array = sCurrentLine.split("\\s");
                for (String s : array) {
                        temp.add(s);
                }
                if (type.equalsIgnoreCase("equivalencies"))
                    equivalencies.add(temp);
                else if (type.equalsIgnoreCase( "level"))
                    level.add(temp);
                else if (type.equalsIgnoreCase("area"))
                    area.add(temp);
                else System.out.println("unsupported type: " + type);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }

    private static void close(){
        try {
            if (br != null)
                br.close();
            if (fr != null)
                fr.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static ArrayList<ArrayList<String>> getEquivalencies(){
        equivalencies = new ArrayList<>();
        readConfig("equivalencies");
        return equivalencies;
    }

    public static ArrayList<ArrayList<String>> getArea(){
        area = new ArrayList<>();
        readConfig("area");
        return area;
    }

    public static ArrayList<ArrayList<String>> getLevel(){
        level = new ArrayList<>();
        readConfig("level");
        return level;
    }

    public static ArrayList<Transcript> getTranscripts(int year){
        readTranscript(year);
        return transcripts;
    }

    /* number of cohort students in a certain year */
    public static int countCohorts(int year){
        try{
            int count = new File("data/"+year+"//").list().length;
            return count;
        }
        catch (NullPointerException e){
            e.printStackTrace();
        }
        return 0;
    }

    /** THIS IS FOR TESTING THE FUNCTION */
    public static void main(String[] args) {

//        System.out.println(transcripts.get(1).getCourses().get(7).toString());
//        System.out.println(transcripts.get(1).getCourses().get(7).getSection().getYear());
//        System.out.println(transcripts.get(1).getCourses().get(7).getSection().getCampus());
//        System.out.println(transcripts.get(1).getYearOfStudy());
//        System.out.println(transcripts.get(1).getTranscriptID());
//        System.out.println(transcripts.get(1).getCourses().get(7).getSection().getTerm());

//        System.out.println(AnalyzeTranscript.countNumberOfStudentPerYearPerCourse(2011, "STAT2593"));
//        System.out.println(AnalyzeTranscript.getCoursesPerArea(2011,"math"));
//        System.out.println(AnalyzeTranscript.getGradeDistributionPerArea(2011,"math"));
//        System.out.println(OutputWriter.writeMasterList(2011));
//        System.out.println(AnalyzeTranscript.getGradeDistributionPerCourse(2011, "STAT2593"));
//        System.out.println(AnalyzeTranscript.getGradeDistributionPerCohort(2011));
//        System.out.println(AnalyzeTranscript.getGradeDistributionForEveryArea(2011));
        OutputWriter.writeDistributionPerArea(2011);
        OutputWriter.writeDistributionPerCourse(2011);
        OutputWriter.writeMasterList(2011);
        OutputWriter.writeGpaPerAreaPerTranscript(2011,1);
        OutputWriter.writeGpaPerAreaPerTranscript(2015,15);
    }
}