
<%@ page import="java.util.ArrayList" %>
<%@ page import="main.model.Order" %>
<%@ page import="main.servlets.ParaName" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="false" %>

<%@ page errorPage="../log/loginError_log.jsp" %>

<%@ taglib prefix="user" uri="/WEB-INF/tlds/userOnline.tld" %>

<user:checkSession username="<%= (String)request.getAttribute(ParaName.reqUserName) %>"/>

<html>
<head>
    <title>Orders</title>
</head>
<body>
    <h2>用户  <%= (String)request.getAttribute(ParaName.reqUserName) %>   订单详情</h2>
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

        <jsp:useBean id="orders" scope="request" type="main.javabean.OrderListBean"/>
        <jsp:useBean id="item" scope="page" class="main.model.Order"/>
        <%
            ArrayList<Order> myOrders = (ArrayList<Order>) orders.getOrders();
            for(int i=0; i<myOrders.size(); i++){
                Order order = myOrders.get(i);
                pageContext.setAttribute("item", order);
                if( order.getGoodNum()>order.getStoreNum() ){
                    out.println("<tr style='color:red;'>");
                }
                else {
                    out.println("<tr>");
                }
        %>
        <%--${item.goodNum}--%>
        <td><jsp:getProperty name="item" property="orderID"/></td>
        <td><jsp:getProperty name="item" property="goodID"/></td>
        <td><jsp:getProperty name="item" property="goodName"/></td>
        <td><jsp:getProperty name="item" property="goodNum"/></td>
        <td><jsp:getProperty name="item" property="storeNum"/></td>
        <td><jsp:getProperty name="item" property="goodPrice"/></td>
        <td><jsp:getProperty name="item" property="totalPrice"/></td>
        <td><jsp:getProperty name="item" property="orderDate"/></td>

        <%
                if( order.getGoodNum()>order.getStoreNum() ){
                    out.println("<td>库存不足</td>");
                }
                else {
                    out.println("<td></td>");
                }
            }
        %>
        </tr>
    </table>

    <%@ include file="../part/logout_part.jsp"%>
    <br/>
    <br/>
    <%@ include file="../part/userCount_part.jsp"%>
</body>
</html>
