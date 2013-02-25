<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="decorator"
	uri="http://www.opensymphony.com/sitemesh/decorator"%>
<%@taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page"%>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>


<%-- START Page Content --%>
<%-- Page Content --%> <decorator:body />
<%-- Stylesheets imported via javascript-components.xml --%>
<s:iterator value="cssImports">
	<s:if test="%{absolutePath}">
		<link rel="stylesheet" href="<s:property value='path'/>"></link>
	</s:if>
	<s:else>
		<link rel="stylesheet"
			href="<%=request.getContextPath()%><s:property value='path'/>"></link>
	</s:else>
</s:iterator>

<%-- JavaScript settings imported via javascript-components.xml --%>
<s:iterator value="jsSettings">
	<s:if test="%{absolutePath}">
		<script src="<s:property value="path"/>"></script>
	</s:if>
	<s:else>
		<script src="<%=request.getContextPath()%><s:property value="path"/>"></script>
	</s:else>
</s:iterator>

<s:iterator value="jsImports">
	<s:if test="%{absolutePath}">
		<script src="<s:property value="path"/>"></script>
	</s:if>
	<s:elseif test="%{sec}">
		<jsp:include page="${path}" flush="true" />
	</s:elseif>
	<s:else>
		<script src="<%=request.getContextPath()%><s:property value="path"/>"></script>
	</s:else>
</s:iterator>

<%-- END Page Content --%>