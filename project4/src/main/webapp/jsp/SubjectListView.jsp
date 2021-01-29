<%@page import="in.co.sunrays.controller.SubjectListCtl"%>
<%@page import="in.co.sunrays.controller.BaseCtl"%>
<%@page import="in.co.sunrays.util.ServletUtility"%>
<%@page import="in.co.sunrays.bean.SubjectBean"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page import="in.co.sunrays.model.SubjectModel"%>
<html>
<head>
<script src="<%=ORSView.APP_CONTEXT%>/js/select.js"></script>
</head>
<body>
	<%
		SubjectBean bean = new SubjectBean();

		SubjectModel model = new SubjectModel();
	%>
	<%@include file="Header.jsp"%>
	<center>
		<h1>Subject list</h1>

		<form action="<%=ORSView.SUBJECT_LIST_CTL%>" method="POST"
			name="list01">

					<font color="red"><%=ServletUtility.getErrorMessage(request)%></font>
				
			<table width="100%">
				<tr>
					<td align="center"><label>SubjectName :</label> <input
						type="text" name="subjectName"
						value="<%=ServletUtility.getParameter("subjectname", request)%>">
						&emsp; <label>SubjectCode :</label> <input type="text"
						name="subjectCode"
						value="<%=ServletUtility.getParameter("rollNo", request)%>">&emsp;
						<input type="submit" name="operation"
						value="<%=SubjectListCtl.OP_SEARCH%>"></td>
				</tr>
			</table>
			<br>
			<table border="1" width="100%" name="table">
				<tr>
					<th>S.No.</th>
					<th><input type="checkbox" name="Check_ctr" value="yes"
						onClick="checkAll(document.list01.ids, this)"><b>select
							All</b>
					<dd></th>

					<th>CourceName</th>
					<th>Subject name</th>
					<th>Subject code</th>

					<th>description</th>

				</tr>
				
				<%
					int pageNo = ServletUtility.getPageNo(request);
					int pageSize = ServletUtility.getPageSize(request);
					int index = ((pageNo - 1) * pageSize) + 1;

					List list = ServletUtility.getList(request);
					Iterator<SubjectBean> it = list.iterator();

					while (it.hasNext()) {

						bean = it.next();
				%>
				<tr>



					<td><%=index++%></td>
					<td><input type="checkbox" name="ids"
						value="<%=bean.getId()%>"></td>

					<td><%=bean.getCourceName()%></td>
					<td><%=bean.getSubjectCode()%></td>
					<td><%=bean.getSubjectName()%></td>
					<td><%=bean.getDescription()%></td>

					<td><a href="SubjectCtl?id=<%=bean.getId()%>">Edit</a></td>
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
						%><input type="submit" name="operation" disabled="disabled"
						value="<%=SubjectListCtl.OP_PREVIOUS%>">
					</td>
					<%
						} else {
					%>
					<td align="left"><input type="submit" name="operation"
						value="<%=SubjectListCtl.OP_PREVIOUS%>"> <%
 	}
 %></td>
					<td><input type="submit" name="operation"
						value="<%=SubjectListCtl.OP_DELETE%>"></td>
					<%
						if (list.size() < pageSize || model.nextPK() - 1 == bean.getId()) {
					%>
					<td align="right"><input type="submit" name="operation"
						disabled="disabled" value="<%=SubjectListCtl.OP_NEXT%>"></td>
					<%
						} else {
					%>
					<td align="right"><input align="right" type="submit"
						name="operation" value="<%=SubjectListCtl.OP_NEXT%>"> <%
 	}
 %></td>
				</tr>
			</table>
			<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
				type="hidden" name="pageSize" value="<%=pageSize%>">
		</form>
	</center>
	<%@include file="Footer.jsp"%>
</body>
</html>