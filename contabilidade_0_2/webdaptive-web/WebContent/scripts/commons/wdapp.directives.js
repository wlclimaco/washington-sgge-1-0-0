(function() {
  'use strict';
	var commonDirectives = angular.module('wdApp.directives', []);
	commonDirectives.directive('toggleNavCollapsedMin', ['$rootScope', function($rootScope) {
		  return {
			restrict: 'A',
			link: function(scope, ele, attrs) {
			  var wdApp;
			  wdApp = $('#wdApp');
			  return ele.on('click', function(e) {
				if (wdApp.hasClass('nav-collapsed-min')) {
				  wdApp.removeClass('nav-collapsed-min');
				} else {
				  wdApp.addClass('nav-collapsed-min');
				  $rootScope.$broadcast('nav:reset');
				}
				return e.preventDefault();
			  });
			}
		  };
	}]);
  
	commonDirectives.directive('collapseNav', [function() {
		return {
			restrict: 'A',
			link: function(scope, ele, attrs) {
			  var $a, $aRest, $wdApp, $lists, $listsRest, $nav, $window, Timer, prevWidth, updateClass;
			  $window = $(window);
			  $lists = ele.find('ul').parent('li');
			  $lists.append('<i class="fa fa-caret-down icon-has-ul-h"></i><i class="fa fa-caret-right icon-has-ul"></i>');
			  $a = $lists.children('a');
			  $listsRest = ele.children('li').not($lists);
			  $aRest = $listsRest.children('a');
			  $wdApp = $('#wdApp');
			  $nav = $('#nav-container');
			  $a.on('click', function(event) {
				var $parent, $this;
				if ($wdApp.hasClass('nav-collapsed-min') || ($nav.hasClass('nav-horizontal') && $window.width() >= 768)) {
				  return false;
				}
				$this = $(this);
				$parent = $this.parent('li');
				$lists.not($parent).removeClass('open').find('ul').slideUp();
				$parent.toggleClass('open').find('ul').slideToggle();
				return event.preventDefault();
			  });
			  $aRest.on('click', function(event) {
				return $lists.removeClass('open').find('ul').slideUp();
			  });
			  scope.$on('nav:reset', function(event) {
				return $lists.removeClass('open').find('ul').slideUp();
			  });
			  Timer = void 0;
			  prevWidth = $window.width();
			  updateClass = function() {
				var currentWidth;
				currentWidth = $window.width();
				if (currentWidth < 768) {
				  $wdApp.removeClass('nav-collapsed-min');
				}
				if (prevWidth < 768 && currentWidth >= 768 && $nav.hasClass('nav-horizontal')) {
				  $lists.removeClass('open').find('ul').slideUp();
				}
				return prevWidth = currentWidth;
			  };
			  return $window.resize(function() {
				var t;
				clearTimeout(t);
				return t = setTimeout(updateClass, 300);
			  });
			}
		};
    }]);
  
	commonDirectives.directive('highlightActive', [function() {
		return {
			restrict: "A",
			controller: [
			  '$scope', '$element', '$attrs', '$location', function($scope, $element, $attrs, $location) {
				var highlightActive, links, path;
				links = $element.find('a');
				path = function() {
				  return $location.path();
				};
				highlightActive = function(links, path) {
				  path = '#' + path;
				  return angular.forEach(links, function(link) {
					var $li, $link, href;
					$link = angular.element(link);
					$li = $link.parent('li');
					href = $link.attr('href');
					if ($li.hasClass('active')) {
					  $li.removeClass('active');
					}
					if (path.indexOf(href) === 0) {
					  return $li.addClass('active');
					}
				  });
				};
				highlightActive(links, $location.path());
				return $scope.$watch(path, function(newVal, oldVal) {
				  if (newVal === oldVal) {
					return;
				  }
				  return highlightActive(links, $location.path());
				});
			  }
			]
		};
    }]);
  
	commonDirectives.directive('uiFooter', [function() {
		return {
			restrict: 'A',
			replace: true,
			templateUrl: "views/ui/footer.html",
			controller: ['$scope', function ($scope) {
				$scope.cDate = new Date().getFullYear();
			}]
		};
    }]);
  
	commonDirectives.directive('slimScroll', [function() {
		return {
			restrict: 'A',
			link: function(scope, ele, attrs) {
			  return ele.slimScroll({
				height: attrs.scrollHeight || '100%'
			  });
			}
		};
    }]);
	
    commonDirectives.directive('focus', [ '$timeout', function ($timeout) {
			return {
				scope : {
					trigger : '@focus'
				},
				link : function(scope, element) {
					scope.$watch('trigger', function(value) {
						if (value === "true") {
							$timeout(function() {
								element[0].focus();
							});
						}
					});
				}
			};
    } ]);	

})();
