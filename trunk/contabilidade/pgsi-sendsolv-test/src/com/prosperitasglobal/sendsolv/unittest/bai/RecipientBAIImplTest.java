package com.prosperitasglobal.sendsolv.unittest.bai;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.cbof.unittest.util.CommonTestRoutines;
import com.prosperitasglobal.sendsolv.bac.IRecipientBAC;
import com.prosperitasglobal.sendsolv.bai.IRecipientBAI;
import com.prosperitasglobal.sendsolv.dac.IPersonDAC;
import com.prosperitasglobal.sendsolv.model.PersonTypeEnum;
import com.prosperitasglobal.sendsolv.model.Recipient;
import com.prosperitasglobal.sendsolv.model.Risk;
import com.prosperitasglobal.sendsolv.model.request.RecipientInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.RecipientMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.response.RecipientResponse;
import com.prosperitasglobal.sendsolv.validation.PagedInquiryRequestValidator;
import com.qat.framework.model.Message.MessageLevel;
import com.qat.framework.model.Message.MessageSeverity;
import com.qat.framework.model.QATModel.PersistanceActionEnum;
import com.qat.framework.model.SortExpression;
import com.qat.framework.model.SortExpression.Direction;
import com.qat.framework.model.response.InternalResponse.Status;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * The Class LocationBAIImplTest.
 */
@ContextConfiguration(locations = {
		"classpath:com/prosperitasglobal/sendsolv/unittest/bai/conf/pgsi-sendsolv-validators-context-test.xml",
		"classpath:com/prosperitasglobal/sendsolv/unittest/bai/conf/sendsolv-dac-context.xml",
		"classpath:conf/web/conf/pgsi-sendsolv-shared-context.xml",
		"classpath:com/prosperitasglobal/sendsolv/unittest/bai/conf/recipientbaiimpltest.xml",
		"classpath:com/prosperitasglobal/sendsolv/unittest/conf/pgsi-sendsolv-resourcebundles-context.xml"
		// this last xml file points to message files and is local to this project.
})
@ActiveProfiles("sqlserver")
public class RecipientBAIImplTest extends AbstractJUnit4SpringContextTests
{
	/** The log. */
	private static final Logger LOG = LoggerFactory.getLogger(RecipientBAIImplTest.class);

	/** The Constant DEFAULT_EXCEPTION_MSG. */
	private static final String DEFAULT_EXCEPTION_MSG = "sendsolv.base.defaultexception";

	/** The Constant BAC_KEY. */
	private static final String BAC_KEY = "sendsolv.base.recipientvalidator.id.required";

	/** The Constant PROSPERITASGLOBAL_BASE_PERSONVALIDATOR_CONTACT_LIST_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_PERSONVALIDATOR_CONTACT_LIST_REQUIRED =
			"sendsolv.base.personvalidator.contactlist.required";

	/** The Constant PROSPERITASGLOBAL_BASE_PERSONVALIDATOR_LAST_NAME_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_PERSONVALIDATOR_LAST_NAME_REQUIRED =
			"sendsolv.base.personvalidator.last.name.required";

	/** The Constant PROSPERITASGLOBAL_BASE_PERSONVALIDATOR_FIRST_NAME_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_PERSONVALIDATOR_FIRST_NAME_REQUIRED =
			"sendsolv.base.personvalidator.first.name.required";

	/** The Constant SENDSOLV_BASE_PERSONVALIDATOR_RISK_REQUIRED. */
	private static final String SENDSOLV_BASE_PERSONVALIDATOR_RISK_REQUIRED =
			"sendsolv.base.personvalidator.risk.required";

	/** The Constant PROSPERITASGLOBAL_BASE_PERSONVALIDATOR_PARTICIPANT_ID_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_PERSONVALIDATOR_PARTICIPANT_ID_REQUIRED =
			"sendsolv.base.personvalidator.participant.id.required";

	/** The Constant PROSPERITASGLOBAL_BASE_RECIPIENTVALIDATOR_PARENT_ID_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_RECIPIENTVALIDATOR_PARENT_ID_REQUIRED =
			"sendsolv.base.recipientvalidator.parent.id.required";

	/** The Constant SENDSOLV_BASE_PERSONVALIDATOR_RISKLEVEL_REQUIRED. */
	private static final String SENDSOLV_BASE_PERSONVALIDATOR_RISKLEVEL_REQUIRED =
			"sendsolv.base.personvalidator.risklevel.required";

	/** The Constant SENDSOLV_BASE_PERSONVALIDATOR_RISKLEVELNOTE_TOO_BIG. */
	private static final String SENDSOLV_BASE_PERSONVALIDATOR_RISKLEVELNOTE_TOO_BIG =
			"sendsolv.base.base.personvalidator.risklevelnote.toobig";

	/** The Constant PROSPERITASGLOBAL_BASE_PERSONVALIDATOR_PERSON_STATUS_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_PERSONVALIDATOR_PERSON_STATUS_REQUIRED =
			"sendsolv.base.personvalidator.personstatus.required";

	private static final String RECIPIENT_INQUIRY_REQUEST_REQUIRED =
			"sendsolv.base.recipientinquiryrequestvalidator.request.required";

	/** The Constant FIFTEEN. */
	private static final Integer FIFTEEN = 15;

	/** The Constant ONE. */
	private static final Integer ONE = 1;

	/** The Constant TEN. */
	private static final Integer TEN = 10;

	/** The Constant TWO_HUNDRED_FIFTY_SIX. */
	private static final Integer TWO_HUNDRED_FIFTY_SIX = 256;

	// error response object to be used in several tests.
	/** The err response. */
	private InternalResultsResponse<Recipient> errResponse;

	/** The location bai. */
	private IRecipientBAI recipientBAI;

	/** The mock location bac. */
	private IRecipientBAC mockRecipientBAC;

	/** The mock person dac. */
	private IPersonDAC mockPersonDAC;

	/**
	 * Sets the up.
	 */
	@Before
	public void setUp()
	{
		Mockito.reset(getMockRecipientBAC());
	}

	/**
	 * Gets the mock location bac.
	 *
	 * @return the mock location bac
	 */
	public IRecipientBAC getMockRecipientBAC()
	{
		return mockRecipientBAC;
	}

	/**
	 * Sets the mock location bac.
	 *
	 * @param recipientBAC the mock location bac
	 */
	@Resource
	public void setMockRecipientBAC(IRecipientBAC recipientBAC)
	{
		mockRecipientBAC = recipientBAC;
	}

	/**
	 * Gets the location bai.
	 *
	 * @return the location bai
	 */
	public IRecipientBAI getRecipientBAI()
	{
		return recipientBAI;
	}

	/**
	 * Sets the location bai.
	 *
	 * @param recipientBAI the location bai
	 */
	@Resource
	public void setRecipientBAI(IRecipientBAI recipientBAI)
	{
		this.recipientBAI = recipientBAI;
	}

	/**
	 * Gets the err response.
	 *
	 * @return the err response
	 */
	public InternalResultsResponse<Recipient> getErrResponse()
	{
		return errResponse;
	}

	/**
	 * Sets the err response.
	 *
	 * @param errResponse the err response
	 */
	public void setErrResponse(InternalResultsResponse<Recipient> errResponse)
	{
		this.errResponse = errResponse;
	}

	/**
	 * Gets the mock person dac.
	 *
	 * @return the mock person dac
	 */
	public IPersonDAC getMockPersonDAC()
	{
		return mockPersonDAC;
	}

	/**
	 * Sets the mock person dac.
	 *
	 * @param mockPersonDAC the mock person dac
	 */
	@Resource
	public void setMockPersonDAC(IPersonDAC mockPersonDAC)
	{
		this.mockPersonDAC = mockPersonDAC;
	}

	/**
	 * The Constructor.
	 */
	public RecipientBAIImplTest()
	{
		// setup the error response object.
		setErrResponse(new InternalResultsResponse<Recipient>());
		getErrResponse().addMessage(BAC_KEY, MessageSeverity.Error, MessageLevel.Field);
		getErrResponse().setStatus(Status.UnspecifiedError);
	}

	/**
	 * Test insert recipient with exception.
	 */
	@Test
	public void testInsertRecipientWithException()
	{
		RecipientMaintenanceRequest request = new RecipientMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		Recipient recipient = CommonTestRoutines.createDummyRecipient();
		recipient.setModelAction(PersistanceActionEnum.INSERT);

		request.setRecipient(recipient);

		// Throws an exception when mock's insertLocation is called.
		Mockito.when(getMockRecipientBAC().insertRecipient(request)).thenThrow(new RuntimeException());

		RecipientResponse response = getRecipientBAI().insertRecipient(request);

		CommonTestRoutines.assertMessages(response, DEFAULT_EXCEPTION_MSG);
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
		recipient.setModelAction(PersistanceActionEnum.INSERT);
		request.setRecipient(recipient);

		InternalResultsResponse<Recipient> internalResponse = new InternalResultsResponse<Recipient>();
		internalResponse.addResult(recipient);

		// Throws an exception when mock's insertLocation is called.
		Mockito.when(getMockRecipientBAC().insertRecipient(request)).thenReturn(internalResponse);

		RecipientResponse response = getRecipientBAI().insertRecipient(request);

		CommonTestRoutines.assertResponse(response);

		// ensures the mock's insertLocation was called 1 time.
		Mockito.verify(getMockRecipientBAC()).insertRecipient(request);
	}

	/**
	 * Test fetch recipient by id.
	 */
	@Test
	public void testFetchRecipientById()
	{
		Recipient recipient = CommonTestRoutines.createDummyRecipient();
		FetchByIdRequest request = new FetchByIdRequest(recipient.getId());
		CommonTestRoutines.createDummyUserContext(request);

		Mockito.when(getMockRecipientBAC().fetchRecipientById(request)).thenReturn(
				createFetchResponse(CommonTestRoutines.createDummyRecipient()));

		RecipientResponse result = getRecipientBAI().fetchRecipientById(request);

		CommonTestRoutines.assertResponse(result);

		Mockito.verify(getMockRecipientBAC()).fetchRecipientById(request);
	}

	/**
	 * Test fetch recipient by id exception.
	 */
	@Test
	public void testFetchRecipientByIdException()
	{
		FetchByIdRequest request = new FetchByIdRequest(TEN);

		Mockito.when(getMockRecipientBAC().fetchRecipientById(request)).thenThrow(new RuntimeException());

		RecipientResponse response = getRecipientBAI().fetchRecipientById(request);

		CommonTestRoutines.assertMessages(response, DEFAULT_EXCEPTION_MSG);

		Mockito.verify(getMockRecipientBAC()).fetchRecipientById(request);
	}

	/**
	 * Test fetch recipient by id no recipient id.
	 */
	@Test
	public void testFetchRecipientByIdNoRecipientId()
	{
		FetchByIdRequest request = new FetchByIdRequest();
		RecipientResponse response = getRecipientBAI().fetchRecipientById(request);

		Assert.assertEquals(1, response.getMessageList().size());
		Assert.assertEquals(BAC_KEY, response.getMessageList().get(0).getMessageInfo().getCode());

		Mockito.verify(getMockRecipientBAC(), Mockito.never()).fetchRecipientById(request);
	}

	/**
	 * Test fetch recipient by request.
	 */
	@Test
	public void testfetchRecipientByRequest()
	{
		Mockito.when(getMockRecipientBAC().fetchRecipientByRequest(Matchers.any(RecipientInquiryRequest.class)))
		.thenReturn(createFetchResponse(CommonTestRoutines.createDummyRecipient()));

		RecipientInquiryRequest request = new RecipientInquiryRequest();
		request.addSortExpressions(new SortExpression("firstName", Direction.Ascending));
		request.addSortExpressions(new SortExpression("lastName", Direction.Ascending));
		request.addSortExpressions(new SortExpression("participantId", Direction.Ascending));
		request.addSortExpressions(new SortExpression("personStatus", Direction.Ascending));
		RecipientResponse response = getRecipientBAI().fetchRecipientByRequest(request);

		CommonTestRoutines.assertResponse(response);
	}

	/**
	 * Test fetch recipient by request with a bad sort field name.
	 */
	@Test
	public void testfetchRecipientByRequestBadSortName()
	{
		Mockito.when(getMockRecipientBAC().fetchRecipientByRequest(Matchers.any(RecipientInquiryRequest.class)))
		.thenReturn(createFetchResponse(CommonTestRoutines.createDummyRecipient()));

		RecipientInquiryRequest request = new RecipientInquiryRequest();
		request.addSortExpressions(new SortExpression("badName", Direction.Ascending));
		RecipientResponse response = getRecipientBAI().fetchRecipientByRequest(request);

		CommonTestRoutines.assertMessages(response, LOG, PagedInquiryRequestValidator.INVALID_SORT_FIELD);
	}

	/**
	 * Test fetch recipient by request with error.
	 */
	@Test
	public void testfetchRecipientByRequestWithError()
	{
		RecipientInquiryRequest request = new RecipientInquiryRequest();
		request.setPageSize(null);
		request.setStartPage(null);
		Mockito.when(getMockRecipientBAC().fetchRecipientByRequest(request))
		.thenReturn(createFetchResponse(CommonTestRoutines.createDummyRecipient()));

		RecipientResponse response = getRecipientBAI().fetchRecipientByRequest(request);

		CommonTestRoutines.assertMessages(response, PagedInquiryRequestValidator.PAGING_PARAMETERS_REQUIRED);

		Mockito.verify(getMockRecipientBAC(), Mockito.never()).fetchRecipientByRequest(request);
	}

	/**
	 * Test fetch recipient by request exception.
	 */
	@Test
	public void testfetchRecipientByRequestException()
	{
		RecipientInquiryRequest request = new RecipientInquiryRequest();

		Mockito.when(getMockRecipientBAC().fetchRecipientByRequest(request)).thenThrow(new RuntimeException());

		RecipientResponse response = getRecipientBAI().fetchRecipientByRequest(request);

		CommonTestRoutines.assertMessages(response, DEFAULT_EXCEPTION_MSG);

		Mockito.verify(getMockRecipientBAC()).fetchRecipientByRequest(request);
	}

	/**
	 * Test fetch recipient by organization.
	 */
	@Test
	public void testFetchRecipientByOrganization()
	{
		RecipientInquiryRequest request = new RecipientInquiryRequest();
		request.setParentId(ONE);

		Mockito.when(getMockRecipientBAC().fetchRecipientByRequest(Matchers.any(RecipientInquiryRequest.class)))
		.thenReturn(createFetchResponse(CommonTestRoutines.createDummyRecipient()));

		RecipientResponse response = getRecipientBAI().fetchRecipientByOrganization(request);

		CommonTestRoutines.assertResponse(response);

	}

	/**
	 * Test fetch recipient by organization with error.
	 */
	@Test
	public void testFetchRecipientByOrganizationWithError()
	{
		RecipientInquiryRequest request = new RecipientInquiryRequest();
		request.setParentId(null);

		Mockito.when(getMockRecipientBAC().fetchRecipientByRequest(request))
		.thenReturn(createFetchResponse(CommonTestRoutines.createDummyRecipient()));

		RecipientResponse response = getRecipientBAI().fetchRecipientByOrganization(request);

		CommonTestRoutines.assertMessages(response, PROSPERITASGLOBAL_BASE_RECIPIENTVALIDATOR_PARENT_ID_REQUIRED);

		Mockito.verify(getMockRecipientBAC(), Mockito.never()).fetchRecipientByRequest(request);
	}

	/**
	 * Test fetch recipient by organization exception.
	 */
	@Test
	public void testFetchRecipientByOrganizationException()
	{
		RecipientInquiryRequest request = new RecipientInquiryRequest();
		request.setParentId(ONE);

		Mockito.when(getMockRecipientBAC().fetchRecipientByRequest(request)).thenThrow(new RuntimeException());

		RecipientResponse response = getRecipientBAI().fetchRecipientByOrganization(request);

		CommonTestRoutines.assertMessages(response, DEFAULT_EXCEPTION_MSG);

		Mockito.verify(getMockRecipientBAC()).fetchRecipientByRequest(request);
	}

	/**
	 * Test fetch recipient by location.
	 */
	@Test
	public void testFetchRecipientByLocation()
	{
		RecipientInquiryRequest request = new RecipientInquiryRequest();
		request.setParentId(ONE);

		Mockito.when(getMockRecipientBAC().fetchRecipientByRequest(Matchers.any(RecipientInquiryRequest.class)))
		.thenReturn(createFetchResponse(CommonTestRoutines.createDummyRecipient()));

		RecipientResponse response = getRecipientBAI().fetchRecipientByLocation(request);

		CommonTestRoutines.assertResponse(response);

	}

	/**
	 * Test fetch recipient by location with error.
	 */
	@Test
	public void testFetchRecipientByLocationWithError()
	{
		RecipientInquiryRequest request = new RecipientInquiryRequest();
		request.setParentId(null);

		Mockito.when(getMockRecipientBAC().fetchRecipientByRequest(request))
		.thenReturn(createFetchResponse(CommonTestRoutines.createDummyRecipient()));

		RecipientResponse response = getRecipientBAI().fetchRecipientByLocation(request);

		CommonTestRoutines.assertMessages(response, PROSPERITASGLOBAL_BASE_RECIPIENTVALIDATOR_PARENT_ID_REQUIRED);

		Mockito.verify(getMockRecipientBAC(), Mockito.never()).fetchRecipientByRequest(request);
	}

	/**
	 * Test fetch recipient by location exception.
	 */
	@Test
	public void testFetchRecipientByLocationException()
	{
		RecipientInquiryRequest request = new RecipientInquiryRequest();
		request.setParentId(ONE);

		Mockito.when(getMockRecipientBAC().fetchRecipientByRequest(request)).thenThrow(new RuntimeException());

		RecipientResponse response = getRecipientBAI().fetchRecipientByLocation(request);

		CommonTestRoutines.assertMessages(response, DEFAULT_EXCEPTION_MSG);

		Mockito.verify(getMockRecipientBAC()).fetchRecipientByRequest(request);
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
		recipient.setId(CommonTestRoutines.getRandomInt());
		recipient.setParticipantId(String.valueOf(ONE));
		request.setRecipient(recipient);

		InternalResultsResponse<Recipient> internalResponse = new InternalResultsResponse<Recipient>();
		internalResponse.setStatus(Status.OperationSuccess);

		Mockito.when(getMockRecipientBAC().applyStatus(request)).thenReturn(internalResponse);

		RecipientResponse response = getRecipientBAI().applyStatus(request);
		CommonTestRoutines.assertResponse(response);

		Mockito.verify(getMockRecipientBAC()).applyStatus(request);
	}

	/**
	 * Test apply status without person fields.
	 */
	@Test
	public void testApplyStatusWithoutPersonFields()
	{
		RecipientMaintenanceRequest request = new RecipientMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);
		Recipient recipient = CommonTestRoutines.createDummyRecipient();
		recipient.setId(CommonTestRoutines.getRandomInt());
		recipient.setPersonType(PersonTypeEnum.RECIPIENT);
		recipient.setPersonStatus(null);
		recipient.setRisk(null);
		recipient.setFirstName(null);
		recipient.setLastName(null);
		recipient.setContactList(null);
		recipient.setParticipantId(null);
		request.setRecipient(recipient);

		Mockito.when(getMockRecipientBAC().applyStatus(request))
		.thenReturn(createFetchResponse(recipient));

		RecipientResponse response = getRecipientBAI().applyStatus(request);

		CommonTestRoutines.assertMessages(response, LOG,
				PROSPERITASGLOBAL_BASE_PERSONVALIDATOR_PARTICIPANT_ID_REQUIRED,
				PROSPERITASGLOBAL_BASE_PERSONVALIDATOR_PERSON_STATUS_REQUIRED,
				SENDSOLV_BASE_PERSONVALIDATOR_RISK_REQUIRED,
				PROSPERITASGLOBAL_BASE_PERSONVALIDATOR_FIRST_NAME_REQUIRED,
				PROSPERITASGLOBAL_BASE_PERSONVALIDATOR_LAST_NAME_REQUIRED,
				PROSPERITASGLOBAL_BASE_PERSONVALIDATOR_CONTACT_LIST_REQUIRED);

		Mockito.verify(getMockRecipientBAC(), Mockito.never()).applyStatus(request);
	}

	/**
	 * Test apply status with error.
	 */
	@Test
	public void testApplyStatusWithError()
	{
		RecipientMaintenanceRequest request = new RecipientMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);
		Recipient recipient = CommonTestRoutines.createDummyRecipient();
		recipient.setModelAction(PersistanceActionEnum.UPDATE);
		recipient.setParticipantId(String.valueOf(ONE));
		recipient.setPersonStatus(null);
		recipient.setPersonType(PersonTypeEnum.RECIPIENT);
		request.setRecipient(recipient);

		Mockito.when(getMockRecipientBAC().applyStatus(request))
		.thenReturn(createFetchResponse(recipient));

		RecipientResponse response = getRecipientBAI().applyStatus(request);
		CommonTestRoutines.assertMessages(response, LOG, PROSPERITASGLOBAL_BASE_PERSONVALIDATOR_PERSON_STATUS_REQUIRED);

		Mockito.verify(getMockRecipientBAC(), Mockito.never()).applyStatus(request);
	}

	/**
	 * Test update risk.
	 */
	@Test
	public void testUpdateRisk()
	{
		RecipientMaintenanceRequest request = new RecipientMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		Recipient recipient = new Recipient();
		recipient.setId(FIFTEEN);
		recipient.setModelAction(PersistanceActionEnum.NONE);

		Risk risk = CommonTestRoutines.createDummyRisk();
		risk.setModelAction(PersistanceActionEnum.UPDATE);
		recipient.setRisk(risk);
		request.setRecipient(recipient);

		InternalResultsResponse<Recipient> internalResponse = new InternalResultsResponse<Recipient>();
		internalResponse.setStatus(Status.OperationSuccess);

		Mockito.when(getMockRecipientBAC().updateRecipient(request)).thenReturn(internalResponse);

		RecipientResponse response = getRecipientBAI().updateRecipient(request);

		CommonTestRoutines.assertResponse(response);

		Mockito.verify(getMockRecipientBAC()).updateRecipient(request);
	}

	/**
	 * Test insert risk note too big.
	 */
	@Test
	public void testInsertRiskNoteTooBig()
	{
		// Test Insert Success
		RecipientMaintenanceRequest request = new RecipientMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		Risk risk = CommonTestRoutines.createDummyRisk();
		risk.setModelAction(PersistanceActionEnum.UPDATE);

		risk.setRiskLevelNote("a");
		for (int i = 0; i < TWO_HUNDRED_FIFTY_SIX; i++)
		{
			risk.setRiskLevelNote(risk.getRiskLevelNote().concat("b"));
		}

		Recipient recipient = new Recipient();
		recipient.setId(FIFTEEN);
		recipient.setModelAction(PersistanceActionEnum.NONE);

		recipient.setRisk(risk);
		request.setRecipient(recipient);

		InternalResultsResponse<Recipient> internalResponse = new InternalResultsResponse<Recipient>();
		internalResponse.setStatus(Status.OperationSuccess);

		Mockito.when(getMockRecipientBAC().updateRecipient(request)).thenReturn(internalResponse);

		RecipientResponse response = getRecipientBAI().updateRecipient(request);

		CommonTestRoutines.assertMessages(response, LOG, SENDSOLV_BASE_PERSONVALIDATOR_RISKLEVELNOTE_TOO_BIG);

		Mockito.verify(getMockRecipientBAC(), Mockito.never()).updateRecipient(request);
	}

	/**
	 * Test insert risk fields missing.
	 */
	@Test
	public void testInsertRiskFieldsMissing()
	{
		// Test Insert Success
		RecipientMaintenanceRequest request = new RecipientMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		Risk risk = CommonTestRoutines.createDummyRisk();
		risk.setModelAction(PersistanceActionEnum.UPDATE);

		risk.setRiskLevel(null);

		Recipient recipient = new Recipient();
		recipient.setId(FIFTEEN);
		recipient.setModelAction(PersistanceActionEnum.NONE);

		recipient.setRisk(risk);
		request.setRecipient(recipient);

		InternalResultsResponse<Recipient> internalResponse = new InternalResultsResponse<Recipient>();
		internalResponse.setStatus(Status.OperationSuccess);

		Mockito.when(getMockRecipientBAC().updateRecipient(request)).thenReturn(internalResponse);

		RecipientResponse response = getRecipientBAI().updateRecipient(request);

		CommonTestRoutines.assertMessages(response, LOG, SENDSOLV_BASE_PERSONVALIDATOR_RISKLEVEL_REQUIRED);

		Mockito.verify(getMockRecipientBAC(), Mockito.never()).updateRecipient(request);
	}

	/**
	 * Test fetch recipient by request null.
	 */
	@Test
	public void testFetchRecipientByRequestNull()
	{
		Mockito.when(getMockRecipientBAC().fetchRecipientByRequest(Matchers.any(RecipientInquiryRequest.class)))
		.thenReturn(createFetchResponse(CommonTestRoutines.createDummyRecipient()));

		RecipientInquiryRequest request = null;
		RecipientResponse response = getRecipientBAI().fetchRecipientByRequest(request);

		CommonTestRoutines.assertMessages(response, LOG, RECIPIENT_INQUIRY_REQUEST_REQUIRED);
	}

	/**
	 * Creates the fetch response.
	 *
	 * @param recipient the recipient
	 * @return the internal results response< recipient>
	 */
	private InternalResultsResponse<Recipient> createFetchResponse(Recipient recipient)
	{
		InternalResultsResponse<Recipient> response = new InternalResultsResponse<Recipient>();
		response.addResult(recipient);
		return response;
	}
}
