<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<script type="text/javascript">
/**
 * @namespace     sensus.pages.user
 * @author        Vinicius Scalon Ferreira
 * @description   The main namespace for the User Page.
 * @fileoverview  Defines the core functionality of the User page.
 *
 * The main namespace for the User Page.
 *
 */
sensus.pages.user = {


	nLat       : 0,
	nLng       : 0,
	aGroupsId  : [],
	aLightsId  : [],
	
	/**
	 * Render the default "Selected User" table. Uses the selectedRows
	 * property and the column configuration from the main User table to
	 * retrieve the data to render. Table is rendered and assigned to the
	 * sensus.pages.smartpoint.actionDialogTable property.
	 * 
	 * @see sensus.util.datatable.getColumnSetup
	 * @see sensus.pages.user.tableColumns
	 * @see sensus.widgets.datatable.selectedRows
	 */
	renderDeleteMessage : function() {
		
		var nUsers = sensus.widgets.datatable.isAllRows ? sensus.widgets.datatable.deselectedRows : sensus.widgets.datatable.selectedRows;
		var sUserName = sensus.widgets.datatable.sSelText;
		
		if (nUsers.length == 1) {
			
			$('#selected-users').append(sensus.locale.get("user.validation.deleteUser", sUserName));
			
		} else {
		
			$('#selected-users').append(sensus.locale.get("user.validation.deleteUsers", $('.checked-count').text().split(' ')[0]));
		
		}
	}

 }
</script>