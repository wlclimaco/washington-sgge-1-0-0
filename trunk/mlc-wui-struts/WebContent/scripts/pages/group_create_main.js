/**
 * @namespace sensus.pages.group
 * @description The creat-main namespace for the Group Page.
 */

/**
 * @fileoverview Defines the core functionality of the Create Group page.
 * @author Cristiane Cobo
 */

/**
 * The main group namespace containing group-related functions.
 */
sensus.pages.group = {
	/**
	 * Contains query templates for HTTP requests.
	 */
	query : {
		/**
		 * Template for GET request returning to main group page. The
		 * group parameter will cause a message to be displayed.
		 */
		group : "{0}?group={1}&groupId={2}",
		/**
		 * Json template for create group Ajax call. While most of the
		 * parameters will contain numeric or string values, the events property
		 * will be set to another Json string (an array of event objects) that
		 * is constructed separately.
		 */
		create : "{'groupName':'{0}','groupDescription':'{1}','groupId':{2},'groupOldName':'{3}'}"
	},

	/**
	* Ajax call that will submit the request ajax
	*/

	ajaxCall : function (sUrlAdress) {

		var sGroupName = $("#group-name").val();
		var nGroupId = $("#group-id").val();
		var sGroupDescription = $("#group-description").val().length ? $("#group-description").val() : "";
		var sOldName = $("#group-old-name").val().length ? $("#group-old-name").val() : null;
		var sMessage = "";
		var sAction = 'INSERT';

		if(nGroupId){

			sAction = 'UPDATE';

		} else {

			nGroupId = null;

		}
		sensus.util.page.startProgressBar(null,true);
		var request = {'groupRequest':new groupRequest(sGroupName, sGroupDescription, sOldName, nGroupId, sAction, false, null)};
		$.ajax( {
			url : sUrlAdress,
			dataType: 'json',
			contentType : "application/json; charset=utf-8",
			type: "POST",
			data : $.toJSON(request),
			/**
			 * The success handler. This will be invoked if the HTTP request
			 * returned correctly. The status of the reuested operation
			 * needs to be determined from the response object.
			 */
			success : function(data) {
				/* If successful, load the main group page */
				if (data.operationSuccess) {

					if (nGroupId == null) {

						sMessage = sensus.locale.get("action.addsgroup.success", [ sGroupName, sensus.settings.smartpointUrl ]);

					} else {

						sMessage = sensus.locale.get("action.updategroup.success", [ sGroupName, sensus.settings.smartpointUrl ]);

					}

					sensus.commons.lib.ajax.do_load($.extend({}, sensus.commons.lib.ajax.param, {
						sUrl : 'group/ajax.list.action',
						message : {
							bMessage : true,
							sMessage : sMessage
						}
					}));

				} else {

					/* Show error message */
					sensus.util.page.stopProgressBar(0,true);
					sensus.util.page.showMessage("messaging-main", data.messageList[0].text, "error");

				}

			}
		/*
		 * At this point there is no error handler. We should consider
		 * entering one. It would take effect if the provided url is not
		 * available or the request returns any other HTTP error.
		 */
		});
	}
};
