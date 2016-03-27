/**
 * @namespace qat.util.page
 * @fileoverview Defines common page-related functionality. Many of those
 *               functions require specific HTML structures, ids or classes to
 *               work correctly.
 * @author Flavio Tosta
 */

/**
 * The main namespace for page-related functionality.
 */

qat.util.page = {

	fnIsSDNFlagged : function(sSDNStatus) {
		if (!$.qat.isNullOrUndefined(sSDNStatus) && sSDNStatus !== "UNKNOWN" && sSDNStatus !== "NEUTRAL" && sSDNStatus != "FALSE_POSITIVE") {
		 	return true;
		}

		 else {
		 	return false;
		 }

		return false;
	},

	fnDisableActiveDesctive : function(sUrl,oRequest,fnFillCallBack){

		$.qat.ajax.post({
			sUrl 		: sUrl,
			oRequest 	: oRequest,
			fnCallback  : function(oResponse) {

				if (oResponse.operationSuccess == true) {
					 fnFillCallBack(oResponse);
				}

				else{
					qat.pages.sendsolv.fnDialogMessageError("",{},oResponse,null,$.qat.locale.get("commons.dialog.error.title"),true);
				}
			}
		});

	},

	fnValidadeSicNaics : function(sName, iCodeValueEnum){

		var bReturn = false;
		//TODO
		$.qat.ajax.post({
			sUrl 		: "api/location/fetchSicNaics",
			oRequest 	: iCodeValueEnum,
			bHideProgressBar : true,
			bAsync      : false,
			fnCallback  : function(oResponse) {

				if (oResponse) {

					for (var i=0; i < oResponse.length; i++) {
						if (oResponse[i].key === sName) {
							bReturn =  true;
							break;
						}
					}
				}
			}

		});

		return bReturn;

	},

	fnValidadeDatePayDay : function(){

		var bReturn = false;

		$.each( $('.contact-info2').find('input'), function( i, val ) {

			if ($(this).is(":checked")) {
				bReturn = true;
			}
		})

		return bReturn
	},

	fnReloadTable     : function(sNameSpace)
	{
		//Reload table function
		$.qat.table.reloadTable({table : sNameSpace});

	},
	fnInsertButtonSDNSAR : function(aData,sPage){

		var sButtonSDN = "";
		var sButtonSAR = "";

		if ((aData.sdnstatus === "POSITIVE")||(aData.sdnstatus === "PENDING_INVESTIGATION")||(aData.sdnstatus === "PENDING_NEUTRAL")||(aData.sdnstatus === "PENDING_FEDERAL_INVESTIGATION")){
			sButtonSDN = '<a href="sdn/'+sPage+'/view?type='+sPage+'&id=' + aData.id + '" title="'+ $.qat.locale.get("pages.sdn.view.table.SDN") + ' ' + aData.firstName + ' ' + aData.lastName + '" id="update" class="alist"><span class="icon-nav icon-security icon-shield82 icon-small-button sarDialog"></span></a>'
		}
		sButtonSAR = '<a href="#" class="sarDialog"><span class="icon-nav icon-security icon-eye-1 icon-small-button sarDialog icon-small-button" title="' + $.qat.locale.get("commons.title.table.button.SAR") + ' ' + aData.firstName + ' ' + aData.lastName + '"></span></a>';

		return sButtonSDN + sButtonSAR;
	},

	fnInsertMask : function(sMask, iValue){
		var sReturn;
		if((!$.qat.isNullOrUndefined(sMask))&&(!$.qat.isNullOrUndefined(iValue))){
			var input  = document.createElement("input");
			input.type = "text";
			input.value  = iValue;
			var $maskRecip = $(input);
			$maskRecip.mask(sMask);
			sReturn = $maskRecip.val()
		}else{
			sReturn = "";
		}
		return sReturn;
	},

	fnInsertMaskNumeric : function(sMask, iValue){
		var input = document.createElement("input");
		input.type = "text";
		input.dataset.inputmask = sMask;
		input.value = iValue;
		var $input = $(input);
		$input.inputmask();

		return $input.val()
	},

	fnValidadeRecipient : function(iId){

		iId = iId.replace('-','');

		var bReturn = false;

		$.qat.ajax.post({
			sUrl 		: "api/recipient/fetch",
			oRequest 	: {stringId : iId},
			bAsync      : false,
			fnCallback  : function(oResponse) {

				if (oResponse.operationSuccess == true) {
					if(oResponse.recipientList.length > 0){
						bReturn = true
					}else{
						$('.newline label.parent.spacer').text('');
					}
				}
				$.qat.progressBar.stop();
			}

		});

		return bReturn;

	},
	fnValidadeSAR : function(iId){

		iId = iId.replace('-','');
		var oResponse;
		var bReturn = false;
		if ((iId.indexOf("LAA") != -1)||(iId.indexOf("CAA") != -1)||(iId.indexOf("MAA") != -1)||(iId.indexOf("OAA") != -1)||(iId.indexOf("RAA") != -1 ))
		{
			oResponse = qat.pages.sar.common.ajaxCallSearch(iId);
			if (!$.qat.isNullOrUndefined(oResponse)) {
				bReturn = true;
			}
		}

		return bReturn;

	},


	// Supress back to previous page default behaviour
	supressBackspace : function() {
		$(document).unbind('keydown').bind('keydown', function (event) {
		    var doPrevent = false;

		    if (event.keyCode === 8) {
		        var d = event.srcElement || event.target;
		        if ((d.tagName.toUpperCase() === 'INPUT' &&
		             (
		                 d.type.toUpperCase() === 'TEXT' ||
		                 d.type.toUpperCase() === 'PASSWORD' ||
		                 d.type.toUpperCase() === 'FILE' ||
		                 d.type.toUpperCase() === 'EMAIL' ||
		                 d.type.toUpperCase() === 'SEARCH' ||
		                 d.type.toUpperCase() === 'DATE' )
		             ) ||
		             d.tagName.toUpperCase() === 'TEXTAREA') {
		            doPrevent = d.readOnly || d.disabled;
		        }
		        else {
		            doPrevent = true;
		        }
		    }

		    if (doPrevent) {
		        event.preventDefault();
		    }

		});
	},

	allowBackspace : function() {
		$(document).unbind('keydown');
	},

	getParameterFromUrl : function(sId) {
		return $.address.parameter(sId);
	},

	fnCheckXSS : function(value) {
		var bReturn;

		try {
			bReturn = decodeURI(value).match(/(<([^>]+)>)/ig);
		} catch (e) {
			bReturn = null;
		}

		return bReturn;
	},
	fnUpdateStatus : function(sUrl,iId,sType,sStatusValue,fnRefreshPage,sTitle,sText,bList){

		var oObject,
			oRequestM,
			sUrlApply,
			sTextButton;
		$.qat.ajax.post({
			 sUrl 		: sUrl,
			 oRequest 	: {id : iId},
			 fnCallback : function(oResponse){
				switch (sType)
				{
					case 'member':
						oObject  = oResponse.memberList[0];
						oObject.modelAction = "UPDATE"
						oObject.personStatusValue = sStatusValue;
						oRequestM =  new MemberMaintenanceRequest({member : oObject});
						sUrlApply = "api/member/applyStatus"
						break;
					case 'recipient':
						oObject = oResponse.recipientList[0];
						oObject.modelAction = "UPDATE"
						oObject.personStatusValue = sStatusValue;
						oRequestM =  new RecipientMaintenanceRequest({recipient : oObject});
						sUrlApply = "api/recipient/applyStatus"
						break;
					case 'contact':
						//TODO
						oObject = oResponse.liaisonList[0];
						break;
					case 'location':
						oObject = oResponse.locationList[0];
						oObject.modelAction = "UPDATE"
						oObject.statusValue = sStatusValue;
						oRequestM =  new LocationMaintenanceRequest({location : {statusValue : oObject.statusValue ,businessType: oObject.businessType,id : oObject.id}});
						sUrlApply = "api/location/applyStatus"
						break;
					case 'organization':
						oObject = oResponse.organizationList[0];
						oObject.modelAction = "UPDATE"
						oObject.statusValue = sStatusValue;
						oRequestM =  new OrganizationMaintenanceRequest({organization : oObject});
						sUrlApply = "api/organization/applyStatus"
						break;
				}
				var fnCallBackActive = function(oReturn) {

						if (oReturn.operationSuccess == true) {
							$('.business-status').text("");
							if(sStatusValue == 2){
								var sStatusName = $.qat.enums.internationalizeByLabel("com.prosperitasglobal.sendsolv.model.StatusEnum","ACTIVE");
								$("#status-value").val(1);
								sTextButton = "pages.view.activate";
							}else{
								var sStatusName = $.qat.enums.internationalizeByLabel("com.prosperitasglobal.sendsolv.model.StatusEnum","INACTIVE");
								$("#status-value").val(2);
								sTextButton = "pages.view.deactivate";
							}
							$('.business-status').text(sStatusName);
							$('#status').text(sStatusName);
							$("#status-field").text(sStatusName);
							$('.active').hide();
							$('.deactivate').show();
							$("#status-value").val(1);

							if (!$.qat.isNullOrUndefined(fnRefreshPage)) {
								if(bList){
									fnRefreshPage(oResponse);
								}else{
									fnRefreshPage();
								}
							}
						}

						else {
							qat.pages.sendsolv.fnDialogMessageError("",{},oReturn,null,$.qat.locale.get("commons.dialog.error.title"),true);
						}
						$.qat.progressBar.stop();
					}

				if(sStatusValue == 1){
					sTextButton = "pages.view.activate";
				}else{
					sTextButton = "pages.view.deactivate";
				}
				$.qat.progressBar.stop();
				qat.util.actiondialog.launchActionDialog("activeDesactiveDialog", qat.pages.business.dialogSettings.activeDesactiveDialog(
						 sUrlApply,
						 oRequestM,
						 sTitle,
						 fnCallBackActive,
						 sText,
						 sTextButton));
			 }
		});

	},

	getSortExpression : function(table, aaSorting, config) {

		var tableSettings = config;
		var sortEnum = tableSettings.oSettings.sortEnum;
		var oSorted = table.find(".sorting_asc, .sorting_desc");
		var oSpan;
		var aSortExpressions = [];
		var aFieldId = [];
		var sEnumValue;
		var sDirection;
		var sFullDirection;

		if (!oSorted.length) {
			oSorted = table.find("thead th:eq(" + aaSorting[0][0] + ")");
		}
		oSpan = oSorted.find("span");
		if (!$.qat.isNullOrUndefined(oSpan.attr('id'))) {
			if (oSpan.length > 0) {
				aFieldId = oSpan.attr('id').replace('id=\"', '').replace('\"',
						'').replace(/-/g, '_').split('|');
			} else {
				aFieldId = tableSettings.oSettings.sDefaulSortEnum.split('|');
			}
		}
		if (aaSorting != undefined) {
			sDirection = aaSorting[0][1];
		} else {
			sDirection = table.fnSettings().aaSorting[0][1];
		}

		sFullDirection = sDirection.substr(0, 1).toUpperCase()
				+ sDirection.substr(1) + 'ending';

		for (var i = 0; i < aFieldId.length; i++) {
			sEnumValue = aFieldId[i];

			aSortExpressions.push({
				'field' : sEnumValue,
				'direction' : sFullDirection
			});

			$.address.parameter("sort",
					(aFieldId[i].toLowerCase() + "|" + sDirection));
		}

		return sortExpressions = aSortExpressions;
	},

	form : {
		fnInitForm : function($container) {

			if (!$.qat.isNullOrUndefined($container)) {
				qat.util.page.form.fnInitSelectmenu($container.find('select'));
				qat.util.page.form.fnInitTolltip($container.find('.error'));
				$container.find(".btn").button();
			}

			else {
				qat.util.page.form.fnInitSelectmenu();
				qat.util.page.form.fnInitTolltip();
				$(".btn").button();
			}


		},

		// Set up select elements
		fnInitSelectmenu : function($elem) {

			if ($.qat.isNullOrUndefined($elem)) {
				$elem = $("select");
			}
			$elem.selectmenu({
				"change" : function(event, ui) {
					var $select = $(event.target);

					qat.util.page.form.initRequiredSelects($select);

				}
			});

			var $span;

			$elem.filter(".required").each(function() {
				$span = $(this).next('span');

				$span.addClass("required");

				if ($span.text() <= 1) {
					$span.addClass("required-field");
				}
			});
		},

		initRequiredSelects : function($select) {
			var $span = $select.nextAll("span.ui-selectmenu-button").first();
			if ($span.length === 0) {
				$span = $select.prevAll("span.ui-selectmenu-button").first();
			}

			if ($span.hasClass("required")) {
				if ($span.text().trim().length > 0) {
					$span.removeClass("error");
					$span.removeClass("required-field"); // * placeholder
				}
				else {
					$span.addClass("required-field"); // * placeholder
					$span.addClass("error");
				}
			}
		},

		fnInitTolltip : function(sSelectors) {

			var aItens = "input.error, textarea.error, .ui-selectmenu-button.error";

			if (!$.qat.isNullOrUndefined(sSelectors)) {
				aItens = sSelectors;
			}

			$(document).tooltip({
				items : aItens,

				content : function() {
				 	if ($(this).siblings("label.error").length) {
						return "<span class='icon-nav icon-exclamation-triangle'></span>" + $(this).siblings("label.error").text();

					}

					else {
						return "<span class='icon-nav icon-exclamation-triangle'></span>" + $.qat.locale.get("pages.form.required");
					}

			     },

				position : {
					my : "center bottom-20",
					at : "center top",
					using : function(position, feedback) {
						$(this).css(position);
						$("<div>").addClass("arrow").addClass(feedback.vertical).addClass(feedback.horizontal).appendTo(this);
					}
				}
			});
		}

	},

	business : {
		form : {
			fnInitForm: function() {

				qat.util.page.form.fnInitForm();
				qat.util.page.business.form.setFieldSizes();
				qat.util.page.business.form.maskFields.fnMask();

				var fnActivate = function (){
					$input = $(this);

					$sic = $("input#sic");
					$naics = $("input#naics");

					// Neither SIC or NAICS filled
					if ($sic.val().length === 0 && $naics.val().length === 0) {
						$sic.addClass("required").prop("placeholder", "*");
						$naics.addClass("required").prop("placeholder", "*");
					}

					else {
						// SIC
						if ($input.attr("id") === "sic") {

							if ( $input.val().length > 0) {
								$naics.removeClass("required").removeClass("error").prop("placeholder", "");
							}
							else {
								$naics.addClass("required").prop("placeholder", "*");
								if ($naics.val().length > 0 ) {
									$input.removeClass("required").removeClass("error").prop("placeholder", "");
								}
							}
						}

						// NAICS
						else {
							if ( $input.val().length > 0) {
								$sic.removeClass("required").removeClass("error").prop("placeholder", "");
							}
							else {
								$sic.addClass("required").prop("placeholder", "*");
								if ($sic.val().length > 0 ) {
									$input.removeClass("required").removeClass("error").prop("placeholder", "");
								}
							}
						}
					}

				};

				$("#sic").on("blur", fnActivate);
				$("#naics").on("blur", fnActivate);

				$("#naics").inputmask("Regex");
				$("#sic").inputmask("Regex");
			},

			maskFields: {

				aMasks : {
						"ein" : "**-*******"
					},

				fnMask : function() {
					if( $.isFunction( $.fn.mask) ) {
						$("#ein").mask(qat.util.page.business.form.maskFields.aMasks["ein"]);
					}
				},

				fnUnmask : function () {
					$("#ein").mask("*********");
				}

			},

			setFieldSizes : function() {
				// Set up elements size
				$("#create-form input").outerHeight(30);
				$("#create-form .ui-selectmenu-button").outerHeight(30);
				$("#name").outerWidth(360);
				$("#ein").outerWidth(100);
				$("#sic").outerWidth(100);
				$("#naics").outerWidth(100);
				$("#key").outerWidth(85);
				$("#number-members-button").outerWidth(150);
				$("#number-migrant-members-button").outerWidth(150);
				$("#payroll-centralized-button").outerWidth(130);
				$(".buttons-form input").outerHeight(32);
				$("#buttons a").outerHeight(45);
				$("#total-locations").outerWidth(50);
				$("#organization-key").outerWidth(360);
			},

			fillFormFields : function(oData) {
				var oBusiness = oData;

				$("#business-id").val(oBusiness.id);
				$("#name").val(oBusiness.name);
				$("#dbaname").val(oBusiness.dbaName);
				$("#status").val(oBusiness.status);

				var oEin = document.createElement("input");
				oEin.type= "text";
				oEin.value = oBusiness.employerIdentificationNumber;
				var $ein = $(oEin);
				$ein.mask("99-9999999");

				$("#ein").val($ein.val());

				if (oBusiness.standardIndustrialClassificationCode) {
					$("#sic")
							.val(
									oBusiness.standardIndustrialClassificationCode.code);
				}

				if (oBusiness.northAmericanIndustryClassificationSystemCode) {
					$("#naics")
							.val(
									oBusiness.northAmericanIndustryClassificationSystemCode.code);
				}

				if (!$.qat.isNullOrUndefined(oBusiness.numberOfEmployees)) {
					// Number Of Employees
					$('#number-members').val(oBusiness.numberOfEmployees.id)
							.selectmenu('refresh').nextAll(
									".ui-selectmenu-button").removeClass(
									"required-field").outerWidth(150);

					var iIndex = $('#number-members').find(":selected").index();

					$("#number-migrant-members").find("option").each(function(i){

						if (i >  iIndex) {
							$(this).remove();
						}
					});
				}

				if (!$.qat.isNullOrUndefined(oBusiness.numberOfMigrantWorkers)) {
					// Number of Imigrants
					$('#number-migrant-members').val(
							oBusiness.numberOfMigrantWorkers.id).selectmenu(
							'refresh').nextAll(".ui-selectmenu-button")
							.removeClass("required-field").outerWidth(150);
				}

				// Either SIC or NAIC are required but not BOTH
				var $sic = $("input#sic");
				var $naic = $("input#naics");

				if ($sic.val().length > 0) {
					$naic.removeClass("required required-field");
					$naic.removeClass("error");
					$naic.prop("placeholder", "");
				}

				else if ($naic.val().length > 0) {
					$sic.removeClass("required required-field");
					$sic.removeClass("error");
					$sic.prop("placeholder", "");
				}

			},

			fillObject : function(sModelAction) {

				var oBusiness = new Business();

				var sName = $("#name").val();
				var sEIN = $("#ein").val();
				var sSIC = $("#sic").val();
				var sNAICS = $("#naics").val();
				var sNumberOfMembers = $("#number-members").val();
				var sNumberOfMigrantMembers = $("#number-migrant-members").val();
				var sCountryId = $("#country").val();
				var iBusinessId = $("#business-id").val();

				oBusiness.id = iBusinessId;
				oBusiness.country = {
					code : sCountryId
				};

				oBusiness.employerIdentificationNumber = sEIN;

				oBusiness.name = sName;
				oBusiness.northAmericanIndustryClassificationSystemCode = {
					code : sNAICS
				};
				oBusiness.standardIndustrialClassificationCode = {
					code : sSIC
				};
				oBusiness.numberOfEmployees = {
					id : parseInt(sNumberOfMembers)
				};
				oBusiness.numberOfMigrantWorkers = {
					id : parseInt(sNumberOfMigrantMembers)
				};

				oBusiness.modelAction = sModelAction;

				return oBusiness;
			}
		},

		view : {
			fillFields : function(oData) {

				var oBusiness = oData;

				// SDN status specific formatting / styling
				if (qat.util.page.fnIsSDNFlagged(oBusiness.sdnstatus)) {
					$("#edit-business, #edit-risk, #add-contact, #add-document").remove();
					$("#tabs").find(".ui-tabs-panel").first().addClass('sdnFlagged');
				}

				$("#business-id").val(oBusiness.id);
				$("#business-type").val(oBusiness.businessTypeValue);
				$("#business-name, #company-name, #company-name-field").text(oBusiness.name);

				if (!$.qat.isNullOrUndefined(oBusiness.employerIdentificationNumber)) {
					var oEin = document.createElement("input");
					oEin.type= "text";
					oEin.value = oBusiness.employerIdentificationNumber;
					var $ein = $(oEin);
					$ein.mask("99-9999999");
					$("#ein-field").text($ein.val());
					$("#ein-field").show();
					$("#ein-field").prev("span.label").show();
				}

				else {
					$("#ein-field").hide();
					$("#ein-field").prev("span.label").hide();
				}

				if (oBusiness.standardIndustrialClassificationCode) {
					$("#sic-field").text(oBusiness.standardIndustrialClassificationCode.value);
					$("#sic-field").show();
					$("#sic-field").prev("span.label").show();
				}

				else {
					$("#sic-field").hide();
					$("#sic-field").prev("span.label").hide();
				}

				if (oBusiness.northAmericanIndustryClassificationSystemCode) {
					$("#naics-field").text(oBusiness.northAmericanIndustryClassificationSystemCode.value);
					$("#naics-field").show();
					$("#naics-field").prev("span.label").show();
				}

				else {
					$("#naics-field").hide();
					$("#naics-field").prev("span.label").hide();
				}

				if (!$.qat.isNullOrUndefined(oBusiness.numberOfEmployees)) {
					// Number Of Employees
					$('#number-members-field').text(oBusiness.numberOfEmployees.name);
				}

				if (!$.qat.isNullOrUndefined(oBusiness.numberOfMigrantWorkers)) {
					// Number of Imigrants
					$('#migrant-members-field').text(oBusiness.numberOfMigrantWorkers.name);
				}

				if (!$.qat.isNullOrUndefined(oBusiness.numberOfMigrantWorkers)) {
					// Number of Imigrants
					$('#migrant-members-field').text(oBusiness.numberOfMigrantWorkers.name);
				}

				if(oBusiness.statusValue === 1){
					$('.active').hide();
					$('.deactivate').show();
				}else if(oBusiness.statusValue === 2){
					$('.deactivate').hide();
					$('.active').show();
				}else if(oBusiness.statusValue === 3){
					$('.deactivate').hide();
					$('.active').show();
				}else if(oBusiness.statusValue === 4){
					$('.deactivate').hide();
					$('.active').show();
				}

				// Status
				if (oBusiness.businessTypeValue === 1 ) {
					$('#status-template .newline').text($.qat.locale.get("commons.organization.view.organizationId"))
				}

				else {
					$('#status-template .newline').text($.qat.locale.get("pages.location.locationId"))
				}

				$('#status-template #status').text($.qat.enums.internationalizeByLabel("com.prosperitasglobal.sendsolv.model.StatusEnum",oBusiness.status));

				$('#status-template #status-id').text(qat.util.page.fnInsertMask("***-99999",oBusiness.key) || oBusiness.id);

				// SDN Status
				qat.util.page.status.view.fnFillStatusFlags(oBusiness);


			}
		}
	},

	person : {
		otherNames : {

			form : {

				fnInit : function($container, iBusinessId) {

					$("#otherName").attr('data-personid', iBusinessId);
					$("#otherName").attr('data-modelaction', "INSERT");

					$container.on('click', 'a.close-button-form',  function(e) {
						e.preventDefault();

						$(this).prev('input[name="otherName"]').attr('data-modelaction', "DELETE").addClass('hide').prev().addClass('hide');

						$(this).remove();
					});

					$('#add-another-name').on('click', function(e) {
						e.preventDefault();

						$container.append(qat.util.page.person.otherNames.form.fnCreateNodes($container, iBusinessId));
						$container.find('input[name="otherName"]').last().focus();
					});
				},

				// Returns array containing [0] label, [1] value, [3] close button
				fnCreateNodes : function($container, iBusinessId) {

					var aOtherName =[];

					aOtherName.push($container.find('label').first().clone().removeAttr('for').text(''));
					aOtherName.push($container.find('input[name="otherName"]').first().clone().val('').removeAttr('id'));
					aOtherName[1].attr('data-id', '');
					aOtherName[1].attr('data-personid', iBusinessId);
					aOtherName[1].attr('data-modelaction', 'INSERT');
					aOtherName.push($container.find('a.close-button-form').first().clone().val('').removeAttr('id').removeClass('hide'));

					return aOtherName;
				},

				// Fill form with PersonNames array
				fnFill : function(aOtherNames, $container) {

					if (aOtherNames.length === 0) {
						return;
					}

					var aOtherName = qat.util.page.person.otherNames.form.fnCreateNodes($container);
				    var aOtherNameCopy = [];

					var aNodes = [];

					$container.empty();

					for (var i=0; i < aOtherNames.length; i++) {

						aOtherNameCopy[0] = aOtherName[0].clone();
						aOtherNameCopy[1] = aOtherName[1].clone();
						aOtherNameCopy[2] = aOtherName[2].clone();

						if (i === 0) {
							aOtherNameCopy[0].text($.qat.locale.get('pages.member.form.label.otherNames'));
							aOtherNameCopy[2].addClass('hide');
						}
						aOtherNameCopy[1].val(aOtherNames[i].otherName);
						aOtherNameCopy[1].attr("data-id", aOtherNames[i].id);
						aOtherNameCopy[1].attr('data-personid', aOtherNames[i].personId);
						aOtherNameCopy[1].attr('data-modelaction', "UPDATE");

						for (var j=0; j < aOtherNameCopy.length; j++) {
							aNodes.push(aOtherNameCopy[j]);
						}
					}

					$container.append(aNodes);
				},

				// Create array of PersonNames to be used in the request objects
				fnCreateArray : function($container) {
					var aInputs = $container.find('input[name="otherName"]');
					var aPersonNames = [];
					var oPersonName;
					var oInput;

					for (var i=0; i < aInputs.length; i++) {

						oInput = $(aInputs[i]);

						if (oInput.attr('data-modelaction').length === 0) {
							continue;
						}

						oPersonName = new PersonName({
							id : oInput.attr('data-id'),
							personId : oInput.attr('data-personid'),
							otherName : oInput.val(),
							modelAction : oInput.attr('data-modelaction')
						});

						aPersonNames.push(oPersonName);
					}

					return aPersonNames;

				}
			},

			view : {
				fnFill : function(aNameList, $container) {
					$container.empty();
					for (var i=0; i < aNameList.length; i++) {
						$container.append('<li>' + aNameList[i].otherName + '</li>');
					}
				}
			}

		}
	},

	email : {
		form : {
			maskFields: function(){
				if ($.isFunction($.fn.mask)) {

					$(".email").mask("");
					// Used to avoid event blur from plugin.
					// The default behavior from the plugin is clear input field when value does not match with mask.
					$(".phone-number").unbind("blur");
				}
			},

			setFieldSizes : function() {
				$(".email-type").next("span").outerWidth(140);
			},

			/*
	 		* Returns a new row for the email template
	 		*/
	 		createNewNode : function() {

	 			var $rows = $("#email-template").find(".container").find(".row-form");
		 		var $copy = $rows.first().clone();
		 		var iIndex = $rows.length;

		 		$.each($copy.find('input[type="text"], select'), function(index, value){
		 			$(value).prop('name', $(value).prop('name') + iIndex);
		 		});

				$copy.find('input').removeClass('required').removeClass('error').prop('placeholder', "").val('');
				$copy.find('select').removeClass('required').removeClass('error');
				$copy.find(".close-button-form").removeClass("hide");
				$copy.find(".ui-selectmenu-button").remove();
				$copy.find('input[type="radio"]').attr("checked", false);
				var sCopy = $("<div class='row-form'>" + $copy.html() + "</div>");

				return sCopy;
	 		},

			fillObject : function() {

				var aEmails = [];
				var oEmail;

				// Phone Contact
				$("#email-template").find(".row-form").each(function() {

					oEmail = new Email();

					var sEmailId = $(this).find(".email-id").val();
					var sModelAction = $(this).find(".model-action").val();
					oEmail.contactTypeValue = $(this).find(".email-type").val();

					oEmail.id = parseInt(sEmailId);
					oEmail.type = "email";

					if (sModelAction.toUpperCase().indexOf("DELETE") != -1) {

						if (!$.qat.isNullOrUndefined(sEmailId) && sEmailId.length > 0) {
							oEmail.modelAction = "DELETE";
							aEmails.push(oEmail);
						}
						return;

					}

					if (!$.qat.isNullOrUndefined(sEmailId) && sEmailId.length > 0) {
						oEmail.modelAction = "UPDATE";
					}

					else {
						oEmail.modelAction = "INSERT";
					}

					oEmail.emailAddress = $(this).find(".email-address").val();

					var sVersion = $(this).find(".email-version").val();

					if (!$.qat.isNullOrUndefined(sVersion) && sVersion.length > 0) {
						oEmail.version = parseInt(sVersion);
					}

					if ($(this).find('input:checked').prop("checked")) {
						oEmail.priorityValue = 1;
					}

					else{
						oEmail.priorityValue = 2;
					}

					if (oEmail.emailAddress.length !== 0 && oEmail.contactTypeValue.length !== 0) {
						aEmails.push(oEmail);
					}

				});

				return aEmails;
			},

			fillFormFields : function(oContactList) {
				var oEmailList = [];

				for (var i = 0; i < oContactList.length; i++) {
					// Check for Contact of type Email
					if (oContactList[i].contactTypeValue == 3
							|| oContactList[i].contactTypeValue == 6) {

						if ($.qat.isNullOrUndefined(oEmail)) {
							oEmailList.push(oContactList[i]);
						}
					}
				}

				var oEmail;
				var $emailTemplate = $("#email-template");
				var $email;

				// Iterates over the Email list and adds new rows
				for (var i=0; i < oEmailList.length; i++) {
					oEmail = oEmailList[i];

					var $copy = $(qat.util.page.email.form.createNewNode());

					if (i == 0) {
						$emailTemplate.find(".row-form").remove();
					}

					$emailTemplate.find(".container").append("<div class='row-form'>" + $copy.html() + "</div>");
					$emailTemplate = $("#email-template");

					$email = $emailTemplate.find(".container").find(".row-form:last-child");

					qat.util.page.form.fnInitSelectmenu($email.find("select"));

					$email.find(".email-version").val(oEmail.version);
					$email.find(".email-id").val(oEmail.id);
					$email.find(".email-type").val(oEmail.contactTypeValue).selectmenu('refresh').nextAll(".ui-selectmenu-button").removeClass("required-field");
					$email.find(".email-address").val(oEmail.emailAddress);

					qat.util.page.email.form.setFieldSizes();
					if (oEmail.priorityValue === 1) {
						$email.find("input[type='radio']").prop("checked", true);
						$email.find(".close-button-form").addClass("hide");
					}

					else {
						$email.find(".close-button-form").removeClass("hide");
					}
				}

				$emailTemplate = $("#phone-template");

				// mark the first phone as primary in case no other field is selected
				if ($emailTemplate.find("input[type='radio']:checked").length == 0 ) {
					$firstPhone = $emailTemplate.find(".row-form:eq(0)");
					$firstPhone.find("input[type='radio']").prop("checked", true);
					$firstPhone.find(".close-button-form").addClass("hide");
				}

			}
		},

		view : {
			fillFields : function(oContactList) {

				$("#email-container").empty();

				var oContact;
				var oEmailList = [];
				var sEmailList = "";
				var oEmail;

				for (var i = 0; i < oContactList.length; i++) {
					oContact = oContactList[i];

					if (oContact.contactType.toUpperCase() === "EMAIL_HOME" || oContact.contactType.toUpperCase() === "EMAIL_WORK"
						|| oContact.contactType.toUpperCase() === "EMAIL_PERSONAL") {
						oEmailList.push(oContact);
					}
				}

				for (var i = 0; i < oEmailList.length; i++) {
					oEmail = oEmailList[i];

					if (i === 0) {
						sEmailList += "<div class='newline all primary spacer'><a href='mailto:" + oEmail.emailAddress + "'>" + oEmail.emailAddress + "</a></div>";
					}

					else {
						sEmailList += "<div class='newline all secondary'><a href='mailto:" + oEmail.emailAddress + "'>" + oEmail.emailAddress + "</a></div>";
					}
				}

				if (sEmailList.length > 0) {
					$("#email-container").append(sEmailList);
				}
				else {
					$("#email-container").append("");
				}
			}
		}
	},

	status : {
		view : {
			fnFillStatusFlags : function (oBusiness) {
				if (qat.util.page.fnIsSDNFlagged(oBusiness.sdnstatus)) {
					var sSDNStatus = $.qat.enums.internationalizeByLabel("com.prosperitasglobal.sendsolv.sdn.model.SDNStatusEnum",oBusiness.sdnstatus);
					$("#status-risk").removeClass("hide").find('span:eq(1)').text(sSDNStatus);
				}

				else {
					$("#status-risk").addClass("hide");
				}

				if (oBusiness.risk.riskLevel === "MEDIUM") {
					$('#medium-risk').removeClass('hide');
					$('#high-risk').addClass('hide');
				}

				else if (oBusiness.risk.riskLevel === "HIGH") {
					$('#high-risk').removeClass('hide');
					$('#medium-risk').addClass('hide');
				}

				else {
					$('#high-risk').addClass('hide');
					$('#medium-risk').addClass('hide');
				}

				if (oBusiness.hasOwnProperty("pepStatus") && oBusiness.pepStatus === "YES") {
					$('#status-pep').removeClass('hide');
				}

				else {
					$('#status-pep').addClass('hide');
				}
			},

			fnFillBoxStatusFlags : function (oBusiness) {

				sSdnIcons = "&nbsp&nbsp";

				if (qat.util.page.fnIsSDNFlagged(oBusiness.sdnstatus)) {
					sSdnIcons += '<a class="security high" id="status-risk"><span class="icon-security icon-shield84"></span></a>';
				}

				if (oBusiness.risk.riskLevel === "MEDIUM") {
					sSdnIcons += '<a class="security medium" id="medium-risk" href="javascript:;"><span class="icon-security icon-flag"></span></a>';
				}

				else if (oBusiness.risk.riskLevel === "HIGH") {
					sSdnIcons += '<a class="security high" id="high-risk" href="javascript:;"><span class="icon-security icon-flag"></span></a>';
				}

				if (oBusiness.pepStatus === "YES") {
					sSdnIcons += '<a class="security high" id="status-pep" href="javascript:;"><span class="icon-security icon-torso"></span></a>';
				}

				return sSdnIcons;
			}
		}
	},

	reports : {
		sar : {
			fnInit : function($container) {
				$container.find(".reports-SARSummaryReport").prop('href', qat.settings.sendsolvReportServerUrl + "/Pages/ReportViewer.aspx?%2fqatReports%2fSARSummaryReport&rs:Command=Render");
				$container.find(".reports-SARSummaryByEmployeeReport").prop('href', qat.settings.sendsolvReportServerUrl + "/Pages/ReportViewer.aspx?%2fqatReports%2fSARSummaryByEmployeeReport&rs:Command=Render");
				$container.find(".reports-SARSummaryByOrganizationReport").prop('href', qat.settings.sendsolvReportServerUrl + "/Pages/ReportViewer.aspx?%2fqatReports%2fSARSummaryByOrganizationReport&rs:Command=Render");
				$container.find(".reports-SARGeneratedAgainstOrganizationsReport").prop('href', qat.settings.sendsolvReportServerUrl + "/Pages/ReportViewer.aspx?%2fqatReports%2fSARGeneratedAgainstOrganizationsReport&rs:Command=Render");
				$container.find(".reports-SARGeneratedAgainstIndividuals").prop('href', qat.settings.sendsolvReportServerUrl + "/Pages/ReportViewer.aspx?%2fqatReports%2fSARGeneratedAgainstIndividuals&rs:Command=Render");
			}
		},

		operation : {
			fnInit : function($container) {
				$container.find(".reports-OperationalOrganizationReport").prop('href', qat.settings.sendsolvReportServerUrl + "/Pages/ReportViewer.aspx?%2fqatReports%2fOperationalOrganizationReport&rs:Command=Render");
				$container.find(".reports-OperationalMemberReport").prop('href', qat.settings.sendsolvReportServerUrl + "/Pages/ReportViewer.aspx?%2fqatReports%2fOperationalMemberReport&rs:Command=Render");
				$container.find(".reports-OperationalRevenueReport").prop('href', qat.settings.sendsolvReportServerUrl + "/Pages/ReportViewer.aspx?%2fqatReports%2fOperationalRevenueReport&rs:Command=Render");
			}
		}
	}
};



$.fn.fnLoadDropDownListBusinessPlan = function(aList) {

	if (aList) {
		var $this = this;
		var sOptions = "";
		$.each(aList, function(key, value) {
			if (!$.qat.isNullOrUndefined(value)) {
				if (value.length > 0){
					oBusdinessPlanId = value.split('|');
					$('#busdinessPlanId').val(oBusdinessPlanId[0]);
					sOptions = sOptions + "<option value='" + key + "'>" + oBusdinessPlanId[1] + "</option>";
				}
			}
		});

		$this.append(sOptions);
	}
};

/**
* Common dropdown function
* @param aList - key/value array
*/
$.fn.fnLoadDropDownList = function(aList) {
		if (aList) {
			var $this = this;
			$this.find("option").remove();
			var sOptions = "<option></option>";
			for(var i = 0; i < aList.length; i++ ) {
				sOptions = sOptions + "<option value='" + aList[i].key + "'>" + aList[i].value + "</option>";
			}

			$this.append(sOptions);
		}

		return $(this);
};

/**
* Common dropdown function with Internationalization
* @param aList - key/value array
*/
$.fn.fnLoadDropDownListWithInternationalization = function(aList) {
		if (aList) {
			var $this = this;
			$this.find("option").remove();
			var sOptions = "<option></option>";
			for(var i = 0; i < aList.length; i++ ) {
				sOptions = sOptions + "<option value='" + aList[i].key + "'>" +  $.qat.locale.get(aList[i].value) + "</option>";
			}

			$this.append(sOptions);
		}

		return $(this);
};