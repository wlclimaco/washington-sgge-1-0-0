//head(function() {
	(function ($) {
		$.moduleController = (function() {

			/**
			 * Convert a Array of properties to an object. Used to pass parameter to jsp page
			 *
			 * @param aProperties - [Array] The array with the properties to show at the page
			 * @param sType - [String] The Type of the array.
			 * @return The Parameter Object converted from the array
			 */
			var convertToObject = function (aProperties, sType) {

				var oParameter = {},
					iPropertiesLength,
					iPropertiesIndex = 0;

				if (aProperties) {

					iPropertiesLength = aProperties.length || 0;

					for (; iPropertiesIndex < iPropertiesLength; iPropertiesIndex += 1) {
						oParameter[aProperties[iPropertiesIndex]] = sType;
					}
				}
				return oParameter;

			};

			return {

				init : function(oParameter, sDynamicTabId) {

					var oPropertiesTabs = {},
						oPropertiesContent = {},
						oPropertiesSummaryData = {},
						oProperties = {},
						oDevice = {};

					if (oParameter) {

						oDevice.id = $.address.parameter("id");
						oDevice.type = $.address.parameter("deviceType");

						// Fill the object with the parameters to pass to JSP page.
						oPropertiesTabs 		= convertToObject(oParameter.tabs, 'tab');
						oPropertiesContent 		= convertToObject(oParameter.contents, 'content');
						oPropertiesSummaryData 	= convertToObject(oParameter.summaryDatas, 'summaryData');
						oProperties 			= $.extend({}, oPropertiesTabs, oPropertiesContent, oPropertiesSummaryData, oDevice);

						$.ajax({
							url: "device/modules",
							type: "POST",
							data: oProperties,
							async : false,
							success: function (result) {

								var oDynamicTab;

								$('#content-controlle').html(result);

								sensus.util.page.stopProgressBar();

								if (oParameter.tabs && oParameter.tabs.length) {
									oDynamicTab = $(sDynamicTabId);
									oDynamicTab.data("parameter", oParameter.contents);
									sensus.commons.modules.oPropertiesContent = oPropertiesContent;
									sensus.commons.modules.executeTab(oParameter.tabs);
								}

								if (oParameter.summaryDatas && oParameter.summaryDatas.length) {
									sensus.commons.modules.executeSummaryData(oParameter.summaryDatas);
								}

								if (oParameter.contents && oParameter.contents.length) {
									sensus.commons.modules.executeContent(oParameter.contents);
								}
							}
						});
					}
				}
			}
		})();
	}(jQuery));
	//}); head