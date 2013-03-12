<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<sec:authorize access="hasAnyRole('ROLE_Role.Admin', 'ROLE_Role.System Operator', 'ROLE_Role.Customer Support', 'ROLE_Role.Billing Manager')">
<script type="text/javascript">

sensus.pages.ecomode = {

	tags : {

		fnReloadTags: function(aData){

			var aTags = aData.tags;

			if(aTags){

				for (i in aTags) {

					if (aTags.hasOwnProperty(i)) {

						$('#select_tags').append('<option value=' + aTags[i].id + '>' + aTags[i].name + '</option>');

					}

				}

				$(".chzn-select").chosen();

			}

		},

		fnLoadTagList : function() {

			var oRequest  = new inquiryTagRequest();
			var oResponse = $.sc.getJson("api/tag/fetch", oRequest, false, null, null);
			sensus.pages.ecomode.tags.fnReloadTags(oResponse);

		},

		init : function (){

			sensus.pages.ecomode.tags.fnLoadTagList();

		},

		fnLoadTagChosen : function () {

			$(".chzn-choices li.search-choice, .search-field input").each(function() {

				var sLiText = $(this).text() || $(this).val();
				var oOption = null;

				$('select#select_tags option').each(function() {

					if($(this).text().trim() === sLiText) {

						oOption = $(this);
						return;

					}

				});

				if (!oOption && sLiText
						 && sLiText != 'Create or select multiple Tags'
						 && sLiText.replace(/\s/g, '').length
						 && sensus.settings.userContext.userRole == 'ROLE_Role.Admin') {

					sensus.util.ajaxaction.actionUrlAdress = "tag/addTag.action";

					sensus.util.ajaxaction.data = {
							'tagRequest': new tagRequest(null, sLiText, null, null, null, null)
					};

					sensus.util.ajaxaction.sendActionDefault(null, [sLiText, sensus.settings.smartpointUrl]);

					var tag = sensus.util.ajaxaction.oResponse.tags[0];

					$("#select_tags").append("<option value='" + tag.id + "' selected='selected'>" + tag.name + "</option>");

				}
			});

		}

	}
};

</script>
</sec:authorize>