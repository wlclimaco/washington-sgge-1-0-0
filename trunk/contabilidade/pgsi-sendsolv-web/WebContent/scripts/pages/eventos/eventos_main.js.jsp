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
			var fValor = 0;

			if(oCnae.valor > 0){
				fValor = 'R$ ' + oCnae.valor;
			}else{
				fValor = oCnae.porcentagem + " %"
			}
			tbory = tbory + '<tr><td><input type="checkbox" class="checkthis" id="'+oCnae.id+'" /></td><td>'+oCnae.id+'</td><td>'+oCnae.codigo+'</td><td>'+oCnae.descricao +'</td><td>'+oCnae.Tipo +'</td><td>'+ fValor +'</td><td>'+oCnae.isMensal+'</td><td>'+oCnae.noteText+'</td><td><div class="row"><button type="button" id="'+oCnae.id+'" class="btn btn-primary editar">Editar</button><button type="button" id="'+oCnae.id+'" class="btn btn-primary delete">Deletar</button></div></td><tr>';
		}

		return tbory;
	},
	fnFillEventos : function(oResponse) {
debugger;
		if (!$.pgsi.isNullOrUndefined(oResponse)) {
			if (!$.pgsi.isNullOrUndefined(oResponse.eventosList)) {
				$('#id').val(oResponse.eventosList[0].id);
				$('#descricao').val(oResponse.eventosList[0].descricao);
				$('#tipo').val(oResponse.eventosList[0].tipo);
				$('#valor').val(oResponse.eventosList[0].valor);
				$('#porcentagem').val(oResponse.eventosList[0].porcentagem);
				if(oResponse.eventosList[0].isMensal){
					$('#isMensal').prop('checked', true);
				}else{
					$('#isMensal').prop('checked', false);
				}
				if(oResponse.eventosList[0].isAutomatico){
					$('#isAutomatico').prop('checked', false);
				}else{
					$('#isAutomatico').prop('checked', false);
				}
				$('#observacao').val(oResponse.eventosList[0].noteText);
			}
		}

	},

	fnInitEventos : function() {
		$.pgsi.ajax.post({
			sUrl 		: "api/funcionario/eventos",
			oRequest 	: {"userContext":{"userId":"washington","id":null,"userRole":null,"localeString":null,"tenant":null,"authorities":null},"sortExpressions":[],"preQueryCount":true,"startPage":0,"pageSize":9999},
			fnCallback  : function(oResponsea) {
console.log(oResponsea)
				$('#mytable tbody').empty();
				$('#mytable tbody').append(pgsi.pages.eventos.fnTable(oResponsea));

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

		var isMensal 	 = 1;
		var isAutomatico = 1;

			//$('#isMensal').is(":checked")),
			//$('#isAutomatico').is(":checked"));
		oEventos = new Eventos({
			id 				: $('#id').val(),
			descricao 		: $('#descricao').val(),
			tipo 			: $('#tipo').val(),
			valor 			: $('#valor').val(),
			porcentagem		: $('#porcentagem').val(),
			isMensal 		: isMensal,
			isSistema   	: isAutomatico,
			noteText 		: $('#observacao').val(),
			modelAction 	: sModelAction

		})

		oEventoPessoa = new EventoPessoa({
			idEvent 	: oEventos,
			modelAction : "NONE"
		})

		oFuncionario = new Funcionario({
			eventosList	: [oEventoPessoa],
			modelAction : "NONE"
		})

		var request = new FuncionarioMaintenanceRequest();

		request.funcionario = oFuncionario;

		$.pgsi.ajax.post({
			sUrl : "api/funcionario/add",
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