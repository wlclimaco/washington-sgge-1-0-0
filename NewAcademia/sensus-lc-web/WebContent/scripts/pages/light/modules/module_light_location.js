sensus.commons.modules.content.lightLocation = {

	fnLoadLocation : function(aData){

		var objLatLon = [];
		objLatLon.push({
			latitudeAvg					: aData.radio.location.latitude,
			longitudeAvg				: aData.radio.location.longitude,
			currentLightStatus			: $.map(sensus.settings.enums.PrecedenceEnum, function(e, i) { if(e === aData.lastNotificationHistory.precedence) { return i }; })[0],
			smartpointsTotalByLatLong	: 1,
			lightIds					: [$.address.parameter('id')]
		});

		$("#detail-map-container").addClass("detail-map");

		$("#detail-map-container div").remove();

		var aAddress = [
			aData.radio.location.address,
			aData.radio.location.city,
			aData.radio.location.state,
			aData.radio.location.zip
		];

		var bValidAddress = true;

		for (u in aAddress)
		{
			if (aAddress[u] == null)
			{
				bValidAddress = false;
			}
		}

		if (!bValidAddress)
		{
			aAddress = [$.sc.locale('smartpointdetail.page.noaddress')];
		}

		sensus.commons.modules.util.fnFillData($('#location-address'), aAddress);

		var dLatitude	= (aData.radio.location.latitude) ? $.sc.nTruncate(parseFloat(aData.radio.location.latitude), 4) : null;
		var dLongitude	= (aData.radio.location.longitude) ? $.sc.nTruncate(parseFloat(aData.radio.location.longitude), 4) : null;

		sensus.commons.modules.util.fnFillData($('#location-latitude'), [dLatitude]);
		sensus.commons.modules.util.fnFillData($('#location-longitude'), [dLongitude]);

		$.sc.maps('detail-map-container', objLatLon, null, null, null, 1);
		// Add map drag function
		$.sc.loadLocationEditor(false, true);

		$('#address .edit-only').hide();
		$('#address .read-only').show();

		$('.olMapViewport').height('320px');

	},

	fnUpdateLocation: function()
	{
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

		if (oFirstMeter.lifeCycleState == "DEACTIVATED")
		{
			$('#edit-location').remove();
		}
	}

};
sensus.commons.modules.content.lightLocation.currentLocation =
{
		latitude: null,
		longitude: null
}
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

	// Activate map drag function
	$.sc.loadLocationEditor(true, true);

	return false;
});

//Cancel Edit Location
$('#address .cancel-edit').click(function() {
	$('#address .edit-only').hide();
	$('#address .read-only').show();
	// Deactivate map drag function
	sensus.commons.modules.content.lightLocation.fnUpdateLocation();
	return false;
});

/* Update lat and long */
$('#update-coordinates-form').validationEngine('attach', {
	validationEventTrigger  : 'submit',
	onValidationComplete    : function(form, status) {

		if (status)
		{
			var latitude   = $("[name='latitude']").val().trim().replace(",",".");
			if(sensus.commons.modules.content.lightLocation.currentLocation.latitude
					&& $.sc.nTruncate(sensus.commons.modules.content.lightLocation.currentLocation.latitude, 4) == latitude)
			{
				var latitude = sensus.commons.modules.content.lightLocation.currentLocation.latitude;
			}

			var longitude  = $("[name='longitude']").val().trim().replace(",",".");
			if(sensus.commons.modules.content.lightLocation.currentLocation.longitude
					&& $.sc.nTruncate(sensus.commons.modules.content.lightLocation.currentLocation.longitude, 4) == longitude)
			{
				var longitude = sensus.commons.modules.content.lightLocation.currentLocation.longitude;
			}

			var id = parseInt($.address.parameter('id'));
			var oLocation = {
				latitude : latitude,
				longitude : longitude
			};

			sensus.commons.modules.content.lightLocation.currentLocation =
			{
					latitude: null,
					longitude: null
			}

			var oLightMaintenanceRequest = new lightMaintenanceRequest(new Light(null, id, null, oLocation));

			var fnCallBack = sensus.commons.modules.content.lightLocation.fnUpdateLocation;
			$.sc.monitor("api/lighttop/upsertproperty/location", oLightMaintenanceRequest, false, fnCallBack, $.sc.locale("action.longRunningProcessDialog.confirm"), true);
		}
	}
});