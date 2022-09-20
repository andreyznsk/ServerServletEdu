<%@ page import="java.util.Objects" %>
<html>
<body>
<h2>Hello World!</h2>

<%
    String user = (String)session.getAttribute("user");
    if (Objects.nonNull(user)) {
        out.println("<br>");
        out.println("Hello, " + user  + "!");
        out.println("try all endpoints:");
        out.println("<br><a href=\"loginPage.jsp\"> loginPage</a>");
        out.println("<br><a href=\"fileuploadservlet\"> file uploader</a>");
        out.println("<br><a href=\"start\"> show catalog</a>");


    } else {
%>
    <p> User is unknown! Please try to auth:
    <a href="loginPage.jsp"> signIn</a>
    <br><a href="welcome"> Welcome page</a>

<%
            }
%>

</body>
</html>
