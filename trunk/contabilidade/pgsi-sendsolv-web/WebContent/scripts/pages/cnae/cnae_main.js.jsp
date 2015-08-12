<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>

<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN', 'ROLE_CSR')">

<script type="text/javascript">
/**
 * @namespace pgsi.pages.location
 * @fileoverview The main namespace for the Location List Page.
 */
pgsi.pages.cnae = {

	fnTable : function(oResponse) {

		tbory = "";
		for(var i = 0;i < oResponse.cnaeList.length;i++ ){
			var oCnae = oResponse.cnaeList[i];
			tbory = tbory + '<tr><td><input type="checkbox" class="checkthis" id="'+oCnae.id+'" /></td><td>'+oCnae.id+'</td><td>'+oCnae.codigo+'</td><td>'+oCnae.cnae+'</td><td>'+oCnae.descricao+'</td><tr>';
		}

		return tbory;
	},

	fnCreateRequest : function(sModelAction){

		oCnae = [];
		for(var i=0;i< (parseInt($('.cnae .form-group').length,10)-1);i++){
			aCnae = new CnaeRel({
				idCnae 		:    new Cnae({id : $('#bookForm').find('[name="book['+i+'].cnae"]').val(),modelAction:"INSERT"}),
				modelAction :    sModelAction
			});
			oCnae.push(aCnae);
		}

		return oCnae;
	}
}
</script>

</sec:authorize>