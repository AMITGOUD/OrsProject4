<%@page import="in.co.sunrays.controller.ForgetPasswordCtl"%>
<%@page import="in.co.sunrays.util.DataUtility"%>
<%@page import="in.co.sunrays.util.ServletUtility"%>
<%@page errorPage="ErrorView.jsp"%>
<html>
<body> <%@ include file="Header.jsp"%>
    <form action="<%=ORSView.FORGET_PASSWORD_CTL%>" method="post">

       

        <jsp:useBean id="bean" class="in.co.sunrays.bean.UserBean"
            scope="request"></jsp:useBean>

        <center>
            <h1>Forgot your password?</h1>
            <input type="hidden" name="id" value="<%=bean.getId()%>">

            <table>
                 <lable>Submit your email address and we'll send you password.</lable><br><br>
                <label>Email Id :</label>&emsp;
                <input type="text" name="login" placeholder="Enter ID Here"
                    value="<%=ServletUtility.getParameter("login", request)%>">&emsp;
                <input type="submit" name="operation" value="<%=ForgetPasswordCtl.OP_GO%>"><br>
                <font color="red"> <%=ServletUtility.getErrorMessage("login", request)%></font>
            </table>
            
            <H2>
                <font color="green"> <%=ServletUtility.getSuccessMessage(request)%>
                </font>
            </H2>
            <H2>
                <font color="red"> <%=ServletUtility.getErrorMessage(request)%>
                </font>
            </H2>
    </form>
    </center>
    <%@ include file="Footer.jsp"%>
</body>
</html>