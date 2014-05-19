$(function() {

    $('.paginator a').click(function() {
        $(this).attr('href', $(this).attr('href') + '#tabs-comments');
    });

});
