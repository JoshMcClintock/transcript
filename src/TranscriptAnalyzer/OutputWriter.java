package TranscriptAnalyzer;


import java.io.*;
import java.text.*;
import java.util.ArrayList;

public class OutputWriter {

	private static BufferedWriter bw = null;
	private static FileWriter fw = null;
	private static NumberFormat formatter = new DecimalFormat("#0.00");
	
	public static void writeMasterList(int year){
		ArrayList<String> masterList = AnalyzeTranscript.createMasterList(year);
		try {
			fw = new FileWriter("output/"+year+"/Master List");
			bw = new BufferedWriter(fw);
			for (String str : masterList)
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
			fw = new FileWriter("output/"+year+"/Distribution Per Area");
			bw = new BufferedWriter(fw);
			writeLevelIntoFile();
			for (int i=0;i<distributionPerArea.size();i++){
				bw.write("\n");
				String temp = ConfigReader.getArea().get(i).get(0);
                temp = formatItTo12Char(temp);
				bw.write(temp+"\t");
				for (int dis : distributionPerArea.get(i)) {
					bw.write(dis + "\t\t\t");
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
			fw = new FileWriter("output/"+year+"/Distribution Per Course");
			bw = new BufferedWriter(fw);
			writeLevelIntoFile();
			for (int i=0;i<distributionPerCourse.size();i++){
				bw.write("\n");
				String temp = AnalyzeTranscript.createMasterList(year).get(i);
		        temp = formatItTo12Char(temp);
				bw.write(temp+"\t");
				for (int dis : distributionPerCourse.get(i)) {
					bw.write(dis + "\t\t\t");
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

	public static void writeGpaPerAreaPerTranscript(int year, int index){
		ArrayList<Double> gpaPerAreaPerTranscript = AnalyzeTranscript.getAverageGradePerTranscriptForEachArea(year,index);
		try{
			fw = new FileWriter("output/"+year+"/GPA - Transcript" + index);
			bw = new BufferedWriter(fw);
			for (int i=0;i<gpaPerAreaPerTranscript.size();i++) {
				String temp = ConfigReader.getArea().get(i).get(0);
				temp = formatItTo12Char(temp);
				bw.write(temp+"\t"+ formatter.format(gpaPerAreaPerTranscript.get(i))+"\n");
			}
		}
		catch (IOException e){
			e.printStackTrace();
		}
		finally {
			close();
		}
	}

	private static String formatItTo12Char(String temp){
        while (temp.length()<12){
            temp += " ";
        }
        return temp;
    }

	private static void writeLevelIntoFile() throws IOException {
		bw.write("\t\t\t");
		for (int i = 0; i< ConfigReader.getLevel().get(0).size()-1; i++){
			bw.write("\t"+ConfigReader.getLevel().get(i).get(0)+"\t");
		}
		bw.write(ConfigReader.getLevel().get(ConfigReader.getLevel().get(0).size()-1).get(0));
		bw.write("\t\tOthers");
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