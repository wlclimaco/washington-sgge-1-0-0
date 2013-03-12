<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<sec:authorize access="hasAnyRole('ROLE_Role.Admin', 'ROLE_Role.System Operator', 'ROLE_Role.Customer Support', 'ROLE_Role.Billing Manager')">
<script type="text/javascript">
/**
 * @namespace sensus.pages.systemsettings
 * @description The main namespace for the System Settings Page.
 */

/**
 * @fileoverview Defines the core functionality of the System Settings Page.
 * @author Raphael Constantino
 */

/**
 * The main namespace for schedule-related functionality.
 */
sensus.pages.systemsettings = {


};
</script>
</sec:authorize>