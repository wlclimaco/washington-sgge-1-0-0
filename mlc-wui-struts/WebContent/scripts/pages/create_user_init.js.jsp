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
	var nUserId         = $.address.parameter('userId');
	var	request         = {'inquiryPaginationRequest' : new inquiryPaginationRequest()};
	var	oElementChosen  = $(".chzn-select"); 
	var oSpinDownCild 	= $('.spindown_child');
	var oGroupSection	= $('.select-group, .summary, .all-desc-container, .overflow');
	var oAllLight		= $('#include-all-lights');
	var oSomeLights		= $('#include-some-lights');
	var oBubble			= $('.bubble');
	var oRoleAdmin		= $('#role-admin');
	var oRoles			= $('#role-operator, #role-support, #role-analytic ');
	
	sensus.util.page.initMessaging();

	// Click on Difinition open a dialog to give a tip to user about roles
	$(".click-form-definition-roles", "#roles").click(function() {
		sensus.util.actiondialog.launchActionDialog("roleDefinitions", sensus.pages.user.dialogSettings["roleDefinitions"]);
		return false;
	});

	
	$.ajaxValidator.fnDoCall(sensus.settings.fillGroups, request, false, sensus.pages.createUser.fnFillGroupsCallBack);


	if (nUserId) {
		var oSpanThis = $('span', this);
		
		$.ajaxValidator.fnDoCall(sensus.settings.fillUserPage, {'userRequest' : new userRequest(nUserId)}, false, sensus.pages.createUser.fnFillUser);
		
		//Add Validation at Select Group field
		oElementChosen.addClass('validate[required]');

		//Spindown
		oSpinDownCild.hide();

		oSpinDown.click(function(e) {
			e.preventDefault();
			$(this).parent().next(oSpinDown).toggle('blind',null,500);
			if (oSpanThis.hasClass('ui-icon-triangle-1-e')) {
				oSpanThis.switchClass('ui-icon-triangle-1-e', 'ui-icon-triangle-1-s', 500);
			} else {
				oSpanThis.switchClass('ui-icon-triangle-1-s','ui-icon-triangle-1-e', 500);
			}
		});

	} else {
		$('.content-header h1').html(sensus.locale.get('user.actions.createuser'));
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
			if (status) {
				if (sensus.pages.createUser.fnGroupsValidator()){
					sensus.pages.createUser.ajaxCall(sensus.settings.createUser, sensus.locale.get("action.adduser.success", $("#user-user").val()));
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
			if (status) {
				if (sensus.pages.createUser.fnGroupsValidator()){
					sensus.pages.createUser.ajaxCall(sensus.settings.updateUser, sensus.locale.get("action.updateuser.success", $("#user-user").val()));
				}
			}
		}
	});

	//Select All Groups
	$('.select-group .text_button').click(function(e) {
		e.preventDefault();
		var aGroupsId = [];
		
		oElementChosen.show().removeClass('chzn-done').removeAttr('id').next().remove();
		$('.chzn-select option').each(function(){

			$(this).prop('selected', true);
			aGroupsId.push({'id' : $(this).val() });

		});
		
		oElementChosen.chosen();
		$('.chzn-container').css('width', '324px');
		
		sensus.pages.createUser.fnCountSmartpoints(aGroupsId, true);
	});

	//Set up default select view	
	$('.register').validate();

	
	oAllLight.click(function (e) {
		$('.select-groupsformError').remove();
		oGroupSection.hide();
	});

	oSomeLights.click(function (e) {
		sensus.pages.createUser.loadIncludeLights();
	});
	
	if ($('#include-some-lights:checked')){
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
	$('#role-admin, #role-operator, #role-support, #role-analytic').change(function() {           	

		if (oRoleAdmin.is(':checked')) {

			$('#select_groups option').each(function() {
				$(this).attr('selected', false);
			});

			//disable grant access control
			$('#include-all-lights, #include-some-lights').attr("disabled", "disabled");
			oAllLight.attr('checked',true);
			
			oGroupSection.hide();

		} else {

			if (oRoles.is(':checked')) {
				//enable group access control
				oAllLight.removeAttr("disabled");
				oSomeLights.removeAttr("disabled");
			}

		}
	});

	oRoles.change(function() {      

		if ($(this).is(':checked') && oRoleAdmin.is(':not(:checked)'))  {
			//enable group access control
			oAllLight.removeAttr("disabled");
			oSomeLights.removeAttr("disabled");
		}
	});

	$('#detail-map-container').width('670px').height('350px');

	$('.map-col a').click(function(e) {
		e.preventDefault();

		if (parseInt($('.map-col strong').text()) > 0) {
			sensus.widgets.datatable.oTableSettings.id = "user-create";
			sensus.util.actiondialog.launchActionDialog("mapIt", sensus.widgets.datatable.dialogSettings.mapIt);
		}

	});
	
	if (!$('#include-some-lights').attr('checked')){
		oGroupSection.hide();
	}
	sensus.util.page.stopProgressBar(null,true);

});
</script>
</sec:authorize>