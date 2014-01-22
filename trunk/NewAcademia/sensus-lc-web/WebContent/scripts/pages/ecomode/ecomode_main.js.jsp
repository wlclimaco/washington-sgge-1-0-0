<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<sec:authorize access="hasAnyRole('ROLE_Role.Admin', 'ROLE_Role.System Operator', 'ROLE_Role.Customer Support', 'ROLE_Role.Billing Manager')">
<script type="text/javascript">

sensus.pages.ecomode = {

	tags :
	{

		fnReloadTags: function(aData)
		{

			var aTags = aData.tags;

			if(aTags)
			{

				for (i in aTags)
				{

					if (aTags.hasOwnProperty(i))
					{

						$('#select_tags').append('<option value=' + aTags[i].id + '>' + aTags[i].name + '</option>');

					}

				}

				$(".chzn-select").chosen();

			}

		},

		fnLoadTagList : function()
		{

			var oRequest  = new inquiryTagRequest();
			var oResponse = $.sc.getJson("api/tag/fetch", oRequest, false, null, null);
			sensus.pages.ecomode.tags.fnReloadTags(oResponse);

		},

		init : function ()
		{

			sensus.pages.ecomode.tags.fnLoadTagList();

		}
	}
};

</script>
</sec:authorize>