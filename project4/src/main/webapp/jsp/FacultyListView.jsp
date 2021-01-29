<%@page import="in.co.sunrays.controller.FacultyListCtl"%>
<%@page import="in.co.sunrays.util.ServletUtility"%>
<%@page import="in.co.sunrays.bean.FacultyBean"%>
<%@page import="in.co.sunrays.model.FacultyModel"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<html>
<head>
<script src="<%=ORSView.APP_CONTEXT%>/js/select.js"></script>
</head>
<body>
<%FacultyBean bean=new FacultyBean();
FacultyModel model=new FacultyModel();

%>
					
				
	<%@include file="Header.jsp"%>
	<center>
		<h1>Faculty List</h1>

		<form action="<%=ORSView.FACULTY_LIST_CTL%>" method="post" name="list01">
			<table width="100%">
				<tr>
					<td align="center"><label> FirstName :</label> <input
						type="text" name="firstName"
						value="<%=ServletUtility.getParameter("firstName", request)%>">
						
						<label>CollegeName :</label> <input type="text" name="collegename"
						value="<%=ServletUtility.getParameter("collegename", request)%>">
						
						
						<input type="submit" name="operation"
						value="<%=FacultyListCtl.OP_SEARCH%>"></td>
				</tr>
			</table><font color="red"><%=ServletUtility.getErrorMessage(request)%></font>
			<br>
			<table border="1" width="100%" name="table">
			
				<tr>
				<th>S.No.</th>
				<th><input type="checkbox" name="Check_ctr" value="yes" onClick="checkAll(document.list01.ids, this)"><b>select All</b><dd></th>
				   
					
					
					<th>First Name.</th>
					<th>Last Name.</th>
					<th>Date Of joining.</th>
					<th>Mobil No.</th>
					<th>Email ID.</th>
					<th>Edit</th>
				</tr>
				
				<%
					int pageNo = ServletUtility.getPageNo(request);
					int pageSize = ServletUtility.getPageSize(request);
					int index = ((pageNo - 1) * pageSize) + 1;

					List list = ServletUtility.getList(request);
					Iterator<FacultyBean> it = list.iterator();

					while (it.hasNext()) {

						 bean = it.next();
				%>
				<tr>
					<td><%=index++%></td>
					
					<td><input type="checkbox" name="ids" value="<%=bean.getId()%>"></td>
					<td><%=bean.getFirstName()%></td>
					<td><%=bean.getLastName()%></td>
					<td><%=bean.getJoiningDate()%></td>
					<td><%=bean.getMobileNo()%></td>
					<td><%=bean.getLoginId()%></td>
					<td><a href="FacultyCtl?id=<%=bean.getId()%>">Edit</a></td>
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
						disabled="disabled" value="<%=FacultyListCtl.OP_PREVIOUS%>">
					</td><%
							} else {
						%> 
					<td align="left">
						<input type="submit" name="operation"
						value="<%=FacultyListCtl.OP_PREVIOUS%>">
						<%
							}
						%>
					</td>
					<td><input type="submit" name="operation" value="<%=FacultyListCtl.OP_DELETE%>"></td>
					<%
						if (list.size() < pageSize || model.nextPK() - 1 == bean.getId()) {
					%>
					<td align="right"><input type="submit" name="operation"
						disabled="disabled" value="<%=FacultyListCtl.OP_NEXT%>"></td><%}else { %>
					<td align="right">
						 <input align="right" type="submit" name="operation"
						value="<%=FacultyListCtl.OP_NEXT%>">
						<%
							}
						%>
					</td>
				</tr>
			</table>
			<input type="hidden" name="pageNo" value="<%=pageNo%>"><input
				type="hidden" name="pageSize" value="<%=pageSize%>">


		</form>
	</center>
</body>
</html>