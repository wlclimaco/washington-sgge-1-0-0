$(document).ready(function ()
{
	var id = $("div[id*='tabs']" ).attr("id");
	var qtabs = $('#'+ id).tabs({
		ajaxOptions: {
			error: function( xhr, status, index, anchor ) {
				$( anchor.hash ).html(wdtabload.error.msg);
			}
		}
	});

	// when the tab is selected update the address value
	$('#'+ id).bind("tabsactivate", function(event, ui){
		$.address.value('wdtree?id=' + id + '&tab=' + $('#'+ id).tabs( "option", "active" ));
	});

	$('#'+id).bind( "tabsload", function(event, ui) {
		tabval = ($.address.parameter('tab'));
		if (ui.index != tabval)
		{
			$('#'+ id).tabs( "option", "active", parseInt(tabval));
		}
	});

	var centerSize = wdLayout.state.center.innerHeight - 60;
	var tabCtrlHeight = $("ul.ui-tabs-nav").outerHeight();
	var tabHeight = (centerSize - tabCtrlHeight - 20);
	$('#'+ id).equalHeights(centerSize);
	$('#'+ id).find(".ui-tabs-panel").height(tabHeight);
});