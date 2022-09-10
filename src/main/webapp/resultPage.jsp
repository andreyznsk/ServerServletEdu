<%--
  Created by IntelliJ IDEA.
  User: 19208093
  Date: 02.09.2022
  Time: 10:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Result</title>
</head>
<body>

<%
        Object result = request.getSession().getAttribute("result");
        out.println("<h4> Result is: " + result + "</h4>");
%>

</body>
</html>
