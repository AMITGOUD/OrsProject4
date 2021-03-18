package in.co.sunrays.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.sunrays.bean.BaseBean;
import in.co.sunrays.bean.CourceBean;
import in.co.sunrays.bean.StudentBean;
import in.co.sunrays.bean.TimeTableBean;
import in.co.sunrays.exception.ApplicationException;
import in.co.sunrays.model.StudentModel;
import in.co.sunrays.model.TimeTableModel;
import in.co.sunrays.util.DataUtility;
import in.co.sunrays.util.PropertyReader;
import in.co.sunrays.util.ServletUtility;

/**
 * Servlet implementation class TimeTableListCtl
 */
public class TimeTableListCtl extends BaseCtl {
	private static Logger log = Logger.getLogger(TimeTableListCtl.class);

    @Override
    protected BaseBean populateBean(HttpServletRequest request) {

        TimeTableBean bean = new TimeTableBean();

        bean.setSubjectName(DataUtility.getString(request
                .getParameter("subjectname")));
        bean.setCourceName(DataUtility.getString(request.getParameter("courcename")));
        bean.setExamDate(DataUtility.getString(request.getParameter("exameDate")));
        return bean;
    }

    /**
     * Contains Display logics
     */
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        log.debug("StudentListCtl doGet Start");
        List list = null;

        int pageNo = 1;

        int pageSize = DataUtility.getInt(PropertyReader.getValue("page.size"));

        TimeTableBean bean = (TimeTableBean) populateBean(request);

        String op = DataUtility.getString(request.getParameter("operation"));

        TimeTableModel model = new TimeTableModel();
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
        log.debug("TimetableListCtl doGet End");
    }

    /**
     * Contains Submit logics
     */
    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        log.debug("StudentListCtl doPost Start");
        List list = null;
        int pageNo = DataUtility.getInt(request.getParameter("pageNo"));
        int pageSize = DataUtility.getInt(request.getParameter("pageSize"));
        pageNo = (pageNo == 0) ? 1 : pageNo;
        pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader
                .getValue("page.size")) : pageSize;

        TimeTableBean bean = (TimeTableBean) populateBean(request);
        String op = DataUtility.getString(request.getParameter("operation"));
        // get the selected checkbox ids array for delete list
        String[] ids = request.getParameterValues("ids");
        TimeTableModel model = new TimeTableModel();

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

            }else if (OP_NEW.equalsIgnoreCase(op)) {
                ServletUtility.redirect(ORSView.TIMETABLE_CTL, request,
                        response);
                return;
            } else if (OP_DELETE.equalsIgnoreCase(op)) {
                pageNo = 1;
                if (ids != null && ids.length > 0) {
                    TimeTableBean deletebean = new TimeTableBean();
                    for (String id : ids) {
                        deletebean.setId(DataUtility.getInt(id));
                        model.delete(deletebean);
                    }
                } else {
                    ServletUtility.setErrorMessage(
                            "Select at least one record", request);
                }}
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
        log.debug("TimeTableListCtl doGet End");
    }

    @Override
    protected String getView() {
        return ORSView.TIMETABLE_LIST_VIEW;
    }
}
