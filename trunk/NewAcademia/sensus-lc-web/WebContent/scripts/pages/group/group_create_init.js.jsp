<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>
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

	//Receives preloaded data
	<c:choose>
		<c:when test="${empty response}">
	    	var oPreLoadResponse = null;
	    </c:when>
	    <c:otherwise>
	    	var oPreLoadResponse = ${response};
	    </c:otherwise>
	</c:choose>

	//Get the groupId parameter from the url
	var iGroupId = $.address.parameter('groupId');


	//Verify if is the page of update or creation
	if (!iGroupId)
	{
		//Show title of Create Group
		$('#title-create-group').removeClass('hide');
	}
	else if($.sc.isValidPreLoad(oPreLoadResponse, true))
	{
		//Create buttom to update a group
		$('#group-create-button').val($.sc.locale("groupcreate.page.editgroup"));

		//Show title of Edit Group
		$('#title-edit-group').removeClass('hide');

		//Add id "update-group-form" to form
		$("#create-group-form").attr("id", "update-group-form");

		sensus.pages.groupInsert.fnFillGroup(oPreLoadResponse);
	}

    /*
	 * Validate the fields required at Create Group Page and send the data for Controller
	*/
	$("#create-group-form").validationEngine('attach', {
		validationEventTrigger: 'submit',
		onValidationComplete: function(form, status)
		{
			if (status)
			{
				var sGroupName = $("#group-name").val();
				sensus.pages.groupInsert.ajaxCall("api/group/insert", $.sc.locale("action.addsgroup.success",sGroupName,sensus.settings.smartpointUrl));
			}
		}
	});


    /*
	 * Validate the fields required at Update Group Page and send the data for Controller
	*/
	$("#update-group-form").validationEngine('attach', {
		validationEventTrigger: 'submit',
		onValidationComplete: function(form, status)
		{
			if (status)
			{
				var sGroupName = $("#group-name").val();
				sensus.pages.groupInsert.ajaxCall("api/group/update", $.sc.locale("action.updategroup.success",sGroupName,sensus.settings.smartpointUrl));

			}
		}
	});

	$.sc.stopGlobalProgressBar();
});
</script>
</sec:authorize>