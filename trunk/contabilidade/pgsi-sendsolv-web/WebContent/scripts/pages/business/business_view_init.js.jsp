<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags'%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>

<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN', 'ROLE_CSR')">

<script type="text/javascript">

var oBusiness = null;
if (oPreLoadResponse.hasOwnProperty("organizationList")) {
	oBusiness = oPreLoadResponse.organizationList[0];
}

else if (oPreLoadResponse.hasOwnProperty('locationList')) {
	oBusiness = oPreLoadResponse.locationList[0];
}

$(document).ready(function() {

	$('#edit-business').click(function(e)
	{
		var nBusinessType= parseInt($("#business-type").val());

		// Check for Organization or Location

		e.preventDefault();


 		pgsi.util.actiondialog.launchActionDialog (
 			"insUpdBusiness",
 			pgsi.pages.business.dialogSettings.insUpdBusiness(
 				$('#business-id').val(),
 				$('#company-name-field').text(),
 				nBusinessType)
 		);

	});

	$("#edit-risk").click(function(event)
	{
		event.preventDefault();

		var oRequest = new RiskMaintenanceRequest({
			risk : pgsi.pages.risk.view.fillObject()
		});

		pgsi.util.actiondialog.launchActionDialog("insUpdRisk", pgsi.pages.business.dialogSettings.insUpdRisk(parseInt($('#business-id').val(),10), $('#company-name-field').text(), oRequest));
	});


	// Hover effects for risk, notes, contact
	$("#business-view").find("section").on("mouseenter", ".box", function() {

		// Get box height/width value
		var sHeight = $(this).height();

		// Showing long text version for boxes
		$(this).find('.text_here').addClass("hide");
		$(this).find(".full-text").removeClass("hide");

		$(this).addClass("hover");

		// Custom positioning for documents
		if ($(this).parents('section').hasClass("documents")) {
			// Get box height/width value after hover class applied
			var sHoverHeight = $(this).height();

			var nHeightDifference = parseInt(sHoverHeight) - parseInt(sHeight);

			// Positioning box above to don't break layout
			$(this).css({"margin-top":"-" + nHeightDifference + "px" });
		}

	});

	$("#business-view").find("section").on("mouseleave", ".box", function() {
		// Hiding long text version for boxes
		$(this).find('.text_here').removeClass("hide");
		$(this).find(".full-text").addClass("hide");

		$(this).removeClass("hover");

		// Custom positioning for documents
		if ($(this).parents('section').hasClass("documents")) {
			$(this).css({"margin-top":"0" });
		}
	});

	$('#status-template').on("click", "a.active, a.deactivate, a.delete", function(e) {

		e.preventDefault();

		if (pgsi.util.page.fnIsSDNFlagged(oBusiness.sdnstatus)) {
			return;
		}

		if($(this).hasClass('active')){


			if(parseInt($('#business-type').val(),10) === 1 ){
				pgsi.util.page.fnUpdateStatus('api/organization/fetch',parseInt($('#business-id').val(),10),'organization',1,null,"Activate Organization for "+ $('#company-name').text(),"<span>"+$.pgsi.locale.get("pages.person.dialog.status.question",$.pgsi.locale.get("pages.view.activate"),"Organization")+"<span>");


			}else{
				pgsi.util.page.fnUpdateStatus('api/location/fetch',parseInt($('#business-id').val(),10),'location',1,null,"Activate Location for "+ $('#company-name').text(),"<span>"+$.pgsi.locale.get("pages.person.dialog.status.question",$.pgsi.locale.get("pages.view.activate"),"Location")+"<span>");

			}

		}

		else if($(this).hasClass('deactivate')){


			if(parseInt($('#business-type').val(),10) === 1 ){
				pgsi.util.page.fnUpdateStatus('api/organization/fetch',parseInt($('#business-id').val(),10),'organization',2,null,"Deactivate Organization for "+ $('#company-name').text(),"<span>"+$.pgsi.locale.get("pages.person.dialog.status.question",$.pgsi.locale.get("pages.view.deactivate"),"Organization")+"<span>");
			}else{
				pgsi.util.page.fnUpdateStatus('api/location/fetch',parseInt($('#business-id').val(),10),'location',2,null,"Activate Organization for "+ $('#company-name').text(),"<span>"+$.pgsi.locale.get("pages.person.dialog.status.question",$.pgsi.locale.get("pages.view.activate"),"Organization")+"<span>");
			}

		}

		else if($(this).hasClass('delete')){

			if(parseInt($('#business-type').val(),10) === 1 ){
				var oRequest = new OrganizationMaintenanceRequest({organization : {id : parseInt($('#business-id').val(),10), modelAction : "DELETE"}}),
				sUrlReturn = "organization",
				sUrlDelete = "api/organization/delete",
				stitle     = $.pgsi.locale.get("pages.organization.dialog.title", $('#company-name').text()),
				sText      = $.pgsi.locale.get("commons.pages.erroView", $.pgsi.locale.get("commons.pages.organization"));


			}else{
				var oRequest = new LocationMaintenanceRequest({location : {id : parseInt($('#business-id').val(),10), modelAction : "DELETE"}}),
			    sUrlReturn = "location",
			    sUrlDelete = "api/location/delete",
				stitle     = $.pgsi.locale.get("pages.location.dialog.title", $('#company-name').text()),
				sText      = $.pgsi.locale.get("commons.pages.erroView", $.pgsi.locale.get("commons.title.location"));
			}

			var fnCallBack = function(oResponse) {

				if (oResponse.operationSuccess == true) {

					$.pgsi.pageLoader.load({
						url : sUrlReturn,
						$content : $("#load"),
						bStartProgressBar : false
					});

				}else{
					pgsi.pages.sendsolv.fnDialogMessageError("",{},oResponse,null,$.pgsi.locale.get("commons.dialog.error.title"),true);
				}
			}

			pgsi.util.actiondialog.launchActionDialog(
				"deleteDialog",
				 pgsi.pages.business.dialogSettings.deleteDialog(
					 sUrlDelete,
					 oRequest,
					 stitle,
					 fnCallBack,
					 sText
				 ));
		}

	});


});

</script>

</sec:authorize>