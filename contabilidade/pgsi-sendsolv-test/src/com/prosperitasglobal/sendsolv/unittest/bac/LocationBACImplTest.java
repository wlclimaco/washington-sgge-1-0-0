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

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.cbof.unittest.util.CommonTestRoutines;
import com.prosperitasglobal.sendsolv.bac.ILocationBAC;
import com.prosperitasglobal.sendsolv.bai.IMemberBAI;
import com.prosperitasglobal.sendsolv.dac.ILocationDAC;
import com.prosperitasglobal.sendsolv.dac.IParticipantIdDAC;
import com.prosperitasglobal.sendsolv.dac.IPersonDAC;
import com.prosperitasglobal.sendsolv.model.Business;
import com.prosperitasglobal.sendsolv.model.EmploymentInfo;
import com.prosperitasglobal.sendsolv.model.Location;
import com.prosperitasglobal.sendsolv.model.LocationParticipantId;
import com.prosperitasglobal.sendsolv.model.Member;
import com.prosperitasglobal.sendsolv.model.Organization;
import com.prosperitasglobal.sendsolv.model.Risk;
import com.prosperitasglobal.sendsolv.model.request.LocationMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.request.MemberMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.request.PagedInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.RiskMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.response.MemberResponse;
import com.prosperitasglobal.sendsolv.sdn.bac.ISdnCheckerBAC;
import com.prosperitasglobal.sendsolv.sdn.model.SdnStatusHistory;
import com.prosperitasglobal.sendsolv.sdn.model.request.SdnCheckerRequest;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResponse.Status;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * The Class LocationBACImplTest.
 */

@ContextConfiguration(locations = {
		"classpath:com/prosperitasglobal/sendsolv/unittest/conf/pgsi-sendsolv-resourcebundles-context.xml",
		"classpath:com/prosperitasglobal/sendsolv/unittest/bac/conf/locationbacimpltest.xml"})
public class LocationBACImplTest extends AbstractJUnit4SpringContextTests
{

	/** The Constant 99999. */
	private static final Integer INT_99999 = 99999;

	/** The Constant ONE. */
	private static final Integer ONE = new Integer(1);

	/** The mock location dac. */
	private ILocationDAC mockLocationDAC;

	/** The participant id dac. */
	private IParticipantIdDAC participantIdDAC;

	/** The location bac. */
	private ILocationBAC locationBAC;

	/** The mock sdn checker bac. */
	private ISdnCheckerBAC mockSdnCheckerBAC;

	private IMemberBAI memberBAI;

	/** The person dac. */
	private IPersonDAC personDAC;

	/**
	 * Sets the up.
	 */
	@Before
	public void setUp()
	{
		Mockito.reset(getMockLocationDAC());
		Mockito.reset(getParticipantIdDAC());
	}

	/**
	 * Gets the location bac.
	 *
	 * @return the location bac
	 */
	public ILocationBAC getLocationBAC()
	{
		return locationBAC;
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
	 * Sets the location bac.
	 *
	 * @param locationBAC the location bac
	 */
	@Resource
	public void setLocationBAC(ILocationBAC locationBAC)
	{
		this.locationBAC = locationBAC;
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
	 * Gets the mock location dac.
	 *
	 * @return the mock location dac
	 */
	public ILocationDAC getMockLocationDAC()
	{
		return mockLocationDAC;
	}

	/**
	 * Sets the mock location dac.
	 *
	 * @param mockDAC the mock location dac
	 */
	@Resource
	public void setMockLocationDAC(ILocationDAC mockDAC)
	{
		mockLocationDAC = mockDAC;
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
	 * @return the memberBAI
	 */
	public IMemberBAI getMemberBAI()
	{
		return memberBAI;
	}

	/**
	 * @param memberBAI the memberBAI to set
	 */
	@Resource
	public void setMemberBAI(IMemberBAI memberBAI)
	{
		this.memberBAI = memberBAI;
	}

	/**
	 * Gets the person dac.
	 *
	 * @return the personDAC
	 */
	public IPersonDAC getPersonDAC()
	{
		return personDAC;
	}

	/**
	 * Sets the person dac.
	 *
	 * @param personDAC the personDAC to set
	 */
	@Resource
	public void setPersonDAC(IPersonDAC personDAC)
	{
		this.personDAC = personDAC;
	}

	/**
	 * Test insert location.
	 */
	@Test
	public void testInsertLocation()
	{
		LocationMaintenanceRequest request = new LocationMaintenanceRequest();

		Location location = CommonTestRoutines.createDummyLocation(CommonTestRoutines.createDummyOrganization());
		request.setLocation(location);

		CommonTestRoutines.createDummyUserContext(request);

		Mockito.when(getMockLocationDAC().insertLocation(location)).thenReturn(
				new InternalResultsResponse<Location>(Arrays.asList(new Location())));

		Mockito.when(getParticipantIdDAC().fetchNextLocationParticipantId()).thenReturn(
				new InternalResultsResponse<LocationParticipantId>(Arrays.asList(new LocationParticipantId('A', 'A', 1,
						INT_99999))));

		mockCheckSdn();

		InternalResultsResponse<Location> response = getLocationBAC().insertLocation(request);

		CommonTestRoutines.assertResultResponse(response);

		// ensures the insertLocation method on the mock was called 1 time.
		Mockito.verify(getMockLocationDAC()).insertLocation(location);

	}

	/**
	 * Test insert location with error.
	 */
	@Test
	public void testInsertLocationWithError()
	{
		LocationMaintenanceRequest request = new LocationMaintenanceRequest();

		Location location = CommonTestRoutines.createDummyLocation(CommonTestRoutines.createDummyOrganization());
		request.setLocation(location);

		CommonTestRoutines.createDummyUserContext(request);

		InternalResultsResponse<Location> response = new InternalResultsResponse<Location>();
		response.setStatus(Status.NoRowsInsertedError);

		Mockito.when(getMockLocationDAC().insertLocation(location)).thenReturn(response);

		Mockito.when(getParticipantIdDAC().fetchNextLocationParticipantId()).thenReturn(
				new InternalResultsResponse<LocationParticipantId>(Arrays.asList(new LocationParticipantId('A', 'A', 1,
						INT_99999))));

		InternalResponse results = getLocationBAC().insertLocation(request);

		Assert.assertEquals(Status.NoRowsInsertedError, results.getStatus());
		Mockito.verify(getMockLocationDAC()).insertLocation(location);
		Mockito.verify(getParticipantIdDAC()).fetchNextLocationParticipantId();
	}

	/**
	 * Test update location.
	 */
	@Test
	public void testUpdateLocation()
	{
		Location location = CommonTestRoutines.createDummyLocation(CommonTestRoutines.createDummyOrganization());

		LocationMaintenanceRequest request = new LocationMaintenanceRequest();
		request.setLocation(location);

		CommonTestRoutines.createDummyUserContext(request);

		Mockito.when(getMockLocationDAC().updateLocation(location)).thenReturn(
				new InternalResultsResponse<Location>(Arrays.asList(new Location())));

		mockCheckSdn();

		InternalResultsResponse<Location> response = getLocationBAC().updateLocation(request);

		CommonTestRoutines.assertResultResponse(response);

		Mockito.verify(getMockLocationDAC()).updateLocation(location);
	}

	/**
	 * Test update location with error.
	 */
	@Test
	public void testUpdateLocationWithError()
	{
		Location location = CommonTestRoutines.createDummyLocation(CommonTestRoutines.createDummyOrganization());

		LocationMaintenanceRequest request = new LocationMaintenanceRequest();
		request.setLocation(location);

		CommonTestRoutines.createDummyUserContext(request);

		InternalResultsResponse<Location> response = new InternalResultsResponse<Location>();
		response.setStatus(Status.NoRowsUpdatedError);

		Mockito.when(getMockLocationDAC().updateLocation(location)).thenReturn(response);

		InternalResponse results = getLocationBAC().updateLocation(request);

		Assert.assertEquals(Status.NoRowsUpdatedError, results.getStatus());
		Mockito.verify(getMockLocationDAC()).updateLocation(location);
	}

	/**
	 * Test delete location.
	 */
	@Test
	public void testDeleteLocation()
	{
		Location location = new Location();
		LocationMaintenanceRequest request = new LocationMaintenanceRequest();
		request.setLocation(location);

		Mockito.when(getMockLocationDAC().deleteLocation(location)).thenReturn(new InternalResponse());

		InternalResponse response = getLocationBAC().deleteLocation(request);

		CommonTestRoutines.assertResponse(response);

		Mockito.verify(getMockLocationDAC()).deleteLocation(location);
	}

	/**
	 * Test delete location with error.
	 */
	@Test
	public void testDeleteLocationWithError()
	{
		Location location = new Location();
		LocationMaintenanceRequest request = new LocationMaintenanceRequest();
		request.setLocation(location);

		Mockito.when(getMockLocationDAC().deleteLocation(location)).thenReturn(new InternalResponse());

		Mockito.when(getMockLocationDAC().deleteLocation(location)).thenReturn(
				new InternalResponse(Status.NoRowsRemovedError));

		InternalResponse results = getLocationBAC().deleteLocation(request);

		Assert.assertEquals(Status.NoRowsRemovedError, results.getStatus());
		Mockito.verify(getMockLocationDAC()).deleteLocation(location);
	}

	/**
	 * Test fetch location by request.
	 */
	@Test
	public void testFetchLocationByRequest()
	{
		PagedInquiryRequest request = new PagedInquiryRequest();

		Mockito.when(getMockLocationDAC().fetchLocationByRequest(request)).thenReturn(
				new InternalResultsResponse<Location>(
						Arrays.asList(new Location(), new Location(), new Location(), new Location())));

		InternalResultsResponse<Location> response = getLocationBAC().fetchLocationByRequest(request);

		CommonTestRoutines.assertResultResponse(response);

		Mockito.verify(getMockLocationDAC()).fetchLocationByRequest(request);
	}

	/**
	 * Test fetch location by request with error.
	 */
	@Test
	public void testFetchLocationByRequestWithError()
	{
		PagedInquiryRequest request = new PagedInquiryRequest();
		InternalResultsResponse<Location> response = new InternalResultsResponse<Location>();
		response.setStatus(Status.NoRowsFoundError);

		Mockito.when(getMockLocationDAC().fetchLocationByRequest(request)).thenReturn(response);

		response = getLocationBAC().fetchLocationByRequest(request);

		Assert.assertEquals(Status.NoRowsFoundError, response.getStatus());

		Mockito.verify(getMockLocationDAC()).fetchLocationByRequest(request);
	}

	/**
	 * Test fetch location by id.
	 */
	@Test
	public void testFetchLocationById()
	{
		FetchByIdRequest request = new FetchByIdRequest(ONE);

		Mockito.when(getMockLocationDAC().fetchLocationById(request)).thenReturn(
				new InternalResultsResponse<Location>(Arrays.asList(new Location())));

		InternalResultsResponse<Location> response = getLocationBAC().fetchLocationById(request);

		CommonTestRoutines.assertResultResponse(response);

		Mockito.verify(getMockLocationDAC()).fetchLocationById(request);
	}

	/**
	 * Test update location risk.
	 */
	@Test
	public void testUpdateLocationRisk()
	{
		RiskMaintenanceRequest request = new RiskMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		Risk risk = CommonTestRoutines.createDummyRisk();

		request.setRisk(risk);

		Mockito.when(getMockLocationDAC().updateRisk(request)).thenReturn(
				new InternalResultsResponse<Risk>(Arrays.asList(new Risk())));

		InternalResultsResponse<Risk> response = getLocationBAC().updateRisk(request);

		CommonTestRoutines.assertResultResponse(response);

		Mockito.verify(getMockLocationDAC()).updateRisk(request);
	}

	/**
	 * Test fetch location by id with error.
	 */
	@Test
	public void testFetchLocationByIdWithError()
	{
		FetchByIdRequest request = new FetchByIdRequest(ONE);

		InternalResultsResponse<Location> response = new InternalResultsResponse<Location>();
		response.setStatus(Status.NoRowsFoundError);

		Mockito.when(getMockLocationDAC().fetchLocationById(request)).thenReturn(response);

		Assert.assertNotNull(getLocationBAC().fetchLocationById(request));

		Assert.assertEquals(Status.NoRowsFoundError, response.getStatus());

		Mockito.verify(getMockLocationDAC()).fetchLocationById(request);
	}

	/**
	 * Test apply status.
	 */
	@Test
	public void testApplyStatus()
	{
		LocationMaintenanceRequest request = new LocationMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);
		Location location = CommonTestRoutines.createDummyLocation(CommonTestRoutines.createDummyOrganization());
		request.setLocation(location);

		mockFetchEmploymentInfoByLocationId();
		mockApplyMemberStatus();
		mockFetchMemberById();

		Mockito.when(getMockLocationDAC().applyLocationStatus(location)).thenReturn(new InternalResponse());

		InternalResponse response = getLocationBAC().applyStatus(request);

		CommonTestRoutines.assertResponse(response);

		Mockito.verify(getMockLocationDAC()).applyLocationStatus(location);
	}

	/**
	 * Test apply status exception.
	 */
	@Test
	public void testApplyStatusException()
	{
		LocationMaintenanceRequest request = new LocationMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);
		Location location = CommonTestRoutines.createDummyLocation(CommonTestRoutines.createDummyOrganization());
		request.setLocation(location);

		// test for bad return
		Mockito.when(getMockLocationDAC().applyLocationStatus(location)).thenReturn(
				new InternalResponse(Status.NoRowsRemovedError));

		InternalResponse results = getLocationBAC().applyStatus(request);
		Assert.assertEquals(Status.NoRowsRemovedError, results.getStatus());

		Mockito.verify(getMockLocationDAC()).applyLocationStatus(location);
	}

	/**
	 * Test apply status exception fetch employment info by location id failed.
	 */
	@Test
	public void testApplyStatusExceptionFetchEmploymentInfoByLocationIdFailed()
	{
		LocationMaintenanceRequest request = new LocationMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);
		Location location = CommonTestRoutines.createDummyLocation(CommonTestRoutines.createDummyOrganization());
		request.setLocation(location);

		mockApplyMemberStatus();
		mockFetchMemberById();

		Mockito.when(getMockLocationDAC().applyLocationStatus(location)).thenReturn(new InternalResponse());

		InternalResultsResponse<EmploymentInfo> internalResultResponse = new InternalResultsResponse<EmploymentInfo>();
		internalResultResponse.setStatus(Status.NoRowsFoundError);
		Mockito.when(getPersonDAC().fetchEmploymentInfoByLocationId(Matchers.any(Integer.class))).thenReturn(
				internalResultResponse);

		InternalResponse response = getLocationBAC().applyStatus(request);

		Assert.assertEquals(Status.NoRowsFoundError, response.getStatus());
		CommonTestRoutines.assertResponse(response);

		Mockito.verify(getMockLocationDAC()).applyLocationStatus(location);
	}

	/**
	 * Test apply status exception apply member status failed.
	 */
	@Test
	public void testApplyStatusExceptionApplyMemberStatusFailed()
	{
		LocationMaintenanceRequest request = new LocationMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);
		Location location = CommonTestRoutines.createDummyLocation(CommonTestRoutines.createDummyOrganization());
		request.setLocation(location);

		mockFetchEmploymentInfoByLocationId();
		mockFetchMemberById();

		Mockito.when(getMockLocationDAC().applyLocationStatus(location)).thenReturn(new InternalResponse());

		MemberResponse memberResponse = new MemberResponse();
		memberResponse.setOperationSuccess(Status.ExceptionError);

		Mockito.when(getMemberBAI().applyStatus(Matchers.any(MemberMaintenanceRequest.class)))
		.thenReturn(memberResponse);

		InternalResponse response = getLocationBAC().applyStatus(request);

		Assert.assertTrue("The message should bring a error.", response.isInError());
		CommonTestRoutines.assertResponse(response);

		Mockito.verify(getMockLocationDAC()).applyLocationStatus(location);
	}

	/**
	 * Test apply status exception fetch member by id failed.
	 */
	@Test
	public void testApplyStatusExceptionFetchMemberByIdFailed()
	{
		LocationMaintenanceRequest request = new LocationMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);
		Location location = CommonTestRoutines.createDummyLocation(CommonTestRoutines.createDummyOrganization());
		request.setLocation(location);

		mockFetchEmploymentInfoByLocationId();
		mockApplyMemberStatus();

		Mockito.when(getMockLocationDAC().applyLocationStatus(location)).thenReturn(new InternalResponse());

		InternalResultsResponse<Member> internalResultResponse = new InternalResultsResponse<Member>();
		internalResultResponse.setStatus(Status.ExceptionError);
		Mockito.when(getPersonDAC().fetchMemberById(Matchers.any(FetchByIdRequest.class))).thenReturn(
				internalResultResponse);

		InternalResponse response = getLocationBAC().applyStatus(request);

		Assert.assertEquals(Status.ExceptionError, response.getStatus());
		CommonTestRoutines.assertResponse(response);

		Mockito.verify(getMockLocationDAC()).applyLocationStatus(location);
	}

	/**
	 * Mock check sdn.
	 */
	private void mockCheckSdn()
	{
		SdnCheckerRequest<Business> request = new SdnCheckerRequest<Business>();

		// -- create the organization
		Organization organization = CommonTestRoutines.createDummyOrganization();

		// -- create the location
		Location location = CommonTestRoutines.createDummyLocation(organization);

		request.setPersonOrBusiness(location);

		InternalResultsResponse<SdnStatusHistory> response = new InternalResultsResponse<SdnStatusHistory>();

		response.setStatus(Status.OperationSuccess);
		response.addResult(new SdnStatusHistory());

		Mockito.when(getMockSdnCheckerBAC().checkBusinessSdn(request))
		.thenReturn(response);
	}

	/**
	 * Mock apply member status.
	 */
	private void mockApplyMemberStatus()
	{
		MemberResponse response = new MemberResponse();
		response.setOperationSuccess(Status.OperationSuccess);

		Mockito.when(getMemberBAI().applyStatus(Matchers.any(MemberMaintenanceRequest.class)))
		.thenReturn(response);

	}

	/**
	 * Mock fetch employment info by location id.
	 */
	private void mockFetchEmploymentInfoByLocationId()
	{
		Mockito.when(getPersonDAC().fetchEmploymentInfoByLocationId(Matchers.any(Integer.class))).thenReturn(
				new InternalResultsResponse<EmploymentInfo>(Arrays.asList(CommonTestRoutines
						.createDummyEmploymentInfo())));

	}

	/**
	 * Mock fetch member by id.
	 */
	private void mockFetchMemberById()
	{
		Mockito.when(getPersonDAC().fetchMemberById(Matchers.any(FetchByIdRequest.class))).thenReturn(
				new InternalResultsResponse<Member>(Arrays.asList(CommonTestRoutines.createDummyMember())));

	}

}
