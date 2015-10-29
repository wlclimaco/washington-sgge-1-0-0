$(document).ready(function(){
	$(window).responsiveWeb({
		debug: true
	});
});

function manipulateDesign() {
	$("img.withcaption").each(function(){
		var caption = $(this).attr('title');
		$(this).wrap('<div class="imgwithcaption"></div>');
		$(this).after('<span class="thecaption"></span>');
		$(this).next('span.thecaption').text('"' + caption + '"');
	});
}

function rearrangeObjects() {
	if (jQuery('body').hasClass('w1024') || jQuery('body').hasClass('w1280')) {
		$('#MainMenu').appendTo('#Column1');
		$('#MainMenu > ul').addClass('sf-menu sf-vertical');
		$('#ServiceMenu').prependTo('#Header');
		$('#Column1').prependTo('#ContentWrapper');
		$('#Columns, #Navigation').hide();
	}
	else if (jQuery('body').hasClass('w1440')) {
		$('#MainMenu').appendTo('#Column1');
		$('#MainMenu > ul').addClass('sf-menu sf-vertical');
		$('#ServiceMenu').appendTo('#Column1');
		$('#Column1').prependTo('#ContentWrapper');
		$('#Column2').appendTo('#ContentWrapper');
		$('#Columns, #Navigation').hide();	
	}
	else {
		$('#MainMenu').appendTo('#Navigation');
		$('#ServiceMenu').appendTo('#Navigation');
		$('#Column1').prependTo('#Columns');
		$('#Column2').prependTo('#Columns');
		$('#Columns, #Navigation').show();
		$('#MainMenu > ul').removeClass('sf-menu sf-vertical');
	}
}