<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<!-- Start Tags -->
<div id="tags" class="point-detail-container">

	<div class="current-monitored-event">
		<h3>
			<s:text name="smartpointdetail.tabs.about.tags"/>
			<sec:authorize access="hasAnyRole('ROLE_Role.EPM_ADMIN', 'ROLE_Role.EPM_SYSTEM_OPERATOR')">
				<a href="" id="add-tags" class="edit right"><s:text name="commons.pages.addTag"/></a>
			</sec:authorize>
		</h3>
	</div>
	
	<fieldset id="div-tags" class="edit-only two-line">
		<ul>
			<li class="ui-widget">
				<label for="tagAddSelect"><s:text name="smartpoint.page.description"/> <span class="required">*</span></label>
				<br/>
				<div id="combo-tag" >
                            <select name="tag_add_select_smartpoint" class="combobox" id="tag_add_select_smartpoint">
                            </select>
					
					<label id="textValidationTag" class="error hide tag-add-error" generated="true" for="combo-tag"></label>
					<div class="yui-u"></div>
				</div>
			</li>
			                              
			<li class="submit-row">
                        	<input id="tag-submit" type="submit" class="submit-short-form" value="<s:text name="smartpointdetail.tabs.about.addTagToSmartPoint"/>" />
                        	<s:text name="commons.pages.or"/>
                        	<a href="" class="cancel-edit cancel"><s:text name="commons.pages.cancel"/></a>
			</li>                                    
		</ul>    

		</fieldset>

		<div class="blankslate" id="blankslate-tags">
			<h5><s:text name="tag.page.addFirstTag"/></h5>
			<p><s:text name="smartpointdetail.tabs.about.noTagsWithThisSmartPoint"/></p>
		</div>
		                                                      
		<ul class="tag-container">
		
		</ul>                                                                 
</div>
<!-- End Tags -->