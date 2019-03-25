package spike;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

/**
 * this class reads configuration
 */

public class ConfigReader {

    static BufferedReader br = null;
    static FileReader fr = null;
    static BufferedWriter bw = null;
    static FileWriter fw = null;

    static ArrayList<ArrayList<String>> equivalencies = new ArrayList<>();
    static ArrayList<ArrayList<String>> area = new ArrayList<>();
    static ArrayList<ArrayList<String>> level = new ArrayList<>();
    static ArrayList<Transcript> transcripts = new ArrayList<>();

    public static void readTranscript(int i){
        try {
            Transcript transcript = new Transcript();

            String fileName = "data/transcript"+i+".txt";
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
                    String grade = arrayList.get(arrayList.size() - 3);
                    String ch = arrayList.get(arrayList.size() - 2);
                    String term = arrayList.get(arrayList.size() - 1);

                    Section s = new Section(sectionId, term);
                    Course c = new Course(courseNum, s, Double.parseDouble(ch), grade);
                    transcript.addCourse(c);
                }
            }

            fileName = "output/result"+i+".txt";
            fw = new FileWriter(fileName);
            bw = new BufferedWriter(fw);
            //print the transcript to see if any bug here
            bw.write(transcript.toString()); //print something to file now
            transcripts.add(transcript);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null)
                    br.close();
                if (fr != null)
                    fr.close();
                if (bw != null)
                    bw.close();
                if (fw != null)
                    fw.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }


    public static void readConfig(String type){
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
            try {
                if (br != null)
                    br.close();
                if (fr != null)
                    fr.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static ArrayList<ArrayList<String>> getEquivalencies(){
        return equivalencies;
    }

    public static ArrayList<ArrayList<String>> getArea(){
        return area;
    }

    public static ArrayList<ArrayList<String>> getLevel(){
        return level;
    }

    public static ArrayList<Transcript> getTranscripts(){
        return transcripts;
    }

    /** THIS IS FOR TESTING THE FUNCTION */
    public static void main(String[] args) {

        readTranscript(1);
        readTranscript(2);
        readTranscript(3);
        readTranscript(4);
        readConfig("area");
        readConfig("equivalencies");
        readConfig("level");

        System.out.println(area);
        System.out.println(equivalencies);
        System.out.println(level);
//        System.out.println(transcripts.get(1).getCourses().get(7).toString());
//        System.out.println(transcripts.get(1).getCourses().get(7).getSection().getYear());
//        System.out.println(transcripts.get(1).getCourses().get(7).getSection().getCampus());
//        System.out.println(transcripts.get(1).getYearOfStudy());
//        System.out.println(transcripts.get(1).getTranscriptID());
//        System.out.println(transcripts.get(1).getCourses().get(7).getSection().getTerm());

        System.out.println(AnalyzeTranscript.countNumberOfStudentPerYear(2011));
        System.out.println(AnalyzeTranscript.countNumberOfStudentPerYearPerCourse(2011, "STAT2593"));
        System.out.println(AnalyzeTranscript.getCoursesPerArea("math"));
        System.out.println(AnalyzeTranscript.getGradeDistributionPerArea("math"));
    }
}