<%@ taglib prefix='sec'
	uri='http://www.springframework.org/security/tags'%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt'%>

	<script type="text/javascript">

$(document).ready(function()
{


		var viewModel = {

				empresa :  ko.observable(new empresaModule.EMPRESA({}))



};
				self.addPhone = function() {
					debugger
					viewModel.empresa.telefone().push({
						ddd: "",
						numero:"",
						telefoneTypeEnumValue:""
					})
				};

				self.removePhone = function(phone) {

				};
				self.addSocio = function() {

				};

				self.removePhone = function(phone) {

				};

				self.removePhone = function(socio) {

				};

			ko.applyBindings(viewModel);
			debugger
			console.log(viewModel)




});
</script>
