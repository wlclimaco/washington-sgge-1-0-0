package com.prosperitasglobal.sendsolv.document.controller.unittest;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.prosperitasglobal.cbof.bai.ICountryBAI;
import com.prosperitasglobal.cbof.bai.IDocumentTypeBAI;
import com.prosperitasglobal.cbof.model.Country;
import com.prosperitasglobal.cbof.model.request.FetchByCodeRequest;
import com.prosperitasglobal.cbof.model.response.CountryResponse;
import com.prosperitasglobal.sendsolv.common.util.AbstractTestBase;
import com.prosperitasglobal.sendsolv.model.DocumentType;
import com.prosperitasglobal.sendsolv.model.request.DocumentTypeRequest;
import com.prosperitasglobal.sendsolv.model.response.DocumentTypeResponse;

public class DocumentViewControllerTest extends AbstractTestBase
{
	/** The URL mapping constants. */
	/** The view mapping constants . */
	private static final String VIEW_DOCUMENT_MAIN = "/document/document_create";
	private static final String VIEW_IDENTIFICATION_MAIN = "/identification/identification_create";
	private static final String FETCH_EDIT = "/document/edit";

	private static final Logger LOG = LoggerFactory.getLogger(DocumentViewControllerTest.class);

	private static final String UNEXPECTED_EXCEPTION_WHILE_TESTING_RESPONSE_1 =
			"Unexpected exception while testing response 1---";

	private IDocumentTypeBAI documentTypeBAI;

	private ICountryBAI countryBAI;

	public ICountryBAI getCountryBAI()
	{
		return countryBAI;
	}

	@Resource
	public void setCountryBAI(ICountryBAI countryBAI)
	{
		this.countryBAI = countryBAI;
	}

	public IDocumentTypeBAI getDocumentTypeBAI()
	{
		return documentTypeBAI;
	}

	@Resource
	public void setDocumentTypeBAI(IDocumentTypeBAI documentTypeBAI)
	{
		this.documentTypeBAI = documentTypeBAI;
	}

	@Test
	public void loadDocumentType()
	{

		// ============= Mock Country Response
		DocumentTypeResponse documentTypeResponse = new DocumentTypeResponse();
		documentTypeResponse.setDocumentTypeList(new ArrayList<DocumentType>());

		CountryResponse countryResponse = new CountryResponse();
		countryResponse.setCountryList(new ArrayList<Country>());

		for (int i = 0; i < 5; i++)
		{
			DocumentType dType = new DocumentType();
			dType.setDescription("Description_" + i);
			dType.setName("Name_" + i);
			dType.setId(i);
			documentTypeResponse.getDocumentTypeList().add(dType);

			Country c = new Country();
			c.setCode("USA");
			c.setDescription("United States");
			c.setPhoneCode("1");
			countryResponse.getCountryList().add(c);
		}

		Mockito.when(
				getCountryBAI().fetchAllCountry(
						Matchers.any(FetchByCodeRequest.class)))
						.thenReturn(countryResponse);

		Mockito.when(
				getDocumentTypeBAI().fetchDocumentTypeByRequest(
						Matchers.any(DocumentTypeRequest.class)))
						.thenReturn(documentTypeResponse);

		try
		{

			setData("");
			performTestGet(FETCH_EDIT + "?parentKeyType=1")
			.andExpect(view().name(containsString(VIEW_DOCUMENT_MAIN))).andExpect(
					(model().size(2))).andExpect(model().attributeExists("documentType"));

			Mockito.calls(1);

			performTestGet(FETCH_EDIT + "?parentKeyType=3")
			.andExpect(view().name(containsString(VIEW_IDENTIFICATION_MAIN))).andExpect(
					(model().size(1)));

			Mockito.calls(1);

		}
		catch (Exception e)
		{
			String msg = UNEXPECTED_EXCEPTION_WHILE_TESTING_RESPONSE_1;
			LOG.error(msg, e);
		}
	}
}
