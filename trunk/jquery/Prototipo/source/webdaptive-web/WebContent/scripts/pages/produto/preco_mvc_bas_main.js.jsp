
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
    <c:when test="${empty precoResponse}">
			viewLoadedObject = null;
    </c:when>
    <c:otherwise>
			viewLoadedObject = ${precoResponse};
    </c:otherwise>
</c:choose>
var columns=[];
 var checkboxSelector = new Slick.CheckboxSelectColumn({
      cssClass: "slick-cell-checkboxsel"
    });
	var buttonFormat = function (row, cell, value, columnDef, dataContext) {
		if(row > 0)
			return "<input type='button' value='Detail' class='btn ' />"
		else
			return ""
	}
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
var aRowChg = new Array();
var rowChg;
var data = new Array();
var pgrid;
var gridPager;
var availableTags = [];
var pagingData = new qat.model.pageData(null,null,null,null);
var id = $("div[id*='tabs']" ).attr("id");
var viewLoadedObject;

//loads object if being served via controller

var columns=[];
 var checkboxSelector = new Slick.CheckboxSelectColumn({
      cssClass: "slick-cell-checkboxsel"
    });
	var buttonFormat = function (row, cell, value, columnDef, dataContext) {
		if(row > 0)
			return "<input type='button' value='Detail' class='btn ' />"
		else
			return ""
	}

	  function AutoCompleteEditor(args) {
		    var $input;
		    var defaultValue;
		    var scope = this;
		    var calendarOpen = false;

		    this.init = function () {

				var callback = function  (oResponse)
				{
					var embalagems = oResponse.embalagem;
					var tmpLength = embalagems.length;
					for (var i=0; i < tmpLength; i++){
						availableTags.push(embalagems[i].id+" - "+embalagems[i].nome);
					}
				}

				if(availableTags.length == 0){
					var onProcDataLoading = new EventHelper();
					onProcDataLoading.notify({});
					rest_post_call('qat-sysmgmt-sample/services/rest/ProdutoService/fetchAllUniMeds', {embalagem:{unimedid:{}}}, callback, null,false);
				}
					$input = $("<INPUT id='tags' class='editor-text' />");
					$input.appendTo(args.container);
					$input.focus().select();
					$input.autocomplete({
						  source: availableTags
					});

		    };

		    this.destroy = function () {
		      $input.autocomplete("destroy");
		    };

		    this.focus = function () {
		      $input.focus();
		    };

		    this.loadValue = function (item) {
		      defaultValue = item[args.column.field];
		      $input.val(defaultValue);
		      $input[0].defaultValue = defaultValue;
		      $input.select();
		    };

		    this.serializeValue = function () {
		      return $input.val();
		    };

		    this.applyValue = function (item, state) {
		      item[args.column.field] = state;
		    };

		    this.isValueChanged = function () {
		      return (!($input.val() == "" && defaultValue == null)) && ($input.val() != defaultValue);
		    };

		    this.validate = function () {
		      return {
		        valid: true,
		        msg: null
		      };
		    };

		    this.init();
		  }
	 function SelectCellEditor(args) {
        var $select;
        var defaultValue;
        var scope = this;

        this.init = function() {

            if(args.column.options){
              opt_values = args.column.options.split(',');
            }else{
              opt_values ="yes,no".split(',');
            }
            option_str = ""
            for( i in opt_values ){
              v = opt_values[i];
              option_str += "<OPTION value='"+v+"'>"+v+"</OPTION>";
            }
            $select = $("<SELECT tabIndex='0' class='editor-select'>"+ option_str +"</SELECT>");
            $select.appendTo(args.container);
            $select.focus();
        };

        this.destroy = function() {
            $select.remove();
        };

        this.focus = function() {
            $select.focus();
        };

        this.loadValue = function(item) {
            defaultValue = item[args.column.field];
            $select.val(defaultValue);
        };

        this.serializeValue = function() {
            if(args.column.options){
              return $select.val();
            }else{
              return ($select.val() == "yes");
            }
        };

        this.applyValue = function(item,state) {
            item[args.column.field] = state;
        };

        this.isValueChanged = function() {
            return ($select.val() != defaultValue);
        };

        this.validate = function() {
            return {
                valid: true,
                msg: null
            };
        };

        this.init();
    }
//    columns.push(checkboxSelector.getColumnDefinition());
//columns & column settings for the grid
columns[0] = {id:"cellno", name: "#", field:"cellno", resizable:false, cssClass:"cell-center", width:30};
columns[1] = {id:"action", name: procedure.grid.act.title, field:"action", resizable:false, cssClass:"cell-center", width:65, formatter:Slick.Formatters.HTML};
columns[2] = {id:"id", name: procedure.grid.psak.title, field:"id", resizable:false, cssClass:"cell-center", width:75};
columns[3] = {id:"nome", name: marca.grid.pmarca.title, field:"nome", editor:Slick.Editors.Text};
columns[4] = {id:"qnt", name: "Quantidade", field:"qnt", editor:Slick.Editors.Text};
columns[5] = {id:"unimed", name:submenu.grid.pmenu.title, field:"unimed",  width:135, editable:true, cssClass:"pad-4-left", sortable:true, editor:AutoCompleteEditor},
columns[6] = {id:"produtos", name: menu.grid.pprodutos.title, field:"produtos",resizable:false, cssClass:"cell-center", width:65, formatter:Slick.Formatters.HTML};
columns[7] = {id:"data", name: cidade.grid.pdata.title, field:"data"};
columns[8] = {id:"userId", name: cidade.grid.puser.title, field:"userId"};
columns[9] = {id:"id", name:"Lock", field:"id",  options: "Locked,Unlocked", editor: SelectCellEditor};

//];

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
		var onMarcaDataLoading = new EventHelper();
		var onMarcaDataLoaded = new EventHelper();

		<sec:authorize  access="hasAnyRole('ROLE_DOMAIN USERS', 'ROLE_DOMAIN ADMINS')">
		function callInsertWS()
		{
			onMarcaDataLoading.notify({});
			//debugger
			var ac = data[0].unimed;
			var ab = ac.split('-');
			var oData = new qat.model.reqEmbalagem(null, new qat.model.embalagem(0,data[0].nome,data[0].qnt,parseInt(ab[0]),null, null),true,true);
			rest_post_call('qat-sysmgmt-sample/services/rest/ProdutoService/insertEmbalagem', oData, fill_data, process_error,true);
		}

		function callUpdateWS()
		{
			var tempLength = aRowChg.length;
			if (tempLength > 0)
			{
				onMarcaDataLoading.notify({});
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
				onMarcaDataLoading.notify({});
				var ac = data[aRowChg[a]].unimed;
				var ab = ac.split('-');
				var oData = new qat.model.reqEmbalagem(null, new qat.model.embalagem(data[aRowChg[a]].id,data[aRowChg[a]].nome,data[aRowChg[a]].qnt,parseInt(ab[0]),null, null),true,true);
				rest_post_call('qat-sysmgmt-sample/services/rest/ProdutoService/updateEmbalagem', oData, fill_data, process_error,true);
			}
		}
		function openProdutos(value){
			dom = "<div class='id'>" + value + "</div>";
			$actionTagDialog = $("#action-produto-dialog").load('../produto/produtosDialogByRequestBAS').dialog({
				autoOpen: false,
				title: 'Lista Produto Supermercado',
				width: 1024,
				height: 400,
				modal: true,
				buttons: {
					Cancel: function() {
						$(this).dialog('close');
					}
				},
				dialogClass: 'action-dialog buttons-left',
				resizable: false
			});
			$actionTagDialog.empty();
			$actionTagDialog.dialog('open');
			openDialogCadastro({produto:{tabela:2,marca:{id:parseInt(value),userId:'rod'},userId:'rod'}});
			valueGlobal = parseInt(value);
		}

		function callDeleteWS(_procId)
		{
			onMarcaDataLoading.notify({});
		    var oData = new qat.model.reqEmbalagem(null, new qat.model.embalagem(_procId),true,true);
			rest_post_call('qat-sysmgmt-sample/services/rest/ProdutoService/deleteEmbalagem', oData, fill_data, process_error,true);

		}

		function openDialogCadastro(oData)
		{

		    onMarcaDataLoading.notify({});
			rest_post_call('qat-sysmgmt-sample/services/rest/ProdutoService/fetchAllEmbalagems',oData , fill_data2, process_error);

		}
		function fill_data2(procResponse)
		{

			data = reuse_fill_data(procResponse,data,"produtoDialog2");
			onMarcaDataLoaded.notify({});
		}

		function exportCSVProd()
		{
			debugger;
		    onMarcaDataLoading.notify({});
			rest_post_call('qat-sysmgmt-sample/services/rest/ProdutoService/fetchAllProdutos',{produto:{tabela:2,marca:{id:parseInt(valueGlobal),userId:'rod'},userId:'rod'}} , fill_dataCSV, process_error);
		}
		function fill_dataCSV(procResponse)
		{
			data = reuse_fill_data(procResponse,data,"produtoDialog2");
			qat.model.supermercado.pages.JSONToCSVConvertor(data, "Supermercado", true);
			onMarcaDataLoaded.notify({});

		}

		function callRefreshWS(_i)
		{
			onMarcaDataLoading.notify({});
			rest_post_call('qat-sysmgmt-sample/services/rest/ProdutoService/fetchAllEmbalagems',{}, fill_data, process_error,true);
		}
		</sec:authorize>

		function callPagedFetchWS(_iPageSize, _iStartPage)
		{
		    onMarcaDataLoading.notify({});
		    //if viewLOaddedObject filled by controller don't make a ajax call
			if (viewLoadedObject == null)
			{
			    var oData = new qat.model.pagedInquiryRequest(null, _iPageSize, _iStartPage, true);
				rest_post_call('qat-sysmgmt-sample/services/rest/ProdutoService/fetchAllEmbalagems',{}, fill_data, process_error,true);
			}
			else
			{
				fill_data(viewLoadedObject);
				viewLoadedObject = null;
			}
		}

		function fill_data(procResponse)
		{
			data = reuse_fill_data(procResponse,data,"embalagem");
			onMarcaDataLoaded.notify({});
		}

		function process_error(jqXHR, textStatus, errorThrown)
		{
			wd.core.process_xhr_error(jqXHR, textStatus, errorThrown);
			onMarcaDataLoaded.notify({});
		}

		function isMarcaDataLoaded()
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
			"isMarcaDataLoaded": isMarcaDataLoaded,
			"callPagedFetchWS": callPagedFetchWS,
			<sec:authorize  access="hasAnyRole('ROLE_DOMAIN USERS', 'ROLE_DOMAIN ADMINS')">
			"callDeleteWS" : callDeleteWS,
			"callInsertWS" : callInsertWS,
			"callUpdateWS" : callUpdateWS,
			"callRefreshWS": callRefreshWS,
			"openProdutos" : openProdutos,
			"exportCSVProd":exportCSVProd,
			"openDialogCadastro" : openDialogCadastro,
			</sec:authorize>
			// events
			"onMarcaDataLoading": onMarcaDataLoading,
			"onMarcaDataLoaded": onMarcaDataLoaded
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

function validateFieldsMarca(rowValue)
{
	if (wd.core.isEmpty(data[rowValue].nome))
	{
		pgrid.gotoCell(rowValue,3,true);
		$(pgrid.getActiveCellNode()).addClass("invalid");
		$(pgrid.getActiveCellNode()).stop(true,true).effect("highlight", {color:"red"}, 300);
		wd.core.displayNotificationMessage('#StatusBar',procedure.requiredfield.msg, false, 'error', 5000);
	}
	else if (wd.core.isEmpty(data[rowValue].qnt))
	{
		pgrid.gotoCell(rowValue,4,true);
		$(pgrid.getActiveCellNode()).addClass("invalid");
		$(pgrid.getActiveCellNode()).stop(true,true).effect("highlight", {color:"red"}, 300);
		wd.core.displayNotificationMessage('#StatusBar',procedure.requiredfield.msg, false, 'error', 5000);
	}else if ( $.inArray(data[rowValue].unimed,availableTags) == -1){
			pgrid.gotoCell(rowValue,5,true);
			$(pgrid.getActiveCellNode()).addClass("invalid");
			$(pgrid.getActiveCellNode()).stop(true,true).effect("highlight", {color:"red"}, 300);
			wd.core.displayNotificationMessage('#StatusBar',"UNIDADE MEDIDA NÃO CADASTRADO FAVOR ENTRAR NO MENU > CADASTRO MENU E CADASTRAR!", false, 'error', 5000);
	}
	else
	{
		return true;
	}
	return false;
};

<sec:authorize  access="hasAnyRole('ROLE_DOMAIN USERS', 'ROLE_DOMAIN ADMINS')">
$('#marcaGrid').keyup(function(e)
{
	if (e.keyCode == 13)
	{
		if (rowChg >= 1 )
		{
		if (validateFieldsMarca(rowChg))
			{
				ploaderEmb.callUpdateWS(aRowChg);
			}

		}
		else
		{
			if (validateFieldsMarca(0))
			{
				ploaderEmb.callInsertWS();
			}
		}
	}
});

$('#refreshmarca').click(function() {
	ploaderEmb.callRefreshWS(135);
});
</sec:authorize>
$('#listmarca').click(function() {
	 ploaderEmb.callPagedFetchWS(20,0);
});
</script>

	//columns & column settings for the grid
	columns.push(checkboxSelector.getColumnDefinition());
	columns[1] = {id:"cellno", name: "#", field:"cellno", resizable:false, cssClass:"cell-center", width:30};
	columns[2] = {id:"action", name: procedure.grid.act.title, field:"action", resizable:false, cssClass:"cell-center", width:65, formatter:Slick.Formatters.HTML};
	columns[3] = {id:"id", name: procedure.grid.psak.title, field:"id", resizable:false, cssClass:"cell-center", width:75};
	columns[4] = {id:"supermercadoid", name:preco.grid.supermercadoid.title, field:"supermercadoid",  width:135, editable:true, cssClass:"pad-4-left", sortable:true,editor:Slick.Editors.Text},
	columns[5] = {id:"preco", name: preco.grid.preco.title, field:"preco", editor:Slick.Editors.Text};
	columns[6] = {id:"promocao", name: preco.grid.promocao.title, field:"promocao" ,editor:Slick.Editors.Text};
	columns[7] = {id:"precopromo", name: preco.grid.precopromo.title, field:"precopromo",editor:Slick.Editors.Text};
	columns[8] = {id:"data", name: cidade.grid.pdata.title, field:"data"};
	columns[9] = {id:"userId", name: cidade.grid.puser.title, field:"userId"};
	columns[10] = { id:'column1', field:'column1', name: "Column 1", width:75, editor: Slick.Editors.Select, editorOptions:{options:"val1,val2,val3"}  }



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
		function callInsertWS(_Id)
		{

			onProcDataLoading.notify({});
			if(_Id > 0){
				var oData = callCreateObject(_Id);
				var oData = new qat.model.reqProduto(null, oData, false, false);
				rest_post_call('qat-sysmgmt-sample/services/rest/ProdutoService/updateProduto', oData, fill_data_2, process_error);
			}else{
				var oData = callCreateObject();
				var oData = new qat.model.reqProduto(null, oData, false, false);
				rest_post_call('qat-sysmgmt-sample/services/rest/ProdutoService/insertProduto', oData, fill_data_2, process_error);
			}
		}
		</sec:authorize>

		function callPagedFetchWS(_iPageSize, _iStartPage,iId)
		{
		    var oData = new qat.model.pagedInquiryRequest(null, _iPageSize, _iStartPage, true);
			if(iId > 0)
				rest_post_call('qat-sysmgmt-sample/services/rest/ProdutoService/fetchProdutoById', {fetchId:iId}, fill_data, process_error)
			else{
				insertProduto_fill_data(null,data);
			}

		}
		function callCreateObject(_id)
		{
			var tmpLength = data.length;
			var oDataPreco =[];
			for (var i=1; i < tmpLength; i++){
				oDataPreco.push(new qat.model.preco(data[i].id, parseInt(_id), data[i].supermercadoid, null,data[i].preco, data[i].promocao,data[i].precopromo, null, null));
			}
			return new qat.model.produto(parseInt(_id),
									 null,
									 $('#codbarra').val(),
									 $('#marca').val(),
									 $('#menu').val(),
									 $('#submenu').val(),
									 $('#trimenu').val(),
									 $('#unimed').val(),
									 $('#nomeProd').val(),
									 $('#descr').val(),
									 null,
									 oDataPreco,
									 null,
									 1);
		}

		function fill_data(procResponse)
		{
			data = reuse_fill_data(procResponse,data,"insertproduto");
			onProcDataLoaded.notify({});
		}
		function fill_data_2(procResponse)
		{
			$("#action-produto-dialog").dialog('close');
			rest_post_call('qat-sysmgmt-sample/services/rest/ProdutoService/fetchAllProdutos', {}, fill_data_3, process_error);
			onProcDataLoaded.notify({});
		}
		function fill_data_3(procResponse)
		{
			data = reuse_fill_data(procResponse,data,"produto");
			onProcDataLoaded.notify({});
		}
		function fill_data2(datas)
		{
			data = datas;
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

			"fill_data2"  : fill_data2,
			"callInsertWS": callInsertWS,
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

	if (wd.core.isEmpty(data[rowValue].supermercadoid))
	{
		pgrid.gotoCell(rowValue,3,true);
		$(pgrid.getActiveCellNode()).addClass("invalid");
		$(pgrid.getActiveCellNode()).stop(true,true).effect("highlight", {color:"red"}, 300);
		wd.core.displayNotificationMessage('#StatusBar',procedure.requiredfield.msg, false, 'error', 5000);
	}
	else if (wd.core.isEmpty(data[rowValue].preco))
	{
		pgrid.gotoCell(rowValue,4,true);
		$(pgrid.getActiveCellNode()).addClass("invalid");
		$(pgrid.getActiveCellNode()).stop(true,true).effect("highlight", {color:"red"}, 300);
		wd.core.displayNotificationMessage('#StatusBar',procedure.requiredfield.msg, false, 'error', 5000);
	}
	else if ((wd.core.isEmpty(data[rowValue].promocao))||(!wd.core.isEmpty(data[rowValue].promocao)))
	{
		if(wd.core.isEmpty(data[rowValue].promocao)){
			pgrid.gotoCell(rowValue,5,true);
			$(pgrid.getActiveCellNode()).addClass("invalid");
			$(pgrid.getActiveCellNode()).stop(true,true).effect("highlight", {color:"red"}, 300);
			wd.core.displayNotificationMessage('#StatusBar',procedure.requiredfield.msg, false, 'error', 5000);
		}else if (data[rowValue].promocao == "1"){
			if(wd.core.isEmpty(data[rowValue].precopromo)){
				pgrid.gotoCell(rowValue,6,true);
				$(pgrid.getActiveCellNode()).addClass("invalid");
				$(pgrid.getActiveCellNode()).stop(true,true).effect("highlight", {color:"red"}, 300);
				wd.core.displayNotificationMessage('#StatusBar',procedure.requiredfield.msg, false, 'error', 5000);
			}
			else{return true;}
		}else{
			return true;
		}
	}else
	{
		return true;
	}
	return false;
};

<sec:authorize  access="hasAnyRole('ROLE_DOMAIN USERS', 'ROLE_DOMAIN ADMINS')">
$('#precoGrid').keyup(function(e)
{
	if (e.keyCode == 13)
	{
		if (rowChg >= 1 )
		{
			var data3 = data;
			data.operationSuccess = true;
			ploaderPre.fill_data2(reuse_fill_data(data,data3,"preco"));
		}
		else
		{
			if (validateFields(0))
			{
				var data3 = data;
				data.operationSuccess = true;
				ploaderPre.fill_data2(reuse_fill_data(data,data3,"preco"));
			}
		}
	}
});

$('#refreshpreco').click(function() {
	ploaderPre.callPagedFetchWS(135);
});
</sec:authorize>
$('#listpreco').click(function() {
	 ploaderPre.callPagedFetchWS(20,0);
});
</script>
