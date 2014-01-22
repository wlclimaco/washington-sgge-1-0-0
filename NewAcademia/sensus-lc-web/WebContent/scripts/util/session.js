/**
 * @description Create, Read and Delete Session
 * @version     -
 * @file        session.js
 * @author      Miqueias de Oliveira Gomes (miqueias_gomes@qat.com)
 *
 * @copyright Copyright 2012 QAT Global, all rights reserved.
 *
 */

sensus.util.session = {

	/**
	 * @param sTypeOfElement
	 * 			string, url
	 * @param objData
	 * 			object, data object
	 * @return sendAjaxRequest
	 * 			function to do ajax call
	 */
	createSession : function (sTypeOfElement, objData, sPolygonId, objPolygon) {
		return sensus.util.session.sendAjaxRequest(sTypeOfElement, objData, "api/session/insert", sPolygonId, objPolygon);
	},

	/**
	 * @param sTypeOfElement
	 * 			string, type of customization
	 * @return sendAjaxRequest
	 * 			function to do ajax call
	 */
	readSession : function (sTypeOfElement) {
		return  sensus.util.session.sendAjaxRequest(sTypeOfElement, null, "api/session/fetch");
	},

	/**
	 * @param sTypeOfElement
	 * 			string, type of customization
	 * @return sendAjaxRequest
	 * 			function to do ajax call
	 */
	removeSessionCustomize : function (sTypeOfElement) {
		return sensus.util.session.sendAjaxRequest(sTypeOfElement, null, "api/session/remove");
	},

	/**
	 * @param sTypeOfElement
	 * 			string, type of customization
	 * @param oData
	 * 			object, data object
	 * @param sUrl
	 * 			string, url
	 * @return oResult
	 * 			object, data object
	 */
	sendAjaxRequest : function (sTypeOfElement, oData, sUrl, sPolygonId, oPolygon) {

		if (!sTypeOfElement && !sUrl) {
			return null;
		}

		var oResult = null;

		$.ajax({
			type         : "POST",
			async        : false,
			url          : sUrl,
			dataType     : 'json',
			data         : $.toJSON({
				'elementType'          : sTypeOfElement,
				'data'  : oData
				//'polygonId'			   : sPolygonId,
				//'polygon'			   : oPolygon
			}),
			contentType  : "application/json; charset=utf-8",
			success      : function(oResponse) {

				if (oResponse && !oResponse.operationSuccess) {

					return;
				}

				oResult = oResponse;

			}
		});

		return oResult;
	}
};