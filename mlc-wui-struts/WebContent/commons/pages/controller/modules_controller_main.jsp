 <%@ page language="java" isELIgnored="false" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix="s" uri="/struts-tags"%>

	<!-- Header Tabs Device Detail -->
	<div id="tabs" class="yui-ge point-type-navigation header rounded-top-corners">
		<div class="yui-u first">
		    <a id="ajax-button" href="list.action#/smartpoint/ajax.list.action" class="back url"><s:text name="smartpointdetail.page.backlist"/></a>  
			<ul class="tabs">
				
				<!-- About this Device Tab -->
				<c:if test="${param.aboutThisDevice == 'tab'}">	
					<li class="about-this-device"><a id="about-deivce" href="smartpoint/ajax.tabs.smartpoint.detail.about.action" class="url"><s:text name="smartpointdetail.page.abouttab"/></a></li>
				</c:if>	
				
				<!-- Light Detail -->
				<c:if test="${param.lightDetail == 'tab'}">	
					<li class="about-this-device"><a id="about-deivce" href="smartpoint/ajax.tabs.smartpoint.detail.about.action" class="url smartpoint-detail active gradient-vert"><s:text name="smartpointdetail.page.lightdetail"/></a></li>
				</c:if>	
				
				<!-- About Load Profile Tab -->
				<c:if test="${param.loadProfile == 'tab'}">
					<li><a href="smartpointdetail/ajax.tabs.smartpoint.detail.loadprofile.action" class="url"><s:text name="smartpointdetail.page.loadprofiletab"/></a></li>
				</c:if>
	
				<!-- About Snap Shot Tab -->
				<c:if test="${param.snapShot == 'tab'}">
					<li><a id="touReads" href="smartpointdetail/ajax.tabs.smartpoint.detail.snapshot.action" class="url"><s:text name="smartpoint.page.snapshot"/></a></li>
				</c:if>
				
				<!-- About History Tab -->
				<c:if test="${param.history == 'tab'}">
					<li><a href="smartpointdetail/ajax.tabs.smartpoint.detail.history.action" class="url"><s:text name="smartpointdetail.page.historytab"/></a></li>
				</c:if>

				<!-- Light Eco-Mode Tab -->
				<c:if test="${param.lightHistory == 'tab'}">
					<c:if test="${userContext.getTenant().getEcoModeDisable() == false}">
						<li><a href="smartpoint/ajax.tabs.smartpoint.detail.ecoMode.action" class="url"><s:text name="smartpointdetail.ecoMode.ecoModeTab"/></a></li>
					</c:if>
				</c:if>
				
				<!-- Light History Tab -->
				<c:if test="${param.lightHistory == 'tab'}">
					<li><a href="smartpoint/ajax.tabs.smartpoint.detail.history.action" class="url"><s:text name="smartpointdetail.page.historytab"/></a></li>
				</c:if>
				
			</ul>
		</div>
	</div>
	
	
	<div class="content-inner">
	
	
		<div id="comunication-messaging" class="hide">
			<span class="message">
				<span class="sui-icon sui-icon-alert"></span>
				<h3>${lightView.statusExceptions[0].message}</h3>
			</span>
		</div> 	
	
		<div id="yui-main">
		
			<div id="messaging-main" class="messaging messaging-smartpoint-detail"><span class="message"></span><a href="" class="remove"><s:text name="message.action.close" /></a></div>
					
			<div id="address" class="point-detail-container">
			
				<div class="stamp-smartpoint highlight">
					<s:text name="commons.pages.dataReflects"/>
					<strong><span id="receivedDate"></span></strong>
					<span class="messaging-warning rounded ui-state-highlight" style="display: none;"><s:text name="smartpointdetail.page.timeZoneInfo"/> <span id="meter-timeZone"></span>.</span>
				</div>
				
				<!-- Start Summaray Data -->
				<div id="detail-header-container" class="ss-widget-table-summary-kpi">
					<table class="summary-kpi">
						<tr>
							
							<!-- Device Information -->
							<c:if test="${param.deviceInformation == 'summaryData'}">
							   <td class="first">
								   <div id="detail-header-pane-1" class="detail-header">
									<h1>
										<s:text name="commons.pages.meterId"/>
										<strong># </strong><strong class="meterId" id="meter-id"></strong>
									</h1>
									<p class="description-address"></p>
									<p class="description-id"><s:text name="smartpointdetail.page.flexnetid"/><span class="description-id" id="flexnet-id"></span></p>
								  </div>                      
							   </td>
						    </c:if>
							
							<!-- On Glass Read -->
							<c:if test="${param.onGlassRead == 'summaryData'}">						   
								<td id="reads" title="">
									<!-- START meter plate read -->
									<div id="meter-plate" class="">
									</div>
									<small class="onglass-read"><s:text name="smartpointdetail.page.onglassread"/></small>
								   <!-- END meter plate read -->
							   </td>
							</c:if>
						   
						   <!-- On Glass Read -->
							<c:if test="${param.hour == 'summaryData'}">		
							   <td id="hour" title="">
									<s:text name="commons.pages.hour" /> 
									<span id="smartpointdetail-page-hour"></span>
							   </td>
							</c:if> 
						   
						   <!-- On Glass Read -->
							<c:if test="${param.month == 'summaryData'}">		
								<td id="month" title="">
									<s:text name="commons.pages.month" />
									<span id="smartpointdetail-page-month"></span>
								</td>
							</c:if>	
						   
						   <!-- On Glass Read -->
							<c:if test="${param.peakDemand == 'summaryData'}">		
								<td id="peakDemand" title="">
									<s:text name="commons.pages.peakDemand" />
									<span id="smartpointdetail-page-peakdemand"></span>
								</td>
							</c:if>
						   <c:if test="${param.actions == 'summaryData'}">	
								<td class="last">
									<sec:authorize access="hasAnyRole('ROLE_Role.EPM_ADMIN', 'ROLE_Role.EPM_SYSTEM_OPERATOR')">										
										<a tabindex="1" href="#actions-options" class="fg-button fg-button-icon-right ui-widget ui-state-default ui-corner-all" id="actions-button">
											<span class="ui-icon ui-icon-triangle-1-s"></span><s:text name="commons.pages.actions"/>
										</a>
										<!-- START SmartPoint Actions-->
										<div id="actions-options" class="hidden">
											<ul>
												<s:iterator status="actionsType" value="%{listDemandReset}">
													<li>
														<a href="#" name="<s:property />"><s:property value="%{actionType}"/></a>
														<ul class="action-min">
															<s:iterator status="actions" value="%{listIdValuePair}">
																<li><a href="#" name="<s:property value='id' />" id="<s:property value='value' />"><s:property value="value" /></a></li>
															</s:iterator>	
														</ul>
													</li>
												</s:iterator>
											</ul>
										</div> 
										<!--END SmartPoint Actions -->
									</sec:authorize>
							   </td>
						   </c:if>
						   
							<!-- Light Information -->
							<c:if test="${param.lightInformation == 'summaryData'}">
								<td class="first">
									<div id="detail-header-pane-1" class="detail-header">
											<h1><s:text name="smartpointdetail.page.pole" /> <strong id="pole-id"></strong></h1>
											<p class="description-address">${lightView.address}</p>
											<p><s:text name="smartpoint.search.flexNetID" /> #<span class="description-id"></span></p>
								   </div>                      
								</td>							   
						    </c:if>	

							<!-- Light Intensity  -->
							<c:if test="${param.lightState == 'summaryData'}">
							<td>
								<s:text name="smartpointdetail.status.lightonoff" /> 
								<span id="light-state">
								</span>
								<span id="light-dim" style="display:none"></span>
								<span id="light-levels" style="display:none"></span>
							</td>							   
						    </c:if>

							<!-- Light Status  -->
							<c:if test="${param.lightStatus == 'summaryData'}">
							<td><s:text name="smartpointdetail.status.light" />
								<span id="light-status">
								</span>
							</td>
							</c:if>	

							<!-- Light Status Message -->
							<c:if test="${param.statusMessage == 'summaryData'}">
							<td>
								<s:text name="smartpointdetail.status.message" /> 
								<span id="date-message"></span> 
								<div style="display:block">
									<small id="hour-message"></small>
									<small id="time-message"></small>
								</div>
							</td>
							</c:if>		
						   	
							<!-- Light Alarm Count -->
							<c:if test="${param.alarmCount == 'summaryData'}">
							<td>
								<s:text name="smartpointdetail.status.alarms" />  
								<span id="count-alarms"></span> 
								<small id="alarm-description">
									<s:text name="smartpointdetail.page.noalarms" />
								</small>
							</td>
							</c:if>		

							<!-- Light Warning Count -->
							<c:if test="${param.warningCount == 'summaryData'}">
							<td>
								<s:text name="smartpointdetail.status.warnings" /> 
								<span id="count-warnings"></span> 
								<small id="warning-description">
									<s:text name="smartpointdetail.page.nowarnings" />
								</small>
							</td>
							</c:if>

							<!-- Light EcoMode -->
							<c:if test="${param.ecoMode == 'summaryData'}">
								<c:if test="${userContext.getTenant().getEcoModeDisable() == false}">
									<td class="eco-economy">
										<s:text name="smartpoint.detail.ecoMode.EcoMode" />
										<span id="ecomode-percent"></span>
										<small>
											
										</small>
									</td>
								</c:if>
							</c:if>

							<!-- Light Actions -->
							<c:if test="${param.lightActions == 'summaryData'}">
							<td class="last">
                               	<!-- START SmartPoint Actions -->
                                <a tabindex="0" id="lightsOnOff" href="#" class="fg-button ui-widget ui-state-default ui-corner-all"><s:text name="smartpoint.actions.switchDim" /></a>      
                                   <sec:authorize access="hasAnyRole('ROLE_Role.Admin', 'ROLE_Role.System Operator', 'ROLE_Role.Customer Support')">
									<a tabindex="2" href="#actions-options" class="fg-button fg-button-icon-right ui-widget ui-state-default ui-corner-all" id="actions-button"><span class="ui-icon ui-icon-triangle-1-s"></span><s:text name="smartpointdetail.page.moreactions" /></a>
									<div id="actions-options" class="hidden">
										<ul>
											<s:iterator value="actionList">
												<li>	
													<s:if test="%{value.toLowerCase() == getText('smartpoint.actions.getDataLight').toLowerCase()}">
														<sec:authorize access="hasAnyRole('ROLE_Role.Admin', 'ROLE_Role.System Operator', 'ROLE_Role.Customer Support')">
															<s:a href="#" id="%{id}" title="%{value}" tooltip="%{value}" onclick="javascript:return false;"><s:property value="%{value}" /></s:a>
														</sec:authorize>
													</s:if>
													<s:elseif test="%{value.toLowerCase() == getText('smartpoint.actions.clearManual').toLowerCase() }">
														<sec:authorize access="hasAnyRole('ROLE_Role.Admin', 'ROLE_Role.System Operator')">
															<s:a href="#" id="%{id}" title="%{value}" tooltip="%{value}" onclick="javascript:return false;"><s:property value="%{value}" /></s:a>
														</sec:authorize>
													</s:elseif>
													<s:else>
														<sec:authorize access="hasAnyRole('ROLE_Role.Admin')">
															<s:a href="#" id="%{id}" title="%{value}" tooltip="%{value}" onclick="javascript:return false;"><s:property value="%{value}" /></s:a>
														</sec:authorize>
													</s:else>
												</li>
											</s:iterator>
										</ul>
									  </div> 
								</sec:authorize>
							</td>
							</c:if>



							
					   </tr>
				   </table>
				</div>
				<!-- End Summaray Data -->
				
			</div>
		
		</div>
	
		<!-- Start Content -->
		<div id="tabs-content">
		
		
		</div>
		<!-- End Content -->
	
	</div>

	
