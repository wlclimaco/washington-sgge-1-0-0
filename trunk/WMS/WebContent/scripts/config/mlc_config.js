// Vars setting WebDaptive Web Settings
//Web UI Host
var WDHost = "auto";
var WDContext = "qat-webdaptive";
if (WDHost == "auto") {
    WDHost = top.location.href.substring(0, top.location.href.indexOf(WDContext));
}
//WebDaptive Base URL																								
var WDurl = WDHost + WDContext + "/";

//Vars for i18n or Internationalization
var WDi18nPropFile = "messages";														// Base name of the internationalized properties file
var WDi18nPropFilePath = "WEB-INF/classes/locale/";												// Relative path for the properties file 
//var WDi18nPropFilePath = "scripts/locale/";												// Relative path for the properties file
var WDi18nLanguage = "en_US";															// Provided language and country codes (ISO-639 and ISO-3166)

//Vars for Header
var WDLoginDateFormat = "EEE, MMM dd, yyyy hh:mm:ss a";									//Header Date Format

//Vars for Session Management for RIA
//In seconds so 600 = 10 minutes
var WDIdleTimeout = 600;
var WDPollingInterval = 100;
var WDWarningLength = 30;

//WebDaptive Session Service URL                                 							
//var WDSessURL = WDHost + "qat-wdservices/wdservices/WDSessionService";
//RBClient responsemsg suffix - see documentation for further details
var WDWSResp = "Return";

// Initialize Logger to a certain level
//LOG_LEVEL_TRACE
//LOG_LEVEL_DEBUG
//LOG_LEVEL_INFO
//LOG_LEVEL_WARN
//LOG_LEVEL_ERROR
//LOG_LEVEL_NONE
//wdl.initializeLogger(wdl.LOG_LEVEL_NONE); 
//-->