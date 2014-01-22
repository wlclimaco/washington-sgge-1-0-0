<%@ page session="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	response.setContentType("text/html; charset=iso-8859-1");
	response.setHeader("Cache-Control", "no-cache, private, must-revalidate, max-stale=0, post-check=0, pre-check=0 no-store"); // HTTP 1.1.
	response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
	response.setDateHeader("Expires", 0); // Proxies.
%>
<!DOCTYPE html>
<html>
<head>
<title>WebDaptive-jDaptive Login</title>
<style type="text/css">
BODY {
	FONT-FAMILY: Arial, Helvetica; FONT-SIZE: 100%
}
.login {
	POSITION: relative; PADDING-BOTTOM: 10px; PADDING-LEFT: 10px; PADDING-RIGHT: 10px; OVERFLOW: hidden; PADDING-TOP: 100px;
}
.login-error-msg{
	FONT-WEIGHT: bold; FONT-SIZE: 14pt; FONT-FAMILY: Arial, Helvetica; COLOR: red; TEXT-ALIGN: center; PADDING-TOP: 5px;
}
.login-data{
	FONT-SIZE: 9pt; FONT-FAMILY: Arial, Helvetica;  margin-left: 50%; PADDING-TOP: 5px;
}
.login-label{
	FONT-SIZE: 9pt; FONT-WEIGHT: bold;  FONT-FAMILY: Arial, Helvetica;  float: left;  width: 50%; text-align: right; PADDING-RIGHT: 7px; PADDING-TOP: 9px;
}
.login-msg{
	FONT-SIZE: 11pt; FONT-FAMILY: Arial, Helvetica; text-align: center;
}
.login-buttons {
	margin-left: 47%; PADDING-TOP: 5px;
}
</style>
</head>
<body onload='init();'>
<div class="login">
	<form id="loginForm" name="loginForm" action="j_spring_security_check" method="post">
		<div id="login-warning" class="login-msg">This is a private network all unauthorized use will be prosecuted to the fullest extent of the law.</div>
		<div id="uidlabel" class="login-label">Username</div>
		<div class="login-data"><input id="j_username" name="j_username" type="text" size=17 maxlength=25 tabindex="1" value=""/></div>
		<div id="pwdlabel" class="login-label">Password</div>
		<div class="login-data"><input id="j_password" name="j_password" type="password" size=17 maxlength=25 tabindex="2" value=""></div>
		<div class="login-buttons"><input type="submit" value="Login" />&nbsp;&nbsp;<input type="reset" value="Reset" /></div>
		<div id="login-error" class="login-error-msg">Please Login.</div>
	</form>
</div>
<script type="text/javascript">
window.history.go(1);
function init()
{
	if (window.location.search.substring(0, 1) == '?')
	{
		document.getElementById('login-error').innerHTML = unescape(window.location.search.substring(1));
	}
	document.loginForm.j_username.focus();
}
</script>
</body>
</html>