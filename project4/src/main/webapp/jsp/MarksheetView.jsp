<%@page import="in.co.sunrays.controller.MarksheetCtl"%>
<%@page import="java.util.List"%>
<%@page import="in.co.sunrays.util.HTMLUtility"%>
<%@page import="in.co.sunrays.util.DataUtility"%>
<%@page import="in.co.sunrays.util.ServletUtility"%>
<%@page errorPage="ErrorView.jsp"%>
<html>
<head>
 <style type="text/css">
table.fixed {table-layout:fixed; width:800px; align-self: center; margin-left: 26.5em}/*Setting the table width is important!*/

table.fixed td:nth-of-type(1) {width:80px}/*Setting the width of column 1.*/
table.fixed td:nth-of-type(2) {width:200px}/*Setting the width of column 2.*/
table.fixed td:nth-of-type(3) {width:3500px;}/*Setting the width of column 3*/


</style> 
</head>
<body>
<%@ include file="Header.jsp"%>
    <form action="<%=ORSView.MARKSHEET_CTL%>" method="post">
        

        <jsp:useBean id="bean" class="in.co.sunrays.bean.MarksheetBean"
            scope="request"></jsp:useBean>

        <%
            List l = (List) request.getAttribute("studentList");
        %>

        <center>
            <%if(bean.getId()>0){ %>
            <h1 align="center">Update Marksheet</h1><%}else{ %>
            <h1 align="center">Add marksheet</h1><%} %>

            <H2>
                <font color="red"> <%=ServletUtility.getErrorMessage(request)%>
                </font>
            </H2>
            <H2>
                <font color="green"> <%=ServletUtility.getSuccessMessage(request)%>
                </font>
            </H2>


            <input type="hidden" name="id" value="<%=bean.getId()%>">
            <input type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
            <input type="hidden" name="modifiedBy" value="<%=bean.getModifiedBy()%>">
            <input type="hidden" name="createdDatetime" value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
            <input type="hidden" name="modifiedDatetime" value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">
            

            <table class="fixed">
                <tr>
                    
                    <td>Rollno<font color="red">*</font></td>
                    <td><input type="text" name="rollNo"
                        value="<%=DataUtility.getStringData(bean.getRollNo())%>"
                        <%=(bean.getId() > 0) ? "readonly" : ""%>></td>
                        <td> 
                        <font color="red"><%=ServletUtility.getErrorMessage("rollNo", request)%> </font></td>
               
                </tr>
                <tr>
                    <td>Name<font color="red">*</font></td>
                    <td><% if(l!=null){%><%=HTMLUtility.getList("studentId",
                    String.valueOf(bean.getStudentId()), l)%><%} %></td><td></td>
                </tr>
                <tr>
                    <td>Physics<font color="red">*</font></td>
                    <td><input type="text" name="physics"
                        value="<%=DataUtility.getStringData(bean.getPhysics())%>"></td><td><font
                        color="red"> <%=ServletUtility.getErrorMessage("physics", request)%></font></td>
                </tr>
                <tr>
                    <td>Chemistry<font color="red">*</font></td>
                    <td><input type="text" name="chemistry"
                        value="<%=DataUtility.getStringData(bean.getChemistry())%>"></td><td><font
                        color="red"> <%=ServletUtility.getErrorMessage("chemistry", request)%></font></td>
                </tr>
                <tr>
                    <td>Maths<font color="red">*</font></td>
                    <td><input type="text" name="maths"
                        value="<%=DataUtility.getStringData(bean.getMaths())%>"></td><td><font
                        color="red"> <%=ServletUtility.getErrorMessage("maths", request)%></font></td>

                </tr>
                <tr>
                    <th></th>
                    <td colspan="2"><input type="submit" name="operation"
                        value="<%=MarksheetCtl.OP_SAVE%>">  <input type="submit" name="operation"
                        value="<%=MarksheetCtl.OP_CANCEL%>"></td>
                </tr>
            </table>
        </form>
        </center>
        
    </body>
    <%@ include file="Footer.jsp"%>
</html>