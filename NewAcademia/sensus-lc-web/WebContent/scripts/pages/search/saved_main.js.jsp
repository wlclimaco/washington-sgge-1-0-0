<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>

<script type="text/javascript">

/**
 * @namespace sensus.pages.saved
 * @description The main namespace for the Saved Page.
 */


/**
 * @fileoverview Defines the core functionality of the Saved page.
 * @author Cristiane Cobo
 */

/**
 * The main namespace for the Saved Page.
 */
sensus.pages.saved = {

	initialized     : false, // Whether this is page has been fully initialized.
	searchId        : 0,     // Track search id
	filterSearchId  : "",    // Tack filter saved id
	searchName      : "",    // The Search Name
	config          : {

		SORT             			: 'sort=',
		TAG_ID           			: 'tags=',
		HOUSING          			: 'housing=',
		ZIP_CODE         			: 'zip=',
		DIMMABLE         			: 'dimmable=',
		GROUP_ID         			: 'groups=',
		PROTECTED        			: 'configuration=',
		LAMP_TYPE        			: 'lamptype=',
		CITY_NAME        			: 'city=',
		ALERTS						:  'alerts=',
		LIFECYCLE_STATE				: 'lifecycle_state=',
		ALL_ALARMS       			: 'alarm_type=',
		STREET_NAME      			: 'street=' ,
		ALL_WARNINGS     			: 'warning_type=',
		WATTAGE_RATING   			: 'wattage=',
		EVENT_SCHEDULE   			: 'event_schedule=',
		POLE_ID			 			: 'query=12|',
		FLEXNET_ID 		 			: 'query=36|',
		OFFSET_SCHEDULE  			: 'offset_schedule=',
		HOUSING_COLOR	 			: 'housing_color=',
		MODEL_NUMBER	 			: 'model_number=',
		LIGHT_DRIVER_SERIAL_NUMBER	: 'light_driver_serial_number=',
		BULB_SERIAL_NUMBER			: 'bulb_serial_number=',
		FIRMWARE_VERSION			: 'firmware_version=',
		UPPER_ASSEMBLY_SERIAL_NUMBER: 'upper_assembly_serial_number=',
		LOWER_ASSEMBLY_SERIAL_NUMBER: 'lower_assembly_serial_number=',
		COLOR_TEMPERATURE			: 'color_temperature=',
		INPUT_VOLTAGE_RANGE			: 'voltage_range=',
		DATE_ADDED_BEFORE			: 'date_added_before=',
		DATE_ADDED_AFTER			: 'date_added_after=',
		ECOMODE 					: 'ecomode='

	}
};
</script>