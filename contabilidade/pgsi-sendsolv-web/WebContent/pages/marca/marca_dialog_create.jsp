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
						Marca
						</label>
						<div class="col-sm-5">
							<input type="" class="form-control" id="cidade" />
						</div>
					</div>

					<div class="row">
						<label for="inputEmail3" class="col-sm-2 control-label" style="margin-left:20px;">
						Fabricante
						</label>
						<div class="col-sm-8">
							<input type="" class="form-control" id="cep" />
						</div>
					</div>

					<div class="row">
						<jsp:include page="../address/address_main.jsp" flush="true" />
					</div>

					<div class="row">
						<div class="page-header"></div>
						<jsp:include page="../phone/phone_main.jsp" flush="true" />
					</div>

					<div class="row">
						<div class="page-header"></div>
						<jsp:include page="../email/email_main.jsp" flush="true" />
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

	pgsi.pages.marca.fnFillMarca(oPreLoadResponse)
});
</script>