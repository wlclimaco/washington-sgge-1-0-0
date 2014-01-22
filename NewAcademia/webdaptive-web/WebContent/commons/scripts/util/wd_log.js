var wdl = {};

/** @ignore internal use only */
wdl.LOG_LEVEL = 0;

/** Log Level Trace Constant */
wdl.LOG_LEVEL_TRACE=0;
/** Log Level Debug Constant */
wdl.LOG_LEVEL_DEBUG=1;
/** Log Level Info Constant */
wdl.LOG_LEVEL_INFO=2;
/** Log Level Warn Constant */
wdl.LOG_LEVEL_WARN=3;
/** Log Level Error Constant */
wdl.LOG_LEVEL_ERROR=4;
/** Log Level None Constant */
wdl.LOG_LEVEL_NONE=5;

//Mapping to the string representations of different log levels
/** @ignore internal use only */
wdl.LOG_LEVELS = new Array("TRACE", "DEBUG", "INFO", "WARN", "ERROR", "NONE");

//Represents whether or not the logger has been initialized
/** @ignore internal use only */
wdl.LOG_INITIALIZED = false;

//Holds the log window
/** @ignore internal use only */
wdl.LOG_WINDOW = null;


/** 
 * Initializes the logger, with the logLevel parameters. This method is declared, initialized and loglevel set in the rbconfig/rbconfig.js and used fro the whole Web UI.
 * @param {string} logLevel Logging Level which the logger is set for. 
 * wdl.LOG_LEVEL_TRACE,wdl.LOG_LEVEL_DEBUG,wdl.LOG_LEVEL_INFO,wdl.LOG_LEVEL_WARN,wdl.LOG_LEVEL_ERROR or wdl.LOG_LEVEL_NONE are the only valid values.     
 * @example
 * 
 *	 top.wdl.initializeLogger(top.LOG_LEVEL_TRACE);  //Sets the logger to the trace level. 
 */ 
wdl.initializeLogger = function(logLevel) 
{
	if (logLevel == null) 
	{
		logLevel = wdl.LOG_LEVEL_TRACE;
	}
	wdl.LOG_LEVEL = logLevel;
	
	try 
	{
		if (!wdl.LOG_INITIALIZED) 
		{
		}
	} 
	catch(err) 
	{
	} 
	finally 
	{
		wdl.LOG_INITIALIZED = true;
	}
	
};

/** 
 * Log at the TRACE level.
 * @param {string} logString Log message you desired to be output to the trace level.     
 * @example
 * 
 *	 top.wdl.trace("WDTab,changeURL()");  //Will log a message at the trace level. 
 *   The logger must be initialized at the trace for this message to output. 
 */ 
wdl.trace = function(logString) 
{
	wdl.log(logString, wdl.LOG_LEVEL_TRACE);
};

/**
 * Log at the DEBUG level
 * @param {string} logString Log message you desired to be output to the debug level.     
 * @example
 * 
 *	 top.wdl.debug("WDTab,changeURL()");  //Will log a message at the debug level. 
 *   The logger must be initialized at the debug or lesser level for this message to output. 
 */ 
wdl.debug = function(logString) 
{
	wdl.log(logString, wdl.LOG_LEVEL_DEBUG);
};

/**
 * Log at the INFO level
 * @param {string} logString Log message you desired to be output to the info level.     
 * @example
 * 
 *	 top.wdl.info("WDTab,changeURL()");  //Will log a message at the info level. 
 *   The logger must be initialized at the info or lesser level for this message to output. 
 */
wdl.info = function(logString) 
{
	wdl.log(logString, wdl.LOG_LEVEL_INFO);
};

/**
 * Log at the WARN level
 * @param {string} logString Log message you desired to be output to the warn level.     
 * @example
 * 
 *	 top.wdl.warn("WDTab,changeURL()");  //Will log a message at the warn level. 
 *   The logger must be initialized at the warn or lesser level for this message to output. 
 */
wdl.warn = function(logString) 
{
	wdl.log(logString, wdl.LOG_LEVEL_WARN);
};

/**
 * Log at the ERROR level
 * @param {string} logString Log message you desired to be output to the error level.  
 * @param {string} alertFlag Whether push out an alert box with this message. 
 * @param {string}logFlag Whether to wrote to the log with this error message.       
 * @example
 * 
 *	 top.wdl.error("WDTab,changeURL()","Y","Y");  //Will log a message at the error level and pop-up an alert box.
 *   The logger must be initialized at the error or lesser level for this message to output. 
 */
wdl.error = function(logString,alertFlag,logFlag) 
{
	if (alertFlag == "Y")
	{
		alert(logString);
	}
	if (logFlag == "Y")
	{
		 wdl.log(logString, wdl.LOG_LEVEL_ERROR);
	}	 	
};

/**
 * Helper method to flatten a JavaScript Object for log display purposes only.
 * @param {object} obj Object you would like displayed.  
 * @param {string} indent As the object is flattened how many spaces to ident for readability (default is 4). 
 * @example
 * 
 * top.wdl.trace("WDClient,_getTypesFromWsdl()-results " + top.wdl.dumpObject(someObject));
 */
wdl.dumpObject = function(obj,indent) 
{
	if (!indent) { indent="";}
	if (indent.length>20) { return ; } // don't go too far...
	var s="{\n";
		for (var p in obj) {
			s+=indent+p+":";
			var type=typeof(obj[p]);
			type=type.toLowerCase();
			if (type=='object') {
				s+= wdl.dumpObject(obj[p],indent+"-");
			} else {
				s+= obj[p];
			}
			s+="\n";
		}
		s+=indent+"}";
		return s;
}

/** @ignore internal use only
 *  Write out the log string to the logger pop-up window
 */
wdl.log = function(logString, logLevel) 
{
		//Only log to console if logger has been initialized
		if (wdl.LOG_INITIALIZED && logLevel >= wdl.LOG_LEVEL) 
		{
			if (wdl.LOG_WINDOW == null || wdl.LOG_WINDOW == 'undefined' || !wdl.LOG_WINDOW.window.document) 
			{
				wdl.LOG_WINDOW = window.open("",'wd_logger','width=710,height=500,scrollbars=1,status=0,toolbars=0,resizable=1');
				wdl.LOG_WINDOW.moveTo(0, 0);
				if (!wdl.LOG_WINDOW.document.getElementById('loggerTable')) 
				{
					wdl.LOG_WINDOW.document.writeln("<table STYLE='table-layout: fixed;' align='center' cellSpacing='0' cellPadding='0' width='660' border='1' id='loggerTable'><tr><caption><b><u>Web UI Log Console</u></b></caption></table>");
					wdl.LOG_WINDOW.document.close();
				}
			}
			var tbl = wdl.LOG_WINDOW.document.getElementById("loggerTable");
			var row = tbl.insertRow(-1);
			var cell_1 = row.insertCell(-1);
	
			var d = new Date();
			var h = d.getHours();
			if (h<10) { h="0"+h; }
			var m = d.getMinutes();
			if (m<10) { m="0"+m; }
			var s = d.getSeconds();
			if (s<10) { s="0"+s; }
			var ms = d.getMilliseconds();
			var date = (d.getMonth()+1)+"/"+d.getDate()+"/"+d.getFullYear()+"&nbsp;-&nbsp;"+h+":"+m+":"+s+":"+ms;
	
			cell_1.style.fontSize="8pt";
			if (tbl.rows.length % 2 == 0) 
			{
				cell_1.style.backgroundColor="#eeeeee";
			}
			cell_1.innerHTML=date + ", " + wdl.LOG_LEVELS[logLevel] + ", " + logString;
		}
}
