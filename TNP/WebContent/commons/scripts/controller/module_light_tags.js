sensus.commons.modules.content.lighttags = {

	addSmartpointToTag : function(aIdTags) {

		var aIdLight = [parseInt($.address.parameter("id"))];
		// Send ajax action to Add Tag to Light
		oRequest = new tagRequest(null, null, aIdTags, false, aIdLight, sensus.util.page.getSearchParameters());

		$.sc.getJson("api/tag/insertlights", oRequest, false, null, null);

		$(".search-choice-close").off("click");

	},

	fnReloadTags: function(aData){

		var aTags = aData.tags;

		if(aTags){

			for (t in aTags) {

				if (aTags.hasOwnProperty(t)) {

					$('#select-tags option[value="'+aTags[t].id+'"]').prop('selected', true);

				}

			}

			$(".chzn-select").chosen();

		}

		if(aData.currentStatusMessage.lightStatusEnumValue == 4){

			$("#tags .blankslate").show();
			$("#tags ul").remove();

		}

	},

	fnFillTagList : function(data) {

		var aTags = data.tags;
		var sOptions = '';

		for (g in aTags) {

			if (aTags.hasOwnProperty(g)) {

				sOptions += '<option value="'+aTags[g].id+'">'+aTags[g].name+'</option>';

			}

		}

		$('#select-tags').append(sOptions);

	},

	fnLoadTagList : function() {

		var oRequest = new inquiryTagRequest();
		$.sc.getJson("api/tag/fetch", oRequest, false, sensus.commons.modules.content.lighttags.fnFillTagList, null);

	},

	init : function (){

		var oResponse = sensus.commons.modules.summaryData.lightInformation.fnGetResponse();
		var oFirstMeter = sensus.commons.modules.util.fnGetFirstDeviceResponse(oResponse);
		sensus.commons.modules.content.lighttags.fnLoadTagList();
		sensus.commons.modules.content.lighttags.fnReloadTags(oFirstMeter);

	}

};

sensus.commons.modules.content.lighttags.init();

$(".chzn-select").change(function(event, data) {

	if (data && data.selected) {

		// Submit insert smartpoint to tag
		var aIdLight = [parseInt($.address.parameter("id"))];
		var iTagId = parseInt(data.selected.split("-")[0]);

		var aIdTags = [{ id : iTagId, lights: [{id: parseInt($.address.parameter("id")),  }], name : $("#select-tags option[value='"+ iTagId +"']").html() }];


		// Send ajax action to Add Tag to Light
		var oRequest = new tagRequest(null, null, aIdTags, false, aIdLight, null) ;
		$.sc.getJson("api/tag/insertlights", oRequest, false, null, null);

	}

	sensus.commons.modules.content.lightGroups.fnReloadGroups();

	$(".search-choice-close").off("click");

});

if (sensus.settings.userContext.userRole != 'ROLE_Role.Admin' && sensus.settings.userContext.userRole != 'ROLE_Role.System Operator') {

	$('.search-choice-close').remove();

}

$(".search-choice-close").off("click");

$("#tags").on("click", ".search-choice-close" , function() {
console.log();
	$('.chzn-results', '#tags').find('li:contains("' + $(this).attr('title') + '")').removeClass('result-selected').addClass('active-result');
	$(this).parent().remove();

	// Remove smartpoint to tag
	var aIdTags = [{ id : parseInt($(this).attr("id")), name: $(this).siblings(":first").text() }];
	var aIdLight = [parseInt($.address.parameter("id"))];

	// Send ajax action to Add Tag to Light
	var oRequest = new tagRequest(null, null, aIdTags, false, aIdLight, sensus.util.page.getSearchParameters()) ;
	$.sc.getJson("api/tag/deletelights", oRequest, false, null, null);

});