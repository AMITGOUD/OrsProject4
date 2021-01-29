package in.co.sunrays.bean;

public class SubjectBean extends BaseBean{

	public String getSubjectCode() {
		return subjectCode;
	}

	public void setSubjectCode(String subjectId) {
		this.subjectCode = subjectId;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getCourceId() {
		return courceId;
	}

	public void setCourceId(long l) {
		this.courceId = l;
	}

	public String getCourceName() {
		return courceName;
	}

	public void setCourceName(String courceName) {
		this.courceName = courceName;
	}

	private String subjectCode;
	private String subjectName;
	private String description;
	private Long courceId;
	private String courceName;
	
	
	public String getKey() {
		// TODO Auto-generated method stub
		return id+"";
	}

	public String getValue() {
		// TODO Auto-generated method stub
		return subjectName;
	}

}
