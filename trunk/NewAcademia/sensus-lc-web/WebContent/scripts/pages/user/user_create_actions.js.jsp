<script type="text/javascript">
/**
 * @namespace sensus.pages.user
 * @description The action namespace for the User Page.
 * @fileoverview Defines the various action dialog options and common behaviors for the group-related actions.
 * @author Vinicius Scalon Ferreira
 */

sensus.pages.user.dialogSettings = {

    /*
	 * Role Definitions
	 */
	roleDefinitions : {
		title                : $.sc.locale("usercreate.page.titleRoleDefinition"),
		width                : 800,
		requiresSmartpoints  : false,
		action               : function(actionDialog)
		{
			$('#action-dialog').empty()
							   .html('<div class="sui-pad1">' + $.sc.locale("usercreate.page.roleDefinition") + '</div>')
							   .dialog('open');
		}
	}
};
</script>