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

						<label for="inputEmail3" class="col-sm-1 control-label" style="margin-left:20px;">
						Cidade
						</label>
						<div class="col-sm-5">
							<input type="" class="form-control" id="cidade" />
						</div>
					</div>
					<div class="row">
						<label for="inputEmail3" class=" col-sm-2 control-label" style="margin-left:3%;">Estado</label>
						<select id="estado" name="estado" class="estado" style="width:60%!Important;margin-left:3%;"></select>
					</div>

					<div class="row">
						<label for="inputEmail3" class="col-sm-2 control-label" style="margin-left:20px;">
						CEP
						</label>
						<div class="col-sm-8">
							<input type="" class="form-control" id="cep" />
						</div>
					</div>

					<div class="row">
						<label for="inputEmail3" class="col-sm-2 control-label" style="margin-left:20px;">
						Codigo
						</label>
						<div class="col-sm-8">
							<input type="" class="form-control" id="codigo" />
						</div>
					</div>

					<div class="row">
						<label for="inputEmail3" class="col-sm-2 control-label" style="margin-left:20px;">
						Codigo IBGE
						</label>
						<div class="col-sm-8">
							<input type="" class="form-control" id="ibge" />
						</div>
					</div>

					<div class="row">
						<label for="inputEmail3" class="col-sm-2 control-label" style="margin-left:20px;">
						Codigo Municipal
						</label>
						<div class="col-sm-8">
							<input type="" class="form-control" id="municipal" />
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

	<c:choose>
		<c:when test="${empty estado}">
		   var oEstado = null;
		</c:when>
		<c:otherwise>
			var oEstado = ${estado};
		</c:otherwise>
	</c:choose>

	console.log(oEstado);
	if (!$.pgsi.isNullOrUndefined(oEstado)) {
		sCnaeoption = ""
		for(var a = 0;a<oEstado.length;a++){
			sCnaeoption = sCnaeoption + '<option value="'+oEstado[a].key+'">'+oEstado[a].value+'</option>';
		}
		$('#estado').append(sCnaeoption);
	}
	$("select").select2({
		  placeholder: "Select a state",
		  allowClear: true
		});

	pgsi.pages.cidade.fnFillCidade(oPreLoadResponse)
});
</script>