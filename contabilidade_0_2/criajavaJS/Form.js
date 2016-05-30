
function titleize(text) {

    // Convertendo primeira letra em maiuscula.
    text = text.charAt(0).toUpperCase() + text.slice(1);

    for (var i = 0; i < text.length; i++) {
        if (text.charAt(i) ===" ") {

            // Convertendo letra ap�s o ESPA�O em maiuscula
            var charToUper = text.charAt(i+1).toUpperCase();

            // Colocando texto de antes do ESPA�O na vari�vel
            var sliceBegin = text.slice(0, (i+1));

            // colocando o texto de depois do ESPA�O na vari�vel
            var sliceEnd = text.slice(i + 2);

            // Juntando tudo
            text = sliceBegin + charToUper + sliceEnd;

        } else {

            // NAO CONSIGO PENSAR EM COMO TRANSFORMAR O RESTANTE DAS LETRAS EM MINUSCULA
        }
    }
    return text;
}

Form_insert = function (teste,bar,local){


	var text = '/** create by system gera-java version 1.0.0 '+dataAtualFormatada()+'*/\n';
text = text + '<link rel="stylesheet" href="dist/css/formValidation.css"/>
text = text + '    <link rel="stylesheet" href="http://cdnjs.cloudflare.com/ajax/libs/select2/3.4.8/select2.css"/>
text = text + '    <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/select2/3.5.0/select2-bootstrap.min.css" />
text = text + '
text = text + '    
text = text + '    <script src="dist/js/select2.min.js"></script>
text = text + '    <script src="dist/js/formValidation.js"></script>
text = text + '    <script src="dist/js/framework/bootstrap.js"></script>
text = text + '
text = text + '
text = text + '
text = text + '<!-- Include Select2 CSS -->
text = text + '
text = text + '<!-- CSS to make Select2 fit in with Bootstrap 3.x -->
text = text + '
text = text + '
text = text + '<script src="vendor/bootstrap/js/bootstrap.js"></script>
text = text + '<!-- Include Select2 JS -->
text = text + '
text = text + '    <script src="dist/js/formValidation.js"></script>
text = text + '    <script src="dist/js/framework/bootstrap.js"></script>
text = text + '
text = text + '<style type="text/css">
text = text + '#select2Form .form-control-feedback {
text = text + '    /* To make the feedback icon visible */
text = text + '    z-index: 100;
text = text + '}
text = text + '
text = text + '.content-container.animate-fade-up.teste.ng-scope {
text = text + '    margin-top: 130px;
text = text + '}
text = text + '</style>
text = text + '
text = text + '
text = text + '<form id="html5Form" method="post" class="form-horizontal"
text = text + '    data-fv-framework="bootstrap"
text = text + '    data-fv-message="This value is not valid"
text = text + '    data-fv-feedbackicons-valid="glyphicon glyphicon-ok"
text = text + '    data-fv-feedbackicons-invalid="glyphicon glyphicon-remove"
text = text + '    data-fv-feedbackicons-validating="glyphicon glyphicon-refresh">
text = text + '
text = text + '    <div class="form-group">
text = text + '        <label class="col-xs-3 control-label">Username</label>
text = text + '       <div class="col-xs-5">
text = text + '            <input type="text" class="form-control" name="username"
text = text + '                data-fv-message="The username is not valid"
text = text + '                required data-fv-notempty-message="The username is required and cannot be empty"
text = text + '                pattern="^[a-zA-Z0-9]+$" data-fv-regexp-message="The username can only consist of alphabetical, number" />
text = text + '        </div>
text = text + '    </div>
text = text + '
text = text + '    <div class="form-group">
text = text + '        <label class="col-xs-3 control-label">Email address</label>
text = text + '        <div class="col-xs-5">
text = text + '            <input class="form-control" name="email" type="email"
text = text + '                required
text = text + '                data-fv-emailaddress-message="The input is not a valid email address" />
text = text + '        </div>
text = text + '    </div>
text = text + '
text = text + '    <div class="form-group">
text = text + '        <label class="col-xs-3 control-label">Website</label>
text = text + '        <div class="col-xs-5">
text = text + '            <input class="form-control" name="website" type="url"
text = text + '               required
text = text + '               data-fv-uri-message="The input is not a valid website address" />
text = text + '        </div>
text = text + '    </div>
text = text + '
text = text + '    <div class="form-group">
text = text + '        <label class="col-xs-3 control-label">Fav color</label>
text = text + '        <div class="col-xs-3">
text = text + '            <input class="form-control" name="color" type="color"
text = text + '               required
text = text + '               data-fv-hexcolor-message="The input is not a valid color code" />
text = text + '        </div>
text = text + '    </div>
text = text + '
text = text + '    <div class="form-group">
text = text + '        <label class="col-xs-3 control-label">Age</label>
text = text + '        <div class="col-xs-2">
text = text + '            <input type="text" class="form-control" name="age"
text = text + '                required
text = text + '
text = text + '                min="10"
text = text + '                data-fv-greaterthan-inclusive="true"
text = text + '                data-fv-greaterthan-message="The input must be greater than or equal to 10"
text = text + '
text = text + '                max="100"
text = text + '                data-fv-lessthan-inclusive="false"
text = text + '                data-fv-lessthan-message="The input must be less than 100" />
text = text + '        </div>
text = text + '    </div>
text = text + '</form>
text = text + '
text = text + '<script>
text = text + '$(document).ready(function() {
text = text + '    $('#html5Form').formValidation();
text = text + '});
text = text + '</script>
text = text + '
}
Form_Delete = function (teste,bar,local){
	var text = '/** create by system gera-java version 1.0.0 '+dataAtualFormatada()+'*/\n';

}
Form_Update = function (teste,bar,local){
	var text = '/** create by system gera-java version 1.0.0 '+dataAtualFormatada()+'*/\n';

}
