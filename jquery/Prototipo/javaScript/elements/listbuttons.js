$(function(){
    $('.item .item-list').disableSelection();

/*
    $('div.item-promoted form').change(function() {
        $item = $(this).parents('.item-promoted');
        mc.setProduct($item);
    });
*/

    $('.item-list-qtd').keypress(function(e) {
        if (e.which!=8 && e.which!=9 && (e.which<48 || e.which>57))
            return false;
        
    });

    $('.item form').change(function() {
        var $item = $(this).parents('.item');
        var $item_list_qtd = $item.find('.item-list-qtd');
        var id = $item.attr('id');
        var quantity = parseInt($item.find('input[name="quantity"]').val());
        if (quantity > 99) { 
            quantity = 99;
            $item.find('input[name="quantity"]').val(99);
        }
        if (quantity == '')
            quantity = 0;

        $item.find('input[name="quantity"]').hide();
        $item.find('div.item-list-qtd').show();

        var price = $item.find('input[name="unit_price"]').val();

        if (price) {
            var priceorder = price*quantity;
            priceorder = mc.intToPrice(priceorder);
            $item.find('span.item-qtd').html(quantity);
            $item.find('span.item-order').html(priceorder);
        } else {
            $item.find('input[name="quantity"]').val(quantity);
        }
        if (!quantity) {
            $item.find('.item-list .item-list-add').hide();
            $item.find('.item-list .item-list-remove').hide();
            $item.find('.item-list .item-list-quantity-unit').hide();
            $item.find('.item-list .item-list-bigadd').show();
        }
        mc.setProduct($item);
    });

    $('.item .item-list-bigadd, div.item-promoted .item-list-bigadd').live('click', function(){
        if ($(this).hasClass('no-list-hash')) {
            $.fancybox({
                "overlayOpacity"    : 0.8,
                "overlayColor"      : "black",
                "overlayShow"       : true,
                "href"              : $(this).attr('href') + "&no_html=1",
                "transitionIn"      : "elastic",
                "transitionOu"      : "elastic",
                "padding"           : 10,
                "width"             : 400,
                "height"            : 400
            });
            return false;            
        } else if (!$(this).hasClass('login')) {
            var $item = $(this).parents('.item');
            var id = $item.attr('id');
 
            var quantity = 1;

            $item.find('.item-list .item-list-bigadd').hide();
            $item.find('.item-list .item-list-add').show();
            $item.find('.item-list .item-list-remove').show();
            $item.find('.item-list .item-list-quantity-unit').show();

            var price = $item.find('input[name="unit_price"]').val();
            if (price) {
                var priceorder = price*quantity;
                priceorder = mc.intToPrice(priceorder);
                $item.find('span.item-qtd').html(quantity);
                $item.find('span.item-order').html(priceorder);
                $item.find('input[name="quantity"]').val(quantity);
            } else {
                $item.find('div.item-list-qtd span.item-qtd').html(quantity);
                $item.find('input[name="quantity"]').val(quantity);
            }

            mc.setProduct($item);
            return false;
        }
    });
    $('.item .item-list-add, div.item-promoted .item-list-add').live('click', function(){
        var $item = $(this).parents('.item');
        var $item_list_qtd = $item.find('.item-list-qtd');
        var id = $item.attr('id');
        var quantity = parseInt($item.find('input[name="quantity"]').val()) + 1;
        if (quantity > 99) quantity = 99;

        $item.find('input[name="quantity"]').hide();
        $item.find('div.item-list-qtd').show();

        var price = $item.find('input[name="unit_price"]').val();
        if (price) {
            var priceorder = price*quantity;
            priceorder = mc.intToPrice(priceorder);
            //$item.find('input[name="quantity"]').val(quantity + ' ' + priceorder);
            $item.find('span.item-qtd').html(quantity);
            $item.find('span.item-order').html(priceorder);
            $item.find('input[name="quantity"]').val(quantity);
        } else {
            $item.find('div.item-list-qtd span.item-qtd').html(quantity);
            $item.find('input[name="quantity"]').val(quantity);
        }
            

        mc.setProduct($item);
        return false;
    });

    $('div.item-list-qtd').click(function() {
        $(this)
            .hide()
            .parent().find('input[name="quantity"]').show().focus();
    });
    $('input[name="quantity"]').blur(function() {
        $(this)
            .hide()
            .parent().find('div.item-list-qtd').show();
    });
    $('input[name="quantity"]').keydown(function(event) {
        if (event.which == 13) {
            var $item = $(this).parents('.item');
            var $item_list_qtd = $item.find('.item-list-qtd');
            var id = $item.attr('id');
            var quantity = parseInt($item.find('input[name="quantity"]').val());
            if (quantity > 99) quantity = 99;

            $item.find('input[name="quantity"]').hide();
            $item.find('div.item-list-qtd').show();

            var price = $item.find('input[name="unit_price"]').val();
            if (price) {
                var priceorder = price*quantity;
                priceorder = mc.intToPrice(priceorder);
                $item.find('span.item-qtd').html(quantity);
                $item.find('span.item-order').html(priceorder);
            } else {
                $item.find('input[name="quantity"]').val(quantity);
            }
            mc.setProduct($item);

            return false;
        } 
    });

    $('.item .item-list-remove, div.item-promoted .item-list-remove').live('click', function(){
        var $item = $(this).parents('.item');
        var $item_list_qtd = $item.find('.item-list-qtd');
        var id = $item.attr('id');
        var quantity = parseInt($item.find('input[name="quantity"]').val()) - 1;
        if (quantity > 99) quantity = 99;

        $item.find('input[name="quantity"]').hide();
        $item.find('div.item-list-qtd').show();

        if(quantity) {
            var price = $item.find('input[name="unit_price"]').val();
            if (price) {
                var priceorder = price*quantity;
                priceorder = mc.intToPrice(priceorder);
                //$item.find('input[name="quantity"]').val(quantity + ' ' + priceorder);
                $item.find('span.item-qtd').html(quantity);
                $item.find('span.item-order').html(priceorder);
                $item.find('input[name="quantity"]').val(quantity);
            } else {
                $item.find('div.item-list-qtd span.item-qtd').html(quantity);
                $item.find('input[name="quantity"]').val(quantity);
            }

        } else {
            $item.find('.item-list .item-list-add').hide();
            $item.find('.item-list .item-list-remove').hide();
            $item.find('.item-list .item-list-quantity-unit').hide();
            $item.find('.item-list .item-list-bigadd').show();
            $item.find('input[name="quantity"]').val(quantity);
        }

        mc.setProduct($item);
        return false;
    });
})
