angular.module("myApp1", ["ngTable","myApp"]);
angular.module("myApp1").controller("demo2Controller", demo2Controller);
  demo2Controller.$inject = ["NgTableParamsd"];

  function demo2Controller(NgTableParamsd, simpleList) {
	  demoController(NgTableParams).demoController(null,null);
  }