<%@page import="in.co.sunrays.controller.LoginCtl"%>
<%@page import="in.co.sunrays.util.DataUtility"%>
<%@page import="in.co.sunrays.util.ServletUtility"%>
<html>
<body>
 <%@ include file="Header.jsp"%>
	<form action="<%=ORSView.LOGIN_CTL%>" method="post">
		
<h1 style="margin-left: 16.5em">Login</h1>
		<jsp:useBean id="bean" class="in.co.sunrays.bean.UserBean"
			scope="request"></jsp:useBean>
		
		<center>


			<H2>
				<font color="green"> <%=ServletUtility.getSuccessMessage(request)%></font>
						<font color="red"><%=ServletUtility.getErrorMessage(request)%></font>
				


				<font color="green"> <%
 	String s = (String) request.getAttribute("message");
 	if (s != null) {
 %><%=s%> <%
 	}
 %>

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
			<%
				String uri = (String) request.getAttribute("uri");
			%>
            
			<table  align="center">
                
				<tr>
					<td width=""><label>LoginId*</label></td>
					<td width="" ><input type="text" name="login" size=30 
						value="<%=DataUtility.getStringData(bean.getLogin())%>"></td>
					<td width="33%"><font color="red"> <%=ServletUtility.getErrorMessage("login", request)%></font></td>
				</tr>
				
				<tr>
					<td><label>Password*</label></td>
					<td><input type="password" name="password" size=30
						value="<%=DataUtility.getStringData(bean.getPassword())%>"></td>
					<td><font color="red"> <%=ServletUtility.getErrorMessage("password", request)%></font></td>
				</tr>
				<tr>
					<th></th>
					<td colspan="2"><input type="submit" name="operation"
						value="<%=LoginCtl.OP_SIGN_IN%>"> &nbsp; <input
						type="submit" name="operation" value="<%=LoginCtl.OP_SIGN_UP%>">
						&nbsp;</td>
				</tr>
				<tr>
					<th></th>
					<td><a href="<%=ORSView.FORGET_PASSWORD_CTL%>"><b>Forget
								my password</b></a>&nbsp;</td>
				</tr>
			</table>
			<input type="hidden" name="uri" value="<%=uri%>">
	</form>
	</center>
	<font color="black"><%@ include file="Footer.jsp"%></font>
</body>
</html>