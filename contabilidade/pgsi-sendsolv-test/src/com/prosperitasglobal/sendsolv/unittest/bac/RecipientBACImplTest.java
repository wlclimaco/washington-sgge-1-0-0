package com.prosperitasglobal.sendsolv.unittest.bac;

import java.util.Arrays;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.cbof.unittest.util.CommonTestRoutines;
import com.prosperitasglobal.sendsolv.bac.IRecipientBAC;
import com.prosperitasglobal.sendsolv.dac.IParticipantIdDAC;
import com.prosperitasglobal.sendsolv.dac.IPersonDAC;
import com.prosperitasglobal.sendsolv.model.Recipient;
import com.prosperitasglobal.sendsolv.model.RecipientParticipantId;
import com.prosperitasglobal.sendsolv.model.request.RecipientInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.RecipientMaintenanceRequest;
import com.prosperitasglobal.sendsolv.sdn.bac.ISdnCheckerBAC;
import com.prosperitasglobal.sendsolv.sdn.model.SdnStatusHistory;
import com.prosperitasglobal.sendsolv.sdn.model.request.SdnCheckerRequest;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResponse.Status;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * The Class RecipientBACImplTest.
 */

@ContextConfiguration(locations = {
		"classpath:com/prosperitasglobal/sendsolv/unittest/conf/pgsi-sendsolv-resourcebundles-context.xml",
		"classpath:com/prosperitasglobal/sendsolv/unittest/bac/conf/recipientbacimpltest.xml"})
@ActiveProfiles("sqlserver")
public class RecipientBACImplTest extends AbstractJUnit4SpringContextTests
{
	/** The recipient bac. */
	private IRecipientBAC recipientBAC;

	/** The person dac. */
	private IPersonDAC personDAC;

	/** The participant id dac. */
	private IParticipantIdDAC participantIdDAC;

	/** The mock sdn checker bac. */
	private ISdnCheckerBAC mockSdnCheckerBAC;

	/** The Constant ONE. */
	private static final Integer ONE = 1;

	/** The Constant 99999. */
	private static final Integer INT_99999 = 99999;

	/**
	 * Gets the person dac.
	 *
	 * @return the person dac
	 */
	public IPersonDAC getPersonDAC()
	{
		return personDAC;
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
	 * Sets the person dac.
	 *
	 * @param personDAC the person dac
	 */
	@Resource
	public void setPersonDAC(IPersonDAC personDAC)
	{
		this.personDAC = personDAC;
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
	 * Gets the recipient bac.
	 *
	 * @return the recipient bac
	 */
	public IRecipientBAC getRecipientBAC()
	{
		return recipientBAC;
	}

	/**
	 * Sets the recipient bac.
	 *
	 * @param recipientBAC the recipient bac
	 */
	@Resource
	public void setRecipientBAC(IRecipientBAC recipientBAC)
	{
		this.recipientBAC = recipientBAC;
	}

	/**
	 * @return the mockSdnCheckerBAC
	 */
	public ISdnCheckerBAC getMockSdnCheckerBAC()
	{
		return mockSdnCheckerBAC;
	}

	/**
	 * @param mockSdnCheckerBAC the mockSdnCheckerBAC to set
	 */
	@Resource
	public void setMockSdnCheckerBAC(ISdnCheckerBAC mockSdnCheckerBAC)
	{
		this.mockSdnCheckerBAC = mockSdnCheckerBAC;
	}

	/**
	 * Sets the up.
	 */
	@Before
	public void setUp()
	{
		Mockito.reset(getPersonDAC());
		Mockito.reset(getParticipantIdDAC());
	}

	/**
	 * Test insert recipient.
	 */
	@Test
	public void testInsertRecipient()
	{
		RecipientMaintenanceRequest request = new RecipientMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		Recipient recipient = CommonTestRoutines.createDummyRecipient();
		request.setRecipient(recipient);

		Mockito.when(getPersonDAC().insertRecipient(recipient)).thenReturn(
				new InternalResultsResponse<Recipient>(Arrays.asList(new Recipient())));

		Mockito.when(getParticipantIdDAC().fetchNextRecipientParticipantId()).thenReturn(
				new InternalResultsResponse<RecipientParticipantId>(Arrays.asList(new RecipientParticipantId('A', 'A',
						1, INT_99999))));

		mockCheckSdn();

		InternalResultsResponse<Recipient> response = getRecipientBAC().insertRecipient(request);
		Assert.assertNotNull(recipient.getParticipantId());
		CommonTestRoutines.assertResultResponse(response);

		Mockito.verify(getPersonDAC()).insertRecipient(recipient);
		Mockito.verify(getParticipantIdDAC()).fetchNextRecipientParticipantId();
	}

	/**
	 * Test insert recipient error.
	 */
	@Test
	public void testInsertRecipientError()
	{
		RecipientMaintenanceRequest request = new RecipientMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		Recipient recipient = CommonTestRoutines.createDummyRecipient();
		request.setRecipient(recipient);

		InternalResultsResponse<RecipientParticipantId> response =
				new InternalResultsResponse<RecipientParticipantId>();
		response.setStatus(Status.SystemError);
		Mockito.when(getParticipantIdDAC().fetchNextRecipientParticipantId()).thenReturn(response);

		InternalResponse results = getRecipientBAC().insertRecipient(request);

		Assert.assertEquals(Status.SystemError, results.getStatus());

		Mockito.verify(getParticipantIdDAC()).fetchNextRecipientParticipantId();
	}

	/**
	 * Test insert recipient exception.
	 */
	@Test
	public void testInsertRecipientException()
	{
		RecipientMaintenanceRequest request = new RecipientMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		Recipient recipient = CommonTestRoutines.createDummyRecipient();
		request.setRecipient(recipient);

		InternalResultsResponse<Recipient> response = new InternalResultsResponse<Recipient>();
		response.setStatus(Status.NoRowsInsertedError);

		Mockito.when(getPersonDAC().insertRecipient(recipient)).thenReturn(response);

		Mockito.when(getParticipantIdDAC().fetchNextRecipientParticipantId()).thenReturn(
				new InternalResultsResponse<RecipientParticipantId>(Arrays.asList(new RecipientParticipantId('A', 'A',
						1, INT_99999))));

		InternalResponse results = getRecipientBAC().insertRecipient(request);

		Assert.assertEquals(Status.NoRowsInsertedError, results.getStatus());

		Mockito.verify(getPersonDAC()).insertRecipient(recipient);
		Mockito.verify(getParticipantIdDAC()).fetchNextRecipientParticipantId();
	}

	/**
	 * Test update recipient.
	 */
	@Test
	public void testUpdateRecipient()
	{
		RecipientMaintenanceRequest request = new RecipientMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		Recipient recipient = CommonTestRoutines.createDummyRecipient();
		recipient.setId(ONE);
		request.setRecipient(recipient);

		Mockito.when(getPersonDAC().updateRecipient(recipient)).thenReturn(
				new InternalResultsResponse<Recipient>(Arrays.asList(new Recipient())));

		mockCheckSdn();

		InternalResultsResponse<Recipient> response = getRecipientBAC().updateRecipient(request);

		CommonTestRoutines.assertResultResponse(response);

		Mockito.verify(getPersonDAC()).updateRecipient(recipient);
	}

	/**
	 * Test update recipient exception.
	 */
	@Test
	public void testUpdateRecipientException()
	{
		RecipientMaintenanceRequest request = new RecipientMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);
		Recipient recipient = CommonTestRoutines.createDummyRecipient();
		request.setRecipient(recipient);

		InternalResultsResponse<Recipient> response = new InternalResultsResponse<Recipient>();
		response.setStatus(Status.NoRowsUpdatedError);

		// test for bad return
		Mockito.when(getPersonDAC().updateRecipient(recipient)).thenReturn(response);

		InternalResponse results = getRecipientBAC().updateRecipient(request);

		Assert.assertEquals(Status.NoRowsUpdatedError, results.getStatus());

		Mockito.verify(getPersonDAC()).updateRecipient(recipient);
	}

	/**
	 * Test delete recipient.
	 */
	@Test
	public void testDeleteRecipient()
	{
		RecipientMaintenanceRequest request = new RecipientMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);
		Recipient recipient = CommonTestRoutines.createDummyRecipient();
		request.setRecipient(recipient);

		Mockito.when(getPersonDAC().deletePerson(recipient)).thenReturn(new InternalResponse());

		InternalResponse response = getRecipientBAC().deleteRecipient(request);

		CommonTestRoutines.assertResponse(response);

		Mockito.verify(getPersonDAC()).deletePerson(recipient);
	}

	/**
	 * Test delete recipient exception.
	 */
	@Test
	public void testDeleteRecipientException()
	{
		RecipientMaintenanceRequest request = new RecipientMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);
		Recipient recipient = CommonTestRoutines.createDummyRecipient();
		request.setRecipient(recipient);

		// test for bad return
		Mockito.when(getPersonDAC().deletePerson(recipient)).thenReturn(
				new InternalResponse(Status.NoRowsRemovedError));

		InternalResponse results = getRecipientBAC().deleteRecipient(request);
		Assert.assertEquals(Status.NoRowsRemovedError, results.getStatus());

		Mockito.verify(getPersonDAC()).deletePerson(recipient);
	}

	/**
	 * Test fetch recipient by request.
	 */
	@Test
	public void testFetchRecipientByRequest()
	{
		RecipientInquiryRequest request = new RecipientInquiryRequest();

		Mockito.when(getPersonDAC().fetchRecipientByRequest(request)).thenReturn(
				new InternalResultsResponse<Recipient>(
						Arrays.asList(new Recipient(), new Recipient(), new Recipient(), new Recipient())));

		InternalResultsResponse<Recipient> results = getRecipientBAC().fetchRecipientByRequest(request);

		CommonTestRoutines.assertResultResponse(results);

		Mockito.verify(getPersonDAC()).fetchRecipientByRequest(request);
	}

	/**
	 * Test fetch recipient by id.
	 */
	@Test
	public void testFetchRecipientById()
	{
		FetchByIdRequest request = new FetchByIdRequest(ONE);

		request.getId();

		Mockito.when(getPersonDAC().fetchRecipientById(request)).thenReturn(
				new InternalResultsResponse<Recipient>(Arrays.asList(new Recipient())));

		InternalResultsResponse<Recipient> response = getRecipientBAC().fetchRecipientById(request);

		CommonTestRoutines.assertResultResponse(response);

		Mockito.verify(getPersonDAC()).fetchRecipientById(request);
	}

	/**
	 * Test fetch recipient by request exception.
	 */
	@Test
	public void testFetchRecipientByRequestException()
	{
		RecipientInquiryRequest request = new RecipientInquiryRequest();
		InternalResultsResponse<Recipient> response = new InternalResultsResponse<Recipient>();
		response.setStatus(Status.NoRowsFoundError);

		// test for bad return
		Mockito.when(getPersonDAC().fetchRecipientByRequest(request)).thenReturn(response);

		response = getRecipientBAC().fetchRecipientByRequest(request);
		Assert.assertEquals(Status.NoRowsFoundError, response.getStatus());

		Mockito.verify(getPersonDAC()).fetchRecipientByRequest(request);
	}

	/**
	 * Test fetch recipient by id exception.
	 */
	@Test
	public void testFetchRecipientByIdException()
	{
		FetchByIdRequest request = new FetchByIdRequest(ONE);

		InternalResultsResponse<Recipient> response = new InternalResultsResponse<Recipient>();
		response.setStatus(Status.NoRowsFoundError);

		// test for bad return
		Mockito.when(getPersonDAC().fetchRecipientById(request)).thenReturn(response);

		response = getRecipientBAC().fetchRecipientById(request);
		Assert.assertEquals(Status.NoRowsFoundError, response.getStatus());

		Mockito.verify(getPersonDAC()).fetchRecipientById(request);

	}

	/**
	 * Test apply status.
	 */
	@Test
	public void testApplyStatus()
	{
		RecipientMaintenanceRequest request = new RecipientMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);
		Recipient recipient = CommonTestRoutines.createDummyRecipient();
		request.setRecipient(recipient);

		Mockito.when(getPersonDAC().applyPersonStatus(recipient)).thenReturn(new InternalResponse());

		InternalResponse response = getRecipientBAC().applyStatus(request);

		CommonTestRoutines.assertResponse(response);

		Mockito.verify(getPersonDAC()).applyPersonStatus(recipient);
	}

	/**
	 * Test apply status exception.
	 */
	@Test
	public void testApplyStatusException()
	{
		RecipientMaintenanceRequest request = new RecipientMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);
		Recipient recipient = CommonTestRoutines.createDummyRecipient();
		request.setRecipient(recipient);

		// test for bad return
		Mockito.when(getPersonDAC().applyPersonStatus(recipient)).thenReturn(
				new InternalResponse(Status.ExceptionError));

		InternalResponse results = getRecipientBAC().applyStatus(request);
		Assert.assertEquals(Status.ExceptionError, results.getStatus());

		Mockito.verify(getPersonDAC()).applyPersonStatus(recipient);
	}

	/**
	 * Mock check sdn.
	 */
	private void mockCheckSdn()
	{
		SdnCheckerRequest<Recipient> request = new SdnCheckerRequest<Recipient>();
		Recipient recipient = CommonTestRoutines.createDummyRecipient();
		request.setPersonOrBusiness(recipient);

		InternalResultsResponse<SdnStatusHistory> response = new InternalResultsResponse<SdnStatusHistory>();

		response.setStatus(Status.OperationSuccess);
		response.addResult(new SdnStatusHistory());

		Mockito.when(getMockSdnCheckerBAC().checkRecipientSdn(request))
		.thenReturn(response);
	}

}
