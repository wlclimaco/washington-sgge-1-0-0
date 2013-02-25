<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<script type="text/javascript">
/**
 * @namespace sensus.pages.schedule
 * @description The main namespace for the Schedule Page.
 */

/**
 * @fileoverview Defines the core functionality of the Schedule Page.
 * @author Anke Doerfel-Parker
 */

/**
 * The main namespace for schedule-related functionality.
 */
sensus.pages.schedule = {
		 
	/**
	* Whether this is page has been fully initialized.
	*/
	initialized : false,

	/** The Schedule Name */
	scheduleName: "",
	
	/** The Schedule Id */
	scheduleId: "",
	
	/** The Offset Schedule Id */
	offsetScheduleId : "",
	
	/** The Event Schedule Id */
	eventScheduleId : "",
	
	/** Schedule Name */
	scheduleName : "",
	
	/** Count smartpoints */
	countSmartpoints : 0

	
};
</script>