$(function() {

    $('h1').remove();
    $('#col-left').prepend('<h1>' + $('#title').val() + '</h1>');

    $('.user-form form input[name="cep"]').mask('99999-999');

    $('.user-form form a.reset').click(function() {
        
    });
    $('#form-bt-login').click(function() {
        _gaq.push(['_trackEvent', 'Login', 'Botao_Pagina_Login']);
    });
    $('#form-bt-register').click(function() {
        _gaq.push(['_trackEvent', 'CadastreSe', 'Botao_Pagina_Cadastro']);
    });

    $('.user-form form').submit(function() {
        var msg = null;
        $(this).find('input[type="text"]').each(function() {
            if (!$(this).val()) {
                msg = '<div class="form-error-message"><p>Você precisa preencher todos os campos para prosseguir.</p></div>';
            }
                
        });
        if (msg) {
            $msg = $(msg);
            $('.form-error-message').remove();
            $('.user-form form').after($msg);
            $('.form-error-message').hide().fadeIn();
            return false;
        }
    });

    $('a#link-register').click(function() {
        _gaq.push(['_trackEvent', 'CadastreSe', 'Link_Pagina_Login']);
        $('#form-login').fadeOut('fast', function() {
            $('#form-register').fadeIn();
            $('.form-error-message').remove();
        });
        return false;
    });

    $('a#link-login').click(function() {
        _gaq.push(['_trackEvent', 'Login', 'Link_Pagina_Cadastro']);
        $('#form-register').fadeOut('fast', function() {
            $('#form-login').fadeIn();
            $('.form-error-message').remove();
        });
        return false;
    });

});
