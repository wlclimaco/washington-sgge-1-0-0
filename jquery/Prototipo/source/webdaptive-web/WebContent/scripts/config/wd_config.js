// Vars setting WebDaptive Web Settings
//Web UI Host
var WDHost = "auto";
var WDContext = "retaguarda";
if (WDHost == "auto") {
    WDHost = top.location.href.substring(0, top.location.href.indexOf(WDContext));
}
//WebDaptive Base URL
var WDurl = WDHost + WDContext + "/";

//Vars for i18n or Internationalization
var WDi18nPropFile = "wd_messages";														// Base name of the internationalized properties file
var WDi18nPropFilePath = "scripts/locale/";												// Relative path for the properties file
var WDi18nLanguage = "en_US";															// Provided language and country codes (ISO-639 and ISO-3166)

//Vars for Header
var WDLoginDateFormat = "EEE, MMM dd, yyyy hh:mm:ss a";									//Header Date Format

//Vars for Session Management for RIA
//In seconds so 600 = 10 minutes
var WDIdleTimeout = 900;
var WDPollingInterval = 120;

// Initialize Logger to a certain level
//LOG_LEVEL_TRACE
//LOG_LEVEL_DEBUG
//LOG_LEVEL_INFO
//LOG_LEVEL_WARN
//LOG_LEVEL_ERROR
//LOG_LEVEL_NONE
wdl.initializeLogger(wdl.LOG_LEVEL_NONE);

//do not touch or delete declarations below
var wdLayout;
var tabval;
var treeval;
var boolAppend;
var path;
//-->