<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
var aRowChg = new Array();
var rowChg;
var data = new Array();
var pgrid;
var gridPager;
var pagingData = new qat.model.pageData(null,null,null,null);
var id = $("div[id*='tabs']" ).attr("id");
var viewLoadedObject;

//loads object if being served via controller
<c:choose>
    <c:when test="${empty supermercadoResponse}">
			viewLoadedObject = null;
    </c:when>
    <c:otherwise>
			viewLoadedObject = ${supermercadoResponse};
    </c:otherwise>
</c:choose>

var buttonFormat = function (row, cell, value, columnDef, dataContext) {

	return "<input type='button' value='Editar' class='btn "+value+"' />"

}

var columns = [
	{id:"cellno", name: "#", field:"cellno", resizable:false, cssClass:"cell-center", width:30},
	{id:"action", name: procedure.grid.act.title, field:"action", resizable:false, cssClass:"cell-center", width:65, formatter:Slick.Formatters.HTML},
	{id:"supermercadoid", name: procedure.grid.psak.title, field:"supermercadoid", editor:Slick.Editors.Text},
	{id:"razao", name: supermercado.grid.prazao.title, field:"razao", editor:Slick.Editors.Text},
	{id:"cpfCnpj", name: supermercado.grid.pcnpj.title, field:"cpfCnpj", editor:Slick.Editors.Text},
	{id:"rgInc", name: supermercado.grid.pincmun.title, field:"rgInc", editor:Slick.Editors.Text},
	{id:"id", name: supermercado.grid.pprod.title, field:"id",  width:100,formatter: buttonFormat},
	{id:"logradouro", name: supermercado.grid.plogradouro.title, field:"logradouro", editor:Slick.Editors.Text},
	{id:"bairro", name: supermercado.grid.pbairro.title, field:"bairro", editor:Slick.Editors.Text},
	{id:"cidade", name: supermercado.grid.pcidade.title, field:"cidade", editor:Slick.Editors.Text},
	{id:"id", name: " ", field:"id",  width:100,formatter: buttonFormat}
];

//grid options
var options =
{
	<sec:authorize  access="hasAnyRole('ROLE_DOMAIN USERS', 'ROLE_DOMAIN ADMINS')">
	editable: true,
	</sec:authorize>
	enableAddRow: false,
	forceFitColumns: true,
	enableCellNavigation: true,
	explicitInitialization: true
};

//Custom RemoteModel Extension for SlickGrid
(function($)
{
	function RemoteModel()
	{
		var onProcDataLoading = new EventHelper();
		var onProcDataLoaded = new EventHelper();

		<sec:authorize  access="hasAnyRole('ROLE_DOMAIN USERS', 'ROLE_DOMAIN ADMINS')">
		function callInsertWS()
		{
			onProcDataLoading.notify({});

		   // var oData = new qat.model.reqCadastro(null, new qat.model.procedure(0,0,data[0].pcode,data[0].pdesc,0.0),true,true);qat.model.cadastro = function(_Id, _type, _nome, _descricao,_controlAcess)
			var oData = new qat.model.reqSupermercado(null, new qat.model.supermercado(data[0].supermercadoid,data[0].usuario, data[0].email,data[0].site,data[0].usuario,data[0].senha,data[0].enderecoid,data[0].eid,data[0].endereco,data[0].logradouro,data[0].bairro,data[0].estado,data[0].cidade,data[0].numero,data[0].cep,data[0].nome,data[0].complemento,data[0].documenroid,data[0].did,data[0].rgInc,data[0].cpfCnpj,data[0].razao,data[0].dateNascimento),true,true);
			console.log(oData);
			rest_post_call('qat-sysmgmt-sample/services/rest/SupermercadoService/insertSupermercado', oData, fill_data, process_error);
			//rest_post_call('qat-sysmgmt-sample/services/rest/CidadeService/insertCidade', oData, fill_data, process_error);

		}

		function callUpdateWS()
		{
			var tempLength = aRowChg.length;
			if (tempLength > 0)
			{
				onProcDataLoading.notify({});
			}

			var bList = true;
			for(a=0; a < (tempLength); a++)
			{
				if (a==(tempLength-1))
				{
					bList = true;
				}
				else
				{
					bList = false;
				}

				var oData = new qat.model.reqSupermercado(null,
				new qat.model.supermercado(data[aRowChg[a]].supermercadoid,data[aRowChg[a]].usuario, data[aRowChg[a]].email,data[aRowChg[a]].site,data[aRowChg[a]].usuario,data[aRowChg[a]].senha,data[aRowChg[a]].enderecoid,data[aRowChg[a]].eid,data[aRowChg[a]].endereco,data[aRowChg[a]].logradouro,data[aRowChg[a]].bairro,data[aRowChg[a]].estado,data[aRowChg[a]].cidade,data[aRowChg[a]].numero,data[aRowChg[a]].cep,data[aRowChg[a]].nome,data[aRowChg[a]].complemento,data[aRowChg[a]].documenroid,data[aRowChg[a]].did,data[aRowChg[a]].rgInc,data[aRowChg[a]].cpfCnpj,data[aRowChg[a]].razao,data[aRowChg[a]].dateNascimento)
				, bList, true);
				//rest_post_call('qat-webdaptive/procedure/api/updateBAS', oData, fill_data, process_error);
				rest_post_call('qat-sysmgmt-sample/services/rest/SupermercadoService/updateSupermercado', oData, fill_data, process_error);
			}
		}

		function callDeleteWS(_procId)
		{
			onProcDataLoading.notify({});
		    var oData = new qat.model.reqSupermercado(null,
			new qat.model.supermercado(_procId,"","","","","","","","","","","","","","","","","","","","")
			,true,true);
			rest_post_call('qat-sysmgmt-sample/services/rest/SupermercadoService/deleteSupermercado', oData, fill_data, process_error);

		//	rest_post_call('qat-sysmgmt-sample/services/rest/SupermercadoService/fetchAllSupermercados', {}, fill_data, process_error);
		}

		function callRefreshWS(_i)
		{
			onProcDataLoading.notify({});
			var oData = new qat.model.refreshRequest(null, _i, true, true);
			rest_post_call('qat-webdaptive/procedure/api/refreshBAS', oData, fill_data, process_error);
		}
		</sec:authorize>

		function callPagedFetchWS(_iPageSize, _iStartPage)
		{
		    onProcDataLoading.notify({});
		    //if viewLOaddedObject filled by controller don't make a ajax call
			if (viewLoadedObject == null)
			{
			    var oData = new qat.model.pagedInquiryRequest(null, _iPageSize, _iStartPage, true);
				//rest_post_call('qat-webdaptive/procedure/api/fetchByRequestBAS', oData, fill_data, process_error);
				rest_post_call('qat-sysmgmt-sample/services/rest/SupermercadoService/fetchAllSupermercados', {}, fill_data, process_error);
			}
			else
			{
				//rest_post_call('qat-sysmgmt-sample/services/rest/SupermercadoService/fetchAllSupermercados', {}, fill_data, process_error);
				fill_data(viewLoadedObject);
				viewLoadedObject = null;
			}
		}

		function fill_data(procResponse)
		{
			data = reuse_fill_data(procResponse,data,"supermercado");
			onProcDataLoaded.notify({});
		}

		function process_error(jqXHR, textStatus, errorThrown)
		{
			wd.core.process_xhr_error(jqXHR, textStatus, errorThrown);
			onProcDataLoaded.notify({});
		}

		function isProcDataLoaded()
		{
			if (data == undefined || data == null)
			{
				return false;
			}
			return true;
		}

		return{
			// properties
			"data": data,

			// methods
			"isProcDataLoaded": isProcDataLoaded,
			"callPagedFetchWS": callPagedFetchWS,
			<sec:authorize  access="hasAnyRole('ROLE_DOMAIN USERS', 'ROLE_DOMAIN ADMINS')">
			"callDeleteWS": callDeleteWS,
			"callInsertWS": callInsertWS,
			"callUpdateWS": callUpdateWS,
			"callRefreshWS": callRefreshWS,
			</sec:authorize>
			// events
			"onProcDataLoading": onProcDataLoading,
			"onProcDataLoaded": onProcDataLoaded
		};
	};
	$.extend(true, window, { Slick: { Data: { RemoteModel: RemoteModel }}});
})(jQuery);

function requiredFieldValidator(value)
{
	if (wd.core.isEmpty(value))
	{
		wd.core.displayNotificationMessage('#StatusBar',procedure.requiredfield.msg, false, 'error', 5000);
		return {valid:false, msg:null};
	}
	else
	{
		return {valid:true, msg:null};
	}
};

function validateFields(rowValue)
{
	if (wd.core.isEmpty(data[rowValue].pcode))
	{
		pgrid.gotoCell(rowValue,3,true);
		$(pgrid.getActiveCellNode()).addClass("invalid");
		$(pgrid.getActiveCellNode()).stop(true,true).effect("highlight", {color:"red"}, 300);
		wd.core.displayNotificationMessage('#StatusBar',procedure.requiredfield.msg, false, 'error', 5000);
	}
	else if (wd.core.isEmpty(data[rowValue].pdesc))
	{
		pgrid.gotoCell(rowValue,4,true);
		$(pgrid.getActiveCellNode()).addClass("invalid");
		$(pgrid.getActiveCellNode()).stop(true,true).effect("highlight", {color:"red"}, 300);
		wd.core.displayNotificationMessage('#StatusBar',procedure.requiredfield.msg, false, 'error', 5000);
	}
	else
	{
		return true;
	}
	return false;
};

<sec:authorize  access="hasAnyRole('ROLE_DOMAIN USERS', 'ROLE_DOMAIN ADMINS')">
$('#supGrid').keyup(function(e)
{
	if (e.keyCode == 13)
	{
		if (rowChg >= 1 )
		{
			ploader.callUpdateWS(aRowChg);
		}
		else
		{
		//	if (validateFields(0))
		//	{
				ploader.callInsertWS();
		//	}
		}
	}
});

$('#refreshsup').click(function() {
	ploader.callRefreshWS(135);
});
</sec:authorize>
$('#listsup').click(function() {
	 ploader.callPagedFetchWS(20,0);
});
</script>
