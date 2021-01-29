package in.co.sunrays.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.sunrays.bean.BaseBean;
import in.co.sunrays.bean.MarksheetBean;
import in.co.sunrays.bean.RoleBean;
import in.co.sunrays.bean.StudentBean;
import in.co.sunrays.bean.SubjectBean;
import in.co.sunrays.exception.ApplicationException;
import in.co.sunrays.exception.DuplicateRecordException;
import in.co.sunrays.model.*;
import in.co.sunrays.util.DataUtility;
import in.co.sunrays.util.DataValidator;
import in.co.sunrays.util.PropertyReader;
import in.co.sunrays.util.ServletUtility;


/**
 * Servlet implementation class SubjectCtl
 */
@ WebServlet(name="SubjectCtl",urlPatterns={"/ctl/SubjectCtl"})
public class SubjectCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(SubjectCtl.class);
	
	@Override
		protected void preload(HttpServletRequest request) {
			CourceModel cmodel=new CourceModel();
			try {
				List list=cmodel.list();
				request.setAttribute("courceList", list);
				
			} catch (ApplicationException e) {
				// TODO Auto-generated catch block
				log.error(e);
			}
			
		}
	 @Override
	    protected boolean validate(HttpServletRequest request) {

	        log.debug("SubjectCtl Method validate Started");

	        boolean pass = true;

	        String op = DataUtility.getString(request.getParameter("operation"));
	       

	        if (DataValidator.isNull(request.getParameter("subject"))) {
	            request.setAttribute("subject",
	                    PropertyReader.getValue("error.require", "subject Name"));
	            pass = false;
	        }
	        if (DataValidator.isNull(request.getParameter("subjectCode"))) {
	            request.setAttribute("subjectCode",
	                    PropertyReader.getValue("error.require", "subjectCode"));
	            pass = false;
	        }
	        if (DataValidator.isNull(request.getParameter("desciption"))) {
	            request.setAttribute("desciption",
	                    PropertyReader.getValue("error.require", "desciption"));
	            pass = false;
	        }
	        if (DataValidator.isNull(request.getParameter("courceID"))) {
	            request.setAttribute("courceID",
	                    PropertyReader.getValue("error.require", "courceID"));
	            pass = false;
	        }

	        log.debug("SubjectCtl Method validate Ended");

	        return pass;
	    }
	 @Override
	    protected BaseBean populateBean(HttpServletRequest request) {

	        log.debug("SubjectCtl Method populatebean Started");

	        SubjectBean bean = new SubjectBean();

	        bean.setId(DataUtility.getLong(request.getParameter("id")));

	        bean.setSubjectName(DataUtility.getString(request
	                .getParameter("subject")));

	        bean.setCourceId(DataUtility.getLong(request.getParameter("courceID")));
            bean.setDescription(DataUtility.getString(request.getParameter("desciption")));
	        bean.setSubjectCode(DataUtility.getString(request.getParameter("subjectCode")));
	        populateDTO(bean, request);

	        log.debug("StudentCtl Method populatebean Ended");

	        return bean;
	    }

	 protected void doGet(HttpServletRequest request,
	            HttpServletResponse response) throws ServletException, IOException {
	        log.debug("Subjecttl Method doGet Started");

	        System.out.println("In do get");

	        String op = DataUtility.getString(request.getParameter("operation"));

	        // get model
	        SubjectModel model = new SubjectModel();

	        long id = DataUtility.getLong(request.getParameter("id"));
	        if (id > 0 || op != null) {
	           SubjectBean bean;
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
	        log.debug("RoleCtl Method doGetEnded");
	    }
	 protected void doPost(HttpServletRequest request,
	            HttpServletResponse response) throws ServletException, IOException {

	        log.debug("MarksheetCtl Method doPost Started");

	        String op = DataUtility.getString(request.getParameter("operation"));
	        // get model
	        SubjectModel model = new SubjectModel();

	        long id = DataUtility.getLong(request.getParameter("id"));

	        if (OP_SAVE.equalsIgnoreCase(op)) {

	        	SubjectBean bean = (SubjectBean) populateBean(request);
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
	                ServletUtility.setErrorMessage("subject allready exist",
	                        request);
	            }

	        } else if (OP_DELETE.equalsIgnoreCase(op)) {

	        	SubjectBean bean = (SubjectBean) populateBean(request);
	            System.out.println("in try");
	            try {
	                model.delete(bean);
	                ServletUtility.redirect(ORSView.SUBJECT_LIST_CTL, request,
	                        response);
	                System.out.println("in try");
	                return;
	            } catch (ApplicationException e) {
	                System.out.println("in catch");
	                log.error(e);
	                ServletUtility.handleException(e, request, response);
	                return;
	            }

	        } else if (OP_CANCEL.equalsIgnoreCase(op)) {

	            ServletUtility.redirect(ORSView.SUBJECT_CTL, request,
	                    response);
	            return;

	        }
	        ServletUtility.forward(getView(), request, response);

	        log.debug("SubjectCtl Method doPost Ended");
	    }
	 
	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return ORSView.SUBJECT_VIEW;
	}

}
