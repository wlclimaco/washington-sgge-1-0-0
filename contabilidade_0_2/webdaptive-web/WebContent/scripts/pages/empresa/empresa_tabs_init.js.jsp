<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>
<script type="text/javascript">

$(document).ready(function()
{

	var iLocationId = qat.util.page.fnCheckXSS($.address.parameter("locationId")) ? null : $.address.parameter("locationId");

	//Attach Links to tabs
	$("#infoTab").attr('href', "empresa/view/info?locationId=" + iLocationId);
	$('#pricingTab').attr('href',"funcionario/?locationId=" + iLocationId);

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
