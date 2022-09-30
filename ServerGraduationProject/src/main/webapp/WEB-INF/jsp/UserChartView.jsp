<%--suppress unchecked --%>
<%@ page contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="ex" uri="/WEB-INF/tld/productListPrinter.tld" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page import="ru.homeWork.dto.Product" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Locale" %>
<%@ page import="java.util.ResourceBundle" %>
<%@ page import="java.text.MessageFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="static ru.homeWork.command.CommandTypeAndParam.USER_PARAM" %>
<%@ page import="static ru.homeWork.command.CommandTypeAndParam.COMMAND_CONTEXT" %>
<%@ page import="static ru.homeWork.command.CommandTypeAndParam.SHOW_CHART" %>
<%@ page import="static ru.homeWork.command.CommandTypeAndParam.*" %>

<%
    Locale locale = request.getLocale();
    ResourceBundle rb = ResourceBundle.getBundle("messages", locale);
    String user = (String) request.getAttribute(USER_PARAM.getCommand());

    if (user == null) {
        user = rb.getString("unknown.user");
    }
    String welcomeMsgTemplate = rb.getString("welcome.message");
    String welcomeMsg = MessageFormat.format(welcomeMsgTemplate, user);
    String datePattern = rb.getString("date.format");
    Date today = new Date();
    String showChartLink = pageContext.getServletContext().getContextPath() + COMMAND_CONTEXT.getCommand() + SHOW_CHART.getCommand();
    String homePage = pageContext.getServletContext().getContextPath() + INDEX_CONTEXT.getCommand();
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

<h2><fmt:message key="title"/></h2>
<h3>"<%=welcomeMsg%>"</h3>
<%
    List<Product> productDescList = (List<Product>) request.getAttribute("products");
    int quantityProdInChart = (int) request.getAttribute("charQuantity");
    double totalPrice = (double) request.getAttribute("totalPrice");
    if (productDescList.isEmpty()) {
%>
<h3>Chart is empty</h3>
<%
} else {
%>
<ex:listPrinter products='<%=productDescList%>' action="RemoveFromChart"/>
<h3>Total price: <%=totalPrice%>
</h3>
<%
    }
%>


<hr>
<a href="<%=showChartLink%>"
   charset="UTF-8"> User chart</a>
<br>
Chart quantity: "<%=quantityProdInChart%>"
<br>
<a href="<%=homePage%>" charset="UTF-8"> Product
    catalog</a>


<footer>
    <h4><fmt:message key="time.message"/> <fmt:formatDate pattern='<%=datePattern%>' value='<%=today%>'/></h4>
</footer>

</body>
</html>
