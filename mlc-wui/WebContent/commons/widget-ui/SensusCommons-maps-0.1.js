/**
 * @fileoverview Defines common map-related functionality.
 * @author Cristiane Cobo / Lucas Oliveira
 */
/**
 * The main namespace for map-related functionality.
 */
sensus.util.mapopen = {

	sFromProjection	: new OpenLayers.Projection("EPSG:4326"),   // transform from WGS 1984
	sToProjection : new OpenLayers.Projection("EPSG:900913"), // to Spherical Mercator Projection
	clusters : new OpenLayers.Layer.Vector("Clusters"),
	features : [],
	iZoom : -1,
	nId : null,
	nMax: 500,
	oColors : {},
	objIcon : {},
	objLatLon : {},
	objLayer : {},
	objMap : {},
	objPoint : {},
	oRequest : {},
	oResponse : {},
	vector : { color : '', area : 0, index : 0, aIds : [] },

	dialogSettings : {

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
			buttons : [{
					id : "apply-polygon-help",
					text : $.sc.locale("smartpoint.map.dialog.thanks"),
					click : function() {

						var aSettingsList = [];

						sensus.settings.pageSizeShowDialog = "1";

						//aSettingsList.push({ propertyEnum : "SHOW_DIALOG_POLYGON",   propertyValue : "false" });

						//sensus.util.ajaxaction.actionUrlAdress = sensus.settings.saveProfileSettings;
						//sensus.util.ajaxaction.data = { 'settingsRequest' : new settingsRequest(aSettingsList) };
						//sensus.util.ajaxaction.sendActionDefault($.sc.locale("action.savesettings.success"), $.sc.locale("action.longRunningProcessDialog.error"));

						//sensus.widgets.datatable.savePageSize();

						sensus.util.page.fnSavePropertyProfile( { "SHOW_DIALOG_POLYGON" : "false" } );
						$(this).dialog('close');

					}

				}, {

					id: "apply-polygon-cancel",
					text :$.sc.locale("action.addtoschedule.cancel"),
					click : function() {

						$(this).dialog('close');

					}

			}]

		}

	}

}


	$.fn.extend($.sc , function(){

		var sUrl = null;
		var oRequest = null;

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
		var _maps = function(sContainer, objLatLon, sUrl, request, sMapType, iZoom) {

			oRequest = request;
			var bCluster = true;
			if(objLatLon.length > 0) {

				var centerLat = objLatLon[0].latitude;
				var centerLon = objLatLon[0].longitude;
				bCluster = false;

			}

			$('#detail-map-total').hide();

			if (sMapType == "small") {

				sensus.util.mapopen.iZoom = -1;

				//if(iGroupId) {
					/** build group map **/
					//var aGroupList = [ { 'id': iGroupId } ];
					//sensus.util.mapopen.oRequest = {'groupRequest':new groupRequest(null, null, null, null, null, null, null, null, aGroupList)};
					//sensus.util.mapopen.sUrl = "group/ajax.fetchLightByGroupToMap.action";
					var objLayer = new OpenLayers.Layer.OSM("Map", "", { zoomOffset: 8, resolutions: [611.4962261718752,305.7481130859376,152.8740565429688,76.4370282714844,38.2185141357422,19.1092570678711,9.55462853393555,4.77731426696777,2.38865713348389,1.194328566741945]} );
					_buildMap(objLayer, objLatLon, centerLat, centerLon, sContainer, 10, bCluster);

/*
				} else if (iScheduleId) {

					sensus.util.mapopen.oRequest = {'scheduleRequest':new scheduleRequest(iScheduleId)};
					sensus.util.mapopen.sUrl = "schedule/ajax.fetchLightByScheduleToMap.action";
					var objLayer = new OpenLayers.Layer.OSM("Map", "", { zoomOffset: 8, resolutions: [611.4962261718752,305.7481130859376,152.8740565429688,76.4370282714844,38.2185141357422,19.1092570678711,9.55462853393555,4.77731426696777,2.38865713348389,1.194328566741945]} );
					sensus.util.mapopen.buildMap(objLayer, objLatLon, centerLat, centerLon, sContainer, 10, true);

				} else if (iUserId) {

					sensus.util.mapopen.oRequest = {'userRequest':new userRequest(iUserId)};
					sensus.util.mapopen.sUrl = "user/ajax.fetchLightByUserToMap.action";
					var objLayer = new OpenLayers.Layer.OSM("Map", "", { zoomOffset: 8, resolutions: [611.4962261718752,305.7481130859376,152.8740565429688,76.4370282714844,38.2185141357422,19.1092570678711,9.55462853393555,4.77731426696777,2.38865713348389,1.194328566741945]} );
					sensus.util.mapopen.buildMap(objLayer, objLatLon, centerLat, centerLon, sContainer, 10, true);


				} else if (aGroups) {

					sensus.util.mapopen.oRequest = {'groupRequest':new groupRequest(null, null, null, null, null, null, null, null, aGroups)};
					sensus.util.mapopen.sUrl = "group/ajax.fetchLightByGroupToMap.action";
					var objLayer = new OpenLayers.Layer.OSM("Map", "", { zoomOffset: 8, resolutions: [611.4962261718752,305.7481130859376,152.8740565429688,76.4370282714844,38.2185141357422,19.1092570678711,9.55462853393555,4.77731426696777,2.38865713348389,1.194328566741945]} );
					sensus.util.mapopen.buildMap(objLayer, objLatLon, centerLat, centerLon, sContainer, 10, true);


				}else {


					var objLayer = new OpenLayers.Layer.OSM("Map", "", { zoomOffset: 8, resolutions: [611.4962261718752,305.7481130859376,152.8740565429688,76.4370282714844,38.2185141357422,19.1092570678711,9.55462853393555,4.77731426696777,2.38865713348389,1.194328566741945]} );
					var iZoom = 18;
					sensus.util.mapopen.buildMap(objLayer, objLatLon, centerLat, centerLon, sContainer, iZoom, false);

				}
				*/

			} else {
				/** build list map **/
				centerLat = sensus.settings.searchMapCenterLatitude;
				centerLon = sensus.settings.searchMapCenterLongitude;
				var iZoom = 6;
				_buildMap(objLayer, objLatLon, centerLat, centerLon, sContainer, iZoom, true);

			}
		};

		var _buildPopUp = function (nId){

			var sHtml = '';

			function fnFillPopUp(data){

				sensus.util.mapopen.oResponse = data;

				if(data.firstLight){

					var sStatusMessage = sensus.widgets.datatable.fnGetEnumValue('LightStatusEnum', ($.grep(data.firstLight.parameters, function(e) { return e.propertyEnum == "CURRENT_LIGHT_STATUS"; }))[0].value).toLowerCase();
					var sPoleId = ($.grep(data.firstLight.parameters, function(e) { return e.propertyEnum == "POLE_ID"; }))[0].value;
					var sLampType = ($.grep(data.firstLight.parameters, function(e) { return e.propertyEnum == "LAMP_TYPE_WATTAGE_DIMMABLE"; }))[0].value;
					if(data.firstLight.lightStateEnum == "BLINK"){

						if(data.firstLight.lightBlinkEnumValue == 1){

							sMsg = 'BLINK FAST';

						} else if(data.firstLight.lightBlinkEnumValue == 2){

							sMsg = 'BLINK SLOW';

						}

					} else if(data.firstLight.lightStateEnum == "ON"){

						if(data.firstLight.lightIntensityEnumValue != 0 && data.firstLight.lightIntensityEnumValue != 6){

							sMsg = 'DIM '+data.firstLight.lightIntensityPercentageValue+'%';

						} else {

							sMsg = 'ON';

						}

					} else {

						sMsg = 'OFF';

					}

					var sLightState = sMsg;
					var aMessages = data.firstLight.currentAlarmWarningMessageList;
					var sFlexNetId = data.firstLight.rniId;
					var sLastMessageDate = data.firstLight.currentStatusMessage.date;
					var sLastMessage = Globalize.format(new Date(sLastMessageDate.replace('T',' ').replace(/-/g,'/').split('.')[0]),'D');
					sLastMessage += ', '+ $.date.dateFormat(sLastMessageDate, 'h:i A',null,true);

					//light-levels
					var aLightLevels = [];
					if(data.firstLight.lightIntensitylevels.length){

						for (var i = 0; i < data.firstLight.lightIntensitylevels.length; i++) {

							aLightLevels.push(data.firstLight.lightIntensitylevels[i].percentage);

						}

					} else {

						aLightLevels = [25,50,75,90];

					}

					sHtml += '<div class="rounded box-shadow messageMap"><div class="messageArrow"></div>';
					sHtml += '<div class="infowindow-title '+sStatusMessage+'">'+$.sc.locale('search.label.poleid')+': <strong>';

					if (sensus.settings.userContext.userRole != "ROLE_Role.Analytic User") {

						sHtml += '<a class="alist" href="lightDetail?id='+nId+'">'+sPoleId+'</a> </strong>';

					} else {

						sHtml += sPoleId + '</strong>';

					}

					sHtml += '<span class="status">' + $.sc.locale('smartpoint.status.'+ sStatusMessage.toUpperCase() ) + '<span title="CLOSE" class="ui-icon icon-close ui-icon-circle-close">close</span></span></div>';

					if(aMessages.length){

						if(aMessages[0].statusMessage != 'ACTIVE'){

							var sHtmlMessages = '';
							sHtmlMessages += '<div id="status-messages-popup" class="status-messages-'+sStatusMessage+'" style="display: block;"><dl>';
							var nSize = 1;
							var bShowMessageBox = false;
							var aMessagesSize = aMessages.length;
							if(aMessagesSize > 2){

								nSize = 2;

							}

							for (var i = 0; i < nSize; i++) {

								if(aMessages[i].statusMessageSubtype){

									bShowMessageBox = true;
									sHtmlMessages += '<li><span class="message"><strong>'+$.sc.locale('sensus.mlc.status_subtype.'+aMessages[i].statusMessageSubtype.replace(/_/g,'').toLowerCase())+': </strong>'+$.date.dateFormat(aMessages[i].messageDate, 'simpleDate')+'  '+$.date.dateFormat(aMessages[i].messageDate, 'h:i:s.fff A',null,true)+'</span></li>';

								}

							}

							sHtmlMessages += '</dl></div>';

							if(bShowMessageBox){

								sHtml += sHtmlMessages;

							}

						}

					}

					if (sensus.settings.userContext.userRole != "ROLE_Role.Analytic User" && data.firstLight.currentStatusMessage.lightStatusEnum != "DEACTIVATED") {

						sensus.util.mapopen.nId = nId;
						sHtml += '<div class="lighting-controls"><span class="hide" id="light-levels">'+[aLightLevels]+'</span><label class="title">Lighting Control</label><button class="fg-button ui-widget ui-state-default ui-corner-all map-control-lights">Control Lights</button></div>';

					}

					sHtml += '<table><tbody><tr><td class="title">'+$.sc.locale('smartpointdetail.status.lightonoff')+':</td><td>'+sLightState+'</td></tr><tr><td class="title">'+$.sc.locale('smartpoint.filter.lamptype')+':</td><td>'+sLampType+'</td></tr><tr><td class="title">'+$.sc.locale('search.label.flexnetid')+':</td>';
					sHtml += '<td>'+sFlexNetId+'</td></tr><tr><td class="title">'+$.sc.locale('smartpointdetail.status.lastMessage')+':</td><td>'+sLastMessage+'</td></tr></tbody></table><div class="infowindow-button-row"><ul>';

					var sUrl = $.address.value().split('?')[0];
					if(sUrl == "/smartpoint/ajax.list.action"){

						var sChecked = '';
						nId = nId.toString();
						if($.inArray(nId,sensus.widgets.datatable.selectedRows) != -1){

							sChecked = ' checked ';

						}

						sHtml += '<li class="checkbox"><input type="checkbox" '+sChecked+' class="map-checkbox" id="map-smartpoint-id-'+nId+'" value="'+nId+'"><label for="addSingleton">'+$.sc.locale('smartpoint.actions.includeInAction')+'</label></li></ul></div></div>';


					}

				}

			}

			var oRequest = {'lightRequest':new lightRequest(nId, null, null, null)};
			$.ajaxValidator.fnDoCall("/slc/smartpoint/getSmartpointInfo.action", oRequest, false, fnFillPopUp, null, null, true);
			//sensus.util.page.stopProgressBar(null, true);
			return sHtml;

		};

		var _getRandomColor = function(id){

			var letters = '0123456789ABCDEF'.split('');
			var color = '#';
			for (var i = 0; i < 6; i++) {

				color += letters[Math.round(Math.random() * 15)];

			}

			if(sensus.util.mapopen.oColors[id]){

				color = sensus.util.mapopen.oColors[id];

			} else {

				sensus.util.mapopen.oColors[id] = color;

			}

			sensus.util.mapopen.vector.color = color;
			return color;

		};

		var _buildMarkersLayer = function (){

			var clusters = sensus.util.mapopen.objMap.getLayersByName('Clusters');
			if(clusters){

				_clearLayers();

			}

			iZoom = sensus.util.mapopen.objMap.getZoom();

			var nLat = 60;
			var nLon = 10;
			var nIcon = 40;
			if(iZoom < 18){
				nLat = 44;
				nIcon = 13;
				var nDiff = 17 - iZoom;
				for(var i=0; i<=nDiff; i++){

					nLat = nLat * 2;

				}

			}

			sensus.util.mapopen.iZoom = iZoom;

			$('#draw-polygon').removeClass('ui-state-disabled');

				if(sensus.util.mapopen.objLatLon.length > 0) {
					/** Create markers and add on layer **/
					var markers = sensus.util.mapopen.objMap.getLayersByName('Markers');
					var markersShadown = sensus.util.mapopen.objMap.getLayersByName('Markers Shadow');

					if(markers.length){

						markers[0].destroy();
						markersShadown[0].destroy();
						while( sensus.util.mapopen.objMap.popups.length ) {

							sensus.util.mapopen.objMap.removePopup(sensus.util.mapopen.objMap.popups[0]);

						}
						markers = new OpenLayers.Layer.Markers("Markers");
						markersShadown = new OpenLayers.Layer.Vector("Markers Shadow", {style: {fill: false}});
						sensus.util.mapopen.objMap.addLayer(markersShadown, {style: {fill: false}});
						sensus.util.mapopen.objMap.addLayer(markers);


					} else {

						markers = new OpenLayers.Layer.Markers("Markers");
						markersShadown = new OpenLayers.Layer.Vector("Markers Shadow", {style: {fill: false}});
						sensus.util.mapopen.objMap.addLayer(markersShadown);
						sensus.util.mapopen.objMap.addLayer(markers);

					}

					/** Set size and offset to icon **/
					var nSize = new OpenLayers.Size(nIcon,nIcon);
					var offset = new OpenLayers.Pixel(-(nSize.w/2), -nSize.h);
					var icon;
					var pt = '';
					var iCount = 0;
					var bounds = sensus.util.mapopen.objMap.calculateBounds();
					for (var i = 0; i < sensus.util.mapopen.objLatLon.length; i++) {

						if(bounds.containsLonLat(new OpenLayers.LonLat(sensus.util.mapopen.objLatLon[i].longitude, sensus.util.mapopen.objLatLon[i].latitude).transform(sensus.util.mapopen.sFromProjection, sensus.util.mapopen.sToProjection))){

							iCount = iCount+1;

						}


						pt = new OpenLayers.LonLat(sensus.util.mapopen.objLatLon[i].longitude, sensus.util.mapopen.objLatLon[i].latitude).transform(sensus.util.mapopen.sFromProjection, sensus.util.mapopen.sToProjection);
						var feature = new OpenLayers.Feature(markers, pt);
						var sStatus = sensus.util.mapopen.objLatLon[i].status;
						if (iZoom > 17) {
							_buildIconLight(iZoom, sStatus, nSize, offset);
						} else {

							_buildIconDot(iZoom, sStatus, nSize, offset);

						}

						icon = sensus.util.mapopen.objIcon;

						var marker = new OpenLayers.Marker(pt, icon.clone());
						marker.id = sensus.util.mapopen.objLatLon[i].id;

						if(sensus.util.mapopen.objLatLon[i].id){

							marker.events.register("mousedown", marker, function(evt) {

								var nRniId = this.id;
								var position = new OpenLayers.LonLat((this.lonlat.lon)+nLon, (this.lonlat.lat)+nLat);
								if (this.popup == null) {

									this.popup = new OpenLayers.Popup("popup-"+nRniId, position, null, _buildPopUp(nRniId),false);
									this.popup.backgroundColor = null;
									this.popup.contentSize.h = "330";
									this.popup.contentSize.w = "350";
									this.popup.panMapIfOutOfView = true;
									sensus.util.mapopen.objMap.addPopup(this.popup);
										//this.popup.show();
									var that = this;

									$('.olMap .alist').click(function(e){
										e.preventDefault();
										$('#messaging-main').hide();
										oParam = sensus.commons.lib.ajax.param;
										sensus.commons.lib.ajax.do_load($.extend({}, oParam, {
											$element : $(this)
										}));
									});

									$('.icon-close').click(function(){

										that.popup.destroy();
										delete that.popup;

									});

									$(".map-control-lights").wLightControl(false);

									$(".map-checkbox").click(function() {

										var bChecked = $(this).attr('checked')?true:false;
										if(bChecked){

											sensus.widgets.datatable.addSelected($(this).attr("value"), false);
											$('#smartpoint-table :checkbox[value='+$(this).attr("value")+']').attr("checked", true);

										} else {

											sensus.widgets.datatable.addDeselected($(this).attr("value"), false);
											$('#smartpoint-table :checkbox[value='+$(this).attr("value")+']').attr("checked", false);

										}

										sensus.widgets.datatable.setTotalResult(false);
										$("#actions .message").removeClass("ui-state-error");

									});

								} else {

									this.popup.destroy();
									delete this.popup;

								}

								OpenLayers.Event.stop(evt);

							});

						}

						var gPoint = new OpenLayers.Geometry.Point(pt.lon, pt.lat);
						gPoint.data = {'rniId':sensus.util.mapopen.objLatLon[i].id};
						markersShadown.addFeatures(new OpenLayers.Feature.Vector(gPoint));
						markers.addMarker(marker);

					//} else {

							/** Add dot icon to map **/
							//var sStatus = sensus.util.mapopen.objLatLon[i].status;
							//sensus.util.mapopen.buildIconDot(iZoom, sStatus, nSize, offset);
							//icon = sensus.util.mapopen.objIcon;
							//markers.addMarker(new OpenLayers.Marker(new OpenLayers.LonLat(sensus.util.mapopen.objLatLon[i].longitude, sensus.util.mapopen.objLatLon[i].latitude).transform(sensus.util.mapopen.sFromProjection, sensus.util.mapopen.sToProjection),icon.clone()));

						//}

				}

				sensus.util.mapopen.objMap.addLayer(markersShadown);

			} else {

				_clearLayers();

			}
	/*
			} else {

				var iCount = 0;
				var bounds = sensus.util.mapopen.objMap.calculateBounds();
				for (var i = 0; i < sensus.util.mapopen.objLatLon.length; i++) {

					if(bounds.containsLonLat(new OpenLayers.LonLat(sensus.util.mapopen.objLatLon[i].longitude, sensus.util.mapopen.objLatLon[i].latitude).transform(sensus.util.mapopen.sFromProjection, sensus.util.mapopen.sToProjection))){

						iCount = iCount+1;

					}

				}

			}
	*/
		$('#map-view-records').text(iCount);
		$('img[id^="OL_Icon_"]').css('cursor', 'pointer');

	};

		var _clearLayers = function(){

			/** Set clusters, markers and shadows for destroy in order to remove duplicated points at new requests **/
			var clusters = sensus.util.mapopen.objMap.getLayersByName('Clusters');
			var markers = sensus.util.mapopen.objMap.getLayersByName('Markers');
			var markersShadown = sensus.util.mapopen.objMap.getLayersByName('Markers Shadow');
			/** Clear markers and shadows **/
			if(markers.length){

				markers[0].destroy();
				markersShadown[0].destroy();
				/** Remove all popups **/
				while( sensus.util.mapopen.objMap.popups.length ) {

					sensus.util.mapopen.objMap.removePopup(sensus.util.mapopen.objMap.popups[0]);

				}

			}
			/** Clear clusters layer **/
			if(clusters.length){

				clusters[0].destroy();

			}

		};

		var _buildClustersLayer = function(oRequest){

			/** Clear Markers, Shadows and Clusters Layers **/
			_clearLayers();
			iZoom = sensus.util.mapopen.objMap.getZoom();
			var maxSmartpoints = 500;
			var sFColor = "#4169E1";
			var sSColor = "#000000";
			var	iPRadius = "${radius}";

			/** Set points style **/
			var style = new OpenLayers.Style({
				pointRadius: iPRadius,
				fillColor: sFColor,
				fillOpacity: 0.7,
				strokeColor: sSColor,
				strokeWidth: "${width}",
				label: "${label} ${count}",
				strokeOpacity: 0.7
				}, {
				context: {
					width: function(feature) {
						return (feature.cluster) ? 2 : 1;
					},
					radius: function(feature) {
						var pix = 2;
						if(feature.cluster) {
							if(feature.attributes.count == 1) {
								pix = 4;
								} else {
								pix = parseInt(feature.attributes.count,10)/9;
								if(pix < 10) {
								pix = 10;
								}
							}
						}
						return pix;
					},
					label: function(feature) {
						if(iZoom == 0) {
							if(sName) {

								return sName+"\n\n";

							} else {

								return '';

							}

						} else {
							return '';
						}
					},
					count: function(feature) {
						if(iZoom < 2) {
							return feature.attributes.count;
						} else {
							return '';
						}
					}
				}
			});

			if(iZoom <= 5) {

				switch(iZoom) {
					case 2:
						iDistance = 90;
						break;
					case 3:
						iDistance = 80;
						break;
					case 4:
						iDistance = 70;
						break;
					case 5:
						iDistance = 60;
						break;
					case 6:
						iDistance = 20;
						break;
					case 7:
						iDistance = 8;
						break;
					case 8:
						iDistance = 2;
						break;
					}
				}

			var strategy = new OpenLayers.Strategy.Cluster();
			strategy.distance = iDistance;
			sensus.util.mapopen.clusters = new OpenLayers.Layer.Vector("Clusters", {
				strategies: [strategy],
				styleMap: new OpenLayers.StyleMap({
					"default": style,
					"select": {
						fillColor: "#8aeeef",
						strokeColor: "#32a8a9"
					}
				})
			});

			/** add clusters to map **/
			sensus.util.mapopen.objMap.addLayer(sensus.util.mapopen.clusters);
			sensus.util.mapopen.clusters.addFeatures(sensus.util.mapopen.features);
			sensus.util.mapopen.features.length = 0;

			$('#draw-polygon').addClass('ui-state-disabled').removeClass('ui-state-focus');
			$('#draw-pan').addClass('ui-state-focus');
			if(drawControls){

				drawControls['polygon'].deactivate();

			}

		};

		var _treatData = function(oResponse){

			sensus.widgets.datatable.objLatLon.splice(0, sensus.widgets.datatable.objLatLon.length);
			iDistance = 100;
			iZoom = sensus.util.mapopen.objMap.getZoom();
			var iTotalPoints = oResponse.resultsSetInfo.endRow;
			if(iTotalPoints < sensus.util.mapopen.nMax){

				iTotalPoints = oResponse.lights.length;

				for (l in oResponse.lights) {

					if (oResponse.lights.hasOwnProperty(l)) {

						sensus.widgets.datatable.objLatLon.push({
							latitude:$.grep(oResponse.lights[l].parameters, function(e) { return e.labelKey == 'LATITUDE'; })[0].value,
							longitude:$.grep(oResponse.lights[l].parameters, function(e) { return e.labelKey == 'LONGITUDE'; })[0].value,
							status:oResponse.lights[l].currentStatusMessage.lightStatusEnumValue,
							id:oResponse.lights[l].id
						});

					}

				}

				_buildMarkersLayer();

			} else {

				var point = null;

				for (l in oResponse.lights) {

					if (oResponse.lights.hasOwnProperty(l)) {

						point = new OpenLayers.Geometry.Point($.grep(oResponse.lights[l].parameters, function(e) { return e.labelKey == 'LONGITUDE'; })[0].value, $.grep(oResponse.lights[l].parameters, function(e) { return e.labelKey == 'LATITUDE'; })[0].value);
						sensus.util.mapopen.features.push(new OpenLayers.Feature.Vector(point.transform(sensus.util.mapopen.sFromProjection, sensus.util.mapopen.sToProjection)));

					}

				}

				_buildClustersLayer();

			}
			//$total-records
			$('#map-total-records, #total-records, #general-total').html(oResponse.resultsSetInfo.totalRowsAvailable);
			$('#map-view-records').html(iTotalPoints);

		};

		var _getData = function(){

			var extent = sensus.util.mapopen.objMap.getExtent();

			if(extent && !$.isEmptyObject(oRequest)){

				extent.transform(new OpenLayers.Projection(sensus.util.mapopen.sToProjection), new OpenLayers.Projection(sensus.util.mapopen.sFromProjection));
				var sRequest = null;
				for (var key in oRequest) {

					sRequest = key;
				}

				oRequest[sRequest].bottomLeftLat = extent.bottom;
				oRequest[sRequest].bottomLeftLon = extent.left;
				oRequest[sRequest].topRightLat = extent.top;
				oRequest[sRequest].topRightLon = extent.right;
				oRequest[sRequest].maxSmartpointCount = sensus.util.mapopen.nMax;


			$.ajaxValidator.fnDoCall(sUrl, oRequest, false, _treatData, null, null, true);
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
		var _buildMap = function (objLayer, objLatLon, centerLat, centerLon, sContainer, iZoom, bCluster) {

			/** Set map options **/
			var options = {
				controls: [
						   new OpenLayers.Control.Navigation(),
						   new OpenLayers.Control.PanZoomBar(),
						   new OpenLayers.Control.ScaleLine(),
						   new OpenLayers.Control.MousePosition( { displayProjection : new OpenLayers.Projection(sensus.util.mapopen.sFromProjection) } )
						   ]
			};

			/** Create map object with options and add layer **/
			if(!$.isEmptyObject(sensus.util.mapopen.objMap)) {

				sensus.util.mapopen.iZoom = -1;
				sensus.util.mapopen.objIcon = {};
				sensus.util.mapopen.objLatLon = {};
				sensus.util.mapopen.objLayer = {};
				sensus.util.mapopen.objMap = {};
				sensus.util.mapopen.objPoint = {};

			}

			sensus.util.mapopen.objMap = new OpenLayers.Map(sContainer, options);
			sensus.util.mapopen.objLayer = new OpenLayers.Layer.OSM("Map");
			sensus.util.mapopen.objMap.addLayer(sensus.util.mapopen.objLayer);
			sensus.util.mapopen.objLatLon = objLatLon;

			var style = $.extend(true, {}, OpenLayers.Feature.Vector.style['default']); // get a copy of the default style
			style.fillColor = "${getFillColor}";

			var myStyleMap = new OpenLayers.StyleMap({
				"default": new OpenLayers.Style(style, {
					context: {
						getFillColor: function (feature) {

							var oNewVectorId = feature.id.replace(/\./g,'\\.').split('.')[2];

							return _getRandomColor(oNewVectorId);
						}
					}
				})
			});

			if(sContainer == 'map') {

				var vlayer = new OpenLayers.Layer.Vector( "Editable", {  styleMap: myStyleMap });

				vlayer.events.on({
					featuresadded: function (event) {

						sensus.util.mapopen.vector.index = sensus.util.mapopen.vector.index + 1;
						var oVectorId = event.features[0].id.replace(/\./g,'\\.').split('.')[2].toLowerCase();
						var sFeatureId = "OpenLayers.Feature."+ oVectorId.charAt(0).toUpperCase() + oVectorId.slice(1);
						sensus.util.mapopen.vector.area = event.features[0].geometry.getGeodesicArea(sensus.util.mapopen.sToProjection)/ 1000000;
						sensus.pages.objFilters.buildTag('','','drawing',oVectorId);

						var oLayer = sensus.util.mapopen.objMap.getLayersByName('Markers Shadow')[0];
						var sId = '';
						sensus.util.mapopen.vector.aIds.splice(0, sensus.util.mapopen.vector.aIds.length);

						if(oLayer){

							for (var i=0;i<oLayer.features.length;i++) {

								 if (oLayer.features[i].geometry.intersects(event.features[0].geometry)) {

									sId = oLayer.features[i].geometry.data.rniId.toString();
									sensus.widgets.datatable.addSelected(sId, false);
									$('#smartpoint-table :checkbox[value='+sId+']').attr("checked", true);
									sensus.util.mapopen.vector.aIds.push(sId);

								}

							}

						drawControls['polygon'].deactivate();
						$('#draw-polygon').removeClass('ui-state-focus');
						$('#draw-pan').addClass('ui-state-focus');

						}
						sensus.widgets.datatable.setTotalResult(false);
						$("#actions .message").removeClass("ui-state-error");

						//var geometry = event.features[0].geometry;
						//sensus.util.mapopen.objLayer.filter = new OpenLayers.Filter.Spatial({
						//	type: OpenLayers.Filter.Spatial.INTERSECTS,
						//	value: event.features[0].geometry
						//});
						//layer.refresh({force: true});
						//return false;


						//var gjson = new OpenLayers.Format.GeoJSON();
						//var event.features[0] = event.features[0];
						//var oJson = gjson.write(event.features[0]);

						var wkt = new OpenLayers.Format.WKT()
						var oJson = wkt.write(event.features[0]);

						//oJson = {'polygon':oJson};
						//sensus.util.session.createSession("polygons", null, oVectorId, oJson);
						/*
						geom = event.features[0].geometry + '';
						geom = geom.replace(/POLYGON/i, 'POLYGON((');
						geom = geom.replace(/\(\(/g, '');
						geom = geom.replace(/\)\)/g, '');
						geom += '))';
						geom = $.parseJSON(geom);
						sensus.util.session.createSession(null, null, oVectorId, geom);
						*/
					}
				});
				sensus.util.mapopen.objMap.addLayer(vlayer);

				drawControls = {
					polygon: new OpenLayers.Control.DrawFeature(vlayer,	OpenLayers.Handler.Polygon)
				};

				for(var key in drawControls) {
					sensus.util.mapopen.objMap.addControl(drawControls[key]);
				}

				$('#draw-polygon').click(function(){

					if(!$(this).hasClass('ui-state-disabled')){

						if(sensus.settings.polygonDialog == 'true'){

							sensus.util.actiondialog.launchActionDialog("polygonHelp", sensus.util.mapopen.dialogSettings["polygonHelp"]);

						}
						drawControls['polygon'].activate();
						$(this).addClass('ui-state-focus');
						$('#draw-pan').removeClass('ui-state-focus');

					} else {

						drawControls['polygon'].deactivate();
						$(this).removeClass('ui-state-focus');

					}

				});

				$('#draw-pan').click(function(){

					$(this).addClass('ui-state-focus');
					$('#draw-polygon').removeClass('ui-state-focus');
					drawControls['polygon'].deactivate();


				});

				sensus.util.mapopen.objMap.setCenter(new OpenLayers.LonLat(centerLon, centerLat).transform(sensus.util.mapopen.sFromProjection, sensus.util.mapopen.sToProjection ), iZoom);
				sensus.util.mapopen.objMap.events.register("moveend", null, _getData);
				//sensus.util.mapopen.objMap.moveTo();
				_getData();


			} else {

				if(bCluster){

					sensus.util.mapopen.objMap.setCenter(new OpenLayers.LonLat(centerLon, centerLat).transform(sensus.util.mapopen.sFromProjection, sensus.util.mapopen.sToProjection ), iZoom);
					sensus.util.mapopen.objMap.events.register("moveend", null, _getData);
					_getData();

				} else {

					/** Set center points to map **/
					sensus.util.mapopen.objMap.setCenter(new OpenLayers.LonLat(centerLon, centerLat).transform(sensus.util.mapopen.sFromProjection, sensus.util.mapopen.sToProjection ), iZoom);
					_buildMarkersLayer();
					var markers = sensus.util.mapopen.objMap.getLayersByName('Markers');
					if(markers.length){
						/** Get zoom dynamic to show all markers **/
						sensus.util.mapopen.objMap.zoomToExtent(markers[0].getDataExtent());

					}

				}

			}

			$('#map-total-records').text($('#total-records').text());

		};


		/**
		 * @param iZoom
		 * 			integer, zoom
		 * @param latitude
		 * 			string, latitude
		 * @param size
		 * 			integer, size
		 * @param offset
		 * 			integer, offset
		 */
		var _buildIconLight = function(iZoom, status, size, offset) {

			if (status != null) {

				/** Setting the correct icon according with light status **/
				if(status == 'active' || status == 3) {
					sensus.util.mapopen.objIcon = new OpenLayers.Icon('images/map/map_icn_light_active.png',size,offset);
				} else if (status == 'error' || status == 'alarm' || status == 'Lamp_failure' || status == 'Power_failure' || status == 1) {
					sensus.util.mapopen.objIcon = new OpenLayers.Icon('images/map/map_icn_light_alert.png',size,offset);
				} else if (status == 'warning' || status == 'Communication_fail' || status == 'Power_surge_detected' || status == 'Brown_out_detected' || status == 2) {
					sensus.util.mapopen.objIcon = new OpenLayers.Icon('images/map/map_icn_light_warning.png',size,offset);
				} else {
					sensus.util.mapopen.objIcon = new OpenLayers.Icon('images/map/map_icn_light_inventory.png',size,offset);
				}
			} else {
				sensus.util.mapopen.objIcon = new OpenLayers.Icon('images/map/map_icn_light_inventory.png',size,offset);
			}
		};

		/**
		 * @param iZoom
		 * 			integer, zoom
		 * @param latitude
		 * 			string, latitude
		 * @param size
		 * 			integer, size
		 * @param offset
		 * 			integer, offset
		 */
		var _buildIconDot = function(iZoom, status, size, offset) {
		offset = 0;

			/** Setting the correct icon according with light status **/
			if (status == 'active' || status == 3) {
				sensus.util.mapopen.objIcon = new OpenLayers.Icon('images/map/map_icn_dot_active.png',size,offset);
			} else if (status == 'error' || status == 'alarm' || status == 'Lamp_failure' || status == 'Power_failure' || status == 1) {
				sensus.util.mapopen.objIcon = new OpenLayers.Icon('images/map/map_icn_dot_alert.png',size,offset);
			} else if (status == 'warning' || status == 'Communication_Fail' || status == 'Power_surge_detected' || status == 'Brown_out_detected' || status == 2) {
				sensus.util.mapopen.objIcon = new OpenLayers.Icon('images/map/map_icn_dot_warning.png',size,offset);
			} else {
				sensus.util.mapopen.objIcon = new OpenLayers.Icon('images/map/map_icn_dot_inventory.png',size,offset);
			}
		};


		return {

		maps : _maps


		};

	}());

