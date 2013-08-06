sensus.settings = $.extend({
		search : '${base}/${search}',
		maxExtent: '[${maxExtent1},${maxExtent2},${maxExtent3},${maxExtent4}]',
		menuItem: '${menuItem}',
		searchUrlLRP : '${base}/${searchUrlLRP}',
		saveProfileSettings: '${base}/${saveProfileSettings}',
		generateSummaryFileCSV : '${base}/${generateSummaryFileCSV}',
		deleteUser : '${base}/${deleteUser}',
		createUser : '${base}/${createUser}',
		updateUser : '${base}/${updateUser}',
		fillUserPage : '${base}/${fillUserPage}',
		fetchLightByUserToMap : '${base}/${fetchLightByUserToMap}',
		fillGroups : '${base}/${fillGroups}',
		fetchLightsByGroup : '${base}/${fetchLightsByGroup}' 
}, sensus.commons.lib.ajax.settings);