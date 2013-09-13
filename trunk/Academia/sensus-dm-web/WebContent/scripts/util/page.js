/**
 * @fileoverview Defines common page-related functionality. Many of those
 *               functions require specific HTML structures, ids or classes to
 *               work correctly.
 */
sensus.util.page = {

		/**
		 * Show a message, either as confirmation or error. This is intended to work
		 * with the general messaging area at the top of the main content pages but
		 * will work with other targets as well.
		 *
		 * @param the
		 *            id of the target element to display the message into
		 * @param the
		 *            message
		 * @param the
		 *            message type, either "confirm" or "error".
		 */
		showMessage : function (target, message, type) {
			/** animate to scroll to top **/
			$('html, body').animate({ scrollTop: 0 }, 'slow');
			/** create and show message **/
			$("#" + target).fadeIn(200, function () {
				var targetEl = $("#" + target);
				$("#" + target + " .message").html(message);
				if ("confirm" == type) {
					targetEl.removeClass("ui-state-error");
					targetEl.addClass("ui-state-highlight");
				} else {
					targetEl.removeClass("ui-state-highlight");
					targetEl.addClass("ui-state-error");

				}
				$("#" + target).show();
			});
		},

		fnCreateDeviceObject : function (oProp) {

			var devices = [];
			var deviceTypeEnum = $.address.parameter("device_type") || $.address.parameter("deviceType")
			var oPropLength = oProp.length;

			if (deviceTypeEnum) {
				deviceTypeEnum = deviceTypeEnum.replace("|", '');
			}

			for (var i = 0; i < oPropLength; i ++) {
				var radio = new Radio({flexNetId : oProp[i].id, customerId : oProp[i].customerId});
				var device = new Device({radio : radio, deviceType : deviceTypeEnum});
				devices.push(device);
			}

			return devices;

		},

		/** TODO - REMOVE THIS FUNCTION. */
		createDevice : function (listFlexNet) {

			var devices = [];

			if ((listFlexNet != null) || (listFlexNet != undefined) || (listFlexNet != "") || (listFlexNet.length > 0)) {

				for (x=0; x < listFlexNet.length; x++) {

					flexNetId = listFlexNet[x].split('|');

					var deviceTypeEnum = $.address.parameter("device_type") || $.address.parameter("deviceType");

					if (deviceTypeEnum) {
						deviceTypeEnum = deviceTypeEnum.replace("|", '');
					}

					devices.push({

						flexNetId : flexNetId[0],

						deviceType : flexNetId[1],

						customerId : flexNetId[2],

						electricMeterFlexNetId : flexNetId[3],

						specificDeviceTypeEnumValue : new Number(flexNetId[8]),

						deviceId 		   : flexNetId[4],

						deviceType         : deviceTypeEnum,

						lifecycleStateEnum : flexNetId[10]
					});

				}
			}
			return devices;
		},

		/**
		 * Initialized the common messaging area on the page. It hides the main
		 * messaging container and initialized the remove button. These expected
		 * ".messaging" (messaging container) and ".remove" (the remove button) to
		 * be present.
		 */
		initMessaging : function () {
			/** message html template **/
			var sClose = sensus.locale.get("commons.pages.close"),
				oMessaginMain = $("#messaging-main"),
				messageContainer = '<div id="messaging-main" class="messaging"  style="display:none;"><span class="message"></span><a href="" class="remove">' + sClose + '</a></div>';

			oMessaginMain.remove();
			oMessaginMain = [];

			if (!oMessaginMain.length) {
				$("#bd").prepend(messageContainer);
			}

			$('.messaging').hide();
			$(".remove").click(function(e) {
				e.preventDefault();
				$(this).parent().fadeOut("slow");

				return false;
			});

			/** * Errors displayed when a pageAction is called, inserted by decorator_epm_components.jsp ** */
			var txtMsgErros = $("#messageErrorPages").text();

			var whiteSpace = txtMsgErros.replace(/\s/g, "");

			if (whiteSpace.length) {
				sensus.util.page.showMessage("messaging-main", txtMsgErros, "error");
			}

		},

		/**
		 * Initialized the common messaging area on the tabs page. It hides the main
		 * messaging container and initialized the remove button. These expected
		 * ".messaging" (messaging container) and ".remove" (the remove button) to
		 * be present.
		 */
		initMessagingTabs : function () {

			var sClose = sensus.locale.get("commons.pages.close");
			var messageContainer = '<div id="messaging-main" class="messaging"  style="display:none;"><span class="message"></span><a href="" class="remove">' + sClose + '</a></div>';
			var $messageMain = $("#messaging-main");

			if ($messageMain.length) {
				$messageMain.remove();
			}

			var $tabsContent = $("#tabs-content");

			if (!$tabsContent.length) {

				$tabsContent = $(".yui-t2");
			}

			$tabsContent.prepend(messageContainer);

			$('.messaging').hide();
			$(".remove").click(function(e) {
				e.preventDefault();
				$(this).parent().fadeOut("slow");
			});

		},

		/**
		 * Resets the fields in the form with the provide id.
		 *
		 * @param target
		 *            the id of the form to clear
		 */
		clearFormElements : function (target) {
			$("#" + target + " .messaging").hide();
			$("#" + target).find(':input').each(function () {
				$(this).removeClass("error");
				switch (this.type) {
				case 'password':
				case 'select-multiple':
				case 'select-one':
				case 'text':
				case 'textarea':
					$(this).val('');
					break;
				case 'checkbox':
				case 'radio':
					this.checked = false;
				}
			});
		},

		initiated : false,

		startGlobalProgressBar : function (fnCallback) {

			sensus.util.page.initiated = false;

			$("#doc1").fadeOut(300);
		    $(".preload").fadeIn(300, function () {

		    	if ($.isFunction(fnCallback)) {
		    		fnCallback();
		    	}

		    });

		},

		stopGlobalProgressBar : function () {

			$(".preload").fadeOut(300);
			$("#doc1").fadeIn(300);

			sensus.util.page.initiated = true;
		},

		/**
		 * Initialize the progressbar dialog. This requires the "#loading" element.
		 */
		initProgressBar : function () {

			$("#loading").show().dialog({
				autoOpen : false,
				modal : true,
				dialogClass : 'loading',
				resizable : false
			});

		},

		/**
		 * Hide and start the progressbar. This requires the "#loading" and
		 * "#progressbar" elements to be present. The progress of the bar is
		 * currently hard-coded.
		 */
		startProgressBar : function (sMessage) {

			if (sensus.util.page.initiated) {

				$('.formError').remove();

				var oLoading = $('#loading');
				var oProgressbar = $("#progressbar");

				if (sMessage == null) {
					$('h5', oLoading).text(sensus.locale.get("commons.pages.updating"));
				} else {
					$('h5', oLoading).text(sensus.locale.get(sMessage));
				};

				if (!oLoading.dialog('isOpen')) {
					oLoading.dialog('open');
					oProgressbar.progressbar( {
						value : 1
					});
					(function progress() {
						var value = oProgressbar.progressbar('value');
						oProgressbar.progressbar('value', value + 1);
						if (value < 100) {
							setTimeout(progress, 200);
							if (value == 99){
								oProgressbar.progressbar( {
									value : 1
								});
							}
						}
					}());
				}
			}
		},


		/**
		 * Complete, stop and hide the progressbar. This requires the "#loading" and
		 * "#progressbar" elements to be present.
		 *
		 * @param recordcount
		 *            if a value greater than 0 is provided, the progressbar will
		 *            complete and stop after a delay instead of immediately
		 * @param bTable
		 * 			  Boolean - if is started from the table or application
		 */
		stopProgressBar : function (recordCount) {

			if (sensus.util.page.initiated) {

				var oProgressbar = $("#progressbar");
				var oLoading = $("#loading");
				var oFullscreen = $('.fullscreen');

				if (oProgressbar.is(":visible")) {

					oProgressbar.progressbar("value", 100);
				}

				setTimeout(function () {

					oLoading.dialog("close");
					oFullscreen.hide();

				}, 50);
			}
		},


		/**
		 * Converts the message array of an ajax callback result object to an HTML
		 * UL list.
		 *
		 * @param messages
		 *            array of message strings
		 * @return HTML for the message list
		 */
		getMessageList : function (messages) {
			if (messages != null) {
				if ($.isArray(messages) && !$.isPlainObject(messages[0])) {
					return messages.join("");
				} else if ($.isArray(messages) && $.isPlainObject(messages[0])) {
					var sMessage = "",
						i = 0,
						iMessagesLength = messages.length;
					for (; i < iMessagesLength; i++) {
						if (messages[i] && sMessage + messages[i].text)
							sMessage = sMessage + messages[i].text;
					}

					return sMessage;
				}
			}
		},

		/**
		 * Execute action to add devices from group
		 * @param sUrl
		 * 				String - The url to add devices
		 * @param iId
		 * 				Integer - The Id from the action to update
		 */
		executeUploadAction : function (sUrl, iId, uploadFile, processId, deviceType) {
			$.ajaxValidator.fnDoCall(sUrl,{uploadFile : uploadFile, uploadActionId : iId, processId:processId,bMonitored:"true", deviceType: deviceType}, false);
		},

		/**
		 * Initialize action buttons
		 * @param namespace
		 * @param fnCallback
		 * @param oActions
		 * @param sAdditionalActions
		 */
		menuPlug : function (namespace, fnCallback, oActions, sAdditionalActions, bDetail, filterSearch) {

			var sCategory;
			var oAction;
			var oActionType;
			var $actions = [];
			var i;
			var length;

			if (oActions) {

				var fnLoadActions =  function (sCategory) {
					oAction = oActions[sCategory];

					$actions.push('<li><a href="#" ');
					$actions.push('class="action-option" name="');
					$actions.push(oAction.name);
					$actions.push('">');
					$actions.push(oAction.description);
					$actions.push('</a><ul>');

					for (i = 0, length = oAction.actionType.length; i < length; i = i + 1) {

						oActionType = oAction.actionType[i];
						$actions.push('<li><a href="#" id="')
						$actions.push(oActionType.id);
						$actions.push('" name="');
						$actions.push(oActionType.name);
						$actions.push('">');
						$actions.push(oActionType.description);
						$actions.push('</a></li>');
					}

					$actions.push('</ul></li>');
				}


				if ($.isArray(oActions)) {
					for (var index = 0; index < oActions.length; index++) {
						fnLoadActions(index);
					}
				} else {
					for (sCategory in oActions) {
						fnLoadActions(sCategory);
					}
				}

				$('#actions-options ul').append($actions.join('') + (sAdditionalActions ? sAdditionalActions : ""));
			}

			// Initialize action buttons
			$('#actions-button').menuPlug({

				content : $('#actions-button').next().html(),
				showSpeed : 400,
				backLink: false,
				actionCallback : function (actionId, actionName) {

					/**
					 * TODO - Remove all conditions besides: fnCallback execute code and the last else condition.
					 * All this conditions now goes inside the fnCallback, letting just the else condition with the generic code.
					 * TODO - Functions at globalActions has a parameter 'isDetail', since detail pages is called by fnCallback, device
					 * list will go at else conditions, so false parameter is hard code.
					 */
					if ($.isFunction(fnCallback)) {

						fnCallback(actionName, actionId, bDetail, filterSearch);

					} else {

						$("input#actionName").val(actionName);
						var oCheckCount = $("#checked-count");
						var checkedNum = oCheckCount.text();
						var sTrimOption = actionName.replace(/\s/g,'');
						var oCheckCount = $("#checked-count");

						if (0 != checkedNum || !oCheckCount.length) {

							$('.message').removeClass("ui-state-error");
							sensus.util.actiondialog.launchActionDialog(sTrimOption, namespace.dialogSettings[sTrimOption]);

						} else {

							$('.yui-ge .message').addClass("ui-state-error");
							$('.yui-ge .messaging').hide();
						}
					}
				}
			});
		},

		scheduledEventName : "",
		scheduledEventId : 0,
		smartpointId: 0,
		actionName : "",
		actionId : 0,
		pages:"",

		fnPad : function (nNumber, iLength) {

			var sStr = '' + nNumber;

			while (sStr.length < iLength) {
				sStr = '0' + sStr;
			}

			return sStr;

		},

		/**
		 * Format URL Filters
		 */

		fnFormatURLFilter : function (sFilterValue, bIsString, sType) {

			var sFormatFilter = null;

			if (!$.ajaxValidator.fnIsNullOrUndefined(sFilterValue)) {

				if (bIsString) {
					sFormatFilter = decodeURI(sFilterValue);
				} else {
					sFormatFilter = decodeURI(sFilterValue).replace(/%26/g, '&').slice(0, -1).split('|');
				}

				if (sType == "int") {
					 var sFormatFilter = $.map(sFormatFilter, function(item, index) {
						return window.parseInt(item);
					});
				}
			}
			return sFormatFilter;
		},

		/**
		 * Format Date Filter
		 */

		fnFormatDateFilter : function (sDate, sTypeDate, sStartDate, oTz) {

			var dFormatDate = null;
			var sDateFormatMask = sensus.settings.dateFormatMask.replace("yyyy", "yy") ;
			var dDate;

			if (!$.ajaxValidator.fnIsNullOrUndefined(sDate)) {

				if (sDate.length && (sTypeDate == 'startDate' || sTypeDate == 'endDate' || sTypeDate == 'date_start')) {

					dDate = $.datepicker.parseDate(sDateFormatMask, sDate);

					if (sTypeDate == 'date_start') {

						if (sStartDate == 'startYear') {
							dDate.setMonth(0);
							dDate.setDate(1);
						} else if (sStartDate == 'endYear') {
							dDate.setMonth(11);
							dDate.setDate(31);
						}
					}

				} else if (!$.ajaxValidator.fnIsNullOrUndefined(sStartDate)) {

						if (sStartDate.length) {
							dDate = $.datepicker.parseDate(sDateFormatMask, sStartDate);
						} else {
							dDate 		= $.date.setDateServer();
						}

						dDate.setDate(dDate.getDate() - parseInt(sDate));

				} else {
					dDate 		= $.date.setDateServer(true);
				}

				if (sTypeDate == 'startDate' || sTypeDate == 'date_start' || sTypeDate =='setStartDate') {
					dDate.setHours(0);
					dDate.setMinutes(0);
					dDate.setSeconds(1);
				} else {
					dDate.setHours(23);
					dDate.setMinutes(59);
					dDate.setSeconds(59);

				}

				// When it do not receive time Zone, apply application time zone
				if ($.sc.isNullOrUndefined(oTz)) {
					oTz = true;
				}

				dFormatDate = $.date.fnTimeToUTC(dDate, oTz);
			}

			return dFormatDate;
		},

		/**
		 * Verify the action type that was aborted
		 *
		 * If the action is Initiate Demand Response or Send Text Message the abort message is different.
		 * @param action type
		 *
		 * @return message string
		 */
		fnAbortProcessMessage : function (sType) {

			var sMessage;

			if ((sType == "sensus.dm.action.demand.response") || (sType == "sensus.dm.action.send.han.text.message")) {
				sMessage = "expire.expireReceived";
			} else {
				sMessage = "expire.successfully";
			}

			return sMessage;
		},


		fnGetRemoteDisconnectSate : function (sValue) {
			var oValues = {
				UNKNOWN : sensus.locale.get("commons.pages.UNKNOWN"),
				CONNECT : sensus.locale.get("commons.pages.CONNECT"),
				DISCONNECT : sensus.locale.get("commons.pages.DISCONNECT"),
				ARMED : sensus.locale.get("commons.pages.ARMED"),
				CLOSED : sensus.locale.get("commons.pages.CLOSED"),
				OPEN : sensus.locale.get("commons.pages.OPEN")
			}

			return oValues[sValue];
		},

		getSortExpression : function (table, aaSorting) {

		    var tableSettings    = table.data("table");
		    var	sortEnum 		 = tableSettings.oSettings.sortEnum;
		    var	oSorted 		 = table.find(".sorting_asc, .sorting_desc");
		    var	oSpan 			 = oSorted.find("span");
			var aSortExpressions = [];
			var aFieldId		 = [];
			var	sEnumValue;
			var	sDirection;

			if (oSpan.length > 0) {
				aFieldId = oSpan.attr('id').replace('id=\"','').replace('\"','').replace(/-/g,'_').split('|');
			} else {
				aFieldId = tableSettings.oSettings.sDefaulSortEnum.split('|');
			}

			if (aaSorting != undefined) {
				sDirection = aaSorting[0][1];
			} else {
				sDirection = table.fnSettings().aaSorting[0][1];
			}

			sDirection = sDirection.substr(0, 1).toUpperCase() + sDirection.substr(1) + 'ending';

			for (var i = 0; i < aFieldId.length; i++) {

				sEnumValue = sensus.util.page.fnGetEnumValue(sortEnum, aFieldId[i]);
				aSortExpressions.push({'field' : sEnumValue, 'direction' : sDirection});
			}

		    return sortExpressions = aSortExpressions;
		},

		fnGetEnumValue : function (sEnum, enumKey) {
			var sValue = "";

			if ($.ajaxValidator.fnIsNullOrUndefined(sEnum) || $.ajaxValidator.fnIsNullOrUndefined(enumKey)) {
				return "";
			}

			enumKey = enumKey.toUpperCase();
			sValue = sensus.settings.enums[sEnum];

			if ($.ajaxValidator.fnIsNullOrUndefined(sValue)) {
				return "";
			}

			sValue = sValue[enumKey];

			return sValue;
		},

		getDateFormat : function (sDateFormat) {

			if (sDateFormat === "mm/dd/yyyy") {
				return "MM/dd/yyyy";
			}

			if (sDateFormat === "dd/mm/yyyy") {
				return "dd/MM/yyyy";
			}

			return null;
		},

		/**
		 * Create the options HTML tags and add to select element at the screen
		 *
		 * @param $select
		 * 			[Object], the jQuery selector
		 * @param aData
		 * 			[Array], the data array
		 * @param sType
		 * 			[String], the type (chosen, update, combo)
		 * @param bAddOption
		 * 			[Boolean], the add option
		 * @param fnGetValue
		 * 			[Function], the get value function
		 * @param fnGetLabel
		 * 			[Function], the get label function
		 */
		createOptions : function ($select, aData, sType, bAddOption, fnGetValue, fnGetLabel) {

			var length = aData ? (aData.length || 0) : 0;
			var aOptions = [];
			var i;
			var oData;

			// default get value
			if (!fnGetValue) {

				fnGetValue = function (oData) {

					return oData.id;
				}
			}

			// default get label
			if (!fnGetLabel) {

				fnGetLabel = function (oData) {

					return oData.name;
				}
			}

			if (length > 0) {

				if (bAddOption) {

					aOptions.push("<option value=''></option>");
				}

				for (i = 0; i < length; i = i + 1) {

					oData = aData[i];

					aOptions.push("<option value='");
					aOptions.push(fnGetValue(oData));
					aOptions.push("'>");
					aOptions.push(fnGetLabel(oData));
					aOptions.push("</option>");
				}

			} else {

				if (bAddOption) {

					aOptions.push("<option value=''>");
					aOptions.push(sensus.locale.get("tag.page.addTag"))
					aOptions.push("</option>");
				}
			}

			$select.html(aOptions.join(""));

			switch (sType) {
			case "chosen":

				$select.chosen();
				break;

			case "update":

				$select.trigger("liszt:updated");
				break;

			case "combo":

				$select.combobox({
					zIndex : 3999
				});
				break;

			default:
				break;
			}
		},

		/**
		 * Format the link to redirect to device list
		 *
		 * @return string with type of service
		 */
		fnFormatURLService : function () {

			return sensus.settings.serviceType.toLowerCase() + 'list';
		},

		sort : function (arr, getSortProperty) {

			var fnGetSortProperty;
			var first;

			if ($.isArray(arr) && arr.length > 0) {

				switch ($.type(getSortProperty)) {

				case "string": // When indicate the property name: create the default getSortProperty function

					first = arr[0];

					// Whether the property value is string
					if (first[getSortProperty] == undefined) {

						return null;
					}

					if ($.type(first[getSortProperty]) == "string") {

						// Default get sort function for string property type
						fnGetSortProperty = function (o) {

							return o[getSortProperty].toLowerCase();
						};

					} else {

						// Default get sort function for others property type
						fnGetSortProperty = function (o) {

							return o[getSortProperty];
						};
					}

					break;

				case "function":

					fnGetSortProperty = getSortProperty;

					// When indicate the getSortProperty function
					break;

				default:

					return null;
				}

				return arr.sort(function (a, b) {

				    var aName = fnGetSortProperty(a);
				    var bName = fnGetSortProperty(b);

				    return aName < bName ? -1 : (aName > bName ? 1 : 0);
				});
			}

			return null;
		}
};