
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="false" %>

<%@ page errorPage="loginError.jsp" %>

<html>
<head>
    <title>Login</title>
</head>
<body>
    <jsp:useBean id="userAccount" scope="request" type="main.javabean.UserAccountBean"/>
    <form method='POST' action='/ShowMyStockServlet'>
        username: <input type='text' name='username' value='<jsp:getProperty name="userAccount" property="username"/>'/>
        password: <input type='password' name='password' value=''/>
        <input type='submit' name='Submit' value='Submit'/>
    </form>

    <%@ include file="../userCount.jsp"%>
</body>
</html>
