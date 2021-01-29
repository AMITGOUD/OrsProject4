package in.co.sunrays.model.test;

import java.sql.Timestamp;
import java.util.Date;

import in.co.sunrays.bean.StudentBean;
import in.co.sunrays.exception.ApplicationException;
import in.co.sunrays.exception.DuplicateRecordException;
import in.co.sunrays.model.StudentModel;

public class StudentModelTest {

	public static void main(String[] args) throws Exception {
		StudentBean sb=new StudentBean();
		sb.setCollegeId(1l);
		sb.setCollegeName("svpi");
		sb.setDob(new java.sql.Date(new Date().getTime()));
		sb.setEmail("lnt123@gmial.com");
		sb.setFirstName("radhe");
		sb.setFirstName("desuza");
		//sb.setId(1);
		sb.setMobileNo("7999714095");
		sb.setCreatedBy("rrradmin");
		sb.setCreatedDatetime(new Timestamp(new Date().getTime()));
		sb.setModifiedBy("admin");
		sb.setModifiedDatetime(new Timestamp(new Date().getTime()));
		sb.setLastName("kalu");
		StudentModel sm=new StudentModel();
		
		sm.add(sb);;
	}

}
