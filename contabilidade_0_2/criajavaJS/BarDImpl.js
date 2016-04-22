
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

BarImpl = function (teste,bar,local){

	var text = "";
text = text + "package com.qat.samples.sysmgmt.bar.mybatis."+titleize(local)+";\n";
text = text + "\n";
text = text + "\n";
text = text + "import org.mybatis.spring.support.SqlSessionDaoSupport;\n";
text = text + "import org.springframework.stereotype.Repository;\n";
text = text + "\n";
text = text + "import com.qat.framework.model.response.InternalResponse;\n";
text = text + "import com.qat.framework.model.response.InternalResultsResponse;\n";
text = text + "import com.qat.framework.util.MyBatisBARHelper;\n";
text = text + "import com.qat.samples.sysmgmt.bar.IComprasBAR;\n";
text = text + "import com.qat.samples.sysmgmt.bar.mybatis.delegate.PagedResultsBARD;\n";
text = text + "import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;\n";
text = text + "import com.qat.samples.sysmgmt.util.model.request.PagedInquiryRequest;\n";
text = text + "\n";
text = text + "/**\n";
text = text + " * The Class CountyBARImpl. (Business Access Repository - BAR)\n";
text = text + " */\n";
text = text + "@Repository\n";
text = text + "public class "+bar+"BARImpl extends SqlSessionDaoSupport implements I"+bar+"BAR\n";
text = text + "{\n";	
text = text + "\n";
text = text + "/** The Constant ZERO. */\n";
text = text + "	private static final int ZERO = 0;\n";
text = text + "\n";
for(i=0;i < teste.length;i++){
	

var nome = teste[i].classe.toLowerCase();
nomeM = titleize(nome);
text = text + "\n";
text = text + '///===================================### '+nomeM.toUpperCase()+' ####======================================\n';
text = text + '/** The Constant NAMESPACE. */\n';
text = text + 'private static final String NAMESPACE_'+nomeM.toUpperCase()+' = "'+nomeM+'Map.";\n';
text = text + '\n';
text = text + '/** The Constant STMT_INSERT_'+nomeM.toUpperCase()+'. */\n';
text = text + 'private static final String STMT_INSERT_'+nomeM.toUpperCase()+' = NAMESPACE_'+nomeM.toUpperCase()+' + "insert'+nomeM+'";\n';
text = text + '\n';
text = text + '/** The Constant STMT_UPDATE_'+nomeM.toUpperCase()+'. */\n';
text = text + 'private static final String STMT_UPDATE_'+nomeM.toUpperCase()+' = NAMESPACE_'+nomeM.toUpperCase()+' + "update'+nomeM+'";\n';
text = text + '\n';
text = text + '/** The Constant STMT_DELETE_'+nomeM.toUpperCase()+'. */\n';
text = text + 'private static final String STMT_DELETE_'+nomeM.toUpperCase()+' = NAMESPACE_'+nomeM.toUpperCase()+' + "delete'+nomeM+'ById";\n';
text = text + '\n';
text = text + '	/** The Constant STMT_DELETE_'+nomeM.toUpperCase()+'_ALL. */\n';
text = text + '	private static final String STMT_DELETE_'+nomeM.toUpperCase()+'_ALL = NAMESPACE_'+nomeM.toUpperCase()+' + "deleteAll'+nomeM+'s";\n';
text = text + '\n';
text = text + '	/** The Constant STMT_FETCH_'+nomeM.toUpperCase()+'. */\n';
text = text + '	private static final String STMT_FETCH_'+nomeM.toUpperCase()+' = NAMESPACE_'+nomeM.toUpperCase()+' + "fetch'+nomeM+'ById";\n';
text = text + '\n';
text = text + '	/** The Constant STMT_FETCH_'+nomeM.toUpperCase()+'_ALL. */\n';
text = text + '	private static final String STMT_FETCH_'+nomeM.toUpperCase()+'_ALL = NAMESPACE_'+nomeM.toUpperCase()+' + "fetchAll'+nomeM+'s";\n';
text = text + '\n';
text = text + '	/** The Constant STMT_FETCH_'+nomeM.toUpperCase()+'_COUNT. */\n';
text = text + '	private static final String STMT_FETCH_'+nomeM.toUpperCase()+'_COUNT = NAMESPACE_'+nomeM.toUpperCase()+' + "fetch'+nomeM+'RowCount";\n';
text = text + '\n';
text = text + '	/** The Constant STMT_FETCH_'+nomeM.toUpperCase()+'_ALL_REQUEST. */\n';
text = text + '	private static final String STMT_FETCH_'+nomeM.toUpperCase()+'_ALL_REQUEST = NAMESPACE_'+nomeM.toUpperCase()+' + "fetchAll'+nomeM+'sRequest";\n';
}

for(i=0;i < teste.length;i++){

var nome = teste[i].classe.toLowerCase();
nomeM = titleize(nome)
text = text + "\n";
text = text + '//===================================### '+nomeM.toUpperCase()+' ####======================================\n';
text = text + "	/**\n";
text = text + "/*\n";
text = text + " * (non-Javadoc)\n";
text = text + " * @see com.qat.samples.sysmgmt.base.bar.I"+nomeM+"BAR#insert"+nomeM+"(com.qat.samples.sysmgmt.base.model."+nomeM+")\n";
text = text + " */\n";
text = text + "@Override\n";
text = text + "public InternalResponse insert"+nomeM+"("+nomeM+" county)\n";
text = text + "{\n";
text = text + "	InternalResponse response = new InternalResponse();\n";
text = text + "	MyBatisBARHelper.doInsert(getSqlSession(), STMT_INSERT_"+nomeM.toUpperCase()+", county, response);\n";
text = text + "	return response;\n";
text = text + "}\n";
text = text + '\n';
text = text + "/*\n";
text = text + " * (non-Javadoc)\n";
text = text + " * @see com.qat.samples.sysmgmt.base.bar.I"+nomeM+"BAR#update"+nomeM+"(com.qat.samples.sysmgmt.base.model."+nomeM+")\n";
text = text + " */\n";
text = text + "@Override\n";
text = text + "public InternalResponse update"+nomeM+"("+nomeM+" county)\n";
text = text + "{\n";
text = text + "	InternalResponse response = new InternalResponse();\n";
text = text + "	MyBatisBARHelper.doUpdate(getSqlSession(), STMT_UPDATE_"+nomeM.toUpperCase()+", county, response);\n";
text = text + "	return response;\n";
text = text + "}\n";
text = text + '\n';
text = text + "/*\n";
text = text + " * (non-Javadoc)\n";
text = text + " * @see com.qat.samples.sysmgmt.base.bar.I"+nomeM+"BAR#delete"+nomeM+"(com.qat.samples.sysmgmt.base.model."+nomeM+")\n";
text = text + " */\n";
text = text + "@Override\n";
text = text + "public InternalResponse delete"+nomeM+"ById("+nomeM+" county)\n";
text = text + "{\n";
text = text + "	InternalResponse response = new InternalResponse();\n";
text = text + "	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_"+nomeM.toUpperCase()+", county, response);\n";
text = text + "	return response;\n";
text = text + "}\n";
text = text + '\n';
text = text + "/*\n";
text = text + " * (non-Javadoc)\n";
text = text + " * @see com.qat.samples.sysmgmt.base.bar.I"+nomeM+"BAR#deleteAll"+nomeM+"s()\n";
text = text + " */\n";
text = text + "@Override\n";
text = text + "public InternalResponse deleteAll"+nomeM+"s()\n";
text = text + "{\n";
text = text + "	InternalResponse response = new InternalResponse();\n";
text = text + "	MyBatisBARHelper.doRemove(getSqlSession(), STMT_DELETE_"+nomeM.toUpperCase()+"_ALL, response);\n";
text = text + "	return response;\n";
text = text + "}\n";
text = text + '\n';
text = text + "/*\n";
text = text + " * (non-Javadoc)\n";
text = text + " * @see\n";
text = text + " * com.qat.samples.sysmgmt.bar.I"+nomeM+"BAR#fetch"+nomeM+"ById(com.qat.samples.sysmgmt.model.request.FetchByIdRequest)\n";
text = text + " */\n";
text = text + "@Override\n";
text = text + "public InternalResultsResponse<"+nomeM+"> fetch"+nomeM+"ById(FetchByIdRequest request)\n";
text = text + "{\n";
text = text + "	InternalResultsResponse<"+nomeM+"> response = new InternalResultsResponse<"+nomeM+">();\n";
text = text + "	response.addResult(("+nomeM+")MyBatisBARHelper.doQueryForObject(getSqlSession(), STMT_FETCH_"+nomeM.toUpperCase()+",\n";
text = text + "			request.getFetchId()));\n";
text = text + "	return response;\n";
text = text + "}\n";
text = text + '\n';
text = text + "/*\n";
text = text + " * (non-Javadoc)\n";
text = text + " * @see com.qat.samples.sysmgmt.base.bar.I"+nomeM+"BAR#fetchAll"+nomeM+"sCache()\n";
text = text + " */\n";
text = text + "@Override\n";
text = text + "public InternalResultsResponse<"+nomeM+"> fetchAll"+nomeM+"s("+nomeM+" "+nome+")\n";
text = text + "{\n";
text = text + "	InternalResultsResponse<"+nomeM+"> response = new InternalResultsResponse<"+nomeM+">();\n";
text = text + "	response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(getSqlSession(), STMT_FETCH_"+nomeM.toUpperCase()+"_ALL));\n";
text = text + "	return response;\n";
text = text + "}\n";
text = text + '\n';
text = text + "/*\n";
text = text + " * (non-Javadoc)\n";
text = text + " * @see com.qat.samples.sysmgmt.bar.I"+nomeM+"BAR#fetch"+nomeM+"sByRequest(com.qat.samples.sysmgmt.model.request.\n";
text = text + " * PagedInquiryRequest)\n";
text = text + " */\n";
text = text + "@Override\n";
text = text + "public InternalResultsResponse<"+nomeM+"> fetch"+nomeM+"sByRequest("+nomeM+"InquiryRequest request)\n";
text = text + "{\n";
text = text + "	InternalResultsResponse<"+nomeM+"> response = new InternalResultsResponse<"+nomeM+">();\n";
text = text + "	fetch"+nomeM+"sByRequest(getSqlSession(), request, STMT_FETCH_"+nomeM.toUpperCase()+"_COUNT, STMT_FETCH_"+nomeM.toUpperCase()+"_ALL_REQUEST,\n";
text = text + "			response);\n";
text = text + "	return response;\n";
text = text + "}\n";
text = text + '\n';
text = text + '//===================================### fetch'+nomeM+'sByRequest ####======================================\n';
text = text + "\n";
text = text + "public static void fetch"+nomeM+"sByRequest(SqlSession sqlSession, "+nomeM+"InquiryRequest request, String countStatement,\n";
text = text + "			String fetchPagedStatement,\n";
text = text + "			InternalResultsResponse<?> response)\n";
text = text + "	{\n";
text = text + "\n";
text = text + "		// If the user requested the total rows/record count\n";
text = text + "		if (request.isPreQueryCount())\n";
text = text + "		{\n";
text = text + "			// set the total rows available in the response\n";
text = text + "			response.getResultsSetInfo().setTotalRowsAvailable(\n";
text = text + "					(Integer)MyBatisBARHelper.doQueryForObject(sqlSession, countStatement, request));\n";
text = text + "\n";
text = text + "			if (response.getResultsSetInfo().getTotalRowsAvailable() == ZERO)\n";
text = text + "			{\n";
text = text + "				response.setStatus(BusinessErrorCategory.NoRowsFound);\n";
text = text + "				return;\n";
text = text + "			}\n";
text = text + "		}\n";
text = text + "\n";
text = text + "		// Fetch Objects by InquiryRequest Object, paged of course\n";
text = text + "		response.getResultsList().addAll(MyBatisBARHelper.doQueryForList(sqlSession, fetchPagedStatement, request));\n";
text = text + "\n";
text = text + "		// move request start page to response start page\n";
text = text + "		response.getResultsSetInfo().setStartPage(request.getStartPage());\n";
text = text + "\n";
text = text + "		// move request page size to response page size\n";
text = text + "		response.getResultsSetInfo().setPageSize(request.getPageSize());\n";
text = text + "\n";
text = text + "		// calculate correct startPage for more rows available comparison, since it is zero based, we have to offset by\n";
text = text + "		// 1.\n";
text = text + "		int startPage = (request.getStartPage() == 0) ? 1 : (request.getStartPage() + 1);\n";
text = text + "\n";
text = text + "		// set moreRowsAvailable in response based on total rows compared to (page size * start page)\n";
text = text + "		// remember if the count was not requested the TotalRowsAvailable will be false because the assumption\n";
text = text + "		// is that you your own logic to handle this.\n";
text = text + "		if (response.getResultsSetInfo().getTotalRowsAvailable() > (response.getResultsSetInfo().getPageSize() * startPage))\n";
text = text + "		{\n";
text = text + "			response.getResultsSetInfo().setMoreRowsAvailable(true);\n";
text = text + "		}\n";
text = text + "\n";
text = text + "	}\n";
text = text + "\n";
}
text = text + "}\n";
return text;
}
