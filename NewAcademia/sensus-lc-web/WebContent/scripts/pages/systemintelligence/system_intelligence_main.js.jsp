<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<sec:authorize access="hasAnyRole('ROLE_Role.Admin', 'ROLE_Role.System Operator', 'ROLE_Role.Customer Support', 'ROLE_Role.Billing Manager')">
<script type="text/javascript">


/**
 * @fileoverview Defines the core functionality of the System intelligence Page.
 * @author Rodolfo alves
 */

/**
 * The main namespace for schedule-related functionality.
 */
sensus.pages.systemintelligence = {


};
</script>
</sec:authorize>