R<%@page import="in.co.sunrays.controller.RoleCtl"%>
<%@page import="in.co.sunrays.controller.BaseCtl"%>
<%@page import="in.co.sunrays.util.DataUtility"%>
<%@page import="in.co.sunrays.util.ServletUtility"%>
<%@page errorPage="ErrorView.jsp"%>
<html>
<head>
<style type="text/css">
table.fixed {table-layout:fixed; width:520px; align-self: center; margin-left: 26.5em}/*Setting the table width is important!*/

table.fixed td:nth-of-type(1) {width:105px}/*Setting the width of column 1.*/
table.fixed td:nth-of-type(2) {width:160px}/*Setting the width of column 2.*/
table.fixed td:nth-of-type(3) {width:200px;}/*Setting the width of column 3*/


</style> 
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body><%@ include file="Header.jsp"%>
    <form action="<%=ORSView.ROLE_CTL%>" method="post">
        

        <jsp:useBean id="bean" class="in.co.sunrays.bean.RoleBean"
            scope="request"></jsp:useBean>

        
            <%if(bean.getId()>0){ %>
            <h1 style="margin-left: 15.5em">Update Role</h1><%}else{ %>
            <h1 style="margin-left: 15.5em">Add Role</h1><%} %>

            <H2>
                <font color="green"> <%=ServletUtility.getSuccessMessage(request)%>
                </font>
            </H2>
            <H2>
                <font color="red"> <%=ServletUtility.getErrorMessage(request)%>
                </font>
            </H2>

            <input type="hidden" name="id" value="<%=bean.getId()%>">
            <input type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
            <input type="hidden" name="modifiedBy" value="<%=bean.getModifiedBy()%>">
            <input type="hidden" name="createdDatetime" value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
            <input type="hidden" name="modifiedDatetime" value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">
            

            <table class="fixed">
                <tr>
                    <td>Name<font color="red">*</font></td>
                    <td><input type="text" name="name"
                        value="<%=DataUtility.getStringData(bean.getName())%>"></td><td><font
                        color="red"> <%=ServletUtility.getErrorMessage("name", request)%></font></td>
                </tr>
                <tr>
                    <td>Description<font color="red">*</font></td>
                    <td><input type="text" name="description"
                        value="<%=DataUtility.getStringData(bean.getDescription())%>"></td><td><font
                        color="red"> <%=ServletUtility.getErrorMessage("description", request)%></font></td>
                </tr>
               <tr><td></td>
             <td colspan="2"><input type="submit" name="operation"
                        value="<%=RoleCtl.OP_SAVE%>">  <input type="submit" name="operation"
                        value="<%=RoleCtl.OP_CANCEL%>"></td>
             
             
             
             </tr>
            </table>
    </form>
   
    <%@ include file="Footer.jsp"%>
</body>
</html>