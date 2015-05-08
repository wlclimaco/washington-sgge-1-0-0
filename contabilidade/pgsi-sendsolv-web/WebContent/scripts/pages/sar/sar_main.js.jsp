<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>

<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN')">

<script type="text/javascript">
/**
 * @namespace pgsi.pages.member
 * @description The main namespace for the Member List Page.
 * @author Flavio Tosta, Washington Costa
 */

pgsi.pages.sar = {
	sMask : "***-99999",
	dialog : {
		form : {

			validator : $("#SAR-form").validate({
				ignore : "",

				invalidHandler : function(form, validator) {

					$.each(validator.errorList, function(index, value) {
						$(value.element).addClass("error");

						if (value.element.nodeName.toLowerCase() == 'select') {
							$(value.element).next('span').addClass("error");
						}
					});
				},
				rules: {
					recipientId: {
						required: true,
						sarInvalid:18
					}
				}
			}),

			fillFormFields : function(oData) {},
			initialForm : function(iId,sType,sName,sKey) {

				if((sType.toUpperCase() == "MEMBER")||(sType.toUpperCase() == "RECIPIENT")||(sType.toUpperCase() == "LIAISON")){
				}else{
					if ((sKey > 0)||(iId > 0)){
						$('#recipientParticipantId').val(sKey||iId);
					}
					$('#nameParticipant').text(sName);
					$('#reportTarget').addClass('hide');
				}
			},

			fillObject : function(sModelAction,iId,sType,sName) {
				var oBusinessList = new Array();
				var oPerson = new Array();

				sType = sType || $('#sar-type').val()
				if((sType.toUpperCase() == "MEMBER")||(sType.toUpperCase() == "RECIPIENT")||(sType.toUpperCase() == "LIAISON")){
					$.each( $('.employment').find('input'), function( i, val ) {
						if ($(this).is(":checked")) {
							iLocationId = $(this).val().split('|')[0];
							var oBusiness = new Business({
								id           : parseInt(iLocationId,10),
								businessType : "LOCATION"
							});
							oBusinessList.push(oBusiness)
						}
					});
					oPerson = [new Person({
						id                : parseInt($("#participant-id").val(),10)
					})]
				}else{
					var oBusiness = new Business({
						id           : parseInt(iId || $('#sar-business-id').val()),
						businessType : sType.toUpperCase()
					});
					oBusinessList.push(oBusiness);
				}


				oSuspiciousActivity = new SuspiciousActivity({
					type 		 				: sType.toUpperCase(),
					modelAction 				: sModelAction,
					status						: "PGSI_REVIEW",
					summary 		 			: $('#summary').val(),
					detail		     			: $('#note').val(),
					activityStartDateTimeUTC	: new Date($('#activityStartDateTimeUTC').val()).getTime(),
					activityStopDateTimeUTC		: new Date($('#activityStopDateTimeUTC').val()).getTime(),
					amountAssociated			: $('#amount').val().substring(4).replace(",", ""),
					statusDateTime				: new Date().getTime(),
					personList 					: oPerson,
					businessList 				: oBusinessList
				});
				return new SarMaintenanceRequest({suspiciousActivity : oSuspiciousActivity});
			}
		}

	},
	view :{
		fillSARDetail : function(oListSar,oObject){

			if (!$.pgsi.isNullOrUndefined(oListSar)){
				var oSAR = oListSar[oListSar.length -1];
				$('#key').val(oSAR.businessKey);
				$.pgsi.storage.set('SAR',oSAR);
				$('#header-id').text('Suspicious Activity Report #'+pgsi.util.page.fnInsertMask(pgsi.pages.sar.sMask,oSAR.businessKey) + '('+$.pgsi.enums.internationalizeByLabel("com.prosperitasglobal.sendsolv.model.SARStatusEnum",oSAR.status)+')' );
				$('#sarKey').text('#'+pgsi.util.page.fnInsertMask(pgsi.pages.sar.sMask,oSAR.businessKey))
				$('#sarReported').text('Reported '+$.pgsi.date.format(new Date(oSAR.createDateUTC), "mm/dd/yy", null)+' by '+ oSAR.createUser)
				$('#sarFile').text('');

				$('#summary').text(oSAR.summary);
				if (!$.pgsi.isNullOrUndefined(oSAR.businessList)){
					var sSARLocation = "";
					for(var iCount = 0;iCount<oSAR.businessList.length;iCount++){
						sSARLocation = sSARLocation + "<a href='location/view?tab=info&locationId="+oSAR.businessList[iCount].id+"'>"+oSAR.businessList[iCount].name+"</a><br>"
					}

				}
				$('#location-sar').append(sSARLocation);
				$('#period').text($.pgsi.date.format(new Date(oSAR.activityStartDateTimeUTC), "mm/dd/yy", null) + " - " + $.pgsi.date.format(new Date(oSAR.activityStopDateTimeUTC), "mm/dd/yy", null));
				$('#amount').text(pgsi.util.page.fnInsertMaskNumeric("'alias': 'numeric', 'groupSeparator': ',', 'autoGroup': true, 'digits': 2, 'digitsOptional': false, 'prefix': 'USD ', 'placeholder': '0'",oSAR.amountAssociated));
				$('#detail').text(oSAR.detail);

			}
			if (!$.pgsi.isNullOrUndefined(oObject)){

				if((oObject.businessType == "ORGANIZATION")||(oObject.businessType == "LOCATION")){
					$('.narrow:eq(4)').hide();
					$('#memberId').text(oObject.name +" (#"+(pgsi.util.page.fnInsertMask(pgsi.pages.sar.sMask,oObject.key) || oObject.id)+")");
					$('#type').text($.pgsi.enums.internationalizeByLabel("com.prosperitasglobal.cbof.model.BusinessTypeEnum",oObject.businessType))
					$('#status').text($.pgsi.enums.internationalizeByLabel("com.prosperitasglobal.sendsolv.model.StatusEnum",oObject.status))
					$('#enrollment').val()
					$('#date').val()
					// SDN status specific formatting / styling
					sSdnIcons = pgsi.util.page.status.view.fnFillBoxStatusFlags(oObject);
					if("&nbsp&nbsp" != sSdnIcons){
						$('.sdnStatus-business').append(sSdnIcons);
					}
					if ($.pgsi.isNullOrUndefined(oObject)) {
						return false;
					}
					if (!$.pgsi.isNullOrUndefined(oObject.employmentInfoList)){
						var sLocation = "";
						if(oObject.employmentInfoList.length > 0){
							for(var i = 0;i<oObject.employmentInfoList.length;i++){
								sLocation = sLocation + "<a href='location/view?tab=info&locationId="+oObject.employmentInfoList[i].locationId+"'>"+oObject.employmentInfoList[i].locationName+"</a>, <a href='organization/view?tab=info&organizationId="+oObject.employmentInfoList[i].organizationId+"'>"+oObject.employmentInfoList[i].organizationName+"</a><br>";
							}
							$('#location').append(sLocation);
						}
					}

					if (!$.pgsi.isNullOrUndefined(oSAR.personList)){
						var sPerson = "";

						if(oSAR.personList.length > 0){
							for(var i = 0;i<oSAR.personList.length;i++){
								sName = oSAR.personList[i].firstName + ' ' + oSAR.personList[i].lastName +'(#'+pgsi.util.page.fnInsertMask(pgsi.pages.sar.sMask,oSAR.personList[i].participantId)+")";
								if(oSAR.personList[i].personType == "MEMBER"){
									sPerson = sPerson + '<div class="label newline" style="width: auto"><a href="member_view.html" class="newline">'+sName+'</a></div>'
								}else if(oSAR.personList[i].personType == "RECIPIENT"){
									sPerson = sPerson + '<div class="label newline" style="width: auto"><a href="member_view.html" class="newline">'+sName+')</a></div>'
								}else if(oSAR.personList[i].personType == "LIAISON"){
									sPerson = sPerson + '<div class="label newline" style="width: auto"><a href="member_view.html" class="newline">'+sName+'</a></div>'
								}else if(oSAR.personList[i].personType == "OTHER"){
									sPerson = sPerson + '<div class="label newline" style="width: auto"><a href="member_view.html" class="newline">'+sName+'</a></div>'
								}
							}
							$('.list-person').append(sPerson);

							if(oSAR.personList.length > 0){
								if(oSAR.personList.length == 1){
									$('#totalIndividuals').text(oSAR.personList.length + " " +$.pgsi.locale.get("pages.sar.view.page.total.Individual"))
								}else{
									$('#totalIndividuals').text(oSAR.personList.length + " " +$.pgsi.locale.get("pages.sar.view.page.total.Individuals"))
								}
							}
						}
					}

				}else{
					$('#memberId').text(oObject.firstName +" "+oObject.lastName + " (#"+pgsi.util.page.fnInsertMask(pgsi.pages.sar.sMask,oObject.participantId)+")");
					$('#type').text($.pgsi.enums.internationalizeByLabel("com.prosperitasglobal.cbof.model.BusinessTypeEnum",oObject.personType))
					$('#status').text($.pgsi.enums.internationalizeByLabel("com.prosperitasglobal.sendsolv.model.StatusEnum",oObject.personStatus))
					$('#enrollment').val()
					$('#date').val()
					// SDN status specific formatting / styling
					sSdnIcons = pgsi.util.page.status.view.fnFillBoxStatusFlags(oObject);
					if("&nbsp&nbsp" != sSdnIcons){
						$('.sdnStatus').append(sSdnIcons);
					}
					if ($.pgsi.isNullOrUndefined(oObject)) {
						return false;
					}
					if (!$.pgsi.isNullOrUndefined(oObject.employmentInfoList)){
						var sLocation = "";
						if(oObject.employmentInfoList.length > 0){
							for(var i = 0;i<oObject.employmentInfoList.length;i++){
								sLocation = sLocation + "<a href='location/view?tab=info&locationId="+oObject.employmentInfoList[i].locationId+"'>"+oObject.employmentInfoList[i].locationName+"</a>, <a href='organization/view?tab=info&organizationId="+oObject.employmentInfoList[i].organizationId+"'>"+oObject.employmentInfoList[i].organizationName+"</a><br>";
							}
							$('#location').append(sLocation);
						}
					}
				}

						// fill phone fields
				pgsi.pages.phone.view.fillFields(oObject.contactList);
				// fill address fields
				pgsi.pages.address.view.fillFields(oObject.contactList);

				// Emails
				pgsi.util.page.email.view.fillFields(oObject.contactList);

				// fill risk
				pgsi.pages.risk.view.fillFields(oObject);



			}
		}

	},
	table :{
		fnCreateIDLink : function (val, type, full)
		{
			var iParentId = 0;
			if (type !== "display")
			{
				return val;
			}
			if((full.type == "ORGANIZATION")||(full.type == "LOCATION")){
				iParentId = full.businessList[0].id
			}else{
				iParentId = full.personList[0].id
			}

			var idLink = '<a  id= "edit_links" title="View/Edit ' + (pgsi.util.page.fnInsertMask(pgsi.pages.sar.sMask,full.businessKey ) || full.id) + '" href="sar/view?id=' + full.businessKey + '&parentKey='+iParentId+'&type='+full.type+'" class="edit_link alist">' + pgsi.util.page.fnInsertMask("***-99999",full.businessKey ) || full.id+'</a>';

			return idLink;
		},

		fnSARStatus : function (val, type, full)
		{
			if (type !== "display")
			{
				return val;
			}
			return $.pgsi.enums.internationalizeByLabel("com.prosperitasglobal.sendsolv.model.SARStatusEnum",full.status);
		},
		fnSARType : function (val, type, full)
		{
			if (type !== "display")
			{
				return val;
			}
			return $.pgsi.enums.internationalizeByLabel("com.prosperitasglobal.cbof.model.BusinessTypeEnum",full.type);
		},
		// Returns link for edit view
		fnCreateBusinessNamePersonNameLink : function (val, type, full)
		{

			if (type !== "display")
			{
				return val;
			}

			if((full.type == "ORGANIZATION")||(full.type == "LOCATION"))
				return '<a title="View/Edit ' + full.businessList[0].name   + '" href="#/location/view?tab=info&locationId=' + full.businessList[0].id + '">' + full.businessList[0].name + '</a>';
				//return '<a title="View/Edit ' + full.businessList[0].name   + '" href="#/location/view?tab=info&locationId=' + full.businessList[0].id + '">' + full.businessList[0].name + '</a><a title="View"' + full.businessList[0].parentOrganizationName + ' href="#/organization/view?organizationId=' + full.businessList[0].parentOrganizationId + '">&nbsp(' + full.businessList[0].parentOrganizationName + ') </a>';
			else
				return '<a title="View/Edit ' + full.personList[0].firstName + ' ' + full.personList[0].lastName + '" href="#/location/view?tab=info&locationId=' + full.personList[0].id + '">' + full.personList[0].firstName + ' ' + full.personList[0].lastName + '</a>';
		},
		fnActivityPeriod : function (val, type, full)
		{

			if (type !== "display")
			{
				return val;
			}
			return $.pgsi.date.format(new Date(full.activityStartDateTimeUTC), "mm/dd/yy", null) + " - " + $.pgsi.date.format(new Date(full.activityStopDateTimeUTC), "mm/dd/yy", null)
		},
		fnTotalAmout : function (val, type, full)
		{

			if (type !== "display")
			{
				return val;
			}
			return pgsi.util.page.fnInsertMaskNumeric("'alias': 'numeric', 'groupSeparator': ',', 'autoGroup': true, 'digits': 2, 'digitsOptional': false, 'prefix': 'USD ', 'placeholder': '0'",full.amountAssociated);
		}

	},
	common : {
		ajaxCallSuspiciousActivity : function(sModelAction,fnCallBack,iId,sType,sName,sKey){

			var validator = $("#SAR-form").validate({
				ignore : "",

				invalidHandler : function(form, validator) {

					$.each(validator.errorList, function(index, value) {
						$(value.element).addClass("error");

						if (value.element.nodeName.toLowerCase() == 'select') {
							$(value.element).next('span').addClass("error");
						}
					});
				},
				rules: {
					recipientId: {
						required: true,
						sarInvalid:18
					}
				}
			});

			var bValidForm = validator.form();

			if (bValidForm){
				$.pgsi.ajax.post({
					 sUrl : 'api/sar/insert',
					 oRequest : pgsi.pages.sar.dialog.form.fillObject(sModelAction, iId,sType,sName,sKey),
					 fnCallback : function(oResponse){
						 if(oResponse.operationSuccess == true){
							 $("#action-dialog").dialog('close');
						 }else {
								pgsi.pages.sendsolv.fnDialogMessageError("",{},oResponse,null,$.pgsi.locale.get("commons.dialog.error.title"),true);
						}
						 if (!$.pgsi.isNullOrUndefined(fnCallBack)){
							fnCallBack(oResponse);
						 }
						 $.pgsi.progressBar.stop();
					 }
				});
			}
		},
		ajaxCallSearch : function(sId){
			var oObject;
			if ((sId.indexOf("LAA") != -1)||(sId.indexOf("CAA") != -1)||(sId.indexOf("MAA") != -1)||(sId.indexOf("OAA") != -1)||(sId.indexOf("RAA") != -1 ))
			{
				$.pgsi.ajax.post({
					 sUrl : 'api/sar/search',
					 bAsync		: false,
					 bHideProgressBar : true,
					 oRequest : {stringId : sId},
					 fnCallback : function(oResponse){
						if(oResponse.operationSuccess){
							if (sId.indexOf("LAA") != -1)
							{
								if(oResponse.locationList.length > 0){
									oObject = oResponse.locationList[0];
								}
							}
							else if (sId.indexOf("CAA") != -1)
							{
								if(oResponse.liaisonList.length > 0){
									oObject = oResponse.liaisonList[0];
								}
							}
							else if (sId.indexOf("MAA") != -1)
							{
								if(oResponse.memberList.length > 0){
									oObject = oResponse.memberList[0];
								}
							}
							else if (sId.indexOf("OAA") != -1)
							{
								if(oResponse.organizationList.length > 0){
									oObject = oResponse.organizationList[0];
								}
							}
							else if (sId.indexOf("RAA") != -1 )
							{
								if(oResponse.recipientList.length > 0){
									oObject = oResponse.recipientList[0];
								}
							}
						}
					}
				});
			}
			return oObject;
		}
	}
}
</script>

</sec:authorize>