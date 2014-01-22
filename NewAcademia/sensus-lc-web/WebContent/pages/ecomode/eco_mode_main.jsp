<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<sec:authorize access="hasAnyRole('ROLE_Role.Admin')">
<div id="eco-mode">
<form id="import-eco-mode" name="importEcoModeForm"  method="post" action="ecomode/upload" enctype="multipart/form-data">
	<div id="list-baseline-type-contianer">
    	<fieldset>
      	<p class="desc"><s:message code="systemsettings.ecomode.page.note" /> <span class="note"><a href="#" class="text_button"><s:message code="systemsettings.ecomode.page.importfileformat" /></a></span></p>
             <ul>
                 <li class="highlight">
                     <label for="upload-ecomode"><s:message code="systemsettings.ecomode.page.selectfile" /> <span class="required-small">*<s:message code="systemsettings.ecomode.page.required" /></span></label>
                     <input id="upload-ecomode" name="upload" type="file"/>
                 </li>
                 <li  class="chzn-row select-tags">
					<label for="select_tags"><s:message code="systemsettings.ecomode.page.tagtheselights" /> <small class="optional"><s:message code="systemsettings.ecomode.page.optional" /></small></label><br />
					<select data-placeholder="Create or select multiple Tags"  multiple class="chzn-select" id="select_tags" name="uploadTag" tabindex="8">
					</select>
                 </li>
                 <li class="submit-row-pad">
                     <input type="submit" id="submit-ecomode" class="submit-short-form" value="Import Baseline Definitions" /> <s:message code="systemsettings.ecomode.page.or"/> <a href="#" class="cancel-edit cancel"><s:message code="commons.pages.cancel" /></a>
                 </li>
             </ul>
         <!-- START default season container -->
         </fieldset>
     </div>
 </form>
</div>
<%-- START Main Page Content --%>
<%@ include file="../../scripts/pages/ecomode/ecomode_main.js.jsp" %>
<%@ include file="../../scripts/pages/ecomode/ecomode_actions.js.jsp" %>
<%@ include file="../../scripts/pages/ecomode/ecomode_init.js.jsp" %>
<%-- START Main Content Container --%>
</sec:authorize>