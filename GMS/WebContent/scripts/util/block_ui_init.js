$("#loading").show().dialog({
	modal        : true,
	autoOpen     : false,
	resizable    : false,
	dialogClass  : 'loading'
});

var oLoading      = $('#loading'),
	oProgressbar  = $("#progress-bar");

if (oLoading.data("bTable") == undefined) {

	oLoading.data("bTable", true);

	$('h5', oLoading).text("Loading...");

	if (!oLoading.dialog('isOpen')) {

		oLoading.dialog('open');

		oProgressbar.progressbar( {
			value : 1
		});

		(function progress() {

			var value = oProgressbar.progressbar('value');

			oProgressbar.progressbar('value', value + 1);

			if (value < 100) {

				setTimeout(progress, 200);

				if (value == 99) {

					oProgressbar.progressbar( {
						value : 1
					});

				}

			}

		}());
	}
}
