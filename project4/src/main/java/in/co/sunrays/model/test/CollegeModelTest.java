package in.co.sunrays.model.test;

import in.co.sunrays.bean.CollegeBean;
import in.co.sunrays.exception.ApplicationException;
import in.co.sunrays.exception.DuplicateRecordException;
import in.co.sunrays.model.CollegeModel;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * College Model Test classes
 *
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 *
 */
public class CollegeModelTest {

    /**
     * Model object to test
     */
    public static CollegeModel model = new CollegeModel();

    /**
     * Main method to call test methods.
     *
     * @param args
     * @throws Exception 
     */
    public static void main(String[] args) throws Exception {
        // testAdd();
        // testDelete();
         //testUpdate();
         //testFindByName();
        // testFindByPK();
        // testSearch();
        //testList();
		/*
		 *  cb.setAddress("khandwa naka");
		 * cb.setCity("indore"); cb.setCreatedBy("admin"); cb.setCreatedDatetime(new
		 * Timestamp(new Date().getTime())); cb.setModifiedBy("admin");
		 * cb.setName("sgsits"); cb.setPhoneNo("07312894561"); cb.setState("mp");
		 * cb.setModifiedDatetime(new Timestamp(new Date().getTime()));
		 */
    	CollegeBean cb=new CollegeBean();
CollegeModel cm=new CollegeModel();


ArrayList l=(ArrayList) cm.list();
Iterator it=l.iterator();
while (it.hasNext()) {
	CollegeBean cbl = (CollegeBean) it.next();
	System.out.println(cbl.getId());
	
}
CollegeBean bn=cm.findByPK(1);
System.out.println(bn.getName());



    }}

  

