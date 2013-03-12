<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<sec:authorize access="hasAnyRole('ROLE_Role.Admin', 'ROLE_Role.System Operator', 'ROLE_Role.Customer Support', 'ROLE_Role.Billing Manager')">
<script type="text/javascript">
/**
 * @namespace sensus.pages.group
 * @description The init namespace for the Group Create Page.
 */

/**
 * @fileoverview Initializes the group page.
 *
 * @author Cristiane Cobo
 */
$(document).ready(function($) {

	//Stop the Progress Bar
	$.sc.stopProgressBar(null, true);


	//Get the groupId parameter from the url
	var iGroupId = $.address.parameter('groupId');


	//Verify if is the page of update or creation
	if (iGroupId) {

		var request = {action: "fetchById", groupId: iGroupId};

		//Create buttom to update a group
		$('#group-create-button').val($.sc.locale("groupcreate.page.editgroup"));

		//Show title of Edit Group
		$('#title-edit-group').removeClass('hide');

		//Add id "update-group-form" to form
		$("#create-group-form").attr("id", "update-group-form");

		//Performs a search from the id of group and populate the form with the data received
		$.sc.getJson("api/group/fetch", request, false, sensus.pages.groupInsert.fnFillGroup);

	}else{
		//Show title of Create Group
		$('#title-create-group').removeClass('hide');
	}

    /*
	 * Validate the fields required at Create Group Page and send the data for Controller
	*/
	$("#create-group-form").validationEngine('attach', {
		validationEventTrigger: 'submit',
		onValidationComplete: function(form, status) {

			if (status) {
				var sGroupName = $("#group-name").val();
				sensus.pages.groupInsert.ajaxCall("api/group/insert", $.sc.locale("action.addsgroup.success",sGroupName,'light/'));
			}
		}
	});


    /*
	 * Validate the fields required at Update Group Page and send the data for Controller
	*/
	$("#update-group-form").validationEngine('attach', {
		validationEventTrigger: 'submit',
		onValidationComplete: function(form, status) {

			if (status) {
				var sGroupName = $("#group-name").val();
				sensus.pages.groupInsert.ajaxCall("api/group/update", $.sc.locale("action.updategroup.success",sGroupName,'light/'));

			}
		}
	});

	$.sc.stopProgressBar(null, true);
});
</script>
</sec:authorize>