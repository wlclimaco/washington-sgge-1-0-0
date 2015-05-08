<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags'%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>

<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN', 'ROLE_CSR')">

<script type="text/javascript">
$(document).ready(function() {

	<c:choose>
		<c:when test="${empty response}">
			var oPreLoadResponse = null;
		</c:when>
		<c:otherwise>
			var oPreLoadResponse = ${response};
		</c:otherwise>
	</c:choose>

	<c:choose>
		<c:when test="${empty countries}">
	    	var oCountries = null;
	    </c:when>
	    <c:otherwise>
	    	var oCountries = ${countries};
	    </c:otherwise>
	</c:choose>

	<c:choose>
		<c:when test="${empty suffix}">
	    	var oSuffix = null;
	    </c:when>
	    <c:otherwise>
	    	var oSuffix = ${suffix};
	    </c:otherwise>
	</c:choose>

	<c:choose>
		<c:when test="${empty prefix}">
	    	var oPrefix = null;
	    </c:when>
	    <c:otherwise>
	    	var oPrefix = ${prefix};
	    </c:otherwise>
	</c:choose>

	<c:choose>
		<c:when test="${empty documentType}">
	    	var oDocumentType = null;
	    </c:when>
	    <c:otherwise>
	    	var oDocumentType = ${documentType};
	    </c:otherwise>
	</c:choose>

	<c:choose>
		<c:when test="${empty languageResponse}">
	    	var oLanguageResponse = null;
	    </c:when>
	    <c:otherwise>
	    	var oLanguageResponse = ${languageResponse};
	    </c:otherwise>
	</c:choose>

	<c:choose>
		<c:when test="${empty countryBAI}">
	    	var oState = null;
	    </c:when>
	    <c:otherwise>
	    	var oState = ${countryBAI};
	    </c:otherwise>
	</c:choose>

	<c:choose>
		<c:when test="${empty languageResponse}">
	    	var oLanguageResponse = null;
	    </c:when>
	    <c:otherwise>
	    	var oLanguageResponse = ${languageResponse};
	    </c:otherwise>
	</c:choose>

	// Set the page title
	var sMemberId = $.address.parameter("memberId");

	if ($.pgsi.isNullOrUndefined(sMemberId)) {
		$.pgsi.pageLoader.title($.pgsi.locale.get("commons.pages.memberaddnew"), true);
	}

	// Fill the dropdownlists
	$("#suffix").fnLoadDropDownList(oSuffix);
	$("#prefix").fnLoadDropDownList(oPrefix);
	$("#idtype").fnLoadDropDownList(oDocumentType);
	$("#preferredLanguage").fnLoadDropDownList(oLanguageResponse);
	$("#idstate").fnLoadDropDownList(oState);
	$("#idcountry").fnLoadDropDownList(oCountries);
	$("#birth").fnLoadDropDownList(oCountries);
	$("#citizenship").fnLoadDropDownList(oCountries);
	$("#residence").fnLoadDropDownList(oCountries);

	$("#dob.datepicker").datepicker({
		maxDate: "+0D",
		onClose : function(dateText, object) {
			if (isNaN((new Date(dateText)).getTime()) || (new Date(dateText) > new Date)) { $(this).val(""); }
		}
	});

	$("#hiredate.datepicker").datepicker({
		maxDate: "+0D",
		onClose : function(dateText, object) {
			if (isNaN((new Date(dateText)).getTime()) || (new Date(dateText) > new Date)) { $(this).val(""); }
		}
	});

	$("#idexpirationdate.datepicker").datepicker({
		buttonImageOnly: true,
		required: true,
		message: "This is a required field",
		onClose: function() {$(this).valid();}
	});

	$('#remember').attr("disabled", true);

	// Button Save Member
	$("#save-button").click(function(e) {
		e.preventDefault();
		var fnCallbackSave = function(oResponse) {
			if (oResponse.operationSuccess == true) {
				if (!$.pgsi.isNullOrUndefined(oResponse.memberList[0])&&
					!$.pgsi.isNullOrUndefined(oResponse.memberList[0].id)) {
					var iMemberId = oResponse.memberList[0].id;
					$.pgsi.pageLoader.load({
						url : "member/view?memberId="
								+ iMemberId,
						$content : $("#load"),
						bStartProgressBar : false
					});
				}
			} else {
				$.pgsi.progressBar.stopGlobal();
				pgsi.pages.sendsolv.fnDialogMessageError("",{},oResponse,null,$.pgsi.locale.get("commons.dialog.error.title"),true);
			}
		};
			$.pgsi.progressBar.startGlobal();
			pgsi.pages.member.create.form.fnAjaxCallInsertUpdateMember($('#member-id').val(),fnCallbackSave);
		});

		// Button Save Recipient
		$("#next-button").click(function(e) {
			e.preventDefault();

			var fnCallbackNext = function(oResponse) {

				if (oResponse.operationSuccess == true) {
					if (!$.pgsi
						.isNullOrUndefined(oResponse.memberList[0])
								&& !$.pgsi
										.isNullOrUndefined(oResponse.memberList[0].id)) {
							var iMemberId = oResponse.memberList[0].id;
							$("member-id").val(oResponse.memberList[0].id);
							pgsi.pages.recipient.sMemberName = oResponse.memberList[0].firstName;
							pgsi.pages.recipient.iMemberId   = oResponse.memberList[0].id;

							$.pgsi.pageLoader.load({
								url : "recipient/add?member="+oResponse.memberList[0].id+"|"+oResponse.memberList[0].firstName +"|"+oResponse.memberList[0].lastName+"&location="+oResponse.memberList[0].employmentInfoList[0].locationName+"|"+oResponse.memberList[0].employmentInfoList[0].locationId+'&employmentInfoId='+oResponse.memberList[0].employmentInfoList[0].id,
								$content : $("#load"),
								bStartProgressBar : false
							});

						}
				} else {
					$.pgsi.progressBar.stopGlobal();
					pgsi.pages.sendsolv.fnDialogMessageError("",{},oResponse,null,$.pgsi.locale.get("commons.dialog.error.title"),true);
				}
			};

			$.pgsi.progressBar.startGlobal();
			pgsi.pages.member.create.form.fnAjaxCallInsertUpdateMember($('#member-id').val(),fnCallbackNext);

		});

		$("#toggle-id").click(function(e) {
			e.preventDefault();
			$("#id-section").toggle();
			$("#toggle-id").hide();

			$("#id-section").find("span").first().focus();
		});
		$("#toggle-employment").click(function(e) {
			e.preventDefault();
			$("#employment-section").toggle();
			$("#toggle-employment").hide();

			$("#employment-section").find("input").first().focus();
		});

		$(".remembered-location").find('.remove').click(function(e) {

			$(".remembered-location").hide();
			pgsi.location = {locationId:null,locationName:null,organizationId:null};

			$('#remember').attr("disabled", true);
			$('#remember').prop("checked", false);
			sessionStorage.clear()
		});


		$("#remember").click(function(e) {

			if($(".remember #remember").is(':checked') == true){

				$(".remembered-location").show();

				var fnCallBack = function(oResponse) {

					if(oResponse.organizationList.length > 0){
						$('.view-organization').text(' '+ oResponse.organizationList[0].name +' ');
						$('.view-organization').attr("href", "organization/view?tab=info&organizationId="+oResponse.organizationList[0].id);
					}else{
						$('.view-organization').text('');
					}
				};
				sessionStorage.locationId   = parseInt($('#location-id').val(),10);
				sessionStorage.locationName = $('#location').val();

				$('.view-Location').text(' (' + $('#location').val() +' ) ');
				$('.view-Location').attr("href", 'location/view?locationId='+$('#location-id').val());

				if(($('#parentOrganizationId').val() != "") || ($('#parentOrganizationId').val() != null)){
					sessionStorage.organizationId = parseInt($('#parentOrganizationId').val(),10);
					pgsi.pages.member.create.fnFetchOrganizationById(parseInt($('#parentOrganizationId').val(),10),fnCallBack);
				}
			}else{
				$(".remembered-location").hide();
				sessionStorage.clear();
				$('#remember').attr("disabled", true);
				$('#remember').prop("checked", false);
			}
		});

		$(function() {
			function log( message ) {
				$( "<div>" ).text( message ).prependTo( "#log" );
				$( "#log" ).scrollTop( 0 );
		}
		$( "#location" ).autocomplete({
			source: function( request, response ) {
				$.pgsi.ajax.post({
						sUrl 		: "api/location/fetchall",
						bHideProgressBar : true,
						oRequest 	: {inquiryCriteria:{name:request.term},statusList : $.pgsi.enums.fetchLabelsByValues("com.prosperitasglobal.sendsolv.model.StatusEnum", ["1", "3"]),sortExpressions:[{field:"name",direction:"Descending"}],preQueryCount:true,startPage:0,pageSize:10000},
						fnCallback  : function(oResponse) {
							response( $.map( oResponse.locationList, function( item ) {

								var iLength = item.contactList.length,
								sAddress = item.contactList,
								sComplet = "";
								if(iLength > 0 ){
									for(i=0;i < iLength;i++){
										if(sAddress[i].type == "address") {
											sComplet = " ( " + sAddress[i].cityName + ", " + sAddress[i].stateProvinceRegion.description.trim() + ", " + sAddress[i].country.code + " ) ";
										}
									}
								}
								return {
									label 				   : item.name,
									desc  				   : sComplet,
									value 				   : item.name,
									parentOrganizationId   : item.parentOrganizationId,
									parentOrganizationName : item.parentOrganizationName,
									id    				   : item.id
								}
							}));
							$.pgsi.progressBar.stop();
						}
					});
			},
			minLength: 1,
			select: function( event, ui ) {

				$('#location-id').val(ui.item.id)
				sessionStorage.locationId = ui.item.id;
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

	pgsi.pages.member.create.form.fnInitForm();

	if((!$.pgsi.isNullOrUndefined(sessionStorage.locationId))&&(!$.pgsi.isNullOrUndefined(sessionStorage.locationName))) {

		var fnCallBack = function(oResponse) {

			if(oResponse.organizationList.length > 0){
				$('.view-organization').text(' '+ oResponse.organizationList[0].name +' ');
				$('.view-organization').attr("href", "organization/view?tab=info&organizationId="+oResponse.organizationList[0].id);
			}else{
				$('.view-organization').text('');
			}
		};

		$('.view-Location').text(' (' + sessionStorage.locationName +' ) ');
		$('.view-Location').attr("href", 'location/view?locationId='+sessionStorage.locationId);

		if((sessionStorage.organizationId != "") || (sessionStorage.organizationId != null)){
			pgsi.pages.member.create.fnFetchOrganizationById(parseInt(sessionStorage.organizationId,10),fnCallBack);
		}
		$(".remembered-location").show();
		$('#location-id').val(sessionStorage.locationId);
		$('#location').val(sessionStorage.locationName);
		$('#organizationId').val(sessionStorage.organizationId);
		$('#remember').attr("disabled", false);
		$('#remember').prop("checked", true);
	}else{
		$('#remember').attr("disabled", true);
		$('#remember').prop("checked", false);
		sessionStorage.clear();
	}

	if(oPreLoadResponse != null){
		pgsi.pages.member.create.form.fillObject(oPreLoadResponse.memberList[0]);
	}

	$('#id-section .document').change(function () {

		pgsi.pages.member.create.fnValidDocument();

	});

	$("#id-section .document").on("selectmenuchange", function( event, ui ) {

		pgsi.pages.member.create.fnValidDocument();

	});

	$.pgsi.progressBar.stopGlobal();

});

</script>

</sec:authorize>