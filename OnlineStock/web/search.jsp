<%--
  Created by IntelliJ IDEA.
  User: h2p
  Date: 2018/1/3
  Time: 下午10:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="false" %>
<jsp:useBean id="searchBean" scope="request" class="main.javabean.UserSearchBean">
    <%-- 初始化 javaBean --%>
    <jsp:setProperty name="searchBean" property="keywords"/>
</jsp:useBean>
<html>
<head>
    <title>Search</title>
</head>
<body>
    <h1>No Items Found</h1>
    <form action="search.jsp">
        <p style="color: red">
            Your search for " <jsp:getProperty name="searchBean" property="keywords"/> " returned no items.
            Try another search to find a match.
        </p>
        <p>Search Keywords:
            <input type="text" size="30" name="keywords">
        </p>
        <input type="submit" value="submit">
    </form>

    <p>
        Search keywords:
        <input type="text" size="30" name="keywords" value="
            <jsp:getProperty name="searchBean" property="keywords"/>
        "/>
    </p>
</body>
</html>
