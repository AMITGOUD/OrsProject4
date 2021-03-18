<%@page import="in.co.sunrays.controller.MarksheetListCtl"%>
<%@page import="in.co.sunrays.controller.BaseCtl"%>
<%@page import="in.co.sunrays.util.ServletUtility"%>
<%@page import="in.co.sunrays.bean.MarksheetBean"%>
<%@page import="in.co.sunrays.model.MarksheetModel"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page errorPage="ErrorView.jsp"%>
<html>
<head>
<script src="<%=ORSView.APP_CONTEXT%>/js/select.js"></script>
</head>
<body>
<%MarksheetBean bean=new MarksheetBean();
MarksheetModel model=new MarksheetModel();

%>
    <%@include file="Header.jsp"%>
    
        <h1 align="center">Marksheet List</h1>
<h2 style="color:green"><%=ServletUtility.getSuccessMessage(request)%></h2>
        <form action="<%=ORSView.MARKSHEET_LIST_CTL%>" method="POST" name="list01">

            <table width="100%">
                <tr>
                    <td align="center"><label> Name :</label> <input type="text"
                        name="name"
                        value="<%=ServletUtility.getParameter("name", request)%>">
                        &emsp; <label>RollNo :</label> <input type="text" name="rollNo"
                        value="<%=ServletUtility.getParameter("rollNo", request)%>">&emsp;
                        <input type="submit" name="operation" value="<%=MarksheetListCtl.OP_SEARCH %>">&nbsp&nbsp<input type="reset" value="reset"></td>
                </tr>
            </table>
             
                    <input type=button value="Back" onCLick="history.back()"><h4 align="center"><font color="red"><%=ServletUtility.getErrorMessage(request)%></font></h4>
            <table border="1" width="100%" name="table">
                <tr style="background-color:gray;color: menu;">
                    <th><input type="checkbox" name="Check_ctr" value="yes" onClick="checkAll(document.list01.ids, this)"><b>select All</b><dd></th>
                    <th>S.no.</th>
                    <th>RollNo</th>
                    <th>Name</th>
                    <th>Physics</th>
                    <th>Chemistry</th>
                    <th>Maths</th>
                    <th>Edit</th>
                </tr>
               
                <%
                    int pageNo = ServletUtility.getPageNo(request);
                    int pageSize = ServletUtility.getPageSize(request);
                    int index = ((pageNo - 1) * pageSize) + 1;

                    List list = ServletUtility.getList(request);
                    Iterator<MarksheetBean> it = list.iterator();

                    while (it.hasNext()) {

                         bean = it.next();
                %>
                <tr style="text-align: center;">
                    <td><input type="checkbox" name="ids" value="<%=bean.getId()%>"></td>
                   <td><%=index++%></td>
                    <td><%=bean.getRollNo()%></td>
                    <td><%=bean.getName()%></td>
                    <td><%=bean.getPhysics()%></td>
                    <td><%=bean.getChemistry()%></td>
                    <td><%=bean.getMaths()%></td>
                    <td><a href="MarksheetCtl?id=<%=bean.getId()%>">Edit</a></td>
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
						disabled="disabled" value="<%=MarksheetListCtl.OP_PREVIOUS%>">
					</td><%
							} else {
						%> 
					<td align="left">
						<input type="submit" name="operation"
						value="<%=MarksheetListCtl.OP_PREVIOUS%>">
						<%
							}
						%>
					</td>
					<td align="center"><input type="submit" name="operation" value="<%=MarksheetListCtl.OP_DELETE%>">
					&emsp;&emsp;&emsp;&emsp;&emsp;
&emsp;&emsp;&emsp;&emsp;&emsp;
<input type="submit" name="operation"value="<%=MarksheetListCtl.OP_NEW%>">
					
					</td>
					<%
						if (list.size() < pageSize || model.nextPK() - 1 == bean.getId()) {
					%>
					<td align="right"><input type="submit" name="operation"
						disabled="disabled" value="<%=MarksheetListCtl.OP_NEXT%>"></td><%}else { %>
					<td align="right">
						 <input align="right" type="submit" name="operation"
						value="<%=MarksheetListCtl.OP_NEXT%>">
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