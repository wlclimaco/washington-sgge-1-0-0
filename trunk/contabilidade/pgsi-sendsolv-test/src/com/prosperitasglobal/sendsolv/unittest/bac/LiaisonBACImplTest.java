package com.prosperitasglobal.sendsolv.unittest.bac;

import java.util.Arrays;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.cbof.unittest.util.CommonTestRoutines;
import com.prosperitasglobal.sendsolv.bac.ILiaisonBAC;
import com.prosperitasglobal.sendsolv.dac.IParticipantIdDAC;
import com.prosperitasglobal.sendsolv.dac.IPersonDAC;
import com.prosperitasglobal.sendsolv.model.ContactParticipantId;
import com.prosperitasglobal.sendsolv.model.Liaison;
import com.prosperitasglobal.sendsolv.model.request.LiaisonMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.request.PagedInquiryRequest;
import com.prosperitasglobal.sendsolv.sdn.bac.ISdnCheckerBAC;
import com.prosperitasglobal.sendsolv.sdn.model.SdnStatusHistory;
import com.prosperitasglobal.sendsolv.sdn.model.request.SdnCheckerRequest;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResponse.Status;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * The Class LiaisonBACImplTest.
 */
@ContextConfiguration(locations = {
		"classpath:com/prosperitasglobal/sendsolv/unittest/conf/pgsi-sendsolv-resourcebundles-context.xml",
		"classpath:com/prosperitasglobal/sendsolv/unittest/bac/conf/liaisonbacimpltest.xml"})
public class LiaisonBACImplTest extends AbstractJUnit4SpringContextTests
{

	/** The Constant 99999. */
	private static final Integer INT_99999 = 99999;

	/** The Constant ONE. */
	private static final Integer ONE = new Integer(1);

	/** The liaison bac. */
	private ILiaisonBAC liaisonBAC;

	/** The person dac. */
	private IPersonDAC personDAC;

	/** The participant id dac. */
	private IParticipantIdDAC participantIdDAC;

	/** The mock sdn checker bac. */
	private ISdnCheckerBAC mockSdnCheckerBAC;

	/**
	 * Sets the up.
	 */
	@Before
	public void setUp()
	{
		Mockito.reset(getPersonDAC());
	}

	/**
	 * Gets the liaison bac.
	 *
	 * @return the liaison bac
	 */
	public ILiaisonBAC getLiaisonBAC()
	{
		return liaisonBAC;
	}

	/**
	 * Sets the liaison bac.
	 *
	 * @param liaisonBAC the liaison bac
	 */
	@Resource
	public void setLiaisonBAC(ILiaisonBAC liaisonBAC)
	{
		this.liaisonBAC = liaisonBAC;
	}

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
	 * Test insert liaison.
	 */
	@Test
	public void testInsertLiaison()
	{
		LiaisonMaintenanceRequest request = new LiaisonMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		Liaison liaison = CommonTestRoutines.createDummyLiaison();
		liaison.setId(ONE);

		request.setLiaison(liaison);

		Mockito.when(getPersonDAC().insertLiaison(liaison)).thenReturn(
				new InternalResultsResponse<Liaison>(Arrays.asList(new Liaison())));

		Mockito.when(getParticipantIdDAC().fetchNextContactParticipantId()).thenReturn(
				new InternalResultsResponse<ContactParticipantId>(Arrays.asList(new ContactParticipantId('A', 'A', 1,
						INT_99999))));

		mockCheckSdn();

		InternalResultsResponse<Liaison> response = getLiaisonBAC().insertLiaison(request);

		CommonTestRoutines.assertResultResponse(response);

		Mockito.verify(getPersonDAC()).insertLiaison(liaison);
	}

	/**
	 * Test insert liaison exception.
	 */
	@Test
	public void testInsertLiaisonException()
	{
		LiaisonMaintenanceRequest request = new LiaisonMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		Liaison liaison = CommonTestRoutines.createDummyLiaison();
		liaison.setId(ONE);

		request.setLiaison(liaison);

		InternalResultsResponse<Liaison> response = new InternalResultsResponse<Liaison>();
		response.setStatus(Status.NoRowsInsertedError);

		Mockito.when(getPersonDAC().insertLiaison(liaison)).thenReturn(response);

		Mockito.when(getParticipantIdDAC().fetchNextContactParticipantId()).thenReturn(
				new InternalResultsResponse<ContactParticipantId>(Arrays.asList(new ContactParticipantId('A', 'A', 1,
						INT_99999))));

		mockCheckSdn();

		InternalResponse results = getLiaisonBAC().insertLiaison(request);

		Assert.assertEquals(Status.NoRowsInsertedError, results.getStatus());

		Mockito.verify(getPersonDAC()).insertLiaison(liaison);
	}

	/**
	 * Test update liaison.
	 */
	@Test
	public void testUpdateLiaison()
	{
		LiaisonMaintenanceRequest request = new LiaisonMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		Liaison liaison = CommonTestRoutines.createDummyLiaison();
		liaison.setId(ONE);

		request.setLiaison(liaison);

		Mockito.when(getPersonDAC().updateLiaison(liaison)).thenReturn(
				new InternalResultsResponse<Liaison>(Arrays.asList(new Liaison())));

		mockCheckSdn();

		InternalResultsResponse<Liaison> response = getLiaisonBAC().updateLiaison(request);

		CommonTestRoutines.assertResultResponse(response);

		Mockito.verify(getPersonDAC()).updateLiaison(liaison);
	}

	/**
	 * Test update liaison exception.
	 */
	@Test
	public void testUpdateLiaisonException()
	{
		Liaison liaison = CommonTestRoutines.createDummyLiaison();

		LiaisonMaintenanceRequest request = new LiaisonMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);
		request.setLiaison(liaison);

		InternalResultsResponse<Liaison> response = new InternalResultsResponse<Liaison>();
		response.setStatus(Status.NoRowsUpdatedError);

		// test for bad return
		Mockito.when(getPersonDAC().updateLiaison(liaison)).thenReturn(response);

		mockCheckSdn();

		InternalResponse results = getLiaisonBAC().updateLiaison(request);

		Assert.assertEquals(Status.NoRowsUpdatedError, results.getStatus());

		Mockito.verify(getPersonDAC()).updateLiaison(liaison);
	}

	/**
	 * Test delete liaison.
	 */
	@Test
	public void testDeleteLiaison()
	{
		Liaison liaison = new Liaison();
		LiaisonMaintenanceRequest request = new LiaisonMaintenanceRequest();
		request.setLiaison(liaison);

		Mockito.when(getPersonDAC().deletePerson(liaison)).thenReturn(new InternalResponse());

		InternalResponse response = getLiaisonBAC().deleteLiaison(request);

		CommonTestRoutines.assertResponse(response);

		Mockito.verify(getPersonDAC()).deletePerson(liaison);
	}

	/**
	 * Test delete liaison exception.
	 */
	@Test
	public void testDeleteLiaisonException()
	{

		Liaison liaison = new Liaison();
		LiaisonMaintenanceRequest request = new LiaisonMaintenanceRequest();
		request.setLiaison(liaison);

		// test for bad return
		Mockito.when(getPersonDAC().deletePerson(liaison)).thenReturn(
				new InternalResponse(Status.NoRowsRemovedError));

		InternalResponse results = getLiaisonBAC().deleteLiaison(request);
		Assert.assertEquals(Status.NoRowsRemovedError, results.getStatus());

		Mockito.verify(getPersonDAC()).deletePerson(liaison);
	}

	/**
	 * Test fetch liaison by request.
	 */
	@Test
	public void testFetchLiaisonByRequest()
	{
		PagedInquiryRequest request = new PagedInquiryRequest();

		Mockito.when(getPersonDAC().fetchLiaisonByRequest(request)).thenReturn(
				new InternalResultsResponse<Liaison>(
						Arrays.asList(new Liaison(), new Liaison(), new Liaison(), new Liaison())));

		InternalResultsResponse<Liaison> results = getLiaisonBAC().fetchLiaisonByRequest(request);

		CommonTestRoutines.assertResultResponse(results);

		Mockito.verify(getPersonDAC()).fetchLiaisonByRequest(request);
	}

	/**
	 * Test fetch liaison by id.
	 */
	@Test
	public void testFetchLiaisonById()
	{
		FetchByIdRequest request = new FetchByIdRequest(ONE);

		request.getId();

		Mockito.when(getPersonDAC().fetchLiaisonById(request)).thenReturn(
				new InternalResultsResponse<Liaison>(Arrays.asList(new Liaison())));

		InternalResultsResponse<Liaison> response = getLiaisonBAC().fetchLiaisonById(request);

		CommonTestRoutines.assertResultResponse(response);

		Mockito.verify(getPersonDAC()).fetchLiaisonById(request);
	}

	/**
	 * Mock check sdn.
	 */
	private void mockCheckSdn()
	{
		SdnCheckerRequest<Liaison> request = new SdnCheckerRequest<Liaison>();

		Liaison liaison = new Liaison();
		liaison.setFirstName("Mary");
		liaison.setLastName("Abdelnur");

		request.setPersonOrBusiness(liaison);

		InternalResultsResponse<SdnStatusHistory> response = new InternalResultsResponse<SdnStatusHistory>();

		response.setStatus(Status.OperationSuccess);
		response.addResult(new SdnStatusHistory());

		Mockito.when(getMockSdnCheckerBAC().checkLiaisonSdn(request)).thenReturn(response);
	}

}
