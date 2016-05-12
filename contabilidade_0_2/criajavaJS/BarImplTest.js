
barImplTest = function (teste,bar){

var text = '/** create by system gera-java version 1.0.0 '+dataAtualFormatada()+'*/\n';

text = text + "package com.qat.samples.sysmgmt.bar.mybatis;	\n";
text = text + "\n";
text = text + "\n";
text = text + "import java.util.List;\n";
text = text + "\n";
text = text + "import javax.annotation.Resource;\n";
text = text + "\n";
text = text + "import org.junit.Assert;\n";
text = text + "import org.junit.Before;\n";
text = text + "import org.junit.Test;\n";
text = text + "import org.slf4j.Logger;\n";
text = text + "import org.slf4j.LoggerFactory;\n";
text = text + "import org.springframework.test.context.ActiveProfiles;\n";
text = text + "import org.springframework.test.context.ContextConfiguration;\n";
text = text + "import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;\n";
text = text + "import org.springframework.test.context.transaction.TransactionConfiguration;\n";
text = text + "import org.springframework.transaction.annotation.Transactional;\n";
text = text + "\n";
text = text + "import com.qat.framework.model.response.InternalResponse.BusinessErrorCategory;\n";
text = text + "import com.qat.framework.model.response.InternalResultsResponse;\n";
text = text + "import com.qat.samples.sysmgmt.bar.I"+bar+"BAR;\n";
text = text + "import com.qat.samples.sysmgmt.model."+bar+";\n";
text = text + "import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;\n";
text = text + "import com.qat.samples.sysmgmt.model.request."+bar+"InquiryRequest;\n";
text = text + "\n";
text = text + "@ContextConfiguration(locations = {\n";
text = text + '		"classpath:conf/unittest-base-context.xml",\n';
text = text + '		"classpath:conf/bartest-context.xml"\n';
text = text + "})\n";
text = text + '@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)\n';
text = text + "@Transactional\n";
text = text + '@ActiveProfiles("postgres")\n';
text = text + "public class "+bar+"BARTest extends AbstractTransactionalJUnit4SpringContextTests\n";
text = text + "{\n";
text = text + "private static final Logger LOG = LoggerFactory.getLogger("+bar+"BARTest.class);\n";
text = text + "private I"+bar+"BAR "+bar.toLowerCase()+"BAR; // injected by Spring through @Resource\n";
text = text + "\n";
text = text + "@Resource\n";
text = text + "public void set"+bar+"BAR(I"+bar+"BAR "+bar.toLowerCase()+"BAR)\n";
text = text + "{\n";
text = text + "	this."+bar.toLowerCase()+"BAR = "+bar.toLowerCase()+"BAR;\n";
text = text + "}\n";
text = text + "\n";
text = text + "public I"+bar+"BAR get"+bar+"BAR()\n";
text = text + "{\n";
text = text + "	return "+bar.toLowerCase()+"BAR;\n";
text = text + "}\n";
text = text + "\n";


for(i=0;i < teste.length;i++){

var nome = teste[i].classe.toLowerCase();
nomeM = titleize(nome)
text = text + "\n";
text = text + '//===================================### '+nomeM.toUpperCase()+' ####======================================\n';
text = text + "\n";
text = text + "\n";
text = text + "@Test\n";
text = text + "	public void testDelete"+nomeM+"()\n";
text = text + "	{\n";
text = text + '		'+nomeM+' '+nome+' = new '+nomeM+'(4, "'+nomeM+'_999");\n';
text = text + "		FetchByIdRequest request = new FetchByIdRequest();\n";
text = text + "		request.setFetchId(4);\n";
text = text + "		"+nomeM+" "+nome+"Response = get"+bar+"BAR().fetch"+nomeM+"ById(request);\n";
text = text + "		Assert.assertEquals("+nome+"Response, null);\n";
text = text + "		get"+bar+"BAR().insert"+nomeM+"("+nome+");\n";
text = text + "		"+nome+"Response = get"+bar+"BAR().fetch"+nomeM+"ById(request);\n";
text = text + "		Assert.assertEquals("+nome+".getId(), "+nome+"Response.getId());\n";
text = text + "		get"+bar+"BAR().delete"+nomeM+"ById("+nome+");\n";
text = text + "		"+nome+"Response = get"+bar+"BAR().fetch"+nomeM+"ById(request);\n";
text = text + "		Assert.assertEquals("+nome+"Response, null);\n";
text = text + "	}\n";
text = text + "\n";
text = text + "	@Test\n";
text = text + "	public void testFetchAll"+nomeM+"s()\n";
text = text + "	{\n";
text = text + "	"+nomeM+" "+nome+" = new "+nomeM+"();\n";
text = text + "		List<"+nomeM+"> response = get"+bar+"BAR().fetchAll"+nomeM+"s("+nome+").getResultsList();\n";
text = text + "		Assert.assertNotNull(response);\n";
text = text + "	}\n";
text = text + "\n";
text = text + "	@Test\n";
text = text + "	public void testDeleteAll"+nomeM+"s()\n";
text = text + "	{\n";
text = text + "		get"+bar+"BAR().deleteAll"+nomeM+"s();\n";
text = text + "	"+nomeM+" "+nome+" = new "+nomeM+"();\n";
text = text + "		List<"+nomeM+"> response = get"+bar+"BAR().fetchAll"+nomeM+"s(new "+nomeM+"()).getResultsList();\n";
text = text + "		Assert.assertEquals(response.size(), 0);\n";
text = text + "	}\n";
text = text + "\n";
text = text + "	@Test\n";
text = text + "	public void testUpdate"+nomeM+"()\n";
text = text + "	{\n";
text = text + '		'+nomeM+' '+nome+' = new '+nomeM+'(1, "NATIVE INSERT UPDATE");\n';
text = text + "		FetchByIdRequest request = new FetchByIdRequest();\n";
text = text + "		request.setFetchId(1);\n";
text = text + "		"+nomeM+" "+nome+"Response = get"+bar+"BAR().fetch"+nomeM+"ById(request);\n";
text = text + '		Assert.assertEquals('+nome+'Response.getDescription(), "NATIVE INSERT");\n';
text = text + "		get"+bar+"BAR().update"+nomeM+"("+nome+");\n";
text = text + "		"+nome+"Response = get"+bar+"BAR().fetch"+nomeM+"ById(request);\n";
text = text + '		Assert.assertEquals('+nome+'Response.getDescription(), "NATIVE INSERT UPDATE");\n';
text = text + "	}\n";
text = text + "\n";
text = text + "	@Test\n";
text = text + "	public void testFetch"+nomeM+"sByRequest() throws Exception\n";
text = text + "	{\n";
text = text + "		// check for valid and precount\n";
text = text + "		"+nomeM+"InquiryRequest request = new "+nomeM+"InquiryRequest();\n";
text = text + "		request.setPreQueryCount(true);\n";
text = text + "		request.setStartPage(0);\n";
text = text + "		request.setPageSize(3);\n";
text = text + "		InternalResultsResponse<"+nomeM+"> response = get"+bar+"BAR().fetch"+nomeM+"sByRequest(request);\n";
text = text + "		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());\n";
text = text + "		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);\n";
text = text + "		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);\n";
text = text + "		// check for valid and precount and start 2nd page\n";
text = text + "		request.setPreQueryCount(true);\n";
text = text + "		request.setStartPage(1);\n";
text = text + "		request.setPageSize(3);\n";
text = text + "		response = get"+bar+"BAR().fetch"+nomeM+"sByRequest(request);\n";
text = text + "		//Assert.assertTrue(response.getResultsSetInfo().isMoreRowsAvailable());\n";
text = text + "		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);\n";
text = text + "		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);\n";
text = text + "\n";
text = text + "		// check for valid and no precount\n";
text = text + "		"+nomeM+"InquiryRequest request2 = new "+nomeM+"InquiryRequest();\n";
text = text + "		request2.setPreQueryCount(false);\n";
text = text + "		InternalResultsResponse<"+nomeM+"> response2 = get"+bar+"BAR().fetch"+nomeM+"sByRequest(request2);\n";
text = text + "		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());\n";
text = text + "		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);\n";
text = text + "		// this is because we did not choose to precount\n";
text = text + "		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);\n";
text = text + "\n";
text = text + "		// check for zero rows\n";
text = text + "		get"+bar+"BAR().deleteAll"+nomeM+"s();\n";
text = text + "		"+nomeM+"InquiryRequest request3 = new "+nomeM+"InquiryRequest();\n";
text = text + "		request3.setPreQueryCount(true);\n";
text = text + "		InternalResultsResponse<"+nomeM+"> response3 = get"+bar+"BAR().fetch"+nomeM+"sByRequest(request3);\n";
text = text + "		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);\n";
text = text + "\n";
text = text + "	}\n";

}
text = text + "\n";
text = text + "	@Before\n";
text = text + "	public void setup()\n";
text = text + "	{\n";
for(i=0;i < teste.length;i++){

	var nome = teste[i].classe.toLowerCase();
	nomeM = titleize(nome)
text = text + '		executeSqlScript("conf/insert'+nomeM+'.sql", false);\n';
}
text = text + "	}\n";
text = text + "\n";

for(var i=0;i < teste.length;i++){
text = text + '	\n';

text = text + '	public '+titleize(teste[i].classe)+' insert'+titleize(teste[i].classe)+'(Integer id,TabelaEnum tabela,PersistenceActionEnum action)\n';
text = text + '		{\n';
text = text + '			'+titleize(teste[i].classe)+' '+teste[i].classe.toLowerCase()+' = new '+titleize(teste[i].classe)+'();\n';
text = text + '			Date a = new Date();\n';
	for(var y=0;y < teste[i].model.length;y++){

		console.log(teste[i].model[y].field.tipo);
		if(teste[i].model[y].field.tipo !== undefined){
			if(teste[i].model[y].field.tipo.indexOf('List') == -1){
				text = text + '			'+teste[i].classe.toLowerCase()+'.set'+titleize(teste[i].model[y].field.campo)+'('+convertModule(teste[i].model[y].field.tipo,teste[i].model[y].field.campo,teste[i].model[y].field.List)+');\n';
			}else{
				text = text + '			'+teste[i].classe.toLowerCase()+'.set'+titleize(teste[i].model[y].field.campo)+'('+convertModule(teste[i].model[y].field.tipo,teste[i].model[y].field.campo,teste[i].model[y].field.List)+');\n';
				text = text + '			'+teste[i].classe.toLowerCase()+'.get'+teste[i].model[y].field.List+'().add(insert'+teste[i].model[y].field.List+'(id,TabelaEnum.'+teste[i].classe.toUpperCase()+',action));\n';
			}
		}
	}
text = text + '			'+teste[i].classe.toLowerCase()+'.setParentId(id);\n';
text = text + '			'+teste[i].classe.toLowerCase()+'.setEmprId(1);\n';
text = text + '			'+teste[i].classe.toLowerCase()+'.setModifyDateUTC(a.getTime());\n';
text = text + '			'+teste[i].classe.toLowerCase()+'.setCreateDateUTC(a.getTime());\n';
text = text + '			'+teste[i].classe.toLowerCase()+'.setCreateUser("system");\n';
text = text + '			'+teste[i].classe.toLowerCase()+'.setModifyUser("system");\n';
text = text + '			'+teste[i].classe.toLowerCase()+'.setProcessId(1);\n';
text = text + '			'+teste[i].classe.toLowerCase()+'.setModelAction(action);\n';
text = text + '	\n';
text = text + '			return '+teste[i].classe.toLowerCase()+';\n';
text = text + '		}\n';
text = text + "\n";
}
text = text + "\n";
text = text + "}\n";
return text;
}
