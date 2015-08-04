<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>

<script type="text/javascript">
<c:choose>
	<c:when test="${empty response}">
       var oPreLoadResponse = null;
    </c:when>
    <c:otherwise>
    	var oPreLoadResponse = ${response};
    </c:otherwise>
</c:choose>

	if (!$.pgsi.isNullOrUndefined(oPreLoadResponse)) {
		pgsi.pages.empresa.view.fnFillEmpresa(oPreLoadResponse);
	}else{
		$.pgsi.ajax.post({
			sUrl 		: "api/empresa/fetch",
			oRequest 	: {id:parseInt($.address.parameter("locationId"),10)},
			fnCallback  : function(oResponse) {
				pgsi.pages.empresa.view.fnFillEmpresa(oResponse);
			}
		});
	}

	$('#add-note').click(function(e)
	{

		e.preventDefault();
		pgsi.util.actiondialog.launchActionDialog (
			"insert",
			pgsi.pages.note.dialogSettings.insert(
				$('#business-id').val(),
				$('#company-name-field').text(),
				1)
		);
	});

	$('#add-cnae').click(function(e)
	{

		e.preventDefault();
		pgsi.util.actiondialog.launchActionDialog (
			"insert",
			pgsi.pages.cnae.dialogSettings.insert(
				$('#business-id').val(),
				$('#company-name-field').text(),
				1)
		);
	});

	$('#add-plano').click(function(e)
	{

		e.preventDefault();
		pgsi.util.actiondialog.launchActionDialog (
			"insert",
			pgsi.pages.plano.dialogSettings.insert(
				$('#business-id').val(),
				$('#company-name-field').text(),
				1)
		);
	});

	$('#add-filial').click(function(e)
	{

		e.preventDefault();
		pgsi.util.actiondialog.launchActionDialog (
			"insert",
			pgsi.pages.entidade.dialogSettings.insert(
				$('#business-id').val(),
				$('#company-name-field').text(),
				1)
		);
	});

	$('#add-deposito').click(function(e)
	{

		e.preventDefault();
		pgsi.util.actiondialog.launchActionDialog (
			"insert",
			pgsi.pages.entidade.dialogSettings.insert(
				$('#business-id').val(),
				$('#company-name-field').text(),
				1)
		);
	});
$.pgsi.progressBar.stopGlobal();
</script>
