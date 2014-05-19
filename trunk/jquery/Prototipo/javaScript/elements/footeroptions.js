$(function(){

    $('ul#footeroptions input[name="email"]').waterMark()

    $('ul#footeroptions .form-email').submit(function() {
        if (!$(this).find('input[name="email"]').val())
            return false;
    });

});
