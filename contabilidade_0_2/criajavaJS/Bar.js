

as = function (teste,bar,localsss){

	var text = '/** create by system gera-java version 1.0.0 '+dataAtualFormatada()+'*/\n';

text = text + "package com.qat.samples.sysmgmt.bar."+titleize(localsss)+";\n";
text = text + "import com.qat.framework.model.response.InternalResponse;\n";
text = text + "import com.qat.framework.model.response.InternalResultsResponse;\n";
text = text + "import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;\n";
text = text + "import com.qat.samples.sysmgmt.model.request.PagedInquiryRequest;\n";
text = text + "\n";
text = text + "/**\n";
text = text + " * The Interface "+bar+"BAR.. (Data Access Component - DAC)\n";
text = text + " */\n";
text = text + "public interface I"+bar+"BAR \n";
text = text + "{\n";
text = text + "\n";

for(i=0;i < teste.length;i++){

var nome = teste[i].classe.toLowerCase();
nomeM = titleize(teste[i].classe)
text = text + "	/**\n";
text = text + "	 * Fetch "+nome+" by id.\n";
text = text + "	 * \n";
text = text + "	 * @param request the request\n";
text = text + "* @return the internal results response\n";
text = text + "*/\n";
text = text + "	public "+nomeM+" fetch"+nomeM+"ById(FetchByIdRequest request);\n";
text = text + "\n";
text = text + "	/**\n";
text = text + "* Insert "+nome+".\n";
text = text + "* \n";
text = text + "* @param "+nome+" the "+nome+"\n";
text = text + "* \n";
text = text + "* @return the internal response\n";
text = text + "*/\n";
text = text + "	public InternalResponse insert"+nomeM+"("+nomeM+" "+nome+");\n";
text = text + "\n";
text = text + "	/**\n";
text = text + "* Update "+nome+".\n";
text = text + "* \n";
text = text + "* @param "+nome+" the "+nome+"\n";
text = text + "* \n";
text = text + "* @return the internal response\n";
text = text + "*/\n";
text = text + "	public InternalResponse update"+nomeM+"("+nomeM+" "+nome+");\n";
text = text + "\n";
text = text + "	/**\n";
text = text + "* Delete "+nome+".\n";
text = text + "* \n";
text = text + "* @param "+nome+" the "+nome+"\n";
text = text + "* \n";
text = text + "* @return the internal response\n";
text = text + "*/\n";
text = text + "	public InternalResponse delete"+nomeM+"ById("+nomeM+" "+nome+");\n";
text = text + "\n";
text = text + "	/**\n";
text = text + "* Delete all "+nome+"s.\n";
text = text + "* \n";
text = text + "* @return the internal response\n";
text = text + "*/\n";
text = text + "	public InternalResponse deleteAll"+nomeM+"s();\n";
text = text + "\n";
text = text + "	/**\n";
text = text + "* Fetch all "+nome+"s.\n";
text = text + "* \n";
text = text + "* @return the list< "+nome+">\n";
text = text + "*/\n";
text = text + "	public InternalResultsResponse<"+nomeM+"> fetchAll"+nomeM+"s("+nomeM+"  "+nome+");\n";
text = text + "\n";
text = text + "	/**\n";
text = text + "* Fetch "+nome+"s by request.\n";
text = text + "* \n";
text = text + "* @param request the request\n";
text = text + "* @return the internal results response\n";
text = text + "*/\n";
text = text + "	public InternalResultsResponse<"+nomeM+"> fetch"+nomeM+"sByRequest("+nomeM+"InquiryRequest request);\n";
text = text + "\n";

}
text = text + "}\n";
return text;
}
