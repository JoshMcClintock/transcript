package spike;

public class Section {

	private String sectionId;
	private String campus;
	private String term;
	private int year;

	public Section(String sectionId, String term){
		this.sectionId = sectionId;
		this.term = term;
	}

	public String getSectionId() {
		return sectionId;
	}

	public int getYear(){
		return Integer.parseInt(term.substring(0,4));
	}

	public void setSectionId(String sectionId) {
		this.sectionId = sectionId;
	}

	public String getCampus() {
		if (sectionId.substring(0,2).equalsIgnoreCase("sj")){
			return campus = "Saint John";
		}
		else if (sectionId.substring(0,2).equalsIgnoreCase("fr")){
			return campus = "Fredericton";
		}
		else return "unsupported campus";
	}

	public String getTerm() {
		return term;
	}

}
