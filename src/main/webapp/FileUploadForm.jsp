<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.Objects" %>

<!DOCTYPE html>
<html>
<head><title> Java File Upload Servlet Example </title></head>
<body>
<h3>Single file uploading form:</h3>
<br>
<form method="post" action="fileuploadservlet" enctype="multipart/form-data">
  <input type="file" name="file"/>
  <input type="submit" value="Upload"/>
</form>
<h3>Multi file uploading form:</h3>
<br>
<form method="post" action="fileuploadservlet" enctype="multipart/form-data">
  <input type="file" name="file1"/><br>
  <input type="file" name="file2"/><br>
  <input type="submit" value="Upload"/>
</form>

<%
  String result = (String)session.getAttribute("result");
  if (Objects.nonNull(result)) {
    out.println("<br>");
    out.println(result);
  }
%>

</body>
</html>