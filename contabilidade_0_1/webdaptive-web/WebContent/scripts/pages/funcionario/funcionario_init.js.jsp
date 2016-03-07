<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>

<script type="text/javascript">
/**
 * @namespace qat.pages.location
 * @description The init namespace for the Location Page.
 */

$(document).ready(function()
{
	var GiftModel = function(funcionario) {
	    var self = this;

	    self.funcionario = ko.observable(new funcionarioModule.FUNCIONARIO(funcionario));


		$.ajax({
	            url: WDHost + "qat-webdaptive/api/funcionario/add",
	            dataType: "json",
	            contentType: 'application/json',
	            type: "POST",
	            data: ko.toJSON( {funcionario :  self.funcionario()}),
	            processdata: true,

	            beforeSend: function () {
	             //   $.mobile.loading('show');
	            },

	            error: function (xhr, textStatus, errorThrown) {
	                console.log('Sorry!');
	            },

	            success: function (data) {

	               // $.mobile.loading('hide');
	                if (data.result!= '') {
	                  //  self.vendors(data.result);

	console.log(data)

	                } else {
						console.log(data)
	                //    self.vendors({something});

	                }
	            }
	        });

		/**
			$.ajax(WDHost + "qat-webdaptive/api/empresa/add", {
	        data: {
	            empresa: ko.toJSON(self.empresa())
	        },
	        type: "POST",
	        dataType: 'json',
	        success: function(data) {
	           console.log(data)
	        }
	    });
	**/

	};

	var viewModel = new GiftModel(qat.pages.pessoa.createTeste());
	ko.applyBindings(viewModel);

	// Activate jQuery Validation
	$("form").validate({ submitHandler: viewModel.save });



	});
</script>