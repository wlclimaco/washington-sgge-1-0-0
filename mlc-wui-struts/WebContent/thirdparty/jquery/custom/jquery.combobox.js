(function($) {
	$.widget("ui.combobox", {
		_create : function() {
			var self = this;
			var select = this.element.hide();
			var input = $("<input>").attr('id', $(this.element).attr('id') + "_input").insertAfter(select).autocomplete(
					{
						source : function(request, response) {
							var matcher = new RegExp(request.term, "i");
							response(select.children("option").map(
									function() {
										var text = $(this).text();
										if (this.value && (!request.term || matcher.test(text)))
											return {
												id : this.value,
												label : text.replace(new RegExp(
														"(?![^&;]+;)(?!<[^<>]*)(" + $.ui.autocomplete.escapeRegex(request.term) + ")(?![^<>]*>)(?![^&;]+;)", "gi"),
														"$1"),
												value : text
											};
									}));
						},
						delay : 0,
						change : function(event, ui) {
							if (!ui.item) {
								// remove invalid value,
						// as it didn't match
						// anything
						//$(this).val(sensus.locale.get("widgets.combobox.prompt"));
						return false;
					}
					select.val(ui.item.id);
					self._trigger("selected", event, {
						item : select.find("[value='" + ui.item.id + "']")
					});

				},
				minLength : 0
					}).addClass("ui-widget ui-widget-content ui-corner-left").focus(function() {
						if ($(this).attr('value') == sensus.locale.get("widgets.combobox.prompt2")) {	
							$(this).attr('value', '');
						}	
					}).keypress(function(event) {
						if (event.keyCode == '13') {
							return false;
						}
					});
			$("<button class='ui-icon-list-ipt-check'>&nbsp;</button>").attr("tabIndex", -1).attr("title", sensus.locale.get("widgets.combobox.title")).insertAfter(input).button( {
				icons : {
					primary : "ui-icon-triangle-1-s"
				},
				text : false
			}).removeClass("ui-corner-all").addClass("ui-corner-right ui-button-icon").click(function() {
				// close if already visible
					if (input.autocomplete("widget").is(":visible")) {
						input.autocomplete("close");
						return false;
					}
					// pass empty string as value to search for, displaying all
					// results
					input.autocomplete("search", "");
					input.focus();
					/**
					 * Modified by Anke Doerfel-Parker - this will stop the
					 * click event from getting through to other event handlers.
					 */
					return false;
				});
		}
	});

})(jQuery);
