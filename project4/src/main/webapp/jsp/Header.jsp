<%@page import="in.co.sunrays.bean.RoleBean"%>
<%@page import="in.co.sunrays.controller.LoginCtl"%>
<%@page import="in.co.sunrays.bean.UserBean"%>
<%@page import="in.co.sunrays.controller.ORSView"%>
<%
	response.setIntHeader("Header.jsp", 30);
	UserBean userBean = (UserBean) session.getAttribute("user");

	boolean userLoggedIn = userBean != null;

	String welcomeMsg = "Hi, ";

	if (userLoggedIn) {
		String role = (String) session.getAttribute("role");
		welcomeMsg += userBean.getFirstName() + " (" + role + ")";
	} else {

		welcomeMsg += "Guest";
	}
%>
<html>
<body>
<table width="100%" border="0">
	<tr>
		<td width="90%"><a href="<%=ORSView.WELCOME_CTL%>">Welcome</b></a> |
			<%
			if (userLoggedIn) {
		%> <a href="<%=ORSView.LOGIN_CTL%>?operation=<%=LoginCtl.OP_LOG_OUT%>">Logout</b></a>

			<%
				} else {
			%> <a href="<%=ORSView.LOGIN_CTL%>">Login</b></a> <%
 	}
 %></td>

		<td rowspan="2">
			<h1 align="Right">
								<%-- <img src="<%=ORSView.APP_CONTEXT%>/image/customLogo.jpeg" width="318" height="90"> --%>
			</h1>
		</td>

	</tr>

	<tr>
		<td>
			<h3>
				<%=welcomeMsg%></h3>
		</td>
	</tr>
</table>
<table>
	<%
		if (userLoggedIn) {
	
			if (userBean.getRoleId() == RoleBean.STUDENT) {
		%>
	<tr>
		<td colspan="2"><a href="<%=ORSView.TIMETABLE_LIST_CTL%>">
				Timetable List|
		</a> <a href="<%=ORSView.GET_MARKSHEET_CTL%>">Get Marksheet</b>
		</a> | <a href="<%=ORSView.MARKSHEET_MERIT_LIST_CTL%>">Marksheet Merit
				List</b>
		</a> | <a href="<%=ORSView.MY_PROFILE_CTL%>">MyProfile</b></a> | <a
			href="<%=ORSView.CHANGE_PASSWORD_CTL%>">Change Password</b></a> | <a
			href="<%=ORSView.JAVA_DOC_VIEW%>" target="blank">Java Doc</b></a> | 
			<%}
			if (userBean.getRoleId() == RoleBean.ADMIN) {
		%> 
		| <a href="<%=ORSView.MY_PROFILE_CTL%>">MyProfile</b></a> | <a
			href="<%=ORSView.CHANGE_PASSWORD_CTL%>">Change Password</b></a> | <a
			href="<%=ORSView.JAVA_DOC_VIEW%>" target="blank">Java Doc</b></a>| 
		<a href="<%=ORSView.MARKSHEET_CTL%>">Add Marksheet</b></a> | <a
			href="<%=ORSView.MARKSHEET_LIST_CTL%>">Marksheet List</b></a> | <a
			href="<%=ORSView.USER_CTL%>">Add User</b></a> | <a
			href="<%=ORSView.USER_LIST_CTL%>">User List</b></a> | <a
			href="<%=ORSView.COLLEGE_CTL%>">Add College</b></a> | <a
			href="<%=ORSView.COLLEGE_LIST_CTL%>">College List</b></a> | <a
			href="<%=ORSView.STUDENT_CTL%>">Add Student</b></a> | <a
			href="<%=ORSView.STUDENT_LIST_CTL%>">Student List</b></a> | <a
			href="<%=ORSView.ROLE_CTL%>">Add Role</b></a> | <a
			href="<%=ORSView.ROLE_LIST_CTL%>">Role List</b></a>| <a
			href="<%=ORSView.TIMETABLE_CTL%>">Add timetable</b></a>| <a
			href="<%=ORSView.TIMETABLE_LIST_CTL%>"> Timetable List</b></a>| <a
			href="<%=ORSView.FACULTY_CTL%>">Add Faculty</b></a>| <a
			href="<%=ORSView.FACULTY_LIST_CTL%>">Faculty List</b></a>| <a
			href="<%=ORSView.SUBJECT_CTL%>">Add Subject</b></a> | | <a
			href="<%=ORSView.SUBJECT_LIST_CTL%>"> SubjectList</b></a> <%
 	}
 %></td>

	</tr>
	<%
		}
	%>
</table>
<hr>
</body>
</html>
