<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%-- START Header 2--%>
<div id="hd" class="yui-g">
	<%-- START Main application menu --%>

	<div class="yui-u first">
		<div id="sensus-menu">
			<div class="bd">
				<ul class="first-of-type">
					<sec:authorize access="hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR', 'ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">
						<li>
							<a id="menu-dashboard" href="dashboard"
								title="<spring:message code="commons.pages.dashboard"/>"><spring:message code="commons.pages.dashboard" />
							</a>
						</li>
					</sec:authorize>
					<sec:authorize access="hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR', 'ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">
						<li>
							<a id="menu-smartpoint" href="#"
								title="<spring:message code="commons.pages.smartPoints"/>"><spring:message code="commons.pages.smartPoints" />
							</a>
						</li>
					</sec:authorize>
					<sec:authorize access="hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR', 'ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">
						<li>
							<a id="menu-group" href="group"
								title="<spring:message code="commons.pages.Groups"/>"><spring:message code="commons.pages.Groups" />
							</a>
						</li>
					</sec:authorize>
					<sec:authorize access="hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR')	and !hasAnyRole('ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">
						<li>
							<a id="menu-systemintelligence" href="process"
								title="<spring:message code="commons.pages.systemintelligence"/>"><spring:message code="commons.pages.systemintelligence" />
							</a>
						</li>
					</sec:authorize>
				</ul>
			</div>
		</div>
	</div>
	<%-- END Main application menu --%>

	<%-- START Search Box--%>
	<div class="yui-gc">
  			<form id="search-form-container" class="rounded">
        			<div class="search-input-container yui-u first">
         				<div id="search-label-container">
         					<a tabindex="1" href="#labelOptions" class="fg-button fg-button-icon-right ui-widget ui-state-default" id="search-label">
          					<span class="ui-icon ui-icon-triangle-1-s"></span>
          					<input id="search-type" value="DEVICE_ID" type="hidden">
          					<span><spring:message code="commons.pages.deviceId"/></span>
          				</a>
	   				<div id="labelOptions" class="hidden">
              				<ul>
                  				<li class="hide">
                  					<a href="#" class="action-option" id="device-id">
                  						<input value="DEVICE_ID" type="hidden">
                  						<span><spring:message code="commons.pages.deviceId"/></span>
                  					</a>
                  				</li>
                  				<li>
                  					<a href="#" class="action-option" id="network-address">
                  						<input value="NETWORK_ADDRESS" type="hidden" />
                  						<span><spring:message code="commons.pages.networkAddress"/></span>
                  					</a>
                  				</li>
                  				<li>
                  					<a href="#" class="action-option" id="premise-id">
                  						<input value="PREMISE_ID" type="hidden" />
                  						<span><spring:message code="commons.pages.premiseId"/></span>
                  					</a>
                  				</li>
              				</ul>
         				</div>
  				</div>
        				<input type="text" id="search-text" value="" />
       			</div>
       			<div id="search-submit-container" class="yui-u">
          			<a href="#" id="search-submit" class="alist button">
          				<spring:message code="commons.pages.search"/>
          			</a>
           			<a href="savedSearch" id="saved" class="white nodeco alist">
           				<spring:message code="commons.pages.saved"/>
           			</a>
				</div>
		</form>
	</div>
	<%-- END Search Box--%>

</div>
<%-- END Header 2--%>

<%-- START Page Content --%>
<div id="bd" class="content-container">
	<div id="load"><%-- Page Content --%> </div>
</div>

<div id="action-dialog" style="display: none"></div>
<div id="action-dialog-lrp" style="display: none"></div>
<div id="dialog-monitor-dissmiss" style="display: none"></div>

<%-- System Messaging Bar --%>
<div id="system-messaging" style="display:none;">
	<div id="system-messaging-list">
		<ul>
			<li class="message-title">
				<spring:message code="commons.pages.message" />
			</li>
			<li id="rni-offline" class="system-message-label error rni-link" style="display:none;">
				<a rel="black" class="rounded bubble" value="<spring:message code="commons.pages.rniofflinemsg" />">
					<spring:message code="commons.pages.rnioffline"></spring:message> </a>
			</li>
			<li id="request-processing" class="system-message-label" style="display:none;">
				<a href="#" class="processing rounded">
					<spring:message code="commons.pages.recentRequests" /> <span id="long-running-process-size-p" class="count rounded hide"></span>
				</a>
			</li>
			<li id="request-complete" class="system-message-label" style="display:none;">
				<a href="#" class="rounded">
					<spring:message code="commons.pages.recentRequests" /> <span id="long-running-process-size-c" class="count rounded hide"></span>
				</a>
			</li>
			<li id="processing-size" class="hide"></li>
		</ul>
	</div>
</div>
<%-- END Page Content --%>

<div id="customize-filter" class="hide"></div>
<%-- END Main Document --%>