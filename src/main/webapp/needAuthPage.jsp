<%--
  Created by IntelliJ IDEA.
  User: 19208093
  Date: 22.08.2022
  Time: 21:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Unauthorized (400)</title>
</head>
<body>
<h3>Sorry login or password was empty!</h3>
<h3>Cookies:</h3>
<%
    Cookie cookie = null;
    Cookie[] cookies = null;

    // Get an array of Cookies associated with the this domain
    cookies = request.getCookies();

    if( cookies != null ) {
        out.println("<h2> Found Cookies Name and Value</h2>");

        for (int i = 0; i < cookies.length; i++) {
            cookie = cookies[i];
            out.print("Name : " + cookie.getName( ) + ",  ");
            out.print("Value: " + cookie.getValue( )+" <br/>");
        }
    } else {
        out.println("<h2>No cookies founds</h2>");
    }
%>
<p>Try to login:<a href="loginPage.jsp"> signIn</a>
</body>
</html>
