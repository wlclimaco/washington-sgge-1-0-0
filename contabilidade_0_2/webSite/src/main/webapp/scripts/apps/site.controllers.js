(function() {
  angular.module('wdApp.apps.site', []).controller('SiteController',
   ['$scope', '$rootScope', 'SysMgmtData', 'toastr', 'toastrConfig','$location',
     function($scope, $rootScope, SysMgmtData, toastr, toastrConfig,location) {
		var pvm = this;
		var initLoad = true; //used to ensure not calling server multiple times
		var fetch_url =    WebDaptiveAppConfig.base_site_url +  WebDaptiveAppConfig.fetch_url;
		var refresh_url =  WebDaptiveAppConfig.base_site_url +  WebDaptiveAppConfig.refresh_url;
		var create_url =   WebDaptiveAppConfig.base_site_url +  WebDaptiveAppConfig.create_url;
		var update_url =   WebDaptiveAppConfig.base_site_url +  WebDaptiveAppConfig.update_url;
		var delete_url =   WebDaptiveAppConfig.base_site_url +  WebDaptiveAppConfig.delete_url;
		pvm.isActive = false;
		toastrConfig.closeButton = true;

		//form model data
		pvm.county = {
			id: '',
			description: ''
		};

		 pvm.team = 'test'// $scope.$location.search().keyword
	//	 cvm.team =  $scope.$location.url(); new qat.model.county


        //  var commonControllers =  angular.module('wdApp.controllers', ['LoginController']);
          //console.log(commonControllers.login());
          SysMgmtData.processPostPageData(fetch_url, new qat.model.siteInquiryRequest( 100/20, true, "http://localhost:8080/webSite/"), function(res){
               console.log(res)
               pvm.site = new qat.model.Site(res.sites[0]);
          });


	/** create by system gera-java version 1.0.0 19/05/2016 15:48 : 53*/

var servico = function (){
	var _Servico = []

     _Servico.id =  1,
     _Servico.nome =  'nome_1',
     _Servico.descricao =  'descricao_2',
	 _Servico.preco = _Preco;
     _Servico.parentId       = 1;
     _Servico.emprId         = 1;
     _Servico.processId      = 1;
     _Servico.tableEnumValue = 1;
     _Servico.modelAction    = "INSERT";
     _Servico.createUser     = "rod";
     _Servico.createDateUTC  = 1463683733871;
     _Servico.modifyUser     = "rod"
     _Servico.modifyDateUTC  = 1463683733871;

     return _Servico;
}

/** create by system gera-java version 1.0.0 19/05/2016 15:48 : 53*/

//ServicoByPlano Object
var servico = function (){
var _ServicoByPlano = []

     _ServicoByPlano.id =  1,
     _ServicoByPlano.parentId =  2,
     _ServicoByPlano.servico =  'servico_2',
     _ServicoByPlano.parentId       = 1;
     _ServicoByPlano.emprId         = 1;
     _ServicoByPlano.processId      = 1;
     _ServicoByPlano.tableEnumValue = 1;
     _ServicoByPlano.modelAction    = "INSERT";
     _ServicoByPlano.createUser     = "rod";
     _ServicoByPlano.createDateUTC  = 1463683733871;
     _ServicoByPlano.modifyUser     = "rod"
     _ServicoByPlano.modifyDateUTC  = 1463683733871;

      return _Servico;
}

/** create by system gera-java version 1.0.0 19/05/2016 15:48 : 53*/

/** create by system gera-java version 1.0.0 19/05/2016 15:48 : 53*/

//Contato Object
var fnContato = function (){
var _Contato = []

     _Contato.id =  1,
     _Contato.parentId =  2,
     _Contato.dataContato =  1463683733871,
     _Contato.nome =  'nome_3',
     _Contato.motivoValue =  5,
	 _Contato.contatoItensList = _ContatoItens;
     _Contato.parentId       = 1;
     _Contato.emprId         = 1;
     _Contato.processId      = 1;
     _Contato.tableEnumValue = 1;
     _Contato.modelAction    = "INSERT";
     _Contato.createUser     = "rod";
     _Contato.createDateUTC  = 1463683733871;
     _Contato.modifyUser     = "rod"
     _Contato.modifyDateUTC  = 1463683733871;

      return _Servico;
}

/** create by system gera-java version 1.0.0 19/05/2016 15:48 : 53*/

//ContatoItens Object
var fnContatoItens = function (){
var _ContatoItens = []

     _ContatoItens.id =  1,
     _ContatoItens.dataAlt =  1463683733871,
     _ContatoItens.texto =  'texto_2',
     _ContatoItens.titulo =  'titulo_3',
     _ContatoItens.contatoStatus =  5,
     _ContatoItens.visto = true,
     _ContatoItens.parentId       = 1;
     _ContatoItens.emprId         = 1;
     _ContatoItens.processId      = 1;
     _ContatoItens.tableEnumValue = 1;
     _ContatoItens.modelAction    = "INSERT";
     _ContatoItens.createUser     = "rod";
     _ContatoItens.createDateUTC  = 1463683733871;
     _ContatoItens.modifyUser     = "rod"
     _ContatoItens.modifyDateUTC  = 1463683733871;

      return _Servico;
}

/** create by system gera-java version 1.0.0 19/05/2016 15:48 : 53*/

//OrdemServico Object
var fnOrdemServico = function (){
var _OrdemServico = []

     _OrdemServico.id =  1,
     _OrdemServico.emprId =  2,
     _OrdemServico.userId =  'userId_2',
     _OrdemServico.nome =  'nome_3',
     _OrdemServico.data =  1463683733871,
     _OrdemServico.assunto =  'assunto_5',
     _OrdemServico.statusValue =  7,
	 _OrdemServico.OrdemServicoItensList = _OrdemServicoItens;
     _OrdemServico.parentId       = 1;
     _OrdemServico.emprId         = 1;
     _OrdemServico.processId      = 1;
     _OrdemServico.tableEnumValue = 1;
     _OrdemServico.modelAction    = "INSERT";
     _OrdemServico.createUser     = "rod";
     _OrdemServico.createDateUTC  = 1463683733871;
     _OrdemServico.modifyUser     = "rod"
     _OrdemServico.modifyDateUTC  = 1463683733871;

      return _Servico;
}

/** create by system gera-java version 1.0.0 19/05/2016 15:48 : 53*/

//OrdemServicoItens Object
var fnOrdemServicoItens = function (){
var _OrdemServicoItens = []

     _OrdemServicoItens.id =  1,
     _OrdemServicoItens.data =  1463683733871,
     _OrdemServicoItens.texto =  'texto_2',
     _OrdemServicoItens.parentId =  4,
     _OrdemServicoItens.parentId       = 1;
     _OrdemServicoItens.emprId         = 1;
     _OrdemServicoItens.processId      = 1;
     _OrdemServicoItens.tableEnumValue = 1;
     _OrdemServicoItens.modelAction    = "INSERT";
     _OrdemServicoItens.createUser     = "rod";
     _OrdemServicoItens.createDateUTC  = 1463683733871;
     _OrdemServicoItens.modifyUser     = "rod"
     _OrdemServicoItens.modifyDateUTC  = 1463683733871;

      return _Servico;
}

/** create by system gera-java version 1.0.0 19/05/2016 15:48 : 53*/

//Plano Object
var fnPlano = function (){
var _Plano = []

     _Plano.id =  1,
     _Plano.dataInicio =  1463683733871,
     _Plano.dataFinal =  1463683733871,
     _Plano.numeroContrato =  4,
     _Plano.descricao =  'descricao_4',
     _Plano.titulo =  'titulo_5',
	 _Plano.precoList = _Preco;
	 _Plano.servicoList = _Servico;
     _Plano.parentId       = 1;
     _Plano.emprId         = 1;
     _Plano.processId      = 1;
     _Plano.tableEnumValue = 1;
     _Plano.modelAction    = "INSERT";
     _Plano.createUser     = "rod";
     _Plano.createDateUTC  = 1463683733871;
     _Plano.modifyUser     = "rod"
     _Plano.modifyDateUTC  = 1463683733871;

      return _Servico;
}


/** create by system gera-java version 1.0.0 19/05/2016 16:4 : 43*/

//Preco Object
var fnPreco = function (){
var _Preco = []

     _Preco.id =  1,
     _Preco.dataMarcacao =  1463684683948,
     _Preco.precoTypeEnum =  3,
     _Preco.tabelaEnumValue =  4,
     _Preco.valor =  5,
     _Preco.dataProInicial =  1463684683948,
     _Preco.dataProFinal =  1463684683948,
     _Preco.parentId =  8,
     _Preco.emprId =  9,
     _Preco.parentId       = 1;
     _Preco.emprId         = 1;
     _Preco.processId      = 1;
     _Preco.tableEnumValue = 1;
     _Preco.modelAction    = "INSERT";
     _Preco.createUser     = "rod";
     _Preco.createDateUTC  = 1463684683948;
     _Preco.modifyUser     = "rod"
     _Preco.modifyDateUTC  = 1463684683948;

      return _Servico;
}



     //Site Object
     var fnSite = function (){
var _Site = []

     _Site.id =  1,
     _Site.nome =  'nome_1',
     _Site.url =  'url_2',
     _Site.quemSomos =  'quemSomos_3',
     _Site.missao =  'missao_4',
     _Site.visao =  'visao_5',
     _Site.titulo =  'titulo_6',
     _Site.logo =  'logo_7',
     _Site.atorization = true,
     _Site.siteTypeEnum =  10,
	 _Site.servicoList = _Servico;
	 _Site.planoList = _Plano;
     _Site.parentId       = 1;
     _Site.emprId         = 1;
     _Site.processId      = 1;
     _Site.tableEnumValue = 1;
     _Site.modelAction    = "INSERT";
     _Site.createUser     = "rod";
     _Site.createDateUTC  = 1463683733871;
     _Site.modifyUser     = "rod"
     _Site.modifyDateUTC  = 1463683733871;

      return _Servico;
}

     //pvm.site = new qat.model.Site(_Site);

    }

  ]);
}).call(this);



