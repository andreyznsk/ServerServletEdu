<%--suppress unchecked --%>
<%@ taglib prefix="ex" uri="/WEB-INF/productListPrinter.tld"%>

<%@ page import="ru.edu.servlet.dto.ProductDesc" %>
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
<%
    List<ProductDesc> productDescList = (List<ProductDesc>) request.getAttribute("products");
%>

<ex:listPrinter products='<%=productDescList%>'/>
</body>
</html>
