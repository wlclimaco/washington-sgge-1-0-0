$(document).ready(function ()
{
	$.ajax({
				type		: "POST",
				url			: "http://localhost:8080/qat-sysmgmt-sample/services/rest/ProdutoService/fetchAllProdutos",
				username	: "rod",
				password	: "koala",
				data		: $.toJSON({}),
				contentType : "application/json; charset=utf-8",
				//timeout		: 5000,
				async		: true,
				success		: function(aData){console.log(aData)},
				error		: function(aData){console.log(aData)}
	 	});

	$.ajax({
			type		: "POST",
			url			: "http://localhost:8080/qat-sysmgmt-sample/services/rest/SiteService/fetchAllSites",
			username	: "rod",
			password	: "koala",
			data		: $.toJSON({}),
			contentType : "application/json; charset=utf-8",
			//timeout		: 5000,
			async		: true,
			success		: function(aData){console.log(aData)},
			error		: function(aData){console.log(aData)}
	});

		$.ajax({
				type		: "POST",
				url			: "http://localhost:8080/qat-sysmgmt-sample/services/rest/EntidadeService/fetchEmpresaByRequest",
				username	: "rod",
				password	: "koala",
				data		: $.toJSON({criteria:{}}),
				contentType : "application/json; charset=utf-8",
				//timeout		: 5000,
				async		: true,
				success		: function(aData){console.log(aData)},
				error		: function(aData){console.log(aData)}
	 	});
});