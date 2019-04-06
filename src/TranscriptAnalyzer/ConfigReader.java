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

    private static File transcriptDirectory;

    public static void setTranscriptDirectory(File file){
        transcriptDirectory = file;
    }

    public static ArrayList<Transcript> getTranscripts() {
        transcripts = new ArrayList<>();
        try {
            transcriptDirectory = new File("data/2015"); // Default directory

            File[] transcriptFiles = transcriptDirectory.listFiles();

            if (transcriptFiles != null){

                for (File file : transcriptFiles) {

                    transcript = new Transcript();
                    fr = new FileReader(file.getAbsolutePath());
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

                            String grade;
                            String ch;
                            String term;

                            int index = arrayList.size();

                            grade = arrayList.get(index-3);
                            ch = arrayList.get(index-2);
                            term = arrayList.get(index-1);

                            if (term.charAt(0)!='2'){
                                index--;
                                grade = arrayList.get(index-3);
                                ch = arrayList.get(index-2);
                                term = arrayList.get(index-1);
                            }
                            if (ch.equalsIgnoreCase("#")){
                                ch = arrayList.get(index-3);
                                grade = arrayList.get(index - 4);
                                courseName = "";
                                for (int j = 2; j < index - 4; j++) {
                                    courseName += arrayList.get(j) + " ";
                                }
                            }
                            if (grade.equalsIgnoreCase("term")){
                                grade = " ";
                                for (int j = 2; j < index - 2; j++) {
                                    courseName += arrayList.get(j) + " ";
                                }
                            }
                            Section s = new Section(sectionId, term);
                            Course c = new Course(courseNum, courseName, s, Double.parseDouble(ch), grade);
                            transcript.addCourse(c);
                        }
                    }
                    transcripts.add(transcript);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return transcripts;
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

    /** THIS IS FOR TESTING THE FUNCTION */
    public static void main(String[] args) {

//        System.out.println(AnalyzeTranscript.countNumberOfStudentPerYearPerCourse( "STAT2593"));
//        System.out.println(AnalyzeTranscript.getCoursesPerArea("math"));
//        System.out.println(AnalyzeTranscript.getGradeDistributionPerArea("math"));;
//        System.out.println(AnalyzeTranscript.getGradeDistributionPerCourse( "STAT2593"));
//        System.out.println(AnalyzeTranscript.getGradeDistributionPerCohort());
//        System.out.println(AnalyzeTranscript.getGradeDistributionForEveryArea());
        OutputWriter.writeLevelPerArea();
        OutputWriter.writeDistributionPerCourseWithEq();
        OutputWriter.writeMasterListWithEq();
        OutputWriter.writeDistributionPerArea();
        OutputWriter.writeDistributionPerCourse();
        OutputWriter.writeMasterList();
    }
}