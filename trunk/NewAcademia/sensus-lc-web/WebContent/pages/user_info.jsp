<%@page import="org.springframework.security.core.Authentication" %>
<%@page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@page import="org.springframework.security.core.userdetails.User" %>
<%@page import="org.springframework.security.ldap.userdetails.LdapUserDetailsImpl"  %>
<%@page import="org.springframework.security.core.userdetails.UserDetails "  %>
<%@page import="org.springframework.security.web.authentication.WebAuthenticationDetails" %>
<%@page import="org.apache.commons.logging.Log" %>
<%@page import="org.apache.commons.logging.LogFactory" %>

<%
	final Log log = LogFactory.getLog(this.getClass());
	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	log.debug(auth);
	if (auth != null)
	{
%>

<script type="text/javascript">
		var uUserName = '<%= auth.getPrincipal() %>';
		var uAuthorities = '<%= auth.getAuthorities() %>';
</script>
<%
	}
%>