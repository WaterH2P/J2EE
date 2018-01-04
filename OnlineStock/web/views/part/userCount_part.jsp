
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="false" %>
<html>
<head>
    <title>User Count</title>
</head>
<body>
    <jsp:useBean id="userCount" scope="application" type="main.javabean.UserCountBean"/>
    <br/>
    <p>在线总人数：<jsp:getProperty name="userCount" property="total"/></p>
    <p>已登陆总人数：<jsp:getProperty name="userCount" property="online"/></p>
    <p>游客总人数：<jsp:getProperty name="userCount" property="visitor"/></p>
    <br/>
</body>
</html>
