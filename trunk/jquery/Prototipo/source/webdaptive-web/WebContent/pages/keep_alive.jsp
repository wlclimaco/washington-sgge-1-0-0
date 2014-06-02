<%@page import="org.springframework.security.core.Authentication" %>
<%@page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@page import="org.slf4j.Logger" %>
<%@page import="org.slf4j.LoggerFactory" %>
<%
final Logger LOG = LoggerFactory.getLogger(this.getClass());
Authentication auth = SecurityContextHolder.getContext().getAuthentication();
if (auth == null)
{
	out.println("LATER");
	LOG.debug("LATER");
}
else
{
	out.println("OK");
	LOG.debug("OK");
}
%>