(function($) {    
    
    function formatTime(hour, minute, iso)
    {
        var printMinute = minute;
        if (minute < 10) printMinute = '0' + minute;

        if (iso) {
            var printHour = hour
            if (printHour < 10) printHour = '0' + hour;
            return printHour + ':' + printMinute;
        } else {
            var printHour = hour % 12;
            if (printHour == 0) printHour = 12;
            var half = (hour < 12) ? 'am' : 'pm';
            return printHour + ':' + printMinute + half;
        }
    }
    
    function parseTime(text)
    {
        var match = match = /(\d+)\s*[:\-\.,]\s*(\d+)\s*(am|pm)?/i.exec(text);
        if (match && match.length >= 3) {
            var hour = Number(match[1]);
            var minute = Number(match[2])
            if (hour == 12 && match[3]) hour -= 12;
            if (match[3] && match[3].toLowerCase() == 'pm') hour += 12;
            return {
                hour:   hour,
                minute: minute
            };
        } else {
            return null;
        }
    }
    
    
    
    function renderTimeSelect(element, options)
    {
        var selection = options.selection && parseTime(options.selection);
        if (selection) {
            selection.minute = Math.floor(selection.minute / 30.0) * 30;
        }
        var startTime = options.startTime &&
            (options.startTime.hour * 60 + options.startTime.minute);
        
        var scrollTo;   //Element to scroll the dropdown box to when shown
        var ul = $('<ul />');
        for (var hour = 0; hour < 24; hour++) {
            for (var minute = 0; minute < 60; minute += 30) {
                if (startTime && startTime > (hour * 60 + minute)) continue;
                
                (function() {
                    var timeText = formatTime(hour, minute, options.isoTime);
                    var fullText = timeText;
                    if (startTime != null) {
                        var duration = (hour * 60 + minute) - startTime;
                        if (duration < 60) {
                            fullText += ' (' + duration + ' mins)';
                        } else if (duration == 60) {
                            fullText += ' (1 hr)';
                        } else {
                            fullText += ' (' + (duration / 60.0) + ' hrs)';
                        }
                    }
                    var li = $('<li />').append(
                        $('<a href="javascript:;">' + fullText + '</a>')
                        .click(function() {
                            if (options && options.selectTime) {
                                options.selectTime(timeText);
                            }
                        }).mousemove(function() {
                            $('li.selected', ul).removeClass('selected');
                        })
                    ).appendTo(ul);
                    
                    //Set to scroll to the default hour, unless already set
                    if (!scrollTo && hour == options.defaultHour) {
                        scrollTo = li;
                    }
                    
                    if (selection &&
                        selection.hour == hour &&
                        selection.minute == minute)
                    {
                        //Highlight selected item
                        li.addClass('selected');
                        
                        //Set to scroll to the selected hour
                        //
                        //This is set even if scrollTo is already set, since
                        //scrolling to selected hour is more important than
                        //scrolling to default hour
                        scrollTo = li;
                    }
                })();
            }
        }
        if (scrollTo) {
            //Set timeout of zero so code runs immediately after any calling
            //functions are finished (this is needed, since box hasn't been
            //added to the DOM yet)
            setTimeout(function() {
                //Scroll the dropdown box so that scrollTo item is in
                //the middle
                element[0].scrollTop =
                    scrollTo[0].offsetTop - scrollTo.height() * 2;
            }, 0);
        }
        element.empty().append(ul);
    }
    
    
    $.fn.calendricalTime = function(options)
    {
        options = options || {};
        options.padding = options.padding || 4;
        
        return this.each(function() {
            var element = $(this);
            var div;
            var within = false;
            
            element.bind('focus click', function() {
                if (div) return;

                var useStartTime = options.startTime;
                if (useStartTime) {
                    if (options.startDate && options.endDate &&
                        !areDatesEqual(parseDate(options.startDate.val()),
                            parseDate(options.endDate.val())))
                        useStartTime = false;
                }

                var offset = element.position();
                div = $('<div />')
                    .addClass('calendricalTimePopup')
                    .mouseenter(function() { within = true; })
                    .mouseleave(function() { within = false; })
                    .mousedown(function(e) {
                        e.preventDefault();
                    })
                    .css({
                        position: 'absolute',
                        left: offset.left,
                        top: offset.top + element.height() +
                            options.padding * 2
                    });
                if (useStartTime) {
                    div.addClass('calendricalEndTimePopup');
                }

                element.after(div); 
                
                var opts = {
                    selection: element.val(),
                    selectTime: function(time) {
                        within = false;
                        element.val(time);
                        div.remove();
                        div = null;
                    },
                    isoTime: options.isoTime || false,
                    defaultHour: (options.defaultHour != null) ?
                                    options.defaultHour : 8
                };
                
                if (useStartTime) {
                    opts.startTime = parseTime(options.startTime.val());
                }
                
                renderTimeSelect(div, opts);
            }).blur(function() {
                if (within){
                    if (div) element.focus();
                    return;
                }
                if (!div) return;
                div.remove();
                div = null;
            });
        });
    },
    
    $.fn.calendricalTimeRange = function(options)
    {
        if (this.length >= 2) {
            $(this[0]).calendricalTime(options);
            $(this[1]).calendricalTime($.extend({
                startTime: $(this[0])
            }, options));
        }
        return this;
    };

})(jQuery);