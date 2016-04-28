
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

domain = function (teste,bar,local){

	var text = '/** create by system gera-java version 1.0.0 '+dataAtualFormatada()+'*/\n';
text = text + "package com.qat.samples.sysmgmt.bac."+titleize(local)+";\n";
text = text + "import com.qat.framework.model.response.InternalResultsResponse;\n";
text = text + "import com.qat.samples.sysmgmt.model.County;\n";
text = text + "import com.qat.samples.sysmgmt.model.request.CountyMaintenanceRequest;\n";
text = text + "import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;\n";
text = text + "import com.qat.samples.sysmgmt.util.model.request.PagedInquiryRequest;\n";
text = text + "import com.qat.samples.sysmgmt.util.model.request.RefreshRequest;\n";
text = text + "\n";
text = text + "/**\n";
text = text + " * The Interface I"+bar+"BAC. (Business Area Component - BAC)\n";
text = text + " */\n";
text = text + "public interface I"+bar+"BAC\n";
text = text + "{\n";
text = text + "\n";

text = text + "\n";

for(i=0;i < teste.length;i++){

var nome = teste[i].classe.toLowerCase();
nomeM = titleize(nome)
text = text + "\n";
text = text + '//===================================### '+nomeM.toUpperCase()+' ####======================================\n';
text = text + "	/**\n";
text = text + "\n";
text = text + "	/**\n";
text = text + "	 * Insert "+nome+".\n";
text = text + "	 *\n";
text = text + "* @param request the "+nome+" maintenance request\n";
text = text + "*\n";
text = text + "* @return the internal results response\n";
text = text + "*/\n";
text = text + "	public InternalResultsResponse<"+nomeM+"> insert"+nomeM+"("+nomeM+"MaintenanceRequest request);\n";
text = text + "\n";
text = text + "	/**\n";
text = text + "* Update "+nome+".\n";
text = text + "*\n";
text = text + "* @param request the "+nome+" maintenance request\n";
text = text + "*\n";
text = text + "* @return the internal results response\n";
text = text + "*/\n";
text = text + "	public InternalResultsResponse<"+nomeM+"> update"+nomeM+"("+nomeM+"MaintenanceRequest request);\n";
text = text + "\n";
text = text + "	/**\n";
text = text + "* Delete "+nome+".\n";
text = text + "*\n";
text = text + "* @param request the "+nome+" maintenance request\n";
text = text + "*\n";
text = text + "* @return the internal results response\n";
text = text + "*/\n";
text = text + "	public InternalResultsResponse<"+nomeM+"> delete"+nomeM+"("+nomeM+"MaintenanceRequest request);\n";
text = text + "\n";
text = text + "	/**\n";
text = text + "* Refresh "+nome+"s.\n";
text = text + "*\n";
text = text + "* @param request containing the number to refresh with and whether to return the result\n";
text = text + "*/\n";
text = text + "	public InternalResultsResponse<"+nomeM+"> refresh"+nomeM+"s(RefreshRequest request);\n";
text = text + "\n";
text = text + "	/**\n";
text = text + "* Fetch "+nome+" by id.\n";
text = text + "*\n";
text = text + "* @param request the request\n";
text = text + "* @return the internal results response\n";
text = text + "*/\n";
text = text + "	public InternalResultsResponse<"+nomeM+"> fetch"+nomeM+"ById(FetchByIdRequest request);\n";
text = text + "\n";
text = text + "	/**\n";
text = text + "* Fetch all "+nome+"s.\n";
text = text + "*\n";
text = text + "* @return the internal results response< "+nome+">\n";
text = text + "*/\n";
text = text + "	public InternalResultsResponse<"+nomeM+"> fetchAll"+nomeM+"s("+nomeM+"  "+nome+");\n";
text = text + "\n";
text = text + "	/**\n";
text = text + "* Fetch "+nome+"s by request.\n";
text = text + "*\n";
text = text + "* @param request the request\n";
text = text + "* @return the internal results response\n";
text = text + "*/\n";
text = text + "	public InternalResultsResponse<"+nomeM+"> fetch"+nomeM+"sByRequest("+nomeM+"InquiryRequest request);\n";

text = text + "\n";

}
text = text + "}\n";
return text;
}
