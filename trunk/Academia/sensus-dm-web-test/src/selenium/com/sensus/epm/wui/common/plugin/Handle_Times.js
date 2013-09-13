/*
var time = new Date().toLocaleTimeString();
var str = "2:16:00.000 PM";

var t1 = str.replace(/(\d{1,2}:\d\d):\d\d.\d{1,3}( .M)/, "\$1\$2");
var t2 = time.replace(/(\d{1,2}:\d\d):\d\d( .M)/, "\$1\$2");

console.log(t1);
console.log(t2);
*/

/*
* Handle Time in selenium 
*
* @author Vinícius Lemes
*/

var mySetTimeFormat = "hh/mm/ss";

/**
*
* @param varName, name of the variable date to be set to.
* @param timeFormat, optional, format for the time.
*/
Selenium.prototype.doGetSysTime = function(varName, timeFormat) {
	try {
	
		//Get the system date
		var currDate = new Date();
		
		// Get the provided date format, if not provided
		// set the default format
		if (!timeFormat) {
			currDate = Selenium.prototype.doFormatTime(currDate, mySetTimeFormat);
		} else {
			currDate = Selenium.prototype.doFormatTime(currDate, timeFormat);
		}
		
		//Set the date to the provided variable
		if (globalStoredVars) {
			globalStoredVars[varName] = currDate;
		} else {
			storedVars[varName] = currDate;
		}
		
	} catch(e) {
		throw new SeleniumError("Exception occured in getSysDate: " + e.message);
	}
}

/**
*
* @param varDate, instance of Date object with date to be changed.
* @param timeFormat, format time to be changed to.
*/
Selenium.prototype.doFormatTime = function(varDate, timeFormat) {
	// check if the passed param is a valid date
	if (varDate instanceof Date) {
		
		/*
		// Get Hours
		var hours = varDate.getHours();
		
		// Get Minutes
		var minutes = varDate.getMinutes();
		var twoDigitMinutes = minutes < 10 ? "0"+minutes : minutes;
		// Get Seconds
		var seconds = varDate.getSeconds();
		
		timeFormat = hours + ":" + twoDigitMinutes;
		*/
		
		timeFormat = varDate.toLocaleTimeString();
		timeFormat = timeFormat.replace(/(\d{1,2}:\d\d):\d\d( .M)/, "\$1\$2");
		
	} else {
		throw new SeleniumError("Passed parameter is not a valid date.");
	}
	
	return timeFormat;
}