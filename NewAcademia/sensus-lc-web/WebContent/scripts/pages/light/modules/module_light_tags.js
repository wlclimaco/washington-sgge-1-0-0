sensus.commons.modules.content.lighttags = {

	addSmartpointToTag : function(aIdTags) {

		var aIdLight = [parseInt($.address.parameter("id"))];

		oRequest = new tagRequest(null, null, aIdTags, false, aIdLight, sensus.util.page.getSearchParameters());
		oRequest.lightRequest = new lightTableRequest(sensus.util.page.getSearchCriteria());
		oRequest.lightRequest = sensus.pages.smartpoint.setLightIdsOnLightCriteria(oRequest.lightRequest, aIdLight);

		$.sc.getJson("api/tag/insertlights", oRequest, false, null, null);

		$(".search-choice-close").off("click");

	},

	removeSmartpointToTag : function(aIdTags) {

		var aIdLight = [parseInt($.address.parameter("id"))];

		oRequest = new tagRequest(null, null, aIdTags, false, aIdLight, sensus.util.page.getSearchParameters());
		oRequest.lightRequest = new lightTableRequest(sensus.util.page.getSearchCriteria());
		oRequest.lightRequest = sensus.pages.smartpoint.setLightIdsOnLightCriteria(oRequest.lightRequest, aIdLight);

		$.sc.getJson("api/tag/deletelights", oRequest, false, null, null);

	},

	fnReloadTags: function(aData){

		var aTags = aData.tags;

		if(aTags)
		{
			for (t in aTags)
			{
				if (aTags.hasOwnProperty(t))
				{
					$('#select-tags option[value="'+aTags[t].id+'"]').prop('selected', true);
				}
			}

			$(".chzn-select").chosen({
				callBackData 	: null,
				fnCallBack		: apllyTagEvents
			});
		}

		if(aData.light.lifeCycleStateValue == 4)
		{
			$("#tags .blankslate").show();
			$("#tags ul").remove();
		}

	},

	fnFillTagList : function(data) {

		var aTags = data.tags;
		var sOptions = '';

		for (g in aTags)
		{
			if (aTags.hasOwnProperty(g))
			{
				sOptions += '<option value="'+aTags[g].id+'">'+aTags[g].name+'</option>';
			}
		}

		$('#select-tags').append(sOptions);

	},

	fnLoadTagList : function() {

		var obj = function() {
			return {
					url : "api/tag/fetch",
					data : new inquiryTagRequest()
				};
		};

		$.when(sensus.commons.modules.util.getData(obj)).done(function(oResponse) {
			sensus.commons.modules.content.lighttags.fnFillTagList(oResponse);
		});
	},

	init : function (){

		var oResponse = sensus.commons.modules.summaryData.lightInformation.fnGetResponse();
		sensus.commons.modules.content.lighttags.fnLoadTagList();
		sensus.commons.modules.content.lighttags.fnReloadTags(oResponse);

	}

};

function apllyTagEvents()
{
	if (sensus.settings.userContext.userRole != 'ROLE_Role.Admin' && sensus.settings.userContext.userRole != 'ROLE_Role.System Operator')
	{
		$('#select_tags_chzn .search-choice-close').css("display", "none");
	}

	$(".chzn-select").change(function(event, data) {

		if (data && data.selected)
		{
			var iTagId = parseInt(data.selected.split("-")[0]);
			var aIdTags = [{ id : iTagId, lights: [{id: parseInt($.address.parameter("id")),  }], name : $("#select-tags option[value='"+ iTagId +"']").html() }];

			sensus.commons.modules.content.lighttags.addSmartpointToTag(aIdTags);
		}
	});

	$(".search-choice-close").off("click");

	$("#tags").on("click", ".search-choice-close" , function() {

		$('.chzn-results', '#tags').find('li:contains("' + $(this).attr('title') + '")').removeClass('result-selected').addClass('active-result');
		$(this).parent().remove();

		var iTagId = parseInt($(this).attr("id"));
		var sTagName = $(this).siblings(":first").text();
		var aIdTags = [{ id : iTagId, name : sTagName }];

		sensus.commons.modules.content.lighttags.removeSmartpointToTag(aIdTags);

	});
}

sensus.commons.modules.content.lighttags.init();