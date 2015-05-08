package com.prosperitasglobal.cbof.unittest.util;

import java.io.StringReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.junit.Assert;
import org.slf4j.Logger;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.prosperitasglobal.cbof.model.Address;
import com.prosperitasglobal.cbof.model.BusinessTypeEnum;
import com.prosperitasglobal.cbof.model.CodeValue;
import com.prosperitasglobal.cbof.model.Contact;
import com.prosperitasglobal.cbof.model.ContactTypeEnum;
import com.prosperitasglobal.cbof.model.Country;
import com.prosperitasglobal.cbof.model.Currency;
import com.prosperitasglobal.cbof.model.Email;
import com.prosperitasglobal.cbof.model.FilingStatusEnum;
import com.prosperitasglobal.cbof.model.GenderEnum;
import com.prosperitasglobal.cbof.model.Language;
import com.prosperitasglobal.cbof.model.Note;
import com.prosperitasglobal.cbof.model.Phone;
import com.prosperitasglobal.cbof.model.Range;
import com.prosperitasglobal.cbof.model.StateProvinceRegion;
import com.prosperitasglobal.sendsolv.model.Account;
import com.prosperitasglobal.sendsolv.model.AccountTypeEnum;
import com.prosperitasglobal.sendsolv.model.BusinessProductPlan;
import com.prosperitasglobal.sendsolv.model.CountryUsage;
import com.prosperitasglobal.sendsolv.model.CountryUsageEnum;
import com.prosperitasglobal.sendsolv.model.CurrencyAvailability;
import com.prosperitasglobal.sendsolv.model.CurrencyPurchase;
import com.prosperitasglobal.sendsolv.model.CustomFee;
import com.prosperitasglobal.sendsolv.model.DailyCurrencyRate;
import com.prosperitasglobal.sendsolv.model.DailyCurrencyRateDetail;
import com.prosperitasglobal.sendsolv.model.Document;
import com.prosperitasglobal.sendsolv.model.DocumentType;
import com.prosperitasglobal.sendsolv.model.EmploymentInfo;
import com.prosperitasglobal.sendsolv.model.EnrollmentTypeEnum;
import com.prosperitasglobal.sendsolv.model.FeeTier;
import com.prosperitasglobal.sendsolv.model.FrequencyBasedEvent;
import com.prosperitasglobal.sendsolv.model.FrequencyBasedEventCalendar;
import com.prosperitasglobal.sendsolv.model.FrequencyBasedEventTypeEnum;
import com.prosperitasglobal.sendsolv.model.FrequencyEventBiWeekly;
import com.prosperitasglobal.sendsolv.model.FrequencyEventMonthly;
import com.prosperitasglobal.sendsolv.model.FrequencyEventOneDay;
import com.prosperitasglobal.sendsolv.model.FrequencyEventSemiMonthly;
import com.prosperitasglobal.sendsolv.model.FrequencyEventWeekly;
import com.prosperitasglobal.sendsolv.model.Liaison;
import com.prosperitasglobal.sendsolv.model.LiaisonTypeEnum;
import com.prosperitasglobal.sendsolv.model.Location;
import com.prosperitasglobal.sendsolv.model.Member;
import com.prosperitasglobal.sendsolv.model.MoneyTransfer;
import com.prosperitasglobal.sendsolv.model.MoneyTransferAmount;
import com.prosperitasglobal.sendsolv.model.MoneyTransferBatch;
import com.prosperitasglobal.sendsolv.model.MoneyTransferBatchStatus;
import com.prosperitasglobal.sendsolv.model.MoneyTransferBatchStatusEnum;
import com.prosperitasglobal.sendsolv.model.MoneyTransferParticipant;
import com.prosperitasglobal.sendsolv.model.MoneyTransferParticipantAddress;
import com.prosperitasglobal.sendsolv.model.MoneyTransferParticipantPersonName;
import com.prosperitasglobal.sendsolv.model.MoneyTransferStatus;
import com.prosperitasglobal.sendsolv.model.MoneyTransferStatusEnum;
import com.prosperitasglobal.sendsolv.model.MoneyTransferTransaction;
import com.prosperitasglobal.sendsolv.model.Organization;
import com.prosperitasglobal.sendsolv.model.Payer;
import com.prosperitasglobal.sendsolv.model.PaymentType;
import com.prosperitasglobal.sendsolv.model.PaymentTypeEnum;
import com.prosperitasglobal.sendsolv.model.PayrollTypeEnum;
import com.prosperitasglobal.sendsolv.model.Person;
import com.prosperitasglobal.sendsolv.model.PersonName;
import com.prosperitasglobal.sendsolv.model.PersonTypeEnum;
import com.prosperitasglobal.sendsolv.model.PlanCategory;
import com.prosperitasglobal.sendsolv.model.PoliticallyExposedPersonEnum;
import com.prosperitasglobal.sendsolv.model.PriorityEnum;
import com.prosperitasglobal.sendsolv.model.Product;
import com.prosperitasglobal.sendsolv.model.ProductPlan;
import com.prosperitasglobal.sendsolv.model.ProductPlanApplicability;
import com.prosperitasglobal.sendsolv.model.ProductPlanTemplateGroup;
import com.prosperitasglobal.sendsolv.model.Recipient;
import com.prosperitasglobal.sendsolv.model.Risk;
import com.prosperitasglobal.sendsolv.model.RiskLevelEnum;
import com.prosperitasglobal.sendsolv.model.SARStatusEnum;
import com.prosperitasglobal.sendsolv.model.StatusEnum;
import com.prosperitasglobal.sendsolv.model.SuspiciousActivity;
import com.prosperitasglobal.sendsolv.model.TemplateProductPlan;
import com.prosperitasglobal.sendsolv.model.TransferSetting;
import com.prosperitasglobal.sendsolv.model.TransferTypeEnum;
import com.prosperitasglobal.sendsolv.util.PGSiDateUtil;
import com.qat.framework.model.Message.MessageSeverity;
import com.qat.framework.model.MessageInfo;
import com.qat.framework.model.QATModel;
import com.qat.framework.model.QATModel.PersistanceActionEnum;
import com.qat.framework.model.QATModelOL;
import com.qat.framework.model.SortExpression.Direction;
import com.qat.framework.model.UserContext;
import com.qat.framework.model.request.Request;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResponse.Status;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.model.response.Response;
import com.qat.framework.util.QATMessageUtil;
import com.qat.framework.validation.ValidationUtil;

/**
 * The Class CommonTestRoutines.
 */
public final class CommonTestRoutines
{
	private static final String CURRENCY_CODE_MXP = "MXP";

	/** The bronze plan. */
	private static final String BRONZE_PLAN_CATEGORY = "Bronze";

	/** The silver plan. */
	private static final String SILVER_PLAN_CATEGORY = "Silver";

	/** The gold plan. */
	private static final String GOLD_PLAN_CATEGORY = "Gold";

	/** The Constant CURRENCY_NAME. */
	private static final String CURRENCY_NAME = "US Dollar";

	/** The Constant CURRENCY_CODE. */
	private static final String CURRENCY_CODE = "USD";

	/** The Constant CURRENCY_CODE. */
	private static final String FOREIGN_CURRENCY_CODE = CURRENCY_CODE_MXP;

	/** The Constant RANDOM. */
	private static final Random RANDOM = new Random();

	/** The Constant NUMBER_RANGE. */
	private static final Integer NUMBER_RANGE = Integer.MAX_VALUE;

	/** The Constant UPDATED. */
	private static final String UPDATED = "UPDATED";

	/** The Constant LAST_NAME. */
	private static final String LAST_NAME = "Last";

	/** The Constant FIRST_NAME. */
	private static final String FIRST_NAME = "First";

	/** The Constant MIDDLE_NAME. */
	private static final String MIDDLE_NAME = "Middle";

	/** The Constant TWO_HUNDRED. */
	private static final BigDecimal TWO_HUNDRED = new BigDecimal("200.00");

	/** The Constant LOC_NAME. */
	private static final String LOC_NAME = "Alex Loc";

	/** The Constant ORG_NAME. */
	private static final String ORG_NAME = "Alex Org";

	/** The Constant FOURTY_FIVE. */
	private static final Integer FOURTY_FIVE = 45;

	/** The Constant NAICS_CODE. */
	private static final String NAICS_CODE = "111110    ";

	/** The Constant SIC_ID. */
	private static final Integer SIC_ID = 3527;

	/** The Constant NAICS_ID. */
	private static final Integer NAICS_ID = 6095;

	/** The Constant HUNDRED. */
	private static final String HUNDRED = "100";

	/** The Constant EMPLOYER_IDENTIFICATION_NUMBER. */
	private static final String EMPLOYER_IDENTIFICATION_NUMBER = "123456789";

	/** The Constant RESPONSE_NOT_NULL. */
	private static final String RESPONSE_NOT_NULL = "Response object should not to be null";

	/** The dummy work email. */
	private static final String DUMMY_WORK_EMAIL = "theCeo@mycompany.com";

	/** The dummy home email. */
	private static final String DUMMY_HOME_EMAIL = "theCeo@myhome.com";

	/** The dummy country code. */
	private static final String DUMMY_COUNTRY_CODE = "USA";

	/** The dummy state. */
	private static final String DUMMY_STATE = "NE";

	/** The dummy state description. */
	private static final String DUMMY_STATE_DESCRIPTION = "Nebraska";

	/** The dummy extension. */
	private static final String DUMMY_EXTENSION = "1234";

	/** The dummy home number. */
	private static final String DUMMY_HOME_NUMBER = "9975647";

	/** The dummy work number. */
	private static final String DUMMY_WORK_NUMBER = "4023919200";

	/** The dummy home city. */
	private static final String DUMMY_HOME_CITY = "Omaha";

	/** The dummy work city. */
	private static final String DUMMY_WORK_CITY = "Lincoln";

	/** The dummy adress line1. */
	private static final String DUMMY_ADRESS_LINE1 = "9205 Sesame St";

	/** The dummy adress line2. */
	private static final String DUMMY_ADRESS_LINE2 = "apt 468";

	/** The dummy adress line3. */
	private static final String DUMMY_ADRESS_LINE3 = "9204 Sesame St";

	/** The dummy adress line4. */
	private static final String DUMMY_ADRESS_LINE4 = "apt 467";

	/** The Constant DUMMY_RISK_LEVEL_NOTE. */
	private static final String DUMMY_RISK_LEVEL_NOTE = "This client is very risky";

	/** The dummy postal code. */
	private static final String DUMMY_POSTAL_CODE = "68102";

	/** The Constant dummyCreateUser. */
	private static final String DUMMY_CREATE_USER = "JDOE";

	/** The Constant dummyCreateDateUTC. */
	private static final Long DUMMY_CREATE_DATE_UTC = PGSiDateUtil.getCurrentDateTimeMillis();

	/** The Constant DUMMY_UPDATE_USER. */
	private static final String DUMMY_UPDATE_USER = "MARY DOE";

	/** The Constant DUMMY_TITLE. */
	private static final String DUMMY_TITLE = "CIO";

	/** The Constant DUMMY_SSN. */
	private static final String DUMMY_SSN = "111223333";

	/** The Constant ONE. */
	private static final Integer ONE = 1;

	/** Constant $10.00 dollars. */
	private static final BigDecimal TEN_DOLLARS = new BigDecimal("10.00");

	/** Constant FOREIGN_EXCHANGE_RATE. */
	private static final BigDecimal FOREIGN_EXCHANGE_RATE = new BigDecimal("13.00");

	/** The Constant DUMMY_PAYER_ID. */
	private static final Integer DUMMY_PAYER_ID = 2087;

	/** The Constant 10 days. */
	private static final Integer TEN_DAYS = 10;

	/** The Constant 20 days. */
	private static final Integer TWENTY_DAYS = 20;

	/**
	 * The Constructor.
	 */
	private CommonTestRoutines()
	{

	}

	/**
	 * Gets the random int.
	 *
	 * @return the random int
	 */
	public static Integer getRandomInt()
	{
		return RANDOM.nextInt(NUMBER_RANGE);
	}

	/**
	 * Helper method to update the QATModel attributes on an update.
	 *
	 * @param model The QAT model.
	 */
	public static void assignQATModelUpdateFields(QATModel model)
	{
		model.setModelAction(PersistanceActionEnum.UPDATE);
		model.setModifyDateUTC(PGSiDateUtil.getCurrentDateTimeMillis());
		model.setModifyUser("NewUser");
	}

	/**
	 * Compare 2 attributes against the sort.
	 *
	 * @param attribute1 The first attribute.
	 * @param attribute2 The second attribute.
	 * @param direction The sort direction.
	 */
	public static void assertAttributeSort(String attribute1, String attribute2, Direction direction)
	{
		if (Direction.Ascending == direction)
		{
			Assert.assertTrue("Asc Sort Incorrect! Attribute Name " + attribute1 + " is greater than Attribute "
					+ attribute2, attribute1.toUpperCase().compareTo(attribute2.toUpperCase()) <= 0);
		}
		else
		{
			Assert.assertTrue("Desc Sort Incorrect! Attribute Name " + attribute1 + " is less than Attribute "
					+ attribute2, attribute1.toUpperCase().compareTo(attribute2.toUpperCase()) >= 0);
		}
	}

	/**
	 * Compare 2 different QATModel objects.
	 *
	 * @param qatModel The first QATModel object.
	 * @param dbQatModel The second QATModel object.
	 * @param action The model action performed.
	 */
	public static void assertFieldsQATModel(QATModel qatModel, QATModel dbQatModel, PersistanceActionEnum action)
	{
		Assert.assertEquals("QATModel create date UTC", qatModel.getCreateDateUTC(), dbQatModel.getCreateDateUTC());
		Assert.assertEquals("QATModel create user", qatModel.getCreateUser(), dbQatModel.getCreateUser());

		if (action == PersistanceActionEnum.INSERT)
		{
			Assert.assertNull("DB QATModel modify date UTC is not null", dbQatModel.getModifyDateUTC());
			Assert.assertNull("DB QATModel modify user is not null", dbQatModel.getModifyUser());
		}
		else if ((action == PersistanceActionEnum.UPDATE) || (action == PersistanceActionEnum.NONE))
		{
			Assert.assertEquals("QATModel modify date UTC", dbQatModel.getModifyDateUTC(), qatModel.getModifyDateUTC());
			Assert.assertEquals("QATModel modify user", qatModel.getModifyUser(), dbQatModel.getModifyUser());
		}
	}

	/**
	 * Compare 2 different QATModelOL objects.
	 *
	 * @param qatModelOL The first QATModel object.
	 * @param dbQatModelOL The second QATModel object.
	 * @param action The model action performed.
	 */
	public static void assertFieldsQATModelOL(QATModelOL qatModelOL, QATModelOL dbQatModelOL,
			PersistanceActionEnum action)
	{
		CommonTestRoutines.assertFieldsQATModel(qatModelOL, dbQatModelOL, action);

		if (action == PersistanceActionEnum.INSERT)
		{
			Assert.assertEquals("DB QATModelOL version not 0 for an INSERT", 0, dbQatModelOL.getVersion().intValue());
		}
		else if (action == PersistanceActionEnum.UPDATE)
		{
			Assert.assertEquals("DB QATModelOL version is not 1 more than before the UPDATE", qatModelOL.getVersion()
					.intValue() + 1, dbQatModelOL.getVersion().intValue());
		}
		else if (action == PersistanceActionEnum.NONE)
		{
			Assert.assertEquals("QATModelOL version is not equal to DB QATModlOL", qatModelOL.getVersion().intValue(),
					dbQatModelOL.getVersion().intValue());
		}
	}

	/**
	 * Validate assert response.
	 *
	 * @param response the response
	 */
	public static void assertResultResponse(InternalResultsResponse<?> response)
	{
		assertResponse(response);
		Assert.assertEquals("Status should to be success", Status.OperationSuccess, response.getStatus());
		Assert.assertTrue("Result list should not to be empty", response.hasResults());
	}

	/**
	 * Validate assert group.
	 *
	 * @param response the response
	 */
	public static void assertResponse(InternalResponse response)
	{
		Assert.assertNotNull(RESPONSE_NOT_NULL, response);
		Assert.assertNotNull("Message list object should not to be null", response.getMessageInfoList());

		List<MessageInfo> messagesInfo = response.getMessageInfoList();
		for (MessageInfo messageInfo : messagesInfo)
		{
			if (messageInfo.getSeverity() != MessageSeverity.Info)
			{
				Assert.fail("Message severity should not to be: " + messageInfo.getSeverity());
			}
		}
	}

	/**
	 * Assert response.
	 *
	 * @param response the response
	 */
	public static void assertResponse(Response response)
	{
		Assert.assertNotNull(RESPONSE_NOT_NULL, response);
		Assert.assertTrue("Response object should to be operation success", response.isOperationSuccess());
	}

	/**
	 * Creates the dummy user context.
	 *
	 * @param request the request
	 */
	public static void createDummyUserContext(Request request)
	{

		request.setUserContext(createDummyUserContext());
	}

	/**
	 * Creates the dummy user context.
	 *
	 * @return the user context
	 */
	public static UserContext createDummyUserContext()
	{
		UserContext userContext = new UserContext();
		userContext.setUserId("TestUser");

		return userContext;

	}

	/**
	 * Creates one dummy Organization (along with Contacts).
	 *
	 * @return the organization
	 */
	public static Organization createDummyOrganization()
	{
		Organization organization = new Organization();
		organization.setId(getRandomInt());
		organization.setKey("OAA00001");
		organization.setBusinessType(BusinessTypeEnum.ORGANIZATION);
		organization.setName(ORG_NAME);
		organization.setDbaName("Alex Org Dba");
		organization.setEmployerIdentificationNumber(EMPLOYER_IDENTIFICATION_NUMBER);
		organization.setNumberOfLocations(FOURTY_FIVE);
		organization.setIsPayrollCentralized(PayrollTypeEnum.CENTRALIZED);
		organization.setStatus(StatusEnum.ACTIVE);

		organization.setCreateDateUTC(PGSiDateUtil.getCurrentDateTimeMillis());
		organization.setCreateUser(DUMMY_CREATE_USER);

		Risk risk = new Risk();
		risk.setRiskLevel(RiskLevelEnum.LOW);
		risk.setRiskLevelNote(DUMMY_RISK_LEVEL_NOTE);
		organization.setRisk(risk);

		Country country = new Country();
		country.setCode(DUMMY_COUNTRY_CODE);
		organization.setCountry(country);

		Range r = new Range();
		r.setId(1);
		organization.setNumberOfEmployees(r);
		organization.setNumberOfMigrantWorkers(r);

		CodeValue naics = new CodeValue();
		naics.setId(NAICS_ID);
		naics.setCode(NAICS_CODE);

		CodeValue sic = new CodeValue();
		sic.setId(SIC_ID);
		sic.setCode(HUNDRED);

		organization.setNorthAmericanIndustryClassificationSystemCode(naics);
		organization.setStandardIndustrialClassificationCode(sic);

		ArrayList<Contact> contactList = new ArrayList<Contact>();
		contactList.add(createDummyEmail(ContactTypeEnum.EMAIL_PERSONAL));
		contactList.add(createDummyEmail(ContactTypeEnum.EMAIL_WORK));

		contactList.add(createDummyPhone(ContactTypeEnum.OTHER, PriorityEnum.SECONDARY));
		// Make this Phone primary
		contactList.add(createDummyPhone(ContactTypeEnum.PHONE_WORK, PriorityEnum.PRIMARY));

		contactList.add(createDummyAddress(ContactTypeEnum.POSTAL_HOME));
		contactList.add(createDummyAddress(ContactTypeEnum.POSTAL_WORK));

		organization.setContactList(contactList);

		return organization;
	}

	/**
	 * Creates the frequency base event.
	 *
	 * @return the frequency based event
	 */
	public static FrequencyBasedEvent createFrequencyBaseEvent()
	{
		FrequencyEventBiWeekly frequencyEventBiWeekly = new FrequencyEventBiWeekly();
		frequencyEventBiWeekly.setEffectiveStartDate(PGSiDateUtil.getCurrentDateMillisUTC());
		frequencyEventBiWeekly.setCreateDateUTC(PGSiDateUtil.getCurrentDateTimeMillis());
		frequencyEventBiWeekly.setType(FrequencyBasedEventTypeEnum.PAY_DAY);
		frequencyEventBiWeekly.setCreateUser(DUMMY_CREATE_USER);

		return frequencyEventBiWeekly;
	}

	/**
	 * Creates the dummy frequency base event calendar.
	 *
	 * @return the frequency based event calendar
	 */
	public static FrequencyBasedEventCalendar createDummyFrequencyBaseEventCalendar()
	{
		FrequencyBasedEventCalendar frequencyBasedEventCalendar = new FrequencyBasedEventCalendar();
		frequencyBasedEventCalendar.setEventDate(DUMMY_CREATE_DATE_UTC);
		frequencyBasedEventCalendar.setFrequencyBasedEventId(FOURTY_FIVE);
		frequencyBasedEventCalendar.setId(ONE);
		return frequencyBasedEventCalendar;
	}

	/**
	 * Fill in frequency based event.
	 *
	 * @param argEvent the arg event
	 * @param eventType the event type
	 * @return the frequency based event
	 */
	public static FrequencyBasedEvent fillInFrequencyBasedEvent(FrequencyBasedEvent argEvent,
			FrequencyBasedEventTypeEnum eventType)
	{
		argEvent.setModelAction(PersistanceActionEnum.INSERT);
		argEvent.setEffectiveStartDate(PGSiDateUtil.getCurrentDateMillisUTC());
		argEvent.setCreateDateUTC(PGSiDateUtil.getCurrentDateTimeMillis());
		argEvent.setType(eventType);
		argEvent.setCreateUser(DUMMY_CREATE_USER);

		return argEvent;
	}

	/**
	 * Creates the frequency base event.
	 *
	 * @return the frequency based event
	 */
	public static List<FrequencyBasedEvent> createFrequencyBaseEventList()
	{
		List<FrequencyBasedEvent> eventList = new ArrayList<FrequencyBasedEvent>();

		FrequencyBasedEvent frequencyBasedEvent = new FrequencyEventBiWeekly();
		eventList.add(fillInFrequencyBasedEvent(frequencyBasedEvent, FrequencyBasedEventTypeEnum.PAY_DAY));

		frequencyBasedEvent = new FrequencyEventWeekly();
		eventList.add(fillInFrequencyBasedEvent(frequencyBasedEvent, FrequencyBasedEventTypeEnum.PAY_PREPARATION));

		frequencyBasedEvent = new FrequencyEventMonthly();
		eventList.add(fillInFrequencyBasedEvent(frequencyBasedEvent, FrequencyBasedEventTypeEnum.BATCH_APPROVAL));

		frequencyBasedEvent = new FrequencyEventSemiMonthly();
		eventList.add(fillInFrequencyBasedEvent(frequencyBasedEvent,
				FrequencyBasedEventTypeEnum.CHANGE_TRANSFER_SETTING));

		frequencyBasedEvent = new FrequencyEventOneDay();
		eventList.add(fillInFrequencyBasedEvent(frequencyBasedEvent, FrequencyBasedEventTypeEnum.FUNDS_TRANSFER));

		return eventList;
	}

	/**
	 * Creates one dummy Location (along with Contacts).
	 *
	 * @param organization the organization
	 * @return the location
	 */

	@Transactional
	public static Location createDummyLocation(Organization organization)
	{

		Location location = new Location();
		location.setParentOrganizationId(organization.getId());
		location.setKey("LAA00001");
		location.setFrequencyBasedEventList(createFrequencyBaseEventList());
		location.setBatchApprovalDayLimit(-TWENTY_DAYS);
		location.setFundsTransferDayLimit(-TEN_DAYS);

		location.setBusinessType(BusinessTypeEnum.LOCATION);
		location.setName(LOC_NAME);
		location.setEmployerIdentificationNumber(EMPLOYER_IDENTIFICATION_NUMBER);
		location.setStatus(StatusEnum.ACTIVE);

		location.setCreateDateUTC(PGSiDateUtil.getCurrentDateTimeMillis());
		location.setCreateUser(DUMMY_CREATE_USER);

		Country country = new Country();
		country.setCode(DUMMY_COUNTRY_CODE);
		location.setCountry(country);

		Range r = new Range();
		r.setId(1);
		location.setNumberOfEmployees(r);
		location.setNumberOfMigrantWorkers(r);

		CodeValue naics = new CodeValue();
		naics.setId(NAICS_ID);
		naics.setCode(NAICS_CODE);

		CodeValue sic = new CodeValue();
		sic.setId(SIC_ID);
		sic.setCode(HUNDRED);

		location.setNorthAmericanIndustryClassificationSystemCode(naics);
		location.setStandardIndustrialClassificationCode(sic);

		ArrayList<Contact> contactList = createDummyListOfContact();

		location.setContactList(contactList);

		return location;
	}

	/**
	 * Creates the dummy recipient.
	 *
	 * @return the recipient
	 */
	public static Recipient createDummyRecipient()
	{

		// Main info
		Recipient recipient = new Recipient();
		recipient.setPersonType(PersonTypeEnum.RECIPIENT);

		populateDocumentList(recipient, BusinessTypeEnum.RECIPIENT);
		populatePersonData(recipient);

		return recipient;
	}

	/**
	 * Creates the dummy liaison.
	 *
	 * @return the liaison
	 */
	public static Liaison createDummyLiaison()
	{
		Organization organization = createDummyOrganization();
		return createDummyLiaison(organization);
	}

	/**
	 * Creates the dummy liaison.
	 *
	 * @param organization the organization
	 * @return the liaison
	 */
	public static Liaison createDummyLiaison(Organization organization)
	{
		return createDummyLiaison(organization.getId());
	}

	/**
	 * Creates the dummy liaison.
	 *
	 * @param parentId the parent id
	 * @return the liaison
	 */
	public static Liaison createDummyLiaison(Integer parentId)
	{
		// Main info
		Liaison liaison = new Liaison();
		liaison.setPersonType(PersonTypeEnum.LIAISON);

		populateDocumentList(liaison, BusinessTypeEnum.LIAISON);
		populatePersonData(liaison);

		// liaison specific data
		liaison.setLocationId(parentId);
		liaison.setTitle(DUMMY_TITLE);
		liaison.setLiaisonType(LiaisonTypeEnum.EXECUTIVE);

		return liaison;
	}

	/**
	 * Creates the updated dummy liaison.
	 *
	 * @param liaison the liaison
	 * @return the liaison
	 */
	public static Liaison createUpdatedDummyLiaison(Liaison liaison)
	{
		liaison.setTitle(liaison.getTitle() + UPDATED);
		liaison.setLiaisonType(LiaisonTypeEnum.EXECUTIVE);
		populateUpdateDocumentList(liaison, BusinessTypeEnum.LIAISON);
		populateUpdatePersonData(liaison);

		return liaison;

	}

	/**
	 * Creates the updated dummy liaison.
	 *
	 * @param recipient the recipient
	 * @return the liaison
	 */
	public static Recipient createUpdatedDummyRecipient(Recipient recipient)
	{
		populateUpdateDocumentList(recipient, BusinessTypeEnum.RECIPIENT);
		populateUpdatePersonData(recipient);

		return recipient;
	}

	/**
	 * Creates the updated dummy member.
	 *
	 * @param member the member
	 * @return the member
	 */
	public static Member createUpdatedDummyMember(Member member)
	{
		populateUpdateDocumentList(member, BusinessTypeEnum.MEMBER);
		populateUpdatePersonData(member);
		populateUpdateCountryUsageData(member);
		populateUpdateEmploymentInfo(member);
		return member;
	}

	/**
	 * Populate document list.
	 *
	 * @param person the person
	 * @param businessType the business type
	 */
	private static void populateDocumentList(Person person, BusinessTypeEnum businessType)
	{
		Document document = createDummyDocument(businessType);
		document.setValue(DUMMY_SSN);
		document.setModelAction(PersistanceActionEnum.INSERT);
		document.setCreateDateUTC(PGSiDateUtil.getCurrentDateTimeMillis());
		document.setCreateUser(DUMMY_CREATE_USER);
		document.setIssueCountry(new Country(DUMMY_COUNTRY_CODE));
		document.setIssueStateProvinceRegion(new StateProvinceRegion(DUMMY_STATE));
		document.setExpirationDate(DUMMY_CREATE_DATE_UTC);
		person.setDocumentList(Arrays.asList(document));
	}

	/**
	 * Populate update document list.
	 *
	 * @param person the person
	 * @param businessType the business type
	 */
	private static void populateUpdateDocumentList(Person person, BusinessTypeEnum businessType)
	{
		Document document = createDummyDocument(businessType);
		document.setValue(DUMMY_SSN);
		document.setExpirationDate(PGSiDateUtil.getCurrentDateMillisUTC());
		document.setModelAction(PersistanceActionEnum.UPDATE);
		document.setModifyDateUTC(PGSiDateUtil.getCurrentDateTimeMillis());
		document.setModifyUser(DUMMY_CREATE_USER);
		person.setDocumentList(Arrays.asList(document));
	}

	/**
	 * Populate update country usage data.
	 *
	 * @param member The member.
	 */
	private static void populateUpdateCountryUsageData(Member member)
	{
		List<CountryUsage> countryUsageList = member.getCountryUsageList();
		for (CountryUsage countryUsage : countryUsageList)
		{
			countryUsage.setPersonId(member.getId());
			countryUsage.setModelAction(PersistanceActionEnum.UPDATE);
			countryUsage.setCountry(new Country("GBR"));
			countryUsage.setModifyDateUTC(PGSiDateUtil.getCurrentDateTimeMillis());
			countryUsage.setModifyUser(DUMMY_CREATE_USER);
		}
	}

	/**
	 * Populate update employment info.
	 *
	 * @param member the member
	 */
	private static void populateUpdateEmploymentInfo(Member member)
	{
		List<EmploymentInfo> employmentInfoList = member.getEmploymentInfoList();
		for (EmploymentInfo employmentInfo : employmentInfoList)
		{
			employmentInfo.setMemberId(getRandomInt());
			employmentInfo.setCurrentPay(TWO_HUNDRED);
			employmentInfo.setPayPerPeriod(TWO_HUNDRED);
			employmentInfo.setHireDate(1L);
			employmentInfo.setModelAction(PersistanceActionEnum.UPDATE);
			employmentInfo.setEmploymentInfoStatus(StatusEnum.ACTIVE);
			employmentInfo.setModifyDateUTC(PGSiDateUtil.getCurrentDateTimeMillis());
			employmentInfo.setModifyUser(DUMMY_CREATE_USER);
		}
	}

	/**
	 * Populate update person data.
	 *
	 * @param person the person
	 */
	private static void populateUpdatePersonData(Person person)
	{
		person.setModelAction(PersistanceActionEnum.UPDATE);
		person.setGender(GenderEnum.FEMALE);
		person.setFirstName(person.getFirstName() + UPDATED);
		person.setMiddleName(person.getMiddleName() + UPDATED);
		person.setLastName(person.getLastName() + UPDATED);

		person.setDateOfBirth(2L);
		person.setPepStatus(PoliticallyExposedPersonEnum.NO);
		person.setPersonStatus(StatusEnum.ACTIVE);

		person.setModifyDateUTC(PGSiDateUtil.getCurrentDateTimeMillis());
		person.setModifyUser(DUMMY_UPDATE_USER);

		// Name
		for (PersonName personName : person.getNameList())
		{
			personName.setModelAction(PersistanceActionEnum.UPDATE);
			personName.setOtherName(personName.getOtherName() + UPDATED);
			personName.setModifyDateUTC(PGSiDateUtil.getCurrentDateTimeMillis());
			personName.setModifyUser(DUMMY_UPDATE_USER);
		}
	}

	/**
	 * Populate person data.
	 *
	 * @param person the person
	 */
	private static void populatePersonData(Person person)
	{
		person.setId(getRandomInt());
		person.setGender(GenderEnum.MALE);
		person.setFirstName(FIRST_NAME);
		person.setMiddleName(MIDDLE_NAME);
		person.setLastName(LAST_NAME);
		person.setPrefix(createDummySuffixPrefix());
		person.setSuffix(createDummySuffixPrefix());

		person.setDateOfBirth(1L);
		person.setPepStatus(PoliticallyExposedPersonEnum.YES);
		person.setPersonStatus(StatusEnum.ACTIVE);

		person.setModelAction(PersistanceActionEnum.INSERT);
		person.setCreateDateUTC(PGSiDateUtil.getCurrentDateTimeMillis());
		person.setCreateUser(DUMMY_CREATE_USER);

		person.setSocialSecurityNumber("000000000");

		// Contact
		ArrayList<Contact> contactList = new ArrayList<Contact>();
		contactList.add(createDummyEmail(ContactTypeEnum.EMAIL_PERSONAL));
		contactList.add(createDummyEmail(ContactTypeEnum.EMAIL_WORK));

		contactList.add(createDummyPhone(ContactTypeEnum.OTHER, PriorityEnum.SECONDARY));
		// Make this Phone primary
		contactList.add(createDummyPhone(ContactTypeEnum.PHONE_WORK, PriorityEnum.PRIMARY));

		contactList.add(createDummyAddress(ContactTypeEnum.POSTAL_HOME));
		contactList.add(createDummyAddress(ContactTypeEnum.POSTAL_WORK));
		person.setContactList(contactList);

		// Risk
		person.setRisk(createDummyRisk());

		// Notes
		ArrayList<Note> noteList = new ArrayList<Note>();
		noteList.add(createDummyNote(BusinessTypeEnum.MEMBER, null));
		noteList.add(createDummyNote(BusinessTypeEnum.MEMBER, null));
		person.setNoteList(noteList);

		// Name
		ArrayList<PersonName> nameList = new ArrayList<PersonName>();
		nameList.add(createDummyPersonName());
		person.setNameList(nameList);
	}

	/**
	 * Populate data for a product plan.
	 *
	 * @param productPlan The product plan to default the data.
	 * @param product the product
	 * @param payer The payer to associate to this product plan.
	 */
	private static void populateProductPlanData(ProductPlan productPlan, Product product, Payer payer)
	{
		productPlan.setId(1);
		productPlan.setPlanStatus(StatusEnum.SETUP);
		productPlan.setModelAction(PersistanceActionEnum.INSERT);
		productPlan.setDefaultProductPlan(false);
		productPlan.setEffectiveStartDate(PGSiDateUtil.getCurrentDateMillisUTC());
		productPlan.setEffectiveEndDate(PGSiDateUtil.getCurrentDateMillisUTC());
		productPlan.setName("business product");

		productPlan.setPlanCategoryList(new ArrayList<PlanCategory>());
		productPlan.getPlanCategoryList().add(createDummyPlanCategory(GOLD_PLAN_CATEGORY));
		productPlan.getPlanCategoryList().add(createDummyPlanCategory(SILVER_PLAN_CATEGORY));
		productPlan.getPlanCategoryList().add(createDummyPlanCategory(BRONZE_PLAN_CATEGORY));

		productPlan.setCreateDateUTC(PGSiDateUtil.getCurrentDateTimeMillis());
		productPlan.setCreateUser(DUMMY_CREATE_USER);

		ProductPlanApplicability productPlanApplicability = createDummyProductPlanApplicability(payer);
		// productPlanApplicability.getPayer().setId(SEVEN);
		productPlan.setProductPlanApplicabilityList(new ArrayList<ProductPlanApplicability>());
		productPlan.getProductPlanApplicabilityList().add(productPlanApplicability);

		productPlan.setParentProduct(product);
	}

	/**
	 * Creates the dummy note.
	 *
	 * @param parentKeyType the parent key type
	 * @param parentKeyValue the parent key value
	 * @return the note
	 */
	public static Note createDummyNote(BusinessTypeEnum parentKeyType, Integer parentKeyValue)
	{
		Note note = new Note();
		note.setId(null);
		note.setNoteText("NoteText");
		note.setParentKeyType(parentKeyType);
		note.setParentKey(parentKeyValue);

		note.setModelAction(PersistanceActionEnum.INSERT);
		note.setCreateDateUTC(PGSiDateUtil.getCurrentDateTimeMillis());
		note.setCreateUser(DUMMY_CREATE_USER);
		return note;
	}

	/**
	 * Creates the dummy person name.
	 *
	 * @return the person name
	 */
	public static PersonName createDummyPersonName()
	{
		PersonName personName = new PersonName();

		personName.setOtherName("Other Name");
		personName.setPersonId(null);

		personName.setModelAction(PersistanceActionEnum.INSERT);
		personName.setCreateDateUTC(DUMMY_CREATE_DATE_UTC);
		personName.setCreateUser(DUMMY_CREATE_USER);
		return personName;
	}

	/**
	 * Creates the dummy suffix prefix.
	 *
	 * @return the code value
	 */
	public static CodeValue createDummySuffixPrefix()
	{
		CodeValue code = new CodeValue();
		code.setId(getRandomInt());
		code.setCode(String.valueOf(getRandomInt()));
		code.setValue(String.valueOf(getRandomInt()));
		return code;
	}

	/**
	 * Creates the dummy template product plan.
	 *
	 * @param product The product to associate with the template product plan.
	 * @param payer The payer to associate with the template product plan.
	 * @return The template product plan.
	 */
	public static TemplateProductPlan createDummyTemplateProductPlan(Product product, Payer payer)
	{
		TemplateProductPlan templateProductPlan = new TemplateProductPlan();
		templateProductPlan.setModelAction(PersistanceActionEnum.INSERT);
		populateProductPlanData(templateProductPlan, product, payer);
		return templateProductPlan;
	}

	/**
	 * Creates the dummy list of documents.
	 *
	 * @param documentTypeList the document type list
	 * @return the list< document>
	 */
	public static List<Document> createDummyListOfDocuments(ArrayList<DocumentType> documentTypeList)
	{
		ArrayList<Document> documentList = new ArrayList<Document>();

		for (DocumentType documentType : documentTypeList)
		{
			Document d = new Document();
			d.setNoteText("the note" + documentType.getId());

			d.setDocumentType(documentType);
			d.setCreateUser(DUMMY_CREATE_USER);
			d.setCreateDateUTC(DUMMY_CREATE_DATE_UTC);
			d.setModelAction(PersistanceActionEnum.INSERT);
			documentList.add(d);
		}

		return documentList;
	}

	/**
	 * Creates the dummy document.
	 *
	 * @param partentKeyType the partent key type
	 * @return the document
	 */
	public static Document createDummyDocument(BusinessTypeEnum partentKeyType)
	{
		Document document = new Document();
		document.setId(1);
		document.setParentKey(1);
		document.setParentKeyType(partentKeyType);
		document.setDocumentType(new DocumentType());
		document.getDocumentType().setName(FIRST_NAME);
		document.getDocumentType().setId(1);
		document.setFilingStatus(FilingStatusEnum.FILED);
		document.setIsActionRequired(false);

		document.setCreateDateUTC(DUMMY_CREATE_DATE_UTC);
		document.setCreateUser(DUMMY_CREATE_USER);
		return document;
	}

	/**
	 * Creates the dummy risk.
	 *
	 * @return the risk
	 */
	public static Risk createDummyRisk()
	{
		Risk risk = new Risk();
		risk.setParentKey(1);
		risk.setParentKeyType(BusinessTypeEnum.ORGANIZATION);
		risk.setRiskLevel(RiskLevelEnum.LOW);
		risk.setRiskLevelNote(DUMMY_RISK_LEVEL_NOTE);

		risk.setCreateDateUTC(PGSiDateUtil.getCurrentDateTimeMillis());
		risk.setCreateUser(DUMMY_CREATE_USER);
		return risk;
	}

	/**
	 * Creates two dummy emails.
	 *
	 * @param type the type
	 * @return the contact
	 */
	public static Contact createDummyEmail(ContactTypeEnum type)
	{
		Email email = new Email();
		email.setId(1);
		email.setContactType(type);
		email.setPriority(PriorityEnum.PRIMARY);
		email.setVerified(true);
		email.setCreateDateUTC(DUMMY_CREATE_DATE_UTC);
		email.setCreateUser(DUMMY_CREATE_USER);
		email.setModelAction(PersistanceActionEnum.INSERT);

		switch (type)
		{
			case EMAIL_PERSONAL:

				email.setEmailAddress(DUMMY_HOME_EMAIL);
				break;

			case EMAIL_WORK:

				email.setEmailAddress(DUMMY_WORK_EMAIL);
				break;

			default:
				break;
		}

		return email;
	}

	/**
	 * Creates a dummy {@link FeeTier} object.
	 *
	 * @param minValue The minimum value for this tier.
	 * @param maxValue The maximum value for this tier.
	 * @return The {@link FeeTier} object.
	 */
	public static FeeTier createDummyFeeTier(BigDecimal minValue, BigDecimal maxValue)
	{
		FeeTier feeTier = new FeeTier();
		feeTier.setModelAction(PersistanceActionEnum.INSERT);
		feeTier.setFeeValue(TEN_DOLLARS);
		feeTier.setMaximumValue(maxValue);
		feeTier.setMinimumValue(minValue);
		feeTier.setSequenceNumber(ONE);
		feeTier.setCreateDateUTC(PGSiDateUtil.getCurrentDateTimeMillis());
		feeTier.setCreateUser(DUMMY_CREATE_USER);
		return feeTier;
	}

	/**
	 * Creates two dummy phones.
	 *
	 * @param type the type
	 * @param priority the priority
	 * @return the contact
	 */
	public static Contact createDummyPhone(ContactTypeEnum type, PriorityEnum priority)
	{
		Phone phone = new Phone();
		phone.setId(1);
		phone.setContactType(type);
		phone.setPriority(priority);
		phone.setVerified(true);

		Country country = new Country();
		country.setCode(DUMMY_COUNTRY_CODE);
		phone.setCountry(country);

		phone.setExtension(DUMMY_EXTENSION);

		phone.setCreateDateUTC(DUMMY_CREATE_DATE_UTC);
		phone.setCreateUser(DUMMY_CREATE_USER);
		phone.setModelAction(PersistanceActionEnum.INSERT);

		switch (type)
		{
			case OTHER:

				phone.setNumber(DUMMY_HOME_NUMBER);
				break;

			case PHONE_WORK:

				phone.setNumber(DUMMY_WORK_NUMBER);
				break;
			default:
				break;

		}

		return phone;
	}

	/**
	 * Creates a dummy {@link PlanCategory} object.
	 *
	 * @param name The name of this plan.
	 * @return The {@link PlanCategory} object.
	 */
	public static PlanCategory createDummyPlanCategory(String name)
	{
		PlanCategory planCategory = new PlanCategory();
		planCategory.setModelAction(PersistanceActionEnum.INSERT);
		planCategory.setCallCreditAllowance(TEN_DOLLARS);
		planCategory.setName(name);
		planCategory.setFeeTierList(new ArrayList<FeeTier>());
		planCategory.getFeeTierList().add(createDummyFeeTier(new BigDecimal("0.00"), new BigDecimal("100.00")));
		planCategory.getFeeTierList().add(createDummyFeeTier(new BigDecimal("100.01"), new BigDecimal("250.00")));
		planCategory.getFeeTierList().add(createDummyFeeTier(new BigDecimal("250.01"), new BigDecimal("99999.00")));
		planCategory.setCreateDateUTC(PGSiDateUtil.getCurrentDateTimeMillis());
		planCategory.setCreateUser(DUMMY_CREATE_USER);
		return planCategory;
	}

	/**
	 * Creates a dummy {@link Product} object.
	 *
	 * @return The {@link Product} object.
	 */
	public static Product createDummyProduct()
	{
		Product product = new Product();
		product.setId(ONE);
		product.setModelAction(PersistanceActionEnum.INSERT);
		product.setDescription("Money Transfer Business");
		product.setName("Money Transfer");
		product.setProductStatus(StatusEnum.SETUP);
		product.setEffectiveStartDate(PGSiDateUtil.getCurrentDateMillisUTC());
		product.setEffectiveEndDate(PGSiDateUtil.getCurrentDateMillisUTC());
		product.setCreateDateUTC(PGSiDateUtil.getCurrentDateTimeMillis());
		product.setCreateUser(DUMMY_CREATE_USER);
		return product;
	}

	/**
	 * Creates a dummy {@link ProductPlanTemplateGroup} object.
	 *
	 * @param product The product associated with the template product plans.
	 * @param payer The payer to associate with the template product plan.
	 * @return The {@link ProductPlanTemplateGroup} object.
	 */
	public static ProductPlanTemplateGroup createDummyProductPlanTemplateGroup(Product product, Payer payer)
	{
		ProductPlanTemplateGroup productPlanTemplateGroup = new ProductPlanTemplateGroup();
		productPlanTemplateGroup.setId(1);
		productPlanTemplateGroup.setModelAction(PersistanceActionEnum.INSERT);
		productPlanTemplateGroup.setName("Template 1");
		productPlanTemplateGroup.setStatus(StatusEnum.SETUP);
		productPlanTemplateGroup.setTemplateProductPlanList(new ArrayList<TemplateProductPlan>());
		productPlanTemplateGroup.setCreateDateUTC(PGSiDateUtil.getCurrentDateTimeMillis());
		productPlanTemplateGroup.setCreateUser(DUMMY_CREATE_USER);
		productPlanTemplateGroup.setTemplateProductPlanList(new ArrayList<TemplateProductPlan>());
		productPlanTemplateGroup.getTemplateProductPlanList().add(createDummyTemplateProductPlan(product, payer));
		productPlanTemplateGroup.getTemplateProductPlanList().add(createDummyTemplateProductPlan(product, payer));
		productPlanTemplateGroup.getTemplateProductPlanList().add(createDummyTemplateProductPlan(product, payer));
		return productPlanTemplateGroup;
	}

	/**
	 * Creates two dummy addresses.
	 *
	 * @param type the type
	 * @return the contact
	 */
	public static Contact createDummyAddress(ContactTypeEnum type)
	{
		Address address = new Address();
		address.setId(1);
		address.setContactType(type);
		address.setPriority(PriorityEnum.PRIMARY);
		address.setVerified(true);

		Country country = new Country();
		country.setCode(DUMMY_COUNTRY_CODE);
		address.setCountry(country);

		StateProvinceRegion state = new StateProvinceRegion();
		state.setCode(DUMMY_STATE);
		address.setStateProvinceRegion(state);

		address.setAddressLine1(DUMMY_ADRESS_LINE1);
		address.setAddressLine2(DUMMY_ADRESS_LINE2);
		address.setAddressLine3(DUMMY_ADRESS_LINE3);
		address.setAddressLine4(DUMMY_ADRESS_LINE4);

		address.setPostalCode(DUMMY_POSTAL_CODE);

		address.setCreateDateUTC(DUMMY_CREATE_DATE_UTC);
		address.setCreateUser(DUMMY_CREATE_USER);
		address.setModelAction(PersistanceActionEnum.INSERT);

		switch (type)
		{
			case POSTAL_HOME:

				address.setCityName(DUMMY_HOME_CITY);
				break;

			case POSTAL_WORK:

				address.setCityName(DUMMY_WORK_CITY);
				break;

			default:
				break;
		}

		return address;
	}

	/**
	 * Assert messages.
	 *
	 * @param response the response
	 * @param messages the messages
	 */
	public static void assertMessages(Response response, String... messages)
	{
		Logger log = null;
		assertMessages(response, log, messages);
	}

	/**
	 * Assert messages.
	 *
	 * @param response the response
	 * @param log The logger for error messages.
	 * @param messages the messages
	 */
	public static void assertMessages(Response response, Logger log, String... messages)
	{
		Assert.assertNotNull("Response should not be null", response);

		List<MessageInfo> messageInfoList = response.getMessageInfoList();
		Assert.assertNotNull("Messages from response should not be null", messageInfoList);
		Assert.assertEquals("Messages amount should be equal", messages.length, messageInfoList.size());

		assertMessagesInfo(messageInfoList, log, messages);
	}

	/**
	 * Assert messages info.
	 *
	 * @param messagesInfo the messages info
	 * @param log The logger for error messages.
	 * @param messages the messages
	 */
	public static void assertMessagesInfo(List<MessageInfo> messagesInfo, Logger log, String... messages)
	{
		if (ValidationUtil.isNullOrEmpty(messagesInfo))
		{
			return;
		}

		List<String> messageList = Arrays.asList(messages);
		String errorMessage = "";

		for (MessageInfo messageInfo : messagesInfo)
		{
			if (!messageList.contains(messageInfo.getCode()))
			{
				Assert.fail("Response object should contains message code: "
						+ messageInfo.getCode());
			}

			errorMessage = QATMessageUtil.getMessage(messageInfo.getCode(), messageInfo.getArguments());
			if (ValidationUtil.isNull(errorMessage))
			{
				Assert.assertFalse("Error code " + messageInfo.getCode()
						+ " is missing from the Spring messageSource bean", true);
			}
			else
			{
				if (!ValidationUtil.isNull(log) && log.isDebugEnabled())
				{
					log.debug(errorMessage);
				}
			}
		}
	}

	/**
	 * Creates the dummy list of contact.
	 *
	 * @return the array list< contact>
	 */
	public static ArrayList<Contact> createDummyListOfContact()
	{
		ArrayList<Contact> contactList = new ArrayList<Contact>();
		contactList.add(createDummyEmail(ContactTypeEnum.EMAIL_PERSONAL));
		contactList.add(createDummyEmail(ContactTypeEnum.EMAIL_WORK));

		contactList.add(createDummyPhone(ContactTypeEnum.OTHER, PriorityEnum.SECONDARY));
		contactList.add(createDummyPhone(ContactTypeEnum.PHONE_WORK, PriorityEnum.PRIMARY));

		contactList.add(createDummyAddress(ContactTypeEnum.POSTAL_HOME));
		contactList.add(createDummyAddress(ContactTypeEnum.POSTAL_WORK));
		return contactList;
	}

	/**
	 * Creates the dummy member.
	 *
	 * @return the member
	 */
	public static Member createDummyMember()
	{
		Member member = new Member();
		member.setEmploymentInfoList(
				Arrays.asList(createDummyEmploymentInfo()));
		member.setCountryUsageList(
				Arrays.asList(createDummyCountryUsage(CountryUsageEnum.BIRTH),
						createDummyCountryUsage(CountryUsageEnum.RESIDENCE),
						createDummyCountryUsage(CountryUsageEnum.CITIZENSHIP)));
		member.setPersonType(PersonTypeEnum.MEMBER);
		member.setPreferredLanguage(new Language());
		member.getPreferredLanguage().setId(ONE);
		member.setBestTimeToCall("10:00 pm");
		member.setTransferSettingList(Arrays.asList(CommonTestRoutines.createDummyTransferSetting()));
		populateDocumentList(member, BusinessTypeEnum.MEMBER);
		populatePersonData(member);
		return member;
	}

	/**
	 * Creates the dummy employment info.
	 *
	 * @return the employment info
	 */
	public static EmploymentInfo createDummyEmploymentInfo()
	{
		EmploymentInfo employmentInfo = new EmploymentInfo();
		employmentInfo.setId(getRandomInt());
		employmentInfo.setMemberId(getRandomInt());
		employmentInfo.setLocationId(FOURTY_FIVE);
		employmentInfo.setLocationName(LOC_NAME);
		employmentInfo.setOrganizationId(FOURTY_FIVE);
		employmentInfo.setOrganizationName(ORG_NAME);
		employmentInfo.setCurrentPay(TWO_HUNDRED);
		employmentInfo.setPayPerPeriod(TWO_HUNDRED);
		employmentInfo.setHireDate(1L);
		employmentInfo.setModelAction(PersistanceActionEnum.INSERT);
		employmentInfo.setEmploymentInfoStatus(StatusEnum.ACTIVE);
		employmentInfo.setEnrollmentType(EnrollmentTypeEnum.PHONE);
		employmentInfo.setCreateDateUTC(PGSiDateUtil.getCurrentDateTimeMillis());
		employmentInfo.setCreateUser(DUMMY_CREATE_USER);

		return employmentInfo;
	}

	/**
	 * Creates the dummy business product plan.
	 *
	 * @param product The product associated with the business product plan.
	 * @param payer The payer to associate with the business product plan.
	 * @return The business product plan.
	 */
	public static BusinessProductPlan createDummyBusinessProductPlan(Product product, Payer payer)
	{
		return createDummyBusinessProductPlan(product, createDummyLocation(createDummyOrganization()), payer);
	}

	/**
	 * Creates the dummy business product plan.
	 *
	 * @param product The product associated with the business product plan.
	 * @param location The location associated with the business product plan.
	 * @param payer The payer to associate with the business product plan.
	 * @return The business product plan.
	 */
	public static BusinessProductPlan createDummyBusinessProductPlan(Product product, Location location, Payer payer)
	{
		BusinessProductPlan businessProductPlan = new BusinessProductPlan();
		businessProductPlan.setLocationId(location.getId());
		populateProductPlanData(businessProductPlan, product, payer);
		return businessProductPlan;
	}

	/**
	 * Creates the dummy MoneyTransferBatch.
	 *
	 * @param location The location associated with the MoneyTransferBatch.
	 * @return The MoneyTransferBatch.
	 */
	public static MoneyTransferBatch createDummyMoneyTransferBatch(Location location)
	{

		return createDummyMoneyTransferBatch(location, null);
	}

	/**
	 * Creates the dummy MoneyTransferBatch.
	 *
	 * @param location The location associated with the MoneyTransferBatch.
	 * @param batchStatusEnum The batch status enumeration.
	 * @return The MoneyTransferBatch.
	 */
	public static MoneyTransferBatch createDummyMoneyTransferBatch(Location location,
			MoneyTransferBatchStatusEnum batchStatusEnum)
	{
		MoneyTransferBatch moneyTransferBatch = new MoneyTransferBatch();
		moneyTransferBatch.setKey("BAA00001");
		moneyTransferBatch.setModelAction(PersistanceActionEnum.INSERT);
		moneyTransferBatch.setLocation(location);
		moneyTransferBatch.setId(1);

		MoneyTransferAmount moneyTransferAmount = new MoneyTransferAmount();
		moneyTransferAmount.setAmount(TEN_DOLLARS);
		moneyTransferAmount.setCountry(new Country(DUMMY_COUNTRY_CODE));
		moneyTransferAmount.setCurrency(new Currency(CURRENCY_CODE));
		moneyTransferAmount.setStateProvinceRegion(new StateProvinceRegion(DUMMY_STATE));
		moneyTransferBatch.setOriginAmount(moneyTransferAmount);

		moneyTransferBatch.setTransferDate(PGSiDateUtil.getCurrentDateMillisUTC());
		moneyTransferBatch.setPayrollReceivedDate(PGSiDateUtil.getCurrentDateMillisUTC());

		moneyTransferBatch.setStatusList(new ArrayList<MoneyTransferBatchStatus>());

		MoneyTransferBatchStatusEnum localStatusEnum = MoneyTransferBatchStatusEnum.PENDING_APPROVAL;
		if (batchStatusEnum != null)
		{
			localStatusEnum = batchStatusEnum;
		}

		moneyTransferBatch.getStatusList().add(
				createDummyMoneyTransferBatchStatus(localStatusEnum));

		moneyTransferBatch.setNoteList(new ArrayList<Note>());
		moneyTransferBatch.getNoteList().add(
				createDummyNote(BusinessTypeEnum.MONEY_TRANSFER_BATCH, moneyTransferBatch.getId()));

		moneyTransferBatch.setCreateDateUTC(PGSiDateUtil.getCurrentDateTimeMillis());
		moneyTransferBatch.setCreateUser(DUMMY_CREATE_USER);

		moneyTransferBatch.setPayPreparationDate(PGSiDateUtil.getCurrentDateMillisUTC());
		moneyTransferBatch.setReleaseUser(DUMMY_CREATE_USER);

		moneyTransferBatch.addMoneyTransfer(CommonTestRoutines.createDummyMoneyTransfer(moneyTransferBatch,
				CommonTestRoutines.createDummyTransferSetting()));

		return moneyTransferBatch;
	}

	/**
	 * Creates the dummy MoneyTransfer.
	 *
	 * @param moneyTransferBatch The money transfer batch.
	 * @param transferSetting The transfer setting to use.
	 * @return The MoneyTransfer.
	 */
	public static MoneyTransfer createDummyMoneyTransfer(MoneyTransferBatch moneyTransferBatch,
			TransferSetting transferSetting)
	{

		return createDummyMoneyTransfer(moneyTransferBatch, transferSetting, MoneyTransferStatusEnum.PENDING_APPROVAL);
	}

	/**
	 * Creates the dummy MoneyTransfer.
	 *
	 * @param moneyTransferBatch The money transfer batch.
	 * @param transferSetting The transfer setting to use.
	 * @param transferStatusEnum The transfer status enumeration.
	 * @return The MoneyTransfer.
	 */
	public static MoneyTransfer createDummyMoneyTransfer(MoneyTransferBatch moneyTransferBatch,
			TransferSetting transferSetting, MoneyTransferStatusEnum transferStatusEnum)
	{
		MoneyTransfer moneyTransfer = new MoneyTransfer();
		moneyTransfer.setKey("SAA00001");
		moneyTransfer.setModelAction(PersistanceActionEnum.INSERT);

		moneyTransfer.setMoneyTransferBatchId(moneyTransferBatch.getId());
		Account account = new Account();
		account.setNumber(1);
		account.setType(AccountTypeEnum.SAVINGS);
		moneyTransfer.setRecipientAccount(account);

		account = new Account();
		account.setNumber(0);
		account.setType(AccountTypeEnum.CHECKING);
		moneyTransfer.setSenderAccount(account);

		moneyTransfer.setConfirmationNumber("123");
		moneyTransfer
		.setDestinationAmount(new MoneyTransferAmount(TEN_DOLLARS, new Country("MEX"), new Currency(
				FOREIGN_CURRENCY_CODE)));
		moneyTransfer.setDiscountAmount(new BigDecimal("1.50"));
		moneyTransfer.setOriginFlatFee(new BigDecimal("20.00"));
		moneyTransfer.setOriginAutomatedClearingHouseFee(new BigDecimal("2.50"));
		moneyTransfer.setOriginCallCreditAmount(new BigDecimal("2.23"));
		moneyTransfer.setForeignExchangeRate(new BigDecimal("1.23"));
		moneyTransfer.setForeignExchangeRateCustomer(new BigDecimal("1.10"));

		moneyTransfer.setNoteList(new ArrayList<Note>());
		moneyTransfer.getNoteList().add(createDummyNote(BusinessTypeEnum.MONEY_TRANSFER, moneyTransfer.getId()));

		moneyTransfer.setOriginAmount(new MoneyTransferAmount(new BigDecimal("100.29"),
				new Country(DUMMY_COUNTRY_CODE), new Currency(CURRENCY_CODE)));

		moneyTransfer.setPaymentType(PaymentTypeEnum.CASH);
		moneyTransfer.setSpreadPercentage(new BigDecimal("7.78"));

		moneyTransfer.setStatusList(new ArrayList<MoneyTransferStatus>());
		MoneyTransferStatusEnum localStatusEnum = MoneyTransferStatusEnum.PENDING_APPROVAL;
		if (transferStatusEnum != null)
		{
			localStatusEnum = transferStatusEnum;
		}

		MoneyTransferStatus status = createDummyMoneyTransferStatus(localStatusEnum);
		status.setMoneyTransferTransaction(CommonTestRoutines.createDummyMoneyTransferTransaction(moneyTransfer));
		moneyTransfer.getStatusList().add(status);

		moneyTransfer.setTransferSetting(transferSetting);
		moneyTransfer.setCreateDateUTC(PGSiDateUtil.getCurrentDateTimeMillis());
		moneyTransfer.setCreateUser(DUMMY_CREATE_USER);

		moneyTransferBatch.setReleaseUser(DUMMY_CREATE_USER);

		return moneyTransfer;
	}

	/**
	 * Creates the dummy MoneyTransferBatchStatus.
	 *
	 * @param moneyTransferBatchStatusEnum The enum for the status.
	 * @return The MoneyTransferBatchStatus.
	 */
	public static MoneyTransferBatchStatus createDummyMoneyTransferBatchStatus(
			MoneyTransferBatchStatusEnum moneyTransferBatchStatusEnum)
	{
		MoneyTransferBatchStatus moneyTransferBatchStatus = new MoneyTransferBatchStatus();
		moneyTransferBatchStatus.setModelAction(PersistanceActionEnum.INSERT);
		moneyTransferBatchStatus.setMoneyTransferBatchId(ONE);
		moneyTransferBatchStatus.setActionDueDate(PGSiDateUtil.getCurrentDateMillisUTC());
		moneyTransferBatchStatus.setStatus(moneyTransferBatchStatusEnum);
		moneyTransferBatchStatus.setCreateDateUTC(PGSiDateUtil.getCurrentDateTimeMillis());
		moneyTransferBatchStatus.setCreateUser(DUMMY_CREATE_USER);
		return moneyTransferBatchStatus;
	}

	/**
	 * Creates the dummy MoneyTransferStatus.
	 *
	 * @param moneyTransferStatusEnum The enum for the status.
	 * @return The MoneyTransferStatus.
	 */
	public static MoneyTransferStatus createDummyMoneyTransferStatus(MoneyTransferStatusEnum moneyTransferStatusEnum)
	{
		MoneyTransferStatus moneyTransferStatus = new MoneyTransferStatus();
		moneyTransferStatus.setModelAction(PersistanceActionEnum.INSERT);
		moneyTransferStatus.setStatus(moneyTransferStatusEnum);
		moneyTransferStatus.setCreateDateUTC(PGSiDateUtil.getCurrentDateTimeMillis());
		moneyTransferStatus.setCreateUser(DUMMY_CREATE_USER);
		return moneyTransferStatus;
	}

	/**
	 * Creates the dummy MoneyTransferTransaction.
	 *
	 * @param moneyTransfer The money transfer.
	 * @return The MoneyTransferTransaction.
	 */
	public static MoneyTransferTransaction createDummyMoneyTransferTransaction(MoneyTransfer moneyTransfer)
	{
		MoneyTransferTransaction moneyTransferTransaction = new MoneyTransferTransaction();
		moneyTransferTransaction.setModelAction(PersistanceActionEnum.INSERT);

		moneyTransferTransaction.setKey(moneyTransfer.getKey());
		moneyTransferTransaction.setAchPayeeCode("ABC");
		moneyTransferTransaction.setConfirmationNumber(moneyTransfer.getConfirmationNumber());
		moneyTransferTransaction.setCreateDateUTC(PGSiDateUtil.getCurrentDateTimeMillis());
		moneyTransferTransaction.setCreateUser(DUMMY_CREATE_USER);

		moneyTransferTransaction.setDestinationAmount(moneyTransfer.getDestinationAmount());
		moneyTransferTransaction.setForeignExchangeRateCustomer(moneyTransfer.getForeignExchangeRateCustomer());
		moneyTransferTransaction.setOriginAmount(moneyTransfer.getOriginAmount());
		moneyTransferTransaction.setPaymentType(moneyTransfer.getPaymentType());

		MoneyTransferParticipant recipientOfTransfer = new MoneyTransferParticipant();
		recipientOfTransfer.setPhoneNumber(DUMMY_WORK_NUMBER);
		MoneyTransferParticipantAddress recipientAddress = new MoneyTransferParticipantAddress();
		recipientAddress.setAddress("Recipient 123 Someplace");
		recipientAddress.setCity("Recipient City");
		recipientAddress.setStateProvinceRegion(new StateProvinceRegion(DUMMY_STATE));
		recipientAddress.setCountry(new Country(DUMMY_COUNTRY_CODE));
		recipientAddress.setPostalCode(DUMMY_POSTAL_CODE);
		recipientOfTransfer.setAddress(recipientAddress);
		MoneyTransferParticipantPersonName recipientName = new MoneyTransferParticipantPersonName();
		recipientName.setFirstName("RecipientFirstName");
		recipientName.setLastName("RecipientLastName");
		recipientName.setMotherMaidenName("RecipientMotherMaiden");
		recipientOfTransfer.setName(recipientName);
		Account account = new Account();
		account.setNumber(moneyTransfer.getRecipientAccount().getNumber());
		account.setType(moneyTransfer.getRecipientAccount().getType());
		recipientOfTransfer.setAccount(account);
		moneyTransferTransaction.setRecipient(recipientOfTransfer);

		MoneyTransferParticipant senderOfTransfer = new MoneyTransferParticipant();
		senderOfTransfer.setPhoneNumber(DUMMY_WORK_NUMBER);
		MoneyTransferParticipantAddress senderAddress = new MoneyTransferParticipantAddress();
		senderAddress.setAddress("Sender 123 Someplace");
		senderAddress.setCity("Sender City");
		senderAddress.setStateProvinceRegion(new StateProvinceRegion(DUMMY_STATE));
		senderAddress.setCountry(new Country(DUMMY_COUNTRY_CODE));
		senderAddress.setPostalCode(DUMMY_POSTAL_CODE);
		senderOfTransfer.setAddress(senderAddress);
		MoneyTransferParticipantPersonName sendertName = new MoneyTransferParticipantPersonName();
		sendertName.setFirstName("SenderFirstName");
		sendertName.setLastName("SenderLastName");
		sendertName.setMotherMaidenName("SenderMotherMaiden");
		senderOfTransfer.setName(sendertName);
		account = new Account();
		account.setNumber(moneyTransfer.getSenderAccount().getNumber());
		account.setType(moneyTransfer.getSenderAccount().getType());
		senderOfTransfer.setAccount(account);
		moneyTransferTransaction.setSender(senderOfTransfer);

		moneyTransferTransaction.setReleaseUser(DUMMY_CREATE_USER);
		moneyTransferTransaction.setOriginFlatFee(new BigDecimal("2.01"));

		return moneyTransferTransaction;
	}

	/**
	 * Creates the dummy country usage.
	 *
	 * @param usage The usage of country.
	 * @return the country usage info.
	 */
	public static CountryUsage createDummyCountryUsage(CountryUsageEnum usage)
	{
		CountryUsage countryUsage = new CountryUsage();
		Country country = new Country(DUMMY_COUNTRY_CODE);
		country.setDescription("United States of America");
		countryUsage.setCountry(country);
		countryUsage.setUsage(usage);
		countryUsage.setModelAction(PersistanceActionEnum.INSERT);
		countryUsage.setCreateDateUTC(PGSiDateUtil.getCurrentDateTimeMillis());
		countryUsage.setCreateUser(DUMMY_CREATE_USER);
		return countryUsage;
	}

	/**
	 * Creates the dummy payer.
	 *
	 * @return the payer
	 */
	public static Payer createDummyPayer()
	{
		Payer payer = new Payer();
		payer.setModelAction(PersistanceActionEnum.INSERT);
		Country country = new Country();
		country.setCode(DUMMY_COUNTRY_CODE);
		country.setCurrencyList(Arrays.asList(createDummyCurrency()));
		payer.setAchPayeeCode(NAICS_CODE);
		payer.setAutomatedClearingHouseId(ONE);
		payer.setCountry(country);
		payer.setId(ONE);
		payer.setName(FIRST_NAME);
		payer.setPostfundAllowed(true);

		List<PaymentType> paymentTypeList = new ArrayList<PaymentType>();
		paymentTypeList.add(createDummyPaymentType(PaymentTypeEnum.CASH, new Currency(CURRENCY_CODE)));
		paymentTypeList.add(createDummyPaymentType(PaymentTypeEnum.HOME_DELIVERY, new Currency(FOREIGN_CURRENCY_CODE)));
		payer.setPaymentTypeList(paymentTypeList);

		payer.setCreateDateUTC(PGSiDateUtil.getCurrentDateTimeMillis());
		payer.setCreateUser(DUMMY_CREATE_USER);

		StateProvinceRegion stateProvinceRegion = new StateProvinceRegion();
		stateProvinceRegion.setModelAction(PersistanceActionEnum.INSERT);
		stateProvinceRegion.setCode(DUMMY_STATE);
		stateProvinceRegion.setId(HUNDRED);
		stateProvinceRegion.setDescription(DUMMY_STATE_DESCRIPTION);
		payer.setStateProvinceRegionList(Arrays.asList(stateProvinceRegion));

		List<DailyCurrencyRate> dailyCurrencyRateList = new ArrayList<DailyCurrencyRate>();
		dailyCurrencyRateList.add(CommonTestRoutines.createDummyDailyCurrencyRate(payer, new Currency(CURRENCY_CODE)));
		dailyCurrencyRateList.add(CommonTestRoutines.createDummyDailyCurrencyRate(payer, new Currency(CURRENCY_CODE)));
		payer.setDailyCurrencyRateList(dailyCurrencyRateList);

		List<CurrencyAvailability> currencyAvailabilityList = new ArrayList<CurrencyAvailability>();
		currencyAvailabilityList.add(CommonTestRoutines.createDummyCurrencyAvailability(payer, new Currency(
				CURRENCY_CODE)));
		currencyAvailabilityList.add(CommonTestRoutines.createDummyCurrencyAvailability(payer, new Currency(
				CURRENCY_CODE_MXP)));
		payer.setCurrencyAvailabilityList(currencyAvailabilityList);

		return payer;
	}

	/**
	 * Creates the dummy currency availability.
	 *
	 * @param payer The payer for the rate.
	 * @param currency The currency for the rate.
	 * @return the currency availability.
	 */
	public static CurrencyAvailability createDummyCurrencyAvailability(Payer payer, Currency currency)
	{
		CurrencyAvailability currencyAvailability = new CurrencyAvailability();
		currencyAvailability.setModelAction(PersistanceActionEnum.INSERT);
		currencyAvailability.setCurrency(currency);
		currencyAvailability.setPayerId(payer.getId());
		// currencyAvailability.setBalance(TWO_HUNDRED);
		currencyAvailability.setCurrencyCreditCumulative(TWO_HUNDRED);
		currencyAvailability.setCurrencyDebitCumulative(TEN_DOLLARS);
		currencyAvailability.setEffectiveForeignExchangeRate(new BigDecimal("1.89"));
		currencyAvailability.setCreateDateUTC(DUMMY_CREATE_DATE_UTC);
		currencyAvailability.setCreateUser(DUMMY_CREATE_USER);
		return currencyAvailability;
	}

	/**
	 * Creates the dummy currency purchase.
	 *
	 * @return the currency purchase
	 */
	public static CurrencyPurchase createDummyCurrencyPurchase()
	{
		CurrencyPurchase currencyPurchase = new CurrencyPurchase();
		currencyPurchase.setModelAction(PersistanceActionEnum.INSERT);
		Currency currency = new Currency();
		currency.setCode(CURRENCY_CODE);
		currencyPurchase.setAmountPurchased(TWO_HUNDRED);
		currencyPurchase.setAmountNotAllocated(TWO_HUNDRED);
		currencyPurchase.setCreateDateUTC(DUMMY_CREATE_DATE_UTC);
		currencyPurchase.setCreateUser(DUMMY_CREATE_USER);
		currencyPurchase.setCurrency(currency);
		currencyPurchase.setForeignExchangeRate(FOREIGN_EXCHANGE_RATE);
		currencyPurchase.setPayerId(DUMMY_PAYER_ID);
		currencyPurchase.setPurchaseDate(PGSiDateUtil.getCurrentDateMillisUTC());
		currencyPurchase.setUsDollarsSpent(TEN_DOLLARS);
		currencyPurchase.setId(ONE);

		return currencyPurchase;
	}

	/**
	 * Creates the dummy daily currency rate.
	 *
	 * @param payer The payer for the rate.
	 * @param currency The currency for the rate.
	 * @return the daily currency rate
	 */
	public static DailyCurrencyRate createDummyDailyCurrencyRate(Payer payer, Currency currency)
	{
		DailyCurrencyRate dailyCurrencyRate = new DailyCurrencyRate();
		dailyCurrencyRate.setModelAction(PersistanceActionEnum.INSERT);
		dailyCurrencyRate.setCurrency(currency);
		dailyCurrencyRate.setPayerId(payer.getId());
		dailyCurrencyRate.setValidForDate(PGSiDateUtil.getCurrentDateMillisUTC());
		dailyCurrencyRate.setCreateDateUTC(DUMMY_CREATE_DATE_UTC);
		dailyCurrencyRate.setCreateUser(DUMMY_CREATE_USER);
		dailyCurrencyRate.setDailyCurrencyRateDetailList(new ArrayList<DailyCurrencyRateDetail>());

		DailyCurrencyRateDetail dailyCurrencyRateDetail = new DailyCurrencyRateDetail();
		dailyCurrencyRateDetail.setModelAction(PersistanceActionEnum.INSERT);
		dailyCurrencyRateDetail.setCreateDateUTC(DUMMY_CREATE_DATE_UTC);
		dailyCurrencyRateDetail.setCreateUser(DUMMY_CREATE_USER);
		dailyCurrencyRateDetail.setExchangeRate(new BigDecimal("1.78"));
		dailyCurrencyRate.getDailyCurrencyRateDetailList().add(dailyCurrencyRateDetail);

		dailyCurrencyRateDetail = new DailyCurrencyRateDetail();
		dailyCurrencyRateDetail.setModelAction(PersistanceActionEnum.INSERT);
		dailyCurrencyRateDetail.setCreateDateUTC(DUMMY_CREATE_DATE_UTC);
		dailyCurrencyRateDetail.setCreateUser(DUMMY_CREATE_USER);
		dailyCurrencyRateDetail.setExchangeRate(new BigDecimal("1.67"));
		dailyCurrencyRate.getDailyCurrencyRateDetailList().add(dailyCurrencyRateDetail);

		return dailyCurrencyRate;
	}

	/**
	 * Creates the dummy payment type.
	 *
	 * @param paymentTypeEnum The payment type enumeration to wrap.
	 * @param currency The currency.
	 * @return the payment type
	 */
	public static PaymentType createDummyPaymentType(PaymentTypeEnum paymentTypeEnum, Currency currency)
	{
		PaymentType paymentType = new PaymentType(currency, paymentTypeEnum);
		paymentType.setModelAction(PersistanceActionEnum.INSERT);
		paymentType.setCreateDateUTC(PGSiDateUtil.getCurrentDateTimeMillis());
		paymentType.setCreateUser(DUMMY_CREATE_USER);
		return paymentType;
	}

	/**
	 * Creates the dummy currency.
	 *
	 * @return the currency
	 */
	public static Currency createDummyCurrency()
	{
		return new Currency(CURRENCY_CODE, CURRENCY_NAME);
	}

	/**
	 * Creates the dummy custom fee.
	 *
	 * @param transferSettingId the transfer setting id
	 * @param startDate The start date.
	 * @param endDate The end date.
	 * @return the custom fee
	 */
	public static CustomFee createDummyCustomFee(Integer transferSettingId, Long startDate, Long endDate)
	{
		CustomFee customFee = new CustomFee();
		customFee.setId(ONE);
		customFee.setStatus(StatusEnum.ACTIVE);
		customFee.setValue(TEN_DOLLARS);
		customFee.setTransferSettingId(transferSettingId);
		customFee.setModelAction(PersistanceActionEnum.INSERT);
		customFee.setEffectiveStartDate(startDate);
		customFee.setEffectiveEndDate(endDate);
		customFee.setCreateDateUTC(DUMMY_CREATE_DATE_UTC);
		customFee.setCreateUser(DUMMY_CREATE_USER);
		return customFee;
	}

	/**
	 * Creates the dummy product plan applicability.
	 *
	 * @param payer The payer to associate with the product plan applicability.
	 * @return the product plan applicability.
	 */
	public static ProductPlanApplicability createDummyProductPlanApplicability(Payer payer)
	{
		ProductPlanApplicability productPlanApplicability = new ProductPlanApplicability();
		productPlanApplicability.setModelAction(PersistanceActionEnum.INSERT);
		productPlanApplicability.setPayer(payer);
		productPlanApplicability.setPaymentType(PaymentTypeEnum.CASH);
		productPlanApplicability.setCurrency(new Currency(CURRENCY_CODE));
		productPlanApplicability.getCurrency().setName("TEST");
		productPlanApplicability.setCreateDateUTC(DUMMY_CREATE_DATE_UTC);
		productPlanApplicability.setCreateUser(DUMMY_CREATE_USER);
		return productPlanApplicability;
	}

	/**
	 * Creates the dummy transfer setting.
	 *
	 * @return the transfer setting
	 */
	public static TransferSetting createDummyTransferSetting()
	{
		TransferSetting transferSetting = new TransferSetting();
		transferSetting.setKey("TAA00001");
		transferSetting.setModelAction(PersistanceActionEnum.INSERT);
		transferSetting.setCyclesToSkip(ONE);
		transferSetting.setId(getRandomInt());
		transferSetting.setMemberId(getRandomInt());
		transferSetting.setRecipientId(getRandomInt());
		transferSetting.setModelAction(PersistanceActionEnum.INSERT);
		transferSetting.setNumberOfCyclesSkipped(ONE);
		transferSetting.setStatus(StatusEnum.ACTIVE);
		transferSetting.setTransferType(TransferTypeEnum.ONE_TIME);
		transferSetting.setTransferAmount(TWO_HUNDRED);
		transferSetting.setEffectiveStartDate(PGSiDateUtil.getCurrentDateMillisUTC());
		transferSetting.setEffectiveEndDate(PGSiDateUtil.getCurrentDateMillisUTC());
		transferSetting.setCreateDateUTC(DUMMY_CREATE_DATE_UTC);
		transferSetting.setCreateUser(DUMMY_CREATE_USER);

		Long startDate1 = PGSiDateUtil.subtractDays(PGSiDateUtil.getCurrentDateMillisUTC(), TWENTY_DAYS);
		Long endDate1 = PGSiDateUtil.addDays(startDate1, TEN_DAYS);

		Long startDate2 = PGSiDateUtil.addDays(endDate1, 1);
		Long endDate2 = PGSiDateUtil.addDays(startDate2, TEN_DAYS);

		Long startDate3 = PGSiDateUtil.addDays(endDate2, 1);
		Long endDate3 = PGSiDateUtil.addDays(startDate3, TWENTY_DAYS);

		transferSetting.setCustomFeeList(Arrays.asList(
				createDummyCustomFee(transferSetting.getId(), startDate1, endDate1),
				createDummyCustomFee(transferSetting.getId(), startDate2, endDate2),
				createDummyCustomFee(transferSetting.getId(), startDate3, endDate3)));

		transferSetting.setPlanCategory(createDummyPlanCategory(GOLD_PLAN_CATEGORY));
		transferSetting.getPlanCategory().setId(getRandomInt());
		transferSetting.setProductPlanApplicability(createDummyProductPlanApplicability(createDummyPayer()));
		transferSetting.getProductPlanApplicability().setId(getRandomInt());
		transferSetting.setEmploymentInfo(createDummyEmploymentInfo());
		// Notes
		ArrayList<Note> noteList = new ArrayList<Note>();
		noteList.add(createDummyNote(BusinessTypeEnum.MEMBER, null));
		noteList.add(createDummyNote(BusinessTypeEnum.MEMBER, null));
		transferSetting.setNoteList(noteList);

		Account account = new Account();
		account.setType(AccountTypeEnum.SAVINGS);
		account.setNumber(ONE);
		transferSetting.setAccount(account);

		return transferSetting;
	}

	/**
	 * Creates the dummy suspicious activity.
	 *
	 * @return the suspicious activity
	 */
	public static SuspiciousActivity createDummySuspiciousActivity(BusinessTypeEnum type)
	{
		SuspiciousActivity suspiciousActivity = new SuspiciousActivity();
		suspiciousActivity.setId(ONE);
		suspiciousActivity.setBusinessKey("SAR1234");
		suspiciousActivity.setType(type);
		suspiciousActivity.setSummary("Test Summary");
		suspiciousActivity.setDetail("Test Detail");
		suspiciousActivity.setActivityStartDateTimeUTC(PGSiDateUtil.getCurrentDateMillisUTC());
		suspiciousActivity
		.setActivityStopDateTimeUTC(PGSiDateUtil.addDays(PGSiDateUtil.getCurrentDateMillisUTC(), ONE));
		suspiciousActivity.setStatus(SARStatusEnum.PGSI_REVIEW);
		suspiciousActivity.setStatusDateTime(PGSiDateUtil.getCurrentDateTimeMillis());
		suspiciousActivity.setAmountAssociated(TWO_HUNDRED);
		suspiciousActivity.setCreateDateUTC(PGSiDateUtil.getCurrentDateTimeMillis());
		suspiciousActivity.setCreateUser(DUMMY_CREATE_USER);

		return suspiciousActivity;
	}

	public static NodeList simpleXPath(String source, String xpathExpression)
	{

		XPath xpath = XPathFactory.newInstance().newXPath();

		InputSource inputSource = new InputSource(new StringReader(source));

		NodeList nodeList = null;

		try
		{
			nodeList = (NodeList)xpath.evaluate
					(xpathExpression, inputSource, XPathConstants.NODESET);

			int j = nodeList.getLength();

			for (int i = 0; i < j; i++)
			{
				System.out.println(nodeList.item(i).getTextContent());
			}
		}
		catch (XPathExpressionException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return nodeList;
	}
}
