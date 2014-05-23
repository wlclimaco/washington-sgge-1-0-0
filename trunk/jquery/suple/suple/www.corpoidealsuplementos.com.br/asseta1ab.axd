(function ($, window, document, undefined) {

	$.widget('wd.ProfileLogin', $.wd.widget, {
		
		_base: '',
		_create: function () {

			var widget = this,
				$w = this.getContext(),
				cb =  function(){ window.location.reload()};

			widget.subscribe('/login/showlogin', function (e, args) {
				if (args.hasOwnProperty('cb')) {
					cb = args.cb;
				}
				widget._showModalLogin();

			});

			widget.validate($w('form.js-login').first(), {
				errorPlacement: widget.options.validate.errorFlyout,
				rules: {
					'Login.Email': {
						required: true,
						email: true
					},
					'Login.Password': {
						required: true
					}
				},
				messages: {
					'Login.Email': {
						email: 'Informe um e-mail válido.'
					}
				},
				submitHandler: function (form) {
					widget._submit(form, cb);
				}
			});

			$w('.js-login-recoverpassword').click(function (e) {
				e.preventDefault();
				widget.modal({
					element: '.wd-profile-login-recover-password:first',
					'class': 'wd-profile-login',
					onComplete: function ($modal) {
						var $form = $('form', $modal);
						//console.log($form);
						$w('.js-result').empty();
						widget.validate($form, {
							rules: {
								Email: { required: true, email: true }
							},
							submitHandler: function (form) {
								widget._submit(form);
							}
						});
					}
				});

			});
			widget._base = $w('input[name="widget-base-url"]').val();
			//console.log(this);
			$w('.profile-login-modalText').bind('click', function () {
				widget._showModalLogin();
			});

		},

		_showModalLogin: function () {

			var widget = this, $w = this.getContext();
			if( $w('.profile-login-modal').length){
				widget.modal({
					element: $w('.profile-login-modal'),
					'class': 'wd-profile-login'
				});
			}
			widget.validate($('.profile-login-modal.modal form'), {
				errorPlacement: widget.options.validate.errorFlyout,
				rules: {
					'Login.Email': {
						required: true,
						email: true
					},
					'Login.Password': {
						required: true
					}
				},
				messages: {
					'Login.Email': {
						email: 'Informe um e-mail válido.'
					}
				}
			});
			$('.profile-login-modal.modal .js-login-recoverpassword').click(function (e) {
				e.preventDefault();
				widget.modal({
					element: '.wd-profile-login-recover-password',
					onComplete: function ($modal) {
						var $form = $('form', $modal);
						$w('.js-result').empty();
						widget.validate($form, {
							rules: {
								Email: { required: true, email: true }
							},
							submitHandler: function (form) {
								widget._submit(form);
							}

						});
					}
				});

			});
			$('.profile-login-modal.modal form').bind('submit', function () {
				var $form = $(this);

				if ($form.data('locked') == true)
					return false;

				$form.data('locked', true);
				$('.login-loading', $form).addClass('show');
				$.ajax({
					url: widget._base + 'Login.json',
					type: 'post',
					data: $(':input', $form).serialize(),
					success: function (response) {
						if (response.Response.IsValid) {
							$.publish('/login/success', {});
							$.colorbox.close();
						}
						else {
							$.publish('/login/error', {});
							$('.login-error-placeholder', $form).text(response.Response.Errors[0].ErrorMessage);
						}
					},
					error: function () { },
					complete: function () {
						$('.login-loading', $form).removeClass('show');
						$form.data('locked', false);
					}
				});

				return false;
			});

		},

		_submit: function (form, cb) {
			var widget = this,
				$form = $(form),
				$button = $('button[type="submit"]', $form);
				$load = $form.find('.load');

			$load.css('display','inline-block');
			$button.attr('disabled', true);

			//cb = cb || function(){};
			//console.log($form.serialize());
			widget.ajax({
				url: $form.attr('action'),
				type: $form.attr('method'),
				data: $form.serialize(),
				dataType: 'json',
				success: function (data) {
					$button.removeAttr('disabled');
					if (data.Response !== undefined)
						data = data.Response;
					if (data.IsValid) {
						if (cb){
							cb();
							$.colorbox.close();
						}
						else{
							widget._handleSuccess(data);
						}
					}
					else{
						widget._handleError(data);
						$load.hide();
					}
				},
				error: function (e) {
					$button.removeAttr('disabled');
					$load.hide();
					widget.alert('Ocorreu um erro, contate o suporte', 'error', {
						'class': 'wd-profile-login-recover-password-response'
					});
				}
			});
		},
		_handleSuccess: function (response) {
			var widget = this, $w = this.getContext();
			widget.alert('O e-mail com as instruções para redefinir a senha foi enviado com sucesso!', 'success', {
				'class': 'wd-profile-login-recover-password-response'
			});
		},
		_handleError: function (response) {
			$.publish('/profile/login/failed', {});
			var inputErrors = response.InputErrors,
				widget = this, $w = this.getContext(),
				$err = $('<div class="alert alert-error"><div class="message"></div></div>');
			if (inputErrors) {
				for (var i = 0, len = inputErrors.length; i < len; i++) {

					var errors = inputErrors[i].Errors;

					for (var k = 0, errorsLen = errors.length; k < errorsLen; k++) {
						$err.append('<div class="input-wrapper"><span>' + errors[k] + '</span></div>');
					}
				}
			} else if ( response.hasOwnProperty('Errors') && response.Errors.length > 0 ) {
				var errors = response.Errors;
				console.log(errors);
				for (var i = 0, len = errors.length; i < len; i++) {
					$err.append('<div class="input-wrapper"><span>' + errors[i].ErrorMessage + '</span></div>');
				}
			}
			//$.colorbox.close();
			widget.modal({
				element: $err,
				'class': 'wd-profile-login-recover-password-response'
			});


		}
	});

})(jQuery, window, document);

; (function($, window, document, undefined) {
	
	$.widget('wd.ProfileLoginOauth',  $.wd.widget, {
		
		// Create
	_create: function () {
			var $widget = this,
				$w = $widget.getContext(),
				$id = this.element,
				_window = {};
			
			$w('a.login-link').bind('click', function(e){ e.preventDefault(); });
			// Facebook
			$(document).on('click', '.loginFacebook', function (e) {
				_window = window.open( $w('input[name="widget-base-url"]').val() + 'Oauth/Facebook', 'Facebook_Login', 'width=950,height=550,scrollbars=yes,resizable=1');
			});
			// Twitter
			$(document).on('click', '.loginTwitter', function (e) {
				_window = window.open( $w('input[name="widget-base-url"]').val() + 'Oauth/Twitter', 'Twitter_Login', 'width=800,height=400,scrollbars=yes,resizable=1');
			});
			
			$widget.subscribe('/OAuth/Provider/Success', function(e, result) {
				var obj = $.parseJSON(result);
				//var obj = JSON.parse(result);
				if(obj.IsUser)
				{
					_window.close();
					var urlCallbackUser = $w('input[name="url-current"]').val();
					window.location.href = urlCallbackUser;
					return;
				}

				if(!obj.IsValid) {

					if ( obj.ModelName === "Twitter") {
						//alert(obj.Error);
					}
					else {
						$("#iframe-login-facebook").remove();
						$("#iframe-facebook-content").html("<div style='float:left; margin:auto; color:red; font-size:12px; text-align:center; margin-top:40px;'>" + obj.Error);
					}
					_window.close();
				}
				else {
					
					_window.close();
					var urlCallback = $w('input[name="url-callback"]').val();
					window.location.href = urlCallback + "?provider=" + obj.ModelName;
				}
			});
		}
	});

	//options defaults
	$.extend($.wd.ProfileLoginOauth.prototype.options, {
		
	});
})(jQuery, window, document);





		
	  
; (function ($, window, document, undefined) {

    $.widget('wd.ProfileWelcomeShopper', $.wd.widget, {

        _create: function () {
            var widget = this,
                $w = widget.getContext(),
                currentUrl = $w('[name="widget-current-url"]').val(),
                href = $w('.sign-out').attr('href');

            //widget.messages = JSON.parse($w().attr('data-messages'));
            widget.messages = $.parseJSON ($w().attr('data-messages'));
            widget.currentUrl = currentUrl;
            if (href) {
                $w('.sign-out').attr('href', href.replace('{CurrentUrl}', currentUrl));
            }
            
            $w = this.getContext(),
            cs = $w('input[name="widget-cache-strategy"]').val();
            if (cs != 'Disabled') {
                widget.reload();
            }

            $.subscribe('/login/success', function(){
                //console.log('pub login/success');
                widget.reload();
            });

            var hoverTimer;

            $w().on({
                mouseenter: function(){
                    clearTimeout(hoverTimer);
                    if (!$w('.login-wrapper').hasClass('hover')) {
                        hoverTimer = setTimeout(function(){ $w('.login-wrapper').addClass('hover'); }, parseInt($w().attr('data-hovertimer')));
                    }
                },
                mouseleave: function(){
                    clearTimeout(hoverTimer);
                    if ($w('.login-wrapper').hasClass('hover')) {
                        hoverTimer = setTimeout(function(){ $w('.login-wrapper').removeClass('hover'); }, parseInt($w().attr('data-hovertimer')));
                    }
                }
            }, '.login-wrapper');

        },

        reload: function () {
            var widget = this,
            $w = this.getContext(),
            o = this.options;
            b = $w('input[name="widget-base-url"]').val();
           
            widget.ajax({
                url: b + 'widget/browsing_welcome_shopper',
                type: 'get',
                data: $.extend({ Template: o.Template }, widget.messages),
                dataType: 'html',
                cache: false,
                success: function (response) {
                    $w('.widget-content').html(response);

                    var href = $w('.sign-out').attr('href') || '';

                    $w('.sign-out').attr('href', href.replace('{CurrentUrl}', widget.currentUrl));
                }
            });
        }

    });

   // Defaults
   $.extend($.wd.ProfileWelcomeShopper.prototype.options, {
        Template: 'welcome.shopper.data.template'/*,
        TextLoading: 'carregando...'*/
   });
   

}) (jQuery, window, document);








//
//
//
; (function ($, window, document, undefined) {

	$.widget('wd.CheckoutBasketSummaryHeader', $.wd.widget, {

		_create: function () {
			var widget = this, 
				$w = this.getContext(),
				cache = $w('input[name="widget-cache-strategy"]').val(),
				timer = $w('input[name="widget-reload-timer"]').val();
			
			/* funciona apenas dentro do basket */
			widget.unsubscribe('/checkout/basket/changed');
			widget.subscribe('/checkout/basket/changed', function () {
				widget.reload();
			});
			/* está quebrando a sessão
			setInterval(function(){ widget.reload() },3000);
			*/

			$w('.go-to-basket, .go-to-checkout').live('click', function(){
				location.href = $(this).attr('data-href');
			});

			if (cache != 'Disabled') {
				setTimeout(function(){ widget.reload(); }, 100); //primeira atualização/ pra limpar o cache
				setInterval(function(){ widget.reload(); }, timer || 20*60*1000); //caso expire a seção (20 minutos)
			} else {
				if ( $w('input[name="basketSize"]').val() == 0 )
					$w('.go-to-checkout').hide();
			}

		},

		reload: function () {
			var widget = this,
				$w = this.getContext(),
				o = this.options,
				b = $w('input[name="widget-base-url"]').val() || '/';

			$.ajax({
				url: b + 'widget/browsing_basket_summary',
				type: 'get',
				data: {
					nocache: Math.floor(Math.random()*11000000000),
					Template: o.Template,
					TextLoading: o.TextLoading
				},
				dataType: 'html',
				cache: false,
				success: function (response) {				
					$w('.basket-size').html($('.basket-size', response).html());
					$w('.subtotal .value').first().html($('.subtotal .value', response).first().html());
					$w('.wd-content').html($('.wd-content', response).html());
					$w('.basket-size-wrapper').show();

					if ( $('input[name="basketSize"]', response).val() == 0 )
						$w('.go-to-checkout').hide();
				}
			});
		}

	});

	// Defaults
	$.extend($.wd.CheckoutBasketSummaryHeader.prototype.options, { 
		Template: 'wd.checkout.basket.summaryheader.template',
		TextLoading: 'carregando...'
	});

}) (jQuery, window, document);
; (function ($, window, document, undefined) {
	var toFixed = function(num,dec) {
		var $result = (Math.round(num*Math.pow(10,dec))/Math.pow(10,dec)),
			$arr = ($result+[]).split('.'),
			$int = $arr[0] + '.',
			$dec = $arr[1] || '0';
		return ($int + $dec + (Math.pow(10,(dec - $dec.length))+[]).substr(1));
	};

	$.widget('wd.ProductSearch', $.wd.widget, {
		_create: function(){
			var $widget = this,
				$w = $widget.getContext(),
				$input = $w('.search-field'),
				$suggestions = {
					view: $w('.suggestion-box'),
					template: $w('.suggestion-template').text()
				};

			// no ie pegar conteudo de ums script atraves do .text() da erro, temos que usar .html()
			//var $varjson =$.parseJSON( $w('script[type=jsoptions]').text());
				var $varjson =$.parseJSON( $w('script[type=jsoptions]').html());
			//alert($varjson);

			$widget.options = $varjson;

			$input
				.focus(function(){
					$suggestions.view.removeClass('hide');
				})
				.blur(function(){
					$suggestions.view.addClass('hide');
				})
				.keydown(function(e){
					var $key = e.which,
						$arrowKeys = {
							38:1,	//up arrow
							40:1	//down arrow
						};

					if ($arrowKeys[$key]) {
						e.preventDefault();
						$widget.navigateSuggestions({38:-1, 40:1}[$key]);
					}
					/*else if ($key === 13) {
						var $val = $(this).val(),
							$mask = /\(produto:\/url(-[a-zA-z0-9]+)+\)/,
							$pmask = /\/url(-[a-zA-z0-9]+)+/,
							$match = $val.match($mask);
						if ($match.length) {
							e.preventDefault();
							window.location.href = $match[0].match($pmask)[0];
						}
					}*/
				})
				.keyup(function(e){
					var $key = e.which,
						$serviceKeys = {
							9:1,	//tab
							13:1,	//enter
							16:1,	//shift
							17:1,	//ctrl
							18:1,	//alt
							19:1,	//pause/break
							20:1,	//caps lock
							27:1,	//escape
							33:1,	//page up
							34:1,	//page down
							35:1,	//end
							36:1,	//home
							37:1,	//left arrow
							38:1,	//up arrow
							39:1,	//right arrow
							40:1,	//down arrow
							45:1,	//insert
							91:1,	//left window key
							92:1,	//right window key
							112:1,	//f1
							113:1,	//f2
							114:1,	//f3
							115:1,	//f4
							116:1,	//f5
							117:1,	//f6
							118:1,	//f7
							119:1,	//f8
							120:1,	//f9
							121:1,	//f10
							122:1,	//f11
							123:1,	//f12
							144:1,	//num lock
							145:1	//scroll lock
						};

					if (!$serviceKeys[$key])
						$widget.doSuggestions({
							widget: $widget,
							delay: $widget.keystrokeDelay,
							e: e,
							key: $key,
							text: $(this).val(),
							suggestions: $suggestions
						});
				});


		},

		keystrokeDelay: 200, timer: null, ajaxHandler: { abort:function(){} },

		doSuggestions: function(options) {
			var $widget = options.widget,
				$suggestions = options.suggestions,
				$text = options.text,
				$delay = options.delay,
				$w = $widget.getContext(),
				$input = $w('.search-field'),
				$o = $widget.options.suggestions,
				b = $w('input[name="widget-base-url"]').val();

			//cancela o ultimo pedido
			clearTimeout($widget.timer);
			$widget.ajaxHandler.abort();

			//cria um pedido novo
			if (!$text.length)
				$suggestions.view.html('');
			else
				$widget.timer = setTimeout(function(){

					$widget.ajaxHandler = $.ajax({
						url: b+'sugestao.json',
						type: 'GET',
						data: {
							t: $text
						},
						success: function(response){
							var $scope = {
									products: response.Model.Grid.Products,
									correction: response.Model.Grid.Spell.Collation,
									terms: (response.Model.Grid.Terms.length ? response.Model.Grid.Terms[0].Options: [])
								};

							$scope.products.length = $o.productsLimit || $scope.products.length;
							$scope.terms.length = $o.termsLimit || $scope.terms.length;

							$scope.showProducts = ($o.showProducts ? Boolean($scope.products.length) : false);
							$scope.showCorrections = ($o.showCorrections ? Boolean($scope.correction.length) : false);
							$scope.showTerms = ($o.showTerms ? Boolean($scope.terms.length) : false);

							$scope.hasData = ($scope.showProducts || $scope.showCorrections || $scope.showTerms);

							if ($o.bannerTop.src.length) {
								$scope.bannerTop = $o.bannerTop;
								$scope.bannerTop.target = ($scope.bannerTop.targetBlank ? 'blank' : 'none');
							}
							if ($o.bannerBottom.src.length) {
								$scope.bannerBottom = $o.bannerBottom;
								$scope.bannerBottom.target = ($scope.bannerTop.targetBlank ? 'blank' : 'none');
							}

							(function(){
								for (var k=0;k<$scope.terms.length;k++){
									var $last_term = $text.split(' ').reverse()[0];

									$scope.terms[k].TermHighlight = {
										highlight: $last_term,
										suffix: $scope.terms[k].Term.replace($last_term.toLowerCase(),'')
									};
								}
							})();

							(function(){
								for (var k=0;k<$scope.products.length;k++){
									$scope.products[k].Price = toFixed($scope.products[k].RetailPrice, 2).replace('.',',');
								}
							})();

							var $html = Mustache.render($suggestions.template, $scope);

							$suggestions.view.html($html);

							$('.suggestion-correction', $suggestions.view).click(function(e){
								var $t = $(this).attr('data-val');
								$input.val($t);
								$widget.doSuggestions({
									widget: $widget,
									delay: 1,
									e: e,
									key: null,
									text: $t,
									suggestions: $suggestions
								});
							});
							$('.suggestion-item', $suggestions.view).click(function(){
								window.location.href = '/pesquisa/' + $(this).attr('data-term');
							});
							$('.suggestion-product', $suggestions.view).click(function(){
								window.location.href = $(this).attr('data-url');
							});
							$('.target-blank', $suggestions.view).click(function(){
								window.open($(this).attr('href'));
							});

						}
					});

				}, $delay);
		},
		nav: {
			index: -1
		},
		navigateSuggestions: function(key){
			var $widget = this,
				$w = this.getContext(),
				$suggestions = $w('.suggestion-box'),
				$map =  $('.nav-indexable', $suggestions),
				$current = $map.filter('.nav-current');

			$widget.nav.index += key;

			if ($map.length === $widget.nav.index)
				$widget.nav.index = 0;
			else if ($widget.nav.index < 0)
				$widget.nav.index = $map.length - 1;
			//console.log('navigating', key, $next, $map.length, $current.index($map));
			$current.removeClass('nav-current');
			$current = $map.eq($widget.nav.index).addClass('nav-current');
			$w('.search-field').val($current.attr('data-indexable'));
		}
	});

	$.extend($.wd.ProductSearch.prototype.options, {
		
	});

})( jQuery, window, document );
//
// Widget de categorias
//

$.widget('wd.CategoryMenu', $.wd.widget, {
    _init: function () {
        var self = this;
        $("#shadow").click(function () {
            self.modal({ content : "mengaaaa", title: "meu titulo custom" });
        });
    }
});


// Defaults
$.extend($.wd.CategoryMenu.prototype.options, {
    name: 2
});



; (function($, window, document, undefined) {

    $.widget('wd.MarketingBanner', $.wd.widget, {
        _create: function() {
            var widget = this,
                o = this.options,
                $w = this.getContext();

            if ($w(".js-positionID").length)
                widget.reload();

            if ($w().hasClass('slider') && ($w('.banner-wrapper').length > 1)){
                //widget.bgPreLoader();
                widget.slider();
            }

            widget.datasource(this.options.datasource);

        },
        datasource: function(ds) {
            var widget = this;
            widget.options.datasource = ds;

            $('.banner-swf', widget.element).each(function() {
                var container = $(this);

                var bID = container.attr('id').replace('banner-swf-', ''); //Remove prefixo
                var b = $.grep(widget.options.datasource.Banners, function(n, i) { return n.BannerID === parseInt(bID); })[0];

                var options = {
                    quality: 'high', wmode: 'transparent', scale: 'noscale', allowDomain: '*',
                    allowScriptAccess: true, allowFullScreen: true, menu: false,
                    swf: b.BannerPath, movie: b.BannerPath, width: b.Width, height: b.Height
                };

                if (b.BackgroundHexa !== null && b.BackgroundHexa !== '')
                    options.bgcolor = '#' + b.BackgroundHexa;

                container.fadeOut('fast', 'swing', function() {
                    container.flash(options);
                    container.css('background', 'none').fadeIn('slow', 'swing');
                });
            });

        },
        reload: function() {
            var widget = this,
                $w = this.getContext(),
                o = this.options,
                b = $w('input[name="widget-base-url"]').val();

            $.ajax({
                    url: b+'Widget/marketing_banner',
                    type: 'get',
                    data: {
                        PositionId: $w(".js-positionID").val(),
                        ContextId: $w(".js-contextID").val(),
                        Context: $w(".js-context").val(),
                        IsCacheable: 'True'
                    },
                    dataType: 'html',
                    cache: false,
                    success: function(response) {
                        var x = response;
                        
                        if ('sliderTimer' in o)
                            clearInterval(o.sliderTimer);
                        
                        // $w('.basket-size').html($('.basket-size',x).html());
                        $w().html($(x).html());
                        
                        if ($w().hasClass('slider') && ($w('.banner-wrapper').length > 1))
                            widget.slider();
                        //$w('.basket-size-wrapper').show();
                    }
                });
        },
        bgPreLoader : function(){
            var widget = this,
                o = this.options,
                $w = this.getContext();
            $w(".banner-wrapper").each(function(index) {
               var img = ($(this).css('background-image'));
                if(img != 'none'){
                    var image = new Image();
                    img = img.replace('url("','').replace('")','');
                    image.src=img;
                    //console.log('img',img);
                    //console.log('image',image);
                }

           });

        },
        slider: function() {
            var widget = this,
                o = this.options,
                $w = this.getContext();

            (function(){
                //atualiza as options

               // var $json = JSON.parse($w().attr('data-slider')) || {};
                var $json = $.parseJSON($w().attr('data-slider')) || {};
                if ($json.sliderDelay)
                    o.sliderDelay = $json.sliderDelay;
                if ($json.sliderFadeDelay)
                    o.sliderFadeDelay = $json.sliderFadeDelay;

            })();

            (function(){//ajusta heights e seta maior height como tamanho do slider
                var h = 0;
                $w('.banner-wrapper').each(function(i){
                    var $this = $(this);
                    $this.show();
                    if ($this.height() > h)
                        h = $this.height();

                    //CORE-4890 - antigo
                    // var bg = $(this).css('background-color');           
                    // $(this).removeAttr('style');
                    // $(this).css('background-color',bg);
                   // if (i && $.browser.msie) $(this).hide(); //compensar o css:nth-child ausente nos IE
                   // end antigo

                   var bg = $this.css('background-image'),
                       bg_color = $this.css('background-color');         
                    $(this).removeAttr('style');
                    $(this).css('background-image',bg).css('background-color',bg_color);

                });
                $w().css({ height: h, lineHeight: h + 'px' });
            })();

            (function(){ //adiciona controle de slides
                var $html = '<ul class="slider-controller">';
                $w('.banner-wrapper').each(function(i){
                    $html += '<li>'+(i+1)+'</li>';
                });
                $html += '</ul>';
                $w().append($html);
                $w('.slider-controller li').first().addClass('current');
                $w('.slider-controller li').click(function(){
                    changeBanner($(this).index());
                });
            })();
            var changeBanner = function($toIndex){
                var $cur = $w('.banner-wrapper:visible'),
                    $to = (
                        $toIndex !== undefined ?
                        $w('.banner-wrapper').eq($toIndex) :
                        ($cur.next('.banner-wrapper').length ?
                            $cur.next('.banner-wrapper') :
                            $cur.siblings('.banner-wrapper').first()
                        )
                    );

                $cur.animate({ opacity: 0 }, o.sliderFadeDelay, function(){
                    $(this).removeClass('banner-show');
                    if ($w('.slider-controller').length){
                        $w('.slider-controller li').removeClass('current').eq($w('.banner-wrapper').index($to)).addClass('current');
                    }
                    $to.addClass('banner-show');
                    $to.animate({ opacity: 1 }, o.sliderFadeDelay);
                });
            };
            var startTimer = function(){
                if (o.sliderTimer) {
                    clearInterval(o.sliderTimer);
                }
                o.sliderTimer = setInterval(changeBanner, o.sliderDelay);
            }; startTimer();
            

            //comportamento de parar o slider com o mouse em cima
            $w().on({
                mouseenter: function(){
                    clearInterval(o.sliderTimer);
                },
                mouseleave: function(){
                    startTimer();
                }
            });
            
            
            
        }


    });

     $.extend($.wd.MarketingBanner.prototype.options, {
        sliderDelay: 7000,
        sliderFadeDelay: 300
     });
})(jQuery, window, document);
; (function ($, window, document, undefined) {

    $.widget('wd.SuccessMessage', $.wd.widget, {

        _create: function () {
            var widget = this,
                $w = this.getContext();
            widget.options.autoLoad = $w().attr("data-autoload");
            console.log(widget.options.autoLoad);
            if (widget.options.autoLoad == "true") {
                widget.modal({
                    element: $w().clone().removeClass("displayNone"),
                    'class': 'wd-success-message'
                });
            }else{
                $w().show();
            }
        }

    });

   // Defaults
   $.extend($.wd.SuccessMessage.prototype.options, {
   });
   

}) (jQuery, window, document);
//
// Widget de criação de novo review
// Esse widget tem responsabilidade de mostrar o link para novo review e abrir a modal para o form
//
// Publish:
//

;$.widget('wd.ErrorSummary', $.wd.widget, {

    _create: function () {

        var widget = this,
            $w = widget.getContext();

        // Se for popup, avisa a tela principal
        if (window && window.opener) {
            try{
                window.opener.$.publish('/Page/Error', widget);
              }
            catch(err){
              }
        }
        // Se for a tela principal, recebe o evento
        if (window && window.opener == null) {
            widget.subscribe('/Page/Error', function (e, w) {
                widget.element.html(w.element.html());
            });

            widget.subscribe('/Page/Response', function (e, r) {
                if (r && !r.IsValid) {
                    var template = $("#ResponseErrorTmpl").html(),
                        html = Mustache.render(template, r);                    
                    widget.element.html( html );
                    if ($w().attr('data-mode') === 'modal') {
                        widget.alert(html);
                    }else{$('html, body').animate({ scrollTop: 0 }, 300);}

                }
            });
        }

        if ($w().attr('data-mode') === 'modal') {
            var errors = [];
            $w('.error-summary-message').each(function(){
                errors.push($(this).text());
            });
            if (errors.length) {
                widget.alert('Erro(s):<ul><li>'+errors.join('</li><li>')+'</li></ul>', 'error');
            }
        }
    }
});
; (function ($, window, document, undefined) {
    
    $.widget('wd.BrandCarousel', $.wd.widget, {

        _create: function () {

        	var old_jcarousel = $.fn.jcarousel;
            $.fn.jcarousel = {};
            /*! jCarousel - v0.3.0 - 2014-01-09
			* http://sorgalla.com/jcarousel
			* Copyright (c) 2014 Jan Sorgalla; Licensed MIT */
			(function(t){"use strict";var i=t.jCarousel={};i.version="0.3.0";var s=/^([+\-]=)?(.+)$/;i.parseTarget=function(t){var i=!1,e="object"!=typeof t?s.exec(t):null;return e?(t=parseInt(e[2],10)||0,e[1]&&(i=!0,"-="===e[1]&&(t*=-1))):"object"!=typeof t&&(t=parseInt(t,10)||0),{target:t,relative:i}},i.detectCarousel=function(t){for(var i;t.length>0;){if(i=t.filter("[data-jcarousel]"),i.length>0)return i;if(i=t.find("[data-jcarousel]"),i.length>0)return i;t=t.parent()}return null},i.base=function(s){return{version:i.version,_options:{},_element:null,_carousel:null,_init:t.noop,_create:t.noop,_destroy:t.noop,_reload:t.noop,create:function(){return this._element.attr("data-"+s.toLowerCase(),!0).data(s,this),!1===this._trigger("create")?this:(this._create(),this._trigger("createend"),this)},destroy:function(){return!1===this._trigger("destroy")?this:(this._destroy(),this._trigger("destroyend"),this._element.removeData(s).removeAttr("data-"+s.toLowerCase()),this)},reload:function(t){return!1===this._trigger("reload")?this:(t&&this.options(t),this._reload(),this._trigger("reloadend"),this)},element:function(){return this._element},options:function(i,s){if(0===arguments.length)return t.extend({},this._options);if("string"==typeof i){if(s===void 0)return this._options[i]===void 0?null:this._options[i];this._options[i]=s}else this._options=t.extend({},this._options,i);return this},carousel:function(){return this._carousel||(this._carousel=i.detectCarousel(this.options("carousel")||this._element),this._carousel||t.error('Could not detect carousel for plugin "'+s+'"')),this._carousel},_trigger:function(i,e,r){var n,o=!1;return r=[this].concat(r||[]),(e||this._element).each(function(){n=t.Event((s+":"+i).toLowerCase()),t(this).trigger(n,r),n.isDefaultPrevented()&&(o=!0)}),!o}}},i.plugin=function(s,e){var r=t[s]=function(i,s){this._element=t(i),this.options(s),this._init(),this.create()};return r.fn=r.prototype=t.extend({},i.base(s),e),t.fn[s]=function(i){var e=Array.prototype.slice.call(arguments,1),n=this;return"string"==typeof i?this.each(function(){var r=t(this).data(s);if(!r)return t.error("Cannot call methods on "+s+" prior to initialization; "+'attempted to call method "'+i+'"');if(!t.isFunction(r[i])||"_"===i.charAt(0))return t.error('No such method "'+i+'" for '+s+" instance");var o=r[i].apply(r,e);return o!==r&&o!==void 0?(n=o,!1):void 0}):this.each(function(){var e=t(this).data(s);e instanceof r?e.reload(i):new r(this,i)}),n},r}})(jQuery),function(t,i){"use strict";var s=function(t){return parseFloat(t)||0};t.jCarousel.plugin("jcarousel",{animating:!1,tail:0,inTail:!1,resizeTimer:null,lt:null,vertical:!1,rtl:!1,circular:!1,underflow:!1,relative:!1,_options:{list:function(){return this.element().children().eq(0)},items:function(){return this.list().children()},animation:400,transitions:!1,wrap:null,vertical:null,rtl:null,center:!1},_list:null,_items:null,_target:null,_first:null,_last:null,_visible:null,_fullyvisible:null,_init:function(){var t=this;return this.onWindowResize=function(){t.resizeTimer&&clearTimeout(t.resizeTimer),t.resizeTimer=setTimeout(function(){t.reload()},100)},this},_create:function(){this._reload(),t(i).on("resize.jcarousel",this.onWindowResize)},_destroy:function(){t(i).off("resize.jcarousel",this.onWindowResize)},_reload:function(){this.vertical=this.options("vertical"),null==this.vertical&&(this.vertical=this.list().height()>this.list().width()),this.rtl=this.options("rtl"),null==this.rtl&&(this.rtl=function(i){if("rtl"===(""+i.attr("dir")).toLowerCase())return!0;var s=!1;return i.parents("[dir]").each(function(){return/rtl/i.test(t(this).attr("dir"))?(s=!0,!1):void 0}),s}(this._element)),this.lt=this.vertical?"top":"left",this.relative="relative"===this.list().css("position"),this._list=null,this._items=null;var i=this._target&&this.index(this._target)>=0?this._target:this.closest();this.circular="circular"===this.options("wrap"),this.underflow=!1;var s={left:0,top:0};return i.length>0&&(this._prepare(i),this.list().find("[data-jcarousel-clone]").remove(),this._items=null,this.underflow=this._fullyvisible.length>=this.items().length,this.circular=this.circular&&!this.underflow,s[this.lt]=this._position(i)+"px"),this.move(s),this},list:function(){if(null===this._list){var i=this.options("list");this._list=t.isFunction(i)?i.call(this):this._element.find(i)}return this._list},items:function(){if(null===this._items){var i=this.options("items");this._items=(t.isFunction(i)?i.call(this):this.list().find(i)).not("[data-jcarousel-clone]")}return this._items},index:function(t){return this.items().index(t)},closest:function(){var i,e=this,r=this.list().position()[this.lt],n=t(),o=!1,l=this.vertical?"bottom":this.rtl&&!this.relative?"left":"right";return this.rtl&&this.relative&&!this.vertical&&(r+=this.list().width()-this.clipping()),this.items().each(function(){if(n=t(this),o)return!1;var a=e.dimension(n);if(r+=a,r>=0){if(i=a-s(n.css("margin-"+l)),!(0>=Math.abs(r)-a+i/2))return!1;o=!0}}),n},target:function(){return this._target},first:function(){return this._first},last:function(){return this._last},visible:function(){return this._visible},fullyvisible:function(){return this._fullyvisible},hasNext:function(){if(!1===this._trigger("hasnext"))return!0;var t=this.options("wrap"),i=this.items().length-1;return i>=0&&!this.underflow&&(t&&"first"!==t||i>this.index(this._last)||this.tail&&!this.inTail)?!0:!1},hasPrev:function(){if(!1===this._trigger("hasprev"))return!0;var t=this.options("wrap");return this.items().length>0&&!this.underflow&&(t&&"last"!==t||this.index(this._first)>0||this.tail&&this.inTail)?!0:!1},clipping:function(){return this._element["inner"+(this.vertical?"Height":"Width")]()},dimension:function(t){return t["outer"+(this.vertical?"Height":"Width")](!0)},scroll:function(i,s,e){if(this.animating)return this;if(!1===this._trigger("scroll",null,[i,s]))return this;t.isFunction(s)&&(e=s,s=!0);var r=t.jCarousel.parseTarget(i);if(r.relative){var n,o,l,a,h,u,c,f,d=this.items().length-1,_=Math.abs(r.target),p=this.options("wrap");if(r.target>0){var v=this.index(this._last);if(v>=d&&this.tail)this.inTail?"both"===p||"last"===p?this._scroll(0,s,e):t.isFunction(e)&&e.call(this,!1):this._scrollTail(s,e);else if(n=this.index(this._target),this.underflow&&n===d&&("circular"===p||"both"===p||"last"===p)||!this.underflow&&v===d&&("both"===p||"last"===p))this._scroll(0,s,e);else if(l=n+_,this.circular&&l>d){for(f=d,h=this.items().get(-1);l>f++;)h=this.items().eq(0),u=this._visible.index(h)>=0,u&&h.after(h.clone(!0).attr("data-jcarousel-clone",!0)),this.list().append(h),u||(c={},c[this.lt]=this.dimension(h),this.moveBy(c)),this._items=null;this._scroll(h,s,e)}else this._scroll(Math.min(l,d),s,e)}else if(this.inTail)this._scroll(Math.max(this.index(this._first)-_+1,0),s,e);else if(o=this.index(this._first),n=this.index(this._target),a=this.underflow?n:o,l=a-_,0>=a&&(this.underflow&&"circular"===p||"both"===p||"first"===p))this._scroll(d,s,e);else if(this.circular&&0>l){for(f=l,h=this.items().get(0);0>f++;){h=this.items().eq(-1),u=this._visible.index(h)>=0,u&&h.after(h.clone(!0).attr("data-jcarousel-clone",!0)),this.list().prepend(h),this._items=null;var g=this.dimension(h);c={},c[this.lt]=-g,this.moveBy(c)}this._scroll(h,s,e)}else this._scroll(Math.max(l,0),s,e)}else this._scroll(r.target,s,e);return this._trigger("scrollend"),this},moveBy:function(t,i){var e=this.list().position(),r=1,n=0;return this.rtl&&!this.vertical&&(r=-1,this.relative&&(n=this.list().width()-this.clipping())),t.left&&(t.left=e.left+n+s(t.left)*r+"px"),t.top&&(t.top=e.top+n+s(t.top)*r+"px"),this.move(t,i)},move:function(i,s){s=s||{};var e=this.options("transitions"),r=!!e,n=!!e.transforms,o=!!e.transforms3d,l=s.duration||0,a=this.list();if(!r&&l>0)return a.animate(i,s),void 0;var h=s.complete||t.noop,u={};if(r){var c=a.css(["transitionDuration","transitionTimingFunction","transitionProperty"]),f=h;h=function(){t(this).css(c),f.call(this)},u={transitionDuration:(l>0?l/1e3:0)+"s",transitionTimingFunction:e.easing||s.easing,transitionProperty:l>0?function(){return n||o?"all":i.left?"left":"top"}():"none",transform:"none"}}o?u.transform="translate3d("+(i.left||0)+","+(i.top||0)+",0)":n?u.transform="translate("+(i.left||0)+","+(i.top||0)+")":t.extend(u,i),r&&l>0&&a.one("transitionend webkitTransitionEnd oTransitionEnd otransitionend MSTransitionEnd",h),a.css(u),0>=l&&a.each(function(){h.call(this)})},_scroll:function(i,s,e){if(this.animating)return t.isFunction(e)&&e.call(this,!1),this;if("object"!=typeof i?i=this.items().eq(i):i.jquery===void 0&&(i=t(i)),0===i.length)return t.isFunction(e)&&e.call(this,!1),this;this.inTail=!1,this._prepare(i);var r=this._position(i),n=this.list().position()[this.lt];if(r===n)return t.isFunction(e)&&e.call(this,!1),this;var o={};return o[this.lt]=r+"px",this._animate(o,s,e),this},_scrollTail:function(i,s){if(this.animating||!this.tail)return t.isFunction(s)&&s.call(this,!1),this;var e=this.list().position()[this.lt];this.rtl&&this.relative&&!this.vertical&&(e+=this.list().width()-this.clipping()),this.rtl&&!this.vertical?e+=this.tail:e-=this.tail,this.inTail=!0;var r={};return r[this.lt]=e+"px",this._update({target:this._target.next(),fullyvisible:this._fullyvisible.slice(1).add(this._visible.last())}),this._animate(r,i,s),this},_animate:function(i,s,e){if(e=e||t.noop,!1===this._trigger("animate"))return e.call(this,!1),this;this.animating=!0;var r=this.options("animation"),n=t.proxy(function(){this.animating=!1;var t=this.list().find("[data-jcarousel-clone]");t.length>0&&(t.remove(),this._reload()),this._trigger("animateend"),e.call(this,!0)},this),o="object"==typeof r?t.extend({},r):{duration:r},l=o.complete||t.noop;return s===!1?o.duration=0:t.fx.speeds[o.duration]!==void 0&&(o.duration=t.fx.speeds[o.duration]),o.complete=function(){n(),l.call(this)},this.move(i,o),this},_prepare:function(i){var e,r,n,o,l=this.index(i),a=l,h=this.dimension(i),u=this.clipping(),c=this.vertical?"bottom":this.rtl?"left":"right",f=this.options("center"),d={target:i,first:i,last:i,visible:i,fullyvisible:u>=h?i:t()};if(f&&(h/=2,u/=2),u>h)for(;;){if(e=this.items().eq(++a),0===e.length){if(!this.circular)break;if(e=this.items().eq(0),i.get(0)===e.get(0))break;if(r=this._visible.index(e)>=0,r&&e.after(e.clone(!0).attr("data-jcarousel-clone",!0)),this.list().append(e),!r){var _={};_[this.lt]=this.dimension(e),this.moveBy(_)}this._items=null}if(o=this.dimension(e),0===o)break;if(h+=o,d.last=e,d.visible=d.visible.add(e),n=s(e.css("margin-"+c)),u>=h-n&&(d.fullyvisible=d.fullyvisible.add(e)),h>=u)break}if(!this.circular&&!f&&u>h)for(a=l;;){if(0>--a)break;if(e=this.items().eq(a),0===e.length)break;if(o=this.dimension(e),0===o)break;if(h+=o,d.first=e,d.visible=d.visible.add(e),n=s(e.css("margin-"+c)),u>=h-n&&(d.fullyvisible=d.fullyvisible.add(e)),h>=u)break}return this._update(d),this.tail=0,f||"circular"===this.options("wrap")||"custom"===this.options("wrap")||this.index(d.last)!==this.items().length-1||(h-=s(d.last.css("margin-"+c)),h>u&&(this.tail=h-u)),this},_position:function(t){var i=this._first,s=i.position()[this.lt],e=this.options("center"),r=e?this.clipping()/2-this.dimension(i)/2:0;return this.rtl&&!this.vertical?(s-=this.relative?this.list().width()-this.dimension(i):this.clipping()-this.dimension(i),s+=r):s-=r,!e&&(this.index(t)>this.index(i)||this.inTail)&&this.tail?(s=this.rtl&&!this.vertical?s-this.tail:s+this.tail,this.inTail=!0):this.inTail=!1,-s},_update:function(i){var s,e=this,r={target:this._target||t(),first:this._first||t(),last:this._last||t(),visible:this._visible||t(),fullyvisible:this._fullyvisible||t()},n=this.index(i.first||r.first)<this.index(r.first),o=function(s){var o=[],l=[];i[s].each(function(){0>r[s].index(this)&&o.push(this)}),r[s].each(function(){0>i[s].index(this)&&l.push(this)}),n?o=o.reverse():l=l.reverse(),e._trigger(s+"in",t(o)),e._trigger(s+"out",t(l)),e["_"+s]=i[s]};for(s in i)o(s);return this}})}(jQuery,window),function(t){"use strict";t.jcarousel.fn.scrollIntoView=function(i,s,e){var r,n=t.jCarousel.parseTarget(i),o=this.index(this._fullyvisible.first()),l=this.index(this._fullyvisible.last());if(r=n.relative?0>n.target?Math.max(0,o+n.target):l+n.target:"object"!=typeof n.target?n.target:this.index(n.target),o>r)return this.scroll(r,s,e);if(r>=o&&l>=r)return t.isFunction(e)&&e.call(this,!1),this;for(var a,h=this.items(),u=this.clipping(),c=this.vertical?"bottom":this.rtl?"left":"right",f=0;;){if(a=h.eq(r),0===a.length)break;if(f+=this.dimension(a),f>=u){var d=parseFloat(a.css("margin-"+c))||0;f-d!==u&&r++;break}if(0>=r)break;r--}return this.scroll(r,s,e)}}(jQuery),function(t){"use strict";t.jCarousel.plugin("jcarouselControl",{_options:{target:"+=1",event:"click",method:"scroll"},_active:null,_init:function(){this.onDestroy=t.proxy(function(){this._destroy(),this.carousel().one("jcarousel:createend",t.proxy(this._create,this))},this),this.onReload=t.proxy(this._reload,this),this.onEvent=t.proxy(function(i){i.preventDefault();var s=this.options("method");t.isFunction(s)?s.call(this):this.carousel().jcarousel(this.options("method"),this.options("target"))},this)},_create:function(){this.carousel().one("jcarousel:destroy",this.onDestroy).on("jcarousel:reloadend jcarousel:scrollend",this.onReload),this._element.on(this.options("event")+".jcarouselcontrol",this.onEvent),this._reload()},_destroy:function(){this._element.off(".jcarouselcontrol",this.onEvent),this.carousel().off("jcarousel:destroy",this.onDestroy).off("jcarousel:reloadend jcarousel:scrollend",this.onReload)},_reload:function(){var i,s=t.jCarousel.parseTarget(this.options("target")),e=this.carousel();if(s.relative)i=e.jcarousel(s.target>0?"hasNext":"hasPrev");else{var r="object"!=typeof s.target?e.jcarousel("items").eq(s.target):s.target;i=e.jcarousel("target").index(r)>=0}return this._active!==i&&(this._trigger(i?"active":"inactive"),this._active=i),this}})}(jQuery),function(t){"use strict";t.jCarousel.plugin("jcarouselPagination",{_options:{perPage:null,item:function(t){return'<a href="#'+t+'">'+t+"</a>"},event:"click",method:"scroll"},_pages:{},_items:{},_currentPage:null,_init:function(){this.onDestroy=t.proxy(function(){this._destroy(),this.carousel().one("jcarousel:createend",t.proxy(this._create,this))},this),this.onReload=t.proxy(this._reload,this),this.onScroll=t.proxy(this._update,this)},_create:function(){this.carousel().one("jcarousel:destroy",this.onDestroy).on("jcarousel:reloadend",this.onReload).on("jcarousel:scrollend",this.onScroll),this._reload()},_destroy:function(){this._clear(),this.carousel().off("jcarousel:destroy",this.onDestroy).off("jcarousel:reloadend",this.onReload).off("jcarousel:scrollend",this.onScroll)},_reload:function(){var i=this.options("perPage");if(this._pages={},this._items={},t.isFunction(i)&&(i=i.call(this)),null==i)this._pages=this._calculatePages();else for(var s,e=parseInt(i,10)||0,r=this.carousel().jcarousel("items"),n=1,o=0;;){if(s=r.eq(o++),0===s.length)break;this._pages[n]=this._pages[n]?this._pages[n].add(s):s,0===o%e&&n++}this._clear();var l=this,a=this.carousel().data("jcarousel"),h=this._element,u=this.options("item");t.each(this._pages,function(i,s){var e=l._items[i]=t(u.call(l,i,s));e.on(l.options("event")+".jcarouselpagination",t.proxy(function(){var t=s.eq(0);if(a.circular){var e=a.index(a.target()),r=a.index(t);parseFloat(i)>parseFloat(l._currentPage)?e>r&&(t="+="+(a.items().length-e+r)):r>e&&(t="-="+(e+(a.items().length-r)))}a[this.options("method")](t)},l)),h.append(e)}),this._update()},_update:function(){var i,s=this.carousel().jcarousel("target");t.each(this._pages,function(t,e){return e.each(function(){return s.is(this)?(i=t,!1):void 0}),i?!1:void 0}),this._currentPage!==i&&(this._trigger("inactive",this._items[this._currentPage]),this._trigger("active",this._items[i])),this._currentPage=i},items:function(){return this._items},_clear:function(){this._element.empty(),this._currentPage=null},_calculatePages:function(){for(var t,i=this.carousel().data("jcarousel"),s=i.items(),e=i.clipping(),r=0,n=0,o=1,l={};;){if(t=s.eq(n++),0===t.length)break;l[o]=l[o]?l[o].add(t):t,r+=i.dimension(t),r>=e&&(o++,r=0)}return l}})}(jQuery),function(t){"use strict";t.jCarousel.plugin("jcarouselAutoscroll",{_options:{target:"+=1",interval:3e3,autostart:!0},_timer:null,_init:function(){this.onDestroy=t.proxy(function(){this._destroy(),this.carousel().one("jcarousel:createend",t.proxy(this._create,this))},this),this.onAnimateEnd=t.proxy(this.start,this)},_create:function(){this.carousel().one("jcarousel:destroy",this.onDestroy),this.options("autostart")&&this.start()},_destroy:function(){this.stop(),this.carousel().off("jcarousel:destroy",this.onDestroy)},start:function(){return this.stop(),this.carousel().one("jcarousel:animateend",this.onAnimateEnd),this._timer=setTimeout(t.proxy(function(){this.carousel().jcarousel("scroll",this.options("target"))},this),this.options("interval")),this},stop:function(){return this._timer&&(this._timer=clearTimeout(this._timer)),this.carousel().off("jcarousel:animateend",this.onAnimateEnd),this}})}(jQuery);

            var widget = this;
            var o = widget.options;

            $.each(o, function(k,v){ if (typeof v === 'string') o[k] = v.toLowerCase(); });   

            $('.jcarousel', widget.element).jcarousel(o);


            switch (widget.options.layout) {
                case 'fixed': $('.jcarousel-container-horizontal', widget.element).addClass('jcarousel-container-fixed'); break;
                case 'fluid': $('.jcarousel-container-horizontal', widget.element).addClass('jcarousel-container-fluid'); break;
            }
            switch (widget.options.vertical) {
                case true : 
                $('.jcarousel', widget.element).addClass('jcarousel-vertical'); 
                break;
            }

            $('.jcarousel-control-prev', widget.element)
                .on('jcarouselcontrol:active', function() {
                    $(this).removeClass('inactive');
                })
                .on('jcarouselcontrol:inactive', function() {
                    $(this).addClass('inactive');
                })
                .jcarouselControl({
                    // Options go here
                    target: '-=1'
                });

            /*
             Next control initialization
             */
            $('.jcarousel-control-next', widget.element)
                .on('jcarouselcontrol:active', function() {
                    $(this).removeClass('inactive');
                })
                .on('jcarouselcontrol:inactive', function() {
                    $(this).addClass('inactive');
                })
                .jcarouselControl({
                    // Options go here
                    target: '+=1'
                });

                $.fn.jcarousel = old_jcarousel;
        }
    });

    // Defaults
    $.extend($.wd.BrandCarousel.prototype.options, {
        auto: 0,
        visible: 10,
        scroll: 1,
        wrap: 'last',
        layout: 'fixed',
        vertical: false
        
    });
})(jQuery, window, document);
/*! jCarousel - v0.3.0 - 2014-01-09
* http://sorgalla.com/jcarousel
* Copyright (c) 2014 Jan Sorgalla; Licensed MIT */
(function(t){"use strict";var i=t.jCarousel={};i.version="0.3.0";var s=/^([+\-]=)?(.+)$/;i.parseTarget=function(t){var i=!1,e="object"!=typeof t?s.exec(t):null;return e?(t=parseInt(e[2],10)||0,e[1]&&(i=!0,"-="===e[1]&&(t*=-1))):"object"!=typeof t&&(t=parseInt(t,10)||0),{target:t,relative:i}},i.detectCarousel=function(t){for(var i;t.length>0;){if(i=t.filter("[data-jcarousel]"),i.length>0)return i;if(i=t.find("[data-jcarousel]"),i.length>0)return i;t=t.parent()}return null},i.base=function(s){return{version:i.version,_options:{},_element:null,_carousel:null,_init:t.noop,_create:t.noop,_destroy:t.noop,_reload:t.noop,create:function(){return this._element.attr("data-"+s.toLowerCase(),!0).data(s,this),!1===this._trigger("create")?this:(this._create(),this._trigger("createend"),this)},destroy:function(){return!1===this._trigger("destroy")?this:(this._destroy(),this._trigger("destroyend"),this._element.removeData(s).removeAttr("data-"+s.toLowerCase()),this)},reload:function(t){return!1===this._trigger("reload")?this:(t&&this.options(t),this._reload(),this._trigger("reloadend"),this)},element:function(){return this._element},options:function(i,s){if(0===arguments.length)return t.extend({},this._options);if("string"==typeof i){if(s===void 0)return this._options[i]===void 0?null:this._options[i];this._options[i]=s}else this._options=t.extend({},this._options,i);return this},carousel:function(){return this._carousel||(this._carousel=i.detectCarousel(this.options("carousel")||this._element),this._carousel||t.error('Could not detect carousel for plugin "'+s+'"')),this._carousel},_trigger:function(i,e,r){var n,l=!1;return r=[this].concat(r||[]),(e||this._element).each(function(){n=t.Event((s+":"+i).toLowerCase()),t(this).trigger(n,r),n.isDefaultPrevented()&&(l=!0)}),!l}}},i.plugin=function(s,e){var r=t[s]=function(i,s){this._element=t(i),this.options(s),this._init(),this.create()};return r.fn=r.prototype=t.extend({},i.base(s),e),t.fn[s]=function(i){var e=Array.prototype.slice.call(arguments,1),n=this;return"string"==typeof i?this.each(function(){var r=t(this).data(s);if(!r)return t.error("Cannot call methods on "+s+" prior to initialization; "+'attempted to call method "'+i+'"');if(!t.isFunction(r[i])||"_"===i.charAt(0))return t.error('No such method "'+i+'" for '+s+" instance");var l=r[i].apply(r,e);return l!==r&&l!==void 0?(n=l,!1):void 0}):this.each(function(){var e=t(this).data(s);e instanceof r?e.reload(i):new r(this,i)}),n},r}})(jQuery),function(t,i){"use strict";var s=function(t){return parseFloat(t)||0};t.jCarousel.plugin("jcarousel",{animating:!1,tail:0,inTail:!1,resizeTimer:null,lt:null,vertical:!1,rtl:!1,circular:!1,underflow:!1,relative:!1,_options:{list:function(){return this.element().children().eq(0)},items:function(){return this.list().children()},animation:400,transitions:!1,wrap:null,vertical:null,rtl:null,center:!1},_list:null,_items:null,_target:null,_first:null,_last:null,_visible:null,_fullyvisible:null,_init:function(){var t=this;return this.onWindowResize=function(){t.resizeTimer&&clearTimeout(t.resizeTimer),t.resizeTimer=setTimeout(function(){t.reload()},100)},this},_create:function(){this._reload(),t(i).on("resize.jcarousel",this.onWindowResize)},_destroy:function(){t(i).off("resize.jcarousel",this.onWindowResize)},_reload:function(){this.vertical=this.options("vertical"),null==this.vertical&&(this.vertical=this.list().height()>this.list().width()),this.rtl=this.options("rtl"),null==this.rtl&&(this.rtl=function(i){if("rtl"===(""+i.attr("dir")).toLowerCase())return!0;var s=!1;return i.parents("[dir]").each(function(){return/rtl/i.test(t(this).attr("dir"))?(s=!0,!1):void 0}),s}(this._element)),this.lt=this.vertical?"top":"left",this.relative="relative"===this.list().css("position"),this._list=null,this._items=null;var i=this._target&&this.index(this._target)>=0?this._target:this.closest();this.circular="circular"===this.options("wrap"),this.underflow=!1;var s={left:0,top:0};return i.length>0&&(this._prepare(i),this.list().find("[data-jcarousel-clone]").remove(),this._items=null,this.underflow=this._fullyvisible.length>=this.items().length,this.circular=this.circular&&!this.underflow,s[this.lt]=this._position(i)+"px"),this.move(s),this},list:function(){if(null===this._list){var i=this.options("list");this._list=t.isFunction(i)?i.call(this):this._element.find(i)}return this._list},items:function(){if(null===this._items){var i=this.options("items");this._items=(t.isFunction(i)?i.call(this):this.list().find(i)).not("[data-jcarousel-clone]")}return this._items},index:function(t){return this.items().index(t)},closest:function(){var i,e=this,r=this.list().position()[this.lt],n=t(),l=!1,o=this.vertical?"bottom":this.rtl&&!this.relative?"left":"right";return this.rtl&&this.relative&&!this.vertical&&(r+=this.list().width()-this.clipping()),this.items().each(function(){if(n=t(this),l)return!1;var a=e.dimension(n);if(r+=a,r>=0){if(i=a-s(n.css("margin-"+o)),!(0>=Math.abs(r)-a+i/2))return!1;l=!0}}),n},target:function(){return this._target},first:function(){return this._first},last:function(){return this._last},visible:function(){return this._visible},fullyvisible:function(){return this._fullyvisible},hasNext:function(){if(!1===this._trigger("hasnext"))return!0;var t=this.options("wrap"),i=this.items().length-1;return i>=0&&!this.underflow&&(t&&"first"!==t||i>this.index(this._last)||this.tail&&!this.inTail)?!0:!1},hasPrev:function(){if(!1===this._trigger("hasprev"))return!0;var t=this.options("wrap");return this.items().length>0&&!this.underflow&&(t&&"last"!==t||this.index(this._first)>0||this.tail&&this.inTail)?!0:!1},clipping:function(){return this._element["inner"+(this.vertical?"Height":"Width")]()},dimension:function(t){return t["outer"+(this.vertical?"Height":"Width")](!0)},scroll:function(i,s,e){if(this.animating)return this;if(!1===this._trigger("scroll",null,[i,s]))return this;t.isFunction(s)&&(e=s,s=!0);var r=t.jCarousel.parseTarget(i);if(r.relative){var n,l,o,a,h,u,c,f,d=this.items().length-1,_=Math.abs(r.target),p=this.options("wrap");if(r.target>0){var v=this.index(this._last);if(v>=d&&this.tail)this.inTail?"both"===p||"last"===p?this._scroll(0,s,e):t.isFunction(e)&&e.call(this,!1):this._scrollTail(s,e);else if(n=this.index(this._target),this.underflow&&n===d&&("circular"===p||"both"===p||"last"===p)||!this.underflow&&v===d&&("both"===p||"last"===p))this._scroll(0,s,e);else if(o=n+_,this.circular&&o>d){for(f=d,h=this.items().get(-1);o>f++;)h=this.items().eq(0),u=this._visible.index(h)>=0,u&&h.after(h.clone(!0).attr("data-jcarousel-clone",!0)),this.list().append(h),u||(c={},c[this.lt]=this.dimension(h),this.moveBy(c)),this._items=null;this._scroll(h,s,e)}else this._scroll(Math.min(o,d),s,e)}else if(this.inTail)this._scroll(Math.max(this.index(this._first)-_+1,0),s,e);else if(l=this.index(this._first),n=this.index(this._target),a=this.underflow?n:l,o=a-_,0>=a&&(this.underflow&&"circular"===p||"both"===p||"first"===p))this._scroll(d,s,e);else if(this.circular&&0>o){for(f=o,h=this.items().get(0);0>f++;){h=this.items().eq(-1),u=this._visible.index(h)>=0,u&&h.after(h.clone(!0).attr("data-jcarousel-clone",!0)),this.list().prepend(h),this._items=null;var g=this.dimension(h);c={},c[this.lt]=-g,this.moveBy(c)}this._scroll(h,s,e)}else this._scroll(Math.max(o,0),s,e)}else this._scroll(r.target,s,e);return this._trigger("scrollend"),this},moveBy:function(t,i){var e=this.list().position(),r=1,n=0;return this.rtl&&!this.vertical&&(r=-1,this.relative&&(n=this.list().width()-this.clipping())),t.left&&(t.left=e.left+n+s(t.left)*r+"px"),t.top&&(t.top=e.top+n+s(t.top)*r+"px"),this.move(t,i)},move:function(i,s){s=s||{};var e=this.options("transitions"),r=!!e,n=!!e.transforms,l=!!e.transforms3d,o=s.duration||0,a=this.list();if(!r&&o>0)return a.animate(i,s),void 0;var h=s.complete||t.noop,u={};if(r){var c=a.css(["transitionDuration","transitionTimingFunction","transitionProperty"]),f=h;h=function(){t(this).css(c),f.call(this)},u={transitionDuration:(o>0?o/1e3:0)+"s",transitionTimingFunction:e.easing||s.easing,transitionProperty:o>0?function(){return n||l?"all":i.left?"left":"top"}():"none",transform:"none"}}l?u.transform="translate3d("+(i.left||0)+","+(i.top||0)+",0)":n?u.transform="translate("+(i.left||0)+","+(i.top||0)+")":t.extend(u,i),r&&o>0&&a.one("transitionend webkitTransitionEnd oTransitionEnd otransitionend MSTransitionEnd",h),a.css(u),0>=o&&a.each(function(){h.call(this)})},_scroll:function(i,s,e){if(this.animating)return t.isFunction(e)&&e.call(this,!1),this;if("object"!=typeof i?i=this.items().eq(i):i.jquery===void 0&&(i=t(i)),0===i.length)return t.isFunction(e)&&e.call(this,!1),this;this.inTail=!1,this._prepare(i);var r=this._position(i),n=this.list().position()[this.lt];if(r===n)return t.isFunction(e)&&e.call(this,!1),this;var l={};return l[this.lt]=r+"px",this._animate(l,s,e),this},_scrollTail:function(i,s){if(this.animating||!this.tail)return t.isFunction(s)&&s.call(this,!1),this;var e=this.list().position()[this.lt];this.rtl&&this.relative&&!this.vertical&&(e+=this.list().width()-this.clipping()),this.rtl&&!this.vertical?e+=this.tail:e-=this.tail,this.inTail=!0;var r={};return r[this.lt]=e+"px",this._update({target:this._target.next(),fullyvisible:this._fullyvisible.slice(1).add(this._visible.last())}),this._animate(r,i,s),this},_animate:function(i,s,e){if(e=e||t.noop,!1===this._trigger("animate"))return e.call(this,!1),this;this.animating=!0;var r=this.options("animation"),n=t.proxy(function(){this.animating=!1;var t=this.list().find("[data-jcarousel-clone]");t.length>0&&(t.remove(),this._reload()),this._trigger("animateend"),e.call(this,!0)},this),l="object"==typeof r?t.extend({},r):{duration:r},o=l.complete||t.noop;return s===!1?l.duration=0:t.fx.speeds[l.duration]!==void 0&&(l.duration=t.fx.speeds[l.duration]),l.complete=function(){n(),o.call(this)},this.move(i,l),this},_prepare:function(i){var e,r,n,l,o=this.index(i),a=o,h=this.dimension(i),u=this.clipping(),c=this.vertical?"bottom":this.rtl?"left":"right",f=this.options("center"),d={target:i,first:i,last:i,visible:i,fullyvisible:u>=h?i:t()};if(f&&(h/=2,u/=2),u>h)for(;;){if(e=this.items().eq(++a),0===e.length){if(!this.circular)break;if(e=this.items().eq(0),i.get(0)===e.get(0))break;if(r=this._visible.index(e)>=0,r&&e.after(e.clone(!0).attr("data-jcarousel-clone",!0)),this.list().append(e),!r){var _={};_[this.lt]=this.dimension(e),this.moveBy(_)}this._items=null}if(l=this.dimension(e),0===l)break;if(h+=l,d.last=e,d.visible=d.visible.add(e),n=s(e.css("margin-"+c)),u>=h-n&&(d.fullyvisible=d.fullyvisible.add(e)),h>=u)break}if(!this.circular&&!f&&u>h)for(a=o;;){if(0>--a)break;if(e=this.items().eq(a),0===e.length)break;if(l=this.dimension(e),0===l)break;if(h+=l,d.first=e,d.visible=d.visible.add(e),n=s(e.css("margin-"+c)),u>=h-n&&(d.fullyvisible=d.fullyvisible.add(e)),h>=u)break}return this._update(d),this.tail=0,f||"circular"===this.options("wrap")||"custom"===this.options("wrap")||this.index(d.last)!==this.items().length-1||(h-=s(d.last.css("margin-"+c)),h>u&&(this.tail=h-u)),this},_position:function(t){var i=this._first,s=i.position()[this.lt],e=this.options("center"),r=e?this.clipping()/2-this.dimension(i)/2:0;return this.rtl&&!this.vertical?(s-=this.relative?this.list().width()-this.dimension(i):this.clipping()-this.dimension(i),s+=r):s-=r,!e&&(this.index(t)>this.index(i)||this.inTail)&&this.tail?(s=this.rtl&&!this.vertical?s-this.tail:s+this.tail,this.inTail=!0):this.inTail=!1,-s},_update:function(i){var s,e=this,r={target:this._target||t(),first:this._first||t(),last:this._last||t(),visible:this._visible||t(),fullyvisible:this._fullyvisible||t()},n=this.index(i.first||r.first)<this.index(r.first),l=function(s){var l=[],o=[];i[s].each(function(){0>r[s].index(this)&&l.push(this)}),r[s].each(function(){0>i[s].index(this)&&o.push(this)}),n?l=l.reverse():o=o.reverse(),e._trigger(s+"in",t(l)),e._trigger(s+"out",t(o)),e["_"+s]=i[s]};for(s in i)l(s);return this}})}(jQuery,window);
