<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>
<script type="text/javascript">
//executes when head.js says all js files loaded
head(function()
{
	$(document).ready(function ()
	{
			// Get Settings from pre-loaded data and set
			<c:choose>
				<c:when test="${empty response}">
			    	var oSettingsResponse = null;
			    </c:when>
			    <c:otherwise>
			    	var oSettingsResponse = ${response};
			    </c:otherwise>
			</c:choose>

			qat.pages.wd.init(oSettingsResponse);
	});
});
</script>