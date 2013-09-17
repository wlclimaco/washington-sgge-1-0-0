sensus.util.generatelinkaction = {

	/**
	 * @param sDate
	 * 			string, date
	 * @return sLink
	 * 			string, links url
	 */
	generateLink : function(sDescription, sStatus, sEventType, iProcessId, sDeviceTypes, sDeviceSubType) {
		//sDescription example: '[Schedule.100]Demand Reset [On Demand] for Network Address 1001'

		// Get action type and its id, for instance "Schedule" and "100"
		var aTypes = sDescription.substring(1,sDescription.indexOf(']')).split(".");
		// The description removing the type: 'Demand Reset [On Demand] for Network Address 1001'
		var sLink = sDescription.substring(sDescription.indexOf(']') + 1, sDescription.length);

		var sGroupLink;

		if (aTypes[0].toLowerCase() != 'action') {

			if (sLink.contains("[On Demand]")) {
				// Replace "[]" from On Demand
				sLink = sLink.replace("[On Demand]", "On Demand");
				// Change type to process, so when user click on number of devices it will
				// redirect to device list page filtered by the process.
				aTypes[0] = "processId";
				aTypes[1] = iProcessId;
			}

			// Replace "[" to "a" tag
			sLink = sLink.replace("[","<a href='#' id='" + aTypes[1] + "' class='" + aTypes[0] + "'>\"");
			// Replace "]" to "/a" tag
			sLink = sLink.replace("]","\"</a>");
			// Replace last "[]" in case that have more than one. Usually used at Schedule processes.
			sLink = sLink.replace(/\[|\]/g, "");
		}

		if (sLink.contains("[OnDemand")){
			sLink = sLink.substring(0, sLink.indexOf("[OnDemand|"));
		}

		//Generate links for Flexnet Id to smartpoint detail page
		if (sDescription.contains("network address")) {
			var aDeviceIdType = [];

			sFlexNetId = sDescription.substring(sDescription.indexOf("network address") + 16).split("|");
			sFlexNetId.push(sFlexNetId.pop().split(" ")[0]);
			sFlexNetId = sFlexNetId.join('|');
			sFlexNetId = sFlexNetId.replace(/\,/g, "").replace(/\./g, "");
			aDeviceIdType = sFlexNetId.split("|");

			if (aDeviceIdType[0] && aDeviceIdType[1] && aDeviceIdType[2] && aDeviceIdType[3]) {
				//If the action is delete han device put the text without link
				if (!sLink.contains("Delete HAN device")) {
					sLinkFlexNet = "<a class='alist' href='device/detail?id=" + aDeviceIdType[0] + "&deviceType=" + aDeviceIdType[2] + "&typeEnum=" + aDeviceIdType[3] + "'>" + aDeviceIdType[1] + "</a>";
				} else {
					sLinkFlexNet = aDeviceIdType[1];
				}
			} else if ((aDeviceIdType[0]) && (sLink.contains("Delete HAN device"))) {
				sLinkFlexNet = aDeviceIdType[0];
			} else {
				sLinkFlexNet = "";
			}

			if (sDescription.contains("Add tag") || sDescription.contains("Demand Reset")) {
				var sValue = sLink.substring(sLink.indexOf("</a>")+5, sLink.length);
				sLink = sLink.replace(sValue, sValue.replace(sFlexNetId, sLinkFlexNet));
				sLink = sLink.replace("network address", sensus.locale.get("commons.pages.device"))
			} else {
				sLink = sLink.replace(sFlexNetId, sLinkFlexNet);
				sLink = sLink.replace("network address", sensus.locale.get("commons.pages.device"));
			}
		}

		if (sLink.contains("demand.read")) {

			sLink = sensus.locale.get(sLink);
		}

		sLink = $("<span>" + sLink + "</span>");

		switch (aTypes[0].toLowerCase()) {
			case 'group':

				if (sDeviceTypes) {

					sGroupLink = "?device_type=" + sDeviceTypes + "|&group=" + aTypes[1] + "|";

					if (sDeviceSubType) {

						sGroupLink += "&device_subtype=" + sDeviceSubType + "|";
					}

				} else {

					sGroupLink = "?group=" + aTypes[1] + "|";
				}

				sLink.find("a.Group").addClass('alist');
				sLink.find("a.Group").attr("href", sensus.util.page.fnFormatURLService() + sGroupLink);
				break;

			case 'tag':

				sLink.find("a.Tag").addClass('alist');
				sLink.find("a.Tag").attr("href", sensus.util.page.fnFormatURLService() + "?tag=" + aTypes[1] + "|");
				break;

			case 'processid' :

				sLink.find("a.processId").addClass('alist');
				sLink.find("a.processId").attr("href", sensus.util.page.fnFormatURLService() + "?processId=" + aTypes[1]);
				break;

			default :
				break;
		}

		// Open schedule dialog
		sLink.find("a.Schedule").click(function (e) {

			e.preventDefault();

			if (sEventType == "Schedule" || sStatus == "Scheduled") {
				sensus.util.actiondialog.launchActionDialog("eventView", sensus.util.process.actions.eventView(aTypes[1], "openUpdateById"));
			} else {
				sensus.util.actiondialog.launchActionDialog("eventView", sensus.util.process.actions.eventView(aTypes[1], "openUpdateByAction"));
			}

		});

		return sLink;
	}
}