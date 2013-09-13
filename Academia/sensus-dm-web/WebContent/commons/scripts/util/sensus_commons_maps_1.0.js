/**
 * @fileoverview Defines common map-related functionality.
 * @author Cristiane Cobo / Lucas Oliveira
 */
/**
 * The main namespace for map-related functionality.
 * Used in early versions and will be migrated in the future.
 */
$(document).ready(function(){
	sensus.util.mapopen = {

		/**
		 * Definitions for the help dialog for polygon use.
		 */
		dialogSettings : {

			/**
			 * Creates and shows the help dialog.
			 */
			polygonHelp : {

				title                : $.sc.locale("smartpoint.map.dialog.polygonHelpTitle"),
				width                : 450,
				requiresSmartpoints  : false,
				action               : function(actionDialog) {

					$('#action-dialog').empty()
									   .html('<div class="ui-widget help"><div class="ui-state-highlight ui-corner-all sui-pad"><span class="ui-icon ui-icon-info left"></span><p>'
									   +$.sc.locale("smartpoint.map.dialog.topText")+'</p></div>'
									   +'<img height="82" width="363" alt="Polygon tool" class="sui-pad1v" style="margin-left:26px;" src="images/polygon-tool-view.png">'
									   +'<ul class="sui-pad1"><li>'+$.sc.locale("smartpoint.map.dialog.startText")
									   +'<li>'+$.sc.locale("smartpoint.map.dialog.endText")+'</li><li>'+$.sc.locale("smartpoint.map.dialog.lastText")+'</li></ul></div>')
									   .dialog('open');

				},
				/**
				 * Definitions for button actions on the help dialog.
				 */
				buttons : [{
					/**
					 * Save user settings.
					 */
					id : "apply-polygon-help",
					text : $.sc.locale("smartpoint.map.dialog.thanks"),
					click : function() {

						var aSettingsList = [];

						sensus.settings.showDialogPolygon = false;

						// Save User Settings
						var properties = [new Property({propertyName : "SHOW_DIALOG_POLYGON", propertyValue : false})];

						var oRequest = new PropertyRequest({properties : properties});

						$.ajaxValidator.fnDoCall("api/settings/upsert", oRequest, false);

						$(this).dialog('close');
					}
				},
				/**
				 * Just close the dialog.
				 */
				{
					id: "apply-polygon-cancel",
					text :$.sc.locale("commons.pages.cancel"),
					click : function() {

						$(this).dialog('close');

					}
				}]
			}
		}
	}

	$.sc.map = (function() {

		var sUrl = null;
		var bBounds = true;
		var bZoom = true;
		var iZoom = -1;
		var nCurrentTrunc = null;
		var oInitialRequest = null;
		var oRequest = null;
		var popupCenter = null;
		var bMapFromFilter = false;
		var currentPopUpLights = null;
		var nFromProjection	= new OpenLayers.Projection("EPSG:4326");// transform from WGS 1984
		var nToProjection = new OpenLayers.Projection("EPSG:900913");// to Spherical Mercator Projection
		var vector = { color : '', area : 0, index : 0, aIds : [], aLatLonTruncs : [] };
		var oColors = {};
		var objMap = null;
		var objMarkers = null;
		var objPolygons = null;
		var objSelectorEvents = null;

		var styleMarker = new OpenLayers.Style({ cursor : "${pointer}", labelSelect : true, externalGraphic: "${status}", pointRadius: "${size}", fontColor: "#FFFFFF", fontSize: "15px", fontWeight: "bold", label: "${count}", labelAlign : "cm", labelYOffset : "${offset}" }, {
			context: {
				/**
				 * Bug-fix: check for browser version.
				 */
				offset : function(){
					if($.browser.msie && $.browser.version == '9.0'){
						return -5;
					} else {
						return 0;
					}
				},
				pointer : function(feature) {
					if(feature && feature.attributes && feature.attributes.totalObj) {

						if(!$.sc.isNullOrUndefined(feature.attributes.groupBounds[0])
							|| feature.attributes.deviceIds
							&& $.address.path().split('/').pop() != 'detail')
						{
							return "pointer";
						}
					}

					return "default";
				},
				/**
				 * Set the circle's size according to the number of lights it holds.
				 */
				size : function(feature) {
					var s = 6; // Why initialize with 6?
					if(feature && feature.attributes && feature.attributes.totalObj){
						var t = feature.attributes.totalObj;
						if(t > 1 && t <= 9){
							s = 11;
						} else if( t > 9 && t <= 99) {
							s = 20;
						} else if(t > 99 && t <= 999){
							s = 30;
						} else if(t > 999){
							s = 50;
						}
					}
					return s;
				},
				/**
				 * Set the background icon (circle color) depending on the priority status of the light set.
				 */
				status : function(feature){
					if(feature && feature.attributes && feature.attributes.totalObj && feature.attributes.groupStatus){
						return "images/map/map_icn_dot_"+feature.attributes.groupStatus+".png";
					} else if (feature && feature.attributes && feature.attributes.totalObj) {
						return "images/map/map_icn_dot_3.png";
					} else {
						return "images/map/map_icn_dot_0.png";
					}
				},
				/**
				 * Returns the number to be set on the marker.
				 */
				count: function(feature) {
					if(feature && feature.attributes && feature.attributes.totalObj){
						var t = feature.attributes.totalObj;
						if(t == 1){
							t = "";
						}
					} else {

						t = "";
					}
					return t;
				}
			}
		});

		/**
		 * OpenLayers modification to add Double click option on vector features.
		 * The previous featureselected event doesn't support it natively.
		 */

		OpenLayers.Handler.FeatureWithDbClick = OpenLayers.Class(OpenLayers.Handler.Feature, {
		    name: 'OpenLayers.Handler.FeatureWithDbClick',
		    CLASS_NAME: 'OpenLayers.Handler.FeatureWithDbClick',

		    timer: null,
		    clickTolerance: 4,
		    clickDelay: 300,

		    initialize: function(control, layer, callbacks, options) {
		        OpenLayers.Handler.Feature.prototype.initialize.apply(this, [control, layer, callbacks, options]);
		        this.layer = layer;
		        this.map = layer.map;
		    },

		    triggerCallback: function(type, mode, args) {
		        if (type === 'click') {
		            if (this.timer === null) {
		                var ins = this;
		                this.timer = setTimeout(function() {
		                    OpenLayers.Handler.Feature.prototype.triggerCallback.apply(ins, [type, mode, args]);
		                    ins.timer = null;
		                }, this.clickDelay);
		            }
		        } else if (type === 'dblclick') {
		            if (this.timer) {
		                clearTimeout(this.timer);
		                this.timer = null;
		            }

		            OpenLayers.Handler.Feature.prototype.triggerCallback.apply(this, [type, mode, args]);
		        } else {
		            OpenLayers.Handler.Feature.prototype.triggerCallback.apply(this, [type, mode, args]);
		        }
		    }
		});

		OpenLayers.Control.FeatureSelector = OpenLayers.Class(OpenLayers.Control, {
		    handlerOptions: {
		        'single': true,
		        'double': true,
		        'pixelTolerance': 0,
		        'stopSingle': false,
		        'stopDouble': false
		    },

		    initialize: function(layer, options) {
		        OpenLayers.Control.prototype.initialize.apply(this, arguments);
		        this.handler = new OpenLayers.Handler.FeatureWithDbClick(this, layer, {
		            'click': options.onClick,
		            'dblclick': options.onDblclick
		        }, this.handlerOptions);
		    }
		});

		/** ################# EVENTS ################# */

		var _closePopup = function() {

			if(objMap.popups.length){
				objMap.popups[0].destroy();
			}
			$('.olMapViewport').click();
			objMap.panTo(popupCenter);

		};

		var _listClick = function(e) {

			e.preventDefault();

			$(this).parents("div.ui-dialog-content").dialog("close");

			if ($.fn.pageLoader.currentPage() != "group") {
				sensus.util.session.create({
					key : "SelectedFilters",
					value : $.fn.pageLoader.queryString()
				});
			}

			$.fn.pageLoader.load($(this).attr("href"), $("#load"));
		};

		var _popUpSlideBox = function(e) {
			e.preventDefault();

			$(this).addClass('on');

			// Close previous clicked slide
			$('.light-info').each(function(){

				if(!$(this).prev().hasClass('on')){

					$(this).slideUp('slow');
					$(this).prev().find('.icon-colapse').removeClass('ui-icon-triangle-1-s').addClass('ui-icon-triangle-1-e');

				} else {

					$(this).prev().removeClass('on');

				}

			});


			// Change the clicked item triangle icon (open/close).
			var that = $(this).find('.icon-colapse');

			if(that.hasClass('ui-icon-triangle-1-e')){

				that.removeClass('ui-icon-triangle-1-e').addClass('ui-icon-triangle-1-s');

			} else {

				that.removeClass('ui-icon-triangle-1-s').addClass('ui-icon-triangle-1-e');

			}

			// Open the clicked light slide.
			$(this).next(".collapse").toggle('blind',null,500);

		};

		var _mapCheckbox = function() {

			var bChecked = $(this).attr('checked') ? true : false;
			var sValue = $(this).attr("value");


			if(bChecked) {

				sensus.widgets.datatable.addSelected({id : sValue}, false);
				$('#smartpoint-table td input[value=' + sValue + ']').attr('checked', true);

			} else {

				sensus.widgets.datatable.addDeselected({id : sValue}, false);
				$('#smartpoint-table td input[value=' + sValue + ']').attr('checked', false);

			}

			sensus.widgets.datatable.setTotalResult(false);
			$("#actions .message").removeClass("ui-state-error");

		};

		var _pointerSingleClick = function(selectedFeature)
		{

			if($.address.parameter("pg").split('/')[1] == 'about') // TODO
			{
				return false;
			}

			var iPopUpSizeH = 310;
			var iPopUpSizeW = 400;
			// Unset any existing pop-up.
			if(objMap.popups.length){
				objMap.popups[0].destroy();
			}

			if(!popupCenter) {
				 popupCenter = objMap.getCenter();
			}

			// Creates the pop-up and add it to the map.
			var position = new OpenLayers.LonLat(selectedFeature.geometry.getBounds().getCenterLonLat().lon, selectedFeature.geometry.getBounds().getCenterLonLat().lat);
			var popup = new OpenLayers.Popup("popup", position, null, functionalities.popup.buildPopUp(selectedFeature.attributes), false);
			popup.backgroundColor = null;
			popup.contentSize.h = iPopUpSizeH;
			popup.contentSize.w = iPopUpSizeW;
			popup.autosize = true;
			popup.panMapIfOutOfView = true;
			selectedFeature.popup = popup;
			objMap.addPopup(popup);

			$('.olMap .alist').click(events.listClick);

			// Close the pop-up
			$('.icon-close').click(events.closePopup);

			// Open items (slide) in the pop-up.
			$('.infowindow-title').click(events.popUpSlideBox);

			// If singleton, open detail by default
			if(selectedFeature.attributes.totalObj == 1)
			{
				var currHeader = $(".infowindow-title");
				currHeader.next(".collapse").toggle('blind',null,100);
				currHeader.find('.icon-colapse').removeClass('ui-icon-triangle-1-e').addClass('ui-icon-triangle-1-s');
			}


			// Controls the light selection (datatable - count and actions).
			$(".map-checkbox").click(events.mapCheckbox);
		};

		var _pointerDoubleClick = function(selectedFeature)
		{
			if(selectedFeature.geometry.getBounds() && selectedFeature.geometry.getBounds().getCenterLonLat() && selectedFeature.geometry.getBounds().getCenterLonLat().lon && selectedFeature.geometry.getBounds().getCenterLonLat().lat)
			{

				if(selectedFeature.attributes.ids && selectedFeature.attributes.ids.length == 1)
				{
					objMap.zoomToExtent(selectedFeature.geometry.getBounds(), closest=true);
				}
				else if(selectedFeature.attributes.ids && selectedFeature.attributes.ids.length > 0 && selectedFeature.attributes.ids.length <= 8)
				{
					var oPreviousRequest = oRequest;
					var _zoomToFeature = function(response)
					{
						response = response.geocodeDeviceInfo;
						var bounds =
						[
							response[0].bottomLeftLon,
							response[0].bottomLeftLat,
							response[0].topRightLon,
							response[0].topRightLat
						];
						bounds = new OpenLayers.Bounds(bounds).transform(nFromProjection, nToProjection);
						oRequest = oPreviousRequest;
						objMap.zoomToExtent(bounds);
					}
					oRequest = new inquiryLightRequest(null, null, selectedFeature.attributes.ids);
					oRequest.bottomLeftLat = null;
					oRequest.bottomLeftLon = null;
					oRequest.topRightLat = null;
					oRequest.topRightLon = null;
					oRequest.geoCodeTrunc = 3;
//						oRequest.fetchBounds = true;
					$.sc.getJson(sUrl, oRequest, false, _zoomToFeature, null, false, true, false);

				}
				else if(selectedFeature.attributes.groupBounds[0] != undefined && selectedFeature.attributes.groupBounds[0] != null)
				{
					bounds = new OpenLayers.Bounds(selectedFeature.attributes.groupBounds).transform(nFromProjection, nToProjection);
					objMap.zoomToExtent(bounds);
				}

			}
		};

		var _polygonAdded = function (event, drawControls) {

			vector.index = vector.index + 1;
			var oVectorId = event.features[0].id.replace(/\./g,'\\.').split('.')[2].toLowerCase();
			var sFeatureId = "OpenLayers.Feature."+ oVectorId.charAt(0).toUpperCase() + oVectorId.slice(1);
			vector.area = event.features[0].geometry.getGeodesicArea(nToProjection)/ 1000000;

			var oLayer = objMap.getLayersByName(objMarkers.name)[0];
			var aCurrentLatLonTruncs = [];

			if (oLayer) {

				for (var i=0;i<oLayer.features.length;i++) {

					 if (oLayer.features[i].geometry.intersects(event.features[0].geometry)) {

						if(oLayer.features[i].attributes.latLonTrunc.latitudeTrunc){

							aCurrentLatLonTruncs.push(oLayer.features[i].attributes.latLonTrunc);

						}

					}
				}

				vector.aLatLonTruncs = aCurrentLatLonTruncs;

				if (vector.aLatLonTruncs.length > 0)
				{
					var fnSelectLights = function(data)
					{
						var aDevices = data.devices;
						var aIds = [];

						for (var o = 0; o < aDevices.length; o++) {

							if ($.sc.isNullOrUndefined(aDevices[o].radio) || $.sc.isNullOrUndefined(aDevices[o].radio.flexNetId))
							{
								continue;
							}

							var sDeviceTypeEnum	= $.address.parameter("device_type").replace("|", '');
							var sTypeEnum = "";
							var sTypeEnumValue = "";

							switch (sDeviceTypeEnum) {
								case sensus.constants.services.electric.meter.name :
									sTypeEnum		= aDevices[o].electricMeterTypeEnum;
									sTypeEnumValue	= aDevices[o].electricMeterTypeEnumValue;
									break;

								case sensus.constants.services.electric.han.name :
									sTypeEnum 		= aDevices[o].hanDeviceTypeEnum;
									sTypeEnumValue 	= aDevices[o].hanDeviceTypeEnumValue;
									break;

								case sensus.constants.services.electric.lcm.name :
									sTypeEnum		= aDevices[o].lcmTypeEnum;
									sTypeEnumValue	= aDevices[o].lcmTypeEnumValue;
									break;

								default :
									break;
							}


							var oSelectData = {
											id 				 : aDevices[o].radio.flexNetId.toString(),
											DeviceType  	 : aDevices[o].deviceType,
											CustomerId  	 : aDevices[o].radio.customerId,
											BaseRepId		 : aDevices[o].electricMeterFlexNetId,
											ClientEndPointId : aDevices[o].deviceId,
											DeviceSubType	 : aDevices[o].deviceType,
											DeviceSubTypeId	 : aDevices[o].deviceTypeValue,
											TypeEnum		 : sTypeEnum,
											TypeEnumValue	 : sTypeEnumValue
									}

							sensus.widgets.datatable.addSelected(oSelectData, false);
							$('#smartpoint-table input:checkbox[value=' + aDevices[o].radio.flexNetId + ']').prop("checked", true);
							aIds.push(aDevices[o].radio.flexNetId.toString());

						}
						vector.aIds = aIds;


						drawControls['polygon'].deactivate();
						$('#draw-polygon').removeClass('ui-state-focus');
						$('#draw-pan').addClass('ui-state-focus');
					}
					var oRequest = config.ajaxCalls.fetchAll.request(nCurrentTrunc, vector.aLatLonTruncs);

					$.sc.getJson(config.ajaxCalls.fetchAll.url, oRequest, false, fnSelectLights, null, false, true);
				}

				$("#w-filters").filters("addTag", "drawing", null, null, event.features[0].id);
			}
			sensus.widgets.datatable.setTotalResult(false);
			$("#actions .message").removeClass("ui-state-error");

		};

		var _drawPolygon = function(event) {

			var drawControls = event.data.drawControls;

			if (!$(this).hasClass('ui-state-disabled')) {

				var sShowDialogPolygon = sensus.settings.showDialogPolygon;

				if (sShowDialogPolygon && sShowDialogPolygon != "false") {
					sensus.util.actiondialog.launchActionDialog("polygonHelp", sensus.util.mapopen.dialogSettings["polygonHelp"]);
				}

				drawControls['polygon'].activate();
				$(this).addClass('ui-state-focus');
				$('#draw-pan').removeClass('ui-state-focus');

			} else {

				drawControls['polygon'].deactivate();
				$(this).removeClass('ui-state-focus');

			}

		};

		var _drawPan = function(event) {

			var drawControls = event.data.drawControls;

			$(this).addClass('ui-state-focus');
			$('#draw-polygon').removeClass('ui-state-focus');
			drawControls['polygon'].deactivate();

		};

		/** ################ POP UP FUNCTIONALITY ################ */

		var _fnFillPopUp = function (obj, attributes) {

			// Validate "obj"
			if (!obj.devices.length) {
				return;
			}

			var devices = obj.devices;
			var sHtml = "";
			currentPopUpLights = devices;

			sHtml += '<div class="rounded box-shadow messageMap"><div class="messageArrow"></div>';

			// The close pop-up icon

			sHtml += '<div style="background-color:#F0F0F0; padding: 1px; height: 17px;">';
			sHtml += '<span title="' + $.sc.locale('message.action.close') + '" class="ui-icon icon-close ui-icon-closethick">' + $.sc.locale('message.action.close') + '</span>';
			sHtml += '</div>';

			// The scrollable container
			sHtml += '<div id="lightContainer">';

			for(a in devices) {

				var data = devices[a];
				if ((data.deviceId === undefined)||(data.deviceId === null)) {
					var sDeviceId="";
				}else{
					var sDeviceId = data.deviceId;
				}
				var sFlexNetId = data.radio.flexNetId;
				var sDeviceDescription = "";

				if (!$.sc.isNullOrUndefined(data.firstAlarm)) {
					var sDateFormat = sensus.settings.dateFormatMask.replace("yyyy", "yy");
					var oAlarmTime = $.date.dateFormat(data.firstAlarm.alarmTime, sDateFormat + ' - h:i:s A') || '--';
					var alarm = sensus.locale.get("filter.alarm." + data.firstAlarm.alarmEnum.toLowerCase());
					sHtml += '<div class="infowindow-title alarm">';
				} else {
					sHtml += '<div class="infowindow-title active">';
				}

				if (data.deviceModel.description) {
					sDeviceDescription = data.deviceModel.description;
				}

				sHtml += '<span class="ui-icon icon-colapse ui-icon-triangle-1-e"></span>';
				sHtml += $.sc.locale('commons.pages.deviceId') + ': <strong><a href="device/detail?id=' + sFlexNetId + '&deviceType=' + data.deviceType + '&typeEnum=' + escape(data.electricMeterTypeEnum) + '" class="alist">' + sDeviceId + '</a></strong>';

				if (!$.sc.isNullOrUndefined(data.firstAlarm)) {
					sHtml += '<span class="status">' + $.sc.locale('commons.pages.alert') + '</span>';
				}

				sHtml += '</div>';

				sHtml += '<div class="light-info collapse" style="display: none;">';

				if (!$.sc.isNullOrUndefined(data.firstAlarm)) {
					sHtml += '<div class="ui-state-error" style="display: block;"><span class="message"><strong>Alarm:</strong>' + alarm + '</span> <small>' + oAlarmTime + '</small></div>';
				}

				sHtml += '<table><tbody>';

				// Device Id

				sHtml += '<tr><td class="title">'+$.sc.locale('commons.pages.deviceId')+':</td>';
				sHtml += '<td>'+ sDeviceId +'</td></tr>';

				// Network Address
				sHtml += '<tr><td class="title">' + $.sc.locale('commons.pages.network_address') + ':</td>';
				sHtml += '<td>'+ sFlexNetId +'</td></tr>';

				// Devie Description
				sHtml += '<tr><td class="title">' + $.sc.locale('commons.pages.device_description') + ':</td>';
				sHtml += '<td>' + sDeviceDescription + '</td></tr>';

				sHtml += '</tbody></table>';

				if(this.bActionve)
				{
					var sChecked = ' checked ';

					if($.inArray(sFlexNetId, sensus.widgets.datatable.fnGetSelectedData(true)) != -1)
					{
						sChecked = ' ';
					}
					else
					{
						if(sensus.widgets.datatable.isAllRows)
						{
							sChecked = ' checked ';
						}
						else
						{
							var oGrep = $.grep(sensus.widgets.datatable.fnGetSelectedData(false, false), function(n) {
								return n.id == sFlexNetId;
							});

							if($.sc.isValidArray(oGrep))
							{
								sChecked = ' checked ';
							}
							else
							{
								sChecked = ' ';
							}
						}
					}
					sHtml += '<div class="infowindow-button-row"><ul><li class="checkbox"><input type="checkbox" '+sChecked+' class="map-checkbox" id="map-smartpoint-id-' + sFlexNetId + '" value="' + sFlexNetId + '"><label for="addSingleton">'+$.sc.locale('device.actions.includeInAction')+'</label></li></ul></div>';
				}


				sHtml += '</div>';

			}

			sHtml +="</div>";

			if(attributes.totalObj > 10)
			{
				sHtml += '<div class="infowindow-button-row">' + $.sc.locale("map.popup.zoom.doubleClick") + '</div>';
			}

			return sHtml +="</div>";

		};

		// Create a pop-up based on the light ids.
		var _buildPopUp = function (attributes) {

			var sHtml = '';
			var aIds = null;
			var alatLongTrunc = null;

			if(!$.sc.isNullOrUndefined(attributes.deviceIds))
			{
				aIds = attributes.deviceIds;
			}

			if(!$.sc.isNullOrUndefined(attributes.latLonTrunc.latitudeTrunc))
			{
				alatLongTrunc = [ attributes.latLonTrunc ];
			}

			var sHtml = "";

			var fnFillPopUpCallback = function (oRequest) {
				sHtml = functionalities.popup.fnFillPopUp(oRequest, attributes);
			}

			var oRequest = config.ajaxCalls.fetchAll.request(nCurrentTrunc, alatLongTrunc, aIds);

			$.sc.getJson(config.ajaxCalls.fetchAll.url, oRequest, false, fnFillPopUpCallback, null, false, true);

			return sHtml;

		};

		/** ################ MAP FUNCTIONALITY ################ */

		var _fetchMap = function(nTrunc, bDoZoom, bounds) {

			if (bBounds) {

				nTrunc = 3;
				bounds = bBounds;

			}

			var iCurrentZoom = objMap.getZoom();
			if (iCurrentZoom != iZoom) {

				if(objMap.popups.length){
					objMap.popups[0].destroy();
				}

			}

			if(objMap.popups.length == 0) {

				popupCenter = null;

				var extent = objMap.getExtent();

				if(!$.isEmptyObject(oRequest)){

					if(!nTrunc || isNaN(nTrunc)){

						var nTrunc = 0;

						if(iCurrentZoom > 2 && iCurrentZoom <= 4) {
							nTrunc = 1;
						}

						if(iCurrentZoom > 4 && iCurrentZoom < 7) {

							nTrunc = 2;

						}

						if(iCurrentZoom >= 7 && iCurrentZoom < 9) {

							nTrunc = 3;

						}

						if (iCurrentZoom >= 9){

							nTrunc = 4;

						}

						nCurrentTrunc = nTrunc;

					}

					if(extent && !bMapFromFilter)
					{
						extent.transform(new OpenLayers.Projection(nToProjection), new OpenLayers.Projection(nFromProjection));
						oRequest.bottomLeftLat = extent.bottom;
						oRequest.bottomLeftLon = extent.left;
						oRequest.topRightLat = extent.top;
						oRequest.topRightLon = extent.right;
					}
					else
					{
						oRequest.bottomLeftLat = null;
						oRequest.bottomLeftLon = null;
						oRequest.topRightLat = null;
						oRequest.topRightLon = null;
						bMapFromFilter = false;
					}
					oRequest.geoCodeTrunc = nTrunc;
//					oRequest.fetchBounds = bounds;

					var sInUrl = sUrl;

					if (!bounds) {
						sInUrl = "api/maps/fetchDevice"
					}

					$.sc.getJson(sInUrl, oRequest, false, functionalities.map.fetchMapCallback, null, false, true);

				}

			}

		};

		var _fetchMapCallback = function(response)
		{

			if (!$.sc.isNullOrUndefined(response.resultsSetInfo) && !$.sc.isNullOrUndefined(response.resultsSetInfo.endRow)) {
				$('#map-view-records').html(response.resultsSetInfo.endRow);
				$('#map-total-records, #results').html(response.resultsSetInfo.totalRowsAvailable);
				sensus.widgets.datatable.allRowsCount = response.resultsSetInfo.totalRowsAvailable;
			}

			if(response && response.geocodeDeviceInfo.length){

				if(!$('.olMap').hasClass('detail-map')){

					sensus.widgets.datatable.objLatLon.splice(0, sensus.widgets.datatable.objLatLon.length);
					functionalities.map.buildMarkersLayer(response.geocodeDeviceInfo);

				}

			} else {

				if(objMap.getLayersByName(objMarkers.name).length){

					objMap.getLayersByName(objMarkers.name)[0].removeAllFeatures();

				}

			}

		};

		var _buildMarkersLayer = function (response)
		{

			var iCurrentZoom = objMap.getZoom();

			if(iZoom != iCurrentZoom){

				if(objMap.popups.length){
					objMap.popups[0].destroy();
				}

			}

			iZoom = iCurrentZoom;

			if(response && response.length > 0) {

				if(objMap.getLayersByName(objMarkers.name).length){

					objMap.getLayersByName(objMarkers.name)[0].removeAllFeatures();

				} else {

					objMap.addLayer(objMarkers);

				}

				if (iZoom > 8) {

					$('#draw-polygon').removeClass('ui-state-disabled');

				} else {

					$('#draw-polygon').addClass('ui-state-disabled').removeClass('ui-state-focus');

					$('#draw-pan').addClass('ui-state-focus');

				}

				var point = null;
				var aIds = null;
				var aFeatures = [];

				if(!$.sc.isNullOrUndefined(response[0]))
				{
					if(response[0].bottomLeftLat && response[0].latitudeAvg == null){

						var point1 = new OpenLayers.Geometry.Point(response[0].bottomLeftLon, response[0].bottomLeftLat).transform(nFromProjection, nToProjection);
						var point2 = new OpenLayers.Geometry.Point(response[0].topRightLon, response[0].topRightLat).transform(nFromProjection, nToProjection);
						aFeatures.push(new OpenLayers.Feature.Vector(point1));
						aFeatures.push(new OpenLayers.Feature.Vector(point2));
						bBounds = false;

					} else {

						for (var i = 0; i < response.length; i++) {

							point = new OpenLayers.Geometry.Point(response[i].longitudeAvg, response[i].latitudeAvg).transform(nFromProjection, nToProjection);
							var iAlert = null;


							var flexNetId = null;

							if (response[i].radio && response[i].radio.flexNetId) {
								flexNetId = response[i].radio.flexNetId;
							}

							if (!$.sc.isNullOrUndefined(response[i].alertEnum) && response[i].alertEnum.length)
							{
								iAlert = 1;
							}

							aFeatures.push(new OpenLayers.Feature.Vector(point,
									{
										totalObj	: response[i].devicesTotalByLatLong,
										latLonTrunc	: { latitudeTrunc : response[i].latitudeTrunc, longitudeTrunc : response[i].longitudeTrunc },
										deviceIds	: flexNetId,
										groupStatus	: iAlert,
										groupBounds	: [response[i].bottomLeftLon, response[i].bottomLeftLat, response[i].topRightLon, response[i].topRightLat]
									}
							));

						}

					}

					objMarkers.addFeatures(aFeatures);
				}
				else
				{
					bZoom = false;
				}

			}

			if(bZoom){

				_zoomToExtent();

			} else {

				$.sc.stopProgressBar();

			}

		};

		var _buildMapSettings = function (sContainer) {
			popupCenter = null;

			/** Set map options **/
			var options = {
				controls : [
						   new OpenLayers.Control.Navigation({mouseWheelOptions: {interval: 100}}),
						   new OpenLayers.Control.PanZoomBar(),
						   new OpenLayers.Control.MousePosition( { displayProjection : new OpenLayers.Projection(nFromProjection) } ),
						   new OpenLayers.Control.Attribution()
						   ]
			};

			/** Create map object with options and add layer **/
			if(!$.isEmptyObject(objMap)) {

				//objLatLon = {};
				objLayer = {};
				objMap = {};

			}

			objMap = new OpenLayers.Map(sContainer, options);

			var oResolutionsOffset = { 	zoomOffset: 8,
										resolutions: [611.4962261718752,305.7481130859376,152.8740565429688,76.4370282714844,38.2185141357422, 19.1092570678711,
										              9.55462853393555, 4.77731426696777,2.38865713348389,1.194328566741945, 0.597164283],
										transitionEffect: 'resize',
										attribution: null
									}

			oResolutionsOffset.attribution = "&copy; OpenStreetMap contributors";
	        baseMQOSM = new OpenLayers.Layer.OSM(sensus.settings.map.osmName,  [sensus.settings.map.osmUrl1, sensus.settings.map.osmUrl2, sensus.settings.map.osmUrl3, sensus.settings.map.osmUrl4], oResolutionsOffset);

	        oResolutionsOffset.attribution = "Portions Courtesy NASA/JPL-Caltech and U.S. Depart. of Agriculture, Farm Service Agency";
	        baseAerial = new OpenLayers.Layer.OSM(sensus.settings.map.aerialName, [sensus.settings.map.aerialUrl1, sensus.settings.map.aerialUrl2, sensus.settings.map.aerialUrl3, sensus.settings.map.aerialUrl4], oResolutionsOffset);

	        if(sContainer == 'detail-map-container'){

				$('.olMapViewport').height('500px');

			}

			objMap.addLayers([baseMQOSM, baseAerial, objMarkers]);
			objMap.addControl(new OpenLayers.Control.LayerSwitcher());
		};

		/**
		 * @param objLayer
		 * 			object, layer map
		 * @param objLatLon
		 * 			object, latitude, longitude, status and light id object
		 * @param centerLat
		 * 			string, center latitude
		 * @param centerLon
		 * 			string, center longitude
		 * @param sContainer
		 * 			string, dom to render the map
		 * @param iZoom
		 * 			integer, zoom map
		 */
		var _buildMap = function (latLon, centerLat, centerLon, sContainer, iZoom, bCluster) {

			functionalities.map.buildMapSettings(sContainer);

			if(sContainer == 'map') {

				var stylePolygon = $.extend(true, {}, OpenLayers.Feature.Vector.style['default']); // get a copy of the default style
				stylePolygon.fillColor = "${getFillColor}";

				var polygonStyleMap = new OpenLayers.StyleMap({
					"default": new OpenLayers.Style(stylePolygon, {
						context: {
							getFillColor: function (feature) {

								var oNewVectorId = feature.id.replace(/\./g,'\\.').split('.')[2];
								return _getRandomColor(oNewVectorId);
							}
						}
					})
				});

				objPolygons = new OpenLayers.Layer.Vector( "Polygon", {  styleMap: polygonStyleMap });

				objMap.addLayer(objPolygons);

				var drawControls = {

					selecthover: new OpenLayers.Control.SelectFeature(
						objMarkers,
						{
							multiple: false,
							hover: false
						}
					),

					polygon: new OpenLayers.Control.DrawFeature(objPolygons, OpenLayers.Handler.Polygon)
				};

				for(var key in drawControls) {
					objMap.addControl(drawControls[key]);
				}

				drawControls.selecthover.activate();

				var polygonAddedFunction = function (e) {
					events.polygonAdded(e, drawControls);
				}

				objPolygons.events.on({
					featuresadded : polygonAddedFunction
				});

				$('#draw-polygon').click({drawControls : drawControls}, events.drawPolygon);

				$('#draw-pan').click({drawControls : drawControls}, events.drawPan);

				functionalities.map.fetchMap(null, true, true);
				functionalities.map.fetchMap(null, true, false);
				objMap.events.register("moveend", null, functionalities.map.fetchMap);

			} else {

				if (latLon.length && !$.sc.isNullOrUndefined(latLon[0].latitudeAvg) && !$.sc.isNullOrUndefined(latLon[0].longitudeAvg)) {

					/** Set center points to map **/
					objMap.setCenter(new OpenLayers.LonLat(latLon[0].centerLon, latLon[0].centerLat).transform(nFromProjection, nToProjection ), iZoom);
					functionalities.map.buildMarkersLayer(latLon);

				} else {
					objMap.events.register("moveend", null, functionalities.map.fetchMap);
					functionalities.map.fetchMap(null, true, true);
				}

				var drawControls = {

					selecthover: new OpenLayers.Control.SelectFeature(
						objMarkers,
						{
							multiple: false,
							hover: false
						}
					)
				};

				for (var key in drawControls) {
					objMap.addControl(drawControls[key]);
				}

				drawControls.selecthover.activate();

			}

			objMap.addControl(objSelectorEvents);
			objSelectorEvents.handler.map = objSelectorEvents.handler.layer.map;
			objSelectorEvents.activate();
			$('#map-total-records').text($('#total-records').text());

		};

		/** ###################### General ####################### */

		var _zoomToExtent = function(){

			var markers = objMap.getLayersByName(objMarkers.name);
			if(markers.length){
				/** Get zoom dynamic to show all markers **/
				bZoom = false;
				var bounds = markers[0].getDataExtent();
				objMap.zoomToExtent(bounds);
				objMap.getLayersByName(objMarkers.name)[0].setVisibility(true);
			}

		};

		var _zoomTo = function(n){

			objMap.zoomTo(n);

		};

		var _getVectorArea = function(){

			return vector.area;

		};

		var _getVectorColor = function(){

			return vector.color;

		};

		var _getVectorIndex = function(){

			return vector.index;

		};

		var _getVectorAIds = function(){

			return vector.aIds;

		};

		var _destroyVectorFeatures = function(){

			var polygonsLayer = objMap.getLayersByName(objPolygons.name);

			if ($.sc.isValidArray(polygonsLayer))
			{
				polygonsLayer[0].destroyFeatures();
				$('#polygon-filters').empty();
			}

		}

		var _destroyVectorFeaturesById = function(id){

			var polygonsLayer = objMap.getLayersByName(objPolygons.name);

			if ($.sc.isValidArray(polygonsLayer))
			{

				var feature = $.grep(polygonsLayer[0].features, function(o) {
					return o.id == id;
				});

				if ($.sc.isValidArray(feature))
				{
					feature[0].destroy();
				}

			}

		}

		var _getLayersByName = function(name){

			var layers = objMap.getLayersByName(name);

			return ( $.sc.isValidArray(layers[0]) ) ? layers[0] : false;

		}

		var _mapExists = function()
		{
			return !$.isEmptyObject(objMap);
		}

		var _getCurrentPopUpLights = function()
		{
			return currentPopUpLights;
		}

		// Random colors for vectors.
		var _getRandomColor = function(id){

			var letters = '0123456789ABCDEF'.split('');
			var color = '#';
			for (var i = 0; i < 6; i++) {

				color += letters[Math.round(Math.random() * 15)];

			}

			if(oColors[id]){

				color = oColors[id];

			} else {

				oColors[id] = color;

			}

			vector.color = color;
			return color;

		};

		/** #################### BUILD #################### */

		var build = function(externalConfig, sContainer, objLatLon, iZoom, sMapType) {

			_readConfig(externalConfig);

			if (sMapType != "small")
			{
				$("#map").css({height : config.height});
			}

			sUrl 	 = config.ajaxCalls.fetchBounds.url;
			oRequest = config.ajaxCalls.fetchBounds.request;

			oInitialRequest = oRequest;
			// Setting bZoom every time a map is created, else it won't zoom to max possible.
			bZoom = true;

			objMarkers = new OpenLayers.Layer.Vector("Markers", { styleMap: new OpenLayers.StyleMap({"default": styleMarker , "select": styleMarker})});

			objSelectorEvents = new OpenLayers.Control.FeatureSelector(objMarkers, {
			    onClick: function(feature) {
			    	events.pointerSingleClick(feature);
			    },

			    onDblclick: function(feature) {
			    	events.pointerDoubleClick(feature);
			    }
			});


			$('#detail-map-total').remove();

			// Check from where the maps is being summoned.
			if (sMapType == "small") {

				/** build map for dialog pop-up **/
				functionalities.map.buildMap(objLatLon, objLatLon[0].longitudeAvg, objLatLon[0].longitudeAvg, sContainer, 1, true);

			} else {

				/** build list map **/
				functionalities.map.buildMap(objLatLon, 31.56073, -113.01831, sContainer, iZoom, true); // TODO

			}
		}


		var _readConfig = function (externalConfig) {

			if ($.sc.isNullOrUndefined(externalConfig))
			{
				return;
			}

			if (!$.sc.isNullOrUndefined(externalConfig.ajaxCalls) && !$.sc.isNullOrUndefined(externalConfig.ajaxCalls.fetchBounds))
			{
				if (!$.sc.isNullOrUndefined(externalConfig.ajaxCalls.fetchBounds.url))
				{
					config.ajaxCalls.fetchBounds.url = externalConfig.ajaxCalls.fetchBounds.url;
				}

				if (!$.sc.isNullOrUndefined(externalConfig.ajaxCalls.fetchBounds.request))
				{
					config.ajaxCalls.fetchBounds.request = externalConfig.ajaxCalls.fetchBounds.request;
				}

			}

			if (!$.sc.isNullOrUndefined(externalConfig.ajaxCalls) && !$.sc.isNullOrUndefined(externalConfig.ajaxCalls.fetchAll))
			{
				if (!$.sc.isNullOrUndefined(externalConfig.ajaxCalls.fetchAll.url))
				{
					config.ajaxCalls.fetchAll.url = externalConfig.ajaxCalls.fetchAll.url;
				}

				if (!$.sc.isNullOrUndefined(externalConfig.ajaxCalls.fetchAll.request))
				{
					config.ajaxCalls.fetchAll.request = externalConfig.ajaxCalls.fetchAll.request;
				}

			}

			if (!$.sc.isNullOrUndefined(externalConfig.functionalities) && !$.sc.isNullOrUndefined(externalConfig.functionalities.popup))
			{

				$.extend(functionalities.popup, externalConfig.functionalities.popup);
			}

			if (!$.sc.isNullOrUndefined(externalConfig.height))
			{
				config.height = externalConfig.height;
			} else {
				config.height = "600px";
			}
		}

		/**
		 * @param sContainer
		 * 			string, dom to render the map
		 * @param objLatLon
		 * 			object, latitude, longitude, status and light id object
		 * @param iGroupId
		 * 			integer, group id
		 * @param sName
		 * 			string, group or schedule name
		 * @param iScheduleId
		 * 			integer, schedule id
		 */

		var mapFromFilter = function(bFromList, sUrl) {

//			$.sc.startProgressBar();
			sensus.util.page.startProgressBar();

			if (!$.sc.isNullOrUndefined(sUrl))
			{
				config.ajaxCalls.fetchAll.url = sUrl;
			}

			// Remove previous created vectors
			$.sc.map.destroyVectorFeatures();

			if(!bFromList)
			{
				sensus.widgets.datatable.isAllRows = false;
				sensus.widgets.datatable.selectedRows = [];
				sensus.widgets.datatable.setTotalResult(false);
			}

			var oSearch = sensus.pages.device.smartpointTable.data().config.oSettings.fnRequest();
			oRequest = new InquiryDeviceRequest(oSearch);

			objMap.zoomTo(1);

			bZoom = true;
			bMapFromFilter = true;
			functionalities.map.fetchMap(1, true, true);

//			$.sc.stopProgressBar();
			sensus.util.page.stopProgressBar();
		};

		var config = {

			height : null,

			mapTypes : {
				osm : {
					name : "",
					urls : []
				},
				aerial : {
					name : "",
					urls : []
				},

			},

			ajaxCalls :  {
				fetchBounds : {
					url : null,
					request : null,
					callback : null
				},

				fetchAll : {
					url : null,
					request : null,
					callback : null
				}
			},

			detailUrl : ""

		};

		var events = {
			pointerSingleClick : _pointerSingleClick,
			pointerDoubleClick : _pointerDoubleClick,
			closePopup : _closePopup,
			listClick : _listClick,
			popUpSlideBox : _popUpSlideBox,
			mapCheckbox : _mapCheckbox,
			polygonAdded : _polygonAdded,
			drawPolygon : _drawPolygon,
			drawPan : _drawPan
		};

		var functionalities = {

			map : {
				fetchMap : _fetchMap,
				fetchMapCallback : _fetchMapCallback,
				buildMap : _buildMap,
				buildMapSettings : _buildMapSettings,
				buildMarkersLayer : _buildMarkersLayer
			},

			popup : {
				bActionve : true,
				fnFillPopUp : _fnFillPopUp,
				buildPopUp : _buildPopUp
			},

			draw : {}

		};

		return {
			build : build,
			mapExists : _mapExists,
			zoomTo : _zoomTo,
			destroyVectorFeatures	: _destroyVectorFeatures,
			destroyVectorFeaturesById	: _destroyVectorFeaturesById,
			zoomToExtent : _zoomToExtent,
			mapFromFilter : mapFromFilter,
			getVectorArea 			: _getVectorArea,
			getVectorColor 			: _getVectorColor,
			getVectorIndex 			: _getVectorIndex,
			getVectorAIds			: _getVectorAIds
		}

	})();
});