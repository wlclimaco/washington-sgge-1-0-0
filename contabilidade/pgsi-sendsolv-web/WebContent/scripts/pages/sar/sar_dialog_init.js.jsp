<%@ taglib prefix='sec'
	uri='http://www.springframework.org/security/tags'%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt'%>

<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN', 'ROLE_CSR')">
<script type="text/javascript">
/**
 * @namespace pgsi.pages.member
 * @description The init namespace for the Member List Page.
 * @author Washington Costa
 */

 <c:choose>
	<c:when test="${empty response}">
 	var oResponse = null;
 </c:when>
 <c:otherwise>
 	var oResponse = ${response};
 </c:otherwise>
</c:choose>

$(document).ready(function()
{

	$("input#recipientParticipantId").inputmask();
	$("input#amount").inputmask();

	if(!$.pgsi.isNullOrUndefined(oResponse)){
	    var oMember = oResponse.memberList[0],
		    sCheckbox = "";
		for(var i = 0 ;i < oMember.employmentInfoList.length;i++){
			sCheckbox = sCheckbox + '<div class="employment"><input type="checkbox" value="'+oMember.employmentInfoList[i].locationId+'|'+oMember.employmentInfoList[i].organizationId+'" name="location"></div><label class="parent">'+oMember.employmentInfoList[i].locationName+' ('+oMember.employmentInfoList[i].organizationName+') </label><label class="first"> </label>'
		}
		$("#participant-id").val(oMember.id);
		$("#recipientParticipantId").val(pgsi.util.page.fnInsertMask("***-99999",oMember.participantId ) || oMember.id);
		$("#nameParticipant").text(' '+ oMember.firstName + ' ' + oMember.lastName +' ');
		$('#locationName').append(sCheckbox);
		$("#recipientParticipantId").prop('disabled', true);

	}
	$('#activityStartDateTimeUTC').datepicker(
		{
			onClose : function(dateText, object) {
				if (isNaN((new Date(dateText)).getTime()) || (new Date(dateText) > new Date)) { $(this).val(""); }
			}
		}
	);
	$('#activityStopDateTimeUTC').datepicker(
		{
			onClose : function(dateText, object) {
				if (isNaN((new Date(dateText)).getTime()) || (new Date(dateText) > new Date)) { $(this).val(""); }
			}
		}
	);
	$('#recipientParticipantId').focusout(function(e){

		e.preventDefault();

		var oObjectInfo = pgsi.pages.sar.common.ajaxCallSearch($(this).val().replace('-', ''));
		var sType = "",
		sCheckbox = "";

		if(!$.pgsi.isNullOrUndefined(oObjectInfo)){
			if(!$.pgsi.isNullOrUndefined(oObjectInfo.businessType)){
				sType =  oObjectInfo.businessType;
			}else{
				sType =  oObjectInfo.personType;
			}
			if((sType == "ORGANIZATION")||(sType == "LOCATION")){
				$('#nameParticipant').text(oObjectInfo.name + " (#"+(pgsi.util.page.fnInsertMask("***-99999",oObjectInfo.key ) || oObjectInfo.id) + ")");
				$('#sar-business-id').val(oObjectInfo.id)
			}else{
				$('#nameParticipant').text(oObjectInfo.firstName + ' ' + oObjectInfo.lastName + " (#"+(pgsi.util.page.fnInsertMask("***-99999",oObjectInfo.participantId ) || oObjectInfo.id) + ")");
				if(!$.pgsi.isNullOrUndefined(oObjectInfo.employmentInfoList)){
					if(oObjectInfo.employmentInfoList.length > 0){
						for(var i = 0 ;i < oObjectInfo.employmentInfoList.length;i++){
							sCheckbox = sCheckbox + '<div class="employment"><input type="checkbox" value="'+oObjectInfo.employmentInfoList[i].locationId+'|'+oObjectInfo.employmentInfoList[i].organizationId+'" name="location"></div><label class="parent">'+oObjectInfo.employmentInfoList[i].locationName+' ('+oObjectInfo.employmentInfoList[i].organizationName+') </label><label class="first"> </label>'
						}
						$('#locationName').append(sCheckbox);
						$('#reportTarget').removeClass('hide');
					}
				}
			}
			$("#participant-id").val(oObjectInfo.id);
			$('#sar-type').val(sType)
		}else{
			$('#locationName').text('');
			$('#reportTarget').addClass('hide');
		}

	});
});
</script>
</sec:authorize>