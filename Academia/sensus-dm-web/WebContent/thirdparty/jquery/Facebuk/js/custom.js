
$(window).load(function () {
    //Text + Image Slider
    $('.slider-text-image').flexslider({
        animation: "fade",
        slideshowSpeed: 4000,
        animationDuration: 600,
        controlNav: true,
        keyboardNav: true,
        directionNav: false,
        pauseOnHover: true,
        pauseOnAction: true

    });

    //testimonial slider
    $('.slider-testimonial').flexslider({
        animation: "fade",
        slideshowSpeed: 7000,
        animationDuration: 200,
        controlNav: false,
        keyboardNav: true,
        directionNav: false,
        pauseOnHover: true,
        pauseOnAction: true
    });
});



// Progress Bar
$(function () {
    $(".meter > span").each(function () {
        $(this)
	.data("origWidth", $(this).width())
	.width(0)
	.animate({
	    width: $(this).data("origWidth")
	}, 1200);
    });
});


/* ---------------------------------------------------------------------- */
/*	Portfolio
/* ---------------------------------------------------------------------- */

// Needed variables
var $container = $('#portfolio-list');
var $filter = $('#portfolio-filter');

// Run Isotope  
$container.isotope({
    filter: '*',
    layoutMode: 'fitRows',
    animationEngine: 'jQuery',
    animationOptions: {
        duration: 750,
        easing: 'linear'
    }
});

// Isotope Filter 
$filter.find('a').click(function () {
    var selector = $(this).attr('data-filter');
    $container.isotope({
        filter: selector,
        animationOptions: {
            duration: 750,
            easing: 'linear',
            queue: false
        }
    });
    return false;
});

// Portfolio image animation 
$container.find('img').adipoli({
    'startEffect': 'transparent',
    'hoverEffect': 'boxRandom',
    'imageOpacity': 0.6,
    'animSpeed': 100
});

// Copy categories to item classes
$filter.find('a').click(function () {
    var currentOption = $(this).attr('data-filter');
    $filter.find('a').removeClass('current');
    $(this).addClass('current');
});

/* ---------------------------------------------------------------------- */
/*	Fancybox 
/* ---------------------------------------------------------------------- */
$container.find('.folio').fancybox({
    'transitionIn': 'elastic',
    'transitionOut': 'elastic',
    'speedIn': 200,
    'speedOut': 200,
    'overlayOpacity': 0.6
});

/* ---------------------------------------------------------------------- */
/*	Accordion
/* ---------------------------------------------------------------------- */


    
  var allPanels = $('.accordion > dd').hide();
    
  $('.accordion > dt > a').click(function() {
      $this = $(this);
      $target =  $this.parent().next();

      if(!$target.hasClass('active')){
         allPanels.removeClass('active').slideUp();
         $target.addClass('active').slideDown();
      }
      
    return false;
  });

