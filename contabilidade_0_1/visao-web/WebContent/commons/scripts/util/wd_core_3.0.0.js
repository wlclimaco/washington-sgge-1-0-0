/*

* WebDaptive Global Objects, Namespace
*/

/*
*#######################
* WebDaptive LAYOUT SETTINGS
*#######################
*
* This configuration illustrates how extensively the layout can be customized
* ALL SETTINGS ARE OPTIONAL - and there are more available than shown below
*
* These settings are set in 'sub-key format' - ALL data must be in a nested data-structures
* All default settings (applied to all panes) go inside the defaults:{} key
* Pane-specific settings go inside their keys: north:{}, south:{}, center:{}, etc
*/
var layoutSettings_WebDaptive =
{
	name: "webdaptiveLayout" // NO FUNCTIONAL USE, but could be used by custom code to 'identify' a layout
	//	reference only - these options are NOT required because 'true' is the default
	,	closable:				true	// pane can open & close
	,	resizable:				true	// when open, pane can be resized
	,	slidable:				true	// when closed, pane can 'slide' open over other panes - closes on mouse-out
	,	togglerTip_open:		"Close This Pane"
	,	togglerTip_closed:		"Open This Pane"
	,	resizerTip:				"Resize This Pane"

	//	north or header settings
	,	north__resizable:		false	// OVERRIDE the pane-default of 'resizable=true'
	,   north__slidable:        false   // OVERRIDE the pane-default of 'slidable=false'
	,	north__spacing_open:	0		// no resizer-bar when open (zero height)
	,	north__minSize:			65
	,	north__maxSize:			65
	// south or footer settings
	,	south__resizable:		false	// OVERRIDE the pane-default of 'resizable=true'
	,   south__slidable:        false   // OVERRIDE the pane-default of 'slidable=false'
	,	south__spacing_open:	0		// no resizer-bar when open (zero height)
	,	south__minSize:			15
	,	south__maxSize:			15
	// west or navigation settings
	,	west__size:				250
	,	west__showOverflowOnHover:  true
	,	west__onresize:		$.layout.callbacks.resizePaneAccordions
};


//Simple subscription model
function EventHelper()
{
	this.handlers = [];

	this.subscribe = function(fn)
	{
        this.handlers.push(fn);
    };

	this.notify = function(args)
	{
        for (var i = 0; i < this.handlers.length; i++)
		{
            this.handlers[i].call(this, args);
        }
    };

	return this;
};

//WebDaptive Core Namespace, Functions & Variables
var wd =
{
	session:
	{
		"sessionId"				: null,
		"userId" 				: null,
		"userPassword" 			: null,
		"authorities"			: null
	},
	core:
	{
		showTime: function()
		{
			var TimerKey;
			$('#time').html($.formatter.date(new Date(), WDLoginDateFormat));
			TimerKey = setTimeout(wd.core.showTime,1000);
		},
		displayNotificationMessage: function(selector, messageText, bPermanent, messageType, messageTime)
		{
			$(selector).jnotifyAddMessage({
				text: messageText,
				permanent:  bPermanent,
				disappearTime: messageTime,
				type: messageType
			});
		},
		blockUIMessage: function(selector, bTheme, bDraggable, messageTitle, messageText)
		{
			 $(selector).block({
					theme		: bTheme,
					draggable	: bDraggable,
					title		: messageTitle,
					message		: messageText
			});
		},
		unblockUIMessage: function(selector)
		{
			$(selector).unblock();
		},
		logOut: function()
		{
			window.location.href = WDurl + "j_spring_security_logout";
		},
		containsValue: function(a, e)
		{
			for(j=0;j<a.length;j++)
			{
				if(a[j]==e)
				{
					return true;
				}
			}
			return false;
		},
		isEmpty: function(value)
		{
			if(value == null || value == undefined || !value.length || value == "")
			{
				return true;
			}
			else
			{
				return false;
			}
		},
		removeLeadZeros: function(value)
		{
		    while (value.length > 1 && value.substring(0, 1) == '0')
		    {
		    	value = value.substring(1, value.length);
		    }
		    return value;
		},
		replaceCharacters: function(s, oldVal, newVal)
		{
		    return s.replace(eval("/" + oldVal + "/g"), newVal);
		},
		leftTrim: function(sString)
		{
		    if (sString == null) { return ""; }
		    while (sString.substring(0, 1) == ' ')
		    {
		        sString = sString.substring(1, sString.length);
		    }
		    return sString;
		},
		rightTrim: function(sString)
		{
		    if (sString == null)
		    {
		    	return "";
		    }
		    while (sString.substring(sString.length - 1, sString.length) == ' ')
		    {
		        sString = sString.substring(0, sString.length - 1);
		    }
		    return sString;
		},
		trimAll: function(sString)
		{
		    return sString == null ? "" : sString.replace(/(^\s+|\s+$)/g, '');
		},
		isTrue:  function (value)
		{
			return /^true$/i.test(value);
		},
		pad:  function(pad_str, pad_char, pad_length, pad_right)
		{
			var result = pad_str;
			if( (typeof pad_char === 'string') && (pad_char.length === 1) && (pad_length > pad_str.length) )
			{
				var padding = new Array(pad_length - pad_str.length + 1).join(pad_char);
				result = (pad_right ? result + padding : padding + result);
			}
			return result;
		},
		process_xhr_error: function (jqXHR, textStatus, errorThrown)
		{
			var msgOut = wdajax.error.msg  + " " + textStatus + "," + errorThrown;
			wd.core.displayNotificationMessage('#StatusBar',msgOut, true, 'error', 5000);
		},
		getURLParameter: function(strURL, strParm)
		{
		    var regexS = "[\\?&]" + strParm + "=([^&#]*)";
		    var regex = new RegExp(regexS);
		    var results = regex.exec(strURL);
		    if (results == null)
		    {
		    	return "";
		    }
		    else
		    {
		    	return results[1];
		    }
		}
	}
};