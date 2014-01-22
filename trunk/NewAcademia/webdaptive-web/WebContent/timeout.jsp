<%@page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%
	response.setContentType("text/html; charset=iso-8859-1");
	response.setHeader("Cache-Control", "no-cache, private, must-revalidate, max-stale=0, post-check=0, pre-check=0 no-store"); // HTTP 1.1.
	response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
	response.setDateHeader("Expires", 0); // Proxies.
%>
<%
	SecurityContextHolder.getContext().setAuthentication(null);
	session.invalidate();
%>

<script language="javascript">
	var message = "Access Denied.";
	if (window.location.href.indexOf("?") != -1)
	{
		message = window.location.href.slice(window.location.href.indexOf("?") + 1);
	}
	window.location.href=  "./index.jsp?" + message;
</script>
