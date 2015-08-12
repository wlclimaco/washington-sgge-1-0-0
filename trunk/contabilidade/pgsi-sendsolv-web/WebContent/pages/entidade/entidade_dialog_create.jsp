<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>
<style>
.teste .form-horizontal .control-label {
	width: auto!important;
}
</style>
	<div class="teste" style="width:100%;">
	<form id="bookForm" method="post" class="form-horizontal">
	<div class="page-header"></div>

	<!-- inicio: -->
	<div class="form-group">
		<div class="row">
			<label class="col-xs-1 control-label">Codigo</label>
			<div class="col-xs-2">
				<input type="text" class="form-control disabled" id="codigo" name="codigo" placeholder="" />
			</div>
			<label class="col-xs-1 control-label">Nome *</label>
			<div class="col-xs-6">
				<input type="text" class="form-control" name="nome" id="nome" placeholder="Nome" />
			</div>
		</div>
		<div class="row">
		</div>
		<label class="col-xs-1 control-label">Regime</label>
		<div class="col-xs-8">
			<select id="mySel" name="regime" class="regime" style="width: 80%;">

			</select><br>
		</div>
	</div>
	<div class="panel-group" id="accordion">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h4 class="panel-title">
					<a data-toggle="collapse" data-parent="#accordion" href="#collapseOne">1 - Cadastro Cnae</a>
				</h4>
			</div>
			<div id="collapseOne" class="panel-collapse collapse in">
				<div class="panel-body">
					<jsp:include page="../cnae/cnae_create_main.jsp" flush="true" />
			</div>
		</div>
	</div>
	<div class="panel panel-default">
		<div class="panel-heading">
			<h4 class="panel-title">
				<a data-toggle="collapse" data-parent="#accordion" href="#collapseTwo">2 - Cadastro Emails</a>
			</h4>
		</div>
		<div id="collapseTwo" class="panel-collapse collapse">
			<div class="panel-body">
				<!-- email: -->
	<jsp:include page="../email/email_main.jsp" flush="true" />
			</div>
		</div>
	</div>

	<div class="panel panel-default">
		<div class="panel-heading">
			<h4 class="panel-title">
				<a data-toggle="collapse" data-parent="#accordion" href="#collapseFour">3 - Telefone</a>
			</h4>
		</div>
		<div id="collapseFour" class="panel-collapse collapse">
			<div class="panel-body">
				<!-- Telefone: -->
	<jsp:include page="../phone/phone_main.jsp" flush="true" />
			</div>
		</div>
	</div>

	<div class="panel panel-default">
		<div class="panel-heading">
			<h4 class="panel-title">
				<a data-toggle="collapse" data-parent="#accordion" href="#collapseThree">5 - Enderecos</a>
			</h4>
		</div>
		<div id="collapseThree" class="panel-collapse collapse">
			<div class="panel-body">
				<!-- endereco: -->
	<jsp:include page="../address/address_main.jsp" flush="true" />
			</div>
		</div>
	</div>

	<div class="panel panel-default">
		<div class="panel-heading">
			<h4 class="panel-title">
				<a data-toggle="collapse" data-parent="#accordion" href="#collapseFive">6 - Documentos</a>
			</h4>
		</div>
		<div id="collapseFive" class="panel-collapse collapse">
			<div class="panel-body">
				<!-- documentos: -->
	<jsp:include page="../document/document_main.jsp" flush="true" />
			</div>
		</div>
	</div>

	<div class="panel panel-default">
		<div class="panel-heading">
			<h4 class="panel-title">
				<a data-toggle="collapse" data-parent="#accordion" href="#collapseEith">7 - Socios</a>
			</h4>
		</div>
		<div id="collapseEith" class="panel-collapse collapse">
			<div class="panel-body">
				<!-- endereco: -->
					<jsp:include page="../socios/socios_create_main.jsp" flush="true" />
				</div>
			</div>
		</div>
	</div>
	</form>
	</div>


<script type="text/javascript">

$(document).ready(function() {
	<c:choose>
	<c:when test="${empty response}">
    	var oPreLoadResponse = null;
    </c:when>
    <c:otherwise>
    	var oPreLoadResponse = ${response};
    </c:otherwise>
</c:choose>

<c:choose>
	<c:when test="${empty regime}">
    	var oRegime = null;
    </c:when>
    <c:otherwise>
    	var oRegime = ${regime};
    </c:otherwise>
</c:choose>

<c:choose>
	<c:when test="${empty cnae}">
    	var oCnae = null;
    </c:when>
    <c:otherwise>
    	var oCnae = ${cnae};
    </c:otherwise>
</c:choose>

	console.log(oCnae);
	sCnaeoption = "";
	for(var i = 0;i < oCnae.length;i++){
		sCnaeoption = sCnaeoption + '<option value="'+oCnae[i].key+'">'+oCnae[i].value+'</option>';
	}
	$('.cnaeId').append(sCnaeoption);
	sCnaeoption = "";
	for(var i = 0;i < oRegime.length;i++){
		sCnaeoption = sCnaeoption + '<option value="'+oRegime[i].key+'">'+oRegime[i].value+'</option>';
	}
	$('.regime').append(sCnaeoption);

 var titleValidators = {
            row: '.col-xs-4',   // The title is placed inside a <div class="col-xs-4"> element
            validators: {
                notEmpty: {
                    message: 'The title is required'
                }
            }
        },
        isbnValidators = {
            row: '.col-xs-4',
            validators: {
                notEmpty: {
                    message: 'The ISBN is required'
                },
                isbn: {
                    message: 'The ISBN is not valid'
                }
            }
        },
        priceValidators = {
            row: '.col-xs-2',
            validators: {
                notEmpty: {
                    message: 'The price is required'
                },
                numeric: {
                    message: 'The price must be a numeric number'
                }
            }
        },
        bookIndex = 0;

    $('#bookForm')
        .formValidation({
            framework: 'bootstrap',
            icon: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                'book[0].title': titleValidators,
                'book[0].isbn': isbnValidators,
                'book[0].price': priceValidators,
				'book[0].cnae': priceValidators,
				'book[0].email': priceValidators
            }
        })

        // Add button click handler
        .on('click', '.addButton', function() {
            bookIndex++;
            var $template = $('#bookTemplate'),
                $clone    = $template
                                .clone()
                                .removeClass('hide')
                                .removeAttr('id')
                                .attr('data-book-index', bookIndex)
                                .insertBefore($template);

            // Update the name attributes
            $clone
                .find('[name="title"]').attr('name', 'book[' + bookIndex + '].title').end()
                .find('[name="isbn"]').attr('name', 'book[' + bookIndex + '].isbn').end()
                .find('[name="price"]').attr('name', 'book[' + bookIndex + '].price').end();

            // Add new fields
            // Note that we also pass the validator rules for new field as the third parameter
            $('#bookForm')
                .formValidation('addField', 'book[' + bookIndex + '].title', titleValidators)
                .formValidation('addField', 'book[' + bookIndex + '].isbn', isbnValidators)
                .formValidation('addField', 'book[' + bookIndex + '].price', priceValidators);
        })

        // Remove button click handler
        .on('click', '.removeButton', function() {
            var $row  = $(this).parents('.form-group'),
                index = $row.attr('data-book-index');

            // Remove fields
            $('#bookForm')
                .formValidation('removeField', $row.find('[name="book[' + index + '].title"]'))
                .formValidation('removeField', $row.find('[name="book[' + index + '].isbn"]'))
                .formValidation('removeField', $row.find('[name="book[' + index + '].price"]'));

            // Remove element containing the fields
            $row.remove();
        });

		$("select").select2({
		  placeholder: "Select a state",
		  allowClear: true
		});


});
</script>

<jsp:include page="../../scripts/pages/entidade/entidade_create_main.js.jsp" flush="true" />
<jsp:include page="../../scripts/pages/empresa/empresa_main.js.jsp" flush="true" />
