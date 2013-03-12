$.ajax({
	dataType    : 'json',
	type        : "POST",
	url         : 'api/settings/fetchproperties',
	async       : false,
	success     : function(oData) {

		sensus = oData;

	}

});