<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<%-- Switch SmartPoint On/Off Dialog --%>
<div id="action-dialog-panel" class="action-dialog">
<form id="switch-groups-lights-form" name="switch-groups-lights-form" method="post">

<%-- Messaging --%>
<div id="action-messaging" class="messaging"><span class="message"></span></div>
<fieldset class="two-line">
<!--START: Dimmer -->
<div class="lighting-controls dimmer" >
       <fieldset>
           <label class="title"><s:text name="action.switchlight.subtitle" /></label>
               <div class="control">
                    <select name="dimmer" id="dimmer">
	                    <option value="0">OFF</option>
	                    <option value="50">50%</option>
	                    <option value="100">ON</option>
	                </select>
               </div>
        </fieldset>                                
</div>
<!--END: Dimmer -->
</fieldset>

<%-- Addtional Description --%>
<div class="highlight"><small><s:text
	name="action.switchlight.label.hint"></s:text></small></div>
</form>
</div>