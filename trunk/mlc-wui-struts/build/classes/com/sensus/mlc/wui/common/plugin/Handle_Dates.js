/*
* Set of mthods to handle Dates in selenium.
*
* @author Nikhil Wanpal
*/
var mySetDateFormat = "mm/dd/yyyy";

/**
* Gets the system date and sets in the provided variable. If date 
* format is provided, the date will be converted to given date format and
* set in the variable. Default date format is mm/dd/yyyy where
* d= Date, m= Month and y = Year.
*
* @param varName, name of the variable date to be set to.
* @param dtFormat, optional, format for the date.
*/
Selenium.prototype.doGetSysDate = function(varName,dtFormat) {
	try {
	
		//Get the system date
		var currDate = new Date();
		
		// Get the provided date format, if not provided
		// set the default format
		if (!dtFormat){
			currDate = Selenium.prototype.doFormatDate(currDate,mySetDateFormat);
		} else {
			currDate = Selenium.prototype.doFormatDate(currDate,dtFormat);
		}
		
		//Set the date to the provided variable
		storedVars[varName] = currDate;
		
	} catch(e){
		throw new SeleniumError("Exception occured in getSysDate: "+e.message);
	}
}

/**
* Does reformatting of the given date. Gets the value of the passed
* variable, parses to date, converts to the provided format and sets
* back in the variable. Default date format is mm/dd/yyyy where
* d= Date, m= Month and y = Year.
* Limitation: The passed date variable should have date in 
* format as: mm/dd/yyyy.
*
* @param varName, name of the variable having the date.
* @param dtFormat, optional, format for the date.
*/
Selenium.prototype.doReFormatDate = function(varDate, format) {
	try {
		// Get the value of the passed date variable
		var varDateVal = Selenium.prototype.replaceVariables("\${"+varDate+"}");
		
		// Parse the passed date string to date object.
		var parsedDate = new Date(varDateVal);
		if (parsedDate.toString() == "Invalid Date"){
			throw new SeleniumError("Failed to parse string as date. Invalid dateformat.");
		}
		
		// If format not provided, set the default dateformat.
		if (!format){
			parsedDate = Selenium.prototype.doFormatDate(parsedDate,mySetDateFormat);
		} else {
			parsedDate = Selenium.prototype.doFormatDate(parsedDate,format);
		}
		
		//Set the date to the provided variable
		storedVars[varDate] = parsedDate;
		
	} catch(e) {
		throw new SeleniumError("Exception occured in reFormatDate: "+e.message);
	}
}

/**
* Does Formatting of given date, the passed variable must be an instance of
* JavaScript date object.
* This method is not expected to be called directly in a test case.
*
* @param varDate, instance of Date object with date to be changed.
* @param dateFormat, format date to be changed to.
*/
Selenium.prototype.doFormatDate = function(varDate, dateFormat) {
	// check if the passed param is a valid date
	if (varDate instanceof Date){
				
		// Get year values.
		var fullYr = varDate.getFullYear();
		var year = fullYr.toString().substring(2);
		
		// Set all month strings
		var monthArray = new Array("Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec");
		// Get month values
		var month = varDate.getMonth()+1;
		var twoDigitMonth = month < 10 ? "0"+month : month;
		var strMonth = monthArray[month-1];

		// Get date values
		var dt = varDate.getDate();
		var twoDigitdt = dt < 10 ? "0"+dt : dt;
		
		// Convert date to given format
		dateFormat = dateFormat.replace(/yyyy/i, fullYr);
		dateFormat = dateFormat.replace(/yy/i, year);
		dateFormat = dateFormat.replace(/mmm/i, strMonth);
		dateFormat = dateFormat.replace(/mm/i, twoDigitMonth);
		dateFormat = dateFormat.replace(/m/i, month);
		dateFormat = dateFormat.replace(/dd/i, twoDigitdt);
		dateFormat = dateFormat.replace(/d/i, dt);
		
	} else {
		throw new SeleniumError("Passed parameter is not a valid date. To change format"+
		" of date stored in a variable, use reFormatDate method.");
	}
	
	return dateFormat;
	
}

/**
* Increments / decrements , the date value of the passed variable by the provided
* increment value. Increment value is always 'number of Days'.
* returned value is always the default dateformat which is mm/dd/yyyy where
* d= Date, m= Month and y = Year.
* Limitation: The passed date variable should have date in 
* format as: mm/dd/yyyy. This is critical, as formats like dd/mm/yyyy can cause
* unpredictable date values to be returned.
*
* @param varDate, variable with date to be incremented.
* @param optional, incValue, Number of days the date to be incremented/ decremented by
*/
Selenium.prototype.doIncrementDate = function(varDate, incValue) {
	try {
		// Get the string value of date
		var varDateVal = Selenium.prototype.replaceVariables("\${"+varDate+"}");
		
		// Convert the string to Date object
		var parsedDate = new Date(varDateVal);
		if (parsedDate.toString() == "Invalid Date"){
			throw new SeleniumError("Failed to parse string as date. Invalid dateformat.");
		}
		
		var milliSecs = 0;
		
		// Check if increment value entred
		if (!incValue){
			milliSecs = 86400000;
		} else {
		
			// Parse the increment value provided
			var newIncVal = parseInt(incValue);
			
			//Check if output is NaN, if so throw error.
			if(isNaN(newIncVal)){
				throw new SeleniumError("Provided increment value is not a number.");
			}
			
			// Get the number of milliseconds for the days
			milliSecs = newIncVal*86400000;
		}
		
		// Calculate the new date
		var newDate = new Date(parsedDate.getTime()+milliSecs);
		
		// Set the default date format
		newDate = Selenium.prototype.doFormatDate(newDate,mySetDateFormat);
		
		//Set the date to the provided variable
		storedVars[varDate] = newDate;
		
	} catch(e) {
		throw new SeleniumError("Exception occured in incrementDate: "+e.message);
	}
}

/**
* Gets the system date, Increments / decrements it by the provided
* increment value. Increment value is always 'number of Days'.
* 
* This method is added specifically to overcome the limitations of
* incrementDate and reFormatDate methods.
*
* @param varDate, variable to set date value to.
* @param incValue, Number of days the date to be incremented/decremented by
*/
Selenium.prototype.doGetIncrementedSysDate = function(varDate, incValue) {
	try {
		// Get the system date in default format
		Selenium.prototype.doGetSysDate(varDate,null);
		
		// Increment it by passed increment value.
		Selenium.prototype.doIncrementDate(varDate, incValue);
	
	} catch(e) {
		throw new SeleniumError("Exception occured in incrementDate: "+e.message);
	}
}