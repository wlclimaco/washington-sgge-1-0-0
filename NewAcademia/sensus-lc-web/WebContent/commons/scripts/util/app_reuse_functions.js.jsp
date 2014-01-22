<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<script type="text/javascript">
/**
 /**
 * @fileoverview common reuse functions for webdaptive, counties & procedures pages.
 * @author Rich Barndt
 */

function rest_post_call(_url, _oData, _successFunction, _errorFunction)
{
	$.ajax({
			type		: "POST",
			url			: WDHost + _url,
			username	: "superuser",
			password	: "koala",
			data		: $.toJSON(_oData),
			contentType : "application/json; charset=utf-8",
			timeout		: 5000,
			success		: _successFunction,
			error		: _errorFunction
 	});
};

//Common routine to process reponse & grid data
function reuse_fill_data(response,data2,gridProcess)
{
debugger;
	//Successful Return and got some type of object back
	if (response != null && response.operationSuccess == true)
	{
		//after error, data may become undefined
		if(typeof(data2)=="undefined")
		{
			//create a blank one
			data2 = new Array();
		}
		else
		{
			//it exists zero out the old one
			data2.length=0;
		}

		//call proc or county
		if (gridProcess === "county" )
		{
			data2 = county_fill_data(response,data2);
		}
		else if (gridProcess === "procedure" )
		{
			data2 = procedure_fill_data(response,data2);
		}
	}
	else
	{
		fill_data_error(response);
	}
	return data2;
};


//Fills the county array after county calls to the back-end
function county_fill_data(countyResponse,data2)
{
	//Make sure return is an array
	if ($.isArray(countyResponse.counties))
	{
		var tmpLength = countyResponse.counties.length;
		for (var i=0; i < tmpLength; i++)
		{
			data2[i] =
			{
				cellno:     i,
				csak: 		countyResponse.counties[i].id,
				cdesc:  	countyResponse.counties[i].description
			}
		}
	}
	return data2;
};

//Fills the procedure array after procedure calls to the back-end
function procedure_fill_data(procResponse,data2)
{
	data2[0] =
	{
		cellno: 0,
		action: "<span>Create>>></span>",
		psak: 0,
		pcode: null,
		pdesc: null,
		pprice: 0,
		pversion: 0
	};

	data2[1] =
	{
		cellno: 0,
		action: "",
		psak: 0,
		pcode: null,
		pdesc: null,
		pprice: 0,
		pversion: 0
	};

	//Fill paging data
	if (procResponse.resultsSetInfo != undefined)
	{
	 	pagingData.pageSize =  parseInt(procResponse.resultsSetInfo.pageSize);
	 	pagingData.startPage =  parseInt(procResponse.resultsSetInfo.startPage);
	 	pagingData.moreRowsAvailable =  procResponse.resultsSetInfo.moreRowsAvailable;
	 	pagingData.totalRowsAvailable =  parseInt(procResponse.resultsSetInfo.totalRowsAvailable);
	}

	//make sure return is an array
/* 	if ($.isArray(procResponse.academias))
	{
		var oi = 0;
		var tmpLength = procResponse.academias.length;
		<sec:authorize  access="hasAnyRole('ROLE_DOMAIN USERS', 'ROLE_DOMAIN ADMINS')">
		for (var i=1; i <= tmpLength; i++)
		</sec:authorize>
		<sec:authorize  access="hasRole('ROLE_GUEST')">
		for (var i=0; i < tmpLength; i++)
		</sec:authorize>
		{
			data2[i] =
			{
				cellno:     i,
				<sec:authorize  access="hasAnyRole('ROLE_DOMAIN USERS', 'ROLE_DOMAIN ADMINS')">
				action: 	"<a href='#' onclick='javascript:ploader.callDeleteWS(" + procResponse.academias[oi].id +");'>Delete</a>",
				</sec:authorize>
				<sec:authorize ifAllGranted="ROLE_GUEST">
				action: 'None',
				</sec:authorize>
				psak: 		procResponse.academias[oi].id,
				pcode:		procResponse.academias[oi].code,
				pdesc:  	procResponse.academias[oi].description,
				pprice: 	procResponse.academias[oi].price,
				pversion: 	procResponse.academias[oi].version
			}
			oi++;
		}
	} */
	return data2;
};

//error routine for all ajax calls to the back-end or MVC
function fill_data_error(response)
{
	var msgOut = "";
	if (response == null)
	{
		msgOut = wdbackend.error.msg;
	}
	else
	{
		//Make sure return is an array
		if ($.isArray(response.messageList))
		{
			var tmpLength = response.messageList.length;
			for (var q = 0; q < (tmpLength); q++)
			{
				msgOut = msgOut + "Severity:" + response.messageList[q].messageInfo.severity + ",Level:" + response.messageList[q].messageInfo.level +  ",Text:"  + response.messageList[q].text + ",traceInfo:"  +  response.messageList[q].messageInfo.traceInfo;
			}
		}
		msgOut = wdbackend.error.msg + msgOut;
	}
	wd.core.displayNotificationMessage('#StatusBar', msgOut, true, 'error', 5000);
};
</script>