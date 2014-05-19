$(function() {

    $('#home-bt-register').click(function() {
        _gaq.push(['_trackEvent', 'Lista', 'Criar_Lista_BotaoHome']);
        if ($(this).parent('#homedest').hasClass('homedest-compare') || $(this).parent('#homedest').hasClass('homedest-compare-arrow')) {
            $(this).attr('href', $(this).attr('href').replace('t=list', 't=compare'));
        }
    });
    $('#download-app-iphone').click(function() {
        _gaq.push(['_trackEvent', 'Download_Iphone', 'Home']);
    });
    $('#download-app-android').click(function() {
        _gaq.push(['_trackEvent', 'Download_Android', 'Home']);
    });
    $('#namidia ul li a').click(function() {
        _gaq.push(['_trackEvent', 'Na_Midia', $(this).attr('title')]);
    });
    $('#namidia-more').click(function() {
        gaq.push(['_trackEvent', 'Na_Midia', 'Mais_Reportagens']);
    });

    setInterval(function() {
        var left = parseInt($('#home-screenshots').css('left'));
        left -= 215;

        if (left < -859) {
            $('#home-screenshots').css('left', 0); 
            left = -215;
        }

        $('#home-screenshots').animate({
            left: left
        }, 'fast');

    }, 5000); 
});
