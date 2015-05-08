<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags'%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>

<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN', 'ROLE_CSR')">

<script type="text/javascript">


	<c:choose>
		<c:when test="${empty response}">
			var oPreLoadResponse = null;
		</c:when>
		<c:otherwise>
			var oPreLoadResponse = ${response};
		</c:otherwise>
	</c:choose>


	$(document).ready(function() {

		pgsi.pages.transfer.create.form.fnInitForm();


		if(oPreLoadResponse != null)
			pgsi.pages.transfer.create.form.fillObjectDialog(oPreLoadResponse.memberList[0]);
	});


</script>

</sec:authorize>