$.widget("sc.filters", {

	_parameters	: {},

	_objFiltersCustomize : [],

	options : {

		filters				: {},

		createReload		: false,

		table				: null,

		hasSideSlide 		: true,
		isOpenSlide 		: true,
		hasCustomize		: false,

		title 				: "",

		openFirstFilters	: 3,
		firstOptions		: 6,
		maxCharacters		: 17,

		tagsDiv				: ".active-filters-list > div.first",

		tagsMapDiv			: "#map-list .filter-results-container",

		createTitle			: function () {

			return '<h4>' + this.title + '</h4>';
		},

		defaultSearchNameLabel : "commons.pages.searchName"
	},

	_create : function() {

		var $filter = [];
		var filter;
		var count = 0;
		var i;
		var filters = this.options.filters;
		var iFirstFilters = this.options.openFirstFilters;
		var length = filters.length;
		var isOpen;
		var type;

		this._parameters = {};
		this._objFiltersCustomize = [];

		this.defaultSearchNameLabel = sensus.locale.get(this.options.defaultSearchNameLabel);

		$filter.push('<div class="yui-b"><form class="views-vert">');
		$filter.push(this.options.createTitle());

		// Side slide
		if (this.options.hasSideSlide) {

			$filter.push(this._createSideSlide());
		}

		$filter.push('<div class="filter-vert rounded">');

		// Filters
		for (i in filters) {

			filter = filters[i];

			filter.id = i;

			$filter.push('<div id="' + i + '" class="filter-input ' + filter.type + ' ui-widget">');

			isOpen = count < iFirstFilters;

			switch (filter.type) {

			case "search" :

				type = "textFilter";

				$filter.push(this._createSearchFilters(filter, isOpen, i));

				break;

			case "options" :

				this._parameters[filter.urlParameter] = filter;

				type = "checkBox";

				$filter.push(this._createOptionFilters(filter, isOpen, i));
				break;
			}

			if (i != "search") {

				this._objFiltersCustomize.push({
					name : sensus.locale.get(filter.title),
					session : i,
					type : type
				});

			}

			$filter.push('</div>');

			count = count + 1;
		}

		// Whether has customize button
		if (this.options.hasCustomize) {

			$filter.push(this._createCustomizeButton());
		}

		$filter.push('</div></form></div>');

		// Set Filters on DOM
		this.element.html($filter.join(''));

		// Set events
		this._delegateSearchFilterEvents();
		this._optionFilterEvent();
		this._delegateTagEvents();
		this._toggleEvent();
		this._slideEvent();
		this._validationRemoveEvent();
		this._customizeFiltersEvent();

		// Datepicker
		this._startDatePicker();

		// jQuery Combobox and auto complete for Option Filters
		this._createCombobox();
		this._autocomplete();

		// Load filters from url
		this.loadFilters();

		if (this.options.createReload) {

			this.reload();
		}
	},

	_createCombobox : function () {

		$("select.listcombobox").combobox();
		var filters = this.options.filters;
		var aux = this.options;
		var firstOptions = this.options.firstOptions;
		$(this.element).find("input.ui-autocomplete-input").each(function() {
			var id = $(this).closest('.filter-input').attr('id');
			iSelectSize = filters[id].dataList.length - firstOptions;
			$(this).val(iSelectSize + ' ' + sensus.locale.get('filter.more'));
		});
	},

	_customizeFiltersEvent : function () {

		var oFiltersCustomize = this._objFiltersCustomize;

		$('#custom-filter-action').click(function(e) {

			e.preventDefault();

			$('#customize-filter').wCustomize('Filters', null, oFiltersCustomize, 'smartpointlist', null);
		});
	},

	_createCustomizeButton : function () {

		return '<div id="customFilter" class="filter-input advanced ui-widget">'
			+ '<a class="button ui-button ui-widget ui-state-default ui-corner-all '
			+ 'ui-button-text-only" id="custom-filter-action" href="" role="button" aria-disabled="false">'
			+ '<span class="ui-button-text">' + sensus.locale.get('filter.customize') + '</span></a></div>';
	},

	_createSideSlide : function() {

		return '<div class="side-slide-toggle">'
				+ '<span class="hide-side-slide ui-icon ui-icon-arrowthick-1-w"'
					+ (!this.options.isOpenSlide ? ' style="display:none"' : "") + '></span>'
				+ '<span class="show-side-slide ui-icon ui-icon-arrowthick-1-e"'
					+ (this.options.isOpenSlide ? ' style="display:none"' : "") + '></span>'
			+ '</div>';
	},

	_createSearchFilters : function(flt, isOpen) {

		var inputs = flt.inputs;
		var _get = sensus.locale.get;
		var $filter = [];
		var input;
		var i;

		// Filter Title
		if (flt.title) {

			$filter.push(this._createFilterTitle(_get(flt.title), isOpen));
		}

		$filter.push('<ul class="collapse" ' + (isOpen ? "" : 'style="display:none;"') + '>');

		for (i in inputs) {

			input = inputs[i];

			$filter.push("<li class='filter-search-column'>");

			// Label
			if (input.label) {

				$filter.push(this._createLabelField(_get(input.label), input.span));
			}

			// Span
			if (input.span) {

				$filter.push(this._createSpanField(_get(input.label)));
			}

			// Field
			switch (input.type) {
			case "text":

				this._parameters[input.name] = input;

				$filter.push(this._createTextField(input.name, input.clazz, input.style, input.maxsize,
						input.hint || (input.hintMessageKey ? _get(input.hintMessageKey) : "")));
				break;

			case "select":
				$filter.push(this._createSelectField(input.name, input.options));
				break;
			}

			// Field Button
			if (input.goButtonLabel) {

				$filter.push(this._createGoButton(_get(input.goButtonLabel)));
			}

			$filter.push("</li>");
		}

		// Filter button
		if (flt.goButtonLabel) {

			$filter.push("<li>" + this._createGoButton(_get(flt.goButtonLabel)) + "</li>");
		}

		$filter.push('</ul>');
		return $filter.join('');
	},

	_createLabelField : function(label, isHide) {

		return "<label " + (isHide ? "style='display:none;'" : "") + ">" + label + "</label>";
	},

	_createSpanField : function(label) {

		return "<span>" + label + "</span> ";
	},

	_createGoButton : function(label) {

		return "<button class='go-button'>" + label + "</button>";
	},

	_createTextField : function(name, clazz, style, maxsize, value) {

		return "<input id='" + name + "' name='" + name + "' "
			+ (clazz 	? (" class='" 		+ clazz 	+ "'") : "")
			+ (style 	? (" style='" 		+ style 	+ "'") : "")
			+ (maxsize 	? (" maxlength='" 	+ maxsize 	+ "'") : "")
			+ (value 	? (" value='" 		+ value 	+ "'") : "")
			+ " type='text'/>";
	},

	_createSelectField : function(name, options) {

		var length = options.length;
		var $select = ['<select ' + (length < 2 ? 'style="display:none;"' : '') + 'name="' + name + '">'];
		var i = 0;
		var get = sensus.locale.get;
		var opt;

		for (; i < length; i = i + 1) {

			opt = options[i];
			$select.push('<option value="' + opt.value + '" name="' + opt.validation + '">' + get(opt.label) + '</option>');
		}

		$select.push("</select>");

		return $select.join('');
	},

	_createFilterTitle : function(title, isOpen) {

		return '<label class="toggle filter-title ' + (isOpen ? 'on' : 'off') + '">' + title + '</label>';
	},

	_createOptionFilters : function(flt, isOpen, filterId) {

		var $filter = [];
		var iFirstOptions = this.options.firstOptions;
		var propertyTitle = flt.propertyTitle;
		var propertyValue = flt.propertyValue;
		var dataList = flt.dataList || [];
		var length = dataList.length;
		var maxCharacters = this.options.maxCharacters;
		var $select;
		var i = 0;
		var title;
		var value;
		var data;

		// Filter Title
		$filter.push(this._createFilterTitle(sensus.locale.get(flt.title), isOpen));

		$filter.push('<div class="collapse checkBoxUl" ' + (isOpen ? "" : 'style="display:none;"') + '>');

		// All Checked
		$filter.push(this._createAllCheckbox(flt, true));

		// First filters
		$filter.push('<ul class="ui-listcombobox">');
		for (; i < iFirstOptions && i < length; i = i + 1) {

			data = dataList[i];
			title = data[propertyTitle] || "";
			value = data[propertyValue];

			$filter.push('<li class="checkbox" title="' + title + '">'
					+ '<label><input type="checkbox" class="checkbox" value="' + value + '"/> <span>'
					+ (title.length > maxCharacters ? (title.substr(0, maxCharacters) + '...') : title)
					+ '</span></label></li>');
		}
		$filter.push('</ul>');

		// Select element
		if (i < length) {

			$select = ['<select id="' + filterId + '_slc" class="listcombobox" style="display:none">'];
			for (; i < length; i = i + 1) {

				data = dataList[i];
				title = data[propertyTitle] || "";
				value = data[propertyValue];

				$select.push('<option value="' + value + '">' + title + '</option>');
			}
			$select.push('</select>');
			$filter.push($select.join(''));
		}

		$filter.push('</div>');

		return $filter.join('');
	},

	_createAllCheckbox : function(flt, isChecked) {

		return '<ul class="all-checked"><li class="checkbox">'
				+ '<label><input type="checkbox" ' + (isChecked ? 'checked="checked"' : '') + ' value="ALL"/> '
				+ '<strong>' + sensus.locale.get(flt.allLabel) + '</strong></label></li></ul>';
	},

	_optionFilterEvent : function () {

		var $filter = $(this.element);
		var fnSelectFilterEvent = this._selectFilterEvent;

		// Check-boxes Event
		$filter.on("click", "input:checkbox", function(e) {

			// Whether return false prevent event to keep ALL checked
			if (fnSelectFilterEvent($(this), $filter, true) === false) {

				e.preventDefault();
			}
		});
	},

	_searchFilterEvent : function ($filter, $filterDiv, filter, event) {

		var $form = $filterDiv.find("form");;
		var $fields = $filter.find("input, select");
		var length = $fields.length;
		var $field;
		var val;
		var name;
		var i = 0;
		var oFilterValue = {};
		var sFilterValue;
		var filterValue;
		var showPrompt;

		event.preventDefault();

		// Validate
		if (filter.validate) {

			if (filter.validate($form, $filter)) {

				return false;
			}
		}

		// Load Filter Values
		for (; i < length; i = i + 1) {

			$field = $($fields[i]);

			val = $field.val();
			name = $field.attr("name");

			if (!oFilterValue[name]) {

				oFilterValue[name] = [];
			}

			oFilterValue[name].push(val);
		}

		for (i in oFilterValue) {

			filterValue = oFilterValue[i];
			sFilterValue = filterValue.join("|");

			// Add parameters
			$.fn.pageLoader.parameter(i, encodeURI(sFilterValue));
		}

		// Add tag
		if ($filterDiv.filters("addSearchTag", $filter)) {

			// Reload table
			$filterDiv.filters("reload");
		}
	},

	_delegateSearchFilterEvents : function() {

		var $filterDiv = $(this.element);
		var $searchContainer = $filterDiv.find("div.filter-input.search");
		var fnSearchFilterEvent = this._searchFilterEvent;
		var filters = this.options.filters;

		$searchContainer.on("click", ".go-button", function(e) {

			var $filter = $(this).parents("div.filter-input.search");
			var filter = filters[$filter.attr("id")];
			var $input = $filter.find("input");

			if ($input.hasClass("input-clear")) {

				$input.val("").removeClass("input-clear");
			}

			fnSearchFilterEvent($filter, $filterDiv, filter, e);
		});

		$searchContainer.on("keypress", "input", function(e) {

			var $filter;
			var filter;

			if (e.which == 13) {

				$filter = $(this).parents("div.filter-input.search");
				filter = filters[$filter.attr("id")];

				fnSearchFilterEvent($filter, $filterDiv, filter, e);
			}
		});
	},

	_selectFilterEvent : function($checkbox, $filter, bReload) {

		var $parent = $checkbox.parents("div.filter-input");
		var id = $parent.attr("id");
		var value = $checkbox.val();

		if (value == "ALL") {

			// Unchecked options
			$parent.find(".ui-listcombobox input:checked").prop("checked", false);

			// Remove all tags options by filter type
			$filter.filters("removeAllTags", id);

			// Remove all parameter
			$filter.filters("removeParameter", id, null);

			// Reload table
			if (bReload) {

				$filter.filters("reload");
			}

			// Return true when checked
			return $checkbox.is(":checked");

		} else {

			// Unchecked the ALL option
			$parent.find(".all-checked input:checked").prop("checked", false);

			// Whether is checked
			if ($checkbox.is(':checked')) {

				// Add parameter
				$filter.filters("addParameter", id, value);

				// Add Tag
				$filter.filters("addOptionTag", $checkbox);

			} else {

				// Remove parameter
				$filter.filters("removeParameter", id, value);

				// Remove Tag
				$filter.filters("removeTag", id, value);
			}

			// Whether no options selected
			if ($parent.find(".ui-listcombobox input:checked").length == 0) {

				// Check the ALL option
				$parent.find(".all-checked input:checkbox").prop("checked", true);
			}
		}

		// Reload table
		if (bReload) {

			$filter.filters("reload");
		}
	},

	_delegateTagEvents : function () {

		var $filter = $(this.element);
		var fnRemoveTagEvent = this._removeTagEvent;

		var fnRemove = function (e) {

			e.preventDefault();

			fnRemoveTagEvent($(this), $filter, true);

			// IE8 javascript throws exceptions
			e.stopPropagation();
		};

		// Remove Tag and Filter
		$(this.options.tagsDiv).find(".filter-container").on("click", "a.clear, span.type.remove", fnRemove);
		$(this.options.tagsMapDiv).find(".filter-container").on("click", "a.clear, span.type.remove", fnRemove);
	},

	getInput : function ($tag) {

		var clazz = $tag.attr("class").split(" ");

		if (clazz.length > 1) {

			return $(this.element).find("#" + clazz[1]);
		}

		return $(this.element).find("#" + clazz[0] + " input[value='" + $tag.attr("name") + "']");
	},

	_removeTagEvent : function ($tag, $filterDiv, bReload, keepFilters) {

		var $parent = $tag.parents("li");
		var $input;
		var filterId;
		var filter;
		var $tags;
		var sValue;
		var length;
		var i;
		var disabledCount;
		var aIds;

		if ($tag.hasClass("clear")) {

			if (keepFilters && keepFilters.length) {

				$tags = $parent.parent().find("li:not(li:last,." + keepFilters.join(",.") + ")");

			} else {

				$tags = $parent.parent().find("li:not(li:last)");
			}

			length = $tags.length;
			disabledCount = 0;

			for (i = 0; i < length; i = i + 1) {

				$tag = $($tags[i]);

				$input = $filterDiv.filters("getInput", $tag);

				if ($input.is(":disabled")) {

					disabledCount += 1; // count the filters disabled
					continue;
				}

				if ($input.is(":checked")) {

					// Remove filter of option type
					$input.prop("checked", false);

				} else if ($input.attr("type") == "text") {

					// Remove filter of search type
					$input.val("");
				}

				// Remove parameter and tag
				$filterDiv.filters("removeTag", $tag.attr("class").split(" ")[0], $tag.attr("name"), $tag);
				$filterDiv.filters("removeParameter", $tag.attr("class").split(" ")[0], null, $input.attr("name"));
			}

			// Check all 'ALL' inputs
			$filterDiv.find(".all-checked input:not(:checked,:disabled)").prop("checked", true);

			// Could call the table reload
			if (bReload && length > disabledCount) {

				$filterDiv.filters("reload");
			}

		} else {

			$input = $filterDiv.filters("getInput", $parent);

			if ($input) {

				if ($input.is(":checked")) {

					// Option Filter Type: Remove parameter, tag and filter than could call the table reload
					$filterDiv.filters("selectFilterOption", $input, bReload);

				} else if ($input.attr("type") == "text") {

					// Search Filter Type:
					// Remove filter
					$input.val("");

					filterId = $parent.attr("class").split(" ")[0];
					filter = $filterDiv.filters("option").filters[filterId];

					// Remove whether has associated tags
					if (filter.joinTags) {

						$tags = $parent.parent().find("li." + filterId);
						length = $tags.length;

						for (i = 0; i < length; i = i + 1) {

							$tag = $($tags[i]);
							$input = $filterDiv.filters("getInput", $tag);
							$input.val("");

							// Remove parameter and tag
							$filterDiv.filters("removeTag", filterId, $tag.attr("name"), $tag);
							$filterDiv.filters("removeParameter", filterId, null, $input.attr("name"));
						}
					}

					// Remove parameter and tag
					$filterDiv.filters("removeTag", $parent.attr("class").split(" ")[0], $parent.attr("name"), $parent);
					$filterDiv.filters("removeParameter", $parent.attr("class").split(" ")[0], null, $input.attr("name"));

					// Could call the table reload
					if (bReload) {

						$filterDiv.filters("reload");
					}
				}

			} else {

				// Map
				sValue = $parent.data("value");
				aIds = $parent.data("aIds") || [];

				$parent.remove();

				// Clear Selected checkbox
				for (var i = 0; i < aIds.length; i++) {

					sensus.widgets.datatable.addDeselected({id : aIds[i]}, false);
					$("#smartpoint-table :checkbox[value=" + aIds[i] + "]").attr("checked", false);

				}

				sensus.widgets.datatable.setTotalResult(false);

				// Remove Polygon
				$.sc.map.destroyVectorFeaturesById(sValue);
			}
		}
	},

	_toggleEvent : function () {

		$(this.element).find(".toggle").click(function(){

			var $this = $(this);

			// hide filter
			$this.next(".collapse").toggle('blind', null, 500);

			if ($this.hasClass('on')) {

				$this.switchClass('on', 'off', 500);

			} else {

				$this.switchClass('off', 'on', 500);
			}

			return false;
		});
	},

	_slideEvent : function () {

		var $filters 		 = $(this.element);
		var $filterContainer = $(".yui-b", $filters);
		var $tableContainer	 = $(".yui-b", "#yui-main");
		var $arrowToggle	 = $(".side-slide-toggle", $filters);
		var iMarginLeft		 = $tableContainer.css("margin-left");

		// Whether filter is hide and navigate between tabs, keep the filter hide
		if ($tableContainer.css("margin-left") == "-5px") {

			$filterContainer.css("margin-left", -170);
			$arrowToggle.find("span").toggle();
		}

		$arrowToggle.click(function() {

			var $this = $(this);

			if ($this.find("span:visible").hasClass("hide-side-slide")) {

				// Animate to hide filters
				$filterContainer.animate({marginLeft : -170}, "slow");
				$tableContainer.animate({marginLeft : -5}, "slow");

			} else {

				// Animate to show filters
				$filterContainer.animate({marginLeft : 0}, "slow");
				$tableContainer.animate({marginLeft : iMarginLeft}, "slow");
			}

			// Toggle the arrow
			$this.find("span").toggle();
		});
	},

	_validationRemoveEvent : function () {

		$(this.element).find(".formError").on("click", "div.formErrorContent", function(e) {

			var $this = $(this);
			$this.next().remove();
			$this.remove();
		});
	},

	_startDatePicker : function () {

		var sDateFormat = sensus.settings.dateFormatMask.replace('yyyy','yy');

		$(this.element).find(".date-filter").datepicker({dateFormat: sDateFormat, maxDate: "+0D"});
	},

	_autocomplete : function () {

		var maxCharacters = this.options.maxCharacters;
		var fnAddOption = this.addOption;

		$(this.element).find(".ui-autocomplete-input").on("autocompleteclose", function(event, ui) {

			var $this = $(this);
			var $filter = $this.parents("div.filter-input");
			var $option;
			var title;
			var value = $this.val();
			var sHint = sensus.locale.get('filter.more');

			if (value && value.indexOf(sHint) == -1 && event.originalEvent) {

				// remove selected option
				$option = $filter.find(".listcombobox option").filter(function(i, e) {

					 return $(e).text() == $this.val();

				}).remove();

				title = $option.text();
				value = $option.val();

				// add selected option
				fnAddOption($filter, value, title,
						(title.length > maxCharacters ? title.substr(0, maxCharacters) + '...' : title), true);

				$option = $filter.find("input[value='" + value + "']");

				$option.trigger("click");
				$option.prop("checked", true);

				$this.val("");
				$filter.find("input.ui-autocomplete-input").val(
						$filter.find("select option").length + ' ' + sHint);
			}

		}).focusin(function() {

			$(this).val("");

		}).focusout(function() {

			var $this = $(this);
			var iSelectSize = $this.siblings("select").find("option").length;
			$this.val(iSelectSize + ' ' + sensus.locale.get('filter.more'));
		});
	},

	addOption : function ($filter, value, title, label, isChecked) {

		var $select;
		var $option = $("<li class='checkbox' title=\"" + title + "\">"
				+ "<label><input class='checkbox' " + (isChecked ? "checked='checked'" : "")
				+ " type='checkbox' value='" + value + "'/> <span>" + label + "</span></label></li>");

		// add selected option
		$filter.find(".ui-listcombobox").append($option);

		// remove combobox when no more options
		if ($filter.find(".listcombobox option").length == 0) {

			$select = $filter.find(".listcombobox");
			$select.siblings(".ui-autocomplete-input, button").remove();
			$select.remove();
		}

		return $option;
	},

	reload : function () {

		if ($.sc.map.mapExists() && ($('#map-list').length && !$('#map-list').hasClass('hide-map'))) {

			$.sc.map.mapFromFilter();

		} else {

			if (this.options.reloadTableFunction) {

				this.options.reloadTableFunction();

			} else {

				sensus.widgets.datatable.reloadTable(this.options.table, 0);
			}
		}
	},

	addParameter : function(filterId, value) {

		var flt = this.options.filters[filterId];

		if (flt.type == "options") {

			$.fn.pageLoader.parameter(flt.urlParameter,
					encodeURI(decodeURI($.address.parameter(flt.urlParameter) || "") + (value ? (value + "|") : "")));

		} else {

			$.fn.pageLoader.parameter(flt.urlParameter, encodeURI(value));
		}
	},

	removeParameter : function (filterId, value, field) {

		var flt = this.options.filters[filterId];
		var actualValue;
		var indexOf;

		if (value && flt.type == "options") {

			actualValue = decodeURI($.address.parameter(flt.urlParameter)).split("|");
			indexOf = $.inArray("" + value, actualValue);

			if (indexOf != -1) {

				actualValue.splice(indexOf, 1);
				actualValue.pop();

				$.fn.pageLoader.parameter(flt.urlParameter,
						actualValue.length > 0 ? encodeURI(actualValue.join("|") + "|") : null);
			}

		} else if (flt.inputs && flt.inputs[field]) {

			$.fn.pageLoader.parameter(flt.inputs[field].urlParameter || field, null);

		} else {

			$.fn.pageLoader.parameter(flt.urlParameter, null);
		}
	},

	addOptionTag : function ($checkbox) {

		var $filter = $checkbox.parents("div.filter-input");

		this.addTag($filter.attr("id"),
				$filter.find(".toggle").text(),
				$checkbox.siblings("span").text(),
				$checkbox.val(),
				$checkbox.parents("li").attr("title"),
				$checkbox);
	},

	addSearchTag : function ($filter) {

		var label;
		var value;
		var typeLabel;
		var $inputs;
		var $input;
		var length;
		var id = $filter.attr("id");
		var i;
		var isAdded = false;

		if ($filter.find("select").length) {

			value = $filter.find("input").val();
			typeLabel = $filter.find("select option[value='" + $filter.find("select").val() + "']").text();

			this.addTag(id, typeLabel, value, value, value, $filter.find("input"));

			isAdded = true;

		} else {

			$inputs = $filter.find("input");
			length = $inputs.length;

			for (i = 0; i < length; i = i + 1) {

				$input = $($inputs[i]);
				value = $input.val();

				if (value) {

					if ($input.siblings("label").length) {

						label = $input.siblings("label").text();

					} else if ($filter.find(".toggle").length) {

						label = $filter.find(".toggle").text();

					} else {

						label = this.defaultSearchNameLabel;
					}

					this.addTag(id + " " + $input.attr("name"),
							label, value, value, value, $input);

					isAdded = true;
				}
			}
		}

		return isAdded;
	},

	_createMapTag : function () {

		var value = parseFloat($.sc.map.getVectorArea()).toFixed(2);
		var label = sensus.locale.get("map.tag.shape", [$.sc.map.getVectorIndex(), value]);
		var typeLabel = sensus.locale.get("map.tag.drawing");

		return $("<li class='drawing'><span class='type remove'>" +
				typeLabel + "</span>" +
				"<span class='title'><span class='shape-icon' style='background:" +
				$.sc.map.getVectorColor() + "; opacity:0.6'></span>" +
				label + "</span></li>");
	},

	loadFilters : function () {

		var parameterNames = $.address.parameterNames();
		var length = parameterNames.length;
		var parameters = this._parameters;
		var $filterDiv = $(this.element);
		var i = 0;
		var filter;
		var $filter;
		var parameter;
		var values;

		for (; i < length; i = i + 1) {

			parameter = parameterNames[i];
			filter = parameters[parameter];

			if (filter) {

				switch (filter.type) {
				case "options" :

					this.loadOptionFilter($filterDiv.find("#" + filter.id), parameter);
					break;

				case "text" : // search
					this.loadSearchFilter(filter, parameter);
					break;
				}
			}
		}
	},

	loadSearchFilter : function (filter, parameter) {

		var $filterDiv = $(this.element);
		var value = decodeURI($.address.parameter(parameter));
		var $input = $filterDiv.find("input[name='" + filter.name + "']");
		var $filter = $input.parents("div.filter-input.search");
		var $toggle = $filter.find(".toggle");
		var $select;
		var $option;
		var typeId = $filter.attr("id");
		var typeLabel;

		if ($toggle.hasClass("off")) {

			$toggle.click();
		}

		if ($filter.find("select").length) {

			value = value.split("|");

			$select = $filter.find("select[name='" + filter.name + "']");
			$option = $select.find("option[value='" + value[1] + "']");
			$select.val(value[1]);
			typeLabel = $option.text();
			value = value[0];
			label = value;

		} else {

			label = value;

			if ($input.siblings("label").length) {

				typeLabel = $input.siblings("label").text();

			} else if ($filter.find(".toggle").text()) {

				typeLabel = $filter.find(".toggle").text();

			} else {

				typeLabel = this.defaultSearchNameLabel;
			}

			typeId = typeId + " " + filter.name;
		}

		$input.val(value).removeClass("input-clear");

		this.addTag(typeId, typeLabel, label, value, value, $input);
	},

	loadOptionFilter : function ($filter, parameter) {

		var values = decodeURI($.address.parameter(parameter));
		var typeId = $filter.attr("id");
		var $toggle = $filter.find(".toggle");
		var typeLabel = $toggle.text();
		var maxCharacters;
		var $input;
		var $li;
		var length;
		var value;
		var title;
		var label;
		var i;

		if (values) {

			if ($toggle.hasClass("off")) {

				$toggle.click();
			}

			maxCharacters = this.options.maxCharacters;
			values = values.split("|");
			values.pop();
			length = values.length;

			if (length > 0) {

				$filter.find(".all-checked input[value='ALL']").prop("checked", false);

				for (i = 0; i < length; i = i + 1) {

					value = values[i];
					$input = $filter.find(".ui-listcombobox input:checkbox[value='" +  value + "'], select option[value='" +  value + "']");
					$li = null;

					if ($input.length) {

						if ($input.is(":checkbox")) {

							title = $input.parents("li").attr("title");
							label = $input.siblings("span").text();
							$input.prop("checked", true);

						} else {

							title = $input.text();
							label = title.length > maxCharacters ? title.substr(0, maxCharacters) + '...' : title;
							$input.remove();
							$li = this.addOption($filter, $input.val(), title, label, true);
						}

						this.addTag(typeId, typeLabel, label, $input.val(), title, $li ? $li.find("input") : $input);
					}
				}
			}
		}
	},

	addTag : function (typeId, typeLabel, label, value, title, $input) {

		var $tagsDiv;
		var $tag;

		if (typeId == "drawing") {

			$tagsDiv = $(this.options.tagsMapDiv);

			$tag = this._createMapTag();

			$tagsDiv.find("ul").append($tag);

			$tag.data("value", value);
			$tag.data("aIds", $.sc.map.getVectorAIds());

		} else {

			$tagsDiv = $(this.options.tagsDiv);

			if ($input.parents("div.filter-input.search").length) {

				$tag = $tagsDiv.find(".filter-container li." + typeId.split(" ").join("."));

			} else {

				$tag = $tagsDiv.find(".filter-container li." + typeId.split(" ").join(".") + "[name='" + value + "']");
			}

			// if tag exist
			if ($tag.length) {

				$tag.find("span.type").text(typeLabel);
				$tag.find("span.title").attr("title", title).text(label);

			} else {

				// Show 'Reset Filter' tag
				if ($tagsDiv.hasClass("hide")
						&& $tagsDiv.find(".filter-container li").length > 0) {

					$tagsDiv.removeClass("hide");
				}

				$tag = $("<li class='" + typeId + "' name='" + value + "'><span class='type " +
						($input.is(":disabled") ? "" : "remove") + "'>" +
						typeLabel + "</span><span class='title' title='" + title + "'>" + label +
						"</span></li>").data("input", $input);

				$tagsDiv.find(".filter-container li:last").before($tag);
			}
		}
	},

	removeTag : function (typeId, value, $tag) {

		var $tagsDiv = $(this.options.tagsDiv);

		if ($tag) {

			$tag.remove();

		} else {

			$tagsDiv.find(".filter-container li." + typeId + "[name='" + value + "']").remove();
		}

		// Hide 'Reset Filter'
		if ($tagsDiv.find(".filter-container li").length == 1) {

			$tagsDiv.addClass("hide");
		}
	},

	removeAllTags : function (type) {

		var $tagsDiv = $(this.options.tagsDiv);

		$tagsDiv.find(".filter-container li" + (type ? ("." + type) : ":not(li:last)")).remove();

		// Hide 'Reset Filter'
		if ($tagsDiv.find(".filter-container li").length == 1) {

			$tagsDiv.addClass("hide");
		}
	},

	selectFilterOption : function ($checkbox, bReload) {

		$checkbox.prop("checked", !$checkbox.is(':checked'));

		this._selectFilterEvent($checkbox, $(this.element), bReload);
	},

	setFilterOptions : function ($checkboxs, bCheckbox) {

		var $filterDiv = $(this.element);
		var iCheckbox = $checkboxs.length;
		var $filter;
		var $checkbox;
		var i = 0;

		for (; i < iCheckbox; i = i + 1) {

			$checkbox = $($checkboxs[i]);

			$checkbox.prop("checked", bCheckbox);

			$filter = $checkbox.parents("div.filter-input");

			if (bCheckbox) {

				$filterDiv.filters("addParameter", $filter.attr("id"), $checkbox.val());
				$filterDiv.filters("addOptionTag", $checkbox);

			} else {

				$filterDiv.filters("removeTag", $filter.attr("id"), $checkbox.val());
				$filterDiv.filters("removeParameter", $filter.attr("id"), null, $checkbox.val());
			}
		}
	},

	setFilterDisable : function ($filter, bValue) {

		$filter.find("input").prop("disabled", bValue);

		if (bValue == false) {

			$(this.options.tagsDiv).find("span.type:not(.remove)").addClass("remove");
		}
	},

	resetAllFilters : function () {

		$(this.options.tagsDiv).find(".clear").click();
	},

	/**
	 * Clean All Filter
	 * Before call this method, call the widget destroy
	 */
	cleanAllFilters : function (keepFilters) {

		var $tagDiv = $(this.options.tagsDiv);
		var $filterDiv = $(this.element);

		this.setFilterDisable($filterDiv, false);

		this._removeTagEvent($tagDiv.find(".clear"), $filterDiv, false, keepFilters);

		// Off events
		// Click and Keypress
		$filterDiv.off("click");
		$filterDiv.find("div.filter-input.search").off("click").off("keypress");
		$filterDiv.find(".formError").off("click");

		// Tags
		$tagDiv.find(".filter-container").off("click");

		// Map Tags
		$(this.options.tagsMapDiv).find(".filter-container").off("click");
	}
});