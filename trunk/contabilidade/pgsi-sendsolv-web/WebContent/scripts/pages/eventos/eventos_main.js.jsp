<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>

<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN', 'ROLE_CSR')">

<script type="text/javascript">
/**
 * @namespace pgsi.pages.location
 * @fileoverview The main namespace for the Location List Page.
 */
pgsi.pages.eventos = {

	fnCreateNomeLink : function (val, type, full)
	{ 		if (type !== "display")
		{
			return val;
		}

		return '<a title="View/Edit ' + full.nome + '" href="#/eventos/view?tab=info&pessoaId=' + full.id + '" class="edit_link">' + full.nome + '</a>';

	},

	fnEstado : function (val, type, full)
	{ 		if (type !== "display")
		{
			return val;
		}

		if (!$.pgsi.isNullOrUndefined(full.estado)) {
			return '<span>'+full.estado.nome+'</span>';
		}
		else
		{
			return "";
		}
	},

	fnUf : function (val, type, full)
	{ 		if (type !== "display")
		{
			return val;
		}

		if (!$.pgsi.isNullOrUndefined(full.estado)) {
			return '<span>'+full.estado.abreviacao+'</span>';
		}
		else
		{
			return "";
		}
	},

	fnTable : function(oResponse) {

		tbory = "";
		for(var i = 0;i < oResponse.eventosList.length;i++ ){
			var oCnae = oResponse.eventosList[i];
			tbory = tbory + '<tr><td><input type="checkbox" class="checkthis" id="'+oCnae.id+'" /></td><td>'+oCnae.id+'</td><td>'+oCnae.nome+'</td><td>'+oCnae.estado.nome +'</td><td>'+oCnae.estado.abreviacao +'</td><td>'+oCnae.codigo +'</td><td>'+oCnae.cdIBGE+'</td><td>'+oCnae.municipio+'</td><td><div class="row"><button type="button" id="'+oCnae.id+'" class="btn btn-primary editar">Editar</button><button type="button" id="'+oCnae.id+'" class="btn btn-primary delete">Deletar</button></div></td><tr>';
		}

		return tbory;
	},
	fnFillEventos : function(oResponse) {

		if (!$.pgsi.isNullOrUndefined(oResponse)) {
			if (!$.pgsi.isNullOrUndefined(oResponse.eventosList)) {
				$('#id').val(oResponse.eventosList[0].id);
				$('#eventos').val(oResponse.eventosList[0].nome);
				$('#estado').val(oResponse.eventosList[0].estado.id);
				$('#cep').val(oResponse.eventosList[0].cep);
				$('#codigo').val(oResponse.eventosList[0].codigo);
				$('#ibge').val(oResponse.eventosList[0].cdIBGE);
				$('#municipal').val(oResponse.eventosList[0].municipio);
			}
		}

	},

	fnInitEventos : function() {
		$.pgsi.ajax.post({
			sUrl 		: "api/cliente/fetch/eventos",
			oRequest 	: {"userContext":{"userId":"washington","id":null,"userRole":null,"localeString":null,"tenant":null,"authorities":null},"sortExpressions":[],"preQueryCount":true,"startPage":0,"pageSize":9999},
			fnCallback  : function(oResponse) {

				$('#mytable tbody').empty();
				$('#mytable tbody').append(pgsi.pages.eventos.fnTable(oResponse));

				$(".editar").on("click", function(e)
				{
					e.preventDefault();
					pgsi.util.actiondialog.launchActionDialog (
					"insert",
						pgsi.pages.eventos.dialogSettings.insert(
							$(this).attr('id'),
							1,
							'INSERT'
						)
					);
				});

				$(".delete").on("click", function(e)
				{
					e.preventDefault();
					pgsi.util.actiondialog.launchActionDialog (
					"insert",
						pgsi.pages.eventos.dialogSettings.delete(
							$(this).attr('id'),
							1,
							'INSERT'
						)
					);
				});
			}
		});
	},

	fnRequestEventos : function(sModelAction) {

		oEventos = new Eventos({
			id			: $('#id').val(),
			codigo		: $('#codigo').val(),
			nome		: $('#eventos').val(),
			cdIBGE		: $('#ibge').val(),
			estado		: {id:$('#estado').val()},
			cep			: $('#cep').val(),
			municipio   : $('#municipal').val(),
			modelAction : sModelAction

		})

		oEndereco = new Endereco({
			eventos 		: oEventos,
			modelAction : "NONE"
		})

		oEmpresa = new Empresa({
			enderecos	: [oEndereco],
			modelAction : "NONE"
		})

		var request = new EmpresaMaintenanceRequest();

		request.empresa = oEmpresa;

		$.pgsi.ajax.post({
			sUrl : "api/empresa/add",
			oRequest : request,
			fnCallback : function(oResponse){
				pgsi.pages.eventos.fnInitEventos();
				$.pgsi.progressBar.stop();
			}
		});

	},

	fnDeleteEventos : function(iId) {

		oEventos = new Eventos({
			id			: iId,
			modelAction : "DELETE"

		})

		oEndereco = new Endereco({
			eventos 		: oEventos,
			modelAction : "NONE"
		})

		oEmpresa = new Empresa({
			enderecos	: [oEndereco],
			modelAction : "NONE"
		})

		var request = new EmpresaMaintenanceRequest();

		request.empresa = oEmpresa;

		$.pgsi.ajax.post({
			sUrl : "api/empresa/add",
			oRequest : request,
			fnCallback : function(oResponse){
				pgsi.pages.eventos.fnInitEventos();
				$.pgsi.progressBar.stop();
			}
		});

	},

	eventosTable: {

	}
}
</script>


</sec:authorize>