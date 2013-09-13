<%@page contentType="text/html; charset=UTF-8" language="java" session="true" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%
	SecurityContextHolder.getContext().setAuthentication(null);
	session.invalidate();
%>  
<%
    response.sendRedirect(request.getContextPath() + "/josso_logout/");
%>
