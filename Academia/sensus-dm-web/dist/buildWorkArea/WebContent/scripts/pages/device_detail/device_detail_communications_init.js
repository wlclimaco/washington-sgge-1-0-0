$(document).ready(function() {

	var oDevice = sensus.pages.device.module.request.get("device");
	var oConfig = oDevice.configuration || {};
	var $dl = $("#config-communications dl");

	// Network Address
	$dl.find("dd:eq(0)").text(oDevice.radio ? oDevice.radio.flexNetId : "");

	// Transmit Rate
	$dl.find("dd:eq(1)").text(oDevice.transmitRate + " " + sensus.locale.get("commons.pages.minutes") || "");

	// Encrypted
	$dl.find("dd:eq(2)").text(oConfig.encryptionSupported ? "ON" : "OFF");

	// Sample Rate
	$dl.find("dd:eq(3)").text(oConfig.sampleRate || "");

	// Billing Cycle
	$dl.find("dd:eq(4)").text(oConfig.billingCycle || "");

	// Receiver Channel
	$dl.find("dd:eq(5)").text(oConfig.receiverChannel || "");

	// Transmit Channel Mask
	$dl.find("dd:eq(6)").text(oConfig.transmitChannelMask || "--");

	// Transmit Operational Mode
	$dl.find("dd:eq(7)").text(sensus.pages.device.module.summaryDatas.transmitMode.getTransmitMode(oConfig.transmitterOperationalModeEnum)  || "");

	// Receive Operational Mode
	$dl.find("dd:eq(8)").text(sensus.pages.devicecommunications.receiveOperational.getReceiveOperational(oConfig.receiverOperationalModeEnum)  || "");

	sensus.util.page.stopGlobalProgressBar();
});