<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>

<style>
.filterable {
    margin-top: 15px;
}
.filterable .panel-heading .pull-right {
    margin-top: -20px;
}
.filterable .filters input[disabled] {
    background-color: transparent;
    border: none;
    cursor: auto;
    box-shadow: none;
    padding: 0;
    height: auto;
}
.filterable .filters input[disabled]::-webkit-input-placeholder {
    color: #333;
}
.filterable .filters input[disabled]::-moz-placeholder {
    color: #333;
}
.filterable .filters input[disabled]:-ms-input-placeholder {
    color: #333;
}


</style>

<div class="container-fluid">
	<div class="row">
		<div class="col-md-12">
			<div class="row">
				<div class="col-md-12">
					<div class="row">
						<div class="col-md-6">
							<form role="form">
								<div class="form-group">

									<label for="exampleInputEmail1">
										Data Inicio
									</label>
									<input class="form-control datepicker" id="dataInicial" type="text" />
								</div>
								<div class="form-group">

									<label for="exampleInputPassword1">
										Data Final
									</label>
									<input class="form-control datepicker" id="dataFinal" type="text" />
								</div>
								<div class="form-group">

									<label for="exampleInputPassword1">
										Numero Contrato
									</label>
									<input class="form-control" id="num-contrato" type="text" />
								</div>
								<div class="form-group">
									<div class="checkbox">

										<label>
											<input type="checkbox" /> Gerar parcelas Contas Receber<br>
											<input type="checkbox" /> Gerar parcelas Contas Pagar
										</label>
									</div>
								</div>
							</form>
						</div>
					</div>
					<div class="panel panel-primary filterable">
			            <div class="panel-heading">
			                <h3 class="panel-title">Users</h3>
			                <div class="pull-right">
			                    <button class="btn btn-default btn-xs btn-filter"><span class="glyphicon glyphicon-filter"></span> Filter</button>
			                </div>
			            </div>
			            <table class="table" id="mytable">
			                <thead>
			                    <tr class="filters">
									<th><input type="checkbox" id="checkall" /></th>
			                        <th><input type="text" class="form-control" placeholder="#" disabled></th>
			                        <th><input type="text" class="form-control" placeholder="First Name" disabled></th>
			                        <th><input type="text" class="form-control" placeholder="Last Name" disabled></th>
			                        <th><input type="text" class="form-control" placeholder="Username" disabled></th>
			                    </tr>
			                </thead>
			                <tbody>

			                </tbody>
			            </table>
			        </div>
				</div>
			</div>
		</div>
	</div>
</div>
<jsp:include page="../../scripts/pages/plano/plano_main.js.jsp" flush="true" />
<script>
$(document).ready(function(){

	$("#dataInicial.datepicker").datepicker({
		maxDate: "+0D",
		onClose : function(dateText, object) {
			if (isNaN((new Date(dateText)).getTime()) || (new Date(dateText) > new Date)) { $(this).val(""); }
		}
	});

	$("#dataFinal.datepicker").datepicker({
		maxDate: "+0D",
		onClose : function(dateText, object) {
			if (isNaN((new Date(dateText)).getTime()) || (new Date(dateText) > new Date)) { $(this).val(""); }
		}
	});


 	$.pgsi.ajax.post({
 		sUrl 		: "api/produto/fetchall",
 		oRequest 	: {"userContext":{"userId":"washington","id":null,"userRole":null,"localeString":null,"tenant":null,"authorities":null},"sortExpressions":[],"preQueryCount":true,"startPage":0,"pageSize":9999},
 		fnCallback  : function(oResponse) {

 			$('#mytable tbody').append(pgsi.pages.plano.fnTable(oResponse));
 		}
 	});
    $('.filterable .btn-filter').click(function(){
        var $panel = $(this).parents('.filterable'),
        $filters = $panel.find('.filters input'),
        $tbody = $panel.find('.table tbody');
        if ($filters.prop('disabled') == true) {
            $filters.prop('disabled', false);
            $filters.first().focus();
        } else {
            $filters.val('').prop('disabled', true);
            $tbody.find('.no-result').remove();
            $tbody.find('tr').show();
        }
    });

    $('.filterable .filters input').keyup(function(e){
        /* Ignore tab key */
        var code = e.keyCode || e.which;
        if (code == '9') return;
        /* Useful DOM data and selectors */
        var $input = $(this),
        inputContent = $input.val().toLowerCase(),
        $panel = $input.parents('.filterable'),
        column = $panel.find('.filters th').index($input.parents('th')),
        $table = $panel.find('.table'),
        $rows = $table.find('tbody tr');
        /* Dirtiest filter function ever ;) */
        var $filteredRows = $rows.filter(function(){
            var value = $(this).find('td').eq(column).text().toLowerCase();
            return value.indexOf(inputContent) === -1;
        });
        /* Clean previous no-result if exist */
        $table.find('tbody .no-result').remove();
        /* Show all rows, hide filtered ones (never do that outside of a demo ! xD) */
        $rows.show();
        $filteredRows.hide();
        /* Prepend no-result row if all rows are filtered */
        if ($filteredRows.length === $rows.length) {
            $table.find('tbody').prepend($('<tr class="no-result text-center"><td colspan="'+ $table.find('.filters th').length +'">No result found</td></tr>'));
        }
    });

	$("#mytable #checkall").click(function () {
        if ($("#mytable #checkall").is(':checked')) {
            $("#mytable input[type=checkbox]").each(function () {
                $(this).prop("checked", true);
            });

        } else {
            $("#mytable input[type=checkbox]").each(function () {
                $(this).prop("checked", false);
            });
        }
    });

    $("[data-toggle=tooltip]").tooltip();
});
</script>

