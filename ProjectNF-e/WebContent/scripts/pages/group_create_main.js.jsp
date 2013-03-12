<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<sec:authorize access="hasAnyRole('ROLE_Role.Admin', 'ROLE_Role.System Operator', 'ROLE_Role.Customer Support', 'ROLE_Role.Billing Manager')">
<script type="text/javascript">
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
sensus.pages.groupInsert = {

	/**
	 * Ajax call to get data from a group
	 * This method will get the data and populate the fields of form.
	 *
	 * @param {Object} data
	 * 			The Object containing data of group
	 */
	fnFillGroup : function(data){

		if((!$.sc.isNullOrUndefined(data.groups)) && (!$.sc.isNullOrUndefined(data.groups[0]))){

			var sGroupName = $.sc.isNullOrUndefined(data.groups[0].name) ? '' : data.groups[0].name,
				sGroupDescription = $.sc.isNullOrUndefined(data.groups[0].description) ? '' : data.groups[0].description;

			$('#group-name').val(sGroupName);
			$('#group-old-name').val(sGroupName);
			$('#group-description').val(sGroupDescription);
			$('#title-edit-group span.group-name').text(sGroupName)
		}

	},

	/**
	 * Ajax call that will submit the request ajax
	 *
	 * @param {String} sUrlAdress
	 * 			The URL to post
	 * @param {String} sMessage
	 * 			Message that will display after the ajax call
	 */
	ajaxCall : function (sUrlAdress,sMessage) {

		//Starts the progress bar
		$.sc.startProgressBar(null,false);

		var sGroupName = $("#group-name").val();
		var sGroupNameOld = $('#group-old-name').val();
		var nGroupId = $("#group-id").val();
		var sGroupDescription = $("#group-description").val().length ? $("#group-description").val() : "";
		var sOldName = $("#group-old-name").val().length ? $("#group-old-name").val() : null;
		var iGroupId = $.address.parameter('groupId');


		//Create request of creating or update
		if (iGroupId) {
			var request = {groupName: sGroupName,groupNameOld: sGroupNameOld,groupDescription: sGroupDescription, groupId:iGroupId};
		}else{
			var request = {groupName: sGroupName,groupDescription: sGroupDescription};
		}


		//Function that will execute after the call ajax
		var fnCallBack = function(data) {

			if (data && data.operationSuccess) {

				$.sc.getPage($.extend({}, sensus.commons.lib.ajax.param, {
					sUrl     : 'group',
					message  : {
						bMessage : true,
						sMessage : sMessage,
						status : 'success'
					}
				}));
			}
		};

		//Send the request for controller, from the url received
		$.sc.getJson(sUrlAdress, request, false, fnCallBack);
	}
};
</script>
</sec:authorize>