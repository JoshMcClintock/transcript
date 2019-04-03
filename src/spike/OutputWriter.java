package spike;

import java.io.*;
import java.util.ArrayList;

public class OutputWriter {

	static BufferedWriter bw = null;
	static FileWriter fw = null;
	
	public static void writeMasterList(int year){
		ArrayList<String> masterlist = AnalyzeTranscript.createMasterList(year);
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
			close();
		}
	}

	public static void writeDistributionPerArea(int year){
		ArrayList<ArrayList<Integer>> distributionPerArea = AnalyzeTranscript.getGradeDistributionForEveryArea(year);
		try {
			fw = new FileWriter("output/distribution_per_area-" + year);
			bw = new BufferedWriter(fw);
			for (int i=0;i<ConfigReader.getLevel().get(0).size();i++){
				bw.write(ConfigReader.getLevel().get(i).get(0)+"\t");
			}
			bw.write("others");
			for (int i=0;i<distributionPerArea.size();i++){
				bw.write("\n");
				bw.write(ConfigReader.getArea().get(i).get(0)+"\t");
				for (int dis : distributionPerArea.get(i)) {
					bw.write(dis + "\t");
				}
			}
		}
		catch (IOException e){
			e.printStackTrace();
		}
		finally {
			close();
		}
	}

	public static void writeDistributionPerCourse(int year){
		ArrayList<ArrayList<Integer>> distributionPerCourse = AnalyzeTranscript.getGradeDistributionPerCohort(year);
		try {
			fw = new FileWriter("output/distribution_per_course-" + year);
			bw = new BufferedWriter(fw);
			for (int i=0;i<ConfigReader.getLevel().get(0).size();i++){
				bw.write(ConfigReader.getLevel().get(i).get(0)+"\t");
			}
			bw.write("others");
			for (int i=0;i<distributionPerCourse.size();i++){
				bw.write("\n");
				bw.write(AnalyzeTranscript.createMasterList(year).get(i)+"\t");
				for (int dis : distributionPerCourse.get(i)) {
					bw.write(dis + "\t");
				}
			}
		}
		catch (IOException e){
			e.printStackTrace();
		}
		finally {
			close();
		}
	}

	private static void close(){
		try {
			if (bw != null)
				bw.close();
			if (fw != null)
				fw.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

}