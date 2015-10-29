
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
var aRowChg = new Array();
var rowChg;
var dataPre = new Array();
var pgrid;
var availableTags = [];
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

		  function AutoCompleteEditor3(args) {
		    var $input;
		    var defaultValue;
		    var scope = this;
		    var calendarOpen = false;

		    this.init = function () {

				var callback = function  (oResponse)
				{
					var embalagems = oResponse.supermercados;
					var tmpLength = embalagems.length;
					for (var i=0; i < tmpLength; i++){
						availableTags.push(embalagems[i].superId+" - "+embalagems[i].documentos[0].razao);
					}
				}

				if(availableTags.length == 0){
					var onProcDataLoading = new EventHelper();
					onProcDataLoading.notify({});
					rest_post_call('qat-sysmgmt-sample/services/rest/SupermercadoService/fetchAllSupermercados', {}, callback, null);
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

	//columns & column settings for the grid
	columns.push(checkboxSelector.getColumnDefinition());
	columns[1] = {id:"cellno", name: "#", field:"cellno", resizable:false, cssClass:"cell-center", width:30};
	columns[2] = {id:"action", name: procedure.grid.act.title, field:"action", resizable:false, cssClass:"cell-center", width:65, formatter:Slick.Formatters.HTML};
	columns[3] = {id:"id", name: procedure.grid.psak.title, field:"id", resizable:false, cssClass:"cell-center", width:75};
	columns[4] = {id:"supermercadoid", name:preco.grid.supermercadoid.title, field:"supermercadoid",  width:135, editable:true, cssClass:"pad-4-left", sortable:true, editor:AutoCompleteEditor3},
	columns[5] = {id:"preco", name: preco.grid.preco.title, field:"preco", editor:Slick.Editors.Text};
	columns[6] = {id:"promocao", name:preco.grid.promocao.title, field:"promocao",  options: "Sim,Não", editor: SelectCellEditor};
	columns[7] = {id:"precopromo", name: preco.grid.precopromo.title, field:"precopromo",editor:Slick.Editors.Text};
	columns[8] = {id:"data", name: cidade.grid.pdata.title, field:"data"};
	columns[9] = {id:"userId", name: cidade.grid.puser.title, field:"userId"};



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
		function callInsertWSPrec(_Id)
		{
			onProcDataLoading.notify({});
			if(_Id > 0){
				var oData = callCreateObject(_Id);
				var oData = new qat.model.reqProduto(null, oData, true, true);
				rest_post_call('qat-sysmgmt-sample/services/rest/ProdutoService/updateProduto', oData, ploaderPro.fill_data_3, process_error);
			}else{
				var oData = callCreateObject();
				var oData = new qat.model.reqProduto(null, oData, true, true);
				rest_post_call('qat-sysmgmt-sample/services/rest/ProdutoService/insertProduto', oData, ploaderPro.fill_data_3, process_error);
			}
		}
		</sec:authorize>

		function callPagedFetchWSPrec(_iPageSize, _iStartPage,iId)
		{
		    var oData = new qat.model.pagedInquiryRequest(null, _iPageSize, _iStartPage, true);
			if(iId > 0)
				rest_post_call('qat-sysmgmt-sample/services/rest/ProdutoService/fetchProdutoById', {fetchId:iId}, fill_data, process_error)
			else{
				insertProduto_fill_data(null,dataPre);
			}

		}
		function callCreateObject(_id)
		{
			var tmpLength = dataPre.length;
			var oDataPreco =[];
			for (var i=1; i < tmpLength; i++){
				oDataPreco.push(new qat.model.preco(dataPre[i].id, parseInt(_id), dataPre[i].supermercadoid, null,dataPre[i].preco, dataPre[i].promocao,dataPre[i].precopromo, null, null));
			}
			return new qat.model.produto(parseInt(_id),
									 null,
									 $('#codbarra').val(),
									 $('.niceform #marca option:selected').val(),
									 $('.niceform #menu option:selected').val(),
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
			dataPre = reuse_fill_data(procResponse,dataPre,"insertproduto");
			onProcDataLoaded.notify({});
		}
		function fill_data_2(procResponse)
		{
			$("#action-produto-dialog").dialog('close');
			//rest_post_call('qat-sysmgmt-sample/services/rest/ProdutoService/fetchAllProdutos', {}, fill_data_3, process_error);
			onProcDataLoaded.notify({});
		}

		function fill_data2(datas)
		{
			dataPre = datas;
			onProcDataLoaded.notify({});
		}

		function process_error(jqXHR, textStatus, errorThrown)
		{
			wd.core.process_xhr_error(jqXHR, textStatus, errorThrown);
			onProcDataLoaded.notify({});
		}

		function isPrecDataLoaded()
		{
			if (dataPre == undefined || dataPre == null)
			{
				return false;
			}
			return true;
		}

		return{
			// properties
			"dataPre": dataPre,

			// methods
			"isPrecDataLoaded": isPrecDataLoaded,
			"callPagedFetchWSPrec": callPagedFetchWSPrec,
			<sec:authorize  access="hasAnyRole('ROLE_DOMAIN USERS', 'ROLE_DOMAIN ADMINS')">

			"fill_data2"  : fill_data2,
			"callInsertWSPrec": callInsertWSPrec,
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

	if (wd.core.isEmpty(dataPre[rowValue].supermercadoid))
	{
		pgrid.gotoCell(rowValue,3,true);
		$(pgrid.getActiveCellNode()).addClass("invalid");
		$(pgrid.getActiveCellNode()).stop(true,true).effect("highlight", {color:"red"}, 300);
		wd.core.displayNotificationMessage('#StatusBar',procedure.requiredfield.msg, false, 'error', 5000);
	}
	else if (wd.core.isEmpty(dataPre[rowValue].preco))
	{
		pgrid.gotoCell(rowValue,4,true);
		$(pgrid.getActiveCellNode()).addClass("invalid");
		$(pgrid.getActiveCellNode()).stop(true,true).effect("highlight", {color:"red"}, 300);
		wd.core.displayNotificationMessage('#StatusBar',procedure.requiredfield.msg, false, 'error', 5000);
	}
	else if ((wd.core.isEmpty(dataPre[rowValue].promocao))||(!wd.core.isEmpty(dataPre[rowValue].promocao)))
	{
		if(wd.core.isEmpty(dataPre[rowValue].promocao)){
			pgrid.gotoCell(rowValue,5,true);
			$(pgrid.getActiveCellNode()).addClass("invalid");
			$(pgrid.getActiveCellNode()).stop(true,true).effect("highlight", {color:"red"}, 300);
			wd.core.displayNotificationMessage('#StatusBar',procedure.requiredfield.msg, false, 'error', 5000);
		}else if (dataPre[rowValue].promocao == "1"){
			if(wd.core.isEmpty(dataPre[rowValue].precopromo)){
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
			var data3 = dataPre;
			data.operationSuccess = true;
			ploaderPre.fill_data2(reuse_fill_data(dataPre,data3,"preco"));
		}
		else
		{
			if (validateFields(0))
			{
				var data3 = dataPre;
				dataPre.operationSuccess = true;
				ploaderPre.fill_data2(reuse_fill_data(dataPre,data3,"preco"));
			}
		}
	}
});

$('#refreshpreco').click(function() {
	ploaderPre.callPagedFetchWSPrec(135);
});
</sec:authorize>
$('#listpreco').click(function() {
	 ploaderPre.callPagedFetchWSPrec(20,0);
});
</script>
