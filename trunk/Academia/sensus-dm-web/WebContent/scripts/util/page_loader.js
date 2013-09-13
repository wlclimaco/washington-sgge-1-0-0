(function($) {

	$.fn.pageLoader = {

		pageParameter : "pg",

		initLoadParameter : "initialLoad",

		titlePrefix : sensus.locale.get("commons.pages.sensus"),

		titleService : sensus.locale.get("main.page.manage_" + sensus.settings.serviceType.toLowerCase()),

		titleSeparator : " - ",

		load : function (url, $content, $link, callback, bUpdateUrl, bStartProgressBar) {

			// Clear checkbox at table
			sensus.widgets.datatable.clearSelectsFunction.call($(".list"));

			if (bStartProgressBar == true || $.sc.isNullOrUndefined(bStartProgressBar)) {

				sensus.util.page.startGlobalProgressBar();
			}

			var defaultAppMenu,
				splitedUrl;

			if ($link && $link.length) {

				this.activeMenu($link.parents("ul"), $link);

			} else {

				splitedUrl = url.split("?")[0];

				defaultAppMenu = $("#sensus-menu");

				$link = defaultAppMenu.find("a[href^='" + splitedUrl + "']");

				if (!$link.length) {

					splitedUrl = splitedUrl.split("/")[0];

					$link = defaultAppMenu.find("a[href^='" + splitedUrl + "']");

					if (!$link.length) {

						$link = $(this.mapPagesMenu[splitedUrl]);
					}
				}

				this.activeMenu(defaultAppMenu, $link);
			}

			if (bUpdateUrl !== false) {

				$.address.value(this.pageUrl(url));
			}

			this.title(this.currentPage());

			this.ajaxLoad(url, $content, callback);
		},

		title : function (currentPage) {

			if (currentPage.indexOf("/") != -1) {

				currentPage = currentPage.split("/")[0];
			}

			$.address.title([this.titlePrefix, this.titleService,
                 sensus.locale.get("commons.pages." + (this.mapPagesTitle[currentPage] || currentPage))]
                 	.join(this.titleSeparator));
		},

		mapPagesTitle : {

			"electriclist" : "smartPoints",
			"waterlist" : "smartPoints",
			"gaslist" : "smartPoints",
			"group" : "Groups",
			"process" : "systemintelligence",
			"schedule" : "systemintelligence",
			"settings" : "profile"
		},

		mapPagesMenu : {

			"device" : "#menu-smartpoint",
			"schedule" : "#menu-systemintelligence"
		},

		pageUrl : function (url) {

			var _service = sensus.settings.serviceType.toLowerCase(),
				splitedUrl;

			if (url.indexOf("?") != -1) {

				splitedUrl = url.split("?");

				return ["/", _service, "?", this.pageParameter, "=", splitedUrl[0], "&", splitedUrl[1]].join("");
			}

			return ["/", _service, "?", this.pageParameter, "=", url].join("");
		},

		activeMenu : function ($menu, $item) {

			$menu.find("a.active").removeClass("active");
			$item.addClass("active");
		},

		currentPage : function () {

			return $.address.parameter(this.pageParameter);
		},

		currentURL : function () {

			return $.address.value();
		},

		currentService : function () {

			return $.address.value().split("?")[0].replace("/", "");
		},

		tabUrl : function (url) {

			var preTabUrl;
			var posTabUrl;
			var barIndexOf = url.indexOf("/");
			var pIndexOf = url.indexOf("?");

			if (barIndexOf != -1 && (pIndexOf == -1 || barIndexOf < pIndexOf)) {

				preTabUrl = url.substring(0, barIndexOf);
				posTabUrl = url.substring(barIndexOf, url.lenght);
				return preTabUrl + "/tab" + posTabUrl;
			}

			return "tab/" + url;
		},

		loadTab : function (url, $tabContent) {

			var _pageLoader = this;

			$.address.value(_pageLoader.pageUrl(url));

			_pageLoader.ajaxLoad(_pageLoader.tabUrl(url), $tabContent);
		},

		tabs : function ($ul, $tabContent, fnUrlInterceptor) {

			var _pageLoader = this,
				firstTab = _pageLoader.currentPage(),
				$initTab,
				aPage,
				query;

			$ul.on("click", "li a", function(e) {

				var _this = $(this),
					_href = fnUrlInterceptor ? fnUrlInterceptor(_this) : _this.attr("href");

				e.preventDefault();

				sensus.util.page.startGlobalProgressBar();

				// Clear checkbox at table
				sensus.widgets.datatable.clearSelectsFunction.call($(".list"));

				$.address.value(_pageLoader.pageUrl(_href));

				_pageLoader.activeMenu($ul, _this);

				_pageLoader.ajaxLoad(_pageLoader.tabUrl(_href), $tabContent);
			});

			// load first tab
			$initTab = $ul.find("a[href='" + firstTab + "']");

			if (!$initTab.length) {

				$initTab = $ul.find("a:first");
			}

			_pageLoader.activeMenu($ul, $initTab);

			query = this.queryString();

			this.loadTab($initTab.attr("href") + (query ? ("?" + query) : ""), $tabContent);
		},

		parameterNames : function (invalidParameters) {

			var parameterNames = $.address.parameterNames();
			var length = parameterNames.length;
			var parameterName;
			var arr = [];
			var i = 0;

			for (; i < length; i = i + 1) {

				parameterName = parameterNames[i];

			    if ($.inArray(parameterName, invalidParameters) == -1) {

			    	arr.push(parameterName);
			    }
			}

			return invalidParameters && invalidParameters.length ? arr : parameterNames;
		},

		queryString : function (parameterNames) {

			var parameterName;
			var length;
			var value;
			var arr;
			var i;

			if (!parameterNames) {

				parameterNames = this.parameterNames([this.pageParameter]);
			}

			length = parameterNames.length;
			arr = [];

			for (i = 0; i < length; i = i + 1) {

				parameterName = parameterNames[i];
				value = $.address.parameter(parameterName);

				if (value) {

					arr.push(parameterName + "=" + value);
				}
			}

			return arr.join("&");
		},

		ajaxLoad : function (url, $content, callback) {

			$.ajax({
				url : sensus.settings.appRoot + "/" + url,
				method : 'GET',
				cache : false,
				async : true,
				success : function (html) {

					$content.html(html);

					if (callback) {

						callback();
					}
				}
			});
		},

		parameter : function (parameter, value) {

			$.address.history(false);
			$.address.parameter(parameter, value);
			$.address.history(true);
		},

		parameters : function (parameters) {

			var i;

			$.address.history(false);

			for (i in parameters) {

				$.address.parameter(i, parameters[i]);
			}

			$.address.history(true);
		}
	};

})(jQuery);