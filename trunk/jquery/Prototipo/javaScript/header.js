$(function(){

    $('form.cep-form input[name="cep"]').mask('99999-999');

    $('#bt-login').click(function() {
        if ($(this).hasClass('login-home'))
            _gaq.push(['_trackEvent', 'Login', 'Link_Home']);
        else if ($(this).hasClass('login-product'))
            _gaq.push(['_trackEvent', 'Login', 'Link_Pagina_Produto']);
    });
    $('#bt-register').click(function() {
        if ($(this).hasClass('register-home'))
            _gaq.push(['_trackEvent', 'CadastreSe', 'Link_Home']);
        else if ($(this).hasClass('register-product'))
            _gaq.push(['_trackEvent', 'CadastreSe', 'Link_Pagina_Produto']);
    });
    $('#mc-header-logo a.logo').click(function() {
        _gaq.push(['_trackEvent', 'Clique_Logo', $(this).attr('class')]);
    });
    $('#mc-header-lists li.current-list a').click(function() {
        _gaq.push(['_trackEvent', 'Visualizar_Lista', 'Lista_Ativa', 'Footer']);
    });
    $('#mc-header-lists li.user-list a').click(function() {
        _gaq.push(['_trackEvent', 'Trocou', 'Lista', 'Header']);
    });
    $('#mc-header-config-cep-options form').submit(function() {
        _gaq.push(['_trackEvent', 'Trocou', 'Cep', 'Header']);
    });
    $('#mc-header-config-seller-options ul li.nowrap a').click(function() {
        _gaq.push(['_trackEvent', 'Trocou', 'Supermercado', 'Header']);
    });
    $('#mc-header-config-user-options a#user-account').click(function() {
        _gaq.push(['_trackEvent', 'Usuario', 'Minha_Conta']);
    });
    $('#mc-header-config-user-options a#user-logout').click(function() {
        _gaq.push(['_trackEvent', 'Usuario', 'Sair']);
    });

    $('#mc-header-config a.menu').click(function() {
        var $menu = $(this);
        $(this).parents('#mc-header-config').find('a.menu').each(function() {
            if ($menu.parent().attr('id') == $(this).parent().attr('id'))
                return true;

            $(this)
                .removeClass('selected')
                .parent().next('div')
                    .hide()
                    .removeClass('visible');
        });
        var $options = $menu.parent().next('div');
        if ($options.hasClass('visible')) {
                $menu.removeClass('selected');
                $options.hide();
        } else {
            $menu.addClass('selected');
            $options.show();
        }
        $options.toggleClass('visible');
 
        $options.position({
            of: $(this),
            my: 'left top',
            at: 'left bottom'
        });

        return false;
   });

})
