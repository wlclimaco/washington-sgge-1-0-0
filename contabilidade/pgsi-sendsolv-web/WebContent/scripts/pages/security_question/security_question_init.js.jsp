<%@ taglib prefix='sec'
	uri='http://www.springframework.org/security/tags'%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt'%>

<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN', 'ROLE_CSR')">

	<script type="text/javascript">

	<c:choose>
		<c:when test="${empty security_question}">
	    	var oSecurityQuestion = null;
	    </c:when>
	    <c:otherwise>
	    	var oSecurityQuestion = ${security_question};
	    </c:otherwise>
	</c:choose>

	// Fill security question dropdown
	$("#security-question-template").find('.container').find('.row-form').find('.security-question').fnLoadDropDownListWithInternationalization(oSecurityQuestion);

	pgsi.pages.security.question.form.fnInitForm();

</script>

</sec:authorize>