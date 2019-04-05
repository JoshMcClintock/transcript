package TranscriptAnalyzer;

public class Section {

	private String sectionId;
	private String term;

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
			return "Saint John";
		}
		else if (sectionId.substring(0,2).equalsIgnoreCase("fr")){
			return "Fredericton";
		}
		else return "unsupported campus";
	}

	public String getTerm() {
		return term;
	}

}
