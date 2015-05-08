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

import com.prosperitasglobal.cbof.model.BusinessTypeEnum;
import com.prosperitasglobal.cbof.model.request.FetchByIdRequest;
import com.prosperitasglobal.cbof.unittest.util.CommonTestRoutines;
import com.prosperitasglobal.sendsolv.bac.IMemberBAC;
import com.prosperitasglobal.sendsolv.bai.IRecipientBAI;
import com.prosperitasglobal.sendsolv.callingcard.bac.ICallingCardBAC;
import com.prosperitasglobal.sendsolv.callingcard.model.CallingCardInfo;
import com.prosperitasglobal.sendsolv.callingcard.model.request.CallingCardMaintenanceRequest;
import com.prosperitasglobal.sendsolv.dac.IParticipantIdDAC;
import com.prosperitasglobal.sendsolv.dac.IPersonDAC;
import com.prosperitasglobal.sendsolv.model.Document;
import com.prosperitasglobal.sendsolv.model.EmploymentInfo;
import com.prosperitasglobal.sendsolv.model.Member;
import com.prosperitasglobal.sendsolv.model.MemberParticipantId;
import com.prosperitasglobal.sendsolv.model.Recipient;
import com.prosperitasglobal.sendsolv.model.TransferSetting;
import com.prosperitasglobal.sendsolv.model.TransferSettingParticipantId;
import com.prosperitasglobal.sendsolv.model.request.DocumentMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.request.MemberInquiryRequest;
import com.prosperitasglobal.sendsolv.model.request.MemberMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.request.RecipientMaintenanceRequest;
import com.prosperitasglobal.sendsolv.model.response.RecipientResponse;
import com.prosperitasglobal.sendsolv.sdn.bac.ISdnCheckerBAC;
import com.prosperitasglobal.sendsolv.sdn.model.SdnStatusHistory;
import com.prosperitasglobal.sendsolv.sdn.model.request.SdnCheckerRequest;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResponse.Status;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * The Class MemberBACImplTest.
 */

@ContextConfiguration(locations = {
		"classpath:com/prosperitasglobal/sendsolv/unittest/conf/pgsi-sendsolv-resourcebundles-context.xml",
"classpath:com/prosperitasglobal/sendsolv/unittest/bac/conf/memberbacimpltest.xml"})
public class MemberBACImplTest extends AbstractJUnit4SpringContextTests
{

	/** The Constant ONE. */
	private static final Integer ONE = 1;

	/** The Constant 99999. */
	private static final Integer INT_99999 = 99999;

	/** The member bac. */
	private IMemberBAC memberBAC;

	/** The person dac. */
	private IPersonDAC personDAC;

	/** The participant id dac. */
	private IParticipantIdDAC participantIdDAC;

	/** The mock sdn checker bac. */
	private ISdnCheckerBAC mockSdnCheckerBAC;

	/** The recipient bai. */
	private IRecipientBAI recipientBAI;

	/** The calling card bac. */
	private ICallingCardBAC callingCardBAC;

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
	 * Gets the member bac.
	 *
	 * @return the member bac
	 */
	public IMemberBAC getMemberBAC()
	{
		return memberBAC;
	}

	/**
	 * Sets the member bac.
	 *
	 * @param memberBAC the member bac
	 */
	@Resource
	public void setMemberBAC(IMemberBAC memberBAC)
	{
		this.memberBAC = memberBAC;
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
	 * Gets the recipient bai.
	 *
	 * @return the recipientBAI
	 */
	public IRecipientBAI getRecipientBAI()
	{
		return recipientBAI;
	}

	/**
	 * Sets the recipient bai.
	 *
	 * @param recipientBAI the recipientBAI to set
	 */
	@Resource
	public void setRecipientBAI(IRecipientBAI recipientBAI)
	{
		this.recipientBAI = recipientBAI;
	}

	/**
	 * Gets the calling card bac.
	 *
	 * @return the callingCardBAC
	 */
	public ICallingCardBAC getCallingCardBAC()
	{
		return callingCardBAC;
	}

	/**
	 * Sets the calling card bac.
	 *
	 * @param callingCardBAC the callingCardBAC to set
	 */
	@Resource
	public void setCallingCardBAC(ICallingCardBAC callingCardBAC)
	{
		this.callingCardBAC = callingCardBAC;
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
		request.setMember(member);

		Mockito.when(getPersonDAC().insertMember(member)).thenReturn(
				new InternalResultsResponse<Member>(Arrays.asList(new Member())));

		Mockito.when(getParticipantIdDAC().fetchNextMemberParticipantId()).thenReturn(
				new InternalResultsResponse<MemberParticipantId>(Arrays.asList(new MemberParticipantId('A', 'A', 1,
						INT_99999))));
		mockCheckSdn();

		InternalResultsResponse<Member> response = getMemberBAC().insertMember(request);
		Assert.assertNotNull(member.getPinNumber());
		Assert.assertNotNull(member.getParticipantId());
		CommonTestRoutines.assertResultResponse(response);

		Mockito.verify(getPersonDAC()).insertMember(member);
		Mockito.verify(getParticipantIdDAC()).fetchNextMemberParticipantId();
	}

	/**
	 * Test insert member with transfer setting.
	 */
	@Test
	public void testInsertMemberWithTransferSetting()
	{
		MemberMaintenanceRequest request = new MemberMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		Member member = CommonTestRoutines.createDummyMember();
		request.setMember(member);

		TransferSetting transferSetting = CommonTestRoutines.createDummyTransferSetting();
		// set the key to null to test the generation of unique id
		transferSetting.setKey(null);
		transferSetting.setMemberId(member.getId());
		transferSetting.getEmploymentInfo().setId(member.getEmploymentInfoList().get(0).getId());
		member.setTransferSettingList(Arrays.asList(transferSetting));

		Mockito.when(getPersonDAC().insertMember(member)).thenReturn(
				new InternalResultsResponse<Member>(Arrays.asList(new Member())));

		Mockito.when(getParticipantIdDAC().fetchNextMemberParticipantId()).thenReturn(
				new InternalResultsResponse<MemberParticipantId>(Arrays.asList(new MemberParticipantId('A', 'A', 1,
						INT_99999))));

		Mockito.when(getParticipantIdDAC().fetchNextTransferSettingParticipantId()).thenReturn(
				new InternalResultsResponse<TransferSettingParticipantId>(Arrays
						.asList(new TransferSettingParticipantId('A',
								'A', 1,
								INT_99999))));

		mockCheckSdn();

		InternalResultsResponse<Member> response = getMemberBAC().insertMember(request);
		Assert.assertNotNull(member.getPinNumber());
		Assert.assertNotNull(member.getParticipantId());
		Assert.assertNotNull(member.getTransferSettingList().get(0).getKey());
		CommonTestRoutines.assertResultResponse(response);

		Mockito.verify(getPersonDAC()).insertMember(member);
		Mockito.verify(getParticipantIdDAC()).fetchNextMemberParticipantId();
		Mockito.verify(getParticipantIdDAC()).fetchNextTransferSettingParticipantId();
	}

	/**
	 * Test insert member error.
	 */
	@Test
	public void testInsertMemberError()
	{
		MemberMaintenanceRequest request = new MemberMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		Member member = CommonTestRoutines.createDummyMember();
		request.setMember(member);

		InternalResultsResponse<MemberParticipantId> response = new InternalResultsResponse<MemberParticipantId>();
		response.setStatus(Status.SystemError);
		Mockito.when(getParticipantIdDAC().fetchNextMemberParticipantId()).thenReturn(response);

		InternalResponse results = getMemberBAC().insertMember(request);

		Assert.assertEquals(Status.SystemError, results.getStatus());

		Mockito.verify(getParticipantIdDAC()).fetchNextMemberParticipantId();
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
		request.setMember(member);

		InternalResultsResponse<Member> response = new InternalResultsResponse<Member>();
		response.setStatus(Status.NoRowsInsertedError);

		Mockito.when(getPersonDAC().insertMember(member)).thenReturn(response);
		Mockito.when(getParticipantIdDAC().fetchNextMemberParticipantId()).thenReturn(
				new InternalResultsResponse<MemberParticipantId>(Arrays.asList(new MemberParticipantId('A', 'A', 1,
						INT_99999))));

		InternalResponse results = getMemberBAC().insertMember(request);

		Assert.assertEquals(Status.NoRowsInsertedError, results.getStatus());

		Mockito.verify(getPersonDAC()).insertMember(member);
		Mockito.verify(getParticipantIdDAC()).fetchNextMemberParticipantId();
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
		member.setId(ONE);
		request.setMember(member);

		Mockito.when(getPersonDAC().updateMember(member)).thenReturn(
				new InternalResultsResponse<Member>(Arrays.asList(new Member())));

		mockCheckSdn();

		InternalResultsResponse<Member> response = getMemberBAC().updateMember(request);

		CommonTestRoutines.assertResultResponse(response);

		Mockito.verify(getPersonDAC()).updateMember(member);
	}

	/**
	 * Test update member.
	 */
	@Test
	public void testUpdateMemberWithTransferSetting()
	{
		MemberMaintenanceRequest request = new MemberMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		Member member = CommonTestRoutines.createDummyMember();
		member.setId(ONE);
		request.setMember(member);

		TransferSetting transferSetting = CommonTestRoutines.createDummyTransferSetting();

		transferSetting.setMemberId(member.getId());
		transferSetting.getEmploymentInfo().setId(member.getEmploymentInfoList().get(0).getId());
		member.setTransferSettingList(Arrays.asList(transferSetting));

		Mockito.when(getPersonDAC().updateMember(member)).thenReturn(
				new InternalResultsResponse<Member>(Arrays.asList(new Member())));

		Mockito.when(getParticipantIdDAC().fetchNextTransferSettingParticipantId()).thenReturn(
				new InternalResultsResponse<TransferSettingParticipantId>(Arrays
						.asList(new TransferSettingParticipantId('A',
								'A', 1,
								INT_99999))));

		mockCheckSdn();

		InternalResultsResponse<Member> response = getMemberBAC().updateMember(request);

		CommonTestRoutines.assertResultResponse(response);

		Mockito.verify(getPersonDAC()).updateMember(member);
	}

	/**
	 * Test update member with a request for a new pin number.
	 */
	@Test
	public void testUpdateMemberNewPinNumber()
	{
		MemberMaintenanceRequest request = new MemberMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);

		Member member = CommonTestRoutines.createDummyMember();
		member.setId(ONE);
		member.setPinNumber(null);
		request.setMember(member);

		Mockito.when(getPersonDAC().updateMember(member)).thenReturn(
				new InternalResultsResponse<Member>(Arrays.asList(new Member())));

		InternalResultsResponse<Member> response = getMemberBAC().updateMember(request);

		CommonTestRoutines.assertResultResponse(response);
		Assert.assertNotNull(member.getPinNumber());

		Mockito.verify(getPersonDAC()).updateMember(member);
	}

	/**
	 * Test update liaison exception.
	 */
	@Test
	public void testUpdateLiaisonException()
	{
		MemberMaintenanceRequest request = new MemberMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);
		Member member = CommonTestRoutines.createDummyMember();
		request.setMember(member);

		InternalResultsResponse<Member> response = new InternalResultsResponse<Member>();
		response.setStatus(Status.NoRowsUpdatedError);

		// test for bad return
		Mockito.when(getPersonDAC().updateMember(member)).thenReturn(response);

		InternalResponse results = getMemberBAC().updateMember(request);

		Assert.assertEquals(Status.NoRowsUpdatedError, results.getStatus());

		Mockito.verify(getPersonDAC()).updateMember(member);
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
		request.setMember(member);

		Mockito.when(getPersonDAC().deletePerson(member)).thenReturn(new InternalResponse());

		InternalResponse response = getMemberBAC().deleteMember(request);

		CommonTestRoutines.assertResponse(response);

		Mockito.verify(getPersonDAC()).deletePerson(member);
	}

	/**
	 * Test delete member exception.
	 */
	@Test
	public void testDeleteMemberException()
	{
		MemberMaintenanceRequest request = new MemberMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);
		Member member = CommonTestRoutines.createDummyMember();
		request.setMember(member);

		// test for bad return
		Mockito.when(getPersonDAC().deletePerson(member)).thenReturn(
				new InternalResponse(Status.NoRowsRemovedError));

		InternalResponse results = getMemberBAC().deleteMember(request);
		Assert.assertEquals(Status.NoRowsRemovedError, results.getStatus());

		Mockito.verify(getPersonDAC()).deletePerson(member);
	}

	/**
	 * Test fetch member by request.
	 */
	@Test
	public void testFetchMemberByRequest()
	{
		MemberInquiryRequest request = new MemberInquiryRequest();

		Mockito.when(getPersonDAC().fetchMemberByRequest(request)).thenReturn(
				new InternalResultsResponse<Member>(
						Arrays.asList(new Member(), new Member(), new Member(), new Member())));

		InternalResultsResponse<Member> results = getMemberBAC().fetchMemberByRequest(request);

		CommonTestRoutines.assertResultResponse(results);

		Mockito.verify(getPersonDAC()).fetchMemberByRequest(request);
	}

	/**
	 * Test fetch member by id.
	 */
	@Test
	public void testFetchMemberById()
	{
		FetchByIdRequest request = new FetchByIdRequest(ONE);

		request.getId();

		Mockito.when(getPersonDAC().fetchMemberById(request)).thenReturn(
				new InternalResultsResponse<Member>(Arrays.asList(new Member())));

		InternalResultsResponse<Member> response = getMemberBAC().fetchMemberById(request);

		CommonTestRoutines.assertResultResponse(response);

		Mockito.verify(getPersonDAC()).fetchMemberById(request);
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
		request.setMember(member);

		Mockito.when(getPersonDAC().applyPersonStatus(member)).thenReturn(new InternalResponse());

		mockFetchRecipientById();
		mockApplyRecipientStatus();
		mockApplyTransferSettingStatus();
		mockApplyEmploymentInfoStatus();
		mockAssignCallingCard();

		InternalResponse response = getMemberBAC().applyStatus(request);

		CommonTestRoutines.assertResponse(response);

		Mockito.verify(getPersonDAC()).applyPersonStatus(member);
	}

	/**
	 * Test apply status exception.
	 */
	@Test
	public void testApplyStatusException()
	{
		MemberMaintenanceRequest request = new MemberMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);
		Member member = CommonTestRoutines.createDummyMember();
		request.setMember(member);

		// test for bad return
		Mockito.when(getPersonDAC().applyPersonStatus(member)).thenReturn(
				new InternalResponse(Status.NoRowsRemovedError));

		InternalResponse results = getMemberBAC().applyStatus(request);
		Assert.assertEquals(Status.NoRowsRemovedError, results.getStatus());

		Mockito.verify(getPersonDAC()).applyPersonStatus(member);
	}

	/**
	 * Test apply status exception fetch recipient by id failed.
	 */
	@Test
	public void testApplyStatusExceptionFetchRecipientByIdFailed()
	{
		MemberMaintenanceRequest request = new MemberMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);
		Member member = CommonTestRoutines.createDummyMember();
		request.setMember(member);

		mockAssignCallingCard();

		Mockito.when(getPersonDAC().applyPersonStatus(member)).thenReturn(new InternalResponse());

		InternalResultsResponse<Recipient> internalResultResponse = new InternalResultsResponse<Recipient>();
		internalResultResponse.setStatus(Status.ExceptionError);
		Mockito.when(getPersonDAC().fetchRecipientById(Matchers.any(FetchByIdRequest.class))).thenReturn(
				internalResultResponse);

		InternalResponse results = getMemberBAC().applyStatus(request);
		Assert.assertEquals(Status.ExceptionError, results.getStatus());

		Mockito.verify(getPersonDAC()).applyPersonStatus(member);
	}

	/**
	 * Test apply status exception apply transfer setting status failed.
	 */
	@Test
	public void testApplyStatusExceptionApplyTransferSettingStatusFailed()
	{
		MemberMaintenanceRequest request = new MemberMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);
		Member member = CommonTestRoutines.createDummyMember();
		request.setMember(member);

		mockFetchRecipientById();
		mockApplyRecipientStatus();
		mockApplyRecipientStatus();
		mockAssignCallingCard();

		Mockito.when(getPersonDAC().applyPersonStatus(member)).thenReturn(new InternalResponse());

		Mockito.when(getPersonDAC().applyTransferSettingStatus(Matchers.any(TransferSetting.class)))
		.thenReturn(
				new InternalResponse(Status.ExceptionError));

		InternalResponse results = getMemberBAC().applyStatus(request);
		Assert.assertEquals(Status.ExceptionError, results.getStatus());

		Mockito.verify(getPersonDAC()).applyPersonStatus(member);
	}

	/**
	 * Test apply status exception apply employment info status failed.
	 */
	@Test
	public void testApplyStatusExceptionApplyEmploymentInfoStatusFailed()
	{
		MemberMaintenanceRequest request = new MemberMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);
		Member member = CommonTestRoutines.createDummyMember();
		request.setMember(member);

		mockFetchRecipientById();
		mockApplyRecipientStatus();
		mockApplyTransferSettingStatus();
		mockAssignCallingCard();

		Mockito.when(getPersonDAC().applyPersonStatus(member)).thenReturn(new InternalResponse());

		Mockito.when(getPersonDAC().applyEmploymentInfoStatus(Matchers.any(EmploymentInfo.class)))
				.thenReturn(
						new InternalResponse(Status.ExceptionError));

		InternalResponse results = getMemberBAC().applyStatus(request);
		Assert.assertEquals(Status.ExceptionError, results.getStatus());

		Mockito.verify(getPersonDAC()).applyPersonStatus(member);
	}

	/**
	 * Test apply status exception assign calling card failed.
	 */
	@Test
	public void testApplyStatusExceptionAssignCallingCardFailed()
	{
		MemberMaintenanceRequest request = new MemberMaintenanceRequest();
		CommonTestRoutines.createDummyUserContext(request);
		Member member = CommonTestRoutines.createDummyMember();
		request.setMember(member);

		mockFetchRecipientById();
		mockApplyRecipientStatus();
		mockApplyTransferSettingStatus();
		mockApplyEmploymentInfoStatus();

		InternalResultsResponse<CallingCardInfo> response = new InternalResultsResponse<CallingCardInfo>();
		response.setStatus(Status.ExceptionError);

		Mockito.when(getCallingCardBAC().assignCard(Matchers.any(CallingCardMaintenanceRequest.class)))
				.thenReturn(response);

		Mockito.when(getPersonDAC().applyPersonStatus(member)).thenReturn(new InternalResponse());

		InternalResponse results = getMemberBAC().applyStatus(request);
		Assert.assertEquals(Status.ExceptionError, results.getStatus());

		Mockito.verify(getPersonDAC()).applyPersonStatus(member);
	}

	/**
	 * Test insert document.
	 */
	@Test
	public void testInsertDocument()
	{
		DocumentMaintenanceRequest request = new DocumentMaintenanceRequest();
		Document document = CommonTestRoutines.createDummyDocument(BusinessTypeEnum.MEMBER);
		request.setDocument(document);

		Mockito.when(getPersonDAC().insertPersonDocument(request)).thenReturn(
				new InternalResultsResponse<Document>(document));

		InternalResultsResponse<Document> response = getMemberBAC().insertDocument(request);

		CommonTestRoutines.assertResultResponse(response);

		Mockito.verify(getPersonDAC()).insertPersonDocument(request);
	}

	/**
	 * Test insert document exception.
	 */
	@Test
	public void testInsertDocumentException()
	{
		DocumentMaintenanceRequest request = new DocumentMaintenanceRequest();
		Document document = CommonTestRoutines.createDummyDocument(BusinessTypeEnum.MEMBER);
		request.setDocument(document);

		InternalResultsResponse<Document> internalResponse = new InternalResultsResponse<Document>();
		internalResponse.setStatus(Status.NoRowsInsertedError);

		Mockito.when(getPersonDAC().insertPersonDocument(request)).thenReturn(internalResponse);

		InternalResultsResponse<Document> response = getMemberBAC().insertDocument(request);

		Assert.assertEquals(Status.NoRowsInsertedError, response.getStatus());

		Mockito.verify(getPersonDAC()).insertPersonDocument(request);
	}

	/**
	 * Test delete document.
	 */
	@Test
	public void testDeleteDocument()
	{
		DocumentMaintenanceRequest request = new DocumentMaintenanceRequest();
		Document document = new Document();
		request.setDocument(document);

		Mockito.when(getPersonDAC().deletePersonDocument(request)).thenReturn(new InternalResponse());

		InternalResponse response = getMemberBAC().deleteDocument(request);

		CommonTestRoutines.assertResponse(response);

		Mockito.verify(getPersonDAC()).deletePersonDocument(request);
	}

	/**
	 * Test delete document exception.
	 */
	@Test
	public void testDeleteDocumentException()
	{
		DocumentMaintenanceRequest request = new DocumentMaintenanceRequest();
		Document document = new Document();
		request.setDocument(document);

		// test for bad return
		Mockito.when(getPersonDAC().deletePersonDocument(request)).thenReturn(
				new InternalResponse(Status.NoRowsRemovedError));

		InternalResponse results = getMemberBAC().deleteDocument(request);
		Assert.assertEquals(Status.NoRowsRemovedError, results.getStatus());

		Mockito.verify(getPersonDAC()).deletePersonDocument(request);
	}

	/**
	 * Mock check sdn.
	 */
	private void mockCheckSdn()
	{
		SdnCheckerRequest<Member> request = new SdnCheckerRequest<Member>();

		Member member = CommonTestRoutines.createDummyMember();
		request.setPersonOrBusiness(member);

		InternalResultsResponse<SdnStatusHistory> response = new InternalResultsResponse<SdnStatusHistory>();

		response.setStatus(Status.OperationSuccess);
		response.addResult(new SdnStatusHistory());

		Mockito.when(getMockSdnCheckerBAC().checkMemberSdn(request))
		.thenReturn(response);
	}

	/**
	 * Mock apply recipient status.
	 */
	private void mockApplyRecipientStatus()
	{
		RecipientResponse response = new RecipientResponse();
		response.setOperationSuccess(Status.OperationSuccess);

		Mockito.when(getRecipientBAI().applyStatus(Matchers.any(RecipientMaintenanceRequest.class)))
		.thenReturn(response);
	}

	/**
	 * Mock apply transfer setting status.
	 */
	private void mockApplyTransferSettingStatus()
	{
		InternalResponse internalResponse = new InternalResponse();
		internalResponse.setStatus(Status.OperationSuccess);

		Mockito.when(getPersonDAC().applyTransferSettingStatus(Matchers.any(TransferSetting.class)))
		.thenReturn(internalResponse);

	}

	/**
	 * Mock fetch recipient by id.
	 */
	private void mockFetchRecipientById()
	{
		Mockito.when(getPersonDAC().fetchRecipientById(Matchers.any(FetchByIdRequest.class))).thenReturn(
				new InternalResultsResponse<Recipient>(Arrays.asList(CommonTestRoutines.createDummyRecipient())));

	}

	/**
	 * Mock apply employment info status.
	 */
	private void mockApplyEmploymentInfoStatus()
	{
		InternalResponse internalResponse = new InternalResponse();
		internalResponse.setStatus(Status.OperationSuccess);

		Mockito.when(getPersonDAC().applyEmploymentInfoStatus(Matchers.any(EmploymentInfo.class)))
				.thenReturn(internalResponse);

	}

	/**
	 * Mock assign calling card.
	 */
	private void mockAssignCallingCard()
	{
		InternalResultsResponse<CallingCardInfo> response = new InternalResultsResponse<CallingCardInfo>();
		response.addResult(new CallingCardInfo());
		response.setStatus(Status.OperationSuccess);

		Mockito.when(getCallingCardBAC().assignCard(Matchers.any(CallingCardMaintenanceRequest.class)))
				.thenReturn(response);
	}
}
