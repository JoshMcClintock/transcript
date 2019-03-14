package spike;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * this class reads configuration - area
 */

public class ConfigReader {

    static BufferedReader br = null;
    static FileReader fr = null;
    static BufferedWriter bw = null;
    static FileWriter fw = null;
    static Scanner scanner = null;

    static Transcript transcript = new Transcript();
    static ArrayList<ArrayList<String>> equivalencies = new ArrayList<>();
    static ArrayList<String> areaType = new ArrayList<>();
    static ArrayList<ArrayList<String>> courseListPerArea = new ArrayList<>();

    public static void readTranscript(String fileName){
        try {
            fr = new FileReader(fileName);
            br = new BufferedReader(fr);

            fw = new FileWriter("result.txt");
            bw = new BufferedWriter(fw);

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
                    Course c = new Course(courseNum, s, Double.parseDouble(ch));
                    CourseResult cr = new CourseResult(c, grade);
                    transcript.addCourseResult(cr);
                }
            }
            //print the transcript to see if any bug here
            //System.out.println(transcript);
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


    public static void readArea(String fileName){
        try {
            fr = new FileReader(fileName);
            br = new BufferedReader(fr);

            String sCurrentLine;

            while ((sCurrentLine = br.readLine()) != null) {
                if (!sCurrentLine.isEmpty()) {
                    if (!sCurrentLine.matches(".*\\d.*")){
                        areaType.add(sCurrentLine);
                    }
                }
            }

            fr = new FileReader(fileName);
            br = new BufferedReader(fr);

            ArrayList<String> temp = new ArrayList<>();

            while ((sCurrentLine = br.readLine()) != null) {
                if (!sCurrentLine.isEmpty()) {
                    if (areaType.contains(sCurrentLine)){
                        if (!temp.isEmpty()) {
                            //System.out.println(temp);
                            courseListPerArea.add(temp);
                        }
                        temp = new ArrayList<>();
                    }
                    else temp.add(sCurrentLine);
                }
            }

            System.out.println(areaType);//read the areaList, find the index, and use the index in courseListPerArea to find the match course
            System.out.println(courseListPerArea);//2d arrayList for courseListPerArea
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

    public static void readEquivalencies(String fileName){
        ArrayList<String> temp;

        try {
            fr = new FileReader(fileName);
            br = new BufferedReader(fr);

            String sCurrentLine;
            String str;
            while ((sCurrentLine = br.readLine()) != null) {
                temp = new ArrayList<>();
                str = sCurrentLine.replaceAll("\\s", ",");
                String[] array = str.split("\\,");
                for (String s : array) {
                        temp.add(s);
                }
                equivalencies.add(temp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println(equivalencies); //test if it works
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

    public static ArrayList<String> getAreaType(){
        return areaType;
    }

    public static void main(String[] args) {
        readTranscript("transcript.txt");
        readArea("area.txt");
        readEquivalencies("equivalencies.txt");
    }
}