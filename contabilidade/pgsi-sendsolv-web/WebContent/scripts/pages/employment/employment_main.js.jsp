<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>

<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN', 'ROLE_CSR')">

<script type="text/javascript">
	pgsi.pages.employment = {

		form : {

			fnInitForm : function() {
				pgsi.util.page.form.fnInitForm();
				$("#enrolltype-button").outerWidth(220);
				$("#enrolltype").fnLoadDropDownList($.pgsi.enums.internationalizeEnumsByLabel("com.prosperitasglobal.sendsolv.model.EnrollmentTypeEnum"));

				$(function() {
					function log( message ) {
						$( "<div>" ).text( message ).prependTo("#log");
						$( "#log" ).scrollTop( 0 );
					}

					$( "#locationName" ).autocomplete({
						source: function( request, response ) {
							$.pgsi.ajax.post({
								sUrl 		: "api/location/fetchall",
								bHideProgressBar : true,
								oRequest 	: {inquiryCriteria:{name:request.term},statusList : $.pgsi.enums.fetchLabelsByValues("com.prosperitasglobal.sendsolv.model.StatusEnum", ["1"]),sortExpressions:[{field:"name",direction:"Descending"}],preQueryCount:true,startPage:0,pageSize:10000},
								fnCallback  : function(oResponse) {
									response( $.map( oResponse.locationList, function( item ) {
										var iLength = item.contactList.length,
 										sAddress = item.contactList,
										sComplet = "";

										if(iLength > 0 ){
											for(var i=0;i < iLength;i++){
												if(sAddress[i].type == "address") {
													sComplet = " ( " + sAddress[i].cityName + ", " + sAddress[i].stateProvinceRegion.description.trim() + ", " + sAddress[i].country.code + " ) ";
												}
											}
										}

										return {
											label 				 : item.name,
											desc  				 : sComplet,
											value 				 : item.name,
											parentOrganizationId : item.parentOrganizationId,
											parentOrganizationName : item.parentOrganizationName,
											id    				 : item.id
										}
									}));
										}
							});
						},

						minLength: 1,
						select: function( event, ui ) {
							$('#locationId').val(ui.item.id)
							$('#remember').attr("disabled", false);
							$('#parentOrganizationId').val(ui.item.parentOrganizationId)
							log( ui.item ?
							"Selected: " + ui.item.label :
							"Nothing selected, input was " + this.value);
						},

						open: function() {
							$( this ).removeClass( "ui-corner-all" ).addClass( "ui-corner-top" );
						},

						close: function() {
							$( this ).removeClass( "ui-corner-top" ).addClass( "ui-corner-all" );
						}
					})

					.autocomplete( "instance" )._renderItem = function( ul, item ) {
					    return $( "<li>" )
					    .append( "<a><span class='label'>" + item.label + "<br>" + item.parentOrganizationName +"</span><br><span class='desc'>" + item.desc + "</span></a>" )
					    .appendTo( ul );
					};

				});

				$("input#payPerPeriod").inputmask();
				$("#hireDate").datepicker(
					{
						maxDate: "+0D",
						onClose : function(dateText, object) {
							if (isNaN((new Date(dateText)).getTime()) || (new Date(dateText) > new Date)) { $(this).val(""); }
						}
					}
				);

				$("#hireDate").mask("99/99/9999");
			},

			fillFormFields : function(oEmploymentInfo) {
				$("#locationId").val(oEmploymentInfo.locationId);
				$("#locationName").val(oEmploymentInfo.locationName);
				$("#employeeId").val(oEmploymentInfo.employeeId);
				$("#employeeId").val(oEmploymentInfo.employeeId);
				$("#jobTitle").val(oEmploymentInfo.jobTitle);

				if (!$.pgsi.isNullOrUndefined(oEmploymentInfo.hireDate)) {
					$("#hireDate").val($.pgsi.date.format(new Date(oEmploymentInfo.hireDate), "mm/dd/yy", null));
				}

				$("#payPerPeriod").val(oEmploymentInfo.payPerPeriod);

				$('#enrolltype-button .ui-selectmenu-text').text($.pgsi.enums.internationalizeByLabel("com.prosperitasglobal.sendsolv.model.EnrollmentTypeEnum", oEmploymentInfo.enrollmentType));
				$("#enrolltype").val(oEmploymentInfo.enrollmentTypeValue).prop('selected',true);

			},

			fillObject : function(oEmploymentInfo) {
				oEmploymentInfo.locationId = $("#locationId").val();
				oEmploymentInfo.locationName = $("#locationName").val();
				oEmploymentInfo.employeeId = $("#employeeId").val();
				oEmploymentInfo.jobTitle = $("#jobTitle").val();
				oEmploymentInfo.hireDate = (new Date($("#hireDate").val())).getTime();
				oEmploymentInfo.payPerPeriod = $("#payPerPeriod").val().substring(4).replace(",", "");
				if (!$.pgsi.isNullOrUndefined($("#enrolltype").val())) {
					oEmploymentInfo.enrollmentType = $.pgsi.enums.fetchLabelByValue("com.prosperitasglobal.sendsolv.model.EnrollmentTypeEnum",$("#enrolltype").val());
				}
				return oEmploymentInfo;
			}
		},

		view : {

			filterNumberOfTransfers : function(oEmploymentInfo, oTransferSettingsList) {
				var oTransferSetting;
				var iCounter = 0;
				for (var i =0; i < oTransferSettingsList.length; i++) {
					oTransferSetting = oTransferSettingsList[i];

					if (oEmploymentInfo.id === oTransferSetting.employmentInfo.id) {
                        iCounter++;
					}
				}

				return iCounter;
			},

			filterNumberOfRecipients : function(oEmploymentInfo, oTransferSettingsList) {
				var oTransferSetting;
				var oRecipientList = new Array();
				var isDiferent = false;

				for (var i =0; i < oTransferSettingsList.length; i++) {
					oTransferSetting = oTransferSettingsList[i];

					if (oEmploymentInfo.id === oTransferSetting.employmentInfo.id) {
                        isDiferent = true;
                        for (var j =0; j < oRecipientList.length; j++) {
                        	if (oRecipientList[j] === oTransferSettingsList[i].recipientId) {
                        		isDiferent = false;
                        	}
                        }

                         if (isDiferent === true) {
                        	oRecipientList.push(oTransferSettingsList[i].recipientId);
                        }
					}
				}

				return oRecipientList.length;
			},

			fill : function(oPerson){
				oPerson.employmentInfoList;

				if (!$.pgsi.isNullOrUndefined(oPerson.employmentInfoList)) {
					pgsi.pages.employment.view.fnFillItens(oPerson.employmentInfoList, oPerson.transferSettingList, oPerson.sdnstatus);
				}

				$addEmployment = $("#link-add-empl");

				$addEmployment.unbind("click", pgsi.pages.employment.view.fnAttachEventHandlers);
				$addEmployment.bind("click", pgsi.pages.employment.view.fnAttachEventHandlers);

			},

			fillObject : function(oEmploymentInfo, $parent) {

				if (!$.pgsi.isNullOrUndefined($parent.find('.payPerPeriod')).length > 0 && !$.pgsi.isNullOrUndefined($parent.find('.payPerPeriod').text().trim())) {
					oEmploymentInfo.payPerPeriod = $parent.find('.payPerPeriod').text().trim().substring(4).replace(",", "");
				}

				if (!$.pgsi.isNullOrUndefined($parent.find('input[name="statusValue"]')).length > 0) {
					oEmploymentInfo.employmentInfoStatusValue = $parent.find('input[name="statusValue"]').val();
				}

				if (!$.pgsi.isNullOrUndefined($parent.find('.employeeId')).length > 0) {
					oEmploymentInfo.employeeId = $parent.find('.employeeId').text().trim();
				}

				if (!$.pgsi.isNullOrUndefined($parent.find('.hireDate')).length > 0 && !$.pgsi.isNullOrUndefined($parent.find('.hireDate').text().trim())) {
					oEmploymentInfo.hireDate = (new Date($parent.find('.hireDate').text().trim())).getTime();
				}

				if (!$.pgsi.isNullOrUndefined($parent.find('.enrollDate')).length > 0 && !$.pgsi.isNullOrUndefined($parent.find('.enrollDate').text().trim())) {
					oEmploymentInfo.enrollDate = (new Date($parent.find('.enrollDate').text().trim())).getTime();
				}

				oEmploymentInfo.id = $parent.find('input[name="id"]').val();

				if (!$.pgsi.isNullOrUndefined($parent.find('.jobTitle')).length > 0) {
					oEmploymentInfo.jobTitle = $parent.find('.jobTitle').text().trim();
				}

				oEmploymentInfo.locationId = $parent.find('input[name="locationId"]').val();
				oEmploymentInfo.version = $parent.find('input[name="version"]').val();
				oEmploymentInfo.locationName = $parent.find('.locationName').text().trim();
				oEmploymentInfo.memberId = $("#business-id").val();

				return oEmploymentInfo;
			},

			fnAttachEventHandlers : function(event) {

					event.preventDefault();

					var $box  = $(this).parents('.box');

					var oEmploymentInfo = new EmploymentInfo();
					// Fill EmploymentInfo object with view data
					oEmploymentInfo = pgsi.pages.employment.view.fillObject(oEmploymentInfo, $box);
					oEmploymentInfo.modelAction = "UPDATE";

					// Build the request
					var oRequest = new MemberMaintenanceRequest({
						member : {
							id : parseInt($('#business-id').val()),
							personTypeValue : parseInt($("#personType").val()),
							pepStatusValue : parseInt($("#personPepStatus").val()),
							personStatusValue : parseInt($("#status-value").val()),
							modelAction : "NONE",
							version : pgsi.version.versionMember,
							risk : { modelAction: "NONE" },
							employmentInfoList : [
								oEmploymentInfo
							]
						}
					});

					var fetchCallBack = function (oResponse) {
						// Fill Employment info section with new data
						pgsi.pages.employment.view.fill(oResponse.memberList[0]);
						pgsi.version.versionMember = oResponse.memberList[0].version;
					};

					if ($(this).hasClass('activate')) {
						var fnCallBack = function(oResponse) {
							if (oResponse.operationSuccess == true) {
								$.pgsi.ajax.post({
									 sUrl       : "api/member/fetch",
									 oRequest   : { id : oRequest.member.id },
									 fnCallback : fetchCallBack
								});
							}

							else {
								pgsi.pages.sendsolv.fnDialogMessageError("",{},oResponse,null,$.pgsi.locale.get("commons.dialog.error.title"),true);
							}
						}

						oRequest.member.employmentInfoList[0].employmentInfoStatusValue = 1;
						oRequest.member.employmentInfoList[0].enrollDate = (new Date()).getTime();

						pgsi.util.actiondialog.launchActionDialog("activeDesactiveDialog", pgsi.pages.business.dialogSettings.activeDesactiveDialog(
							"api/member/update",
							 oRequest,
							 $.pgsi.locale.get("pages.member.view.employment.activate"),
							 fnCallBack,
							 "<span>" + $.pgsi.locale.get("pages.person.dialog.status.question", $.pgsi.locale.get("pages.view.activate"), $.pgsi.locale.get("commons.pages.employment")) + "</span>",
							"pages.view.activate"));

					}

					else if ($(this).hasClass('deactivate')) {

						var fnCallBack = function(oResponse) {

							if (oResponse.operationSuccess == true) {

								$.pgsi.ajax.post({
									 sUrl       : "api/member/fetch",
									 oRequest   : { id : oRequest.member.id },
									 fnCallback : fetchCallBack
								});
							}

							else {
								pgsi.pages.sendsolv.fnDialogMessageError("",{},oResponse,null,$.pgsi.locale.get("commons.dialog.error.title"),true);
							}
						}

						oRequest.member.employmentInfoList[0].employmentInfoStatusValue = 2;

						pgsi.util.actiondialog.launchActionDialog("activeDesactiveDialog", pgsi.pages.business.dialogSettings.activeDesactiveDialog(
							"api/member/update",
							 oRequest,
							 $.pgsi.locale.get("pages.member.view.employment.disable"),
							 fnCallBack,
							 "<span>"+$.pgsi.locale.get("pages.person.dialog.status.question",$.pgsi.locale.get("pages.view.disable"),$.pgsi.locale.get("commons.pages.employment")) + "</span>",
							 "pages.view.disable"));
					}

					else if ($(this).hasClass('delete')) {
						// Do nothing for now.
					}

					else if ($(this).hasClass('edit')) {
						sTitle   = $.pgsi.locale.get("pages.business.dialog.employment.title.edit");
						pgsi.util.actiondialog.launchActionDialog("insUpdEmployment", pgsi.pages.business.dialogSettings.insUpdEmployment(sTitle, oRequest));
					}

					else {
						oRequest.member.employmentInfoList[0].modelAction = "INSERT";
						oRequest.member.employmentInfoList[0].employmentInfoStatusValue = 3;
						sTitle   = $.pgsi.locale.get("pages.business.dialog.employment.title.add");
						pgsi.util.actiondialog.launchActionDialog("insUpdEmployment", pgsi.pages.business.dialogSettings.insUpdEmployment(sTitle, oRequest));
					}
			},

			fnFillItens : function(oEmploymentInfoList, oTransferSettingsList, sSdnStatus) {

				var $container = $("section.employment.view").find(".container");
				$container.empty();

				if (oEmploymentInfoList.length == 0) {
					$container.append("<p class='empty'>" + $.pgsi.locale.get("pages.member.view.employment.empty") + "</p>");
				}

				for (var i=0; i < oEmploymentInfoList.length; i++) {

					var sEmploymentList = "";

					(function(){
						var oEmploymentInfo = oEmploymentInfoList[i];

						return (function(oEmploymentInfo) {
							var oEmploymentInfo;

							var sEnrolledDate = "";
							var sHiredDate = "";

							var sHideActive = "";
							var sHideDisable = "";

							var sEmployeeIdRow = "";
							var sJobTitleRow = "";
							var sHiredDateRow = "";
							var sEnrolledDateRow = "";
							var sPayPerPeriodRow = "";

							var sTransfersRow = "";
							var sInsUpdLinks = "";

							oEmploymentInfo = oEmploymentInfoList[i];

							if (oEmploymentInfo.employmentInfoStatusValue === 1) {
								sHideActive = " hide";
							}

							else {
								sHideDisable = " hide";
							}

							if (!$.pgsi.isNullOrUndefined(oEmploymentInfo.employeeId)) {
								sEmployeeIdRow = '<div class="label spacer newline hide">' + $.pgsi.locale.get("pages.member.view.employeeid") + '<b><span class="employeeId"> ' + oEmploymentInfo.employeeId + '</span></b></div>';
							}

							if (!$.pgsi.isNullOrUndefined(oEmploymentInfo.jobTitle)) {
								sJobTitleRow = '<div class="label spacer newline jobTitle hide">' + oEmploymentInfo.jobTitle + '</div>';
							}


							if (!$.pgsi.isNullOrUndefined(oEmploymentInfo.hireDate)) {
								sHiredDate = $.pgsi.date.format(new Date(oEmploymentInfo.hireDate), "mm/dd/yy", null);
								sHiredDateRow = '<div class="label newline hide">' + $.pgsi.locale.get("pages.member.view.hired") + ' <span class="hireDate">' + sHiredDate + '</span></div>'
							}

							if (!$.pgsi.isNullOrUndefined(oEmploymentInfo.enrollDate)) {
								sEnrolledDate = $.pgsi.date.format(new Date(oEmploymentInfo.enrollDate), "mm/dd/yy", null);
								sEnrolledDateRow = '<div class="label newline">' + $.pgsi.locale.get("pages.member.view.enrolledsince") + ' <span class="enrollDate">' + sEnrolledDate + '</span></div>';
							}

							if (!$.pgsi.isNullOrUndefined(oEmploymentInfo.payPerPeriod)) {
								var input = document.createElement("input");
								input.type = "text";
								input.dataset.inputmask = "'alias': 'numeric', 'groupSeparator': ',', 'autoGroup': true, 'digits': 2, 'digitsOptional': false, 'prefix': 'USD ', 'placeholder': '0'";
								input.value = oEmploymentInfo.payPerPeriod;
								var $input = $(input);
								$input.inputmask();

								sPayPerPeriodRow = '<div class="label spacer newline hide">' + $.pgsi.locale.get("pages.member.view.payatenrollment") + '</div> <div class="value spacer hide"><span class="payPerPeriod">' + $input.val() + '</span></div>';
							}

							var iNumberOfTransfers = pgsi.pages.employment.view.filterNumberOfTransfers(oEmploymentInfo, oTransferSettingsList);

							if (iNumberOfTransfers > 0) {
								var iNumberOfRecipients = pgsi.pages.employment.view.filterNumberOfRecipients(oEmploymentInfo, oTransferSettingsList);

								if((iNumberOfTransfers > 1)&&(iNumberOfRecipients > 1)){
									var sNumberRecipientTitle = $.pgsi.locale.get("pages.member.view.employment.plural.transfers", iNumberOfTransfers.toString(), iNumberOfRecipients.toString())
								}else if((iNumberOfTransfers < 1)&&(iNumberOfRecipients > 1)){
									var sNumberRecipientTitle = $.pgsi.locale.get("pages.member.view.employment.plutal.recipients", iNumberOfTransfers.toString(), iNumberOfRecipients.toString())
								}else if((iNumberOfRecipients <= 1)&&(iNumberOfTransfers > 1)){
									var sNumberRecipientTitle = $.pgsi.locale.get("pages.member.view.employment.singular.recipient", iNumberOfTransfers.toString(), iNumberOfRecipients.toString())
								}else{
									var sNumberRecipientTitle = $.pgsi.locale.get("pages.member.view.employment.singular.transfer", iNumberOfTransfers.toString(), iNumberOfRecipients.toString())
								}

								sTransfersRow = "<div class='label spacer newline'>" + sNumberRecipientTitle + "</div>'";
							}

							// Retrieve Organization Info from Location
							$.pgsi.ajax.post({
								 sUrl       : "api/location/fetch",
								 oRequest   : { id : oEmploymentInfo.locationId },
								 bHideProgressBar : true,
								 fnCallback : function (oResponse){

								 	if (!pgsi.util.page.fnIsSDNFlagged(sSdnStatus)) {

								 		<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN')">
									 		 sInsUpdLinks = '<div class="small-box"><div class="links viewNote">'
									 		 	+ '<a href="' + oEmploymentInfo.id + '" class="ui-subtitle edit" title="' + $.pgsi.locale.get("commons.pages.edit")  + '">'
									 		 	+ '	 <span class="icon-small-button icon-nav icon-pencil edit"></span> <span>' + $.pgsi.locale.get("commons.pages.edit")  + '</span>'
									 		 	+ '</a>'
									 		 	+ '<a href="#" class="ui-subtitle activate ' + sHideActive + '" title="' + $.pgsi.locale.get("pages.view.activate") + '">'
									 		 	+ '	 <span class="icon-small-button icon-nav icon-check-mark"></span>'
									 		 	+ '	 <span>' + $.pgsi.locale.get("pages.view.activate") + '</span>'
									 		 	+ '</a>'
									 		 	+ '<a href="#" class="ui-subtitle deactivate ' + sHideDisable + '" title="' + $.pgsi.locale.get("pages.view.disable") + '">'
									 		 	+ '	 <span class="icon-small-button icon-nav icon-minus-circle"></span>'
									 		 	+ '  <span>' + $.pgsi.locale.get("pages.view.disable")  + '</span>'
									 		 	+ '</a>'
									 	    	+ '<a href="#" class="ui-subtitle delete" title="' + $.pgsi.locale.get("commons.pages.delete")  + '">'
												+ '	 <span class="icon-small-button icon-nav icon-trash-bin delete"></span>'
												+ '	 <span>' + $.pgsi.locale.get("commons.pages.delete")  + '</span>'
												+ '</a>'
									 	    	+ '</div></div>';
										</sec:authorize>
								 	}

								 	sEmploymentList = '<div class="outer-box"> <div class="box"><input type="hidden" name="version" value="' + oEmploymentInfo.version + '"><input type="hidden" name="statusValue" value="' + oEmploymentInfo.employmentInfoStatusValue + '"><input type="hidden" name="id" value="' + oEmploymentInfo.id + '"><input type="hidden" name="employmentInfoStatusValue" value="' + oEmploymentInfo.employmentInfoStatusValue + '"><input type="hidden" name="locationId" value="' + oEmploymentInfo.locationId + '">'
								 		+ sInsUpdLinks
								 		+'<div class="value newline"> <a class="locationName alist" href="location/view?locationId=' + oEmploymentInfo.locationId +'" title="' + oEmploymentInfo.locationName + '">' + oEmploymentInfo.locationName + '</a> </div> <div class="label newline"> <a href="organization/view?organizationId=' + oResponse.locationList[0].parentOrganizationId + '" title="' + oResponse.locationList[0].parentOrganizationName + '" class="organizationName alist">' + oResponse.locationList[0].parentOrganizationName + '</a> </div>' + sEmployeeIdRow + sJobTitleRow	+ sHiredDateRow	+ sEnrolledDateRow + sPayPerPeriodRow + sTransfersRow + '</div> </div>';

										$container.append(sEmploymentList);

										$container = $("section.employment.view").find(".container");

										var $box = $container.find(".outer-box").last();
										$box.css("min-height", $box.find(".box").outerHeight() + "px");


										$box.find(".links a").bind('click', pgsi.pages.employment.view.fnAttachEventHandlers);

										$.pgsi.progressBar.stop();
								 }
							});
						})();
					})();
				}
			}
		}

	}

</script>

</sec:authorize>