<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*, java.text.*" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%!
  String getFormattedDate(){
    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
    return sdf.format(new Date());
  }
%>
<html>
  <head>
    <title>Logistics 2019</title>
  </head>
  <body>
    <h1>This is logistics portal 2019!</h1>
    <p>Today is <%= getFormattedDate()%></p>
    <a href="/towns">Towns table</a>
  </body>
</html>
