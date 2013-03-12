sensus.commons.modules.content.lightGroups = {

	fnLoadGroups : function(oFirstMeter){

	var sHtmlGroups = '';
	var aGroups = oFirstMeter.groups;

	if(aGroups){

		for (g in aGroups) {

			if (aGroups.hasOwnProperty(g)) {

				sHtmlGroups += '<tr><td class="group-name">'+aGroups[g].name+'</td>';
				if (sensus.settings.userContext.userRole == "ROLE_Role.Admin"){

					sHtmlGroups += '<td><a id="remove-groups" class="delete right" href="#"><span class="hide">'+aGroups[g].id+'</span>'+$.sc.locale("smartpointdetail.actiongroups.remove")+'</a></td>';

				}
				sHtmlGroups += '</tr>';

			}

		}

		if(aGroups.length < 1){

			$("#groups .blankslate").show();
			$('#add-to-group-link').show();

		} else if(aGroups.length > 4){

			$('#add-to-group-link').hide();

		} else {

			$('#add-to-group-link').show();

		}

	} else {

		$("#groups .blankslate").show();

	}

	$('#table-group	tbody').empty().html(sHtmlGroups);
	$('#add-to-group').hide();

	},

	fnReloadGroups : function () {

		sensus.commons.modules.bForceReload = true;
		var oResponse = sensus.commons.modules.summaryData.lightInformation.fnGetResponse();
		var oFirstMeter = sensus.commons.modules.util.fnGetFirstDeviceResponse(oResponse);
		sensus.commons.modules.bForceReload = false;
		sensus.commons.modules.content.lightGroups.fnLoadGroups(oFirstMeter);

		$('#table-group').show();

	},

	fnLoadGroupList : function(data){

		var aGroups = data.groups;
		var sOptions = '';

		for (g in aGroups) {

			if (aGroups.hasOwnProperty(g)) {

				sOptions += '<option value="'+aGroups[g].id+'">'+aGroups[g].name+'</option>';

			}

		}

		$('#group-add-select').append(sOptions);

	},

	init : function () {

		var oRequest = new inquiryPaginationRequest();
		$.sc.getJson("api/group/fetchall", oRequest, false, sensus.commons.modules.content.lightGroups.fnLoadGroupList,null);

		var oResponse = sensus.commons.modules.summaryData.lightInformation.fnGetResponse();
		var oFirstMeter = sensus.commons.modules.util.fnGetFirstDeviceResponse(oResponse);

		sensus.commons.modules.content.lightGroups.fnLoadGroups(oFirstMeter);

		if (oFirstMeter.currentStatusMessage.lightStatusEnum == "DEACTIVATED") {

			$('#add-to-group-link, #add-to-group, #table-group').remove();

		}

	}

};

sensus.commons.modules.content.lightGroups.init();
/* Set Groups of the smartpoint */
var groupsSize = $("#table-group tr td.group-name").size();

if (groupsSize == 5) {

	$("#groups .edit").hide();

} else if (groupsSize == 0) {

	$("#groups .blankslate").show();

}

// Configurations group combobox
$('#group-add-select').combobox( {
	zIndex : 3999
});

var oGroupAddSelectInput = $("#group-add-select_input");

oGroupAddSelectInput.attr("value", $.sc.locale("widgets.combobox.prompt2"));
oGroupAddSelectInput.focus(function() {

	if ($(this).attr('value') == $.sc.locale("widgets.combobox.prompt3")) {

		$(this).attr('value', '');

	}

});

// Link edit Group
var oGroups = $("#groups");
$(".edit", oGroups).click(function() {
	$('input#group-add-select_input').val($.sc.locale('widgets.combobox.prompt'))
	$(".blankslate, #table-group", oGroups).hide();
	$("#add-to-group", oGroups).show();
	return false;

});

// Link cancel edit Group
$(".cancel-edit", oGroups).click(function() {
	$('.group-add-select_inputformError').remove();
	oGroupAddSelectInput.val($.sc.locale("widgets.combobox.prompt"));
	$("#add-to-group", oGroups).hide();
	$("#table-group").show();

	var groupsSize = 0;

	$("#table-group tr td.group-name").each(function() {

		groupsSize++;

	 });

	if (groupsSize == 0) {

		$(".blankslate", oGroups).show();

	}

	return false;

});

/**
 * Add Smartpoint to group
 */
$(".submit-short-form", oGroups).click(function() {

	if (sensus.util.combobox.getOption("#group-add-select") != undefined
				&& $('#group-add-select_input').val().length != 0
				&& $('#group-add-select_input').val() != $.sc.locale("widgets.combobox.prompt")) {

		$('.formError').remove();

		var sIdGroup = sensus.util.combobox.getOption("#group-add-select");
		var sAction = 'UPDATE';
		var adUrl = [$.address.parameter('id')];
		var sGroupName = $('#group-add-select_input').val();
		var oRequest = new groupRequest(sGroupName, null, null, sIdGroup, false, adUrl);
		oRequest.group.modelAction = sAction;

		$.sc.getJson("api/group/insertlights", oRequest, false, sensus.commons.modules.content.lightGroups.fnReloadGroups, null);

	} else {

		if ($('#group-add-select_input').val() == $.sc.locale("widgets.combobox.prompt") || ($('#group-add-select_input').val().length == 0)) {

			$('#group-add-select_input').validationEngine('showPrompt', $.sc.locale("validation.fieldrequired"), 'red', '', true);

		} else {

			$('#group-add-select_input').validationEngine('showPrompt', $.sc.locale("smartpoint.actions.addToGroup.not.exists", $("#group-add-select_input").val()), 'red', '', true);

		}

	}

	return false;

});

/**
 * Remove Smartpoint from group
 */
$(oGroups).on('click', 'a.delete', function() {

	var sGroupName = $(this).parent().parent().find('.group-name').text();
	var sIdGroup = $(this).find('span').text();
	var sAction	= 'UPDATE';
	var	adUrl = new Array();

	adUrl[0] = $.address.parameter('id');

	// Submit Ajax Action
	var oRequest = new groupRequest(sGroupName, null, null, sIdGroup, false, adUrl);
	oRequest.group.modelAction = sAction;

	$.sc.getJson("api/group/deletelights", oRequest, false, sensus.commons.modules.content.lightGroups.fnReloadGroups, null);

	if (!$('#table-group .group-name').length) {
		$('.blankslate', oGroups).show();
	}

	return false;
});