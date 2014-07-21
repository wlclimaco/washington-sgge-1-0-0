<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
$(document).ready(function ()
{

	var viewLoadedObject;

	//loads object if being served via controller
	<c:choose>
		<c:when test="${empty supermercadoResponse}">
				viewLoadedObject = null;
		</c:when>
		<c:otherwise>
				viewLoadedObject = ${supermercadoResponse};
		</c:otherwise>
	</c:choose>
	qat.model.supermercado.pages.openPagedFetchWSSuper(viewLoadedObject);


});
</script>
