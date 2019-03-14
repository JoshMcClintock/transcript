package src.spike;

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

    public static void readTranscript(String fileName){
        Transcript transcript = new Transcript();
        BufferedReader br = null;
        FileReader fr = null;

        BufferedWriter bw = null;
        FileWriter fw = null;

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
        BufferedReader br = null;
        FileReader fr = null;
        Scanner scanner;

        ArrayList<String> arealist = new ArrayList<>();

        try {
            fr = new FileReader(fileName);
            br = new BufferedReader(fr);

            String sCurrentLine;

            while ((sCurrentLine = br.readLine()) != null) {
                if (!sCurrentLine.isEmpty()) {
                    scanner = new Scanner(sCurrentLine);
                    String str = scanner.next();

                    if (!str.matches(".*\\d.*")){
                        arealist.add(str);
                    }
                }
            }
            ArrayList<ArrayList<String>> courseList = new ArrayList<>();

            fr = new FileReader(fileName);
            br = new BufferedReader(fr);

            ArrayList<String> temp = new ArrayList<>();

            while ((sCurrentLine = br.readLine()) != null) {
                if (!sCurrentLine.isEmpty()) {
                    if (arealist.contains(sCurrentLine)){
                        if (!temp.isEmpty()) {
                            //System.out.println(temp);
                            courseList.add(temp);
                        }
                        temp = new ArrayList<>();
                    }
                    else temp.add(sCurrentLine);
                }
            }

            System.out.println(arealist);//read the areaList, find the index, and use the index in courseList to find the match course
            System.out.println(courseList);//2d arrayList for courseList
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

    public static void main(String[] args) {
        readTranscript("transcript.txt");
        readArea("area.txt");
    }
}