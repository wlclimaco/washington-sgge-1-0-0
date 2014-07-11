<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<script type="text/javascript">
/**
 /**
 * @fileoverview common reuse functions for webdaptive, counties & procedures pages.
 * @author Rich Barndt
 */

function rest_post_call(_url, _oData, _successFunction, _errorFunction)
{
	$.ajax({
			type		: "POST",
			url			: WDHost + _url,
			username	: wd.session.userId,
			password	: wd.session.userPassword,
			data		: $.toJSON(_oData),
			contentType : "application/json; charset=utf-8",
			//timeout		: 5000,
			async		: false,
			success		: _successFunction,
			error		: _errorFunction
 	});
};

//Common routine to process reponse & grid data
function reuse_fill_data(response,data2,gridProcess)
{
	//Successful Return and got some type of object back
	if (response != null && wd.core.isTrue(response.operationSuccess))
	{
		//after error, data may become undefined
		if(typeof(data2)=="undefined")
		{
			//create a blank one
			data2 = new Array();
		}
		else
		{
			//it exists zero out the old one
			//data2.length=0;
		}

		//call proc or county
		if (gridProcess === "county" )
		{
			data2 = county_fill_data(response,data2);
		}
		else if (gridProcess === "procedure" )
		{
			data2 = procedure_fill_data(response,data2);
		}
		else if (gridProcess === "cadastro" )
		{
			data2 = cadastro_fill_data(response,data2);
		}
		else if (gridProcess === "cidade" )
		{
			data2 = cidade_fill_data(response,data2);
		}
		else if (gridProcess === "supermercado" )
		{
			data2 = supermercado_fill_data(response,data2);
		}
		else if (gridProcess === "cliente" )
		{
			data2 = cliente_fill_data(response,data2);
		}
		else if (gridProcess === "menu" )
		{
			data2 = menu_fill_data(response,data2);
		}

		else if (gridProcess === "produto" )
		{
			data2 = produto_fill_data(response,data2);
		}
		else if (gridProcess === "insertproduto" )
		{
			data2 = insertProduto_fill_data(response,data2);
		}
		else if (gridProcess === "preco" )
		{

			data2 = preco_fill_data(response,data2);
		}
	}
	else
	{
		fill_data_error(response);
	}
	return data2;
};


//Fills the county array after county calls to the back-end
function county_fill_data(countyResponse,data2)
{
	//Make sure return is an array
	if ($.isArray(countyResponse.counties))
	{
		var tmpLength = countyResponse.counties.length;
		for (var i=0; i < tmpLength; i++)
		{
			data2[i] =
			{
				cellno:     i,
				csak: 		countyResponse.counties[i].id,
				cdesc:  	countyResponse.counties[i].description
			}
		}
	}
	return data2;
};

//Fills the procedure array after procedure calls to the back-end
function procedure_fill_data(procResponse,data2)
{
	data2[0] =
	{
		cellno: 0,
		action: "<span>Create>>></span>",
		psak: 0,
		pcode: null,
		pdesc: null,
		pprice: 0,
		pversion: 0
	};

	//Fill paging data
	if (procResponse.resultsSetInfo != undefined)
	{
	 	pagingData.pageSize =  parseInt(procResponse.resultsSetInfo.pageSize);
	 	pagingData.startPage =  parseInt(procResponse.resultsSetInfo.startPage);
	 	pagingData.moreRowsAvailable =  procResponse.resultsSetInfo.moreRowsAvailable;
	 	pagingData.totalRowsAvailable =  parseInt(procResponse.resultsSetInfo.totalRowsAvailable);
	}

	//make sure return is an array
	if ($.isArray(procResponse.procedures))
	{
		var oi = 0;
		var tmpLength = procResponse.procedures.length;
		<sec:authorize  access="hasAnyRole('ROLE_DOMAIN USERS', 'ROLE_DOMAIN ADMINS')">
		for (var i=1; i <= tmpLength; i++)
		</sec:authorize>
		<sec:authorize  access="hasRole('ROLE_GUEST')">
		for (var i=0; i < tmpLength; i++)
		</sec:authorize>
		{
			data2[i] =
			{
				cellno:     i,
				<sec:authorize  access="hasAnyRole('ROLE_DOMAIN USERS', 'ROLE_DOMAIN ADMINS')">
				action: 	"<a href='#' onclick='javascript:ploader.callDeleteWS(" + procResponse.procedures[oi].id +");'>Delete</a>",
				</sec:authorize>
				<sec:authorize ifAllGranted="ROLE_GUEST">
				action: 'None',
				</sec:authorize>
				psak: 		procResponse.procedures[oi].id,
				pcode:		procResponse.procedures[oi].code,
				pdesc:  	procResponse.procedures[oi].description,
				pprice: 	procResponse.procedures[oi].price,
				pversion: 	procResponse.procedures[oi].version
			}
			oi++;
		}
	}
	return data2;
};

//          function cadastro
function cadastro_fill_data(procResponse,data2)
{

	data2[0] =
	{
		cellno: 0,
		action: "<span>Novo>>></span>",
		pprod: 2,
		pid: 1,
		pnome: null,
		pdesc: null
	};

	//Fill paging data
	if (procResponse.resultsSetInfo != undefined)
	{
	 	pagingData.pageSize =  parseInt(procResponse.resultsSetInfo.pageSize);
	 	pagingData.startPage =  parseInt(procResponse.resultsSetInfo.startPage);
	 	pagingData.moreRowsAvailable =  procResponse.resultsSetInfo.moreRowsAvailable;
	 	pagingData.totalRowsAvailable =  parseInt(procResponse.resultsSetInfo.totalRowsAvailable);
	}

	//make sure return is an array
	if ($.isArray(procResponse.cadastros))
	{
		var oi = 0;
		var tmpLength = procResponse.cadastros.length;
		<sec:authorize  access="hasAnyRole('ROLE_DOMAIN USERS', 'ROLE_DOMAIN ADMINS')">
		for (var i=1; i <= tmpLength; i++)
		</sec:authorize>
		<sec:authorize  access="hasRole('ROLE_GUEST')">
		for (var i=0; i < tmpLength; i++)
		</sec:authorize>
		{
			data2[i] =
			{
				cellno:     i,
				<sec:authorize  access="hasAnyRole('ROLE_DOMAIN USERS', 'ROLE_DOMAIN ADMINS')">
				action: 	"<a href='#' onclick='javascript:ploader.callDeleteWS(" + procResponse.cadastros[oi].id +");'>Delete</a>",
				</sec:authorize>
				<sec:authorize ifAllGranted="ROLE_GUEST">
				action: 'None',
				</sec:authorize>
				pid: 		procResponse.cadastros[oi].id,
				pprod:		procResponse.cadastros[oi].id,
				pnome:		procResponse.cadastros[oi].nome,
				pdesc:  	procResponse.cadastros[oi].descricao
			}
			oi++;
		}
	}
	console.log(data2);
	return data2;
};
//============================CIDADE=======================

function cidade_fill_data(procResponse,data2)
{

	data2[0] =
	{
		cellno: 0,
		action: "<span>Novo>>></span>",
		pid: 1,
		pqntsup: 0,
		pcidade: null,
		pestado: null,
		pdata :null,
		puser :null
	};

	//Fill paging data
	if (procResponse.resultsSetInfo != undefined)
	{
	 	pagingData.pageSize =  parseInt(procResponse.resultsSetInfo.pageSize);
	 	pagingData.startPage =  parseInt(procResponse.resultsSetInfo.startPage);
	 	pagingData.moreRowsAvailable =  procResponse.resultsSetInfo.moreRowsAvailable;
	 	pagingData.totalRowsAvailable =  parseInt(procResponse.resultsSetInfo.totalRowsAvailable);
	}

	//make sure return is an array
	if ($.isArray(procResponse.cidades))
	{
		var oi = 0;
		var tmpLength = procResponse.cidades.length;
		<sec:authorize  access="hasAnyRole('ROLE_DOMAIN USERS', 'ROLE_DOMAIN ADMINS')">
		for (var i=1; i <= tmpLength; i++)
		</sec:authorize>
		<sec:authorize  access="hasRole('ROLE_GUEST')">
		for (var i=0; i < tmpLength; i++)
		</sec:authorize>
		{
				if (procResponse.cidades[oi].acessos != null){
					var count = procResponse.cidades[oi].acessos.length;
					if(procResponse.cidades[oi].acessos[count-1] != null){
						var b =     procResponse.cidades[oi].acessos[count-1].userId
					}else{
						var b= "";
					}
					if(procResponse.cidades[oi].acessos[count-1] != null){
						var c =     procResponse.cidades[oi].acessos[count-1].data
					}else{
						var c= "";
					}
				}else{
					var b= "";
					var c= "";
				}

			data2[i] =
			{
				cellno:     i,
				<sec:authorize  access="hasAnyRole('ROLE_DOMAIN USERS', 'ROLE_DOMAIN ADMINS')">
				action: 	"<a href='#' onclick='javascript:ploader.callDeleteWS(" + procResponse.cidades[oi].cidadeId +");'>Delete</a>",
				</sec:authorize>
				<sec:authorize ifAllGranted="ROLE_GUEST">
				action: 'None',
				</sec:authorize>
				pid: 		procResponse.cidades[oi].cidadeId,
				pqntsup:	procResponse.cidades[oi].cidadeId,
				pcidade:	procResponse.cidades[oi].cidade,
				pestado:  	procResponse.cidades[oi].estado,
				pdata :c,
				puser :b

			}
			oi++;
		}
	}
	return data2;
};

//=========================================================

//============================SUPERMERCADO=======================

function supermercado_fill_data(procResponse,data2)
{

	data2[0] =
	{
		cellno: 0,
		action: "<span>Novo>>></span>",
		pid: 1,
		pqntsup: 0,
		pcidade: null,
		pestado: null
	};

	//Fill paging data
	if (procResponse.resultsSetInfo != undefined)
	{
	 	pagingData.pageSize =  parseInt(procResponse.resultsSetInfo.pageSize);
	 	pagingData.startPage =  parseInt(procResponse.resultsSetInfo.startPage);
	 	pagingData.moreRowsAvailable =  procResponse.resultsSetInfo.moreRowsAvailable;
	 	pagingData.totalRowsAvailable =  parseInt(procResponse.resultsSetInfo.totalRowsAvailable);
	}

	//make sure return is an array
	if ($.isArray(procResponse.supermercados))
	{
		var oi = 0;
		var tmpLength = procResponse.supermercados.length;

		<sec:authorize  access="hasAnyRole('ROLE_DOMAIN USERS', 'ROLE_DOMAIN ADMINS')">
		for (var i=1; i <= tmpLength; i++)
		</sec:authorize>
		<sec:authorize  access="hasRole('ROLE_GUEST')">
		for (var i=0; i < tmpLength; i++)
		</sec:authorize>
		{
			data2[i] =
			{
				cellno:     i,
				<sec:authorize  access="hasAnyRole('ROLE_DOMAIN USERS', 'ROLE_DOMAIN ADMINS')">
				action: 	"<a href='#' onclick='javascript:ploader.callDeleteWS(" + procResponse.supermercados[oi].superId +");'>Delete</a>",
				</sec:authorize>
				<sec:authorize ifAllGranted="ROLE_GUEST">
				action: 'None',
				</sec:authorize>
				supermercadoid:	procResponse.supermercados[oi].superId,
				usuario:		procResponse.supermercados[oi].usuario,
				email:			procResponse.supermercados[oi].email,
				site:  			procResponse.supermercados[oi].site,
				senha:  		procResponse.supermercados[oi].senha,
				enderecoid:  	procResponse.supermercados[oi].enderecos[0].enderecoid,
				eid:  			procResponse.supermercados[oi].enderecos[0].id,
				logradouro:  	procResponse.supermercados[oi].enderecos[0].logradouro,
				bairro:  		procResponse.supermercados[oi].enderecos[0].bairro,
				estado:  		procResponse.supermercados[oi].enderecos[0].estado,
				cidade:  		procResponse.supermercados[oi].enderecos[0].cidade,
				numero:  		procResponse.supermercados[oi].enderecos[0].numero,
				cep:  			procResponse.supermercados[oi].enderecos[0].cep,
				nome:  			procResponse.supermercados[oi].enderecos[0].nome,
				complemento:  	procResponse.supermercados[oi].enderecos[0].complemento,
				documenroid:	procResponse.supermercados[oi].documentos[0].documenroid,
				did:  	    	procResponse.supermercados[oi].documentos[0].id,
				rgInc:  		procResponse.supermercados[oi].documentos[0].rgInc,
				cpfCnpj:  		procResponse.supermercados[oi].documentos[0].cpfCnpj,
				razao:  		procResponse.supermercados[oi].documentos[0].razao,
				dateNascimento: procResponse.supermercados[oi].documentos[0].dateNascimento
			}

			oi++;
		}
	}

	return data2;
};

//=========================================================


//============================CLIENTE=======================

function cliente_fill_data(procResponse,data2)
{
	data2[0] =
	{
		cellno: 0,
		action: "<span>Novo>>></span>",
		clienteid:		0,
		type:			"",
		nome:			"",
		sobrenome:  	"",
		usuario:  		"",
		senha:  		"",
		email:  		"",
		enderecoid:  	0,
		eid:  			0,
		logradouro:  	"",
		bairro:  		"",
		estado:  		""
	};

	//Fill paging data
	if (procResponse.resultsSetInfo != undefined)
	{
	 	pagingData.pageSize =  parseInt(procResponse.resultsSetInfo.pageSize);
	 	pagingData.startPage =  parseInt(procResponse.resultsSetInfo.startPage);
	 	pagingData.moreRowsAvailable =  procResponse.resultsSetInfo.moreRowsAvailable;
	 	pagingData.totalRowsAvailable =  parseInt(procResponse.resultsSetInfo.totalRowsAvailable);
	}

	//make sure return is an array
	if ($.isArray(procResponse.clientes))
	{
		var oi = 0;
		var tmpLength = procResponse.clientes.length;

		<sec:authorize  access="hasAnyRole('ROLE_DOMAIN USERS', 'ROLE_DOMAIN ADMINS')">
		for (var i=1; i <= tmpLength; i++)
		</sec:authorize>
		<sec:authorize  access="hasRole('ROLE_GUEST')">
		for (var i=0; i < tmpLength; i++)
		</sec:authorize>
		{
			data2[i] =
			{
				cellno:     i,
				<sec:authorize  access="hasAnyRole('ROLE_DOMAIN USERS', 'ROLE_DOMAIN ADMINS')">
				action: 	"<a href='#' onclick='javascript:ploader.callDeleteWS(" + procResponse.clientes[oi].clienteid +");'>Delete</a>",
				</sec:authorize>
				<sec:authorize ifAllGranted="ROLE_GUEST">
				action: 'None',
				</sec:authorize>

				clienteid:		procResponse.clientes[oi].clienteid,
				type:			procResponse.clientes[oi].type,
				nome:			procResponse.clientes[oi].nome,
				sobrenome:  	procResponse.clientes[oi].sobrenome,
				usuario:  		procResponse.clientes[oi].usuario,
				senha:  		procResponse.clientes[oi].sobrenome,
				email:  		procResponse.clientes[oi].usuario,
				enderecoid:  	procResponse.clientes[oi].enderecos[0].enderecoid,
				eid:  			procResponse.clientes[oi].enderecos[0].id,
				logradouro:  	procResponse.clientes[oi].enderecos[0].logradouro,
				bairro:  		procResponse.clientes[oi].enderecos[0].bairro,
				estado:  		procResponse.clientes[oi].enderecos[0].estado,
				cidade:  		procResponse.clientes[oi].enderecos[0].cidade,
				numero:  		procResponse.clientes[oi].enderecos[0].numero,
				cep:  			procResponse.clientes[oi].enderecos[0].cep,
				nome:  			procResponse.clientes[oi].enderecos[0].nome,
				complemento:  	procResponse.clientes[oi].enderecos[0].complemento,
				documenroid:	procResponse.clientes[oi].documentos[0].documenroid,
				did:  	    	procResponse.clientes[oi].documentos[0].id,
				rgInc:  		procResponse.clientes[oi].documentos[0].rgInc,
				cpfCnpj:  		procResponse.clientes[oi].documentos[0].cpfCnpj,
				razao:  		procResponse.clientes[oi].documentos[0].razao,
				dateNascimento: procResponse.clientes[oi].documentos[0].dateNascimento
			}

			oi++;
		}
	}

	return data2;
};

//=========================================================

//============================MENU=======================

function menu_fill_data(procResponse,data2)
{
	data2[0] =
	{
		cellno: 0,
		action: "<span>Novo>>></span>",
		id:		0,
		nome:			"",
		produtos:		"",
		descricao:  	"",
		imagens:  		"",
		data  :         "",
		userId:  		""

	};

	//Fill paging data
	if (procResponse.resultsSetInfo != undefined)
	{
	 	pagingData.pageSize =  parseInt(procResponse.resultsSetInfo.pageSize);
	 	pagingData.startPage =  parseInt(procResponse.resultsSetInfo.startPage);
	 	pagingData.moreRowsAvailable =  procResponse.resultsSetInfo.moreRowsAvailable;
	 	pagingData.totalRowsAvailable =  parseInt(procResponse.resultsSetInfo.totalRowsAvailable);
	}

	//make sure return is an array
	if ($.isArray(procResponse.cadastros))
	{
		var oi = 0;
		var tmpLength = procResponse.cadastros.length;
		<sec:authorize  access="hasAnyRole('ROLE_DOMAIN USERS', 'ROLE_DOMAIN ADMINS')">
		for (var i=1; i <= tmpLength; i++)
		</sec:authorize>
		<sec:authorize  access="hasRole('ROLE_GUEST')">
		for (var i=0; i < tmpLength; i++)
		</sec:authorize>
		{
			if(procResponse.cadastros[oi].produtos != null){
					var a =		procResponse.cadastros[oi].produtos.length;
				}else{
				a= 0;}
				if (procResponse.cadastros[oi].acessos != null){
					var count = procResponse.cadastros[oi].acessos.length;
					if(procResponse.cadastros[oi].acessos[count-1] != null){
						var b =     procResponse.cadastros[oi].acessos[count-1].userId;
						var c =     procResponse.cadastros[oi].acessos[count-1].data;
					}else{
						var b= "";
						var c= "";
					}
				}else{
					var b= "";
					var c= "";
				}
			data2[i] =
			{

				cellno:     i,
				<sec:authorize  access="hasAnyRole('ROLE_DOMAIN USERS', 'ROLE_DOMAIN ADMINS')">
				action: 	"<a href='#' onclick='javascript:ploader.callDeleteWS(" + procResponse.cadastros[oi].id +");'>Delete</a>",
				</sec:authorize>
				<sec:authorize ifAllGranted="ROLE_GUEST">
				action: 'None',
				</sec:authorize>
				id:				procResponse.cadastros[oi].id,
				nome:			procResponse.cadastros[oi].nome,
				descricao:  	procResponse.cadastros[oi].descricao,
				imagens:  		procResponse.cadastros[oi].imagens,
				produtos:		a,
				data:  		    c,
				userId:  		b
			}

			oi++;
		}
	}

	return data2;
};

//=========================================================produto_fill_data

//============================MENU=======================

function produto_fill_data(procResponse,data2)
{
	data2[0] =
	{
		cellno:          0,
		id:		         0,
		codBarra:		"",
		nome:  	        "",
		unimed  :       "",
		descricao:  	"",
		marca:  	    "",
		menu:  		    "",
		submenu  :      "",
		trimenu  :      "",
		supermercadoId: "",
		preco  :        "",
		imagens:  		"",
		cellno1:        ""

	};

	//Fill paging data
	if (procResponse.resultsSetInfo != undefined)
	{
	 	pagingData.pageSize =  parseInt(procResponse.resultsSetInfo.pageSize);
	 	pagingData.startPage =  parseInt(procResponse.resultsSetInfo.startPage);
	 	pagingData.moreRowsAvailable =  procResponse.resultsSetInfo.moreRowsAvailable;
	 	pagingData.totalRowsAvailable =  parseInt(procResponse.resultsSetInfo.totalRowsAvailable);
	}

	//make sure return is an array
	if ($.isArray(procResponse.produtos))
	{
		var oi = 0;
		var tmpLength = procResponse.produtos.length;
		<sec:authorize  access="hasRole('ROLE_GUEST')">
		for (var i=0; i < tmpLength; i++)
		</sec:authorize>
		{
			if(procResponse.produtos[oi].marca != null){
				var a =		procResponse.produtos[oi].marca[0].nome;
			}else{
				a= 0;
			}
			if(procResponse.produtos[oi].menu != null){
				var b =		procResponse.produtos[oi].marca[0].menu;
			}else{
				b= 0;
			}
			if(procResponse.produtos[oi].submenu != null){
				var c =		procResponse.produtos[oi].marca[0].submenu;
			}else{
				c= 0;
			}
			if(procResponse.produtos[oi].trimenu != null){
				var d =		procResponse.produtos[oi].marca[0].trimenu;
			}else{
				d= 0;
			}
			if(procResponse.produtos[oi].unimed != null){
				var e =		procResponse.produtos[oi].marca[0].unimed;
			}else{
				e= 0;
			}
			if (procResponse.produtos[oi].acessos != null){
				var count = procResponse.produtos[oi].acessos.length;
				if(procResponse.produtos[oi].acessos[count-1] != null){
					var f =     procResponse.produtos[oi].acessos[count-1].userId;
					var g =     procResponse.produtos[oi].acessos[count-1].data;
				}else{
					var f= "";
					var g= "";
				}
			}else{
				var f= "";
				var g= "";
			}
			if (procResponse.produtos[oi].precos != null){
				var count = procResponse.produtos[oi].precos.length;
				if(procResponse.produtos[oi].precos[count-1] != null){
					var h =     procResponse.produtos[oi].precos[count-1].supermercadoid;
					if(procResponse.produtos[oi].precos[count-1].promocao == true)
						var j =     procResponse.produtos[oi].precos[count-1].precopromo
					else
						var j =     procResponse.produtos[oi].precos[count-1].preco
				}else{
					var h= "";
					var j= "";
				}
			}else{
				var h= "";
				var j= "";
			}
			data2[i] =
			{

				cellno:     i,
				<sec:authorize  access="hasAnyRole('ROLE_DOMAIN USERS', 'ROLE_DOMAIN ADMINS')">
				action: 	"<a href='#' onclick='javascript:ploader.callDeleteWS(" + procResponse.produtos[oi].id +");'>Delete</a>",
				</sec:authorize>
				<sec:authorize ifAllGranted="ROLE_GUEST">
				action: 'None',
				</sec:authorize>
				id:		        procResponse.produtos[oi].id,
				codBarra:		procResponse.produtos[oi].codBarra,
				nome:  	        procResponse.produtos[oi].nome,
				unimed  :       e,
				descricao:  	procResponse.produtos[oi].descricao,
				marca:  	    a,
				menu:  		    b,
				submenu  :      c,
				trimenu  :      d,
				supermercadoId: h,
				preco  :        j,
				imagens:  		procResponse.produtos[oi].imagens,
				data:  		    e,
				userId:  		f
			}

			oi++;
		}
	}

	return data2;
};

//=========================================================


//============================MENU=======================

function insertProduto_fill_data(procResponse,data2)
{

	function fill_data(oResponse)
	{
	  var menu = '';
	  var submenu = '';
	  var trimenu = '';
	  var marca = '';
	  var unimed = '';
      for (var i = 0; i < oResponse.cadastros.length; i++) {
		if(oResponse.cadastros[i].tableTypeEnumValue == 3){
			marca +=   '<option value="' + oResponse.cadastros[i].id + '">' + oResponse.cadastros[i].nome + '</option>';
		}else if(oResponse.cadastros[i].tableTypeEnumValue == 4){
			menu +=    '<option value="' + oResponse.cadastros[i].id + '">' + oResponse.cadastros[i].nome + '</option>';
		}else if(oResponse.cadastros[i].tableTypeEnumValue == 5){
			submenu += '<option value="' + oResponse.cadastros[i].id + '">' + oResponse.cadastros[i].nome + '</option>';
		}else if(oResponse.cadastros[i].tableTypeEnumValue == 6){
			trimenu += '<option value="' + oResponse.cadastros[i].id + '">' + oResponse.cadastros[i].nome + '</option>';
		}else if(oResponse.cadastros[i].tableTypeEnumValue == 7){
			unimed +=  '<option value="' + oResponse.cadastros[i].id + '">' + oResponse.cadastros[i].nome + '</option>';
		}
      }
      $("select#marca").html(marca);
	  $("select#menu").html(menu);
	  $("select#submenu").html(submenu);
	  $("select#trimenu").html(trimenu);
	  $("select#unimed").html(unimed);
	}
	rest_post_call('qat-sysmgmt-sample/services/rest/ProdutoService/fetchAllCadastros',{cadastro:{type:0,userId:'rod'}}, fill_data, null);

	data2[0] =
	{
		cellno: 0,
		action: "<span>Novo>>></span>",
		id:		0,
		supermercadoid:	"",
		preco:		"",
		precopromo:  	"",
		promocao:  		"",
		data  :         "",
		userId:  		""

	};

	//Fill paging data
	if (procResponse.resultsSetInfo != undefined)
	{
	 	pagingData.pageSize =  parseInt(procResponse.resultsSetInfo.pageSize);
	 	pagingData.startPage =  parseInt(procResponse.resultsSetInfo.startPage);
	 	pagingData.moreRowsAvailable =  procResponse.resultsSetInfo.moreRowsAvailable;
	 	pagingData.totalRowsAvailable =  parseInt(procResponse.resultsSetInfo.totalRowsAvailable);
	}

	//make sure return is an array
	if (($.isArray(procResponse.produtos[0].precos))&&(procResponse.produtos[0].precos.precoid != null))
	{
		var oi = 0;
		var tmpLength = procResponse.produtos[0].precos.length;
		<sec:authorize  access="hasRole('ROLE_GUEST')">
		for (var i=0; i < tmpLength; i++)
		</sec:authorize>
		{
			if (procResponse.produtos[0].precos[oi].acessos != null){
				var count = procResponse.produtos[0].precos[oi].acessos.length;
				if(procResponse.produtos[0].precos[oi].acessos[count-1] != null){
					var b =     procResponse.produtos[0].precos[oi].acessos[count-1].userId;
					var c =     procResponse.produtos[0].precos[oi].acessos[count-1].data;
				}else{
					var b= "";
					var c= "";
				}
			}else{
				var b= "";
				var c= "";
			}
			data2[i] =
			{


				cellno:     i,
				<sec:authorize  access="hasAnyRole('ROLE_DOMAIN USERS', 'ROLE_DOMAIN ADMINS')">
				action: 	"<a href='#' onclick='javascript:ploader.callDeleteWS(" + procResponse.produtos[0].precos[oi].precoid +");'>Delete</a>",
				</sec:authorize>
				<sec:authorize ifAllGranted="ROLE_GUEST">
				action: 'None',
				</sec:authorize>
				id:				procResponse.produtos[0].precos[oi].precoid,
				supermercadoid:	procResponse.produtos[0].precos[oi].supermercadoid,
				preco:			procResponse.produtos[0].precos[oi].preco,
				precopromo:  	procResponse.produtos[0].precos[oi].precopromo,
				promocao:  		procResponse.produtos[0].precos[oi].promocao,
				data  :         c,
				userId:  		b
			}

			oi++;
		}
	}

	return data2;
};

//=========================================================

//============================PRECO=======================

function preco_fill_data(procResponse,data2)
{

	var tmpLength = procResponse.length;

	data2[tmpLength] =
	{
		cellno:     tmpLength,
		<sec:authorize  access="hasAnyRole('ROLE_DOMAIN USERS', 'ROLE_DOMAIN ADMINS')">
		action: 	"<a href='#' onclick='javascript:ploader.callDeleteWS(" + procResponse.id +");'>Delete</a>",
		</sec:authorize>
		<sec:authorize ifAllGranted="ROLE_GUEST">
		action: 'None',
		</sec:authorize>
		id:				procResponse[0].id,
		supermercadoid:	procResponse[0].supermercadoid,
		preco:			parseFloat(procResponse[0].preco),
		precopromo:  	parseFloat(procResponse[0].precopromo),
		promocao:  		procResponse[0].promocao,
	};

	data2[0] =
	{
		cellno: 0,
		action: "<span>Novo>>></span>",
		pid: 1,
		id:				0,
		supermercadoid:	0,
		preco:			0.00,
		precopromo:  	0.00,
		promocao:  		true
	};

	return data2;
};

//=========================================================


//error routine for all ajax calls to the back-end or MVC
function fill_data_error(response)
{
	var msgOut = "";
	if (response == null)
	{
		msgOut = wdbackend.error.msg;
	}
	else
	{
		//Make sure return is an array
		if ($.isArray(response.messageList))
		{
			var tmpLength = response.messageList.length;
			for (var q = 0; q < (tmpLength); q++)
			{
				msgOut = msgOut + "Severity:" + response.messageList[q].messageInfo.severity + ",Level:" + response.messageList[q].messageInfo.level +  ",Text:"  + response.messageList[q].text + ",traceInfo:"  +  response.messageList[q].messageInfo.traceInfo;
			}
		}
		msgOut = wdbackend.error.msg + msgOut;
	}
	wd.core.displayNotificationMessage('#StatusBar', msgOut, true, 'error', 5000);
};
</script>