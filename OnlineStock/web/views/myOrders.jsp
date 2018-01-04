
<%@ page import="java.util.ArrayList" %>
<%@ page import="main.model.Order" %>
<%@ page import="main.servlets.ParaName" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="false" %>

<%@ page errorPage="log/loginError.jsp" %>

<%@ taglib prefix="user" uri="/WEB-INF/tlds/userOnline.tld" %>

<html>
<head>
    <title>Orders</title>
    <jsp:useBean id="userAccount" scope="request" type="main.javabean.UserAccountBean"/>

    <user:checkSession username="<%= userAccount.getUsername() %>"/>

</head>
<body>

    <h2>用户  <%= request.getAttribute(ParaName.reqUserName) %>   订单详情</h2>
    <table style='padding:1px; text-align:center; margin:2px' width='auto' border='1' >
        <tr>
            <th>订单编号</th>
            <th>商品编号</th>
            <th>商品名称</th>
            <th>订购数量</th>
            <th>商品库存</th>
            <th>商品单价</th>
            <th>订单总价</th>
            <th>订单日期</th>
            <th>备注</th>
        </tr>
        <%
            ArrayList<Order> myOrders = (ArrayList<Order>) request.getAttribute(ParaName.orderList);
            for(int i=0; i<myOrders.size(); i++){
                Order order = (Order) myOrders.get(i);
                if( order.getGoodNum()>order.getStoreNum() ){
                    out.println("<tr style='color:red;'>");
                }
                else {
                    out.println("<tr>");
                }

                out.println("<td>"+order.getOrderID()+"</td>");
                out.println("<td>"+order.getGoodID()+"</td>");
                out.println("<td>"+order.getGoodName()+"</td>");
                out.println("<td>"+order.getGoodNum()+"</td>");
                out.println("<td>"+order.getStoreNum()+"</td>");
                out.println("<td>"+order.getGoodPrice()+"</td>");
                out.println("<td>"+order.getTotalPrice()+"</td>");
                out.println("<td>"+order.getOrderDate()+"</td>");
                if( order.getGoodNum()>order.getStoreNum() ){
                    out.println("<td>库存不足</td>");
                }
                else {
                    out.println("<td></td>");
                }
                out.println("</tr>");
            }
        %>
    </table>

    <%@ include file="log/logout.jsp"%>
    <br/>
    <br/>
    <%@ include file="userCount.jsp"%>
</body>
</html>
