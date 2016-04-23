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

<c:choose>
	<c:when test="${empty cidadeResponse}">
       var oCidadeResponse = null;
    </c:when>
    <c:otherwise>
    	var oCidadeResponse = ${cidadeResponse};
    </c:otherwise>
</c:choose>
console.log(oCidadeResponse);
	if (!$.qat.isNullOrUndefined(oPreLoadResponse)) {
		qat.pages.empresa.view.fnFillEmpresa(oPreLoadResponse);
	}else{
		$.qat.ajax.post({
			sUrl 		: "api/empresa/fetch",
			oRequest 	: {id:parseInt($.address.parameter("locationId"),10)},
			fnCallback  : function(oResponse) {
				qat.pages.empresa.view.fnFillEmpresa(oResponse);
			}
		});
	}

	$('#add-note').click(function(e)
	{

		e.preventDefault();
		qat.util.actiondialog.launchActionDialog (
			"insert",
			qat.pages.note.dialogSettings.insert(
				$('#business-id').val(),
				$('#company-name-field').text(),
				1)
		);
	});

	$('#add-cnae').click(function(e)
	{

		e.preventDefault();
		qat.util.actiondialog.launchActionDialog (
			"insert",
			qat.pages.cnae.dialogSettings.insert(
				$('#business-id').val(),
				$('#company-name-field').text(),
				1)
		);
	});

	$('#add-plano').click(function(e)
	{

		e.preventDefault();
		qat.util.actiondialog.launchActionDialog (
			"insert",
			qat.pages.plano.dialogSettings.insert(
				$('#business-id').val(),
				$('#company-name-field').text(),
				1)
		);
	});

	$('#add-filial').click(function(e)
	{

		e.preventDefault();
		qat.util.actiondialog.launchActionDialog (
			"insert",
			qat.pages.entidade.dialogSettings.insert(
				parseInt($.address.parameter("locationId"),10),
				2,
				'INSERT')
		);
	});

	$('#add-deposito').click(function(e)
	{

		e.preventDefault();
		qat.util.actiondialog.launchActionDialog (
			"insert",
			qat.pages.entidade.dialogSettings.insert(
					parseInt($.address.parameter("locationId"),10),
				3,
				'INSERT')
		);
	});
$.qat.progressBar.stopGlobal();
</script>
