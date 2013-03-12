<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%-- Add SmartPoints to Tag Action Dialog --%>
<div id="action-dialog-panel" class="action-dialog">
	<form id="addTagForm" name="createTagForm" method="post" >
    <%-- Messaging --%>
	<div id="action-messaging" class="messaging"><span class="message"></span></div>        
        <fieldset class="two-line chzn-container">
                <ul>
                    <li class="chzn-row select-tag">
                        <label for="tagAddSelect"><small><s:text name="smartpoint.tags.example" /> </small><span class="required">*</span></label> <br/>
                        <select data-placeholder="Select Tags"  multiple class="chzn-select" id="select_tags" name="select_tags" style="width: 90%"> 
							<s:iterator status="tags" value="%{tagList}">
								<option value="<s:property value='id' />"><s:property value='value' /></option>
							</s:iterator>           
                        </select> 
                    </li>
                    <li>
						<p class="highlight sui-pad dialog-instructions"><s:text name="smartpoint.tags.description1" /> <strong><s:text name="smartpoint.tags.added" /></strong> <s:text name="smartpoint.tags.description2" /> <span class="smartpoint-count">0</span> <s:text name="smartpoint.tags.description3" /></p>
                    </li>
                </ul>
   		 </fieldset>
      </form>	
</div>