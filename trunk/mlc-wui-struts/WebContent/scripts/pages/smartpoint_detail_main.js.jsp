<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<script type="text/javascript">
/**
 * @namespace sensus.pages.smartpointdetail
 * @description The main namespace for the SmartPointDetail Page.
 * @author Alexandre Tiveron
*/
	
sensus.pages.smartpointdetail = {

	id            : 0, //The smartpoint id
	totalRows     : 0, // The Total Rows
	auto_refresh  : 0, // The Interval for auto_refresh
	processingSummaryData : "", // The Processing Summary Data
		
	/**
	 * The Groups Settings
	 */
	setGroups : function () {
		
		/* Set Groups of the smartpoint */
		var groupsSize = 0;
		
		$("#table-group tr td.group-name").each(function() {
			groupsSize++; 
		 });
		 					
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

		oGroupAddSelectInput.attr("value", sensus.locale.get("widgets.combobox.prompt2"));
		oGroupAddSelectInput.focus(function() {
			if ($(this).attr('value') == sensus.locale.get("widgets.combobox.prompt3")) {	
				$(this).attr('value', '');
			}
		});
		
		// Link edit Group
		$("#groups .edit").click(function() {
			$("#groups .blankslate").hide();
			$("#add-to-group", "#groups").show();
			return false;
		});
			
		// Link cancel edit Group 
		$("#groups .cancel-edit").click(function() {

			oGroupAddSelectInput.val(sensus.locale.get("widgets.combobox.prompt"));
			$("#groups #add-to-group").hide();
			$("#table-group").show();

			var groupsSize = 0;
			
			$("#table-group tr td.group-name").each(function() {
				groupsSize++; 
			 });
			 
			if (groupsSize == 0) {
				$("#groups .blankslate").show();
			}

			return false;
		});
		
		window.setTimeout('$(".group-name").truncatable({limit:45, more: "..."});', 500);

	}, 
	
	/**
	 * The Tags Settings
	 */
	setTags : function () {

		$(".chzn-select").chosen();
		$(".chzn-select").change(function(event, data) {

			if (data && data.selected) {

				var aIdTags = [{ id : parseInt(data.selected.split("-")[0]) }];
				
				// Submit insert smartpoint to tag
				sensus.pages.smartpointdetail.addSmartpointToTag(aIdTags);
			}

		});

		$(".search-choice-close").bind('click', function() {

			// Remove smartpoint to tag 
			var aIdTags = [{ id : parseInt($(this).attr("id")) }];

			sensus.pages.smartpointdetail.removeSmartpointToTag(aIdTags);

		});

	},
	
	/**
	 * Add Smartpoint to tags
	 */
	addSmartpointToTag : function(aIdTags) {
	
		sensus.util.page.startProgressBar();
	
		var aIdLight = [parseInt($.address.parameter("id"))];
		
		// Send ajax action to Add Tag to Light
		sensus.util.ajaxaction.actionUrlAdress = sensus.settings.addTag;
		sensus.util.ajaxaction.data = { 
			'tagRequest': new tagRequest(null, null, aIdTags, false, aIdLight, { 'searchParameters' :  sensus.util.page.getSearchParameters()}) 
		};

		sensus.util.ajaxaction.sendActionDefault(sensus.locale.get("smartpoint.actions.addtag.success"), sensus.locale.get("action.longRunningProcessDialog.error"));
		
		<sec:authorize access="hasAnyRole('ROLE_Role.Admin', 'ROLE_Role.System Operator')">
			$(".search-choice-close").show();
			$(".search-choice-close").bind('click', function() {

				// Remove smartpoint to tag
				var aIdTags = [{ id : parseInt($(this).attr("id")) }];
				sensus.pages.smartpointdetail.removeSmartpointToTag(aIdTags);

			});
		</sec:authorize>

		sensus.util.page.stopProgressBar(1);
	},
	
	/**
	 * Remove Smartpoint to tag
	 */
	removeSmartpointToTag : function(aIdTags) {

		sensus.util.page.startProgressBar();
		
		var aIdLight = [parseInt($.address.parameter("id"))];
		
		// Send ajax action to Add Tag to Light
		sensus.util.ajaxaction.actionUrlAdress = sensus.settings.removeTag;
		sensus.util.ajaxaction.data = {
			'tagRequest': new tagRequest(null, null, aIdTags, false, aIdLight, { 'searchParameters' :  sensus.util.page.getSearchParameters()}) 
		};
		sensus.util.ajaxaction.sendActionDefault(sensus.locale.get("smartpoint.actions.removetag.success"), sensus.locale.get("action.longRunningProcessDialog.error"));
		
		sensus.util.page.stopProgressBar(1);
	},
	
	/**
	 * Add Smartpoint to group
	 */
	addSmartpointToGroup : function() {

		$("#groups .submit-short-form").click(function() {

			if (sensus.util.combobox.getOption("#group-add-select") != undefined 
						&& $('#group-add-select_input').val().length != 0 
						&& $('#group-add-select_input').val() != sensus.locale.get("widgets.combobox.prompt")) {

				$('.formError').remove();
				sensus.util.page.startProgressBar();

				var sIdGroup    = sensus.util.combobox.getOption("#group-add-select"),
					sAction     = 'UPDATE',
					adUrl       = [$.address.parameter('id')],
					sGroupName  = $('#group-add-select_input').val();
					
				// Submit Ajax Action
				sensus.util.ajaxaction.actionUrlAdress = sensus.settings.addToGroupUrl;
				sensus.util.ajaxaction.data = { 'groupRequest': new groupRequest(null, null, null, sIdGroup, sAction, false, adUrl) };
				sensus.util.ajaxaction.sendActionDefault(sensus.locale.get("smartpoint.actions.addgroup.success", sGroupName), sensus.locale.get("action.addgroup.error"));
				
				
				$("#groups").load("smartpoint/ajax.openSmartPointDetailMain.action?id=" + $.address.parameter('id')+ " #groups", function() {
					sensus.pages.smartpointdetail.setGroups();
					sensus.pages.smartpointdetail.addSmartpointToGroup();
					sensus.pages.smartpointdetail.removeSmartpointFromGroup();
					sensus.util.page.stopProgressBar(1);
				});

			} else {

				if ($('#group-add-select_input').val() == sensus.locale.get("widgets.combobox.prompt") || ($('#group-add-select_input').val().length == 0)) {
					$('#group-add-select_input').validationEngine('showPrompt', sensus.locale.get("validation.fieldrequired"), 'red', '', true);
				} else {
					$('#group-add-select_input').validationEngine('showPrompt', sensus.locale.get("smartpoint.actions.addToGroup.not.exists", $("#group-add-select_input").val()), 'red', '', true);
				}

			}
			
			return false;
		});
	},
	
	/**
	 * Remove Smartpoint from group
	 */
	removeSmartpointFromGroup : function() {
		$("#groups a.delete").click(function() {
		
			sensus.util.page.startProgressBar();
			
			var sGroupName  = $(this).parent().parent().find('.group-name').text(),
				sIdGroup	= $(this).find('span').text(),
				sAction		= 'UPDATE';
				
			var	adUrl = new Array();
			
			adUrl[0] = $.address.parameter('id');
			
			// Submit Ajax Action
			sensus.util.ajaxaction.actionUrlAdress = sensus.settings.removeFromGroupUrl;
			sensus.util.ajaxaction.data = { 'groupRequest': new groupRequest(sGroupName, null, null, sIdGroup, sAction, false, adUrl) };
			sensus.util.ajaxaction.sendActionDefault(sensus.locale.get("smartpoint.actions.removegroup.success", sGroupName), sensus.locale.get("action.addgroup.error"));
						
			$("#groups").load("smartpoint/ajax.openSmartPointDetailMain.action?id=" + $.address.parameter('id') + " #groups", function() {
				sensus.pages.smartpointdetail.setGroups();
				sensus.pages.smartpointdetail.addSmartpointToGroup();
				sensus.pages.smartpointdetail.removeSmartpointFromGroup();
				sensus.util.page.stopProgressBar(1);
			});
			
			return false;
		});
	},
	
	/**
	 * Update Light Status
	 */
	getDataFromLight : function() {

		sensus.pages.longrunningprocess.bIsDetail = true;
		sensus.pages.longrunningprocess.actionUrlAdress = sensus.settings.forceLightStatus;
		sensus.pages.longrunningprocess.data = "id=" + $.address.parameter('id');
		sensus.pages.longrunningprocess.callPromptMonitorAction(true);

	},
	
	/**
	 * Reset min/max values
	 */
	resetValues :  function() {
		
		// Submit Ajax Action
		sensus.util.ajaxaction.actionUrlAdress = sensus.settings.resetValues;
		sensus.util.ajaxaction.data = { 'lightRequest': new lightRequest($.address.parameter('id')) };
		sensus.util.ajaxaction.sendActionDefault(sensus.locale.get("smartpointdetail.action.resetvalues.success"), sensus.locale.get("widgets.customize.errorRequest"));
		
		$("#readings .small-table").load("smartpoint/ajax.openSmartPointDetailMain.action?id=" + $.address.parameter('id')+ " #readings .small-table", function() {
			sensus.util.page.stopProgressBar(1);
		});
		
	},
	
	/**
	* Launches Map. The map will filter
	* the layer.
	* 
	*/
	setMapLayer : function () {
				
		var objLatLon = [];
		objLatLon.push({
			latitude   : $('#light-lat').val(),
			longitude  : $('#light-long').val(),
			status     : $('#lightStatus').val(),
			id         : $('#light-rni-id').val()
		});
		
		$("#detail-map-container").addClass("detail-map");
		
		sensus.util.mapopen.mapIt('detail-map-container', objLatLon);
	}, 
	
	/**
	 * Update Lat Long
	 */
	updateLatLong : function() {

		sensus.pages.longrunningprocess.bIsDetail = true;
		sensus.pages.longrunningprocess.actionUrlAdress = sensus.settings.updateLightProperty;
		
		var latitude   = $("[name='latitude']").val().replace(",","."),
			longitude  = $("[name='longitude']").val().replace(",",".");
		
		sensus.pages.longrunningprocess.data = "id=" + $.address.parameter('id') + "&latitude=" + latitude + "&longitude=" + longitude + "&isLocation=true";
		sensus.pages.longrunningprocess.callPromptMonitorAction(true);

	}, 
	
	/** 
	 * Configurations schedule combobox 
	 */
	setSchedule : function() {

		$('#offset-schedule-add-select').combobox( {
			zIndex : 3999
		});

		$("#offset-schedule-add-select_input").attr("value", sensus.locale.get("widgets.combobox.prompt.typetosearch"));

		$('#offset-schedule-add-select_input').focus(function() {
			if ($(this).attr('value') == sensus.locale.get("widgets.combobox.prompt.typetosearch")) {	
				$(this).attr('value', '');
			}
		});

		$('#event_schedule_add_select').combobox( {
			zIndex : 3999
		});

		$("#event_schedule_add_select_input").attr("value", sensus.locale.get("widgets.combobox.prompt.typetosearch"));

		$('#event_schedule_add_select_input').focus(function() {
			if ($(this).attr('value') == sensus.locale.get("widgets.combobox.prompt.typetosearch")) {	
				$(this).attr('value', '');
			}
		});
	},

	// Lock and Unlock Light
	setUpdatedProtected : function() {

		// Lock and Unlock Light and refresh div
		$('.update-light-protected').click(function() {

			sensus.pages.longrunningprocess.bIsDetail = true;
			sensus.util.ajaxaction.actionUrlAdress = sensus.settings.updateLightProtected;
			sensus.util.ajaxaction.data = "id=" + $.address.parameter('id') + "&lightProtected=" + $(this).attr('id');
			sensus.util.ajaxaction.sendActionDefault(sensus.locale.get("action.longRunningProcessDialog.confirm"), sensus.locale.get("action.longRunningProcessDialog.error"));

			if (sensus.util.ajaxaction.actionResult == true) {

				$("#update-protect").load("smartpoint/ajax.openSmartPointDetailMain.action?id=" + $.address.parameter('id') + " #update-protect > *", function() {
					$(".button").button();
					sensus.pages.smartpointdetail.setUpdatedProtected();
				});

			}
			
			return false;
		});

	}
};
</script>