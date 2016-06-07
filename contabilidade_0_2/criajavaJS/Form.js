Form_insert = function (oObject){
console.log(oObject);

    var requerido = "Favor preencher o campo solicitado";
	var text = '/** create by system gera-java version 1.0.0 '+dataAtualFormatada()+'*/\n';
    var div = '';
/*
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

text = text + '<section class="panel panel-default">\n';
text = text + '        <div class="panel-heading"><strong><span class="glyphicon glyphicon-th"></span> Wizard Form</strong></div>\n';
text = text + '        <div class="panel-body" >\n';
text = text + '         <form id="bookForm" method="post" class="form-horizontal">\n';
text = text + '            <div data-ui-wizard-form>\n';
text = text + '                <h1>Informação do Usuario</h1>\n';
text = text + '                <div>Informação do Usuario\n';

*/
var tamanho = 0;
var validator = "";
text = text + '<form id="installationForm" class="form-horizontal">\n';
for (var i = 0 ; i < oObject[0].tabs[0].field.table.length;i++){
    var form = oObject[0].tabs[0].field.table[i].field;
    if(form.tipo == 'input' ){
            if( form.class == 'hide'){
                div = div + '    <div class="'+form.class+'">\n';
            }else{
                div = div + '    <div class="col-sm-'+form.tamanho+' '+form.class+'">\n';
            }
        div = div + '        <div class="form-group">\n';
        div = div + '             <label for="exampleInputPassword1">'+form.label+'</label>\n';
        div = div + '             <input type="text" class="form-control col-sm-8 '+form.class+'" ng-model="'+form.ngmodel+'" name="'+form.campo+'"  placeholder="'+form.campo+'">\n';
        div = div + '        </div>\n';
        div = div + '    </div>\n';
        tamanho = tamanho + parseInt(form.tamanho,10);
    }else if (form.tipo == 'radio'){

            div = div + '<div class="col-sm-6">'+form.label+'</div>\n';
            div = div + '<div class="col-sm-6">\n';
            div = div + '    <div  >\n';
            for (y=0;y < form.domain.length ;y++){
                div = div + '        <label >\n';
                div = div + '            <input type="radio" id="'+form.domain[y].value+'" name="'+form.domain[y].value+'" value="'+form.domain[y].value+'" /> "'+form.domain[y].label+'" \n';
                div = div + '        </label> \n';
            }
            div = div + '    </div>\n';
            div = div + '</div>\n';
            tamanho = tamanho + parseInt(form.tamanho,10);
    }else if (form.tipo == 'select2'){


    div = div + '<label class="col-xs-1 control-label">Cnae</label>\n';
    div = div + '<div class="col-xs-'+form.tamanho+'">\n';
    div = div + '    <select id="mySel" name="cnae" class="form-control">\n';
    div = div + '        <option> teste 001</option>\n';
    div = div + '        <option> teste 002</option>\n';
    div = div + '        <option> teste 003</option>\n';
    div = div + '        <option> teste 004</option>\n';
    div = div + '        <option> teste 005</option>\n';             
    div = div + '    </select><br>\n';
    div = div + '</div>\n';
    tamanho = tamanho + parseInt(form.tamanho,10);
}
    if(tamanho > 11){
        tamanho = 0;
        text = text + '<div class="row">\n';
        text = text + ' '+div+'\n';
        text = text + '</div>\n';
        div = "";
    }
if(form.requerid == true){
    validator = validator + '\n';
    validator = validator + ' '+form.campo+': {\n';
    validator = validator + '    validators: {\n';
    validator = validator + '        notEmpty: {\n';
    validator = validator + '            message: "'+requerido+'"\n';
    validator = validator + '        }\n';
    validator = validator + '    }\n';
    validator = validator + '},\n';
}

}
text = text + '<div class="form-group">\n';
text = text + '    <div class="col-lg-offset-3 col-lg-3">\n';
text = text + '        <button type="submit" class="btn btn-primary">Submit</button>\n';
text = text + '    </div>\n';
text = text + '</div>\n';
text = text + '</form>\n';
text = text + '<script>\n';
text = text + '$(document).ready(function() {\n';
/*
text = text + '    function adjustIframeHeight() {\n';
text = text + "        var $body   = $('body'),\n";
text = text + "                $iframe = $body.data('iframe.fv');\n";
text = text + '        if ($iframe) {\n';
text = text + '            // Adjust the height of iframe\n';
text = text + '            $iframe.height($body.height());\n';
text = text + '        }\n';
text = text + '    }\n';

text = text + '$("#shippingForm")\n';
text = text + '    .on("init.form.fv", function(e, data) {\n';
text = text + '        //console.log(data);\n';
text = text + '    })\n';
text = text + '    .formValidation({\n';
text = text + "       message: 'This value is not valid',\n";
text = text + '        icon: {\n';
text = text + "            valid: 'glyphicon glyphicon-ok',\n";
text = text + "            invalid: 'glyphicon glyphicon-remove',\n";
text = text + "            validating: 'glyphicon glyphicon-refresh'\n";
text = text + '        },\n';
text = text + '        fields: {\n';
text = text + ' '+ validator +'\n';
text = text + 'senderCity: {\n';
text = text + 'validators: {\n';
text = text + '            notEmpty: {\n';
text = text + '                message: "The city is required"\n';
text = text + '                }\n';
text = text + '            }\n';
text = text + '        }\n';
text = text + '    }\n';
text = text + '})\n';
text = text + '.on("added.field.fv", function(e, data) {\n';
text = text + "console.log('Added element --> ', data.field, data.element, data.options);\n";
text = text + '})\n';
text = text + '});\n';
text = text + '</script>\n';*/
text = text + '\n';

return text;
}
Form_Delete = function (teste,bar,local){
	var text = '/** create by system gera-java version 1.0.0 '+dataAtualFormatada()+'*/\n';

}
Form_Update = function (teste,bar,local){
	var text = '/** create by system gera-java version 1.0.0 '+dataAtualFormatada()+'*/\n';

}
