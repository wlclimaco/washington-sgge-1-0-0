jQuery(document).ready(function(){
	jQuery(window).responsiveWeb({
		debug: true
	});
});

function manipulateDesign() { }

function rearrangeObjects() {
	if (jQuery('body').hasClass('w480') || jQuery('body').hasClass('android')) {
		$('#mainTabs li:last-child').hide();
		$('#main .baseLayout .nav').hide();
		$('#insideNYTimes').hide();
		imgresize($('#ledePhoto .image a img'),'180');
		$('.wideA .bColumn #pocketRegion').appendTo('.wideB .aColumn.opening');
	}
	else {
		$('#mainTabs li:last-child').show();
		$('#main .baseLayout .nav').show();
		$('#insideNYTimes').show();
		imgresize($('#ledePhoto .image a img'),$('#ledePhoto .image a img').attr('width'));
		$('.wideA .bColumn #pocketRegion').appendTo('.wideA .bColumn > .columnGroup');
	}
}

function imgresize(obj,max_size) {
	$(obj).each(function(i) {
	  if ($(this).height() > $(this).width()) {
		var h = max_size;
		var w = Math.ceil($(this).width() / $(this).height() * max_size);
	  } else {
		var w = max_size;
		var h = Math.ceil($(this).height() / $(this).width() * max_size);
	  }
	  $(this).css({ height: h, width: w });
	});
}