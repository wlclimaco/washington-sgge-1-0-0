<%@page import="java.util.Collection" %>
<%@page import="org.springframework.security.core.Authentication" %>
<%@page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@page import="org.springframework.security.core.userdetails.User" %>
<%@page import="org.springframework.security.ldap.userdetails.LdapUserDetailsImpl"  %>
<%@page import="org.springframework.security.core.userdetails.UserDetails "  %>
<%@page import="org.springframework.security.web.authentication.WebAuthenticationDetails" %>
<%@page import="org.slf4j.Logger" %>
<%@page import="org.slf4j.LoggerFactory" %>

<HTML>
<HEAD>
<%
final Logger LOG = LoggerFactory.getLogger(this.getClass());
Authentication auth = SecurityContextHolder.getContext().getAuthentication();
if (auth == null)
{
	LOG.debug("something is really wrong no security context!");
}
else
{

	Object obj = auth.getPrincipal();
	UserDetails userInfo = null;
	if (obj instanceof User)
	{
		userInfo = (User)obj;
	}
	else
	{
		userInfo = (LdapUserDetailsImpl)obj;
	}
	//No need to check status keep_alive.jsp will fire and check and if not present it will cause spring security to kill the session automatically
	session.setAttribute("username", userInfo.getUsername());
	//Do not display password in production this for learning purposes only
	LOG.debug("user_info details: name:" + userInfo.getUsername() + ",pass:" + userInfo.getPassword() + ",authorities:" + userInfo.getAuthorities());
%>
<script type="text/javascript">
		var uUserName = '<%= userInfo.getUsername() %>';
		var uUserPassword = '<%= userInfo.getPassword() %>';
		var uAuthorities = '<%= userInfo.getAuthorities() %>';
</script>
<%
}
%>
</HEAD>
</HTML>