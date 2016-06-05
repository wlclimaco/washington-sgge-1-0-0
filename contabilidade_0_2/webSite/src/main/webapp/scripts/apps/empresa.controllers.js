(function() {
  angular.module('wdApp.apps.empresa', []).controller('EmpresaController',
   ['$scope', '$rootScope', 'SysMgmtData', 'toastr', 'toastrConfig','$location','$http', '$q',
     function($scope, $rootScope, SysMgmtData, toastr, toastrConfig,location ,$http, $q) {
		var pvm = this;
		var initLoad = true; //used to ensure not calling server multiple times
		var fetch_url =    WebDaptiveAppConfig.base_empresa_url +"/empresa"+  WebDaptiveAppConfig.fetch_url;
		var refresh_url =  WebDaptiveAppConfig.base_empresa_url +"/empresa"+   WebDaptiveAppConfig.refresh_url;
		var create_url =   WebDaptiveAppConfig.base_empresa_url +"/empresa"+   WebDaptiveAppConfig.create_url;
		var update_url =   WebDaptiveAppConfig.base_empresa_url +"/empresa"+   WebDaptiveAppConfig.update_url;
		var delete_url =   WebDaptiveAppConfig.base_empresa_url +"/empresa"+   WebDaptiveAppConfig.delete_url;
		pvm.isActive = false;
		toastrConfig.closeButton = true;

		//form model data
		pvm.county = {
			id: '',
			description: ''
		};

         $scope.senha = "";

        $scope.empresa ={

          id                  : 0,
          nome                : '',
          entidadeId          : 0,
          numFunc             : 0,
          statusInicial       : null,
          entidadeEnumValue   : 1,
          socios              :[{
                      cota : 0,
                      porcentagem : "",
                      socioAdm : 0,
                      documentos          : [{
                                 id : 0,
                                 documentoTypeEnumValue : 1,
                                 numero : 0,
                                 data : null,
                                 parentId       : 0,
                                 emprId         : 0,
                                 processId      : 0,
                                 tableEnumValue : 1,
                                 modelAction    : "NONE",
                                 createUser     : $rootScope.user.user,
                                 createDateUTC  : (new Date()).getTime(),
                                 modifyUser     : $rootScope.user.user,
                                 modifyDateUTC  : (new Date()).getTime(),

                              }],
                    }],
          regime              : {
             id : 0,
             nome : '',
             descricao : '',
             statusInicial  : 0,
             parentId       : 0,
             emprId         : 0,
             processId      : 0,
             tableEnumValue : 0,
             modelAction    : "NONE",
             createUser     : $rootScope.user.user,
             createDateUTC  : (new Date()).getTime(),
             modifyUser     : $rootScope.user.user,
             modifyDateUTC  : (new Date()).getTime(),
          },
          documentos          : [{
                       id : 0,
                       documentoTypeEnumValue : 1,
                       numero : 0,
                       data : null,
                       parentId       : 0,
                       emprId         : 0,
                       processId      : 0,
                       tableEnumValue : 1,
                       modelAction    : "NONE",
                       createUser     : $rootScope.user.user,
                       createDateUTC  : (new Date()).getTime(),
                       modifyUser     : $rootScope.user.user,
                       modifyDateUTC  : (new Date()).getTime(),

                    }],
          enderecos           : [{
                       id : 0,
                       codIbge : 0,
                       logradouro : '',
                       bairro : '',
                       numero : '',
                       enderecoTypeValue : 0,
                       cep : '',
                       latitude : '',
                       longitude : '',
                       complemento : '',
                       cidade : {nome : "",estado:''},
                       parentId       : 0,
                       emprId         : 0,
                       processId      : 0,
                       tableEnumValue : 0,
                       modelAction    :  "NONE",
                       createUser     : $rootScope.user.user,
                       createDateUTC  : (new Date()).getTime(),
                       modifyUser     : $rootScope.user.user,
                       modifyDateUTC  : (new Date()).getTime(),

                    }],

          cnaes   : [{
                       id : 0,
                       idCnae : {

                           id : 0,
                           codigo : '',
                           cnae : '',
                           descricao : '',
                           abreviado : '',
                           parentId       : 0,
                           emprId         : 0,
                           processId      : 0,
                           tableEnumValue : 0,
                           modelAction    : "NONE",
                           createUser     : $rootScope.user.user,
                           createDateUTC  : (new Date()).getTime(),
                           modifyUser     : $rootScope.user.user,
                           modifyDateUTC  : (new Date()).getTime(),

                       },
                       parentId       : 1,
                       emprId         : 0,
                       processId      : 0,
                       tableEnumValue : 0,
                       modelAction    : "NONE",
                       createUser     : $rootScope.user.user,
                       createDateUTC  : (new Date()).getTime(),
                       modifyUser     : $rootScope.user.user,
                       modifyDateUTC  : (new Date()).getTime(),
                    }],
          statusList          : [{
                           id : 0,
                           dataStatus :(new Date()).getTime(),
                           statusValue : 0,
                           acaoEnumValue : 0,
                           note : 0,
                           parentId       : 0,
                           emprId         : 0,
                           processId      : 0,
                           tableEnumValue : 0,
                           modelAction    : "NONE",
                           createUser     : $rootScope.user.user,
                           createDateUTC  : (new Date()).getTime(),
                           modifyUser     : $rootScope.user.user,
                           modifyDateUTC  : (new Date()).getTime(),
                        }],
          notes               : [{
                       id : 0,
                       noteText : "NONE",
                       parentId       : 0,
                       emprId         : 0,
                       processId      : 0,
                       tableEnumValue : 0,
                       modelAction    : "NONE",
                       createUser     : $rootScope.user.user,
                       createDateUTC  : (new Date()).getTime(),
                       modifyUser     : $rootScope.user.user,
                       modifyDateUTC  : (new Date()).getTime(),
                    }],
          parentId            : 0,
          emprId              : 0,
          processId           : 0,
          tableEnumValue      : 1,
          modelAction         : 'NONE',
          createUser          : $rootScope.user.user,
          createDateUTC       : (new Date()).getTime(),
          modifyUser          : $rootScope.user.user,
          modifyDateUTC       : (new Date()).getTime(),
          usuarios            :[{
                 id : 0,
                 nome : '',
                 cpf : {
                       id : 0,
                       documentoTypeEnumValue : 0,
                       numero : 0,
                       data : null,
                       parentId       : 0,
                       emprId         : 0,
                       processId      : 0,
                       tableEnumValue : 2,
                       modelAction    : "NONE",
                       createUser     : $rootScope.user.user,
                       createDateUTC  : (new Date()).getTime(),
                       modifyUser     : $rootScope.user.user,
                       modifyDateUTC  : (new Date()).getTime(),

                    },
                 email : '',
                 telefone : '',
                 senha : '',
                 pergunta : '',
                 role : '',
                 language : '',
                 ultAcesso : (new Date()).getTime(),
                 parentId       : 0,
                 emprId         : 0,
                 processId      : 0,
                 tableEnumValue : 0,

                 modelAction    : 'NONE',
                 createUser     : $rootScope.user.user,
                 createDateUTC  : (new Date()).getTime(),
                 modifyUser     : $rootScope.user.user,
                 modifyDateUTC  : (new Date()).getTime()

                    }]

        }


        //reusable processGetData (insert, update, pagedFetch, delete)
        function processPostData(_url, _req, _bLoading){

            SysMgmtData.processPostPageData(_url, _req, function(res){
                if (res){
                    initLoad = true;
                    debugger
                }
                else{
                   debugger
                }
            });
        };

        $scope.submit = function() {
            processPostData(create_url, new qat.model.reqEmpr($scope.empresa ,true, true), true);
          };
        $scope.doIfChecked = function(_ckecked,_value,_nome) {


            console.log(_value);
        }
        console.log($scope.empresa.usuarios[0].nome)
        $scope.empresa.usuarios[0].nome = 'teste'
        console.log($scope.empresa.usuarios[0].nome)
         pvm.teste = function(){
            console.log($scope.empresa);
            var count = 0;
            var bb = [];
            $('.gugu:visible').each(function() {
                bb.push(fnTelefones($(this).val(),count,1));
                count = count + 1;
            });
            $scope.empresa.telefones = bb;

            // email
            count = 0;
            bb = [];
            $('.input-email:visible').each(function() {
                bb.push(fnEmails($(this).val(),count,1));
                count = count + 1;
            });
            $scope.empresa.emails = bb;
            console.log($scope.empresa);
        }
        fnTelefones =function(numero,id,type)
        {

            telefones = {
               id : id,
               typeValue : 0,
               ddd : '',
               numero : numero,
               telefoneTypeEnumValue : type,
               parentId       : 0,
               emprId         : 0,
               processId      : 0,
               tableEnumValue : 0,
               modelAction    : 0,
               createUser     : $rootScope.user.user,
               createDateUTC  : (new Date()).getTime(),
               modifyUser     : $rootScope.user.user,
               modifyDateUTC  : (new Date()).getTime()
            }

            return telefones;
        }

        fnEmails =function(email,id,type)
        {
            emails  = {
               id : id,
               typeValue : 0,
               email : email,
               emailTypeEnumValue : type,
               parentId       : 0,
               emprId         : 0,
               processId      : 0,
               tableEnumValue : 0,
               modelAction    : "NONE",
               createUser     : $rootScope.user.user,
               createDateUTC  : (new Date()).getTime(),
               modifyUser     : $rootScope.user.user,
               modifyDateUTC  : (new Date()).getTime()

            }
            return emails;
        }


        validateTab = function(index) {
            var fv   = $('#empresaForm').data('formValidation'), // FormValidation instance
                // The current tab
                $tab = $('#empresaForm').find('.tab-pane').eq(index);

            // Validate the container
            fv.validateContainer($tab);

            var isValidStep = fv.isValidContainer($tab);
            if (isValidStep === false || isValidStep === null) {
                // Do not jump to the target tab
                return false;
            }

            return true;
        }

          $('#dashboard-header').hide();
          $('#header').hide();
      //    debugger
		 pvm.team = 'test';// $scope.$location.search().keyword
	    // pvm.empresa = new qat.model.Empresa();
        pvm.buscaRCep = function(cepValue){
            debugger
            var cepValue = $scope.empresa.enderecos[0].cep;
            var formatedCep;
            //formatedCep = cepValue.replace(/\D/g, '');
            var formatedCep = cepValue.replace(/\D/g, '');
            var viaCepUrl = "https://viacep.com.br/ws/" + formatedCep + "/json/";
            $http.get(viaCepUrl).then(function(response) {
                var raw;
                //debugger
                raw = response.data;
                console.log(raw)
                $scope.empresa.enderecos[0].cep = raw.cep;
                $scope.empresa.enderecos[0].bairro = raw.bairro;
                $scope.empresa.enderecos[0].complemento = raw.complemento;
                $scope.empresa.enderecos[0].codIbge = raw.ibge;
                $scope.empresa.enderecos[0].cidade.nome = raw.localidade;
                $scope.empresa.enderecos[0].logradouro = raw.logradouro;
                $scope.empresa.enderecos[0].cidade.estado = raw.uf;

            });
        }
 /*         var config = {headers: {
            'X-Cosmos-Token': 'T9pFIi3coAXpypnWF4miGw',
            'Content-Type': 'application/json',
             "Access-Control-Allow-Origin": "*",
             "Access-Control-Allow-Headers": "Origin, X-Requested-With, Content-Type, Accept",
             'Access-Control-Allow-Credentials': 'true',
             'Accept': 'application/json;odata=verbose'
            }
        };

          var formatedCep;
            //formatedCep = cepValue.replace(/\D/g, '');
          var formatedCep = cepValue.replace(/\D/g, '');
          var viaCepUrl = 'https://cosmos.bluesoft.com.br/api/gtins/7891910000197/json/';
          $http.get(viaCepUrl,config).then(function(response) {
            var raw;
            debugger
            raw = response.data;
            if (raw.erro) {
              return deferred.reject('CEP not found');
            } else {
              return deferred.resolve(raw);
            }
          });*/

    }

  ]);
}).call(this);



