<%@ taglib prefix='sec'
	uri='http://www.springframework.org/security/tags'%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt'%>

	<script type="text/javascript">

$(document).ready(function()
{

	var initialData = [
    { firstName: "Danny", lastName: "LaRusso", phones: [
        { type: "Mobile", number: "(555) 121-2121" },
        { type: "Home", number: "(555) 123-4567"}]
    },
    { firstName: "Sensei", lastName: "Miyagi", phones: [
        { type: "Mobile", number: "(555) 444-2222" },
        { type: "Home", number: "(555) 999-1212"}]
    },
	{phones: [
        { type: "Mobile", number: "(555) 121-2121" },
        { type: "Home", number: "(555) 123-4567"}]
    },
	{ socios :[
		{nome:"washington",cpf:"057.901.676-59",cota:"80"},
		{nome:"livia",cpf:"058.900.600-60",cota:"20"}
	]

	}
];

var ContactsModel = function(contacts) {
    var self = this;

    phones = ko.observableArray([{type: "",number: ""}])
	socios = ko.observableArray([{nome: "",cpf: "",cota: ""}])

    self.addPhone = function() {
        phones.push({
            type: "",
            number: ""
        });
    };

    self.removePhone = function(phone) {
        $.each(self.phones, function() { this.phones.remove(phone) })
    };
	self.addSocio = function() {
        socios.push({
            nome: "",
            cpf: "",
			cota: ""
        });
    };

    self.removePhone = function(phone) {
        $.each(self.phones, function() { this.phones.remove(phone) })
    };

	self.removePhone = function(socio) {
        $.each(self.socios, function() { this.socios.remove(socio) })
    };
};

ko.applyBindings(new ContactsModel(initialData));
});
</script>
