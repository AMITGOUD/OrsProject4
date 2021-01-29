package in.co.sunrays.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.sunrays.bean.BaseBean;
import in.co.sunrays.bean.FacultyBean;
import in.co.sunrays.bean.StudentBean;
import in.co.sunrays.exception.ApplicationException;
import in.co.sunrays.exception.DuplicateRecordException;
import in.co.sunrays.model.CollegeModel;
import in.co.sunrays.model.FacultyModel;
import in.co.sunrays.model.StudentModel;
import in.co.sunrays.util.DataUtility;
import in.co.sunrays.util.DataValidator;
import in.co.sunrays.util.PropertyReader;
import in.co.sunrays.util.ServletUtility;

/**
 * Servlet implementation class FacultyCtl
 */
public class FacultyCtl extends BaseCtl {
	 private static Logger log = Logger.getLogger(FacultyCtl.class);

	    @Override
	    protected void preload(HttpServletRequest request) {
	        CollegeModel model = new CollegeModel();
	        try {
	            List l = model.list();
	            request.setAttribute("collegeList", l);
	        } catch (ApplicationException e) {
	            log.error(e);
	        }

	    }

	    @Override
	    protected boolean validate(HttpServletRequest request) {

	        log.debug("FacultyCtl Method validate Started");

	        boolean pass = true;

	        String op = DataUtility.getString(request.getParameter("operation"));
	        String email = request.getParameter("login");
	        String doj = request.getParameter("doj");

	        if (DataValidator.isNull(request.getParameter("firstname"))) {
	            request.setAttribute("firstname",
	                    PropertyReader.getValue("error.require", "First Name"));
	            pass = false;
	        }
	        if (DataValidator.isNull(request.getParameter("lastname"))) {
	            request.setAttribute("lastname",
	                    PropertyReader.getValue("error.require", "Last Name"));
	            pass = false;
	        }
	        if (DataValidator.isNull(request.getParameter("mobile"))) {
	            request.setAttribute("mobile",
	                    PropertyReader.getValue("error.require", "Mobile No"));
	            pass = false;
	        }
	        if (DataValidator.isNull(request.getParameter("subjectname"))) {
	            request.setAttribute("subjectname",
	                    PropertyReader.getValue("error.require", "subjectname"));
	            pass = false;
	        }
	        if (DataValidator.isNull(request.getParameter("courcename"))) {
	            request.setAttribute("courcename",
	                    PropertyReader.getValue("error.require", "courcename"));
	            pass = false;
	        }
	        if (DataValidator.isNull(request.getParameter("gender"))) {
	            request.setAttribute("gender",
	                    PropertyReader.getValue("error.require", "gender"));
	            pass = false;
	        }
	        
	        if (DataValidator.isNull(email)) {
	            request.setAttribute("login",
	                    PropertyReader.getValue("error.require", "Email "));
	            pass = false;
			} 
				  else if (!DataValidator.isEmail(email)) { request.setAttribute("email",
				  PropertyReader.getValue("error.email", "Email ")); pass = false; }
				 
	        if (DataValidator.isNull(request.getParameter("collegeId"))) {
	            request.setAttribute("collegeId",
	                    PropertyReader.getValue("error.require", "College Name"));
	            pass = false;
	        }
			
			  if (DataValidator.isNull(doj)) { request.setAttribute("doj",
			  PropertyReader.getValue("error.require", "Date Of joining")); pass = false; }
			 
	        else if (!DataValidator.isDate(doj)) {
	            request.setAttribute("doj",
	                    PropertyReader.getValue("error.date", "date of joining "));
	            pass = false;
	        }
	        log.debug("FacultyCtl Method validate Ended");

	        return pass;
	    }

	    @Override
	    protected BaseBean populateBean(HttpServletRequest request) {

	        log.debug("FacultyCtl Method populatebean Started");

	        FacultyBean bean = new FacultyBean();

	        bean.setId(DataUtility.getLong(request.getParameter("id")));

	        bean.setFirstName(DataUtility.getString(request
	                .getParameter("firstname")));

	        bean.setLastName(DataUtility.getString(request.getParameter("lastname")));

	        bean.setJoiningDate(DataUtility.getString(request.getParameter("doj")));

	        bean.setMobileNo(DataUtility.getString(request.getParameter("mobile")));

	        bean.setLoginId(DataUtility.getString(request.getParameter("login")));

	        bean.setCollegeId(DataUtility.getLong(request.getParameter("collegeId")));
            bean.setGender(DataUtility.getString(request.getParameter("gender")));
	        
            bean.setCourcename(DataUtility.getString(request.getParameter("courcename")));
            
            bean.setSubjectName(DataUtility.getString(request.getParameter("subjectname")));
            populateDTO(bean, request);

	        log.debug("StudentCtl Method populatebean Ended");

	        return bean;
	    }

	    /**
	     * Contains Display logics
	     */
	   
	    
	    
	    
	    protected void doGet(HttpServletRequest request,
	            HttpServletResponse response) throws ServletException, IOException {

	        log.debug("FacultyCtl Method doGet Started");

	        String op = DataUtility.getString(request.getParameter("operation"));
	        long id = DataUtility.getLong(request.getParameter("id"));

	        // get model

	        FacultyModel model = new FacultyModel();
	        if (id > 0 || op != null) {
	            FacultyBean bean;
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
	        log.debug("FacultyCtl Method doGett Ended");
	    }

	    /**
	     * Contains Submit logics
	     */
	    protected void doPost(HttpServletRequest request,
	            HttpServletResponse response) throws ServletException, IOException {

	        log.debug("FacultyCtl Method doPost Started");

	        String op = DataUtility.getString(request.getParameter("operation"));

	        // get model

	        FacultyModel model = new FacultyModel();

	        long id = DataUtility.getLong(request.getParameter("id"));

	        if (OP_SAVE.equalsIgnoreCase(op)) {
	        	FacultyBean bean=new FacultyBean();
	             bean = (FacultyBean) populateBean(request);

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
	                        "faculty already exists", request);
	            }

	        }

	        else if (OP_DELETE.equalsIgnoreCase(op)) {

	        	FacultyBean bean = (FacultyBean) populateBean(request);
	            try {
	                model.delete(bean);
	                ServletUtility.redirect(ORSView.FACULTY_LIST_CTL, request,
	                        response);
	                return;

	            } catch (ApplicationException e) {
	                log.error(e);
	                ServletUtility.handleException(e, request, response);
	                return;
	            }

	        } else if (OP_CANCEL.equalsIgnoreCase(op)) {

	            ServletUtility
	                    .redirect(ORSView.FACULTY_CTL, request, response);
	            return;

	        }
	        ServletUtility.forward(getView(), request, response);

	        log.debug("FacultyRCtl Method doPost Ended");
	    }

	    @Override
	    protected String getView() {
	        return ORSView.FACULTY_VIEW;
	    }
}
