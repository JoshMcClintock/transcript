package src.spike;

import java.io.BufferedReader;
//import java.io.BufferedWriter;
import java.io.FileReader;
//import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Driver {

	public static void main(String[] args) {

		final String FILENAME = "transcript.txt"; // CHANGE
		Transcript transcript = new Transcript();

		BufferedReader br = null;
		FileReader fr = null;

		try {
			fr = new FileReader(FILENAME);
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
					Course c = new Course(courseNum, s, Double.parseDouble(ch));
					CourseResult cr = new CourseResult(c, grade);
					transcript.addCourseResult(cr);
				}
			}
			System.out.println(transcript);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null) {
					br.close();
				}

				if (fr != null) {
					fr.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

//		final String OUTPUT = "result.txt";
//
//		BufferedWriter bw = null;
//		FileWriter fw = null;
//
//		try {
//
//			String content = "This is the content to write into file\n";
//
//			fw = new FileWriter(OUTPUT);
//			bw = new BufferedWriter(fw);
//			bw.write(content);
//
//			System.out.println("Done");
//
//		} catch (IOException e) {
//
//			e.printStackTrace();
//
//		} finally {
//
//			try {
//
//				if (bw != null)
//					bw.close();
//
//				if (fw != null)
//					fw.close();
//
//			} catch (IOException ex) {
//
//				ex.printStackTrace();
//
//			}
//
//		}
	}
}
