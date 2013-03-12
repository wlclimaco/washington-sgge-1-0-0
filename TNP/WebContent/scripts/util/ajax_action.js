/**
 * @namespace sensus.util.ajaxactions
 * @description The action namespace for the Ajax actions.
 */

/**
 * @fileoverview Defines the various action for call ajax action.
 *
 * @author Raphael Constantino
 */

sensus.util.ajaxaction = {

	data                : "",
	subData             : "",
	oResponse           : "",
	actionResult        : false,
	actionUrlAdress     : "",
	actionSubUrlAdress  : "",

	//Send ajax action default
	sendActionDefault : function(success) {

		if($.isPlainObject(sensus.util.ajaxaction.data)){

			data = sensus.util.ajaxaction.data;
			sContentType = "application/json; charset=utf-8";
			sType = "POST";

		} else {

			data = sensus.util.ajaxaction.data;
			sContentType = null;
			sType = "GET";

		}

		$.ajax({
			url          : sensus.util.ajaxaction.actionUrlAdress,
			dataType     : 'json',
			contentType  : "application/json; charset=utf-8",
			type         : "POST",
			data         : $.toJSON(sensus.util.ajaxaction.data),
			async        : false,
			success      : function(data) {

				var bLightDetail = false;

				if(sensus.settings.deleteGroupUrl || sensus.settings.deleteSearchUrl || sensus.settings.deleteUser) {

					if (sensus.util.ajaxaction.actionUrlAdress == sensus.settings.deleteGroupUrl) {

						sensus.widgets.datatable.reloadTable(sensus.pages.group.groupTable);

					} else if (sensus.util.ajaxaction.actionUrlAdress == sensus.settings.deleteSearchUrl) {

						sensus.pages.saved.setPageState(sensus.pages.saved.buildHash());

					} else if (sensus.util.ajaxaction.actionUrlAdress == sensus.settings.deleteUser) {

						sensus.widgets.datatable.reloadTable(sensus.pages.user.userTable);

					}

				}


				if ($('#content-controller').length
						&& (sensus.util.ajaxaction.actionUrlAdress == sensus.settings.addTag
								|| sensus.util.ajaxaction.actionUrlAdress == sensus.settings.insertTag
								|| sensus.util.ajaxaction.actionUrlAdress == sensus.settings.addToGroupUrl
								|| sensus.util.ajaxaction.actionUrlAdress == sensus.settings.removeTag
								|| sensus.util.ajaxaction.actionUrlAdress == sensus.settings.removeFromGroupUrl)) {

					bLightDetail = true;

				}

				if (data && data.operationSuccess && bLightDetail) {

					sensus.util.ajaxaction.actionResult = true;
					sensus.util.ajaxaction.oResponse = data;

				} else if (data && data.operationSuccess && !bLightDetail) {

					sensus.util.ajaxaction.actionResult = true;
					sensus.util.ajaxaction.oResponse = data;

					if (success != null) {

						sensus.util.page.showMessage("messaging-main", success + sensus.util.page.getMessageList(data.messageList), "confirm");

					}

				} else {

					if (data.result == 'OK') {

						sensus.util.ajaxaction.actionResult = true;

						if (success != null) {

							sensus.util.page.showMessage("messaging-main", success, "confirm");

						}

					} else {

						sensus.util.ajaxaction.actionResult = false;

						if (!data || (data.messages == null && data.messageList == null)) {

							sensus.util.page.showMessage("messaging-main", $.sc.locale("action.longRunningProcessDialog.error"), "error");

						} else {

							if (data.messages != undefined) {

								sensus.util.page.showMessage("messaging-main", data.messages[0], "error");

							} else {

								sensus.util.page.showMessage("messaging-main", sensus.util.page.getMessageList(data.messageList), "error");

							}

						}

					}
				}
			},
			error: function(e) {

				sensus.util.ajaxaction.actionResult = false;

				//if (e.result == "FAIL") {

					sensus.util.page.showMessage("messaging-main", $.sc.locale("action.longRunningProcessDialog.error"), "error");

				//}
			}
		});

	},

	sendActionAddToNewGroup : function(error, groupName) {

		$.ajax( {
			url      : sensus.util.ajaxaction.actionUrlAdress,
			type     : 'POST',
			async    : false,
			data     : sensus.util.ajaxaction.data,
			success  : function (e) {

				if (e.result === sensus.constants.ajax.ok) {

					sensus.util.ajaxaction.actionUrlAdress = sensus.util.ajaxaction.actionSubUrlAdress;
					sensus.util.ajaxaction.data = "id=" + e.groupId + sensus.util.ajaxaction.subData;
					sensus.util.ajaxaction.newGroupId = e.groupId;
					sensus.util.ajaxaction.sendActionDefault($.sc.locale("smartpoint.actions.addgroup.success", groupName), $.sc.locale("action.longRunningProcessDialog.error"));
					sensus.util.ajaxaction.actionResult = true;

				} else {

					sensus.util.page.showMessage("messaging-main", error + sensus.util.page.getMessageList(e.messages), "error");
					sensus.util.ajaxaction.actionResult = false;

				}
		    },
			error : sensus.pages.smartpoint.getDefaultAjaxErrorFunction(error)
		});

		// Hide Block Ui
		sensus.util.page.stopProgressBar(null, true);
	},

	//Send ajax force light status action
	sendActionUpdatedStatus: function(isMonitored) {

		sensus.util.page.startProgressBar(null,true);

		if($.isPlainObject(sensus.pages.longrunningprocess.data)){

			data = $.toJSON(sensus.pages.longrunningprocess.data);
			sContentType = "application/json; charset=utf-8";
			sType = "POST";

		} else {

			data = sensus.pages.longrunningprocess.data + "&isMonitored=" + isMonitored;
			sContentType = null;
			sType = "GET";

		}


		$.ajax({
			url          : sensus.pages.longrunningprocess.actionUrlAdress,
			dataType     : 'json',
			contentType  : sContentType,
			type         : sType,
			data         : data,
			async        : false,
			success      : function(response) {

				if (response.operationSuccess || response.result == "success" || response.result == sensus.constants.ajax.ok) {

					if (sensus.pages.longrunningprocess.isClearSelect) {

						sensus.widgets.datatable.clearSelects.call(sensus.pages.longrunningprocess.lrpTable);

					}

					if (sensus.pages.longrunningprocess.reloadTable) {

						//sensus.pages.smartpoint.fnReloadTable();

					}
					sensus.util.page.showMessage("messaging-main", $.sc.locale("action.longRunningProcessDialog.confirm"), "confirm");

					sensus.pages.smartpoint.auto_refresh = setInterval(function() {

						sensus.util.ajaxaction.checkUpdatedLightStatus();

					}, 10000);



					if (response.firstProcess) {

						sensus.pages.longrunningprocess.forceLightStatusLRPId = response.firstProcess.id;

					} else {

						sensus.pages.longrunningprocess.forceLightStatusLRPId = response.forceLightStatusLRPId;

					}

				} else if (response.result == "light_protected") {

					sensus.util.ajaxaction.reloadDetailPage("locked");

				} else if (response.result == "successwithoutsubmit") {

					sensus.util.ajaxaction.reloadDetailPage("offline");

				} else {

					sensus.util.page.showMessage("messaging-main", $.sc.locale("action.longRunningProcessDialog.error") + sensus.util.page.getMessageList(""), "error");

				}

				sensus.pages.longrunningprocess.bIsDetail = false;


			},
			error: function(response) {

				if (!response.operationSuccess) {

					sensus.util.page.showMessage("messaging-main", $.sc.locale("action.longRunningProcessDialog.error"), "error");

				}

			}
		});

	},

	//Call check update status loop
	checkUpdatedLightStatus : function() {

		var aSelectedIds  = [];
		aSelectedIds.push({'id':sensus.pages.longrunningprocess.forceLightStatusLRPId});
		$.ajax( {
			url      	 : sensus.settings.checkUpdatedLight,
			dataType     : 'json',
			type         : "POST",
			data         : $.toJSON({'processRequest' : new processRequest(aSelectedIds)}),
			async        : false,
			contentType  : "application/json; charset=utf-8",

			success  : function(e) {

				if(e.lights.length > 0 && sensus.pages.longrunningprocess.bIsDetail) {

					sensus.util.ajaxaction.reloadDetailPage(e);

				} else {

					sensus.util.page.stopProgressBar(null, true);

				}

			}

		});
	},

	ajax : function(url, request) {

		$.ajax({
			dataType     : 'json',
			type         : "POST",
			url          : url,
			data         : $.toJSON(request),
			async        : false,
			contentType  : "application/json; charset=utf-8",
			success      : function(json) {

			}

		});
	},


	reloadDetailPage : function(result) {

		if (result == "success" || result == "locked" || result == "offline" || result.operationSuccess) {

			clearInterval(sensus.pages.smartpoint.auto_refresh);

			if (sensus.pages.longrunningprocess.actionUrlAdress == sensus.settings.clearStatus) {

				sensus.commons.modules.bForceReload = true;

				var oResponse    = sensus.commons.modules.summaryData.lightInformation.fnGetResponse(),
					oFirstMeter  = sensus.commons.modules.util.fnGetFirstDeviceResponse(oResponse),
					aData        = oFirstMeter.currentAlarmWarningMessageList; // Format message Date

				sensus.commons.modules.lightalerts.fnLoadLightAlerts(aData);

				sensus.commons.modules.bForceReload = false;
				sensus.util.page.stopProgressBar(null, true);

			}

			if (sensus.pages.longrunningprocess.actionUrlAdress == sensus.settings.controlLightUrl) {

				sensus.commons.modules.bForceReload = true;
				sensus.commons.modules.summaryData.lightState.init();
				var oResponse = sensus.commons.modules.summaryData.lightInformation.fnGetResponse();

				if (oResponse.firstLight.overrideEnum == 'NONE' && oResponse.firstLight.overridePerDate == null) {

					$('#comunication-messaging').hide();

				}

				var oFirstMeter = sensus.commons.modules.util.fnGetFirstDeviceResponse(oResponse);
				sensus.commons.modules.summaryData.lightInformation.fnSetOverride(oFirstMeter);
				sensus.commons.modules.bForceReload = false;
				sensus.util.page.stopProgressBar(null, true);

			}

			if (sensus.pages.longrunningprocess.actionUrlAdress == sensus.settings.updateLightStatus) {

				sensus.commons.modules.bForceReload = true;
				sensus.commons.modules.summaryData.lightStatus.init();

				var aData = sensus.commons.modules.summaryData.lightStatus.fnGetResponse().firstLight,
					objLatLon = [];

				objLatLon.push({
					latitude   : sensus.commons.modules.util.fnGetParameterValue(aData, "LATITUDE", 'value'),
					longitude  : sensus.commons.modules.util.fnGetParameterValue(aData, "LONGITUDE", 'value'),
					status     : aData.currentStatusMessage.lightStatusEnumValue,
					id         : $('#light-rni-id').val()
				});

				$("#detail-map-container").addClass("detail-map");
				sensus.util.mapopen.iZoom = -1;
				sensus.util.mapopen.mapIt('detail-map-container', objLatLon);

				sensus.commons.modules.bForceReload = false;
				sensus.util.page.stopProgressBar(null, true);

			}

			if(sensus.pages.longrunningprocess.actionUrlAdress == sensus.settings.removeSchedule){

				sensus.commons.modules.bForceReload = true;
				var oResponse    = sensus.commons.modules.summaryData.lightInformation.fnGetResponse(),
					oFirstMeter  = sensus.commons.modules.util.fnGetFirstDeviceResponse(oResponse),
					aData        = oFirstMeter;
				sensus.commons.modules.lightschedule.fnReloadSchedule(aData);
				sensus.commons.modules.bForceReload = false;
				sensus.util.page.stopProgressBar(null, true);

			}

			if(sensus.pages.longrunningprocess.actionUrlAdress == sensus.settings.applySchedule){

				sensus.commons.modules.bForceReload = true;
				var oResponse    = sensus.commons.modules.summaryData.lightInformation.fnGetResponse(),
					oFirstMeter  = sensus.commons.modules.util.fnGetFirstDeviceResponse(oResponse),
					aData        = oFirstMeter;
				sensus.commons.modules.lightschedule.fnReloadSchedule(aData);
				sensus.commons.modules.bForceReload = false;


			}

			if(sensus.pages.longrunningprocess.actionUrlAdress == sensus.settings.updateLightProperty){

				sensus.util.page.stopProgressBar(null, true);

			}

		}

		var lightRequest = {
			lights       : [{ id : $.address.parameter('id') }],
			userContext  : new UserContext()
		};

		// BEGIN -- To receive a LAST message from LIGHT and Change current status
		$.ajax({
			url          : 'smartpoint/ajax.currentAlarmStatus.action',
			type         : "POST",
			async        : false,
			data         : $.toJSON({'lightRequest' : lightRequest}),
			dataType     : 'json',
			contentType  : "application/json; charset=utf-8",
			success      : function(oData) {

				if (oData && oData.operationSuccess && oData.currentAlarmWarningMessages[0]) {

					var textLightStatus = oData.currentAlarmWarningMessages[0].statusMessage.toLowerCase();

					// Set Status on the header
					$("#light-status").text( $.sc.locale('smartpointdetail.status.' +  textLightStatus ) );

					//Format message date ["19-07-2012T08:52:53"]
					$("#date-message").text($.date.dateFormat( oData.currentAlarmWarningMessages[0].messageDate, 'MM d, yy'));

					//Format message hour ["19-07-2012T08:52:53"]
					$("#hour-message").text( $.date.dateFormat( oData.currentAlarmWarningMessages[0].messageDate, 'h:i:s:fff A'));

				}

			}
		});
		// END -- To receive a LAST message from LIGHT and Change current status
	}
};