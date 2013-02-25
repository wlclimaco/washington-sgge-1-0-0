<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<sec:authorize access="hasAnyRole('ROLE_Role.Admin')">
<div id="eco-mode">
<form id="import-eco-mode" name="importEcoModeForm"  method="post" action="systemsettings/ajax.importEcoMode.action" enctype="multipart/form-data">
	<div id="list-baseline-type-contianer">
    	<fieldset>
        <legend><s:text name="systemsettings.ecomode.page.importdefinitions" /></legend>
      	<p class="desc"><s:text name="systemsettings.ecomode.page.note" /> <span class="note"><a href="#" class="text_button"><s:text name="systemsettings.ecomode.page.importfileformat" /></a></span></p>
             <ul>
                 <li class="highlight">
                     <label for="upload-ecomode"><s:text name="systemsettings.ecomode.page.selectfile" /> <span class="required-small">*<s:text name="systemsettings.ecomode.page.required" /></span></label>
                     <input id="upload-ecomode" name="uploadEcoModeFile" class="validate[custom[onlyCsv]]" type="file"/>
                 </li>
                 <li  class="chzn-row select-tags">
                     <label for="select_tags"><s:text name="systemsettings.ecomode.page.tagtheselights" /> <small class="optional"><s:text name="systemsettings.ecomode.page.optional" /></small></label><br />
                     <select data-placeholder="Create or select multiple Tags"  multiple class="chzn-select" id="select_tags" name="uploadTag" tabindex="8">
                                                 
                     </select>
                 </li>
                 <li class="submit-row-pad">
                     <input type="submit" id="submit-ecomode" class="submit-short-form" value="Import Baseline Definitions" /> or <a href="#" class="cancel-edit cancel"><s:text name="commons.pages.cancel" /></a>
                 </li>
             </ul>
         <!-- START default season container -->                                                                  
         </fieldset>                            
     </div> 
 </form>                                                   
</div>
</sec:authorize>