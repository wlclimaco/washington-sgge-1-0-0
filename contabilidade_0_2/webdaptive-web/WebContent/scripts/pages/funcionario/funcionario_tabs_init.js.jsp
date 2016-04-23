<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>

<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN')">

<script type="text/javascript">

$(document).ready(function()
{

	var iFuncionarioId = qat.util.page.fnCheckXSS($.address.parameter("funcionarioId")) ? null : $.address.parameter("funcionarioId");

	//Attach Links to tabs
	$("#infoTab").attr('href', "funcionario/view/info?funcionarioId=" + iFuncionarioId);
	$('#pricingTab').attr('href',"funcionario/?funcionarioId=" + iFuncionarioId);

	var sTab = $.address.parameter("tab");
	var iActiveTab;

	if (!$.qat.isNullOrUndefined(sTab) && sTab.length > 0) {
		iActiveTab = $('*[data-tab="' + sTab + '"]').parent().index();
	}

	else {
		var iActiveTab = 0;
	}

	$("#tabs").tabs({
		active : iActiveTab,

		beforeLoad : function(event, ui) {
			$.qat.progressBar.start();

			// Setting correct tab parameter to the url
			$.address.parameter("tab", ui.tab[0].childNodes[1].dataset.tab);
			sTab = $.address.parameter("tab");
			// update active tab
			iActiveTab = $('*[data-tab="' + sTab + '"]').parent().index();
		},

		load: function(event, ui) {
			$.qat.progressBar.stopGlobal();
		}
	});
});

</script>

</sec:authorize>