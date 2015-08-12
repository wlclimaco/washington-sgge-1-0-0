<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>

<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN', 'ROLE_CSR')">

<script type="text/javascript">
/**
 * @namespace pgsi.pages.location
 * @fileoverview The main namespace for the Location List Page.
 */
pgsi.pages.endereco = {
		 fnCreateRequest : function(sModelAction){
			 aEndereco = [];
			for(var i=0;i< (parseInt($('.endereco .form-group').length,10)-1);i++){
				 var oEndereco = new Endereco({
					id						: $('#bookForm').find('[name="book['+i+'].id"]').val(),
					enderecoTypeEnumValue   : $('#bookForm').find('[name="book['+i+'].emailTipo"]').val(),
					logradouro				: $('#bookForm').find('[name="book['+i+'].logradouro"]').val(),
					cidade					: {nome: $('#bookForm').find('[name="book['+i+'].cidade"]').val(),modelAction : "NONE"},
					estado					: {abreviacao: $('#bookForm').find('[name="book['+i+'].estado"]').val(),modelAction : "NONE"},
					bairro					: $('#bookForm').find('[name="book['+i+'].bairro"]').val(),
					numero					: $('#bookForm').find('[name="book['+i+'].numero"]').val(),
					cep						: $('#bookForm').find('[name="book['+i+'].cep"]').val(),
					modelAction 			: sModelAction
				 })
				 aEndereco.push(oEndereco);
			}
			 return aEndereco;
		 }

}
</script>

</sec:authorize>