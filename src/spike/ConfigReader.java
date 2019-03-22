package spike;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * this class reads configuration
 */

public class ConfigReader {

    static BufferedReader br = null;
    static FileReader fr = null;
    static BufferedWriter bw = null;
    static FileWriter fw = null;
    static Scanner scanner = null;

    static Transcript transcript = new Transcript();
    static ArrayList<ArrayList<String>> equivalencies = new ArrayList<>();
    static ArrayList<ArrayList<String>> courseListPerArea = new ArrayList<>();
    static ArrayList<ArrayList<String>> level = new ArrayList<>();

    public static void readTranscript(int i){
        try {
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
                    //System.out.println(arrayList);
                    // Create objects
                    String courseNum = arrayList.get(0);
                    System.out.println(courseNum);
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
                    courseListPerArea.add(temp);
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

    public static Transcript getTranscript(){
        return transcript;
    }

    public static ArrayList<ArrayList<String>> getEquivalencies(){
        return equivalencies;
    }

    public static ArrayList<ArrayList<String>> getCourseListPerArea(){
        return courseListPerArea;
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

        System.out.println(courseListPerArea);
        System.out.println(equivalencies);
        System.out.println(level);
        System.out.println(transcript.getCourseResults().get(0).getSection().getYear());
        System.out.println(transcript.getCourseResults().get(0).getSection().getCampus());
        System.out.println(transcript.getYearOfStudy());
        System.out.println(transcript.getTranscriptID());
        System.out.println(transcript.getCourseResults().get(0).getSection().getTerm());
    }
}