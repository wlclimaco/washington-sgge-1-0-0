angular.module("wdApp.apps.produto", ["ngTable"]);

(function() {
  "use strict";

  angular.module("wdApp.apps.produto").controller("produtoController",
  ['$scope', 'SysMgmtData', 'toastr',
	function($scope, SysMgmtData, toastr) {

		$scope.tabs = [{
            title: 'Produto',
            url: 'one.tpl.html'
        }, {
            title: 'Dados Tributarios',
            url: 'two.tpl.html'
        }, {
            title: 'Preço/Estoque',
            url: 'three.tpl.html'
        }, {
            title: 'Imagens',
            url: 'imagens'
        }, {
            title: 'Historico',
            url: 'historico'

    }];



    $scope.currentTab = 'one.tpl.html';

    $scope.onClickTab = function (tab) {
        $scope.currentTab = tab.url;
    }
    $scope.onClickTabb = function (tab) {
       debugger
    }

    $scope.isActiveTab = function(tabUrl) {
        return tabUrl == $scope.currentTab;
    }

	$scope.submitt = function() {

		console.log($scope.produto)
		debugger
		//fnMontaObjeto();
		//console.log($scope.empresa)
		//processPostData(create_url, new qat.model.reqEmpr($scope.empresa ,true, true), true);
	};
    $scope.produto = [];
	$scope.produto.aliquotaCOFINSST="10";
	$scope.produto.aliquotaIPI="10";
	$scope.produto.anotainternas="Anotações internas";
	$scope.produto.cEST="1";
	$scope.produto.cFOPPadraoNFe="50201";
	$scope.produto.cNPJProdutor="057102014";
	$scope.produto.cOFINSSituatributaria="49";
	$scope.produto.categoria="08";
	$scope.produto.cdBarras="1111101041010";
	$scope.produto.classeCigarrosBebidas="Classe cigarros e bebidas";
	$scope.produto.codControleIPI="10";
	$scope.produto.codEnquadramento="999";
	$scope.produto.codigo="00001";
	$scope.produto.dataCadastro="11/06/2016";
	$scope.produto.estAtual="10";
	$scope.produto.estMaximo="50";
	$scope.produto.estMinimo="10";
	$scope.produto.excecaoIPI="1";
	$scope.produto.iCMSOrigem="0";
	$scope.produto.iPISitTributaria="00";
	$scope.produto.icmsSitTributaria="90";
	$scope.produto.informAdicionaisParaNFe="Inform. adicionais para a NFe";
	$scope.produto.margemLucro="1;00";
	$scope.produto.nCM="123456789";
	$scope.produto.nome="Produto";
	$scope.produto.pISSituaTributaria="01";
	$scope.produto.pesobruto="1,0";
	$scope.produto.pesoliquido="1,00";
	$scope.produto.precoCusto="1.00";
	$scope.produto.precoVenda="1.01";
	$scope.produto.qtdSeloIPI="1";
	$scope.produto.tipoCalculo="2";
	$scope.produto.tipoCalculoSubstTrib="1";
	$scope.produto.tipocalculoSubstTrib="1";
	$scope.produto.unidTributada="02";
	$scope.produto.valorTribCOFINS="10";
	$scope.produto.valorTribPISST="05";
	$scope.produto.valorUnidtribPIS="10";


	}
  ])

})();