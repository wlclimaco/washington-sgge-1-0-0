<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<sec:authorize access="hasAnyRole('ROLE_Role.Admin', 'ROLE_Role.System Operator', 'ROLE_Role.Customer Support', 'ROLE_Role.Billing Manager')">
<script type="text/javascript">
/**
 * @namespace sensus.pages.user
 * @description The init namespace for the User Page.
 * @fileoverview Initializes the user page.
 *
 * @author Vinicius Scalon Ferreira
 */

$(document).ready(function() {

	var oSpinDown = $('.spindown');
	var nUserId        		= $.address.parameter('userId');
	var	request         	= new inquiryPaginationRequest();
	var	oElementChosen 		= $(".chzn-select");
	var oSpinDownCild 		= $('.spindown_child');
	var oGroupSection		= $('.select-group, .summary, .all-desc-container, .overflow');
	var oAllLight			= $('#include-all-lights');
	var oSomeLights			= $('#include-some-lights');
	var oBubble				= $('.bubble');
	var oRoleAdmin			= $('#role-admin');
	var oRoleAPI            = $('#role-api');
	var oRoles				= $('#role-operator, #role-support, #role-analytic ');
	var oPreLoadResponseGroup = null;
	var oPreLoadResponse      = null;

	$.ajax({
		url    : "subscribe_include",
		async  : false,
		success: function(data)
		{
			$("#subscribe-html").append(data);
		}
	});

	// Align Group select / map it
	$('.map-col a, .map-col span').css("display", "inline");

	// Click on Difinition open a dialog to give a tip to user about roles
	$(".click-form-definition-roles", "#roles").click(function() {
		sensus.util.actiondialog.launchActionDialog("roleDefinitions", sensus.pages.user.dialogSettings["roleDefinitions"]);
		return false;
	});

	request["action"] = "table";
	<c:choose>
		<c:when test="${empty group_response}">
	    	 oPreLoadResponseGroup = null;
	    </c:when>
	    <c:otherwise>
	    	 oPreLoadResponseGroup = ${group_response};
	    </c:otherwise>
	</c:choose>
	if($.sc.isValidPreLoad(oPreLoadResponseGroup, true))
	{
		sensus.pages.createUser.fnFillGroupsCallBack(oPreLoadResponseGroup);
	}
	else
	{
		$.sc.getJson("api/group/fetchall", request, false, sensus.pages.createUser.fnFillGroupsCallBack);
	}

	if (nUserId)
	{
		//	Create buttom to update a user
		$("#user-create-button").val($.sc.locale("usercreate.page.saveuser"));
		//	Add id "update-user-form" to form
		$("#user-list form").attr("id", "update-user-form");

		var oSpanThis = $('span', this);
		<c:choose>
			<c:when test="${empty response}">
		    	 oPreLoadResponse = null;
		    </c:when>
		    <c:otherwise>
		    	 oPreLoadResponse = ${response};
		    </c:otherwise>
		</c:choose>
		var req = {"id": nUserId};
		if($.sc.isValidPreLoad(oPreLoadResponse, true))
		{
			sensus.pages.createUser.fnFillUser(oPreLoadResponse);
		}
		else
		{
			$.sc.getJson("api/user/fetch", req, false, sensus.pages.createUser.fnFillUser);

		}
		//Add Validation at Select Group field
		oElementChosen.addClass('validate[required]');

		//Spindown
		oSpinDownCild.hide();

		oSpinDown.click(function(e)
		{
			e.preventDefault();
			$(this).parent().next(oSpinDown).toggle('blind',null,500);
			if (oSpanThis.hasClass('ui-icon-triangle-1-e'))
			{
				oSpanThis.switchClass('ui-icon-triangle-1-e', 'ui-icon-triangle-1-s', 500);
			}
			else
			{
				oSpanThis.switchClass('ui-icon-triangle-1-s','ui-icon-triangle-1-e', 500);
			}
		});

	}
	else
	{
		//	Create buttom to create a user
		$("#user-create-button").val($.sc.locale("usercreate.page.createuser"));

		//	Add id "create-user-form" to form
		$("#user-list form").attr("id", "create-user-form");

		$('.content-header h1').html($.sc.locale('user.actions.createuser'));
		oSpinDownCild.show();
		var oSpin = oSpinDown.parent();
		oSpin.html(oSpin.text());
	}

    /*
	 * Validate the fields required at Create User Page
	 */
	$("#create-user-form").validationEngine('attach', {
		validationEventTrigger : 'submit',
		onValidationComplete   : function(form, status) {
			if (status)
			{
				if (sensus.pages.createUser.fnGroupsValidator())
				{
					sensus.pages.createUser.ajaxCall("api/user/insert", $.sc.locale("action.adduser.success", $("#user-user").val()));
				}
			}
		}
	});

    /*
	 * Validate the fields required at Update User Page
	 */
	$("#update-user-form").validationEngine('attach', {
		validationEventTrigger : 'submit',
		onValidationComplete   : function(form, status) {
			if (status)
			{
				if (sensus.pages.createUser.fnGroupsValidator())
				{
					sensus.pages.createUser.ajaxCall("api/user/update", $.sc.locale("action.updateuser.success", $("#user-user").val()));
				}
			}
		}
	});

	 /*
	 * Get Groups
	 */
	var fnGetGroups = function(){
		var aGroupsId = [];

		oElementChosen.show().removeClass('chzn-done').removeAttr('id').next().remove();
		$('.chzn-select option').each(function(){
			$(this).prop('selected', true);
			aGroupsId.push({'id' : $(this).val() });
		});

		sensus.pages.createUser.fnCountSmartpoints(aGroupsId, true);
	};

	/*
	 * Load Chosen and count user lights
	 */
	var fnChosenLightCount = function()
	{
		//Deselect only group
		$(".chzn-choices a").click(function(e){

			var aGroupsId = [];

			var sGroup = $(this).attr("title");
			var iId = parseInt($(this).val());


			$('.chzn-select option').each(function(){
				if (sGroup == $(this).html())
				{
					$(this).prop('selected', false);
					aGroupsId.push({'id' : $(this).val() });
				}
			});
			aGroupsId.splice($.inArray(iId, aGroupsId), 1);
			sensus.pages.createUser.fnCountSmartpoints(aGroupsId, true);
		});

	};

	//Select All Groups
	$('.select-group .text_button').click(function(e) {
		e.preventDefault();
		fnGetGroups();
		fnChosenLightCount();
		oElementChosen.chosen();
	});

	$('.chzn-choices .search-field input').unbind("keyup");

	//Set up default select view
	$('.register').validate();

	oAllLight.click(function (e) {
		$('.select-groupsformError').remove();
		oGroupSection.hide();
	});

	oSomeLights.click(function (e) {
		sensus.pages.createUser.loadIncludeLights();
	});

	if ($('#include-some-lights:checked'))
	{
		sensus.pages.createUser.loadIncludeLights();
	}

	//Bubbles
	oBubble.CreateBubblePopup({
		position       : 'top',
		align	       : 'center',
		innerHtml      : oBubble.attr('title'),
		width          : '200px',
		themeMargins   : {
			total       : '5px',
			difference  : '5px'
		},
		innerHtmlStyle : {
			color         : ($(this).attr('id')!='azure' ? '#000000' : '#333333'),
			'text-align'  :'center'
		},
		themeName      : 	'black',
		themePath      : 	'images/jquerybubblepopup-theme'
	});

	oBubble.removeAttr('title');

	//disable group selection if admin is selected
	$('#role-admin, #role-operator, #role-support, #role-analytic, #role-api').change(function()
	{
		if (oRoleAdmin.is(':checked') || oRoleAPI.is(':checked'))
		{
			$('#select_groups option').each(function()
			{
				$(this).attr('selected', false);
			});

			//disable grant access control
			$('#include-all-lights, #include-some-lights').attr("disabled", "disabled");
			oAllLight.attr('checked',true);

			oGroupSection.hide();
		}
		else
		{
			if (oRoles.is(':checked'))
			{
				//enable group access control
				oAllLight.removeAttr("disabled");
				oSomeLights.removeAttr("disabled");
			}
		}

		if(oRoleAPI.is(':checked'))
		{
			sensus.pages.createUser.bSaveSettings = false;
			$("#subscribe-fieldset").hide();
		}
		else
		{
			sensus.pages.createUser.bSaveSettings = true;
			$("#subscribe-fieldset").show();
		}


	});

	oRoles.change(function() {

		if ($(this).is(':checked') && oRoleAdmin.is(':not(:checked)'))
		{
			//enable group access control
			oAllLight.removeAttr("disabled");
			oSomeLights.removeAttr("disabled");
		}
	});

	$('#detail-map-container').width('670px').height('350px');

	$('.map-col a').click(function(e) {
		e.preventDefault();

		if (parseInt($('.map-col strong').text()) > 0)
		{
			sensus.widgets.datatable.oTableSettings.id = "user-create";
			$.sc.launchActionDialog("mapIt", sensus.widgets.datatable.dialogSettings.mapIt);
		}
	});

	if (!$('#include-some-lights').attr('checked'))
	{
		oGroupSection.hide();
	}

	//Open subscribe dialog
	$("#subscribeTable").find(":checkbox").click(function(e)
	{
		e.stopPropagation();
		sensus.pages.profile.fnCallSubscribe($(this) , sensus.settings.SHOW_SUBSCRIPTION_DIALOG == "true");
	});

	$.sc.stopGlobalProgressBar();
});
</script>
</sec:authorize>