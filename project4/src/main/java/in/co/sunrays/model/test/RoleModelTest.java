package in.co.sunrays.model.test;

import java.sql.Timestamp;
import java.util.Date;

import in.co.sunrays.bean.RoleBean;
import in.co.sunrays.exception.ApplicationException;
import in.co.sunrays.exception.DuplicateRecordException;
import in.co.sunrays.model.RoleModel;

public class RoleModelTest {

	public static void main(String[] args) throws Exception {
		
		RoleBean rb=new RoleBean();	
		rb.setName("faculty");
		rb.setCreatedBy("admin");
		rb.setCreatedDatetime(new Timestamp(new Date().getTime()));
		rb.setDescription("faculty");
		rb.setModifiedBy("admin");
		rb.setModifiedDatetime(new Timestamp(new Date().getTime()));
		RoleModel rm=new RoleModel();
		//rm.add(rb);
	rb	=rm.findByName("faculty");
		System.out.println(rb.getId());

	}

}
