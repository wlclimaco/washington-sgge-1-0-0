/**
 * @param dates
 *	{
 *		day  : [],
 *		week : [],
 *		month: [],
 *		year : [],
 *		ytd  : boolean,
 *	}
 * @param fnClickHandler
 *          function
 */

$.widget( "ui.dateFilter",
{
    // Default options.
    options:
    {
        day             : $.pgsi.locale.get("analytics.page.day"),
        week            : $.pgsi.locale.get("analytics.page.week"),
        month           : $.pgsi.locale.get("analytics.page.month"),
        year            : $.pgsi.locale.get("analytics.page.year"),
        ytd             : "YTD",
        config          : {
            day     : 1,
            week    : 8,
            year    : 365
        },
        fnClickHandler  : null
    },

    _create: function(options)
    {
        var $dateFilter = '<div class="right">'
        + '<form id="analytics-filter-date">'
            + '<input type="text" id="datepicker1" name="datepicker1" class="date rounded datepicker validate[required]" value="" data-prompt-position="topLeft"> - '
            + '<input type="text" id="datepicker2" name="datepicker2" class="date rounded datepicker validate[required]" value="" data-prompt-position="bottomLeft : -90,60">'
            + '<input id="submit-date" class="submit" type="submit" value="&raquo;" />'
        + '</form>'
        + '</div><ul id="date-tag" class="link-list">';

        for (var i in this.options.dates)
        {
            for (var j in this.options.dates[i])
            {
                var sId     = this.options.dates[i][j]  + i[0];
                var sLabel  = this.options.dates[i][j]  + this.options[i];

                if (this.options[i] == this.options.month)
                {
                    $dateFilter += '<li class="' + this.options[i] + '" id="' + sId + '"><a class="' + sId + '" href="#">' + sLabel + '</a></li>';
                }
                else if (this.options[i] == this.options.year)
                {
                    $dateFilter += '<li class="' + this.options[i] + '" id="' + sId  + '"><a class="' + (this.options.config[i] * this.options.dates[i][j]) + '" href="#">' + sLabel + '</a></li>';
                }
                else
                {
                    $dateFilter += '<li class="' + this.options[i] + '" id="' + sId  + '"><a class="' + (this.options.config[i] * this.options.dates[i][j] - 1) + '" href="#">' + sLabel + '</a></li>';
                }
            }
        }

        $dateFilter += '</ul>';
        $dateFilter = $($dateFilter);

        if (this.options.dates.ytd)
        {
            var sId = this.options.ytd.toLowerCase();
            $('<li id="' + sId + '"><a class="' + sId + '" href="#">'    + this.options.ytd + '</a></li>').insertAfter($dateFilter.find("li.m:last"));
        }

        if ($.isFunction(this.options.fnClickHandler))
        {
            $("a", $dateFilter).click(this.options.fnClickHandler);
        }

        this.element.append($dateFilter);
    },

    hideOptions: function(options)
    {
        for (var i in options)
        {
            for (var j in options[i])
            {
                var sId = options[i][j] + this.options[i];
                this.element.find("li#" + sId).hide();
            }
        }
    },

    showAllOptions: function()
    {
        this.element.find("li:hidden").show();
    }

});
