/**
 * @namespace sensus.pages.longrunningprocess
 * @description The creat-main namespace for long running process.
 */

/**
 * @fileoverview Defines the core functionality of the long running process.
 * @author Raphael Constantino
 */

/**
 * The main group page namespace containing group functions.
 */

sensus.pages.longrunningprocess = {

		/**
		 * Monitored Process Count
		 */
		lrpSize      : 0,

		/**
		 * Has Error
		 */
		hasError     : false,

		/**
		 * Is Rni online
		 */
		isRniOn      : false,

		/**
		 * Is Detail Page
		 */
		bIsDetail    : false,

		/**
		 * Process List Size
		 */
		processSize  : function(oProcess)
		{
			nSize = 0;

			if(oProcess.length)
			{
				nSize = oProcess.length;
			}

			return nSize;
		},

		/**
		 * Check if rni is online
		 */
		checkRni   : function()
		{
			$.when(this.checkRniAjax()).then(function(e) {
				var rniOnline;
				if ($.sc.isNullOrUndefined(e)){
					rniOnline = false;
				}else{
					rniOnline = e.rniOnline;
				}

				sensus.pages.longrunningprocess.isRniOn = rniOnline;

				if (!rniOnline)
				{
					$("#system-messaging").show();
					$("#rni-offline").show();
				}
				else if (rniOnline)
				{
					$("#rni-offline").hide();
				}

				if (sensus.pages.longrunningprocess.lrpSize == 0 && rniOnline)
				{
					$("#system-messaging").hide();
				}
			});
		},

		/**
		 * Call ajax check rni
		 */
		checkRniAjax : function ()
		{
			return $.sc.getJson("api/process/checkRNIStatus", new processRequest(), false, null, null, false, true, false);
		},

		/**
		 * Long running process bar settings
		 */
		longRunningProcessSystemMessaging : function ()
		{
			var fnCallBack = function(e)
			{

				if ($.sc.isNullOrUndefined(e)){
					var nLrpSize = 0;
					var processingSize = 0;
				}else{
					var nLrpSize = e.countMonitoredProcess.count_monitored;
					var processingSize = e.countMonitoredProcess.count_processing;
				}
				$("#long-running-process-size-p").text(nLrpSize);
				$("#long-running-process-size-c").text(nLrpSize);

				sensus.pages.longrunningprocess.lrpSize = nLrpSize;

				if (nLrpSize > 0)
				{
					$("#system-messaging").show();
				}
				else if (nLrpSize == 0 && sensus.pages.longrunningprocess.isRniOn)
				{
					$("#system-messaging").hide();
				}

				if (processingSize > 0)
				{
					$("#request-complete").hide();
					$("#request-processing").show();
				}
				else if (nLrpSize > 0)
				{
					$("#request-processing").hide();
					$("#request-complete").show();
				}
				else
				{
					$("#request-processing").hide();
					$("#request-complete").hide();
				}
			};

			$.sc.getJson("api/process/fetch/count", {}, false, fnCallBack, null, false, true);
		}
};