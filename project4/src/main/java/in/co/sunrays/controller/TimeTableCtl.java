package in.co.sunrays.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.sunrays.bean.BaseBean;
import in.co.sunrays.bean.StudentBean;
import in.co.sunrays.bean.TimeTableBean;
import in.co.sunrays.exception.ApplicationException;
import in.co.sunrays.exception.DuplicateRecordException;
import in.co.sunrays.model.CollegeModel;
import in.co.sunrays.model.CourceModel;
import in.co.sunrays.model.StudentModel;
import in.co.sunrays.model.SubjectModel;
import in.co.sunrays.model.TimeTableModel;
import in.co.sunrays.util.DataUtility;
import in.co.sunrays.util.DataValidator;
import in.co.sunrays.util.PropertyReader;
import in.co.sunrays.util.ServletUtility;

/**
 * Servlet implementation class TimeTableCtl
 */
public class TimeTableCtl extends BaseCtl {
	 private static Logger log = Logger.getLogger(TimeTableCtl.class);

	    @Override
	    protected void preload(HttpServletRequest request) {
	        CourceModel model = new CourceModel();
	        SubjectModel smodel=new SubjectModel();
	        try {
	            List l = model.list();
	            List l1=smodel.list();
	            request.setAttribute("courceList", l);
	            request.setAttribute("SubjectList", l1);
	        } catch (ApplicationException e) {
	            log.error(e);
	        }

	    }

	    @Override
	    protected boolean validate(HttpServletRequest request) {

	        log.debug("StudentCtl Method validate Started");

	        boolean pass = true;

	        String op = DataUtility.getString(request.getParameter("operation"));
	       
	       
	        if (DataValidator.isNull(request.getParameter("examtime"))) {
	            request.setAttribute("examtime",
	                    PropertyReader.getValue("error.require", "examtime"));
	            pass = false;
	        }
	        if (DataValidator.isNull(request.getParameter("examdate"))) {
	            request.setAttribute("examdate",
	                    PropertyReader.getValue("error.require", "examdate"));
	            pass = false;
	        }
	       
	        if (DataValidator.isNull(request.getParameter("semester"))) {
	            request.setAttribute("semester",
	                    PropertyReader.getValue("error.require", "semester"));
	            pass = false;
	        }
			
			 
	        
	        log.debug("StudentCtl Method validate Ended");

	        return pass;
	    }

	    @Override
	    protected BaseBean populateBean(HttpServletRequest request) {

	        log.debug("StudentCtl Method populatebean Started");

	        TimeTableBean bean = new TimeTableBean();

	        bean.setId(DataUtility.getLong(request.getParameter("id")));

	        bean.setSubjectName(DataUtility.getString(request
	                .getParameter("subjectname")));

	        bean.setCourceId(DataUtility.getLong(request.getParameter("courceId")));

	        bean.setSemester(DataUtility.getString(request.getParameter("semester")));

	        bean.setExamDate(DataUtility.getString(request.getParameter("examdate")));

	        bean.setExamTime(DataUtility.getString(request.getParameter("examtime")));

	       bean.setSubjectId(DataUtility.getLong(request.getParameter("SubjectId")));

	        populateDTO(bean, request);

	        log.debug("StudentCtl Method populatebean Ended");

	        return bean;
	    }

	    /**
	     * Contains Display logics
	     */
	   
	    
	    
	    
	    protected void doGet(HttpServletRequest request,
	            HttpServletResponse response) throws ServletException, IOException {

	        log.debug("TietableCtl Method doGet Started");

	        String op = DataUtility.getString(request.getParameter("operation"));
	        long id = DataUtility.getLong(request.getParameter("id"));

	        // get model

	        TimeTableModel model = new TimeTableModel();
	        if (id > 0 || op != null) {
	            TimeTableBean bean;
	            try {
	                bean = model.findByPK(id);
	                ServletUtility.setBean(bean, request);
	            } catch (ApplicationException e) {
	                log.error(e);
	                ServletUtility.handleException(e, request, response);
	                return;
	            }
	        }
	        ServletUtility.forward(getView(), request, response);
	        log.debug("TimeTableCtl Method doGett Ended");
	    }

	    /**
	     * Contains Submit logics
	     */
	    protected void doPost(HttpServletRequest request,
	            HttpServletResponse response) throws ServletException, IOException {

	        log.debug("TimeTableCtl Method doPost Started");

	        String op = DataUtility.getString(request.getParameter("operation"));

	        // get model

	        TimeTableModel model = new TimeTableModel();

	        long id = DataUtility.getLong(request.getParameter("id"));

	        if (OP_SAVE.equalsIgnoreCase(op)) {
	        	TimeTableBean bean=new TimeTableBean();
	             bean = (TimeTableBean) populateBean(request);

	            try {
	                if (id > 0) {
	                    model.update(bean);
	                } else {
	                    long pk = model.add(bean);
	                    bean.setId(pk);
	                }

	                ServletUtility.setBean(bean, request);
	                ServletUtility.setSuccessMessage("Data is successfully saved",
	                        request);

	            } catch (ApplicationException e) {
	                log.error(e);
	                ServletUtility.handleException(e, request, response);
	                return;
	            } catch (DuplicateRecordException e) {
	                ServletUtility.setBean(bean, request);
	                ServletUtility.setErrorMessage(
	                        "subjectTimetable already exists", request);
	            }

	        }

	        else if (OP_DELETE.equalsIgnoreCase(op)) {

	            TimeTableBean bean = (TimeTableBean) populateBean(request);
	            try {
	                model.delete(bean);
	                ServletUtility.redirect(ORSView.TIMETABLE_LIST_CTL, request,
	                        response);
	                return;

	            } catch (ApplicationException e) {
	                log.error(e);
	                ServletUtility.handleException(e, request, response);
	                return;
	            }

	        } else if (OP_CANCEL.equalsIgnoreCase(op)) {

	            ServletUtility
	                    .redirect(ORSView.TIMETABLE_CTL, request, response);
	            return;

	        }
	        ServletUtility.forward(getView(), request, response);

	        log.debug("TimeTableCtl Method doPost Ended");
	    }

	    @Override
	    protected String getView() {
	        return ORSView.TIMETABLE_VIEW;
	    }
}
