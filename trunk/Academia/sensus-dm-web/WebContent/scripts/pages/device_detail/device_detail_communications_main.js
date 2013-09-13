/**
 * Device Communications
 */
sensus.pages.devicecommunications = {

		receiveOperational : {

			DISABLED : sensus.locale.get("communication.page.buddy.disabled"),
			ENABLED : sensus.locale.get("communication.page.buddy.enabled"),

			/**
			 * Get Receive Operational
			 */
			getReceiveOperational : function (sReceiveEnum) {

				if (!sReceiveEnum) {
					return null;
				}

				if (sReceiveEnum.contains("DISABLED")) {

					return this.DISABLED;
				}

				return this.ENABLED;
			}
		}

};