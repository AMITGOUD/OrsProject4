<%@page import="in.co.sunrays.model.TimeTableModel"%>
<%@page import="in.co.sunrays.controller.TimeTableListCtl"%>
<%@page import="in.co.sunrays.controller.TimeTableCtl"%>
<%@page import="in.co.sunrays.util.ServletUtility"%>
<%@page import="in.co.sunrays.bean.TimeTableBean"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page errorPage="ErrorView.jsp"%>
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
			yearRange:'1980:2021',
			dateFormat: 'mm/dd/yy'
		});
	});
</script>
<script src="<%=ORSView.APP_CONTEXT%>/js/select.js"></script>
</head>
<body>
    <%@include file="Header.jsp"%>
    <%TimeTableBean bean=new TimeTableBean(); 
    TimeTableModel model=new TimeTableModel();
    %>
    
        <h1 align="center">TimeTable List</h1>

        <form action="<%=ORSView.TIMETABLE_LIST_CTL%>" method="post" name="list01">
            <table width="100%">
                <tr>
                    <td align="center"><label> Subjectname :</label> <input
                        type="text" name="subjectname"
                        value="<%=ServletUtility.getParameter("subjectname", request)%>">
                        <label>Courcename :</label> <input type="text" name="courcename"
                        value="<%=ServletUtility.getParameter("courcename", request)%>">
                        <label>ExamDate :</label> <input type="text" name="exameDate" id="dob1" readonly="readonly"
                        value="<%=ServletUtility.getParameter("exameDate", request)%>">
                        <input type="submit" name="operation"  value="<%=TimeTableListCtl.OP_SEARCH %>">
&nbsp&nbsp<input type="reset" value="reset"></td>
                </tr>
            </table>
            
                 <input type=button value="Back" onCLick="history.back()">   <h4 align="center"><font color="red"><%=ServletUtility.getErrorMessage(request)%></font></h4>
               
            
            <table border="1" width="100%" name="table">
                <tr style="background-color:gray;color: menu;">
                     
                    
                    <th><input type="checkbox" name="Check_ctr" value="yes" onClick="checkAll(document.list01.ids, this)"><b>Select All</b><dd></th>
                   <th>S.No.</th>
                    <th>courceName</th>
                    <th>Subject Name.</th>
                    <th>Exam date.</th>
                    <th>Exam Time.</th>
                    
                    <th>Edit</th>
                </tr>
                
                <%
                    int pageNo = ServletUtility.getPageNo(request);
                    int pageSize = ServletUtility.getPageSize(request);
                    int index = ((pageNo - 1) * pageSize) + 1;

                    List list = ServletUtility.getList(request);
                    int k=list.size();
                    Iterator<TimeTableBean> it = list.iterator();

                    while (it.hasNext()) {

                    	 bean = it.next();
                %>
                <tr style="text-align: center;">
               
                    
                     <td><input type="checkbox" name="ids" value="<%=bean.getId()%>"></td>
                    <td><%=index++%></td>
                    <td><%=bean.getCourceName()%></td>
                    <td><%=bean.getSubjectName()%></td>
                    <td><%=bean.getExamDate()%></td>
                    <td><%=bean.getExamTime()%></td>
                   <td><a href="TimeTableCtl?id=<%=bean.getId()%>">Edit</a></td>
                    
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
						disabled="disabled" value="<%=TimeTableListCtl.OP_PREVIOUS%>">
					</td><%
							} else {
						%> 
					<td align="left">
						<input type="submit" name="operation"
						value="<%=TimeTableListCtl.OP_PREVIOUS%>">
						<%
							}
						%>
					</td>
					<td align="center"><input type="submit" name="operation" value="<%=TimeTableListCtl.OP_DELETE%>">
					&emsp;&emsp;&emsp;&emsp;&emsp;
&emsp;&emsp;&emsp;&emsp;&emsp;
<input type="submit" name="operation"value="<%=TimeTableListCtl.OP_NEW%>">
					
					</td>
					<%
						if (list.size() < pageSize || model.nextPK() - 1 == bean.getId()) {
					%>
					<td align="right"><input type="submit" name="operation"
						disabled="disabled" value="<%=TimeTableListCtl.OP_NEXT%>"></td><%}else { %>
					<td align="right">
						 <input align="right" type="submit" name="operation"
						value="<%=TimeTableListCtl.OP_NEXT%>">
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