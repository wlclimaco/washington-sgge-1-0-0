$(function() {

    $('ul#categories li a').click(function() {
        _gaq.push(['_trackEvent', 'Categoria_Header', $(this).find('span').html()]);
    });

});
