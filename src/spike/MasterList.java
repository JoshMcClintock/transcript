package spike;

import java.util.ArrayList;
import java.util.*;


public class MasterList {
	
	static ArrayList<String> masterlist;
	static ArrayList<String> list;
	
	public MasterList() {
		masterlist = new ArrayList<>();
		list = new ArrayList<>();
	}
	
	public void create(ArrayList<Transcript> transcripts) {
		for(int i = 0; i < transcripts.size(); i++) { 
			for(int j = 0; j < transcripts.get(i).getCourses().size(); j++) {
						String temp = transcripts.get(i).getCourses().get(j).getCourseNumber();
						list.add(temp);
						
			}
		}
		
		for(int i = 0; i < list.size(); i++) {
			if(!masterlist.contains(list.get(i))) {
				masterlist.add(list.get(i));
			}
		}
		
	}
	
	public ArrayList<String> getMasterList(){
		return masterlist;
	}
	
	@Override
	public String toString() {
		String temp = "";
		
		for(String c: masterlist) {
			temp += c.toString();
		}
		return temp;
	}
	
	
	
	
	
	
	
	
	
	
	
}