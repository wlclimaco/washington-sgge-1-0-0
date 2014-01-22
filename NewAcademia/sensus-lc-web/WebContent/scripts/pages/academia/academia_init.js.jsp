<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<sec:authorize access="hasAnyRole('ROLE_Role.Admin', 'ROLE_Role.System Operator', 'ROLE_Role.Customer Support', 'ROLE_Role.Billing Manager')">
<script type="text/javascript">
/**
 * @namespace sensus.pages.group
 * @description The init namespace for the Group Page.
 */

/**
 * @fileoverview Initializes the group page.
 *
 * @author Anke Doerfel-Parker
 */
$(document).ready(function() {

	$('#datafin').datepicker();
	$('#dataini').datepicker();

});
</script>
</sec:authorize>