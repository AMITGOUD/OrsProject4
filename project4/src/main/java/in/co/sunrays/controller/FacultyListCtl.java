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
import in.co.sunrays.model.FacultyModel;
import in.co.sunrays.model.StudentModel;
import in.co.sunrays.util.DataUtility;
import in.co.sunrays.util.PropertyReader;
import in.co.sunrays.util.ServletUtility;

/**
 * Servlet implementation class FacultyListCtl
 */
public class FacultyListCtl extends BaseCtl {
	 private static Logger log = Logger.getLogger(FacultyListCtl.class);

	    @Override
	    protected BaseBean populateBean(HttpServletRequest request) {

	        FacultyBean bean = new FacultyBean();

	        bean.setFirstName(DataUtility.getString(request
	                .getParameter("firstName")));
	        
	        bean.setCollegeName(DataUtility.getString(request.getParameter("collegename")));

	        return bean;
	    }

	    /**
	     * Contains Display logics
	     */
	    protected void doGet(HttpServletRequest request,
	            HttpServletResponse response) throws ServletException, IOException {
	        log.debug("FacultyListCtl doGet Start");
	        List list = null;

	        int pageNo = 1;

	        int pageSize = DataUtility.getInt(PropertyReader.getValue("page.size"));

	        FacultyBean bean = (FacultyBean) populateBean(request);

	        String op = DataUtility.getString(request.getParameter("operation"));

	        FacultyModel model = new FacultyModel();
	        try {
	            list = model.search(bean, pageNo, pageSize);
	            ServletUtility.setList(list, request);
	            if (list == null || list.size() == 0) {
	                ServletUtility.setErrorMessage("No record found ", request);
	            }
	            ServletUtility.setList(list, request);

	            ServletUtility.setPageNo(pageNo, request);
	            ServletUtility.setPageSize(pageSize, request);
	            ServletUtility.forward(getView(), request, response);

	        } catch (ApplicationException e) {
	            log.error(e);
	            ServletUtility.handleException(e, request, response);
	            return;
	        }
	        log.debug("FacultyListCtl doGet End");
	    }

	    /**
	     * Contains Submit logics
	     */
	    @Override
	    protected void doPost(HttpServletRequest request,
	            HttpServletResponse response) throws ServletException, IOException {
	        log.debug("FacultyListCtl doPost Start");
	        List list = null;
	        int pageNo = DataUtility.getInt(request.getParameter("pageNo"));
	        int pageSize = DataUtility.getInt(request.getParameter("pageSize"));
	        pageNo = (pageNo == 0) ? 1 : pageNo;
	        pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader
	                .getValue("page.size")) : pageSize;

	        FacultyBean bean = (FacultyBean) populateBean(request);
	        String op = DataUtility.getString(request.getParameter("operation"));
	        FacultyModel model = new FacultyModel();

	        try {

	            if (OP_SEARCH.equalsIgnoreCase(op) || "Next".equalsIgnoreCase(op)
	                    || "Previous".equalsIgnoreCase(op)) {

	                if (OP_SEARCH.equalsIgnoreCase(op)) {
	                    pageNo = 1;
	                } else if (OP_NEXT.equalsIgnoreCase(op)) {
	                    pageNo++;
	                } else if (OP_PREVIOUS.equalsIgnoreCase(op) && pageNo > 1) {
	                    pageNo--;
	                }

	            }
	            list = model.search(bean, pageNo, pageSize);
	            ServletUtility.setList(list, request);
	            if (list == null || list.size() == 0) {
	                ServletUtility.setErrorMessage("No record found ", request);
	            }
	            ServletUtility.setList(list, request);

	            ServletUtility.setPageNo(pageNo, request);
	            ServletUtility.setPageSize(pageSize, request);
	            ServletUtility.forward(getView(), request, response);

	        } catch (ApplicationException e) {
	            log.error(e);
	            ServletUtility.handleException(e, request, response);
	            return;
	        }
	        log.debug("FacultyListCtl doGet End");
	    }

	    @Override
	    protected String getView() {
	        return ORSView.FACULTY_LIST_VIEW;
	    }
}
