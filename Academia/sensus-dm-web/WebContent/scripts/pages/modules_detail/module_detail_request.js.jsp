<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags'%>

<sec:authorize access="hasAnyRole('ROLE_EPM_SERVICE_ELECTRIC', 'ROLE_EPM_SERVICE_WATER', 'ROLE_EPM_SERVICE_GAS') and 
	hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR', 'ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">

	<script type="text/javascript">
	sensus.pages.device.module.request = {

			cacheData : {},

			device : {

				get : function (oResponse) {

					if (oResponse.device && oResponse.device.firstDevice) {

						return oResponse.device.firstDevice;
					}

					return null;
				}
			},

			loadProfiles : {

				get : function (oResponse) {

					if (oResponse.loadProfiles) {

						return oResponse.loadProfiles.loadProfiles || [];
					}

					return [];
				}
			},

			get : function (sDataName, oResponse, bCache) {

				var that = sensus.pages.device.module.request;
				var oReturn;

				if (bCache !== false) {

					bCache = true;
				}

				// Look on cache first
				if (bCache === true && that.cacheData[sDataName]) {

					return that.cacheData[sDataName];
				}

				// If not in cache, call get function referenced
				if (that[sDataName]) {

					oReturn = that[sDataName].get(oResponse);
				}

				that.cacheData[sDataName] = oReturn;

				return oReturn;
			}
	};
	</script>

</sec:authorize>