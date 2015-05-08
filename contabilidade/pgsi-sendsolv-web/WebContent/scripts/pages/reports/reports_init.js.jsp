<%@ taglib prefix='sec'
	uri='http://www.springframework.org/security/tags'%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt'%>

<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN', 'ROLE_CSR')">
<script type="text/javascript">

$(document).ready(function()
{

	$("#cancel, #submit-button").button();

	$("form#adrm-report input.date").datepicker();

	$("form#adrm-report input.date").mask("99/99/9999");
	$("#number-of-days, #number-of-transactions, #number-of-recipients, #number-of-members").inputmask("decimal");
	$("#transfer-value").inputmask();

	pgsi.util.page.form.fnInitForm($("form#adrm-report"));

	$("form#adrm-report").on( "submit", function( event ) {

		event.preventDefault();

		// validate form
		var validator = $(this).validate();

		if (!validator.form()) {
			return false;
		}

		var sQueryString = pgsi.util.page.fnCheckXSS($(this).serialize()) ? null : $(this).serialize();

		// Retrieve and parse form queryString
		if (!$.pgsi.isNullOrUndefined(sQueryString)) {
			var aQueryString = sQueryString.split('&');
			var oQuery = new Object();
			var sParam;
			var sParamVal;
			var aSplit;

			for (var i=0; i < aQueryString.length; i++) {
			   aSplit = aQueryString[i].split("=");
			   sParam = aSplit[0];
			   sParamVal = aSplit[1];
			   if (aSplit[1].length > 0) {
			   		oQuery[sParam] = sParamVal;
			   }
			}

			if (oQuery.hasOwnProperty("transferValue")) {
				oQuery.transferValue = oQuery.transferValue.replace(/USD/g,'').replace('+','').replace(/%2C/g,'');
			}

			sQueryString = pgsi.settings.sendsolvReportServerUrl;

			var bMatch = false;
			aQueryString =  []
			// Change report request depending on the set of parameters
			if (oQuery.hasOwnProperty("numberOfRecipients") && oQuery.hasOwnProperty("numberOfDays")) {
			    aQueryString.push(sQueryString + "/Pages/ReportViewer.aspx?%2fPGSiReports%2fAnomalyDetectionReportA&rs:Command=Render&fromDate=" + oQuery.fromDate + "&toDate=" + oQuery.toDate + "&numberOfRecipients=" + oQuery.numberOfRecipients + "&numberofDays=" + oQuery.numberOfDays);
			    bMatch = true;
			}

			if (oQuery.hasOwnProperty("numberOfDays") && oQuery.hasOwnProperty("numberOfMembers")) {
				aQueryString.push(sQueryString + "/Pages/ReportViewer.aspx?%2fPGSiReports%2fAnomalyDetectionReportB&rs:Command=Render&fromDate=" + oQuery.fromDate + "&toDate=" + oQuery.toDate + "&numberOfDays=" + oQuery.numberOfDays + "&numberOfMembers=" + oQuery.numberOfMembers);
			   bMatch = true;
			}

			if (oQuery.hasOwnProperty("numberOfDays") && oQuery.hasOwnProperty("numberOfTransaction")) {
			    aQueryString.push(sQueryString +"/Pages/ReportViewer.aspx?%2fPGSiReports%2fAnomalyDetectionReportC&rs:Command=Render&fromDate=" + oQuery.fromDate + "&toDate=" + oQuery.toDate + "&numberOfDays=" + oQuery.numberOfDays + "&numberOfTransaction=" + oQuery.numberOfTransaction);
			    bMatch = true;
			}

			if (oQuery.hasOwnProperty("transferValue")) {
			    aQueryString.push(sQueryString + "/Pages/ReportViewer.aspx?%2fPGSiReports%2fAnomalyDetectionReportD&rs:Command=Render&fromDate=" + oQuery.fromDate + "&toDate=" + oQuery.toDate + "&transferValue=" + oQuery.transferValue);
			    bMatch = true;
			}

			if (!bMatch) {
				pgsi.pages.sendsolv.fnDialogMessageError("",{}, {
				  "messageList": [
				    {
				      "text": $.pgsi.locale.get('pages.ardm.label.errorMessage'),
				      "messageInfo": {
				        "severity": "Error"
				      }
				    }
				  ],
				  "operationSuccess": false,
				  "resultsSetInfo": null,
				  "messageInfoList": [
				    {
				      "severity": "Error"
				    }
				  ]
				},null, "", true);
			}

			if (bMatch) {
				for (var i=0; i < aQueryString.length; i++) {
					window.open(aQueryString[i], "window" + i);
				}
			}

		}
	});

	$.pgsi.progressBar.stopGlobal();
});

</script>
</sec:authorize>