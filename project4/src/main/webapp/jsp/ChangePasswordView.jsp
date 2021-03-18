<%@page import="in.co.sunrays.controller.ChangePasswordCtl"%>
<%@page import="in.co.sunrays.util.DataUtility"%>
<%@page import="in.co.sunrays.util.ServletUtility"%>
<%@page errorPage="ErrorView.jsp"%>
<html>
<body><%@ include file="Header.jsp"%>
    <form action="<%=ORSView.CHANGE_PASSWORD_CTL%>" method="post">
        
        

        <jsp:useBean id="bean" class="in.co.sunrays.bean.UserBean"
            scope="request"></jsp:useBean>

        <center>
            <h1>Change Password</h1>


            <H2>
                <font color="red"> <%=ServletUtility.getErrorMessage(request)%>
                </font>
            </H2>

            <input type="hidden" name="id" value="<%=bean.getId()%>">
            <input type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
            <input type="hidden" name="modifiedBy" value="<%=bean.getModifiedBy()%>">
            <input type="hidden" name="createdDatetime" value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
            <input type="hidden" name="modifiedDatetime" value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">

            <table>



                <tr>
                    <td width="30%">Old Password<font color="red">*</font></td>
                    <td width=""><input type="password" name="oldPassword" 
                        value=<%=DataUtility
                    .getString(request.getParameter("oldPassword") == null ? ""
                            : DataUtility.getString(request
                                    .getParameter("oldPassword")))%>></td><td width="36%"><font
                        color="red"> <%=ServletUtility.getErrorMessage("oldPassword", request)%></font></td>
                </tr>

                <tr>
                    <td>New Password<font color="red">*</font></td>
                    <td><input type="password" name="newPassword" 
                        value=<%=DataUtility
                    .getString(request.getParameter("newPassword") == null ? ""
                            : DataUtility.getString(request
                                    .getParameter("newPassword")))%>></td><td><font
                        color="red"> <%=ServletUtility.getErrorMessage("newPassword", request)%></font></td>
                </tr>

                <tr>
                    <td>Confirm Password<font color="red">*</font></td>
                    <td><input type="password" name="confirmPassword"
                        value=<%=DataUtility.getString(request
                    .getParameter("confirmPassword") == null ? "" : DataUtility
                    .getString(request.getParameter("confirmPassword")))%>></td><td><font
                        color="red"> <%=ServletUtility
                    .getErrorMessage("confirmPassword", request)%></font></td>
                </tr>

                <tr>
                    <th></th>
                    <td colspan="" style="margin-left: 15"><input type="submit" name="operation" style="width: 110"
                        value="<%=ChangePasswordCtl.OP_CHANGE_MY_PROFILE %>">  <input type="submit"
                        name="operation" value="<%= ChangePasswordCtl.OP_SAVE%>"> </td>
                </tr>

            </table>
            <H3>
                <font color="green"> <%=ServletUtility.getSuccessMessage(request)%>
                </font>
            </H3>
    </form>
    </center>
    <%@ include file="Footer.jsp"%>
</body>
</html>