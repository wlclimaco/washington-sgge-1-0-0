<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%
	SecurityContextHolder.getContext().setAuthentication(null);
	session.invalidate();
%>  

<script language="javascript">
	window.location.href=  "./login.jsp?Session timed out.";
</script>
