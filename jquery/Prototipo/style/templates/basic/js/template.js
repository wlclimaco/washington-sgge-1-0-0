$(function(){
/*
    $("#registerform_news").submit(function() {
        var email = $("#input_email").val();
        if (!email) {
            alert('Favor informar seu endereço de e-mail para prosseguir!');
            return false;
        }
        var er = new RegExp(/^[A-Za-z0-9_\-\.]+@[A-Za-z0-9_\-\.]{2,}\.[A-Za-z0-9]{2,}(\.[A-Za-z0-9])?/);
        if (!er.test(email)) {
            alert('Favor informar um endereço de e-mail válido para prosseguir!');
            return false;
        }
       
        var data = $("#registerform_news").serialize();
        $.ajax({
            type: "POST",
            url: "index.php?option=com_meucarrinho&no_html=1",
            data: data,
            success: function(msg){
                $("#registerform_news").hide();
                $("#registerform_status").html(msg);
            }
        });
        return false;
    });


    $('#menu_cat > div > ul > li > ul > li > a').click(function(){
        return false;
    });
    $('#menu_cat > div > ul > li > a').click(function(){
        return false;
    });

    if(!$('#menu_cat > div > ul > li').hasClass('active'))
        $('#menu_subcat').hide();

    $('#menu_cat > div > ul > li > a').hover(function(){
        $('#menu_subcat').html($(this).parent().children('ul').clone(true));
        $('#menu_subcat').show();
        $('#menu_cat > div > ul > li.active > a').addClass('notselected');
        $('#menu_cat .selected').removeClass('selected');
        $(this).addClass('selected');
    });
    $('#categorias').mouseleave(function(){
        $('#menu_subcat').html($('#menu_cat > div > ul > li.active > ul').clone(true));
        $('#menu_cat .selected').removeClass('selected');
        $('#menu_cat > div > ul > li.active > a').removeClass('notselected');

        if(!$('#menu_cat > div > ul > li').hasClass('active'))
            $('#menu_subcat').hide();

    }).trigger('mouseleave');
    $('input[type="submit"]').addClass('ui-state-hover');
    alert('oi');

*/

});
