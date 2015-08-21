<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>
<div class="container-fluid">
	<div class="row">
		<div class="col-md-12">
			<div class="panel panel-default">
				<div class="panel-heading">
				</div>
				<div class="panel-body">
					<div class="row">
						<label for="inputEmail3" class="col-sm-2 control-label" style="margin-left:20px;">
						Id
						</label>
						<div class="col-sm-2">
							<input type="" class="form-control" id="id" />
						</div>
					</div>

					<div class="row">
						<label for="inputEmail3" class="col-sm-2 control-label" style="margin-left:20px;">
						Descrição
						</label>
						<div class="col-sm-8">
							<input type="" class="form-control" id="descricao" />
						</div>
					</div>

					<div class="row">
						<label for="inputEmail3" class=" col-sm-2 control-label" style="margin-left:3%;">Tipo</label>
						<select id="tipo" name="evento" class="evento" style="width:16%!Important;margin-left:2%!Important">
							<option value="1">Desconto  ( - )</option>
							<option value="2">Acrescimo ( + )</option>
						</select>
					</div>

					<div class="row">
						<label class="col-xs-2 control-label" style="margin-left:20px;">Valor</label>
						<div class="col-xs-3">
							<input type="text" class="form-control" name="valor" id="valor"  placeholder="R$0.00" />
						</div>
						<label class="col-xs-2 control-label">Porcentagem</label>
						<div class="col-xs-2">
							<input type="text" class="form-control" name="porcentagem" id="porcentagem" placeholder="10.0%" />
						</div>
					</div>

					<div class="row"     style="margin-left: 16%;">
						<div class="checkbox">
							<label>
								<input type="checkbox" id="isMensal" /> Mensal
							</label>
						</div>

						<div class="checkbox">
							<label>
								<input type="checkbox" id="isAutomatico" /> Automatico
							</label>
						</div>
					</div>

					<div class="row">
						<label for="inputEmail3" class="col-sm-2 control-label" style="margin-left:20px;">
						Observação
						</label>
						<div class="col-sm-8">
							<input type="" class="form-control" id="observacao" />
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">

$(document).ready(function()
{
	<c:choose>
		<c:when test="${empty response}">
		   var oPreLoadResponse = null;
		</c:when>
		<c:otherwise>
			var oPreLoadResponse = ${response};
		</c:otherwise>
	</c:choose>

	$("select").select2({
		  placeholder: "Select a state",
		  allowClear: true
		});
console.log(oPreLoadResponse)
	pgsi.pages.eventos.fnFillEventos(oPreLoadResponse)
});
</script>