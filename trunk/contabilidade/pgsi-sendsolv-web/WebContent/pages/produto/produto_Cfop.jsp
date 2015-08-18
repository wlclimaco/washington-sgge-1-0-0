<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>

<div class="container-fluid">
	<div class="row">
		<div class="col-md-12">
			<div class="preco">
				<div class="row">
					<div class="form-group">
						<label class="col-xs-3 control-label">CFOP Venda Dentro do estado</label>
							<select id="mySel" name="book[0].entidade" class="email-type" >
								<option value="1"> Cfop 001</option>
								<option value="2"> teste 002</option>
								<option value="3"> teste 002</option>
								<option value="4"> teste 002</option>
								<option value="5"> teste 002</option>
							</select>
					</div>
				</div>
				<div class="row">
					<div class="form-group">
						<label class="col-xs-3 control-label">CFOP Venda Fora do estado</label>
						<div class="col-xs-15">
							<select id="mySel" name="book[0].entidade" class="email-type" >
								<option value="1"> Cfop 001</option>
								<option value="2"> teste 002</option>
								<option value="3"> teste 002</option>
								<option value="4"> teste 002</option>
								<option value="5"> teste 002</option>
							</select>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="form-group">
						<label class="col-xs-3 control-label">CFOP Devolução Dentro do estado</label>
						<div class="col-xs-15">
							<select id="mySel" name="book[0].entidade" class="email-type" >
								<option value="1"> Cfop 001</option>
								<option value="2"> teste 002</option>
								<option value="3"> teste 002</option>
								<option value="4"> teste 002</option>
								<option value="5"> teste 002</option>
							</select>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="form-group">
						<label class="col-xs-3 control-label">CFOP Devolução Fora do estado</label>
						<div class="col-xs-15">
							<select id="mySel" name="book[0].entidade" class="email-type" >
								<option value="1"> Cfop 001</option>
								<option value="2"> teste 002</option>
								<option value="3"> teste 002</option>
								<option value="4"> teste 002</option>
								<option value="5"> teste 002</option>
							</select>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="form-group">
						<label class="col-xs-3 control-label">CFOP Garantia Dentro do estado</label>
						<div class="col-xs-15">
							<select id="mySel" name="book[0].entidade" class="email-type" >
								<option value="1"> Cfop 001</option>
								<option value="2"> teste 002</option>
								<option value="3"> teste 002</option>
								<option value="4"> teste 002</option>
								<option value="5"> teste 002</option>
							</select>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="form-group">
						<label class="col-xs-3 control-label">CFOP Garantia Fora do estado</label>
							<select id="mySel" name="book[0].entidade" class="email-type" >
								<option value="1"> Cfop 001</option>
								<option value="2"> teste 002</option>
								<option value="3"> teste 002</option>
								<option value="4"> teste 002</option>
								<option value="5"> teste 002</option>
							</select>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function() {
$("select").select2({
		  placeholder: "Select a state",
		  allowClear: true
		});

});
</script>