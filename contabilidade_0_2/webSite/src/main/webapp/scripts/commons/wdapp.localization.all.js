(function() {
  'use strict';
	var commonLocale =  angular.module('wdApp.localization', []);
	
	commonLocale.factory('Localize', ['$http', '$rootScope', '$window', function($http, $rootScope, $window) {
		var localize;
		localize = {
			language: '',
			url: void 0,
			resourceFileLoaded: false,
			successCallback: function(data) {
			  localize.dictionary = data;
			  localize.resourceFileLoaded = true;
			  return $rootScope.$broadcast('localizeResourcesUpdated');
			},
			setLanguage: function(value) {
			  localize.language = value.toLowerCase().split("-")[0];
			  return localize.initLocalizedResources();
			},
			setUrl: function(value) {
			  localize.url = value;
			  return localize.initLocalizedResources();
			},
			buildUrl: function() {
			  if (!localize.language) {
				localize.language = ($window.navigator.userLanguage || $window.navigator.language).toLowerCase();
				localize.language = localize.language.split("-")[0];
			  }
			  return 'i18n/resources-locale_' + localize.language + '.js';
			},
			initLocalizedResources: function() {
			  var url;
			  url = localize.url || localize.buildUrl();
			  //console.log("i18n file:" + url);
			  return $http({
				method: "GET",
				url: url,
				cache: false
			  }).success(localize.successCallback).error(function() {
				return $rootScope.$broadcast('localizeResourcesUpdated');
			  });
			},
			getLocalizedString: function(value) {

			  var result, valueLowerCase;
			  result = void 0;
			  if (localize.dictionary && value) {
				valueLowerCase = value.toLowerCase();
				//console.log("invl:" + valueLowerCase);
				if (localize.dictionary[valueLowerCase] === '') {
				  result = value;
				} else {
				  result = localize.dictionary[valueLowerCase];
				}
			  } else {
				result = value;
			  }
			  //console.log("out:" + result);		  
			  return result;
			}
		};
		return localize;
    }]);
  
	commonLocale.directive('i18n', ['Localize', function(Localize) {
		var i18nDirective;
		i18nDirective = {
			restrict: "EA",
			updateText: function(ele, input, placeholder) {
			  var result;
			  result = void 0;
			  if (input === 'i18n-placeholder') {
				result = Localize.getLocalizedString(placeholder);
				return ele.attr('placeholder', result);
			  } else if (input.length >= 1) {
				result = Localize.getLocalizedString(input);
				return ele.text(result);
			  }
			},
			link: function(scope, ele, attrs) {
			  scope.$on('localizeResourcesUpdated', function() {
				return i18nDirective.updateText(ele, attrs.i18n, attrs.placeholder);
			  });
			  return attrs.$observe('i18n', function(value) {
				return i18nDirective.updateText(ele, value, attrs.placeholder);
			  });
			}
		};
		return i18nDirective;
    }]);
  
	commonLocale.controller('LangController', ['$scope', 'Localize', function($scope, Localize) {
		$scope.lang = 'English';
		$scope.setLang = function(lang) {
			switch (lang) {
			  case 'English':
				Localize.setLanguage('EN-US');
				break;
			  case 'Español':
				Localize.setLanguage('ES-ES');
				break;
			  case '日本語':
				Localize.setLanguage('JA-JP');
				break;
			  case '中文':
				Localize.setLanguage('ZH-TW');
				break;
			  case 'Deutsch':
				Localize.setLanguage('DE-DE');
				break;
			  case 'français':
				Localize.setLanguage('FR-FR');
				break;
			  case 'Italiano':
				Localize.setLanguage('IT-IT');
				break;
			  case 'Portugal':
				Localize.setLanguage('PT-BR');
				break;
			  case 'Русский язык':
				Localize.setLanguage('RU-RU');
				break;
			  case '한국어':
				Localize.setLanguage('KO-KR');
			}
			return $scope.lang = lang;
		};
		return $scope.getFlag = function() {
			var lang;
			lang = $scope.lang;
			switch (lang) {
			  case 'English':
				return 'flags-american';
			  case 'Español':
				return 'flags-spain';
			  case '日本語':
				return 'flags-japan';
			  case '中文':
				return 'flags-china';
			  case 'Deutsch':
				return 'flags-germany';
			  case 'français':
				return 'flags-france';
			  case 'Italiano':
				return 'flags-italy';
			  case 'Portugal':
				return 'flags-portugal';
			  case 'Русский язык':
				return 'flags-russia';
			  case '한국어':
				return 'flags-korea';
			}
		};
    }]);

})();
