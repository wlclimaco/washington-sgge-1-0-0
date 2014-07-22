<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">

qat.model.supermercado.pages  = {
    var data = new Array();
	openPagedFetchWS : function (iId)
	{
	    //if viewLOaddedObject filled by controller don't make a ajax call
		    var oData = new qat.model.fetchByIdRequest(null,iId);
			//rest_post_call('qat-webdaptive/procedure/api/fetchByRequestBAS', oData, fill_data, process_error);
			rest_post_call('qat-sysmgmt-sample/services/rest/SupermercadoService/fetchSupermercadoById', oData, qat.model.supermercado.pages.fill_data, qat.model.supermercado.pages.process_error);
	},
	openPagedFetchWSSuper : function (viewLoadedObject)
	{
		if(viewLoadedObject == null){
			rest_post_call('qat-sysmgmt-sample/services/rest/SupermercadoService/fetchAllSupermercados', {}, qat.model.supermercado.pages.fill_data, qat.model.supermercado.pages.process_error);
		}else{
			qat.model.supermercado.pages.fill_data(viewLoadedObject);
		}
	},

    fill_data :function (oResponse)
	{
		 var marca = '';
		for (var i = 0; i < oResponse.supermercados.length; i++) {
			marca  +=   '<option value="' + oResponse.supermercados[i].superId + '">' + oResponse.supermercados[i].nome + " " +oResponse.supermercados[i].enderecos[0].logradouro+'</option>';
		 }
      $("select#estado").html(marca);
	},
	fill_datas :function (oResponse)
	{
	   console.log('dddd');
	},

	process_error : function (jqXHR, textStatus, errorThrown)
	{
		wd.core.process_xhr_error(jqXHR, textStatus, errorThrown);
	},
	gravar : function (oLista,acao){
		if(oLista.length > 0){
			var oData=[];
			var id = parseInt($('#estado').val());
			debugger;
			if(acao == "addSup"){
				for(var i = 0; i< oLista.length;i++ ){
					preco = new qat.model.preco(0, oLista[i].id, id, null,0.0, 1,0.0, null, null);
					oData.push({id:oLista[i].id,precos:[{precoid:0,idProduto:oLista[i].id,supermercadoid:{superId:id}}],tabela:1});
				}
			}else if(acao == "remSup"){
				for(var i = 0; i< oLista.length;i++ ){
					preco = new qat.model.preco(0, oLista[i].id, id, null,0.0, 1,0.0, null, null);
					oData.push({id:oLista[i].id,precos:[{precoid:0,idProduto:oLista[i].id,supermercadoid:{superId:id}}],tabela:2});
				}
			}else if(acao == "addPro"){
				for(var i = 0; i< oLista.length;i++ ){
					preco = new qat.model.preco(0, oLista[i].id, id, null,0.0, 1,0.0, null, null);
					oData.push({id:oLista[i].id,precos:[{precoid:0,idProduto:oLista[i].id,supermercadoid:{superId:id}}],tabela:3});
				}
			}else if(acao == "remPro"){
				for(var i = 0; i< oLista.length;i++ ){
					preco = new qat.model.preco(0, oLista[i].id, id, null,0.0, 1,0.0, null, null);
					oData.push({id:oLista[i].id,precos:[{precoid:0,idProduto:oLista[i].id,supermercadoid:{superId:id}}],tabela:4});
				}
			}
			rest_post_call('qat-sysmgmt-sample/services/rest/ProdutoService/updateProduto', {produtos:oData}, qat.model.supermercado.pages.fill_data2, qat.model.supermercado.pages.process_error);
		}else{
			$('.yui-ge .message').addClass("ui-state-error");
		}
	},
	JSONToCSVConvertor : function(JSONData, ReportTitle, ShowLabel) {
    //If JSONData is not an object then JSON.parse will parse the JSON string in an Object
    var arrData = typeof JSONData != 'object' ? JSON.parse(JSONData) : JSONData;

    var CSV = '';
    //Set Report title in first row or line

    CSV += ReportTitle + '\r\n\n';

    //This condition will generate the Label/Header
    if (ShowLabel) {
        var row = "";

        //This loop will extract the label from 1st index of on array
        for (var index in arrData[0]) {

            //Now convert each value to string and comma-seprated
            row += index + ',';
        }

        row = row.slice(0, -1);

        //append Label row with line break
        CSV += row + '\r\n';
    }

    //1st loop is to extract each row
    for (var i = 0; i < arrData.length; i++) {
        var row = "";

        //2nd loop will extract each column and convert it in string comma-seprated
        for (var index in arrData[i]) {
            row += '"' + arrData[i][index] + '",';
        }

        row.slice(0, row.length - 1);

        //add a line break after each row
        CSV += row + '\r\n';
    }

    if (CSV == '') {
        alert("Invalid data");
        return;
    }

    //Generate a file name
    var fileName = "MyReport_";
    //this will remove the blank-spaces from the title and replace it with an underscore
    fileName += ReportTitle.replace(/ /g,"_");

    //Initialize file format you want csv or xls
    var uri = 'data:text/csv;charset=utf-8,' + escape(CSV);

    // Now the little tricky part.
    // you can use either>> window.open(uri);
    // but this will not work in some browsers
    // or you will not get the correct file extension

    //this trick will generate a temp <a /> tag
    var link = document.createElement("a");
    link.href = uri;

    //set the visibility hidden so it will not effect on your web-layout
    link.style = "visibility:hidden";
    link.download = fileName + ".csv";

    //this part will append the anchor tag and remove it after automatic click
    document.body.appendChild(link);
    link.click();
    document.body.removeChild(link);
},
	openProdutos function(oData){
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
		qat.model.supermercado.pages.openDialogCadastro(oData);
	},
	openDialogCadastro function(oData,process_error)
	{
		rest_post_call('qat-sysmgmt-sample/services/rest/ProdutoService/fetchAllProdutos',oData , qat.model.supermercado.pages.fill_data2, process_error);
	},
	fill_data2 function(procResponse)
	{
		qat.model.supermercado.pages.data = reuse_fill_data(procResponse,qat.model.supermercado.pages.data,"produtoDialog2");
	},
	exportCSVProd function(oData,process_error)
	{
		rest_post_call('qat-sysmgmt-sample/services/rest/ProdutoService/fetchAllProdutos',oData , fill_dataCSV, process_error);
	},
	fill_dataCSV function(procResponse)
	{
		qat.model.supermercado.pages.data = reuse_fill_data(procResponse,qat.model.supermercado.pages.data,"produtoDialog2");
		qat.model.supermercado.pages.JSONToCSVConvertor(qat.model.supermercado.pages.data, "Supermercado", true);

	}
}

</script>
