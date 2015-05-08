package com.prosperitasglobal.sendsolv.unittest.bai;

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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.prosperitasglobal.cbof.dac.ICountryDAC;
import com.prosperitasglobal.cbof.model.Address;
import com.prosperitasglobal.cbof.model.BusinessTypeEnum;
import com.prosperitasglobal.cbof.model.Contact;
import com.prosperitasglobal.cbof.model.ContactTypeEnum;
import com.prosperitasglobal.cbof.model.Country;
import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.cbof.unittest.util.CommonTestRoutines;
import com.prosperitasglobal.sendsolv.bac.IMemberBAC;
import com.prosperitasglobal.sendsolv.bai.IMemberBAI;
import com.prosperitasglobal.sendsolv.dac.ILocationDAC;
import com.prosperitasglobal.sendsolv.dac.IPersonDAC;
import com.prosperitasglobal.sendsolv.model.CountryUsage;
import com.prosperitasglobal.sendsolv.model.CustomFee;
import com.prosperitasglobal.sendsolv.model.Document;
import com.prosperitasglobal.sendsolv.model.EmploymentInfo;
import com.prosperitasglobal.sendsolv.model.Location;
import com.prosperitasglobal.sendsolv.model.Member;
import com.prosperitasglobal.sendsolv.model.PersonSecurityAnswer;
import com.prosperitasglobal.sendsolv.model.PersonTypeEnum;
import com.prosperitasglobal.sendsolv.model.Risk;
import com.prosperitasglobal.sendsolv.model.SecurityQuestion;
import com.prosperitasglobal.sendsolv.model.StatusEnum;
import com.prosperitasglobal.sendsolv.model.TransferSetting;
import com.prosperitasglobal.sendsolv.model.request.DocumentMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.request.MemberInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.MemberMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.response.DocumentResponse;
import com.prosperitasglobal.sendsolv.model.response.MemberResponse;
import com.prosperitasglobal.sendsolv.validation.CountryUsageListValidator;
import com.prosperitasglobal.sendsolv.validation.PagedInquiryRequestValidator;
import com.qat.framework.model.QATModel.PersistanceActionEnum;
import com.qat.framework.model.SortExpression;
import com.qat.framework.model.SortExpression.Direction;
import com.qat.framework.model.response.InternalResponse.Status;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * The Class MemberBAIImplTest.
 */
@ContextConfiguration(locations = {
		"classpath:com/prosperitasglobal/sendsolv/unittest/bai/conf/pgsi-sendsolv-validators-context-test.xml",
		"classpath:com/prosperitasglobal/sendsolv/unittest/bai/conf/sendsolv-dac-context.xml",
		"classpath:conf/web/conf/pgsi-sendsolv-shared-context.xml",
		"classpath:com/prosperitasglobal/sendsolv/unittest/conf/pgsi-sendsolv-resourcebundles-context.xml",
		"classpath:com/prosperitasglobal/sendsolv/unittest/bai/conf/memberbaiimpltest.xml"})
public class MemberBAIImplTest extends AbstractJUnit4SpringContextTests
{
	/** The log. */
	private static final Logger LOG = LoggerFactory.getLogger(MemberBAIImplTest.class);

	/** The Constant ONE. */
	private static final int ONE = 1;

	/** The Constant TWO. */
	private static final Integer TWO = 2;

	/** The Constant THREE. */
	private static final Integer THREE = 3;

	/** The Constant TEN. */
	private static final Integer TEN = 10;

	/** The Constant TWO_HUNDRED_FIFTY_SIX. */
	private static final Integer TWO_HUNDRED_FIFTY_SIX = 256;

	/** The Constant DEFAULT_EXCEPTION_MSG. */
	private static final String DEFAULT_EXCEPTION_MSG = "sendsolv.base.defaultexception";

	/** The Constant PROSPERITASGLOBAL_BASE_EMPLOYMENTINFOVALIDATOR_EMPLOYMENT_ALREADY_EXIST. */
	private static final String PROSPERITASGLOBAL_BASE_EMPLOYMENTINFOVALIDATOR_EMPLOYMENT_ALREADY_EXIST =
			"sendsolv.base.employmentinfovalidator.employment.already.exist";

	/** The Constant PROSPERITASGLOBAL_BASE_MEMBERVALIDATOR_MEMBER_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_MEMBERVALIDATOR_MEMBER_REQUIRED =
			"sendsolv.base.membervalidator.member.required";

	/** The Constant PROSPERITASGLOBAL_BASE_MEMBERVALIDATOR_BESTTIMETOCALL_TOO_BIG. */
	private static final String PROSPERITASGLOBAL_BASE_MEMBERVALIDATOR_BESTTIMETOCALL_TOO_BIG =
			"sendsolv.base.membervalidator.besttimetocall.too.big";

	/** The Constant PROSPERITASGLOBAL_BASE_DOCUMENTVALIDATOR_ID_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_DOCUMENTVALIDATOR_ID_REQUIRED =
			"sendsolv.base.documentvalidator.id.required";

	/** The Constant PROSPERITASGLOBAL_BASE_DOCUMENTVALIDATOR_ID_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_DOCUMENTVALIDATOR_FILED_REQUIRED =
			"sendsolv.base.documentvalidator.filed.required";

	/** The Constant PROSPERITASGLOBAL_BASE_DOCUMENTVALIDATOR_DOCUMENT_TYPE_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_DOCUMENTVALIDATOR_DOCUMENT_TYPE_REQUIRED =
			"sendsolv.base.documentvalidator.document.type.required";

	/** The Constant PROSPERITASGLOBAL_BASE_DOCUMENTVALIDATOR_PARENT_KEY_TYPE_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_DOCUMENTVALIDATOR_PARENT_KEY_TYPE_REQUIRED =
			"sendsolv.base.documentvalidator.parent.key.type.required";

	/** The Constant PROSPERITASGLOBAL_BASE_DOCUMENTVALIDATOR_PARENT_KEY_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_DOCUMENTVALIDATOR_PARENT_KEY_REQUIRED =
			"sendsolv.base.documentvalidator.parent.key.required";

	/** The Constant SENDSOLV_BASE_PERSONVALIDATOR_RISKLEVEL_REQUIRED. */
	private static final String SENDSOLV_BASE_PERSONVALIDATOR_RISKLEVEL_REQUIRED =
			"sendsolv.base.personvalidator.risklevel.required";

	/** The Constant SENDSOLV_BASE_PERSONVALIDATOR_RISKLEVELNOTE_TOO_BIG. */
	private static final String SENDSOLV_BASE_PERSONVALIDATOR_RISKLEVELNOTE_TOO_BIG =
			"sendsolv.base.base.personvalidator.risklevelnote.toobig";

	/** The Constant PROSPERITASGLOBAL_BASE_EMPLOYMENTINFOVALIDATOR_EMPLOYMENTINFOSTATUS_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_EMPLOYMENTINFOVALIDATOR_EMPLOYMENTINFOSTATUS_REQUIRED =
			"sendsolv.base.employmentinfovalidator.employmentinfostatus.required";

	/** The Constant PROSPERITASGLOBAL_BASE_EMPLOYMENTINFOVALIDATOR_EMPLOYEE_ID_TOO_BIG. */
	private static final String PROSPERITASGLOBAL_BASE_EMPLOYMENTINFOVALIDATOR_EMPLOYEE_ID_TOO_BIG =
			"sendsolv.base.employmentinfovalidator.employee.id.too.big";

	/** The Constant PROSPERITASGLOBAL_BASE_EMPLOYMENTINFOVALIDATOR_JOBTITLE_TOO_BIG. */
	private static final String PROSPERITASGLOBAL_BASE_EMPLOYMENTINFOVALIDATOR_JOBTITLE_TOO_BIG =
			"sendsolv.base.employmentinfovalidator.jobtitle.too.big";

	/** The Constant PROSPERITASGLOBAL_BASE_EMPLOYMENTINFOVALIDATOR_LOCATION_ID_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_EMPLOYMENTINFOVALIDATOR_LOCATION_ID_REQUIRED =
			"sendsolv.base.employmentinfovalidator.location.id.required";

	/** The Constant PROSPERITASGLOBAL_BASE_EMPLOYMENTINFOVALIDATOR_ID_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_EMPLOYMENTINFOVALIDATOR_ID_REQUIRED =
			"sendsolv.base.employmentinfovalidator.id.required";

	/** The Constant PROSPERITASGLOBAL_BASE_PERSONVALIDATOR_PERSON_STATUS_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_PERSONVALIDATOR_PERSON_STATUS_REQUIRED =
			"sendsolv.base.personvalidator.personstatus.required";

	/** The Constant PROSPERITASGLOBAL_BASE_TRANSFERSETTINGVALIDATOR_TRANSFER_TYPE_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_TRANSFERSETTINGVALIDATOR_TRANSFER_TYPE_REQUIRED =
			"sendsolv.base.transfersettingvalidator.transfer.type.required";

	/** The Constant PROSPERITASGLOBAL_BASE_TRANSFERSETTINGVALIDATOR_TRANSFER_AMOUNT_INVALID. */
	private static final String PROSPERITASGLOBAL_BASE_TRANSFERSETTINGVALIDATOR_TRANSFER_AMOUNT_INVALID =
			"sendsolv.base.transfersettingvalidator.transfer.amount.invalid";

	/** The Constant PROSPERITASGLOBAL_BASE_TRANSFERSETTINGVALIDATOR_PRODUCT_PLAN_APPLICABILITY_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_TRANSFERSETTINGVALIDATOR_PRODUCT_PLAN_APPLICABILITY_REQUIRED =
			"sendsolv.base.transfersettingvalidator.product.plan.applicability.required";

	/** The Constant PROSPERITASGLOBAL_BASE_TRANSFERSETTINGVALIDATOR_PRODUCT_PLAN_CATEGORY_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_TRANSFERSETTINGVALIDATOR_PRODUCT_PLAN_CATEGORY_REQUIRED =
			"sendsolv.base.transfersettingvalidator.product.plan.category.required";

	/** The Constant PROSPERITASGLOBAL_BASE_EMPLOYMENTINFOVALIDATOR_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_EMPLOYMENTINFOVALIDATOR_REQUIRED =
			"sendsolv.base.employmentinfovalidator.required";

	/** The Constant PROSPERITASGLOBAL_BASE_BUSINESSVALIDATOR_STATUS_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_BUSINESSVALIDATOR_STATUS_REQUIRED =
			"sendsolv.base.businessvalidator.status.required";

	/** The Constant PROSPERITASGLOBAL_BASE_RECIPIENTVALIDATOR_ID_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_RECIPIENTVALIDATOR_ID_REQUIRED =
			"sendsolv.base.recipientvalidator.id.required";

	/** The Constant PROSPERITASGLOBAL_BASE_MEMBERVALIDATOR_ID_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_MEMBERVALIDATOR_ID_REQUIRED =
			"sendsolv.base.membervalidator.id.required";

	/** The Constant PROSPERITASGLOBAL_BASE_TRANSFERSETTINGVALIDATOR_ID_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_TRANSFERSETTINGVALIDATOR_ID_REQUIRED =
			"sendsolv.base.transfersettingvalidator.id.required";

	/** The Constant MEMBER_INQUIRY_REQUEST_REQUIRED. */
	private static final String MEMBER_INQUIRY_REQUEST_REQUIRED =
			"sendsolv.base.memberinquiryrequestvalidator.request.required";

	/** The Constant PROSPERITASGLOBAL_BASE_CUSTOMFEEVALIDATOR_TRANSFERSETTING_ID_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_CUSTOMFEEVALIDATOR_TRANSFERSETTING_ID_REQUIRED =
			"sendsolv.base.customfeevalidator.transfersetting.id.required";

	/** The Constant PROSPERITASGLOBAL_BASE_CUSTOMFEEVALIDATOR_STATUS_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_CUSTOMFEEVALIDATOR_STATUS_REQUIRED =
			"sendsolv.base.customfeevalidator.status.required";

	/** The Constant PROSPERITASGLOBAL_BASE_CUSTOMFEEVALIDATOR_EXPIRESDATE_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_CUSTOMFEEVALIDATOR_EXPIRESDATE_REQUIRED =
			"sendsolv.base.customfeevalidator.expiresdate.required";

	/** The Constant PROSPERITASGLOBAL_BASE_CUSTOMFEEVALIDATOR_ID_REQUIRED. */
	private static final String PROSPERITASGLOBAL_BASE_CUSTOMFEEVALIDATOR_ID_REQUIRED =
			"sendsolv.base.customfeevalidator.id.required";

	/** The Constant PROSPERITASGLOBAL_BASE_TRANSFERSETTINGVALIDATOR_MAXCYCLETOSKIP_INVALID. */
	private static final String PROSPERITASGLOBAL_BASE_TRANSFERSETTINGVALIDATOR_MAXCYCLETOSKIP_INVALID =
			"sendsolv.base.transfersettingvalidator.maxcycletoskip.invalid";

	/** The Constant SENDSOLV_BASE_MEMBERVALIDATOR_SECURITYANSWER_ID_REQUIRED. */
	private static final String SENDSOLV_BASE_MEMBERVALIDATOR_SECURITYANSWER_ID_REQUIRED =
			"sendsolv.base.membervalidator.securityanswer.id.required";

	/** The Constant SENDSOLV_BASE_MEMBERVALIDATOR_SECURITYANSWER_DUPLICATED_ANSWER. */
	private static final String SENDSOLV_BASE_MEMBERVALIDATOR_SECURITYANSWER_DUPLICATED_ANSWER =
			"sendsolv.base.membervalidator.securityanswer.duplicated.answer";

	/** The Constant SENDSOLV_BASE_MEMBERVALIDATOR_SECURITYANSWER_ANSWER_REQUIRED. */
	private static final String SENDSOLV_BASE_MEMBERVALIDATOR_SECURITYANSWER_ANSWER_REQUIRED =
			"sendsolv.base.membervalidator.securityanswer.answer.required";

	/** The Constant SENDSOLV_BASE_MEMBERVALIDATOR_SECURITYANSWER_NO_LESS_THAN_THREE_QUESTIONS_ALLOWED. */
	private static final String SENDSOLV_BASE_MEMBERVALIDATOR_SECURITYANSWER_NO_LESS_THAN_THREE_QUESTIONS_ALLOWED =
			"sendsolv.base.membervalidator.securityanswer.no.less.than.three.questions.allowed";

	/** The Constant ADDRESSVALIDATOR_ADDRESS_TOO_BIG. */
	private static final String ADDRESSVALIDATOR_ADDRESS_TOO_BIG = "sendsolv.base.addressvalidator.address.too.big";

	/** The Constant ADDRESSVALIDATOR_CITY_NAME_TOO_BIG. */
	private static final String ADDRESSVALIDATOR_CITY_NAME_TOO_BIG = "sendsolv.base.addressvalidator.city.name.too.big";

	/** The Constant MESSAGE_LIST_SHOULD_BE_EMPTY. */
	private static final String MESSAGE_LIST_SHOULD_BE_EMPTY = "Message list should be empty";

	/** The Constant NEW_RECORD_SHOULD_COME_BACK. */
	private static final String NEW_RECORD_SHOULD_COME_BACK = "New record should come back.";

	/** The Constant FIFTEEN. */
	private static final Integer FIFTEEN = 15;

	/** The Constant FIFTY_ONE. */
	private static final Integer FIFTY_ONE = 51;

	/** The member bai. */
	private IMemberBAI memberBAI;

	/** The member bac. */
	private IMemberBAC mockMemberBAC;

	/** The country dac. */
	private ICountryDAC countryDAC;

	/** The person dac. */
	private IPersonDAC personDAC;

	/** The location dac. */
	private ILocationDAC locationDAC;

	/**
	 * Execute before executing each @Test.
	 */
	@Before
	public void setUp()
	{
		Mockito.reset(getCountryDAC());
		Mockito.reset(getPersonDAC());
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
	 * Gets the country dac.
	 *
	 * @return the country dac
	 */
	public ICountryDAC getCountryDAC()
	{
		return countryDAC;
	}

	/**
	 * Sets the country dac.
	 *
	 * @param countryDAC the country dac
	 */
	@Resource
	public void setCountryDAC(ICountryDAC countryDAC)
	{
		this.countryDAC = countryDAC;
	}

	/**
	 * Gets the member bai.
	 *
	 * @return the member bai
	 */
	public IMemberBAI getMemberBAI()
	{
		return memberBAI;
	}

	/**
	 * Sets the member bai.
	 *
	 * @param memberBAI the member bai
	 */
	@Resource
	public void setMemberBAI(IMemberBAI memberBAI)
	{
		this.memberBAI = memberBAI;
	}

	/**
	 * Gets the mock member bac.
	 *
	 * @return the mock member bac
	 */
	public IMemberBAC getMockMemberBAC()
	{
		return mockMemberBAC;
	}

	/**
	 * Sets the mock member bac.
	 *
	 * @param mockMemberBAC the mock member bac
	 */
	@Resource
	public void setMockMemberBAC(IMemberBAC mockMemberBAC)
	{
		this.mockMemberBAC = mockMemberBAC;
	}

	/**
	 * Gets the location dac.
	 *
	 * @return the location dac
	 */
	public ILocationDAC getLocationDAC()
	{
		return locationDAC;
	}

	/**
	 * Sets the location dac.
	 *
	 * @param locationDAC the location dac
	 */
	@Resource
	public void setLocationDAC(ILocationDAC locationDAC)
	{
		this.locationDAC = locationDAC;
	}

	/**
	 * Test insert member.
	 */
	@Test
	public void testInsertMember()
	{
		MemberMaintenanceRequest request = new MemberMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		Member member = CommonTestRoutines.createDummyMember();
		member.setTransferSettingList(Arrays.asList(CommonTestRoutines.createDummyTransferSetting()));
		member.setModelAction(PersistanceActionEnum.INSERT);
		member.setSecurityAnswerList(createSecurityQuestions(THREE, member));
		request.setMember(member);
		InternalResultsResponse<Member> internalResponse = new InternalResultsResponse<Member>();
		internalResponse.setStatus(Status.OperationSuccess);

		Mockito.when(getMockMemberBAC().insertMember(request)).thenReturn(internalResponse);

		String countryCode = mockFetchCountryByCode(member);

		mockLocation();
		MemberResponse response = getMemberBAI().insertMember(request);
		CommonTestRoutines.assertResponse(response);

		Mockito.verify(getMockMemberBAC()).insertMember(request);
		Mockito.verify(getCountryDAC(), Mockito.times(THREE)).fetchCountryByCode(countryCode);
	}

	/**
	 * Test insert member with validation error - without the 3 security questions.
	 */
	@Test
	public void testInsertMemberWithoutSecurityQuestion()
	{
		MemberMaintenanceRequest request = new MemberMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		Member member = CommonTestRoutines.createDummyMember();
		member.setTransferSettingList(Arrays.asList(CommonTestRoutines.createDummyTransferSetting()));
		member.setModelAction(PersistanceActionEnum.INSERT);
		request.setMember(member);
		InternalResultsResponse<Member> internalResponse = new InternalResultsResponse<Member>();
		internalResponse.setStatus(Status.OperationSuccess);

		Mockito.when(getMockMemberBAC().insertMember(request)).thenReturn(internalResponse);

		mockFetchCountryByCode(member);

		mockLocation();
		MemberResponse response = getMemberBAI().insertMember(request);

		CommonTestRoutines
				.assertMessages(response, LOG,
						SENDSOLV_BASE_MEMBERVALIDATOR_SECURITYANSWER_NO_LESS_THAN_THREE_QUESTIONS_ALLOWED);

		Mockito.verify(getMockMemberBAC(), Mockito.never()).insertMember(request);
	}

	/**
	 * Test insert member with validation error - with less than 3 questions.
	 */
	@Test
	public void testInsertMemberWithLessThanThreeSecurityQuestion()
	{
		MemberMaintenanceRequest request = new MemberMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		Member member = CommonTestRoutines.createDummyMember();
		member.setTransferSettingList(Arrays.asList(CommonTestRoutines.createDummyTransferSetting()));
		member.setModelAction(PersistanceActionEnum.INSERT);
		member.setSecurityAnswerList(createSecurityQuestions(TWO, member));
		request.setMember(member);
		InternalResultsResponse<Member> internalResponse = new InternalResultsResponse<Member>();
		internalResponse.setStatus(Status.OperationSuccess);

		Mockito.when(getMockMemberBAC().insertMember(request)).thenReturn(internalResponse);

		mockFetchCountryByCode(member);

		mockLocation();
		MemberResponse response = getMemberBAI().insertMember(request);

		CommonTestRoutines
				.assertMessages(response, LOG,
						SENDSOLV_BASE_MEMBERVALIDATOR_SECURITYANSWER_NO_LESS_THAN_THREE_QUESTIONS_ALLOWED);

		Mockito.verify(getMockMemberBAC(), Mockito.never()).insertMember(request);
	}

	@Test
	public void testInsertMemberWithEmptyAnswerText()
	{
		MemberMaintenanceRequest request = new MemberMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		Member member = CommonTestRoutines.createDummyMember();
		member.setTransferSettingList(Arrays.asList(CommonTestRoutines.createDummyTransferSetting()));
		member.setModelAction(PersistanceActionEnum.INSERT);
		member.setSecurityAnswerList(createSecurityQuestions(THREE, member));
		member.getSecurityAnswerList().get(0).setAnswerText("");
		request.setMember(member);
		InternalResultsResponse<Member> internalResponse = new InternalResultsResponse<Member>();
		internalResponse.setStatus(Status.OperationSuccess);

		Mockito.when(getMockMemberBAC().insertMember(request)).thenReturn(internalResponse);

		mockFetchCountryByCode(member);

		mockLocation();
		MemberResponse response = getMemberBAI().insertMember(request);

		CommonTestRoutines
				.assertMessages(response, LOG,
						SENDSOLV_BASE_MEMBERVALIDATOR_SECURITYANSWER_ANSWER_REQUIRED);

		Mockito.verify(getMockMemberBAC(), Mockito.never()).insertMember(request);
	}

	@Test
	public void testInsertMemberWithIdRequired()
	{
		MemberMaintenanceRequest request = new MemberMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		Member member = CommonTestRoutines.createDummyMember();
		member.setTransferSettingList(Arrays.asList(CommonTestRoutines.createDummyTransferSetting()));
		member.setModelAction(PersistanceActionEnum.DELETE);
		member.setSecurityAnswerList(createSecurityQuestions(THREE, member));
		member.getSecurityAnswerList().get(0).setId(null);
		request.setMember(member);
		InternalResultsResponse<Member> internalResponse = new InternalResultsResponse<Member>();
		internalResponse.setStatus(Status.OperationSuccess);

		Mockito.when(getMockMemberBAC().insertMember(request)).thenReturn(internalResponse);

		mockFetchCountryByCode(member);

		mockLocation();
		MemberResponse response = getMemberBAI().insertMember(request);

		CommonTestRoutines
				.assertMessages(response, LOG,
						SENDSOLV_BASE_MEMBERVALIDATOR_SECURITYANSWER_ID_REQUIRED);

		Mockito.verify(getMockMemberBAC(), Mockito.never()).insertMember(request);
	}

	/**
	 * Test insert member with validation error - with duplicated question.
	 */
	@Test
	public void testInsertMemberWithDuplicatedQuestion()
	{
		MemberMaintenanceRequest request = new MemberMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		Member member = CommonTestRoutines.createDummyMember();
		member.setTransferSettingList(Arrays.asList(CommonTestRoutines.createDummyTransferSetting()));
		member.setModelAction(PersistanceActionEnum.INSERT);

		// -- insert the duplicated questions
		List<PersonSecurityAnswer> personSecurityAnswerList = new ArrayList<PersonSecurityAnswer>();
		personSecurityAnswerList.addAll(createSecurityQuestions(TWO, member));
		personSecurityAnswerList.add(createSecurityQuestions(ONE, member).get(0));
		member.setSecurityAnswerList(personSecurityAnswerList);
		request.setMember(member);
		InternalResultsResponse<Member> internalResponse = new InternalResultsResponse<Member>();
		internalResponse.setStatus(Status.OperationSuccess);

		Mockito.when(getMockMemberBAC().insertMember(request)).thenReturn(internalResponse);

		mockFetchCountryByCode(member);

		mockLocation();
		MemberResponse response = getMemberBAI().insertMember(request);

		CommonTestRoutines
				.assertMessages(response, LOG,
						SENDSOLV_BASE_MEMBERVALIDATOR_SECURITYANSWER_DUPLICATED_ANSWER);

		Mockito.verify(getMockMemberBAC(), Mockito.never()).insertMember(request);
	}

	/**
	 * Test insert member with invalid address.
	 */
	@Test
	public void testInsertMemberWithInvalidAddress()
	{
		MemberMaintenanceRequest request = new MemberMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		Member member = CommonTestRoutines.createDummyMember();
		member.getContactList().clear();
		member.setContactList(Arrays.asList(CommonTestRoutines.createDummyAddress(ContactTypeEnum.POSTAL_HOME)));
		Address address = (Address)member.getContactList().get(0);
		address.setAddressLine1(createInvalidText(TWO_HUNDRED_FIFTY_SIX));
		address.setCityName(createInvalidText(TWO_HUNDRED_FIFTY_SIX));
		member.setContactList(Arrays.asList((Contact)address));

		member.setTransferSettingList(Arrays.asList(CommonTestRoutines.createDummyTransferSetting()));
		member.setModelAction(PersistanceActionEnum.INSERT);
		member.setSecurityAnswerList(createSecurityQuestions(THREE, member));
		request.setMember(member);
		InternalResultsResponse<Member> internalResponse = new InternalResultsResponse<Member>();
		internalResponse.setStatus(Status.OperationSuccess);

		Mockito.when(getMockMemberBAC().insertMember(request)).thenReturn(internalResponse);

		String countryCode = mockFetchCountryByCode(member);
		mockLocation();

		MemberResponse response = getMemberBAI().insertMember(request);

		CommonTestRoutines.assertMessages(response, LOG, ADDRESSVALIDATOR_ADDRESS_TOO_BIG,
				ADDRESSVALIDATOR_CITY_NAME_TOO_BIG);

		Mockito.verify(getMockMemberBAC(), Mockito.never()).insertMember(request);
		Mockito.verify(getCountryDAC(), Mockito.times(THREE)).fetchCountryByCode(countryCode);
	}

	/**
	 * Test insert member and the country usage country is not found.
	 */
	@Test
	public void testInsertMemberCountryUsageCountryNotFound()
	{
		MemberMaintenanceRequest request = new MemberMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		Member member = CommonTestRoutines.createDummyMember();
		member.setModelAction(PersistanceActionEnum.INSERT);
		member.setSecurityAnswerList(createSecurityQuestions(THREE, member));
		request.setMember(member);
		InternalResultsResponse<Member> internalResponse = new InternalResultsResponse<Member>();
		internalResponse.setStatus(Status.OperationSuccess);
		Mockito.when(getMockMemberBAC().insertMember(request)).thenReturn(internalResponse);

		String countryCode = member.getCountryUsageList().get(0).getCountry().getCode();
		Mockito.when(getCountryDAC().fetchCountryByCode(countryCode)).thenReturn(
				createFetchCountryResponse(countryCode, Status.NoRowsInsertedError));

		MemberResponse response = getMemberBAI().insertMember(request);
		CommonTestRoutines.assertMessages(response, LOG, CountryUsageListValidator.COUNTRY_NOT_FOUND,
				CountryUsageListValidator.COUNTRY_NOT_FOUND, CountryUsageListValidator.COUNTRY_NOT_FOUND);

		Mockito.verify(getMockMemberBAC(), Mockito.times(0)).insertMember(request);
		Mockito.verify(getCountryDAC(), Mockito.times(THREE)).fetchCountryByCode(countryCode);
	}

	/**
	 * Test insert member and the country usage usage is missing.
	 */
	@Test
	public void testInsertMemberCountryUsageUsageMissing()
	{
		MemberMaintenanceRequest request = new MemberMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		Member member = CommonTestRoutines.createDummyMember();
		member.setModelAction(PersistanceActionEnum.INSERT);
		member.setSecurityAnswerList(createSecurityQuestions(THREE, member));
		request.setMember(member);
		InternalResultsResponse<Member> internalResponse = new InternalResultsResponse<Member>();
		internalResponse.setStatus(Status.OperationSuccess);
		Mockito.when(getMockMemberBAC().insertMember(request)).thenReturn(internalResponse);

		String countryCode = mockFetchCountryByCode(member);

		member.getCountryUsageList().get(0).setUsage(null);
		member.getCountryUsageList().get(1).setUsage(null);
		member.getCountryUsageList().get(2).setUsage(null);

		MemberResponse response = getMemberBAI().insertMember(request);
		CommonTestRoutines.assertMessages(response, LOG, CountryUsageListValidator.USAGE_BIRTH_REQUIRED,
				CountryUsageListValidator.USAGE_CITIZENSHIP_REQUIRED,
				CountryUsageListValidator.USAGE_RESIDENCE_REQUIRED, CountryUsageListValidator.USAGE_REQUIRED,
				CountryUsageListValidator.USAGE_REQUIRED, CountryUsageListValidator.USAGE_REQUIRED);

		Mockito.verify(getMockMemberBAC(), Mockito.times(0)).insertMember(request);
		Mockito.verify(getCountryDAC(), Mockito.times(THREE)).fetchCountryByCode(countryCode);
	}

	/**
	 * Test insert member employment info missing.
	 */
	@Test
	public void testInsertMemberEmploymentInfoMissing()
	{
		MemberMaintenanceRequest request = new MemberMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		Member member = CommonTestRoutines.createDummyMember();
		member.setModelAction(PersistanceActionEnum.INSERT);
		member.setSecurityAnswerList(createSecurityQuestions(THREE, member));
		member.setTransferSettingList(null);
		request.setMember(member);
		InternalResultsResponse<Member> internalResponse = new InternalResultsResponse<Member>();
		internalResponse.setStatus(Status.OperationSuccess);

		Mockito.when(getMockMemberBAC().insertMember(request)).thenReturn(internalResponse);
		mockLocation();

		String countryCode = mockFetchCountryByCode(member);
		member.getEmploymentInfoList().get(0).setLocationId(null);
		member.getEmploymentInfoList().get(0).setMemberId(null);
		member.getEmploymentInfoList().get(0).setEmploymentInfoStatus(null);
		member.getEmploymentInfoList().get(0).setModelAction(PersistanceActionEnum.UPDATE);
		member.getEmploymentInfoList().get(0).setJobTitle(createInvalidText(FIFTY_ONE));
		member.getEmploymentInfoList().get(0).setEmployeeId(createInvalidText(FIFTY_ONE));

		MemberResponse response = getMemberBAI().insertMember(request);
		CommonTestRoutines.assertMessages(response, LOG,
				PROSPERITASGLOBAL_BASE_EMPLOYMENTINFOVALIDATOR_EMPLOYMENTINFOSTATUS_REQUIRED,
				PROSPERITASGLOBAL_BASE_EMPLOYMENTINFOVALIDATOR_EMPLOYEE_ID_TOO_BIG,
				PROSPERITASGLOBAL_BASE_EMPLOYMENTINFOVALIDATOR_JOBTITLE_TOO_BIG,
				PROSPERITASGLOBAL_BASE_EMPLOYMENTINFOVALIDATOR_LOCATION_ID_REQUIRED,
				PROSPERITASGLOBAL_BASE_MEMBERVALIDATOR_ID_REQUIRED);

		Mockito.verify(getMockMemberBAC(), Mockito.never()).insertMember(request);
		Mockito.verify(getCountryDAC(), Mockito.times(THREE)).fetchCountryByCode(countryCode);
	}

	/**
	 * Test insert member transfer setting without custom fee.
	 */
	@Test
	public void testInsertMemberTransferSettingWithoutCustomFee()
	{
		MemberMaintenanceRequest request = new MemberMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		Member member = CommonTestRoutines.createDummyMember();
		member.setTransferSettingList(Arrays.asList(CommonTestRoutines.createDummyTransferSetting()));
		member.getTransferSettingList().get(0).setCustomFeeList(Arrays.asList(new CustomFee()));
		member.getTransferSettingList().get(0).getCustomFeeList().get(0).setModelAction(PersistanceActionEnum.INSERT);
		member.setModelAction(PersistanceActionEnum.INSERT);
		member.setSecurityAnswerList(createSecurityQuestions(THREE, member));
		request.setMember(member);
		InternalResultsResponse<Member> internalResponse = new InternalResultsResponse<Member>();
		internalResponse.setStatus(Status.OperationSuccess);

		Mockito.when(getMockMemberBAC().insertMember(request)).thenReturn(internalResponse);

		String countryCode = mockFetchCountryByCode(member);

		MemberResponse response = getMemberBAI().insertMember(request);

		CommonTestRoutines.assertMessages(response, LOG,
				PROSPERITASGLOBAL_BASE_CUSTOMFEEVALIDATOR_STATUS_REQUIRED,
				PROSPERITASGLOBAL_BASE_CUSTOMFEEVALIDATOR_EXPIRESDATE_REQUIRED);

		Mockito.verify(getMockMemberBAC(), Mockito.never()).insertMember(request);
		Mockito.verify(getCountryDAC(), Mockito.times(THREE)).fetchCountryByCode(countryCode);
	}

	/**
	 * Test insert member transfer setting missing.
	 */
	@Test
	public void testInsertMemberTransferSettingMissing()
	{
		MemberMaintenanceRequest request = new MemberMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		Member member = CommonTestRoutines.createDummyMember();
		member.setTransferSettingList(Arrays.asList(new TransferSetting()));
		member.getTransferSettingList().get(0).setModelAction(PersistanceActionEnum.INSERT);
		member.setModelAction(PersistanceActionEnum.INSERT);
		member.setSecurityAnswerList(createSecurityQuestions(THREE, member));
		request.setMember(member);
		InternalResultsResponse<Member> internalResponse = new InternalResultsResponse<Member>();
		internalResponse.setStatus(Status.OperationSuccess);

		Mockito.when(getMockMemberBAC().insertMember(request)).thenReturn(internalResponse);

		String countryCode = mockFetchCountryByCode(member);

		MemberResponse response = getMemberBAI().insertMember(request);
		CommonTestRoutines.assertMessages(response, LOG, PROSPERITASGLOBAL_BASE_MEMBERVALIDATOR_ID_REQUIRED,
				PROSPERITASGLOBAL_BASE_RECIPIENTVALIDATOR_ID_REQUIRED,
				PROSPERITASGLOBAL_BASE_TRANSFERSETTINGVALIDATOR_PRODUCT_PLAN_CATEGORY_REQUIRED,
				PROSPERITASGLOBAL_BASE_TRANSFERSETTINGVALIDATOR_PRODUCT_PLAN_APPLICABILITY_REQUIRED,
				PROSPERITASGLOBAL_BASE_EMPLOYMENTINFOVALIDATOR_REQUIRED,
				PROSPERITASGLOBAL_BASE_TRANSFERSETTINGVALIDATOR_TRANSFER_AMOUNT_INVALID,
				PROSPERITASGLOBAL_BASE_TRANSFERSETTINGVALIDATOR_PRODUCT_PLAN_APPLICABILITY_REQUIRED,
				PROSPERITASGLOBAL_BASE_BUSINESSVALIDATOR_STATUS_REQUIRED,
				PROSPERITASGLOBAL_BASE_TRANSFERSETTINGVALIDATOR_TRANSFER_TYPE_REQUIRED);

		Mockito.verify(getMockMemberBAC(), Mockito.never()).insertMember(request);
		Mockito.verify(getCountryDAC(), Mockito.times(THREE)).fetchCountryByCode(countryCode);
	}

	/**
	 * Test insert member and the country usage country is missing.
	 */
	@Test
	public void testInsertMemberCountryUsageCountryMissing()
	{
		MemberMaintenanceRequest request = new MemberMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		Member member = CommonTestRoutines.createDummyMember();
		member.setModelAction(PersistanceActionEnum.INSERT);
		member.setSecurityAnswerList(createSecurityQuestions(THREE, member));
		request.setMember(member);
		InternalResultsResponse<Member> internalResponse = new InternalResultsResponse<Member>();
		internalResponse.setStatus(Status.OperationSuccess);
		Mockito.when(getMockMemberBAC().insertMember(request)).thenReturn(internalResponse);

		String countryCode = mockFetchCountryByCode(member);

		member.getCountryUsageList().get(0).setCountry(null);

		MemberResponse response = getMemberBAI().insertMember(request);
		CommonTestRoutines.assertMessages(response, LOG, CountryUsageListValidator.COUNTRY_REQUIRED);

		Mockito.verify(getMockMemberBAC(), Mockito.times(0)).insertMember(request);
		Mockito.verify(getCountryDAC(), Mockito.times(TWO)).fetchCountryByCode(countryCode);
	}

	/**
	 * Test insert member with error.
	 */
	@Test
	public void testInsertMemberWithError()
	{
		MemberMaintenanceRequest request = new MemberMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		Member member = CommonTestRoutines.createDummyMember();
		member.setModelAction(PersistanceActionEnum.INSERT);
		member.setBestTimeToCall(createInvalidText(FIFTY_ONE));
		member.setPreferredLanguage(null);
		member.setSecurityAnswerList(createSecurityQuestions(THREE, member));
		request.setMember(member);

		Mockito.when(getMockMemberBAC().insertMember(request)).thenReturn(new InternalResultsResponse<Member>());

		mockFetchCountryByCode(member);

		MemberResponse response = getMemberBAI().insertMember(request);

		CommonTestRoutines.assertMessages(response, LOG,
				PROSPERITASGLOBAL_BASE_MEMBERVALIDATOR_BESTTIMETOCALL_TOO_BIG);

		Mockito.verify(getMockMemberBAC(), Mockito.never()).insertMember(request);
	}

	/**
	 * Test insert member without member object.
	 */
	@Test
	public void testInsertMemberWithoutMemberObject()
	{
		MemberMaintenanceRequest request = new MemberMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		Mockito.when(getMockMemberBAC().insertMember(request)).thenReturn(new InternalResultsResponse<Member>());

		MemberResponse response = getMemberBAI().insertMember(request);

		CommonTestRoutines.assertMessages(response, LOG, PROSPERITASGLOBAL_BASE_MEMBERVALIDATOR_MEMBER_REQUIRED);

		Mockito.verify(getMockMemberBAC(), Mockito.never()).insertMember(request);
	}

	/**
	 * Test insert member exception.
	 */
	@Test
	public void testInsertMemberException()
	{
		MemberMaintenanceRequest request = new MemberMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		Member member = CommonTestRoutines.createDummyMember();
		member.setModelAction(PersistanceActionEnum.INSERT);
		member.setSecurityAnswerList(createSecurityQuestions(THREE, member));
		request.setMember(member);

		Mockito.when(getMockMemberBAC().insertMember(request)).thenThrow(new RuntimeException());

		String countryCode = mockFetchCountryByCode(member);

		MemberResponse response = getMemberBAI().insertMember(request);

		CommonTestRoutines.assertMessages(response, LOG, DEFAULT_EXCEPTION_MSG);

		Mockito.verify(getMockMemberBAC()).insertMember(request);
		Mockito.verify(getCountryDAC(), Mockito.times(THREE)).fetchCountryByCode(countryCode);
	}

	/**
	 * Test update member.
	 */
	@Test
	public void testUpdateMember()
	{
		MemberMaintenanceRequest request = new MemberMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		Member member = CommonTestRoutines.createDummyMember();
		member.setId(FIFTEEN);
		member.setModelAction(PersistanceActionEnum.UPDATE);
		member = CommonTestRoutines.createUpdatedDummyMember(member);
		member.setTransferSettingList(Arrays.asList(CommonTestRoutines.createDummyTransferSetting()));
		member.setSecurityAnswerList(createSecurityQuestions(THREE, member));
		request.setMember(member);

		InternalResultsResponse<Member> internalResponse = new InternalResultsResponse<Member>();
		internalResponse.setStatus(Status.OperationSuccess);

		Mockito.when(getMockMemberBAC().updateMember(request)).thenReturn(internalResponse);

		String countryCode = mockFetchCountryByCode(member);
		Integer countryUsageId = mockFetchCountryUsageById(member);
		mockEmptyEmploymentInfo();
		mockLocation();
		MemberResponse response = getMemberBAI().updateMember(request);

		CommonTestRoutines.assertResponse(response);

		Mockito.verify(getMockMemberBAC()).updateMember(request);
		Mockito.verify(getCountryDAC(), Mockito.times(THREE)).fetchCountryByCode(countryCode);
		Mockito.verify(getPersonDAC(), Mockito.times(THREE)).fetchCountryUsageById(countryUsageId);
	}

	/**
	 * Test update member transfer setting without custom fee.
	 */
	@Test
	public void testUpdateMemberTransferSettingWithoutCustomFee()
	{
		MemberMaintenanceRequest request = new MemberMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		Member member = CommonTestRoutines.createDummyMember();
		member.setId(FIFTEEN);
		member.setModelAction(PersistanceActionEnum.UPDATE);
		member = CommonTestRoutines.createUpdatedDummyMember(member);
		member.setTransferSettingList(Arrays.asList(CommonTestRoutines.createDummyTransferSetting()));
		member.getTransferSettingList().get(0).setModelAction(PersistanceActionEnum.UPDATE);
		member.getTransferSettingList().get(0).setCustomFeeList(Arrays.asList(new CustomFee()));
		member.getTransferSettingList().get(0).getCustomFeeList().get(0).setModelAction(PersistanceActionEnum.UPDATE);
		member.setModelAction(PersistanceActionEnum.UPDATE);
		member.setEmploymentInfoList(null);
		member.setSecurityAnswerList(createSecurityQuestions(THREE, member));
		request.setMember(member);

		InternalResultsResponse<Member> internalResponse = new InternalResultsResponse<Member>();
		internalResponse.setStatus(Status.OperationSuccess);

		Mockito.when(getMockMemberBAC().updateMember(request)).thenReturn(internalResponse);

		String countryCode = mockFetchCountryByCode(member);
		Integer countryUsageId = mockFetchCountryUsageById(member);

		MemberResponse response = getMemberBAI().updateMember(request);

		CommonTestRoutines.assertMessages(response, LOG,
				PROSPERITASGLOBAL_BASE_CUSTOMFEEVALIDATOR_ID_REQUIRED,
				PROSPERITASGLOBAL_BASE_CUSTOMFEEVALIDATOR_TRANSFERSETTING_ID_REQUIRED,
				PROSPERITASGLOBAL_BASE_CUSTOMFEEVALIDATOR_STATUS_REQUIRED,
				PROSPERITASGLOBAL_BASE_CUSTOMFEEVALIDATOR_EXPIRESDATE_REQUIRED);

		Mockito.verify(getMockMemberBAC(), Mockito.never()).updateMember(request);
		Mockito.verify(getCountryDAC(), Mockito.times(THREE)).fetchCountryByCode(countryCode);
		Mockito.verify(getPersonDAC(), Mockito.times(THREE)).fetchCountryUsageById(countryUsageId);
	}

	/**
	 * Test update member transfer setting invalid cycles to skip.
	 */
	@Test
	public void testUpdateMemberTransferSettingInvalidCyclesToSkip()
	{
		MemberMaintenanceRequest request = new MemberMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		Member member = CommonTestRoutines.createDummyMember();
		member.setId(FIFTEEN);
		member = CommonTestRoutines.createUpdatedDummyMember(member);
		member.setTransferSettingList(Arrays.asList(CommonTestRoutines.createDummyTransferSetting()));
		member.getTransferSettingList().get(0).setModelAction(PersistanceActionEnum.UPDATE);
		member.getTransferSettingList().get(0).setCyclesToSkip(TWO_HUNDRED_FIFTY_SIX);
		member.getEmploymentInfoList().get(0).setModelAction(PersistanceActionEnum.INSERT);
		member.setModelAction(PersistanceActionEnum.UPDATE);
		member.setSecurityAnswerList(createSecurityQuestions(THREE, member));
		request.setMember(member);

		InternalResultsResponse<Member> internalResponse = new InternalResultsResponse<Member>();
		internalResponse.setStatus(Status.OperationSuccess);

		Mockito.when(getMockMemberBAC().updateMember(request)).thenReturn(internalResponse);

		String countryCode = mockFetchCountryByCode(member);
		Integer countryUsageId = mockFetchCountryUsageById(member);
		mockEmptyEmploymentInfo();
		mockLocation();

		MemberResponse response = getMemberBAI().updateMember(request);

		CommonTestRoutines.assertMessages(response, LOG,
				PROSPERITASGLOBAL_BASE_TRANSFERSETTINGVALIDATOR_MAXCYCLETOSKIP_INVALID);

		Mockito.verify(getMockMemberBAC(), Mockito.never()).updateMember(request);
		Mockito.verify(getCountryDAC(), Mockito.times(THREE)).fetchCountryByCode(countryCode);
		Mockito.verify(getPersonDAC(), Mockito.times(THREE)).fetchCountryUsageById(countryUsageId);
	}

	/**
	 * Test update member employment info already exist.
	 */
	@Test
	public void testUpdateMemberEmploymentInfoAlreadyExist()
	{
		MemberMaintenanceRequest request = new MemberMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		Member member = CommonTestRoutines.createDummyMember();
		member.setId(FIFTEEN);
		member.setModelAction(PersistanceActionEnum.UPDATE);
		member = CommonTestRoutines.createUpdatedDummyMember(member);
		member.setTransferSettingList(Arrays.asList(CommonTestRoutines.createDummyTransferSetting()));
		member.getEmploymentInfoList().get(0).setModelAction(PersistanceActionEnum.UPDATE);
		member.setSecurityAnswerList(createSecurityQuestions(THREE, member));
		request.setMember(member);

		InternalResultsResponse<Member> internalResponse = new InternalResultsResponse<Member>();
		internalResponse.setStatus(Status.OperationSuccess);

		Mockito.when(getMockMemberBAC().updateMember(request)).thenReturn(internalResponse);

		String countryCode = mockFetchCountryByCode(member);
		Integer countryUsageId = mockFetchCountryUsageById(member);
		mockEmploymentInfo(member);
		mockLocation();

		MemberResponse response = getMemberBAI().updateMember(request);

		CommonTestRoutines.assertMessages(response, LOG,
				PROSPERITASGLOBAL_BASE_EMPLOYMENTINFOVALIDATOR_EMPLOYMENT_ALREADY_EXIST);

		Mockito.verify(getMockMemberBAC(), Mockito.never()).updateMember(request);
		Mockito.verify(getCountryDAC(), Mockito.times(THREE)).fetchCountryByCode(countryCode);
		Mockito.verify(getPersonDAC(), Mockito.times(THREE)).fetchCountryUsageById(countryUsageId);
	}

	/**
	 * Test update member employment info id missing.
	 */
	@Test
	public void testUpdateMemberEmploymentInfoIdMissing()
	{
		MemberMaintenanceRequest request = new MemberMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		Member member = CommonTestRoutines.createDummyMember();
		member.setId(FIFTEEN);
		member.setModelAction(PersistanceActionEnum.UPDATE);
		member = CommonTestRoutines.createUpdatedDummyMember(member);
		member.setSecurityAnswerList(createSecurityQuestions(THREE, member));
		request.setMember(member);

		InternalResultsResponse<Member> internalResponse = new InternalResultsResponse<Member>();
		internalResponse.setStatus(Status.OperationSuccess);

		Mockito.when(getMockMemberBAC().updateMember(request)).thenReturn(internalResponse);

		String countryCode = mockFetchCountryByCode(member);
		Integer countryUsageId = mockFetchCountryUsageById(member);
		member.getEmploymentInfoList().get(0).setId(null);

		MemberResponse response = getMemberBAI().updateMember(request);

		CommonTestRoutines.assertMessages(response, LOG, PROSPERITASGLOBAL_BASE_EMPLOYMENTINFOVALIDATOR_ID_REQUIRED);

		Mockito.verify(getMockMemberBAC(), Mockito.never()).updateMember(request);
		Mockito.verify(getCountryDAC(), Mockito.times(THREE)).fetchCountryByCode(countryCode);
		Mockito.verify(getPersonDAC(), Mockito.times(THREE)).fetchCountryUsageById(countryUsageId);
	}

	/**
	 * Test update member transfer setting id missing.
	 */
	@Test
	public void testUpdateMemberTransferSettingIdMissing()
	{
		MemberMaintenanceRequest request = new MemberMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		Member member = CommonTestRoutines.createDummyMember();
		member.setModelAction(PersistanceActionEnum.UPDATE);
		member = CommonTestRoutines.createUpdatedDummyMember(member);
		member.setTransferSettingList(Arrays.asList(CommonTestRoutines.createDummyTransferSetting()));
		member.getTransferSettingList().get(0).setModelAction(PersistanceActionEnum.UPDATE);
		member.setEmploymentInfoList(null);
		member.setSecurityAnswerList(createSecurityQuestions(THREE, member));
		request.setMember(member);

		InternalResultsResponse<Member> internalResponse = new InternalResultsResponse<Member>();
		internalResponse.setStatus(Status.OperationSuccess);

		Mockito.when(getMockMemberBAC().updateMember(request)).thenReturn(internalResponse);

		String countryCode = mockFetchCountryByCode(member);
		Integer countryUsageId = mockFetchCountryUsageById(member);
		member.getTransferSettingList().get(0).setId(null);

		mockLocation();

		MemberResponse response = getMemberBAI().updateMember(request);

		CommonTestRoutines.assertMessages(response, LOG, PROSPERITASGLOBAL_BASE_TRANSFERSETTINGVALIDATOR_ID_REQUIRED);

		Mockito.verify(getMockMemberBAC(), Mockito.never()).updateMember(request);
		Mockito.verify(getCountryDAC(), Mockito.times(THREE)).fetchCountryByCode(countryCode);
		Mockito.verify(getPersonDAC(), Mockito.times(THREE)).fetchCountryUsageById(countryUsageId);
	}

	/**
	 * Test update risk.
	 */
	@Test
	public void testUpdateRisk()
	{
		MemberMaintenanceRequest request = new MemberMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		Member member = new Member();
		member.setId(FIFTEEN);
		member.setModelAction(PersistanceActionEnum.NONE);

		Risk risk = CommonTestRoutines.createDummyRisk();
		risk.setModelAction(PersistanceActionEnum.UPDATE);
		member.setRisk(risk);
		member.setSecurityAnswerList(createSecurityQuestions(THREE, member));
		request.setMember(member);

		InternalResultsResponse<Member> internalResponse = new InternalResultsResponse<Member>();
		internalResponse.setStatus(Status.OperationSuccess);

		Mockito.when(getMockMemberBAC().updateMember(request)).thenReturn(internalResponse);

		MemberResponse response = getMemberBAI().updateMember(request);

		CommonTestRoutines.assertResponse(response);

		Mockito.verify(getMockMemberBAC()).updateMember(request);
	}

	/**
	 * Test insert risk note too big.
	 */
	@Test
	public void testInsertRiskNoteTooBig()
	{
		// Test Insert Success
		MemberMaintenanceRequest request = new MemberMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		Risk risk = CommonTestRoutines.createDummyRisk();
		risk.setModelAction(PersistanceActionEnum.UPDATE);

		risk.setRiskLevelNote("a");
		for (int i = 0; i < TWO_HUNDRED_FIFTY_SIX; i++)
		{
			risk.setRiskLevelNote(risk.getRiskLevelNote().concat("b"));
		}

		Member member = new Member();
		member.setId(FIFTEEN);
		member.setModelAction(PersistanceActionEnum.NONE);
		member.setSecurityAnswerList(createSecurityQuestions(THREE, member));
		member.setRisk(risk);
		request.setMember(member);

		InternalResultsResponse<Member> internalResponse = new InternalResultsResponse<Member>();
		internalResponse.setStatus(Status.OperationSuccess);

		Mockito.when(getMockMemberBAC().updateMember(request)).thenReturn(internalResponse);

		MemberResponse response = getMemberBAI().updateMember(request);

		CommonTestRoutines.assertMessages(response, LOG, SENDSOLV_BASE_PERSONVALIDATOR_RISKLEVELNOTE_TOO_BIG);

		Mockito.verify(getMockMemberBAC(), Mockito.never()).updateMember(request);
	}

	/**
	 * Test insert risk fields missing.
	 */
	@Test
	public void testInsertRiskFieldsMissing()
	{
		// Test Insert Success
		MemberMaintenanceRequest request = new MemberMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		Risk risk = CommonTestRoutines.createDummyRisk();
		risk.setModelAction(PersistanceActionEnum.UPDATE);

		risk.setRiskLevel(null);

		Member member = new Member();
		member.setId(FIFTEEN);
		member.setModelAction(PersistanceActionEnum.NONE);

		member.setRisk(risk);
		member.setSecurityAnswerList(createSecurityQuestions(THREE, member));
		request.setMember(member);

		InternalResultsResponse<Member> internalResponse = new InternalResultsResponse<Member>();
		internalResponse.setStatus(Status.OperationSuccess);

		Mockito.when(getMockMemberBAC().updateMember(request)).thenReturn(internalResponse);

		MemberResponse response = getMemberBAI().updateMember(request);

		CommonTestRoutines.assertMessages(response, LOG, SENDSOLV_BASE_PERSONVALIDATOR_RISKLEVEL_REQUIRED);

		Mockito.verify(getMockMemberBAC(), Mockito.never()).updateMember(request);
	}

	/**
	 * Test update member and country usage id is missing.
	 */
	@Test
	public void testUpdateMemberCountryUsageIdMissing()
	{
		MemberMaintenanceRequest request = new MemberMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		Member member = CommonTestRoutines.createDummyMember();
		member.setId(FIFTEEN);
		member.setModelAction(PersistanceActionEnum.UPDATE);
		member = CommonTestRoutines.createUpdatedDummyMember(member);
		member.setTransferSettingList(Arrays.asList(CommonTestRoutines.createDummyTransferSetting()));
		member.setSecurityAnswerList(createSecurityQuestions(THREE, member));
		request.setMember(member);

		InternalResultsResponse<Member> internalResponse = new InternalResultsResponse<Member>();
		internalResponse.setStatus(Status.OperationSuccess);

		Mockito.when(getMockMemberBAC().updateMember(request)).thenReturn(internalResponse);

		String countryCode = mockFetchCountryByCode(member);
		Integer countryUsageId = mockFetchCountryUsageById(member);

		member.getCountryUsageList().get(0).setId(null);
		mockLocation();
		MemberResponse response = getMemberBAI().updateMember(request);

		CommonTestRoutines
				.assertMessagesInfo(response.getMessageInfoList(), LOG, CountryUsageListValidator.ID_REQUIRED);

		Mockito.verify(getMockMemberBAC(), Mockito.times(0)).updateMember(request);
		Mockito.verify(getCountryDAC(), Mockito.times(THREE)).fetchCountryByCode(countryCode);
		Mockito.verify(getPersonDAC(), Mockito.times(TWO)).fetchCountryUsageById(countryUsageId);
	}

	/**
	 * Test update member and the country usage id is not found.
	 */
	@Test
	public void testUpdateMemberCountryUsageIdNotFound()
	{
		MemberMaintenanceRequest request = new MemberMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		Member member = CommonTestRoutines.createDummyMember();
		member.setId(FIFTEEN);
		member.setModelAction(PersistanceActionEnum.UPDATE);
		member = CommonTestRoutines.createUpdatedDummyMember(member);
		member.setSecurityAnswerList(createSecurityQuestions(THREE, member));
		request.setMember(member);

		InternalResultsResponse<Member> internalResponse = new InternalResultsResponse<Member>();
		internalResponse.setStatus(Status.OperationSuccess);

		Mockito.when(getMockMemberBAC().updateMember(request)).thenReturn(internalResponse);

		member.getCountryUsageList().get(0).setId(0);
		member.getCountryUsageList().get(1).setId(0);
		member.getCountryUsageList().get(2).setId(0);
		Integer countryUsageId = member.getCountryUsageList().get(0).getId();
		Mockito.when(getPersonDAC().fetchCountryUsageById(countryUsageId)).thenReturn(
				createFetchCountryUsageResponse(countryUsageId, Status.NoRowsFoundError));

		String countryCode = mockFetchCountryByCode(member);

		MemberResponse response = getMemberBAI().updateMember(request);

		CommonTestRoutines.assertMessagesInfo(response.getMessageInfoList(), LOG,
				CountryUsageListValidator.ID_NOT_FOUND,
				CountryUsageListValidator.ID_NOT_FOUND, CountryUsageListValidator.ID_NOT_FOUND);

		Mockito.verify(getMockMemberBAC(), Mockito.times(0)).updateMember(request);
		Mockito.verify(getCountryDAC(), Mockito.times(THREE)).fetchCountryByCode(countryCode);
		Mockito.verify(getPersonDAC(), Mockito.times(THREE)).fetchCountryUsageById(countryUsageId);
	}

	/**
	 * Test update member exception.
	 */
	@Test
	public void testUpdateMemberException()
	{
		MemberMaintenanceRequest request = new MemberMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		Member member = CommonTestRoutines.createDummyMember();
		member.setId(FIFTEEN);
		member.setModelAction(PersistanceActionEnum.UPDATE);
		member = CommonTestRoutines.createUpdatedDummyMember(member);
		member.setSecurityAnswerList(createSecurityQuestions(THREE, member));
		request.setMember(member);

		Mockito.when(getMockMemberBAC().updateMember(request)).thenThrow(new RuntimeException());

		String countryCode = mockFetchCountryByCode(member);
		Integer countryUsageId = mockFetchCountryUsageById(member);

		MemberResponse response = getMemberBAI().updateMember(request);

		CommonTestRoutines.assertMessages(response, LOG, DEFAULT_EXCEPTION_MSG);

		Mockito.verify(getMockMemberBAC(), Mockito.never()).updateMember(request);
		Mockito.verify(getCountryDAC(), Mockito.times(THREE)).fetchCountryByCode(countryCode);
		Mockito.verify(getPersonDAC(), Mockito.times(THREE)).fetchCountryUsageById(countryUsageId);
	}

	/**
	 * Test delete member.
	 */
	@Test
	public void testDeleteMember()
	{
		MemberMaintenanceRequest request = new MemberMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		Member member = CommonTestRoutines.createDummyMember();
		member.setModelAction(PersistanceActionEnum.DELETE);
		member.setSecurityAnswerList(createSecurityQuestions(THREE, member));
		request.setMember(member);
		request.getMember().setId(TEN);

		Mockito.when(getMockMemberBAC().deleteMember(request)).thenReturn(
				new InternalResultsResponse<Member>());

		MemberResponse response = getMemberBAI().deleteMember(request);

		CommonTestRoutines.assertResponse(response);
	}

	/**
	 * Test fetch member by id.
	 */
	@Test
	public void testFetchMemberById()
	{
		Member member = CommonTestRoutines.createDummyMember();
		FetchByIdRequest request = new FetchByIdRequest(member.getId());
		CommonTestRoutines.createDummyUserContext(request);

		Mockito.when(getMockMemberBAC().fetchMemberById(request)).thenReturn(
				createFetchResponse(member));

		MemberResponse result = getMemberBAI().fetchMemberById(request);

		CommonTestRoutines.assertResponse(result);
	}

	/**
	 * Test fetch member by id exception.
	 */
	@Test
	public void testFetchMemberByIdException()
	{
		FetchByIdRequest request = new FetchByIdRequest(TEN);

		Mockito.when(getMockMemberBAC().fetchMemberById(request)).thenThrow(new RuntimeException());

		MemberResponse response = getMemberBAI().fetchMemberById(request);

		CommonTestRoutines.assertMessages(response, LOG, DEFAULT_EXCEPTION_MSG);

		Mockito.verify(getMockMemberBAC()).fetchMemberById(request);
	}

	/**
	 * Test fetch member by request.
	 */
	@Test
	public void testFetchMemberByRequest()
	{
		Mockito.when(getMockMemberBAC().fetchMemberByRequest(Matchers.any(MemberInquiryRequest.class)))
				.thenReturn(createFetchResponse(CommonTestRoutines.createDummyMember()));

		MemberInquiryRequest request = new MemberInquiryRequest();
		request.addSortExpressions(new SortExpression("firstName", Direction.Ascending));
		request.addSortExpressions(new SortExpression("lastName", Direction.Ascending));
		request.addSortExpressions(new SortExpression("personStatus", Direction.Ascending));
		request.addSortExpressions(new SortExpression("participantId", Direction.Ascending));
		MemberResponse response = getMemberBAI().fetchMemberByRequest(request);

		CommonTestRoutines.assertResponse(response);
	}

	/**
	 * Test fetch member by request.
	 */
	@Test
	public void testFetchMemberByRequestNull()
	{
		Mockito.when(getMockMemberBAC().fetchMemberByRequest(Matchers.any(MemberInquiryRequest.class)))
				.thenReturn(createFetchResponse(CommonTestRoutines.createDummyMember()));

		MemberInquiryRequest request = null;
		MemberResponse response = getMemberBAI().fetchMemberByRequest(request);

		CommonTestRoutines.assertMessages(response, LOG, MEMBER_INQUIRY_REQUEST_REQUIRED);
	}

	/**
	 * Test fetch member by request.
	 */
	@Test
	public void testFetchMemberByRequestInvalidSortColumn()
	{
		Mockito.when(getMockMemberBAC().fetchMemberByRequest(Matchers.any(MemberInquiryRequest.class)))
				.thenReturn(createFetchResponse(CommonTestRoutines.createDummyMember()));

		MemberInquiryRequest request = new MemberInquiryRequest();
		request.addSortExpressions(new SortExpression("badField", Direction.Ascending));
		MemberResponse response = getMemberBAI().fetchMemberByRequest(request);

		CommonTestRoutines.assertMessages(response, LOG, PagedInquiryRequestValidator.INVALID_SORT_FIELD);
	}

	/**
	 * Test fetch member by request with error.
	 */
	@Test
	public void testFetchMemberByRequestWithError()
	{
		MemberInquiryRequest request = new MemberInquiryRequest();
		request.setPageSize(null);
		request.setStartPage(null);
		Mockito.when(getMockMemberBAC().fetchMemberByRequest(request))
				.thenReturn(createFetchResponse(CommonTestRoutines.createDummyMember()));

		MemberResponse response = getMemberBAI().fetchMemberByRequest(request);

		CommonTestRoutines.assertMessages(response, LOG, PagedInquiryRequestValidator.PAGING_PARAMETERS_REQUIRED);

		Mockito.verify(getMockMemberBAC(), Mockito.never()).fetchMemberByRequest(request);
	}

	/**
	 * Test fetch member by request exception.
	 */
	@Test
	public void testFetchMemberByRequestException()
	{
		MemberInquiryRequest request = new MemberInquiryRequest();

		Mockito.when(getMockMemberBAC().fetchMemberByRequest(request)).thenThrow(new RuntimeException());

		MemberResponse response = getMemberBAI().fetchMemberByRequest(request);

		CommonTestRoutines.assertMessages(response, LOG, DEFAULT_EXCEPTION_MSG);

		Mockito.verify(getMockMemberBAC()).fetchMemberByRequest(request);
	}

	/**
	 * Test apply status.
	 */
	@Test
	public void testApplyStatus()
	{
		MemberMaintenanceRequest request = new MemberMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);
		Member member = CommonTestRoutines.createDummyMember();
		member.setId(CommonTestRoutines.getRandomInt());
		member.setModelAction(PersistanceActionEnum.UPDATE);
		member.setPersonStatus(StatusEnum.INACTIVE);
		member.setPersonType(PersonTypeEnum.MEMBER);
		member.setPinNumber(String.valueOf(ONE));
		member.setParticipantId(String.valueOf(ONE));
		member.setSecurityAnswerList(createSecurityQuestions(THREE, member));
		request.setMember(member);

		InternalResultsResponse<Member> internalResponse = new InternalResultsResponse<Member>();
		internalResponse.setStatus(Status.OperationSuccess);

		mockFetchCountryByCode(member);
		mockLocation();

		Mockito.when(getMockMemberBAC().applyStatus(request)).thenReturn(internalResponse);

		MemberResponse response = getMemberBAI().applyStatus(request);
		CommonTestRoutines.assertResponse(response);

		Mockito.verify(getMockMemberBAC()).applyStatus(request);
	}

	/**
	 * Test apply status with error.
	 */
	@Test
	public void testApplyStatusWithError()
	{
		MemberMaintenanceRequest request = new MemberMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);
		Member member = CommonTestRoutines.createDummyMember();
		member.setTransferSettingList(Arrays.asList(CommonTestRoutines.createDummyTransferSetting()));
		member.setModelAction(PersistanceActionEnum.UPDATE);
		member.setParticipantId(String.valueOf(ONE));
		member.setPersonStatus(null);
		member.setPersonType(PersonTypeEnum.MEMBER);
		member.setPinNumber(String.valueOf(ONE));
		member.setSecurityAnswerList(createSecurityQuestions(THREE, member));
		request.setMember(member);

		Mockito.when(getMockMemberBAC().applyStatus(request))
				.thenReturn(createFetchResponse(member));

		mockFetchCountryByCode(member);
		mockLocation();

		MemberResponse response = getMemberBAI().applyStatus(request);
		CommonTestRoutines.assertMessages(response, LOG, PROSPERITASGLOBAL_BASE_PERSONVALIDATOR_PERSON_STATUS_REQUIRED);

		Mockito.verify(getMockMemberBAC(), Mockito.never()).applyStatus(request);
	}

	/**
	 * Test insert document.
	 */
	@Test
	public void testInsertDocument()
	{
		// Test Insert Success
		DocumentMaintenanceRequest request = new DocumentMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		Document document = CommonTestRoutines.createDummyDocument(BusinessTypeEnum.MEMBER);
		document.setModelAction(PersistanceActionEnum.INSERT);
		request.setDocument(document);

		InternalResultsResponse<Document> internalResponse = new InternalResultsResponse<Document>();
		internalResponse.addResult(CommonTestRoutines.createDummyDocument(BusinessTypeEnum.MEMBER));

		Mockito.when(getMockMemberBAC().insertDocument(request)).thenReturn(internalResponse);

		DocumentResponse response = getMemberBAI().insertDocument(request);

		Assert.assertTrue(MESSAGE_LIST_SHOULD_BE_EMPTY, response.getMessageList().isEmpty());

		// Make sure the row inserted comes back
		Assert.assertTrue(NEW_RECORD_SHOULD_COME_BACK, response.getDocumentList().size() == 1);

		// ensures the mock's insertOrganization was called 1 time.
		Mockito.verify(getMockMemberBAC()).insertDocument(request);

	}

	/**
	 * Test delete document.
	 */
	@Test
	public void testDeleteDocument()
	{
		DocumentMaintenanceRequest request = new DocumentMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);
		Document document = CommonTestRoutines.createDummyDocument(BusinessTypeEnum.MEMBER);
		document.setId(THREE);
		request.setDocument(document);

		Mockito.when(getMockMemberBAC().deleteDocument(request)).thenReturn(
				new InternalResultsResponse<Document>());

		DocumentResponse response = getMemberBAI().deleteDocument(request);

		CommonTestRoutines.assertResponse(response);

		// ensures the mock's insertOrganization was called 1 time.
		Mockito.verify(getMockMemberBAC()).deleteDocument(request);

	}

	/**
	 * Test insert document with error.
	 */
	@Test
	public void testInsertDocumentWithError()
	{
		// Test Insert Success
		InternalResultsResponse<Document> internalResultsResponse = new InternalResultsResponse<Document>();

		DocumentMaintenanceRequest request = new DocumentMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);
		Document document = new Document();
		document.setModelAction(PersistanceActionEnum.INSERT);
		request.setDocument(document);

		Mockito.when(getMockMemberBAC().insertDocument(request)).thenReturn(internalResultsResponse);

		DocumentResponse response = getMemberBAI().insertDocument(request);

		CommonTestRoutines.assertMessages(response, LOG, PROSPERITASGLOBAL_BASE_DOCUMENTVALIDATOR_PARENT_KEY_REQUIRED,
				PROSPERITASGLOBAL_BASE_DOCUMENTVALIDATOR_PARENT_KEY_TYPE_REQUIRED,
				PROSPERITASGLOBAL_BASE_DOCUMENTVALIDATOR_DOCUMENT_TYPE_REQUIRED,
				PROSPERITASGLOBAL_BASE_DOCUMENTVALIDATOR_FILED_REQUIRED);

		Mockito.verify(getMockMemberBAC(), Mockito.never()).insertDocument(request);
	}

	/**
	 * Test delete document with error.
	 */
	@Test
	public void testDeleteDocumentWithError()
	{
		InternalResultsResponse<Document> internalResultsResponse = new InternalResultsResponse<Document>();
		DocumentMaintenanceRequest request = new DocumentMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		Document document = new Document();
		document.setModelAction(PersistanceActionEnum.DELETE);
		request.setDocument(document);

		Mockito.when(getMockMemberBAC().deleteDocument(request)).thenReturn(internalResultsResponse);

		DocumentResponse response = getMemberBAI().deleteDocument(request);

		CommonTestRoutines.assertMessages(response, LOG, PROSPERITASGLOBAL_BASE_DOCUMENTVALIDATOR_ID_REQUIRED);

		Mockito.verify(getMockMemberBAC(), Mockito.never()).deleteDocument(request);
	}

	/**
	 * Test insert document exception.
	 */
	@Test
	public void testInsertDocumentException()
	{
		DocumentMaintenanceRequest request = new DocumentMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		Document document = CommonTestRoutines.createDummyDocument(BusinessTypeEnum.MEMBER);
		document.setModelAction(PersistanceActionEnum.INSERT);
		request.setDocument(document);

		Mockito.when(getMockMemberBAC().insertDocument(request)).thenThrow(new RuntimeException());

		DocumentResponse response = getMemberBAI().insertDocument(request);

		CommonTestRoutines.assertMessages(response, LOG, DEFAULT_EXCEPTION_MSG);

		Mockito.verify(getMockMemberBAC()).insertDocument(request);
	}

	/**
	 * Test delete document exception.
	 */
	@Test
	public void testDeleteDocumentException()
	{
		DocumentMaintenanceRequest request = new DocumentMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);
		Document document = CommonTestRoutines.createDummyDocument(BusinessTypeEnum.MEMBER);
		document.setId(1);
		document.setModelAction(PersistanceActionEnum.DELETE);
		request.setDocument(document);

		Mockito.when(getMockMemberBAC().deleteDocument(request)).thenThrow(new RuntimeException());

		DocumentResponse response = getMemberBAI().deleteDocument(request);

		CommonTestRoutines.assertMessages(response, LOG, DEFAULT_EXCEPTION_MSG);

		Mockito.verify(getMockMemberBAC()).deleteDocument(request);
	}

	/**
	 * Creates the fetch response.
	 *
	 * @param member the member
	 * @return the internal results response< member>
	 */
	private InternalResultsResponse<Member> createFetchResponse(Member member)
	{
		InternalResultsResponse<Member> response = new InternalResultsResponse<Member>();
		response.addResult(member);
		return response;
	}

	/**
	 * Creates the invalid text.
	 *
	 * @param maxValue the max value
	 * @return the string
	 */
	private String createInvalidText(Integer maxValue)
	{
		String text = new String();
		for (int i = 0; i < maxValue; i++)
		{
			text += "A";
		}
		return text;
	}

	/**
	 * Creates the fetch country response.
	 *
	 * @param countryCode the country code
	 * @param status the status
	 * @return the internal results response< country>
	 */
	private InternalResultsResponse<Country> createFetchCountryResponse(String countryCode, Status status)
	{
		InternalResultsResponse<Country> countryResponse = new InternalResultsResponse<Country>();

		if (status == Status.OperationSuccess)
		{
			countryResponse.addResult(new Country(countryCode));
		}

		countryResponse.setStatus(status);
		return countryResponse;
	}

	/**
	 * Creates the fetch country usage response.
	 *
	 * @param id the id
	 * @param status the status
	 * @return the internal results response< country usage>
	 */
	private InternalResultsResponse<CountryUsage> createFetchCountryUsageResponse(Integer id, Status status)
	{
		InternalResultsResponse<CountryUsage> countryUsageResponse = new InternalResultsResponse<CountryUsage>();

		if (status == Status.OperationSuccess)
		{
			CountryUsage countryUsage = new CountryUsage();
			countryUsage.setId(id);
			countryUsageResponse.addResult(countryUsage);
		}

		countryUsageResponse.setStatus(status);
		return countryUsageResponse;
	}

	/**
	 * Mock fetch country usage by id.
	 *
	 * @param member the member
	 * @return the integer
	 */
	private Integer mockFetchCountryUsageById(Member member)
	{
		member.getCountryUsageList().get(0).setId(0);
		member.getCountryUsageList().get(1).setId(0);
		member.getCountryUsageList().get(2).setId(0);
		Integer countryUsageId = member.getCountryUsageList().get(0).getId();
		Mockito.when(getPersonDAC().fetchCountryUsageById(countryUsageId)).thenReturn(
				createFetchCountryUsageResponse(countryUsageId, Status.OperationSuccess));

		return countryUsageId;
	}

	/**
	 * Mock fetch country by code.
	 *
	 * @param member the member
	 * @return the string
	 */
	private String mockFetchCountryByCode(Member member)
	{
		String countryCode = member.getCountryUsageList().get(0).getCountry().getCode();
		Mockito.when(getCountryDAC().fetchCountryByCode(countryCode)).thenReturn(
				createFetchCountryResponse(countryCode, Status.OperationSuccess));

		return countryCode;
	}

	/**
	 * Mock employment info.
	 *
	 * @param member the member
	 */
	private void mockEmploymentInfo(Member member)
	{
		InternalResultsResponse<EmploymentInfo> internalResponse = new InternalResultsResponse<EmploymentInfo>();
		internalResponse.setStatus(Status.OperationSuccess);
		EmploymentInfo employmentInfo = CommonTestRoutines.createDummyEmploymentInfo();
		internalResponse.addResult(employmentInfo);

		Mockito.when(getPersonDAC().fetchEmploymentInfoByMemberId(Matchers.any(EmploymentInfo.class))).thenReturn(
				internalResponse);
	}

	/**
	 * Mock empty employment info.
	 */
	private void mockEmptyEmploymentInfo()
	{
		InternalResultsResponse<EmploymentInfo> internalResponse = new InternalResultsResponse<EmploymentInfo>();
		internalResponse.setStatus(Status.OperationSuccess);

		Mockito.when(getPersonDAC().fetchEmploymentInfoByMemberId(Matchers.any(EmploymentInfo.class))).thenReturn(
				internalResponse);
	}

	/**
	 * Mock location.
	 */
	private void mockLocation()
	{
		InternalResultsResponse<Location> internalResponse = new InternalResultsResponse<Location>();
		Location location = CommonTestRoutines.createDummyLocation(CommonTestRoutines.createDummyOrganization());
		internalResponse.setStatus(Status.OperationSuccess);
		internalResponse.addResult(location);

		Mockito.when(getLocationDAC().fetchLocationById(Matchers.any(FetchByIdRequest.class)))
				.thenReturn(
						internalResponse);
	}

	/**
	 * Create according to the parameter "amount" the security question & answer to the Member.
	 *
	 * @param amount
	 * @param member
	 * @return
	 */
	private List<PersonSecurityAnswer> createSecurityQuestions(Integer amount, Member member)
	{
		List<PersonSecurityAnswer> personSecurityQuestionList = new ArrayList<PersonSecurityAnswer>();
		Integer index = ONE;

		while (index <= amount)
		{
			// -- security question
			SecurityQuestion securityQuestion = new SecurityQuestion();
			securityQuestion.setId(index);
			securityQuestion.setSecurityQuestionKey("Question" + index.toString());

			// -- person security answer
			PersonSecurityAnswer personSecurityAnswer = new PersonSecurityAnswer();
			personSecurityAnswer.setId(index);
			personSecurityAnswer.setAnswerText("Answer" + index.toString());
			personSecurityAnswer.setSecurityQuestion(securityQuestion);
			personSecurityAnswer.setParentKey(member.getId());
			personSecurityAnswer.setModelAction(member.getModelAction());

			personSecurityQuestionList.add(personSecurityAnswer);

			index++;
		}

		return personSecurityQuestionList;
	}

}
