<%@page import="in.co.sunrays.controller.UserListCtl"%>
<%@page import="in.co.sunrays.util.ServletUtility"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page import="in.co.sunrays.model.*"%>
<%@page errorPage="ErrorView.jsp"%>

<html>
<head>
<script src="<%=ORSView.APP_CONTEXT%>/js/select.js"></script>
</head>
<body>
<%
		UserModel model = new UserModel();
		UserBean bean = new UserBean();
	%>
    <%@include file="Header.jsp"%>

    
        <h1 align="center">User List</h1>

        <form action="<%=ORSView.USER_LIST_CTL%>" method="post" name="list01">

            <table width="100%">
                <tr>
                    <td align="center"><label>FirstName :</label> <input
                        type="text" name="firstName"
                        value="<%=ServletUtility.getParameter("firstName", request)%>">
                        &emsp; <label>LoginId:</label> <input type="text" name="login"
                        value="<%=ServletUtility.getParameter("login", request)%>">
                        &emsp; <input type="submit" name="operation" value="<%=UserListCtl.OP_SEARCH %>">
                    </td>
                </tr>
            </table>
            <input type=button value="Back" onCLick="history.back()"><h4 align="center"><font color="red"><%=ServletUtility.getErrorMessage(request)%></font></h4>

            <table border="1" width="100%" name="table">
                <tr style="background-color:gray;color: menu;"> 
                    <th><input type="checkbox" name="Check_ctr" value="yes" onClick="checkAll(document.list01.ids, this)"><b>Select All</b><dd></th>
                    <th>S.no.</th>
                    <th>FirstName</th>
                    <th>LastName</th>
                    <th>LoginId</th>
                    <th>Gender</th>
                    <th>DOB</th>
                    <th>Edit</th>
                </tr>

                
                <%
                    int pageNo = ServletUtility.getPageNo(request);
                    int pageSize = ServletUtility.getPageSize(request);
                    int index = ((pageNo - 1) * pageSize) + 1;

                    List list = ServletUtility.getList(request);
                    int k=list.size();
                    Iterator<UserBean> it = list.iterator();
                    
                    while (it.hasNext()) {
                         bean = it.next();
                %>
                <tr style="text-align: center;">
                   
                    <td><input type="checkbox" name="ids" value="<%=bean.getId()%>"></td>
                     <td><%=index++%></td>
                    <td><%=bean.getFirstName()%></td>
                    <td><%=bean.getLastName()%></td>
                    <td><%=bean.getLogin()%></td>
                    <td><%=bean.getGender()%></td>
                    <td><%=bean.getDob()%></td>
                    <td><a href="UserCtl?id=<%=bean.getId()%>">Edit</a></td>
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
						disabled="disabled" value="<%=UserListCtl.OP_PREVIOUS%>">
					</td><%
							} else {
						%> 
					<td align="left">
						<input type="submit" name="operation"
						value="<%=UserListCtl.OP_PREVIOUS%>">
						<%
							}
						%>
					</td>
					<td align="center"><input type="submit" name="operation" value="<%=UserListCtl.OP_DELETE%>">
					&emsp;&emsp;&emsp;&emsp;&emsp;
&emsp;&emsp;&emsp;&emsp;&emsp;
<input type="submit" name="operation"value="<%=UserListCtl.OP_NEW%>">
					
					
					</td>
					<%
						if (list.size() < pageSize || model.nextPK() - 1 == bean.getId()) {
					%>
					<td align="right"><input type="submit" name="operation"
						disabled="disabled" value="<%=UserListCtl.OP_NEXT%>"></td><%}else { %>
					<td align="right">
						 <input align="right" type="submit" name="operation"
						value="<%=UserListCtl.OP_NEXT%>">
						<%
							}
						%>
					</td>
				</tr>
			</table>
            <input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
                type="hidden" name="pageSize" value="<%=pageSize%>">
        </form>
    </center>
    <%@include file="Footer.jsp"%>
</body>
</html>