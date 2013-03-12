<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<div id="action-han-detail" class="action-dialog">
	<form action="" method="post">
        <fieldset id="" class="create-action-container">
            <legend><span class="left"><strong class="field-header-device-type">Thermostat</strong> <small class="field-flex-net-id"> <a href="sp-detail-thermostat.html" class="text-button launch"><s:text name="smartpointdetail.dialog.deviceInformation.viewDetailPage"/></a></small></span></legend>
                    <!-- START: Device details -->                    
                   	<div class="yui-gc">
                        <div class="point-detail-container yui-u first">
                            <dl class="read-only">
                                <dt><s:text name="smartpointdetail.dialog.deviceInformation.deviceType"/></dt>
                                <dd class="field-device-type"></dd>
                                <dt><s:text name="smartpointdetail.dialog.deviceInformation.manufacture"/></dt>
                                <dd class="field-manufacture"></dd>
                                <dt><s:text name="smartpointdetail.dialog.deviceInformation.modelNumber"/></dt>
                                <dd class="field-model-number"></dd>
                                <dt><s:text name="smartpointdetail.dialog.deviceInformation.mac"/></dt>
                                <dd class="field-mac"></dd>
                                <dt><s:text name="smartpointdetail.dialog.deviceInformation.networkStatus"/></dt>
                                <dd class="field-network-status"></dd>
                                <dt><s:text name="smartpointdetail.dialog.deviceInformation.lastStaus"/></dt>
                                <dd class="field-last-status"></dd>
                                <dt><s:text name="smartpointdetail.dialog.deviceInformation.dateAdded"/></dt>
                                <dd class="field-date-added"></dd>
                            </dl>     
                        </div>
                        <div class="device-image yui-u">
                        	<img src="../assets/images/FPO-han-device.png" width="173" height="115" />
                        </div>
					</div>
                    <!-- END: Step -->
         </fieldset>		
	</form>
</div>