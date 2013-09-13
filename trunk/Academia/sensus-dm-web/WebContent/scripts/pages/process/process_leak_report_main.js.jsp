<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags'%>

<sec:authorize access="hasAnyRole('ROLE_EPM_SERVICE_ELECTRIC', 'ROLE_EPM_SERVICE_WATER', 'ROLE_EPM_SERVICE_GAS') and 
	hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR') and 
		!hasAnyRole('ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">

	<script type="text/javascript">

	sensus.pages.processreport = {

		/**
		 * Function to the fetch of Leaks Report
		 *
		 * @Param [String] url - Method URL
		 * @Return [Array] With the leaks list
		 */
		fnPreDrawCallback : function(oResponse) {

			var aaData 	 = [];
			var oRequest = {};


				var oLeaksList 	= oResponse.leakList;
				var iLeaks 		= oLeaksList.length;

				if (oLeaksList.length > 0) {

					for (var i = 0; i < iLeaks; i++) {

						var record = [null, null, null, null, null, null, null, null, null];

						record[0] = oLeaksList[i].waterMeter.deviceId;
						record[1] = oLeaksList[i].waterMeter.radio.flexNetId;
						record[2] = oLeaksList[i].leakTime;
						record[3] = oLeaksList[i].recentConsumption;
						record[4] = oLeaksList[i].recentConsumptionPercentage;
						record[5] = oLeaksList[i].priorConsumption;
						record[6] = oLeaksList[i].priorConsumptionPercentage;
						record[7] = oLeaksList[i].dailyConsumptionAverage;
						record[8] = oLeaksList[i].waterMeter.deviceType;

						aaData.push(record);
					}
				}



			return aaData;
		},


		/**
		 * Function validate the data that gonna be shown in that table
		 *
		 * @Param [String]
		 * @Return [Array]
		 */
		fnFormatDateTime : function(sDateTime) {

			var sDateTimeFormated = "";
			var sPriorTo = sensus.locale.get("systemintelligence.leakReport.priorTo");

			// If contains Prior to text get just the date to format
			if (sDateTime.contains(sPriorTo)) {

				sDateTime = sDateTime.substring(9);
				sDateTimeFormated = sPriorTo + " ";
			}

			/**
			 *Used to verify if aData[objColumn.dateTime] cames just with 'Prior To '
			 *If it cames like that just white space is showed.
			 */
			if (sDateTime == "") {
				sDateTimeFormated = "";
			} else {

				//Date format EX: 2000-01-01 08:10AM
				var oRegex = /\d{4}\-\d{2}\-\d{2}\ \d{2}\:\d{2}([AP]M)?$/;

				// Verify if sDateTime really have a date format
				if (oRegex.test(sDateTime)) {

					//Adjust the format
					sDateTime = $.date.parseDate(sDateTime, "yy-mm-dd");
					sDateTime = $.date.dateFormat(sDateTime, sensus.settings.dateFormatMask.replace("yyyy", "yy") + " h:i:s A", {bUserTZ : false});
				}

				sDateTimeFormated += sDateTime;
			}

			return sDateTimeFormated;
		}

	};
	</script>

</sec:authorize>