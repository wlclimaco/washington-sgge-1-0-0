/**
* @fileoverview todo
* @author Eduardo Cintra
*
*/

(function ($) {
	$.moduleController = (function() {

		/**
		* Convert a Array of properties to an object. Used to pass parameter to jsp page
		*
		* @param aProperties - [Array] The array with the properties to show at the page.
		* @param sType - [String] The Type of the array.
		* @return The Parameter Object converted from the array
		*/
		var convertToObject = function(aProperties, sType) {
			var oParameter = {};
			if (aProperties) {	
				var iPropertiesLength = aProperties.length;
				var iPropertiesIndex = 0;
				
				if (aProperties.length) {
					for (; iPropertiesIndex < iPropertiesLength; iPropertiesIndex += 1) {
						oParameter[aProperties[iPropertiesIndex]] = sType;
					}
				}
			}	
			return oParameter;
			
		}
		
		return {
		
			/**
			* Initial Function
			*/
			init : function(oParameter, sDynamicTabId) {
				
				var oPropertiesTabs = {};
				var oPropertiesContent = {};
				var oPropertiesSummaryData = {};
				var oProperties = {};
				var oDevice = {};

				
				if (oParameter) {

					oDevice.id = $.address.parameter("id");
					oDevice.deviceType = oParameter.deviceTypeEnum;
					
					// Fill the object with the parameters to pass to jsp page.
					oPropertiesTabs = convertToObject(oParameter.tabs, 'tab');
					oPropertiesContent = convertToObject(oParameter.content, 'content')
					oPropertiesSummaryData = convertToObject(oParameter.summaryData, 'summaryData')
					oProperties = $.extend({}, oPropertiesTabs, oPropertiesContent, oPropertiesSummaryData, oDevice);
					$.ajax({
						url: "smartpoint/ajax.openSmartPointDetailComponent.action",
						type: "POST",
						data:  oProperties,
						async : false,
						success: function(result) {
							$('#content-controller').html(result);

							sensus.util.page.stopProgressBar(null,true);

							if (oParameter.tabs && oParameter.tabs.length){	
								var oDynamicTab = $(sDynamicTabId);
								oDynamicTab.data("parameter", oParameter.content);
								sensus.commons.modules.oPropertiesContent = oPropertiesContent;
								
								sensus.commons.modules.executeTab(oParameter.tabs);
							}
							if (oParameter.summaryData && oParameter.summaryData.length) {
								sensus.commons.modules.executeSummaryData(oParameter.summaryData);
							}	
							if (oParameter.content && oParameter.content.length) {
								sensus.commons.modules.executeContent(oParameter.content);
							}	
						}
					});

				}	
			}
		}
	
	})();
}(jQuery));
