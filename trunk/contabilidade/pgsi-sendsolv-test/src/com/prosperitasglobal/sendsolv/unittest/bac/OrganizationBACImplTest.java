package com.prosperitasglobal.sendsolv.unittest.bac;

import java.util.Arrays;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.prosperitasglobal.cbof.model.BusinessTypeEnum;
import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.cbof.unittest.util.CommonTestRoutines;
import com.prosperitasglobal.sendsolv.bac.ILocationBAC;
import com.prosperitasglobal.sendsolv.bac.IOrganizationBAC;
import com.prosperitasglobal.sendsolv.bai.ILocationBAI;
import com.prosperitasglobal.sendsolv.dac.IOrganizationDAC;
import com.prosperitasglobal.sendsolv.dac.IParticipantIdDAC;
import com.prosperitasglobal.sendsolv.model.Business;
import com.prosperitasglobal.sendsolv.model.Document;
import com.prosperitasglobal.sendsolv.model.Location;
import com.prosperitasglobal.sendsolv.model.Organization;
import com.prosperitasglobal.sendsolv.model.OrganizationParticipantId;
import com.prosperitasglobal.sendsolv.model.Risk;
import com.prosperitasglobal.sendsolv.model.request.DocumentMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.request.LocationMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.request.OrganizationMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.request.PagedInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.RiskMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.response.LocationResponse;
import com.prosperitasglobal.sendsolv.sdn.bac.ISdnCheckerBAC;
import com.prosperitasglobal.sendsolv.sdn.model.SdnStatusHistory;
import com.prosperitasglobal.sendsolv.sdn.model.request.SdnCheckerRequest;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResponse.Status;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * The Class OrganizationBACImplTest.
 */
@ContextConfiguration(locations = {
		"classpath:com/prosperitasglobal/sendsolv/unittest/conf/pgsi-sendsolv-resourcebundles-context.xml",
		"classpath:com/prosperitasglobal/sendsolv/unittest/bac/conf/organizationbacimpltest.xml"})
public class OrganizationBACImplTest extends AbstractJUnit4SpringContextTests
{

	/** The Constant 99999. */
	private static final Integer INT_99999 = 99999;

	/** The Constant TWO. */
	private static final Integer TWO = 2;

	/** The Constant ONE. */
	private static final Integer ONE = new Integer(1);

	/** The mock organization dac. */
	private IOrganizationDAC mockOrganizationDAC;

	/** The mock sdn checker bac. */
	private ISdnCheckerBAC mockSdnCheckerBAC;

	/** The organization bac. */
	private IOrganizationBAC organizationBAC;

	/** The participant id dac. */
	private IParticipantIdDAC participantIdDAC;

	/** The location bai. */
	private ILocationBAI locationBAI;

	/** The location bac. */
	private ILocationBAC locationBAC;

	/**
	 * Setup.
	 */
	@Before
	public void setUp()
	{
		Mockito.reset(getMockOrganizationDAC());
		Mockito.reset(getParticipantIdDAC());
	}

	/**
	 * Gets the organization bac.
	 *
	 * @return the organization bac
	 */
	public IOrganizationBAC getOrganizationBAC()
	{
		return organizationBAC;
	}

	/**
	 * Gets the participant id dac.
	 *
	 * @return the participant id dac
	 */
	public IParticipantIdDAC getParticipantIdDAC()
	{
		return participantIdDAC;
	}

	/**
	 * Sets the organization bac.
	 *
	 * @param organizationBAC the organization bac
	 */
	@Resource
	public void setOrganizationBAC(IOrganizationBAC organizationBAC)
	{
		this.organizationBAC = organizationBAC;
	}

	/**
	 * Sets the participant id dac.
	 *
	 * @param participantIdDAC the participant id dac
	 */
	@Resource
	public void setParticipantIdDAC(IParticipantIdDAC participantIdDAC)
	{
		this.participantIdDAC = participantIdDAC;
	}

	/**
	 * Gets the mock organization dac.
	 *
	 * @return the mock organization dac
	 */
	public IOrganizationDAC getMockOrganizationDAC()
	{
		return mockOrganizationDAC;
	}

	/**
	 * Sets the mock organization dac.
	 *
	 * @param mockDAC the mock organization dac
	 */
	@Resource
	public void setMockOrganizationDAC(IOrganizationDAC mockDAC)
	{
		mockOrganizationDAC = mockDAC;
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
	 * @return the locationBAI
	 */
	public ILocationBAI getLocationBAI()
	{
		return locationBAI;
	}

	/**
	 * @param locationBAI the locationBAI to set
	 */
	@Resource
	public void setLocationBAI(ILocationBAI locationBAI)
	{
		this.locationBAI = locationBAI;
	}

	/**
	 * @return the locationBAC
	 */
	public ILocationBAC getLocationBAC()
	{
		return locationBAC;
	}

	/**
	 * @param locationBAC the locationBAC to set
	 */
	@Resource
	public void setLocationBAC(ILocationBAC locationBAC)
	{
		this.locationBAC = locationBAC;
	}

	/**
	 * Test insert organization.
	 */
	@Test
	public void testInsertOrganization()
	{
		OrganizationMaintenanceRequest request = new OrganizationMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		Organization organization = CommonTestRoutines.createDummyOrganization();
		organization.setId(ONE);

		request.setOrganization(organization);

		Mockito.when(getMockOrganizationDAC().insertOrganization(organization)).thenReturn(
				new InternalResultsResponse<Organization>(Arrays.asList(new Organization())));

		Mockito.when(getParticipantIdDAC().fetchNextOrganizationParticipantId()).thenReturn(
				new InternalResultsResponse<OrganizationParticipantId>(Arrays.asList(new OrganizationParticipantId('A',
						'A', 1, INT_99999))));
		mockCheckSdn();

		InternalResultsResponse<Organization> response = getOrganizationBAC().insertOrganization(request);

		CommonTestRoutines.assertResultResponse(response);

		// ensures the insertOrganization method on the mock was called 1 time.
		Mockito.verify(getMockOrganizationDAC()).insertOrganization(organization);
	}

	/**
	 * Test update organization risk.
	 */
	@Test
	public void testUpdateOrganizationRisk()
	{
		RiskMaintenanceRequest request = new RiskMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		Risk risk = CommonTestRoutines.createDummyRisk();

		request.setRisk(risk);

		Mockito.when(getMockOrganizationDAC().updateRisk(request)).thenReturn(
				new InternalResultsResponse<Risk>(Arrays.asList(new Risk())));

		InternalResultsResponse<Risk> response = getOrganizationBAC().updateRisk(request);

		CommonTestRoutines.assertResultResponse(response);

		Mockito.verify(getMockOrganizationDAC()).updateRisk(request);
	}

	/**
	 * Test update organization.
	 */
	@Test
	public void testUpdateOrganization()
	{
		OrganizationMaintenanceRequest request = new OrganizationMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		Organization organization = CommonTestRoutines.createDummyOrganization();
		organization.setId(ONE);

		request.setOrganization(organization);

		Mockito.when(getMockOrganizationDAC().updateOrganization(organization)).thenReturn(
				new InternalResultsResponse<Organization>(Arrays.asList(new Organization())));

		mockCheckSdn();

		InternalResultsResponse<Organization> response = getOrganizationBAC().updateOrganization(request);

		CommonTestRoutines.assertResultResponse(response);

		Mockito.verify(getMockOrganizationDAC()).updateOrganization(organization);
	}

	/**
	 * Test update organization exception.
	 */
	@Test
	public void testUpdateOrganizationException()
	{
		Organization organization = new Organization();
		organization.setStatusValue(ONE);
		organization.setBusinessTypeValue(ONE);

		OrganizationMaintenanceRequest request = new OrganizationMaintenanceRequest();
		request.setOrganization(organization);

		CommonTestRoutines.createDummyUserContext(request);

		InternalResultsResponse<Organization> response = new InternalResultsResponse<Organization>();
		response.setStatus(Status.NoRowsUpdatedError);

		// test for bad return
		Mockito.when(getMockOrganizationDAC().updateOrganization(organization)).thenReturn(response);

		InternalResponse results = getOrganizationBAC().updateOrganization(request);

		Assert.assertEquals(Status.NoRowsUpdatedError, results.getStatus());

		Mockito.verify(getMockOrganizationDAC()).updateOrganization(organization);
	}

	/**
	 * Test delete organization.
	 */
	@Test
	public void testDeleteOrganization()
	{
		Organization organization = new Organization();
		OrganizationMaintenanceRequest request = new OrganizationMaintenanceRequest();
		request.setOrganization(organization);

		Mockito.when(getMockOrganizationDAC().deleteOrganization(organization)).thenReturn(new InternalResponse());

		InternalResponse response = getOrganizationBAC().deleteOrganization(request);

		CommonTestRoutines.assertResponse(response);

		Mockito.verify(getMockOrganizationDAC()).deleteOrganization(organization);
	}

	/**
	 * Test delete organization exception.
	 */
	@Test
	public void testDeleteOrganizationException()
	{
		Organization organization = new Organization();
		OrganizationMaintenanceRequest request = new OrganizationMaintenanceRequest();
		request.setOrganization(organization);

		// test for bad return
		Mockito.when(getMockOrganizationDAC().deleteOrganization(organization)).thenReturn(
				new InternalResponse(Status.NoRowsRemovedError));

		InternalResponse results = getOrganizationBAC().deleteOrganization(request);
		Assert.assertEquals(Status.NoRowsRemovedError, results.getStatus());

		Mockito.verify(getMockOrganizationDAC()).deleteOrganization(organization);
	}

	/**
	 * Test fetch organizations by request.
	 */
	@Test
	public void testFetchOrganizationsByRequest()
	{
		PagedInquiryRequest request = new PagedInquiryRequest();

		Mockito.when(getMockOrganizationDAC().fetchOrganizationByRequest(request)).thenReturn(
				new InternalResultsResponse<Organization>(
						Arrays.asList(new Organization(), new Organization(), new Organization(), new Organization())));

		InternalResultsResponse<Organization> results = getOrganizationBAC().fetchOrganizationByRequest(request);

		CommonTestRoutines.assertResultResponse(results);

		Mockito.verify(getMockOrganizationDAC()).fetchOrganizationByRequest(request);
	}

	/**
	 * Test fetch organization by id.
	 */
	@Test
	public void testFetchOrganizationById()
	{
		FetchByIdRequest request = new FetchByIdRequest(ONE);

		Mockito.when(getMockOrganizationDAC().fetchOrganizationById(request)).thenReturn(
				new InternalResultsResponse<Organization>(Arrays.asList(new Organization())));

		InternalResultsResponse<Organization> response = getOrganizationBAC().fetchOrganizationById(request);

		CommonTestRoutines.assertResultResponse(response);

		Mockito.verify(getMockOrganizationDAC()).fetchOrganizationById(request);
	}

	/**
	 * Test insert document.
	 */
	@Test
	public void testInsertDocument()
	{
		DocumentMaintenanceRequest request = new DocumentMaintenanceRequest();
		Document document = CommonTestRoutines.createDummyDocument(BusinessTypeEnum.ORGANIZATION);
		request.setDocument(document);

		Mockito.when(getMockOrganizationDAC().insertDocument(request)).thenReturn(
				new InternalResultsResponse<Document>(document));

		InternalResultsResponse<Document> response = getOrganizationBAC().insertDocument(request);

		CommonTestRoutines.assertResultResponse(response);

		Mockito.verify(getMockOrganizationDAC()).insertDocument(request);
	}

	/**
	 * Test insert document exception.
	 */
	@Test
	public void testInsertDocumentException()
	{
		DocumentMaintenanceRequest request = new DocumentMaintenanceRequest();
		Document document = CommonTestRoutines.createDummyDocument(BusinessTypeEnum.ORGANIZATION);
		request.setDocument(document);

		InternalResultsResponse<Document> internalResponse = new InternalResultsResponse<Document>();
		internalResponse.setStatus(Status.NoRowsInsertedError);

		Mockito.when(getMockOrganizationDAC().insertDocument(request)).thenReturn(internalResponse);

		InternalResultsResponse<Document> response = getOrganizationBAC().insertDocument(request);

		Assert.assertEquals(Status.NoRowsInsertedError, response.getStatus());

		Mockito.verify(getMockOrganizationDAC()).insertDocument(request);
	}

	/**
	 * Test update document.
	 */
	@Test
	public void testUpdateDocument()
	{
		DocumentMaintenanceRequest request = new DocumentMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		Document document = new Document();
		document.setId(TWO);
		request.setDocument(document);

		Mockito.when(getMockOrganizationDAC().updateDocument(request)).thenReturn(
				new InternalResultsResponse<Document>(Arrays.asList(new Document())));

		InternalResultsResponse<Document> response = getOrganizationBAC().updateDocument(request);

		CommonTestRoutines.assertResultResponse(response);

		Mockito.verify(getMockOrganizationDAC()).updateDocument(request);
	}

	/**
	 * Test update document exception.
	 */
	@Test
	public void testUpdateDocumentException()
	{
		DocumentMaintenanceRequest request = new DocumentMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		Document document = new Document();
		document.setId(TWO);
		request.setDocument(document);

		InternalResultsResponse<Document> response = new InternalResultsResponse<Document>();
		response.setStatus(Status.NoRowsUpdatedError);

		// test for bad return
		Mockito.when(getMockOrganizationDAC().updateDocument(request)).thenReturn(response);
		InternalResponse results = getOrganizationBAC().updateDocument(request);
		Assert.assertEquals(Status.NoRowsUpdatedError, results.getStatus());

		Mockito.verify(getMockOrganizationDAC()).updateDocument(request);
	}

	/**
	 * Test delete document.
	 */
	@Test
	public void testDeleteDocument()
	{
		DocumentMaintenanceRequest request = new DocumentMaintenanceRequest();
		Document document = new Document();
		request.setDocument(document);

		Mockito.when(getMockOrganizationDAC().deleteDocument(request)).thenReturn(new InternalResponse());

		InternalResponse response = getOrganizationBAC().deleteDocument(request);

		CommonTestRoutines.assertResponse(response);

		Mockito.verify(getMockOrganizationDAC()).deleteDocument(request);
	}

	/**
	 * Test delete document exception.
	 */
	@Test
	public void testDeleteDocumentException()
	{
		DocumentMaintenanceRequest request = new DocumentMaintenanceRequest();
		Document document = new Document();
		request.setDocument(document);

		// test for bad return
		Mockito.when(getMockOrganizationDAC().deleteDocument(request)).thenReturn(
				new InternalResponse(Status.NoRowsRemovedError));

		InternalResponse results = getOrganizationBAC().deleteDocument(request);
		Assert.assertEquals(Status.NoRowsRemovedError, results.getStatus());

		Mockito.verify(getMockOrganizationDAC()).deleteDocument(request);
	}

	/**
	 * Test apply status.
	 */
	@Test
	public void testApplyStatus()
	{
		OrganizationMaintenanceRequest request = new OrganizationMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);
		Organization organization = CommonTestRoutines.createDummyOrganization();
		organization.setLocationList(Arrays.asList(CommonTestRoutines.createDummyLocation(organization)));
		request.setOrganization(organization);

		mockApplyLocationStatus();
		mockFetchLocationByRequest();

		Mockito.when(getMockOrganizationDAC().applyOrganizationStatus(organization)).thenReturn(new InternalResponse());

		InternalResponse response = getOrganizationBAC().applyOrganizationStatus(request);

		CommonTestRoutines.assertResponse(response);

		Mockito.verify(getMockOrganizationDAC()).applyOrganizationStatus(organization);
	}

	/**
	 * Test apply status exception.
	 */
	@Test
	public void testApplyStatusException()
	{
		OrganizationMaintenanceRequest request = new OrganizationMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);
		Organization organization = CommonTestRoutines.createDummyOrganization();
		request.setOrganization(organization);

		// test for bad return
		Mockito.when(getMockOrganizationDAC().applyOrganizationStatus(organization)).thenReturn(
				new InternalResponse(Status.ExceptionError));

		InternalResponse results = getOrganizationBAC().applyOrganizationStatus(request);
		Assert.assertEquals(Status.ExceptionError, results.getStatus());

		Mockito.verify(getMockOrganizationDAC()).applyOrganizationStatus(organization);
	}

	/**
	 * Test apply status exception apply location status failed.
	 */
	@Test
	public void testApplyStatusExceptionApplyLocationStatusFailed()
	{
		OrganizationMaintenanceRequest request = new OrganizationMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);
		Organization organization = CommonTestRoutines.createDummyOrganization();
		organization.setLocationList(Arrays.asList(CommonTestRoutines.createDummyLocation(organization)));
		request.setOrganization(organization);

		Mockito.when(getMockOrganizationDAC().applyOrganizationStatus(organization)).thenReturn(new InternalResponse());

		mockFetchLocationByRequest();

		LocationResponse locResponse = new LocationResponse();
		locResponse.setOperationSuccess(Status.ExceptionError);

		Mockito.when(getLocationBAI().applyStatus(Matchers.any(LocationMaintenanceRequest.class)))
				.thenReturn(locResponse);

		InternalResponse response = getOrganizationBAC().applyOrganizationStatus(request);

		Assert.assertTrue("The message should bring a error.", response.isInError());
		CommonTestRoutines.assertResponse(response);

		Mockito.verify(getMockOrganizationDAC()).applyOrganizationStatus(organization);
	}

	/**
	 * Mock check sdn.
	 */
	private void mockCheckSdn()
	{
		SdnCheckerRequest<Business> request = new SdnCheckerRequest<Business>();
		Organization organization = CommonTestRoutines.createDummyOrganization();
		request.setPersonOrBusiness(organization);

		InternalResultsResponse<SdnStatusHistory> response = new InternalResultsResponse<SdnStatusHistory>();

		response.setStatus(Status.OperationSuccess);
		response.addResult(new SdnStatusHistory());

		Mockito.when(getMockSdnCheckerBAC().checkBusinessSdn(request))
				.thenReturn(response);
	}

	/**
	 * Mock apply location status.
	 */
	private void mockApplyLocationStatus()
	{
		LocationResponse response = new LocationResponse();
		response.isOperationSuccess();

		Mockito.when(getLocationBAI().applyStatus(Matchers.any(LocationMaintenanceRequest.class)))
				.thenReturn(response);

	}

	private void mockFetchLocationByRequest()
	{
		InternalResultsResponse<Location> locationResponse = new InternalResultsResponse<Location>();
		locationResponse
				.addResult(CommonTestRoutines.createDummyLocation(CommonTestRoutines.createDummyOrganization()));

		Mockito.when(getLocationBAC().fetchLocationByRequest(Matchers.any(PagedInquiryRequest.class)))
				.thenReturn(locationResponse);
	}
}
