<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="stock" uri="/WEB-INF/tlds/stockInfo.tld" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Watch List</title>
</head>

<BODY>
	<table width="650" border="0">
		<tr>
			<td width="650" height="80"
				background="<%=request.getContextPath() + "/image/top.jpg"%>">&nbsp;</td>
		</tr>
		<tr>
			<td><a href="<%=request.getContextPath() + "/user/login.jsp"%>">Login</a>&nbsp;&nbsp;
				<a href="<%=request.getContextPath() + "/user/register.html"%>">Register</a>&nbsp;&nbsp;
				<a href="<%=request.getContextPath() + "/StockListServlet"%>">Watch
					Stock List</a>&nbsp;&nbsp; <a
				href="<%=request.getContextPath() + "/logout.do"%>">Log off</a>&nbsp;&nbsp;
				<a href="<%=request.getContextPath() + "/loadUser.do"%>">My
					Profile</a>&nbsp;&nbsp; <a
				href="<%=request.getContextPath() + "/showWatchList.do"%>">Watch
					My Stock List</a>&nbsp;&nbsp; <a
				href="<%=request.getContextPath() + "/index.do"%>">Order
					Cancellation</a>&nbsp;&nbsp; <a
				href="<%=request.getContextPath() + "/stock/search.jsp"%>">Search</a>
			</td>
		</tr>
	</table>
	<H1>Online Stock.</H1>
	<H2>
		<BR>
		<jsp:useBean id="listStock"
			type="edu.nju.onlinestock.action.business.StockListBean"
			scope="session"></jsp:useBean>
		<jsp:useBean id="item" class="edu.nju.onlinestock.model.Stock"
			scope="page"></jsp:useBean>


		There are all Stocks.
	</H2>
	<H4>
		<BR>
		<TABLE width="100%" border="0" cellpadding="0" cellspacing="1">
			<TBODY>
				<TR>
					<TH width="20%">id</TH>
					<TH width="20%">companyName</TH>
					<TH width="20%">type</TH>
					<TH width="20%">price</TH>
					<TH width="20%">date</TH>
				</TR>
 
<!--				<%
					for (int i = 0; i < listStock.getStockList().size(); i++) {
						pageContext.setAttribute("item", listStock.getStockList(i));
				%>
				<TR>
					<TD align="center"><jsp:getProperty name="item" property="id" /></TD>
					<TD align="center"><jsp:getProperty name="item" property="companyName"/></TD>
					<TD align="center"><jsp:getProperty name="item"
							property="type" /></TD>
					<TD align="center"><jsp:getProperty name="item"
							property="price" /></TD>
					<TD align="center"><jsp:getProperty name="item"
							property="date" /></TD>
				</TR>
				<%}%>
-->
				<stock:stockInfo/>
			</TBODY>
		</TABLE>
	</H4>
	
<form method="GET" action="<%=response.encodeURL(request.getContextPath() + "/Login")%>">
</p>
<input type="submit" name="Logout" value="Logout">
</form>

</body></html>
</html>


