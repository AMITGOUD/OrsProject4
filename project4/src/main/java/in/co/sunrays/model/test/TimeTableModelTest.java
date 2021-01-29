package in.co.sunrays.model.test;

import java.sql.Timestamp;
import java.util.Date;

import in.co.sunrays.bean.TimeTableBean;
import in.co.sunrays.exception.ApplicationException;
import in.co.sunrays.exception.DuplicateRecordException;
import in.co.sunrays.model.TimeTableModel;

public class TimeTableModelTest {

	public static void main(String[] args) throws Exception {

TimeTableBean ttb=new TimeTableBean();
ttb.setCourceId(1);
ttb.setSubjectName("dataStructure");
ttb.setSemester("2nd");
ttb.setCreatedBy("admin");
ttb.setModifiedBy("..");
ttb.setExamDate("10-12-20");
ttb.setExamTime("10.00am to 1.00pm ");
ttb.setCreatedDatetime(new Timestamp(new Date().getTime()));
ttb.setModifiedDatetime(new Timestamp(new Date().getTime()));
TimeTableModel tm=new TimeTableModel();
//tm.add(ttb);
tm.update(ttb);

	}

}
