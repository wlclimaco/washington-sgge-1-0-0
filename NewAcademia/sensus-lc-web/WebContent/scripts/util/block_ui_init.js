
var oLoading = $('#loading');

oLoading.dialog({dialogClass: 'loading', resizable: false, closeOnEscape: false});
oLoading.dialog('open');

oLoading.data("bTable", true);

$('h5', oLoading).text("Loading...");

var oProgressbar = $("#progress-bar");

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

