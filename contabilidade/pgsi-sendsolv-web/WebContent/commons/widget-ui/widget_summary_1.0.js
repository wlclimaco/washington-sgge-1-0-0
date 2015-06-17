$.fn.summary = function (sId, sActionEnum, bWithoutTabs, iActionId, iRniEventId, sDeviceCategory, fnCallBackSummary)
{
	var _summaryHtml		= null;
	var _this				= $(this);
	var _fetchSummaryURL	= "api/summary/fetch";

	/**
	 * Array with all ActionEnum of OTA configuration actions
	 */
	var _aRadioConfiguration =
	[
	 	"SET_TRANSMIT_RADIO_CONFIGURATION",
	 	"SET_READS_RADIO_CONFIGURATION",
	 	"SET_ALARM_THRESHOLDS_RADIO_CONFIGURATION",
	 	"SET_GENERAL_SETUP_RADIO_CONFIGURATION",
	 	"SET_POWER_QUALITY_RADIO_CONFIGURATION",
	 	"SET_THAMES_RADIO_CONFIGURATION"
	];

	/**
	 * Array with all ActionEnum of Get Data From Device actions
	 */
	var _aGetDataFromDevice =
	[
	 	"GET_READ_DATA",
	 	"GET_SERVICE_QUALITY_READ",
	 	"GET_SUPERVISORY_READ",
	 	"GET_ON_THE_GLASS_READ"
	];

	/**
	 * Commom Request to all export csv
	 */
	var _oCsvRequest = new InquiryProcessRequest(
	{
		processes :	[
	 	{
	 		id 			: sId,
	 		rniEventId 	: iRniEventId
	 	}],
	 	sortExpressions : [
		{
			direction 	: "Ascending",
			field 		: "process_item_id"
		}]
	});

	/**
	 * Object of ENUMs to store informations
	 */
	var _oActions =
	{
		INITIATE_DEMAND_RESPONSE_EVENT :
		{
			aTabs 		: ["communication", "demandResponse"],
			iStartTab 	: 3,
			sTab		: "demandResponse"
		},
		INITIATE_DEMAND_RESPONSE_EVENT_TO_GROUP :
		{
			aTabs 		: ["communication", "demandResponse"],
			iStartTab 	: 3,
			sTab		: "demandResponse"
		},
		SET_RELAY_CONFIGURATION :
		{
			aTabs 		: ["communication", "relayConfiguration"],
			iStartTab 	: 5,
			sTab		: "relayConfiguration"
		},
		GET_DATA_FROM_DEVICE :
		{
			aTabs 		: ["communication", "demandRead"],
			iStartTab 	: 1,
			sTab		: "demandRead"
		},
		GET_RELAY_CONFIGURATION :
		{
			aTabs 		: ["communication", "relayConfiguration"],
			iStartTab 	: 5,
			sTab		: "relayConfiguration"
		},
		general 		:
		{
			aTabs 		: ["communication"],
			iStartTab 	: 0,
			sTab		: "communication"
		},
		IMPORT_HAN_DEVICE :
		{
			aTabs 		: ["communication","importHanDevices"],
			iStartTab 	: 4,
			sTab		: "importHanDevices"
		},
		RADIO_CONFIGURATION :
		{
			aTabs 		: ["communication", "radioConfigurationSummary"],
			iStartTab 	: 2,
			sTab		: "radioConfigurationSummary"
		}
	};

	/**
	 * Object with informations according sActionENUM
	 */
	var _oAction = _oActions[sActionEnum];

	/**
	 * Function called always that all functions is executed into the summary
	 */
	var _fnCallBackInit = function ()
	{
		if ( !$.sc.isNullOrUndefined(fnCallBackSummary) && $.isFunction(fnCallBackSummary))
		{
			return fnCallBackSummary();
		}
	};

	/**
	 * Show only necessary tabs
	 */
	var _fnShowTabs = function ()
	{
		var aTabs = _oAction.aTabs;

		if ( !$.sc.isNullOrUndefined(aTabs) )
		{
			for ( var i = 0, count = aTabs.length; i < count; i++)
			{
				$("#" + aTabs[i]).removeClass("hide");
			}
		}
	};

	/**
	 * Calculate the percentage and return
	 */
	var _fnGetPercentage = function (sum, per)
	{
		var avg = 0;

		if (sum == avg)
		{
			return avg;
		}
		else
		{
			avg = (per / sum) * 100;

			// Round a number to the nearest integer
			// example: 42.3% and 57.7% turn to 42% and 58%, total 100%
			return Math.round(avg);
		}
	};

	/**
	 *  Create Information
	 *
	 *  @param {Object} $oInfoColumn, jQuery Object
	 *  @param {String} sTitle, title
	 *  @param {String} sValue, value
	 *  @param {String} sSubHead, sub head
	 */
	var _fnCreateInformation = function ($oInfoColumn, sValue, sSubHead) {

		$oInfoColumn
			.find(".value").html(sValue) // value
			.next().html(sSubHead); // sub-head
	};

	/**
	 *  Create Information Field
	 */
	var _fnCreateInformationField = function (oInfoColumn, sValue, sSubHead)
	{
		oInfoColumn.find(".value").html(sValue).next().html(sSubHead);
	};

	/**
	 * Fill fields of success and fails according process items
	 */
	var _fnFillSuccessAndFails = function (oResponse)
	{
		var oProcess 			= oResponse.processes[0];
		var oProcessItems 		= oProcess.processItems;
		var oFailedDevicesGroup = $("#failed-devices-group", _this);
		var oProcessSummary;
		var sDescSuccessDevices	= "";
		var sDescFailedDevices 	= "";
		var iTotalProcessItems;
		var iTotalSuccess;
		var iTotalFailed;
		var iPercentageSuccess;
		var iPercentageFailed;
		var oProperty;

		if (sActionEnum != "INITIATE_DEMAND_RESPONSE_EVENT_TO_GROUP")
		{
			oProcessSummary		= oProcess.processSummary;
			iTotalProcessItems	= oProcessSummary.totalSmartpoints || 0;
			iTotalSuccess		= oProcessSummary.successSmartpoints || 0;
			iTotalFailed		= oProcessSummary.failedSmartpoints || 0;
			iPercentageSuccess	= _fnGetPercentage(iTotalProcessItems, iTotalSuccess);
			iPercentageFailed	= _fnGetPercentage(iTotalProcessItems, iTotalFailed);
			sDescSuccessDevices	= iTotalSuccess + " " + $.sc.locale.get("commons.pages.smartPoints");
			sDescFailedDevices 	= iTotalFailed 	+ " " + $.sc.locale.get("commons.pages.smartPoints");

			// Create list of fails
			_fnCreateListFails(oFailedDevicesGroup, oProcessSummary, null);
		}
		else
		{
			// Fake data, just used in order to set percentage
			iTotalProcessItems 	= 1;
			iTotalSuccess 		= 1;
			iTotalFailed		= 0;

			for (var i = 0, iProperties = oProcess.properties.length; i < iProperties; i++)
			{
				oProperty = oProcess.properties[i];

				if (oProperty.propertyName == "DESCRIPTION")
				{
					iTotalSuccess 	= 0;
					iTotalFailed 	= 1;

					oFailedDevicesGroup.html("<li class='fail'>" + oProperty.propertyValue + "</li>").parent.show();
				}
			}

			iPercentageSuccess	= _fnGetPercentage(iTotalProcessItems, iTotalSuccess);
			iPercentageFailed	= _fnGetPercentage(iTotalProcessItems, iTotalFailed);
		}

		// Create Success Column Information
		_fnCreateInformationField($("#success-information", _this), iPercentageSuccess + "%", sDescSuccessDevices);

		// Create Failed Column Information
		_fnCreateInformationField($("#failed-information", _this), iPercentageFailed+ "%", sDescFailedDevices);
	};

	/**
	 * Create list of fails
	 */
	var _fnCreateListFails = function (oList, oProcessSummary, bRetry)
	{
		var aLi = [];
		var oProcessStatus;
		var sStatusEnum;
		var oStatus;
		var iTotal;

		if ( !$.sc.isNullOrUndefined(oProcessSummary)
				&& !$.sc.isNullOrUndefined(oProcessSummary.processItemStatusCountList)
				&& oProcessSummary.processItemStatusCountList.length )
		{
			oProcessStatus = oProcessSummary.processItemStatusCountList;

			for (iStatus in oProcessStatus)
			{
				oStatus = oProcessStatus[iStatus];
				sStatus = oStatus.processItemStatusEnum;
				iTotal 	= oStatus.totalSmartpoints;

				if ( sStatus != "COMPLETED")
				{
					if (sStatus == "FAILED" && bRetry)
					{
						aLi.push("<li class='fail'>" + ($.sc.locale.get("summary.text.processStatusMessage." + (iTotal > 1 ? "plural." : "single.") + sStatus, "" + iTotal)) +
								" <a href='#' id='retry-failed' class='button small ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only' role='button' aria-disabled='false'>" +
								" <span class='ui-button-text'>" + $.sc.locale.get("summary.text.retryConnect") + "</span></a></li>");
						continue;
					}

					aLi.push("<li class='fail'>" + ($.sc.locale.get("summary.text.processStatusMessage." + (iTotal > 1 ? "plural." : "single.") + sStatus, "" + iTotal))	+ "</li>");
				}
			}
		}

		if (aLi.length)
		{
			oList.html(aLi.join("")).parent().show();
		}
	};

	/**
	 * Object with all tabs content
	 */
	var _contentTabs =
	{
		commonsTable :
		{
			fnChildTableHTML : function (id)
			{
				return "<tr class='sub-table'>" +
							"<td colspan='4'>" +
								"<table id='child-" + id +"' class='small-table' style='margin-left: 90px; width: 90%;'>" +
								"</table>" +
							"</td>" +
						"</tr>";
			},

			fnChildConfig : function (oResponse, sTableId)
			{
				var getColumns = _contentTabs.relayConfiguration.table.aoChildColumns;

				if ($("#tableDemandRead").length)
				{
					getColumns = _contentTabs.demandRead.table.aoChildColumns;
				}

				return {
					id 				: sTableId,
					bPreLoad 		: true,
					bDestroy		: true,
					bParamsOnUrl	: false,
					bIsDialog		: true,
					bRetrieve   	: true,
					aaData 			: oResponse,
					bPagination		: false,
					bSort			: false,

					ajax 			:
					{
						sObj		: "processItemResults"
					},

					oSettings 		:
					{
						bFooterVisible 	: false
					},

					aoColumns		: getColumns
				};
			},

			fnInitializeChildTable : function (oResponse, flexNetId, sTableId, cell)
			{
				$(cell).parent().after(_contentTabs.commonsTable.fnChildTableHTML(flexNetId));

				// Make Table config
				// Initialize table

				$.sc.table.init(_contentTabs.commonsTable.fnChildConfig(oResponse, sTableId));
			},

			fnChildTableButton : function (oResponse, cell, bOpened)
			{
				var $Html 		= "";

				if ( !$.sc.isNullOrUndefined(oResponse) && !$.sc.isNullOrUndefined(oResponse.device) )
				{
					var flexNetId 	= oResponse.device.communicationDevice.flexNetId;
					var sTableId 	= "#child-" + flexNetId; // replace especial chars

					if ($.sc.isNullOrUndefined(bOpened) || bOpened == false)
					{
						$Html = $("<span class='action-link ui-icon ui-icon-plus'></span>");
					}
					else if (oResponse.processItemResults.length)
					{
						$Html = $("<span class='action-link ui-icon ui-icon-minus'></span>");
						_contentTabs.commonsTable.fnInitializeChildTable(oResponse, flexNetId, sTableId, cell);
					}
					else
					{
						return $Html;
					}

					$Html.on("click", function()
					{
						var $this = $(this);

						if ( $this.hasClass("ui-icon-plus") )
						{
							// hide all sub tables
							$("tr.sub-table").hide();
							$("td .action-link").removeClass("ui-icon-minus").addClass("ui-icon-plus");

							// Check if there is table if yes show the tr
							if ( $(sTableId).length )
							{
								// show table
								$(sTableId).closest("tr.sub-table").show();
							}
							else
							{
								_contentTabs.commonsTable.fnInitializeChildTable(oResponse, flexNetId, sTableId, cell);
							}

							//	change the button class switch from plus to minus
							$this.removeClass("ui-icon-plus").addClass("ui-icon-minus");
						}
						else
						{
							//	change the button class switch from minus to plus
							$this.removeClass("ui-icon-minus").addClass("ui-icon-plus");
							$("#child-" + flexNetId).closest("tr.sub-table").hide();
						}
					});
				}

				return $Html;
			}
		},

		commonCellProcessors :
		{
			fnNetworkAddress : function (val, type, full)
			{
				if (type !== "display")
				{
					return val;
				}

				var oDevice = full.device;

				if ( !$.sc.isNullOrUndefined(oDevice) && !$.sc.isNullOrUndefined(oDevice.macAddress) )
				{
					return oDevice.macAddress;
				}

				if ( !$.sc.isNullOrUndefined(oDevice)
						&& !$.sc.isNullOrUndefined(oDevice.communicationDeviceAsRadio)
						&& !$.sc.isNullOrUndefined(oDevice.communicationDeviceAsRadio.flexNetIdString) )
				{
					return oDevice.communicationDeviceAsRadio.flexNetIdString;
				}

				return full.networkAddress;
			},

			fnPremiseId : function (val, type, full)
			{
				if (type !== "display")
				{
					return val;
				}

				if ( !$.sc.isNullOrUndefined(full.device)
						&& !$.sc.isNullOrUndefined(full.device.configuration)
						&& !$.sc.isNullOrUndefined(full.device.configuration.premiseId) )
				{
					return full.device.configuration.premiseId;
				}

				return "";
			},

			fnAddress : function (val, type, full)
			{
				if (type !== "display")
				{
					return val;
				}

				if ( !$.sc.isNullOrUndefined(full.device)
						&& !$.sc.isNullOrUndefined(full.device.communicationDevice)
						&& !$.sc.isNullOrUndefined(full.device.communicationDevice.location)
						&& !$.sc.isNullOrUndefined(full.device.communicationDevice.location.address) )
				{
					return full.device.communicationDevice.location.address;
				}

				return "";
			},

			fnState : function (val, type, full)
			{
				if (type !== "display")
				{
					return val;
				}

				return $.sc.enums.internationalizeByLabel("com.pgsi.dm.common.process.model.ProcessItemStatusEnum", val, "");
			},

			fnCreateButton : function (cell, cellData, rowData, rowIndex, colIndex)
			{
				if (rowData.processItemResults.length
						&& this.data().config.iTotalDisplayRecords > 1)
				{
					$(cell).append(_contentTabs.commonsTable.fnChildTableButton(rowData, cell));
				}
			}
		},

		communication :
		{
			url 		: "api/summary/fetchCommunicationSummary",

			fnRequest	: function (pageSize)
			{
				sId = parseInt(sId, 10);

				if ( !$.isNumeric(sId) )
				{
					sId = null;
				}

				var oProcess = new Process(
				{
					id 			: sId,
					rniEventId	: iRniEventId || null
				});

				return {
					processItemStatusEnum	: "FAILED",
					processes 				: [oProcess],
					processId 				: sId,
					pageSize				: pageSize ? pageSize : undefined,
					sortExpressions 		: [
	   				{
	   					direction 	: "Ascending",
	   					field 		: "process_item_id"
	   				}]
				};
			},

			fnProcessDescription : function (oProcess)
			{
				var sProcessDescription = "";
				var aPropertyLink 		= !$.sc.isNullOrUndefined(oProcess.propertyLink) ? oProcess.propertyLink.split("|") : [];
				var sProcessStatus		= oProcess.processStatusEnum;
				var iDevices			= oProcess.totalSmartpoints;
				var oAction 			= oProcess.action;
				var sActionTypeEnum		= oAction.actionType.actionTypeEnum;
				var sActionCategoryEnum = oAction.actionType.actionCategoryEnum;
				var sCompletedInfo	 	= "";
				var sSentFrom			= oProcess.onDemand ? $.sc.locale.get("pgsi.dm.action.on_demand").toLowerCase() : null;
				var sActionType			= $.sc.enums.internationalizeByLabel("com.pgsi.dm.common.action.model.ActionTypeEnum", sActionTypeEnum).toLowerCase();
				var sActionCategory 	= $.sc.locale.get(oAction.actionType.actionCategoryEnumNameValue).toLowerCase();
				var sDevice 			= "";
				var sTower				= null;

				if (sProcessStatus == "COMPLETED")
				{
					sCompletedInfo = ($.sc.locale.get("longRunning.table.header.completedIn") + " " +
										pgsi.util.process.fnCreateCompletedInColumn(sProcessStatus, oProcess.estimatedSecondsToComplete,	oProcess.startTime,	oProcess.endTime)).toLowerCase();
				}
				else if (sProcessStatus == "COMMAND_SENT")
				{
					sCompletedInfo = $.sc.locale.get("longRunning.table.header.wasSent").toLowerCase();
				}

				switch (sActionCategoryEnum)
				{
					// COMMONS GROUP/TAG/SCHEDULE
					case pgsi.constants.actionCategories.tower:

						sTower = aPropertyLink[1];

					case pgsi.constants.actionCategories.group:
					case pgsi.constants.actionCategories.tag:
					case pgsi.constants.actionCategories.schedule:

						sDevice = aPropertyLink[3];

						if ( iDevices <= 1 )
						{
							return $.sc.locale.get("pgsi.dm.summary.action.description.common.one.device", sSentFrom, sActionType, sActionCategory, sTower || sDevice, sCompletedInfo);
						}

						return $.sc.locale.get("pgsi.dm.summary.action.description.common.several.devices", sSentFrom, sActionType, sActionCategory, iDevices, sCompletedInfo);

					// RNI ACTIONS
					default:

						sDevice = aPropertyLink[2] || aPropertyLink[1];

						if ( !$.sc.isNullOrUndefined(oProcess.rniEventId) )
						{
							$("#rni-event-id em").text($.sc.locale.get("commons.pages.rni.event.id", oProcess.rniEventId)).show();
						}

						// SCHEDULED ACTIONS
						if ( $.sc.isNullOrUndefined(sSentFrom) )
						{
							sSentFrom = $.sc.locale.get("filter.status.scheduled.label").toLowerCase();
						}

						if ( iDevices <= 1 )
						{
							return $.sc.locale.get("pgsi.dm.summary.action.rni.description.one.device", sSentFrom, sActionType, sDevice, sCompletedInfo);
						}

						return $.sc.locale.get("pgsi.dm.summary.action.rni.description.several.devices", sSentFrom, sActionType, iDevices, sCompletedInfo);
				}

				return sProcessDescription;
			},

			fnFillInformations : function (oResponse)
			{
				var oProcess 		= oResponse.processes[0];
				var iProcessItems	= oProcess.processItems.length;

				// PROCESS INFORMATIONS
				$("#description em", _this).text(_contentTabs.communication.fnProcessDescription(oProcess));

				// COUNT PROCESS ITEMS
				if (iProcessItems)
				{
					$("#total-results", _this).text(oResponse.resultsSetInfo.totalRowsAvailable);
				}
			},

			table :
			{
				id 			: "#tableCommunicationSummary",
				oResponse 	: null,

				get aoColumns ()
				{
					return [
					 	{
					 		headerData		: $.sc.locale.get("summary.text.headerTable.device_id"),
					 		mData			: "device.deviceId",
					 		sDefaultContent : ""
					 	},
					 	{
					 		headerData		: $.sc.locale.get("commons.pages.flexnet_id"),
					 		mRender 		: _contentTabs.commonCellProcessors.fnNetworkAddress,
					 		sDefaultContent : ""
					 	},
					 	{
					 		headerData		: $.sc.locale.get("summary.text.headerTable.Premise_ID"),
					 		mRender 		: _contentTabs.commonCellProcessors.fnPremiseId,
					 		sDefaultContent : ""
					 	},
					 	{
					 		headerData		: $.sc.locale.get("summary.text.headerTable.Address"),
					 		mRender 		: _contentTabs.commonCellProcessors.fnAddress,
					 		sDefaultContent : ""
					 	},
					 	{
					 		headerData	: $.sc.locale.get("summary.text.headerTable.Status"),
					 		mData		: "processItemStatusEnum",
					 		mRender 	: _contentTabs.commonCellProcessors.fnState
					 	},
					 	{
					 		headerData	: $.sc.locale.get("summary.text.headerTable.Error"),
					 		mData		: "message",
					 		mRender 	: pgsi.util.process.fnCreateMessageColumn
					 	}
					];
				},

				get oConfig ()
				{
					return {

						id 				: this.id,
						sAjaxSource 	: _contentTabs.communication.url,
						bPreLoad 		: true,
						bDestroy		: true,
						bParamsOnUrl	: false,
						iDisplayLength 	: 5,
						aLengthMenu		: [5,10,15],
						sTotalResults 	: "none",
						sTotalLabel		: "none",
						bIsDialog		: true,
						bRetrieve   	: true,
						aaData 			: this.oResponse,
						bSort			: false,

						ajax 			:
						{
							sObj				: "processItems",
							oRequest 			: InquiryProcessRequest,
							fnRequest 			: _contentTabs.communication.fnRequest,
							responseInterceptor : function (oResponse)
							{
								oResponse.processItems = oResponse.processes[0].processItems;
								return oResponse;
							}
						},

						aoColumns		: this.aoColumns,

						infoCallback 	: function (object, oSettings, iStart, iEnd, iMax, iTotal, sPre)
						{
							$("#results-available", _this).text(object.aoData.length);
						}
					}
				}
			},

			callBack	: function (oResponse)
			{
				// PROCESS INFORMATIONS
				_contentTabs.communication.fnFillInformations(oResponse);

				// SUCCESS AND FAILS INFORMATIONS
				_fnFillSuccessAndFails(oResponse);

				// TABLE
				if ( !$.sc.isNullOrUndefined(oResponse.processes)
						&& !$.sc.isNullOrUndefined(oResponse.processes[0])
						&& !$.sc.isNullOrUndefined(oResponse.processes[0].processItems)
						&& oResponse.processes[0].processItems.length > 0)
				{
					$("#communication-summary .selected-points", _this).show();

					_contentTabs.communication.table.oResponse = oResponse;

					// If is recent request dialog we need to set the id of the table corresponding to process
					if ( $("#process-" + sId + " #tableCommunicationSummary").length )
					{
						_contentTabs.communication.table.id = "#process-" + sId + " #tableCommunicationSummary";
					}
					$.sc.table.init(_contentTabs.communication.table.oConfig);
				}

				_fnCallBackInit();
			},

			ajaxCall 	: function ()
			{
				$.sc.ajax.post(
				{
					sUrl 		: this.url,
					oRequest	: new InquiryProcessRequest(this.fnRequest(5)),
					fnCallback 	: this.callBack
				});
			},

			init 		: function ()
			{
				this.ajaxCall();

				// Generate CSV file
				pgsi.util.exportcsv.setGenerateCSVEvent(
				{
					url 					: "api/export/generateCSV",
					$element 				: $("#communication-summary .csv"),

					getGenerateRequestCSV	: function()
					{
						var oRequest 					= _oCsvRequest;
						oRequest.processItemStatusEnum	= "FAILED";

						return new CSVFERequest (
						{
							inquiryProcessRequest 	: oRequest,
							dmCsvContentTypeEnum	: "COMMUNICATION_SUMMARY",
							timeZoneId				: pgsi.settings.user.timeZone
						});
					}
				});
			}
		},

		demandRead :
		{
			url 		: "api/summary/fetchDemandReadPingSummary",

			fnRequest	: function (pageSize)
			{
				sId = parseInt(sId, 10);

				if ( !$.isNumeric(sId) )
				{
					sId = null;
				}

				var oProcess = new Process(
				{
					id 			: sId,
					rniEventId	: iRniEventId || null
				});

				return {
					processes 				: [oProcess],
					processId 				: sId,
					pageSize				: pageSize ? pageSize : undefined,
					sortExpressions 		: [
	   				{
	   					direction 	: "Ascending",
	   					field 		: "process_item_id"
	   				}]
				};
			},

			table :
			{
				oResponse 		: null,

				oCellProcessors :
				{
					fnReadValue : function (val, type, full)
					{
						if (type !== "display")
						{
							return val;
						}

						if ( !$.sc.isNullOrUndefined(full.channel)
								&& !$.sc.isNullOrUndefined(full.channel.readValue)
								&& !$.sc.isNullOrUndefined(full.channel.readUnit) )
						{
							return full.channel.readValue + " " + full.channel.readUnit;
						}

						return "";
					},

					fnReadTime : function (val, type, full)
					{
						if (type !== "display")
						{
							return val;
						}

						if ( !$.sc.isNullOrUndefined(full.readingDate) )
						{
							return $.sc.date.format(full.readingDate, pgsi.settings.user.dateFormat.replace("yyyy", "yy") + " h:i z", true);
						}

						return "";
					}
				},

				get aoColumns ()
				{
					return [
					    {
					    	fnCreatedCell	: _contentTabs.commonCellProcessors.fnCreateButton,
					    	sDefaultContent : ""
					    },
						{
					    	headerData		: $.sc.locale.get("summary.text.headerTable.device_id"),
							mData			: "device.deviceId",
							sDefaultContent : ""
						},
						{
							headerData		: $.sc.locale.get("commons.pages.flexnet_id"),
							mRender 		: _contentTabs.commonCellProcessors.fnNetworkAddress,
							sDefaultContent : ""
						},
						{
							headerData		: $.sc.locale.get("summary.text.headerTable.Premise_ID"),
							mRender 		: _contentTabs.commonCellProcessors.fnPremiseId,
							sDefaultContent : ""
						}
					]
				},

				get oConfig ()
				{
					return {
						id 				: "#tableDemandRead",
						sAjaxSource 	: _contentTabs.demandRead.url,
						bPreLoad 		: true,
						bDestroy		: true,
						bParamsOnUrl	: false,
						iDisplayLength 	: 5,
						aLengthMenu		: [5,10,15],
						sTotalResults 	: "none",
						sTotalLabel		: "none",
						bIsDialog		: true,
						bRetrieve   	: true,
						aaData 			: this.oResponse,
						bSort			: false,

						ajax 			:
						{
							sObj				: "processItems",
							oRequest 			: InquiryProcessRequest,
							fnRequest 			: _contentTabs.demandRead.fnRequest,
							responseInterceptor : function (oResponse)
							{
								oResponse.processItems = oResponse.processes[0].processItems;
								return oResponse;
							}
						},

						aoColumns		: this.aoColumns,

						infoCallback 	: function (object, oSettings, iStart, iEnd, iMax, iTotal, sPre)
						{
							$("#demand-read-sumary #results-available, #demand-read-sumary #list-fail strong").text(object.aoData.length);
						},

						drawCallback : function (oSettings)
						{
							if (oSettings._iRecordsDisplay == 1)
							{
								var cell 	= this.find("tbody tr td:first");
								var $Html 	= _contentTabs.commonsTable.fnChildTableButton(
												_contentTabs.demandRead.table.oResponse.processes[0].processItems[0],
												cell, true);

								cell.append($Html);
							}
						}
					};
				},

				get aoChildColumns ()
				{
					return [
				        {
				        	headerData		: $.sc.locale.get("commons.pages.channelId"),
				        	mData			: "channel.channelId"
				        },
				        {
				        	headerData		: $.sc.locale.get("commons.pages.readValue"),
				        	mRender 		: this.oCellProcessors.fnReadValue,
				        	sDefaultContent : ""
				        },
				        {
				        	headerData		: $.sc.locale.get("commons.pages.readTime"),
				        	mData 			: "readingDate",
				        	mRender 		: this.oCellProcessors.fnReadTime
				        }
					]
				}
			},

			callBack	: function (oResponse)
			{
				// TABLE
				if ( !$.sc.isNullOrUndefined(oResponse.processes)
						&& !$.sc.isNullOrUndefined(oResponse.processes[0])
						&& !$.sc.isNullOrUndefined(oResponse.processes[0].processItems)
						&& oResponse.processes[0].processItems.length > 0)
				{
					$("#demand-read-sumary .selected-points, #demand-read-sumary #list-fail").show();

					$("#demand-read-sumary #total-results").text(oResponse.processes[0].processSummary.totalSmartpoints);

					_contentTabs.demandRead.table.oResponse = oResponse;
					$.sc.table.init(_contentTabs.demandRead.table.oConfig);
				}

				_fnCallBackInit();
			},

			ajaxCall 	: function ()
			{
				$.sc.ajax.post(
				{
					sUrl 		: this.url,
					oRequest	: new InquiryProcessRequest(this.fnRequest(5)),
					fnCallback 	: this.callBack
				});
			},

			init 		: function ()
			{
				this.ajaxCall();

				// Generate CSV file
				pgsi.util.exportcsv.setGenerateCSVEvent(
				{
					url 					: "api/export/generateCSV",
					$element 				: $("#demand-read-sumary .csv"),

					getGenerateRequestCSV	: function()
					{
						return new CSVFERequest (
						{
							inquiryProcessRequest 	: _oCsvRequest,
							dmCsvContentTypeEnum	: "DEMAND_READ_SUMMARY",
							timeZoneId				: pgsi.settings.user.timeZone
						});
					}
				});
			}
		},

		radioConfigurationSummary :
		{
			url 		: "api/summary/fetchRadioConfigurationSummary",

			fnRequest	: function (pageSize)
			{
				sId = parseInt(sId, 10);

				if ( !$.isNumeric(sId) )
				{
					sId = null;
				}

				var oProcess = new Process(
				{
					id 			: sId,
					rniEventId	: iRniEventId || null
				});

				return {
					processes 				: [oProcess],
					processId 				: sId,
					pageSize				: pageSize ? pageSize : undefined,
					sortExpressions 		: [
	   				{
	   					direction 	: "Ascending",
	   					field 		: "process_item_id"
	   				}]
				};
			},

			table :
			{
				oResponse 		: null,

				oCellProcessors :
				{
					fnHeader : function (sTitle)
					{
						var sDefaultContent = $.sc.enums.internationalizeByLabel("com.pgsi.dm.common.action.model.OTAConfigurationItemKeyEnum", sTitle.toUpperCase());

						return $("<th>" + sDefaultContent + "</th>");
					}
				},

				fnGetStringMessage : function (sValue, sPropertyValue)
				{
					var sDefaultContent = $.sc.locale.get(sValue.toLowerCase());

					return sDefaultContent.indexOf("[") > -1 ? sPropertyValue : sDefaultContent;
				},

				fnGetColumns : function (oResponse)
				{
					var aColumns =
					[
					    {
					 		headerData 		: $.sc.locale.get("summary.text.headerTable.device_id"),
					 		mData			: "device.deviceId",
					 		sDefaultContent : ""
					 	},
					 	{
					 		headerData 		: $.sc.locale.get("commons.pages.flexnet_id"),
					 		mRender 		: _contentTabs.commonCellProcessors.fnNetworkAddress,
					 		sDefaultContent : ""
					 	},
					 	{
					 		headerData 		: $.sc.locale.get("summary.text.headerTable.Premise_ID"),
					 		mRender 		: _contentTabs.commonCellProcessors.fnPremiseId,
					 		sDefaultContent : ""
					 	}
					];

					var aProperties = oResponse.processes[0].properties;
					var oColumn;
					var oProperty;

					// Create additional columns according the received properties
					for (var i = 0, count = aProperties.length; i < count; i++)
					{
						oProperty = aProperties[i];
						var sValue = oProperty.propertyName + "." + oProperty.propertyValue;

						var sDefaultContent = this.fnGetStringMessage(sValue, oProperty.propertyValue);

						oColumn =
						{
					 		headerData 		: this.oCellProcessors.fnHeader,
					 		order			: oProperty.propertyName,
					 		sDefaultContent : sDefaultContent
						};

						aColumns.push(oColumn);
					}

					return aColumns;
				},

				get oConfig ()
				{
					return {

						id 				: "#tableRadioConfiguration",
						sAjaxSource 	: _contentTabs.radioConfigurationSummary.url,
						bPreLoad 		: true,
						bDestroy		: true,
						bParamsOnUrl	: false,
						iDisplayLength 	: 5,
						aLengthMenu		: [5,10,15],
						sTotalResults 	: "none",
						sTotalLabel		: "none",
						bIsDialog		: true,
						bRetrieve   	: true,
						bCustomize		: true,
						bSort			: false,
						aaData 			: this.oResponse,
						bSort			: false,

						ajax 			:
						{
							sObj				: "processItems",
							oRequest 			: InquiryProcessRequest,
							fnRequest 			: _contentTabs.radioConfigurationSummary.fnRequest,
							responseInterceptor : function (oResponse)
							{
								oResponse.processItems = oResponse.processes[0].processItems;
								return oResponse;
							}
						},

						oSettings 		:
						{
							bFooterVisible 	: false
						},

						aoColumns		: this.fnGetColumns(this.oResponse),

						infoCallback 	: function (object, oSettings, iStart, iEnd, iMax, iTotal, sPre)
						{
							$("#radio-configuration-sumary #results-available, #radio-configuration-sumary #list-fail strong").text(object.aoData.length);
						}
					}
				},
			},

			callBack	: function (oResponse)
			{
				// TABLE
				if ( !$.sc.isNullOrUndefined(oResponse.processes)
						&& !$.sc.isNullOrUndefined(oResponse.processes[0])
						&& !$.sc.isNullOrUndefined(oResponse.processes[0].processItems)
						&& oResponse.processes[0].processItems.length > 0)
				{
					$("#radio-configuration-sumary .selected-points, #radio-configuration-sumary #list-fail").show();

					$("#radio-configuration-sumary #total-results").text(oResponse.processes[0].processSummary.totalSmartpoints);

					if ( !$.sc.isNullOrUndefined(oResponse.processes[0].action)
							&& !$.sc.isNullOrUndefined(oResponse.processes[0].action.actionType)
							&& !$.sc.isNullOrUndefined(oResponse.processes[0].action.actionType.actionTypeEnumNameValue) )
					{
						var sTitle = $.sc.locale.get("smartpointdetail.dialog.radioConfiguration") + " - " + $.sc.locale.get(oResponse.processes[0].action.actionType.actionTypeEnumNameValue.toLowerCase());
						$("#radioConfigurationSummary").find("a").empty();
						$("#radioConfigurationSummary").find("a").html(sTitle);
					}
					_contentTabs.radioConfigurationSummary.table.oResponse = oResponse;
					$.sc.table.init(_contentTabs.radioConfigurationSummary.table.oConfig);
				}

				_fnCallBackInit();
			},

			ajaxCall 	: function ()
			{
				$.sc.ajax.post(
				{
					sUrl 		: this.url,
					oRequest	: new InquiryProcessRequest(this.fnRequest(5)),
					fnCallback 	: this.callBack
				});
			},

			init : function ()
			{
				this.ajaxCall();

				// Generate CSV file
				pgsi.util.exportcsv.setGenerateCSVEvent(
				{
					url 					: "api/export/generateCSV",
					$element 				: $("#radio-configuration-sumary .csv"),

					getGenerateRequestCSV	: function()
					{
						return new CSVFERequest (
						{
							inquiryProcessRequest 	: _oCsvRequest,
							dmCsvContentTypeEnum	: "OTA_SUMMARY",
							timeZoneId				: pgsi.settings.user.timeZone
						});
					}
				});
			}
		},

		importHanDevices :
		{
			url 				: "api/summary/fetchImportHanDeviceSummary",

			fnRequest	: function (pageSize)
			{
				sId = parseInt(sId, 10);

				if ( !$.isNumeric(sId) )
				{
					sId = null;
				}

				var oProcess = new Process(
				{
					id 			: sId,
					rniEventId	: iRniEventId || null
				});

				return {
					processItemStatusEnum	: "FAILED",
					processes 				: [oProcess],
					processId 				: sId,
					pageSize				: pageSize ? pageSize : undefined,
					sortExpressions 		: [
	   				{
	   					direction 	: "Ascending",
	   					field 		: "process_item_id"
	   				}]
				};
			},

			fnFillInformations : function (oResponse)
			{
				var $Infotable 		= $("#informationTableImportHan", _this);
				var	sDevices 		= " " + $.sc.locale.get("commons.pages.smartPoints");
				var oProcess 		= oResponse.processes[0];
				var oProcessSummary = oProcess.processSummary;
				var iTotal 			= oProcessSummary.totalSmartpoints;
				var iConnected		= oProcessSummary.joinSmartpoints;
				var iDisconnected	= oProcessSummary.unJoinSmartpoints;
				var oProcessItem;
				var iTotal;

				// CONNECT COLUMN INFORMATION
				_fnCreateInformation($Infotable.find("td:eq(0)"), _fnGetPercentage(iTotal, iConnected) + "%", iConnected + sDevices);

				// DISCONNECT COLUMN INFORMATION
				_fnCreateInformation($Infotable.find("td:eq(1)"), _fnGetPercentage(iTotal, iDisconnected) + "%", iDisconnected + sDevices);

				// Create list of fails
				_fnCreateListFails($Infotable.find($("#failed-devices-group-importHan")), oProcessSummary, oProcess.processStatusEnum == "COMPLETED");

				// COUNT PROCESS ITEMS
				$("#total-results").text(oResponse.resultsSetInfo.totalRowsAvailable);
			},

			table :
			{
				oResponse : null,

				get aoColumns ()
				{
					return [
					 	{
					 		headerData		: $.sc.locale.get("summary.text.headerTable.device_id"),
					 		mData			: "device.deviceId",
					 		sDefaultContent : ""
					 	},
					 	{
					 		headerData		: $.sc.locale.get("commons.pages.flexnet_id"),
					 		mRender 		: _contentTabs.commonCellProcessors.fnNetworkAddress,
					 		sDefaultContent : ""
					 	},
					 	{
					 		headerData		: $.sc.locale.get("summary.text.headerTable.Premise_ID"),
					 		mRender 		: _contentTabs.commonCellProcessors.fnPremiseId,
					 		sDefaultContent : ""
					 	},
					 	{
					 		headerData		: $.sc.locale.get("summary.text.headerTable.Address"),
					 		mRender 		: _contentTabs.commonCellProcessors.fnAddress,
					 		sDefaultContent : ""
					 	},
					 	{
					 		headerData	: $.sc.locale.get("summary.text.headerTable.Status"),
					 		mData		: "processItemStatusEnum",
					 		mRender 	: _contentTabs.commonCellProcessors.fnState
					 	},
					 	{
					 		headerData	: $.sc.locale.get("summary.text.headerTable.Error"),
					 		mData		: "message",
					 		mRender 	: pgsi.util.process.fnCreateMessageColumn
					 	}
					];
				},

				get oConfig ()
				{
					return {

						id 				: "#tableImportHanDevice",
						sAjaxSource 	: _contentTabs.importHanDevices.url,
						bPreLoad 		: true,
						bDestroy		: true,
						bParamsOnUrl	: false,
						iDisplayLength 	: 5,
						aLengthMenu		: [5,10,15],
						sTotalResults 	: "none",
						sTotalLabel		: "none",
						bIsDialog		: true,
						bRetrieve   	: true,
						aaData 			: this.oResponse,
						bSort			: false,

						ajax 			:
						{
							sObj				: "processItems",
							oRequest 			: InquiryProcessRequest,
							fnRequest 			: _contentTabs.importHanDevices.fnRequest,
							responseInterceptor : function (oResponse)
							{
								oResponse.processItems = oResponse.processes[0].processItems;
								return oResponse;
							}
						},

						aoColumns		: this.aoColumns,

						infoCallback 	: function (object, oSettings, iStart, iEnd, iMax, iTotal, sPre)
						{
							$("#import-han-device-summary #results-available, #import-han-device-summary #list-fail strong").text(object.aoData.length);
						}
					}
				}
			},

			callBack	: function (oResponse)
			{
				// PROCESS INFORMATIONS
				_contentTabs.importHanDevices.fnFillInformations(oResponse);

				// TABLE
				if ( !$.sc.isNullOrUndefined(oResponse.processes)
						&& !$.sc.isNullOrUndefined(oResponse.processes[0])
						&& !$.sc.isNullOrUndefined(oResponse.processes[0].processItems)
						&& oResponse.processes[0].processItems.length > 0)
				{
					$("#import-han-device-summary .selected-points, #import-han-device-summary #list-fail").show();

					_contentTabs.importHanDevices.table.oResponse = oResponse;
					$.sc.table.init(_contentTabs.importHanDevices.table.oConfig);
				}

				_fnCallBackInit();
			},

			ajaxCall : function ()
			{
				$.sc.ajax.post(
				{
					sUrl 		: this.url,
					oRequest	: new InquiryProcessRequest(this.fnRequest(5)),
					fnCallback 	: this.callBack
				});
			},

			init : function ()
			{
				this.ajaxCall();

				// Initialize retry button
				$("#import-han-device-summary #retry-failed").button();

				// Retry Connect Failed
				$("#import-han-device-summary").on("click", "#retry-failed", function(e)
				{
					e.preventDefault();

					var oRetryRequest = new ActionsModel();

					oRetryRequest["retryImportHanDeviceAction"] =
					{
						rniEventId 			: iRniEventId,
						deviceCategoryEnum 	: sDeviceCategory,
						onDemand			: true,
						isMonitored			: true,
						actionTime 			: $.sc.date.epochTime.currentUTC()
					};

					$.sc.ajax.post(
					{
						sUrl 			: "api/deviceop/apply",
						oRequest		: oRetryRequest,
						sSuccessMessage : $.sc.locale.get("summary.text.retrySuccess"),
						fnCallback 		: function (oResponse)
						{
							_contentTabs.importHanDevices.ajaxCall();
						}
					});
				});

				// Generate CSV file
				pgsi.util.exportcsv.setGenerateCSVEvent(
				{
					url 					: "api/export/generateCSV",
					$element 				: $("#import-han-device-summary .csv"),

					getGenerateRequestCSV	: function()
					{
						var oRequest 					= _oCsvRequest;
						oRequest.processItemStatusEnum	= "FAILED";

						return new CSVFERequest (
						{
							inquiryProcessRequest 	: oRequest,
							dmCsvContentTypeEnum	: "IMPORT_HAN_SUMMARY",
							timeZoneId				: pgsi.settings.user.timeZone
						});
					}
				});
			}
		},

		demandResponse :
		{
			aDevices : [],

			url 	: "api/summary/fetchDemandResponseSummary",

			getDemandResponseUrl : "api/deviceop/apply",

			getDemandResponseRequest : new Action({
				isMonitored			: true,
				onDemand			: true,
				actionTime			: $.sc.date.epochTime.currentUTC(),
				deviceCategoryEnum 	: sDeviceCategory
			}),

			fnRequest	: function (pageSize)
			{
				sId = parseInt(sId, 10);

				if ( !$.isNumeric(sId) )
				{
					sId = null;
				}

				var oProcess = new Process(
				{
					id 			: sId,
					rniEventId	: iRniEventId || null
				});

				return {
					processes 				: [oProcess],
					processId 				: sId,
					pageSize				: pageSize ? pageSize : undefined,
					sortExpressions 		: [
	   				{
	   					direction 	: "Ascending",
	   					field 		: "process_item_id"
	   				}]
				};
			},

			table :
			{
				oResponse 		: null,

				oCellProcessors :
				{
					fnParent : function (val, type, full)
					{
						if (type !== "display")
						{
							return val;
						}

						return full.device.electricMeterFlexNetId;
					},

					fnStarted : function (val, type, full)
					{
						if (type !== "display")
						{
							return val;
						}

						return val == "Started" ? $.sc.locale.get("commons.pages.yes") : $.sc.locale.get("commons.pages.no");
					},
					fnFullParticipation : function (val, type, full)
					{
						if (type !== "display")
						{
							return val;
						}

						return val == "FullParticipation" ? $.sc.locale.get("commons.pages.yes") : $.sc.locale.get("commons.pages.no");
					},
					fnPartialParticipation : function (val, type, full)
					{
						if (type !== "display")
						{
							return val;
						}

						return val == "PartialParticipation" ? $.sc.locale.get("commons.pages.yes") : $.sc.locale.get("commons.pages.no");
					},
					fnOptOut : function (val, type, full)
					{
						if (type !== "display")
						{
							return val;
						}

						return val == "OptOut" ? $.sc.locale.get("commons.pages.yes") : $.sc.locale.get("commons.pages.no");
					},
					fnOperatorOptOut : function (val, type, full)
					{
						if (type !== "display")
						{
							return val;
						}

						return val == "Operator_OptOut" ? $.sc.locale.get("commons.pages.yes") : $.sc.locale.get("commons.pages.no");
					},

					fnNetworkStatus : function (val, type, full)
					{
						if (type !== "display")
						{
							return val;
						}

						sRemoteConnectStatus = pgsi.util.page.fnGetRemoteDisconnectState(full.device.remoteConnectStatusEnum);
						sRemoteConnectReason = pgsi.util.page.fnGetRemoteDisconnectReason(full.device.remoteConnectReasonEnum);

						if ( sRemoteConnectReason == $.sc.locale.get("commons.pages.doubleHyphen") )
						{
							return sRemoteConnectStatus;
						}

						return sRemoteConnectStatus + " - " + sRemoteConnectReason;
					}
				},

				get aoColumns ()
				{
					return [
						{
							headerData		: $.sc.locale.get("summary.text.headerTable.device_id"),
							mData			: "device.deviceId",
							sDefaultContent : ""
						},
						{
							headerData		: $.sc.locale.get("commons.pages.flexnet_id"),
							mRender 		: _contentTabs.commonCellProcessors.fnNetworkAddress,
							sDefaultContent : ""
						},
						{
							headerData		: $.sc.locale.get("summary.text.headerTable.ParentDeviceID"),
							mRender 		: this.oCellProcessors.fnParent,
							sDefaultContent : ""
						},
						{
							headerData		: $.sc.locale.get("summary.text.headerTable.Premise_ID"),
							mRender 		: _contentTabs.commonCellProcessors.fnPremiseId,
							sDefaultContent : ""
						},
						{
							headerData		: $.sc.locale.get("summary.text.headerTable.NetworkStatus"),
							mRender 		: this.oCellProcessors.fnNetworkStatus,
							sDefaultContent : ""
						},
						{
							headerData		: $.sc.locale.get("summary.text.headerTable.started"),
							mData			: "participation",
							mRender 		: this.oCellProcessors.fnStarted,
							sDefaultContent : ""
						},
						{
							headerData		: $.sc.locale.get("summary.text.headerTable.FullParticipation"),
							mData			: "participation",
							mRender 		: this.oCellProcessors.fnFullParticipation,
							sDefaultContent : ""
						},
						{
							headerData		: $.sc.locale.get("summary.text.headerTable.PartialParticipation"),
							mData			: "participation",
							mRender 		: this.oCellProcessors.fnPartialParticipation,
							sDefaultContent : ""
						},
						{
							headerData		: $.sc.locale.get("summary.text.headerTable.OptOut"),
							mData			: "participation",
							mRender 		: this.oCellProcessors.fnOptOut,
							sDefaultContent : ""
						},
						{
							headerData		: $.sc.locale.get("summary.text.headerTable.OperatorOptOut"),
							mData			: "participation",
							mRender 		: this.oCellProcessors.fnOperatorOptOut,
							sDefaultContent : ""
						},
					]
				},
				get oConfig ()
				{
					return {
						id 				: "#tableDemandResponse",
						sAjaxSource 	: _contentTabs.demandResponse.url,
						bPreLoad 		: true,
						bDestroy		: true,
						bParamsOnUrl	: false,
						iDisplayLength 	: 5,
						aLengthMenu		: [5,10,15],
						sTotalResults 	: "none",
						sTotalLabel		: "none",
						bIsDialog		: true,
						bRetrieve   	: true,
						aaData 			: this.oResponse,
						bSort			: false,

						ajax 			:
						{
							sObj				: "processItems",
							oRequest 			: InquiryProcessRequest,
							fnRequest 			: _contentTabs.demandResponse.fnRequest,
							responseInterceptor : function (oResponse)
							{
								oResponse.processItems = oResponse.processes[0].processItems;
								return oResponse;
							}
						},

						aoColumns		: this.aoColumns,

						infoCallback 	: function (object, oSettings, iStart, iEnd, iMax, iTotal, sPre)
						{
							$("#demand-response-sumary #results-available, #demand-response-sumary #list-fail strong").text(object.aoData.length);
						}
					};
				},
			},

			fnGetParticipation : function (aProcessItems)
			{
				var oParticipations =
				{
					FullParticipation 				: 0,
					PartialParticipation 			: 0,
					Operator_OptOut 				: 0,
					OptOut 							: 0,
					PartialParticipationAndOptOut 	: 0,
					COMPLETED 						: 0,
					Started 						: 0,
					DEVICES 						: []
				}

				var i 	= 0;
				var max = aProcessItems.length;
				var oProcessItem;
				var sParticipation;

				for (; i < max; i = i + 1)
				{
					oProcessItem = aProcessItems[i];
					sParticipation = oProcessItem.participation;

					if (oProcessItem.processItemStatusEnum != "COMPLETED")
					{
						oParticipations["DEVICES"].push(oProcessItem.device);
						continue;
					}

					oParticipations[sParticipation] = oParticipations[sParticipation] + 1;
				}

				return oParticipations;
			},

			callBack	: function (oResponse)
			{
				var $oInfoTable 			= $("#summaryDemandResponse");
				var	oProcess 				= oResponse.processes[0];
				var	aProcessItems 			= oProcess.processItems || [];
				var oAction					= oProcess.action;
				var	aStartTime 				= $.sc.date.format(oProcess.startTime, pgsi.settings.user.dateFormat.replace("yyyy", "yy") + " h:i A", true).split(" ");
				var	oDemandResponseDuration = oAction.duration;
				var	iReceived 				= aProcessItems.length;
				var oParticipations			= _contentTabs.demandResponse.fnGetParticipation(aProcessItems);
				var	iStarted 				= oParticipations.Started;
				var	iFullParticipation 		= oParticipations.FullParticipation;
				var	iOperatorOptOut			= oParticipations.Operator_OptOut;
				var	iPartialParticipation	= oParticipations.PartialParticipation;
				var	iOptOut 				= oParticipations.OptOut;
				var	aDevices 				= oParticipations.DEVICES;
				var	iUncompleted 			= aDevices.length;
				var oActionCancel;

				// Create Columns Information
				// Start
				_fnCreateInformation($oInfoTable.find("td:eq(0)"), aStartTime[1] + "<small>" + aStartTime[2] + "</small>",aStartTime[0]);

				// Duration
				if (oDemandResponseDuration)
				{
					_fnCreateInformation($oInfoTable.find("td:eq(1)"), (oDemandResponseDuration || ""), $.sc.locale.get("commons.pages.Minutes"));
				}

				// Participation
				_fnCreateInformation($oInfoTable.find("td:eq(2)"),
						_fnGetPercentage(iReceived, iFullParticipation) + "%",
						iFullParticipation + " " + $.sc.locale.get("commons.pages.of") + " " + iReceived);

				// Received
				_fnCreateInformation($oInfoTable.find("td:eq(3)"), iReceived - iOperatorOptOut, "");

				// Started
				_fnCreateInformation($oInfoTable.find("td:eq(4)"), iStarted, "");

				// Full Participation
				_fnCreateInformation($oInfoTable.find("td:eq(5)"), iFullParticipation, "");

				// Partial Participation
				_fnCreateInformation($oInfoTable.find("td:eq(6)"), iPartialParticipation, "");

				// Opt-out
				_fnCreateInformation($oInfoTable.find("td:eq(7)"), iOptOut, "");

				// Opt-out
				_fnCreateInformation($oInfoTable.find("td:eq(8)"), iOperatorOptOut, "");

				if (oProcess.processStatusEnum == "COMMAND_SENT" || oProcess.processStatusEnum == "STARTED")
				{
					oActionCancel = $("<a class='button' href='#'>" + $.sc.locale.get("commons.pages.cancel") + "</a>", $oInfoTable).button().click(function(e)
					{
						e.preventDefault();

						var sTableId = "history-table";

						if ( $.address.path().contains("history") )
						{
							sTableId = "device-history-table";
						}

						$.sc.launchActionDialog("cancel", pgsi.util.process.actions.cancelDialog(oProcess, sTableId, true));
					});

					// Cancel-event
					_fnCreateInformation($oInfoTable.find("td:eq(9)"), oActionCancel, "");
				}
				else
				{
					$oInfoTable.find("td:eq(9)").remove();
				}

				// Get Demand Response Event Status
				if (iUncompleted > 0)
				{
					$(".ui-state-error").removeClass("hide").find("strong").text(iUncompleted);
				}

				// Create Demand Response Details
				_contentTabs.demandResponse.createDemandResponseDetails(oProcess.properties, oAction);

				// TABLE
				if ( !$.sc.isNullOrUndefined(oResponse.processes)
						&& !$.sc.isNullOrUndefined(oResponse.processes[0])
						&& !$.sc.isNullOrUndefined(oResponse.processes[0].processItems)
						&& oResponse.processes[0].processItems.length > 0)
				{
					$("#demand-response-sumary .selected-points").show();
					$("#demand-response-sumary #total-results").text(oResponse.processes[0].processItems.length);

					_contentTabs.demandResponse.table.oResponse = oResponse;
					$.sc.table.init(_contentTabs.demandResponse.table.oConfig);
				}

				var iProcessItens = aProcessItems.length;
				var oProcessItem;

				for (var i = 0; i < iProcessItens; i++)
				{
					oProcessItem = aProcessItems[i];

					_contentTabs.demandResponse.aDevices.push(oProcessItem.device);
				}

				_fnCallBackInit();
			},

			createDemandResponseDetails : function (properties, action)
			{
				var aDetails;
				var	sEnrollmentCode 	= !$.sc.isNullOrUndefined(action.enrollmentCode) 	? action.enrollmentCode 		: "-";
				var	sCriticalityLevel 	= !$.sc.isNullOrUndefined(action.criticalityLevel) 	? action.criticalityLevel 		: "-";
				var	sDutyCycleRate 		= !$.sc.isNullOrUndefined(action.dutyCycleRate) 	? action.dutyCycleRate + "%" 	: "-";
				var	sLoadAdjustment 	= !$.sc.isNullOrUndefined(action.loadAdjustment) 	? action.loadAdjustment + "%" 	: "-";
				var sRandomizeStart		= !$.sc.isNullOrUndefined(action.randomizeStart) 	? action.randomizeStart 		: "-";
				var sRandomizeEnd		= !$.sc.isNullOrUndefined(action.randomizeEnd) 		? action.randomizeEnd 			: "-";
				var	aOffset 			= [];
				var	sOffset 			= "-";
				var	sHeating			= "";
				var	sCooling			= "";
				var	sRandomize			= "-";
				var	sTemperatureFormat	= "&#176;";
				var	sTemperatureType	= pgsi.settings.user.temperature;
				var	sDeviceClasses 		= "-";

				if (sTemperatureType)
				{
					if (sTemperatureType == "CELSIUS")
					{
						sTemperatureFormat = "C" + sTemperatureFormat;
					}
					else
					{
						sTemperatureFormat = "F" + sTemperatureFormat;
					}

					if ( !$.sc.isNullOrUndefined(action.heating) )
					{
						sHeating = action.heating;

						if ( sTemperatureType.toLowerCase() == $.sc.locale.get("systemsettings.page.temperature.fahrenheit").toLowerCase() )
						{
							sHeating = $.temperature.convertTemperature(action.heating, "sf");
						}

						aOffset.push($.sc.locale.get("systemintelligence.dialogDemandResponse.heating") + " " + sHeating + " " + $.sc.locale.get("systemintelligence.dialogDemandResponse.degrees") + " " + sTemperatureFormat);
					}

					if ( !$.sc.isNullOrUndefined(action.cooling) )
					{
						sCooling = action.cooling;

						if ( sTemperatureType.toLowerCase() == $.sc.locale.get("systemsettings.page.temperature.fahrenheit").toLowerCase() )
						{
							sCooling = $.temperature.convertTemperature(action.cooling, "sf");
						}

						aOffset.push($.sc.locale.get("systemintelligence.dialogDemandResponse.cooling") + " " + sCooling + " " + $.sc.locale.get("systemintelligence.dialogDemandResponse.degrees") + " " + sTemperatureFormat);
					}

					if (aOffset.length)
					{
						sOffset = aOffset.join(" | ");
					}
				}

				if (action && action.deviceClasses)
				{
					var nDeviceClassesLength 	= action.deviceClasses.length;
					var aDeviceClasses 			= [];

					for (var i = 0; i < nDeviceClassesLength; i++)
					{
						aDeviceClasses.push($.sc.locale.get("systemintelligence.scheduledCreateEvent." + action.deviceClasses[i]));
					}

					if (aDeviceClasses.length)
					{
						sDeviceClasses = aDeviceClasses.join(", ");
					}
				}

				// Randomize
				if (sRandomizeStart == true)
				{
					sRandomize = $.sc.locale.get("systemintelligence.page.event.hanStart") + " ";
				}

				if (sRandomizeEnd == true)
				{
					if (sRandomizeStart == true)
					{
						sRandomize = sRandomize + " | ";
					}
					else
					{
						sRandomize = "";
					}

					sRandomize = sRandomize + $.sc.locale.get("systemintelligence.page.event.hanEnd");
				}

				// Add Data on DOM
				aDetails = [sEnrollmentCode, sOffset, sDutyCycleRate, sLoadAdjustment, sCriticalityLevel, sRandomize, sDeviceClasses];

				$("#demand-response-details dd").each(function (i, item)
				{
					$(item).html(aDetails[i]);
				});
			},

			ajaxCall 	: function ()
			{
				$.sc.ajax.post(
				{
					sUrl 		: this.url,
					oRequest	: new InquiryProcessRequest(this.fnRequest(5)),
					fnCallback 	: this.callBack
				});
			},

			init : function ()
			{
				this.ajaxCall();

				// Generate CSV file
				pgsi.util.exportcsv.setGenerateCSVEvent(
				{
					url 					: "api/export/generateCSV",
					$element 				: $("#demand-response-sumary .csv"),

					getGenerateRequestCSV	: function()
					{
						return new CSVFERequest (
						{
							inquiryProcessRequest 	: _oCsvRequest,
							dmCsvContentTypeEnum	: "DEMAND_RESPONSE_SUMMARY",
							timeZoneId				: pgsi.settings.user.timeZone
						});
					}
				});

				// Toggle demand response details
				$('.spindown', "#summary-tabs").click(function(e)
				{
					e.preventDefault();

					// Change triangle icon
					$(this).find(".ui-icon").toggle();

					// Open Detail Block
					$(".spindown-child").toggle("blind", null, 500);
				});

				// Get Demand Response Event Status
				$(".button:eq(0)", "#summary-tabs").button().click(function(e)
				{
					e.preventDefault();

					// Create Request
					var oRequest = new ActionsModel();
					var iDevices = _contentTabs.demandResponse.aDevices.length;
					var oDevice;

					oRequest["getDemandResponseEventStatusAction"] 	= _contentTabs.demandResponse.getDemandResponseRequest;
					oRequest.deviceSearch 							= null;
					oRequest.selectionPaginationIds 				= [];

					for (var i = 0; i < iDevices; i++)
					{
						oDevice = _contentTabs.demandResponse.aDevices[i];

						oRequest.selectionPaginationIds.push(oDevice.communicationDeviceAsRadio.flexNetId);
					}

					$.sc.ajax.post(
					{
						sUrl			: _contentTabs.demandResponse.getDemandResponseUrl,
						oRequest		: oRequest,
						sSuccessMessage : $.sc.locale.get("commons.pages.getDemandResponseEventStatusSucess")
					});
				});
			}
		},

		relayConfiguration :
		{
			url 		: "api/summary/fetchRelaysByProcessId",

			fnRequest	: function (pageSize)
			{
				sId = parseInt(sId, 10);

				if ( !$.isNumeric(sId) )
				{
					sId = null;
				}

				var oProcess = new Process(
				{
					id 			: sId,
					rniEventId	: iRniEventId || null
				});

				return {
					processes 				: [oProcess],
					processId 				: sId,
					pageSize				: pageSize ? pageSize : undefined,
					sortExpressions 		: [
	   				{
	   					direction 	: "Ascending",
	   					field 		: "process_item_id"
	   				}]
				};
			},

			table :
			{
				oResponse : null,

				get aoChildColumns ()
				{
					return [
				        {
				        	headerData		: "Relay",
				        	mData			: "relay",
				        	sDefaultContent : "--"
				        },
				        {
				        	headerData		: "Device Class",
				        	mData			: "deviceClass",
				        	mRender 		: this.oCellProcessors.fnDeviceClass,
				        },
				        {
				        	headerData		: "Enrollment Group",
				        	mData 			: "enrollmentCode",
				        	sDefaultContent : "--"
				        },
				        {
				        	headerData		: "Start Randomization",
				        	mData 			: "startMinutes",
				        	mRender 		: this.oCellProcessors.fnRandomization
				        },
				        {
				        	headerData		: "Duration Randomization",
				        	mData 			: "endMinutes",
				        	mRender 		: this.oCellProcessors.fnRandomization
				        },
				        {
				        	headerData		: "Tamper Detect",
				        	mData 			: "tamperDetectTimer",
				        	mRender 		: this.oCellProcessors.fnTamperDetect
				        }
					]
				},

				oCellProcessors :
				{
					fnDeviceClass : function (val, type, full)
					{
						if (type !== "display")
						{
							return val;
						}

						return $.sc.enums.internationalizeByLabel("com.pgsi.dm.elec.device.model.DeviceClassEnum", val);
					},

					fnRandomization : function (val, type, full)
					{
						if (type !== "display")
						{
							return val;
						}

						if ( !$.sc.isNullOrUndefined(val) )
						{
							return val + "min";
						}

						return "--";
					},

					fnTamperDetect : function (val, type, full)
					{
						if (type !== "display")
						{
							return val;
						}

						if ( !$.sc.isNullOrUndefined(val) )
						{
							return $.sc.date.msToHumanReadable(val * 60000).toLowerCase();
						}

						return "--";
					}
				},

				get aoColumns ()
				{
					return [
					    {
					    	fnCreatedCell	: _contentTabs.commonCellProcessors.fnCreateButton,
					    	sDefaultContent : ""
					    },
						{
					    	headerData		: $.sc.locale.get("summary.text.headerTable.device_id"),
							mData			: "device.deviceId",
							sDefaultContent : ""
						},
						{
							headerData		: $.sc.locale.get("commons.pages.flexnet_id"),
							mRender 		: _contentTabs.commonCellProcessors.fnNetworkAddress,
							sDefaultContent : ""
						},
						{
							headerData		: $.sc.locale.get("summary.text.headerTable.Premise_ID"),
							mRender 		: _contentTabs.commonCellProcessors.fnPremiseId,
							sDefaultContent : ""
						}
					]
				},

				get oConfig ()
				{
					return {
						id 				: "#tableRelayConfiguration",
						sAjaxSource 	: _contentTabs.relayConfiguration.url,
						bPreLoad 		: true,
						bDestroy		: true,
						bParamsOnUrl	: false,
						iDisplayLength 	: 5,
						aLengthMenu		: [5,10,15],
						sTotalResults 	: "none",
						sTotalLabel		: "none",
						bIsDialog		: true,
						bRetrieve   	: true,
						aaData 			: this.oResponse,
						bSort			: false,

						ajax 			:
						{
							sObj				: "processItems",
							oRequest 			: InquiryProcessRequest,
							fnRequest 			: _contentTabs.relayConfiguration.fnRequest,
							responseInterceptor : function (oResponse)
							{
								oResponse.processItems = oResponse.processes[0].processItems;
								return oResponse;
							}
						},

						aoColumns		: this.aoColumns,

						infoCallback 	: function (object, oSettings, iStart, iEnd, iMax, iTotal, sPre)
						{
							$("#relay-configuration-summary #results-available, relay-configuration-summary #list-fail strong").text(object.aoData.length);
						},

						drawCallback : function (oSettings)
						{
							if (oSettings._iRecordsDisplay == 1)
							{
								var cell 	= this.find("tbody tr td:first");
								var $Html 	= _contentTabs.commonsTable.fnChildTableButton(
												_contentTabs.relayConfiguration.table.oResponse.processes[0].processItems[0],
												cell, true);

								cell.append($Html);
							}
						}
					};
				},
			},

			callBack	: function (oResponse)
			{
				// TABLE
				if ( !$.sc.isNullOrUndefined(oResponse.processes)
						&& !$.sc.isNullOrUndefined(oResponse.processes[0])
						&& !$.sc.isNullOrUndefined(oResponse.processes[0].processItems)
						&& oResponse.processes[0].processItems.length > 0)
				{
					$("#relay-configuration-summary .selected-points, relay-configuration-summary #list-fail").show();

					$("#relay-configuration-summary #total-results").text(oResponse.processes[0].processSummary.totalSmartpoints);

					_contentTabs.relayConfiguration.table.oResponse = oResponse;
					$.sc.table.init(_contentTabs.relayConfiguration.table.oConfig);
				}

				_fnCallBackInit();
			},

			ajaxCall 	: function ()
			{
				$.sc.ajax.post(
				{
					sUrl 		: this.url,
					oRequest	: new InquiryProcessRequest(this.fnRequest(5)),
					fnCallback 	: this.callBack
				});
			},

			init : function ()
			{
				this.ajaxCall();

				// Generate CSV file
				pgsi.util.exportcsv.setGenerateCSVEvent(
				{
					url 					: "api/export/generateCSV",
					$element 				: $("#relay-configuration-summary .csv"),

					getGenerateRequestCSV	: function()
					{
						return new CSVFERequest (
						{
							inquiryProcessRequest 	: _oCsvRequest,
							dmCsvContentTypeEnum	: "DEMAND_RESPONSE_SETUP_SUMMARY",
							timeZoneId				: pgsi.settings.user.timeZone
						});
					}
				});
			}
		}
	};

	/**
	 *  Load content tab
	 */
	var _loadTab = function (event, ui)
	{
		$.sc.progressBar.start();

		var tab = $("#summary-tabs ul li.ui-state-active").attr("id");

		// Start tab content
		_contentTabs[tab].init();

		$(ui.panel).removeClass($(ui.panel).attr("class")).addClass("summary communication-summary");
	};

	/**
	 *	Fetches the process summary data used on the dialog.
	 */
	var fnFetchProcessData = function ()
	{
		// Set the general object when the dialog have only communication tab
		if ( $.sc.isNullOrUndefined(_oAction) )
		{
			// If the action is related Radio Configuration
			if ( !$.sc.isNullOrUndefined(sActionEnum)
					&& $.inArray(sActionEnum, _aRadioConfiguration) != -1)
			{
				_oAction = _oActions.RADIO_CONFIGURATION;
			}
			// If the action is related Get Data From Device
			else if ( !$.sc.isNullOrUndefined(sActionEnum)
						&& $.inArray(sActionEnum, _aGetDataFromDevice) != -1)
			{
				_oAction = _oActions.GET_DATA_FROM_DEVICE;
			}
			else
			{
				_oAction = _oActions["general"];
			}
		}

		if (bWithoutTabs)
		{
			$.sc.progressBar.start();

			$.sc.ajax.get(
			{
				sUrl		: "summary/communication?" + new Date().getTime(),
				bCache		: false,
				fnCallback	: function(data)
				{
					$("#summary-container", _this).removeClass("hide").html(data);
					_contentTabs.communication.init();
				}
			});
		}
		else
		{
			// Show only necessary tabs
			_fnShowTabs();

			// Start tabs
			$.ajaxSetup({ cache : false });

			var oTabs = $("#summary-tabs", _this);

			oTabs.removeClass("hide");

			// Initialize tabs
			oTabs.tabs(
			{
				active 	: _oAction.iStartTab,
				load 	: _loadTab
			});

			// Refresh button event
			oTabs.on("click", ".refresh", function (e)
			{
				e.preventDefault();

				// Get Selected Tab
				var $tab 		= $("#summary-tabs");
				var	sTab 		= $tab.find("li").eq($tab.tabs("option", "active")).attr("id");
				var sTableId 	= _contentTabs[sTab].table.oConfig.id;

				if ( $.fn.DataTable.isDataTable(sTableId) )
				{
				  $(sTableId).dataTable().api().destroy();
				}

				// Refresh selected tab
				_contentTabs[sTab].ajaxCall();
			});
		}
	};

	/**
	 *	Initialize the summary dialog.
	 */
	var _init = function()
	{
		// Verify if the HTML is stored
		if ( !$.sc.isNullOrUndefined(_summaryHtml) )
		{
			fnFetchProcessData();
			return;
		}

		$.sc.ajax.get(
		{
			sUrl		 : "summary",
			bBlockScreen : true,
			fnCallback	 : function(data)
			{
				_summaryHtml = data;

				_this.html(_summaryHtml);

				fnFetchProcessData();
			}
		});
	};

	_init();
};