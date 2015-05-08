package com.prosperitasglobal.sendsolv.unittest.bac;

import java.util.Arrays;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.prosperitasglobal.cbof.model.BusinessTypeEnum;
import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.cbof.unittest.util.CommonTestRoutines;
import com.prosperitasglobal.sendsolv.bac.ISuspiciousActivityBAC;
import com.prosperitasglobal.sendsolv.dac.IParticipantIdDAC;
import com.prosperitasglobal.sendsolv.dac.ISuspiciousActivityDAC;
import com.prosperitasglobal.sendsolv.model.SuspiciousActivity;
import com.prosperitasglobal.sendsolv.model.SuspiciousActivityParticipantId;
import com.prosperitasglobal.sendsolv.model.request.SarInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.SarMaintenanceRequest;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResponse.Status;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * The Class SuspiciousActivityBACImplTest.
 */
@ContextConfiguration(locations = {
		"classpath:com/prosperitasglobal/sendsolv/unittest/conf/pgsi-sendsolv-resourcebundles-context.xml",
"classpath:com/prosperitasglobal/sendsolv/unittest/bac/conf/suspiciousactivitybacimpltest.xml"})
public class SuspiciousActivityBACImplTest extends AbstractJUnit4SpringContextTests
{

	/** The Constant ONE. */
	private static final Integer ONE = 1;

	/** The Constant 99999. */
	private static final Integer INT_99999 = 99999;

	/** The mock suspicious activity dac. */
	private ISuspiciousActivityDAC mockSuspiciousActivityDAC;

	/** The suspicious activity bac. */
	private ISuspiciousActivityBAC suspiciousActivityBAC;

	/** The participant id dac. */
	private IParticipantIdDAC participantIdDAC;

	/**
	 * Gets the participant id dac.
	 *
	 * @return the participantIdDAC
	 */
	public IParticipantIdDAC getParticipantIdDAC()
	{
		return participantIdDAC;
	}

	/**
	 * Sets the participant id dac.
	 *
	 * @param participantIdDAC the participantIdDAC to set
	 */
	@Resource
	public void setParticipantIdDAC(IParticipantIdDAC participantIdDAC)
	{
		this.participantIdDAC = participantIdDAC;
	}

	/**
	 * Gets the mock suspicious activity dac.
	 *
	 * @return the mockSuspiciousActivityDAC
	 */
	public ISuspiciousActivityDAC getMockSuspiciousActivityDAC()
	{
		return mockSuspiciousActivityDAC;
	}

	/**
	 * Sets the mock suspicious activity dac.
	 *
	 * @param mockSuspiciousActivityDAC the mockSuspiciousActivityDAC to set
	 */
	@Resource
	public void setMockSuspiciousActivityDAC(ISuspiciousActivityDAC mockSuspiciousActivityDAC)
	{
		this.mockSuspiciousActivityDAC = mockSuspiciousActivityDAC;
	}

	/**
	 * Gets the suspicious activity bac.
	 *
	 * @return the suspiciousActivityBAC
	 */
	public ISuspiciousActivityBAC getSuspiciousActivityBAC()
	{
		return suspiciousActivityBAC;
	}

	/**
	 * Sets the suspicious activity bac.
	 *
	 * @param suspiciousActivityBAC the suspiciousActivityBAC to set
	 */
	@Resource
	public void setSuspiciousActivityBAC(ISuspiciousActivityBAC suspiciousActivityBAC)
	{
		this.suspiciousActivityBAC = suspiciousActivityBAC;
	}

	/**
	 * Sets the up.
	 */
	@Before
	public void setUp()
	{
		Mockito.reset(getParticipantIdDAC());
	}

	/**
	 * Test insert suspicious activity.
	 */
	@Test
	public void testInsertSuspiciousActivity()
	{
		SarMaintenanceRequest request = new SarMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		SuspiciousActivity suspiciousActivity =
				CommonTestRoutines.createDummySuspiciousActivity(BusinessTypeEnum.MEMBER);
		request.setSuspiciousActivity(suspiciousActivity);

		Mockito.when(getMockSuspiciousActivityDAC().insertSuspiciousActivity(request)).thenReturn(
				new InternalResponse());

		Mockito.when(getParticipantIdDAC().fetchNextSuspiciousActivityParticipantId()).thenReturn(
				new InternalResultsResponse<SuspiciousActivityParticipantId>(Arrays
						.asList(new SuspiciousActivityParticipantId('A', 'A', 1,
								INT_99999))));

		InternalResponse response = getSuspiciousActivityBAC().insertSuspiciousActivity(request);

		CommonTestRoutines.assertResponse(response);

		Mockito.verify(getMockSuspiciousActivityDAC()).insertSuspiciousActivity(request);
		Mockito.verify(getParticipantIdDAC()).fetchNextSuspiciousActivityParticipantId();
	}

	/**
	 * Test insert suspicious activity with exception.
	 */
	@Test
	public void testInsertSuspiciousActivityWithException()
	{
		SarMaintenanceRequest request = new SarMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		SuspiciousActivity suspiciousActivity =
				CommonTestRoutines.createDummySuspiciousActivity(BusinessTypeEnum.MEMBER);
		request.setSuspiciousActivity(suspiciousActivity);

		InternalResponse insertResponse = new InternalResponse();
		insertResponse.setStatus(Status.NoRowsInsertedError);

		Mockito.when(getMockSuspiciousActivityDAC().insertSuspiciousActivity(request)).thenReturn(
				insertResponse);

		Mockito.when(getParticipantIdDAC().fetchNextSuspiciousActivityParticipantId()).thenReturn(
				new InternalResultsResponse<SuspiciousActivityParticipantId>(Arrays
						.asList(new SuspiciousActivityParticipantId('A', 'A', 1,
								INT_99999))));

		InternalResponse response = getSuspiciousActivityBAC().insertSuspiciousActivity(request);

		Assert.assertEquals(Status.NoRowsInsertedError, response.getStatus());

		Mockito.verify(getMockSuspiciousActivityDAC()).insertSuspiciousActivity(request);
		Mockito.verify(getParticipantIdDAC()).fetchNextSuspiciousActivityParticipantId();
	}

	/**
	 * Test insert suspicious activity error.
	 */
	@Test
	public void testInsertSuspiciousActivityError()
	{
		SarMaintenanceRequest request = new SarMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		SuspiciousActivity suspiciousActivity =
				CommonTestRoutines.createDummySuspiciousActivity(BusinessTypeEnum.MEMBER);
		request.setSuspiciousActivity(suspiciousActivity);

		InternalResultsResponse<SuspiciousActivityParticipantId> response =
				new InternalResultsResponse<SuspiciousActivityParticipantId>();
		response.setStatus(Status.SystemError);
		Mockito.when(getParticipantIdDAC().fetchNextSuspiciousActivityParticipantId()).thenReturn(response);

		InternalResponse result = getSuspiciousActivityBAC().insertSuspiciousActivity(request);

		Assert.assertEquals(Status.SystemError, result.getStatus());

		Mockito.verify(getParticipantIdDAC()).fetchNextSuspiciousActivityParticipantId();
	}

	/**
	 * Test delete suspicious activity.
	 */
	@Test
	public void testDeleteSuspiciousActivity()
	{
		SarMaintenanceRequest request = new SarMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		SuspiciousActivity suspiciousActivity =
				CommonTestRoutines.createDummySuspiciousActivity(BusinessTypeEnum.ORGANIZATION);
		request.setSuspiciousActivity(suspiciousActivity);

		Mockito.when(getMockSuspiciousActivityDAC().deleteSuspiciousActivity(request)).thenReturn(
				new InternalResponse());

		InternalResponse response = getSuspiciousActivityBAC().deleteSuspiciousActivity(request);

		CommonTestRoutines.assertResponse(response);

		Mockito.verify(getMockSuspiciousActivityDAC()).deleteSuspiciousActivity(request);
	}

	/**
	 * Test delete suspicious activity with exception.
	 */
	@Test
	public void testDeleteSuspiciousActivityWithException()
	{
		SarMaintenanceRequest request = new SarMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		SuspiciousActivity suspiciousActivity =
				CommonTestRoutines.createDummySuspiciousActivity(BusinessTypeEnum.ORGANIZATION);
		request.setSuspiciousActivity(suspiciousActivity);

		InternalResultsResponse<SuspiciousActivityParticipantId> response =
				new InternalResultsResponse<SuspiciousActivityParticipantId>();
		response.setStatus(Status.SystemError);

		Mockito.when(getMockSuspiciousActivityDAC().deleteSuspiciousActivity(request)).thenReturn(
				response);

		InternalResponse result = getSuspiciousActivityBAC().deleteSuspiciousActivity(request);

		Assert.assertEquals(Status.SystemError, result.getStatus());

		Mockito.verify(getMockSuspiciousActivityDAC()).deleteSuspiciousActivity(request);
	}

	/**
	 * Test update suspicious activity.
	 */
	@Test
	public void testUpdateSuspiciousActivity()
	{
		SarMaintenanceRequest request = new SarMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		SuspiciousActivity suspiciousActivity =
				CommonTestRoutines.createDummySuspiciousActivity(BusinessTypeEnum.MEMBER);
		request.setSuspiciousActivity(suspiciousActivity);

		Mockito.when(getMockSuspiciousActivityDAC().insertSuspiciousActivity(request)).thenReturn(
				new InternalResponse());

		InternalResponse response = getSuspiciousActivityBAC().updateSuspiciousActivity(request);

		CommonTestRoutines.assertResponse(response);

		Mockito.verify(getMockSuspiciousActivityDAC()).insertSuspiciousActivity(request);
	}

	/**
	 * Test update suspicious activity with exception.
	 */
	@Test
	public void testUpdateSuspiciousActivityWithException()
	{
		SarMaintenanceRequest request = new SarMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		SuspiciousActivity suspiciousActivity =
				CommonTestRoutines.createDummySuspiciousActivity(BusinessTypeEnum.MEMBER);
		request.setSuspiciousActivity(suspiciousActivity);

		InternalResultsResponse<SuspiciousActivityParticipantId> response =
				new InternalResultsResponse<SuspiciousActivityParticipantId>();
		response.setStatus(Status.SystemError);

		Mockito.when(getMockSuspiciousActivityDAC().insertSuspiciousActivity(request)).thenReturn(
				response);

		InternalResponse result = getSuspiciousActivityBAC().updateSuspiciousActivity(request);

		Assert.assertEquals(Status.SystemError, result.getStatus());

		Mockito.verify(getMockSuspiciousActivityDAC()).insertSuspiciousActivity(request);
	}

	/**
	 * Test fetch suspicious activity by request.
	 */
	@Test
	public void testFetchSuspiciousActivityByRequest()
	{
		SarInquiryRequest request = new SarInquiryRequest();

		Mockito.when(getMockSuspiciousActivityDAC().fetchSuspiciousActivityByRequest(request)).thenReturn(
				new InternalResultsResponse<SuspiciousActivity>(
						Arrays.asList(new SuspiciousActivity(), new SuspiciousActivity(), new SuspiciousActivity())));

		InternalResultsResponse<SuspiciousActivity> results =
				getSuspiciousActivityBAC().fetchSuspiciousActivityByRequest(request);

		CommonTestRoutines.assertResultResponse(results);

		Mockito.verify(getMockSuspiciousActivityDAC()).fetchSuspiciousActivityByRequest(request);
	}

	/**
	 * Test fetch suspicious activity by request with exception.
	 */
	@Test
	public void testFetchSuspiciousActivityByRequestWithException()
	{
		SarInquiryRequest request = new SarInquiryRequest();

		InternalResultsResponse<SuspiciousActivity> response = new InternalResultsResponse<SuspiciousActivity>();
		response.setStatus(Status.SystemError);

		Mockito.when(getMockSuspiciousActivityDAC().fetchSuspiciousActivityByRequest(request)).thenReturn(
				response);

		InternalResultsResponse<SuspiciousActivity> results =
				getSuspiciousActivityBAC().fetchSuspiciousActivityByRequest(request);

		Assert.assertEquals(Status.SystemError, results.getStatus());

		Mockito.verify(getMockSuspiciousActivityDAC()).fetchSuspiciousActivityByRequest(request);
	}

	/**
	 * Test fetch suspicious activity by id request.
	 */
	@Test
	public void testFetchSuspiciousActivityByIdRequest()
	{
		FetchByIdRequest request = new FetchByIdRequest(ONE);

		Mockito.when(getMockSuspiciousActivityDAC().fetchSuspiciousActivityByIdRequest(request)).thenReturn(
				new InternalResultsResponse<SuspiciousActivity>(Arrays.asList(new SuspiciousActivity())));

		InternalResultsResponse<SuspiciousActivity> response =
				getSuspiciousActivityBAC().fetchSuspiciousActivityByIdRequest(request);

		CommonTestRoutines.assertResultResponse(response);

		Mockito.verify(getMockSuspiciousActivityDAC()).fetchSuspiciousActivityByIdRequest(request);
	}

	/**
	 * Test fetch suspicious activity by id request with exception.
	 */
	@Test
	public void testFetchSuspiciousActivityByIdRequestWithException()
	{
		FetchByIdRequest request = new FetchByIdRequest(ONE);

		InternalResultsResponse<SuspiciousActivity> response = new InternalResultsResponse<SuspiciousActivity>();
		response.setStatus(Status.SystemError);

		Mockito.when(getMockSuspiciousActivityDAC().fetchSuspiciousActivityByIdRequest(request)).thenReturn(
				response);

		InternalResultsResponse<SuspiciousActivity> results =
				getSuspiciousActivityBAC().fetchSuspiciousActivityByIdRequest(request);

		Assert.assertEquals(Status.SystemError, results.getStatus());

		Mockito.verify(getMockSuspiciousActivityDAC()).fetchSuspiciousActivityByIdRequest(request);
	}

	/**
	 * Test fetch business suspicious activity by id request.
	 */
	@Test
	public void testFetchBusinessSuspiciousActivityByIdRequest()
	{
		FetchByIdRequest request = new FetchByIdRequest(ONE);

		Mockito.when(getMockSuspiciousActivityDAC().fetchBusinessSuspiciousActivityByIdRequest(request)).thenReturn(
				new InternalResultsResponse<SuspiciousActivity>(Arrays.asList(new SuspiciousActivity())));

		InternalResultsResponse<SuspiciousActivity> response =
				getSuspiciousActivityBAC().fetchBusinessSuspiciousActivityByIdRequest(request);

		CommonTestRoutines.assertResultResponse(response);

		Mockito.verify(getMockSuspiciousActivityDAC()).fetchBusinessSuspiciousActivityByIdRequest(request);
	}

	/**
	 * Test fetch business suspicious activity by id request with exception.
	 */
	@Test
	public void testFetchBusinessSuspiciousActivityByIdRequestWithException()
	{
		FetchByIdRequest request = new FetchByIdRequest(ONE);

		InternalResultsResponse<SuspiciousActivity> response = new InternalResultsResponse<SuspiciousActivity>();
		response.setStatus(Status.SystemError);

		Mockito.when(getMockSuspiciousActivityDAC().fetchBusinessSuspiciousActivityByIdRequest(request)).thenReturn(
				response);

		InternalResultsResponse<SuspiciousActivity> results =
				getSuspiciousActivityBAC().fetchBusinessSuspiciousActivityByIdRequest(request);

		Assert.assertEquals(Status.SystemError, results.getStatus());

		Mockito.verify(getMockSuspiciousActivityDAC()).fetchBusinessSuspiciousActivityByIdRequest(request);
	}

}
