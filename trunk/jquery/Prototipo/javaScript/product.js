$(function() {
    $('#tabs').tabs();
    $('#tabs .ui-corner-all').removeClass('ui-corner-all');

    var $item = $('#object').find('.item');
    var seller_id = $item.find('input[name="seller_id"]').val();

    var id = $item.attr('id');
    var m = /item([a-z]+)(\d+)/g.exec($item.attr('id'))
    var list_hash = $item.find('form input[name="list_hash"]').val()

    data = m[1] + '_id=' + m[2] + '&list_hash=' + list_hash + '&seller_id=' + seller_id;

    var offer;
    kite.api('meucarrinho/listRelatedOffers', data, function(res){
        $('#tabs-offers').html('');
        var row = '';
        for (var i in res.result) {
            offer = res.result[i];
            row = '<div class="related-offer ui-helper-clearfix">';
            row += '<a href="' + offer.links.main + '" class="related-image" title="' + offer.description + '" rel="nofollow">';
            row += '<img src="' + offer.links.image + '" alt="' + offer.description + '" title="' + offer.description + '" class="lazy-product"/></a>';
            row += '<div class="related-descr">' + offer.description + '</div>';
            row += '<div class="related-seller">';
            row += '<img src="' + offer.seller.links.image_50 + '" alt="' + offer.seller.title + '" title="' + offer.seller.title + '" class="lazy-product" style="margin-bottom:30px;"/>';
            row += '<div><b>' + offer.seller.title.split("-")[0] + '</b><br />';
            row += offer.seller.title.split("-")[1];
            if (offer.links.offer) {
                row += '<p style="margin: 5px 0;"><a href="/redir/r/offer/?offer_id=' + offer.offer_id + '" style="color:#719E02;text-decoration:underline;" target="_blank" rel="nofollow">Ir à loja</a></p>';
            } 
            row += '</div></div>';
            row += '<div class="related-price ui-corner-all">'; 
            row += '<p class="related-unit-price">' + mc.intToPrice(offer.price) + '</p>';
            row += '<p><a href="' + offer.links.main + '" title="Ver página da oferta">ver oferta</a></p></div>';
            row += '</div>';

            $('#tabs-offers').append(row);
        }
        mc.applyLazyProducts($('body'));
    });

    $('#tabs-pages ul.pages li a').click(function() {
        $('#tabs-pages ul.pages').hide();
        $.ajax({
            url: $(this).attr('href') + '&no_html=1',
            success: function(content) {
                $('#tabs-pages').append(content);
                $('#tabs-pages .page').before('<a href="#" class="close">fechar</a>');
                $('#tabs-pages .page').after('<a href="#" class="close">fechar</a>');
            },
        });
        return false; 
    });
    $('#tabs-pages a.close').live('click', function() {
        $('#tabs-pages a.close').remove();
        $('#tabs-pages .page').remove(); 
        $('#tabs-pages ul.pages').show();
    });
});
