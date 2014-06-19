<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<script type="text/javascript">
var data = new Array();
var entryCount = 0;
var buttonClicked = "";
var crgrid;
var sortcol = "cellno";
var sortdir = 1;

var columns = [
	{id:"cellno", name: "#", field:"cellno", resizable:false, cssClass:"cell-center", width:30, sortable:true},
    {id:"csak", name: county.grid.cid.title, field:"csak", resizable:false, cssClass:"cell-center", width:75, sortable:true},
	{id:"cdesc", name: county.grid.cdescription.title, field:"cdesc"}
];

var options =
{
	editable: true,
	enableAddRow: false,
	forceFitColumns: true,
	enableCellNavigation: true,
	explicitInitialization: true
};

$.validator.setDefaults({
	highlight: function(input) {
		$(input).addClass("ui-state-highlight");
	},
	unhighlight: function(input) {
		$(input).removeClass("ui-state-highlight");
	}
});


//Custom RemoteModel Extension for SlickGrid
(function($)
{
	function RemoteModel()
	{
		var onCountyDataLoading = new EventHelper();
		var onCountyDataLoaded = new EventHelper();

		<sec:authorize access="hasRole('ROLE_DOMAIN ADMINS')">
		function callMaintainWS(_csak, _cdesc, _action)
		{
			onCountyDataLoading.notify({});
			var oData = new qat.model.reqCadastro(null, new qat.model.cadastro(1,null,"Test",_cdesc,null),true, false);
			//var oData = {};
			var mUrl = "";
			switch (_action)
			{
				case 'I':
					mUrl = 'qat-sysmgmt-sample/services/rest/ProdutoService/insertCadastro'
					break;
				case 'U':
					mUrl = 'qat-sysmgmt-sample/services/rest/ProdutoService/updateCadastro';
					break;
				case 'D':
					mUrl = 'qat-sysmgmt-sample/services/rest/ProdutoService/deleteCadastroById';
					break;
			}
			rest_post_call(mUrl, oData, fill_data, process_error);
		}

		function callRefreshWS(_i)
		{
			onCountyDataLoading.notify({});
			var oData = new qat.model.refreshRequest(null, _i, true, false);
			rest_post_call('qat-sysmgmt-sample/services/rest/CountyService/refreshCounties', oData, fill_data, process_error);
		}
		</sec:authorize>
		function callFetchWS()
		{
		    onCountyDataLoading.notify({});
		    var oData = new qat.model.fetchAllRequest(null);
			rest_post_call('qat-sysmgmt-sample/services/rest/CountyService/fetchAllCounties', oData, fill_data, process_error);
			//rest_post_call('qat-sysmgmt-sample/services/rest/ProdutoService/fetchAllProdutos', {}, fill_data, process_error);
		}

		function fill_data(countyResponse)
		{
			data = reuse_fill_data(countyResponse,data,"county");
			onCountyDataLoaded.notify({});
		}

		function process_error(jqXHR, textStatus, errorThrown)
		{
			wd.core.process_xhr_error(jqXHR, textStatus, errorThrown);
			onCountyDataLoaded.notify({});
		}

		function isCountyDataLoaded()
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
			"isCountyDataLoaded"	: isCountyDataLoaded,
			"callFetchWS"			: callFetchWS,
			<sec:authorize access="hasRole('ROLE_DOMAIN ADMINS')">
			"callMaintainWS"		: callMaintainWS,
			"callRefreshWS"			: callRefreshWS,
			</sec:authorize>
			// events
			"onCountyDataLoading"	: onCountyDataLoading,
			"onCountyDataLoaded"	: onCountyDataLoaded
		};
	};
	$.extend(true, window, { Slick: { Data: { RemoteModel: RemoteModel }}});
})(jQuery);


function comparer(a,b)
{
	var x = a[sortcol], y = b[sortcol];
	return (x == y ? 0 : (x > y ? 1 : -1));
};

<sec:authorize access="hasRole('ROLE_DOMAIN ADMINS')">
//validate county form
var val = $('#cform_rest').validate({
				submitHandler: function(form) {
					processCountyButtons();
				},
				rules: {
					cid_rest: {
						required: true,
						minlength: 1,
						maxlength: 4,
						number: true
					},
					cdesc_rest: {
						required: true,
						minlength: 5,
						maxlength: 50
					}
				},
				messages: {
					cid_rest: {
						required: county.id.required.msg,
						minlength: county.id.min.msg,
						maxlength: county.id.max.msg
					},
					cdesc_rest: {
						required: county.desc.required.msg,
						minlength: county.desc.min.msg,
						maxlength: county.desc.max.msg
					}
				}
});

function processCountyButtons(form)
{
	if (val.valid())
	{
		var _action = "";
		switch (buttonClicked)
		{
			case 'I':
				_action = 'I';
				break;
			case 'U':
				_action = 'U';
				break;
			case 'D':
				_action = 'D';
				break;
			case 'C':
				_action = 'C';
				break;
			case 'L':
				_action = 'L';
				break;
		}

		if (_action == 'I' || _action == 'U' || _action == 'D')
		{
			crloader.callMaintainWS($('#cid_rest').val(), $('#cdesc_rest').val(), _action);
		}
	}
	else
	{
		if (buttonClicked == 'C' || buttonClicked == 'L')
		{
			val.prepareForm();
			val.resetForm();
		}
	}

	buttonClicked = "";
	$('#cid_rest').val('');
	$('#cdesc_rest').val('');
};

$('#refreshcountiesREST').click(function() {
	crloader.callRefreshWS(13);
});

$('#insert_rest').click(function() {
	buttonClicked = 'I';
});

$('#list_rest').click(function() {
	buttonClicked = 'L';
	crloader.callFetchWS();
});

$('#update_rest').click(function() {
	buttonClicked = 'U';
});

$('#delete_rest').click(function() {
	buttonClicked = 'D';
});

$('#clear_rest').click(function() {
	$('#cid_rest').removeClass("ui-state-highlight");
	$('#cdesc_rest').removeClass("ui-state-highlight");
	buttonClicked = 'C';
});
</sec:authorize>

</script>
