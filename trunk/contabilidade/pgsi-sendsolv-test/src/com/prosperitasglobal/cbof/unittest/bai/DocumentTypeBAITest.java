package com.prosperitasglobal.cbof.unittest.bai;

import java.util.Arrays;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.prosperitasglobal.cbof.bai.IDocumentTypeBAI;
import com.prosperitasglobal.cbof.model.BusinessTypeEnum;
import com.prosperitasglobal.cbof.unittest.util.CommonTestRoutines;
import com.prosperitasglobal.sendsolv.dac.IDocumentTypeDAC;
import com.prosperitasglobal.sendsolv.model.DocumentType;
import com.prosperitasglobal.sendsolv.model.request.DocumentTypeRequest;
import com.prosperitasglobal.sendsolv.model.response.DocumentTypeResponse;
import com.qat.framework.model.response.InternalResponse.Status;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * The Class DocumentTypeBAITest.
 */
@ContextConfiguration(locations = {
		"classpath:com/prosperitasglobal/cbof/unittest/bai/conf/cbofbaiimpltest.xml",
		"classpath:com/prosperitasglobal/cbof/unittest/bai/conf/pgsi-cbof-validators-context-test.xml",
		"classpath:com/prosperitasglobal/sendsolv/unittest/conf/pgsi-sendsolv-resourcebundles-context.xml"})
public class DocumentTypeBAITest extends AbstractJUnit4SpringContextTests
{

	/** The document type bai. */
	private IDocumentTypeBAI documentTypeBAI;

	/** The mock document type bac. */
	private IDocumentTypeDAC mockDocumentTypeDAC;

	/**
	 * Gets the document type bai.
	 *
	 * @return the document type bai
	 */
	public IDocumentTypeBAI getDocumentTypeBAI()
	{
		return documentTypeBAI;
	}

	/**
	 * Sets the document type bai.
	 *
	 * @param documentTypeBAI the document type bai
	 */
	@Resource
	public void setDocumentTypeBAI(IDocumentTypeBAI documentTypeBAI)
	{
		this.documentTypeBAI = documentTypeBAI;
	}

	/**
	 * Gets the mock document type dac.
	 *
	 * @return the mock document type dac
	 */
	public IDocumentTypeDAC getMockDocumentTypeDAC()
	{
		return mockDocumentTypeDAC;
	}

	@Resource
	public void setMockDocumentTypeDAC(IDocumentTypeDAC mockDocumentTypeDAC)
	{
		this.mockDocumentTypeDAC = mockDocumentTypeDAC;
	}

	/**
	 * Test fetch document type by request.
	 */
	@Test
	public void testFetchDocumentTypeByRequest()
	{
		DocumentTypeRequest request = new DocumentTypeRequest();
		request.setBusinessType(BusinessTypeEnum.LOCATION);

		Mockito.when(getMockDocumentTypeDAC().fetchDocumentTypeByRequest(request)).thenReturn(
				new InternalResultsResponse<DocumentType>(Arrays.asList(new DocumentType())));

		DocumentTypeResponse results = getDocumentTypeBAI().fetchDocumentTypeByRequest(request);
		CommonTestRoutines.assertResponse(results);

		Mockito.verify(getMockDocumentTypeDAC()).fetchDocumentTypeByRequest(request);
	}

	/**
	 * Test fetch document type by request with error.
	 */
	@Test
	public void testFetchDocumentTypeByRequestWithError()
	{
		DocumentTypeRequest request = new DocumentTypeRequest();
		request.setBusinessType(BusinessTypeEnum.LOCATION);
		Mockito.when(getMockDocumentTypeDAC().fetchDocumentTypeByRequest(request)).then(
				new Answer<InternalResultsResponse<DocumentType>>()
				{
					@Override
					public InternalResultsResponse<DocumentType> answer(InvocationOnMock invocation) throws Throwable
					{
						InternalResultsResponse<DocumentType> result = new InternalResultsResponse<DocumentType>();
						result.setStatus(Status.ExceptionError);
						return result;
					}
				});

		DocumentTypeResponse results = getDocumentTypeBAI().fetchDocumentTypeByRequest(request);

		Assert.assertNotNull(results);

		Mockito.verify(getMockDocumentTypeDAC()).fetchDocumentTypeByRequest(request);
	}
}
