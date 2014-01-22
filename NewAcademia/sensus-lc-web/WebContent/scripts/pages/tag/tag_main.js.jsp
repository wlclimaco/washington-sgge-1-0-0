<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<script type="text/javascript">
/**
 * @namespace sensus.pages.tag
 * @description The main namespace for the tag Page.
 * @fileoverview Defines the core functionality of the tag Page.
 * @author Raphael Constantino
 *
 * The main namespace for tag-related functionality.
 */
sensus.pages.tag = {

	/**
	 * The Create Tag function
	 */
	createTag : function()
	{

		var sInputVal = $("#combo-tag button").prev().val().trim();
		var iCount = 0;

		$("#tag-add-select option").each(function()
	    {
			if ($(this).text().toLowerCase() == sInputVal.toLowerCase())
			{
				iCount ++;
			}
		});


		/** BEGIN Validation */
		if (sInputVal == $.sc.locale('widgets.combobox.prompt'))
		{

			$('#tag-add-select_input').validationEngine('showPrompt', $.sc.locale("tag.page.error.validValue"), 'red', '', true);
			return;

		}

		if (sInputVal.length > 100)
		{

			$('#tag-add-select_input').validationEngine('showPrompt', $.sc.locale("tag.page.error.maxlength"), 'red', '', true);
			return;

		}

		if ((sInputVal == $.sc.locale('tag.page.addTag')) || (!sInputVal.length))
		{

			$('#tag-add-select_input').validationEngine('showPrompt', $.sc.locale("tag.page.error.required"), 'red', '', true);
			return;

		}
		if (iCount > 0)
		{
			$.sc.showMessage("messaging-main", $.sc.locale("tag.page.error.exist"), "error");
			return;
		}


		/* END Validation **/

		var sTags = $('#tag-add-select_input').val();

		$('.formError').remove();

		var fnCallBack = function()
		{

			$('#tag-add-select_input').val($.sc.locale('tag.page.addTag'));
			$('#tag-add-select').append('<option>'+sInputVal+'</option>');
			sensus.widgets.datatable.reloadTable(sensus.pages.systemsettings.tag.tagTable);

		};

		/** Send ajax action */
		$.sc.getJson('api/tag/insert', new tagRequest(null, sTags, null, sensus.widgets.datatable.isAllRows, null, null), false, fnCallBack, $.sc.locale("action.inserttag.success",sTags));

	}

};
</script>