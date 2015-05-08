package com.prosperitasglobal.sendsolv.sdn.integrationtest.bac;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import com.prosperitasglobal.cbof.model.Address;
import com.prosperitasglobal.cbof.model.Contact;
import com.prosperitasglobal.cbof.model.ContactTypeEnum;
import com.prosperitasglobal.cbof.unittest.util.AbstractTestBaseDAC;
import com.prosperitasglobal.sendsolv.model.Document;
import com.prosperitasglobal.sendsolv.model.Member;
import com.prosperitasglobal.sendsolv.sdn.bac.impl.SdnCheckerBACImpl;
import com.prosperitasglobal.sendsolv.sdn.dac.ISdnCheckerDAC;
import com.prosperitasglobal.sendsolv.sdn.model.SDNStatusEnum;
import com.prosperitasglobal.sendsolv.sdn.model.SdnMatch;
import com.prosperitasglobal.sendsolv.sdn.model.SdnMatchTypeEnum;
import com.prosperitasglobal.sendsolv.sdn.model.SdnStatusHistory;
import com.prosperitasglobal.sendsolv.sdn.model.request.SdnCheckerRequest;
import com.prosperitasglobal.sendsolv.sdn.model.request.SdnStatusHistoryRequest;
import com.prosperitasglobal.sendsolv.sdn.unittest.util.CommonSdnTestRoutines;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResponse.Status;
import com.qat.framework.model.response.InternalResultsResponse;

@ContextConfiguration(locations = {
"classpath:com/prosperitasglobal/sendsolv/sdn/unittest/bac/conf/sdnbacimplWithPersisttest.xml"})
public class SdnCheckerBACImplPersistenceTest extends AbstractTestBaseDAC
{

	@Resource
	private ISdnCheckerDAC realSdnCheckerDAC;

	@Resource
	private SdnCheckerBACImpl sdnCheckerBAC;

	@Test
	public void testFetchCurrentSdnStatusHistory()
	{
		Member member = insertMember();

		SdnMatch sdnMatch = new SdnMatch();

		sdnMatch.setParentKey(member.getId());

		SdnStatusHistoryRequest sdnStatusHistoryRequest = new SdnStatusHistoryRequest();
		sdnStatusHistoryRequest.setMatchType(SdnMatchTypeEnum.INDIVIDUAL);

		SdnStatusHistory sdnStatusHistory = new SdnStatusHistory();
		sdnStatusHistory.setParentKey(member.getId());

		sdnStatusHistoryRequest.setSdnStatusHistory(sdnStatusHistory);

		InternalResultsResponse<SdnStatusHistory> response =
				getSdnCheckerBAC().fetchCurrentSdnStatusHistory(sdnStatusHistoryRequest);

		assertNotNull(response);

		assertEquals("Status should be Neutral", SDNStatusEnum.NEUTRAL, response.getFirstResult().getSdnStatus());
	}

	// Test NO MATCH against NO HISTORY
	@Test
	public void testSdnWithNewMember()
	{
		Member member = insertMember();

		SdnCheckerRequest<Member> request = new SdnCheckerRequest<Member>();
		request.setPersonOrBusiness(member);

		InternalResultsResponse<SdnStatusHistory> response = getSdnCheckerBAC().checkMemberSdn(request);
		assertNotNull(response);
		assertEquals("Status should be Neutral", SDNStatusEnum.NEUTRAL, response.getFirstResult().getSdnStatus());

		response =
				getSdnCheckerBAC().fetchCurrentSdnStatusHistory(
						createSdnStatusHistoryRequest(SdnMatchTypeEnum.INDIVIDUAL, member.getId()));
		assertNotNull(response);
		assertEquals("Status should be Neutral", SDNStatusEnum.NEUTRAL, response.getFirstResult().getSdnStatus());
	}

	// Test NO MATCH against HISTORY
	@Test
	public void testAllOptionsNoMatchWithHistory()
	{
		testNoMatchWithHistory(SDNStatusEnum.NEUTRAL, SDNStatusEnum.NEUTRAL);
		testNoMatchWithHistory(SDNStatusEnum.PENDING_INVESTIGATION, SDNStatusEnum.PENDING_NEUTRAL);
		testMatchWithHistory(SDNStatusEnum.PENDING_FEDERAL_INVESTIGATION, SDNStatusEnum.PENDING_NEUTRAL);
		testMatchWithHistory(SDNStatusEnum.POSITIVE, SDNStatusEnum.PENDING_NEUTRAL);
		testMatchWithHistory(SDNStatusEnum.FALSE_POSITIVE, SDNStatusEnum.NEUTRAL);
		testNoMatchWithHistory(SDNStatusEnum.PENDING_NEUTRAL, SDNStatusEnum.PENDING_NEUTRAL);
	}

	// Test MATCH against HISTORY
	@Test
	public void testAllOptionsMatchWithHistory()
	{
		// Change threshold temporarily to generate matches
		getSdnCheckerBAC().setSimilarityThreshold(0.85d);

		testMatchWithHistory(SDNStatusEnum.NEUTRAL, SDNStatusEnum.PENDING_INVESTIGATION);
		testMatchWithHistory(SDNStatusEnum.PENDING_INVESTIGATION, SDNStatusEnum.PENDING_INVESTIGATION);
		testMatchWithHistory(SDNStatusEnum.PENDING_FEDERAL_INVESTIGATION, SDNStatusEnum.PENDING_INVESTIGATION);
		testMatchWithHistory(SDNStatusEnum.POSITIVE, SDNStatusEnum.PENDING_INVESTIGATION);
		testMatchWithHistory(SDNStatusEnum.FALSE_POSITIVE, SDNStatusEnum.PENDING_INVESTIGATION);
		testMatchWithHistory(SDNStatusEnum.PENDING_NEUTRAL, SDNStatusEnum.PENDING_INVESTIGATION);

		// Restore threshold
		getSdnCheckerBAC().setSimilarityThreshold(0.9d);
	}

	// Test MATCH against NO HISTORY
	@Test
	public void testMatchWithNoHistory()
	{
		Member member = insertMember();

		// Change threshold temporarily to generate matches
		getSdnCheckerBAC().setSimilarityThreshold(0.85d);

		SdnCheckerRequest<Member> request = new SdnCheckerRequest<Member>();

		member.setFirstName("Mary");
		member.setLastName("Abdelnur");
		for (Contact contact : member.getContactList())
		{
			if (ContactTypeEnum.POSTAL_WORK.equals(contact.getContactType()))
			{
				((Address)contact).getCountry().setDescription("Panama");
			}
		}
		member.setMiddleName("");
		member.getNameList().clear();
		member.setDocumentList(new ArrayList<Document>());
		member.setDateOfBirth(null);

		request.setPersonOrBusiness(member);

		InternalResultsResponse<SdnStatusHistory> response = getSdnCheckerBAC().checkMemberSdn(request);
		assertNotNull(response);
		assertEquals("Status should be " + SDNStatusEnum.PENDING_INVESTIGATION, SDNStatusEnum.PENDING_INVESTIGATION,
				response.getFirstResult().getSdnStatus());

		response =
				getSdnCheckerBAC().fetchCurrentSdnStatusHistory(
						createSdnStatusHistoryRequest(SdnMatchTypeEnum.INDIVIDUAL, member.getId()));
		assertNotNull(response);
		assertEquals("Status should be " + SDNStatusEnum.PENDING_INVESTIGATION, SDNStatusEnum.PENDING_INVESTIGATION,
				response.getFirstResult().getSdnStatus());
		assertEquals("Should return 1 history", 1, response.getResultsList().size());
		assertEquals("Should return 3 matches", 3, response.getFirstResult().getSdnMatchRecordList().size());

		for (int i = 0; i < response.getFirstResult().getSdnMatchRecordList().size(); i++)
		{
			assertTrue("Should return at least one field", response.getFirstResult().getSdnMatchRecordList().get(i)
					.getSdnMatchFieldList().size() > 0);
		}

		// Restore threshold
		getSdnCheckerBAC().setSimilarityThreshold(0.9d);
	}

	@Test
	public void testUpdateHistory()
	{
		Member member = insertMember();

		SdnCheckerRequest<Member> request = new SdnCheckerRequest<Member>();

		SdnMatch sdnMatch = new SdnMatch();
		sdnMatch.setMatchType(SdnMatchTypeEnum.INDIVIDUAL);
		sdnMatch.setParentKey(member.getId());
		sdnMatch.setFirstName("Mary");
		sdnMatch.setLastName("Abdelnur");
		sdnMatch.setAddressCountryName("Panama");

		member.setFirstName("Mary");
		member.setLastName("Abdelnur");
		for (Contact contact : member.getContactList())
		{
			if (ContactTypeEnum.POSTAL_WORK.equals(contact.getContactType()))
			{
				((Address)contact).getCountry().setDescription("Panama");
			}
		}

		request.setPersonOrBusiness(member);

		SDNStatusEnum existing = SDNStatusEnum.PENDING_INVESTIGATION;
		// Insert History
		SdnStatusHistory sdnStatusHistory = CommonSdnTestRoutines.mockHistory(existing);
		InternalResponse internalResponse =
				getSdnCheckerDACImpl().insertSdnStatusHistory(sdnStatusHistory, sdnMatch);
		assertNotNull(internalResponse);
		assertEquals("Operation should not fail", Status.OperationSuccess, internalResponse.getStatus());

		SdnStatusHistoryRequest updateRequest = new SdnStatusHistoryRequest();

		sdnStatusHistory.setNoteText("Ok now");
		sdnStatusHistory.setSdnStatus(SDNStatusEnum.FALSE_POSITIVE);

		updateRequest.setSdnStatusHistory(sdnStatusHistory);
		updateRequest.setMatchType(SdnMatchTypeEnum.INDIVIDUAL);

		internalResponse = getSdnCheckerBAC().updateSdnStatusHistory(updateRequest);

		SdnStatusHistoryRequest sdnStatusHistoryRequest = new SdnStatusHistoryRequest();
		sdnStatusHistoryRequest.setMatchType(SdnMatchTypeEnum.INDIVIDUAL);

		sdnStatusHistory = new SdnStatusHistory();
		sdnStatusHistory.setParentKey(member.getId());

		sdnStatusHistoryRequest.setSdnStatusHistory(sdnStatusHistory);

		InternalResultsResponse<SdnStatusHistory> response =
				getSdnCheckerBAC().fetchCurrentSdnStatusHistory(sdnStatusHistoryRequest);

		assertNotNull(response);
		assertNull("Should not have parent", response.getFirstResult().getParentSdnStatusHistoryId());
		assertEquals("Status should be FALSE_POSITIVE", SDNStatusEnum.FALSE_POSITIVE, response.getFirstResult()
				.getSdnStatus());

		response =
				getSdnCheckerBAC().fetchFullSdnStatusHistory(sdnStatusHistoryRequest);

		assertNotNull(response);

		assertEquals("Should have two entries", 2, response.getResultsList().size());

	}

	private void testNoMatchWithHistory(SDNStatusEnum existing, SDNStatusEnum result)
	{
		Member member = insertMember();

		SdnMatch sdnMatch = new SdnMatch();
		sdnMatch.setMatchType(SdnMatchTypeEnum.INDIVIDUAL);
		sdnMatch.setParentKey(member.getId());
		sdnMatch.setFirstName(member.getFirstName());
		sdnMatch.setLastName(member.getLastName());

		// Insert History
		SdnStatusHistory sdnStatusHistory = CommonSdnTestRoutines.mockHistory(existing);
		InternalResponse internalResponse =
				getSdnCheckerDACImpl().insertSdnStatusHistory(sdnStatusHistory, sdnMatch);
		assertNotNull(internalResponse);
		assertEquals("Operation should not fail", Status.OperationSuccess, internalResponse.getStatus());

		SdnCheckerRequest<Member> request = new SdnCheckerRequest<Member>();
		request.setPersonOrBusiness(member);

		// It will return no match
		InternalResultsResponse<SdnStatusHistory> response = getSdnCheckerBAC().checkMemberSdn(request);
		assertNotNull(response);
		assertEquals("Status should be " + result, result, response.getFirstResult()
				.getSdnStatus());

		response =
				getSdnCheckerBAC().fetchCurrentSdnStatusHistory(
						createSdnStatusHistoryRequest(SdnMatchTypeEnum.INDIVIDUAL, member.getId()));
		assertNotNull(response);
		assertEquals("Status should be " + result, result, response.getFirstResult()
				.getSdnStatus());
	}

	private void testMatchWithHistory(SDNStatusEnum existing, SDNStatusEnum result)
	{
		Member member = insertMember();

		SdnCheckerRequest<Member> request = new SdnCheckerRequest<Member>();

		SdnMatch sdnMatch = new SdnMatch();
		sdnMatch.setMatchType(SdnMatchTypeEnum.INDIVIDUAL);
		sdnMatch.setParentKey(member.getId());
		sdnMatch.setFirstName("Mary");
		sdnMatch.setLastName("Abdelnur");
		sdnMatch.setAddressCountryName("Panama");

		member.setFirstName("Mary");
		member.setLastName("Abdelnur");
		for (Contact contact : member.getContactList())
		{
			if (ContactTypeEnum.POSTAL_WORK.equals(contact.getContactType()))
			{
				((Address)contact).getCountry().setDescription("Panama");
			}
		}

		request.setPersonOrBusiness(member);

		// Insert History
		SdnStatusHistory sdnStatusHistory = CommonSdnTestRoutines.mockHistory(existing);
		InternalResponse internalResponse =
				getSdnCheckerDACImpl().insertSdnStatusHistory(sdnStatusHistory, sdnMatch);
		assertNotNull(internalResponse);
		assertEquals("Operation should not fail", Status.OperationSuccess, internalResponse.getStatus());

		// It will return matches
		InternalResultsResponse<SdnStatusHistory> response = getSdnCheckerBAC().checkMemberSdn(request);
		assertNotNull(response);
		assertEquals("Status should be " + result, result, response.getFirstResult()
				.getSdnStatus());

		response =
				getSdnCheckerBAC().fetchCurrentSdnStatusHistory(
						createSdnStatusHistoryRequest(SdnMatchTypeEnum.INDIVIDUAL, member.getId()));
		assertNotNull(response);
		assertEquals("Status should be " + result, result, response.getFirstResult()
				.getSdnStatus());
	}

	private SdnStatusHistoryRequest createSdnStatusHistoryRequest(SdnMatchTypeEnum sdnMatchTypeEnum, Integer parentKey)
	{
		SdnStatusHistoryRequest sdnStatusHistoryRequest = new SdnStatusHistoryRequest();
		sdnStatusHistoryRequest.setMatchType(sdnMatchTypeEnum);
		SdnStatusHistory sdnStatusHistory = new SdnStatusHistory();
		sdnStatusHistory.setParentKey(parentKey);
		sdnStatusHistoryRequest.setSdnStatusHistory(sdnStatusHistory);

		return sdnStatusHistoryRequest;
	}

	public SdnCheckerBACImpl getSdnCheckerBAC()
	{
		return sdnCheckerBAC;
	}

	public void setSdnCheckerBAC(SdnCheckerBACImpl sdnValidator)
	{
		sdnCheckerBAC = sdnValidator;
	}

	public ISdnCheckerDAC getRealSdnCheckerDAC()
	{
		return realSdnCheckerDAC;
	}

	public void setRealSdnCheckerDAC(ISdnCheckerDAC realSdnCheckerDAC)
	{
		this.realSdnCheckerDAC = realSdnCheckerDAC;
	}
}
