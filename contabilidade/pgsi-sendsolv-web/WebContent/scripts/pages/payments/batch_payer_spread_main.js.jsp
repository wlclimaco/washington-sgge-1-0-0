<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>

<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN')">
<script type="text/javascript">
/**
 * @namespace pgsi.pages.payments
 * @description The main namespace for the Spread Review Page.
 * @author Flavio Tosta
 */

pgsi.pages.payments.spreads = {
	table : {
		fnDisplayPayerName : function(val, type, full) {
			if (type !== "display")
			{
				return val;
			}
			return '' + full.transferSetting.productPlanApplicability.payer.name;
		},

		fnDisplayEffectiveRate : function(val, type, full) {
			if (type !== "display")
			{
				return val;
			}
			return full.foreignExchangeRate;
		},

		fnDisplayCustomerRate : function(val, type, full) {
			if (type !== "display")
			{
				return val;
			}
			return full.foreignExchangeRate - ((full.spreadPercentage/100) * full.foreignExchangeRate);
		},

		fnDisplaySpreadInput : function(val, type, full) {
			if (type !== "display")
			{
				return val;
			}
			return "<input data-transferId='" + full.id + "' name='spreadPercentage" + full.id + "' value='" + full.spreadPercentage + "' type='text'>&nbsp%";
		},

		oTable : {},

		fnGetSelectedRows : function(){

			var aSelectedRows;
			var aRowsIndexes;
			var sRowsSelectors = "";
			var aMoneyTransfersSelected = new Array();
			var oMoneyTransfer = new Object;
			var $row;

			aRowsIndexes = $.pgsi.table.checkbox(pgsi.pages.payments.spreads.table.oTable).selected();

			if (aRowsIndexes.length === 0) {
				return new Array();
			}

			for (var i=0; i < aRowsIndexes.length; i++) {
				sRowsSelectors += "input[value='" + aRowsIndexes[i] + "']";
				if (i !== (aRowsIndexes.length - 1)) {
					sRowsSelectors += ",";
				}
			}

			aSelectedRows = $("#dialog_data_list").find(sRowsSelectors).parents('tr');

			// Create Money Transfer array with table data
			for (var i=0; i < aSelectedRows.length; i++) {
				$row = $(aSelectedRows[i]);
				oMoneyTransfer.spreadPercentage = parseFloat($row.find('td:eq(4) input').val());
				oMoneyTransfer.foreignExchangeRateCustomer = parseFloat($row.find('td:eq(5)').text());
				aMoneyTransfersSelected[parseFloat($row.find('td:eq(0) input').val())] = oMoneyTransfer;
			}

			return aMoneyTransfersSelected;
		},

		fnMatchRows : function(aMoneyTransfersSelected, aaData){
			var iIndex;
			var oMoneyTransfer = new Object;
			var aMoneyTransfers = new Array();

			// Create Money Transfer array with aaData
			for (var i=0; i < aaData.moneyTransferList.length; i++) {
				iIndex = aaData.moneyTransferList[i].id;
				if (typeof aMoneyTransfersSelected[iIndex] != "undefined") {
					oMoneyTransfer 				 				= aaData.moneyTransferList[i];
					oMoneyTransfer.spreadPercentage 			= aMoneyTransfersSelected[iIndex].spreadPercentage;
					oMoneyTransfer.foreignExchangeRateCustomer  = aMoneyTransfersSelected[iIndex].foreignExchangeRateCustomer;
					oMoneyTransfer.modelAction					= "UPDATE";
					delete oMoneyTransfer.currentStatus;
					aMoneyTransfers.push(oMoneyTransfer);
				}
			}

			return aMoneyTransfers;
		}
	},

	view : {
		fnInit : function(oBatch) {
			$("#spreadReviewDialog").find('.btn').button();

			var oPrimaryContact = $.grep(oBatch.moneyTransferBatchList[0].location.contactList, function(e){ if (e.contactType === "POSTAL_WORK") { return true; }});

			oPrimaryContact = oPrimaryContact[0];

			if (!$.pgsi.isNullOrUndefined(oPrimaryContact)){
				var sLabel = oBatch.moneyTransferBatchList[0].location.name + "(" + oBatch.moneyTransferBatchList[0].location.parentOrganizationName + ") " + oPrimaryContact.cityName + ", " + oPrimaryContact.stateProvinceRegion.code + oPrimaryContact.country.code;
			}else{
				var sLabel = oBatch.moneyTransferBatchList[0].location.name + "(" + oBatch.moneyTransferBatchList[0].location.parentOrganizationName + ") ";
			}


			$("#spreadReviewDialog").find('div').first().find('b').text(sLabel);

			$('#applyToSelection').on('click', function(e) {
     			e.preventDefault();
     			var sCustomSpread = $('#customSpread').val();
     			var $tr;
     			var dForeignExchangeRate;
     			var dForeignExchangeRateCustomer;
     			var dSpread;

			    if (sCustomSpread.length > 0) {
			    	var aSelectedRows = $.grep($('#dialog_data_list').find('tbody tr'), function(e) {
			    	 		if ($(e).find('td:eq(0) input').prop('checked')) { return true; }
			    	});

			    	for (var i=0; i < aSelectedRows.length; i++) {

			    		$tr = $(aSelectedRows[i]);
			    		$tr.find('td:eq(4) input').val(sCustomSpread);
			    		dForeignExchangeRate = parseFloat($tr.find('td:eq(3)').text());
			    		dSpread = parseFloat($tr.find('td:eq(4) input').val());
			    		dForeignExchangeRateCustomer = parseFloat($tr.find('td:eq(5)').text());

						dForeignExchangeRateCustomer = 	dForeignExchangeRate - ((dSpread/100) * dForeignExchangeRate);

						if (!isNaN(dForeignExchangeRateCustomer)) {
							$tr.find('td:eq(5)').text(dForeignExchangeRateCustomer);
						}
			    	}
			    }
			});

			$('#resetSelection').on('click', function(e){
				e.preventDefault();
			    var aSelectedRows = $.grep($('#dialog_data_list').find('tbody tr'), function(e) {
			    	 		if ($(e).find('td:eq(0) input').prop('checked')) { return true; }
			    	});

			    for (var i=0; i < aSelectedRows.length; i++) {
			    	$tr = $(aSelectedRows[i]);
			    	$tr.find('td:eq(4) input').val('');
			    	$tr.find('td:eq(5)').text($tr.find('td:eq(3)').text());
			    }
			});

			$('#dialog_data_list tbody input[name^="spreadPercentage"]').on('blur', function(e) {
				var dSpread = parseFloat($(this).val());
				var dForeignExchangeRate = parseFloat($(this).parent().prev().text());
     			var dForeignExchangeRateCustomer;

				if (!isNaN(dSpread) && !isNaN(dForeignExchangeRate)) {
					dForeignExchangeRateCustomer = dForeignExchangeRate - ((dSpread/100) * dForeignExchangeRate);
					dForeignExchangeRateCustomer = parseFloat(dForeignExchangeRateCustomer);
					if (dForeignExchangeRateCustomer) {
						$(this).parent().next().text(dForeignExchangeRateCustomer);
					}
				}
			});
		}
	}
};

</script>
</sec:authorize>