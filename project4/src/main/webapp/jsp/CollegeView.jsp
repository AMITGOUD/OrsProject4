<%@page import="in.co.sunrays.controller.CollegeCtl"%>
<%@page import="in.co.sunrays.util.DataUtility"%>
<%@page import="in.co.sunrays.util.ServletUtility"%>
<%@page errorPage="ErrorView.jsp"%>
<html>
<body><%@ include file="Header.jsp"%>
    <form action="CollegeCtl" method="POST">
        

        <jsp:useBean id="bean" class="in.co.sunrays.bean.CollegeBean"
            scope="request"></jsp:useBean>

        <%if(bean.getId()>0){ %>
            <h1 style="margin-left: 15.5em">Update College</h1><%}else{ %>
            <h1 style="margin-left: 15.5em">Add College</h1><%} %>

            <H2 align="center">
                <font color="green" > <%=ServletUtility.getSuccessMessage(request)%>
                </font>
            </H2>
             <H2 align="center">
                <font color="red"> <%=ServletUtility.getErrorMessage(request)%>
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

            <table style="margin-left: 27.5em">
                <tr>
                    <td width="13%">Name<font color="red">*</font></td>
                    <td width="15"><input type="text" name="name"
                        value="<%=DataUtility.getStringData(bean.getName())%>"></td><td width="60%"><font
                        color="red"> <%=ServletUtility.getErrorMessage("name", request)%></font></td>
                </tr>
                <tr>
                    <td>Address<font color="red">*</font></td>
                    <td><input type="text" name="address"
                        value="<%=DataUtility.getStringData(bean.getAddress())%>"></td><td><font
                        color="red"> <%=ServletUtility.getErrorMessage("address", request)%></font></td>
                </tr>
                <tr>
                    <td>State<font color="red">*</font></td>
                    <td><input type="text" name="state"
                        value="<%=DataUtility.getStringData(bean.getState())%>"></td><td><font
                        color="red"> <%=ServletUtility.getErrorMessage("state", request)%></font></td>
                </tr>
                <tr>
                    <td>City<font color="red">*</font></td>
                    <td><input type="text" name="city"
                        value="<%=DataUtility.getStringData(bean.getCity())%>"></td><td><font
                        color="red"> <%=ServletUtility.getErrorMessage("city", request)%></font></td>
                </tr>
                <tr>
                    <td>PhoneNo<font color="red">*</font></td>
                    <td><input type="text" name="phoneNo"
                        value="<%=DataUtility.getStringData(bean.getPhoneNo())%>"></td><td><font
                        color="red"> <%=ServletUtility.getErrorMessage("phoneNo", request)%></font></td>
                </tr>


                <tr>
                    <th></th>
                    <td colspan="2"><input type="submit" name="operation"
                        value="<%=CollegeCtl.OP_SAVE%>"> <%
    
 %>&emsp; <input type="submit" name="operation"
                        value="<%=CollegeCtl.OP_CANCEL%>"></td>
                </tr>
            </table>
    </form>
    
    <%@ include file="Footer.jsp"%>
</body>
</html>