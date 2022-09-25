<%--
  Created by IntelliJ IDEA.
  User: 19208093
  Date: 22.08.2022
  Time: 19:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login page</title>
</head>
<body>
<h2>Trying to login</h2>
<form name="loginForm" method="POST"
      action="/index?command=Auth">
    Username: <input type="text" name="username"/> <br/>
    <input type="submit" value="Login" />
</form>

</body>
</html>
