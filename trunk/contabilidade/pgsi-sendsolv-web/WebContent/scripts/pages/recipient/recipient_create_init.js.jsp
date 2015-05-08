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
$(document).ready(function()
{
	var sRecipientId = $.address.parameter("recipientId");

	if ($.pgsi.isNullOrUndefined(sRecipientId)) {
		$.pgsi.pageLoader.title($.pgsi.locale.get("commons.pages.recipientaddnew"), true);
	}

	// Fill the dropdownlists
	$("#suffix").fnLoadDropDownList(oSuffix);
	$("#prefix").fnLoadDropDownList(oPrefix);


	 $("#dob").datepicker({
			maxDate: "+0D",
			onClose : function(dateText, object) {
				if (isNaN((new Date(dateText)).getTime()) || (new Date(dateText) > new Date)) { $(this).val(""); }
			}
		});


		$("#next-button").click(function(e) {
			e.preventDefault();
				var fnCallbackSave = function(oResponse) {

					if (oResponse.operationSuccess == true) {
						$.pgsi.pageLoader.load({
							url : "member/addTransfer?member="+$.address.parameter('member')+"&location="+$.address.parameter('location')+"&recipient="+oResponse.recipientList[0].id+"|"+oResponse.recipientList[0].firstName +"|"+oResponse.recipientList[0].lastName+"&employmentInfoId="+$.address.parameter('employmentInfoId'),
							$content : $("#load"),
							bStartProgressBar : false
						});
					}else{
						$.pgsi.progressBar.stopGlobal();
						pgsi.pages.sendsolv.fnDialogMessageError("",{},oResponse,null,$.pgsi.locale.get("commons.dialog.error.title"),true);
					}
				}
				$.pgsi.progressBar.startGlobal();
				pgsi.pages.recipient.create.form.fnAjaxCallInsertUpdateRecipient($('#recipient-id').val(),fnCallbackSave);
		});
		$("#save-button").click(function(e) {
			e.preventDefault();
			var fnCallbackSave = function(oResponse) {
				if (oResponse.operationSuccess == true) {
					if (!$.pgsi
						.isNullOrUndefined(oResponse.recipientList[0])
								&& !$.pgsi
										.isNullOrUndefined(oResponse.recipientList[0].id)) {
							var iRecipientId = oResponse.recipientList[0].id;
							$.pgsi.pageLoader.load({
								url : "recipient/view?recipientId="
										+ iRecipientId,
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
			pgsi.pages.recipient.create.form.fnAjaxCallInsertUpdateRecipient($('#recipient-id').val(),fnCallbackSave);
		});

	$("#toggle-id").click(function(e) {
		e.preventDefault();
		$("#id-section").toggle();
		$("#toggle-id").hide();
	});

	$(".remembered-location").find('.icon-remove').click(function(e) {
		$(".remembered-location").hide();

	});

	pgsi.pages.recipient.create.form.fnInitForm();
	// member name
	if (!$.pgsi.isNullOrUndefined( $.address.parameter('member'))){
		var oMember = $.address.parameter('member').split('|');

		$('.nameLabel').text(' '+ oMember[1] +' '+oMember[2] +'');
		$('.memberName').attr({title:' '+ oMember[1] +' '+oMember[2] +''});
		$('.memberName').attr({href:' recipient/view?recipientId=' + oMember[0] + ' '});

	}

	if(oPreLoadResponse != null){
		pgsi.pages.recipient.create.form.fillObject(oPreLoadResponse.recipientList[0]);
	}
	$.pgsi.progressBar.stopGlobal();
});
</script>

</sec:authorize>