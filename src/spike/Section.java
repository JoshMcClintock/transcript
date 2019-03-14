package src.spike;

public class Section {

	private String sectionId;
	private String campus;
	private String term;

	public Section(String sectionId, String term){
		this.sectionId = sectionId;
		this.term = term;
	}

	public String getSectionId() {
		return sectionId;
	}

	public void setSectionId(String sectionId) {
		this.sectionId = sectionId;
	}

	public String getCampus() {
		return campus;
	}

	public void setCampus(String sectionId) {
		if (sectionId.substring(0,1).equalsIgnoreCase("sj")){
			campus = "Saint John";
		}
		else if (sectionId.substring(0,1).equalsIgnoreCase("fr")){
			campus = "Fredericton";
		}
		else System.out.println("not supported campus");
	}

	public String getTerm() {
		return term;
	}

}
