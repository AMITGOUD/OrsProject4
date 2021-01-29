package in.co.sunrays.model.test;

import java.sql.Timestamp;
import java.util.Date;

import in.co.sunrays.bean.CourceBean;
import in.co.sunrays.exception.ApplicationException;
import in.co.sunrays.exception.DuplicateRecordException;
import in.co.sunrays.model.CourceModel;

public class CourceModelTest {
public static void main(String[] args) throws Exception {
	CourceBean cb=new CourceBean();
	cb.setCourcename("Bcom");
	cb.setCourceDescription(" 3 year cource ");
	cb.setId(2L);
	cb.setCreatedBy("admin");
	cb.setModifiedBy("admin");
	cb.setCreatedDatetime(new Timestamp(new Date().getTime()));
	cb.setModifiedDatetime(new Timestamp(new Date().getTime()));
	CourceModel cm=new CourceModel();
	cm.add(cb);
}
}
