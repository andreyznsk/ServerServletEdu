<%@ page import="java.util.Locale" %>
<%@ page import="java.util.ResourceBundle" %>
<%@ page import="java.security.Principal" %>
<%@ page import="java.text.MessageFormat" %>
<%@ page import="java.util.Date" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
    Locale locale = request.getLocale();
    ResourceBundle rb = ResourceBundle.getBundle("messages", locale);
    String user = (String) request.getAttribute("user");

    if (user == null) {
        user = rb.getString("unknown.user");
    }
    String welcomeMsgTemplate = rb.getString("welcome.message");
    String welcomeMsg = MessageFormat.format(welcomeMsgTemplate, user);
    String datePattern = rb.getString("date.format");
    Date today = new Date();
%>
<fmt:setLocale value='<%=locale%>'/>
<fmt:setBundle basename="messages"/>
<!DOCTYPE html>
<html lang="ru">
<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <title>fmt:bundle example</title>
</head>
<body>
<h2><fmt:message key="title"/></h2>
<h3>"<%=welcomeMsg%>"</h3>
<form method="post" action="userAuthServlet">
<fmt:message key="login.label.userName"/> :
    <label>
        <input type="text" name="username" placeholder="<fmt:message key="login.label.userName"/>"/>
    </label><br/>
    <input type="submit" value="<fmt:message key="submit"/>"/>
</form>
</body>
<footer>
    <h4><fmt:message key="time.message"/> <fmt:formatDate pattern='<%=datePattern%>' value='<%=today%>'/></h4>
</footer>
</html>