<%@page import="in.co.sunrays.model.TimeTableModel"%>
<%@page import="in.co.sunrays.controller.TimeTableListCtl"%>
<%@page import="in.co.sunrays.controller.TimeTableCtl"%>
<%@page import="in.co.sunrays.util.ServletUtility"%>
<%@page import="in.co.sunrays.bean.TimeTableBean"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<html>
<head>
<script src="<%=ORSView.APP_CONTEXT%>/js/select.js"></script>
</head>
<body>
    <%@include file="Header.jsp"%>
    <%TimeTableBean bean=new TimeTableBean(); 
    TimeTableModel model=new TimeTableModel();
    %>
    <center>
        <h1>TimeTable List</h1>

        <form action="<%=ORSView.TIMETABLE_LIST_CTL%>" method="post" name="list01">
            <table width="100%">
                <tr>
                    <td align="center"><label> Subjectname :</label> <input
                        type="text" name="subjectname"
                        value="<%=ServletUtility.getParameter("subjectname", request)%>">
                        <label>courcename :</label> <input type="text" name="courcename"
                        value="<%=ServletUtility.getParameter("courcename", request)%>">
                        <input type="submit" name="operation" value="<%=TimeTableListCtl.OP_SEARCH %>"></td>
                </tr>
            </table>
            
                    <font color="red"><%=ServletUtility.getErrorMessage(request)%></font>
               
            <br>
            <table border="1" width="100%" name="table">
                <tr>
                     
                    <th>S.No.</th>
                    <th><input type="checkbox" name="Check_ctr" value="yes" onClick="checkAll(document.list01.ids, this)"><b>select All</b><dd></th>
                    <th>courceName</th>
                    <th>Subject Name.</th>
                    <th>Exam date.</th>
                    <th>Exam Time.</th>
                    
                    
                </tr>
                
                <%
                    int pageNo = ServletUtility.getPageNo(request);
                    int pageSize = ServletUtility.getPageSize(request);
                    int index = ((pageNo - 1) * pageSize) + 1;

                    List list = ServletUtility.getList(request);
                    int k=list.size();
                    Iterator<TimeTableBean> it = list.iterator();

                    while (it.hasNext()) {

                    	 bean = it.next();
                %>
                <tr>
               
                    <td><%=index++%></td>
                     <td><input type="checkbox" name="ids" value="<%=bean.getId()%>"></td>
                    <td><%=bean.getCourceName()%></td>
                    <td><%=bean.getSubjectName()%></td>
                    <td><%=bean.getExamDate()%></td>
                    <td><%=bean.getExamTime()%></td>
                   <td><a href="TimeTableCtl?id=<%=bean.getId()%>">Edit</a></td>
                    
                </tr>
                <%
                    }
                %>
            </table>
            <table width="100%">
				<tr>
					<td align="left">
						<%
							if (pageNo == 1) {
						%><input type="submit" name="operation"
						disabled="disabled" value="<%=TimeTableListCtl.OP_PREVIOUS%>">
					</td><%
							} else {
						%> 
					<td align="left">
						<input type="submit" name="operation"
						value="<%=TimeTableListCtl.OP_PREVIOUS%>">
						<%
							}
						%>
					</td>
					<td><input type="submit" name="operation" value="<%=TimeTableListCtl.OP_DELETE%>"></td>
					<%
						if (list.size() < pageSize || model.nextPK() - 1 == bean.getId()) {
					%>
					<td align="right"><input type="submit" name="operation"
						disabled="disabled" value="<%=TimeTableListCtl.OP_NEXT%>"></td><%}else { %>
					<td align="right">
						 <input align="right" type="submit" name="operation"
						value="<%=TimeTableListCtl.OP_NEXT%>">
						<%
							}
						%>
					</td>
				</tr>
			</table>
            <input type="hidden" name="pageNo" value="<%=pageNo%>"><input
                type="hidden" name="pageSize" value="<%=pageSize%>">


        </form>
        <%@include file="Footer.jsp"%>
    </center>
</body>
</html>