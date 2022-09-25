<%--suppress unchecked --%>
<%@ taglib prefix="ex" uri="/WEB-INF/tld/productListPrinter.tld"%>

<%@ page import="ru.homeWork.dto.Product" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <title>List products with desc</title>
</head>
<body>

Product view!
<%--<%
    List<Product> productDescList = (List<Product>) request.getAttribute("products");
%>

<ex:listPrinter products='<%=productDescList%>'/>--%>
</body>
</html>
