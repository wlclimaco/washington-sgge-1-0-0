<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>

<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN')">

<script type="text/javascript">
/**
 * @namespace pgsi.pages.transaction
 * @fileoverview The main namespace for the Transaction Main Page.
 */

pgsi.pages.transaction = {

	sMaskKey : '***-99999',

	sNumericMask : "'alias': 'numeric', 'groupSeparator': ',', 'autoGroup': true, 'digits': 2, 'digitsOptional': false, 'placeholder': '0'",

	fnGetSDNStatusLabel : function (oMoneyTransfer) {
		var sSDNStatus = "";
		var sMemberSDN ="",
		 sRecipientSDN ="",
		  sLocationSDN ="",
		 sOrganization ="";

		if (!$.pgsi.isNullOrUndefined(oMoneyTransfer.moneyTransferDetail)){
			if (!$.pgsi.isNullOrUndefined(oMoneyTransfer.moneyTransferDetail.member)){
				if (!$.pgsi.isNullOrUndefined(oMoneyTransfer.moneyTransferDetail.member.sdnstatus)) {
					sMemberSDN = oMoneyTransfer.moneyTransferDetail.member.sdnstatus

				}
			}
			if (!$.pgsi.isNullOrUndefined(oMoneyTransfer.moneyTransferDetail.recipient)){
				if (!$.pgsi.isNullOrUndefined(oMoneyTransfer.moneyTransferDetail.recipient.sdnstatus)) {
					sRecipientSDN = oMoneyTransfer.moneyTransferDetail.recipient.sdnstatus;
				}
			}
		}
		if((sMemberSDN == "POSITIVE") ||(sRecipientSDN == "POSITIVE")){
			sSDNStatus = 'POSITIVE';
		}else if((sMemberSDN == "PENDING_FEDERAL_INVESTIGATION") ||(sRecipientSDN == "PENDING_FEDERAL_INVESTIGATION")){
			sSDNStatus = 'PENDING_FEDERAL_INVESTIGATION';
		}else if((sMemberSDN == "PENDING_INVESTIGATION") ||(sRecipientSDN == "PENDING_INVESTIGATION")){
			sSDNStatus = 'PENDING_INVESTIGATION';
		}else if((sMemberSDN == "PENDING_NEUTRAL") ||(sRecipientSDN == "PENDING_NEUTRAL")){
			sSDNStatus = 'PENDING_NEUTRAL';
		}else{
			sSDNStatus = 'NEUTRAL';
		}

		return sSDNStatus;
	},

	fnMask : function(iValue){

		var input  = document.createElement("input");
			input.type = "text";
			input.value  = iValue;
			var $input = $(input);
			$input.mask(pgsi.pages.transaction.sMaskKey);

		return	$input.val();
	},

	fnCreateLocationLink : function (val, type, full)
	{
		var sReturn="";
		if (type !== "display")
		{
			return val;
		}

		if (full.transferSetting !== null){
			if (full.transferSetting.employmentInfo !== null){

				sReturn = sReturn + '<a  id= "edit_links" title="View/Edit ' + full.transferSetting.employmentInfo.locationName + '" href="location/view?locationId=' + full.transferSetting.employmentInfo.locationId + '" class="edit_link alist">' + full.transferSetting.employmentInfo.locationName + '</a> <a  id= "edit_links" title="View/Edit ' + full.transferSetting.employmentInfo.organizationName + '" href="organization/view?organizationId=' + full.transferSetting.employmentInfo.organizationId + '" class="edit_link alist"> ( ' + full.transferSetting.employmentInfo.organizationName + ' ) </a><br>';
			}
		}

		return sReturn;
	},
	fnTransactionLink : function (val, type, full)
	{
		var sReturn="";
		if (type !== "display")
		{
			return val;
		}
		if (!$.pgsi.isNullOrUndefined(full.key)){
			var input  = document.createElement("input");
			input.type = "text";
			input.value  = full.key;
			var $input = $(input);
			$input.mask('***-99999');
			sReturn = sReturn + '<a  id= "edit_links" title="View/Edit ' + $input.val() + '" href="transaction/view?batchId=' + full.moneyTransferBatchId + '&transactionId='+full.id+'" class="edit_link alist">' + $input.val() + '</a>' ;
		}
		else{
			sReturn = sReturn + '<a  id= "edit_links" title="View/Edit ' + full.id + '" href="transaction/view?batchId=' + full.moneyTransferBatchId + '&transactionId='+full.id+'" class="edit_link alist">' + full.id + '</a>' ;
		}

		return sReturn;
	},
	fnConfirmationLink : function (val, type, full)
	{
		var sReturn="";
		if (type !== "display")
		{
			return val;
		}

		if (full.confirmationNumber !== null){

			sReturn = sReturn + '<a  id= "edit_links" title="View/Edit ' + full.confirmationNumber + '" href="transaction/view?batchId=' + full.moneyTransferBatchId + '&transactionId='+full.id+'" class="edit_link alist">' + full.confirmationNumber + '</a>' ;

		}

		return sReturn;
	},

	fnTransactionStatus : function (val, type, full)
	{
		if (type !== "display")
		{
			return val;
		}

		if (!$.pgsi.isNullOrUndefined(full.statusList[0])){
			var sOnHoldText = "";

			if (pgsi.util.page.fnIsSDNFlagged(pgsi.pages.transaction.fnGetSDNStatusLabel(full))) {
				sOnHoldText = "[" + $.pgsi.enums.internationalizeByLabel("com.prosperitasglobal.sendsolv.model.MoneyTransferStatusEnum", "ON_HOLD") + "]";
			}

			return $.pgsi.enums.internationalizeByLabel("com.prosperitasglobal.sendsolv.model.MoneyTransferStatusEnum",full.statusList[full.statusList.length -1 ].status) + "<br>" + sOnHoldText;
		}

		else{
			return "";
		}
	},

	fnMoneyTransferBatchId : function (val, type, full)
	{
		if (type !== "display")
		{
			return val;
		}

		return  '<a  id= "edit_links" title="View/Edit ' + full.moneyTransferBatchId + '" href="payment/batches_view?batchesId=' + full.moneyTransferBatchId + '" class="edit_link alist">' + full.moneyTransferBatchId + '</a>';
	},

	fnTotalRate : function (val, type, full)
	{
		var sReturn="";
		if (type !== "display")
		{
			return val;
		}
		if (!$.pgsi.isNullOrUndefined(full.foreignExchangeRateCustomer)){
			sReturn = pgsi.util.page.fnInsertMaskNumeric(pgsi.pages.transaction.sNumericMask,full.foreignExchangeRateCustomer);
		}

		return  sReturn
	},

	fnPayerStatus : function (val, type, full)
	{
		if (type !== "display")
		{
			return val;
		}
		if (!$.pgsi.isNullOrUndefined(full.transferSetting)){
			if (!$.pgsi.isNullOrUndefined(full.transferSetting.productPlanApplicability)){
				if (!$.pgsi.isNullOrUndefined(full.transferSetting.productPlanApplicability.payer)){
					if (!$.pgsi.isNullOrUndefined(full.transferSetting.productPlanApplicability.payer.name)){
						return full.transferSetting.productPlanApplicability.payer.name;
					}
				}
			}
		}else{
			return "";
		}
	},

	fnDeliverMethod : function (val, type, full)
	{
		if (type !== "display")
		{
			return val;
		}
		if (!$.pgsi.isNullOrUndefined(full.paymentType)){
			return $.pgsi.enums.internationalizeByLabel("com.prosperitasglobal.sendsolv.model.PaymentTypeEnum", full.paymentType);
		}else{
			return "";
		}
	},

	fnMemberName : function (val, type, full)
	{
		if (type !== "display")
		{
			return val;
		}
		if (!$.pgsi.isNullOrUndefined(full.moneyTransferDetail)){
			if (!$.pgsi.isNullOrUndefined(full.moneyTransferDetail.member)){
				return full.moneyTransferDetail.member.firstName + " " + full.moneyTransferDetail.member.lastName;
			}
		}else{
			return "";
		}
	},

	fnRecipientName : function (val, type, full)
	{
		if (type !== "display")
		{
			return val;
		}
		if (!$.pgsi.isNullOrUndefined(full.moneyTransferDetail)){
			if (!$.pgsi.isNullOrUndefined(full.moneyTransferDetail.recipient)){
				return full.moneyTransferDetail.recipient.firstName + " " + full.moneyTransferDetail.recipient.lastName;
			}
		}else{
			return "";
		}
	},

	fnStartpayrollDate : function (val, type, full)
	{
		if (type !== "display")
		{
			return val;
		}
		if (!$.pgsi.isNullOrUndefined(full.transferSetting)){
			if (!$.pgsi.isNullOrUndefined(full.transferSetting.effectiveStartDate)){
				return $.pgsi.date.format(new Date(full.transferSetting.effectiveStartDate), "mm/dd/yy", null)
			}
		}else{
			return "";
		}
	},

	fnTotalUSD : function (val, type, full)
	{
		if (type !== "display")
		{
			return val;
		}
		if (!$.pgsi.isNullOrUndefined(full.originAmount)){
			if (!$.pgsi.isNullOrUndefined(full.originAmount.amount)){
				var input = document.createElement("input");
				input.type = "text";
				input.dataset.inputmask = "'alias': 'numeric', 'groupSeparator': ',', 'autoGroup': true, 'digits': 2, 'digitsOptional': false, 'placeholder': '0'";
				input.value = full.originAmount.amount;
				input = $(input);
				input.inputmask();

				return input.val();
			}
		}else{
			return "";
		}
	},

	fnCurrency : function (val, type, full)
	{
		if (type !== "display")
		{
			return val;
		}
		if (!$.pgsi.isNullOrUndefined(full.transferSetting)){
			if (!$.pgsi.isNullOrUndefined(full.destinationAmount)){
				if (!$.pgsi.isNullOrUndefined(full.destinationAmount.currency)){
					if (!$.pgsi.isNullOrUndefined(full.destinationAmount.currency.code)){
						return full.destinationAmount.currency.code +" "+pgsi.util.page.fnInsertMaskNumeric(pgsi.pages.transaction.sNumericMask,full.destinationAmount.amount);
					}
				}
			}
		}else{
			return "";
		}
	},

	fnSDNStatus : function (val, type, full) {

		if (type !== "display")
		{
			return val;
		}

		var sSDNStatus = pgsi.pages.transaction.fnGetSDNStatusLabel(full);

		sSDNStatus = "<a href='javascript:;' class='sdnstatus'>" + $.pgsi.enums.internationalizeByLabel("com.prosperitasglobal.sendsolv.sdn.model.SDNStatusEnum", sSDNStatus) + "</a>";

		return sSDNStatus;
	},

	fnTotalFEE : function (val, type, full)
	{

		var fTotalFee = full.originFlatFee;
		if (type !== "display")
		{
			return val;
		}

		if (!$.pgsi.isNullOrUndefined(full.originFlatFee)){
			if(full.originFlatFee > 0){
				if(full.originFlatFee >= full.discountAmount){
					fTotalFee  = full.originFlatFee - full.discountAmount
				}
			}
		}
		var input = document.createElement("input");
		input.type = "text";
		input.dataset.inputmask = "'alias': 'numeric', 'groupSeparator': ',', 'autoGroup': true, 'digits': 2, 'digitsOptional': false, 'placeholder': '0'";
		input.value = fTotalFee;
		input = $(input);
		input.inputmask();

		fTotalFee = input.val();
		return fTotalFee;
	},

	fnFillSender: function(oMember,sClass) {

		// fill the sender(member) information in the Transaction Details dialog
		if (!$.pgsi.isNullOrUndefined(oMember))
		{
			$(' .'+sClass+ ' #senderFirstName').text(oMember.firstName);
			$(' .'+sClass+ ' #senderMiddleName').text(oMember.middleName);
			$(' .'+sClass+ ' #senderLastName').text(oMember.lastName);

			// get the Mother's Maiden Name
			if (!$.pgsi.isNullOrUndefined(oMember.nameList) &&
				!$.pgsi.isNullOrUndefined(oMember.nameList[0]))
			{
				$(' .'+sClass+ ' #senderMotherMaidenName').text(oMember.nameList[0].otherName);
			}


			// get the Sender Address
			if (!$.pgsi.isNullOrUndefined(oMember.contactList))
			{
				for (var i=0; i < oMember.contactList.length; i++) {
					if (oMember.contactList[i].contactType == 'POSTAL_WORK')
					{
						$(' .'+sClass+ ' #senderAddress').text(oMember.contactList[i].addressLine1);
						$(' .'+sClass+ ' #senderCity').text(oMember.contactList[i].cityName);
						$(' .'+sClass+ ' #senderPostalCode').text(oMember.contactList[i].postalCode);

						if (!$.pgsi.isNullOrUndefined(oMember.contactList[i].stateProvinceRegion))
						{
							$(' .'+sClass+ ' #senderStateProvince').text(oMember.contactList[i].stateProvinceRegion.code);
						}

						if (!$.pgsi.isNullOrUndefined(oMember.contactList[i].country))
						{
							$(' .'+sClass+ ' #senderCountry').text(oMember.contactList[i].country.description);
						}
					}
					else if (oMember.contactList[i].contactType == 'MOBILE')
					{
						var prefix = ""; //prefix to be used with the phone number, i.e: +1 4027776666

						if (!$.pgsi.isNullOrUndefined(oMember.contactList[i].country))
						{
							prefix = "+" + oMember.contactList[i].country.phoneCode + " ";
						}

						$(' .'+sClass+ ' #senderPhone').text(prefix + oMember.contactList[i].number);
					}
				}
			}
		}
	},
	// fill member dialog details errorDetails.
	fnFillSenderDialog: function(oMember,sClass) {

		// fill the sender(member) information in the Transaction Details dialog
		if (!$.pgsi.isNullOrUndefined(oMember))
		{
			$(' .'+sClass+ ' #senderFirstName').text(oMember.name.firstName || "");
			$(' .'+sClass+ ' #senderMiddleName').text(oMember.name.middleName || "");
			$(' .'+sClass+ ' #senderLastName').text(oMember.name.lastName || "");
			$(' .'+sClass+ ' #senderMotherMaidenName').text(oMember.name.motherMaidenName || "");
			$(' .'+sClass+ ' #senderPhone').text(oMember.phoneNumber || "");


			// get the Sender Address
			if (!$.pgsi.isNullOrUndefined(oMember.address))
			{
				$(' .'+sClass+ ' #senderAddress').text(oMember.address.address || "");
				$(' .'+sClass+ ' #senderCity').text(oMember.address.city || "");
				$(' .'+sClass+ ' #senderPostalCode').text(oMember.address.postalCode || "");

				if (!$.pgsi.isNullOrUndefined(oMember.address.stateProvinceRegion))
				{
					$(' .'+sClass+ ' #senderStateProvince').text(oMember.address.stateProvinceRegion.code || "");
				}

				if (!$.pgsi.isNullOrUndefined(oMember.address.country))
				{
					$(' .'+sClass+ ' #senderCountry').text(oMember.address.country.description || "");
				}
			}
		}
	},
	fnFillRecipientDialog: function(oRecipient,sClass) {

		// fill the recipient information in the Transactin Details dialog
		if (!$.pgsi.isNullOrUndefined(oRecipient))
		{
			$(' .'+sClass+ ' #recipientFirstName').text(oRecipient.name.firstName || "");
			$(' .'+sClass+ ' #recipientMiddleName').text(oRecipient.name.middleName || "");
			$(' .'+sClass+ ' #recipientLastName').text(oRecipient.name.lastName || "");
			$(' .'+sClass+ ' #recipientMotherMaidenName').text(oRecipient.name.motherMaidenName || "");

			$(' .'+sClass+ ' #recipientAddress').text(oRecipient.address.address || "");
			$(' .'+sClass+ ' #recipientCity').text(oRecipient.address.city || "");
			$(' .'+sClass+ ' #recipientPostalCode').text(oRecipient.address.postalCode || "");

			if (!$.pgsi.isNullOrUndefined(oRecipient.address.stateProvinceRegion))
			{
				$(' .'+sClass+ ' #recipientStateProvince').text(oRecipient.address.stateProvinceRegion.code || "");
			}

			if (!$.pgsi.isNullOrUndefined(oRecipient.address.country))
			{
				$(' .'+sClass+ ' #recipientCountry').text(oRecipient.address.country.description || "");
			}

			if (!$.pgsi.isNullOrUndefined(oRecipient.address.country))
			{
				prefix = "+" + oRecipient.address.country.phoneCode + " ";
			}
			if (!$.pgsi.isNullOrUndefined(oRecipient.address.number)){
				$(' .'+sClass+ ' #recipientPhone').text(prefix + oRecipient.address.number);
			}
		}
	},

	fnFillRecipient: function(oRecipient,sClass) {

		// fill the recipient information in the Transactin Details dialog
		if (!$.pgsi.isNullOrUndefined(oRecipient))
		{
			$(' .'+sClass+ ' #recipientFirstName').text(oRecipient.firstName);
			$(' .'+sClass+ ' #recipientMiddleName').text(oRecipient.middleName);
			$(' .'+sClass+ ' #recipientLastName').text(oRecipient.lastName);

			// get the Mother's Maiden Name
			if (!$.pgsi.isNullOrUndefined(oRecipient.nameList) &&
				!$.pgsi.isNullOrUndefined(oRecipient.nameList[0]))
			{
				$(' .'+sClass+ ' #recipientMotherMaidenName').text(oRecipient.nameList[0].otherName);
			}

			// get the Recipient Address
			if (!$.pgsi.isNullOrUndefined(oRecipient.contactList))
			{
				for (var i=0; i < oRecipient.contactList.length; i++) {
					if (oRecipient.contactList[i].contactType == 'POSTAL_WORK')
					{
						$(' .'+sClass+ ' #recipientAddress').text(oRecipient.contactList[i].addressLine1);
						$(' .'+sClass+ ' #recipientCity').text(oRecipient.contactList[i].cityName);
						$(' .'+sClass+ ' #recipientPostalCode').text(oRecipient.contactList[i].postalCode);

						if (!$.pgsi.isNullOrUndefined(oRecipient.contactList[i].stateProvinceRegion))
						{
							$(' .'+sClass+ ' #recipientStateProvince').text(oRecipient.contactList[i].stateProvinceRegion.code);
						}

						if (!$.pgsi.isNullOrUndefined(oRecipient.contactList[i].country))
						{
							$(' .'+sClass+ ' #recipientCountry').text(oRecipient.contactList[i].country.description);
						}
					}
					else if (oRecipient.contactList[i].contactType == 'MOBILE')
					{
						var prefix = ""; //prefix to be used with the phone number, i.e: +1 4027776666

						if (!$.pgsi.isNullOrUndefined(oRecipient.contactList[i].country))
						{
							prefix = "+" + oRecipient.contactList[i].country.phoneCode + " ";
						}

						$(' .'+sClass+ ' #recipientPhone').text(prefix + oRecipient.contactList[i].number);
					}
				}
			}
		}
	},
	fnFillTransferDetails: function(oMoneyTransfer,sClass) {

		//fill all the informations related with the transfer (the transfer details)
		if (!$.pgsi.isNullOrUndefined(oMoneyTransfer.originAmount))
		{
			$(' .'+sClass+ ' #totalAmount').text(pgsi.util.page.fnInsertMaskNumeric(pgsi.pages.transaction.sNumericMask,oMoneyTransfer.originAmount.amount + oMoneyTransfer.originFlatFee));

			if(oMoneyTransfer.originFlatFee >= oMoneyTransfer.discountAmoun ){
				$(' .'+sClass+ ' #fee').text(pgsi.util.page.fnInsertMaskNumeric(pgsi.pages.transaction.sNumericMask,oMoneyTransfer.originFlatFee - oMoneyTransfer.discountAmount));
			}else{
				$(' .'+sClass+ ' #fee').text(pgsi.util.page.fnInsertMaskNumeric(pgsi.pages.transaction.sNumericMask,oMoneyTransfer.originFlatFee));
			}
			$(' .'+sClass+ ' #effectiveExchangeRate').text(pgsi.util.page.fnInsertMaskNumeric(pgsi.pages.transaction.sNumericMask,oMoneyTransfer.foreignExchangeRateCustomer));
			// get the account number & payment type\

			if (!$.pgsi.isNullOrUndefined(oMoneyTransfer.transferSetting.account))
			{
				$(' .'+sClass+ ' #accountNumber').text(oMoneyTransfer.transferSetting.account.number);

				// get the payment type code
				if (!$.pgsi.isNullOrUndefined(oMoneyTransfer.statusList[oMoneyTransfer.statusList.length -1]))
				{
					if (!$.pgsi.isNullOrUndefined(oMoneyTransfer.statusList[oMoneyTransfer.statusList.length -1].moneyTransferTransaction))
					{
						if (!$.pgsi.isNullOrUndefined(oMoneyTransfer.statusList[oMoneyTransfer.statusList.length -1].moneyTransferTransaction.recipient))
						{
							if (!$.pgsi.isNullOrUndefined(oMoneyTransfer.statusList[oMoneyTransfer.statusList.length -1].moneyTransferTransaction.recipient.account))
							{
								$(' .'+sClass+ ' #senderPaymentType').text($.pgsi.enums.internationalizeByLabel("com.prosperitasglobal.sendsolv.model.AccountTypeEnum",oMoneyTransfer.statusList[oMoneyTransfer.statusList.length -1].moneyTransferTransaction.recipient.account.type));

							}
						}
						$(' .'+sClass+ ' #agentOrderName').text(oMoneyTransfer.statusList[oMoneyTransfer.statusList.length -1].moneyTransferTransaction.key);
						$(' .'+sClass+ ' #agentUserName').text(oMoneyTransfer.statusList[oMoneyTransfer.statusList.length -1].moneyTransferTransaction.releaseUser);

					}
				}
				$(' .'+sClass+ ' #agentRegion').text("Nebraska");
				$(' .'+sClass+ ' #agentBranch').text("Omaha");
				$(' .'+sClass+ ' #agentState').text("Nebraska");
				$(' .'+sClass+ ' #agentCountry').text("United States")
			}
		}

		// get the destination currency, amount of the transfer & destination country
		if (!$.pgsi.isNullOrUndefined(oMoneyTransfer.originAmount))
		{

			$(' .'+sClass+ ' #transferAmount').text(pgsi.util.page.fnInsertMaskNumeric(pgsi.pages.transaction.sNumericMask,oMoneyTransfer.originAmount.amount));

			if (!$.pgsi.isNullOrUndefined(oMoneyTransfer.originAmount.country))
			{
				$(' .'+sClass+ ' #destinationCountry').text(oMoneyTransfer.originAmount.country.description);
			}
		}
		if (!$.pgsi.isNullOrUndefined(oMoneyTransfer.destinationAmount)){
			$(' .'+sClass+ ' #transferAmountCurrency').text(pgsi.util.page.fnInsertMaskNumeric(pgsi.pages.transaction.sNumericMask,oMoneyTransfer.destinationAmount.amount));
			$(' .'+sClass+ ' #destinationCurrency').text(oMoneyTransfer.destinationAmount.currency.code);
		}
		// get the payer & transaction method
		if (!$.pgsi.isNullOrUndefined(oMoneyTransfer.transferSetting) &&
			!$.pgsi.isNullOrUndefined(oMoneyTransfer.transferSetting.productPlanApplicability))
		{
			if (!$.pgsi.isNullOrUndefined(oMoneyTransfer.transferSetting.productPlanApplicability.paymentType))
			{
				$(' .'+sClass+ ' #transactionMethod').text($.pgsi.enums.internationalizeByLabel("com.prosperitasglobal.sendsolv.model.PaymentTypeEnum",oMoneyTransfer.transferSetting.productPlanApplicability.paymentType));
			}

			if (!$.pgsi.isNullOrUndefined(oMoneyTransfer.transferSetting.productPlanApplicability.payer))
			{
				$(' .'+sClass+ ' #payerName').text(oMoneyTransfer.transferSetting.productPlanApplicability.payer.name);
			}
		}

		// get the agent date and agent time
		if (!$.pgsi.isNullOrUndefined(oMoneyTransfer.transferSetting.createDateUTC))
		{
			$(' .'+sClass+ ' #agentDate').text($.pgsi.date.format(new Date(oMoneyTransfer.transferSetting.createDateUTC), "mm/dd/yy", true));
			$(' .'+sClass+ ' #agentTime').text($.pgsi.date.format(new Date(oMoneyTransfer.transferSetting.createDateUTC), "h:i:s A", true));
		}


		// get the other details from the money transfer, such as errors, transaction id...
		if (!$.pgsi.isNullOrUndefined(oMoneyTransfer.id))
		{
			$(' .'+sClass+ ' #confirmation').text('#' + oMoneyTransfer.id)
		}

		// get the last status of this transaction
		if (!$.pgsi.isNullOrUndefined(oMoneyTransfer.statusList) &&
				!$.pgsi.isNullOrUndefined(oMoneyTransfer.statusList[oMoneyTransfer.statusList.length - 1]))
		{
			$(' .'+sClass+ ' #requestSent').text($.pgsi.date.format(new Date(oMoneyTransfer.statusList[oMoneyTransfer.statusList.length - 1].createDateUTC), "mm/dd/yy h:i:s A", true));
			$(' .'+sClass+ ' #requestStatus').text($.pgsi.enums.internationalizeByLabel("com.prosperitasglobal.sendsolv.model.MoneyTransferStatusEnum",oMoneyTransfer.statusList[oMoneyTransfer.statusList.length - 1].status));

			// get the ach erros
			if (!$.pgsi.isNullOrUndefined(oMoneyTransfer.statusList[oMoneyTransfer.statusList.length - 1].response) &&
				!$.pgsi.isNullOrUndefined(oMoneyTransfer.statusList[oMoneyTransfer.statusList.length - 1].response.messageList))
			{
				var messageList = oMoneyTransfer.statusList[oMoneyTransfer.statusList.length - 1].response.messageList;

				// get the details of the first message
				if (!$.pgsi.isNullOrUndefined(messageList[0]) &&
					!$.pgsi.isNullOrUndefined(messageList[0].messageInfo))
				{
					$(' .'+sClass+ ' #errorType').text(messageList[messageList.length - 1].messageInfo.code);
					$(' .'+sClass+ ' #errorDetails').text(messageList[0].text);
				}

				// get the details of the second message
				if (!$.pgsi.isNullOrUndefined(messageList[1]) &&
					!$.pgsi.isNullOrUndefined(messageList[1].messageInfo))
				{
					$(' .'+sClass+ ' #errorMessage').text(messageList[1].messageInfo.internalText);
				}

			}
		}
	},
	fnFillTransferDetailsDialog: function(oMoneyTransfer,sClass,oResponse) {


		//TODO
		$(' .'+sClass+ ' #agentRegion').text("Nebraska");
		$(' .'+sClass+ ' #agentBranch').text("Omaha");
		$(' .'+sClass+ ' #agentState').text("Nebraska");
		$(' .'+sClass+ ' #agentCountry').text("United States");

		$(' .'+sClass+ ' #agentOrderName').text(oMoneyTransfer.key || oMoneyTransfer.id);
		$(' .'+sClass+ ' #agentUserName').text(oMoneyTransfer.releaseUser || "");

		//fill all the informations related with the transfer (the transfer details)
		if (!$.pgsi.isNullOrUndefined(oMoneyTransfer.originAmount))
		{
			$(' .'+sClass+ ' #totalAmount').text(pgsi.util.page.fnInsertMaskNumeric(pgsi.pages.transaction.sNumericMask,oMoneyTransfer.originAmount.amount + oMoneyTransfer.originFlatFee));
		}

		if(oMoneyTransfer.originFlatFee >= (oMoneyTransfer.discountAmoun || 0)){
			$(' .'+sClass+ ' #fee').text(pgsi.util.page.fnInsertMaskNumeric(pgsi.pages.transaction.sNumericMask,oMoneyTransfer.originFlatFee - (oMoneyTransfer.discountAmount || 0)));
		}else{
			$(' .'+sClass+ ' #fee').text(pgsi.util.page.fnInsertMaskNumeric(pgsi.pages.transaction.sNumericMask,oMoneyTransfer.originFlatFee));
		}
		$(' .'+sClass+ ' #effectiveExchangeRate').text(pgsi.util.page.fnInsertMaskNumeric(pgsi.pages.transaction.sNumericMask,oMoneyTransfer.foreignExchangeRateCustomer));

		$(' .'+sClass+ ' #transferAmountCurrency').text(pgsi.util.page.fnInsertMaskNumeric(pgsi.pages.transaction.sNumericMask,oMoneyTransfer.destinationAmount.amount));

		// get the account number & payment type\
		if (!$.pgsi.isNullOrUndefined(oMoneyTransfer.recipient.account))
		{
			$(' .'+sClass+ ' #accountNumber').text(oMoneyTransfer.recipient.account.number);

			// get the payment type code
			$(' .'+sClass+ ' #senderPaymentType').text($.pgsi.enums.internationalizeByLabel("com.prosperitasglobal.sendsolv.model.AccountTypeEnum",oMoneyTransfer.recipient.account.type));

		}


		// get the destination currency, amount of the transfer & destination country
		if (!$.pgsi.isNullOrUndefined(oMoneyTransfer.originAmount))
		{
			$(' .'+sClass+ ' #transferAmount').text(pgsi.util.page.fnInsertMaskNumeric(pgsi.pages.transaction.sNumericMask,oMoneyTransfer.originAmount.amount));

		}

		if (!$.pgsi.isNullOrUndefined(oMoneyTransfer.destinationAmount.country))
		{
			$(' .'+sClass+ ' #destinationCountry').text(oMoneyTransfer.destinationAmount.country.description);
			$(' .'+sClass+ ' #destinationCurrency').text(oMoneyTransfer.destinationAmount.currency.code);
		}

		if (!$.pgsi.isNullOrUndefined(oMoneyTransfer.paymentType))
		{
			$(' .'+sClass+ ' #transactionMethod').text($.pgsi.enums.internationalizeByLabel("com.prosperitasglobal.sendsolv.model.PaymentTypeEnum",oMoneyTransfer.paymentType));
		}

		if (!$.pgsi.isNullOrUndefined(oMoneyTransfer.achPayeeCode))
		{
			$.pgsi.ajax.post({
				 sUrl : 'api/transaction/fetchPayer',
				 bAsync		: false,
				 oRequest : new PayerInquiryRequest({criteria:{achPayeeCode:oMoneyTransfer.achPayeeCode,country:oMoneyTransfer.destinationAmount.country}}),

				 fnCallback : function(oResponse){
					 if(oResponse.operationSuccess == true){
						 if(oResponse.payerList.length > 0){

							$(' .'+sClass+ ' #payerName').text(oResponse.payerList[0].name);
						 }
					 }
				 }
			});

		}


		// get the agent date and agent time
		if (!$.pgsi.isNullOrUndefined(oMoneyTransfer.createDateUTC))
		{
			$(' .'+sClass+ ' #agentDate').text($.pgsi.date.format(new Date(oMoneyTransfer.createDateUTC), "mm/dd/yy", true));
			$(' .'+sClass+ ' #agentTime').text($.pgsi.date.format(new Date(oMoneyTransfer.createDateUTC), "h:i:s A", true));
		}


		// get the other details from the money transfer, such as errors, transaction id...
		if (!$.pgsi.isNullOrUndefined(oMoneyTransfer.confirmationNumber))
		{
			$(' .'+sClass+ ' #confirmation').text('#' + oMoneyTransfer.confirmationNumber);
		}else{
			$(' .'+sClass+ ' #confirmation').text('#' + oMoneyTransfer.id);
		}

		// get the last status of this transaction
		if (!$.pgsi.isNullOrUndefined(oResponse)){

			if($.address.parameter("status") == "EXCEPTION"){
				if ($.pgsi.isNullOrUndefined(oResponse.statusList[parseInt($.address.parameter("statusId"),10)+1].moneyTransferTransaction)){
					$(' .'+sClass+ ' #requestStatus').text($.pgsi.enums.internationalizeByLabel("com.prosperitasglobal.sendsolv.model.MoneyTransferStatusEnum",oResponse.statusList[parseInt($.address.parameter("statusId"),10)+1].status));
					$(' .'+sClass+ ' #requestSent').text($.pgsi.date.format(new Date(oResponse.statusList[parseInt($.address.parameter("statusId"),10)+1].createDateUTC), "mm/dd/yy h:i:s A", true));
				}
			}else{
				$(' .'+sClass+ ' #requestStatus').text($.pgsi.enums.internationalizeByLabel("com.prosperitasglobal.sendsolv.model.MoneyTransferStatusEnum",oResponse.statusList[parseInt($.address.parameter("statusId"),10)].status));
				$(' .'+sClass+ ' #requestSent').text($.pgsi.date.format(new Date(oResponse.statusList[parseInt($.address.parameter("statusId"),10)].createDateUTC), "mm/dd/yy h:i:s A", true));
			}

			// get the ach erros
			if($.address.parameter("status") == "EXCEPTION"){
				if (!$.pgsi.isNullOrUndefined(oResponse.statusList[parseInt($.address.parameter("statusId"),10)+1].response) &&
					!$.pgsi.isNullOrUndefined(oResponse.statusList[parseInt($.address.parameter("statusId"),10)+1].response.messageList))
				{
					var messageList = oResponse.statusList[parseInt($.address.parameter("statusId"),10)+1].response.messageList;

					// get the details of the first message
					if (!$.pgsi.isNullOrUndefined(messageList[0]) &&
						!$.pgsi.isNullOrUndefined(messageList[0].messageInfo))
					{
						$(' .'+sClass+ ' #errorType').text(messageList[messageList.length - 1].messageInfo.code);
						$(' .'+sClass+ ' #errorDetails').text(messageList[0].text);
					}

					// get the details of the second message
					if (!$.pgsi.isNullOrUndefined(messageList[1]) &&
						!$.pgsi.isNullOrUndefined(messageList[1].messageInfo))
					{
						$(' .'+sClass+ ' #errorMessage').text(messageList[1].messageInfo.internalText);
					}

				}
			}else{
				if (!$.pgsi.isNullOrUndefined(oResponse.statusList[parseInt($.address.parameter("statusId"),10)].response) &&
					!$.pgsi.isNullOrUndefined(oResponse.statusList[parseInt($.address.parameter("statusId"),10)].response.messageList))
				{
					var messageList = oResponse.statusList[parseInt($.address.parameter("statusId"),10)].response.messageList;

					// get the details of the first message
					if (!$.pgsi.isNullOrUndefined(messageList[0]) &&
						!$.pgsi.isNullOrUndefined(messageList[0].messageInfo))
					{
						$(' .'+sClass+ ' #errorType').text(messageList[messageList.length - 1].messageInfo.code);
						$(' .'+sClass+ ' #errorDetails').text(messageList[0].text);
					}

					// get the details of the second message
					if (!$.pgsi.isNullOrUndefined(messageList[1]) &&
						!$.pgsi.isNullOrUndefined(messageList[1].messageInfo))
					{
						$(' .'+sClass+ ' #errorMessage').text(messageList[1].messageInfo.internalText);
					}

				}
			}
		}
	}

}
</script>

</sec:authorize>