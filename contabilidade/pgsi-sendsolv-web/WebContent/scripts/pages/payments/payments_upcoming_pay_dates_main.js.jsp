<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>

<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN')">

<script type="text/javascript">
/**
 * @namespace pgsi.pages.payments_upcoming
 * @fileoverview The main namespace for the Location List Page.
 */

pgsi.pages.payments_upcoming = {

	/**
	* Set of functions used by the datatables plugin to customize field values
	*/

	// Returns link for edit view
	fnCreateLocationNameLink : function (val, type, full)
	{

		if (type !== "display")
		{
			return val;
		}
			return '<a title="View/Edit ' + full.name + '" href="#/location/view?tab=info&locationId=' + full.id + '" class="edit_link">' + full.name + '</a><br> <a class="edit_link" title="View"' + full.parentOrganizationName + ' href="#/organization/view?organizationId=' + full.parentOrganizationId + '">' + full.parentOrganizationName + ' </a>';
	},

	fnNextPPD : function (val, type, full)
	{
		var iLinePPD = 0,
			 sLinePD = "";
		if (type !== "display")
		{
			return val;
		}

		for(var i=0;i<full.frequencyBasedEventList.length;i++){
			if(full.frequencyBasedEventList[i].type == "PAY_PREPARATION"){
				if (!$.pgsi.isNullOrUndefined(full.frequencyBasedEventList[i].frequencyBasedEventCalendarList[0])){
					if ((pgsi.pages.payments_upcoming.fnGetNextDate(full.frequencyBasedEventList[i].frequencyBasedEventCalendarList).nextDateDay > 0 )&&(pgsi.pages.payments_upcoming.fnGetNextDate(full.frequencyBasedEventList[i].frequencyBasedEventCalendarList).nextDateDay < 1)){
						iLinePPD = 1
					}

					else {
						iLinePPD = parseInt(pgsi.pages.payments_upcoming.fnGetNextDate(full.frequencyBasedEventList[i].frequencyBasedEventCalendarList).nextDateDay,10)
					}
				}
			}
		}

		if (iLinePPD > 0) {
			if(iLinePPD === 1){
				return iLinePPD  +" Day"
			}else{
				return iLinePPD  +" Days"
			}
		} else{
			return "";
		}
	},

	// Returns city name
	fnPayCycle : function (val, type, full)
	{
		var sLinePPD = "",
			 sLinePD = "",
			 slineFrequency = "";


		if (type !== "display")
		{
			return val;
		}
		var weekly=new Array(6);
			weekly[1]='Sunday';
			weekly[2]='Monday';
			weekly[3]='Tuesday';
			weekly[4]='Wednesday';
			weekly[5]='Thursday';
			weekly[6]='Friday';
			weekly[7]='Saturday';


		if(full.frequencyBasedEventList.length > 0){

			for(var i=0;i<full.frequencyBasedEventList.length;i++){
				slineFrequency = full.frequencyBasedEventList[i].frequencyCode;
				switch (full.frequencyBasedEventList[i].frequencyCode) {
					case "WEEKLY":
						pgsi.pages.payments_upcoming.fnGetNextDate(full.frequencyBasedEventList[i].frequencyBasedEventCalendarList)
						if(full.frequencyBasedEventList[i].type == "PAY_DAY"){
							sLinePD = "<span class='lbl'>PD  </span>"+weekly[full.frequencyBasedEventList[i].dayOfWeek]+"<br>";
						}
						if(full.frequencyBasedEventList[i].type == "PAY_PREPARATION"){
							sLinePPD = "<span class='lbl'>PPD </span>"+weekly[full.frequencyBasedEventList[i].dayOfWeek]+"<br>";
						}
					break;
					case "BI_WEEKLY":
						pgsi.pages.payments_upcoming.fnGetNextDate(full.frequencyBasedEventList[i].frequencyBasedEventCalendarList)
						if(full.frequencyBasedEventList[i].type == "PAY_DAY"){
							sLinePD = "<span class='lbl'>PD  </span>"+weekly[full.frequencyBasedEventList[i].dayOfWeek]+"<br>";
						}
						if(full.frequencyBasedEventList[i].type == "PAY_PREPARATION"){
							sLinePPD = "<span class='lbl'>PPD </span>"+weekly[full.frequencyBasedEventList[i].dayOfWeek]+"<br>";
						}
					break;


					case "SEMI_MONTHLY" :

						if(full.frequencyBasedEventList[i].type == "PAY_DAY"){
							sLinePD = "<span class='lbl'>PD  </span>"+full.frequencyBasedEventList[i].firstDayOfMonth+""+pgsi.pages.payments_upcoming.fnCalenderDay(full.frequencyBasedEventList[i].firstDayOfMonth)+"/"+full.frequencyBasedEventList[i].secondDayOfMonth+""+pgsi.pages.payments_upcoming.fnCalenderDay(full.frequencyBasedEventList[i].secondDayOfMonth)+"<br>";
						}
						if(full.frequencyBasedEventList[i].type == "PAY_PREPARATION"){
							sLinePPD = "<span class='lbl'>PD  </span>"+full.frequencyBasedEventList[i].firstDayOfMonth+""+pgsi.pages.payments_upcoming.fnCalenderDay(full.frequencyBasedEventList[i].firstDayOfMonth)+"/"+full.frequencyBasedEventList[i].secondDayOfMonth+""+pgsi.pages.payments_upcoming.fnCalenderDay(full.frequencyBasedEventList[i].secondDayOfMonth)+"<br>";
						}
					break;
					case "MONTHLY" :

						if(full.frequencyBasedEventList[i].type == "PAY_DAY"){
							sLinePD = "<span class='lbl'>PD  </span>"+full.frequencyBasedEventList[i].dayOfMonth+""+pgsi.pages.payments_upcoming.fnCalenderDay(full.frequencyBasedEventList[i].dayOfMonth)+"<br>";
						}
						if(full.frequencyBasedEventList[i].type == "PAY_PREPARATION"){
							sLinePPD = "<span class='lbl'>PPD  </span>"+full.frequencyBasedEventList[i].dayOfMonth+""+pgsi.pages.payments_upcoming.fnCalenderDay(full.frequencyBasedEventList[i].dayOfMonth)+"<br>";
						}
					break;

					case "ONE_DAY" :
						if(full.frequencyBasedEventList[i].type == "PAY_DAY"){
							sLinePD = "<span class='lbl'>PD  </span>"+weekly[full.frequencyBasedEventList[i].dayOfWeek]+"<br>";
						}
						if(full.frequencyBasedEventList[i].type == "PAY_PREPARATION"){
							sLinePPD = "<span class='lbl'>PPD </span>"+full.frequencyBasedEventList[i].frequencyCode+"<br>";
						}
					break;
				}
			}


		}
		if (($.pgsi.isNullOrUndefined(slineFrequency))&&($.pgsi.isNullOrUndefined(sLinePPD))&&($.pgsi.isNullOrUndefined(sLinePD))){
			return ""
		}else{
			return $.pgsi.enums.internationalizeByLabel("com.prosperitasglobal.sendsolv.model.FrequencyCodeEnum",slineFrequency) +"<br>"+ sLinePPD + sLinePD;
		}
	},

	// Returns state name
	fnLastPayroll : function (val, type, full)
	{
		var sLinePPD = "",
			 sLinePD = "";
		if (type !== "display")
		{
			return val;
		}
		for(var i=0;i<full.frequencyBasedEventList.length;i++){
			if(full.frequencyBasedEventList[i].type == "PAY_PREPARATION"){
				if (!$.pgsi.isNullOrUndefined(full.frequencyBasedEventList[i].frequencyBasedEventCalendarList[0])){
					sLinePPD = "<span class='lbl'>PPD </span>"+pgsi.pages.payments_upcoming.fnGetNextDate(full.frequencyBasedEventList[i].frequencyBasedEventCalendarList).nextDate + "<br>"
				}
			}
			if(full.frequencyBasedEventList[i].type == "PAY_DAY"){
				if (!$.pgsi.isNullOrUndefined(full.frequencyBasedEventList[i].frequencyBasedEventCalendarList[0])){
					sLinePD = "<span class='lbl'>PD  </span>"+pgsi.pages.payments_upcoming.fnGetNextDate(full.frequencyBasedEventList[i].frequencyBasedEventCalendarList).nextDate
				}
			}
		}
		if (($.pgsi.isNullOrUndefined(sLinePPD))&&($.pgsi.isNullOrUndefined(sLinePD))){
			return '';
		}else{
			return '<br>'+sLinePPD + sLinePD;
		}

	},

	// Returns phone number
	fnUpcomingPayrolls : function (val, type, full)
	{
		var sLinePPD = "",
			 sLinePD = "";
		if (type !== "display")
		{
			return val;
		}
		for(var i=0;i<full.frequencyBasedEventList.length;i++){
			if(full.frequencyBasedEventList[i].type == "PAY_PREPARATION"){
				if (!$.pgsi.isNullOrUndefined(full.frequencyBasedEventList[i].frequencyBasedEventCalendarList[0])){
					sLinePPD = "<span class='lbl'>PPD </span>"+pgsi.pages.payments_upcoming.fnRemoveStaff(pgsi.pages.payments_upcoming.fnGetNextDate(full.frequencyBasedEventList[i].frequencyBasedEventCalendarList).dates) + "<br>"
				}
			}
			if(full.frequencyBasedEventList[i].type == "PAY_DAY"){
				if (!$.pgsi.isNullOrUndefined(full.frequencyBasedEventList[i].frequencyBasedEventCalendarList[0])){
					sLinePD = "<span class='lbl'>PD  </span>"+pgsi.pages.payments_upcoming.fnRemoveStaff(pgsi.pages.payments_upcoming.fnGetNextDate(full.frequencyBasedEventList[i].frequencyBasedEventCalendarList).dates);
				}
			}
		}

		return '<br>'+sLinePPD + sLinePD;
	},

	// Returns phone number
	fnLocationStatus : function (val, type, full)
	{
		if (type !== "display")
		{
			return val;
		}
			return $.pgsi.enums.internationalizeByLabel("com.prosperitasglobal.sendsolv.model.StatusEnum",full.status);
	},
	fnLocationSDNStatus : function (val, type, full)
	{
		if (type !== "display")
		{
			return val;
		}
			return $.pgsi.enums.internationalizeByLabel("com.prosperitasglobal.sendsolv.sdn.model.SDNStatusEnum",full.sdnstatus);
	},
	fnLocationRiskLevel : function (val, type, full)
	{
		if (type !== "display")
		{
			return val;
		}
			return $.pgsi.enums.internationalizeByLabel("com.prosperitasglobal.sendsolv.model.RiskLevelEnum",full.risk.riskLevel);
	},


	// Returns aData filled with address, city, country and phone info
	fnReturnElement : function (listContact){

		var aData={address:"",city:"",state:"",phone:""};
		$("#action-dialog").append('<input class="numeric hide" type="text" value=""/>');
		for(var i=0;i < listContact.length;i++)
		{
			if(listContact[i].type == "address")
			{
				if(listContact[i].contactType == 'POSTAL_WORK')
				{
					aData.address = listContact[i].addressLine1;
					aData.city = listContact[i].cityName;
					aData.state = listContact[i].stateProvinceRegion.description;
				}
			}
			else if(listContact[i].type == "phone")
			{
				if(listContact[i].priority == 'PRIMARY')
				{
					$(".numeric").val(listContact[i].number);

					aData.phone = "+"+listContact[i].country.phoneCode+" "+ $(".numeric").val();
				}
			}

		}
		$("#action-dialog").empty();
		return aData;

	},

	fnGetNextDate  : function (listDates){

		var oReturn   = [];
		var dDateNow  = new Date();
		var iNextDate = dDateNow.getTime();
		var iAux 	  = null;
		var aDates    = new Array();
		var oDate;

		if (listDates.length > 0) {

			for (var i = 0; i < listDates.length; i++) {

				if (i === 0) {
					iAux = listDates[i].eventDate;
				}

				if (dDateNow.getTime() < listDates[i].eventDate) {
					oDate = $.pgsi.date.getDateObj(listDates[i].eventDate, "UTC");
					aDates.push('<span class="dt">'+$.pgsi.date.format(oDate, "mm/dd/yy") + '</span>');
				}

				if(iAux > listDates[i].eventDate ) {
					if(iNextDate < listDates[i].eventDate) {
						iAux = listDates[i].eventDate;
					}
				}
			}

			oReturn.nextDateDay = (iAux -dDateNow.getTime()) /1000/60/60/24;
			oDate = $.pgsi.date.getDateObj(iAux, "UTC");
			oReturn.nextDate 	= $.pgsi.date.format(oDate, "mm/dd/yy");
			oReturn.dates 		= aDates;
		}

		return oReturn;
	},

	fnRemoveStaff  : function (listDates){
		var sdates = "";
		if (!$.pgsi.isNullOrUndefined(listDates)){
			if(listDates.length > 0){
				for(var y=0;y< listDates.length;y++ ){
					sdates = sdates + " " +listDates[y]
				}
			}
		}
		return sdates
	},

	fnCalenderDay : function (iOption){

		if((iOption == 1)||(iOption == 21)||(iOption == 31)){
			return "st"
		}else if((iOption == 2)||(iOption == 22)){
			return "nd"
		}else if((iOption == 3)||(iOption == 23)){
			return "rd"
		}else if(((iOption > 4 )&&(iOption < 20))||((iOption > 24 )&&(iOption < 30))){
			return "th"
		}
	},
	locationTable: {

	}
}
</script>

</sec:authorize>