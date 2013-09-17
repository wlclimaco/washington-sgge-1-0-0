<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<sec:authorize access="hasRole('ROLE_EPM_SERVICE_ELECTRIC') and 
	hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR', 'ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">

	<div id="send-han-text-message" class="action-dialog">
		<form id="createTextHanForm" name="createTextHanForm" action="" method="post">
			<fieldset id="" class="create-action-container send-han-message">
				<input type="hidden" value="" id="currentDate" />
				<ul>
					<li>
						<p class="desc"><spring:message code="systemintelligence.page.event.hanDescription"/></p>
						<div id="notes" class="sui-pad">
						  <textarea id="textMessageHan"  name="textMessageHan" class="validate[required,maxSize[127]] long" ></textarea>
						</div>
					</li>
					<li><label class="small"><spring:message code="commons.pages.DisplayOn"/>:<span class="required">*</span></label>
						<div class="radio">
							<input type="text" name="hanMessageDate" id="hanMessageDate" class="validate[required, custom[dateFormat]] datepicker short" value="${currentDate}"/>
							<spring:message code="commons.pages.at"/>
							<input type="text" name="hanMessageTime" id="hanMessageTime" class="validate[required, custom[timeFormat]] time short" />
							<span class="note timezone"><spring:message code="smartpointdetail.tabs.about.timeZone"/></span>
						</div>
					</li>
					<li class="highlight sui-pad1h">
						<spring:message code="systemintelligence.page.event.hanMessage"/>
						<input type="text" id="textMessageDuration" name="textMessageDuration" class="validate[required,max[1440],min[1]] date-short" value="15" >&nbsp;<spring:message code="commons.pages.minutes"/>.
					</li>
				 </ul>
			 </fieldset>
		</form>
	</div>

</sec:authorize>