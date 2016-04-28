
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

bacImplTest = function (teste,bar){

	var text = '/** create by system gera-java version 1.0.0 '+dataAtualFormatada()+'*/\n';
text = text + "package com.qat.samples.sysmgmt.bac;	\n";
text = text + "import java.util.Arrays;\n";
text = text + "\n";
text = text + "import javax.annotation.Resource;\n";
text = text + "\n";
text = text + "import org.junit.Assert;\n";
text = text + "import org.junit.Before;\n";
text = text + "import org.junit.Test;\n";
text = text + "import org.mockito.Matchers;\n";
text = text + "import org.mockito.Mockito;\n";
text = text + "import org.mockito.invocation.InvocationOnMock;\n";
text = text + "import org.mockito.stubbing.Answer;\n";
text = text + "import org.springframework.test.context.ContextConfiguration;\n";
text = text + "import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;\n";
text = text + "\n";
text = text + "import com.qat.framework.model.response.InternalResponse;\n";
text = text + "import com.qat.framework.model.response.InternalResponse.BusinessErrorCategory;\n";
text = text + "import com.qat.framework.model.response.InternalResponse.SystemErrorCategory;\n";
text = text + "import com.qat.framework.model.response.InternalResultsResponse;\n";
text = text + "import com.qat.samples.sysmgmt.bar.I"+titleize(bar)+"BAR;\n";
text = text + "import com.qat.samples.sysmgmt.model."+titleize(bar)+";\n";
text = text + "import com.qat.samples.sysmgmt.model.request.FetchByIdRequest;\n";
text = text + "import com.qat.samples.sysmgmt.model.request."+titleize(bar)+"InquiryRequest;\n";
text = text + "import com.qat.samples.sysmgmt.model.request."+titleize(bar)+"MaintenanceRequest;\n";
text = text + "import com.qat.samples.sysmgmt.model.request.RefreshRequest;\n";
text = text + "\n";
text = text + "@ContextConfiguration(locations = {\n";
text = text + '"classpath:conf/'+bar.toLowerCase()+'bacimpltest-context.xml"})\n';
text = text + "public class "+titleize(bar)+"BACImplTest extends AbstractJUnit4SpringContextTests\n";
text = text + "{\n";
text = text + "	private I"+titleize(bar)+"BAR mock"+titleize(bar)+"BAR;\n";
text = text + "\n";
text = text + "	private I"+titleize(bar)+"BAC "+bar.toLowerCase()+"BAC;\n";
text = text + "\n";
text = text + "	public I"+titleize(bar)+"BAC get"+titleize(bar)+"BAC()\n";
text = text + "	{\n";
text = text + "		return "+bar.toLowerCase()+"BAC;\n";
text = text + "	}\n";
text = text + "\n";
text = text + "	@Resource\n";
text = text + "	public void set"+titleize(bar)+"BAC(I"+titleize(bar)+"BAC "+bar.toLowerCase()+"BAC)\n";
text = text + "	{\n";
text = text + "		this."+bar.toLowerCase()+"BAC = "+bar.toLowerCase()+"BAC;\n";
text = text + "	}\n";
text = text + "\n";
text = text + "	public I"+titleize(bar)+"BAR getMock"+titleize(bar)+"BAR()\n";
text = text + "	{\n";
text = text + "		return mock"+titleize(bar)+"BAR;\n";
text = text + "	}\n";
text = text + "\n";
text = text + "	@Resource\n";
text = text + "	public void setMock"+titleize(bar)+"BAR(I"+titleize(bar)+"BAR mockBAR)\n";
text = text + "	{\n";
text = text + "		mock"+titleize(bar)+"BAR = mockBAR;\n";
text = text + "	}\n";
text = text + "\n";

for(i=0;i < teste.length;i++){

var nome = teste[i].classe.toLowerCase();
nomeM = titleize(nome)
text = text + "\n";
text = text + '//===================================### '+nomeM.toUpperCase()+' ####======================================\n';

text = text + "@Test\n";
text = text + "public void testInsert"+nomeM+"()\n";
text = text + "{\n";
text = text + '	'+nomeM+' '+nome+' = new '+nomeM+'("pc88", "desc88", null);\n';

text = text + "	// This shows how to handle when you need to do some specialized code in the mocked method\n";
text = text + "	Mockito.when(getMock"+titleize(bar)+"BAR().insert"+nomeM+"("+nome+")).then(new Answer<InternalResponse>()\n";
text = text + "			{\n";
text = text + "		@Override\n";
text = text + "		public InternalResponse answer(InvocationOnMock invocation) throws Throwable\n";
text = text + "		{\n";
text = text + "			// invocation.getArguments() contains the objects passed to the method.\n";
text = text + "			"+nomeM+" proc = ("+nomeM+")invocation.getArguments()[0];\n";
text = text + "			proc.setId(100);\n";
text = text + "			return new InternalResponse();\n";
text = text + "		}\n";
text = text + "			});\n";

text = text + "	"+nomeM+"MaintenanceRequest request = new "+nomeM+"MaintenanceRequest();\n";
text = text + "	request.set"+nomeM+"("+nome+");\n";

text = text + "	InternalResultsResponse<"+nomeM+"> response = get"+titleize(bar)+"BAC().insert"+nomeM+"(request);\n";

text = text + "	Assert.assertNotNull(response);\n";
text = text + "	Assert.assertNotNull("+nome+".getId());\n";
text = text + "	Assert.assertEquals(new Integer(100), "+nome+".getId());\n";

text = text + "	// ensures the insert"+nomeM+" method on the mock was called 1 time.\n";
text = text + "	Mockito.verify(getMock"+titleize(bar)+"BAR()).insert"+nomeM+"("+nome+");\n";
text = text + "}\n";

text = text + "@Test\n";
text = text + "public void testInsert"+nomeM+"ValidationError()\n";
text = text + "{\n";
text = text + '	'+nomeM+' '+nome+' = new '+nomeM+'("pc88", null, null);\n';

text = text + "	// This shows how to handle when you need to do some specialized code in the mocked method\n";
text = text + "	Mockito.when(getMock"+titleize(bar)+"BAR().insert"+nomeM+"("+nome+")).then(new Answer<InternalResponse>()\n";
text = text + "			{\n";
text = text + "		@Override\n";
text = text + "		public InternalResponse answer(InvocationOnMock invocation) throws Throwable\n";
text = text + "		{\n";
text = text + "			// invocation.getArguments() contains the objects passed to the method.\n";
text = text + "			"+nomeM+" proc = ("+nomeM+")invocation.getArguments()[0];\n";
text = text + "			proc.setId(100);\n";
text = text + "			return new InternalResponse();\n";
text = text + "		}\n";
text = text + "			});\n";

text = text + "	"+nomeM+"MaintenanceRequest request = new "+nomeM+"MaintenanceRequest();\n";
text = text + "	request.set"+nomeM+"("+nome+");\n";

text = text + "	InternalResultsResponse<"+nomeM+"> response = get"+titleize(bar)+"BAC().insert"+nomeM+"(request);\n";

text = text + "	Assert.assertNotNull(response);\n";
text = text + "	Assert.assertTrue(response.hasSystemError());\n";
text = text + "	Assert.assertEquals(SystemErrorCategory.SystemValidation, response.getSystemError());\n";

text = text + "	// ensures the insert"+nomeM+" method on the mock was called 1 time.\n";
text = text + "	Mockito.verify(getMock"+titleize(bar)+"BAR(), Mockito.never()).insert"+nomeM+"("+nome+");\n";
text = text + "}\n";

text = text + "@Test\n";
text = text + "public void testUpdate"+nomeM+"()\n";
text = text + "{\n";
text = text + '	'+nomeM+' '+nome+' = new '+nomeM+'("pc89", "desc89", null);\n';
text = text + "	"+nome+".setId(1);\n";
text = text + "	Mockito.when(getMock"+titleize(bar)+"BAR().update"+nomeM+"("+nome+")).thenReturn(new InternalResponse());\n";

text = text + "	"+nomeM+"MaintenanceRequest request = new "+nomeM+"MaintenanceRequest();\n";
text = text + "	request.set"+nomeM+"("+nome+");\n";

text = text + "	Assert.assertNotNull(get"+titleize(bar)+"BAC().update"+nomeM+"(request));\n";

text = text + "	Mockito.verify(getMock"+titleize(bar)+"BAR()).update"+nomeM+"("+nome+");\n";
text = text + "}\n";

text = text + "@Test\n";
text = text + "public void testUpdate"+nomeM+"Exception()\n";
text = text + "{\n";
text = text + '	'+nomeM+' '+nome+' = new '+nomeM+'("pc89", "desc89", null);\n';
text = text + "	"+nome+".setId(1);\n";
text = text + "	Mockito.when(getMock"+titleize(bar)+"BAR().update"+nomeM+"("+nome+")).thenReturn(\n";
text = text + "			new InternalResponse(BusinessErrorCategory.NoRowsUpdated));\n";
text = text + "	"+nomeM+"MaintenanceRequest request = new "+nomeM+"MaintenanceRequest();\n";
text = text + "	request.set"+nomeM+"("+nome+");\n";
text = text + "	InternalResultsResponse<"+nomeM+"> results = get"+titleize(bar)+"BAC().update"+nomeM+"(request);\n";
text = text + "	Assert.assertTrue(results.hasBusinessError());\n";
text = text + "	Assert.assertEquals(BusinessErrorCategory.NoRowsUpdated, results.getBusinessError());\n";

text = text + "	Mockito.verify(getMock"+titleize(bar)+"BAR()).update"+nomeM+"("+nome+");\n";
text = text + "}\n";

text = text + "@Test\n";
text = text + "public void testUpdate"+nomeM+"ValidationError()\n";
text = text + "{\n";
text = text + "	"+nomeM+" "+nome+" = new "+nomeM+"();\n";
text = text + "	"+nome+".setId(1);\n";
text = text + "	Mockito.when(getMock"+titleize(bar)+"BAR().update"+nomeM+"("+nome+")).thenReturn(\n";
text = text + "			new InternalResponse());\n";
text = text + "	"+nomeM+"MaintenanceRequest request = new "+nomeM+"MaintenanceRequest();\n";
text = text + "	request.set"+nomeM+"("+nome+");\n";
text = text + "	InternalResultsResponse<"+nomeM+"> results = get"+titleize(bar)+"BAC().update"+nomeM+"(request);\n";
text = text + "	Assert.assertEquals(SystemErrorCategory.SystemValidation, results.getSystemError());\n";

text = text + "	Mockito.verify(getMock"+titleize(bar)+"BAR(), Mockito.never()).update"+nomeM+"("+nome+");\n";
text = text + "}\n";

text = text + "@Test\n";
text = text + "public void testDelete"+nomeM+"()\n";
text = text + "{\n";
text = text + '	'+nomeM+' '+nome+' = new '+nomeM+'("pc89", "desc89", null);\n';
text = text + "	"+nome+".setId(1);\n";
text = text + "	Mockito.when(getMock"+titleize(bar)+"BAR().delete"+nomeM+"("+nome+")).thenReturn(new InternalResponse());\n";

text = text + "	"+nomeM+"MaintenanceRequest request = new "+nomeM+"MaintenanceRequest();\n";
text = text + "	request.set"+nomeM+"("+nome+");\n";

text = text + "	Assert.assertNotNull(get"+titleize(bar)+"BAC().delete"+nomeM+"(request));\n";

text = text + "	Mockito.verify(getMock"+titleize(bar)+"BAR()).delete"+nomeM+"("+nome+");\n";
text = text + "}\n";

text = text + "@Test\n";
text = text + "public void testDelete"+nomeM+"Exception()\n";
text = text + "{\n";
text = text + "	"+nomeM+" "+nome+" = new "+nomeM+"();\n";
text = text + "	"+nome+".setId(1);\n";
text = text + "	// test for bad return\n";
text = text + "	Mockito.when(getMock"+titleize(bar)+"BAR().delete"+nomeM+"("+nome+")).thenReturn(\n";
text = text + "			new InternalResponse(BusinessErrorCategory.NoRowsRemoved));\n";
text = text + "	"+nomeM+"MaintenanceRequest request = new "+nomeM+"MaintenanceRequest();\n";
text = text + "	request.set"+nomeM+"("+nome+");\n";
text = text + "	InternalResponse results = get"+titleize(bar)+"BAC().delete"+nomeM+"(request);\n";
text = text + "	Assert.assertEquals(BusinessErrorCategory.NoRowsRemoved, results.getBusinessError());\n";
text = text + "	Mockito.verify(getMock"+titleize(bar)+"BAR()).delete"+nomeM+"("+nome+");\n";
text = text + "}\n";

text = text + "@Test\n";
text = text + "public void testDelete"+nomeM+"ValidationError()\n";
text = text + "{\n";
text = text + "	"+nomeM+" "+nome+" = new "+nomeM+"();\n";

text = text + "	// test for bad return\n";
text = text + "	Mockito.when(getMock"+titleize(bar)+"BAR().delete"+nomeM+"("+nome+")).thenReturn(\n";
text = text + "			new InternalResponse());\n";
text = text + "	"+nomeM+"MaintenanceRequest request = new "+nomeM+"MaintenanceRequest();\n";
text = text + "	request.set"+nomeM+"("+nome+");\n";
text = text + "	InternalResponse results = get"+titleize(bar)+"BAC().delete"+nomeM+"(request);\n";
text = text + "	Assert.assertEquals(SystemErrorCategory.SystemValidation, results.getSystemError());\n";
text = text + "	Mockito.verify(getMock"+titleize(bar)+"BAR(), Mockito.never()).delete"+nomeM+"("+nome+");\n";
text = text + "}\n";

text = text + "@Test\n";
text = text + "public void testFetchAll"+nomeM+"()\n";
text = text + "{\n";
text = text + "	Mockito.when(getMock"+titleize(bar)+"BAR().fetchAll"+nomeM+"s( new "+nomeM+"())).thenReturn(Arrays.asList(new "+nomeM+"(),\n";
text = text + "			new "+nomeM+"(), new "+nomeM+"(), new "+nomeM+"()));\n";

text = text + "	InternalResultsResponse<"+nomeM+"> results = get"+titleize(bar)+"BAC().fetchAll"+nomeM+"s(new "+nomeM+"());\n";
text = text + "	Assert.assertNotNull(results);\n";
text = text + "	Assert.assertNotNull(results.getResultsList());\n";
text = text + "	Assert.assertEquals(4, results.getResultsList().size());\n";

text = text + "	Mockito.verify(getMock"+titleize(bar)+"BAR()).fetchAll"+nomeM+"s(new "+nomeM+"());\n";
text = text + "}\n";

text = text + "@Test\n";
text = text + "public void testFetch"+nomeM+"sByRequest()\n";
text = text + "{\n";
text = text + "	"+nomeM+"InquiryRequest request = new "+nomeM+"InquiryRequest();\n";

text = text + "	Mockito.when(getMock"+titleize(bar)+"BAR().fetch"+nomeM+"sByRequest(request)).thenReturn(\n";
text = text + "			new InternalResultsResponse<"+nomeM+">(\n";
text = text + "					Arrays.asList(new "+nomeM+"(), new "+nomeM+"(), new "+nomeM+"(), new "+nomeM+"())));\n";

text = text + "	InternalResultsResponse<"+nomeM+"> results = get"+titleize(bar)+"BAC().fetch"+nomeM+"sByRequest(request);\n";
text = text + "	Assert.assertNotNull(results);\n";
text = text + "	Assert.assertNotNull(results.getResultsList());\n";
text = text + "	Assert.assertEquals(4, results.getResultsList().size());\n";

text = text + "	Mockito.verify(getMock"+titleize(bar)+"BAR()).fetch"+nomeM+"sByRequest(request);\n";
text = text + "}\n";

text = text + "@Test\n";
text = text + "public void testFetch"+nomeM+"sByRequestNotFound()\n";
text = text + "{\n";
text = text + "	"+nomeM+"InquiryRequest request = new "+nomeM+"InquiryRequest();\n";

text = text + "	InternalResultsResponse<"+nomeM+"> irResponse = new InternalResultsResponse<"+nomeM+">();\n";
text = text + "	irResponse.setStatus(BusinessErrorCategory.NoRowsFound);\n";

text = text + "	Mockito.when(getMock"+titleize(bar)+"BAR().fetch"+nomeM+"sByRequest(request)).thenReturn(irResponse);\n";

text = text + "	InternalResultsResponse<"+nomeM+"> results = get"+titleize(bar)+"BAC().fetch"+nomeM+"sByRequest(request);\n";
text = text + "	Assert.assertNotNull(results);\n";
text = text + "	Assert.assertEquals(BusinessErrorCategory.NoRowsFound, results.getBusinessError());\n";

text = text + "	Mockito.verify(getMock"+titleize(bar)+"BAR()).fetch"+nomeM+"sByRequest(request);\n";
text = text + "}\n";

text = text + "@Test\n";
text = text + "public void testFetch"+nomeM+"ById()\n";
text = text + "{\n";
text = text + "	FetchByIdRequest request = new FetchByIdRequest();\n";

text = text + "	request.setFetchId(1);\n";

text = text + "	Mockito.when(getMock"+titleize(bar)+"BAR().fetch"+nomeM+"ById(request)).thenReturn(new "+nomeM+"());\n";

text = text + "	Assert.assertNotNull(get"+titleize(bar)+"BAC().fetch"+nomeM+"ById(request));\n";

text = text + "	Mockito.verify(getMock"+titleize(bar)+"BAR()).fetch"+nomeM+"ById(request);\n";
text = text + "}\n";

text = text + "@Test\n";
text = text + "public void testFetch"+nomeM+"ByIdValidationError()\n";
text = text + "{\n";
text = text + "	FetchByIdRequest request = new FetchByIdRequest();\n";

text = text + "	Mockito.when(getMock"+titleize(bar)+"BAR().fetch"+nomeM+"ById(request)).thenReturn(new "+nomeM+"());\n";

text = text + "	InternalResultsResponse<"+nomeM+"> results = get"+titleize(bar)+"BAC().fetch"+nomeM+"ById(request);\n";

text = text + "	Assert.assertNotNull(results);\n";

text = text + "	Assert.assertTrue(results.hasSystemError());\n";
text = text + "	Assert.assertEquals(SystemErrorCategory.SystemValidation, results.getSystemError());\n";

text = text + "	Mockito.verify(getMock"+titleize(bar)+"BAR(), Mockito.never()).fetch"+nomeM+"ById(request);\n";
text = text + "}\n";

text = text + "@Test\n";
text = text + "public void testRefresh"+nomeM+"s()\n";
text = text + "{\n";
text = text + "	Mockito.when(getMock"+titleize(bar)+"BAR().insert"+nomeM+"(Matchers.any("+nomeM+".class))).thenReturn(\n";
text = text + "			new InternalResponse());\n";

text = text + "	RefreshRequest request = new RefreshRequest();\n";

text = text + "	request.setRefreshInt(4);\n";

text = text + "	get"+titleize(bar)+"BAC().refresh"+nomeM+"s(request);\n";

text = text + "	// ensures insert"+nomeM+" was called 4 times.\n";
text = text + "	Mockito.verify(getMock"+titleize(bar)+"BAR(), Mockito.times(4)).insert"+nomeM+"(Matchers.any("+nomeM+".class));\n";
text = text + "}\n";

text = text + "@Test\n";
text = text + "public void testRefresh"+nomeM+"sLessThanOne()\n";
text = text + "{\n";
text = text + "	Mockito.when(getMock"+titleize(bar)+"BAR().insert"+nomeM+"(Matchers.any("+nomeM+".class))).thenReturn(\n";
text = text + "			new InternalResponse());\n";
text = text + "\n";
text = text + "	RefreshRequest request = new RefreshRequest();\n";
text = text + "\n";
text = text + "	request.setRefreshInt(0);\n";
text = text + "\n";
text = text + "	get"+titleize(bar)+"BAC().refresh"+nomeM+"s(request);\n";
text = text + "\n";
text = text + "	// if 0 is passed, it inserts the minimum entries (5).\n";
text = text + "	Mockito.verify(getMock"+titleize(bar)+"BAR(), Mockito.times(5)).insert"+nomeM+"(Matchers.any("+nomeM+".class));\n";
text = text + "}\n";
text = text + "\n";
text = text + "@Before\n";
text = text + "public void setup()\n";
text = text + "{\n";
text = text + "	Mockito.reset(getMock"+titleize(bar)+"BAR());\n";
text = text + "}\n";
text = text + "\n";
}
text = text + "}\n";
return text;
}
