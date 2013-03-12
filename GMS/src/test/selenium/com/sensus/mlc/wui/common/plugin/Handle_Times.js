/**
* Handle Time in selenium 
*
* @author QAT-Brazil
*
* @param varName, name of the variable date to be set to.
* @param timeFormat, optional, format for the time.
*/
Selenium.prototype.doGetSysTime = function(varName) {
	try {
	
		//Get the system time
		var currTime = Selenium.prototype.doFormatTime(new Date());
		
		//Set the date to the provided variable
		if (globalStoredVars) {
			globalStoredVars[varName] = currTime;
		} else {
			storedVars[varName] = currTime;
		}
		
	} catch(e) {
		throw new SeleniumError("Exception occured in getSysTime: " + e.message);
	}
}

/**
*
* @param varDate, instance of Date object with date to be changed.
* @param timeFormat, format time to be changed to.
*/
Selenium.prototype.doFormatTime = function(varDate) {
	// check if the passed param is a valid date
	if (varDate instanceof Date) {
	
		var timeFormat = varDate.toLocaleTimeString();
		timeFormat = timeFormat.replace(/(\d{1,2}:\d\d):\d\d( .M)/, "\$1\$2");
		
	} else {
		throw new SeleniumError("Passed parameter is not a valid date.");
	}
	
	return timeFormat;
}