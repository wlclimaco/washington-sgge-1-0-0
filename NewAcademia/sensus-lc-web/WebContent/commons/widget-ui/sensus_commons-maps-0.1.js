/**
 * @fileoverview Defines common map-related functionality.
 * @author Cristiane Cobo / Lucas Oliveira
 */
/**
 * The main namespace for map-related functionality.
 * Used in early versions and will be migrated in the future.
 */
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

						sensus.settings.pageSizeShowDialog = "1";

						$.sc.savePropertyProfile( { "SHOW_DIALOG_POLYGON" : "false" } );
						$(this).dialog('close');

					}

				},
					/**
					 * Just close the dialog.
					 */
					{

					id: "apply-polygon-cancel",
					text :$.sc.locale("action.addtoschedule.cancel"),
					click : function() {

						$(this).dialog('close');

					}

			}]

		}

	}

}


	$.fn.extend($.sc , function()
	{

		var sUrl = null;
		var bBounds = true;
		var bZoom = true;
		var iZoom = -1;
		var nCurrentTrunc = null;
		var oInitialRequest = null;
		var oRequest = null;
		var oGeoCodeCriteria = {};
		var popupCenter = null;
		var bMapFromFilter = false;
		var currentPopUpLights = null;
		var nFromProjection	= new OpenLayers.Projection("EPSG:4326");// transform from WGS 1984
		var nToProjection = new OpenLayers.Projection("EPSG:900913");// to Spherical Mercator Projection
		var oLayerName = { markers: $.sc.locale("map.layer.markers"), poleInfo: $.sc.locale("map.layer.poleInfo"), polygon: $.sc.locale("map.layer.polygon") };
		var vector = { color : '', area : 0, index : 0, aIds : [], aLatLonTruncs : [] };
		var drawControls = null;
		var oColors = {};
		objMap = null;
		var styleMarker = new OpenLayers.Style({ cursor : "${pointer}", labelSelect : true, externalGraphic: "${status}", pointRadius: "${size}", fontColor: "#FFFFFF", fontSize: "15px", fontWeight: "bold", label: "${count}", labelAlign : "cm", labelYOffset : "${offset}" }, {
			context: {
				/**
				 * Bug-fix: check for browser version.
				 */
				offset : function(){
					if($.browser.msie && ($.browser.version == '9.0' || $.browser.version == '10.0')){
						return -5;
					} else {
						return 0;
					}
				},
				/**
				 * Set mouse style depending on click function: if pop-up or zoom in.
				 */
				pointer : function(feature){
					if(feature && feature.attributes && feature.attributes.totalLights){

						if(!$.sc.isNullOrUndefined(feature.attributes.groupBounds[0])
								|| (feature.attributes.lightIds && !$.sc.isNullOrUndefined(feature.attributes.lightIds[0]))
								&& $.address.path().split('/')[1] != 'lightDetail'){
							return "pointer";
						}else{
							return "default";
						}

					} else {
						return "default";
					}
				},
				/**
				 * Set the circle's size according to the number of lights it holds.
				 */
				size : function(feature) {
					var s = 6; // Why initialize with 6?
					if(feature && feature.attributes && feature.attributes.totalLights){
						var t = feature.attributes.totalLights;
						if(t > 1 && t <= 9){
							s = 11;
						} else if( t > 9 && t <= 99) {
							s = 20;
						} else if(t >= 100 && t <= 999){
							s = 30;
						} else if(t >= 1000){
							s = 40;
						}
					}
					return s;
				},
				/**
				 * Set the background icon (circle color) depending on the priority status of the light set.
				 */
				status : function(feature){
					if(feature && feature.attributes && feature.attributes.totalLights)
					{
						if(feature.attributes.totalLights > 9)
						{
							return "images/map/map_icn_dot_hd_"+feature.attributes.groupStatus+".png";
						}
						return "images/map/map_icn_dot_"+feature.attributes.groupStatus+".png";
					}
					else
					{
						return "images/map/map_icn_dot_2.png";
					}
				},
				/**
				 * Returns the number to be set on the marker.
				 */
				count: function(feature) {
					if(feature && feature.attributes && feature.attributes.totalLights){
						var t = feature.attributes.totalLights;
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

		var objMarkers = null;
		var objPoleInfoMarkers = null;
		var objSelectorEvents = null;
		var objHoverControl = null;

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
		var _maps = function(sContainer, objLatLon, url, request, sMapType, iZoom)
		{
			oRequest = null;
			sUrl = url;
			oRequest = request;
			oInitialRequest = oRequest;
			// Setting bZoom every time a map is created, else it won't zoom to max possible.
			bZoom = true;

			// Keeps the map center point for pop-up open/close.
			if(objLatLon.length > 0)
			{
				var centerLat = objLatLon[0].latitudeAvg;
				var centerLon = objLatLon[0].longitudeAvg;
			}
			objMarkers = new OpenLayers.Layer.Vector(oLayerName.markers, { styleMap: new OpenLayers.StyleMap({"default": styleMarker , "select": styleMarker})});

			var onFeatureHighlighted = function(evt)
			{
				var oFeature = evt.feature;
				var fnHighlightCallBack = function(oResponse)
				{
					var oPopUp = _oPoleInfo.generatePopUp(oResponse.lightList);
					if(oPopUp)
					{
						var position = OpenLayers.LonLat.fromString(oFeature.geometry.toShortString());
						var popup = new OpenLayers.Popup("popup", position, new OpenLayers.Size(oPopUp.width, oPopUp.height), oPopUp.sHtml);
						popup.backgroundColor = null;
						popup.autosize = true;
						popup.panMapIfOutOfView = false;
						popup.type = "hover";
						oFeature.popup = popup;
						objMap.addPopup(popup);
					}
				}

				var aLatLongTruncs = [oFeature.attributes.latLonTrunc];

				var oSearchCriteria = sensus.pages.smartpoint.smartpointTable.data().config.oSettings.fnRequest();
				var oPoleInfoRequest = new lightTableRequest(oSearchCriteria);
				oPoleInfoRequest.lightCriteria.geoCodeCriteria = new GeoCodeCriteria(oRequest.lightCriteria.geoCodeCriteria.trunc, aLatLongTruncs);
				oPoleInfoRequest.pageSize = 10;

				$.sc.getJson("api/light/fetchall", oPoleInfoRequest, false, fnHighlightCallBack, null, false, true, false);

			};

			var onFeatureUnHighlighted = function(evt)
			{
				if(evt.feature.popup && evt.feature.popup.events && evt.feature.popup.type == "hover")
				{
		        	evt.feature.popup.destroy();
				}
			}

			var oHoverTimer;
			var oHoverDelay = 300;

			objHoverControl = new OpenLayers.Control.SelectFeature(objMarkers, {
				hover: true,
				highlightOnly: true,
			    eventListeners: {
			         featurehighlighted: function (evt) {
			        	 oHoverTimer = window.setTimeout(function () {

				        	onFeatureHighlighted(evt);

				        }, oHoverDelay);
					 },
			         featureunhighlighted: function (evt) {
			        	 if(oHoverTimer)
			        	 {
			        		 window.clearTimeout(oHoverTimer);
			        		 oHoverTimer = null;
			        	 }
			        	 onFeatureUnHighlighted(evt);
					 }
			    }
			});

			objSelectorEvents = new OpenLayers.Control.FeatureSelector(objMarkers, {
			    onClick: function(feature) {
			    	fnSingleClick(feature);
			    },

			    onDblclick: function(feature) {
			    	fnDoubleClick(feature);
			    }
			});

			var fnSingleClick = function(selectedFeature)
			{
				if($.address.path().split('/')[1] != 'lightDetail')
				{
					var iPopUpSizeH = 310;
					var iPopUpSizeW = 400;
					// Unset any existing pop-up.
					_closePopups();

					if(!popupCenter)
					{
						 popupCenter = objMap.getCenter();
					}

					// Creates the pop-up and add it to the map.
					var position = new OpenLayers.LonLat(selectedFeature.geometry.getBounds().getCenterLonLat().lon, selectedFeature.geometry.getBounds().getCenterLonLat().lat);
					var popup = new OpenLayers.Popup("popup", position, null, _buildPopUp(selectedFeature.attributes), false);
					popup.backgroundColor = null;
					popup.contentSize.h = iPopUpSizeH;
					popup.contentSize.w = iPopUpSizeW;
					popup.autosize = true;
					popup.panMapIfOutOfView = true;
					selectedFeature.popup = popup;
					objMap.addPopup(popup);

					$('.olMap .alist').click(function(e)
					{
						e.preventDefault();
						$.sc.closeActionDialog("#action-dialog").empty();
						$('#messaging-main').hide();
						sensus.util.session.createSession('COLUMNS', { listUrl : $.address.queryString() });
						oParam = sensus.commons.lib.ajax.param;
						$.sc.getPage($.extend({}, oParam, {
							$element : $(this)
						}));
					});

					// Close the pop-up
					$('.icon-close').click(function()
					{
						_closePopups();
						$('.olMapViewport').click();
						var oCurCenter = objMap.getCenter();
						if(oCurCenter.lat == popupCenter.lat && oCurCenter.lon == popupCenter.lon)
						{
							_getData();
						}
						else
						{
							objMap.panTo(popupCenter);
						}

					});

					// Open items (slide) in the pop-up.
					$('.infowindow-title').click(function(e)
					{
						e.preventDefault();

						$(this).addClass('on');

						// Close previous clicked slide
						$('.light-info').each(function()
						{

							if(!$(this).prev().hasClass('on'))
							{
								$(this).slideUp('slow');
								$(this).prev().find('.icon-colapse').removeClass('ui-icon-triangle-1-s').addClass('ui-icon-triangle-1-e');
							}
							else
							{
								$(this).prev().removeClass('on');
							}

						});


						// Change the clicked item triangle icon (open/close).
						var that = $(this).find('.icon-colapse');

						if(that.hasClass('ui-icon-triangle-1-e'))
						{
							that.removeClass('ui-icon-triangle-1-e').addClass('ui-icon-triangle-1-s');
						}
						else
						{
							that.removeClass('ui-icon-triangle-1-s').addClass('ui-icon-triangle-1-e');
						}

						// Open the clicked light slide.
						$(this).next(".collapse").toggle('blind',null,500);

					});

					// If singleton, open detail by default
					if(selectedFeature.attributes.totalLights == 1)
					{
						var currHeader = $(".infowindow-title");
						currHeader.next(".collapse").toggle('blind',null,100);
						currHeader.find('.icon-colapse').removeClass('ui-icon-triangle-1-e').addClass('ui-icon-triangle-1-s');
					}


					// Control light dialog
					$(".map-control-lights").wLightControl(false);

					// Controls the light selection (datatable - count and actions).
					$(".map-checkbox").click(function()
					{
						var bChecked = $(this).attr('checked')?true:false;
						if(bChecked)
						{
							sensus.widgets.datatable.addSelected($(this).attr("value"), false);
							$('#smartpoint-table td input[value='+$(this).attr("value")+']').attr('checked', true);
						}
						else
						{
							sensus.widgets.datatable.addDeselected($(this).attr("value"), false);
							$('#smartpoint-table td input[value='+$(this).attr("value")+']').attr('checked', false);
						}

						sensus.widgets.datatable.setTotalResult(false);
						$("#actions .message").removeClass("ui-state-error");

					});
				}
			}

			var fnDoubleClick = function(selectedFeature)
			{
				if(selectedFeature.geometry.getBounds() && selectedFeature.geometry.getBounds().getCenterLonLat() && selectedFeature.geometry.getBounds().getCenterLonLat().lon && selectedFeature.geometry.getBounds().getCenterLonLat().lat)
				{
					if(selectedFeature.attributes.ids && selectedFeature.attributes.ids.length == 1)
					{
						objMap.zoomToExtent(selectedFeature.geometry.getBounds(), closest=true);
					}
					else if(selectedFeature.attributes.ids && selectedFeature.attributes.ids.length > 0 && selectedFeature.attributes.ids.length <= 8)
					{
						var _zoomToFeature = function(response)
						{
							response = response.geocodeLightList;
							var bounds =
							[
								response[0].bottomLeftLon,
								response[0].bottomLeftLat,
								response[0].topRightLon,
								response[0].topRightLat
							];
							bounds = new OpenLayers.Bounds(bounds).transform(nFromProjection, nToProjection);
							objMap.zoomToExtent(bounds);
						}

						var oLightCriteria = new LightCriteria(selectedFeature.attributes.ids, null, new GeoCodeCriteria(3));
						$.sc.getJson(sUrl, new lightRequest(oLightCriteria), false, _zoomToFeature, null, false, true, false);

					}
					else if(selectedFeature.attributes.groupBounds[0] != undefined && selectedFeature.attributes.groupBounds[0] != null)
					{
						bounds = new OpenLayers.Bounds(selectedFeature.attributes.groupBounds).transform(nFromProjection, nToProjection);
						objMap.zoomToExtent(bounds);
					}

				}
			}

			$('#detail-map-total').remove();

			// Check from where the maps is being summoned.
			if (sMapType == "small")
			{
				/** build map for dialog pop-up **/
				_buildMap(objLatLon, objLatLon[0].centerLat, objLatLon[0].centerLon, sContainer, 1, true);
			}
			else
			{
				/** build list map **/
				_buildMap(objLatLon, sensus.settings.userContext.tenant.latitude, sensus.settings.userContext.tenant.longitude, sContainer, iZoom, true);
			}
		};

		// Create a pop-up based on the light ids.
		var _buildPopUp = function (attributes)
		{
			var sHtml = '';

			function fnFillPopUp(lightData)
			{
				if(lightData.lightList.length)
				{
					var lights = lightData.lightList;
					currentPopUpLights = lights;

					sHtml += '<div class="rounded box-shadow messageMap"><div class="messageArrow"></div>';
					// The close pop-up icon
					sHtml += '<div style="background-color:#F0F0F0; padding: 1px; height: 17px;">';
					sHtml += '<span title="' + $.sc.locale('message.action.close') + '" class="ui-icon icon-close ui-icon-closethick">' + $.sc.locale('message.action.close') + '</span>';
					sHtml += '</div>';
					// The scrollable container
					sHtml += '<div id="lightContainer">';
					for(a in lights)
					{

						var data = lights[a];
						var sStatusMessage = data.lastNotificationHistory.precedence;
						if(!sStatusMessage){
							sStatusMessage = $.sc.locale("smartpoint.status.unknown");
						}
						var sPoleId = data.poleId;
						var sLampType = "--";
						if(data.configuration.lampTypeWattageDimmable)
						{
							sLampType = data.configuration.lampTypeWattageDimmable;
						}
						if(data.deviceLifeCycleState == "BLINK")
						{
							if(data.blinkStatusValue == 1)
							{
								sMsg = $.sc.locale("smartpointdetail.control.blinkfast");
							}
							else if(data.blinkStatusValue == 2)
							{
								sMsg = $.sc.locale("smartpointdetail.control.blinkslow");
							}
							else
							{
								sMsg = $.sc.locale("smartpoint.actions.controlLights.blink");
							}

						}
						else if(data.deviceLifeCycleState == "ON" || data.deviceLifeCycleState == "DIM")
						{
							if(data.intensityValue != 0 && data.intensityValue != 6)
							{
								sMsg = 'DIM '+data.intensityPercentage+'%';
							}
							else
							{
								sMsg = $.sc.locale("smartpointdetail.control.lighton");
							}

						}
						else
						{
							sMsg = $.sc.locale('smartpointdetail.control.lightoff');
						}


						var sLightState = sMsg;
						var aMessages = data.lastNotificationHistory;
						var sFlexNetId = data.radio.flexNetId;
						var sLastMessageDate = data.lastNotificationHistory.updateDate;
						var sLastMessage = null;
						if(sLastMessageDate)
						{
							var oDate		= $.sc.dateFormat(sLastMessageDate, 'dateObj', null, true);
							sLastMessage 	= Globalize.format(new Date(oDate.getFullYear(), oDate.getMonth(), oDate.getDate()),'D');
							sLastMessage	+= ', '+ $.sc.dateFormat(oDate, 'h:i A', null, false);
						}
						else
						{
							sLastMessage = $.sc.locale("smartpoint.status.unknown");
						}

						//light-levels
						var aLightLevels = [];
						if(data.configuration.partNumberConfigurations && data.configuration.partNumberConfigurations.length)
						{
							for (var i = 0; i < data.configuration.partNumberConfigurations.length; i++)
							{
								aLightLevels.push(data.configuration.partNumberConfigurations[i].percentage);
							}

						}
						else
						{
							aLightLevels = [25,50,75,90];
						}

						sHtml += '<div class="infowindow-title '+sStatusMessage.toLowerCase()+'">';
						sHtml += '<span class="ui-icon icon-colapse ui-icon-triangle-1-e"></span>';
						sHtml += $.sc.locale('search.label.poleid')+': <strong>';

						if (sensus.settings.userContext.userRole != "ROLE_Role.Analytic User")
						{
							sHtml += '<a class="alist" href="lightDetail?id='+data.id+'">'+sPoleId+'</a> </strong>';
						}
						else
						{
							sHtml += sPoleId + '</strong>';
						}

						if(sStatusMessage == $.sc.locale("smartpoint.status.unknown"))
						{
							sHtml += '<span class="status">' + sStatusMessage.toUpperCase();
						}
						else
						{
							sHtml += '<span class="status">' + $.sc.locale('smartpoint.status.'+ sStatusMessage.toUpperCase());
						}

						sHtml +='</span></div>';

						sHtml += '<div class="light-info collapse" style="display: none;">';

						//TODO verify if every message is being shown
						if(aMessages.alertClassifications.length)
						{
							var sHtmlMessages = '';
							sHtmlMessages += '<div id="status-messages-popup" class="status-messages-'+sStatusMessage.toLowerCase()+'"><dl>';
							var nSize = 1;
							var bShowMessageBox = false;
							var aMessagesSize = aMessages.alertClassifications.length;
							if(aMessagesSize > 2)
							{
								nSize = 2;
							}
							for (var i = 0; i < nSize; i++)
							{
								if(aMessages.alertClassifications[i].alertSubType)
								{
									bShowMessageBox = true;
									sHtmlMessages += '<li><span class="message"><strong>'+$.sc.locale("sensus.mlc.alert_subtype." + aMessages.alertClassifications[i].alertSubType)+': </strong>'+$.sc.dateFormat(aMessages.alertClassifications[i].messageDate, 'simpleDate')+'  '+$.sc.dateFormat(aMessages.alertClassifications[i].messageDate, 'h:i:s.fff A')+'</span></li>';
								}
							}

							sHtmlMessages += '</dl></div>';

							if(bShowMessageBox)
							{
								sHtml += sHtmlMessages;
							}
						}

						if (sensus.settings.userContext.userRole != "ROLE_Role.Analytic User" && data.lastNotificationHistory.lifeCycleState != "DEACTIVATED")
						{
							sHtml += '<div class="lighting-controls"><span class="hide" id="light-levels">'+[aLightLevels]+'</span><label class="title">' + $.sc.locale("smartpoint.actions.control") + '</label><button class="fg-button ui-widget ui-state-default ui-corner-all map-control-lights" data="'+data.id+'">' + $.sc.locale("smartpoint.actions.control") + '</button></div>';
						}

						sHtml += '<table><tbody><tr><td class="title">'+$.sc.locale('smartpointdetail.status.lightonoff')+':</td><td>'+sLightState+'</td></tr><tr><td class="title">'+$.sc.locale('smartpoint.filter.lamptype')+':</td><td>'+sLampType+'</td></tr><tr><td class="title">'+$.sc.locale('search.label.flexnetid')+':</td>';
						sHtml += '<td>'+sFlexNetId+'</td></tr><tr><td class="title">'+$.sc.locale('smartpointdetail.status.lastMessage')+':</td><td>'+sLastMessage+'</td></tr></tbody></table>';

						var sUrl = $.address.path();
						if(sUrl == "/light")
						{
							var sChecked = ' checked ';
							data.id = data.id.toString();
							if($.inArray(data.id,sensus.widgets.datatable.deselectedRows) != -1)
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
									if($.inArray(data.id,sensus.widgets.datatable.selectedRows) != -1)
									{
										sChecked = ' checked ';
									}
									else
									{
										sChecked = ' ';
									}
								}
							}
							sHtml += '<div class="infowindow-button-row"><ul><li class="checkbox"><input type="checkbox" '+sChecked+' class="map-checkbox" id="map-smartpoint-id-'+data.id+'" value="'+data.id+'"><label for="addSingleton">'+$.sc.locale('smartpoint.actions.includeInAction')+'</label></li></ul></div>';
						}
						sHtml += '</div>';
					}

					sHtml +="</div>";
					if(attributes.totalLights > 10)
					{
						sHtml += '<div class="infowindow-button-row">' + $.sc.locale("map.popup.zoom.doubleClick") + '</div>';
					}
					sHtml +="</div>";
				}

			}

			var oSearchCriteria = null;
			if($.address.path() == "/light")
			{
				oSearchCriteria = sensus.pages.smartpoint.smartpointTable.data().config.oSettings.fnRequest();

			}
			else if(!$.sc.isNullOrUndefined(oInitialRequest))
			{
				oSearchCriteria = oInitialRequest;
			}
			else
			{
				oSearchCriteria = new SearchCriteria();
			}

			var aLightIds = null
			if(!$.sc.isNullOrUndefined(attributes.lightIds))
			{
				aLightIds = attributes.lightIds;
			}

			var alatLongTrunc = null;
			if(!$.sc.isNullOrUndefined(attributes.latLonTrunc.latitude))
			{
				alatLongTrunc = [ attributes.latLonTrunc ];
			}

			var oRequest = new lightTableRequest(oSearchCriteria);
			// Check for lightCriteria for non full maps
			if(!oRequest.lightCriteria)
			{
				oRequest.lightCriteria = new LightCriteria();
			}
			oRequest.lightCriteria.lightIdList = aLightIds;

			if ($.sc.isNullOrUndefined(nCurrentTrunc))
			{
				nCurrentTrunc = 4;
			}

			oRequest.lightCriteria.geoCodeCriteria = new GeoCodeCriteria(nCurrentTrunc, alatLongTrunc);

			oRequest.pageSize = 10;
			oRequest.sortExpressions = [{ field : "POLE_ID", direction : "Ascending" }];

			$.sc.getJson("api/light/fetchall", oRequest, false, fnFillPopUp, null, false, true, false);

			return sHtml;

		};

		// Random colors for vectors.
		var _getRandomColor = function(id)
		{
			var letters = '0123456789ABCDEF'.split('');
			var color = '#';
			for (var i = 0; i < 6; i++)
			{
				color += letters[Math.round(Math.random() * 15)];
			}

			if(oColors[id])
			{
				color = oColors[id];
			}
			else
			{
				oColors[id] = color;
			}

			vector.color = color;
			return color;

		};

		var _buildMarkersLayer = function (response)
		{
			var iCurrentZoom = objMap.getZoom();

			if(iZoom != iCurrentZoom)
			{
				_closePopups();
			}

			iZoom = iCurrentZoom;

			if(response && response.length > 0)
			{

				if(objMap.getLayersByName(oLayerName.markers).length)
				{
					objMap.getLayersByName(oLayerName.markers)[0].removeAllFeatures();
				}
				else
				{
					objMap.addLayer(objMarkers);
				}

				if (iZoom > 8)
				{
					$('#draw-polygon').removeClass('ui-state-disabled');
					$('#drag-location').removeClass('ui-state-disabled');
					if(!$.sc.isNullOrUndefined(drawControls))
					{
						if(drawControls['drag'] && !drawControls['drag'].active)
						{
							_oPoleInfo.changeHoverControl(true);
						}
					}
				}
				else
				{
					$('#draw-polygon').addClass('ui-state-disabled').removeClass('ui-state-focus');
					$('#drag-location').addClass('ui-state-disabled').removeClass('ui-state-focus');
					_oPoleInfo.changeHoverControl(false);

					if(!$.sc.isNullOrUndefined(drawControls))
					{
						if(drawControls['polygon'] && drawControls['polygon'].active)
						{
							drawControls['polygon'].deactivate();
						}
						if(drawControls['drag'] && drawControls['drag'].active)
						{
							drawControls['drag'].deactivate();
						}
					}
					$('#draw-pan').addClass('ui-state-focus');
				}

				var point = null;
				var aIds = null;
				var aFeatures = [];

				if(!$.sc.isNullOrUndefined(response[0]))
				{
					if(response[0].bottomLeftLat && response[0].latitudeAvg == null)
					{
						var point1 = new OpenLayers.Geometry.Point(response[0].bottomLeftLon, response[0].bottomLeftLat).transform(nFromProjection, nToProjection);
						var point2 = new OpenLayers.Geometry.Point(response[0].topRightLon, response[0].topRightLat).transform(nFromProjection, nToProjection);
						aFeatures.push(new OpenLayers.Feature.Vector(point1));
						aFeatures.push(new OpenLayers.Feature.Vector(point2));
						bBounds = false;
					}
					else
					{
						for (var i = 0; i < response.length; i++)
						{
							point = new OpenLayers.Geometry.Point(response[i].longitudeAvg, response[i].latitudeAvg).transform(nFromProjection, nToProjection);
							aFeatures.push(new OpenLayers.Feature.Vector(point,
									{
										totalLights	: response[i].smartpointsTotalByLatLong,
										latLonTrunc	: { latitude : response[i].latitudeTrunc, longitude : response[i].longitudeTrunc },
										lightIds	: response[i].lightIds,
										groupStatus	: response[i].currentLightStatus,
										groupBounds	: [response[i].bottomLeftLon, response[i].bottomLeftLat, response[i].topRightLon, response[i].topRightLat]
									}
							));
						}
					}

					objMarkers.addFeatures(aFeatures);

					if(objMap.getLayersByName(oLayerName.poleInfo).length > 0)
					{
						if (iZoom == 10)
						{
							_getPoleInfoData();
						}
						else
						{
							_getPoleInfoData(null, true);
						}
					}

				}
				else
				{
					bZoom = false;
				}
			}

			if(bZoom)
			{
				_zoomToExtent();
			}
			else
			{
				$.sc.stopProgressBar();
			}

		};

		var _treatData = function(response)
		{
			$('#map-view-records').html(response.resultsSetInfo.endRow);
			$('#map-total-records, #total-records, #general-total').html(response.resultsSetInfo.totalRowsAvailable);
			sensus.widgets.datatable.allRowsCount = response.resultsSetInfo.totalRowsAvailable;

			if(response && response.geocodeLightList.length)
			{
				if(!$('.olMap').hasClass('detail-map'))
				{
					sensus.widgets.datatable.objLatLon.splice(0, sensus.widgets.datatable.objLatLon.length);
					_buildMarkersLayer(response.geocodeLightList);
				}
			}
			else
			{
				if(objMap.getLayersByName(oLayerName.markers).length)
				{
					objMap.getLayersByName(oLayerName.markers)[0].removeAllFeatures();
				}
			}
		};

		var _getData = function(nTrunc, bDoZoom, bounds)
		{
			if(bBounds)
			{
				nTrunc = 3;
				bounds = bBounds;
			}

			var iCurrentZoom = objMap.getZoom();
			if(iCurrentZoom != iZoom)
			{
				_closePopups();
			}

			if(objMap.popups.length == 0)
			{
				popupCenter = null;

				var extent = objMap.getExtent();

				if(!$.isEmptyObject(oRequest))
				{
					if(!nTrunc || isNaN(nTrunc))
					{
						var nTrunc = 0;

						if(iCurrentZoom > 1 && iCurrentZoom < 4)
						{
							nTrunc = 1;
						}

						if(iCurrentZoom >= 4 && iCurrentZoom < 7)
						{
							nTrunc = 2;
						}

						if(iCurrentZoom >= 7 && iCurrentZoom < 9)
						{
							nTrunc = 3;
						}

						if (iCurrentZoom >= 9)
						{
							nTrunc = 4;
						}

						nCurrentTrunc = nTrunc;
					}

					if(extent && !bMapFromFilter && !bounds)
					{
						extent.transform(new OpenLayers.Projection(nToProjection), new OpenLayers.Projection(nFromProjection));
						oGeoCodeCriteria.bottomLeftLat = extent.bottom;
						oGeoCodeCriteria.bottomLeftLong = extent.left;
						oGeoCodeCriteria.topRightLat = extent.top;
						oGeoCodeCriteria.topRightLong = extent.right;
					}
					else
					{
						oGeoCodeCriteria.bottomLeftLat = null;
						oGeoCodeCriteria.bottomLeftLong = null;
						oGeoCodeCriteria.topRightLat = null;
						oGeoCodeCriteria.topRightLong = null;
						bMapFromFilter = false;
					}
					oGeoCodeCriteria.trunc = nTrunc;

					oRequest.lightCriteria.geoCodeCriteria = oGeoCodeCriteria;

					if(bounds)
					{
						$.sc.getJson(sUrl+"bounds", oRequest, false, _treatData, null, false, true, false);
					}
					else
					{
						$.sc.getJson(sUrl, oRequest, false, _treatData, null, false, true, false);
					}

				}
			}
		};

		/**
		 * Pole info functionality
		 */

		var _oPoleInfo = {

				changeHoverControl: function(bActivate)
				{
					if(objMap.div.id == "map")
					{
						if(!bActivate)
						{
							objHoverControl.deactivate();
							return;
						}
						objHoverControl.activate();
						objSelectorEvents.deactivate();
						objSelectorEvents.activate();
					}
				},

				/**
				 * Generates a popup for pole info based on the lights passed
				 * @param Array lights
				 * @returns Object popup
				 */
				generatePopUp: function(aLights)
				{
					var iLength = aLights.length;
					if(!iLength)
					{
						return false;
					}
					var oPopUp = {
						sHtml : '<div class="rounded box-shadow messageMap poleInfo">' +
						'	<div>' +
						'		<table>' +
						'			<tbody>',
						height: iLength * 45,
						width: 165
					};
					for(var i=0; i < aLights.length && i < 5; i++)
					{
						oPopUp.sHtml +=
						'				<tr>' +
						'					<td class="title">'+ $.sc.locale('search.label.poleid') +':</td>' +
						'					<td><span>'+ aLights[i].poleId +'</span></td>' +
						'				</tr>' +
						'				<tr>' +
						'					<td class="title">'+ $.sc.locale('search.label.flexnetid') +':</td>' +
						'					<td><span>'+ aLights[i].radio.flexNetId +'</span></td>' +
						'				</tr>';
						if((i + 1) != aLights.length)
						{
							oPopUp.sHtml +=
							'				<tr>' +
							'					<td colspan="2" ><hr></td>' +
							'				</tr>';
						}
					}
					oPopUp.sHtml +=
					'			</tbody>' +
					'		</table>' +
					'	</div>' +
					'</div>';
					return oPopUp;
				}
		};

		/**
		 *	Get poleid info overlay data.
		 */
		var _getPoleInfoData = function(evt, bDeactivate)
		{

			if(objMap && objMap.getLayersByName(oLayerName.poleInfo)[0])
			{
				var mapLayer = objMap.getLayersByName(oLayerName.poleInfo)[0];
				_closePopups();

				var oLayerButtom = $('.dataLayersDiv input[value="'+ oLayerName.poleInfo +'"]');
				if(bDeactivate)
				{
					mapLayer.setVisibility(false);
					oLayerButtom.attr('disabled', true);
				}
				else
				{
					if(oLayerButtom.is(':disabled'))
					{
						oLayerButtom.attr('disabled', false);
					}
				}

				if(objMap.getZoom() > 8 && !mapLayer.visibility)
				{
					if(!$.sc.isNullOrUndefined(drawControls))
					{
						if(drawControls['drag'] && !drawControls['drag'].active)
						{
							_oPoleInfo.changeHoverControl(true);
						}
					}
				}

				if(mapLayer.visibility && !$.isEmptyObject(oRequest) && !bDeactivate)
				{
					_oPoleInfo.changeHoverControl(false);
					var currentMarkers = objMarkers.features;

					var aLatLongTruncs = [];
					for (m = 0; m < currentMarkers.length; m++)
					{
						if(currentMarkers[m].attributes.totalLights > 0)
						{
							aLatLongTruncs.push(currentMarkers[m].attributes.latLonTrunc);
						}
					}

					if(aLatLongTruncs.length > 0)
					{

						var fnBuildMarkers = function(response)
						{
							var aFeatures = [];

							if(!$.sc.isNullOrUndefined(response.lightList[0]))
							{
								var aLights = response.lightList;
								var iBoxZindex = 999;
								var nTrunc = oRequest.lightCriteria.geoCodeCriteria.trunc;
								var aCurrentPopup = [];
								for (var m = 0; m < currentMarkers.length; m++ )
								{
									for (var i = 0; i < aLights.length; i++)
									{
										if(currentMarkers[m].attributes.latLonTrunc.latitude == $.sc.nTruncate(aLights[i].radio.location.latitude, nTrunc)
												&& currentMarkers[m].attributes.latLonTrunc.longitude == $.sc.nTruncate(aLights[i].radio.location.longitude, nTrunc))
										{
											aCurrentPopup.push(aLights[i]);
										}
									}
									var oPopUp = _oPoleInfo.generatePopUp(aCurrentPopup);
									if(oPopUp)
									{
										var position = OpenLayers.LonLat.fromString(currentMarkers[m].geometry.toShortString());
										var popup = new OpenLayers.Popup("popup", position, new OpenLayers.Size(oPopUp.width, oPopUp.height), oPopUp.sHtml);
										popup.backgroundColor = null;
										popup.autosize = true;
										popup.panMapIfOutOfView = false;
										popup.events.register('mousedown', popup.div, function(evt)
										{
											iBoxZindex++;
											$(this).zIndex(iBoxZindex);
										});
										objMap.addPopup(popup);
									}
									aCurrentPopup = [];
								}
							}
						}

						var oSearchCriteria = sensus.pages.smartpoint.smartpointTable.data().config.oSettings.fnRequest();
						var oPoleInfoRequest = new lightTableRequest(oSearchCriteria);
						oPoleInfoRequest.lightCriteria.geoCodeCriteria = new GeoCodeCriteria(oRequest.lightCriteria.geoCodeCriteria.trunc, aLatLongTruncs);
						oPoleInfoRequest.pageSize = 0;

						$.sc.getJson("api/light/fetchall", oPoleInfoRequest, false, fnBuildMarkers, null, false, true, false);

					}
				}
			}
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
		var _buildMap = function (latLon, centerLat, centerLon, sContainer, iZoom, bCluster)
		{
			popupCenter = null;

			/** Set map options **/
			var options = {
				controls: [
						   new OpenLayers.Control.Navigation({mouseWheelOptions: {interval: 100}}),
						   new OpenLayers.Control.PanZoomBar(),
						   new OpenLayers.Control.MousePosition( { displayProjection : new OpenLayers.Projection(nFromProjection) } ),
						   new OpenLayers.Control.Attribution()
						   ]
			};

			/** Create map object with options and add layer **/
			if(!$.isEmptyObject(objMap))
			{
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

			oResolutionsOffset.attribution = sensus.settings.map.mqosm.attribution;
	        baseMQOSM = new OpenLayers.Layer.OSM(sensus.settings.map.mqosm.name, sensus.settings.map.mqosm.url, oResolutionsOffset);

	        oResolutionsOffset.attribution = sensus.settings.map.aerial.attribution;
	        baseAerial = new OpenLayers.Layer.OSM(sensus.settings.map.aerial.name, sensus.settings.map.aerial.url, oResolutionsOffset);

	        if(sContainer == 'detail-map-container')
	        {
				$('.olMapViewport').height('500px');
			}

			objMap.addLayers([baseMQOSM, baseAerial, objMarkers]);

			// Layer switcher internationalization.
			var oLayerSwitcher = new OpenLayers.Control.LayerSwitcher();
			objMap.addControl(oLayerSwitcher);
			oLayerSwitcher.baseLbl.innerHTML = $.sc.locale("map.layer_switcher.baseLayer");
			oLayerSwitcher.dataLbl.innerHTML = $.sc.locale("map.layer_switcher.overlays");

			if(sContainer == 'map')
			{
				// Add the poleInfo control layer
				objPoleInfoMarkers = new OpenLayers.Layer.Vector(oLayerName.poleInfo, { visibility: false });
				objMap.addLayer(objPoleInfoMarkers);

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

				var vlayer = new OpenLayers.Layer.Vector( oLayerName.polygon, {  styleMap: polygonStyleMap });

				vlayer.events.on({
					featuresadded: function (event) {

						vector.aIds = [];
						vector.index = vector.index + 1;
						var oVectorId = event.features[0].id.replace(/\./g,'\\.').split('.')[2].toLowerCase();
						var sFeatureId = "OpenLayers.Feature."+ oVectorId.charAt(0).toUpperCase() + oVectorId.slice(1);
						vector.area = event.features[0].geometry.getGeodesicArea(nToProjection)/ 1000000;

						var oLayer = objMap.getLayersByName(oLayerName.markers)[0];
						var aCurrentLatLonTruncs = [];

						if(oLayer)
						{
							for (var i=0;i<oLayer.features.length;i++)
							{
								 if (oLayer.features[i].geometry.intersects(event.features[0].geometry))
								 {
									if(oLayer.features[i].attributes.latLonTrunc.latitude)
									{
										aCurrentLatLonTruncs.push(oLayer.features[i].attributes.latLonTrunc);
									}
								}
							}
							vector.aLatLonTruncs = aCurrentLatLonTruncs;

							if (vector.aLatLonTruncs.length > 0)
							{
								var fnSelectLights = function(lightData)
								{
									var aLights = lightData.lightList;
									var aIds = [];
									for (var o = 0; o < aLights.length; o++)
									{
										sensus.widgets.datatable.addSelected(aLights[o].id.toString(), false);
										$('#smartpoint-table :checkbox[value='+aLights[o]+']').attr("checked", true);
										aIds.push(aLights[o].id.toString());
									}
									vector.aIds = aIds;

								}

								// TODO clear function for all zoom levels
								var oSearchCriteria = sensus.pages.smartpoint.smartpointTable.data().config.oSettings.fnRequest();
								var oIdsRequest = new lightTableRequest(oSearchCriteria);

								if ($.sc.isNullOrUndefined(nCurrentTrunc))
								{
									nCurrentTrunc = 4;
								}
								oIdsRequest.lightCriteria.geoCodeCriteria = new GeoCodeCriteria(nCurrentTrunc, vector.aLatLonTruncs);

								$.sc.getJson("api/light/fetchall", oIdsRequest, false, fnSelectLights, null, false, true, false);

							}

							$.sc.buildTag(oVectorId,'','drawing');

							drawControls['polygon'].deactivate();
							$('#draw-polygon').removeClass('ui-state-focus');
							$('#draw-pan').addClass('ui-state-focus');
						}
						sensus.widgets.datatable.setTotalResult(false);
						$("#actions .message").removeClass("ui-state-error");

					}
				});

				objMap.addLayer(vlayer);

				drawControls =
				{

					selecthover: new OpenLayers.Control.SelectFeature(
						objMarkers,
						{
							multiple: true,
							hover: false
						}
					),

					polygon: new OpenLayers.Control.DrawFeature(objMap.getLayersByName(oLayerName.polygon)[0], OpenLayers.Handler.Polygon)
				};

				for(var key in drawControls)
				{
					objMap.addControl(drawControls[key]);
				}

				drawControls.selecthover.activate();

				// Create and add drag ability
				_loadLocationEditor();

				$('#draw-polygon').click(function()
				{
					if(!$(this).hasClass('ui-state-disabled'))
					{
						if(sensus.settings.SHOW_DIALOG_POLYGON == 'true')
						{
							sensus.util.actiondialog.launchActionDialog("polygonHelp", sensus.util.mapopen.dialogSettings["polygonHelp"]);
						}
						drawControls['polygon'].activate();
						$(this).addClass('ui-state-focus');
						$(this).parent().siblings().find('.ui-corner-all').removeClass('ui-state-focus');
					}
					else
					{
						drawControls['polygon'].deactivate();
						$(this).removeClass('ui-state-focus');
					}
				});

				$('#drag-location').click(function()
				{
					if(!$(this).hasClass('ui-state-disabled'))
					{
						// TODO add an explain pop-up to the drag function with the option to never show again.
						//if(sensus.settings.SHOW_DIALOG_POLYGON == 'true'){

							//sensus.util.actiondialog.launchActionDialog("polygonHelp", sensus.util.mapopen.dialogSettings["polygonHelp"]);

						//}
						_oPoleInfo.changeHoverControl(false);
						drawControls['drag'].activate();
						$(this).addClass('ui-state-focus');
						$(this).parent().siblings().find('.ui-corner-all').removeClass('ui-state-focus');
						$('#draw-pan').removeClass('ui-state-focus');

					}
					else
					{
						drawControls['drag'].deactivate();
						$(this).removeClass('ui-state-focus');
						_oPoleInfo.changeHoverControl(true);
					}
				});

				$('#draw-pan').click(function()
				{
					$(this).addClass('ui-state-focus');
					$(this).parent().siblings().find('.ui-corner-all').removeClass('ui-state-focus');
					drawControls['drag'].deactivate();
					drawControls['polygon'].deactivate();
					_getPoleInfoData();
				});

				objMap.setCenter(new OpenLayers.LonLat(centerLon, centerLat).transform(nFromProjection, nToProjection ), iZoom);
				objMap.events.register("moveend", null, _getData);
				objMap.events.register("changelayer", null, _getPoleInfoData);
			}
			else
			{
				if(latLon.length && latLon[0].centerLat && latLon[0].centerLon)
				{
					objMap.setCenter(new OpenLayers.LonLat(latLon[0].centerLon, latLon[0].centerLat).transform(nFromProjection, nToProjection ), iZoom);
					objMap.events.register("moveend", null, _getData);
					_getData(null, true, true);
				}
				else
				{
					/** Set center points to map **/
					//objMap.setCenter(new OpenLayers.LonLat(centerLon, centerLat).transform(nFromProjection, nToProjection ), iZoom);
					objMap.setCenter(new OpenLayers.LonLat(latLon[0].centerLon, latLon[0].centerLat).transform(nFromProjection, nToProjection ), iZoom);
					_buildMarkersLayer(latLon);
					//_buildMarkersLayer(objLatLon);
				}

				drawControls =
				{
					selecthover: new OpenLayers.Control.SelectFeature(
						objMarkers,
						{
							multiple: false,
							hover: false
						}
					)
				};

				for(var key in drawControls)
				{
					objMap.addControl(drawControls[key]);
				}

				drawControls.selecthover.activate();

			}

			objMap.addControl(objHoverControl);
			objMap.addControl(objSelectorEvents);
			objSelectorEvents.handler.map = objSelectorEvents.handler.layer.map;
			objSelectorEvents.activate();
			$('#map-total-records').text($('#total-records').text());

		};

		var _zoomToExtent = function()
		{

			var markers = objMap.getLayersByName(oLayerName.markers);
			if(markers.length)
			{
				/** Get zoom dynamic to show all markers **/
				bZoom = false;
				var bounds = markers[0].getDataExtent();
				objMap.zoomToExtent(bounds);
				objMap.getLayersByName(oLayerName.markers)[0].setVisibility(true);
			}

		};

		var _closePopups = function()
		{
			while(objMap.popups.length)
			{
				objMap.popups[0].destroy();
			}
		}

		var _zoomTo = function(n)
		{
			objMap.zoomTo(n);
		};

		var _getVectorArea = function()
		{
			return vector.area;
		};

		var _getVectorColor = function()
		{
			return vector.color;
		};

		var _getVectorIndex = function()
		{
			return vector.index;
		};

		var _getVectorAIds = function()
		{
			return vector.aIds;
		};

		var _destroyVectorFeatures = function()
		{
			if(_mapExists() && objMap.getLayersByName(oLayerName.polygon).length > 0)
			{
				objMap.getLayersByName(oLayerName.polygon)[0].destroyFeatures();
				$('#polygon-filters').empty();
			}
		}

		var _getLayersByName = function(name)
		{
			return objMap.getLayersByName(name)[0];
		}

		var _mapFromFilter = function(bFromList)
		{
			$.sc.startProgressBar();

			if(!bFromList)
			{
				// Remove previous created vectors
				$.sc.destroyVectorFeatures();

				sensus.widgets.datatable.isAllRows = false;
				sensus.widgets.datatable.selectedRows = [];
				sensus.widgets.datatable.setTotalResult(false);
			}

			var oSearchCriteria = sensus.pages.smartpoint.smartpointTable.data().config.oSettings.fnRequest();
			var oNewRequest = new lightTableRequest(oSearchCriteria);

			oRequest = oNewRequest;
			objMap.zoomTo(1);
			bZoom = true;
			bMapFromFilter = true;
			_getData(1, true, true);
			$.sc.stopProgressBar();

		};

		var _mapExists = function()
		{
			return !$.isEmptyObject(objMap);
		}

		var _getCurrentPopUpLights = function()
		{
			return currentPopUpLights;
		}

		// Add the ability to drag features
		var _loadLocationEditor = function(bActivate, bDetailPage)
		{
			// Check the action for creation or activation mode
			if(drawControls && !drawControls['drag'])
			{
				var onStartDrag = null;
				var onDragFeature = null;
				var onDragComplete = null;

				if(bDetailPage)
				{
					onStartDrag = function(feature)
					{
						feature.attributes.oLonLatStart = OpenLayers.LonLat.fromString(feature.geometry.toShortString());
					}

					onDragFeature = function(feature, pixel)
					{
						var bounds = feature.geometry.getBounds()
						var pos = objMap.getLayerPxFromViewPortPx(objMap.getViewPortPxFromLonLat(bounds.getCenterLonLat()));

						bounds = bounds.transform(nToProjection, nFromProjection);
						$('#latitude').val($.sc.nTruncate(bounds.top, 4));
						sensus.commons.modules.content.lightLocation.currentLocation.latitude = bounds.top;
						$('#longitude').val($.sc.nTruncate(bounds.left, 4));
						sensus.commons.modules.content.lightLocation.currentLocation.longitude = bounds.left;
					}

					onDragComplete = function(feature)
					{

					}
				}
				else
				{
					onStartDrag = function(feature)
					{
						if(!feature.attributes.oLonLatStart)
						{
							feature.attributes.oLonLatStart = OpenLayers.LonLat.fromString(feature.geometry.toShortString());
						}
						_closePopups();
						if(feature.attributes.totalLights && feature.attributes.totalLights == 1)
						{
							var sHtmlLocation = '<div class="rounded box-shadow messageMap location">' +
												'<div id="messageArrow" class="messageArrow"></div>' +
												'	<div style="background-color: #F0F0F0; padding: 3px 0 3px 6px; height: 17px;">' +
												'		<span>Drag to the desired position</span>' +
												'		<span class="ui-icon icon-close ui-icon-closethick" title="Close">Close</span>' +
												'	</div>' +
												'	<div>' +
												'	<form id="update-location-form">' +
												'		<table>' +
												'			<tbody>' +
												'				<tr>' +
												'					<td class="title">Latitude:</td>' +
												'					<td><span id="current_latitude">'+$.sc.nTruncate(feature.attributes.groupBounds[1], 4)+'</span></td>' +
												'				</tr>' +
												'				<tr>' +
												'					<td class="title">Longitude:</td>' +
												'					<td><span id="current_longitude">'+$.sc.nTruncate(feature.attributes.groupBounds[0], 4)+'</span></td>' +
												'				</tr>' +
												'				<tr id="saveOption" class="hide">' +
												'					<td colspan="2" align="center">' +
												'						<button id="saveLocation">'+$.sc.locale("smartpointdetail.location.save")+'</button> '+
																		$.sc.locale("smartpointdetail.location.or") +
												'						<a id="cancelLocation" href="#cancelLocation">'+$.sc.locale("smartpointdetail.location.cancel")+'</a>' +
												'					</td>' +
												'				</tr>' +
												'			</tbody>' +
												'		</table>' +
												'	</form>' +
												'	</div>' +
												'</div>';

							var position = OpenLayers.LonLat.fromString(feature.geometry.toShortString());
							var popup = new OpenLayers.Popup.Anchored("popup", position, new OpenLayers.Size(285,81), sHtmlLocation);
							popup.backgroundColor = null;
							popup.autosize = true;
							popup.panMapIfOutOfView = true;
							feature.popup = popup;
							objMap.addPopup(popup);
							if(!$('#messageArrow').hasClass(feature.popup.relativePosition))
							{
								$('#messageArrow').removeClass().addClass(feature.popup.relativePosition);
							}

							// Close the pop-up
							$('.icon-close').click(function(){

								_closePopups();
								_getData();

							});

							if(feature.attributes.oLonLatStart.lon != position.lon || feature.attributes.oLonLatStart.lat != position.lat)
							{
								this.onComplete(feature);
							}

						}
						else
						{
							$('<div>' + $.sc.locale('smartpointdetail.location.drag.singleonly') + '</div>').dialog(
									{
										title: $.sc.locale('smartpointdetail.location.drag.singleonly.title'),
								        resizable: false,
								        modal: true,
								        buttons: {
								            "Ok": function()
								            {
								                $( this ).dialog( "close" );
								                _getData();
								            }
								        }
									}
							);
						}
					}

					onDragFeature = function(feature, pixel)
					{
						var bounds = feature.geometry.getBounds()
						var pos = objMap.getLayerPxFromViewPortPx(objMap.getViewPortPxFromLonLat(bounds.getCenterLonLat()));
						feature.popup.moveTo(pos);
						feature.attributes.lastPos = pos;
						if(!$('#messageArrow').hasClass(feature.popup.relativePosition))
						{
							$('#messageArrow').removeClass().addClass(feature.popup.relativePosition);
						}
						bounds = bounds.transform(nToProjection, nFromProjection);
						$('#current_latitude').text($.sc.nTruncate(bounds.top, 4));
						$('#current_longitude').text($.sc.nTruncate(bounds.left, 4));
					}

					onDragComplete = function(feature)
					{
						function fnCallBack(oResponse)
						{
							if (oResponse.lightList && oResponse.lightList[0] && oResponse.lightList[0].id)
							{
								$('#saveOption').removeClass('hide');
								$('#current_latitude').append(function()
								{
									var input = $('<input id="latitudeToSave" class="validate[required,custom[number],min[-90],max[90]]" type="text" value="'+$(this).text()+'"></input>');
									$(this).empty();
									return input;
								});
								$('#current_longitude').append(function()
								{
									var input = $('<input id="longitudeToSave" class="validate[required,custom[number],min[-90],max[90]]" type="text" value="'+$(this).text()+'"></input>');
									$(this).empty();
									return input;
								});
								//TODO verify why the original position is set
								feature.popup.updateSize();
								if(feature.attributes.lastPos)
								{
									feature.popup.moveTo(feature.attributes.lastPos);
								}

								$("#cancelLocation").click(function(e)
								{
									e.preventDefault();
									_closePopups();
									_getData();
								});
								$('#update-location-form').validationEngine('attach', {
									validationEventTrigger  : 'submit',
									onValidationComplete    : function(form, status) {

										if (status)
										{
											var bounds = feature.geometry.getBounds();
											var latitude   = $('#latitudeToSave').val();
											if($.sc.nTruncate(bounds.top, 4) == latitude)
											{
												var latitude = bounds.top;
											}

											var longitude  = $('#longitudeToSave').val();
											if($.sc.nTruncate(bounds.left, 4) == longitude)
											{
												var longitude = bounds.left;
											}

											var id = parseInt(oResponse.lightList[0].id);
											var oLocation = {
												latitude : latitude,
												longitude : longitude
											};

											var fnCallBack = function()
											{
												_closePopups();
												$('#draw-pan').click();
												_getData();
											}

											var oLightMaintenanceRequest = new lightMaintenanceRequest(new Light(null, id, null, oLocation));

											$.sc.monitor("api/lighttop/upsertproperty/location", oLightMaintenanceRequest, false, fnCallBack, $.sc.locale("action.longRunningProcessDialog.confirm"), true);
										}

									}
								});

							}
						}


						// TODO clear function for all zoom levels
						var oSearchCriteria = sensus.pages.smartpoint.smartpointTable.data().config.oSettings.fnRequest();
						var oIdsRequest = new lightTableRequest(oSearchCriteria);

						if ($.sc.isNullOrUndefined(nCurrentTrunc))
						{
							nCurrentTrunc = 4;
						}
						oIdsRequest.lightCriteria.geoCodeCriteria = new GeoCodeCriteria(nCurrentTrunc, [feature.attributes.latLonTrunc]);

						$.sc.getJson("api/light/fetchall", oIdsRequest, false, fnCallBack, null, false, true, false);
					}

				}

				var drag = new OpenLayers.Control.DragFeature
				(
					objMarkers,
					{
						autoActivate: false,
						onStart: onStartDrag,
						onDrag: onDragFeature,
						onComplete: onDragComplete
					}
				);
				drawControls.drag = drag;
				objMap.addControl(drawControls['drag']);
			}

			// Check for activation
			if(drawControls && drawControls['drag'] && objMap.getControlsByClass(drawControls['drag'].CLASS_NAME).length == 1)
			{
				if(bActivate && (!drawControls['drag'].active || drawControls['drag'].active == null))
				{
					//TODO New dialog to explain drag functionality with the option to never show again..
//					if(sensus.settings.SHOW_DIALOG_POLYGON == 'true'){
//
//						sensus.util.actiondialog.launchActionDialog("polygonHelp", sensus.util.mapopen.dialogSettings["polygonHelp"]);
//
//					}
					drawControls['drag'].activate();
				}
				else if(!bActivate && drawControls['drag'].active)
				{
					drawControls['drag'].deactivate();
				}
			}

		}

		return {
			getCurrentPopUpLights	: _getCurrentPopUpLights,
			mapExists 				: _mapExists,
			getLayersByName 		: _getLayersByName,
			getVectorAIds 			: _getVectorAIds,
			getVectorArea 			: _getVectorArea,
			getVectorColor 			: _getVectorColor,
			getVectorIndex 			: _getVectorIndex,
			destroyVectorFeatures	: _destroyVectorFeatures,
			maps 					: _maps,
			mapFromFilter 			: _mapFromFilter,
			loadMaps 				: _getData,
			loadLocationEditor		: _loadLocationEditor,
			zoomTo 					: _zoomTo,
			zoomToExtent 			: _zoomToExtent
		};

	}());