package com.prosperitasglobal.cbof.unittest.dac.mybatis;

import org.junit.Test;

import com.prosperitasglobal.cbof.model.BusinessTypeEnum;
import com.prosperitasglobal.cbof.unittest.util.AbstractTestBaseDAC;
import com.prosperitasglobal.cbof.unittest.util.CommonTestRoutines;
import com.prosperitasglobal.sendsolv.model.DocumentType;
import com.prosperitasglobal.sendsolv.model.request.DocumentTypeRequest;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * The Class DocumentTypeDACTest.
 */
public class DocumentTypeDACTest extends AbstractTestBaseDAC
{

	/** The Constant COUNTRY_CODE. */
	private static final String COUNTRY_CODE = "CHN";

	/**
	 * Test fetch document type by request.
	 */
	@Test
	public void testFetchDocumentTypeByRequest()
	{
		DocumentTypeRequest request = new DocumentTypeRequest();

		request.setBusinessType(BusinessTypeEnum.ORGANIZATION);
		InternalResultsResponse<DocumentType> response =
				getDocumentTypeDAC().fetchDocumentTypeByRequest(request);

		CommonTestRoutines.assertResponse(response);
	}

	/**
	 * Test fetch document type by request with country code.
	 */
	@Test
	public void testFetchDocumentTypeByRequestWithCountryCode()
	{
		DocumentTypeRequest request = new DocumentTypeRequest();

		request.setBusinessType(BusinessTypeEnum.ORGANIZATION);
		request.setCountryCode(COUNTRY_CODE);
		InternalResultsResponse<DocumentType> response =
				getDocumentTypeDAC().fetchDocumentTypeByRequest(request);

		CommonTestRoutines.assertResponse(response);
	}

}
