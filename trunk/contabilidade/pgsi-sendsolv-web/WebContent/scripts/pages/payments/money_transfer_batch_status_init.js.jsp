<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>

<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN')">

<script type="text/javascript">

	pgsi.util.page.form.fnInitTolltip();

	$('#date').datepicker({
		maxDate: "+0D",
		onClose : function(dateText, object) {
		  if (isNaN((new Date(dateText)).getTime()) || (new Date(dateText) > new Date)) { $(this).val(""); }
		}
	});

	$('#expiration').datepicker({
		minDate: "+0D",
		onClose : function(dateText, object) {
		  if (isNaN((new Date(dateText)).getTime()) || (new Date(dateText) < new Date)) { $(this).val(""); }
		}
	});
</script>

</sec:authorize>