<%@ page language="java" contentType="text/html; charset=utf-8" session="true"%>
<!--Redirects the user to the proper login page.---->
<html>
<head>
<title>Loading ...</title>
</head>
<body>
<%
    response.sendRedirect(request.getContextPath() + "/josso_login/");
%> 
</body>
</html>