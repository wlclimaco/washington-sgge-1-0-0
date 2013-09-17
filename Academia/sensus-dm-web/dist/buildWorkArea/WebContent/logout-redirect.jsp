<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%
	SecurityContextHolder.getContext().setAuthentication(null);
	session.invalidate();
%>  

<script language="javascript">
	//var baseDir = sensus.commons.lib.ajax.settings.appRoot.split('/')[1];
	//var base = top.location.href.substring(0, top.location.href.indexOf(baseDir));
	window.location.href= "./login.jsp?Session timed out.";
</script>
