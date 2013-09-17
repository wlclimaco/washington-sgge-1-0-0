<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags'%>

<sec:authorize access="hasAnyRole('ROLE_EPM_SERVICE_ELECTRIC', 'ROLE_EPM_SERVICE_WATER', 'ROLE_EPM_SERVICE_GAS') and 
	hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR') and 
		!hasAnyRole('ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">

	<div class="content-inner">
		<div id="yui-main">
	  		<div class="content-header">
	  			<h1 id="group-create-title"></h1>
	  			<p id="group-create-description" class="description"></p>
			</div>
			<div id="create-group">
				<div class="create-action-container">
					<a id="back-group" href="group"><spring:message code="groupcreate.page.backgroups"/></a>&nbsp;&nbsp;
				</div>
				<div class="yui-gc">
		 			<div class="yui-u first">
		 				<form id="group-form" name="createGroupForm" method="post" action="group/upload" enctype="multipart/form-data">
							<fieldset>
								<div class="ui-state-error ui-corner-all hide">
									<p><span class="ui-icon ui-icon-alert"></span>
									<spring:message code="groupcreate.page.error"/>
								</div>
								<ul>
									<li class="highlight">
										<label><spring:message code="groupcreate.page.grouptype"/><span class="required">*</span></label>
										<select id="type" name="type" class="validate[required]" size="1">
											<option value=""><spring:message code="commons.pages.selectType"/></option>
											<option value="4"><spring:message code="commons.pages.billing"/></option>
											<option value="6"><spring:message code="commons.pages.operations"/></option>
											<option value="7"><spring:message code="commons.pages.allOthers"/></option>
										</select>
									</li>
									<li id="meterTypeContent">
										<label><spring:message code="filter.device.devicetype.label"/><span class="required">*</span></label>
										<select id="meterType" name="deviceType" class="validate[required]" size="1">
											<option value=""><spring:message code="commons.pages.selectType"/></option>
											<option value="IHD"><spring:message code="commons.pages.inHomeDisplays" /></option>
											<option value="LCM"><spring:message code="smartpoint.page.tab.sensus_load_control"/></option>
											<option value="ELECTRIC_METER"><spring:message code="commons.pages.Meters"/></option>
											<option value="THERMOSTAT"><spring:message code="commons.pages.thermostats" /></option>
										</select>
									</li>
									<li>
		                            	<label for="groupNameCreate"><spring:message code="commons.pages.groupName"/><span class="required">*</span></label>
		                            	<input type="hidden" name="id" id="group-id" tabindex="1" value="">
		                            	<label id="total-smartpoint" class="hidden"></label>
		                            	<input type="hidden" name="groupOldName" id="group-old-name" tabindex="1" value="">
		                            	<input type="text" name="name" id="group-name-create" tabindex="1" class="validate[required,custom[noSpecialCaracters],maxSize[100]] long" value="">
		                            	<input type="hidden" name="monitored" id="b-monitored" />
									</li>
									<li>
			                        	<label for="groupDescription"><spring:message code="commons.pages.descriptionTwo"/></label>
			                        	<textarea name="description" id="group-description" tabindex="2" class="validate[maxSize[150],custom[noSpecialCaracters]] long"></textarea>
									</li>
								</ul>
							</fieldset>
							<fieldset >
								<legend>
									<a id="click" href="#" class="text-button spindown">
										<span class="ui-icon-triangle-1-e ui-icon"></span>
										<span id="legend-device"></span>
									</a>
								</legend>
								<div id="toggleGroup">
									<p class="desc hide"><spring:message code="groupcreate.page.addsmartpointdesc"/></p>
			 						<ul>

			 							<li id="group-devices" class="highlight hide">
											<label><spring:message code="groupcreate.page.devices" /></label>
											<div class="read-only">
												<span id="total-devices"></span>
												<spring:message code="groupcreate.page.devicesInGroup" />
												<!-- TODO correct URL for to show device -->
												<a class="button-link launch alist" href="#">
													<spring:message code="groupcreate.page.viewInList" />
												</a>
											</div>
			                            </li>

			                            <li id="add-replace-devices" class="hide">
											<label for="add_replace"><spring:message code="groupcreate.page.addOrReplace" /></label>
											<select class="shorter" name="groupAction">
												<option value="1"><spring:message code="commons.pages.addTo" /></option>
												<option value="2"><spring:message code="commons.pages.replace" /></option>
											</select>
											<spring:message code="groupcreate.page.smartPointsGroup" />
										</li>

										<li id="group-devices-create" class="highlight">
											<label for="upload-ids"><spring:message code="commons.pages.upload.ids" /></label>
											<div class="radio sui-padv">
												<input type="radio" name="uploadType" id="upload-type-meter" value="<spring:message code="commons.pages.device_id"/>" checked="checked">
												<label for="upload-type-meter"><spring:message code="commons.pages.device_id"/></label>
												<input type="radio" name="uploadType" id="upload-type-flexnet" value="<spring:message code="commons.pages.network_address"/>">
												<label for="upload-type-flexnet"><spring:message code="commons.pages.network_address"/></label>
											</div>
										</li>

										<li>
											<label for="upload"><spring:message code="commons.pages.upload.div.upload"/></label>
											<input type="file" name="upload" id="upload" class="validate[custom[onlyCsv]]"/>
											<span class="note"><spring:message code="commons.pages.uploadGroup.span.note"/></span>
										</li>

										<li class="or">
											<small><spring:message code="commons.pages.OR"/></small>
										</li>

										<li>
											<label for="upload-list"><spring:message code="commons.pages.upload.div.copy_paste"/></label>
											<textarea name="deviceList" id="upload-list" rows="8" cols="20" class="long"></textarea>
										</li>

										<li class="row-pad"><span class="note"><spring:message code="groupcreate.page.groupDescr"/></span></li>

									</ul>
								</div>
								<ul>
									<li class="submit-row-pad">
										<a id="create-group-button" href="#" class="button submit-form"><spring:message code="commons.pages.create" /></a>
			 							<spring:message code="commons.pages.or"/>
			 							<a id="ajax-button" href="group" class="cancel"><spring:message code="commons.pages.cancel" /></a>
									</li>
								</ul>
							</fieldset>
						</form>
						<div class="yui-u"></div>
					</div>
				</div>
			</div>
			<div id="upload-dialog"></div>
		</div>
	</div>

	<script src="scripts/pages/group/group_create_main.js" ></script>
	<jsp:include page="../../scripts/pages/group/group_create_init.js.jsp" flush="true" />

</sec:authorize>