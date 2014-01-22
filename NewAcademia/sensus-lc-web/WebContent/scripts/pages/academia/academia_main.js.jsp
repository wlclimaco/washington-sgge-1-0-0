<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<script type="text/javascript">
/**
 * @namespace sensus.pages.group
 * @description The main namespace for the Group Page.
 */

/**
 * @fileoverview Defines the core functionality of the Group page.
 * @author Anke Doerfel-Parker
 */

/**
 * The main namespace for the Group Page.
 */
sensus.pages.academia = {

	fnFillAcademia : function(data)
	{
		$('#academiaId').val(sensus.widgets.datatable.currentRow[1]);
		$('#academia').val(sensus.widgets.datatable.currentRow[2]);
		$('#logradouro').val(sensus.widgets.datatable.currentRow[3]);
		$('#numero').val(sensus.widgets.datatable.currentRow[4]);
		$('#bairro').val(sensus.widgets.datatable.currentRow[5]);
		$('#cidade').val(sensus.widgets.datatable.currentRow[6]);
		$('#cep').val(sensus.widgets.datatable.currentRow[7]);
		$('#telef').val(sensus.widgets.datatable.currentRow[7]);
		$('#dataini').val($.sc.dateFormat(sensus.widgets.datatable.currentRow[8], sensus.settings.dateFormatMask));
		$('#datafin').val($.sc.dateFormat(sensus.widgets.datatable.currentRow[9], sensus.settings.dateFormatMask));
		if(sensus.widgets.datatable.currentRow[10] == true)
			$('#atual').attr("checked", true);
		else
			$('#atual').attr("checked", false);
	}
};
</script>