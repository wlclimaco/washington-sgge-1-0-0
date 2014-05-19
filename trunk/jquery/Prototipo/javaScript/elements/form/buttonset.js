$(function(){
    $('.form-buttonset')
        .buttonset();
    setTimeout(function(){
        $('.form-buttonset-fixed')
            .animate({ bottom: 0 });
    }, 2000);
});
