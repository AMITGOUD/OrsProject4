<%@page import="in.co.sunrays.controller.MarksheetMeritListCtl"%>
<%@page import="in.co.sunrays.util.ServletUtility"%>
<%@page import="in.co.sunrays.bean.MarksheetBean"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page errorPage="ErrorView.jsp"%>
<html>
<body>
    <%@include file="Header.jsp"%>
    <center>
        <h1>Marksheet Merit List</h1>

                    <font color="red"><%=ServletUtility.getErrorMessage(request)%></font>
                
        <form action="<%=ORSView.MARKSHEET_MERIT_LIST_CTL%>" method="POST">
            <br>
            <table border="1" width="100%">
                <tr style="background-color:gray;color: menu;">

                    <th>S.No</th>
                    <th>Roll No</th>
                    <th>Name</th>
                    <th>Physics</th>
                    <th>Chemistry</th>
                    <th>Maths</th>
<!--                     <th>Percentage</th> -->

                </tr>
                
                <%
                    int pageNo = ServletUtility.getPageNo(request);
                    int pageSize = ServletUtility.getPageSize(request);
                    int index = ((pageNo - 1) * pageSize) + 1;

                    List list = ServletUtility.getList(request);
                    Iterator<MarksheetBean> it = list.iterator();

                    while (it.hasNext()) {

                        MarksheetBean bean = it.next();
//                        /*  int a=bean.getPhysics();
//                         int b= bean.getChemistry();
//                        int c=  bean.getMaths();
//                          float d=(float)(((a+b+c)/300)*100); */
                        
                %>
                <tr>

                    <td><%=index++%></td>
                    <td><%=bean.getRollNo()%></td>
                    <td><%=bean.getName()%></td>
                    <td><%=bean.getPhysics()%></td>
                    <td><%=bean.getChemistry()%></td>
                    <td><%=bean.getMaths()%></td>
<%--                     <td><%=d%>%</td> --%>

                </tr>

                <%
                    }
                %>
            </table>
            <table>
                <tr>
                    <td align="right"><input type="submit" name="operation"
                        value="<%=MarksheetMeritListCtl.OP_BACK%>"></td>
                </tr>
            </table>
            <input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
                type="hidden" name="pageSize" value="<%=pageSize%>">
        </form>
    </center>
    
</body><%@include file="Footer.jsp"%>

</html>