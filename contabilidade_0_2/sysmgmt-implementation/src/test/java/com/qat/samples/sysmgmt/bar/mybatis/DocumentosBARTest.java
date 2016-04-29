package com.qat.samples.sysmgmt.bar.mybatis;


import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.qat.framework.model.BaseModel.PersistenceActionEnum;
import com.qat.framework.model.response.InternalResponse.BusinessErrorCategory;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.samples.sysmgmt.bar.Documentos.IDocumentoBAR;
import com.qat.samples.sysmgmt.estado.model.Estado;
import com.qat.samples.sysmgmt.util.model.Documento;
import com.qat.samples.sysmgmt.util.model.DocumentoTypeEnum;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;
import com.qat.samples.sysmgmt.util.model.request.PagedInquiryRequest;

@ContextConfiguration(locations = {
		"classpath:conf/unittest-base-context.xml",
		"classpath:conf/documentosbartest-context.xml"
})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
@ActiveProfiles("postgres")
public class DocumentosBARTest extends AbstractTransactionalJUnit4SpringContextTests
{
private static final Logger LOG = LoggerFactory.getLogger(DocumentosBARTest.class);
private IDocumentoBAR documentoBAR; // injected by Spring through @Resource

@Resource
public void setDocumentoBAR(IDocumentoBAR documentoBAR)
{
	this.documentoBAR = documentoBAR;
}

public IDocumentoBAR getDocumentoBAR()
{
	return documentoBAR;
}


//===================================### DOCUMENTO ####======================================


@Test
	public void testDeleteDocumento()
	{
		Documento documento = new Documento(4, DocumentoTypeEnum.CERTIDADONASCIMENTO, "000", (new Date()).getTime(), new Estado(1), PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(4);
		Documento documentoResponse = getDocumentoBAR().fetchDocumentoById(request);
		Assert.assertEquals(documentoResponse, null);
		getDocumentoBAR().insertDocumento(documento);
		documentoResponse = getDocumentoBAR().fetchDocumentoById(request);
		Assert.assertEquals(documento.getId(), documentoResponse.getId());
		getDocumentoBAR().deleteDocumentoById(documento);
		documentoResponse = getDocumentoBAR().fetchDocumentoById(request);
		Assert.assertEquals(documentoResponse, null);
	}

	@Test
	public void testFetchAllDocumentos()
	{
	Documento documento = new Documento();
		List<Documento> response = getDocumentoBAR().fetchAllDocumentos(documento).getResultsList();
		Assert.assertNotNull(response);
	}

	@Test
	public void testDeleteAllDocumentos()
	{
		getDocumentoBAR().deleteAllDocumentos();
	Documento documento = new Documento();
		List<Documento> response = getDocumentoBAR().fetchAllDocumentos(new Documento(1, DocumentoTypeEnum.CERTIDADONASCIMENTO, "000", (new Date()).getTime(), new Estado(1), PersistenceActionEnum.INSERT)).getResultsList();
		Assert.assertEquals(response.size(), 0);
	}

	@Test
	public void testUpdateDocumento()
	{
		Documento documento = new Documento(1, DocumentoTypeEnum.CERTIDADONASCIMENTO, "numero_6", (new Date()).getTime(), new Estado(1), PersistenceActionEnum.INSERT);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setFetchId(1);
		Documento documentoResponse = getDocumentoBAR().fetchDocumentoById(request);
		Assert.assertEquals(documentoResponse.getNumero(), "numero_5");
		getDocumentoBAR().updateDocumento(documento);
		documentoResponse = getDocumentoBAR().fetchDocumentoById(request);
		Assert.assertEquals(documentoResponse.getNumero(), "numero_6");
	}

	@Test
	public void testFetchDocumentosByRequest() throws Exception
	{
		// check for valid and precount
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.setPageSize(3);
		InternalResultsResponse<Documento> response = getDocumentoBAR().fetchDocumentosByRequest(request);
		Assert.assertFalse(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);
		// check for valid and precount and start 2nd page
		request.setPreQueryCount(true);
		request.setStartPage(1);
		request.setPageSize(3);
		response = getDocumentoBAR().fetchDocumentosByRequest(request);
		Assert.assertFalse(response.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response.getResultsSetInfo().getPageSize() == 3);
		Assert.assertTrue(response.getResultsSetInfo().getTotalRowsAvailable() > 0);

		// check for valid and no precount
		PagedInquiryRequest request2 = new PagedInquiryRequest();
		request2.setPreQueryCount(false);
		InternalResultsResponse<Documento> response2 = getDocumentoBAR().fetchDocumentosByRequest(request2);
		Assert.assertFalse(response2.getResultsSetInfo().isMoreRowsAvailable());
		Assert.assertTrue(response2.getResultsSetInfo().getPageSize() == 20);
		// this is because we did not choose to precount
		Assert.assertTrue(response2.getResultsSetInfo().getTotalRowsAvailable() == 0);

		// check for zero rows
		getDocumentoBAR().deleteAllDocumentos();
		PagedInquiryRequest request3 = new PagedInquiryRequest();
		request3.setPreQueryCount(true);
		InternalResultsResponse<Documento> response3 = getDocumentoBAR().fetchDocumentosByRequest(request3);
		Assert.assertTrue(response3.getBusinessError() == BusinessErrorCategory.NoRowsFound);

	}

	@Before
	public void setup()
	{
		executeSqlScript("conf/insertDocumento.sql", false);
	}

}
