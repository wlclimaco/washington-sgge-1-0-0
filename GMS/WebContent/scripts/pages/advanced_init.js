/**
 * @namespace sensus.pages.search.advanced
 * @description The init namespace for the Advanced Search Page.
 */

/**
 * @fileoverview Initializes the advanced search page.
 *
 * @author Cristiane Cobo
 */
$(document).ready(function($) {

	// Set up most page components.
	sensus.util.page.stopProgressBar(1);
	sensus.util.page.initMessaging();
	sensus.util.actiondialog.initActionDialog();


	// Set JQuery UI
	$("a.button").button();

	$(".listcombobox").combobox();

	$("#light-type select").checkbox();

	$("#wattage select").checkbox();

	$("#range select").checkbox();

	$("#color-temperature select").checkbox();

	$("#firmware select").checkbox();

	$('.datepicker').datepicker({dateFormat: sensus.pages.advanced.datePickerFormat(), maxDate: '0d'});


	/**
	 * Append a filter from selectbox to checkbox
	 */
	$("#yui-main").on("focus", ".ui-autocomplete-input", function(event, ui) {

		var that = $(this);

		$(this).autocomplete({
			close : function(event, ui) {

				var sMatcher     = new RegExp( "^" + $.ui.autocomplete.escapeRegex( $(this).val() ) + "$", "i" ),
					bValid       = false,
					sSelect      = $(that).siblings('.listcombobox'),
					iSelectSize  = ((sSelect.children("option").size())-1);


				sSelect.children("option").each(function() {

					if ( $( this ).text().match( sMatcher ) ) {

						this.selected = bValid = true;

			          	$(this).parent()
			          		   .siblings('.ui-listcombobox')
			          		   .append('<li class="checkbox"><input type="checkbox" checked="checked" value="'+ $(this).val() +'"> '+ $(this ).text() +'</li>');

			          	var sFilterId  = $(this).closest('.filter-input').attr('id'),
			          		objFilter  = ($('#'+ sFilterId + ' li:last').children());

						sensus.pages.objFilters.fillObj(sFilterId, 'checkbox', objFilter);

						sensus.widgets.datatable.reloadTable(objTable);

			          	$(this).parent().next().val(iSelectSize+' more...');

						if (iSelectSize == 0) {

							$(this).parent().next().next().remove();
							$(this).parent().next().remove();

						}

						$(this).remove();

			          	return false;
					}
				});
			}
		});

	});

	$(".listcombobox").each(function(i, e) {

		var oOption      = $(e).find("option"),
			oComponent   = $(e).prev(),
			iSelectSize  = oOption.length;

		if (iSelectSize < 6) {

			$(e).next().hide().next().hide();

		}

		oOption.each(function(index, element) {

			if (index > 0) {

				$('<li class="checkbox"><input type="checkbox" value="'+$(element).val()+'"> '+$(element).text()+ '</li>').appendTo(oComponent);

			}

			$(element).remove();

			if (index == 6) {
				return false;
			}

		});

		// set "more..." text to field
		$(e).next().val((parseInt(iSelectSize)-7)+' more...');

	});

	//Spindown
	$('.spindown').click(function(e) {
		e.preventDefault();

		$(this).parent().next('.spindown_child').toggle('blind',null,500);

		if ($('span', this).hasClass('ui-icon-triangle-1-e')) {

			$('span', this).switchClass('ui-icon-triangle-1-e', 'ui-icon-triangle-1-s', 500);

		} else {

			$('span', this).switchClass('ui-icon-triangle-1-s','ui-icon-triangle-1-e', 500);

		}

	});

	$(".datepicker").change(function() {
		$(this).removeClass("error");
	});

	/** * jQuery dataTable setup ** */
	$("#search").click(function(e) {
		e.preventDefault();

		sensus.pages.advanced.setUrl();

		var oDateBefore = $("#before .datepicker"),
			oDateAfter = $("#after .datepicker");

		if (sensus.pages.advanced.validateDate(oDateBefore)
				&& sensus.pages.advanced.validateDate(oDateAfter)) {

			sensus.commons.lib.ajax.do_load($.extend({}, sensus.commons.lib.ajax.param, {
				sUrl : "smartpoint/ajax.list.action?" + $.address.queryString(),
				bFilter : true
			}));

		}
	});

	/** * jQuery dataTable setup ** */
	$("#save_search").click(function(e) {
		e.preventDefault();

		sensus.pages.advanced.setUrl();

		var oDateBefore  = $("#before .datepicker"),
			oDateAfter   = $("#after .datepicker");

		if (sensus.pages.advanced.validateDate(oDateBefore) && sensus.pages.advanced.validateDate(oDateAfter)) {

			sensus.util.actiondialog.launchActionDialog("saveSearch", sensus.pages.smartpoint.dialogSettings["saveSearch"]);
			sensus.pages.smartpoint.isAdvancedSearch = true;

		}

	});
});