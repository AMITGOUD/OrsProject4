<%@page import="in.co.sunrays.controller.MyProfileCtl"%>
<%@page import="in.co.sunrays.util.HTMLUtility"%>
<%@page import="java.util.HashMap"%>
<%@page import="in.co.sunrays.util.DataUtility"%>
<%@page import="in.co.sunrays.util.ServletUtility"%>
<html>
<head>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

<script>
	$(function() {
		$("#dob1").datepicker({
			changeMonth : true,
			changeYear : true,
			yearRange:'1980:2001',
			dateFormat: 'mm/dd/yy'
		});
	});
</script>
<style type="text/css">
table.fixed {table-layout:fixed; width:100%; align-self: center; margin-left: 25.5em}/*Setting the table width is important!*/

table.fixed td:nth-of-type(1) {width:10%}/*Setting the width of column 1.*/
table.fixed td:nth-of-type(2) {width:}/*Setting the width of column 2.*/
table.fixed td:nth-of-type(3) {width:30%;}/*Setting the width of column 3


</style>
</head>
<body>
<%@ include file="Header.jsp"%>
<form action="<%=ORSView.MY_PROFILE_CTL%>" method="post">

        
        <script type="text/javascript" src="../js/calendar.js"></script>
        <jsp:useBean id="bean" class="in.co.sunrays.bean.UserBean"
            scope="request"></jsp:useBean>

      
            <h1 align="center">My Profile</h1>
            
            <H2 align="center">
             
                <font color="green"> <%=ServletUtility.getSuccessMessage(request)%>
                </font>
            
                <font color="red" > <%=ServletUtility.getErrorMessage(request)%>
                </font>
            </H2>
            <input type="hidden" name="id" value="<%=bean.getId()%>">
            <input type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
            <input type="hidden" name="modifiedBy" value="<%=bean.getModifiedBy()%>">
            <input type="hidden" name="createdDatetime" value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
            <input type="hidden" name="modifiedDatetime" value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">
            

            <table class="fixed" align="center">
                <tr>
                    <td  >LoginId*</td>
                    <td width=""><input type="text" name="login" 
                        value="<%=DataUtility.getStringData(bean.getLogin())%>"readonly="readonly"></td><td width="30%"><font
                        color="red"> <%=ServletUtility.getErrorMessage("login", request)%></font></td>
                </tr>

                <tr>
                    <td>First Name*</td>
                    <td><input type="text" name="firstName"
                        value="<%=DataUtility.getStringData(bean.getFirstName())%>"><font
                        color="red"> <%=ServletUtility.getErrorMessage("firstName", request)%></font></td>
                </tr>
                <tr>
                    <td>Last Name*</td>
                    <td><input type="text" name="lastName"
                        value="<%=DataUtility.getStringData(bean.getLastName())%>"><font
                        color="red"> <%=ServletUtility.getErrorMessage("lastName", request)%></font></td>
                </tr>
                <tr>
                    <td>Gender</td>
                    <td>
                        <%
                            HashMap map = new HashMap();
                            map.put("M", "Male");
                            map.put("F", "Female");

                            String htmlList = HTMLUtility.getList("gender", bean.getGender(),
                                    map);
                        %> <%=htmlList%>
                    </td>
                </tr>
                <tr>
                    <td>Mobile No*</td>
                    <td><input type="text" name="mobileNo"
                        value="<%=DataUtility.getStringData(bean.getMobileNo())%>"><font
                        color="red"> <%=ServletUtility.getErrorMessage("mobileNo", request)%></font></td>
                </tr>

                <tr>
                    <td>Date Of Birth </td>
                    <td><input type="text" name="dob" readonly="readonly" id="dob1"
                        value="<%=DataUtility.getDateString(bean.getDob())%>">
                   
                    </a><font
                        color="red"> <%=ServletUtility.getErrorMessage("dob", request)%></font></td>
                </tr>
                
           
                
                <tr>
                    <th></th>
                    <td colspan="2"><input type="submit" name="operation"
                        value="<%=MyProfileCtl.OP_CHANGE_MY_PASSWORD %>"> &nbsp; <input type="submit"
                        name="operation" value="<%=MyProfileCtl.OP_SAVE %>"> &nbsp;</td>
                </tr>
            </table>
    </form>
   
    <%@ include file="Footer.jsp"%>
</body>
</html>