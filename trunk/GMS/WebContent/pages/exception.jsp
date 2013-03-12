<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Municipal Lighting Control: Exception Report</title>
<%
org.apache.commons.logging.Log logger = org.apache.commons.logging.LogFactory.getLog("Exception.jsp");
java.lang.Exception e = (java.lang.Exception) request.getAttribute("exception");
if (e==null){
    logger.error("***** An exception happened but no exception attribute is set *******");
} else {
    logger.error("Exception", e);
}

 %>
</head>
<body>
	<h2>An unexpected error has occurred.</h2>
	<p>
		This error has been recorded in the appropriate log files.  Please report this error to your
		system administrator or appropriate technical support personnel.  Further information on this
		error can be seen by clicking on the button below.
	</p>
	<input type="button" value="Additional Info" onclick="document.getElementById('error-div').style.display = 'block';" />
	<hr/>
	<div id="error-div" style="display: none;">
		<h3>Error Message</h3>
		<s:actionerror/>
		<p>
			<s:property value="%{exception.message}"/>
		</p>
		<h3>Technical Details</h3>
		<pre>
			<s:property value="%{exceptionStack}"/>
		</pre>
	</div>
</body>
</html>


