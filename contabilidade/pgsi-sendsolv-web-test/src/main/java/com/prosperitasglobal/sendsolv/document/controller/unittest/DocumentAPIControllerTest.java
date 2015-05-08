package com.prosperitasglobal.sendsolv.document.controller.unittest;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.prosperitasglobal.cbof.bai.IDocumentTypeBAI;
import com.prosperitasglobal.cbof.model.FilingStatusEnum;
import com.prosperitasglobal.sendsolv.bai.IOrganizationBAI;
import com.prosperitasglobal.sendsolv.common.util.AbstractTestBase;
import com.prosperitasglobal.sendsolv.model.Document;
import com.prosperitasglobal.sendsolv.model.DocumentType;
import com.prosperitasglobal.sendsolv.model.request.DocumentMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.request.DocumentTypeRequest;
import com.prosperitasglobal.sendsolv.model.response.DocumentResponse;
import com.prosperitasglobal.sendsolv.model.response.DocumentTypeResponse;

/**
 * The Class DocumentAPIControllerTest.
 */
public class DocumentAPIControllerTest extends AbstractTestBase
{

	/** The Constant UNEXPECTED_EXCEPTION_WHILE_TESTING_RESPONSE_1. */
	private static final String UNEXPECTED_EXCEPTION_WHILE_TESTING_RESPONSE_1 =
			"Unexpected exception while testing response 1---";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(DocumentAPIControllerTest.class);

	/** The exception. */
	@Rule
	public ExpectedException exception = ExpectedException.none();

	/** The organization BAI. */
	private IOrganizationBAI organizationBAI;

	/** The document type BAI. */
	private IDocumentTypeBAI documentTypeBAI;

	/**
	 * Gets the document type BAI.
	 *
	 * @return the document type BAI
	 */
	public com.prosperitasglobal.cbof.bai.IDocumentTypeBAI getDocumentTypeBAI()
	{
		return documentTypeBAI;
	}

	/**
	 * Sets the document type BAI.
	 *
	 * @param documentTypeBAI the document type BAI
	 */
	@Resource
	public void setDocumentTypeBAI(IDocumentTypeBAI documentTypeBAI)
	{
		this.documentTypeBAI = documentTypeBAI;
	}

	/**
	 * Gets the organization BAI.
	 *
	 * @return the organization BAI
	 */
	public IOrganizationBAI getOrganizationBAI()
	{
		return organizationBAI;
	}

	/**
	 * Sets the organization BAI.
	 *
	 * @param organizationBAI the new organization BAI
	 */
	@Resource
	public void setOrganizationBAI(IOrganizationBAI organizationBAI)
	{
		this.organizationBAI = organizationBAI;
	}

	/**
	 * Edits the.
	 */
	@Test
	public void edit()
	{

		// Mock Response 1
		DocumentResponse response = new DocumentResponse();
		response.setDocumentList(new ArrayList<Document>());

		Document document = new Document();
		DocumentType type = new DocumentType();
		type.setId(1);
		document.setId(1);
		document.setDocumentType(type);
		document.setFilingStatus(FilingStatusEnum.FILED);
		document.setKeywordText("Description");
		document.setNoteText("Note");

		response.getDocumentList().add(document);

		// When BAI is invoked with any request, return response 1
		Mockito.when(
				getOrganizationBAI().updateDocument(
						Matchers.any(DocumentMaintenanceRequest.class)))
						.thenReturn(response);

		try
		{

			setData("{\"document\":{\"id\":1002,\"parentKey\":7887,\"parentKeyValue\":1,\"documentType\":{\"id\":3,\"name\":\"Bylaws \"},\"keywordText\":\"Description\",\"isActionRequired\":true,\"noteText\":\"Note 01\",\"filingStatusValue\":1,\"modelAction\":\"UPDATE\"}}");

			performTest("/api/document/edit").andExpect(
					jsonPath("$.documentList", hasSize(1)))
					.andExpect(jsonPath("$.operationSuccess", equalTo(true)));

			// ============================= Empty situation ==================================

			response = new DocumentResponse();
			response.setOperationSuccess(true);
			response.setDocumentList(new ArrayList<Document>());
			// When BAI is invoked with any request, return response 2
			Mockito.when(
					getOrganizationBAI().updateDocument(
							Matchers.any(DocumentMaintenanceRequest.class)))
							.thenReturn(response);

			performTest("/api/document/edit").andExpect(
					jsonPath("$.documentList", hasSize(0))).andExpect(
							jsonPath("$.operationSuccess", equalTo(true)));

			Mockito.calls(1);

		}
		catch (Exception e)
		{
			String msg = UNEXPECTED_EXCEPTION_WHILE_TESTING_RESPONSE_1;
			LOG.error(msg, e);
		}
		try
		{
			Mockito.when(getOrganizationBAI().updateDocument(
					Matchers.any(DocumentMaintenanceRequest.class))).thenThrow(new NullPointerException());

			performTest("/api/document/edit");

			Mockito.calls(1);

		}

		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Insert.
	 */
	@Test
	public void Insert()
	{
		// Mock Response 1
		DocumentResponse response = new DocumentResponse();
		response.setDocumentList(new ArrayList<Document>());

		Document document = new Document();
		DocumentType type = new DocumentType();
		type.setId(1);
		document.setId(1);
		document.setDocumentType(type);
		document.setFilingStatus(FilingStatusEnum.FILED);
		document.setKeywordText("Description");
		document.setNoteText("Note");

		response.getDocumentList().add(document);

		// When BAI is invoked with any request, return response 1
		Mockito.when(
				getOrganizationBAI().insertDocument(
						Matchers.any(DocumentMaintenanceRequest.class)))
						.thenReturn(response);

		try
		{

			setData("{\"document\":{\"id\":1002,\"parentKey\":7887,\"parentKeyValue\":1,\"documentType\":{\"id\":3,\"name\":\"Bylaws \"},\"keywordText\":\"Description\",\"isActionRequired\":true,\"noteText\":\"Note 01\",\"filingStatusValue\":1,\"modelAction\":\"INSERT\"}}");

			performTest("/api/document/insert").andExpect(
					jsonPath("$.documentList", hasSize(1)))
					.andExpect(jsonPath("$.operationSuccess", equalTo(true)));

			// ============================= Empty situation ==================================

			response = new DocumentResponse();
			response.setOperationSuccess(true);
			response.setDocumentList(new ArrayList<Document>());
			// When BAI is invoked with any request, return response 2
			Mockito.when(
					getOrganizationBAI().insertDocument(
							Matchers.any(DocumentMaintenanceRequest.class)))
							.thenReturn(response);

			performTest("/api/document/insert").andExpect(
					jsonPath("$.documentList", hasSize(0))).andExpect(
							jsonPath("$.operationSuccess", equalTo(true)));

			Mockito.when(getOrganizationBAI().insertDocument(
					Matchers.any(DocumentMaintenanceRequest.class))).thenThrow(new NullPointerException());

			performTest("/api/document/insert");

			Mockito.calls(1);

		}
		catch (Exception e)
		{
			String msg = UNEXPECTED_EXCEPTION_WHILE_TESTING_RESPONSE_1;
			LOG.error(msg, e);
		}

	}

	/**
	 * Delete.
	 */
	@Test
	public void delete()
	{

		// Mock Response 1
		DocumentResponse response = new DocumentResponse();
		response.setDocumentList(new ArrayList<Document>());

		Document document = new Document();
		DocumentType type = new DocumentType();
		type.setId(1);
		document.setId(1);
		document.setDocumentType(type);
		document.setFilingStatus(FilingStatusEnum.FILED);
		document.setKeywordText("Description");
		document.setNoteText("Note");

		response.getDocumentList().add(document);

		// When BAI is invoked with any request, return response 1
		Mockito.when(
				getOrganizationBAI().deleteDocument(
						Matchers.any(DocumentMaintenanceRequest.class)))
						.thenReturn(response);

		try
		{

			setData("{\"document\":{\"id\":1002,\"parentKey\":7887,\"parentKeyValue\":1,\"documentType\":{\"id\":3,\"name\":\"Bylaws \"},\"keywordText\":\"Description\",\"isActionRequired\":true,\"noteText\":\"Note 01\",\"filingStatusValue\":1,\"modelAction\":\"DELETE\"}}");

			performTest("/api/document/delete").andExpect(
					jsonPath("$.documentList", hasSize(1)))
					.andExpect(jsonPath("$.operationSuccess", equalTo(true)));

			// ============================= Empty situation ==================================

			response = new DocumentResponse();
			response.setOperationSuccess(true);
			response.setDocumentList(new ArrayList<Document>());
			// When BAI is invoked with any request, return response 2
			Mockito.when(
					getOrganizationBAI().deleteDocument(
							Matchers.any(DocumentMaintenanceRequest.class)))
							.thenReturn(response);

			performTest("/api/document/delete").andExpect(
					jsonPath("$.documentList", hasSize(0))).andExpect(
							jsonPath("$.operationSuccess", equalTo(true)));

		}
		catch (Exception e)
		{
			String msg = UNEXPECTED_EXCEPTION_WHILE_TESTING_RESPONSE_1;
			LOG.error(msg, e);
		}
		try
		{
			Mockito.when(getOrganizationBAI().deleteDocument(
					Matchers.any(DocumentMaintenanceRequest.class))).thenThrow(new NullPointerException());

			performTest("/api/document/delete");

			Mockito.calls(1);

		}

		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Fetch document type.
	 */
	@Test
	public void fetchDocumentType()
	{
		DocumentTypeResponse response = new DocumentTypeResponse();

		response.setDocumentTypeList(new ArrayList<DocumentType>());

		DocumentType document = new DocumentType();
		document.setId(10);

		response.getDocumentTypeList().add(document);

		Mockito.when(
				getDocumentTypeBAI().fetchDocumentTypeByRequest(
						Matchers.any(DocumentTypeRequest.class)))
						.thenReturn(response);

		try
		{
			setData("{\"businessTypeValue\":3,\"countryCode\":\"BRA\"}");
			performTest("/api/document/type").andExpect(
					jsonPath("$.documentTypeList", hasSize(1))).andExpect(
							jsonPath("$.operationSuccess", equalTo(true)));

			Mockito.calls(1);
		}

		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
