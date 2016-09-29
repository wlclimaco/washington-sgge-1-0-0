convertBanco = function(stipo,iTamanho){
var sReturn = "";
	switch(stipo.toLowerCase()) {
	    case "string":
	        sReturn = "character varying("+iTamanho+")"
	        break;
	    case "long":
	        sReturn = "bigint"
	        break;
	    case "double":
	        sReturn = "float"
	        break;
	       case "boolean":
	        sReturn = "boolean"
	        break;
	    default:
	    			sReturn = "integer";


	        break;
	}
	return sReturn;
}


convertModule = function(stipo,sCampo,list){
	var sReturn = "";
	b = new Date();
		switch(stipo.toLowerCase()) {
		    case "string":
		        sReturn = '"NATIVE INSERT UPDATE"'
		        break;
		    case "long":
		        sReturn = "a.getTime()"
		        break;
		    case "date":
		        sReturn = "a.getTime()"
		        break;
		     case "data":
		        sReturn = "a.getTime()"
		        break;
		    case "double":
		        sReturn = "new Double(10.00)"
		        break;
		    case "boolean":
		        sReturn = "true"
		        break;
		    case "integer":
		        sReturn = "100"
		        break;

		    default:
		    	if(stipo.indexOf('List') > 0){
		    			sReturn = "new ArrayList<"+list+">()"
		    	}else{
		    		sReturn = "10000"
		    	}

		        break;
		}
		return sReturn;
	}

convertModules = function(stipo,index,campo){
	var sReturn = "";
	b = new Date();
		switch(stipo.toLowerCase()) {
		    case "string":
		        sReturn = "'"+campo+'_'+index+"'"
		        break;
		    case "long":
		        sReturn = " "+b.getTime()+" "
		        break;
		    case "date":
		        sReturn = " "+b.getTime()+" "
		        break;
		     case "data":
		        sReturn = " "+b.getTime()+" "
		        break;
		    case "double":
		        sReturn = "10.00"
		        break;
		    case "boolean":
		        sReturn = "true"
		        break;
		    case "integer":
		        sReturn = ""+(1000 + index)+"" 
		        break;
		    case "doisValores":
		        sReturn = ""+(1000 + index)+"" 
		        break;
		    default:
		    	if(stipo.indexOf('List') > 0){
		    			sReturn = ""
		    	}else{
		    		sReturn = ""+(1000 + index)+"" 
		    	}

		        break;
		}
		return sReturn;
	}


	convertModuless = function(stipo,index,campo){
	var sReturn = "";
	b = new Date();
		switch(stipo.toLowerCase()) {
		    case "string":
		        sReturn = '"'+campo+'_'+index+' - "'
		        break;
		    case "long":
		        sReturn = "a.getTime()"
		        break;
		    case "date":
		        sReturn = "a.getTime()"
		        break;
		     case "data":
		        sReturn = "a.getTime()"
		        break;
		    case "double":
		        sReturn = "10.00"
		        break;
		    case "boolean":
		        sReturn = "true"
		        break;
		    case "integer":
		        sReturn = ""+(1000 + index)+"" 
		        break;
		    case "doisValores":
		        sReturn = ""+(1000 + index)+"" 
		        break;
		    default:
		    	if(stipo.indexOf('List') > 0){
		    			sReturn = ""
		    	}else{
		    		sReturn = ""+(1000 + index)+"" 
		    	}

		        break;
		}
		return sReturn;
	}



dataAtualFormatada = function (){
    var data = new Date();
    var dia = data.getDate();
    if (dia.toString().length == 1)
      dia = "0"+dia;
    var mes = data.getMonth()+1;
    if (mes.toString().length == 1)
      mes = "0"+mes;
    var ano = data.getFullYear();
    return dia+"/"+mes+"/"+ano+" "+data.getHours()+":"+data.getMinutes()+" : "+data.getSeconds();
}

titleize = function (text) {

	if((text !== " ") && (text !== "") && (text !== undefined) ){
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
	}
    return text;
}