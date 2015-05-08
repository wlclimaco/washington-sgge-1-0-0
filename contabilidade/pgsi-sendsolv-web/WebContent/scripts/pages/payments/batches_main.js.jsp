<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>

<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN')">

<script type="text/javascript">
/**
 * @namespace pgsi.pages.payments
 * @fileoverview The main namespace for the Batch List Page.
 */

pgsi.pages.batches = {

	/**
	* Set of functions used by the datatables plugin to customize field values
	*/

	fnLinkId : function (val, type, full)
	{

		if (type !== "display")
		{
			return val;
		}
		if ($.pgsi.isNullOrUndefined(full.key)){
			return '<a title="View/Edit ' + full.id + '" href="#/payment/batches_view?batchesId=' + full.id + '" class="edit_link">' + full.id + '</a>';
		}else{
			var input  = document.createElement("input");
			input.type = "text";
			input.value  = full.key;
			var $input = $(input);
			$input.mask('***-99999');
			return '<a title="View/Edit ' + $input.val() + '" href="#/payment/batches_view?batchesId=' + full.id + '" class="edit_link">' + $input.val() + '</a>';
		}
	},

	// Returns link for edit view
	fnCreateLocationNameLink : function (val, type, full)
	{

		if (type !== "display")
		{
			return val;
		}
			return '<a title="View/Edit ' + full.location.name + '" href="#/location/view?tab=info&locationId=' + full.location.id + '">' + full.location.name + '</a><a title="View"' + full.location.parentOrganizationName + ' href="#/organization/view?organizationId=' + full.location.parentOrganizationId + '">&nbsp(' + full.location.parentOrganizationName + ') </a>';
	},

	fnBatcheStatus : function (val, type, full)
	{
		if (type !== "display")
		{
			return val;
		}

		if(full.currentStatus.status == "PENDING_APPROVAL"){
			return '<td class="status urg-1">'+$.pgsi.enums.internationalizeByLabel("com.prosperitasglobal.sendsolv.model.MoneyTransferBatchStatusEnum",full.currentStatus.status)+'</td>'
		}else{
			return $.pgsi.enums.internationalizeByLabel("com.prosperitasglobal.sendsolv.model.MoneyTransferBatchStatusEnum",full.currentStatus.status)
		}
	},
	fnOriginAmount : function (val, type, full)
	{

		if (type !== "display")
		{
			return val;
		}

		var input = document.createElement("input");
		input.type = "text";
		input.dataset.inputmask = "'alias': 'numeric', 'groupSeparator': ',', 'autoGroup': true, 'digits': 2, 'digitsOptional': false, 'prefix': '', 'placeholder': '0'";
		input.value = full.originAmount.amount;
		var $input = $(input);
		$input.inputmask();

		if(full.originAmount.amount != ""){
			return $input.val()
		}else{
			return "0"
		}
	},
	fnActionDue : function (val, type, full)
	{
		if (type !== "display")
		{
			return val;
		}

		if(full.currentStatus.status == "PENDING_APPROVAL"){
			return '<td class="status urg-1">'+$.pgsi.date.format(new Date(full.payrollReceivedDate), "mm/dd/yy", null)+'</td>'
		}else{
			return $.pgsi.date.format(new Date(full.payrollReceivedDate), "mm/dd/yy", null)
		}
	},

	fnCreateBatch : function (val, type, full)
	{
		if (type !== "display")
		{
			return val;
		}
		return $.pgsi.date.format(new Date(full.createDateUTC), "mm/dd/yy", null)
	},

	fnTransferDate : function (val, type, full)
	{
		if (type !== "display")
		{
			return val;
		}
		return $.pgsi.date.format(new Date(full.transferDate), "mm/dd/yy", null)
	},

	fnTotalTransfer : function (val, type, full)
	{
		if (type !== "display")
		{
			return val;
		}
		if (!$.pgsi.isNullOrUndefined(full.moneyTransferList[0])){
			return full.moneyTransferList.length;
		}else{
			return 0;
		}
	},

	fnTotalTransferErrorPend : function (val, type, full)
	{
		var iTotalErroPend=0;

		if (type !== "display")
		{
			return val;
		}

		if (!$.pgsi.isNullOrUndefined(full.moneyTransferList[0])){

			for(var i = 0;i<full.moneyTransferList.length;i++){
				if (!$.pgsi.isNullOrUndefined(full.moneyTransferList[i].currentStatus)){
					if((full.moneyTransferList[i].currentStatus.status == "ON_HOLD")||(full.moneyTransferList[i].currentStatus.status == "EXCEPTION")){
						iTotalErroPend = iTotalErroPend + 1;
					}
				}
			}
			return iTotalErroPend;
		}else{
			return 0;
		}
	},

	fnRegion : function (val, type, full)
	{
		var sAddress = "";
		if (type !== "display")
		{
			return val;
		}
		if (!$.pgsi.isNullOrUndefined(full.location.contactList[0])){
			for(var i =0;i<full.location.contactList.length;i++){
				if (full.location.contactList[i].type == "address"){
					sAddress = full.location.contactList[i].cityName +", "+ full.location.contactList[i].stateProvinceRegion.description.trim() +", "+full.location.contactList[i].country.code

				}
			}

		}
		return sAddress;
	},

	fnNoteList : function (val, type, full)
	{
		var sAddress = "";
		if (type !== "display")
		{
			return val;
		}
		if (!$.pgsi.isNullOrUndefined(full.noteList[0])){
			return full.noteList[0].noteText;
		}else{
			return ""
		}
	},

	batchTable: {

	},

	fnMoneyTransferBatchStatusListFromTable : function(sStatus) {
		var oMoneyTransferBatchStatusList = new Array();
		var oMoneyTransferBatchStatus;
		var oSettings = new Array();
		var iTime = (new Date()).getTime();

		oSettings = $.pgsi.table.checkbox(pgsi.pages.batches.batchTable).selected();

		for (var i = 0; i < oSettings.length; i++) {
			oMoneyTransferBatchStatus = new MoneyTransferBatchStatus({
				id : null,
				moneyTransferBatchId : oSettings[i],
				actionDueDate : iTime,
				status : sStatus,
				modelAction : "INSERT"
			});

			oMoneyTransferBatchStatusList.push(oMoneyTransferBatchStatus);
		};

		return oMoneyTransferBatchStatusList;

	}

}
</script>

</sec:authorize>