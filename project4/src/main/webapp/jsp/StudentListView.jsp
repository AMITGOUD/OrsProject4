<%@page import="in.co.sunrays.controller.StudentListCtl"%>
<%@page import="in.co.sunrays.util.ServletUtility"%>
<%@page import="in.co.sunrays.bean.StudentBean"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page import="in.co.sunrays.model.StudentModel"%>
<%@page errorPage="ErrorView.jsp"%>
<html>
<head>
<script src="<%=ORSView.APP_CONTEXT%>/js/select.js"></script>
</head>
<body>
<%StudentBean bean=new StudentBean();

StudentModel model=new StudentModel();
%>
    <%@include file="Header.jsp"%>
    
        <h1 align="center">Student List</h1>

        <form action="<%=ORSView.STUDENT_LIST_CTL%>" method="post" name="list01">
            <table width="100%">
                <tr>
                    <td align="center"><label> FirstName :</label> <input
                        type="text" name="firstName"
                        value="<%=ServletUtility.getParameter("firstName", request)%>">
                        &nbsp<label>LastName :</label> <input type="text" name="lastName"
                        value="<%=ServletUtility.getParameter("lastName", request)%>">&nbsp<label>Email_Id
                            :</label> <input type="text" name="email"
                        value="<%=ServletUtility.getParameter("email", request)%>">
                        <input type="submit" name="operation" value="<%=StudentListCtl.OP_SEARCH %>">&nbsp&nbsp<input type="reset" value="reset"></td>
                </tr>
            </table>
            <input type=button value="Back" onCLick="history.back()"><h4 align="center"><font color="red"><%=ServletUtility.getErrorMessage(request)%></font></h4>
            <table border="1" width="100%" name="table">
                <tr style="background-color:gray;color: menu;">
                    
                    <th><input type="checkbox" name="Check_ctr" value="yes" onClick="checkAll(document.list01.ids, this)"><b>select All</b><dd></th>
                    <th>S.No.</th>
                    <th>First Name.</th>
                    <th>Last Name.</th>
                    <th>Date Of Birth.</th>
                    <th>Mobil No.</th>
                    <th>Email ID.</th>
                    <th>Edit</th>
                </tr>
                
                    
                
                <%
                    int pageNo = ServletUtility.getPageNo(request);
                    int pageSize = ServletUtility.getPageSize(request);
                    int index = ((pageNo - 1) * pageSize) + 1;

                    List list = ServletUtility.getList(request);
                    Iterator<StudentBean> it = list.iterator();

                    while (it.hasNext()) {

                         bean = it.next();
                %>
                <tr style="text-align: center;"><td><input type="checkbox" name="ids"
						value="<%=bean.getId()%>"></td>
                    <td><%=index++%></td>
                     
                    <td><%=bean.getFirstName()%></td>
                    <td><%=bean.getLastName()%></td>
                    <td><%=bean.getDob()%></td>
                    <td><%=bean.getMobileNo()%></td>
                    <td><%=bean.getEmail()%></td>
                    <td><a href="StudentCtl?id=<%=bean.getId()%>">Edit</a></td>
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
						disabled="disabled" value="<%=StudentListCtl.OP_PREVIOUS%>">
					</td><%
							} else {
						%> 
					<td align="left">
						<input type="submit" name="operation"
						value="<%=StudentListCtl.OP_PREVIOUS%>">
						<%
							}
						%>
					</td>
					<td align="center"><input type="submit" name="operation" value="<%=StudentListCtl.OP_DELETE%>">
					
					&emsp;&emsp;&emsp;&emsp;&emsp;
&emsp;&emsp;&emsp;&emsp;&emsp;
<input type="submit" name="operation"value="<%=StudentListCtl.OP_NEW%>">
					
					</td>
					<%
						if (list.size() < pageSize || model.nextPK() - 1 == bean.getId()) {
					%>
					<td align="right"><input type="submit" name="operation"
						disabled="disabled" value="<%=StudentListCtl.OP_NEXT%>"></td><%}else { %>
					<td align="right">
						 <input align="right" type="submit" name="operation"
						value="<%=StudentListCtl.OP_NEXT%>">
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
    
</body>
</html>