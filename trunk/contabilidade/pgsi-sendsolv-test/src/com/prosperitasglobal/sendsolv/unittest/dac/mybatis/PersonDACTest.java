package com.prosperitasglobal.sendsolv.unittest.dac.mybatis;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import com.prosperitasglobal.cbof.model.BusinessTypeEnum;
import com.prosperitasglobal.cbof.model.CodeValueEnum;
import com.prosperitasglobal.cbof.model.Contact;
import com.prosperitasglobal.cbof.model.Note;
import com.prosperitasglobal.cbof.model.Phone;
import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.cbof.unittest.util.AbstractTestBaseDAC;
import com.prosperitasglobal.cbof.unittest.util.CommonTestRoutines;
import com.prosperitasglobal.sendsolv.model.CountryUsage;
import com.prosperitasglobal.sendsolv.model.Document;
import com.prosperitasglobal.sendsolv.model.EmploymentInfo;
import com.prosperitasglobal.sendsolv.model.Liaison;
import com.prosperitasglobal.sendsolv.model.Member;
import com.prosperitasglobal.sendsolv.model.Person;
import com.prosperitasglobal.sendsolv.model.PersonName;
import com.prosperitasglobal.sendsolv.model.PersonSecurityAnswer;
import com.prosperitasglobal.sendsolv.model.Recipient;
import com.prosperitasglobal.sendsolv.model.SecurityQuestion;
import com.prosperitasglobal.sendsolv.model.StatusEnum;
import com.prosperitasglobal.sendsolv.model.TransferSetting;
import com.prosperitasglobal.sendsolv.model.criteria.MemberCriteria;
import com.prosperitasglobal.sendsolv.model.request.MemberInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.MemberMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.request.PagedInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.RecipientInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.RecipientMaintenanceRequest;
import com.prosperitasglobal.sendsolv.sdn.bac.ISdnCheckerBAC;
import com.prosperitasglobal.sendsolv.sdn.model.SDNStatusEnum;
import com.prosperitasglobal.sendsolv.sdn.model.SdnStatusHistory;
import com.prosperitasglobal.sendsolv.sdn.model.request.SdnCheckerRequest;
import com.prosperitasglobal.sendsolv.sdn.model.request.SdnStatusHistoryRequest;
import com.prosperitasglobal.sendsolv.sdn.unittest.util.CommonSdnTestRoutines;
import com.qat.framework.model.QATModel.PersistanceActionEnum;
import com.qat.framework.model.SortExpression;
import com.qat.framework.model.SortExpression.Direction;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResponse.Status;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.validation.ValidationUtil;

/**
 * The Class PersonDACTest. This test covers Liaison/Member/Recipient
 */
public class PersonDACTest extends AbstractTestBaseDAC
{

	/** The Constant NUMBER_1050. */
	private static final int NUMBER_1050 = 1050;

	/** The Constant RED. */
	private static final String RED = "Red";

	/** The Constant NUMBER_100. */
	private static final int NUMBER_100 = 100;

	/** The Constant BLUE. */
	private static final String BLUE = "Blue";

	/** The Constant WHAT_IS_YOUR_FAVORITE_COLOR. */
	private static final String WHAT_IS_YOUR_FAVORITE_COLOR = "what.is.your.favorite.color";

	/** The Constant LORD_OF_THE_RINGS. */
	private static final String LORD_OF_THE_RINGS = "Lord of the Rings";

	/** The Constant WHAT_IS_YOUR_FAVORITE_MOVIE. */
	private static final String WHAT_IS_YOUR_FAVORITE_MOVIE = "what.is.your.favorite.movie";

	/** The Constant THE_EMPLOYMENT_INFO_STATUS_SHOUD_BE_THE_SAME. */
	private static final String THE_EMPLOYMENT_INFO_STATUS_SHOUD_BE_THE_SAME =
			"The Employment info status shoud be the same.";

	/** The Constant UPDATE_NOTE_TEXT. */
	private static final String UPDATE_NOTE_TEXT = "Update Note Text";

	/** The Constant THE_STATUS_PERSON_NEEDS_TO_BE_THE_SAME. */
	private static final String THE_STATUS_PERSON_NEEDS_TO_BE_THE_SAME = "The status person needs to be the same";

	/** The Constant RESPONSE_GET_FIRST_RESULT_CANNOT_BE_NULL. */
	private static final String RESPONSE_GET_FIRST_RESULT_CANNOT_BE_NULL = "response.getFirstResult() cannot be null";

	/** The Constant RESPONSE_GET_RESULTS_LIST_SIZE_NEEDS_TO_BE_0. */
	private static final String RESPONSE_GET_RESULTS_LIST_SIZE_NEEDS_TO_BE_0 =
			"response.getResultsList().size() needs to be > 0";

	/** The Constant NUMBER_OF_ROWS_NEED_TO_MATCH. */
	private static final String NUMBER_OF_ROWS_NEED_TO_MATCH = "Number of rows need to match";

	/** The Constant THIRD_PAGE_SIZE_NEEDS_TO_BE_0. */
	private static final String THIRD_PAGE_SIZE_NEEDS_TO_BE_0 = "third page size needs to be > 0";

	/** The Constant THIRD_PAGE_SIZE_NEEDS_TO_BE_2. */
	private static final String THIRD_PAGE_SIZE_NEEDS_TO_BE_2 = "third page size needs to be = 2";

	/** The Constant SECOND_PAGE_SIZE_NEEDS_TO_BE_2. */
	private static final String SECOND_PAGE_SIZE_NEEDS_TO_BE_2 = "second page size needs to be = 2";

	/** The Constant SECOND_PAGE_SIZE_NEEDS_TO_BE_0. */
	private static final String SECOND_PAGE_SIZE_NEEDS_TO_BE_0 = "second page size needs to be > 0";

	/** The Constant FIRST_PAGE_SIZE_NEEDS_TO_BE_2. */
	private static final String FIRST_PAGE_SIZE_NEEDS_TO_BE_2 = "first page size needs to be = 2";

	/** The Constant FIRST_PAGE_SIZE_NEEDS_TO_BE_0. */
	private static final String FIRST_PAGE_SIZE_NEEDS_TO_BE_0 = "first page size needs to be > 0";

	/** The Constant SECOND_LIAISON_NEEDS_TO_MATCH_LIST. */
	private static final String SECOND_LIAISON_NEEDS_TO_MATCH_LIST = "Second Liaison needs to match List";

	/** The Constant FIRST_LIAISON_NEEDS_TO_MATCH_LIST. */
	private static final String FIRST_LIAISON_NEEDS_TO_MATCH_LIST = "First Liaison needs to match List";

	/** The Constant SECOND_MEMBER_NEEDS_TO_MATCH_LIST. */
	private static final String SECOND_MEMBER_NEEDS_TO_MATCH_LIST = "Second Member needs to match List";

	/** The Constant FIRST_MEMBER_NEEDS_TO_MATCH_LIST. */
	private static final String FIRST_MEMBER_NEEDS_TO_MATCH_LIST = "First Member needs to match List";

	/** The Constant HUNDRED. */
	private static final Integer HUNDRED = 100;

	/** The Constant UPDATED_NAME. */
	private static final String UPDATED_NAME = "Updated Name";

	/** The Constant TWENTY. */
	private static final Integer TWENTY = 20;

	/** The Constant ZERO. */
	private static final Integer ZERO = 0;

	/** The Constant ONE. */
	private static final Integer ONE = 1;
	/** The Constant TWO. */
	private static final Integer TWO = 2;

	/** The Constant THREE. */
	private static final Integer THREE = 3;

	/** The Constant FOUR. */
	private static final Integer FOUR = 4;

	/** The Constant FIVE. */
	private static final Integer FIVE = 5;

	/** The Constant SIX. */
	private static final Integer SIX = 6;

	/** The Constant NINE. */
	private static final int NINE = 9;

	/** The Constant FIRST_NAME_SORT_FIELD. */
	private static final String FIRST_NAME_SORT_FIELD = "firstName";

	/** The Constant LAST_NAME_SORT_FIELD. */
	private static final String LAST_NAME_SORT_FIELD = "lastName";

	/** The Constant PARTICIPANT_ID_SORT_FIELD. */
	private static final String PARTICIPANT_ID_SORT_FIELD = "participantId";

	/** The Constant PERSON_STATUS_SORT_FIELD. */
	private static final String PERSON_STATUS_SORT_FIELD = "personStatus";

	/** The mock sdn checker bac. */
	private ISdnCheckerBAC mockSdnCheckerBAC;

	/**
	 * Sets the up.
	 */
	@Before
	public void setUp()
	{
		executeSqlScript("com/prosperitasglobal/sendsolv/unittest/dac/mybatis/conf/deletePerson.sql", false);
		Mockito.reset(getMockSdnCheckerBAC());
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
	 * Test insert Liaison (includes Contacts - Email,Phone, Address).
	 */
	@Test
	public void testInsertLiaison()
	{
		Liaison liaison = insertLiaison();
		testLiaisonAgainstDatabaseById(liaison);
	}

	/**
	 * Test update liaison.
	 */
	@Test
	public void testUpdateLiaison()
	{
		Liaison liaison = insertLiaison();

		InternalResultsResponse<Liaison> response =
				getPersonDAC().fetchLiaisonById(new FetchByIdRequest(liaison.getId()));
		liaison = response.getFirstResult();
		List<Document> documentlist = liaison.getDocumentList();

		CommonTestRoutines.createUpdatedDummyLiaison(liaison);
		liaison.setDocumentList(documentlist);
		liaison.setPrefix(fetchSuffixPrefix(CodeValueEnum.PREFIX));
		liaison.setSuffix(fetchSuffixPrefix(CodeValueEnum.SUFFIX));
		response = getPersonDAC().updateLiaison(liaison);

		CommonTestRoutines.assertResultResponse(response);
		testLiaisonAgainstDatabaseById(liaison);
	}

	/**
	 * Test update Liaison OL.
	 */
	@Test
	public void testUpdateLiaisonOL()
	{
		Liaison liaison = insertLiaison();

		liaison.setModelAction(PersistanceActionEnum.UPDATE);
		liaison.setTitle(UPDATED_NAME);

		InternalResultsResponse<Liaison> response = getPersonDAC().updateLiaison(liaison);
		Assert.assertEquals("Should NOT get an Optimistic Locking Error, but got " + response.getStatus(),
				Status.OperationSuccess, response.getStatus());

		response = getPersonDAC().fetchLiaisonById(new FetchByIdRequest(liaison.getId()));
		liaison = response.getFirstResult();
		Assert.assertTrue("Version should be > 0 ", liaison.getVersion() > 0);

		liaison.setModelAction(PersistanceActionEnum.UPDATE);
		// Set the version number to 100 to generate an OL error.
		liaison.setVersion(HUNDRED);
		response = getPersonDAC().updateLiaison(liaison);
		Assert.assertEquals("Should get an Optimistic Locking Error, but got " + response.getStatus(),
				Status.OptimisticLockingError, response.getStatus());

	}

	/**
	 * Test fetchCountryUsageById.
	 */
	@Test
	public void testFetchCountryUsageById()
	{
		Member member = insertMember();
		testMemberAgainstDatabaseById(member, PersistanceActionEnum.INSERT);
		CountryUsage countryUsage = member.getCountryUsageList().get(0);
		InternalResultsResponse<CountryUsage> response = getPersonDAC().fetchCountryUsageById(countryUsage.getId());
		CountryUsage dbCountryUsage = response.getFirstResult();
		assertCountryUsageFields(countryUsage, dbCountryUsage, PersistanceActionEnum.FETCH);
	}

	/**
	 * Test fetchAllLiaison with paging.
	 */
	@Test
	public void testFetchLiaisonByRequest()
	{
		Integer pageSize = TWO;
		Integer totalLiaisons = TWENTY;

		// Add 20 Liaisons
		List<Liaison> liaisonList = insertLiaisons(TWENTY);

		// get first page
		PagedInquiryRequest request = new PagedInquiryRequest();
		request.setParentId(liaisonList.get(0).getLocationId());
		request.setPageSize(pageSize);
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.getSortExpressions().clear();

		InternalResultsResponse<Liaison> response = getPersonDAC().fetchLiaisonByRequest(request);
		CommonTestRoutines.assertResultResponse(response);

		Assert.assertTrue(FIRST_PAGE_SIZE_NEEDS_TO_BE_0, response.getResultsList().size() > ZERO);
		Assert.assertEquals(FIRST_PAGE_SIZE_NEEDS_TO_BE_2, pageSize, (Integer)response.getResultsList().size());

		Assert.assertEquals(FIRST_LIAISON_NEEDS_TO_MATCH_LIST, liaisonList.get(ZERO).getId(),
				response.getResultsList().get(ZERO).getId());
		Assert.assertEquals(SECOND_LIAISON_NEEDS_TO_MATCH_LIST, liaisonList.get(ONE).getId(),
				response.getResultsList().get(ONE).getId());
		Assert.assertEquals(NUMBER_OF_ROWS_NEED_TO_MATCH, totalLiaisons, response.getResultsSetInfo()
				.getTotalRowsAvailable());

		// go to next page
		request.setStartPage(1);

		response = getPersonDAC().fetchLiaisonByRequest(request);
		Assert.assertTrue(SECOND_PAGE_SIZE_NEEDS_TO_BE_0, response.getResultsList().size() > ZERO);
		Assert.assertEquals(SECOND_PAGE_SIZE_NEEDS_TO_BE_2, pageSize, (Integer)response.getResultsList().size());

		Assert.assertEquals(FIRST_LIAISON_NEEDS_TO_MATCH_LIST, liaisonList.get(TWO).getId(),
				response.getResultsList().get(ZERO).getId());
		Assert.assertEquals(SECOND_LIAISON_NEEDS_TO_MATCH_LIST, liaisonList.get(THREE).getId(),
				response.getResultsList().get(ONE).getId());

		// one more page
		request.setStartPage(2);

		response = getPersonDAC().fetchLiaisonByRequest(request);
		Assert.assertTrue(THIRD_PAGE_SIZE_NEEDS_TO_BE_0, response.getResultsList().size() > 0);
		Assert.assertEquals(THIRD_PAGE_SIZE_NEEDS_TO_BE_2, pageSize, (Integer)response.getResultsList().size());

		Assert.assertEquals(FIRST_LIAISON_NEEDS_TO_MATCH_LIST, liaisonList.get(FOUR).getId(),
				response.getResultsList().get(ZERO).getId());
		Assert.assertEquals(SECOND_LIAISON_NEEDS_TO_MATCH_LIST, liaisonList.get(FIVE).getId(),
				response.getResultsList().get(ONE).getId());
	}

	/**
	 * Test insert recipient.
	 */
	@Test
	public void testInsertRecipient()
	{
		Recipient recipient = insertRecipient();
		testRecipientAgainstDatabaseById(recipient);
	}

	/**
	 * Test insert member.
	 */
	@Test
	public void testInsertMember()
	{
		Member member = insertMember();
		testMemberAgainstDatabaseById(member, PersistanceActionEnum.INSERT);
	}

	/**
	 * Test insert member with security question.
	 */
	@Test
	public void testInsertMemberWithSecurityQuestion()
	{
		// -- create a new member
		Member member = createMember(NUMBER_100);

		// -- create the security question
		SecurityQuestion securityQuestion = insertSecurityQuestion(WHAT_IS_YOUR_FAVORITE_COLOR);
		PersonSecurityAnswer personSecurityAnswer = createPersonSecurityAnswer(BLUE, securityQuestion, member);
		personSecurityAnswer.setModelAction(PersistanceActionEnum.INSERT);

		member.setSecurityAnswerList(Arrays.asList(personSecurityAnswer));

		InternalResultsResponse<Member> response = getPersonDAC().insertMember(member);
		CommonTestRoutines.assertResultResponse(response);

		assertQuestions(member);
	}

	/**
	 * Test update member with security question.
	 */
	@Test
	public void testUpdateMemberWithSecurityQuestion()
	{
		// -- create a new member
		Member member = createMember(NUMBER_100);

		// -- create the security question
		SecurityQuestion securityQuestion = insertSecurityQuestion(WHAT_IS_YOUR_FAVORITE_COLOR);
		PersonSecurityAnswer personSecurityAnswer = createPersonSecurityAnswer(RED, securityQuestion, member);
		personSecurityAnswer.setModelAction(PersistanceActionEnum.INSERT);

		member.setSecurityAnswerList(Arrays.asList(personSecurityAnswer));

		InternalResultsResponse<Member> response = getPersonDAC().insertMember(member);
		CommonTestRoutines.assertResultResponse(response);

		// -- update the security answer - the update will be done inside of addTransferSettingsToMember.
		member.getSecurityAnswerList().get(0).setAnswerText(BLUE);
		member.getSecurityAnswerList().get(0).setModelAction(PersistanceActionEnum.UPDATE);

		addTransferSettingsToMember(member);

		assertQuestions(member);
	}

	@Test
	public void testDeleteMemberWithSecurityQuestion()
	{
		// -- create a new member
		Member member = createMember(NUMBER_100);

		// -- create the security question
		SecurityQuestion securityQuestion = insertSecurityQuestion(WHAT_IS_YOUR_FAVORITE_COLOR);
		PersonSecurityAnswer personSecurityAnswer = createPersonSecurityAnswer(RED, securityQuestion, member);
		personSecurityAnswer.setModelAction(PersistanceActionEnum.INSERT);

		member.setSecurityAnswerList(Arrays.asList(personSecurityAnswer));

		InternalResultsResponse<Member> response = getPersonDAC().insertMember(member);
		CommonTestRoutines.assertResultResponse(response);
		addTransferSettingsToMember(member);

		FetchByIdRequest request = new FetchByIdRequest(member.getId());
		response = getPersonDAC().fetchMemberById(request);
		CommonTestRoutines.assertResultResponse(response);

		member = response.getFirstResult();

		// -- delete the security answer
		member.getSecurityAnswerList().get(0).setModelAction(PersistanceActionEnum.DELETE);
		member.getSecurityAnswerList().get(1).setModelAction(PersistanceActionEnum.DELETE);
		response = getPersonDAC().updateMember(member);

		response = getPersonDAC().fetchMemberById(request);

		Assert.assertTrue("The person security answer should be null", response.getFirstResult()
				.getSecurityAnswerList()
				.size() == 0);

	}

	/**
	 * Test fetch member by id with security question.
	 */
	@Test
	public void testFetchMemberByIdWithSecurityQuestion()
	{
		// -- insert a member to start the test
		Member member = insertMember(0);

		// -- insert the first security question
		SecurityQuestion securityQuestion = insertSecurityQuestion(WHAT_IS_YOUR_FAVORITE_COLOR);
		insertPersonSecurityAnswer(BLUE, securityQuestion, member);

		// -- insert the second security question
		securityQuestion = insertSecurityQuestion(WHAT_IS_YOUR_FAVORITE_MOVIE);
		insertPersonSecurityAnswer(LORD_OF_THE_RINGS, securityQuestion, member);

		// -- verify if the questions & answers match
		InternalResultsResponse<Member> response = getPersonDAC().fetchMemberById(new FetchByIdRequest(member.getId()));
		CommonTestRoutines.assertResultResponse(response);

		Assert.assertTrue("The result should have two questions", response.getFirstResult().getSecurityAnswerList()
				.size() == 2);

		Assert.assertTrue("The first answer should be - Blue ", response.getFirstResult()
				.getSecurityAnswerList().get(0).getAnswerText().equals(BLUE));

		Assert.assertTrue("The second answer should be - Lord of the Rings - ", response.getFirstResult()
				.getSecurityAnswerList().get(1).getAnswerText().equals(LORD_OF_THE_RINGS));
	}

	/**
	 * Test update recipient.
	 */
	@Test
	public void testUpdateRecipient()
	{
		Recipient recipient = insertRecipient();

		InternalResultsResponse<Recipient> response =
				getPersonDAC().fetchRecipientById(new FetchByIdRequest(recipient.getId()));
		recipient = response.getFirstResult();
		List<Document> documentList = recipient.getDocumentList();

		CommonTestRoutines.createUpdatedDummyRecipient(recipient);
		recipient.setDocumentList(documentList);
		response = getPersonDAC().updateRecipient(recipient);

		CommonTestRoutines.assertResultResponse(response);
		testRecipientAgainstDatabaseById(response.getFirstResult());
	}

	/**
	 * Test update member.
	 */
	@Test
	public void testUpdateMember()
	{
		Member member = insertMember();

		InternalResultsResponse<Member> response = getPersonDAC().fetchMemberById(new FetchByIdRequest(member.getId()));
		member = response.getFirstResult();
		List<Document> documentList = member.getDocumentList();

		CommonTestRoutines.createUpdatedDummyMember(member);
		member.setDocumentList(documentList);
		response = getPersonDAC().updateMember(member);

		CommonTestRoutines.assertResultResponse(response);
		testMemberAgainstDatabaseById(response.getFirstResult(), PersistanceActionEnum.UPDATE);
	}

	/**
	 * Test update member deleting employment info.
	 */
	@Test
	public void testUpdateMemberDeletingEmploymentInfo()
	{
		Member member = insertMember();

		InternalResultsResponse<Member> response = getPersonDAC().fetchMemberById(new FetchByIdRequest(member.getId()));
		member = response.getFirstResult();
		List<Document> documentList = member.getDocumentList();

		member.getEmploymentInfoList().get(0).setModelAction(PersistanceActionEnum.DELETE);
		// CommonTestRoutines.createUpdatedDummyMember(member);
		member.setDocumentList(documentList);
		response = getPersonDAC().updateMember(member);

		CommonTestRoutines.assertResultResponse(response);
	}

	/**
	 * Test fetch recipient by id.
	 */
	@Test
	public void testFetchRecipientById()
	{
		List<Recipient> insertedRecipients = insertRecipients(1);

		InternalResultsResponse<Recipient> response =
				getPersonDAC().fetchRecipientById(new FetchByIdRequest(insertedRecipients.get(0).getId()));

		CommonTestRoutines.assertResultResponse(response);

	}

	/**
	 * Test fetch recipient by id check sdn status code.
	 */
	@Test
	public void testFetchRecipientByIdCheckSDNStatusCode()
	{
		Recipient recipient = insertRecipient();

		prepareMockSdnCheckerBAC(recipient);

		InternalResultsResponse<Recipient> response =
				getPersonDAC().fetchRecipientById(new FetchByIdRequest(recipient.getId()));
		CommonTestRoutines.assertResultResponse(response);

		// Verify the SDN status code should be equals.
		Assert.assertEquals(SDNStatusEnum.PENDING_INVESTIGATION, response.getFirstResult().getSDNStatus());
	}

	/**
	 * Test fetch recipient by participant id.
	 */
	@Test
	public void testFetchRecipientByParticipantId()
	{
		List<Recipient> insertedRecipients = insertRecipients(1);
		FetchByIdRequest request = new FetchByIdRequest();
		request.setStringId(insertedRecipients.get(0).getParticipantId());

		InternalResultsResponse<Recipient> response =
				getPersonDAC().fetchRecipientById(request);

		CommonTestRoutines.assertResultResponse(response);

	}

	/**
	 * Test fetch recipient by request.
	 */
	@Test
	public void testFetchRecipientByRequest()
	{
		Integer pageSize = TWO;
		Integer totalLiaisons = NINE;

		// Add 20 Liaisons
		List<Recipient> recipientList = insertRecipients(NINE);

		// get first page
		RecipientInquiryRequest request = new RecipientInquiryRequest();
		request.setPageSize(pageSize);
		request.setStartPage(0);
		request.setPreQueryCount(true);
		request.addSortExpressions(new SortExpression(PARTICIPANT_ID_SORT_FIELD, Direction.Ascending));

		InternalResultsResponse<Recipient> response = getPersonDAC().fetchRecipientByRequest(request);
		CommonTestRoutines.assertResultResponse(response);

		Assert.assertTrue(FIRST_PAGE_SIZE_NEEDS_TO_BE_0, response.getResultsList().size() > 0);
		Assert.assertEquals(FIRST_PAGE_SIZE_NEEDS_TO_BE_2, pageSize, (Integer)response.getResultsList().size());

		Assert.assertEquals(FIRST_LIAISON_NEEDS_TO_MATCH_LIST, recipientList.get(0).getId(), response.getResultsList()
				.get(0)
				.getId());
		Assert.assertEquals(SECOND_LIAISON_NEEDS_TO_MATCH_LIST, recipientList.get(1).getId(), response.getResultsList()
				.get(1)
				.getId());
		Assert.assertEquals(NUMBER_OF_ROWS_NEED_TO_MATCH, totalLiaisons, response.getResultsSetInfo()
				.getTotalRowsAvailable());

		// go to next page
		request.setStartPage(1);

		response = getPersonDAC().fetchRecipientByRequest(request);
		Assert.assertTrue(SECOND_PAGE_SIZE_NEEDS_TO_BE_0, response.getResultsList().size() > ZERO);
		Assert.assertEquals(SECOND_PAGE_SIZE_NEEDS_TO_BE_2, pageSize, (Integer)response.getResultsList().size());

		Assert.assertEquals(FIRST_LIAISON_NEEDS_TO_MATCH_LIST, recipientList.get(TWO).getId(),
				response.getResultsList().get(ZERO).getId());
		Assert.assertEquals(SECOND_LIAISON_NEEDS_TO_MATCH_LIST, recipientList.get(THREE).getId(), response
				.getResultsList()
				.get(ONE)
				.getId());

		// one more page
		request.setStartPage(2);

		response = getPersonDAC().fetchRecipientByRequest(request);
		Assert.assertTrue(THIRD_PAGE_SIZE_NEEDS_TO_BE_0, response.getResultsList().size() > 0);
		Assert.assertEquals(THIRD_PAGE_SIZE_NEEDS_TO_BE_2, pageSize, (Integer)response.getResultsList().size());

		Assert.assertEquals(FIRST_LIAISON_NEEDS_TO_MATCH_LIST, recipientList.get(FOUR).getId(),
				response.getResultsList().get(ZERO)
						.getId());
		Assert.assertEquals(SECOND_LIAISON_NEEDS_TO_MATCH_LIST, recipientList.get(FIVE).getId(), response
				.getResultsList()
				.get(ONE)
				.getId());
	}

	/**
	 * Test fetch recipient by request check sdn status code.
	 */
	@Test
	public void testFetchRecipientByRequestCheckSDNStatusCode()
	{
		List<Recipient> recipientList = insertRecipients(TWO);

		prepareMockSdnCheckerBAC(recipientList.get(0));

		RecipientInquiryRequest inquiryRequest = new RecipientInquiryRequest();
		inquiryRequest.setStatusList(Arrays.asList(StatusEnum.ACTIVE));
		InternalResultsResponse<Recipient> recipientResponse = getPersonDAC().fetchRecipientByRequest(inquiryRequest);
		CommonTestRoutines.assertResultResponse(recipientResponse);

		// Verify the SDN status code should match.
		Assert.assertEquals(SDNStatusEnum.PENDING_INVESTIGATION, recipientResponse.getResultsList().get(0)
				.getSDNStatus());
		Assert.assertEquals(SDNStatusEnum.PENDING_INVESTIGATION, recipientResponse.getResultsList().get(1)
				.getSDNStatus());

	}

	/**
	 * Test fetch recipient by request response sorted by first name ascending.
	 */
	@Test
	public void testFetchRecipientByRequestSortFirstNameAscending()
	{
		insertRecipients(TWENTY, true);

		// get first page
		RecipientInquiryRequest request = new RecipientInquiryRequest();
		request.addSortExpressions(new SortExpression(FIRST_NAME_SORT_FIELD, Direction.Ascending));

		InternalResultsResponse<Recipient> response = getPersonDAC().fetchRecipientByRequest(request);
		CommonTestRoutines.assertResultResponse(response);

		assertRecipientSort(response.getResultsList(), FIRST_NAME_SORT_FIELD, Direction.Ascending);
	}

	/**
	 * Test fetch recipient by request response sorted by first name descending.
	 */
	@Test
	public void testFetchRecipientByRequestSortFirstNameDescending()
	{
		insertRecipients(TWENTY, true);

		// get first page
		RecipientInquiryRequest request = new RecipientInquiryRequest();
		request.addSortExpressions(new SortExpression(FIRST_NAME_SORT_FIELD, Direction.Descending));

		InternalResultsResponse<Recipient> response = getPersonDAC().fetchRecipientByRequest(request);
		CommonTestRoutines.assertResultResponse(response);

		assertRecipientSort(response.getResultsList(), FIRST_NAME_SORT_FIELD, Direction.Descending);
	}

	/**
	 * Test fetch recipient by request response sorted by last name ascending.
	 */
	@Test
	public void testFetchRecipientByRequestSortLastNameAscending()
	{
		insertRecipients(TWENTY, true);

		// get first page
		RecipientInquiryRequest request = new RecipientInquiryRequest();
		request.addSortExpressions(new SortExpression(LAST_NAME_SORT_FIELD, Direction.Ascending));

		InternalResultsResponse<Recipient> response = getPersonDAC().fetchRecipientByRequest(request);
		CommonTestRoutines.assertResultResponse(response);

		assertRecipientSort(response.getResultsList(), LAST_NAME_SORT_FIELD, Direction.Ascending);
	}

	/**
	 * Test fetch recipient by request response sorted by last name descending.
	 */
	@Test
	public void testFetchRecipientByRequestSortLastNameDescending()
	{
		insertRecipients(TWENTY, true);

		// get first page
		RecipientInquiryRequest request = new RecipientInquiryRequest();
		request.addSortExpressions(new SortExpression(LAST_NAME_SORT_FIELD, Direction.Descending));

		InternalResultsResponse<Recipient> response = getPersonDAC().fetchRecipientByRequest(request);
		CommonTestRoutines.assertResultResponse(response);

		assertRecipientSort(response.getResultsList(), LAST_NAME_SORT_FIELD, Direction.Descending);
	}

	/**
	 * Test fetch recipient by request response sorted participant id ascending.
	 */
	@Test
	public void testFetchRecipientByRequestSortParticipantIdAscending()
	{
		insertRecipients(TWENTY, true);

		// get first page
		RecipientInquiryRequest request = new RecipientInquiryRequest();
		request.addSortExpressions(new SortExpression(PARTICIPANT_ID_SORT_FIELD, Direction.Ascending));

		InternalResultsResponse<Recipient> response = getPersonDAC().fetchRecipientByRequest(request);
		CommonTestRoutines.assertResultResponse(response);

		assertRecipientSort(response.getResultsList(), PARTICIPANT_ID_SORT_FIELD, Direction.Ascending);
	}

	/**
	 * Test fetch recipient by request response sorted by participant id descending.
	 */
	@Test
	public void testFetchRecipientByRequestSortParticipantIdDescending()
	{
		insertRecipients(TWENTY, true);

		// get first page
		RecipientInquiryRequest request = new RecipientInquiryRequest();
		request.addSortExpressions(new SortExpression(LAST_NAME_SORT_FIELD, Direction.Descending));

		InternalResultsResponse<Recipient> response = getPersonDAC().fetchRecipientByRequest(request);
		CommonTestRoutines.assertResultResponse(response);

		assertRecipientSort(response.getResultsList(), LAST_NAME_SORT_FIELD, Direction.Descending);
	}

	/**
	 * Test fetch recipient by request response sorted person status ascending.
	 */
	@Test
	public void testFetchRecipientByRequestSortPersonStatusAscending()
	{
		insertRecipients(TWENTY, true);

		// get first page
		RecipientInquiryRequest request = new RecipientInquiryRequest();
		request.addSortExpressions(new SortExpression(PERSON_STATUS_SORT_FIELD, Direction.Ascending));

		InternalResultsResponse<Recipient> response = getPersonDAC().fetchRecipientByRequest(request);
		CommonTestRoutines.assertResultResponse(response);

		assertRecipientSort(response.getResultsList(), PERSON_STATUS_SORT_FIELD, Direction.Ascending);
	}

	/**
	 * Test fetch recipient by request response sorted by person status descending.
	 */
	@Test
	public void testFetchRecipientByRequestSortPersonStatusDescending()
	{
		insertRecipients(TWENTY, true);

		// get first page
		RecipientInquiryRequest request = new RecipientInquiryRequest();
		request.addSortExpressions(new SortExpression(PERSON_STATUS_SORT_FIELD, Direction.Descending));

		InternalResultsResponse<Recipient> response = getPersonDAC().fetchRecipientByRequest(request);
		CommonTestRoutines.assertResultResponse(response);

		assertRecipientSort(response.getResultsList(), PERSON_STATUS_SORT_FIELD, Direction.Descending);
	}

	/**
	 * Test fetch member by id.
	 */
	@Test
	public void testFetchMemberById()
	{
		List<Member> insertedMembers = insertMembers(1);

		InternalResultsResponse<Member> response =
				getPersonDAC().fetchMemberById(new FetchByIdRequest(insertedMembers.get(0).getId()));

		CommonTestRoutines.assertResultResponse(response);

	}

	/**
	 * Test fetch member by id check sdn status code.
	 */
	@Test
	public void testFetchMemberByIdCheckSDNStatusCode()
	{
		Member member = insertMember();

		prepareMockSdnCheckerBAC(member);

		InternalResultsResponse<Member> response = getPersonDAC().fetchMemberById(new FetchByIdRequest(member.getId()));
		CommonTestRoutines.assertResultResponse(response);

		// Verify the SDN status code should be equals.
		Assert.assertEquals(SDNStatusEnum.PENDING_INVESTIGATION, response.getFirstResult().getSDNStatus());
	}

	/**
	 * Test fetch member by participant id.
	 */
	@Test
	public void testFetchMemberByParticipantId()
	{
		List<Member> insertedMembers = insertMembers(1);
		FetchByIdRequest request = new FetchByIdRequest();
		request.equals(insertedMembers.get(0).getParticipantId());
		InternalResultsResponse<Member> response =
				getPersonDAC().fetchMemberById(request);

		CommonTestRoutines.assertResultResponse(response);

	}

	/**
	 * Test fetch member by request.
	 */
	@Test
	public void testFetchMemberByRequest()
	{
		Integer pageSize = TWO;
		Integer totalMembers = NINE;

		// Add 20 Liaisons
		List<Member> memberList = insertMembers(NINE);

		// get first page
		MemberInquiryRequest request = new MemberInquiryRequest();
		request.setPageSize(pageSize);
		request.setStartPage(0);
		request.setPreQueryCount(true);
		request.setStartPage(0);
		request.addSortExpressions(new SortExpression(PARTICIPANT_ID_SORT_FIELD, Direction.Ascending));

		InternalResultsResponse<Member> response = getPersonDAC().fetchMemberByRequest(request);
		CommonTestRoutines.assertResultResponse(response);

		Assert.assertTrue(FIRST_PAGE_SIZE_NEEDS_TO_BE_0, response.getResultsList().size() > ZERO);
		Assert.assertEquals(FIRST_PAGE_SIZE_NEEDS_TO_BE_2, pageSize, (Integer)response.getResultsList().size());

		Assert.assertEquals(FIRST_MEMBER_NEEDS_TO_MATCH_LIST, memberList.get(ZERO).getId(), response.getResultsList()
				.get(ZERO).getId());
		Assert.assertEquals(SECOND_MEMBER_NEEDS_TO_MATCH_LIST, memberList.get(ONE).getId(), response.getResultsList()
				.get(ONE)
				.getId());
		Assert.assertEquals(NUMBER_OF_ROWS_NEED_TO_MATCH, totalMembers, response.getResultsSetInfo()
				.getTotalRowsAvailable());

		// go to next page
		request.setStartPage(1);

		response = getPersonDAC().fetchMemberByRequest(request);
		Assert.assertTrue(SECOND_PAGE_SIZE_NEEDS_TO_BE_0, response.getResultsList().size() > ZERO);
		Assert.assertEquals(SECOND_PAGE_SIZE_NEEDS_TO_BE_2, pageSize, (Integer)response.getResultsList().size());

		Assert.assertEquals(FIRST_MEMBER_NEEDS_TO_MATCH_LIST, memberList.get(TWO).getId(), response.getResultsList()
				.get(ZERO)
				.getId());
		Assert.assertEquals(SECOND_MEMBER_NEEDS_TO_MATCH_LIST, memberList.get(THREE).getId(),
				response.getResultsList().get(ONE).getId());

		// one more page
		request.setStartPage(2);

		response = getPersonDAC().fetchMemberByRequest(request);
		Assert.assertTrue(THIRD_PAGE_SIZE_NEEDS_TO_BE_0, response.getResultsList().size() > 0);
		Assert.assertEquals(THIRD_PAGE_SIZE_NEEDS_TO_BE_2, pageSize, (Integer)response.getResultsList().size());

		Assert.assertEquals(FIRST_MEMBER_NEEDS_TO_MATCH_LIST, memberList.get(FOUR).getId(), response.getResultsList()
				.get(ZERO).getId());
		Assert.assertEquals(SECOND_MEMBER_NEEDS_TO_MATCH_LIST, memberList.get(FIVE).getId(), response.getResultsList()
				.get(ONE).getId());
	}

	/**
	 * Test fetch member by request check sdn status code.
	 */
	@Test
	public void testFetchMemberByRequestCheckSDNStatusCode()
	{
		List<Member> memberList = insertMembers(TWO);

		prepareMockSdnCheckerBAC(memberList.get(0));

		MemberInquiryRequest inquiryRequest = new MemberInquiryRequest();
		inquiryRequest.setStatusList(Arrays.asList(StatusEnum.ACTIVE));
		InternalResultsResponse<Member> recipientResponse = getPersonDAC().fetchMemberByRequest(inquiryRequest);
		CommonTestRoutines.assertResultResponse(recipientResponse);

		// Verify the SDN status code should match.
		Assert.assertEquals(SDNStatusEnum.PENDING_INVESTIGATION, recipientResponse.getResultsList().get(0)
				.getSDNStatus());
		Assert.assertEquals(SDNStatusEnum.PENDING_INVESTIGATION, recipientResponse.getResultsList().get(1)
				.getSDNStatus());

	}

	/**
	 * Test fetch member by request response sorted by first name ascending.
	 */
	@Test
	public void testFetchMemberByRequestSortFirstNameAscending()
	{
		insertMembers(TWENTY, true);

		// get first page
		MemberInquiryRequest request = new MemberInquiryRequest();
		request.addSortExpressions(new SortExpression(FIRST_NAME_SORT_FIELD, Direction.Ascending));

		InternalResultsResponse<Member> response = getPersonDAC().fetchMemberByRequest(request);
		CommonTestRoutines.assertResultResponse(response);

		assertMemberSort(response.getResultsList(), FIRST_NAME_SORT_FIELD, Direction.Ascending);
	}

	/**
	 * Test fetch member by request response sorted by first name descending.
	 */
	@Test
	public void testFetchMemberByRequestSortFirstNameDescending()
	{
		insertMembers(TWENTY, true);

		// get first page
		MemberInquiryRequest request = new MemberInquiryRequest();
		request.addSortExpressions(new SortExpression(FIRST_NAME_SORT_FIELD, Direction.Descending));

		InternalResultsResponse<Member> response = getPersonDAC().fetchMemberByRequest(request);
		CommonTestRoutines.assertResultResponse(response);

		assertMemberSort(response.getResultsList(), FIRST_NAME_SORT_FIELD, Direction.Descending);
	}

	/**
	 * Test fetch member by request response sorted by last name ascending.
	 */
	@Test
	public void testFetchMemberByRequestSortLastNameAscending()
	{
		insertMembers(TWENTY, true);

		// get first page
		MemberInquiryRequest request = new MemberInquiryRequest();
		request.addSortExpressions(new SortExpression(LAST_NAME_SORT_FIELD, Direction.Ascending));

		InternalResultsResponse<Member> response = getPersonDAC().fetchMemberByRequest(request);
		CommonTestRoutines.assertResultResponse(response);

		assertMemberSort(response.getResultsList(), LAST_NAME_SORT_FIELD, Direction.Ascending);
	}

	/**
	 * Test fetch member by request response sorted by last name descending.
	 */
	@Test
	public void testFetchMemberByRequestSortLastNameDescending()
	{
		insertMembers(TWENTY, true);

		// get first page
		MemberInquiryRequest request = new MemberInquiryRequest();
		request.addSortExpressions(new SortExpression(LAST_NAME_SORT_FIELD, Direction.Descending));

		InternalResultsResponse<Member> response = getPersonDAC().fetchMemberByRequest(request);
		CommonTestRoutines.assertResultResponse(response);

		assertMemberSort(response.getResultsList(), LAST_NAME_SORT_FIELD, Direction.Descending);
	}

	/**
	 * Test fetch member by request response sorted participant id ascending.
	 */
	@Test
	public void testFetchMemberByRequestSortParticipantIdAscending()
	{
		insertMembers(TWENTY, true);

		// get first page
		MemberInquiryRequest request = new MemberInquiryRequest();
		request.addSortExpressions(new SortExpression(PARTICIPANT_ID_SORT_FIELD, Direction.Ascending));

		InternalResultsResponse<Member> response = getPersonDAC().fetchMemberByRequest(request);
		CommonTestRoutines.assertResultResponse(response);

		assertMemberSort(response.getResultsList(), PARTICIPANT_ID_SORT_FIELD, Direction.Ascending);
	}

	/**
	 * Test fetch member by request response sorted by participant id descending.
	 */
	@Test
	public void testFetchMemberByRequestSortParticipantIdDescending()
	{
		insertMembers(TWENTY, true);

		// get first page
		MemberInquiryRequest request = new MemberInquiryRequest();
		request.addSortExpressions(new SortExpression(LAST_NAME_SORT_FIELD, Direction.Descending));

		InternalResultsResponse<Member> response = getPersonDAC().fetchMemberByRequest(request);
		CommonTestRoutines.assertResultResponse(response);

		assertMemberSort(response.getResultsList(), LAST_NAME_SORT_FIELD, Direction.Descending);
	}

	/**
	 * Test fetch member by request response sorted person status ascending.
	 */
	@Test
	public void testFetchMemberByRequestSortPersonStatusAscending()
	{
		insertMembers(TWENTY, true);

		// get first page
		MemberInquiryRequest request = new MemberInquiryRequest();
		request.addSortExpressions(new SortExpression(PERSON_STATUS_SORT_FIELD, Direction.Ascending));

		InternalResultsResponse<Member> response = getPersonDAC().fetchMemberByRequest(request);
		CommonTestRoutines.assertResultResponse(response);

		assertMemberSort(response.getResultsList(), PERSON_STATUS_SORT_FIELD, Direction.Ascending);
	}

	/**
	 * Test fetch member by request response sorted by person status descending.
	 */
	@Test
	public void testFetchMemberByRequestSortPersonStatusDescending()
	{
		insertMembers(TWENTY, true);

		// get first page
		MemberInquiryRequest request = new MemberInquiryRequest();
		request.addSortExpressions(new SortExpression(PERSON_STATUS_SORT_FIELD, Direction.Descending));

		InternalResultsResponse<Member> response = getPersonDAC().fetchMemberByRequest(request);
		CommonTestRoutines.assertResultResponse(response);

		assertMemberSort(response.getResultsList(), PERSON_STATUS_SORT_FIELD, Direction.Descending);
	}

	/** Test successful fetch of member by request, member id specified in the criteria specified. */
	@Test
	public void testFetchMemberByRequestMemberId()
	{
		MemberInquiryRequest request = new MemberInquiryRequest();
		Member member = insertMember();
		Member memberCriteria = new Member();

		memberCriteria.setParticipantId(member.getParticipantId());
		request.getCriteria().setMember(memberCriteria);

		InternalResultsResponse<Member> response = getPersonDAC().fetchMemberByRequest(request);
		CommonTestRoutines.assertResultResponse(response);
		assertFilterMember(request.getCriteria(), response.getResultsList());
	}

	/** Test successful fetch of member by request, member pin specified in the criteria specified. */
	@Test
	public void testFetchMemberByRequestMemberPin()
	{
		MemberInquiryRequest request = new MemberInquiryRequest();
		Member member = insertMember(NUMBER_1050);
		Member memberCriteria = new Member();

		memberCriteria.setPinNumber(member.getPinNumber());
		request.getCriteria().setMember(memberCriteria);

		InternalResultsResponse<Member> response = getPersonDAC().fetchMemberByRequest(request);
		CommonTestRoutines.assertResultResponse(response);
		assertFilterMember(request.getCriteria(), response.getResultsList());
	}

	/** Test successful fetch of member by request, member first name specified in the criteria specified. */
	@Test
	public void testFetchMoneyTransferByRequestMemberFirstName()
	{
		MemberInquiryRequest request = new MemberInquiryRequest();
		Member member = insertMember();
		Member memberCriteria = new Member();

		memberCriteria.setFirstName(member.getFirstName());
		request.getCriteria().setMember(memberCriteria);

		InternalResultsResponse<Member> response = getPersonDAC().fetchMemberByRequest(request);
		CommonTestRoutines.assertResultResponse(response);
		assertFilterMember(request.getCriteria(), response.getResultsList());
	}

	/** Test successful fetch of member by request, member last name specified in the criteria specified. */
	@Test
	public void testFetchMoneyTransferByRequestMemberLastName()
	{
		MemberInquiryRequest request = new MemberInquiryRequest();
		Member member = insertMember();
		Member memberCriteria = new Member();

		memberCriteria.setLastName(member.getLastName());
		request.getCriteria().setMember(memberCriteria);

		InternalResultsResponse<Member> response = getPersonDAC().fetchMemberByRequest(request);
		CommonTestRoutines.assertResultResponse(response);
		assertFilterMember(request.getCriteria(), response.getResultsList());
	}

	/** Test successful fetch of member by request, locationName specified in the criteria specified. */
	@Test
	public void testFetchMoneyTransferByRequestLocationName()
	{
		MemberInquiryRequest request = new MemberInquiryRequest();
		Member member = insertMember();

		request.getCriteria().setLocationName(member.getEmploymentInfoList().get(0).getLocationName());

		InternalResultsResponse<Member> response = getPersonDAC().fetchMemberByRequest(request);
		CommonTestRoutines.assertResultResponse(response);
		assertFilterMember(request.getCriteria(), response.getResultsList());
	}

	/** Test successful fetch of member by request, organization name specified in the criteria specified. */
	@Test
	public void testFetchMemberByRequestOrganizationName()
	{
		MemberInquiryRequest request = new MemberInquiryRequest();
		Member member = insertMember();

		request.getCriteria().setOrganizationName(member.getEmploymentInfoList().get(0).getOrganizationName());

		InternalResultsResponse<Member> response = getPersonDAC().fetchMemberByRequest(request);
		CommonTestRoutines.assertResultResponse(response);
		assertFilterMember(request.getCriteria(), response.getResultsList());
	}

	/** Test successful fetch of member by request, primary phone number specified in the criteria specified. */
	@Test
	public void testFetchMoneyTransferByRequestPrimaryPhoneNumber()
	{
		MemberInquiryRequest request = new MemberInquiryRequest();
		Member member = insertMember();

		for (Contact contact : member.getContactList())
		{
			switch (contact.getContactType())
			{
				case PHONE_WORK:
					Phone phone = (Phone)contact;
					request.getCriteria().setPrimaryPhoneNumber(phone.getNumber());
					break;
				default:
					break;
			}
		}

		InternalResultsResponse<Member> response = getPersonDAC().fetchMemberByRequest(request);
		CommonTestRoutines.assertResultResponse(response);
		assertFilterMember(request.getCriteria(), response.getResultsList());
	}

	/**
	 * Assert filter member.
	 *
	 * @param criteria the criteria
	 * @param memberList the member list
	 */
	private void assertFilterMember(MemberCriteria criteria, List<Member> memberList)
	{
		Assert.assertTrue("Member Fetch contains 0", memberList.size() > 0);

		for (Member member : memberList)
		{
			if (!ValidationUtil.isNull(criteria.getMember()))
			{

				if (!ValidationUtil.isNull(criteria.getMember().getParticipantId()))
				{
					Assert.assertEquals("MoneyTransferBatch participantId", criteria.getMember().getParticipantId(),
							member.getParticipantId().trim());
				}
				if (!ValidationUtil.isNull(criteria.getMember().getFirstName()))
				{
					Assert.assertEquals("MoneyTransferBatch memberFirstName", criteria.getMember().getFirstName(),
							member.getFirstName());
				}
				if (!ValidationUtil.isNull(criteria.getMember().getLastName()))
				{
					Assert.assertEquals("MoneyTransferBatch memberLastName", criteria.getMember().getLastName(),
							member.getLastName());
				}
				if (!ValidationUtil.isNull(criteria.getMember().getPinNumber()))
				{
					Assert.assertTrue("The memberPinNumber should match with the criteria's pin number", criteria
							.getMember().getPinNumber().equals(member.getPinNumber()));
				}
			}

			if (!ValidationUtil.isNull(criteria.getLocationName()))
			{
				Boolean locationNameMatch = false;
				for (EmploymentInfo employmentInfo : member.getEmploymentInfoList())
				{
					if (employmentInfo.getLocationName().equals(criteria.getLocationName()))
					{
						locationNameMatch = true;
						break;
					}

				}
				Assert.assertTrue("Member location name not matched", locationNameMatch);
			}

			if (!ValidationUtil.isNull(criteria.getOrganizationName()))
			{
				Boolean organizationNameMatch = false;
				for (EmploymentInfo employmentInfo : member.getEmploymentInfoList())
				{
					if (employmentInfo.getOrganizationName().equals(criteria.getOrganizationName()))
					{
						organizationNameMatch = true;
						break;
					}

				}
				Assert.assertTrue("Member organization name not matched", organizationNameMatch);
			}

			if (!ValidationUtil.isNull(criteria.getPrimaryPhoneNumber()))
			{

				Boolean phoneMatch = false;

				for (Contact contact : member.getContactList())
				{
					switch (contact.getContactType())
					{
						case PHONE_WORK:
							Phone phone = (Phone)contact;
							if (phone.getNumber().equals(criteria.getPrimaryPhoneNumber()))
							{
								phoneMatch = true;
							}
							break;
						default:
							break;
					}
				}
				Assert.assertTrue("Member phone not matched", phoneMatch);
			}
		}
	}

	/**
	 * Test enrolled member count to organization.
	 */
	@Test
	public void testEnrolledMemberCountToOrganization()
	{
		Member member = insertMember();

		// get first page
		MemberInquiryRequest request = new MemberInquiryRequest();
		MemberCriteria criteria = new MemberCriteria();
		criteria.setBusinessId(member.getEmploymentInfoList().get(0).getOrganizationId());
		criteria.setBusinessType(BusinessTypeEnum.ORGANIZATION);
		request.setCriteria(criteria);

		InternalResultsResponse<Member> response = getPersonDAC().fetchMemberByRequest(request);
		CommonTestRoutines.assertResultResponse(response);

	}

	/**
	 * Test enrolled member count to location.
	 */
	@Test
	public void testEnrolledMemberCountToLocation()
	{
		Member member = insertMember();

		// get first page
		MemberInquiryRequest request = new MemberInquiryRequest();
		MemberCriteria criteria = new MemberCriteria();
		criteria.setBusinessId(member.getEmploymentInfoList().get(0).getLocationId());
		criteria.setBusinessType(BusinessTypeEnum.LOCATION);
		request.setCriteria(criteria);

		InternalResultsResponse<Member> response = getPersonDAC().fetchMemberByRequest(request);
		CommonTestRoutines.assertResultResponse(response);

	}

	/**
	 * Fetch employment info by member id.
	 */
	@Test
	public void testFetchEmploymentInfoByMemberId()
	{
		Member member = insertMember();

		new EmploymentInfo();

		InternalResultsResponse<EmploymentInfo> response =
				getPersonDAC().fetchEmploymentInfoByMemberId(member.getEmploymentInfoList().get(0));
		CommonTestRoutines.assertResultResponse(response);
	}

	/**
	 * Fetch employment info by location id.
	 */
	@Test
	public void testFetchEmploymentInfoByLocationId()
	{
		Member member = insertMember();
		Integer locationId = member.getEmploymentInfoList().get(0).getLocationId();

		InternalResultsResponse<EmploymentInfo> response = getPersonDAC().fetchEmploymentInfoByLocationId(locationId);

		CommonTestRoutines.assertResultResponse(response);

		Assert.assertTrue(response.getResultsList().size() == 1);
		Assert.assertEquals(locationId, response.getFirstResult().getLocationId());
	}

	/**
	 * Test delete Person (commom for Liaison/Member/Recipient).
	 */
	@Test
	public void testDeletePerson()
	{
		Liaison liaison = insertLiaison();

		InternalResponse deleteResponse = getPersonDAC().deletePerson(liaison);
		CommonTestRoutines.assertResponse(deleteResponse);

		InternalResultsResponse<Liaison> response =
				getPersonDAC().fetchLiaisonById(new FetchByIdRequest(liaison.getId()));

		Assert.assertEquals("Liaison cannot be found after delete", Status.NoRowsFoundError, response.getStatus());
	}

	/**
	 * Test apply member status.
	 */
	@Test
	public void testApplyMemberStatus()
	{
		Member member = insertMember();
		MemberMaintenanceRequest request = new MemberMaintenanceRequest();
		member.setPersonStatus(StatusEnum.INACTIVE);
		request.setMember(member);

		InternalResponse response = getPersonDAC().applyPersonStatus(member);
		CommonTestRoutines.assertResponse(response);

		InternalResultsResponse<Member> result = getPersonDAC().fetchMemberById(new FetchByIdRequest(member.getId()));
		CommonTestRoutines.assertResponse(result);

		Assert.assertEquals(THE_STATUS_PERSON_NEEDS_TO_BE_THE_SAME, member.getPersonStatus(), result.getFirstResult()
				.getPersonStatus());
	}

	/**
	 * Test apply recipient status.
	 */
	@Test
	public void testApplyRecipientStatus()
	{
		Recipient recipient = insertRecipient();
		RecipientMaintenanceRequest request = new RecipientMaintenanceRequest();
		recipient.setPersonStatus(StatusEnum.INACTIVE);
		request.setRecipient(recipient);

		InternalResponse response = getPersonDAC().applyPersonStatus(recipient);
		CommonTestRoutines.assertResponse(response);

		InternalResultsResponse<Recipient> result =
				getPersonDAC().fetchRecipientById(new FetchByIdRequest(recipient.getId()));
		CommonTestRoutines.assertResponse(result);

		Assert.assertEquals(THE_STATUS_PERSON_NEEDS_TO_BE_THE_SAME, recipient.getPersonStatus(), result
				.getFirstResult().getPersonStatus());
	}

	/**
	 * Test apply employment info status.
	 */
	@Test
	public void testApplyEmploymentInfoStatus()
	{
		Member member = insertMember();

		EmploymentInfo employmentInfo = member.getEmploymentInfoList().get(0);
		employmentInfo.setEmploymentInfoStatus(StatusEnum.INACTIVE);

		InternalResponse response = getPersonDAC().applyEmploymentInfoStatus(employmentInfo);
		CommonTestRoutines.assertResponse(response);

		InternalResultsResponse<Member> result = getPersonDAC().fetchMemberById(new FetchByIdRequest(member.getId()));
		CommonTestRoutines.assertResponse(result);

		Assert.assertEquals(THE_EMPLOYMENT_INFO_STATUS_SHOUD_BE_THE_SAME, employmentInfo.getEmploymentInfoStatus(),
				result.getFirstResult().getEmploymentInfoList().get(0)
						.getEmploymentInfoStatus());
	}

	/**
	 * Test apply transfer setting status.
	 */
	@Test
	public void testApplyTransferSettingStatus()
	{
		Member member = insertMember();
		addTransferSettingsToMember(member);

		TransferSetting transferSetting = member.getTransferSettingList().get(0);
		transferSetting.setStatus(StatusEnum.INACTIVE);

		InternalResponse response = getPersonDAC().applyTransferSettingStatus(transferSetting);
		CommonTestRoutines.assertResponse(response);

		InternalResultsResponse<Member> result = getPersonDAC().fetchMemberById(new FetchByIdRequest(member.getId()));
		CommonTestRoutines.assertResponse(result);

		Assert.assertEquals("The Transfer Setting status shoud be the same.", transferSetting.getStatus(),
				result.getFirstResult().getTransferSettingList().get(0).getStatus());
	}

	/**
	 * Test insert transfer setting to member.
	 */
	@Test
	public void testInsertTransferSettingToMember()
	{
		addTransferSettingsToMember(insertMember());
	}

	/**
	 * Test update transfer setting from member.
	 */
	@Test
	public void testUpdateTransferSettingFromMember()
	{
		Member member = insertMember();
		addTransferSettingsToMember(member);

		member.getTransferSettingList().get(0).getCustomFeeList().get(0).setModelAction(PersistanceActionEnum.UPDATE);
		member.getTransferSettingList().get(0).setModelAction(PersistanceActionEnum.UPDATE);
		member.getTransferSettingList().get(0).setTransferAmount(new BigDecimal("500.00"));

		InternalResultsResponse<Member> response = getPersonDAC().updateMember(member);
		CommonTestRoutines.assertResultResponse(response);

	}

	/**
	 * Test delete transfer setting from member.
	 */
	@Test
	public void testDeleteTransferSettingFromMember()
	{
		Member member = insertMember();
		addTransferSettingsToMember(member);

		member.getTransferSettingList().get(0).setModelAction(PersistanceActionEnum.DELETE);
		InternalResultsResponse<Member> response = getPersonDAC().updateMember(member);
		CommonTestRoutines.assertResultResponse(response);

	}

	/**
	 * Test delete custom fee from transfer setting.
	 */
	@Test
	public void testDeleteCustomFeeFromTransferSetting()
	{
		Member member = insertMember();
		addTransferSettingsToMember(member);

		member.getTransferSettingList().get(0).setModelAction(PersistanceActionEnum.UPDATE);
		member.getTransferSettingList().get(0).getCustomFeeList().get(0).setModelAction(PersistanceActionEnum.DELETE);
		InternalResultsResponse<Member> response = getPersonDAC().updateMember(member);
		CommonTestRoutines.assertResultResponse(response);

	}

	/**
	 * Test update note from transfer setting.
	 */
	@Test
	public void testUpdateNoteFromTransferSetting()
	{
		Member member = insertMember();
		addTransferSettingsToMember(member);

		member.getTransferSettingList().get(0).setModelAction(PersistanceActionEnum.UPDATE);
		member.getTransferSettingList().get(0).getNoteList().get(0).setModelAction(PersistanceActionEnum.UPDATE);
		member.getTransferSettingList().get(0).getNoteList().remove(1);
		member.getTransferSettingList().get(0).getNoteList().get(0).setNoteText(UPDATE_NOTE_TEXT);
		InternalResultsResponse<Member> response = getPersonDAC().updateMember(member);
		CommonTestRoutines.assertResultResponse(response);

		response = getPersonDAC().fetchMemberById(new FetchByIdRequest(member.getId()));

		Assert.assertEquals(UPDATE_NOTE_TEXT, response.getFirstResult().getTransferSettingList().get(0).getNoteList()
				.get(0).getNoteText());
	}

	/**
	 * Test delete note from transfer setting.
	 */
	@Test
	public void testDeleteNoteFromTransferSetting()
	{
		Member member = insertMember();
		addTransferSettingsToMember(member);

		member.getTransferSettingList().get(0).setModelAction(PersistanceActionEnum.UPDATE);
		member.getTransferSettingList().get(0).getNoteList().get(0).setModelAction(PersistanceActionEnum.DELETE);
		member.getTransferSettingList().get(0).getNoteList().get(1).setModelAction(PersistanceActionEnum.DELETE);
		InternalResultsResponse<Member> response = getPersonDAC().updateMember(member);
		CommonTestRoutines.assertResultResponse(response);

		response = getPersonDAC().fetchMemberById(new FetchByIdRequest(member.getId()));

		Assert.assertTrue("The List of note should be empty ", response.getFirstResult().getTransferSettingList()
				.get(0)
				.getNoteList().isEmpty());

	}

	/**
	 * **************************
	 * Private utility methods. *
	 * **************************
	 *
	 * @param numberOfLiaisonsToInsert the number of liaisons to insert
	 * @return the list< liaison>
	 */
	private List<Liaison> insertLiaisons(Integer numberOfLiaisonsToInsert)
	{
		ArrayList<Liaison> liaisonList = new ArrayList<Liaison>();

		// First, create a Liaison that will also create Organization
		Liaison liaison = insertLiaison();
		liaisonList.add(liaison);

		for (int i = 0; i < (numberOfLiaisonsToInsert - 1); i++)
		{
			Liaison newLiaison = insertLiaison(liaison.getLocationId());

			if (!ValidationUtil.isNull(newLiaison))
			{
				liaisonList.add(newLiaison);
			}
		}

		return liaisonList;
	}

	/**
	 * Insert recipients.
	 *
	 * @param numberOfRecipientsToInsert the number of recipients to insert
	 * @param uniqueIndexForRecipient Unique index for recipient.
	 * @return the list< recipient>
	 */
	private List<Recipient> insertRecipients(Integer numberOfRecipientsToInsert, boolean uniqueIndexForRecipient)
	{
		ArrayList<Recipient> recipientList = new ArrayList<Recipient>();

		for (int i = 0; i < numberOfRecipientsToInsert; i++)
		{
			Recipient recipient = null;
			if (uniqueIndexForRecipient)
			{
				recipient = insertRecipient(i + 1);
			}
			else
			{
				recipient = insertRecipient(0);
			}

			if (!ValidationUtil.isNull(recipient))
			{
				recipientList.add(recipient);
			}
		}

		return recipientList;
	}

	/**
	 * Insert members.
	 *
	 * @param numberOfMembersToInsert the number of members to insert
	 * @return the list< member>
	 */
	private List<Member> insertMembers(Integer numberOfMembersToInsert)
	{
		return insertMembers(numberOfMembersToInsert, true);
	}

	/**
	 * Insert recipient.
	 *
	 * @param numberOfRecipientsToInsert the number of recipients to insert
	 * @return the list< recipient>
	 */
	private List<Recipient> insertRecipients(Integer numberOfRecipientsToInsert)
	{
		return insertRecipients(numberOfRecipientsToInsert, true);
	}

	/**
	 * Insert members.
	 *
	 * @param numberOfMembersToInsert the number of members to insert
	 * @param uniqueIndexForMember Unique index for member.
	 * @return the list< member>
	 */
	private List<Member> insertMembers(Integer numberOfMembersToInsert, boolean uniqueIndexForMember)
	{
		ArrayList<Member> memberList = new ArrayList<Member>();

		for (int i = 0; i < numberOfMembersToInsert; i++)
		{
			Member member = null;
			if (uniqueIndexForMember)
			{
				member = insertMember(i + 1);
			}
			else
			{
				member = insertMember(0);
			}

			if (!ValidationUtil.isNull(member))
			{
				memberList.add(member);
			}
		}

		return memberList;
	}

	/**
	 * This method compares a passed Recipient with one read from the database (same id).
	 *
	 * @param recipient the recipient
	 */
	private void testRecipientAgainstDatabaseById(Recipient recipient)
	{
		InternalResultsResponse<Recipient> response =
				getPersonDAC().fetchRecipientById(new FetchByIdRequest(recipient.getId()));

		Assert.assertTrue(RESPONSE_GET_RESULTS_LIST_SIZE_NEEDS_TO_BE_0, response.getResultsList().size() > ZERO);
		Assert.assertNotNull(RESPONSE_GET_FIRST_RESULT_CANNOT_BE_NULL, response.getFirstResult());

		assertPersonFields(recipient, response.getFirstResult());
	}

	/**
	 * Test member against database by id.
	 *
	 * @param member the member
	 * @param modelAction the model action
	 */
	private void testMemberAgainstDatabaseById(Member member, PersistanceActionEnum modelAction)
	{
		InternalResultsResponse<Member> response =
				getPersonDAC().fetchMemberById(new FetchByIdRequest(member.getId()));

		Assert.assertTrue(RESPONSE_GET_RESULTS_LIST_SIZE_NEEDS_TO_BE_0, response.getResultsList().size() > 0);
		Assert.assertNotNull(RESPONSE_GET_FIRST_RESULT_CANNOT_BE_NULL, response.getFirstResult());

		assertMemberFields(member, response.getFirstResult(), modelAction);
	}

	/**
	 * This method compares a passed Liaison with one read from the database (same id).
	 *
	 * @param liaison the liaison
	 */
	private void testLiaisonAgainstDatabaseById(Liaison liaison)
	{
		InternalResultsResponse<Liaison> response =
				getPersonDAC().fetchLiaisonById(new FetchByIdRequest(liaison.getId()));

		Assert.assertTrue(RESPONSE_GET_RESULTS_LIST_SIZE_NEEDS_TO_BE_0, response.getResultsList().size() > 0);
		Assert.assertNotNull(RESPONSE_GET_FIRST_RESULT_CANNOT_BE_NULL, response.getFirstResult());

		Assert.assertEquals("Title needs to be the same", liaison.getTitle(), response.getFirstResult().getTitle());
		Assert.assertEquals("Location Id needs to be the same", liaison.getLocationId(), response.getFirstResult()
				.getLocationId());
		Assert.assertEquals("SSN needs to be the same", liaison.getDocumentList().get(0).getValue(),
				response.getFirstResult().getDocumentList().get(0).getValue());
		Assert.assertEquals("Liaison Type needs to be the same", liaison.getLiaisonTypeValue(), response
				.getFirstResult()
				.getLiaisonTypeValue());

		assertPersonFields(liaison, response.getFirstResult());
	}

	/**
	 * This method compares fields between two Person objects.
	 *
	 * @param person the person
	 * @param dbPerson the db person
	 */
	private void assertPersonFields(Person person, Person dbPerson)
	{
		Assert.assertEquals("Status need to be the same", person.getPersonStatus(), dbPerson.getPersonStatus());
		Assert.assertEquals("Date of Birth needs to be the same", person.getDateOfBirth(), dbPerson
				.getDateOfBirth());
		Assert.assertEquals("PEP status needs to be the same", person.getPepStatusValue(), dbPerson
				.getPepStatusValue());
		Assert.assertEquals("Number of Locations need to be the same", person.getPersonTypeValue(), dbPerson
				.getPersonTypeValue());
		Assert.assertEquals("RiskLevel needs to be the same", person.getRisk().getRiskLevel(), dbPerson
				.getRisk().getRiskLevel());
		Assert.assertEquals("RiskLevelNote needs to be the same", person.getRisk().getRiskLevelNote(), dbPerson
				.getRisk().getRiskLevelNote());
		Assert.assertEquals("SocialSecurityNumber needs to be the same", person.getSocialSecurityNumber(),
				dbPerson.getSocialSecurityNumber());

		Assert.assertNotNull("Contact List cannot be null", dbPerson.getContactList());
		Assert.assertTrue("Contact List needs to be > 0", dbPerson.getContactList().size() > 0);
		Assert.assertTrue("Contact List needs to be = 6 but is " + dbPerson.getContactList().size(),
				dbPerson.getContactList().size() == SIX);

		// This routine reads from DB based on ids and compare fields
		for (Contact contact : person.getContactList())
		{
			compareContactFields(contact);
		}

		Assert.assertNotNull("Name List cannot be null", dbPerson.getNameList());
		Assert.assertTrue("Name List needs to be > 0", dbPerson.getNameList().size() > 0);
		for (PersonName personName : person.getNameList())
		{
			compareNameFields(personName);
		}

		Assert.assertNotNull("Notes List cannot be null", dbPerson.getNoteList());
		Assert.assertTrue("Notes List needs to be > 0", dbPerson.getNoteList().size() > 0);
		for (Note note : person.getNoteList())
		{
			compareNoteFields(note);
		}
	}

	/**
	 * This method compares fields between two lists of CountryUsage objects.
	 *
	 * @param countryUsageList The country usage object.
	 * @param dbCountryUsageList The db country usage object.
	 * @param modelAction The last model action performed.
	 */
	private void assertCountryUsageListFields(List<CountryUsage> countryUsageList,
			List<CountryUsage> dbCountryUsageList, PersistanceActionEnum modelAction)
	{
		if (!ValidationUtil.isNullOrEmpty(countryUsageList))
		{
			Assert.assertNotNull("DB CountryUsage list cannot be null", dbCountryUsageList);
			Assert.assertEquals("DB CountryUsage list size different", countryUsageList.size(),
					dbCountryUsageList.size());

			for (CountryUsage countryUsage : countryUsageList)
			{
				boolean dbObjectFound = false;
				for (CountryUsage dbCountryUsage : dbCountryUsageList)
				{
					if ((countryUsage.getCountry().getCode().equals(dbCountryUsage.getCountry().getCode())) &&
							(countryUsage.getUsage() == dbCountryUsage.getUsage()) &&
							(countryUsage.getPersonId().equals(dbCountryUsage.getPersonId())))
					{
						dbObjectFound = true;
						assertCountryUsageFields(countryUsage, dbCountryUsage, modelAction);
					}
				}

				if (!dbObjectFound)
				{
					Assert.assertTrue("CountryUsage not found in the DB CountryUsage list: " + countryUsage.toString(),
							false);
				}
			}
		}
	}

	/**
	 * This method compares fields between two CountryUsage objects.
	 *
	 * @param countryUsage The country usage object.
	 * @param dbCountryUsage The db country usage object.
	 * @param modelAction The last model action performed.
	 */
	private void assertCountryUsageFields(CountryUsage countryUsage, CountryUsage dbCountryUsage,
			PersistanceActionEnum modelAction)
	{
		Assert.assertNotNull("DB CountryUsage id null", dbCountryUsage.getId());

		if (modelAction == PersistanceActionEnum.INSERT)
		{
			Assert.assertNotNull("DB CountryUsage create date null", dbCountryUsage.getCreateDateUTC());
			Assert.assertNotNull("DB CountryUsage create user null", dbCountryUsage.getCreateUser());
			Assert.assertEquals("DB CountryUsage version is not 0", dbCountryUsage.getVersion().intValue(), 0);
		}
		else if (modelAction == PersistanceActionEnum.UPDATE)
		{
			Assert.assertNotNull("DB CountryUsage modify date null", dbCountryUsage.getModifyDateUTC());
			Assert.assertNotNull("DB CountryUsage modify user null", dbCountryUsage.getModifyUser());
			Assert.assertEquals("DB CountryUsage version is not 1 more than before update", countryUsage.getVersion()
					.intValue() + 1, dbCountryUsage.getVersion().intValue());
		}
		else if (modelAction == PersistanceActionEnum.FETCH)
		{
			Assert.assertEquals("Id mismatch", countryUsage.getId().intValue(), dbCountryUsage.getId().intValue());
		}

		Assert.assertEquals("Country mismatch", countryUsage.getCountry().getCode(), dbCountryUsage.getCountry()
				.getCode());
		Assert.assertEquals("Country usage mismatch", countryUsage.getUsage(), dbCountryUsage.getUsage());
		Assert.assertEquals("Peron id mismatch", countryUsage.getPersonId().intValue(), dbCountryUsage.getPersonId()
				.intValue());
	}

	/**
	 * This method compares fields between two Member objects.
	 *
	 * @param member The member.
	 * @param dbMember The db member.
	 * @param modelAction The last model action performed.
	 */
	private void assertMemberFields(Member member, Member dbMember, PersistanceActionEnum modelAction)
	{
		assertCountryUsageListFields(member.getCountryUsageList(), dbMember.getCountryUsageList(), modelAction);
		assertPersonFields(member, dbMember);
	}

	/**
	 * Assert attribute sort.
	 *
	 * @param attribute1 the attribute1
	 * @param attribute2 the attribute2
	 * @param direction the direction
	 */
	private void assertAttributeSort(String attribute1, String attribute2, Direction direction)
	{
		if (Direction.Ascending == direction)
		{
			Assert.assertTrue("Asc Sort Incorrect! Attribute Name " + attribute1 + " is greater than Attribute "
					+ attribute2, attribute1.compareTo(attribute2) <= 0);
		}
		else
		{
			Assert.assertTrue("Desc Sort Incorrect! Attribute Name " + attribute1 + " is less than Attribute "
					+ attribute2, attribute1.compareTo(attribute2) >= 0);
		}
	}

	/**
	 * Asserts that the list of member objects is sorted correctly.
	 *
	 * @param memberList the member list
	 * @param fieldName The field name that was sorted.
	 * @param direction The direction of the sort.
	 */
	private void assertMemberSort(List<Member> memberList, String fieldName, Direction direction)
	{
		boolean firstTime = true;
		Member previousMember = null;
		for (Member member : memberList)
		{
			if (firstTime)
			{
				previousMember = member;
				firstTime = false;
			}
			else
			{
				if (fieldName.equals(FIRST_NAME_SORT_FIELD))
				{
					assertAttributeSort(previousMember.getFirstName(), member.getFirstName(), direction);
				}
				else if (fieldName.equals(LAST_NAME_SORT_FIELD))
				{
					assertAttributeSort(previousMember.getLastName(), member.getLastName(), direction);
				}
				else if (fieldName.equals(PARTICIPANT_ID_SORT_FIELD))
				{
					assertAttributeSort(previousMember.getParticipantId(), member.getParticipantId(), direction);
				}
				else if (fieldName.equals(PERSON_STATUS_SORT_FIELD))
				{
					assertAttributeSort(previousMember.getPersonStatus().getValue().toString(), member
							.getPersonStatus().getValue().toString(), direction);
				}
				else
				{
					Assert.assertTrue("Unknown member sort field: " + fieldName, false);
				}
			}
		}
	}

	/**
	 * Asserts that the list of recipient objects is sorted correctly.
	 *
	 * @param recipientList The sorted list of recipient objects.
	 * @param fieldName The field name that was sorted.
	 * @param direction The direction of the sort.
	 */
	private void assertRecipientSort(List<Recipient> recipientList, String fieldName, Direction direction)
	{
		boolean firstTime = true;
		Recipient previousRecipient = null;
		for (Recipient recipient : recipientList)
		{
			if (firstTime)
			{
				previousRecipient = recipient;
				firstTime = false;
			}
			else
			{
				if (fieldName.equals(FIRST_NAME_SORT_FIELD))
				{
					assertAttributeSort(previousRecipient.getFirstName(), recipient.getFirstName(), direction);
				}
				else if (fieldName.equals(LAST_NAME_SORT_FIELD))
				{
					assertAttributeSort(previousRecipient.getLastName(), recipient.getLastName(), direction);
				}
				else if (fieldName.equals(PARTICIPANT_ID_SORT_FIELD))
				{
					assertAttributeSort(previousRecipient.getParticipantId(), recipient.getParticipantId(), direction);
				}
				else if (fieldName.equals(PERSON_STATUS_SORT_FIELD))
				{
					assertAttributeSort(previousRecipient.getPersonStatus().getValue().toString(), recipient
							.getPersonStatus().getValue().toString(), direction);
				}
				else
				{
					Assert.assertTrue("Unknown recipient sort field: " + fieldName, false);
				}
			}
		}
	}

	/**
	 * Prepare the Sdn Checker BAC's Mock for Member/Recipient/Liaison in order to return the expected result.
	 *
	 * @param person The person.
	 */
	private void prepareMockSdnCheckerBAC(Person person)
	{
		SdnCheckerRequest<Person> request = new SdnCheckerRequest<Person>();
		request.setPersonOrBusiness(person);

		InternalResultsResponse<SdnStatusHistory> response = new InternalResultsResponse<SdnStatusHistory>();
		response.addResult(CommonSdnTestRoutines.createDummySdnStatusHistory(SDNStatusEnum.PENDING_INVESTIGATION));

		Mockito.when(getMockSdnCheckerBAC().fetchCurrentSdnStatusHistory(Matchers.any(SdnStatusHistoryRequest.class)))
				.thenReturn(response);

	}

	/**
	 * Assert if the Questions & Answers were inserted to the {@link Member}.
	 *
	 * @param member
	 */
	private void assertQuestions(Member member)
	{
		InternalResultsResponse<Member> response =
				getPersonDAC().fetchMemberById(new FetchByIdRequest(member.getId()));

		Assert.assertTrue("The result should have one question", response.getFirstResult().getSecurityAnswerList()
				.size() == 1);

		Assert.assertTrue("The first answer should be - Blue - ", response.getFirstResult()
				.getSecurityAnswerList().get(0).getAnswerText().equals(BLUE));

	}

}