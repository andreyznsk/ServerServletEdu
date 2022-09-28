<%--suppress unchecked --%>
<%@ page contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="ex" uri="/WEB-INF/tld/productListPrinter.tld"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page import="ru.homeWork.dto.Product" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Locale" %>
<%@ page import="java.util.ResourceBundle" %>
<%@ page import="java.text.MessageFormat" %>
<%@ page import="java.util.Date" %>

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

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta charset="UTF-8" content="text/html">
    <title>List products</title>
</head>
<body>



Product view!
<%
    List<Product> productDescList = (List<Product>) request.getAttribute("products");
    int quantityProdInChart = (int) request.getAttribute("charQuantity");
%>

<ex:listPrinter products='<%=productDescList%>' action="AddToChart"/>

<hr>
<a href="${pageContext.request.contextPath}/index?command=ShowChart" charset="UTF-8"> User chart</a>
<br>
Chart quantity: "<%=quantityProdInChart%>"

<footer>
    <h4><fmt:message key="time.message"/> <fmt:formatDate pattern='<%=datePattern%>' value='<%=today%>'/></h4>
</footer>

</body>
</html>
