<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags'%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>


<script type="text/javascript">

// Set main menu active class on click

console.log('eeeeeeeeeeeeeeee');
angular.module("myApp", ["ngTable", "ngResource", "ngTableDemos"]);
(function() {
  "use strict";

  angular.module("myApp").controller("demoController", demoController);
  demoController.$inject = ["NgTableParams", "IssueService"];

  function demoController(NgTableParams, IssueService) {
    this.tableParams = new NgTableParams({}, {
      getData: function(params) {
        return IssueService.query({
          page: params.page(),
          per_page: params.count(),

          state: 'all',
          username: 'esvit',
          repo: 'ng-table'
        }, function(data, headersGetter) {
          var headers = headersGetter(),
            pages = headers['link'].split(', '),
            totalPages = 1;

          // get total pages count
          angular.forEach(pages, function(page) {
            var parts = page.split(' rel=');
            if (parts[1] == '"last"') {
              totalPages = parseInt(parts[0].match(/page=(\d+)/)[1], 10);
            }
            if (totalPages == 1 && parts[1] == '"prev"') { // if last page
              totalPages = parseInt(parts[0].match(/page=(\d+)/)[1], 10) + 1;
            }
          });
          params.total(totalPages * params.count());
          return data;
        }).$promise;
      }
    });
  }
})();

(function() {
  "use strict";

  angular.module("myApp").factory("IssueService", ["$resource", function($resource) {
    return $resource("https://api.github.com/repos/:username/:repo/issues", {
      state: "open"
    }, {
      query: {
        method: "GET",
        isArray: true
      }
    });
  }]);
})();
//$.qat.progressBar.stopGlobal();
</script>

