<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<script type="text/javascript">
/**
 /**
 * @fileoverview common reuse functions for webdaptive, counties & procedures pages.
 * @author Rich Barndt
 */

function rest_post_call(_url, _oData, _successFunction, _errorFunction,_async)
{
	if(_async == null){
		_async = true;
	}
	$.ajax({
			type		: "POST",
			url			: WDHost + _url,
			username	: wd.session.userId,
			password	: wd.session.userPassword,
			data		: $.toJSON(_oData),
			contentType : "application/json; charset=utf-8",
			//timeout		: 5000,
			async		: _async,
			success		: _successFunction,
			error		: _errorFunction
 	});
};

function convertData(_time){

	a = new Date();
	a.setTime(_time);
	b = a.toString("yyyy-MM-ddTHH:mm:ss")
	c = b.split(" ")
	d = c[2] +"/" + c[1] +"/" + c[3] +" " + c[4];
	return d;
}

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
		else if (gridProcess === "marca" )
		{
			data2 = marca_fill_data(response,data2);
		}
		else if (gridProcess === "submenu" )
		{
			data2 = submenu_fill_data(response,data2);
		}
		else if (gridProcess === "trimenu" )
		{
			data2 = trimenu_fill_data(response,data2);
		}
		else if (gridProcess === "unimed" )
		{
			data2 = unimed_fill_data(response,data2);
		}

		else if (gridProcess === "produto" )
		{
			data2 = produto_fill_data(response,data2);
		}
		else if (gridProcess === "embalagem" )
		{

			data2 = embalagem_fill_data(response,data2);
		}
		else if (gridProcess === "insertproduto" )
		{
			data2 = insertProduto_fill_data(response,data2);
		}
		else if (gridProcess === "preco" )
		{

			data2 = preco_fill_data(response,data2);
		}else if (gridProcess === "produtoDialog" )
		{

			data2 = produtoDialog_fill_data(response,data2);
		}else if (gridProcess === "produtoDialog2" )
		{

			data2 = produtoDialog_fill_data2(response,data2);
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
		</sec:authorize>
		pid: 		null,
		pcidade:	null,
		pmunicipio:	null,
		pestado:  	null,
		pibge:  	null,
		pcodigo:  	null,
		pcep:  		null,
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
	if ($.isArray(procResponse.cidadeList))
	{
		var oi = 0;
		var tmpLength = procResponse.cidadeList.length;
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
				action: 	"<a href='#' onclick='javascript:ploader.callDeleteWS(" + procResponse.cidadeList[oi].id +");'>Delete</a>",
				</sec:authorize>
				<sec:authorize ifAllGranted="ROLE_GUEST">
				action: 'None',
				</sec:authorize>
				pid: 		procResponse.cidadeList[oi].id,
				pcidade:	procResponse.cidadeList[oi].nome,
				pmunicipio:	procResponse.cidadeList[oi].municipio,
				pestado:  	procResponse.cidadeList[oi].estado,
				pibge:  	procResponse.cidadeList[oi].cdIBGE,
				pcodigo:  	procResponse.cidadeList[oi].codigo,
				pcep:  		procResponse.cidadeList[oi].cep,
				pdata :"rod",
				puser :"rod"

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
		action:"",
		supermercadoid:0,
		razao:"",
		cpfCnpj:"",
		rgInc:"",
		produto:"",
		logradouro:"",
		bairro:"",
		cidade:""
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
			if(procResponse.supermercados[oi].produtos != null){
				var a =		procResponse.supermercados[oi].produtos
			}else{
				var a =	0;
			}
			data2[i] =
			{
				cellno:     i,
				action: 	"<a href='#' onclick='javascript:ploaderSup.callDeleteWS(" + procResponse.supermercados[oi].superId +");'>Delete</a>",
				cellno: procResponse.supermercados[oi].superId,
				supermercadoid:procResponse.supermercados[oi].superId,
				razao:procResponse.supermercados[oi].documentos[0].razao,
				cpfCnpj:procResponse.supermercados[oi].documentos[0].cpfCnpj,
				rgInc:procResponse.supermercados[oi].documentos[0].rgInc,
				produto:"<a href='#' onclick='javascript:ploaderSup.openProdutos(" + procResponse.supermercados[oi].superId +");'><span>"+a+"<span></a>",
				logradouro:procResponse.supermercados[oi].enderecos[0].logradouro,
				bairro:procResponse.supermercados[oi].enderecos[0].bairro,
				cidade:procResponse.supermercados[oi].enderecos[0].cidade,
				supermercadoid:	procResponse.supermercados[oi].superId
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
	var co = data2.length -1;
	data2[co-1] = null;
	frutas = data2;
	frutas.splice(co, 1);
	data2 = frutas;
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
					var a =		procResponse.cadastros[oi].produtos;
				}else{
				a= 0;}
				if (procResponse.cadastros[oi].acessos != null){
					var count = procResponse.cadastros[oi].acessos.length;
					if(procResponse.cadastros[oi].acessos[count-1] != null){
						var b =     procResponse.cadastros[oi].acessos[count-1].userId;
						var c =     convertData(procResponse.cadastros[oi].acessos[count-1].data);
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
				action: 	"<a href='#' onclick='javascript:ploaderMenu.callDeleteWS(" + procResponse.cadastros[oi].id +");'>Delete</a>",
				</sec:authorize>
				<sec:authorize ifAllGranted="ROLE_GUEST">
				action: 'None',
				</sec:authorize>
				id:				procResponse.cadastros[oi].id,
				nome:			procResponse.cadastros[oi].nome,
				descricao:  	procResponse.cadastros[oi].descricao,
				imagens:  		procResponse.cadastros[oi].imagens,
				produtos:		"<a href='#' onclick='javascript:ploaderMenu.openProdutos(" + procResponse.cadastros[oi].id +");'><span>"+a+"<span></a>",
				data:  		    c,
				userId:  		b
			}

			oi++;
		}
	}

	return data2;
};

//=========================================================

//============================MARCA=======================

function marca_fill_data(procResponse,data2)
{
	var co = data2.length -1;
	data2[co-1] = null;
	frutas = data2;
	frutas.splice(co, 1);
	data2 = frutas;
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
					var a =		procResponse.cadastros[oi].produtos;
					var w=[];
					w.push(a);
					w.push(procResponse.cadastros[oi].id);
				}else{
				a= 0;}
				if (procResponse.cadastros[oi].acessos != null){
					var count = procResponse.cadastros[oi].acessos.length;
					if(procResponse.cadastros[oi].acessos[count-1] != null){
						var b =     procResponse.cadastros[oi].acessos[count-1].userId;
						var c =     convertData(procResponse.cadastros[oi].acessos[count-1].data);
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
				action: 	"<a href='#' onclick='javascript:ploaderMarca.callDeleteWS(" + procResponse.cadastros[oi].id +");'>Delete</a>",
				</sec:authorize>
				<sec:authorize ifAllGranted="ROLE_GUEST">
				action: 'None',
				</sec:authorize>
				id:				procResponse.cadastros[oi].id,
				nome:			procResponse.cadastros[oi].nome,
				descricao:  	procResponse.cadastros[oi].descricao,
				imagens:  		procResponse.cadastros[oi].imagens,
				produtos:		"<a href='#' onclick='javascript:ploaderMarca.openProdutos(" + procResponse.cadastros[oi].id +");'><span>"+a+"<span></a>",
				data:  		    c,
				userId:  		b
			}

			oi++;
		}
	}

	return data2;
};

//============================SUBMENU=======================

function submenu_fill_data(procResponse,data2)
{
	var co = data2.length -1;
	data2[co-1] = null;
	frutas = data2;
	frutas.splice(co, 1);
	data2 = frutas;
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
					var a =		procResponse.cadastros[oi].produtos;
					var w=[];
					w.push(a);
					w.push(procResponse.cadastros[oi].id);
				}else{
				a= 0;}
				if (procResponse.cadastros[oi].acessos != null){
					var count = procResponse.cadastros[oi].acessos.length;
					if(procResponse.cadastros[oi].acessos[count-1] != null){
						var b =     procResponse.cadastros[oi].acessos[count-1].userId;
						var c =     convertData(procResponse.cadastros[oi].acessos[count-1].data);
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
				action: 	"<a href='#' onclick='javascript:ploaderSub.callDeleteWS(" + procResponse.cadastros[oi].id +");'>Delete</a>",
				</sec:authorize>
				<sec:authorize ifAllGranted="ROLE_GUEST">
				action: 'None',
				</sec:authorize>
				id:				procResponse.cadastros[oi].id,
				nome:			procResponse.cadastros[oi].nome,
				descricao:  	procResponse.cadastros[oi].descricao,
				imagens:  		procResponse.cadastros[oi].imagens,
				produtos:		"<a href='#' onclick='javascript:ploaderSub.openProdutos(" + procResponse.cadastros[oi].id +");'><span>"+a+"<span></a>",
				data:  		    c,
				userId:  		b
			}

			oi++;
		}
	}

	return data2;
};

//============================TRIMENU=======================

function trimenu_fill_data(procResponse,data2)
{
	var co = data2.length -1;
	data2[co-1] = null;
	frutas = data2;
	frutas.splice(co, 1);
	data2 = frutas;
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
					var a =		procResponse.cadastros[oi].produtos;
					var w=[];
					w.push(a);
					w.push(procResponse.cadastros[oi].id);
				}else{
				a= 0;}
				if (procResponse.cadastros[oi].acessos != null){
					var count = procResponse.cadastros[oi].acessos.length;
					if(procResponse.cadastros[oi].acessos[count-1] != null){
						var b =     procResponse.cadastros[oi].acessos[count-1].userId;
						var c =     convertData(procResponse.cadastros[oi].acessos[count-1].data);
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
				action: 	"<a href='#' onclick='javascript:ploaderTri.callDeleteWS(" + procResponse.cadastros[oi].id +");'>Delete</a>",
				</sec:authorize>
				<sec:authorize ifAllGranted="ROLE_GUEST">
				action: 'None',
				</sec:authorize>
				id:				procResponse.cadastros[oi].id,
				nome:			procResponse.cadastros[oi].nome,
				descricao:  	procResponse.cadastros[oi].descricao,
				imagens:  		procResponse.cadastros[oi].imagens,
				produtos:		"<a href='#' onclick='javascript:ploaderTri.openProdutos(" + procResponse.cadastros[oi].id +");'><span>"+a+"<span></a>",
				data:  		    c,
				userId:  		b
			}

			oi++;
		}
	}

	return data2;
};

//============================UNIMED=======================

function unimed_fill_data(procResponse,data2)
{

	var co = data2.length -1;
	data2[co-1] = null;
	frutas = data2;
	frutas.splice(co, 1);
	data2 = frutas;
	data2[0] =
	{
		cellno: 0,
		action: "<span>Novo>>></span>",
		id:		0,
		nome:			"",
		sigla:  	"",
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
	if ($.isArray(procResponse.embalagem))
	{
		var oi = 0;
		var tmpLength = procResponse.embalagem.length;
		<sec:authorize  access="hasAnyRole('ROLE_DOMAIN USERS', 'ROLE_DOMAIN ADMINS')">
		for (var i=1; i <= tmpLength; i++)
		</sec:authorize>
		<sec:authorize  access="hasRole('ROLE_GUEST')">
		for (var i=0; i < tmpLength; i++)
		</sec:authorize>
		{
			if (procResponse.embalagem[oi].acessos != null){
				var count = procResponse.embalagem[oi].acessos.length;
				if(procResponse.embalagem[oi].acessos[count-1] != null){
					var b =     procResponse.embalagem[oi].acessos[count-1].userId;
					var c =     convertData(procResponse.embalagem[oi].acessos[count-1].data);
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
				action: 	"<a href='#' onclick='javascript:ploaderUni.callDeleteWS(" + procResponse.embalagem[oi].id +");'>Delete</a>",
				</sec:authorize>
				<sec:authorize ifAllGranted="ROLE_GUEST">
				action: 'None',
				</sec:authorize>
				id:				procResponse.embalagem[oi].id,
				nome:			procResponse.embalagem[oi].nome,
				sigla:  	    procResponse.embalagem[oi].sigla,
				data:  		    c,
				userId:  		b
			}

			oi++;
		}
	}

	return data2;
};
//============================SUBMENU=======================

function embalagem_fill_data(procResponse,data2)
{

	var co = data2.length -1;
	data2[co-1] = null;
	frutas = data2;
	frutas.splice(co, 1);
	data2 = frutas;
	data2[0] =
	{
		cellno: 0,
		action: "<span>Novo>>></span>",
		id:		        0,
		nome:			"",
		qnt:			0,
		unimed:			"",
		idunimed :      "",
		produtos:		0,
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
	if ($.isArray(procResponse.embalagem))
	{
		var oi = 0;
		var tmpLength = procResponse.embalagem.length;
		<sec:authorize  access="hasAnyRole('ROLE_DOMAIN USERS', 'ROLE_DOMAIN ADMINS')">
		for (var i=1; i <= tmpLength; i++)
		</sec:authorize>
		<sec:authorize  access="hasRole('ROLE_GUEST')">
		for (var i=0; i < tmpLength; i++)
		</sec:authorize>
		{
			if(procResponse.embalagem[oi].produtos != null){
					var a =		procResponse.embalagem[oi].produtos;
				}else{
				a= 0;}
				if (procResponse.embalagem[oi].acessos != null){
					var count = procResponse.embalagem[oi].acessos.length;
					if(procResponse.embalagem[oi].acessos[count-1] != null){
						var b =     procResponse.embalagem[oi].acessos[count-1].userId;
						var c =     convertData(procResponse.embalagem[oi].acessos[count-1].data);
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
				action: 	"<a href='#' onclick='javascript:ploaderEmb.callDeleteWS(" + procResponse.embalagem[oi].id +");'>Delete</a>",
				</sec:authorize>
				<sec:authorize ifAllGranted="ROLE_GUEST">
				action: 'None',
				</sec:authorize>
				id:				procResponse.embalagem[oi].id,
				nome:			procResponse.embalagem[oi].nome,
				qnt:			procResponse.embalagem[oi].qnt,
				unimed:			procResponse.embalagem[oi].unimedid.nome,
				idunimed :      procResponse.embalagem[oi].unimedid.id,
				produtos:		"<a href='#' onclick='javascript:ploaderEmb.openProdutos(" + procResponse.embalagem[oi].id +");'><span>"+a+"<span></a>",
				data:  		    c,
				userId:  		b
			}

			oi++;
		}
	}

	return data2;
};


//============================PRODUTO=======================

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

		for (var i=0; i < tmpLength; i++)
		{
			if(procResponse.produtos[oi].marca != null){
				var a =		procResponse.produtos[oi].marca.nome;
			}else{
				a= 0;
			}
			if(procResponse.produtos[oi].menu != null){
				var b =		procResponse.produtos[oi].menu.descricao;
			}else{
				b= 0;
			}
			if(procResponse.produtos[oi].embalagem != null){
				var e =		procResponse.produtos[oi].embalagem.qnt + ' '+procResponse.produtos[oi].embalagem.unimedid.nome;
			}else{
				e= 0;
			}
			if (procResponse.produtos[oi].acessos != null){
				var count = procResponse.produtos[oi].acessos.length;
				if(procResponse.produtos[oi].acessos[count-1] != null){
					var f =     procResponse.produtos[oi].acessos[count-1].userId;
					var g =     convertData(procResponse.produtos[oi].acessos[count-1].data);
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
				if(procResponse.produtos[oi].precos[0] != null){
					if(procResponse.produtos[oi].precos[0].supermercadoid != null)
						var h =     procResponse.produtos[oi].precos[0].supermercadoid.documentos[0].razao
					else{
						var h = "";
					}
					if(procResponse.produtos[oi].precos[0].promocao == true)
						var j =     procResponse.produtos[oi].precos[0].precopromo
					else
						var j =     procResponse.produtos[oi].precos[0].preco
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
				action: 	"<a href='#' onclick='javascript:ploaderPro.callDeleteWS(" + procResponse.produtos[oi].id +");'>Delete</a>",
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
				supermercadoId: h,
				preco  :        j,
				imagens:  		procResponse.produtos[oi].imagens,
				data:  		    g,
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
	  var marca = '';
      for (var i = 0; i < oResponse.cadastros.length; i++) {
		if(oResponse.cadastros[i].tableTypeEnumValue == 3){
			marca  +=   '<option value="' + oResponse.cadastros[i].id + '">' + oResponse.cadastros[i].nome + '</option>';
		}else if((oResponse.cadastros[i].tableTypeEnumValue == 4)||(oResponse.cadastros[i].tableTypeEnumValue == 5)||(oResponse.cadastros[i].tableTypeEnumValue == 6)){
			menu   +=    '<option value="' + oResponse.cadastros[i].id + '">' + oResponse.cadastros[i].descricao + '</option>';
		}
      }
      $("select#marca").html(marca);
	  $("select#menu").html(menu);
	}
	function callback(oResponse)
	{
	  var unimed = '';
      for (var i = 0; i < oResponse.embalagem.length; i++) {
		unimed  +=   '<option value="' + oResponse.embalagem[i].id + '">' + oResponse.embalagem[i].qnt + ' '+oResponse.embalagem[i].unimedid.nome+'</option>';
      }
	  $("select#unimed").html(unimed);
	}
	var onProcDataLoading = new EventHelper();
	onProcDataLoading.notify({});
	rest_post_call('qat-sysmgmt-sample/services/rest/ProdutoService/fetchAllCadastros',{cadastro:{type:0,userId:'rod'}}, fill_data, null,false);
	rest_post_call('qat-sysmgmt-sample/services/rest/ProdutoService/fetchAllEmbalagems',{}, callback, null,false);

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

	if(procResponse != null){
		var codbarra = procResponse.produtos[0].codBarra ,
			nomeProd = procResponse.produtos[0].nome,
			descr    = procResponse.produtos[0].descricao,
			foto    = procResponse.produtos[0].foto;

			if(procResponse.produtos[0].menu != null)
				var menu    = procResponse.produtos[0].menu.id
			else
			   var menu = "SELECIONE"

			if(procResponse.produtos[0].embalagem != null)
				var unimed  = procResponse.produtos[0].embalagem.id
			else
			   var unimed = "SELECIONE"

			if(procResponse.produtos[0].marca != null)
				var marca   = procResponse.produtos[0].marca.id
			else
			   var marca = "SELECIONE"


		$('#codbarra').val(codbarra);
		$('#nomeProd').val(nomeProd);
		$('#descr').val(descr);
		$('#foto').val(foto);
		$('#codId').val(procResponse.produtos[0].id);
		$('#menu').val(menu);
		$('#unimed').val(unimed);
		$('#marca').val(marca);



		//Fill paging data
		if (procResponse.resultsSetInfo != undefined)
		{
			pagingData.pageSize =  parseInt(procResponse.resultsSetInfo.pageSize);
			pagingData.startPage =  parseInt(procResponse.resultsSetInfo.startPage);
			pagingData.moreRowsAvailable =  procResponse.resultsSetInfo.moreRowsAvailable;
			pagingData.totalRowsAvailable =  parseInt(procResponse.resultsSetInfo.totalRowsAvailable);
		}

		//make sure return is an array
		if ($.isArray(procResponse.produtos[0].precos)){

			if(procResponse.produtos[0].precos != [])
			{
				var oi = 0;
				var tmpLength = procResponse.produtos[0].precos.length;
				for (var i=1; i <= tmpLength; i++)
				{
					if (procResponse.produtos[0].precos[oi].acessos != null){
						var count = procResponse.produtos[0].precos[oi].acessos.length;
						if(procResponse.produtos[0].precos[oi].acessos[count-1] != null){
							var b =     procResponse.produtos[0].precos[oi].acessos[count-1].userId;
							var c =     convertData(procResponse.produtos[0].precos[oi].acessos[count-1].data);
						}else{
							var b= "";
							var c= "";
						}
					}else{
						var b= "";
						var c= "";
					}
					if(procResponse.produtos[0].precos[oi].supermercadoid != null){
							var sup = procResponse.produtos[0].precos[oi].supermercadoid.superId;
					}else{
							var sup = "";
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
						supermercadoid:	sup,
						preco:			procResponse.produtos[0].precos[oi].preco,
						precopromo:  	procResponse.produtos[0].precos[oi].precopromo,
						promocao:  		procResponse.produtos[0].precos[oi].promocao,
						data  :         c,
						userId:  		b
					}

					oi++;
				}
			}
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

function produtoDialog_fill_data(procResponse,data2)
{
	data2[0] =
	{
		cellno:          0,
		id:		         0,
		codBarra:		"",
		nome:  	        "",
		unimed  :       "",
		marca:  	    "",
		supermercadoId: "",
		preco  :        "",
		promocao:  		"",
		precopro:  		"",
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
	if (($.isArray(procResponse.produtos))&&(procResponse.produtos[0].precos != null))
	{

		var oi = 0;
		var tmpLength = procResponse.produtos[0].precos.length;
		for (var i=1; i <= tmpLength; i++)
		{
			if(procResponse.produtos[0].precos[oi].idProduto.marca != null){
				var a =		procResponse.produtos[0].precos[oi].idProduto.marca.nome;
			}else{
				a= 0;
			}
			if(procResponse.produtos[0].precos[oi].idProduto.unimed != null){
				var e =		procResponse.produtos[0].precos[oi].idProduto.unimed.nome;
			}else{
				e= 0;
			}
			var conta = procResponse.produtos[0].precos[oi].idProduto.precos.length;
			var preco = procResponse.produtos[0].precos[oi].idProduto.precos;
			var precoId = 0;
			for(var ac = 0;ac<conta;ac++){
				if(preco[ac].supermercadoid != null){
					if(preco[ac].supermercadoid.superId == parseInt($('#supId').val())){
						if(preco[ac].precoid > precoId){
							if (preco[ac].acessos != null){
								var count = preco[ac].acessos.length;
								if(preco[ac].acessos[count-1] != null){
									var f =     preco[ac].acessos[count-1].userId;
									var g =     convertData(preco[ac].acessos[count-1].data);
								}else{
									var f= "";
									var g= "";
								}
							}else{
								var f= "";
								var g= "";
							}
							var h =     $('#supId').val();
							if(preco != null){
								var w =    preco[ac].preco;
								var y =    preco[ac].promocao;
								if(y==1)
									y = "Sim"
								else
								    y = "Não"
								var z =    preco[ac].precopromo;
							}else{
								var w = "";
								var y = "";
								var z = "";
							}
							precoId = preco[ac].precoid;
						}
					}
				}
			}
			data2[i] =
			{

				cellno:         i,
				id:		        procResponse.produtos[0].precos[oi].idProduto.id,
				codBarra:		procResponse.produtos[0].precos[oi].idProduto.codBarra,
				nome:  	        procResponse.produtos[0].precos[oi].idProduto.nome,
				unimed  :       e,
				marca:  	    a,
				supermercadoId: h,
				preco  :        w,
				promocao:  		y,
				precopro:  		z,
				data:  		    g,
				userId:  		f,
				cellno1:        ""

			}

			oi++;
		}
	}

	return data2;
};

//==========================================================



//=========================================================

function produtoDialog_fill_data2(procResponse,data2)
{

	data2[0] =
	{
		cellno:          0,
		id:		         0,
		codBarra:		"",
		nome:  	        "",
		unimed  :       "",
		marca:  	    "",
		supermercadoId: "",
		preco  :        "",
		promocao:  		"",
		precopro:  		"",
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
	if (($.isArray(procResponse.produtos))&&(procResponse.produtos != null))
	{

		var oi = 0;
		var tmpLength = procResponse.produtos.length;
		for (var i=1; i <= tmpLength; i++)
		{
			if(procResponse.produtos[oi].marca != null){
				var a =		procResponse.produtos[oi].marca.nome;
			}else{
				a= 0;
			}
			if(procResponse.produtos[oi].menu != null){
				var b =		procResponse.produtos[oi].menu.nome;
			}else{
				b= 0;
			}
			if(procResponse.produtos[oi].unimed != null){
				var e =		procResponse.produtos[oi].unimed.nome;
			}else{
				e= 0;
			}
			if (procResponse.produtos[oi].acessos != null){
				var count = procResponse.produtos[oi].acessos.length;
				if(procResponse.produtos[oi].acessos[count-1] != null){
					var f =     procResponse.produtos[oi].acessos[count-1].userId;
					var g =     convertData(procResponse.produtos[oi].acessos[count-1].data);
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
					if(procResponse.produtos[oi].precos[0].supermercadoid != null){
						var h =     procResponse.produtos[oi].precos[0].supermercadoid.superId;
					}else{
						var h = "";
					}
					var j =     procResponse.produtos[oi].precos[0].precopromo
					var w =     procResponse.produtos[oi].precos[0].preco
					var y =     procResponse.produtos[oi].precos[0].promocao

				}else{
					var h= "";
					var j= "";
					var w= "";
					var y= "";
				}
			}else{
				var h= "";
				var j= "";
			}
			data2[i] =
			{

				cellno:         i,
				id:		        procResponse.produtos[0].id,
				codBarra:		procResponse.produtos[0].codBarra,
				nome:  	        procResponse.produtos[0].nome,
				unimed  :       e,
				marca:  	    a,
				supermercadoId: h,
				preco  :        w,
				promocao:  		y,
				precopro:  		j,
				data:  		    g,
				userId:  		f,
				cellno1:        ""

			}

			oi++;
		}
	}

	return data2;
};

//==========================================================

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