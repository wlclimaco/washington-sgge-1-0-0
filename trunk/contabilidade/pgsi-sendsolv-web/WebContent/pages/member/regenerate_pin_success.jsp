<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>

<%-- Regenerate Pin Number--%>
<div>
	<p class="bold"><s:message code="pages.member.pin.dialog.successtext" text="default text" /></p>
    <p>
    	<span class="label">
    		<s:message code="pages.member.pin.dialog.newpin" text="default text" />
    	</span>
    	<span id="pin-newpin" class="bold"></span>
    </p>
</div>