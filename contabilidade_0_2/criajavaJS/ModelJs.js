function titleize(text) {

    // Convertendo primeira letra em maiuscula.
    text = text.charAt(0).toUpperCase() + text.slice(1);

    for (var i = 0; i < text.length; i++) {
        if (text.charAt(i) === " ") {

            // Convertendo letra ap�s o ESPA�O em maiuscula
            var charToUper = text.charAt(i + 1).toUpperCase();

            // Colocando texto de antes do ESPA�O na vari�vel
            var sliceBegin = text.slice(0, (i + 1));

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


function titleize2(text) {

    // Convertendo primeira letra em maiuscula.
    text = text.charAt(0).toLowerCase() + text.slice(1);

    for (var i = 0; i < text.length; i++) {
        if (text.charAt(i) === " ") {

            // Convertendo letra ap�s o ESPA�O em maiuscula
            var charToUper = text.charAt(i + 1).toLowerCase();

            // Colocando texto de antes do ESPA�O na vari�vel
            var sliceBegin = text.slice(0, (i + 1));

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


modelJS = function(oField, name) {
    var text = '/** create by system gera-java version 1.0.0 ' + dataAtualFormatada() + '*/\n';
    text = text + "class " + titleize(name) + " {;\n";
    text = text + "\n";
    text = text + "     constructor (";
    for (i = 0; i < oField.length; i++) {
        if ((oField[i].field.xml == true) && ( i < (oField.length -1))) {
            text = text + "" + oField[i].field.campo + ", "
        } else {
            text = text + "" + oField[i].field.campo + ")";
        }
    }
    text = text + " {\n"
    text = text + "\n";
    for (i = 0; i < oField.length; i++) {
        if (oField[i].field.xml == true) {
            text = text + "     this._" + oField[i].field.campo + ";\n";
        }
    }
    text = text + " }\n"
    
    for (i = 0; i < oField.length; i++) {
        text = text + "\n";
        if (oField[i].field.xml == true) {
            text = text + "     get " + oField[i].field.campo + "() {\n";
            text = text + "          return this._" + oField[i].field.campo + ";\n";
            text = text + "     }\n";
        }
    }

    
    for (i = 0; i < oField.length; i++) {
        text = text + "\n";
        if (oField[i].field.xml == true) {
            text = text + "     set " + oField[i].field.campo + "("+oField[i].field.campo+") {\n";
            text = text + "          this._" + oField[i].field.campo + " = "+oField[i].field.campo+";\n";
            text = text + "     }\n";
        }
    }
  
    text = text + '}\n';


    return text;
}