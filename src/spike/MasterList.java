package spike;

import java.io.*;
import java.util.ArrayList;

public class MasterList {

	static BufferedWriter bw = null;
	static FileWriter fw = null;
	
	public static ArrayList<String> createMasterList(int year){
		ArrayList<String> masterlist = new ArrayList<>();
		ArrayList<Transcript> transcripts = ConfigReader.getTranscripts(year);
		for (Transcript transcript : transcripts){
			for (Course course : transcript.getCourses()){
				String courseNum = course.getCourseNumber();
				if (!masterlist.contains(courseNum) && !courseNum.isEmpty()){
					masterlist.add(courseNum);
				}
			}
		}
		try {
			fw = new FileWriter("output/MasterList-" + year);
			bw = new BufferedWriter(fw);
			for (String str : masterlist)
				bw.write(str+"\n");
		}
		catch (IOException e){
			e.printStackTrace();
		}
		finally {
			try {
				if (bw != null)
					bw.close();
				if (fw != null)
					fw.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return masterlist;
	}

}