
function titleize(text) {

    // Convertendo primeira letra em maiuscula.
    text = text.charAt(0).toUpperCase() + text.slice(1);

    for (var i = 0; i < text.length; i++) {
        if (text.charAt(i) ===" ") {

            // Convertendo letra após o ESPAÇO em maiuscula
            var charToUper = text.charAt(i+1).toUpperCase();

            // Colocando texto de antes do ESPAÇO na variável
            var sliceBegin = text.slice(0, (i+1));

            // colocando o texto de depois do ESPAÇO na variável
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


}
Form_Delete = function (teste,bar,local){


}
Form_Update = function (teste,bar,local){


}
