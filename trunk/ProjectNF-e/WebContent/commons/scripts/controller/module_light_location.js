sensus.commons.modules.content.lightLocation = {

	fnLoadLocation : function(aData){

		var objLatLon = [];
		objLatLon.push({
			latitude   : aData.latitude,
			longitude  : aData.longitude,
			status     : aData.currentStatusMessage.lightStatusEnumValue,
			id         : null
		});

		$("#detail-map-container").addClass("detail-map");

		$("#detail-map-container div").remove();

		var aAddress = [
			aData.streetName,
			aData.cityName,
			aData.stateName,
			aData.zipCode
		];
		var bValidAddress = true;

		for (u in aAddress) {

			if (aAddress[u] == null) {

				bValidAddress = false;

			}

		}

		if (!bValidAddress) {
			aAddress = [$.sc.locale('smartpointdetail.page.noaddress')];
		}

		sensus.commons.modules.util.fnFillData($('#location-address'), aAddress);

		var dLatitude  = parseFloat(aData.latitude).toFixed(4);
		var dLongitude = parseFloat(aData.longitude).toFixed(4);

		sensus.commons.modules.util.fnFillData($('#location-latitude'), [dLatitude]);

		sensus.commons.modules.util.fnFillData($('#location-longitude'), [dLongitude]);

		sensus.util.mapopen.iZoom = 18;

		//sensus.util.mapopen.mapIt('detail-map-container', objLatLon);

		$('#address .edit-only').hide();
		$('#address .read-only').show();

	},

	fnUpdateLocation: function() {

		sensus.commons.modules.bForceReload = true;

		var oResponse = sensus.commons.modules.summaryData.lightInformation.fnGetResponse();
		var oFirstMeter = sensus.commons.modules.util.fnGetFirstDeviceResponse(oResponse);
		sensus.commons.modules.content.lightLocation.fnLoadLocation(oFirstMeter);
		sensus.commons.modules.bForceReload = false;

	},


	init : function () {

		var oResponse = sensus.commons.modules.summaryData.lightInformation.fnGetResponse();
		var oFirstMeter = sensus.commons.modules.util.fnGetFirstDeviceResponse(oResponse);
		sensus.commons.modules.content.lightLocation.fnLoadLocation(oFirstMeter);

		if (oFirstMeter.currentStatusMessage.lightStatusEnum == "DEACTIVATED") {

			$('#edit-location').remove();

		}

	}

};

sensus.commons.modules.content.lightLocation.init();

//Edit Location
$('#address .edit').click(function() {

	var lat = $("#address .read-only dd #location-latitude").text();
	var lon = $("#address .read-only dd #location-longitude").text();

	$("[name='latitude']").val(lat.substring(0, lat.length - 1));
	$("[name='longitude']").val(lon.substring(0, lon.length - 1));
	$('#latitude').val($('#location-latitude').html().trim());
	$('#longitude').val($('#location-longitude').html().trim());
	$('#address .read-only').hide();
	$('#address .edit-only').show();

	return false;
});

//Cancel Edit Location
$('#address .cancel-edit').click(function() {
	$('#address .edit-only').hide();
	$('#address .read-only').show();
	return false;
});

/* Update lat and long */
$('#update-coordinates-form').validationEngine('attach', {
	validationEventTrigger  : 'submit',
	onValidationComplete    : function(form, status) {

		if (status) {

			var latitude   = $("[name='latitude']").val().trim().replace(",",".");
			var longitude  = $("[name='longitude']").val().trim().replace(",",".");

			var aParam = [
				{	value : latitude,
					propertyEnum : "LATITUDE"
				},
				{	value : longitude,
					propertyEnum : "LONGITUDE"
				}
			];

			var oRequest = new lightRequest(parseInt($.address.parameter('id')), null, null, null, null, null, null, null, null, null, null, null, null, aParam);
			var fnCallBack = sensus.commons.modules.content.lightLocation.fnUpdateLocation;
			$.ajaxValidator.fnMonitor("api/lighttop/upsertproperty", oRequest, false, fnCallBack, $.sc.locale("action.longRunningProcessDialog.confirm"), true);

		}

	}
});