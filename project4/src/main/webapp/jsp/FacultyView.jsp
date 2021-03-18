<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="in.co.sunrays.controller.*"%>
<%@ page import="in.co.sunrays.util.*"%>
<%@ page import="java.util.*"%>
<%@page errorPage="ErroView.jsp"%>
<jsp:useBean id="bean" class="in.co.sunrays.bean.FacultyBean"
	scope="request"></jsp:useBean>
<!DOCTYPE html>
<html>

<head>
<style type="text/css">
table.fixed {table-layout:fixed; width:520px; align-self: center; margin-left: 26.5em}/*Setting the table width is important!*/

table.fixed td:nth-of-type(1) {width:105px}/*Setting the width of column 1.*/
table.fixed td:nth-of-type(2) {width:160px}/*Setting the width of column 2.*/
table.fixed td:nth-of-type(3) {width:200px;}/*Setting the width of column 3*/


</style> 
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

</head>
<body>
<%@include file="Header.jsp" %>
	<form action="<%=ORSView.FACULTY_CTL%>" method="post">
	<%
            List l = (List) request.getAttribute("collegeList");
	
        %>
	
	<%if(bean.getId()>0){ %>
            <h1 style="margin-left: 15.5em">Update Faculty</h1><%}else{ %>
            <h1 style="margin-left: 15.5em">Add Faculty</h1><%} %>

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
				<td>Firstname<font color="red">*</font></td>
				<td><input type="text" name="firstname"
					value="<%=DataUtility.getStringData(bean.getFirstName())%>"
					<%=(bean.getId() > 0) ? "readonly" : ""%>></td><td><font color="red"><%=ServletUtility.getErrorMessage("firstname", request)%></font></td>
			</tr>

             <tr>
				<td>Lastname<font color="red">*</font></td>
				<td><input type="text" name="lastname"
					value="<%=DataUtility.getStringData(bean.getLastName())%>"
					<%=(bean.getId() > 0) ? "readonly" : ""%>></td><td><font color="red"><%=ServletUtility.getErrorMessage("lastname", request)%></font></td>
			</tr>
             
<tr>
				<td>Mobile<font color="red">*</font></td>
				<td><input type="text" name="mobile"
					value="<%=DataUtility.getStringData(bean.getMobileNo())%>"
					<%=(bean.getId() > 0) ? "readonly" : ""%>></td><td><font color="red"><%=ServletUtility.getErrorMessage("mobile", request)%></font></td>
			</tr>
			<tr>
				<td>EmailId<font color="red">*</font></td>
				<td><input type="text" name="login"
					value="<%=DataUtility.getStringData(bean.getLoginId())%>"
					<%=(bean.getId() > 0) ? "readonly" : ""%>></td><td><font color="red"><%=ServletUtility.getErrorMessage("login", request)%></font></td>
			</tr>
			<tr>
				<td>SubjectName<font color="red">*</font></td>
				<td><input type="text" name="subjectname"
					value="<%=DataUtility.getStringData(bean.getSubjectName())%>"
					<%=(bean.getId() > 0) ? "readonly" : ""%>></td><td><font color="red"><%=ServletUtility.getErrorMessage("subjectname", request)%></font></td>
			</tr>
			<tr>
				<td>CourceName<font color="red">*</font></td>
				<td><input type="text" name="courcename"
					value="<%=DataUtility.getStringData(bean.getCourcename())%>"
					<%=(bean.getId() > 0) ? "readonly" : ""%>></td><td><font color="red"><%=ServletUtility.getErrorMessage("courcename", request)%></font></td>
			</tr>
			<tr>
					<td>Gender</td>
					<td>
						<%
							HashMap map = new HashMap();
							map.put("M", "Male");
							map.put("F", "Female");

							String htmlList = HTMLUtility.getList("gender", bean.getGender(), map);
						%> <%=htmlList%> </td>
				</tr>
			<tr>
				<td>Date of joining<font color="red">*</font></td>
				<td><input type="text" name="doj" id="dob1" value="<%=DataUtility.getStringData(bean.getJoiningDate())%>"
					<%=(bean.getId() > 0) ? "readonly" : ""%>></td><td><font color="red"><%=ServletUtility.getErrorMessage("doj", request)%></font></td>
			</tr>
			<tr>
				<td>CollegeName<font color="red">*</font></td>
				<td><% if(l!=null){%><%=HTMLUtility.getList("collegeId",
                    String.valueOf(bean.getCollegeId()), l)%>
						<%}%></td>
			</tr>
			

             <tr><td></td>
             <td colspan="2"><input type="submit" name="operation"
                        value="<%=StudentCtl.OP_SAVE%>">  <input type="submit" name="operation"
                        value="<%=StudentCtl.OP_CANCEL%>"></td>
             
             
             
             </tr>



		</table>


	</form>
<%@include file="Footer.jsp" %>
</body>
</html>