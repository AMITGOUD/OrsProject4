<%@page import="in.co.sunrays.model.CollegeModel"%>
<%@page import="in.co.sunrays.controller.CollegeListCtl"%>
<%@page import="in.co.sunrays.util.ServletUtility"%>
<%@page import="in.co.sunrays.bean.CollegeBean"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page errorPage="ErrorView.jsp"%>

<html>
<head>
<script src="<%=ORSView.APP_CONTEXT%>/js/select.js"></script>
</head>
<body>
	<%@include file="Header.jsp"%>
	<%
		CollegeModel model = new CollegeModel();
		CollegeBean bean = new CollegeBean();
	%>
	<form action=""></form>
	
		<h1 align="center">College List</h1>

		<form action="<%=ORSView.COLLEGE_LIST_CTL%>" method="POST" name="list01">

			<table width="100%">
				<tr>
					<td align="center"><label> Name :</label> <input type="text"
						name="name"
						value="<%=ServletUtility.getParameter("name", request)%>">
						<label>City :</label> <input type="text" name="city"
						value="<%=ServletUtility.getParameter("city", request)%>">
						<input type="submit" name="operation"
						value="<%=CollegeListCtl.OP_SEARCH%>">
						<input type="reset" value="reset">
						</td>
				</tr>
			</table>
			<input type=button value="Back" onCLick="history.back()">
			
			
				<h4 align="center">	<font color="red" ><%=ServletUtility.getErrorMessage(request)%></font></h4>
				
			<table border="1" width="100%" name="table">
				<tr style="background-color:gray;color: menu;">
					
					
					<th><input type="checkbox" name="Check_ctr" value="yes" onClick="checkAll(document.list01.ids, this)"><b>select All</b><dd></th>
					<th>S.No.</th>
					<th>Name.</th>
					<th>Address.</th>
					<th>State.</th>
					<th>City.</th>
					<th>PhoneNo.</th>
					<th>Edit</th>
				</tr>
				
				<%
					int pageNo = ServletUtility.getPageNo(request);
					int pageSize = ServletUtility.getPageSize(request);
					int index = ((pageNo - 1) * pageSize) + 1;

					List list = ServletUtility.getList(request);
					Iterator<CollegeBean> it = list.iterator();

					while (it.hasNext()) {

						bean = it.next();
				%>
				<tr style="text-align: center;">
				<td><input type="checkbox" name="ids"
						value="<%=bean.getId()%>"></td>
				<td><%=index++%></td>
					
					

					<td><%=bean.getName()%></td>
					<td><%=bean.getAddress()%></td>
					<td><%=bean.getState()%></td>
					<td><%=bean.getCity()%></td>
					<td><%=bean.getPhoneNo()%></td>
					<td><a href="CollegeCtl?id=<%=bean.getId()%>">Edit</a></td>
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
						disabled="disabled" value="<%=CollegeListCtl.OP_PREVIOUS%>">
					</td><%
							} else {
						%> 
					<td align="left">
						<input type="submit" name="operation"
						value="<%=CollegeListCtl.OP_PREVIOUS%>">
						<%
							}
						%>
					</td>
					<td align="center"><input type="submit" name="operation" value="<%=CollegeListCtl.OP_DELETE%>">
					&emsp;&emsp;&emsp;&emsp;&emsp;
&emsp;&emsp;&emsp;&emsp;&emsp;
<input type="submit" name="operation"value="<%=CollegeListCtl.OP_NEW%>"></td>
					<%
						if (list.size() < pageSize || model.nextPK() - 1 == bean.getId()) {
					%>
					<td align="right"><input type="submit" name="operation"
						disabled="disabled" value="<%=CollegeListCtl.OP_NEXT%>"></td><%}else { %>
					<td align="right">
						 <input align="right" type="submit" name="operation"
						value="<%=CollegeListCtl.OP_NEXT%>">
						<%
							}
						%>
					</td>
				</tr>
			</table>
			<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
				type="hidden" name="pageSize" value="<%=pageSize%>">
		</form>
	
	<%@include file="Footer.jsp"%>
</body>
</html>