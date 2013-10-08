/* Style Switcher */
$(document).ready(function () {

    var styleswitcherstr = ' \
	<h2>Style Switcher <a href="#"></a></h2> \
    <div class="content"> \
    <div class="clear"></div> \
    <h3>Light Theme</h3> \
	<a href="../Facebuk-Dark/index.html" class="white-style">Dark Theme</a> \
    </div><!-- End content --> \
	';


    var wrap = $('<div/>');
    wrap.addClass('switcher');
    wrap.append(styleswitcherstr);
    $("#copyright").after(wrap);

});

/* boxed & wide syle */
$(document).ready(function () {

    var cookieName = 'wide';

    function changeLayout(layout) {
        $.cookie(cookieName, layout);
        $('head link[name=layout]').attr('href', 'css/layout/' + layout + '.css');
    }

    if ($.cookie(cookieName)) {
        changeLayout($.cookie(cookieName));
    }

    $("#wide").click(function () {
        $
        changeLayout('wide');
    });

    $("#boxed").click(function () {
        $
        changeLayout('boxed');
    });

});


/* background images */
$(document).ready(function () {

    var startClass = $.cookie('mycookie');
    $("body").addClass("wood");
    $('.pattern').click(function () {
        $('.pattern').each(function () {
            $('body').removeClass($(this).attr('id').replace('#', ''));
        });
        var bg = $(this).attr('id').replace('#', '');
        $('body').addClass(bg);
        $.cookie('mycookie', bg);
    });

    if ($.cookie('mycookie')) {
        $('body').addClass($.cookie('mycookie'));
    }

});

/* Skins Style */
$(document).ready(function () {

    var cookieName = 'colorCookie';

    function changeLayout(theme) {
        $.cookie(cookieName, theme);
        $('head link[name=theme]').attr('href', 'css/themes/' + theme + '.css');
    }

    if ($.cookie(cookieName)) {
        changeLayout($.cookie(cookieName));
    }

    $(".color").click(function () {
        changeLayout($(this).attr('id').replace('#',''));
    }); 


});


/* Reset Switcher */
$(document).ready(function () {

    // Style Switcher	
    $('.switcher').animate({
        left: '-160px'
    });

    $('.switcher h2 a').click(function (e) {
        e.preventDefault();
        var div = $('.switcher');
        console.log(div.css('left'));
        if (div.css('left') === '-160px') {
            $('.switcher').animate({
                left: '0px'
            });
        } else {
            $('.switcher').animate({
                left: '-160px'
            });
        }
    })


    /*$('a.color').click(function(e){
    e.preventDefault();
    $(this).parent().parent().find('a').removeClass('selected');
    $(this).addClass('selected');
    })

    $('a.pattern').click(function(e){
    e.preventDefault();
    $(this).parent().parent().find('a').removeClass('selected2');
    $(this).addClass('selected2');
    })

    $('a.layout').click(function(e){
    e.preventDefault();
    $(this).parent().parent().find('a').removeClass('selected3');
    $(this).addClass('selected3');
    })*/


});						   

