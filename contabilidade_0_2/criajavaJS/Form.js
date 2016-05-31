
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

Form_insert = function (oObject){


	var text = '/** create by system gera-java version 1.0.0 '+dataAtualFormatada()+'*/\n';
text = text + '<link rel="stylesheet" href="dist/css/formValidation.css"/>\n';
text = text + '    <link rel="stylesheet" href="http://cdnjs.cloudflare.com/ajax/libs/select2/3.4.8/select2.css"/>\n';
text = text + '    <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/select2/3.5.0/select2-bootstrap.min.css" />\n';
text = text + '\n';
text = text + ' \n';
text = text + '    <script src="dist/js/select2.min.js"></script>\n';
text = text + '    <script src="dist/js/formValidation.js"></script>\n';
text = text + '    <script src="dist/js/framework/bootstrap.js"></script>\n';
text = text + '\n';
text = text + '\n';
text = text + '\n';
text = text + '<!-- Include Select2 CSS -->\n';
text = text + '\n';
text = text + '<!-- CSS to make Select2 fit in with Bootstrap 3.x -->\n';
text = text + '\n';
text = text + '\n';
text = text + '<script src="vendor/bootstrap/js/bootstrap.js"></script>\n';
text = text + '<!-- Include Select2 JS -->\n';
text = text + '\n';
text = text + '    <script src="dist/js/formValidation.js"></script>\n';
text = text + '    <script src="dist/js/framework/bootstrap.js"></script>\n';
text = text + '\n';
text = text + '<style type="text/css">\n';
text = text + '#select2Form .form-control-feedback {\n';
text = text + '    /* To make the feedback icon visible */\n';
text = text + '    z-index: 100;\n';
text = text + '}\n';
text = text + '\n';
text = text + '.content-container.animate-fade-up.teste.ng-scope {\n';
text = text + '    margin-top: 130px;\n';
text = text + '}\n';
text = text + '</style>\n';
text = text + '\n';
text = text + '<!-- Include Bootstrap Wizard -->\n';
text = text + '<script src="//cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap-wizard/1.2/jquery.bootstrap.wizard.min.js"></script>\n';

text = text + '<style type="text/css">\n';
text = text + '#installationForm .tab-content {\n';
text = text + '    margin-top: 20px;\n';
text = text + '}\n';
text = text + '</style>\n';

text = text + '<form id="installationForm" class="form-horizontal">\n';
text = text + '    <ul class="nav nav-pills">\n';
text = text + '        <li class="active"><a href="#basic-tab" data-toggle="tab">Site information</a></li>\n';
text = text + '        <li><a href="#database-tab" data-toggle="tab">Database</a></li>\n';
text = text + '    </ul>\n';

text = text + '    <div class="tab-content">\n';
text = text + '        <!-- First tab -->\n';
text = text + '        <div class="tab-pane active" id="basic-tab">\n';
text = text + '            <div class="form-group">\n';
text = text + '                <label class="col-xs-3 control-label">Site name</label>\n';
text = text + '                <div class="col-xs-5">\n';
text = text + '                    <input type="text" class="form-control" name="name" />\n';
text = text + '                </div>\n';
text = text + '            </div>\n';

text = text + '</form>\n';

text = text + '<div class="modal fade" id="completeModal" tabindex="-1" role="dialog">\n';
text = text + '    <div class="modal-dialog modal-sm">\n';
text = text + '        <div class="modal-content">\n';
text = text + '            <div class="modal-header">\n';
text = text + '                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>\n';
text = text + '                <h4 class="modal-title">Complete</h4>\n';
text = text + '            </div>\n';

text = text + '            <div class="modal-body">\n';
text = text + '                <p class="text-center">The installation is completed</p>\n';
text = text + '            </div>\n';

text = text + '            <div class="modal-footer">\n';
text = text + '                <button type="button" class="btn btn-success" data-dismiss="modal">Visit the website</button>\n';
text = text + '            </div>\n';
text = text + '        </div>\n';
text = text + '    </div>\n';
text = text + '</div>\n';

text = text + '<script>\n';
text = text + '$(document).ready(function() {\n';

text = text + '    function adjustIframeHeight() {\n';
text = text + "        var $body   = $('body'),\n";
text = text + "                $iframe = $body.data('iframe.fv');\n";
text = text + '        if ($iframe) {\n';
text = text + '            // Adjust the height of iframe\n';
text = text + '            $iframe.height($body.height());\n';
text = text + '        }\n';
text = text + '    }\n';

text = text + "    $('#installationForm')\n";
text = text + '        .formValidation({\n';
text = text + "            framework: 'bootstrap',\n";
text = text + '            icon: {\n';
text = text + "                valid: 'glyphicon glyphicon-ok',\n";
text = text + "                invalid: 'glyphicon glyphicon-remove',\n";
text = text + "                validating: 'glyphicon glyphicon-refresh'\n";
text = text + '            },\n';
text = text + '            // This option will not ignore invisible fields which belong to inactive panels\n';
text = text + "            excluded: ':disabled',\n";
text = text + '            fields: {\n';
text = text + '                name: {\n';
text = text + '                    validators: {\n';
text = text + '                        notEmpty: {\n';
text = text + "                            message: 'The site name is required'\n";
text = text + '                        }\n';
text = text + '                    }\n';
text = text + '                }\n';
text = text + '        })\n';
text = text + '        .bootstrapWizard({\n';
text = text + "            tabClass: 'nav nav-pills',\n";
text = text + '            onTabClick: function(tab, navigation, index) {\n';
text = text + '                return validateTab(index);\n';
text = text + '            },\n';
text = text + '            onNext: function(tab, navigation, index) {\n';
text = text + "                var numTabs    = $('#installationForm').find('.tab-pane').length,\n";
text = text + '                    isValidTab = validateTab(index - 1);\n';
text = text + '                if (!isValidTab) {\n';
text = text + '                    return false;\n';
text = text + '                }\n';

text = text + '                if (index === numTabs) {\n';
text = text + '                    // We are at the last tab\n';

text = text + '                    // Uncomment the following line to submit the form using the defaultSubmit() method\n';
text = text + "                    // $('#installationForm').formValidation('defaultSubmit');\n";

text = text + '                    // For testing purpose\n';
text = text + "                    $('#completeModal').modal();\n";
text = text + '                }\n';

text = text + '                return true;\n';
text = text + '            },\n';
text = text + '            onPrevious: function(tab, navigation, index) {\n';
text = text + '                return validateTab(index + 1);\n';
text = text + '            },\n';
text = text + '            onTabShow: function(tab, navigation, index) {\n';
text = text + '                // Update the label of Next button when we are at the last tab\n';
text = text + "                var numTabs = $('#installationForm').find('.tab-pane').length;\n";
text = text + "                $('#installationForm')\n";
text = text + "                    .find('.next')\n";
text = text + "                       .removeClass('disabled')    // Enable the Next button\n";
text = text + "                        .find('a')\n";
text = text + "                        .html(index === numTabs - 1 ? 'Install' : 'Next');\n";

text = text + "                // You don't need to care about it\n";
text = text + '                // It is for the specific demo\n';
text = text + '                adjustIframeHeight();\n';
text = text + '            }\n';
text = text + '        });\n';

text = text + '    function validateTab(index) {\n';
text = text + "        var fv   = $('#installationForm').data('formValidation'), // FormValidation instance\n";
text = text + '            // The current tab\n';
text = text + "            $tab = $('#installationForm').find('.tab-pane').eq(index);\n";

text = text + '        // Validate the container\n';
text = text + '        fv.validateContainer($tab);\n';

text = text + '        var isValidStep = fv.isValidContainer($tab);\n';
text = text + '        if (isValidStep === false || isValidStep === null) {\n';
text = text + '            // Do not jump to the target tab\n';
text = text + '            return false;\n';
text = text + '        }\n';

text = text + '        return true;\n';
text = text + '    }\n';
text = text + '});\n';
text = text + '</script>\n';
text = text + '\n';

return text;
}
Form_Delete = function (teste,bar,local){
	var text = '/** create by system gera-java version 1.0.0 '+dataAtualFormatada()+'*/\n';

}
Form_Update = function (teste,bar,local){
	var text = '/** create by system gera-java version 1.0.0 '+dataAtualFormatada()+'*/\n';

}
