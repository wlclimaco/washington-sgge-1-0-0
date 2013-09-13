sensus.util.module_operation = {

	createNewDeviceForReadingsRequest : function (device) {

		return {
			deviceId : device.deviceId,
			deviceType : device.deviceType,
			radio : {
				customerId : device.radio.customerId,
				flexNetId : device.radio.flexNetId,
				location : {
					timeZoneInfo : {
						displayMinutes : device.radio.location.timeZoneInfo.displayMinutes,
						displayName : device.radio.location.timeZoneInfo.displayName
					}
				}
			}
		};
	},

	createDeviceReadingRequest : function(oParam) {

		var oDevice = sensus.util.module_operation.createNewDeviceForReadingsRequest(sensus.util.module_operation.fnPrepareDeviceData());

		deviceReadingRequest = new DeviceReadingRequest({

			device			: oDevice,
			initialDate	 	: $.date.fnTimeToUTC(oParam.initialDate, false),
			endDate		 	: $.date.fnTimeToUTC(oParam.initialDate, false)

		});

		return deviceReadingRequest;
	},

	fnPrepareDeviceData : function() {

		var oDevice = sensus.pages.device.module.request.get("device");

		return sensus.pages.device.module.util.createDeviceRequest(oDevice);
	},

	fnPreDrawCallback : function(oResponse, url) {

		var aaData = [];

		if (!$.sc.isNullOrUndefined(url) && !$.sc.isNullOrUndefined(oResponse)) {

			if (url.contains("fetchTouRead")) {

				var  count = 1;

				if (!$.sc.isNullOrUndefined(oResponse) && !$.sc.isNullOrUndefined(oResponse.touRead) && oResponse.touRead.length > 0) {

					var dDateServer = $.date.setDateServer(true).getTime();
					var iTouRead 	= oResponse.touRead.length;

					for (var y = 1; y < iTouRead; y++) {

						if (oResponse.touRead[y]) {

							var record 	 = [null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null];
							var aTouRead = oResponse.touRead[y];
							var iTou = aTouRead.length;

							for (var i = 0; i < iTou; i++) {

								record[count++] = aTouRead[i];
								record[count++] = oResponse.touRead[0][i];

								if (dDateServer) {
									record[33] = dDateServer;
								}

							}
						}

						count = 1;
						aaData.push(record);
					}
				}

				return aaData;

			} else {

				var  count = 0;

				if (!$.sc.isNullOrUndefined(oResponse.intervalReads) && oResponse.intervalReads.length > 0) {

					var iIntervalReads = oResponse.intervalReads.length;

					for (var y = 0; y < iIntervalReads; y++) {

						if (oResponse.intervalReads[y].channels) {

							var record 		= [null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null];
							var iChannels 	= oResponse.intervalReads[y].channels.length;

							for (var x = 0; x < iChannels; x++) {

								var channelKey;

								if (oResponse.intervalReads[0].samplePoint) {
									record[0] = oResponse.intervalReads[y].samplePoint;
								}

								var channel = oResponse.intervalReads[y].channels[x];

								count++;

								for ( sKey in channel) {
									channelKey = sKey;
								}

								record[count] = channel[channelKey];

								count++;

								if (channel[channelKey]) {
									record[count] = channelKey;
								} else {
									record[count] = channel[channelKey];
								}
							}
						}

						if (oResponse.intervalReads[y].readingDate) {
							record[33] = oResponse.intervalReads[y].readingDate;
						}

						count = 0;
						aaData.push(record);
					}
				}

				return aaData;
			}

			return aaData;

		} else {

			var aaData 		= [];
			var fnCallback;
			var sDateStart 	= $.date.parseDate($.address.parameter('date_start'), sensus.settings.dateFormatMask.replace("yyyy", "yy"));

			var oParam	   = {
					initialDate 	: sDateStart,
					endDate 		: sDateStart
			};

			var oRequest = sensus.util.module_operation.createDeviceReadingRequest(oParam);

			if (url.contains("fetchTouRead")) {

				fnCallback = function(oResponse) {

					var  count = 1;

					if (!$.sc.isNullOrUndefined(oResponse) && !$.sc.isNullOrUndefined(oResponse.touRead) && oResponse.touRead.length > 0) {

						var dDateServer = $.date.setDateServer(true).getTime();
						var iTouRead 	= oResponse.touRead.length;

						for (var y = 1; y < iTouRead; y++) {

							if (oResponse.touRead[y]) {

								var record 	 = [null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null];
								var aTouRead = oResponse.touRead[y];
								var iTou = aTouRead.length;

								for (var i = 0; i < iTou; i++) {

									record[count++] = aTouRead[i];
									record[count++] = oResponse.touRead[0][i];

									if (dDateServer) {
										record[33] = dDateServer;
									}
								}
							}

							count = 1;
							aaData.push(record);
						}
					}

					return aaData;
				};

			} else {

				fnCallback = function(oResponse) {

					var  count = 0;

					if (!$.sc.isNullOrUndefined(oResponse) && !$.sc.isNullOrUndefined(oResponse.intervalReads) && oResponse.intervalReads.length > 0) {

						var iIntervalReads = oResponse.intervalReads.length;

						for (var y = 0; y < iIntervalReads; y++) {

							if (oResponse.intervalReads[y].channels) {

								var record 		= [null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null];
								var iChannels 	= oResponse.intervalReads[y].channels.length;

								for (var x = 0; x < iChannels; x++) {

									var channelKey;

									if (oResponse.intervalReads[0].samplePoint) {
										record[0] = oResponse.intervalReads[y].samplePoint;
									}

									var channel = oResponse.intervalReads[y].channels[x];

									count++;

									for (sKey in channel) {
										channelKey = sKey;
									}

									record[count] = channel[channelKey];

									count++;

									if (channel[channelKey]) {
										record[count] = channelKey;
									} else {
										record[count] = channel[channelKey];
									}
								}
							}

							if (oResponse.intervalReads[y].readingDate) {
								record[33] = oResponse.intervalReads[y].readingDate;
							}

							count = 0;
							aaData.push(record);
						}
					}

					return aaData;
				};
			}

			$.ajaxValidator.fnDoCall(url, oRequest, false, fnCallback);
		}
		return aaData;
	},

	fnReload : function(sNameSpace,url) {

		sNameSpace.fnClearTable();
		sNameSpace.fnAddData(sensus.pages.readings.aDataTable(null, url));

		$(".dataTables_scroll").show();

		if ($("#reading-table:visible").length) {

			$(".blankslate").hide();
			// Ajust the columns size when table loading is finished
			sNameSpace.fnAdjustColumnSizing();

		} else {

			 $(".dataTables_scroll").hide();
			 $(".blankslate").show();

		}
	}
}