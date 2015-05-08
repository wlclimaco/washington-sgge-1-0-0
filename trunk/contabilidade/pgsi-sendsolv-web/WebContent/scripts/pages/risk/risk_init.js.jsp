<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags'%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>

<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN', 'ROLE_CSR')">

<script type="text/javascript">

$(document).ready(function()
{
	pgsi.pages.risk.form.validator =
	$("#risk-form").validate({
		ignore : "",

		invalidHandler : function(form, validator) {
			$.each(validator.errorList, function(index, value) {
				$(value.element).addClass("error");

				if (value.element.nodeName.toLowerCase() == 'select') {
					$(value.element).next('span').addClass("error");
				}
			});
		}
	})
});

</script>

</sec:authorize>