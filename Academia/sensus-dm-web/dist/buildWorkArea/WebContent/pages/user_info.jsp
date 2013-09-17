<%@page import="org.springframework.security.core.Authentication" %>
<%@page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@page import="org.springframework.security.core.userdetails.User" %>
<%@page import="org.springframework.security.ldap.userdetails.LdapUserDetailsImpl"  %>
<%@page import="org.springframework.security.core.userdetails.UserDetails "  %>
<%@page import="org.springframework.security.web.authentication.WebAuthenticationDetails" %>
<%@page import="org.apache.commons.logging.Log" %>
<%@page import="org.apache.commons.logging.LogFactory" %>

<%
final Log LOG = LogFactory.getLog(this.getClass());
Authentication auth = SecurityContextHolder.getContext().getAuthentication();
LOG.debug(auth);
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


	WebAuthenticationDetails details = (WebAuthenticationDetails)auth.getDetails();
%>

<SCRIPT type="text/javascript">


		var uSessionId = '<%= details.getSessionId() %>';
		var uUserName = '<%= userInfo.getUsername() %>';
		var uAuthorities = '<%= userInfo.getAuthorities() %>';

</SCRIPT>
<%
}
%>