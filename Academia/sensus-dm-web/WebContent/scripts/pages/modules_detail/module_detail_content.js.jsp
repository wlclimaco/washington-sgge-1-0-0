<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags'%>

<sec:authorize access="hasAnyRole('ROLE_EPM_SERVICE_ELECTRIC', 'ROLE_EPM_SERVICE_WATER', 'ROLE_EPM_SERVICE_GAS') and
	hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR', 'ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">

	<script type="text/javascript">
		sensus.pages.device.module.contents = {

			waterGasMeterDeviceInformationUtil : {

				doubleHyphen : sensus.locale.get('commons.pages.doubleHyphen'),

				fillWaterGasMeterInformation : function (oDevice) {

					var doubleHyphen = this.doubleHyphen;
					var	module	 	 = sensus.pages.device.module;
					var oRadio		 = oDevice.radio || {};
					var oConfig 	 = oDevice.configuration || {};
					var sEncrypted	 = oConfig.encrypted;
					var iPremiseId	 = oConfig.premiseId;
					var $device 	 = $("#device-information");
					var sReadQuality = oDevice.quarantine ? sensus.locale.get('filter.quarantine.name') : doubleHyphen;
					var oTimeZone 	 = module.util.getTimeZoneMinutes(oDevice);

					// Device ID
					$device.find("dd:eq(0)").text(oDevice.deviceId || doubleHyphen);

					// Network Address
					$device.find("dd:eq(1)").text(oRadio.flexNetId || doubleHyphen);

					// Premise ID
					if (iPremiseId) {

						$device.find("dd:eq(2) a.request-premise-detail").text(iPremiseId).click(function(e) {

							e.preventDefault();

							sensus.util.actiondialog.launchActionDialog("premiseIdDialog",
									sensus.pages.deviceactions.premiseIdDialog(iPremiseId, oDevice));
						});

					} else {

						$device.find("dd:eq(2)").html(doubleHyphen);
					}

					// IP Address
					$device.find("dd:eq(3)").html(oRadio.ipAddress || "--.--.--.--.--");

					// Last Heard
					$device.find("dd:eq(4)").text(oDevice.lastHeard ? $.date.dateFormat(oDevice.lastHeard, sensus.settings.dateFormatMask + " h:i", oTimeZone) : doubleHyphen);

					// Status
					$device.find("dd:eq(5)").text(module.decorators.getStatus(oDevice.status) || doubleHyphen);

					// Installed Date
					$device.find("dd:eq(6)").text(oConfig.installDate ? $.date.dateFormat(oConfig.installDate, "simpleDate", oTimeZone) : doubleHyphen);

					// Encryption
					$device.find("dd:eq(7)").text(sEncrypted || doubleHyphen);

					// Ready Quality
					$device.find("dd:eq(8)").text(sReadQuality || doubleHyphen);

					// Firmware
					this.firmwareInformation($("#firmware-information"), oConfig);
				},

				firmwareInformation : function ($div, oConfig) {

					$("dd:eq(0)", $div).text(oConfig.firmwareFlexnet || this.doubleHyphen);
					$("dd:eq(1)", $div).text(oConfig.firmwareMeter || this.doubleHyphen);
				}
			},

			gasMeterDeviceInformation : {

				init : function (oResponse) {

					var oDevice = sensus.pages.device.module.request.get("device", oResponse);

					if (oDevice) {

						sensus.pages.device.module.contents
							.waterGasMeterDeviceInformationUtil.fillWaterGasMeterInformation(oDevice);
					}
				}
			},

			waterMeterDeviceInformation : {

				init : function (oResponse) {

					var oDevice = sensus.pages.device.module.request.get("device", oResponse);

					if (oDevice) {

						sensus.pages.device.module.contents
							.waterGasMeterDeviceInformationUtil.fillWaterGasMeterInformation(oDevice);
					}
				}
			},

			alarmsGasMeter : {

				init : function (oResponse) {

					var module = sensus.pages.device.module;
					var oDevice = module.request.get("device", oResponse);

					if (oDevice) {

						sensus.pages.device.module.contents.alarm.loadAlarm(oDevice.alarms,
								module.util.getTimeZoneMinutes(oDevice));
					}
				}
			},

			alarmsWaterMeter : {

				init : function (oResponse) {

					var module = sensus.pages.device.module;
					var oDevice = module.request.get("device", oResponse);

					if (oDevice) {

						sensus.pages.device.module.contents.alarm.loadAlarm(oDevice.alarms,
								module.util.getTimeZoneMinutes(oDevice));
					}
				}
			},

			alarmsflexnetLcm : {

				init : function (oResponse) {

					var module 	= sensus.pages.device.module;
					var oDevice = module.request.get("device", oResponse);

					if (oDevice) {

						sensus.pages.device.module.contents.alarm.loadAlarm(oDevice.alarms,
								module.util.getTimeZoneMinutes(oDevice));
					}
				}
			},

			alarm : {

				firstAlarmLabel : sensus.locale.get("smartpointdetail.dialog.alerts.first_alarm"),

				alarmDetectedLabel : sensus.locale.get('smartpointdetail.dialog.alerts.alarm_detected'),

				atLabel : sensus.locale.get('commons.pages.at'),

				alarmTimeMask : sensus.settings.dateFormatMask.replace("yyyy", "yy") + " - h:i:s A",

				doubleHyphen : sensus.locale.get('commons.pages.doubleHyphen'),

				getAlarmName : function(get, oAlarm) {

					return oAlarm.alarmEnum ? get("filter.alarm." + oAlarm.alarmEnum.toLowerCase()) : this.doubleHyphen;
				},

				getAlarmTime : function(oAlarm, sTimeMask, oTimeZone) {

					return oAlarm.alarmTime ? ($.date.dateFormat(oAlarm.alarmTime, sTimeMask, oTimeZone).replace("-", this.atLabel)) : this.doubleHyphen;
				},

				loadAlarmDetail : function($alarmDialog, alarmName, alarmTime, voltage, current) {

					$alarmDialog.find(".fail span").text(alarmName + " " + this.alarmDetectedLabel);

					$alarmDialog.find("tbody").html([
							"<tr><td>", alarmName, "</td>",
							"<td>", alarmTime, "</td>",
	//						"<td>", voltage, "</td>",
	//						"<td>", current, "</td>",
							"</tr>"].join(""));
				},

				alarmInformationDialog : function (oAlarm, sAlarm, oTimeZone) {

					var fnCallback = function (oResponse) {

						var oAlarm;
						var get = sensus.locale.get;
						var $alarmDialog = $('#dialog-processing-alert-details');
						var alarms = oResponse.alarms || [];
						var length = alarms.length;
						var $list = [];
						var i = 0;
						var sTimeMask = sensus.settings.dateFormatMask.replace("yyyy", "yy") + " '" + get('commons.pages.at') + "' h:i:s a";
						var doubleHyphen = sensus.locale.get('commons.pages.doubleHyphen');

						$alarmDialog.find(".fail span").text(sAlarm);

						for (; i < length; i = i + 1) {

							oAlarm = alarms[i];

							$list.push(["<tr><td>", (oAlarm.alarmEnum ? get("filter.alarm." + oAlarm.alarmEnum.toLowerCase()) : doubleHyphen), "</td>",
									"<td>", (oAlarm.alarmTime ? $.date.dateFormat(oAlarm.alarmTime, sTimeMask, oTimeZone) : doubleHyphen), "</td>",
	//								"<td>", (oAlarm.voltage || doubleHyphen), "</td>",
	// 								"<td>", (oAlarm.current || doubleHyphen), "</td>",
									"</tr>"].join(''));
						}

						$alarmDialog.find("tbody").html($list.join(""));

						sensus.util.page.stopProgressBar();
					};

					return {

						title : sensus.locale.get("smartpointdetail.dialog.alerts.alertDetails"),

						width : 555,

						action : function(actionDialog) {

							actionDialog.load("device/deviceAlarmInformation", function() {

								$.ajaxValidator.fnDoCall("api/device/fetchAlarmHistory", {
									flexNetId : $.address.parameter("id"),
									alarmEnum : oAlarm.alarmEnum,
									alarmTime : $.date.fnGetDate(oAlarm.alarmTime, {bUserTZ : false}).getTime()
								}, false, fnCallback, null, null, true);

								actionDialog.dialog('open');
							});
						}
					};
				},

				loadAlarm : function (aAlarms, oTimeZone) {

					var $deviceContent = $('#device-alarm-content');
					var aRequest;
					var oAlarms;
					var sAlarmName;
					var length = aAlarms ? aAlarms.length : 0;
					var get = sensus.locale.get;
					var sTimeMask = this.alarmTimeMask;
					var $list = [];
					var i = 0;

					if (length > 0) {

						$('#device-alarm').removeClass('hide');

						for (; i < length; i = i + 1) {

							oAlarm = aAlarms[i];
							sAlarmName = this.getAlarmName(get, oAlarm);

							$list.push(["<dt class='alarm first quiet-sample'>", sAlarmName,
								"</dt><dd class='first'>",
								(oAlarm.alarmTime ?
									["<a class='alert-detail-action text-button quiet-sample' data-index='", i, "' href='#'>",
									sAlarmName, " ",  this.alarmDetectedLabel, "</a>"].join("") : [sAlarmName, " ",  this.alarmDetectedLabel].join("")),
								"<span class='right time-stamp quiet-sample'>", this.firstAlarmLabel, ": <span>",
								this.getAlarmTime(oAlarm, sTimeMask, oTimeZone), "</span></span></dd>"].join(""));
						}

						$deviceContent.html($list.join(""));

						$(".alert-detail-action").click(function(e) {

							var $this = $(this);
							var index = $this.data("index");
							var oAlarm = aAlarms[index];

							e.preventDefault();

							sensus.util.page.startProgressBar();
							sensus.util.actiondialog.launchActionDialog("alarmInformationDialog",
									sensus.pages.device.module.contents.alarm.alarmInformationDialog(oAlarm, $this.parent().prev().text(), oTimeZone));

						});
					}
				}
			},

			deviceDetails : {

				doubleHyphen : sensus.locale.get('commons.pages.doubleHyphen'),

				fillDeviceDetails : function (oDevice) {

					var sCounts;
					var oConfig;
					var $div;
					var get;

					if (oDevice && oDevice.configuration) {

						oConfig = oDevice.configuration;
						sCounts = " " + sensus.locale.get("commons.pages.counts");
						get = sensus.locale.get;

						$div = $("#device-information .device-details");

						if (oConfig.brokenPipe) {

							$div.find("dd:eq(0)").text(get("smartpointdetail.tabs.about.meter_information.BrokenPipe."
									+ oConfig.brokenPipe));
						} else {

							$div.find("dd:eq(0)").text(this.doubleHyphen);
						}

						if (oConfig.meterSerialNumber) {

							$div.find("dd:eq(1)").text(oConfig.meterSerialNumber);
						} else {

							$div.find("dd:eq(1)").text(this.doubleHyphen);
						}


						if (oConfig.historyScale) {

							$div.find("dd:eq(2)").text(get("smartpointdetail.tabs.about.meter_information.HistoryScale."
									+ oConfig.historyScale));
						} else {

							$div.find("dd:eq(2)").text(this.doubleHyphen);
						}


						if (oConfig.leakConsecutiveReads) {

							$div.find("dd:eq(3)").text(get("smartpointdetail.tabs.about.meter_information.LeakConsecutiveReads."
									+ oConfig.leakConsecutiveReads));
						} else {

							$div.find("dd:eq(3)").text(this.doubleHyphen);
						}


						if (oConfig.leakDetectionThreshold) {

							$div.find("dd:eq(4)").text(get("smartpointdetail.tabs.about.meter_information.LeakDetectionThreshold."
									+ oConfig.leakDetectionThreshold));
						} else {

							$div.find("dd:eq(4)").text(this.doubleHyphen);
						}


						if (oConfig.meterUnits) {

							$div.find("dd:eq(5)").text(get("smartpointdetail.tabs.about.meter_information.MeterUnits."
									+ oConfig.meterUnits));
						} else {

							$div.find("dd:eq(5)").text(this.doubleHyphen);
						}


						if (oConfig.readingResolution) {

							$div.find("dd:eq(6)").text(get("smartpointdetail.tabs.about.meter_information.ReadingResolution."
									+ oConfig.readingResolution));
						} else {

							$div.find("dd:eq(6)").text(this.doubleHyphen);
						}


						if (oConfig.reverseFlowThreshold) {

							$div.find("dd:eq(7)").text(get("smartpointdetail.tabs.about.meter_information.ReverseFlowThreshold."
									+ oConfig.reverseFlowThreshold));
						} else {

							$div.find("dd:eq(7)").text(this.doubleHyphen);
						}

					}
				},

				init : function (oResponse) {

					sensus.pages.device.module.contents.deviceDetails.fillDeviceDetails(
							sensus.pages.device.module.request.get("device", oResponse));
				}
			},

			postNote : {

				dateFormatMask : " h:i A " + sensus.settings.dateFormatMask.replace("yyyy", "yy"),

				noteLabel : sensus.locale.get('smartpointdetail.tabs.about.note'),

				doubleHyphen : sensus.locale.get('commons.pages.doubleHyphen'),

				addPostNote : function(sPostNote, iFlexNetId, oTimeZone, $noteInput) {

					$.ajaxValidator.fnDoCall("api/note/insertNote", {notes : [{
						text 		: sPostNote,
						flexNetId	: iFlexNetId
					}]}, false, function(oResponse) {

						$noteInput.val("");

						// Reload notes
						if (oResponse && oResponse.notes) {

							sensus.pages.device.module.contents.postNote.load(oResponse.notes, oTimeZone);
						}
					});
				},

				removePostNote : function(iPostNoteId, iFlexNetId, oTimeZone) {

					$.ajaxValidator.fnDoCall("api/note/deleteNote", {notes : [{
						id			: iPostNoteId,
						flexNetId 	: iFlexNetId
					}]}, false, function(oResponse) {

						// Reload notes
						if (oResponse && oResponse.notes) {

							sensus.pages.device.module.contents.postNote.load(oResponse.notes, oTimeZone);
						}
					});
				},

				validatePostNote : function (sPostNote) {

					var sMessagingDetail = "messaging-smartpoint-detail";

					if (sPostNote && sPostNote.length > 0) {

						if (sPostNote.length > 1000) {

							// Show error in case of limit character
							sensus.util.page.showMessage(sMessagingDetail,	sensus.locale.get("commons.pages.max.characterJs", "1000"), "error");
							return false;
						}

						return true;
					}

					// Show error in case of field empty
					sensus.util.page.showMessage(sMessagingDetail, sensus.locale.get("smartpointdetail.tabs.about.noteEmpty"), "error");
					return false;
				},

				addPostNoteEvent : function ($postNoteSubmit, $noteInput, iFlexNetId, oTimeZone) {

					$postNoteSubmit.click(function(e) {

						var sPostNote = $noteInput.attr("value");
						var postNote = sensus.pages.device.module.contents.postNote;

						e.preventDefault();

						if (postNote.validatePostNote(sPostNote)) {

							postNote.addPostNote(sPostNote, iFlexNetId, oTimeZone, $noteInput);
						}
					});
				},

				removePostNoteEvent : function ($notesSelector, iFlexNetId, oTimeZone) {

					$notesSelector.delegate(".delete-dialog", "click", function(e) {

						var sPostNoteId = $(this).attr('id');

						e.preventDefault();

						sensus.pages.device.module.contents.postNote.removePostNote(sPostNoteId, iFlexNetId, oTimeZone);
					});
				},

				createPostNote : function (oNote, oTimeZone) {

					var $note = [];

					$note.push('<div><dt class="note"><span class="icon">N</span><span class="what start rounded">');
					$note.push(this.noteLabel);
					$note.push('</span><span class="who">');
					$note.push(oNote.createUser || this.doubleHyphen);
					$note.push('</span> &raquo; <span class="when">');
					$note.push($.date.dateFormat(oNote.createDate, this.dateFormatMask, oTimeZone));
					$note.push('</span><small> | <a href="#" id="');
					$note.push(oNote.id || this.doubleHyphen);
					$note.push('" class="text-button delete-dialog" title="Delete Note for Meter">');
					$note.push(sensus.locale.get('commons.pages.delete'));
					$note.push('</a></small></dt><dd class="description"><p>');
					$note.push(oNote.text || this.doubleHyphen);
					$note.push('</p></dd><div>');

					return $note.join('');
				},

				load : function(aNotes, oTimeZone) {

					var oNote;
					var i = 0;
					var length = aNotes.length;
					var $notes = [];
					var $note = $("dl", "#notes");

					for (; i < length; i = i + 1) {

						oNote = aNotes[i];

						$notes.push(this.createPostNote(oNote, oTimeZone));
					}

					$note.html($notes.join(''));

					if (length > 0) {

						$('#blankslate-note').hide();
						$note.show();

					} else {

						$('#blankslate-note').show();
						$note.hide();
					}
				},

				init : function(oResponse) {

					var oDevice = sensus.pages.device.module.request.get("device", oResponse);
					var iFlexNetId;
					var postNote;
					var oTimeZone;

					if (oDevice && oDevice.radio) {

						postNote	= sensus.pages.device.module.contents.postNote;
						iFlexNetId	= oDevice.radio.flexNetId;
						oTimeZone	= sensus.pages.device.module.util.getTimeZoneMinutes(oDevice);

						if (oResponse.deviceNotes && oResponse.deviceNotes.notes) {

							// Load
							postNote.load(oResponse.deviceNotes.notes, oTimeZone);
						}

						// Events
						postNote.addPostNoteEvent($("#note-submit"), $('#new-note'), iFlexNetId, oTimeZone);
						postNote.removePostNoteEvent($("#notes"), iFlexNetId, oTimeZone);
					}
				}
			},

			deviceInformation : {

				decorators : {

					doubleHyphen : sensus.locale.get('commons.pages.doubleHyphen'),

					meterType : function(data) {

						return [data.get("smartpointdetail.tabs.about.meterType"),
								data.oDevice.deviceModel ? data.oDevice.deviceModel.description : this.doubleHyphen];
					},

					networkAddress : function(data) {

						return [data.get("commons.pages.networkAddress"), data.oDevice.macAddress || data.oRadio.flexNetId];
					},

					deviceId  : function(data) {

						return [data.get("smartpointdetail.tabs.about.deviceId"), data.oDevice.deviceId];
					},

					premiseId : function(data) {

						return [data.get("commons.pages.premiseId"), data.oConfiguration.premiseId || this.doubleHyphen];
					},

					connectStatus : function(data) {

						var remoteConnectStatusEnum = data.oDevice.remoteConnectStatusEnum;

						return [data.get("commons.pages.connectStatus"),
									remoteConnectStatusEnum ? sensus.util.page.fnGetRemoteDisconnectSate(remoteConnectStatusEnum) : this.doubleHyphen];
					},

					quarantine : function(data) {

						return [data.get("commons.pages.quarantine"),
									data.oDevice.quarantine ? sensus.locale.get('filter.quarantine.name') : this.doubleHyphen];
					},

					ipAddress : function(data) {

						return [data.get("commons.pages.ipAddress"), this.doubleHyphen];
					},

					lifecycleState : function(data) {

						if((data.oDevice.lifecycleStateEnum != undefined) ||(data.oDevice.lifecycleStateEnum != null) ){
							return [data.get("smartpointdetail.tabs.about.state"),
									data.get("commons.pages." + data.oDevice.lifecycleStateEnum)];
						}else{
							return [data.get("smartpointdetail.tabs.about.state"), this.doubleHyphen];
						}


					},

					installedDate : function(data) {

						var installDate = data.oConfiguration.installDate;
						var sInstallDate;

						if (installDate) {

							sInstallDate = $.date.dateFormat(installDate,
									   sensus.settings.dateFormatMask.replace("yyyy", "yy"), data.oTimeZone);
						}

						return [data.get("smartpointdetail.tabs.about.installedDate"), sInstallDate || this.doubleHyphen];
					},

					encryption : function(data) {

						return [data.get("smartpointdetail.tabs.about.encryption"), data.oConfiguration.encrypted || this.doubleHyphen];
					},

					deviceLcmType : function(data) {

						return [data.get("smartpointdetail.tabs.about.deviceType"),
								data.get("commons.pages." + data.oDevice.lcmTypeEnum) || this.doubleHyphen];
					},

					manufacture : function(data) {

						return [data.get("smartpointdetail.dialog.deviceInformation.manufacture"), data.oDeviceModel.manufacture || this.doubleHyphen];
					},

					modelNumber : function(data) {

						return [data.get("smartpointdetail.dialog.deviceInformation.modelNumber"), data.oDeviceModel.description || this.doubleHyphen];
					}
				},

				information : {

					ELECTRIC_METER : ["meterType", "networkAddress", "premiseId", "connectStatus", "quarantine",
										"ipAddress", "lifecycleState", "installedDate", "encryption"],

					HAN_DEVICE : ["networkAddress", "premiseId"],

					LCM : ["deviceLcmType", "manufacture", "modelNumber", "encryption", "networkAddress", "premiseId"],

					FLEXNET_LCM : ["deviceLcmType", "manufacture", "modelNumber", "encryption", "deviceId", "networkAddress",
								   "premiseId", "lifecycleState", "installedDate"]

				},

				firmware : {

					decorator : function(data, sFirmwareProp) {

						return [data.get("firmware." + sFirmwareProp), data.oConfiguration[sFirmwareProp] || sensus.locale.get('commons.pages.doubleHyphen')];
					},

					ELECTRIC_METER : ["firmwareMeter", "firmwareFlexnet", "firmwareBootflasher", "firmwareZigbee"],

					HAN_DEVICE : ["esiFirmware", "hanFirmware"],

					LCM : [],

					FLEXNET_LCM: ["firmwareFlexnet", "firmwareBootflasher", "enTek"]

				},

				loadDeviceInformation : function (oDevice) {

					var $information 	= [];
					var $firmware		= [];
					var sDeviceType 	= oDevice.deviceType;
					var decorators 		= this.decorators;
					var fnDecFirmware	= this.firmware.decorator;
					var data 			= this.data;
					var i 				= 0;
					var data			= {
							oDevice			: oDevice,
							oConfiguration 	: oDevice.configuration || {},
							oRadio 			: oDevice.radio || {},
							oDeviceModel	: oDevice.deviceModel || {},
							oTimeZone 		: sensus.pages.device.module.util.getTimeZoneMinutes(oDevice),
							sDeviceType 	: sDeviceType,
							services 		: sensus.constants.services,
							get 			: sensus.locale.get,
							sServiceType 	: sensus.settings.serviceType
					};
					var fnCreateLineInformation = sensus.pages.device.module.util.createLineInformation;
					var aRowDecorators;
					var length;

					// LCM (Device Sub Type)
					if (sDeviceType == sensus.constants.services.electric.lcm.name) {

						sDeviceType = oDevice.lcmTypeEnum;
					}

					// information
					aRowDecorators = this.information[sDeviceType];
					length = aRowDecorators.length;

					for (; i < length; i = i + 1) {

						$information.push(fnCreateLineInformation(decorators[aRowDecorators[i]](data)));
					}
					$('#device-information tbody').html($information.join(''));

					// firmware
					aRowDecorators = this.firmware[sDeviceType];
					length = aRowDecorators.length;

					if (length > 0) {

						for (i = 0; i < length; i = i + 1) {

							$firmware.push(fnCreateLineInformation(fnDecFirmware(data, aRowDecorators[i])));
						}

						$('#firmware-information tbody').html($firmware.join(''));

					} else {

						$('#firmware-information').hide();
					}
				},

				init : function(oResponse) {

					var oDevice = sensus.pages.device.module.request.get("device", oResponse);

					if (oDevice) {

						sensus.pages.device.module.contents.deviceInformation.loadDeviceInformation(oDevice);
					}
				}
			},

			location : {

				doubleHyphen : sensus.locale.get('commons.pages.doubleHyphen'),

				setLocation : function(oLocation) {

					$('#address-val').text([oLocation.address,
											oLocation.city,
											oLocation.state,
											oLocation.zip].join(' '));
				},

				loadLocation : function(oDevice) {

					var $detail = $("#detail-map-container");
					var oRadio = oDevice.radio || {};
					var oLocation = oRadio.location || {};
					var latitude = oLocation.latitude || 0;
					var longitude = oLocation.longitude || 0;
					var timezone = oLocation.timeZoneInfo ? (oLocation.timeZoneInfo.displayName || this.doubleHyphen) : this.doubleHyphen;
					var $doc1 = $("#doc1");

					var objLatLon = [{
						latitudeAvg					: latitude,
						longitudeAvg				: longitude,
						alertEnum					: oDevice.alarms,
						devicesTotalByLatLong		: 1,
						radio						: oDevice.radio
					}];

					this.setLocation(oLocation);

					$detail.addClass("detail-map");
					$detail.find("div").remove();

					$('#lat-val').text(latitude);
					$('#long-val').text(longitude);
					$('#timeZone').text(timezone);

					$doc1.css("visibility", "hidden").css("display", "block");

					$.sc.map.build(null, 'detail-map-container', objLatLon, 1);

					$doc1.css("visibility", "visible");

					$(".olMapViewport").height("320px");

					// hack: fix map controls overridinf dialogs
					$(".olControlPanZoomBar").css("z-index", 999);
				},

				init : function(oResponse) {

					var oDevice = sensus.pages.device.module.request.get("device", oResponse);

					if (oDevice) {

						sensus.pages.device.module.contents.location.loadLocation(oDevice);
					}
				}
			},

			group : {

				allGroups : null,

				deleteGroupDialog : function($groupTr, sGroupId, sGroupName) {

					// Return action object
					return {

						title : sensus.locale.get("commons.pages.removeConfirmGroup"),

						width : 300,

						buttons : (function() {

							var buttons = {};

							// Remove Button Event (Confirm)
							buttons[sensus.locale.get("commons.pages.remove")] = function() {

								sensus.pages.device.module.contents.group.removeGroup(sGroupId, sGroupName, function(oResponse) {

									var _allGroups = sensus.pages.device.module.contents.group.allGroups;
									var group;

									if (_allGroups) {

										group = $.grep(_allGroups, function(group) {
											return group.id == sGroupId;
										});

										if (group.length) {

											group[0].devicesCount -= 1;
										}
									}

									if ($groupTr.siblings().length == 0) {

										$("#groups small-table").hide();
										$("#blankslate-group").show();
									}

									$groupTr.remove();

									$("#action-dialog").dialog("close");
								});
							};

							// Cancel
							buttons[sensus.locale.get("commons.pages.cancel")] = function() {

								$(this).dialog("close");
							};

							return buttons;

						})(),

						action : function(actionDialog) {

							$("#action-dialog").text(
									sensus.locale.get("smartpointdetail.tabs.about.deleteGroupPermanently",
									"", sGroupName));

							actionDialog.dialog("open");
						}
					};
				},

				getInquiryGroupRequest : function (sGroupId, sGroupName) {

					var _paramRequest = sensus.util.filter.paramRequest;
					var _electric = sensus.constants.services.electric;
					var sDeviceType = _paramRequest.getParameter("deviceType");
					var sDeviceSubType = _paramRequest.getParameter("typeEnum");

					var oGroup = {id: sGroupId, name : sGroupName, deviceType: sDeviceType};

					var oInquiryGroupRequest = {
							deviceType 		: sDeviceType,
							endRow 			: 0,
							startRow 		: 0,
							groups 			: [oGroup],
							sortExpressions : [{field : "name", direction : "Ascending"}],
							devices 		: [{radio : {flexNetId : $.address.parameter("id")}}]
					};

					var oDeviceSearch = {};
					var oSearch = {};

					if (sensus.settings.serviceType == _electric.name) {

						oSearch.deviceTypes = [sDeviceType];

						if (sDeviceType && sDeviceType == _electric.han.name) {

							oGroup.hanDeviceType = sDeviceSubType;

							oDeviceSearch.hanDeviceTypeEnumList = [sDeviceSubType];

							oSearch.hanDeviceSearch = oDeviceSearch;
						}

						oInquiryGroupRequest.deviceSearch = oSearch;
					}

					return oInquiryGroupRequest;
				},

				insertDeviceToGroup : function(sGroupId, sGroupName, fnCallback) {

					if (!sGroupId) {

						sGroupId = "";
					}

					$.ajaxValidator.fnDoCall("api/group/insertDevice",
							this.getInquiryGroupRequest(sGroupId, sGroupName), false, fnCallback);
				},

				removeGroup : function(sGroupId, sGroupName, fnCallback) {

					$.ajaxValidator.fnDoCall("api/group/deleteDevice",
							this.getInquiryGroupRequest(sGroupId, sGroupName), false, fnCallback);
				},

				fetchAllGroups : function(fnCallback) {

					var _electric = sensus.constants.services.electric;
					var _paramRequest = sensus.util.filter.paramRequest;

					var sDeviceType = _paramRequest.getParameter("deviceType");
					var sDeviceSubType = _paramRequest.getParameter("typeEnum");

					var oRequest = {endRow : 0, startRow : 0, sortExpressions : [{field : "name", direction : "Ascending"}]};

					var oDeviceSearch = {};
					var oSearch = {};

					if (sensus.settings.serviceType == _electric.name) {

						oSearch.deviceTypes = [sDeviceType];

						if (sDeviceType && sDeviceType == _electric.han.name) {

							oDeviceSearch.hanDeviceTypeEnumList = [sDeviceSubType];

							oSearch.hanDeviceSearch = oDeviceSearch;
						}

						oRequest.deviceSearch = oSearch;
					}

					$.ajaxValidator.fnDoCall("api/group/fetchAll", oRequest, false, fnCallback);
				},

				validateGroup : function(sGroupName) {

					var $input = $('#combo_group_input');

					// Required
					if (!sGroupName || sGroupName == sensus.locale.get('smartpointdetail.tabs.about.selectGroup')) {

						$input.validationEngine('showPrompt', sensus.locale.get('commons.pages.fieldRequired'), 'red', '', true);
						return false;
					}

					// Check if has group
					if (!$("#combo_group option").filter(function() {
							return sGroupName == $(this).text();
						}).length) {

						$input.validationEngine('showPrompt', sensus.locale.get('commons.pages.selectGroup'), 'red', '', true);
						return false;
					};

					// Check if group exists
					if ($("#groups .small-table .groupName").filter(function() {
							return sGroupName == $(this).text();
						}).length) {

						$input.validationEngine('showPrompt', sensus.locale.get('group.page.error.exist'), 'red', '', true);
						return false;
					};

					return true;
				},

				removeLabel : sensus.locale.get("commons.pages.remove"),

				createLine : function(oGroup) {

					return "<tr><td class='remove-group'><a class='removeGroup' href='#' data-id='" + oGroup.id + "'>" + this.removeLabel + "</a></td>" +
							"<td class='groupName'>" + oGroup.name + "</td>" +
							"<td class='value'>" + $.convertionNumber(oGroup.devicesCount, false, 0).dvalueConverter + "</td></tr>";
				},

				createOption : function(oGroup) {

					return "<option value='" + oGroup.id + "'>" + oGroup.name + "</option>";
				},

				loadSelect : function(aGroups) {

					var $options = [];
					var length = aGroups.length;
					var i = 0;

					this.allGroups = aGroups;

					for (; i < length; i = i + 1) {

						$options.push(this.createOption(aGroups[i]));
					}

					if (length > 0) {

						$('#combo_group').html($options.join(''));
					}
				},

				addGroup :function(oGroup) {

					if (oGroup.devicesCount) {

						oGroup.devicesCount += 1;

					} else {

						oGroup.devicesCount = 1;
					}

					$(" .small-table tbody", "#groups").append(this.createLine(oGroup)).parent().show();
				},

				loadListGroups : function(aGroups) {

					var $group = $("#groups");
					var $list = [];
					var $options = [];
					var length = aGroups.length;
					var i = 0;

					for (; i < length; i = i + 1) {

						$list.push(this.createLine(aGroups[i]));
					}

					$(' .small-table tbody', $group).html($list.join(''));

					if (length > 0) {

						$(' .small-table', $group).show();
						$('#blankslate-group').hide();

						<sec:authorize access="hasAnyRole('ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">
							$('.removeGroup').hide();
						</sec:authorize>

						<sec:authorize access="!hasAnyRole('ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">
							$('.removeGroup').show();
						</sec:authorize>

					} else {

						$(' .small-table', $group).hide();
						$('#blankslate-group').show();
					}
				},

				init : function(oResponse) {

					var $group = $("#groups");
					var $groupDiv = $('#div-groups');
					var $blankslate = $('#blankslate-group');
					var $input;
					var aGroups = [];
					var sInputLabel = sensus.locale.get('smartpointdetail.tabs.about.selectGroup');

					$groupDiv.hide();

					// Start Combobox
					$("#combo_group").combobox();

					$input = $("#combo_group_input");

					if (oResponse.deviceGroups) {

						aGroups = oResponse.deviceGroups.groups || [];
					}

					sensus.pages.device.module.contents.group.loadListGroups(aGroups);

					// Events
					// Show 'Add Group' input
					$('#add-groups').click(function(e) {

						e.preventDefault();

						if ($('#combo_group option').length) {

							$input.val(sInputLabel);
							$blankslate.hide();
							$groupDiv.show();

						} else {

							// Load All Groups on combobox
							sensus.util.page.startProgressBar();

							sensus.pages.device.module.contents.group.fetchAllGroups(function(oResponse) {

								sensus.pages.device.module.contents.group.loadSelect(oResponse.groups || []);

								$input.val(sInputLabel);
								$blankslate.hide();
								$groupDiv.show();

								sensus.util.page.stopProgressBar();
							});
						}
					});

					// Hide 'Add Group' input
					$('.cancel-edit', $group).click(function(e) {

						e.preventDefault();

						$groupDiv.hide();

						if (!$('.small-table tr', $group).length) {

							$blankslate.show();
						}

						$('.tag_add_select_smartpoint_inputformError').remove();
					});

					// Insert Group from Device
					$('#group-submit').button().click(function(e) {

						var sGroupName = $input.val();
						var sGroupId;
						var $groupOption;

						e.preventDefault();

						// Validate Group Name
						if (sensus.pages.device.module.contents.group.validateGroup(sGroupName)) {

							$groupOption = $("#combo_group option").filter(function() {
								return sGroupName == $(this).text();
							});

							sGroupId = $groupOption.attr("value");

							sensus.pages.device.module.contents.group.insertDeviceToGroup(sGroupId, sGroupName, function(oResponse) {

								var _group = sensus.pages.device.module.contents.group;

								$groupDiv.hide();

								_group.addGroup(_group.allGroups[$groupOption.index()]);

								$(".combo_group_inputformError").remove();
							});
						}
					});

					// Remove Group from Device
					$group.delegate("a.removeGroup", "click", function(e) {

						var $this = $(this);

						e.preventDefault();

						sensus.util.actiondialog.launchActionDialog("deleteGroup",
								sensus.pages.device.module.contents.group.deleteGroupDialog(
										$this.parents("tr"),
										$this.data("id"),
										$this.parent().next().text()));
					});
				}
			},

			tag : {

				addTag : function(sTagId, sTagName, fnCallback) {

					if (!sTagId) {

						sTagId = "";
					}

					$.ajaxValidator.fnDoCall("api/tag/insertDevice", {
						tags 			: [{id 		: sTagId, name : sTagName}],
						devices 		: [{radio 	: {flexNetId : $.address.parameter("id")}}],
						sortExpressions : [{"field" : "name", "direction" : "Ascending"}]
					}, false, fnCallback);
				},

				removeTag : function(sTagId, sTagName, fnCallback) {

					$.ajaxValidator.fnDoCall("api/tag/deleteDevice", {
						tags 			: [{id		: sTagId, name : sTagName}],
						devices 		: [{radio	: {flexNetId : $.address.parameter("id")}}],
						sortExpressions : [{"field"	: "name", "direction" : "Ascending"}]
					}, false, fnCallback);
				},

				fetchTagByDevice : function(fnCallback) {

					$.ajaxValidator.fnDoCall("api/tag/fetch", {id : $.address.parameter("id")}, false, fnCallback);
				},

				fetchAllTags : function(fnCallback) {

					$.ajaxValidator.fnDoCall("api/tag/fetchAll",
							{endRow : 0, startRow : 0, sortExpressions : [{field : "name", direction : "Ascending"}]},
							false, fnCallback);
				},

				validateTag : function(sTagName) {

					var $input = $('#tag_add_select_smartpoint_input');
					sTagName = sTagName.toLowerCase();

					// Required
					if (!sTagName || sTagName == sensus.locale.get('smartpointdetail.tabs.about.selectTag')
							|| sTagName.replace(/\s/g,'').length == 0) {

						$input.validationEngine('showPrompt', sensus.locale.get('tag.page.error.required'), 'red', '', true);
						return false;
					}

					// Invalid character
					if (sTagName.match(/^(?:(?:[a-z0-9#]*(?:(?:(?:\s?-\s?|'s\s?|[(?:s)]'\s?|\s)[a-z0-9#]*)?))\s?)*$/i) == null) {

						$input.validationEngine('showPrompt', sensus.locale.get('commons.pages.characterInvalid'), 'red', '', true);
						return false;
					}

					// Limit character
					if (sTagName.length > 100) {

						$input.validationEngine('showPrompt', sensus.locale.get('commons.pages.max.characterJs','100'), 'red', '', true);
						return false;
					}

					// Check if tag exists
					if ($("#tags .tag-container span.title").filter(function() {
							return sTagName == $(this).text().toLowerCase();
						}).length) {

						$input.validationEngine('showPrompt', sensus.locale.get('tag.page.error.exist'), 'red', '', true);
						return false;
					};

					return true;
				},

				createLine : function(oTag) {

					return "<li><a class='remove-tag icon-small' href='#' data-id='" + oTag.id + "'></a>" +
							"<span class='title'>" + oTag.name + "</span>" +
							"<span class='count'>" + $.convertionNumber(oTag.devicesCount, false, 0).dvalueConverter + "</span></li>";
				},

				createOption : function(oTag) {

					return "<option value='" + oTag.id + "'>" + oTag.name + "</option>";
				},

				loadSelect : function(aTags) {

					var $options = [];
					var length = aTags.length;
					var i = 0;

					for (; i < length; i = i + 1) {

						$options.push(this.createOption(aTags[i]));
					}

					if (length > 0) {

						$('#tag_add_select_smartpoint').html($options.join(''));
					}
				},

				loadListTags : function(aTags) {

					var $tag = $("#tags");
					var $list = [];
					var $options = [];
					var length = aTags.length;
					var i = 0;

					for (; i < length; i = i + 1) {

						$list.push(this.createLine(aTags[i]));
					}

					$(' .tag-container', $tag).html($list.join(''));

					if (length > 0) {

						$(' .small-table', $tag).show();
						$('#blankslate-tags').hide();

						<sec:authorize access="hasAnyRole('ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">
							$('.remove-tag').hide();
						</sec:authorize>

						<sec:authorize access="!hasAnyRole('ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">
							$('.remove-tag').show();
						</sec:authorize>

					} else {

						$(' .small-table', $tag).hide();
						$('#blankslate-tags').show();
					}
				},

				init : function(oResponse) {

					var $tag 		= $("#tags");
					var $tagDiv 	= $('#div-tags');
					var $blankslate = $('#blankslate-tag');
					var sInputLabel = sensus.locale.get('smartpointdetail.tabs.about.selectTag');
					var $input;
					var aTags = [];

					$tagDiv.hide();

					// Start Combobox
					$("#tag_add_select_smartpoint").combobox();

					$input = $("#tag_add_select_smartpoint_input").addClass("input-clear");

					if (oResponse.deviceTags) {
						aTags = oResponse.deviceTags.tags || [];
					}

					sensus.pages.device.module.contents.tag.loadListTags(aTags);

					// Events
					// Show 'Add Tag' input
					$('#add-tags').click(function(e) {

						e.preventDefault();

						if ($('#tag_add_select_smartpoint option').length) {

							$input.val(sInputLabel);
							$blankslate.hide();
							$tagDiv.show();

						} else {

							// Load All Tags on combobox
							sensus.util.page.startProgressBar()

							sensus.pages.device.module.contents.tag.fetchAllTags(function(oResponse) {

								sensus.pages.device.module.contents.tag.loadSelect(oResponse.tags || []);

								$input.val(sInputLabel);
								$blankslate.hide();
								$tagDiv.show();

								sensus.util.page.stopProgressBar();
							});
						}
					});

					// Hide 'Add Tag' input
					$('.cancel-edit', $tag).click(function(e) {

						e.preventDefault();

						$tagDiv.hide();

						if (!$(".small-table tr", $tag).length) {
							$blankslate.show();
						}

						$('.tag_add_select_smartpoint_inputformError').remove();
					});

					// Insert Tag from Device
					$('#tag-submit').button().click(function(e) {

						var sTagName = $input.val();
						var sTagId;

						e.preventDefault();

						// Validate Tag Name
						if (sensus.pages.device.module.contents.tag.validateTag(sTagName)) {

							sTagId = $("#tag_add_select_smartpoint option").filter(function() {
								return sTagName == $(this).text();
							}).attr("value");

							sensus.pages.device.module.contents.tag.addTag(sTagId, sTagName, function(oResponse) {

								$tagDiv.hide();

								sensus.pages.device.module.contents.tag.fetchTagByDevice(function(oResponse) {
									sensus.pages.device.module.contents.tag.loadListTags(oResponse.tags || []);
								});

								sensus.pages.device.module.contents.tag.fetchAllTags(function(oResponse) {
									sensus.pages.device.module.contents.tag.loadSelect(oResponse.tags || []);
								});

								$(".tag_add_select_smartpoint_inputformError").remove();
							});
						}
					});

					// Remove Tag from Device
					$tag.delegate(".remove-tag", "click", function(e) {

						var $this = $(this);

						e.preventDefault();

						sensus.pages.device.module.contents.tag.removeTag(
								$this.data("id"),
								$this.next().text(), function(oResponse) {

									sensus.pages.device.module.contents.tag.fetchTagByDevice(function(oResponse) {
										sensus.pages.device.module.contents.tag.loadListTags(oResponse.tags || []);
									});
								});
					});
				}
			},

			scheduledEvents : {

				scheduleNameMaxLength : 45,

				formatScheduleName : function(sName) {

					if (sName.length > this.scheduleNameMaxLength) {
						return sName.substring(0, this.scheduleNameMaxLength) + " ...";
					}

					return sName;
				},

				reloadSchedules : function() {

					var fnCallback = function(oResponse) {

						if (oResponse.operationSuccess) {
							// Call scheduled events init passing the updated schedules
							sensus.pages.device.module.contents.scheduledEvents.init({deviceSchedules : {schedules : oResponse.schedules}});
						}
					};

					var oRequest = {
						type 	: "scheduleByDevice",
						id 		: $.address.parameter("id")
					};

					$.ajaxValidator.fnDoCall("api/schedule/fetch", oRequest, false, fnCallback);
				},

				createLine : function(oSchedule, oTimeZone) {

					var sActionName 	= oSchedule.dmAction.actionType.actionTypeEnumNameValue;
					var oDevice 		= sensus.pages.device.module.request.get("device");
					var sCategoryName 	= oSchedule.dmAction.actionType.actionCategoryEnumNameValue;
					var sStartTime 		= $.date.dateFormat(oSchedule.startTime, sensus.settings.dateFormatMask.replace("yyyy", "yy") + " h:i A", oTimeZone).split(" ");
					var sTime 			= sStartTime[1].split(":");
					var oBlackList 		= oSchedule.dmAction.devicesExcluded;
					var oActionUl 		= $('<ul/>');
					var sStatusEnum 	= oSchedule.statusEnum || null;
					var sStatus 		= "";

					if (!$.sc.isNullOrUndefined(oBlackList) && oBlackList.length > 0) {

						sStatus = sensus.locale.get("summary.text.headerTable.OptOut");

					} else if (!$.sc.isNullOrUndefined(sStatusEnum)) {

						sStatus = sensus.locale.get("systemintelligence.scheduledEvent.status."+sStatusEnum);

					}

					// Create actions button
					var oAction = $('<td class="button-action-expand"><a class="button" role="button"><span class="left">' + sensus.locale.get("commons.pages.actions")
									+ '</span><span class="ui-icon ui-icon-triangle-1-s left"></span></a><div id="listMenu" class="ui-dialog view-options hide"></div></td>')
					.hover(
							function () { $('div', this).stop(true, true).delay(200).slideDown(200); },
							function () { $('div', this).stop(true, true).slideUp(200); }
					);

					var fnCallback = function(oResponse) {

						// If success uptade the schedule list
						if (oResponse.operationSuccess) {
							sensus.pages.device.module.contents.scheduledEvents.reloadSchedules();
						}

					};

					/**
					 * START
					 * CREATE ACTION REQUEST
					*/
					var oDevice = new Device({
						radio : new Radio({
							flexNetId : oDevice.radio.flexNetId
						})
					});

					var oDMAction = new DMAction({
						devices : [oDevice],
						id : oSchedule.dmAction.id
					});

					var oActionRequest = new ActionRequest({
						action : oDMAction
					});
					/** END ACTION REQUEST */

					// Add button Opt-in or Opt-out of according the black list
					if (!$.sc.isNullOrUndefined(oBlackList) && oBlackList.length > 0) {

						// Opt-In Button
						var oActionOptIn  = $('<li><a id="menu-bt-expire" class="remove-dialog last" rel="Expire" href="" title="'
								+ sensus.locale.get("process.action.expireDevice.title") + '"><span class="icon-small icn-notice-form"></span> '
								+ sensus.locale.get("commons.pages.optIn") +'</a></li>');

						// Event Click of the opt-in action
						oActionOptIn.click(function(e) {

							e.preventDefault();

							$.ajaxValidator.fnDoCall("api/action/deleteDeviceToOptOutList", oActionRequest, false, fnCallback);
						});

						oActionUl.append(oActionOptIn);

					} else {

						// Opt-Out Button
						var oActionOptOut  	= $('<li><a id="menu-bt-cancel" class="remove-dialog last" rel="Expire" href="" title="'
								+ sensus.locale.get("process.action.cancelDevice.title") + '"><span class="icon-small icn-delete"></span> '
								+ sensus.locale.get("summary.text.headerTable.OptOut") +'</a></li>');

						// Event Click of the opt-out action
						oActionOptOut.click(function(e) {

							e.preventDefault();

							$.ajaxValidator.fnDoCall("api/action/insertDeviceToOptOutList", oActionRequest, false, fnCallback);
						});

						oActionUl.append(oActionOptOut);
					}

					oAction.find('#listMenu').append(oActionUl);

					return $("<tr>"
							+ "<td>" + sensus.locale.get(sCategoryName) + "</td>"
							+ "<td>" + sensus.locale.get(sActionName) + "</td>"
							+ "<td class='scheduled-id'><a class='request-readonly-event schedule-id' href='#'>" + this.formatScheduleName(oSchedule.name) + "<span class='hide'>" + oSchedule.id + "</span></a></td>"
							+ "<td class='date-scheduled'>" + sStartTime[0] + "</td>"
							+ "<td>" + sTime[0] + ":" + sTime[1] + "<sup>" + sStartTime[2] + "</sup></td>"
							+ "<td>" + sensus.locale.get("commons.pages." + oSchedule.frequency.frequencyTypeEnum) + "</td>"
							+ "<td>" + sStatus + "</td>"
							+ "</tr>").append(oAction);
				},

				loadSchedules : function (aSchedules, oTimeZone) {

					var length = aSchedules.length;
					var $list;
					var i;

					if (length > 0) {

						$list = [];

						for (i = 0; i < length; i = i + 1) {
							$list.push(this.createLine(aSchedules[i], oTimeZone));
						}

						$("#scheduled-events tbody").html($list);
						$(".button").button();

					} else {
						$('#scheduled-events').hide();
						$('#blankslate-scheduled').show();
					}
				},

				init : function(oResponse) {

					var oDevice 	= sensus.pages.device.module.request.get("device", oResponse);
					var oTimeZone 	= sensus.pages.device.module.util.getTimeZoneMinutes(oDevice);

					sensus.pages.device.module.contents.scheduledEvents.loadSchedules(
							oResponse.deviceSchedules ? (oResponse.deviceSchedules.schedules || []) : [], oTimeZone);

					// Open Dialog Schedule
					$(".schedule-id").click(function(e) {

						var sId = $(this).find("span").text();

						e.preventDefault();

						sensus.util.actiondialog.launchActionDialog("eventView",
								sensus.util.process.actions.eventView(sId, "openUpdateById", oTimeZone));
					});
				}
			},

			hanDevices : {

				hanDeviceInformationDialog : function(oTimeZone) {

					return {

						title : sensus.locale.get("smartpointdetail.dialog.deviceInformation.hanDeviceDetails"),

						width : 790,

						action : function(actionDialog) {

							var aIds 			= "";
							var sIdUrl 			= "";
							var actionDialogId 	= $("#action-dialog");

							$('#action-dialog').load("device/hanInformation", function() {

								var hanDevices 		= sensus.pages.device.module.contents.hanDevices;
								var idHanDevice 	= hanDevices.idHanDevice;
								var sDeviceType 	= hanDevices.sDeviceType;
								var sSubDeviceType 	= hanDevices.sSubDeviceType;

								$.ajaxValidator.fnDoCall("api/electricdevice/fetch",
										{id : idHanDevice , deviceType : sDeviceType}, false, function (oResponse) {

										var oHanDevice 			= sensus.pages.device.module.util.getFirstDeviceResponse(oResponse) || {};
										var oDialogHanDevice 	= $('#action-han-detail');
										var oHeaderDeviceType 	= oDialogHanDevice.find('.field-header-device-type');
										var oReadOnly 			= oDialogHanDevice.find('.read-only');
										var oSmallFlexNetId 	= $('.field-flex-net-id span', oDialogHanDevice);
										var oDeviceType 		= $('.field-device-type', oReadOnly);
										var oManufacture 		= $('.field-manufacture', oReadOnly);
										var oModelNumber 		= $('.field-model-number', oReadOnly);
										var oMac 				= $('.field-mac', oReadOnly);
										var oNetworkStatus 		= $('.field-network-status', oReadOnly);
										var oLastStatus 		= $('.field-last-status', oReadOnly);
										var oDateAdded 			= $('.field-date-added', oReadOnly);
										var oViewDetail 		= $('a.text-button', oDialogHanDevice);
										var sDeviceId 			= oHanDevice.deviceId;
										var sDeviceType 		= "";
										var doubleHyphen		= sensus.locale.get('commons.pages.doubleHyphen');
										var sManufacture 		= oHanDevice.deviceModel ? oHanDevice.deviceModel.manufacture : doubleHyphen;
										var sModel 				= oHanDevice.deviceModel ? oHanDevice.deviceModel.description : doubleHyphen;
										var sFlexNetId 			= oHanDevice.radio.flexNetId;
										var sMac 				= oHanDevice.macAddress;
										var sStatus 			= "";
										var sLastDateStatus 	= oHanDevice.configuration.lastDateStatus || doubleHyphen;
										var sDateAdded 			= oHanDevice.configuration.installDate || doubleHyphen;
										var sDateFormatMask 	= sensus.settings.dateFormatMask.replace("yyyy", "yy");

										if (oHanDevice.deviceType) {

											sDeviceType = sensus.locale.get("commons.pages." + oHanDevice.deviceType);
										}

										oViewDetail.attr('href',
												'device/detail?id=' + sFlexNetId + '&deviceType=' + oHanDevice.deviceType + "&typeEnum=" + sSubDeviceType)
												.addClass('alist').click(function() {
													actionDialogId.dialog('close');
												});

										oSmallFlexNetId.text('('+sDeviceId+')');
										oHeaderDeviceType.text(sDeviceType);
										oDeviceType.text(sensus.locale.get("commons.pages." + sSubDeviceType));
										oManufacture.text(sManufacture);
										oModelNumber.text(sModel);
										oMac.text(sMac);
										sStatus = sensus.locale.get("commons.pages." + oHanDevice.lifecycleStateEnum);
										oNetworkStatus.text(sStatus);
										oLastStatus.text($.date.dateFormat(sLastDateStatus, sDateFormatMask + " h:i A", oTimeZone));
										oDateAdded.text($.date.dateFormat(sDateAdded, sDateFormatMask, oTimeZone));
								});

								actionDialogId.dialog('open');
							});
						}
					};
				},

				loadDevices : function(aDevices, oTimeZone) {

					var	length = aDevices.length;
					var	i;
					var	aTbody;
					var	oDevice;
					var	sCustomer;
					var	oConfig;
					var	oRadio;
					var	sType;
					var	sSpecificType;
					var	sStatus;
					var	iFlexNetId;
					var	sDateFormat;
					var	iLastDateStatus;
					var get;

					if (length == 0) {

						$("#han-devices").hide();
						$("#blankslate-han-devices").show();

					} else {

						aTbody = [];
						sDateFormat = sensus.settings.dateFormatMask.replace("yyyy", "yy") + " h:i A";
						get = sensus.locale.get;

						for (i = 0; i < length; i = i + 1) {

							oDevice 		= aDevices[i];
							oConfig 		= oDevice.configuration || {};
							oRadio  		= oDevice.radio || {};
							iFlexNetId 		= oRadio.flexNetId;
							sType 			= oDevice.hanDeviceTypeEnum || oDevice.lcmTypeEnum || oDevice.deviceType;
							sSpecificType	= oDevice.hanDeviceTypeEnumValue || oDevice.lcmTypeEnumValue;
							sCustomer		= oDevice.radio.customerId;
							iLastDateStatus = oConfig.lastDateStatus;

							aTbody.push("<tr>");

							// FlexNet Id
							aTbody.push("<td class='hide flexNetId'>");
							aTbody.push(iFlexNetId);
							aTbody.push("</td>");

							// Mac Address
							aTbody.push("<td class='hide macAddressId'>");
							aTbody.push(oDevice.macAddress);
							aTbody.push("</td>");

							// Device Type
							aTbody.push("<td class='hide deviceType'>");
							aTbody.push(oDevice.deviceType);
							aTbody.push("</td>");

							// Specific Device Type
							aTbody.push("<td class='hide deviceSpecificType'>");
							aTbody.push(sSpecificType);
							aTbody.push("</td>");

							// Sub Device Type
							aTbody.push("<td class='hide subDeviceType'>");
							aTbody.push(sType);
							aTbody.push("</td>");

							// Customer Id
							aTbody.push("<td class='hide hanCustomerId'>");
							aTbody.push(sCustomer);
							aTbody.push("</td>");

							// Type
							aTbody.push("<td>");
							aTbody.push("<a href='" + iFlexNetId + "' class='request-han-detail' >"
									+ get("commons.pages." + sType) + "</a>") ;
							aTbody.push("</td>");

							// Device Id
							aTbody.push("<td class='deviceId'>");
							aTbody.push(oDevice.deviceId);
							aTbody.push("</td>");

							// Status
							aTbody.push("<td>");
							aTbody.push(get("commons.pages." + oDevice.lifecycleStateEnum));
							aTbody.push("</td>");

							// Last Status
							aTbody.push("<td>");
							aTbody.push(iLastDateStatus ? $.date.dateFormat(iLastDateStatus, sDateFormat, oTimeZone) : sensus.locale.get('commons.pages.doubleHyphen'));
							aTbody.push("</td>");

							// Remove
							aTbody.push("<td>");

							if (oDevice.lifecycleStateEnum == "JOINED") {

								aTbody.push('<a href="' + iFlexNetId + '" class="icn-delete icon-small remove-dialog" rel="Remove" title="'
										+ get("smartpointdetail.tabs.about.removeHanDevice", sType, ("" + iFlexNetId)) + '"></a>');
							}

							aTbody.push("</td>");
							aTbody.push("</tr>");
						}

						$("#han-devices tbody").html(aTbody.join(''));
					}
				},

				init : function(oResponse) {

					var	$hanContainer 	= $("#device-han-container");
					var	$table 			= $('#han-devices', $hanContainer);
					var oDevice 		= sensus.pages.device.module.request.get("device", oResponse);
					var oTimeZone 		= sensus.pages.device.module.util.getTimeZoneMinutes(oDevice);

					sensus.pages.device.module.contents.hanDevices.loadDevices(oResponse.hanDevices.devices || [],
							oTimeZone);

					// Connect Device
					$hanContainer.delegate(".han-join", "click", function(e) {

						var sHanDeviceEnum 			= sensus.constants.services.electric.han.name;
						var deviceTypePermissions 	= sensus.settings.oDeviceTypeParameters.deviceTypePermissions;

						var aHanDeviceActions = $.grep(deviceTypePermissions, function(n, i) {
							return n.deviceType == sHanDeviceEnum;
						})[0].deviceTypeModels[0].actions;

						var oConnectHanAction = $.grep(aHanDeviceActions, function(n, i) {
							return n.actionTypeEnumNameValue.contains("connect.to.han");
						})[0];

						e.preventDefault();

						sensus.util.actiondialog.launchActionDialog("connectHanDevice",
								sensus.commons.modules.dialogSettings.connectHanDevice(true,
										oConnectHanAction.actionTypeEnumNameValue, oConnectHanAction.actionTypeEnumValue));
					});

					// Device Detail
					$table.delegate(".request-han-detail", "click", function(e) {

						var hanDevices = sensus.pages.device.module.contents.hanDevices;

						e.preventDefault();

						hanDevices.idHanDevice 		= $(this).parent().siblings(".flexNetId").text();
						hanDevices.sDeviceType 		= $(this).parent().siblings(".deviceType").text();
						hanDevices.sSubDeviceType 	= $(this).parent().siblings(".subDeviceType").text();

						sensus.util.actiondialog.launchActionDialog('hanDeviceInformationDialog',
								hanDevices.hanDeviceInformationDialog(oTimeZone));
					});

					// Remove Device
					$table.delegate(".remove-dialog", "click", function(e) {

						var $this = $(this);
						var $parent = $this.parent();
						var disconnectHanFromMeterPage = sensus.commons.modules.dialogSettings.disconnectHanFromMeterPage;

						e.preventDefault();

						disconnectHanFromMeterPage.flexNetId 					= $parent.siblings(".flexNetId").text();
						disconnectHanFromMeterPage.handeviceType 				= $parent.siblings(".deviceType").text();
						disconnectHanFromMeterPage.customerId 					= $parent.siblings(".hanCustomerId").text();
						disconnectHanFromMeterPage.parentId 					= $.address.parameter("id");
						disconnectHanFromMeterPage.sNameHanDevice 				= $parent.find('a').attr('href');
						disconnectHanFromMeterPage.idDevice 					= $parent.siblings(".deviceId").text();
						disconnectHanFromMeterPage.specificDeviceTypeEnumValue 	= $parent.siblings(".deviceSpecificType").text();

						sensus.util.actiondialog.launchActionDialog('disconnectHanFromMeterPage', disconnectHanFromMeterPage);
					});
				}
			},

			endpoints : {

				/*
				* Endpoint Information Dialog
				* @param [Object] oTimeZone that contains the time zone in minutes
				*/
				endpointInformationDialog : function(oTimeZone) {

					return {

						// The dialog title.
						title : sensus.locale.get("smartpointdetail.dialog.deviceInformation.endpointDetails"),

						// The dialog width.
						width : 790,

						/**
						 * The function that will be called when the action dialog is launched.
						 *
						 * @param actionDialog
						 *            a reference to the actionDialog objext
						 */
						action : function(actionDialog) {

							var aIds 			= "";
							var sIdUrl 			= "";
							var actionDialogId 	= $("#action-dialog");

							$('#action-dialog').load("device/endpointInformation", function() {

								var endpoints 		= sensus.pages.device.module.contents.endpoints;
								var idEndpoint 		= endpoints.idEndpoint;
								var sDeviceType 	= endpoints.sDeviceType;
								var sSubDeviceType 	= endpoints.sSubDeviceType;

								$.ajaxValidator.fnDoCall("api/arqivadevice/fetch", {type : "search" , id : idEndpoint}, false, function (oResponse) {

										var oEndpoint 			= sensus.pages.device.module.util.getFirstDeviceResponse(oResponse.endpoints) || {};
										var oDialogEndpoint 	= $('#action-endpoint-detail');
										var oHeaderDeviceType 	= oDialogEndpoint.find('.field-header-device-type');
										var oReadOnly 			= oDialogEndpoint.find('.read-only');
										var oSmallFlexNetId 	= $('.field-flex-net-id span', oDialogEndpoint);
										var oDeviceType 		= $('.field-device-type', oReadOnly);
										var oManufacture 		= $('.field-manufacture', oReadOnly);
										var oModelNumber 		= $('.field-model-number', oReadOnly);
										var oMac 				= $('.field-mac', oReadOnly);
										var oNetworkStatus 		= $('.field-network-status', oReadOnly);
										var oLastStatus 		= $('.field-last-status', oReadOnly);
										var oDateAdded 			= $('.field-date-added', oReadOnly);
										var oViewDetail 		= $('a.text-button', oDialogEndpoint);
										var sDeviceId 			= endpoints.DeviceId;
										var sDeviceType 		= endpoints.DeviceType;
										var sManufacture 		= endpoints.Manufacture;
										var sModel 				= endpoints.ModelNumber;
										var sMac				= endpoints.Mac;
										var sFlexNetId 			= endpoints.idEndpoint;
										var sDateFormatMask 	= sensus.settings.dateFormatMask.replace("yyyy", "yy");

										if (oEndpoint.deviceType) {

											sDeviceType = sensus.locale.get("commons.pages." + oEndpoint.deviceType);
										}

										oViewDetail.attr('href',
												'device/detail?id=' + sFlexNetId + '&deviceType=' + oEndpoint.deviceType + "&typeEnum=" + sSubDeviceType)
												.addClass('alist').click(function() {
													actionDialogId.dialog('close');
												});

										oSmallFlexNetId.text(sModel);
										oDeviceType.text(sDeviceType);
										oManufacture.text(sManufacture);
										oModelNumber.text(sModel);
										oMac.text(sMac);
								});

								actionDialogId.dialog('open');
							});
						}
					};
				},

				loadDevices : function(aDevices, oTimeZone) {

					var	length = aDevices.length;
					var	i;
					var	aTbody;
					var	oDevice;
					var	sCustomer;
					var	oConfig;
					var	oRadio;
					var	sType;
					var	sSpecificType;
					var	sStatus;
					var	iFlexNetId;
					var	sDateFormat;
					var	iLastDateStatus;
					var get;

					if (length == 0) {

						$("#endpoint-devices").hide();
						$("#blankslate-endpoints-devices").show();

					} else {

						aTbody = [];
						sDateFormat = sensus.settings.dateFormatMask.replace("yyyy", "yy") + " h:i A";
						get = sensus.locale.get;

						for (i = 0; i < length; i = i + 1) {

							oDevice 		= aDevices[i];
							oConfig 		= oDevice.configuration || {};
							oRadio  		= oDevice.radio || {};
							iFlexNetId 		= oRadio.flexNetId;
							sType 			= oDevice.hanDeviceTypeEnum || oDevice.lcmTypeEnum || oDevice.deviceType;
							sSpecificType	= oDevice.hanDeviceTypeEnumValue || oDevice.lcmTypeEnumValue;
							sCustomer		= oDevice.radio.customerId;
							iLastDateStatus = oConfig.lastDateStatus;
							sManufacture 	= oDevice.deviceModel.manufacture;
							sModelNumber 	= oDevice.deviceModel.description;
							sMac 			= oDevice.macAddress;
							sDeviceId 		= oDevice.deviceId;

							aTbody.push("<tr>");

							// FlexNet Id
							aTbody.push("<td class='hide flexNetId'>");
							aTbody.push(iFlexNetId);
							aTbody.push("</td>");

							// Mac Address
							aTbody.push("<td class='hide macAddressId'>");
							aTbody.push(oDevice.macAddress);
							aTbody.push("</td>");

							// Device Type
							aTbody.push("<td class='hide deviceType'>");
							aTbody.push(oDevice.deviceType);
							aTbody.push("</td>");

							// Specific Device Type
							aTbody.push("<td class='hide deviceSpecificType'>");
							aTbody.push(sSpecificType);
							aTbody.push("</td>");

							// Sub Device Type
							aTbody.push("<td class='hide subDeviceType'>");
							aTbody.push(sType);
							aTbody.push("</td>");

							// Customer Id
							aTbody.push("<td class='hide hanCustomerId'>");
							aTbody.push(sCustomer);
							aTbody.push("</td>");

							// Manufacture
							aTbody.push("<td class='hide manufacture'>");
							aTbody.push(sManufacture);
							aTbody.push("</td>");

							// Model Number
							aTbody.push("<td class='hide modelNumber'>");
							aTbody.push(sModelNumber);
							aTbody.push("</td>");

							// Mac Address
							aTbody.push("<td class='hide mac'>");
							aTbody.push(sMac);
							aTbody.push("</td>");

							// Type
							aTbody.push("<td>");
							aTbody.push("<a href='" + iFlexNetId + "' class='request-endpoint-detail' >"
									+ sensus.locale.get("commons.pages." + sType) + "</a>") ;
							aTbody.push("</td>");

							// Device Id
							aTbody.push("<td class='deviceId'>");
							aTbody.push(sDeviceId);
							aTbody.push("</td>");

							// Remove
							aTbody.push("<td>");

							aTbody.push("</td>");
							aTbody.push("</tr>");
						}

						$("#endpoint-devices tbody").html(aTbody.join(''));
					}
				},

				init : function(oResponse) {

					var	$endpointContainer 	= $("#device-endpoint-container");
					var	$table 			= $('#endpoint-devices', $endpointContainer);
					var oDevice 		= sensus.pages.device.module.request.get("device", oResponse);
					var oTimeZone 		= sensus.pages.device.module.util.getTimeZoneMinutes(oDevice);

					sensus.pages.device.module.contents.endpoints.loadDevices(oResponse.endpoints.devices || [],
							oTimeZone);

					// Device Detail
					$table.delegate(".request-endpoint-detail", "click", function(e) {

						var endpoints = sensus.pages.device.module.contents.endpoints;

						e.preventDefault();

						endpoints.idEndpoint = $(this).parent().siblings(".flexNetId").text();
						endpoints.DeviceId = $(this).parent().siblings(".deviceId").text();
						endpoints.Manufacture = $(this).parent().siblings(".manufacture").text();
						endpoints.ModelNumber = $(this).parent().siblings(".modelNumber").text();
						endpoints.Mac = $(this).parent().siblings(".mac").text();
						endpoints.DeviceType = $(this).parent().siblings(".deviceType").text();
						endpoints.SubDeviceType = $(this).parent().siblings(".subDeviceType").text();

						sensus.util.actiondialog.launchActionDialog('endpointInformationDialog',
								endpoints.endpointInformationDialog(oTimeZone));
					});

				}
			},

			lcmRelay : {

				AMP : sensus.locale.get("smartpointdetail.tabs.about.amp"),

				createLine : function(oLcm) {

					return "<tr><td>" + oLcm.relay + "</td><td>" + oLcm.amp + "<small>" + this.AMP + "</small></td>" +
							"<td>" + sensus.locale.get("smartpointdetail.tabs.about.lcmRelay." + oLcm.deviceClass) + "</td><td>--</td>" +
							"<td>--</td><td>" + (oLcm.tamperDetectTimeout || "--") + "</td></tr>";
				},

				getLcmRelays : function(oLcmRelayResponse) {

					if (oLcmRelayResponse && oLcmRelayResponse.lcmRelays) {
						return oLcmRelayResponse.lcmRelays;
					}

					return [];
				},

				loadLcmRelay : function(aLcm) {

					var length = aLcm.length;
					var $list;

					if (length == 0) {

						$("#lcm-relay").hide();
						$('#blankslate-lcm-relay').show();

					} else {

						$list = [];

						for (i = 0; i < length; i = i + 1) {

							$list.push(this.createLine(aLcm[i]));
						}

						$("#lcm-relay tbody").html($list.join(''));
					}
				},

				init : function(oResponse) {

					var oLcmRelays = sensus.pages.device.module.contents.lcmRelay.getLcmRelays(oResponse.lcmRelay);

					sensus.pages.device.module.contents.lcmRelay.loadLcmRelay(oLcmRelays ? (oLcmRelays || []) : []);
				}
			},

			demandResponseProgramParticipation : {

				dateMask : sensus.settings.dateFormatMask.replace("yyyy", "yy") + " h:i a",

				createLine : function(oProcessItemHistory, oTimeZone) {

					var id = oProcessItemHistory.id;

					return ["<tr><td><span class='item-demand-response' data-id='", id, "' title='", id, "'>",
							oProcessItemHistory.description, "</span></td><td>",
							$.date.dateFormat(oProcessItemHistory.statusTime, this.dateMask, oTimeZone),
							"</td><td>", sensus.locale.get("commons.pages." + oProcessItemHistory.processItemHistoryStatusEnum),
							"</td></tr>"].join("");
				},

				loadDemandResponseProgramParticipation : function(aProcessItemHistories, oTimeZone) {

					var length = aProcessItemHistories.length;
					var $list;

					if (length == 0) {

						$("#demand-response-program-participation").hide();
						$('#blankslate-demand-response-program-participation').show();

					} else {

						$list = [];

						for (i = 0; i < length; i = i + 1) {

							$list.push(this.createLine(aProcessItemHistories[i], oTimeZone));
						}

						$("#demand-response-program-participation tbody").html($list.join(''));
					}
				},

				getProcessItemHistories : function (oProcessResponse) {

					if (oProcessResponse && oProcessResponse.processes
							&& oProcessResponse.processes[0].processItems
							&& oProcessResponse.processes[0].processItems[0].processItemHistories) {

						return oProcessResponse.processes[0].processItems[0].processItemHistories;
					}

					return [];
				},

				init : function(oResponse) {

					var	$div = $("#device-demand-response-program-participation");

					var aProcessItemHistories = sensus.pages.device.module.contents.demandResponseProgramParticipation
														.getProcessItemHistories(oResponse.demandResponseProgramParticipation);

					sensus.pages.device.module.contents.demandResponseProgramParticipation
							.loadDemandResponseProgramParticipation(aProcessItemHistories);

					// Summary
					$div.delegate(".item-demand-response", "click", function(e) {

						e.preventDefault();

						sensus.util.globalActions.sId = $(this).data("id");
						sensus.util.globalActions.sType = "Demand Response";
						sensus.util.actiondialog.launchActionDialog("summary", sensus.util.globalActions.summary);
					});
				}
			},

			demandResponseSetup : {

				dateMask : sensus.settings.dateFormatMask.replace("yyyy", "yy") + " h:i:sA",

				createLine : function(oProcess, oTimeZone) {

					var action = oProcess.action || {};
					var last = action.actionTime ? $.date.dateFormat(action.actionTime, this.dateMask, oTimeZone) : sensus.locale.get('commons.pages.doubleHyphen');

					return ["<tr><td>", action.enrollmentCode, "</td><td>", action.startMinutes,
							"</td><td>", action.endMinutes, "</td><td>", last, "</td></tr>"].join("");
				},

				loadDemandResponseSetup : function(aProcess, oTimeZone) {

					var length = aProcess.length;
					var $list;

					if (length == 0) {

						$("#demand-response-setup").hide();
						$('#blankslate-demand-response-setup').show();

					} else {

						$list = [];

						for (i = 0; i < length; i = i + 1) {

							$list.push(this.createLine(aProcess[i]), oTimeZone);
						}

						$("#demand-response-setup tbody").html($list.join(''));
					}
				},

				init : function(oResponse) {

					var aProcess = [];

					if (oResponse.demandResponseSetup && oResponse.demandResponseSetup.processes) {

						aProcess = oResponse.demandResponseSetup.processes;
					}

					sensus.pages.device.module.contents.demandResponseSetup.loadDemandResponseSetup(aProcess);
				}
			},

			demandResponseSetupLcm : {

				init : function(oResponse) {

					var aProcess = [];

					if (oResponse.demandResponseSetup && oResponse.demandResponseSetup.processes) {
						aProcess = oResponse.demandResponseSetup.processes;
					}

					sensus.pages.device.module.contents.demandResponseSetup.loadDemandResponseSetup(aProcess);
				}
			}
		}
	</script>

</sec:authorize>