$(function() {

    $('#sidebar #sellers-available ul li.nowrap a').click(function() {
        _gaq.push(['_trackEvent', 'Trocou', 'Supermercado', 'Menu_Lateral']);
    });

    $('#sidebar a.more').click(function() {

        if ($(this).hasClass('expanded')) {
            $(this).parent().find('ul.menu').slideUp();
            $(this).find('span.more-icon').css('background-position-y', 5);
        } else {
            $(this).parent().find('ul.menu').slideDown();
            $(this).find('span.more-icon').css('background-position-y', -11);
        }
        $(this).toggleClass('expanded');

        return false;
    });

    $('#sidebar-banner-app > a.app-download-iphone').click(function() {
        _gaq.push(['_trackEvent', 'Download_Iphone', 'Banner pagina produto']);
    });
    $('#sidebar-banner-app > a.app-download-android').click(function() {
        _gaq.push(['_trackEvent', 'Download_Android', 'Banner pagina produto']);
    });

});
