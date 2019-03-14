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

    public static void main(String[] args) {

        final String FILENAME = "config.txt";

        BufferedReader br = null;
        FileReader fr = null;
        Scanner scanner;

        ArrayList<String> arealist = new ArrayList<>();

        try {
            fr = new FileReader(FILENAME);
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

            fr = new FileReader(FILENAME);
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
}