var mc = {
    set_product_timeout: [],
    priceToInt: function (price) {
        price = parseFloat(price.replace('R$', '').replace(',','.')).toFixed(2).toString().replace('.', '');
        return price;
    },
    intToPrice: function (v) {
        if (v) {
            var price = (v/100).toFixed(2).replace('.', ',');
            price = price.split(','); 
            return 'R$ <span class="price-dec">' + price[0] + '</span>,' + price[1];
        } else {
            return 'R$ <span class="price-dec">-</span>';
        }
    },
    setProduct: function ($item) {
        if ($item.parents('ul.items').hasClass('items'))
            var $type = 'list';
        else
            var $type = 'shelf';

        var id = $item.attr('id');

        function set_product_timeout()
        {
            var seller_id = $item.find('input[name="seller_id"]').val();

            var price = $item.find('input[name="unit-price"]').val();
            var quantity = parseInt($item.find('input[name="quantity"]').val());
            var priceorder = 0;
            if (price) {
                var priceorder = price*quantity;
            }
            var data = $item.find('form').serialize();
            if ($type == 'shelf')
                $item.find('.item-order-price').html('(' + mc.intToPrice(priceorder) + ')');
            else
                $item.find('.item-order-price').html(mc.intToPrice(priceorder));

            $item.find('.item-order-n').html(quantity);
            if ($item.hasClass('item-promoted')) {
                var status = '<div class="col-right item-list-status">Salvando</div>'
                $item.find('.col-right').hide().before(status);
            } else {
                var status = '<div class="item-list-status">Salvando</div>'
                $item.find('.item-list').hide().before(status);
            }

            var compare_page = $('#compare-page').html();
            if (compare_page)
                data += '&page=' + compare_page;

            kite.api('meucarrinho/saveListItem', data, function (res) {
                $item.find('.item-list-status').remove();

                if ($item.hasClass('item-promoted'))
                    $item.find('.col-right').show();
                else
                    $item.find('.item-list').show();

                mc.updateBar(data + '&limit=2&hide_seller_id=' + seller_id, res['result']);
            });
        }

        if (this.set_product_timeout[id])
            clearTimeout(this.set_product_timeout[id]);

        this.set_product_timeout[id] = setTimeout(set_product_timeout, 500);
    },
    updateBar: function (data, new_list) {
        if (typeof data === 'string') {
            aux = {};
            vars = data.split('&');
            for (var i in vars) {
                var pair = vars[i].split('=');
                aux[pair.shift()] = pair.shift();
            }
            data = aux;
        }

        if (typeof new_list !== 'undefined') {
            $('#bar .active-list .list-total-items').html(new_list.total_items);
            $('.list-total-price').html(mc.intToPrice(new_list.total_price));

            $('#bar div.selected-seller').find('.total-price').html(mc.intToPrice(new_list.total_price));

            var total_items = new_list.total_items_available + new_list.total_items_swapped;
            $('#bar div.selected-seller').find('.list-total-items').html(total_items);
            $('#bar div.selected-seller').find('.seller-more-info-available').html(total_items + ' disponível(is)');
            $('#bar div.selected-seller').find('.seller-more-info-swapped').html(new_list.total_items_swapped + ' substituído(s) automaticamente');
            $('#bar div.selected-seller').find('.seller-more-info-unavailable').html(new_list.total_items_unavailable + ' não encontrado(s)');
        }

        kite.api('meucarrinho/listLastOfferLists', data, function (res) {
            var i = 0;
            var total_items = 0;

            $('#sellers-compare li').each(function () {
                $(this).find('.total-price').html(mc.intToPrice(res['result'].items[i].list.total_price));
                $(this).find('img').attr('src', res['result'].items[i].links.image_50);
                var url = $(this).find('.seller-list').attr('href');
                url = url.substring(0, url.indexOf('&seller_id'));
                $(this).find('.seller-list').attr('href', url + '&seller_id=' + res['result'].items[i].seller_id);
                $(this).find('> a').attr('href', url + '&seller_id=' + res['result'].items[i].seller_id);

                total_items = res['result'].items[i].list.total_items_available + res['result'].items[i].list.total_items_swapped;
                $(this).find('.list-total-items').html(total_items);
                $(this).find('.seller-more-info-available').html(total_items + ' disponível(is)');
                $(this).find('.seller-more-info-swapped').html(res['result'].items[i].list.total_items_swapped + ' substituído(s) automaticamente');
                $(this).find('.seller-more-info-unavailable').html(res['result'].items[i].list.total_items_unavailable + ' não encontrado(s)');
                i++;
            });

            $('#bar a.next-button').show();

            if (data.page == 1) {
                $('#bar a.prev-button').hide();
                $('#bar div.prev-button-disabled').show();
                $('#bar div.next-button-disabled').hide();
                $('#bar a.next-button').show();
            } else if (data.page == 2) {
                $('#bar div.prev-button-disabled').hide();
                $('#bar a.prev-button').show();
            }

            if (!res.result.more_pages) {
                $('#bar a.next-button').hide();
                $('#bar div.next-button-disabled').show();
            } else {
                $('#bar div.next-button-disabled').hide();
                $('#bar a.next-button').show();
            }

            $('#compare-page').html(data.page)
        });
    },
    applyLazyProducts: function($doc)
    {
        $doc.find('img.lazy-product').each(function() {
            var $img = $(this);
            var src = $img.attr('src');
            $img.attr('src', 'http://image.buscape.com/bui-bp7/loading-100x100.gif');
            $('<img />').attr('src', src)
                .bind('load', function(){ $img.attr('src', src); })
                .bind('error', function(){ $img.attr('src', '/components/com_meucarrinho/images/no-image-available-80.jpg'); });
        });
    },
    reloadItems: function(page)
    {
        var data = $('.item-original form').serialize();
        data+= '&page=' + page;
        data += '&limit=3';
        kite.api('meucarrinho/listSimilarOffers',
            data, function(res) {
                var items = res.result.items;
                var i = 0;
                $('#swap-products .item').each(function() {
                    if (i >= res.result.count) {
                        $(this).hide();
                    } else { 
                        $(this).show();
                        $(this).find('img').attr('src', items[i].links.image);
                        $(this).find('.descr').html(items[i].description);
                        $(this).find('.item-unit-price').html(mc.intToPrice(items[i].price));
                        var url = $(this).find('.item-list-swap').attr('href');
                        url = url.substring(0, url.indexOf('offer_id'));
                        url += 'offer_id=' + items[i].object_id;
                        $(this).find('.item-list-swap').attr('href', url);
                        mc.applyLazyProducts($(this));
                    }
                    i++;
                });
            }
        );
    }
};

$(function(){
    mc.applyLazyProducts($('body'));

    $('#swapitem-next').live('click', function() {
        var page = parseInt($('#navigator-page').html()) + 1;
        var total = parseInt($('#navigator-total-page').html());

        if (page <= total) {
            mc.reloadItems(page)
            $(this).show();
            if (page > 0)
                $('#swapitem-prev').show();
        }
        if (page >= total)
            $(this).hide();

        $('#navigator-page').html(page);
    });

    $('#swapitem-prev').live('click', function() {
        var page = parseInt($('#navigator-page').html()) -1;
        var total = parseInt($('#navigator-total-page').html());

        if (page > 0) {
            mc.reloadItems(page)
            $(this).show();
            if (page < total)
                $('#swapitem-next').show();
        }
        if (page < 2)
            $(this).hide();

        $('#navigator-page').html(page);
    });

    /*
    $('a.item-url').live('click', function() {
        var url = $(this).attr('href');
        if (url.charAt(url.length - 1) == '/')
            url += '?tmpl=component';
        else
            url += '&tmpl=componenet'; 

        $.fancybox({
            "overlayOpacity"    : 0.8,
            "overlayColor"      : "black",
            "overlayShow"       : true,
            "scrolling"         : 'no',
            "href"              : url,
            "transitionIn"      : "elastic",
            "transitionOu"      : "elastic",
            "padding"           : 0,
            "width"             : 780,
            "height"            : 450
        });
        return false;
    });
    */

    $('#seller-select').click(function() {
        if ($('#sellers-available').hasClass('visible'))
            $('#sellers-available').hide();
        else
            $('#sellers-available')
                .show()
                .position({
                    of: $(this),
                    my: 'right top',
                    at: 'right bottom'
                });

        $('#sellers-available').toggleClass('visible');
    });
});
