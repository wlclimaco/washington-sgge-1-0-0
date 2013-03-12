<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>

<script type="text/javascript">
/**
 * @author QATEmployee
 */
sensus.pages.smartpoint.detail = {};  

$(document).ready(function() { 
	 
	  // The Mock Parameters
	   sensus.settings.oDeviceTypeParameters = {
	    summaryData : ['lightInformation', 'lightState', 'lightStatus', 'statusMessage', 'ecoMode', 'lightActions'],
	    tabs    : ['lightDetail', 'lightHistory'],
	    content   : ['lightAlerts','lightSchedule','lightReadings','lightConfigurations','lightLocation','lightGroups', 'lighttags']
	   };  
	  
	//  var oParameters = $.grep(sensus.settings.oDeviceTypeParameters, function(n) {
	      //var sDeviceType = $.address.parameter("deviceType");
	//   return n.deviceTypeEnum == 'METER';
	//  }) 
	  
	  
	  $.moduleController.init(sensus.settings.oDeviceTypeParameters, "#about-deivce");

	 
	});
</script>
