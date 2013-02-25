<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="utf-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>

<div id="settings-container" class="content-inner">
    <!-- Messaging -->
	<div id="messaging-main" class="messaging hide" >
		<span class="message"></span><a href="" class="remove"><s:text name="message.action.close" /></a>
	</div>        
	<div id="yui-main" >
      	<div class="content-header">
          	<h1><s:text name="systemsettings.page.title" /></h1>
		</div>
		<div id="tabs">
			<ul>
				<sec:authorize access="hasAnyRole('ROLE_Role.Admin')">
					<li><a href="systemsettings/ajax.profilePageAction.action"><s:text name="systemsettings.page.tab.general" /></a></li>
				</sec:authorize>
				<sec:authorize access="hasAnyRole('ROLE_Role.Admin', 'ROLE_Role.System Operator')">
					<li><a href="systemsettings/ajax.tagPageAction.action" class="tag-page-action"><s:text name="systemsettings.page.tab.tag" /></a></li>
				</sec:authorize>	
				<sec:authorize access="hasAnyRole('ROLE_Role.Admin')">
					<c:if test="${userContext.getTenant().getEcoModeDisable() == false}">
						<li><a href="systemsettings/ajax.ecoModePageAction.action" class="tag-page-action"><s:text name="systemsettings.page.tab.eco-mode" /></a></li>
					</c:if>
				</sec:authorize>	
			</ul>                                                   
		</div>
	</div>
</div>