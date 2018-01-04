
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="false" %>

<%@ page isErrorPage="true" %>

<html>
<head>
    <title>Login</title>
    <meta http-equiv='Refresh' content='0;url=/LoginServlet'>
</head>
<body>
    <form method='POST' action='/ShowMyStockServlet'>
        username: <input type='text' name='username' value=''/>
        password: <input type='password' name='password' value=''/>
        <input type='submit' name='Submit' value='Submit'/>
    </form>
</body>
</html>
