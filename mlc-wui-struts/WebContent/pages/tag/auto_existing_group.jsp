<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<%-- Delete Group Dialog --%>
<div id="action-dialog-delete" class="action-dialog">
	<div id="action-group-exists-dialog" class="action-dialog">
	<form id="add-group-exists-form" name="addGroupExistsForm" method="post" >
    
    <!-- Messaging -->
    <h2 id="auto-existing-group-message"></h2>
    <div id="action-group-exists-messaging" class="messaging"><span class="message"></span></div>        
        <fieldset>
                <div class="point-detail-container">
                	<dl>
	                   <dt><s:text name="action.autogroup.groupName"/></dt>
                       <dd><strong id="action-group-exists-name"></strong></dd>
	                   <dt><s:text name="action.autoexistinggroup.description"/></dt>
                       <dd id="action-group-exists-description"></dd>
                    </dl>
                </div>
                <div class="highlight"><small id="include-smarpoints-msg"><input class="include-smartpoints-to-group" type="checkbox"> </small></div>                
   		 </fieldset>
      </form>	
</div>
</div>