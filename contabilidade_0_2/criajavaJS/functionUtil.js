convertBanco = function(stipo,iTamanho){
var sReturn = "";
	switch(stipo) {
	    case "String":
	        sReturn = "character varying("+iTamanho+")"
	        break;
	    case "Long":
	        sReturn = "bigint"
	        break;
	    default:
	        sReturn = stipo.toLowerCase();
	        break;
	}
	return sReturn;
}