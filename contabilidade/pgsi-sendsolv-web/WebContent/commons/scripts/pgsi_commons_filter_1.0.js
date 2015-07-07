$(document).ready(function()
{
	$.pgsi.filter = (function()
	{
		var _oConfig =
		{
			common 		:
			{
				mapListId 			: "map-list",
				mapResultsContainer	: "filter-results-container-map",
				resultsContainer	: "active-filters-list >",
				filterInputSearch 	: "filter-input.search",
				filterInput			: "filter-input",
				inputClear			: "input-clear",
				toggle 				: "toggle",
				off 				: "off",
				on 					: "on",
				clear 				: "clear",
				collapse 			: "collapse",
				blind 				: "blind",
				allChecked 			: "all-checked",
				allNotChecked 		: "all-not-checked",
				notInChecked 		: "not-in-checked",
				hideMap 			: "hide-map",
				filterInput 		: "filter-input",
				search 				: "search",
				filterContainer 	: "filter-container",
				filterQuery  		: "query",
				viewsVert 			: "views-vert",
				filterViewFrom 		: "view_from",
				rangeSlider 		: "range-slider",
				sideSlideToggle 	: "side-slide-toggle",
				hideSideSlide 		: "hide-side-slide",
				formError 			: "formError",
				formErrorContent 	: "formErrorContent",
				customAction 		: "custom-filter-action",
				goButton 			: "go-button",
				bActionPerformed	: false
			},
			messages  	:
			{
				defaultSearchNameLabel 	: "commons.pages.search",
				viewing 				: "commons.pages.viewing",
				back 					: "process.page.filter.back",
				mapShape 				: "map.tag.shape",
				mapDrawing 				: "map.tag.drawing",
				rangeTo 				: "commons.filter.range.to",
				filterCustomize 		: "filter.customize",
				filterMore 				: "filter.more"
			},
			project 	:
			{
				sViewFromParam 			: "view_from",
				sTotalDaysParam			: "total_days",
				allValue 				: "ALL",
				notInValue 				: "-1",
				customizationType 		: "filters"
			}
		};

		/**
         * Change common default configuration
         * @param {Object} - An object containing the properties to be changed in defaults.
         */
        var _setGeneralConfig = function(oConfig)
        {
        	$.extend(_options, oConfig);
        };

		var _options =
		{
			filterTypes 		: ["options", "text", "select", "range", "search", "drawing"],
			_parameters 		: {},
			_objFiltersCustomize: [],
			filters				: {},
			createReload		: false,
			table				: null,
			defaultContainer	: true,
			hasSideSlide 		: true,
			isOpenSlide 		: true,
			hasCustomize		: false,
			title 				: "",
			openFirstFilters	: 3,
			firstOptions		: 6,
			maxCharacters		: 19,
			tagsDiv				: "." + _oConfig.common.resultsContainer + " div.first",
			tagsMapDiv			: "#" + _oConfig.common.mapListId + " ." + _oConfig.common.mapResultsContainer,
			createTitle			: function ()
			{
				return "<h4>" + this.title + "</h4>";
			}
		};

		/**
		 *	---------------------------------------------------------------------------------------------------------
		 *	Functionalities
		 *	---------------------------------------------------------------------------------------------------------
		 */
		var _loadFilters = function (options)
		{
			var parameterNames  = $.address.parameterNames();
			var parameters 		= options._parameters;
			var $filterDiv 		= $(options.element);
			var filter;
			var parameter;

			for (var i = 0, length = parameterNames.length; i < length; i = i + 1)
			{
				parameter = parameterNames[i];
				filter 	  = parameters[parameter];

				if (filter)
				{
					switch (filter.type)
					{
						case _options.filterTypes[0] : // Options
							_loadOptionFilter($filterDiv.find("#" + filter.id), parameter, options);
							break;

						case _options.filterTypes[1] : // Text
							_loadSearchFilter(filter, parameter, options);
							break;

						case _options.filterTypes[2] : // Select
							_loadSearchFilter(filter, parameter, options);
							break;
					}
				}
				else if ( pgsi.util.filter.noFiltersTags && pgsi.util.filter.noFiltersTags[parameter] )
				{
					_addTag(
					{
						typeId 		: parameter,
						typeLabel 	: $.pgsi.locale.get((pgsi.util.filter.noFiltersTags[parameter].label ? pgsi.util.filter.noFiltersTags[parameter].label : parameter)),
						label 		: $.address.parameter(parameter),
						value 		: parameter,
						title 		: parameter,
						options		: options
					});
				}
			}
		};

		var _loadSearchFilter = function (filter, parameter, options)
		{
			var value = decodeURI($.address.parameter(parameter));

			// If filter is empty
			if(value === "|")
			{
				return;
			}

			var $filterDiv 	= $(options.element);
			var $input 		= $filterDiv.find("[name='" + filter.name + "']");
			var $filter 	= $input.parents("div."+_oConfig.common.filterInputSearch);
			var $toggle 	= $filter.find("." + _oConfig.common.toggle);
			var typeId 		= $filter.attr("id");
			var $select;
			var $option;
			var typeLabel;

			if ( $toggle.hasClass("off") )
			{
				$toggle.click();
			}

			$input.each(function(i, e)
			{
				if (value.indexOf("|") !== -1)
				{
					$(this).val(value.split("|")[1]);
				}
				else
				{
					$(this).val(value);
				}

				$(this).removeClass(_oConfig.common.inputClear);
			});

			if ( $filter.find("select#"+parameter).length )
			{
				$select 	= $filter.find("select#"+parameter);
				label 		= value.indexOf("|") !== -1 ? value.split("|")[1] : value;
				value 		= value.indexOf("|") !== -1 ? value.split("|")[0] : value;
				$option 	= $select.find("option[value='" + value + "']");
				typeLabel 	= _oConfig.common.filterQuery == parameter ? $option.text() : parameter;

				$select.val(value);
			}
			else
			{
				label = value;

				if ($input.siblings("label").length)
				{
					if (parameter == _oConfig.project.sViewFromParam)
					{
						typeLabel = parameter;
					}
					else
					{
						typeLabel = $input.siblings("label").text();
					}
				}
				else if ( $filter.find("." + _oConfig.common.toggle).text() )
				{
					typeLabel = $filter.find("." + _oConfig.common.toggle).text();
				}
				else
				{
					typeLabel = options.defaultSearchName;
				}
			}

			typeId = typeId + " " + filter.name;

			_addTag(
			{
				typeId 		: typeId,
				typeLabel 	: typeLabel,
				label 		: label,
				value 		: value,
				title 		: value,
				oInput 		: $input,
				options		: options
			});
		};

		var _loadOptionFilter = function ($filter, parameter, options)
		{
			var values 			= decodeURI($.address.parameter(parameter));
			var typeId 			= $filter.attr("id");
			var $toggle 		= $filter.find("." + _oConfig.common.toggle);
			var typeLabel 		= $toggle.text();
			var maxCharacters;
			var $input;
			var $li;
			var length;
			var value;
			var title;
			var label;

			if (values)
			{
				if ( $toggle.hasClass(_oConfig.common.off) )
				{
					$toggle.click();
				}

				maxCharacters   = _options.maxCharacters;
				values 			= values.split("|");
				values.pop();
				length 			= values.length;

				$filter.find("." + _oConfig.common.allChecked + " input[value='" + _oConfig.project.allValue + "']").prop("checked", false);

				if (length > 0)
				{
					for (var i = 0; i < length; i = i + 1)
					{
						value 	= values[i];
						$input 	= $filter.find("input:checkbox[value='" +  value + "'], select option[value='" +  value + "']");
						$li 	= null;

						// If the filter input doesn't exists, then the filter must be removed from URL
						if ( !$input.length )
						{
							_removeParameter(typeId, value, typeId, options);
							continue;
						}

						if ( $input.is(":checkbox") )
						{
							title = $input.parents("li").attr("title");
							label = $input.siblings("span:last").text();
							$input.prop("checked", true);
						}
						else
						{
							title = $input.text();
							label = title.length > maxCharacters ? title.substr(0, maxCharacters) + "..." : title;
							var data = $input.data();
							$input.remove();
							$li = _addOption($filter, $input.val(), title, label, true, data);
						}

						_addTag(
						{
							typeId 		: typeId,
							typeLabel 	: typeLabel,
							label 		: label,
							value 		: $input.val(),
							title 		: title,
							oInput 		: $li ? $li.find("input") : $input,
							options		: options
						});
					}
				}
			}
		};

		var _reload = function (options)
		{
			_oConfig.common.bActionPerformed = true;

			if ( $.pgsi.map.mapExists() && ($("#" + _oConfig.common.mapListId).length && !$("#" + _oConfig.common.mapListId).hasClass(_oConfig.common.hideMap)) )
			{
				$.pgsi.map.mapFromFilter();
			}
			else
			{
				if (options.reloadTableFunction)
				{
					options.reloadTableFunction();
				}
				else
				{
					$.pgsi.table.reloadTable({
						table 		: options.table,
						iStart 		: 0
					});
				}
			}
		};

		var _addParameter = function(filterId, value, options)
		{
			var flt = options.filters[filterId];

			if ( flt.type == _options.filterTypes[0] )
			{
				$.pgsi.pageLoader.setParameter(flt.urlParameter,
					(($.pgsi.replaceAll(_oConfig.project.notInValue + "\\|", "", decodeURI($.address.parameter(flt.urlParameter) || "")) || "") + (value ? (value + "|") : "")));
			}
			else
			{
				$.pgsi.pageLoader.setParameter(flt.urlParameter, value);
			}
		};

		var _removeParameter = function (filterId, value, field, options)
		{
			var flt = options.filters[filterId];
			var actualValue;
			var indexOf;

			if ( value && flt && flt.type == _options.filterTypes[0] )
			{
				actualValue = decodeURI($.address.parameter(flt.urlParameter)).split("|");
				indexOf 	= $.inArray("" + value, actualValue);

				if (indexOf != -1)
				{
					actualValue.splice(indexOf, 1);
					actualValue.pop();

					$.pgsi.pageLoader.setParameter(flt.urlParameter,
						actualValue.length > 0 ? decodeURI(actualValue.join("|") + "|") : null);
				}
			}
			else if ( flt && flt.inputs && flt.inputs[field] )
			{
				$.pgsi.pageLoader.setParameter(flt.inputs[field].urlParameter || field, null);
			}
			else
			{
				$.pgsi.pageLoader.setParameter(flt.urlParameter || field, null);
			}
		};

		/**
		 *	@param oParam
		 *		- typeId
		 *		- typeLabel
		 * 		- label
		 * 		- value
		 * 		- title
		 * 		- oInput
		 *		- options
		 *
		 *	Create tag for filter
		 */
		var _addTag = function (oParam)
		{
			var $tagsDiv;
			var $tag;
			var sValue;

			//	Set default Options
			oParam.options = $.extend(true, {}, _options, oParam.options || {});

			// Replace is used to lock down application from XSS
			if ( pgsi.util.page.fnCheckXSS(oParam.value) )
			{
				var sInvalidTag = $.pgsi.locale.get("pgsi.filter.validation.invalidTag");

				oParam.value = sInvalidTag;
				oParam.label = sInvalidTag;
				oParam.title = sInvalidTag;

				$.pgsi.progressBar.stop();
			}

			if ( oParam.typeId == _options.filterTypes[5] )
			{
				$tagsDiv	= $(oParam.options.tagsMapDiv);
				$tag 		= _createMapTag();

				$tagsDiv.find("ul").append($tag);

				$tag.data("value", oParam.value);
				$tag.data("aIds", oParam.aDeviceIds);
			}
			else
			{
				$tagsDiv = $(oParam.options.tagsDiv);

				if ( $tagsDiv.find("." + _oConfig.common.filterContainer + " li." + oParam.typeId.split(" ").join(".")).size() )
				{
					$tag = $tagsDiv.find("." + _oConfig.common.filterContainer + " li." + oParam.typeId.split(" ").join("."));
				}
				else
				{
					$tag = $tagsDiv.find("." + _oConfig.common.filterContainer + " li." + oParam.typeId.split(" ").join(".") + '[name="' + oParam.value + '"]');
				}

				if (oParam.typeLabel == _oConfig.project.sViewFromParam)
				{
					sValue 				= _getViewFromLabel($(oParam.oInput[0]));
					oParam.typeLabel	= $.pgsi.locale.get(_oConfig.messages.viewing);
					oParam.title 		= sValue;
					oParam.label 		= sValue;
				}
				else if (oParam.typeLabel == _oConfig.project.sTotalDaysParam)
				{
					sValue 				= $(oParam.oInput[0]).val();
					oParam.typeLabel 	= $(oParam.oInput[0]).siblings("label").text();
					oParam.title 		= sValue;
					oParam.label 		= sValue;
				}
				else if (oParam.value.indexOf("|") !== -1)
				{
					oParam.label = oParam.value.split("|")[0];
				}
				else
				{
					sValue 	= oParam.label;
				}

				// if tag exist
				if ( $tag.length && !oParam.oInput.is(":checkbox") )
				{
					$tag.find("span.title").attr("title", oParam.title).text(sValue);
					$tag.find("span.remove").text(oParam.typeLabel);
				}
				else if ( oParam.typeLabel !== $.pgsi.locale.get(_oConfig.messages.back) )
				{
					// Show 'Reset Filter' tag
					if ( $tagsDiv.hasClass("hide") && $tagsDiv.find("." + _oConfig.common.filterContainer + " li").length > 0 )
					{
						$tagsDiv.removeClass("hide");
					}

					$tag = $("<li id='" + oParam.typeId  + "' class='" + oParam.typeId + "' name='" + oParam.value + "'><span class='type " +
							(oParam.oInput && oParam.oInput.is(":disabled") ? "" : "remove") + "'>" + oParam.typeLabel
							+ "</span><span class='title' title=\"" + oParam.title + "\">" + oParam.label +	"</span></li>");

					if (oParam.oInput)
					{
						$tag.data("input", oParam.oInput);
					}

					$tagsDiv.find("." + _oConfig.common.filterContainer + " li:last").before($tag);
				}
			}
		};

		var _getViewFromLabel = function($input, bUrl)
		{
			var sValue 	= $input.val().split("|")[0];
			var iBack 	= parseInt($input.parent().next().find("input").val());
			var dStart 	= $.datepicker.parseDate(pgsi.settings.user.dateFormat, sValue);
			var dEnd 	= $.datepicker.formatDate(pgsi.settings.user.dateFormat, new Date(dStart - (iBack*24*60*60*1000)));
			sValue 		= bUrl ? (dEnd + "|" + sValue) : (dEnd + " - " + sValue);

			return sValue;
		};

		var _removeTag = function (typeId, value, $tag, options)
		{
			var $tagsDiv = $(options.tagsDiv);

			if ($tag)
			{
				$tag.remove();
			}
			else
			{
				$tagsDiv.find("." + _oConfig.common.filterContainer + " li." + typeId + "[name='" + value + "']").remove();
			}

			// Hide 'Reset Filter'
			if ($tagsDiv.find("." + _oConfig.common.filterContainer + " li").length == 1)
			{
				$tagsDiv.addClass("hide");
			}
		};

		var _removeAllTags = function (type, options, clearActive, aKeepFilters)
		{
			// Use default div if not in options
			options.tagsDiv = options.tagsDiv || _options.tagsDiv;

			var $tagsDiv	= $(options.tagsDiv);
			var $tags 		= $tagsDiv.find("." + _oConfig.common.filterContainer + " li" + (type ? ("." + type) : ":not(li:last)"));

			if (clearActive)
			{
				for (var i = 0, l = $tags.length; i < l; i++)
				{
					var filterId = $tags[i].id.split(" ");

					if($.pgsi.isValidArray(aKeepFilters) && aKeepFilters.indexOf(filterId[0]) !== -1)
					{
						continue;
					}

					_removeParameter(filterId[0], null, filterId[1], options);
				}
			}

			$tags.remove();

			// Hide 'Reset Filter'
			if ($tagsDiv.find("." + _oConfig.common.filterContainer + " li").length == 1)
			{
				$tagsDiv.addClass("hide");
			}
		};

		var _selectFilterOption = function ($checkbox, bReload, options)
		{
			$checkbox.prop("checked", !$checkbox.is(':checked'));

			_selectFilterEvent($checkbox, $(options.element), bReload, options);
		};

		var _addOptionTag = function ($checkbox, options)
		{
			var $filter = $checkbox.parents("div." + _oConfig.common.filterInput);

			_addTag(
			{
				typeId 		: $filter.attr("id"),
				typeLabel 	: $filter.find("." + _oConfig.common.toggle).text(),
				label 		: $checkbox.siblings("span:last").text(),
				value 		: $checkbox.val(),
				title 		: $checkbox.parents("li").attr("title"),
				oInput 		: $checkbox,
				options		: options
			});
		};

		var _addSearchTag = function ($filter, options)
		{
			var value;
			var typeLabel;
			var $inputs;
			var $input;
			var $option;
			var length;
			var id = $filter.attr("id");
			var i;
			var isAdded = false;

			if ($filter.find("#" + _oConfig.common.filterQuery).length)
			{
				$input 	= $filter.find("input:eq(0)");
				$option = $filter.find("select option:selected");
				value 	= $input.val();

				if ($filter.find("select#" + _oConfig.common.filterQuery).length)
				{
					typeLabel = $option.text();
				}
				else if(!$input.siblings("label").length)
				{
					typeLabel = options.defaultSearchName;
				}

				if (value)
				{
					_addTag(
					{
						typeId 		: id + " " + $input.attr("name"),
						typeLabel 	: typeLabel,
						label 		: value,
						value 		: value,
						title 		: value,
						oInput 		: $input,
						options		: options
					});

					isAdded = true;
				}
			}
			else if ($filter.find("#" + _oConfig.common.filterViewFrom).length)
			{
				$inputs = $filter.find("input");
				length 	= $inputs.length;

				for (i = 0; i < length; i = i + 1)
				{
					$input 		= $($inputs[i]);
					value 		= $input.val();
					typeLabel 	= $input.attr("id");

					if (value)
					{
						_addTag(
						{
							typeId 		: id + " " + $input.attr("name"),
							typeLabel 	: typeLabel,
							label 		: value,
							value 		: value,
							title 		: value,
							oInput 		: $input,
							options		: options
						});

						isAdded = true;
					}
				}
			}
			else if ($filter.find(_options.filterTypes[2]).length)
			{
				$.each($filter.find(_options.filterTypes[2]), function(e)
				{
				    $input 		= $(this);
					value 		= $input.val();
					typeLabel 	= $(this).attr("name");

					_addTag(
					{
						typeId 		: id + " " + $input.attr("id"),
						typeLabel 	: typeLabel,
						label 		: value,
						value 		: value,
						title 		: value,
						oInput 		: $filter.find("input"),
						options		: options
					});
				});

				isAdded = true;
			}
			else if ($filter.find("input").length)
			{
				$inputs = $filter.find("input");
				length 	= $inputs.length;

				for (i = 0; i < length; i = i + 1)
				{
					$input 	= $($inputs[i]);
					value 	= $input.val();

					if (value)
					{
						_addTag(
						{
							typeId 		: id + " " + $input.attr("name"),
							typeLabel 	: $input.siblings("label").length ? $input.siblings("label").text() : $filter.find("." + _oConfig.common.toggle).text(),
							label 		: value,
							value 		: value,
							title 		: value,
							oInput 		: $input,
							options		: options
						});

						isAdded = true;
					}
					else
					{
						var tag = $(options.tagsDiv).find("." + _oConfig.common.filterContainer + " li." + id + "." + $input.attr("name"));

						_removeTag(id + "." + $input.attr("name"), value, tag, options);
					}
				}
			}
			else
			{
				var label;

				$inputs = $filter.find("." + _oConfig.common.rangeSlider);
				length 	= $inputs.length;

				for (i = 0; i < length; i = i + 1)
				{
					$input = $($inputs[i]);
					var sMin = $input.closest("div").siblings("p").find("span:eq(0)").text();
					var sMax = $input.closest("div").siblings("p").find("span:eq(1)").text();
					value = sMin + " " + $.pgsi.locale.get(_oConfig.messages.rangeTo) + " " + sMax;

					if (value)
					{
						if ($input.siblings("label").length)
						{
							label = $input.siblings("label").text();
						}
						else if ($filter.find("." + _oConfig.common.toggle).length)
						{
							label = $filter.find("." + _oConfig.common.toggle).text();
						}
						else
						{
							label = options.defaultSearchName;
						}

						_addTag(
						{
							typeId 		: id + " " + $input.attr("name"),
							typeLabel 	: label,
							label 		: value,
							value 		: value,
							title 		: value,
							oInput 		: $input,
							options		: options
						});

						isAdded = true;
					}
				}
			}
			return isAdded;
		};

		/**
		 *	---------------------------------------------------------------------------------------------------------
		 *	Visual components
		 *	---------------------------------------------------------------------------------------------------------
		 */

		 /**
		  *
		  */
		var _createMapTag = function ()
		{
			var value 		= parseFloat($.pgsi.map.getVectorArea()).toFixed(2);
			var label 		= $.pgsi.locale.get(_oConfig.messages.mapShape, [$.pgsi.map.getVectorIndex(), value, "<sup>2</sup>"]);
			var typeLabel 	= $.pgsi.locale.get(_oConfig.messages.mapDrawing);

			$(".filter-results-container-map").removeClass("hide");

			return $("<li class='" + _options.filterTypes[5] + "'><span class='type remove'>" +
				typeLabel + "</span>" +
				"<span class='title'><span class='shape-icon' style='background:" +
				$.pgsi.map.getVectorColor() + "; opacity:0.6'></span>" +
				label + "</span></li>");
		};

		var _createFilterTitle = function(title, isOpen)
		{
			return "<label class='toggle filter-title " + (isOpen ? "on" : "off") + "'>" + title + "</label>";
		};

		var _createLabelField = function(label, isHide)
		{
			return "<label " + (isHide ? "style='display:none;'" : "") + ">" + label + "</label>";
		};

		var _createSpanField = function(label)
		{
			return "<span>" + label + "</span> ";
		};

		var _createGoButton = function(label)
		{
			return "<button class='" + _oConfig.common.goButton + "'>" + label + "</button>";
		};

		var _createTextField = function(name, clazz, style, maxsize, value, hint)
		{
			return "<input id='" + name + "' name='" + name + "' "
				+ (clazz 	? (" class='" 		+ clazz 	+ "'") : "")
				+ (style 	? (" style='" 		+ style 	+ "'") : "")
				+ (maxsize 	? (" maxlength='" 	+ maxsize 	+ "'") : "")
				+ (hint 	? (" placeholder='"	+ hint 		+ "'") : "")
				+ (value 	? (" value='"		+ value 	+ "'") : "")
				+ " type='text'/>";
		};

		var _createSelectField = function(name, options, clazz)
		{
			var length 		= options.length;
			var $select 	= ["<select " + (length < 2 ? "style='display:none;'" : "") + "name='" + name + "' class='" + clazz + "' id='" + name + "'>"];
			var i 			= 0;
			var get 		= $.pgsi.locale.get;
			var opt;
			var sValue;
			var sLabel;

			for (; i < length; i = i + 1)
			{
				opt 	= options[i];
				sValue 	= opt.value ? opt.value : opt[0];
				sLabel 	= opt.label ? get(opt.label) : opt[1];
				$select.push("<option value='" + sValue + "'" + (opt.validation ? (" name='" + opt.validation + "'>") : " >") + sLabel + "</option>");
			}

			$select.push("</select>");

			return $select.join("");
		};

		var _createRangeField = function(name, options)
		{
			var $range 		= [];
			var sIdRangeMin = options.label + "-range-min";
			var sIdRangeMax = options.label + "-range-max";

			$range.push("<p><span class'range-min' id='"+ sIdRangeMin +"'>"+ (options.values ? options.values[0] : options.minsize) + "</span>  " + $.pgsi.locale.get(_oConfig.messages.rangeTo) + " ");
			$range.push("<span class'range-max' id='"+ sIdRangeMax +"'>"+ (options.values ? options.values[1] : options.maxsize) + "</span></p>");
			$range.push("<div name='" + name + "' id='" + name + "' class='" + _oConfig.common.rangeSlider+ "' data-begin='" + options.minsize + "' data-end='" + options.maxsize + "'></div>");

			return $range.join("");
		};

		/**
		 *	Create checkbox for all checkbox selection and Not In
		 */
		var _createAllAndNotInCheckbox = function(flt, isChecked)
		{
			var sHtml = "";

			sHtml += "<ul class='" + _oConfig.common.allNotChecked + "'>";

				//	All
				sHtml += "<li class='checkbox " + _oConfig.common.allChecked + (flt.allClass ? " " + flt.allClass : "") + "'>";
					sHtml += "<label><input type='checkbox' " + (isChecked ? "checked='checked'" : "") + " value='" + _oConfig.project.allValue + "'/> ";
					sHtml += "<strong>" + $.pgsi.locale.get(flt.allLabel) + "</strong></label>";
				sHtml += "</li>";

				//	Not In
				if (flt.notInFilter)
				{
					sHtml += "<li class='checkbox " + _oConfig.common.notInChecked + "'>";
						sHtml += "<label><input type='checkbox' value='" + _oConfig.project.notInValue + "'/> ";
						sHtml += "<span>" + $.pgsi.locale.get(flt.notInFilter.label) + "</span></label>";
					sHtml += "</li>";
				}

			sHtml += "</ul>";
			return sHtml;
		};

		/**
		 *	Create side slide effect
		 */
		var _createSideSlide = function(options)
		{
			return 	(
				"<div class='side-slide-toggle'>"
					+ "<span class='hide-side-slide ui-icon ui-icon-arrowthick-1-w'"
					+ (!options.isOpenSlide ? " style='display:none'" : "") + "></span>"
					+ "<span class='show-side-slide ui-icon ui-icon-arrowthick-1-e'"
					+ (options.isOpenSlide ? " style='display:none'" : "") + "></span>"
					+ "</div>"
			);
		};

		//	Create Customize Filters Button
		var _createCustomizeButton = function ()
		{
			return "<div id='customFilter' class='" + _oConfig.common.filterInput + " advanced ui-widget'>"
				+ "<a class='button ui-button ui-widget ui-state-default ui-corner-all "
				+ "ui-button-text-only' id='custom-filter-action' href='' role='button' aria-disabled='false'>"
				+ "<span class='ui-button-text'>" + $.pgsi.locale.get(_oConfig.messages.filterCustomize) + "</span></a></div>";
		};


		//	Add DatePicker functionality
		var _startDatePicker = function (options)
		{
//			var currentTime = new Date();
//			var systemTime 	= $.pgsi.date.createTimeZoneJS();
//			var iDifTime	= systemTime.getDate() - currentTime.getDate();
//
//			var sDifTime;
//
//			if (iDifTime <= 1 && iDifTime >= -1)
//			{
//				sDifTime = ( (iDifTime == -1) ? (iDifTime) : ("+" + iDifTime) ) + "D";
//			}
//			else if (iDifTime > 1)
//			{
//				sDifTime = "-1D";
//			}
//			else if (iDifTime < -1)
//			{
//				sDifTime = "+1D";
//			}
//
//			$(options.element).find(".date-filter").datepicker({dateFormat: pgsi.settings.user.dateFormat, maxDate: sDifTime});
		};

		//	Add Range Slider functionality
		var _startRangeSlider = function (options)
		{
			$(options.element).find("." + _oConfig.common.rangeSlider).each(function(i, obj)
			{
				var minsize 	= $(this).siblings("p").find("span:eq(0)").text();
				var maxsize 	= $(this).siblings("p").find("span:eq(1)").text();
				var sSliderId	= $(this).prop("id");
				var realRange 	= $(this).data().begin - $(this).data().end;
				var aValues;

				realRange 		= realRange < 0 ? realRange * -1 : realRange;
				aValues			= $(this).data().begin < 0 ? [(parseInt(minsize) + realRange), (parseInt(maxsize) + realRange)] : [minsize, maxsize];

				$("#"+sSliderId).slider({
					range: true,
					min: 0,
					max: realRange,
					values: aValues,
					animate: true,
					slide: function( event, ui )
					{
						if ($(this).data().begin >= 0)
						{
							$(this).siblings("p").find("span:eq(0)").text((ui.values[ 0 ]));
							$(this).siblings("p").find("span:eq(1)").text((ui.values[ 1 ]));
						}
						else
						{
							$(this).siblings("p").find("span:eq(0)").text((ui.values[ 0 ] - realRange));
							$(this).siblings("p").find("span:eq(1)").text((ui.values[ 1 ] - realRange));
						}
					}
				});
			});
		};

		//	Add autocomplete functionality
		var _autocomplete = function (options)
		{
			var maxCharacters 	= options.maxCharacters;
			var fnAddOption 	= _addOption;

			$(options.element).find(".ui-autocomplete-input").on("autocompleteclose", function(event, ui)
			{
				var $this = $(this);
				var $filter = $this.parents("div." + _oConfig.common.filterInput);
				var $option;
				var title;
				var value = $this.val();
				var sHint = $.pgsi.locale.get(_oConfig.messages.filterMore);

				if (value && value.indexOf(sHint) == -1 && event.originalEvent)
				{
					// remove selected option
					$option = $filter.find(".listcombobox option").filter(function(i, e)
					{
						return $(e).text() == $this.val();
					});

					if($option.val())
					{
						title = $option.text();
						value = $option.val();
						var data = $option.data();
						$option.remove();

						// add selected option
						fnAddOption($filter, value, title,
							(title.length > maxCharacters ? title.substr(0, maxCharacters) + '...' : title), false, data);

						$option = $filter.find("input[value='" + value + "']");

						$option.trigger("click");
						$option.prop("checked", true);

						//$this.val("");
						$filter.find("input.ui-autocomplete-input").val(
							$filter.find("select option").length + " " + sHint);
					}
				}
			}).focusin(function()
			{
				$(this).val("");
			}).focusout(function()
			{
				var $this = $(this);
				var iSelectSize = $this.siblings(_options.filterTypes[2]).find("option").length;
				$this.val(iSelectSize + " " + $.pgsi.locale.get(_oConfig.messages.filterMore));
			}).trigger("focusout");
		};

		var _addOption = function ($filter, value, title, label, isChecked, extraData)
		{
			var $select;
			var $option

			// TODO - review impl and char limit and symbol
			if ( !$.pgsi.isNullOrUndefined(extraData) && !$.pgsi.isNullOrUndefined(extraData.symboltitlevalid) )
			{
				$option = $("<li class='checkbox' title=\"" + title + "\">"
						+ "<input class='checkbox' " + (isChecked ? "checked='checked'" : "") + " type='checkbox' value='" + value + "'/> "
						+ "<span class='label label-default " + extraData.symbolclass + "'>" + extraData.symboltitlevalid + "</span> "
						+ '<span class="title_filter_checkbox">' + (label.length > 15 ? (label.substr(0, 15) + '...') : label) + "</span></li>");
			}
			else
			{
				$option = $("<li class='checkbox' title=\"" + title + "\">"
						+ "<label><input class='checkbox' " + (isChecked ? "checked='checked'" : "")
						+ " type='checkbox' value='" + value + "'/> <span>" + label + "</span></label></li>");
			}

			// add selected option
			$filter.find(".ui-listcombobox").append($option);

			// remove combobox when no more options
			if ($filter.find(".listcombobox option").length == 0)
			{
				$select = $filter.find(".listcombobox");
				$select.siblings(".ui-autocomplete-input, button").remove();
				$select.remove();
			}

			return $option;
		};

		//	Create combobox
		var _createCombobox = function (options)
		{
			$("select.listcombobox").combobox();
			var filters 		= options.filters;
			var firstOptions 	= options.firstOptions;
			$(this.element).find("input.ui-autocomplete-input").each(function()
			{
				var id = $(this).closest("." + _oConfig.common.filterInput).attr("id");
				iSelectSize = filters[id].dataList.length - firstOptions;
				$(this).val(iSelectSize + " " + $.pgsi.locale.get(_oConfig.messages.filterMore));
			});
		};

		var _createSearchFilters = function(flt, isOpen, options)
		{
			var inputs 	= flt.inputs;
			var _get 	= $.pgsi.locale.get;
			var $filter = [];
			var input;

			// Filter Title
			if (flt.title)
			{
				$filter.push(_createFilterTitle(_get(flt.title), isOpen));
			}

			$filter.push("<div class='collapse checkBoxUl' " + (isOpen ? "" : "style='display:none;'") + ">");
			$filter.push("<ul class='collapse'>");

			for (var i in inputs)
			{
				input = inputs[i];

				$filter.push("<li class='filter-search-column'>");

				// Label
				if (input.label)
				{
					$filter.push(_createLabelField(_get(input.label), input.span));
				}

				// Span
				if (input.span)
				{
					$filter.push(_createSpanField(_get(input.label)));
				}

				// Field
				switch (input.type)
				{
					case _options.filterTypes[1]: //text
						options._parameters[input.name] = input;

						$filter.push(_createTextField(input.name, input.clazz, input.style, input.maxsize, (input.value ? input.value : ""),
							input.hint || (input.hintMessageKey ? _get(input.hintMessageKey) : "")));
						break;

					case _options.filterTypes[2]: //select
						options._parameters[input.name] = input;

						$filter.push(_createSelectField(input.name, input.options, input.clazz));
						break;

					case _options.filterTypes[3]: //range
						options._parameters[input.name] = input;

						$filter.push(_createRangeField(input.name, input));
						break;
				}

				// Field Button
				if (input.goButtonLabel)
				{
					$filter.push(_createGoButton(_get(input.goButtonLabel)));
				}

				$filter.push("</li>");
			}

			// Filter button
			if (flt.goButtonLabel)
			{
				$filter.push("<li>" + _createGoButton(_get(flt.goButtonLabel)) + "</li>");
			}

			$filter.push("</ul>");
			$filter.push("</div>");
			return $filter.join("");
		};

		//	Create options for filter
		var _createOptionFilters = function(flt, isOpen, filterId, options)
		{
			var $filter 		= [];
			var iFirstOptions 	= options.firstOptions;
			var propertyTitle 	= flt.propertyTitle;
			var propertyValue 	= flt.propertyValue;
			var oSymbol		 	= flt.symbol;
			var dataList		= flt.dataList ? flt.dataList[flt.listObj] ? flt.dataList[flt.listObj] : flt.dataList  : [];
			var length 			= dataList.length;
			var maxCharacters 	= options.maxCharacters;
			var i 				= 0;
			var $select;
			var title;
			var value;
			var data;

			// Filter Title
			$filter.push(_createFilterTitle($.pgsi.locale.get(flt.title), isOpen));

			$filter.push("<div class='collapse checkBoxUl' " + (isOpen ? "" : "style='display:none;'") + ">");

			// All Checked
			$filter.push(_createAllAndNotInCheckbox(flt, true));

			// First filters
			$filter.push("<ul class='ui-listcombobox'>");
			for (; i < iFirstOptions && i < length; i = i + 1)
			{
				data = dataList[i];
				title = data[propertyTitle] || "";
				value = data[propertyValue];

				// TODO - review impl and char limit and symbol
				if ( !$.pgsi.isNullOrUndefined(oSymbol) )
				{
					var symbolTitle 		= data[oSymbol.propertyTitle];
					var symbolClass 		= oSymbol.sClass ? oSymbol.sClass + symbolTitle.toString().toLowerCase() : "";
					var symbolTitleValid 	= typeof symbolTitle === 'string' ? symbolTitle.substr(0, 2) : symbolTitle;

					$filter.push('<li class="checkbox" title="' + title + '">'
							+ '<input type="checkbox" class="checkbox" value="' + value + '"/> '
							+ '<span class="label label-default ' + symbolClass + '">'+ symbolTitleValid +'</span> '
							+ '<span class="title_filter_checkbox">'
							+ (title.length > 15 ? (title.substr(0, 15) + '...') : title) + '</span>'
							+ '</span></li>');
				}
				else
				{
					$filter.push("<li class='checkbox' title=\"" + title + "\">"
							+ "<label><input type='checkbox' class='checkbox' value='" + value + "'/> <span>"
							+ (title.length > maxCharacters ? (title.substr(0, maxCharacters) + "...") : title)
							+ "</span></label></li>");
				}
			}

			$filter.push("</ul>");

			// Select element
			if (i < length)
			{
				$select = ["<select id='" + filterId + "_slc' class='listcombobox' style='display:none'>"];
				for (; i < length; i = i + 1)
				{
					data = dataList[i];
					title = data[propertyTitle] || "";
					value = data[propertyValue];

					if ( !$.pgsi.isNullOrUndefined(flt.listObj) )
					{
						value = data[propertyValue];
					}

					if ( !$.pgsi.isNullOrUndefined(oSymbol) )
					{
						var symbolTitle 		= data[oSymbol.propertyTitle];
						var symbolClass 		= oSymbol.sClass ? oSymbol.sClass + symbolTitle.toString().toLowerCase() : "";
						var symbolTitleValid 	= typeof symbolTitle === 'string' ? symbolTitle.substr(0, 2) : symbolTitle;

						$select.push('<option class="set_id_value" value="' + value
										+ '" data-symbolclass="' + symbolClass
										+ '" data-symboltitlevalid="' + symbolTitleValid + '"> '
										+ title + ' </option>');
					}
					else
					{
						$select.push('<option value="' + value + '">' + title + '</option>');
					}

				}
				$select.push("</select>");
				$filter.push($select.join(''));
			}

			$filter.push("</div>");

			return $filter.join("");
		};


		/**
		 *	---------------------------------------------------------------------------------------------------------
		 *											Events
		 *	---------------------------------------------------------------------------------------------------------
		 */
		 //	Events for search filters
		var _searchFilterEvent = function ($filter, $filterDiv, filter, event, options)
		{
			var $form 		 = $filterDiv.find("form");
			var $fields 	 = $filter.find("input, select, ." + _oConfig.common.rangeSlider);
			var length 		 = $fields.length;
			var oFilterValue = {};
			var i 			 = 0;
			var $field;
			var val;
			var name;
			var sFilterValue;
			var filterValue;

			event.preventDefault();

			// Validate
			if (filter.validate)
			{
				if (filter.validate($form, $filter))
				{
					return false;
				}
			}

			// Load Filter Values
			for (; i < length; i = i + 1)
			{
				$field = $($fields[i]);

				//	input and select
				if ( !$.pgsi.isNullOrUndefined($field.val()) )
				{
					val  = $field.val();
					name = $field.attr("name");

					if (!oFilterValue[name])
					{
						oFilterValue[name] = [];
					}

					oFilterValue[name].unshift(val);
				//	Range slider
				}
				else
				{
					var sMin = $field.closest("div").siblings("p").find("span:eq(0)").text();
					var sMax = $field.closest("div").siblings("p").find("span:eq(1)").text();

					val  = sMin + "|" + sMax;
					name = $field.attr("name");

					if ( !$.pgsi.isNullOrUndefined(oFilterValue[name]) )
					{
						oFilterValue[name] = [val];
					}
				}
			}

			for (i in oFilterValue)
			{
				filterValue = oFilterValue[i];
				sFilterValue = filterValue.join("|");

				// Add parameters
				$.pgsi.pageLoader.setParameter(i, decodeURI(sFilterValue));
			}

			// Add tag
			if (_addSearchTag($filter, options))
			{
				// Reload table
				_reload(options);
			}
		};

		 var _delegateSearchFilterEvents = function(options)
		 {
			var $filterDiv 			= $(options.element);
			var $searchContainer 	= $filterDiv.find("div." + _oConfig.common.filterInputSearch);
			var fnSearchFilterEvent = _searchFilterEvent;
			var filters 			= options.filters;

			$searchContainer.on("click", "." + _oConfig.common.goButton, function(e)
			{
				var $filter = $(this).parents("div." + _oConfig.common.filterInputSearch);
				var filter 	= filters[$filter.attr("id")];
				var $input 	= $filter.find("input");

				if ($input.hasClass("input-clear"))
				{
					$input.val("").removeClass("input-clear");
				}

				fnSearchFilterEvent($filter, $filterDiv, filter, e, options);
			});

			$searchContainer.on("keypress", "input", function(e)
			{
				var $filter;
				var filter;

				if (e.which == 13)
				{
					$filter = $(this).parents("div." + _oConfig.common.filterInputSearch);
					filter 	= filters[$filter.attr("id")];

					fnSearchFilterEvent($filter, $filterDiv, filter, e, options);
				}
			});
		};


		//	Events for others filters
		var _optionFilterEvent = function (options)
		{
			var $filter 			= $(options.element);
			var fnSelectFilterEvent = _selectFilterEvent;

			// Check-boxes Event
			$filter.off("click", "input:checkbox");
			$filter.on("click", "input:checkbox", function(e)
			{
				//Verify if input checked is not in lights
				var isNotInChecked = $(this).parents().hasClass("not-in-checked");

				// Whether return false prevent event to keep ALL checked
				if (fnSelectFilterEvent($(this), $filter, true, options) === false && !isNotInChecked)
				{
					e.preventDefault();
				}
			});
		};


		var _selectFilterEvent = function($checkbox, $filter, bReload, options)
		{
			var $parent = $checkbox.parents("div." + _oConfig.common.filterInput);
			var id 		= $parent.attr("id");
			var value 	= $checkbox.val();
			var options = $filter.data(_options.filterTypes[0]);

			if (value == _oConfig.project.allValue)
			{
				// Unchecked options
				$parent.find(".ui-listcombobox input:checked").prop("checked", false);
				// Unchecked the NOT option
				$parent.find("." + _oConfig.common.notInChecked + " input:checked").prop("checked", false);

				// Remove all tags options by filter type
				_removeAllTags(id, options);

				// Remove all parameter
				_removeParameter(id, null, id, options);

				// Reload table
				if (bReload)
				{
					_reload(options);
				}

				// Return true when checked
				return $checkbox.is(":checked");
			}
			else if (value == _oConfig.project.notInValue)
			{
				// Unchecked options
				$parent.find(".ui-listcombobox input:checked").prop("checked", false);
				// Unchecked the ALL option
				$parent.find("." + _oConfig.common.allChecked + " input:checked").prop("checked", false);

				// Remove all tags options by filter type
				_removeAllTags(id, options);

				// Remove all parameter
				_removeParameter(id, null, id, options);

				// Whether is checked
				if ($checkbox.is(":checked"))
				{
					// Add parameter
					_addParameter(id, value, options);

					// Add Tag
					_addOptionTag($checkbox, options);
				}
				else
				{
					// Remove parameter
					_removeParameter(id, value, id, options);

					// Remove Tag
					_removeTag(id, value, null, options);

					// Check the ALL option
					$parent.find("." + _oConfig.common.allChecked).find("input").prop("checked", true);

				}

				// Reload table
				if (bReload)
				{
					_reload(options);
				}

				// Return true when checked
				return $checkbox.is(":checked");
			}
			else
			{
				// Remove Not In Tag
				_removeTag(id, _oConfig.project.notInValue, null, options);

				// Unchecked the ALL option
				$parent.find("." + _oConfig.common.allChecked + " input:checked").prop("checked", false);
				// Unchecked the NOT option
				$parent.find("." + _oConfig.common.notInChecked + " input:checked").prop("checked", false);

				// Whether is checked
				if ($checkbox.is(":checked"))
				{
					// Add parameter
					_addParameter(id, value, options);

					// Add Tag
					_addOptionTag($checkbox, options);
				}
				else
				{
					// Remove parameter
					_removeParameter(id, value, id, options);

					// Remove Tag
					_removeTag(id, value, null, options);
				}

				// Whether no options selected
				if ($parent.find(".ui-listcombobox input:checked").length == 0)
				{
					// Check the ALL option
					$parent.find("." + _oConfig.common.allChecked).find("input").prop("checked", true);
				}
			}

			// Reload table
			if (bReload)
			{
				_reload(options);
			}
		};

//		Events for tag remove (drop filter)
		var _removeTagEvent = function ($tag, $filterDiv, bReload, keepFilters, options)
		{
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

			if ($tag.hasClass(_oConfig.common.clear))
			{
				if (keepFilters && keepFilters.length)
				{
					$tags = $parent.parent().find("li:not(li:last,." + keepFilters.join(",.") + ")");
				}
				else
				{
					$tags = $parent.parent().find("li:not(li:last)");
				}

				length 			= $tags.length;
				disabledCount 	= 0;

				for (i = 0; i < length; i = i + 1)
				{
					$tag = $($tags[i]);

					$input = _getInput($tag, options);

					if ($input.is(":disabled"))
					{
						disabledCount += 1; // count the filters disabled
						continue;
					}

					if ($input.is(":checked"))
					{
						// Remove filter of option type
						$input.prop("checked", false);
					}
					else if ($input.attr("type") == _options.filterTypes[1])
					{
						// Remove filter of search type
						$input.val("");
					}
					else if ($input.is(_options.filterTypes[2]))
					{
						// Remove filter of select type
						$input.val(_oConfig.project.allValue);
					}

					// Remove parameter and tag
					var sField 		= $tag.attr("class").split(" ")[0];
					var sTagName 	= $tag.attr("name");
					var sInputName;
					var sInputVal;

					if ($input.size())
					{
						sInputName 	= $input.attr("name");
						sInputVal 	= $input.val();
					}
					else
					{
						sInputName 	= sTagName;
						sInputVal 	= sTagName;
					}

					_removeTag(sField, sTagName, $tag, options);
					_removeParameter(sField, sInputVal, sInputName, options);
				}

				// Check all 'ALL' inputs
				$filterDiv.find("." + _oConfig.common.allChecked + " input:not(:checked,:disabled)").prop("checked", true);

				// Could call the table reload
				if (bReload && length > disabledCount)
				{
					_reload(options);
				}

				// hide the polygon filters tags div
				if( $('#polygon-filters .drawing').length == 0 )
				{
					$(".filter-results-container-map").addClass("hide");
				}
			}
			else
			{
				$input = _getInput($parent, options);

				if ($input.length)
				{
					if ($input.is(":checked"))
					{
						// Option Filter Type: Remove parameter, tag and filter than could call the table reload
						_selectFilterOption($input, bReload, options);
					}
					else if ($input.attr("type") == _options.filterTypes[1])
					{
						// Search Filter Type:
						// Remove filter
						$input.val("");

						filterId 	= $parent.attr("class").split(" ")[0];
						filter 		= options.filters[filterId];

						// Remove whether has associated tags
						if (filter.joinTags)
						{
							$tags 	= $parent.parent().find("li." + filterId);
							length 	= $tags.length;

							for (i = 0; i < length; i = i + 1)
							{
								$tag 	= $($tags[i]);
								$input 	= _getInput($tag, options);
								$input.val("");

								// Remove parameter and tag
								_removeTag(filterId, $tag.attr("name"), $tag, options);
								_removeParameter(filterId, null, $input.attr("name"), options);
							}
						}

						// Remove parameter and tag
						_removeTag($parent.attr("class").split(" ")[1], $parent.attr("name"), $parent, options);
						_removeParameter($parent.attr("class").split(" ")[0], null, $input.attr("name"), options);

						// Could call the table reload
						if (bReload)
						{
							_reload(options);
						}
					}
					else if ($input.is(_options.filterTypes[2]))
					{
						// Remove parameter and tag
						_removeTag($parent.attr("class").split(" ")[0], $parent.attr("name"), $parent, options);
						_removeParameter($parent.attr("class").split(" ")[0], null, $input.attr("name"), options);

						// Remove filter of search type
						$input.val(_oConfig.project.allValue);

						// Could call the table reload
						if (bReload)
						{
							_reload(options);
						}
					}
				}
				else if (pgsi.util.filter.noFiltersTags && pgsi.util.filter.noFiltersTags[$parent.attr("id")])
				{
					// Remove parameter and tag
					_removeTag($parent.attr("class").split(" ")[0], $parent.attr("name"), $parent, options);
					_removeParameter($parent.attr("class").split(" ")[0], null, $parent.attr("name"), options);

					// Could call the table reload
					if (bReload)
					{
						_reload(options);
					}
				}
				else
				{
					// Map
					sValue  = $parent.data("value");
					aIds 	= $parent.data("aIds") || [];

					// Clear Selected checkbox
					for (var i = 0; i < aIds.length; i++)
					{
						$.pgsi.table.checkbox(options.table).selected(aIds[i], true);
						options.table.find(":checkbox[value=" + aIds[i] + "]").prop("checked", false);
					}

					$.pgsi.table.checkbox(options.table).setTotalResult();

					// Remove Polygon
					$.pgsi.map.destroyVectorFeatureById(sValue);

					$parent.remove();

					// hide the polygon filters tags div
					if( $('#polygon-filters .drawing').length == 0 )
					{
						$(".filter-results-container-map").addClass("hide");
					}
				}
			}
		};

		//	Event for clean filters
		var _delegateTagEvents = function (options)
		{
			var $filter 		 = $(options.element);
			var fnRemove 		 = function (e)
			{
				e.preventDefault();
				_removeTagEvent($(this), $filter, true, null, options);

				// IE8 javascript throws exceptions
				e.stopPropagation();
			};

			// Remove Tag and Filter
			$(options.tagsDiv).find(".filter-container").on("click", "a.clear, span.type.remove", fnRemove);
			$(options.tagsMapDiv).find(".filter-container").on("click", "a.clear, span.type.remove", fnRemove);
		};

		//
		var _toggleEvent = function (options)
		{
			$(options.element).find("." + _oConfig.common.toggle).click(function()
			{
				var $this = $(this);

				// hide filter
				$this.next("." + _oConfig.common.collapse).toggle(_oConfig.common.blind, null, 500);

				if ($this.hasClass(_oConfig.common.on))
				{
					$this.switchClass(_oConfig.common.on, _oConfig.common.off, 500);
				}
				else
				{
					$this.switchClass(_oConfig.common.off, _oConfig.common.on, 500);
				}

				return false;
			});
		};

		//	Add events for side slide
		var _slideEvent = function (options)
		{
			var $filters 		 = $(options.element);
			var $filterContainer = $(".yui-b", $filters);
			var $tableContainer	 = $(".yui-b", "#yui-main");
			var $arrowToggle	 = $("." + _oConfig.common.sideSlideToggle, $filters);
			var iMarginLeft		 = $tableContainer.css("margin-left");

			var fnAnimationStart = function ()
			{
				options.table.data("fixedHeader").fnSetWidth("100%");
			};

			var fnAnimationComplete = function ()
			{
				options.table.data("fixedHeader").fnUpdate(true);
			};

			// Whether filter is hide and navigate between tabs, keep the filter hide
			if ($tableContainer.css("margin-left") == "0px")
			{
				$filterContainer.css("margin-left", -170);
				$arrowToggle.find("span").toggle();
			}

			$arrowToggle.click(function()
			{
				var $this = $(this);

				if ($this.find("span:visible").hasClass(_oConfig.common.hideSideSlide))
				{
					// Animate to hide filters
					$filterContainer.animate({marginLeft : -170}, "slow");
					//fnAnimationStart and fnAnimationComplete are being used to fix the fixedHeader resizes
					$tableContainer.animate({marginLeft : 0},{start : fnAnimationStart, complete : fnAnimationComplete, duration: "slow"});
				}
				else
				{
					// Animate to show filters
					$filterContainer.animate({marginLeft : 0}, "slow");
					//fnAnimationStart and fnAnimationComplete are being used to fix the fixedHeader resizes
					$tableContainer.animate({marginLeft : iMarginLeft}, {start : fnAnimationStart, complete : fnAnimationComplete, duration: "slow"});
				}

				// Toggle the arrow
				$this.find("span").toggle();
			});
		};

		//	Event for remove
		var _validationRemoveEvent = function (options)
		{
			$(options.element).find("." + _oConfig.common.formError).on("click", "div." + _oConfig.common.formErrorContent, function(e)
			{
				var $this = $(this);
				$this.next().remove();
				$this.remove();
			});
		};

		//	Events for customize filter
		var _customizeFiltersEvent = function (options)
		{
			var fnCallback = function(e)
			{
				e.preventDefault();
				$("#customize-filter").wCustomize("Filters", null, options._objFiltersCustomize, "smartpointlist", null);

				// TODO - future commons customize
				/*$.pgsi.customization.open({
					sType : _oConfig.project.customizationType
				});*/
			};

			$(options.element).off(	"click", "#" + _oConfig.common.customAction, fnCallback);
			$(options.element).on(	"click", "#" + _oConfig.common.customAction, fnCallback);
		};

		var _getInput = function ($tag, options)
		{
			var clazz = $tag.attr("class").split(" ");

			if (clazz.length > 1)
			{
				if ($(options.element).find("#" + clazz[1] + " input").size())
				{
					return $(options.element).find("#" + clazz[1] + " input");
				}

				return $(options.element).find("#" + clazz[1]);
			}

			return $(options.element).find("#" + clazz[0] + " input[value='" + $tag.attr("name") + "']");
		};


		/**
		 *	---------------------------------------------------------------------------------------------------------
		 *	Main functions
		 *	Create filter and call the others functions such as event handler
		 *	---------------------------------------------------------------------------------------------------------
		 */
		var _create = function(options)
		{
			//	Merge objects, default with options passed
			options = $.extend(true, {}, _options, options);

			var $filter 		= [];
			var count 			= 0;
			var filters 		= options.filters;
			var iFirstFilters 	= options.openFirstFilters;
			var $tagsDiv 		= $(options.tagsDiv);
			var filter;
			var isOpen;
			var type;

			options.defaultSearchName = $.pgsi.locale.get(_oConfig.messages.defaultSearchNameLabel);

			$filter.push("<div class='filter-vert rounded'>");

			// Filters
			for (var i in filters)
			{
				filter = filters[i];

				if (filter)
				{
					filter.id = i;

					$filter.push("<div id='" + i + "' class='" + _oConfig.common.filterInput + " " + filter.type + " ui-widget'>");

					isOpen = count < iFirstFilters;

					switch (filter.type)
					{
						case _options.filterTypes[4] :

							type = "textFilter";

							$filter.push(_createSearchFilters(filter, isOpen, options));

							break;

						case _options.filterTypes[0] :

							options._parameters[filter.urlParameter] = filter;

							type = "checkBox";

							$filter.push(_createOptionFilters(filter, isOpen, i, options));
							break;

						default :
							$filter.pop();
							continue;
							break;
					}

					if (i.indexOf(_options.filterTypes[4]) === -1)
					{
						options._objFiltersCustomize.push({
							name : $.pgsi.locale.get(filter.title),
							session : i,
							type : type
						});
					}

					$filter.push("</div>");

					count = count + 1;
				}
			}

			// Whether has customize button
			if (options.hasCustomize)
			{
				$filter.push(_createCustomizeButton());
			}

			$filter.push("</div>");

			if (options.defaultContainer)
			{
				$filter.splice(0, 0, "<div class='yui-b'><form class='" + _oConfig.common.viewsVert + "'>");
				$filter.splice(1, 0, options.createTitle());

				// Side slide
				if (options.hasSideSlide)
				{
					$filter.splice(2, 0, _createSideSlide(options));
				}

				$filter.push("</form></div>");

				// Set Filters on DOM
				$(options.element).html($filter.join(""));
			}
			else
			{
				// Set Filters on DOM
				$(options.element).append($filter.join(""))
					.prepend(options.createTitle());
			}

			// Hide 'Reset Filter'
			if ($tagsDiv.find("." + _oConfig.common.filterContainer + " li").length == 1)
			{
				$tagsDiv.addClass("hide");
			}

			// Set events
			_delegateSearchFilterEvents(options);
			_optionFilterEvent(options);
			_delegateTagEvents(options);
			_toggleEvent(options);
			_slideEvent(options);
			_validationRemoveEvent(options);
			_customizeFiltersEvent(options);

			// Datepicker
			_startDatePicker(options);

			// Range Slider
			_startRangeSlider(options);

			// jQuery Combobox and auto complete for Option Filters
			_createCombobox(options);

			//	TODO : Duplicated method on jquery.customs
			_autocomplete(options);

			// Load filters from url
			_loadFilters(options);

			if (options.createReload)
			{
				_reload(options);
			}

			$(options.element).data(_options.filterTypes[0], options);

			if ( !$.pgsi.isNullOrUndefined(options.fnCreateCallback) )
			{
				options.fnCreateCallback.call();
			}
		};

		var _destroy = function(options, clearActive, aKeepFilters)
		{
			// options can be either an object or an selector
			if ("string" === typeof options)
			{
				options	= $(options).data(_options.filterTypes[0]);
			}

			if ( $.pgsi.isNullOrUndefined(options) )
			{
				return;
			}

			var oFilter				= $(options.element);
			var oFilterContainer	= oFilter.parent();

			oFilter.remove();
			oFilterContainer.append("<div id='" + options.element.replace("#","") + "'></div>");

			_removeAllTags(null, options, clearActive, aKeepFilters);

			var $TagsDiv 			= $(options.tagsDiv);
			var $TagsMapDiv 		= $(options.tagsMapDiv);

			// clone the filter container without the events
			var $TagsDivClone 		= $TagsDiv.find(".filter-container").clone();
			var $TagsMapDivClone 	= $TagsMapDiv.find(".filter-container").clone();

			// Clear the container
			$TagsDiv.empty();
			$TagsMapDiv.empty();

			// Refill the container with the same elements without the events
			$TagsDiv.append($TagsDivClone);
			$TagsMapDiv.append($TagsMapDivClone);
		};

		var _rebuild = function(options, clearActive, aKeepFilters)
		{
			_destroy(options, clearActive, aKeepFilters);
			_create(options);
		};

		var _actionPerformed = function(bReset)
		{
			if(!$.pgsi.isNullOrUndefined(bReset))
			{
				_oConfig.common.bActionPerformed = false;
			}

			return _oConfig.common.bActionPerformed;
		};

		var _setFilterOptions = function ($checkboxs, bCheckbox, sElement)
		{
			var $filterDiv 	= $(sElement);
			var iCheckbox 	= $checkboxs.length;
			var i 			= 0;

			var $filter;
			var $checkbox;

			for (; i < iCheckbox; i = i + 1)
			{
				$checkbox = $($checkboxs[i]);

				$checkbox.prop("checked", bCheckbox);

				$filter = $checkbox.parents("div.filter-input");

				if ( bCheckbox )
				{
					_addParameter( $filter.attr("id"), $checkbox.val(), $filterDiv.data(_options.filterTypes[0]) );
					_addOptionTag( $checkbox, $filterDiv.data(_options.filterTypes[0]) );
				}
				else
				{
					_removeTag( $filter.attr("id"), $checkbox.val(), null, $filterDiv.data(_options.filterTypes[0]) );
					_removeParameter( $filter.attr("id"), $checkbox.val(), $checkbox.val(), $filterDiv.data(_options.filterTypes[0]) );
				}
			}
		};

		var _getParamMultipleValues = function(param, value)
		{
			return decodeURI($.address.parameter(param)).split("|").indexOf(value.toUpperCase());
		};

		/**
		 *	Return Public Methods and objects
		 */
		return (
		{
			addTag 					: _addTag,
			create					: _create,
			destroy					: _destroy,
			setGeneralConfig		: _setGeneralConfig,
			rebuild 				: _rebuild,
			actionPerformed			: _actionPerformed,
			setFilterOptions		: _setFilterOptions,
			getParamMultipleValues	: _getParamMultipleValues
		});
	})();
});