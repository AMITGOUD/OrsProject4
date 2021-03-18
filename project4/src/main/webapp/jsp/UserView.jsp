<%@page import="in.co.sunrays.controller.UserCtl"%>
<%@page import="java.util.List"%>
<%@page import="in.co.sunrays.util.HTMLUtility"%>
<%@page import="java.util.HashMap"%>
<%@page import="in.co.sunrays.util.DataUtility"%>
<%@page errorPage="ErrorView.jsp"%>
<%@page import="in.co.sunrays.util.ServletUtility"%>
<html>
<head>
 <style type="text/css">
table.fixed {table-layout:fixed; width:520px; align-self: center; margin-left: 26.5em}/*Setting the table width is important!*/

table.fixed td:nth-of-type(1) {width:150px}/*Setting the width of column 1.*/
table.fixed td:nth-of-type(2) {width:170px}/*Setting the width of column 2.*/
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
<body><%@ include file="Header.jsp"%>
	<form action="<%=ORSView.USER_CTL%>" method="post">
		
		<script type="text/javascript" src="../js/calendar.js"></script>
		<jsp:useBean id="bean" class="in.co.sunrays.bean.UserBean"
			scope="request"></jsp:useBean>
			<%if(bean.getId()>0){ %><h1 style="margin-left: 16.5em">Update User</h1><%} else {%>
			<h1 style="margin-left: 16.5em">Add User</h1><%}%>

		<%
			List l = (List) request.getAttribute("roleList");
		%>

		

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
					<td width="17%">First Name<font color="red">*</font></td>
					<td width="21%"><input type="text" name="firstName"
						value="<%=DataUtility.getStringData(bean.getFirstName())%>"></td><td width="62%"><font
						color="red"> <%=ServletUtility.getErrorMessage("firstName", request)%></font></td>
				</tr>
				<tr>
					<td>Last Name<font color="red">*</font></td>
					<td><input type="text" name="lastName"
						value="<%=DataUtility.getStringData(bean.getLastName())%>"></td><td width=""><font
						color="red"> <%=ServletUtility.getErrorMessage("lastName", request)%></font></td>
				</tr>
				<tr>
					<td>LoginId<font color="red">*</font></td>
					<td><input type="text" name="login"
						value="<%=DataUtility.getStringData(bean.getLogin())%>"
						<%=(bean.getId() > 0) ? "readonly" : ""%>></td><td width=""><font
						color="red"> <%=ServletUtility.getErrorMessage("login", request)%></font></td>
				</tr>
				<tr>
					<td>Password<font color="red">*</font></td>
					<td><input type="password" name="password"
						value="<%=DataUtility.getStringData(bean.getPassword())%>"></td><td width=""><font
						color="red"> <%=ServletUtility.getErrorMessage("password", request)%></font></td>
				</tr>
				<tr>
					<td>Confirm Password<font color="red">*</font></td>
					<td><input type="password" name="confirmPassword"
						value="<%=DataUtility.getStringData(bean.getPassword())%>"></td><td width=""><font
						color="red"> <%=ServletUtility.getErrorMessage("confirmPassword", request)%></font></td>
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
				<tr><td>Role : </td><td><%=HTMLUtility.getList("roleId", String.valueOf(bean.getRoleId()), l)%></td></tr>
				<tr>
					<td>Date Of Birth </td>
					<td><input type="text" name="dob" id="dob1" placeholder="(mm/dd/yyyy)"
						value="<%=DataUtility.getDateString(bean.getDob())%>"> </td><td width=""><font
						color="red"> <%=ServletUtility.getErrorMessage("dob", request)%></font></td>
				</tr>
				<tr>
					<th></th>
					<td colspan="2"><input type="submit" name="operation"
						value="<%=UserCtl.OP_SAVE%>">&emsp; <input type="submit"
						name="operation" value="<%=UserCtl.OP_CANCEL%>"></td>
				</tr>
			</table>
	</form>
	
	<%@ include file="Footer.jsp"%>
</body>
</html>