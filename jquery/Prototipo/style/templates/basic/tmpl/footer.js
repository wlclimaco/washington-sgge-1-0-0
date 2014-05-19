$(function() {

    $('#social-facebook').click(function() {
        _gaq.push(['_trackSocial', 'Facebook', 'Seguir']);
    });
    $('#social-twitter').click(function() {
        _gaq.push(['_trackSocial', 'Twitter', 'Seguir']);
    });
    $('#social-feed').click(function() {
        _gaq.push(['_trackSocial', 'RSS', 'Seguir']);
    });
    $('.menu-main a').click(function() {
        _gaq.push(['_trackEvent', 'Main_Menu', $(this).find('span').html()]);
    });
    $('#company a').click(function() {
        _gaq.push(['_trackEvent', 'Company_Links', $(this).html()])
    });
});
