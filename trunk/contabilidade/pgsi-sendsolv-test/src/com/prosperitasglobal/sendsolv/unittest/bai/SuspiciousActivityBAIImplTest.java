package com.prosperitasglobal.sendsolv.unittest.bai;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.prosperitasglobal.cbof.model.BusinessTypeEnum;
import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.cbof.unittest.util.CommonTestRoutines;
import com.prosperitasglobal.sendsolv.bac.ISuspiciousActivityBAC;
import com.prosperitasglobal.sendsolv.bai.ISuspiciousActivityBAI;
import com.prosperitasglobal.sendsolv.model.SuspiciousActivity;
import com.prosperitasglobal.sendsolv.model.request.SarInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.SarMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.response.SarResponse;
import com.qat.framework.model.QATModel.PersistanceActionEnum;
import com.qat.framework.model.response.InternalResponse.Status;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.model.response.MaintenanceResponse;

/**
 * The Class SuspiciousActivityBAIImplTest.
 */
@ContextConfiguration(locations = {
		"classpath:com/prosperitasglobal/sendsolv/unittest/bai/conf/pgsi-sendsolv-validators-context-test.xml",
		"classpath:com/prosperitasglobal/sendsolv/unittest/bai/conf/sendsolv-dac-context.xml",
		"classpath:conf/web/conf/pgsi-sendsolv-shared-context.xml",
		"classpath:com/prosperitasglobal/sendsolv/unittest/conf/pgsi-sendsolv-resourcebundles-context.xml",
"classpath:com/prosperitasglobal/sendsolv/unittest/bai/conf/suspiciousactivitybaiimpltest.xml"})
public class SuspiciousActivityBAIImplTest extends AbstractJUnit4SpringContextTests
{
	/** The log. */
	private static final Logger LOG = LoggerFactory.getLogger(SuspiciousActivityBAIImplTest.class);

	/** The Constant DEFAULT_EXCEPTION_MSG. */
	private static final String DEFAULT_EXCEPTION_MSG = "sendsolv.base.defaultexception";

	/** The Constant SUSPICIOUS_ACTIVITY_VALIDATOR_SUMMARY_REQUIRED. */
	private static final String SUSPICIOUS_ACTIVITY_VALIDATOR_SUMMARY_REQUIRED =
			"sendsolv.base.suspiciousactivityvalidator.summary.required";

	/** The Constant SUSPICIOUS_ACTIVITY_VALIDATOR_DETAIL_REQUIRED. */
	private static final String SUSPICIOUS_ACTIVITY_VALIDATOR_DETAIL_REQUIRED =
			"sendsolv.base.suspiciousactivityvalidator.detail.required";

	/** The Constant SUSPICIOUS_ACTIVITY_VALIDATOR_STARTDATE_REQUIRED. */
	private static final String SUSPICIOUS_ACTIVITY_VALIDATOR_STARTDATE_REQUIRED =
			"sendsolv.base.suspiciousactivityvalidator.startdate.required";

	/** The Constant SUSPICIOUS_ACTIVITY_VALIDATOR_STOPDATE_REQUIRED. */
	private static final String SUSPICIOUS_ACTIVITY_VALIDATOR_STOPDATE_REQUIRED =
			"sendsolv.base.suspiciousactivityvalidator.stopdate.required";

	/** The Constant SUSPICIOUS_ACTIVITY_VALIDATOR_SARSTATUS_REQUIRED. */
	private static final String SUSPICIOUS_ACTIVITY_VALIDATOR_SARSTATUS_REQUIRED =
			"sendsolv.base.suspiciousactivityvalidator.sarstatus.required";

	/** The Constant SUSPICIOUS_ACTIVITY_VALIDATOR_TYPE_REQUIRED. */
	private static final String SUSPICIOUS_ACTIVITY_VALIDATOR_TYPE_REQUIRED =
			"sendsolv.base.suspiciousactivityvalidator.type.required";

	/** The Constant SUSPICIOUS_ACTIVITY_VALIDATOR_STATUS_DATE_REQUIRED. */
	private static final String SUSPICIOUS_ACTIVITY_VALIDATOR_STATUS_DATE_REQUIRED =
			"sendsolv.base.suspiciousactivityvalidator.statusdate.required";

	/** The Constant SUSPICIOUS_ACTIVITY_VALIDATOR_PERSONORBUSINESSLIST_REQUIRED. */
	private static final String SUSPICIOUS_ACTIVITY_VALIDATOR_PERSONORBUSINESSLIST_REQUIRED =
			"sendsolv.base.suspiciousactivityvalidator.personorbusinesslist.required";

	/** The Constant SUSPICIOUS_ACTIVITY_VALIDATOR_ID_REQUIRED. */
	private static final String SUSPICIOUS_ACTIVITY_VALIDATOR_ID_REQUIRED =
			"sendsolv.base.suspiciousactivityvalidator.id.required";

	/** The Constant TEN. */
	private static final Integer TEN = 10;

	/** The suspicious activity bai. */
	private ISuspiciousActivityBAI suspiciousActivityBAI;

	/** The mock suspicious activity bac. */
	private ISuspiciousActivityBAC mockSuspiciousActivityBAC;

	/**
	 * Gets the suspicious activity bai.
	 *
	 * @return the suspiciousActivityBAI
	 */
	public ISuspiciousActivityBAI getSuspiciousActivityBAI()
	{
		return suspiciousActivityBAI;
	}

	/**
	 * Sets the suspicious activity bai.
	 *
	 * @param suspiciousActivityBAI the suspiciousActivityBAI to set
	 */
	@Resource
	public void setSuspiciousActivityBAI(ISuspiciousActivityBAI suspiciousActivityBAI)
	{
		this.suspiciousActivityBAI = suspiciousActivityBAI;
	}

	/**
	 * Gets the mock suspicious activity bac.
	 *
	 * @return the mockSuspiciousActivityBAC
	 */
	public ISuspiciousActivityBAC getMockSuspiciousActivityBAC()
	{
		return mockSuspiciousActivityBAC;
	}

	/**
	 * Sets the mock suspicious activity bac.
	 *
	 * @param mockSuspiciousActivityBAC the mockSuspiciousActivityBAC to set
	 */
	@Resource
	public void setMockSuspiciousActivityBAC(ISuspiciousActivityBAC mockSuspiciousActivityBAC)
	{
		this.mockSuspiciousActivityBAC = mockSuspiciousActivityBAC;
	}

	/**
	 * Sets the up.
	 */
	@Before
	public void setUp()
	{
		Mockito.reset(getMockSuspiciousActivityBAC());
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
		suspiciousActivity.addPerson(CommonTestRoutines.createDummyMember());
		suspiciousActivity.setModelAction(PersistanceActionEnum.INSERT);
		request.setSuspiciousActivity(suspiciousActivity);
		InternalResultsResponse<SuspiciousActivity> internalResponse =
				new InternalResultsResponse<SuspiciousActivity>();
		internalResponse.setStatus(Status.OperationSuccess);

		Mockito.when(getMockSuspiciousActivityBAC().insertSuspiciousActivity(request)).thenReturn(internalResponse);

		MaintenanceResponse response = getSuspiciousActivityBAI().insertSuspiciousActivity(request);
		CommonTestRoutines.assertResponse(response);

		Mockito.verify(getMockSuspiciousActivityBAC()).insertSuspiciousActivity(request);
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
		suspiciousActivity.addPerson(CommonTestRoutines.createDummyMember());
		suspiciousActivity.setModelAction(PersistanceActionEnum.INSERT);
		request.setSuspiciousActivity(suspiciousActivity);

		Mockito.when(getMockSuspiciousActivityBAC().insertSuspiciousActivity(request))
		.thenThrow(new RuntimeException());

		MaintenanceResponse response = getSuspiciousActivityBAI().insertSuspiciousActivity(request);

		CommonTestRoutines.assertMessages(response, LOG, DEFAULT_EXCEPTION_MSG);

		Mockito.verify(getMockSuspiciousActivityBAC()).insertSuspiciousActivity(request);
	}

	/**
	 * Test insert suspicious activity without fields.
	 */
	@Test
	public void testInsertSuspiciousActivityWithoutFields()
	{
		SarMaintenanceRequest request = new SarMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		request.setSuspiciousActivity(new SuspiciousActivity());
		InternalResultsResponse<SuspiciousActivity> internalResponse =
				new InternalResultsResponse<SuspiciousActivity>();
		internalResponse.setStatus(Status.OperationSuccess);

		Mockito.when(getMockSuspiciousActivityBAC().insertSuspiciousActivity(request)).thenReturn(internalResponse);

		MaintenanceResponse response = getSuspiciousActivityBAI().insertSuspiciousActivity(request);

		CommonTestRoutines.assertMessages(response, LOG, SUSPICIOUS_ACTIVITY_VALIDATOR_SUMMARY_REQUIRED,
				SUSPICIOUS_ACTIVITY_VALIDATOR_DETAIL_REQUIRED,
				SUSPICIOUS_ACTIVITY_VALIDATOR_STARTDATE_REQUIRED,
				SUSPICIOUS_ACTIVITY_VALIDATOR_STOPDATE_REQUIRED,
				SUSPICIOUS_ACTIVITY_VALIDATOR_SARSTATUS_REQUIRED,
				SUSPICIOUS_ACTIVITY_VALIDATOR_TYPE_REQUIRED,
				SUSPICIOUS_ACTIVITY_VALIDATOR_STATUS_DATE_REQUIRED,
				SUSPICIOUS_ACTIVITY_VALIDATOR_PERSONORBUSINESSLIST_REQUIRED);

		Mockito.verify(getMockSuspiciousActivityBAC(), Mockito.never()).insertSuspiciousActivity(request);
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
		suspiciousActivity.setId(TEN);
		suspiciousActivity.addPerson(CommonTestRoutines.createDummyMember());
		suspiciousActivity.setModelAction(PersistanceActionEnum.UPDATE);
		request.setSuspiciousActivity(suspiciousActivity);
		InternalResultsResponse<SuspiciousActivity> internalResponse =
				new InternalResultsResponse<SuspiciousActivity>();
		internalResponse.setStatus(Status.OperationSuccess);

		Mockito.when(getMockSuspiciousActivityBAC().updateSuspiciousActivity(request)).thenReturn(internalResponse);

		MaintenanceResponse response = getSuspiciousActivityBAI().updateSuspiciousActivity(request);
		CommonTestRoutines.assertResponse(response);

		Mockito.verify(getMockSuspiciousActivityBAC()).updateSuspiciousActivity(request);
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
		suspiciousActivity.addPerson(CommonTestRoutines.createDummyMember());
		suspiciousActivity.setModelAction(PersistanceActionEnum.UPDATE);
		request.setSuspiciousActivity(suspiciousActivity);

		Mockito.when(getMockSuspiciousActivityBAC().updateSuspiciousActivity(request))
		.thenThrow(new RuntimeException());

		MaintenanceResponse response = getSuspiciousActivityBAI().updateSuspiciousActivity(request);

		CommonTestRoutines.assertMessages(response, LOG, DEFAULT_EXCEPTION_MSG);

		Mockito.verify(getMockSuspiciousActivityBAC()).updateSuspiciousActivity(request);
	}

	/**
	 * Test update suspicious activity without fields.
	 */
	@Test
	public void testUpdateSuspiciousActivityWithoutFields()
	{
		SarMaintenanceRequest request = new SarMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		request.setSuspiciousActivity(new SuspiciousActivity());
		InternalResultsResponse<SuspiciousActivity> internalResponse =
				new InternalResultsResponse<SuspiciousActivity>();
		internalResponse.setStatus(Status.OperationSuccess);

		Mockito.when(getMockSuspiciousActivityBAC().updateSuspiciousActivity(request)).thenReturn(internalResponse);

		MaintenanceResponse response = getSuspiciousActivityBAI().updateSuspiciousActivity(request);

		CommonTestRoutines.assertMessages(response, LOG, SUSPICIOUS_ACTIVITY_VALIDATOR_SUMMARY_REQUIRED,
				SUSPICIOUS_ACTIVITY_VALIDATOR_DETAIL_REQUIRED,
				SUSPICIOUS_ACTIVITY_VALIDATOR_STARTDATE_REQUIRED,
				SUSPICIOUS_ACTIVITY_VALIDATOR_STOPDATE_REQUIRED,
				SUSPICIOUS_ACTIVITY_VALIDATOR_SARSTATUS_REQUIRED,
				SUSPICIOUS_ACTIVITY_VALIDATOR_TYPE_REQUIRED,
				SUSPICIOUS_ACTIVITY_VALIDATOR_STATUS_DATE_REQUIRED,
				SUSPICIOUS_ACTIVITY_VALIDATOR_PERSONORBUSINESSLIST_REQUIRED,
				SUSPICIOUS_ACTIVITY_VALIDATOR_ID_REQUIRED);

		Mockito.verify(getMockSuspiciousActivityBAC(), Mockito.never()).updateSuspiciousActivity(request);
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
				CommonTestRoutines.createDummySuspiciousActivity(BusinessTypeEnum.MEMBER);
		suspiciousActivity.addPerson(CommonTestRoutines.createDummyMember());
		suspiciousActivity.setModelAction(PersistanceActionEnum.DELETE);
		suspiciousActivity.setId(TEN);
		request.setSuspiciousActivity(suspiciousActivity);

		Mockito.when(getMockSuspiciousActivityBAC().deleteSuspiciousActivity(request)).thenReturn(
				new InternalResultsResponse<SuspiciousActivity>());

		MaintenanceResponse response = getSuspiciousActivityBAI().deleteSuspiciousActivity(request);
		CommonTestRoutines.assertResponse(response);

		Mockito.verify(getMockSuspiciousActivityBAC()).deleteSuspiciousActivity(request);
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
				CommonTestRoutines.createDummySuspiciousActivity(BusinessTypeEnum.MEMBER);
		suspiciousActivity.addPerson(CommonTestRoutines.createDummyMember());
		suspiciousActivity.setModelAction(PersistanceActionEnum.DELETE);
		request.setSuspiciousActivity(suspiciousActivity);

		Mockito.when(getMockSuspiciousActivityBAC().deleteSuspiciousActivity(request))
				.thenThrow(new RuntimeException());

		MaintenanceResponse response = getSuspiciousActivityBAI().deleteSuspiciousActivity(request);
		CommonTestRoutines.assertMessages(response, LOG, DEFAULT_EXCEPTION_MSG);
	}

	/**
	 * Test fetch suspicious activity by id request.
	 */
	@Test
	public void testFetchSuspiciousActivityByIdRequest()
	{
		SuspiciousActivity suspiciousActivity =
				CommonTestRoutines.createDummySuspiciousActivity(BusinessTypeEnum.MEMBER);
		suspiciousActivity.addPerson(CommonTestRoutines.createDummyMember());

		FetchByIdRequest request = new FetchByIdRequest(suspiciousActivity.getId());
		CommonTestRoutines.createDummyUserContext(request);

		Mockito.when(getMockSuspiciousActivityBAC().fetchSuspiciousActivityByIdRequest(request)).thenReturn(
				createFetchResponse(suspiciousActivity));

		SarResponse result = getSuspiciousActivityBAI().fetchSuspiciousActivityByIdRequest(request);

		CommonTestRoutines.assertResponse(result);
	}

	/**
	 * Test fetch suspicious activity by id request exception.
	 */
	@Test
	public void testFetchSuspiciousActivityByIdRequestException()
	{
		FetchByIdRequest request = new FetchByIdRequest(TEN);

		Mockito.when(getMockSuspiciousActivityBAC().fetchSuspiciousActivityByIdRequest(request)).thenThrow(
				new RuntimeException());

		SarResponse response = getSuspiciousActivityBAI().fetchSuspiciousActivityByIdRequest(request);

		CommonTestRoutines.assertMessages(response, LOG, DEFAULT_EXCEPTION_MSG);

		Mockito.verify(getMockSuspiciousActivityBAC()).fetchSuspiciousActivityByIdRequest(request);
	}

	/**
	 * Test fetch business suspicious activity by id request.
	 */
	@Test
	public void testFetchBusinessSuspiciousActivityByIdRequest()
	{
		SuspiciousActivity suspiciousActivity =
				CommonTestRoutines.createDummySuspiciousActivity(BusinessTypeEnum.LOCATION);
		suspiciousActivity.addPerson(CommonTestRoutines.createDummyMember());

		FetchByIdRequest request = new FetchByIdRequest(suspiciousActivity.getId());
		CommonTestRoutines.createDummyUserContext(request);

		Mockito.when(getMockSuspiciousActivityBAC().fetchBusinessSuspiciousActivityByIdRequest(request)).thenReturn(
				createFetchResponse(suspiciousActivity));

		SarResponse result = getSuspiciousActivityBAI().fetchBusinessSuspiciousActivityByIdRequest(request);

		CommonTestRoutines.assertResponse(result);
	}

	/**
	 * Test fetch business suspicious activity by id request with error.
	 */
	@Test
	public void testFetchBusinessSuspiciousActivityByIdRequestWithError()
	{
		SuspiciousActivity suspiciousActivity =
				CommonTestRoutines.createDummySuspiciousActivity(BusinessTypeEnum.LOCATION);
		suspiciousActivity.addPerson(CommonTestRoutines.createDummyMember());

		FetchByIdRequest request = new FetchByIdRequest();
		CommonTestRoutines.createDummyUserContext(request);

		Mockito.when(getMockSuspiciousActivityBAC().fetchBusinessSuspiciousActivityByIdRequest(request)).thenReturn(
				createFetchResponse(suspiciousActivity));

		SarResponse result = getSuspiciousActivityBAI().fetchBusinessSuspiciousActivityByIdRequest(request);

		CommonTestRoutines.assertMessages(result, LOG, SUSPICIOUS_ACTIVITY_VALIDATOR_ID_REQUIRED);
	}

	/**
	 * Test fetch business suspicious activity by id request exception.
	 */
	@Test
	public void testFetchBusinessSuspiciousActivityByIdRequestException()
	{
		FetchByIdRequest request = new FetchByIdRequest(TEN);

		Mockito.when(getMockSuspiciousActivityBAC().fetchBusinessSuspiciousActivityByIdRequest(request)).thenThrow(
				new RuntimeException());

		SarResponse response = getSuspiciousActivityBAI().fetchBusinessSuspiciousActivityByIdRequest(request);

		CommonTestRoutines.assertMessages(response, LOG, DEFAULT_EXCEPTION_MSG);

		Mockito.verify(getMockSuspiciousActivityBAC()).fetchBusinessSuspiciousActivityByIdRequest(request);
	}

	/**
	 * Test fetch suspicious activity by request.
	 */
	@Test
	public void testFetchSuspiciousActivityByRequest()
	{
		Mockito.when(
				getMockSuspiciousActivityBAC().fetchSuspiciousActivityByRequest(Matchers.any(SarInquiryRequest.class)))
				.thenReturn(
						createFetchResponse(CommonTestRoutines.createDummySuspiciousActivity(
								BusinessTypeEnum.LOCATION)));

		SarInquiryRequest request = new SarInquiryRequest();

		SarResponse response = getSuspiciousActivityBAI().fetchSuspiciousActivityByRequest(request);
		CommonTestRoutines.assertResponse(response);

		Mockito.verify(getMockSuspiciousActivityBAC()).fetchSuspiciousActivityByRequest(request);
	}

	/**
	 * Test fetch suspicious activity exception.
	 */
	@Test
	public void testFetchSuspiciousActivityException()
	{
		SarInquiryRequest request = new SarInquiryRequest();

		Mockito.when(getMockSuspiciousActivityBAC().fetchSuspiciousActivityByRequest(request)).thenThrow(
				new RuntimeException());

		SarResponse response = getSuspiciousActivityBAI().fetchSuspiciousActivityByRequest(request);

		CommonTestRoutines.assertMessages(response, LOG, DEFAULT_EXCEPTION_MSG);

		Mockito.verify(getMockSuspiciousActivityBAC()).fetchSuspiciousActivityByRequest(request);
	}

	/**
	 * Creates the fetch response.
	 *
	 * @param suspiciousActivity the suspicious activity
	 * @return the internal results response
	 */
	private InternalResultsResponse<SuspiciousActivity> createFetchResponse(SuspiciousActivity suspiciousActivity)
	{
		InternalResultsResponse<SuspiciousActivity> response = new InternalResultsResponse<SuspiciousActivity>();
		response.addResult(suspiciousActivity);
		return response;
	}

}
