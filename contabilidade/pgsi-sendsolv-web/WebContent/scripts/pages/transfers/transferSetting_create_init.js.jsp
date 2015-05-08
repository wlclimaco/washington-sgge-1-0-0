<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags'%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>

<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN', 'ROLE_CSR')">

<script type="text/javascript">

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
		<c:when test="${empty countryBAI}">
	    	var oState = null;
	    </c:when>
	    <c:otherwise>
	    	var oState = ${countryBAI};
	    </c:otherwise>
	</c:choose>

$(document).ready(function() {
		// Fill the dropdownlists

		$("#country").fnLoadDropDownList(oCountries);

		$("input.datepicker").datepicker({
			buttonImageOnly: true,
			required: true,
			message: "This is a required field",
			onClose: function() { $(this).valid(); }
		});

		$("#recipient-id").focusout(function(e){
			e.preventDefault();
			var oRecipientInfo = pgsi.pages.transfer.create.form.fnAjaxCallRecipientByID({stringId : $(this).val().replace('-','')},"api/recipient/fetch",3);
			if(!$.pgsi.isNullOrUndefined(oRecipientInfo)){
				if(oRecipientInfo.length > 0){
					$('.parent.spacer').text(oRecipientInfo[0]);
					if(oRecipientInfo[3] != 1){
						$('.spacer.active').text('No active recipient found.');
					}
					$('.search').prop('value',$(this).val());
					$('.search').prop('checked', true);
				}
			}
		});


		// member information name
		if (!$.pgsi.isNullOrUndefined($.address.parameter('member'))){
			var oMember = $.address.parameter('member').split('|');
			$('.memberName').text(' '+ oMember[1] +' '+oMember[2] +'');
			$(".memberTitle").attr({href:' member/view?memberId=' + oMember[0] + ' '}).attr({title:' '+ oMember[1] +' '+oMember[2] +''});
			$('#member-id').val(oMember[0]);
		}
		// recipient
		if (!$.pgsi.isNullOrUndefined($.address.parameter('recipient'))){
			var oRecipient = $.address.parameter('recipient').split('|');
			$(".recipientName").text(' '+ oRecipient[1] +' '+oRecipient[2] +'');
			$(".recipientTitle").attr({href:' recipient/view?recipientId=' + oRecipient[0] + ' '}).attr({title:' '+ oRecipient[1] +' '+oRecipient[2] +''});
			$('#recipient-id').val(oRecipient[0]);
			$('#recipientId').val(pgsi.pages.transfer.create.form.fnAjaxCallRecipientByID({id : oRecipient[0]},"api/recipient/fetch",3)[1]);
		}
		if (!$.pgsi.isNullOrUndefined($.address.parameter('location'))){
			var oLocationAddress = $.address.parameter('location').split('|');
			if(oLocation !== null){

				pgsi.pages.transfer.create.form.fnAjaxCallProductPlan(oLocationAddress[1]);

				var oLocation = pgsi.pages.transfer.create.form.fnAjaxCallLocationByID(oLocationAddress[1]);
				pgsi.pages.transfer.create.payPreparationDays = oLocation[4];
				$('.frequencyBasedEventCalendarList').empty();
				$('.frequencyBasedEventCalendarList').append(oLocation[2]);
				$('.contact-info2 input:eq(0)').prop('checked', true);

				var fnCallBack = function(oResponse) {
					if(oResponse.locationList.length > 0){
						$('.locationName').text(' '+oLocationAddress[0]+' ('+ oResponse.locationList[0].parentOrganizationName +') ');
						$('.locationTitle').attr({href:'#/location/view?tab=info&locationId=' + oLocationAddress[1]}).attr({title:' ' + oLocationAddress[0]+' ('+ oResponse.locationList[0].parentOrganizationName +') '});
					}else{
						$('.locationName').text(' '+oLocationAddress[0]);
						$('.locationTitle').attr({href:'#/location/view?tab=info&locationId=' + oLocationAddress[1]}).attr({title:' ' + oLocationAddress[0]});
					}
				};

				pgsi.pages.member.create.fnFetchLocationById(parseInt(oLocationAddress[1],10),fnCallBack);

				$('#location-id').val(oLocationAddress[1]);
				$('#location-name').val(oLocationAddress[0]);

				// employmentInfo
				$('#employmentInfo-id').val($.address.parameter('employmentInfoId'));
			}
		}

		if($("#methodTransfer").val() != 4){
			pgsi.pages.address.form.makeItOptional();
		}

		$("#methodTransfer").on("selectmenuchange", function(event, ui) {

			switch (ui.item.value) {
			case '2':

				$(".deposit").removeClass('hide');
				$("account-type").addClass('required').prop("placeholder","*");
				$("account").addClass('required').prop("placeholder","*");
				pgsi.pages.address.form.makeItOptional();


				$(".address").addClass('hide');
				break;
			case '4':
				$(".address").removeClass('hide');
				pgsi.pages.address.form.fnInitForm();
				pgsi.pages.transfer.create.fnFetchRecipientByAddress({ id : $('#recipientId').val().replace('-','') });
				$(".deposit").addClass('hide');
				$("account-type").removeClass('required').prop("placeholder","");
				$("account").removeClass('required').prop("placeholder","");

				break;
			default:
				$(".address").addClass('hide');
				$(".deposit").addClass('hide');
				$("account-type").removeClass('required').prop("placeholder","");
				$("account").removeClass('required').prop("placeholder","");
				pgsi.pages.address.form.makeItOptional();
				break;

			}
		});


		// Button Save TransferSetting
		$("#save-button").click(function(e) {
			e.preventDefault();
			var fnCallbackSave = function(oResponse) {
				if (oResponse.operationSuccess == true) {
					if($('#methodTransfer').val() == '4'){
						pgsi.pages.transfer.create.form.fillAddressRecipientRequestAndAjaxCall($('#recipientId').val());
					}
					if(!$.pgsi.isNullOrUndefined($.address.parameter('member'))){
						$.pgsi.pageLoader.load({
									url : "member/view?memberId="
											+ $.address.parameter('member').split('|')[0],
									$content : $("#load"),
									bStartProgressBar : false
								});
					}

				}else{
					$.pgsi.progressBar.stopGlobal();
				}
			}
			$.pgsi.progressBar.startGlobal();
			pgsi.pages.transfer.create.form.fnAjaxCallInsertUpdateMemberTransfer($('#transfer-id').val(),fnCallbackSave);

		});

		pgsi.pages.transfer.create.form.fnInitForm();

		$('#fee-exp').change(function () {
			if($(this).val() != ""){
				$('#fee').addClass('required').prop("placeholder","*");
			}else{
				$('#fee').removeClass('required').prop("placeholder","");
			}
		});
		$('#fee').change(function () {
			if($(this).val() != ""){
				$('#fee-exp').addClass('required').prop("placeholder","*");
			}else{
				$('#fee-exp').removeClass('required').prop("placeholder","");
			}
		})

		$("#schedule").on("selectmenuchange", function(event, ui) {

			if($(this).val() == "1"){
				$('#skips').addClass('required').removeClass('hide').prop("placeholder","*");
				$('.skips-label').removeClass('hide');
			}else{
				$('#skips').removeClass('required').addClass('hide').prop("placeholder","");
				$('.skips-label').addClass('hide');
			}
		});

		if(oPreLoadResponse != null){
			pgsi.pages.transfer.create.form.fillObject(oPreLoadResponse.memberList[0]);
		}else{
			pgsi.pages.transfer.create.form.fillObject(oPreLoadResponse);
		}

		$.pgsi.progressBar.stopGlobal();
});


</script>

</sec:authorize>