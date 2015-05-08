package com.prosperitasglobal.sendsolv.unittest.dac.mybatis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.prosperitasglobal.cbof.model.Address;
import com.prosperitasglobal.cbof.model.BusinessTypeEnum;
import com.prosperitasglobal.cbof.model.Contact;
import com.prosperitasglobal.cbof.model.Email;
import com.prosperitasglobal.cbof.model.Phone;
import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.cbof.unittest.util.AbstractTestBaseDAC;
import com.prosperitasglobal.cbof.unittest.util.CommonTestRoutines;
import com.prosperitasglobal.sendsolv.model.Business;
import com.prosperitasglobal.sendsolv.model.Document;
import com.prosperitasglobal.sendsolv.model.Organization;
import com.prosperitasglobal.sendsolv.model.Risk;
import com.prosperitasglobal.sendsolv.model.StatusEnum;
import com.prosperitasglobal.sendsolv.model.request.DocumentMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.request.OrganizationMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.request.PagedInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.RiskMaintenanceRequest;
import com.prosperitasglobal.sendsolv.sdn.bac.ISdnCheckerBAC;
import com.prosperitasglobal.sendsolv.sdn.model.SDNStatusEnum;
import com.prosperitasglobal.sendsolv.sdn.model.SdnStatusHistory;
import com.prosperitasglobal.sendsolv.sdn.model.request.SdnCheckerRequest;
import com.prosperitasglobal.sendsolv.sdn.model.request.SdnStatusHistoryRequest;
import com.prosperitasglobal.sendsolv.sdn.unittest.util.CommonSdnTestRoutines;
import com.prosperitasglobal.sendsolv.util.PGSiDateUtil;
import com.qat.framework.model.QATModel.PersistanceActionEnum;
import com.qat.framework.model.SortExpression;
import com.qat.framework.model.SortExpression.Direction;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResponse.Status;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * The Class OrganizationDACTest.
 */
public class OrganizationDACTest extends AbstractTestBaseDAC
{

	/** The Constant SECOND_ORG_NEEDS_TO_MATCH_LIST. */
	private static final String SECOND_ORG_NEEDS_TO_MATCH_LIST = "Second Org needs to match List";

	/** The Constant FIRST_ORG_NEEDS_TO_MATCH_LIST. */
	private static final String FIRST_ORG_NEEDS_TO_MATCH_LIST = "First Org needs to match List";

	/** The Constant SIX. */
	private static final Integer SIX = 6;

	/** The Constant HUNDRED. */
	private static final Integer HUNDRED = 100;

	/** The Constant UPDATED_DBA_NAME. */
	private static final String UPDATED_DBA_NAME = "Updated DBA Name";

	/** The Constant DUMMY_MODIFY_USER. */
	private static final String DUMMY_MODIFY_USER = "MDOE";

	/** The Constant ZERO. */
	private static final Integer ZERO = 0;

	/** The Constant ONE. */
	private static final Integer ONE = 1;

	/** The Constant TWO. */
	private static final Integer TWO = 2;

	/** The Constant THREE. */
	private static final Integer THREE = 3;

	/** The Constant FOUR. */
	private static final Integer FOUR = 4;

	/** The Constant FIVE. */
	private static final Integer FIVE = 5;

	/** The Constant TWENTY. */
	private static final Integer TWENTY = 20;

	/** The Constant FOUR_HUNDRED_ONE. */
	private static final String FOUR_HUNDRED_ONE = "401";

	/** The Constant COUNTRY_CODE. */
	private static final String COUNTRY_CODE = "BRA";

	/** The Constant CITY_NAME. */
	private static final String CITY_NAME = "Oz";

	/** The Constant EMAIL_ADDRESS_UPDATED. */
	private static final String EMAIL_ADDRESS_UPDATED = "emailAddressUpdated";

	/** The Constant UPDATED_NAME. */
	private static final String UPDATED_NAME = "Updated Name";

	private static final String NAME_SORT_FIELD = "name";

	/** The log. */
	private static final Logger LOG = LoggerFactory.getLogger(OrganizationDACTest.class);

	/** The mock sdn checker bac. */
	private ISdnCheckerBAC mockSdnCheckerBAC;

	/**
	 * Sets the up.
	 */
	@Before
	public void setUp()
	{
		executeSqlScript("com/prosperitasglobal/sendsolv/unittest/dac/mybatis/conf/deleteBusiness.sql", false);
		Mockito.reset(getMockSdnCheckerBAC());
	}

	/**
	 * Gets the mock sdn checker bac.
	 *
	 * @return the mockSdnCheckerBAC
	 */
	public ISdnCheckerBAC getMockSdnCheckerBAC()
	{
		return mockSdnCheckerBAC;
	}

	/**
	 * Sets the mock sdn checker bac.
	 *
	 * @param mockSdnCheckerBAC the mockSdnCheckerBAC to set
	 */
	@Resource
	public void setMockSdnCheckerBAC(ISdnCheckerBAC mockSdnCheckerBAC)
	{
		this.mockSdnCheckerBAC = mockSdnCheckerBAC;
	}

	/**
	 * Test fetchAllOrganizations with paging.
	 */
	@Test
	public void testfetchAllOrganizations()
	{
		Integer pageSize = TWO;
		Integer totalOrgs = TWENTY;

		// Add 20 orgs
		List<Organization> orgList = insertOrganizations(totalOrgs);

		// get first page
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.setPageSize(pageSize);
		request.setStartPage(ZERO);
		request.setPreQueryCount(true);
		request.setStartPage(ZERO);
		request.setStatusList(Arrays.asList(StatusEnum.ACTIVE));
		request.getSortExpressions().clear();

		InternalResultsResponse<Organization> response = getOrganizationDAC().fetchOrganizationByRequest(request);
		CommonTestRoutines.assertResultResponse(response);

		Assert.assertTrue("first page size needs to be > 0", response.getResultsList().size() > ZERO);
		Assert.assertEquals("first page size needs to be = 2", pageSize, (Integer)response.getResultsList().size());

		Assert.assertEquals(FIRST_ORG_NEEDS_TO_MATCH_LIST, orgList.get(ZERO).getId(),
				response.getResultsList().get(ZERO).getId());
		Assert.assertEquals(SECOND_ORG_NEEDS_TO_MATCH_LIST, orgList.get(ONE).getId(),
				response.getResultsList().get(ONE).getId());
		Assert.assertEquals("Number of rows need to match", totalOrgs, response.getResultsSetInfo()
				.getTotalRowsAvailable());

		// go to next page
		request.setStartPage(ONE);

		response = getOrganizationDAC().fetchOrganizationByRequest(request);
		Assert.assertTrue("second page size needs to be > 0", response.getResultsList().size() > ZERO);
		Assert.assertEquals("second page size needs to be = 2", pageSize, (Integer)response.getResultsList().size());

		Assert.assertEquals(FIRST_ORG_NEEDS_TO_MATCH_LIST, orgList.get(TWO).getId(),
				response.getResultsList().get(ZERO).getId());
		Assert.assertEquals(SECOND_ORG_NEEDS_TO_MATCH_LIST, orgList.get(THREE).getId(), response.getResultsList()
				.get(ONE).getId());

		// one more page
		request.setStartPage(TWO);

		response = getOrganizationDAC().fetchOrganizationByRequest(request);
		Assert.assertTrue("third page size needs to be > 0", response.getResultsList().size() > ZERO);
		Assert.assertEquals("third page size needs to be = 2", pageSize, (Integer)response.getResultsList().size());

		Assert.assertEquals(FIRST_ORG_NEEDS_TO_MATCH_LIST, orgList.get(FOUR).getId(),
				response.getResultsList().get(ZERO).getId());
		Assert.assertEquals(SECOND_ORG_NEEDS_TO_MATCH_LIST, orgList.get(FIVE).getId(),
				response.getResultsList().get(ONE).getId());

	}

	/**
	 * Test fetchAllOrganizations with paging and an ascending sort on name.
	 */
	@Test
	public void testfetchAllOrganizationsSortNameAscending()
	{
		insertOrganizations(TWENTY, true);

		// get first page
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.addSortExpressions(new SortExpression(NAME_SORT_FIELD, Direction.Ascending));

		InternalResultsResponse<Organization> response = getOrganizationDAC().fetchOrganizationByRequest(request);

		CommonTestRoutines.assertResultResponse(response);

		assertOrganizationSort(response.getResultsList(), NAME_SORT_FIELD, Direction.Ascending);
	}

	/**
	 * Test fetchAllOrganizations with paging and a descending sort on name.
	 */
	@Test
	public void testfetchAllOrganizationsSortNameDescending()
	{
		insertOrganizations(TWENTY, true);

		// get first page
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.addSortExpressions(new SortExpression(NAME_SORT_FIELD, Direction.Descending));

		InternalResultsResponse<Organization> response = getOrganizationDAC().fetchOrganizationByRequest(request);

		CommonTestRoutines.assertResultResponse(response);

		assertOrganizationSort(response.getResultsList(), NAME_SORT_FIELD, Direction.Descending);
	}

	// Test insert Organization (includes Contacts - Email,Phone, Address)
	/**
	 * Test insert organization.
	 */
	@Test
	public void testInsertOrganization()
	{
		Organization organization = CommonTestRoutines.createDummyOrganization();

		InternalResultsResponse<Organization> response = getOrganizationDAC().insertOrganization(organization);

		CommonTestRoutines.assertResultResponse(response);

		testOrganizationAgainstDatabaseById(organization);

	}

	/**
	 * Test update organization.
	 */
	@Test
	public void testUpdateOrganization()
	{
		Organization organization = CommonTestRoutines.createDummyOrganization();

		getOrganizationDAC().insertOrganization(organization);

		organization.setModelAction(PersistanceActionEnum.UPDATE);
		organization.setName(UPDATED_NAME);
		organization.setDbaName(UPDATED_DBA_NAME);
		organization.setModifyUser(DUMMY_MODIFY_USER);
		organization.setModifyDateUTC(PGSiDateUtil.getCurrentDateTimeMillis());

		for (Contact contact : organization.getContactList())
		{
			contact.setModelAction(PersistanceActionEnum.UPDATE);

			switch (contact.getContactType())
			{
				case OTHER:
					((Phone)contact).setNumber("2349987");
					((Phone)contact).setExtension("A401");
					((Phone)contact).getCountry().setCode(COUNTRY_CODE);
					break;
				case EMAIL_PERSONAL:
					((Email)contact).setEmailAddress(EMAIL_ADDRESS_UPDATED);
					break;
				case POSTAL_HOME:
					((Address)contact).setCityName(CITY_NAME);
					break;
				case PHONE_WORK:
					((Phone)contact).setNumber(FOUR_HUNDRED_ONE);
					((Phone)contact).setExtension(FOUR_HUNDRED_ONE);
					((Phone)contact).getCountry().setCode(COUNTRY_CODE);
					break;
				case EMAIL_WORK:
					((Email)contact).setEmailAddress(EMAIL_ADDRESS_UPDATED);
					break;
				case POSTAL_WORK:
					((Address)contact).setCityName(CITY_NAME);
					break;
				default:
					break;
			}
		}

		InternalResultsResponse<Organization> response = getOrganizationDAC().updateOrganization(organization);
		CommonTestRoutines.assertResponse(response);

		testOrganizationAgainstDatabaseById(organization);

	}

	/**
	 * Test update organization ol.
	 */
	@Test
	public void testUpdateOrganizationOL()
	{
		Organization organization = CommonTestRoutines.createDummyOrganization();

		InternalResultsResponse<Organization> response = getOrganizationDAC().insertOrganization(organization);

		organization.setModelAction(PersistanceActionEnum.UPDATE);
		organization.setName(UPDATED_NAME);

		response = getOrganizationDAC().updateOrganization(organization);
		Assert.assertEquals("Should NOT get an Optimistic Locking Error, but got " + response.getStatus(),
				Status.OperationSuccess, response.getStatus());

		response = getOrganizationDAC().fetchOrganizationById(new FetchByIdRequest(organization.getId()));
		organization = response.getFirstResult();

		Assert.assertTrue("Version should be > 0 ", organization.getVersion() > 0);

		organization.setModelAction(PersistanceActionEnum.UPDATE);

		// Set the version number to 100 to generate an OL error.
		organization.setVersion(HUNDRED);
		response = getOrganizationDAC().updateOrganization(organization);
		Assert.assertEquals("Should get an Optimistic Locking Error, but got " + response.getStatus(),
				Status.OptimisticLockingError, response.getStatus());
	}

	/**
	 * Test delete organization.
	 */
	@Test
	public void testDeleteOrganization()
	{
		Organization organization = CommonTestRoutines.createDummyOrganization();

		InternalResultsResponse<Organization> response = getOrganizationDAC().insertOrganization(organization);

		if (response.isInError())
		{
			LOG.info("error adding organization");
		}

		InternalResponse deleteResponse = getOrganizationDAC().deleteOrganization(organization);

		if (deleteResponse.isInError())
		{
			LOG.info("error deleting organization");
		}

		response = getOrganizationDAC().fetchOrganizationById(new FetchByIdRequest(organization.getId()));

		Assert.assertEquals("Organization cannot be found after delete", Status.NoRowsFoundError,
				response.getStatus());
	}

	/**
	 * Test insert document.
	 */
	@Test
	public void testInsertDocument()
	{
		// Insert an organization first.
		Organization organization = CommonTestRoutines.createDummyOrganization();
		InternalResultsResponse<Organization> internalResponse =
				getOrganizationDAC().insertOrganization(organization);
		CommonTestRoutines.assertResultResponse(internalResponse);

		InternalResultsResponse<Document> response = insertDocument(organization.getId());
		Assert.assertTrue(response.getFirstResult().getId() > 0);

		testDocumentAgainstDatabaseById(organization, response.getFirstResult());
	}

	/**
	 * Test update document.
	 */
	@Test
	public void testUpdateDocument()
	{
		// Insert an organization first.
		Organization organization = CommonTestRoutines.createDummyOrganization();
		InternalResultsResponse<Organization> organizationResponse =
				getOrganizationDAC().insertOrganization(organization);

		InternalResultsResponse<Document> response = insertDocument(organizationResponse.getFirstResult().getId());

		// Update the document
		DocumentMaintenanceRequest request = new DocumentMaintenanceRequest();

		request.setDocument(response.getFirstResult());

		InternalResponse internalResponse = getOrganizationDAC().updateDocument(request);
		CommonTestRoutines.assertResponse(internalResponse);

		testDocumentAgainstDatabaseById(organization, response.getFirstResult());
	}

	/**
	 * Test delete document.
	 */
	@Test
	public void testDeleteDocument()
	{
		// Insert an organization first.
		Organization organization = CommonTestRoutines.createDummyOrganization();
		InternalResultsResponse<Organization> organizationResponse =
				getOrganizationDAC().insertOrganization(organization);

		// Insert a document
		DocumentMaintenanceRequest request = new DocumentMaintenanceRequest();
		InternalResultsResponse<Document> response = insertDocument(organizationResponse.getFirstResult().getId());
		request.setDocument(response.getFirstResult());

		getOrganizationDAC().insertDocument(request);

		InternalResponse internalResponse = getOrganizationDAC().deleteDocument(request);
		CommonTestRoutines.assertResponse(internalResponse);
	}

	/**
	 * Test update risk.
	 */
	@Test
	public void testUpdateRisk()
	{
		// Insert an organization first.
		Organization organization = CommonTestRoutines.createDummyOrganization();
		InternalResultsResponse<Organization> organizationResponse =
				getOrganizationDAC().insertOrganization(organization);

		// update risk
		RiskMaintenanceRequest request = new RiskMaintenanceRequest();
		Risk risk = CommonTestRoutines.createDummyRisk();
		risk.setParentKey(organizationResponse.getFirstResult().getId());
		risk.setParentKeyType(BusinessTypeEnum.ORGANIZATION);
		risk.setVersion(organizationResponse.getFirstResult().getVersion());
		request.setRisk(risk);

		InternalResponse internalResponse = getOrganizationDAC().updateRisk(request);
		Assert.assertEquals("internalResponse.getStatus() should be Success", Status.OperationSuccess,
				internalResponse.getStatus());
	}

	/**
	 * Test apply status.
	 */
	@Test
	public void testApplyStatus()
	{
		Organization organization = CommonTestRoutines.createDummyOrganization();
		getOrganizationDAC().insertOrganization(organization);

		OrganizationMaintenanceRequest request = new OrganizationMaintenanceRequest();
		organization.setStatus(StatusEnum.INACTIVE);
		request.setOrganization(organization);

		InternalResponse response = getOrganizationDAC().applyOrganizationStatus(organization);
		CommonTestRoutines.assertResponse(response);

		InternalResultsResponse<Organization> result =
				getOrganizationDAC().fetchOrganizationById(new FetchByIdRequest(organization.getId()));
		CommonTestRoutines.assertResponse(result);

		Assert.assertEquals("The status person needs to be the same", organization.getStatus(), result.getFirstResult()
				.getStatus());
	}

	/**
	 * Test fetch organization by id check sdn status code.
	 */
	@Test
	public void testFetchOrganizationByIdCheckSDNStatusCode()
	{
		Organization organization = insertOrganization();

		prepareMockSdnCheckerBAC(organization);

		InternalResultsResponse<Organization> response2 =
				getOrganizationDAC().fetchOrganizationById(new FetchByIdRequest(organization.getId()));
		CommonTestRoutines.assertResultResponse(response2);

		// Verify the SDN status code should be equals.
		Assert.assertEquals(SDNStatusEnum.PENDING_INVESTIGATION, response2.getFirstResult().getSDNStatus());

	}

	/**
	 * Test fetch organization by request check sdn status code.
	 */
	@Test
	public void testFetchOrganizationByRequestCheckSDNStatusCode()
	{
		List<Organization> organizationList = insertOrganizations(TWO);

		prepareMockSdnCheckerBAC(organizationList.get(0));

		PagedInquiryRequest inquiryRequest = new PagedInquiryRequest();
		inquiryRequest.setStatusList(Arrays.asList(StatusEnum.ACTIVE));
		InternalResultsResponse<Organization> organizationResponse =
				getOrganizationDAC().fetchOrganizationByRequest(inquiryRequest);
		CommonTestRoutines.assertResultResponse(organizationResponse);

		// Verify the SDN status code should match.
		Assert.assertEquals(SDNStatusEnum.PENDING_INVESTIGATION, organizationResponse.getResultsList().get(0)
				.getSDNStatus());
		Assert.assertEquals(SDNStatusEnum.PENDING_INVESTIGATION, organizationResponse.getResultsList().get(1)
				.getSDNStatus());

	}

	/**
	 * *************************
	 * Private utility methods *
	 * *************************.
	 *
	 * @param numberOfOrgsToInsert the number of orgs to insert
	 * @return the list< organization>
	 */

	/*
	 * Creates Dummy Organizations for testing
	 * @param numberOfOrgsToInsert the number of orgs to insert
	 * @return the list< organization>
	 */
	private List<Organization> insertOrganizations(Integer numberOfOrgsToInsert)
	{
		return insertOrganizations(numberOfOrgsToInsert, false);
	}

	/*
	 * Creates Dummy Organizations for testing
	 * @param numberOfOrgsToInsert the number of orgs to insert
	 * @return the list< organization>
	 */
	private List<Organization> insertOrganizations(Integer numberOfOrgsToInsert, boolean uniqueIndexForOrganization)
	{
		ArrayList<Organization> orgList = new ArrayList<Organization>();

		for (int i = 0; i < numberOfOrgsToInsert; i++)
		{
			Organization organization = CommonTestRoutines.createDummyOrganization();

			if (uniqueIndexForOrganization)
			{
				organization.setName(organization.getName() + Integer.toString(i + 1));
			}

			InternalResultsResponse<Organization> response = getOrganizationDAC().insertOrganization(organization);

			if (!response.isInError())
			{
				orgList.add(organization);
			}
		}

		return orgList;
	}

	/**
	 * Test one Organization passed in against the same Organization read from database by id.
	 *
	 * @param organization the organization
	 */
	private void testOrganizationAgainstDatabaseById(Organization organization)
	{
		InternalResultsResponse<Organization> response =
				getOrganizationDAC().fetchOrganizationById(new FetchByIdRequest(organization.getId()));

		Assert.assertTrue("response.getResultsList().size() needs to be > 0", response.getResultsList().size() > 0);
		Assert.assertNotNull("response.getFirstResult() cannot be null", response.getFirstResult());

		Assert.assertEquals("Names need to be the same", organization.getName(), response.getFirstResult().getName());
		Assert.assertEquals("DBA Names need to be the same", organization.getDbaName(), response.getFirstResult()
				.getDbaName());
		Assert.assertEquals("EIN need to be the same", organization.getEmployerIdentificationNumber(), response
				.getFirstResult().getEmployerIdentificationNumber());
		Assert.assertEquals("Number of Locations need to be the same", organization.getNumberOfLocations(), response
				.getFirstResult().getNumberOfLocations());
		Assert.assertEquals("Status need to be the same", organization.getStatus(), response
				.getFirstResult().getStatus());
		Assert.assertEquals("Business Types need to be the same", organization.getBusinessType(), response
				.getFirstResult().getBusinessType());

		Assert.assertEquals("RiskLevel needs to be the same", organization.getRisk().getRiskLevel(), response
				.getFirstResult().getRisk().getRiskLevel());
		Assert.assertEquals("RiskLevelNote needs to be the same", organization.getRisk().getRiskLevelNote(), response
				.getFirstResult().getRisk().getRiskLevelNote());

		Assert.assertEquals("Number Of Employees need to be the same", organization.getNumberOfEmployees().getId(),
				response
						.getFirstResult().getNumberOfEmployees().getId());
		Assert.assertEquals("Number Of Migrant workers need to be the same", organization.getNumberOfMigrantWorkers()
				.getId(),
				response
						.getFirstResult().getNumberOfMigrantWorkers().getId());

		Assert.assertEquals("NAICS need to be the same", organization
				.getNorthAmericanIndustryClassificationSystemCode()
				.getId(), response.getFirstResult().getNorthAmericanIndustryClassificationSystemCode().getId());
		Assert.assertEquals("SIC need to be the same", organization.getStandardIndustrialClassificationCode().getId(),
				response.getFirstResult().getStandardIndustrialClassificationCode().getId());

		Assert.assertNotNull("Contact List cannot be null", response.getFirstResult().getContactList());
		Assert.assertTrue("Contact List needs to be > 0", response.getFirstResult().getContactList().size() > 0);
		Assert.assertTrue("Contact List needs to be = 6 but is " + response.getFirstResult().getContactList().size(),
				response
						.getFirstResult().getContactList().size() == SIX);

		// This routine reads from DB based on ids and compare fields
		for (Contact contact : organization.getContactList())
		{
			compareContactFields(contact);
		}
	}

	/**
	 * Test document against database by id.
	 *
	 * @param organization the organization
	 * @param document the document
	 */
	private void testDocumentAgainstDatabaseById(Organization organization, Document document)
	{
		InternalResultsResponse<Organization> response =
				getOrganizationDAC().fetchOrganizationById(new FetchByIdRequest(organization.getId()));

		// This routine reads from DB based on ids and compare fields
		for (Document businessDocument : response.getFirstResult().getDocumentList())
		{
			if (businessDocument.getId().equals(document.getId()))
			{
				Assert.assertEquals(businessDocument.getDocumentType().getId(), document.getDocumentType().getId());
				Assert.assertEquals(businessDocument.getFilingStatus(), document.getFilingStatus());
				Assert.assertEquals(businessDocument.getIsActionRequired(), document.getIsActionRequired());
				Assert.assertEquals(businessDocument.getNoteText(), document.getNoteText());
				Assert.assertEquals(businessDocument.getKeywordText(), document.getKeywordText());
			}
		}
	}

	/**
	 * Insert document.
	 *
	 * @param organizationId the organization id
	 * @return the internal results response< document>
	 */
	private InternalResultsResponse<Document> insertDocument(Integer organizationId)
	{
		// Insert a document
		DocumentMaintenanceRequest request = new DocumentMaintenanceRequest();
		Document document = CommonTestRoutines.createDummyDocument(BusinessTypeEnum.ORGANIZATION);
		document.getDocumentType().setId(ONE);
		document.setParentKey(organizationId);
		request.setDocument(document);
		InternalResultsResponse<Document> response = getOrganizationDAC().insertDocument(request);
		CommonTestRoutines.assertResultResponse(response);
		return response;
	}

	/**
	 * Assert attribute sort.
	 *
	 * @param attribute1 the attribute1
	 * @param attribute2 the attribute2
	 * @param direction the direction
	 */
	private void assertAttributeSort(String attribute1, String attribute2, Direction direction)
	{
		if (Direction.Ascending == direction)
		{
			Assert.assertTrue("Asc Sort Incorrect! Attribute Name " + attribute1 + " is greater than Attribute "
					+ attribute2, attribute1.compareTo(attribute2) <= 0);
		}
		else
		{
			Assert.assertTrue("Desc Sort Incorrect! Attribute Name " + attribute1 + " is less than Attribute "
					+ attribute2, attribute1.compareTo(attribute2) >= 0);
		}
	}

	/**
	 * Assert organization sort.
	 *
	 * @param organizationList the organization list
	 * @param fieldName the field name
	 * @param direction the direction
	 */
	private void assertOrganizationSort(List<Organization> organizationList, String fieldName, Direction direction)
	{
		boolean firstTime = true;
		Organization previousOrganization = null;
		for (Organization organization : organizationList)
		{
			if (firstTime)
			{
				previousOrganization = organization;
				firstTime = false;
			}
			else
			{
				if (fieldName.equals(NAME_SORT_FIELD))
				{
					assertAttributeSort(previousOrganization.getName(), organization.getName(), direction);
				}
				else
				{
					Assert.assertTrue("Unknown sort field: " + fieldName, false);
				}
			}
		}
	}

	/**
	 * Prepare the Sdn Checker BAC's Mock for Organization in order to return the expected result.
	 *
	 * @param business
	 */
	private void prepareMockSdnCheckerBAC(Business business)
	{
		SdnCheckerRequest<Business> request = new SdnCheckerRequest<Business>();
		request.setPersonOrBusiness(business);

		InternalResultsResponse<SdnStatusHistory> response = new InternalResultsResponse<SdnStatusHistory>();
		response.addResult(CommonSdnTestRoutines.createDummySdnStatusHistory(SDNStatusEnum.PENDING_INVESTIGATION));

		Mockito.when(getMockSdnCheckerBAC().fetchCurrentSdnStatusHistory(Matchers.any(SdnStatusHistoryRequest.class)))
				.thenReturn(response);

	}

}
