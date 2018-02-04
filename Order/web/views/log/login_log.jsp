<%@ page import="main.servlets.ParaName" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="false" %>

<%@ page errorPage="loginError_log.jsp" %>

<html>
<head>
    <title>Login</title>
</head>
<body>
    <form method='POST' action='/ShowMyOrderServlet'>
        username: <input type='text' name='username' value='<%= (String)request.getAttribute(ParaName.reqUserName) %>'/>
        password: <input type='password' name='password' value=''/>
        <input type='submit' name='Submit' value='Submit'/>
    </form>

    <%@ include file="../part/userCount_part.jsp"%>
</body>
</html>
