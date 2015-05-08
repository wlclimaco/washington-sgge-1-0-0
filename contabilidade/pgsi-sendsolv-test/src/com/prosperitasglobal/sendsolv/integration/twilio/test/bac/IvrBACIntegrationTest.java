package com.prosperitasglobal.sendsolv.integration.twilio.test.bac;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.w3c.dom.NodeList;

import com.prosperitasglobal.cbof.model.Contact;
import com.prosperitasglobal.cbof.model.ContactTypeEnum;
import com.prosperitasglobal.cbof.model.Phone;
import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.cbof.unittest.util.AbstractTestBaseDAC;
import com.prosperitasglobal.cbof.unittest.util.CommonTestRoutines;
import com.prosperitasglobal.sendsolv.integration.twilio.bac.IIvrBAC;
import com.prosperitasglobal.sendsolv.integration.twilio.bac.IIvrModel;
import com.prosperitasglobal.sendsolv.integration.twilio.model.CallRecord;
import com.prosperitasglobal.sendsolv.integration.twilio.model.CallRecordContext;
import com.prosperitasglobal.sendsolv.integration.twilio.model.IvrIdentity;
import com.prosperitasglobal.sendsolv.integration.twilio.model.SupportedLanguage;
import com.prosperitasglobal.sendsolv.integration.twilio.model.request.IvrRequest;
import com.prosperitasglobal.sendsolv.model.Member;
import com.prosperitasglobal.sendsolv.model.MoneyTransfer;
import com.prosperitasglobal.sendsolv.model.MoneyTransferBatch;
import com.qat.framework.model.QATModel.PersistanceActionEnum;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.validation.ValidationUtil;

/**
 * The Class IvrBACIntegrationTest.
 */
@ContextConfiguration(locations = {
		"classpath:com/prosperitasglobal/sendsolv/integration/twilio/test/bac/conf/ivrbacimpltest.xml"})
public class IvrBACIntegrationTest extends AbstractTestBaseDAC
{

	/** The Constant ONE. */
	private static final int ONE = 1;

	/** The Constant THREE. */
	private static final int THREE = 3;

	/** The Constant FOUR. */
	private static final int FOUR = 4;

	/** The Constant FIVE. */
	private static final int FIVE = 5;

	/** The Constant TEN. */
	private static final int TEN = 10;

	/** The Constant SHOULD_RETURN_1_STRING_ONLY. */
	private static final String SHOULD_RETURN_1_STRING_ONLY = "Should return 1 String only";

	/** The Constant RESPONSE_CANNOT_BE_NULL. */
	private static final String RESPONSE_CANNOT_BE_NULL = "Response cannot be null";

	/** The Constant GREETING. */
	private static final String GREETING = "greeting";

	/** The Constant LANGUAGE_CONTROLLER. */
	private static final String LANGUAGE_CONTROLLER = "language";

	/** The Constant PHONE_CONTROLLER. */
	private static final String PHONE_CONTROLLER = "phone";

	/** The Constant VALIDATE_PIN. */
	private static final String VALIDATE_PIN = "pin";

	/** The Constant VALIDATE_PHONE. */
	private static final String VALIDATE_PHONE = "validate_phone";

	/** The Constant MAIN_MENU. */
	private static final String MAIN_MENU = "main_menu";

	/** The Constant RELOAD. */
	private static final String RELOAD = "reload";

	/** The Constant DEFAULT. */
	private static final String DEFAULT = "default";

	/** The Constant PRIOR_TRANSACTION_MENU. */
	private static final String PRIOR_TRANSACTION_MENU = "prior_transaction_menu";

	/** The Constant READ_PRIOR_TRANSACTION. */
	private static final String READ_PRIOR_TRANSACTION = "read_prior_transaction";

	/** The Constant LANGUAGE. */
	private static final String LANGUAGE = "en";

	/** The Constant PHONE. */
	private static final String PHONE = "+123";

	/** The Constant CALL_SID. */
	private static final String CALL_SID = "ABX";

	/** The Constant START_DATE_TIME. */
	private static final Long START_DATE_TIME = 1L;

	/** The Constant STOP_DATE_TIME. */
	private static final Long STOP_DATE_TIME = 2L;

	/** The Constant DURATION. */
	private static final Integer DURATION = 20;

	/** The Constant CURRENT_SEARCH_DATE. */
	private static final Long CURRENT_SEARCH_DATE = 3L;

	/** The Constant CURRENT_IVR_OPTION. */
	private static final Integer CURRENT_IVR_OPTION = 1;

	/** The Constant ENTER_PHONE_OPTION. */
	private static final String ENTER_PHONE_OPTION = "2";

	/** The Constant TRANSFER_TO_CSR_OPTION. */
	private static final String TRANSFER_TO_CSR_OPTION = "0";

	/** The Constant INTERNATIONAL_CALL_OPTION. */
	private static final String INTERNATIONAL_CALL_OPTION = "7";

	/** The Constant FIRST_NAME. */
	private static final String FIRST_NAME = "John";

	/** The Constant LAST_NAME. */
	private static final String LAST_NAME = "Doe";

	/** The Constant PHONE_NUMBER. */
	private static final String PHONE_NUMBER = "14021112222";

	/** The Constant PIN_NUMBER. */
	private static final String PIN_NUMBER = "1234";

	/** The Constant XPTO. */
	private static final String XPTO = "xpto";

	/** The ivr bac impl. */
	private IIvrBAC ivrBACImpl;

	/** The main url. */
	private String mainUrl;

	/** The action path. */
	private String controllerPath;

	/** The resource path. */
	private String resourcePath;

	/** The static resource path. */
	private String staticResourcePath;

	/** The supported languages list. */
	private List<SupportedLanguage> supportedLanguagesList;

	/** The options list. */
	private HashMap<String, HashMap<String, IIvrModel>> optionsList;

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(IvrBACIntegrationTest.class);

	/** The Constant ANY_PIN. */
	private static final String ANY_PIN = "12345";

	/**
	 * Test no action found.
	 */
	@Test
	public void testNoActionFound()
	{
		prepareForTest(ONE);

		IvrRequest ivrRequest = createIvrRequest();

		ivrRequest.setDigits(PHONE_NUMBER);

		ivrRequest.setIvrController(XPTO);

		InternalResultsResponse<String> response = getIvrBACImpl().processIvrOption(ivrRequest);

		assertNoActionFound(response);
	}

	/**
	 * Test invalid selection.
	 */
	@Test
	public void testInvalidSelection()
	{
		prepareForTest(ONE);

		IvrRequest ivrRequest = createIvrRequest();

		ivrRequest.setIvrController(LANGUAGE_CONTROLLER);

		ivrRequest.setDigits("9");

		InternalResultsResponse<String> response = getIvrBACImpl().processIvrOption(ivrRequest);

		assertInvalidAction(response);
	}

	/**
	 * Test language controller.
	 */
	@Test
	public void testLanguageController()
	{
		prepareForTest(ONE);

		IvrRequest ivrRequest = createIvrRequest();

		ivrRequest.setIvrController(LANGUAGE_CONTROLLER);

		ivrRequest.setDigits("2");

		InternalResultsResponse<String> response = getIvrBACImpl().processIvrOption(ivrRequest);

		assertFetch(response, "Response/Gather/Say", FOUR);

		assertFetch(response, "Response/Say", ONE);

		assertFetch(response, "Response/Redirect", ONE);
	}

	/**
	 * Test greeting action happy path.
	 */
	@Test
	public void testGreetingAction()
	{
		IvrRequest ivrRequest = createIvrRequest();

		ivrRequest.setIvrController(GREETING);

		ivrRequest.setCallSid("A1");
		IvrIdentity ivrIdentity = new IvrIdentity();
		ivrIdentity.setPhoneNumber("11");
		ivrRequest.setIvrIdentity(ivrIdentity);

		InternalResultsResponse<String> response =
				(getOptionsList().get(GREETING).get(DEFAULT)).resolveAction(ivrRequest, null);

		assertGreeting(response);
	}

	/**
	 * Test greeting action reload.
	 */
	@Test
	public void testGreetingActionReload()
	{
		IvrRequest ivrRequest = createIvrRequest();

		ivrRequest.setIvrController(GREETING);

		ivrRequest.setAction(RELOAD);

		InternalResultsResponse<String> response =
				(getOptionsList().get(GREETING).get(DEFAULT)).resolveAction(ivrRequest, null);

		assertGreeting(response);

	}

	/**
	 * Test phone action enter phone.
	 */
	@Test
	public void testPhoneActionEnterPhone()
	{
		prepareForTest(THREE);

		IvrRequest ivrRequest = createIvrRequest();

		ivrRequest.setIvrController(PHONE_CONTROLLER);
		ivrRequest.setDigits(ENTER_PHONE_OPTION);

		InternalResultsResponse<String> response = getIvrBACImpl().processIvrOption(ivrRequest);

		assertFetch(response, "Response/Gather/Say", ONE);

		assertFetch(response, "Response/Say", ONE);

		assertFetch(response, "Response/Redirect", ONE);

	}

	/**
	 * Test phone action csr action.
	 */
	@Test
	public void testPhoneActionCsrAction()
	{
		prepareForTest(THREE);

		IvrRequest ivrRequest = createIvrRequest();

		ivrRequest.setIvrController(PHONE_CONTROLLER);
		ivrRequest.setDigits(TRANSFER_TO_CSR_OPTION);

		InternalResultsResponse<String> response = getIvrBACImpl().processIvrOption(ivrRequest);

		assertFetch(response, "Response/Say", ONE);

		assertFetch(response, "Response/Dial", ONE);

	}

	/**
	 * Test validate phone when phone is not found.
	 */
	@Test
	public void testPhoneActionValidateNoPhoneFound()
	{
		prepareForTest(THREE);

		IvrRequest ivrRequest = createIvrRequest();

		ivrRequest.setIvrController(VALIDATE_PHONE);

		InternalResultsResponse<String> response = getIvrBACImpl().processIvrOption(ivrRequest);

		// Phone not found
		assertFetch(response, "Response/Gather/Say", THREE);
	}

	/**
	 * Test validate phone when phone is found.
	 */
	@Test
	public void testPhoneActionValidatePhoneFound()
	{
		CallRecord callRecord = prepareForTest(THREE);

		IvrRequest ivrRequest = createIvrRequest(callRecord.getOriginatingPhone(), null);

		ivrRequest.setIvrController(VALIDATE_PHONE);

		InternalResultsResponse<String> response = getIvrBACImpl().processIvrOption(ivrRequest);

		// Phone found, request pin
		assertFetch(response, "Response/Gather/Say", ONE);
	}

	/**
	 * Test validate pin when pin is found.
	 */
	@Test
	public void testPhoneActionValidatePinFound()
	{
		Member member = insertMember(99); // This will be the pin

		CallRecord callRecord = prepareForTest(ONE, member);

		IvrRequest ivrRequest = createIvrRequest(callRecord.getOriginatingPhone(), member.getPinNumber());
		ivrRequest.setDigits(member.getPinNumber());

		ivrRequest.setIvrController(VALIDATE_PIN);

		InternalResultsResponse<String> response = getIvrBACImpl().processIvrOption(ivrRequest);

		// PIN ok, bring menu
		assertFetch(response, "Response/Gather/Say", FIVE);
	}

	/**
	 * Test validate pin when pin is not found.
	 */
	@Test
	public void testPhoneActionValidatePinNotFound()
	{
		Member member = insertMember(99); // This will be the pin

		CallRecord callRecord = prepareForTest(ONE, member);

		IvrRequest ivrRequest = createIvrRequest(callRecord.getOriginatingPhone(), member.getPinNumber());
		ivrRequest.setDigits(ANY_PIN);

		ivrRequest.setIvrController(VALIDATE_PIN);

		InternalResultsResponse<String> response = getIvrBACImpl().processIvrOption(ivrRequest);

		// PIN not found
		assertFetch(response, "Response/Gather/Say", FOUR);
	}

	@Test
	public void testMultipleLocations()
	{
		CallRecord callRecord = prepareForTest(THREE);

		IvrRequest ivrRequest = createIvrRequest();

		ivrRequest.setDigits("1");
		ivrRequest.setIvrController(MAIN_MENU);

		InternalResultsResponse<String> response = getIvrBACImpl().processIvrOption(ivrRequest);

		assertFetch(response, "Response/Redirect", ONE);
	}

	/**
	 * Test fetch money transfer action. Reads transaction details.
	 */
	@Test
	public void testFetchMoneyTransferAction()
	{
		CallRecord callRecord = prepareForTest(THREE);

		IvrRequest ivrRequest = createIvrRequest();

		ivrRequest.setDigits("6");
		ivrRequest.setIvrController(READ_PRIOR_TRANSACTION);

		InternalResultsResponse<String> response =
				(getOptionsList().get(READ_PRIOR_TRANSACTION).get("6")).resolveAction(ivrRequest, callRecord);

		assertFetch(response, "Response/Say", TEN);

		response =
				(getOptionsList().get(READ_PRIOR_TRANSACTION).get("6")).resolveAction(ivrRequest, callRecord);

		assertFetch(response, "Response/Say", TEN);
	}

	/**
	 * Test fetch money transfer action no records.
	 */
	@Test
	public void testFetchMoneyTransferActionNoRecords()
	{
		Member theMember = insertMember();

		CallRecord callRecord = insertCallRecord(theMember, new InternalResponse());

		IvrRequest ivrRequest = createIvrRequest();

		ivrRequest.setDigits("1");
		ivrRequest.setIvrController(MAIN_MENU);

		InternalResultsResponse<String> response =
				(getOptionsList().get(MAIN_MENU).get("1")).resolveAction(ivrRequest, callRecord);

		assertFetch(response, "Response/Gather/Say", THREE);

	}

	/**
	 * Test when in transaction Summary and selects 'Repeat the menu'
	 *
	 * <entry key="8">
	 * <ref bean="priorTransactionMenuModel"/>
	 * </entry>.
	 */
	@Test
	public void testFetchMoneyTransferActionRepeatThisMenu()
	{
		CallRecord callRecord = prepareForTest(ONE);

		IvrRequest ivrRequest = createIvrRequest();

		ivrRequest.setDigits("8");
		ivrRequest.setIvrController(PRIOR_TRANSACTION_MENU);

		InternalResultsResponse<String> response =
				(getOptionsList().get(PRIOR_TRANSACTION_MENU).get("8")).resolveAction(ivrRequest, callRecord);

		assertFetch(response, "Response/Gather/Say", FIVE);

	}

	/**
	 * Test fetch money transfer action csr disclaimer.
	 */
	@Test
	public void testFetchMoneyTransferActionCsrDisclaimer()
	{
		CallRecord callRecord = prepareForTest(THREE);

		IvrRequest ivrRequest = createIvrRequest();

		ivrRequest.setDigits(TRANSFER_TO_CSR_OPTION);
		ivrRequest.setIvrController(READ_PRIOR_TRANSACTION);

		InternalResultsResponse<String> response =
				(getOptionsList().get(READ_PRIOR_TRANSACTION).get(TRANSFER_TO_CSR_OPTION)).resolveAction(ivrRequest,
						callRecord);

		assertDisclaimer(response);
	}

	/**
	 * Test fetch money transfer action international call disclaimer.
	 */
	@Test
	public void testFetchMoneyTransferActionInternationalCallDisclaimer()
	{
		CallRecord callRecord = prepareForTest(THREE);

		IvrRequest ivrRequest = createIvrRequest();

		ivrRequest.setDigits(INTERNATIONAL_CALL_OPTION);
		ivrRequest.setIvrController(READ_PRIOR_TRANSACTION);

		InternalResultsResponse<String> response =
				(getOptionsList().get(READ_PRIOR_TRANSACTION).get(INTERNATIONAL_CALL_OPTION)).resolveAction(ivrRequest,
						callRecord);

		assertDisclaimer(response);
	}

	/**
	 * Creates the ivr request.
	 *
	 * @return the ivr request
	 */
	private IvrRequest createIvrRequest()
	{
		return createIvrRequest(null, null);
	}

	/**
	 * Creates the ivr request.
	 *
	 * @param phone the phone
	 * @param pin the pin
	 * @return the ivr request
	 */
	private IvrRequest createIvrRequest(String phone, String pin)
	{
		IvrRequest ivrRequest = new IvrRequest();
		ivrRequest.setSupportedLanguagesList(getSupportedLanguagesList());
		ivrRequest.setMainUrl(getMainUrl());
		ivrRequest.setControllerPath(getControllerPath());
		ivrRequest.setResourcePath(getResourcePath());
		ivrRequest.setStaticResourcePath(getStaticResourcePath());
		ivrRequest.setCallSid(CALL_SID);

		IvrIdentity ivrIdentity = new IvrIdentity();

		if (ValidationUtil.isNullOrEmpty(phone))
		{
			ivrIdentity.setPhoneNumber(PHONE_NUMBER);
		}
		else
		{
			ivrIdentity.setPhoneNumber(phone);
		}

		if (ValidationUtil.isNullOrEmpty(pin))
		{
			ivrIdentity.setPinNumber((PIN_NUMBER));
		}
		else
		{
			ivrIdentity.setPinNumber(pin);
		}

		ivrRequest.setIvrIdentity(ivrIdentity);

		return ivrRequest;
	}

	/**
	 * Assert invalid action.
	 *
	 * @param response the response
	 */
	private void assertInvalidAction(InternalResultsResponse<String> response)
	{
		assertGeneric(response);

		NodeList nodeList = CommonTestRoutines.simpleXPath(response.getFirstResult(), "Response/Say");

		assertEquals("Should get 1 Say node", 1, nodeList.getLength());

		nodeList = CommonTestRoutines.simpleXPath(response.getFirstResult(), "Response/Redirect");

		assertEquals("Should get 1 Redirect node", 1, nodeList.getLength());
	}

	/**
	 * Assert no action found.
	 *
	 * @param response the response
	 */
	private void assertNoActionFound(InternalResultsResponse<String> response)
	{
		assertGeneric(response);

		NodeList nodeList = CommonTestRoutines.simpleXPath(response.getFirstResult(), "Response/Say");

		assertEquals("Should get 1 Say node", 1, nodeList.getLength());

		nodeList = CommonTestRoutines.simpleXPath(response.getFirstResult(), "Response/Hangup");

		assertEquals("Should get 1 Hangup node", 1, nodeList.getLength());
	}

	/**
	 * Assert disclaimer.
	 *
	 * @param response the response
	 */
	private void assertDisclaimer(InternalResultsResponse<String> response)
	{
		assertGeneric(response);

		NodeList nodeList = CommonTestRoutines.simpleXPath(response.getFirstResult(), "Response/Play");

		assertEquals("Should get 1 Play node", 1, nodeList.getLength());

		nodeList = CommonTestRoutines.simpleXPath(response.getFirstResult(), "Response/Redirect");

		assertEquals("Should get 1 Redirect node", 1, nodeList.getLength());
	}

	/**
	 * Assert fetch.
	 *
	 * @param response the response
	 * @param xPath the x path
	 * @param numberOfNodes the number of nodes
	 */
	private void assertFetch(InternalResultsResponse<String> response, String xPath, int numberOfNodes)
	{
		assertGeneric(response);

		NodeList nodeList = CommonTestRoutines.simpleXPath(response.getFirstResult(), xPath);

		assertEquals("Should get " + numberOfNodes + " nodes", numberOfNodes, nodeList.getLength());
	}

	/**
	 * Assert generic.
	 *
	 * @param response the response
	 */
	private void assertGeneric(InternalResultsResponse<String> response)
	{
		assertNotNull(RESPONSE_CANNOT_BE_NULL, response);

		assertEquals(SHOULD_RETURN_1_STRING_ONLY, 1, response.getResultsList().size());
	}

	/**
	 * Prepare for test.
	 *
	 * @param numberOfMoneyTransfersToCreate the number of money transfers to create
	 * @return the call record
	 */
	private CallRecord prepareForTest(int numberOfMoneyTransfersToCreate)
	{
		return prepareForTest(numberOfMoneyTransfersToCreate, null);
	}

	/**
	 * Prepare for test.
	 *
	 * @param numberOfMoneyTransfersToCreate the number of money transfers to create
	 * @param member the member
	 * @return the call record
	 */
	private CallRecord prepareForTest(int numberOfMoneyTransfersToCreate, Member member)
	{
		InternalResponse response = new InternalResponse();

		if (member == null)
		{
			member = insertMember(199); // 199 will be the PIN too. See protected Member createMember(int
			// uniqueIndexForMember)
		}

		CallRecord callRecord = insertCallRecord(member, response);

		if (!response.isInError())
		{
			callRecord.setCallRecordContextList(new ArrayList<CallRecordContext>());

			for (int index = 1; index < (numberOfMoneyTransfersToCreate + ONE); index++)
			{
				callRecord.getCallRecordContextList().add(
						insertCallRecordContext(callRecord, index, member, response));

				// read from DB again to get the updated version number (OL).
				member = getPersonDAC().fetchMemberById(new FetchByIdRequest(member.getId())).getFirstResult();
			}
		}

		return callRecord;
	}

	/**
	 * Insert call record.
	 *
	 * @param member the member
	 * @param response the response
	 * @return the call record
	 */
	private CallRecord insertCallRecord(Member member, InternalResponse response)
	{
		CallRecord callRecord = new CallRecord();
		callRecord.setCallSid(CALL_SID);
		callRecord.setLanguage(LANGUAGE);
		callRecord.setOriginatingPhone(PHONE);
		callRecord.setStartDateTimeUTC(START_DATE_TIME);
		callRecord.setStopDateTimeUTC(STOP_DATE_TIME);
		callRecord.setCallDurationSeconds(DURATION);
		callRecord.setCurrentSearchDate(CURRENT_SEARCH_DATE);
		callRecord.setCurrentIvrOption(CURRENT_IVR_OPTION);

		callRecord.setParentPersonId(member.getId());

		for (Contact contact : member.getContactList())
		{
			if (contact.getContactType().equals(ContactTypeEnum.PHONE_WORK))
			{
				Phone phone = (Phone)contact;
				callRecord.setOriginatingPhone("1" + phone.getNumber());
			}
		}

		response = getIvrDACImpl().insertCallRecord(callRecord);

		return callRecord;
	}

	/**
	 * Insert call record context.
	 *
	 * @param callRecord the call record
	 * @param ivrOption the ivr option
	 * @param member the member
	 * @param response the response
	 * @return the call record context
	 */
	private CallRecordContext insertCallRecordContext(CallRecord callRecord, Integer ivrOption, Member member,
			InternalResponse response)
	{
		// This routine Inserts a member with pin = ivrOption
		MoneyTransferBatch moneyTransferBatch = insertMoneyTransferBatch(ivrOption);

		// Since this routine will update Member, make sure we set action=None on contacts so they are not inserted
		// again.
		if (!ValidationUtil.isNullOrEmpty(member.getContactList()))
		{
			for (Contact contact : member.getContactList())
			{
				contact.setModelAction(PersistanceActionEnum.NONE);
			}
		}

		MoneyTransfer moneyTransfer =
				insertMoneyTransfer(Integer.parseInt(member.getPinNumber().trim()), moneyTransferBatch, member);

		CallRecordContext callRecordContext = new CallRecordContext();
		callRecordContext.setIvrOption(ivrOption);
		callRecordContext.setParentId(callRecord.getId());
		callRecordContext.setMoneyTransferId(moneyTransfer.getId());
		callRecordContext.setRecipientFirstName(FIRST_NAME);
		callRecordContext.setRecipientLastName(LAST_NAME);

		response = getIvrDACImpl().insertCallRecordContext(callRecordContext);

		return callRecordContext;
	}

	/**
	 * Assert greeting.
	 *
	 * @param response the response
	 */
	private void assertGreeting(InternalResultsResponse<String> response)
	{
		assertNotNull(RESPONSE_CANNOT_BE_NULL, response);

		assertEquals(SHOULD_RETURN_1_STRING_ONLY, 1, response.getResultsList().size());

		NodeList nodeList = CommonTestRoutines.simpleXPath(response.getFirstResult(), "Response/Gather/Say");

		assertEquals("Should get 4 nodes", 4, nodeList.getLength());
	}

	/**
	 * Gets the ivr bac impl.
	 *
	 * @return the ivr bac impl
	 */
	public IIvrBAC getIvrBACImpl()
	{
		return ivrBACImpl;
	}

	/**
	 * Sets the ivr bac impl.
	 *
	 * @param ivrBACImpl the new ivr bac impl
	 */
	@Resource
	public void setIvrBACImpl(IIvrBAC ivrBACImpl)
	{
		this.ivrBACImpl = ivrBACImpl;
	}

	/**
	 * Gets the supported languages list.
	 *
	 * @return the supported languages list
	 */
	public List<SupportedLanguage> getSupportedLanguagesList()
	{
		return supportedLanguagesList;
	}

	/**
	 * Sets the supported languages list.
	 *
	 * @param supportedLanguagesList the new supported languages list
	 */
	@Resource
	public void setSupportedLanguagesList(List<SupportedLanguage> supportedLanguagesList)
	{
		this.supportedLanguagesList = supportedLanguagesList;
	}

	/**
	 * Gets the resource path.
	 *
	 * @return the resource path
	 */
	public String getResourcePath()
	{
		return resourcePath;
	}

	/**
	 * Sets the resource path.
	 *
	 * @param resourcePath the new resource path
	 */
	@Resource
	public void setResourcePath(String resourcePath)
	{
		this.resourcePath = resourcePath;
	}

	/**
	 * Gets the options list.
	 *
	 * @return the options list
	 */
	public HashMap<String, HashMap<String, IIvrModel>> getOptionsList()
	{
		return optionsList;
	}

	/**
	 * Sets the options list.
	 *
	 * @param optionsList the options list
	 */
	@Resource
	public void setOptionsList(HashMap<String, HashMap<String, IIvrModel>> optionsList)
	{
		this.optionsList = optionsList;
	}

	/**
	 * Gets the main url.
	 *
	 * @return the main url
	 */
	public String getMainUrl()
	{
		return mainUrl;
	}

	/**
	 * Sets the main url.
	 *
	 * @param mainUrl the new main url
	 */
	@Resource
	public void setMainUrl(String mainUrl)
	{
		this.mainUrl = mainUrl;
	}

	/**
	 * Gets the action path.
	 *
	 * @return the action path
	 */
	public String getControllerPath()
	{
		return controllerPath;
	}

	/**
	 * Sets the action path.
	 *
	 * @param controllerPath the new action path
	 */
	@Resource
	public void setControllerPath(String controllerPath)
	{
		this.controllerPath = controllerPath;
	}

	/**
	 * Gets the static resource path.
	 *
	 * @return the static resource path
	 */
	public String getStaticResourcePath()
	{
		return staticResourcePath;
	}

	/**
	 * Sets the static resource path.
	 *
	 * @param staticResourcePath the new static resource path
	 */
	@Resource
	public void setStaticResourcePath(String staticResourcePath)
	{
		this.staticResourcePath = staticResourcePath;
	}
}
