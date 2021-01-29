package in.co.sunrays.bean;

import java.util.Date;

public class TimeTableBean extends BaseBean {
	private String courseName;

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public long getCourceId() {
		return CourceId;
	}

	public void setCourceId(long courceId) {
		CourceId = courceId;
	}

	public String getSubjectName() {
		return SubjectName;
	}

	public void setSubjectName(String subjectName) {
		SubjectName = subjectName;
	}

	public String getExamDate() {
		return examDate;
	}

	public void setExamDate(String examDate) {
		this.examDate = examDate;
	}

	public String getExamTime() {
		return examTime;
	}

	public void setExamTime(String examTime) {
		this.examTime = examTime;
	}

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	private long CourceId;
	private String SubjectName;
	private long SubjectId;
	private String examDate;
	private String examTime;
	private String semester;
    private String CourceName;
	public long getSubjectId() {
		return SubjectId;
	}

	public void setSubjectId(long subjectId) {
		SubjectId = subjectId;
	}

	public String getCourceName() {
		return CourceName;
	}

	public void setCourceName(String courceName) {
		CourceName = courceName;
	}

	public String getKey() {
		// TODO Auto-generated method stub
		 return id + "";
	}

	public String getValue() {
		// TODO Auto-generated method stub
		return SubjectName;
	}

}
