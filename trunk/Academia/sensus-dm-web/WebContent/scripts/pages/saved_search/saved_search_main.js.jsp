<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags'%>

<sec:authorize access="hasAnyRole('ROLE_EPM_SERVICE_ELECTRIC', 'ROLE_EPM_SERVICE_WATER', 'ROLE_EPM_SERVICE_GAS') and 
	hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR', 'ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">

	<script type="text/javascript">
		sensus.pages.search = {

			filterParameters : {
				device_id			: {parameter : "query", 		hasMany : false},
				network_address		: {parameter : "query", 		hasMany : false},
				premise_id			: {parameter : "query", 		hasMany : false},
				address				: {parameter : "street", 		hasMany : false},
				city_name 			: {parameter : "city", 			hasMany : false},
				install_date_start 	: {parameter : "start", 		hasMany : false},
				install_date_end 	: {parameter : "end", 			hasMany : false},
				device_type_enum 	: {parameter : "device_type", 	hasMany : true},
				meter_firmware 		: {parameter : "firmware", 		hasMany : false},
				zip_code 			: {parameter : "zip", 			hasMany : false}
			},

			/**
			 * Fill Filters
			 *
			 * @param aFilters
			 * 			[Array], the filters
			 * @return oFillFilters
			 *			[Object], the filled filters object with the url and html elements
			 */
			fillFilters : function (aFilters) {

				var oFillFilters 	= {url : "", filters : ""};
				var aParameters		= [];
				var oParameters		= {};
				var $filters 		= ["<ul class='filter-container'>"];
				var iFiltersLength 	= aFilters.length;
				var i 				= 0;
				var sDateFormatMask = sensus.settings.dateFormatMask.replace("yyyy", "yy");
				var oFilter;
				var sProperty;
				var sValue;
				var sLabelValue;
				var oFilterParameters = sensus.pages.search.filterParameters;
				var oFilterParameter;

				for (; i < iFiltersLength; i = i + 1) {

					oFilter 	= aFilters[i];
					sProperty 	= oFilter.filterEnum;

					if (sProperty != "DATE_FORMAT") {

						sLabelValue	= oFilter.value;
						sValue 		= sLabelValue;

						// Fill Filters HTML Element
						$filters.push("<li class='read-only display-table'>");

						// TODO
						// This is a ugly hack caused by definicion of prorotype, where the ALARMS are called by ALERT and the ALARM TYPES are call ALARMS
						if (sProperty == "ALERT") {

							sProperty = "alarms";
							sLabelValue = sensus.locale.get("filter.alarm.name");

						} else if (sProperty == "TAG" || sProperty == "GROUP" || sProperty == "DESCRIPTION") { // tag or group case

							// Filters Enums (Properties) cases
							sLabelValue = oFilter.name;

						} else if (sProperty == 'CONNECTION_STATUS' || sProperty == 'REMOTE_DISCONNECT'
								|| sProperty == 'LIFECYCLE_STATE' 	|| sProperty == 'DEVICE_TYPE_ENUM'
								|| sProperty == 'STATUS_METER' 		|| sProperty == 'DEVICE_SUBTYPE'
								|| sProperty == 'ALARM' 			|| sProperty == 'QUARANTINE') {

							sLabelValue = sensus.locale.get("filter." + sProperty.toLowerCase() + "." + sLabelValue.toLowerCase());

						} else if (sProperty == "INSTALL_DATE_START" || sProperty == "INSTALL_DATE_END") { // install interval date case

							sLabelValue = $.date.dateFormat(sLabelValue, sDateFormatMask);
							sValue = sLabelValue;

						} else if (sProperty == "DEVICE_ID" || sProperty == "NETWORK_ADDRESS" || sProperty == "PREMISE_ID") { // Query case

							sValue = sLabelValue + "|" + sProperty;
						}

						$filters.push("<span class='type max-size-type'>" + sensus.locale.get('commons.pages.' + sProperty.toLowerCase()) + "</span>");
						$filters.push("<span class='title max-size-title'>" + sLabelValue + "</span>");

						$filters.push("</li>");

						// TODO
						// This is a ugly hack caused by definicion of prorotype, where the ALARMS are called by ALERT and the ALARM TYPES are call ALARMS
						if (sProperty == "alarms") {

							sProperty = "alert";
						}

						// Fill Filters parameters to build URL
						if (!oParameters[sProperty]) {
							oParameters[sProperty] = [];
						}

						oParameters[sProperty].push(sValue);
					}
				}

				$filters.push("</ul>");

				// Build the filter URL
				for (i in oParameters) {

					if (oParameters.hasOwnProperty(i)) {

						oFilterParameter = oFilterParameters[i.toLowerCase()] || {parameter : i.toLowerCase(), hasMany : true};

						// Whether the filter parameter object has 'hasMany' attribute equals TRUE
						// build the url with pipes like 'parameter=value|value|'
						if (oFilterParameter.hasMany) {

							aParameters.push(oFilterParameter.parameter + "=" + oParameters[i].join('|') + "|");
							continue;
						}

						// Whether the filter parameter object has 'hasMany' attribute equals FALSE
						// build the url without pipes like 'parameter=value'
						aParameters.push(oFilterParameter.parameter + "=" + oParameters[i].join());
					}
				}

				oFillFilters.filters = $filters.join(''); // HTML filters
				oFillFilters.url = aParameters.join('&'); // URL filters

				return oFillFilters;
			}
		};
	</script>

</sec:authorize>