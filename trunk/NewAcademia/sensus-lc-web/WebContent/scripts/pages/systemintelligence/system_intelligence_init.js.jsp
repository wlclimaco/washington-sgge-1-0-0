<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<sec:authorize access="hasAnyRole('ROLE_Role.Admin', 'ROLE_Role.System Operator', 'ROLE_Role.Customer Support', 'ROLE_Role.Billing Manager')">
<script type="text/javascript">
/**
 * @namespace     sensus.pages.systemintelligence
 * @description   The init namespace for the Tag System Intelligence.
 * @fileoverview  Initializes the systemIntelligence page.
 * @author        Rodolfo Alves
 *
 */
$(document).ready(function() {

	var	$load = $('#load'),
		$tabContent = $('#tabs-content'),
		sPath = $.address.path(),
		aTabsPath = {
			schedule : "schedule",
			process : "process"
	};

	// tabs event
	$('.tabs li a').click(function(e) {
		e.preventDefault();

		$.sc.startProgressBar();
		var $this = $(this),
			sMessage = $.address.parameter("message"),
			sUrlTab = "systemintelligence/" + aTabsPath[$this.attr('id')];

		if (sMessage)
		{
			sUrlTab = "systemintelligence/" + sUrlTab + "?" + $.address.queryString();
		}

		$.sc.loadTab($.extend({}, sensus.commons.lib.ajax.param,
			{
				sUrl : sUrlTab,
				$container : $load,
				$container_tabs : $tabContent,
				$element : $this,
				bTab : true
				<c:if test="${param.initialLoad == 'false'}">
					,bInitialLoad : false
				</c:if>
			}
		));
		$.sc.stopProgressBar();
	});

	// set first load page
	if (!$.sc.isNullOrUndefined(sPath) && sPath != "/systemintelligence/")
	{
		var aPath = sPath.split("/");
		sPath = aPath[aPath.length - 1];
	}
	else
	{
		sPath = "process";
	}

	$("#" + sPath).click();

	$.sc.stopGlobalProgressBar();
});
</script>
</sec:authorize>