<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<!-- Start Groups -->
<div id="groups" class="point-detail-container">
	<div class="current-monitored-event">
		<h3>
			<s:text name="commons.pages.groups"/> <sec:authorize access="hasAnyRole('ROLE_Role.EPM_ADMIN', 'ROLE_Role.EPM_SYSTEM_OPERATOR')">
				<a href="" id="add-groups" class="edit right">
					<s:text name="commons.pages.addtogroup"/>
				</a>
			</sec:authorize>
		</h3>
	</div>
	<div class="blankslate" id="blankslate-group">
		<h5><s:text name="smartpointdetail.tabs.about.addYourFirstGroup"/></h5>
		<p><s:text name="smartpointdetail.tabs.about.noGroupsWithThisSmartPoint"/></p>
	</div>
	<fieldset id="div-groups" class="edit-only two-line">
		<ul>
			<li class="ui-widget">
				<label for="groupAddSelect"><s:text name="commons.pages.selectGrouplist"/> <span class="required">*</span></label> <label class="error hide group-add-error" generated="true" for="combo-tag" id="smartpoint-invalid-tag" style="display: inline;"></label><br/>
				
				<select name="combo_group" class="combobox" id="combo_group">
				</select>
				
				<label id="textValidationGroup" class="error hide tag-add-error" generated="true" for="combo-tag"></label>
			</li>                                  
			<li class="submit-row">
				<input id="group-submit" type="submit" class="submit-short-form" value="<s:text name="smartpointdetail.tabs.about.addSmartPointToGroup"/>" /> <s:text name="commons.pages.or"/> <a href="" class="cancel-edit cancel"><s:text name="commons.pages.cancel"/></a>
			</li>
		</ul>     
	</fieldset>                       
	<table class="small-table">
		<tbody>

		</tbody>
	</table>
</div>
<!-- end Groups -->