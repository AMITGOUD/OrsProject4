<%@page import="in.co.sunrays.bean.TimeTableBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="in.co.sunrays.controller.*"%>
<%@ page import="in.co.sunrays.util.*"%>
<%@ page import="java.util.List"%>
<%@page errorPage="ErrorView.jsp"%>
<jsp:useBean id="bean" class="in.co.sunrays.bean.TimeTableBean"
	scope="request"></jsp:useBean>
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

<meta charset="ISO-8859-1">
<script>
$(function() {
	$("#dob1").datepicker({
		changeMonth : true,
		changeYear : true,
		yearRange:'1980:2021',
		dateFormat: 'mm/dd/yy'
	});
});
</script>
<style type="text/css">
table.fixed {table-layout:fixed; width:520px; align-self: center; margin-left: 26.5em}/*Setting the table width is important!*/

table.fixed td:nth-of-type(1) {width:120px}/*Setting the width of column 1.*/
table.fixed td:nth-of-type(2) {width:170px}/*Setting the width of column 2.*/
table.fixed td:nth-of-type(3) {width:210px;}/*Setting the width of column 3*/


</style> 
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%@include file="Header.jsp" %>
	<form action="<%=ORSView.TIMETABLE_CTL%>" method="post">
	<%
	       
	
            List l = (List) request.getAttribute("courceList");
	List l1 = (List) request.getAttribute("SubjectList");
	
        %><%if(bean.getId()>0){ %><h1 align="center">Update TimeTable</h1><%}else{ %>
	
	<h1 align="center">Add TimeTable</h1><%} %>
			<H2 align="center">
				<font color="red"> <%=ServletUtility.getErrorMessage(request)%>
				</font>
			</H2>
			<H2 align="center">
				<font color="green"> <%=ServletUtility.getSuccessMessage(request)%>
				</font>
			</H2>


			<input type="hidden" name="id" value="<%=bean.getId()%>"> <input
				type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
			<input type="hidden" name="modifiedBy"
				value="<%=bean.getModifiedBy()%>"> <input type="hidden"
				name="createdDatetime"
				value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
			<input type="hidden" name="modifiedDatetime"
				value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">
	
		<table class="fixed">
		<tr>
				<td>CourceName<font color="red">*</font></td>
				<td><% if(l!=null){%><%=HTMLUtility.getList("courceId",
                    String.valueOf(bean.getCourceId()), l)%>
						<%}%></td><td></td>
						
						
						<%
						
						%>
						
			</tr>
			<tr><td>SubjectName<font color="red">*</font></td>
				<td><% if(l!=null){%><%=HTMLUtility.getList("SubjectId",
                    String.valueOf(bean.getSubjectId()), l1)%>
						<%}%></td><td></td></tr>
			<%-- <tr>
				<th>SubjectName*</th>
				<td><input type="text" name="subjectname"
					value="<%=DataUtility.getStringData(bean.getSubjectName())%>"
					<%=(bean.getId() > 0) ? "readonly" : ""%>><font color="red"><%=ServletUtility.getErrorMessage("subjectname", request)%></font></td>
			</tr> --%>

             <tr>
				<td>ExamTime<font color="red">*</font></td>
				<td><input type="text" name="examtime"
					value="<%=DataUtility.getStringData(bean.getExamTime())%>"
					<%=(bean.getId() > 0) ? "readonly" : ""%>></td><td><font color="red"><%=ServletUtility.getErrorMessage("examtime", request)%></font></td>
			</tr>
             
               <tr>
				<td>ExamDate<font color="red">*</font></td>
				<td><input type="text" name="examdate" id="dob1"
					value="<%=DataUtility.getStringData(bean.getExamDate())%>"
					<%=(bean.getId() > 0) ? "readonly" : ""%>></td><td><font color="red"><%=ServletUtility.getErrorMessage("examdate", request)%></font></td>
			</tr>
			<tr>
				<td>Semester<font color="red">*</font></td>
				<td><input type="text" name="semester"
					value="<%=DataUtility.getStringData(bean.getSemester())%>"
					<%=(bean.getId() > 0) ? "readonly" : ""%>></td><td><font color="red"><%=ServletUtility.getErrorMessage("semester", request)%></font></td>
			</tr>
			
			
			

             <tr><td></td>
             <td colspan="2"><input type="submit" name="operation"
                        value="<%=TimeTableCtl.OP_SAVE%>">  <input type="submit" name="operation"
                        value="<%=TimeTableCtl.OP_CANCEL%>"></td>
             
             
             
             </tr>



		</table>


	</form>
<%@include file="Footer.jsp" %>
</body>
</html>