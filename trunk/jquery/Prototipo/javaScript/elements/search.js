$(function() {
    var changes = [
        {'base':'a', 'letters':/[\u0061\u24D0\uFF41\u1E9A\u00E0\u00E1\u00E2\u1EA7\u1EA5\u1EAB\u1EA9\u00E3\u0101\u0103\u1EB1\u1EAF\u1EB5\u1EB3\u0227\u01E1\u00E4\u01DF\u1EA3\u00E5\u01FB\u01CE\u0201\u0203\u1EA1\u1EAD\u1EB7\u1E01\u0105\u2C65\u0250]/g},
        {'base':'e', 'letters':/[\u0065\u24D4\uFF45\u00E8\u00E9\u00EA\u1EC1\u1EBF\u1EC5\u1EC3\u1EBD\u0113\u1E15\u1E17\u0115\u0117\u00EB\u1EBB\u011B\u0205\u0207\u1EB9\u1EC7\u0229\u1E1D\u0119\u1E19\u1E1B\u0247\u025B\u01DD]/g},
        {'base':'i', 'letters':/[\u0069\u24D8\uFF49\u00EC\u00ED\u00EE\u0129\u012B\u012D\u00EF\u1E2F\u1EC9\u01D0\u0209\u020B\u1ECB\u012F\u1E2D\u0268\u0131]/g},
        {'base':'o', 'letters':/[\u006F\u24DE\uFF4F\u00F2\u00F3\u00F4\u1ED3\u1ED1\u1ED7\u1ED5\u00F5\u1E4D\u022D\u1E4F\u014D\u1E51\u1E53\u014F\u022F\u0231\u00F6\u022B\u1ECF\u0151\u01D2\u020D\u020F\u01A1\u1EDD\u1EDB\u1EE1\u1EDF\u1EE3\u1ECD\u1ED9\u01EB\u01ED\u00F8\u01FF\u0254\uA74B\uA74D\u0275]/g},
        {'base':'u','letters':/[\u0075\u24E4\uFF55\u00F9\u00FA\u00FB\u0169\u1E79\u016B\u1E7B\u016D\u00FC\u01DC\u01D8\u01D6\u01DA\u1EE7\u016F\u0171\u01D4\u0215\u0217\u01B0\u1EEB\u1EE9\u1EEF\u1EED\u1EF1\u1EE5\u1E73\u0173\u1E77\u1E75\u0289]/g}
    ];

    function remove_diacr(str) {
        str = str.toLowerCase();
        for (var i=0; i<changes.length; i++) {
            str = str.replace(changes[i].letters, changes[i].base);
        }
        return str;
    }

    $('.search')
        .waterMark()
        .data('timeout', null);

    kite.api('meucarrinho/getCategoryTree','', function(res) {
        var categories = new Array();
        var limit = 10;
        if (res && res.result) {
            for (i = 0; i < res.result.count; i++) {
                categories[i] = {
                    s: remove_diacr(res.result.items[i].description),
                    v: res.result.items[i].description
                }
            }
        }

        $('.search').autocomplete({
            source: function(request, response) {
                var count = 0, res = [];
                var q = remove_diacr(request.term);
                for (var i in categories) {
                    var pos = categories[i]['s'].search(q);
                    if (pos >= 0) {
                        var found = categories[i]['v']
                        var found_strong = found.substring(0, pos);
                        found_strong += '<strong>' + found.substring(pos, pos + q.length) + '</strong>';
                        found_strong += found.substring(pos + q.length);

                        res.push({label: found_strong, value: found});
                        count++;
                        if (count >= limit) break;
                    }
                }
                response(res);
            },
            minLength: 2
        });
    });
   
    $('#form-search').submit(function() {
        //_gaq.push(['_trackEvent', 'Busca', $(this).class()]);
        _gaq.push(['_trackEvent', 'Busca']);
    });
 
/*
    $('.search').keyup(function() {
        if (!$(this).val()) {
            $('.search-results').remove();
            $('.content').show();
        } else {
            clearTimeout($(this).data('timeout'));
            $(this).data('timeout', setTimeout(function() {
                $.ajax({
                    url: $('.search').parents('form').attr('action') + '?tmpl=component',
                    data: $('.search').parents('form').serialize(),
                    success: function(content) {
                        $('.search-results').remove();
                        $('#content')
                            .after('<div class="search-results"></div>')
                            .hide();
                        $('.search-results')
                            .html(content)
                    }
                })
            }, 500))
        }
    });
*/    
});
