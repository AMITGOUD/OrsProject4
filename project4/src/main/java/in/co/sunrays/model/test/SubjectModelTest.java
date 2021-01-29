package in.co.sunrays.model.test;

import java.sql.Timestamp;
import java.util.Date;

import in.co.sunrays.bean.SubjectBean;
import in.co.sunrays.exception.ApplicationException;
import in.co.sunrays.exception.DuplicateRecordException;
import in.co.sunrays.model.SubjectModel;

public class SubjectModelTest {

	public static void main(String[] args) throws Exception {
		SubjectBean sb=new SubjectBean();
		sb.setCourceId(1);
		sb.setId(1);
		sb.setSubjectName("dbms");
		sb.setSubjectCode("102");
		sb.setDescription("very important");
		sb.setCreatedBy("admin");
		sb.setCreatedDatetime(new Timestamp(new Date().getTime()));
		sb.setModifiedBy("admin");
		sb.setModifiedDatetime(new Timestamp(new Date().getTime()));

		SubjectModel sm=new SubjectModel();
		//sm.add(sb);
		sm.update(sb);
	}

}
