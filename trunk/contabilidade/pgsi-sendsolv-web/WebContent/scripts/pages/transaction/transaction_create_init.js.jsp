<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>

<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN')">

<script type="text/javascript">
	<c:choose>
		<c:when test="${empty batch_response}">
	    	var oBatchResponse = null;
	    </c:when>
	    <c:otherwise>
	    	var oBatchResponse = ${batch_response};
	 	</c:otherwise>
	 </c:choose>

	 <c:choose>
		<c:when test="${empty location_response}">
	    	var oLocationResponse = null;
	    </c:when>
	    <c:otherwise>
	    	var oLocationResponse = ${location_response};
	 	</c:otherwise>
	 </c:choose>

	 <c:choose>
	 	<c:when test="${empty productPlan}">
	    	var oProductPlan = null;
	    </c:when>
	    <c:otherwise>
	    	var oProductPlan = ${productPlan};
	 	</c:otherwise>
	</c:choose>
$(document).ready(function() {
	$("#pricing").fnLoadDropDownList(oProductPlan);

	// Init form
	pgsi.pages.transaction.form.fnInitForm($("#dialog-one-off").find('form'), oBatchResponse);
	$.pgsi.progressBar.stop();
	$.pgsi.progressBar.stopGlobal();
});
</script>

</sec:authorize>