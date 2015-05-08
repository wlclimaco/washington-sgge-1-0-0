package com.prosperitasglobal.sendsolv.sdn.unittest.dac.mybatis;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.annotation.Resource;

import junit.framework.Assert;

import org.junit.Test;

import com.prosperitasglobal.cbof.unittest.util.AbstractTestBaseDAC;
import com.prosperitasglobal.cbof.unittest.util.CommonTestRoutines;
import com.prosperitasglobal.sendsolv.model.Member;
import com.prosperitasglobal.sendsolv.sdn.dac.ISdnCheckerDAC;
import com.prosperitasglobal.sendsolv.sdn.model.ExtendedSdnEntry;
import com.prosperitasglobal.sendsolv.sdn.model.SDNStatusEnum;
import com.prosperitasglobal.sendsolv.sdn.model.SdnFieldEnum;
import com.prosperitasglobal.sendsolv.sdn.model.SdnHistory;
import com.prosperitasglobal.sendsolv.sdn.model.SdnMatch;
import com.prosperitasglobal.sendsolv.sdn.model.SdnMatchField;
import com.prosperitasglobal.sendsolv.sdn.model.SdnMatchRecord;
import com.prosperitasglobal.sendsolv.sdn.model.SdnMatchTypeEnum;
import com.prosperitasglobal.sendsolv.sdn.model.SdnStatusHistory;
import com.prosperitasglobal.sendsolv.sdn.model.request.SdnStatusHistoryInquiryRequest;
import com.prosperitasglobal.sendsolv.sdn.model.request.criteria.SdnStatusHistoryCriteria;
import com.prosperitasglobal.sendsolv.sdn.unittest.util.CommonSdnTestRoutines;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;

public class SdnCheckerDACImplTest extends AbstractTestBaseDAC
{
	@Resource
	private ISdnCheckerDAC sdnCheckerDACImpl;

	@Test
	public void testInsertSdnStatusHistory()
	{

		SdnMatch sdnMatch = new SdnMatch();
		sdnMatch.setMatchType(SdnMatchTypeEnum.INDIVIDUAL);

		Member member = insertMember();
		sdnMatch.setParentKey(member.getId());

		SdnStatusHistory sdnStatusHistory = CommonSdnTestRoutines.mockHistory(SDNStatusEnum.PENDING_INVESTIGATION);
		InternalResponse response =
				getSdnCheckerDACImpl().insertSdnStatusHistory(sdnStatusHistory, sdnMatch);

		assertNotNull("response is Null", response);

		// Make sure an id is generated
		assertNotNull("Id cannot be null", sdnStatusHistory.getId());

		InternalResultsResponse<SdnStatusHistory> response2 =
				getSdnCheckerDACImpl().fetchCurrentSdnStatusHistory(sdnMatch.getMatchType(), sdnMatch.getParentKey());

		assertNotNull("Response2 is Null", response2);
		assertNotNull("List of Matches is Null", response2.getFirstResult().getSdnMatchRecordList());
		assertEquals("Should return 1 history", 1, response2.getResultsList().size());

		assertEquals("Should return 2 matches", 2, response2.getFirstResult().getSdnMatchRecordList().size());

		assertEquals("Should return 2 fields for record 1", 2, response2.getFirstResult().getSdnMatchRecordList()
				.get(0).getSdnMatchFieldList().size());
		assertEquals("Should return 2 fields for record 2", 2, response2.getFirstResult().getSdnMatchRecordList()
				.get(1).getSdnMatchFieldList().size());

		assertMatchRecord(response2.getFirstResult().getSdnMatchRecordList());

	}

	private void assertMatchRecord(List<SdnMatchRecord> sdnMatchRecordList)
	{
		for (SdnMatchRecord sdnMatchRecord : sdnMatchRecordList)
		{
			assertTrue("Record should have at least one field", sdnMatchRecord.getSdnMatchFieldList().size() > 0);

			for (SdnMatchField sdnMatchField : sdnMatchRecord.getSdnMatchFieldList())
			{
				if (!SdnFieldEnum.FIRST_LAST_NAME.equals(sdnMatchField.getSdnField()) &&
						!SdnFieldEnum.FIRST_NAME.equals(sdnMatchField.getSdnField())
						&& !SdnFieldEnum.LAST_NAME.equals(sdnMatchField.getSdnField()))
				{
					assertTrue("Should return uid", sdnMatchField.getSdnUid() > 0);
				}
			}
		}
	}

	@Test
	public void testInsertSdnMatchRecord()
	{

		SdnMatch sdnMatch = new SdnMatch();
		sdnMatch.setMatchType(SdnMatchTypeEnum.INDIVIDUAL);

		Member member = insertMember();

		sdnMatch.setParentKey(member.getId());

		InternalResultsResponse<ExtendedSdnEntry> response = new InternalResultsResponse<ExtendedSdnEntry>();

		SdnStatusHistory sdnStatusHistory = CommonSdnTestRoutines.mockHistory(SDNStatusEnum.PENDING_INVESTIGATION);
		InternalResponse internalResponse =
				getSdnCheckerDACImpl().insertSdnStatusHistory(sdnStatusHistory, sdnMatch);

		SdnMatchRecord sdnMatchRecord = new SdnMatchRecord();
		sdnMatchRecord.setParentKey(sdnStatusHistory.getId());
		sdnMatchRecord.setXmlRecord("xpto");

		int insertCount = getSdnCheckerDACImpl().insertSdnMatchRecord(sdnMatchRecord, response);

		assertNotNull("response is Null", response);

		// Make sure an id is generated
		assertNotNull("Id cannot be null", sdnStatusHistory.getId());

		assertEquals("Must insert 1 record", 1, insertCount);
	}

	@Test
	public void testInsertSdnMatchField()
	{

		SdnMatch sdnMatch = new SdnMatch();
		sdnMatch.setMatchType(SdnMatchTypeEnum.INDIVIDUAL);

		Member member = insertMember();

		sdnMatch.setParentKey(member.getId());

		InternalResultsResponse<ExtendedSdnEntry> response = new InternalResultsResponse<ExtendedSdnEntry>();

		SdnStatusHistory sdnStatusHistory = CommonSdnTestRoutines.mockHistory(SDNStatusEnum.PENDING_INVESTIGATION);
		InternalResponse internalResponse =
				getSdnCheckerDACImpl().insertSdnStatusHistory(sdnStatusHistory, sdnMatch);
		assertNotNull("response is Null", internalResponse);

		SdnMatchRecord sdnMatchRecord = new SdnMatchRecord();
		sdnMatchRecord.setParentKey(sdnStatusHistory.getId());
		sdnMatchRecord.setXmlRecord("xpto");
		Integer insertCount = getSdnCheckerDACImpl().insertSdnMatchRecord(sdnMatchRecord, response);
		assertEquals("Must insert 1 record", new Integer(1), insertCount);

		SdnMatchField sdnMatchField = new SdnMatchField();
		sdnMatchField.setParentKey(sdnMatchRecord.getId());
		sdnMatchField.setMatchProximity(0.9d);
		sdnMatchField.setSdnField(SdnFieldEnum.FIRST_LAST_NAME);
		sdnMatchField.setSdnUid(1);
		sdnMatchField.setSdnValue("Sdnvalue");
		sdnMatchField.setSystemValue("systemValue");

		insertCount = getSdnCheckerDACImpl().insertSdnMatchField(sdnMatchField, response);

		assertNotNull("response is Null", response);

		// Make sure an id is generated
		assertNotNull("Id cannot be null", sdnStatusHistory.getId());

	}

	@Test
	public void testUpdateSdnStatusHistory()
	{

		SdnMatch sdnMatch = new SdnMatch();
		sdnMatch.setMatchType(SdnMatchTypeEnum.INDIVIDUAL);

		Member member = insertMember();
		sdnMatch.setParentKey(member.getId());

		SdnStatusHistory sdnStatusHistory = CommonSdnTestRoutines.mockHistory(SDNStatusEnum.PENDING_INVESTIGATION);
		InternalResponse response = getSdnCheckerDACImpl().insertSdnStatusHistory(sdnStatusHistory, sdnMatch);

		assertNotNull("response is Null", response);

		// Make sure an id is generated
		assertNotNull("Id cannot be null", sdnStatusHistory.getId());

		Integer parentHistory = 2356;
		sdnStatusHistory.setParentSdnStatusHistoryId(parentHistory);
		sdnStatusHistory.setSdnStatus(SDNStatusEnum.FALSE_POSITIVE);
		response = getSdnCheckerDACImpl().updateSdnHistory(sdnStatusHistory);

		InternalResultsResponse<SdnStatusHistory> response2 =
				getSdnCheckerDACImpl().fetchCurrentSdnStatusHistory(sdnMatch.getMatchType(), sdnMatch.getParentKey());

		assertNotNull("response is Null", response2);
		assertNotNull("Parent History Id cannot be null", response2.getFirstResult().getParentSdnStatusHistoryId());
		assertEquals("Parent History Id must be " + parentHistory, parentHistory, response2.getFirstResult()
				.getParentSdnStatusHistoryId());
		assertEquals("Status must be " + parentHistory, SDNStatusEnum.FALSE_POSITIVE, response2.getFirstResult()
				.getSdnStatus());
	}

	@Test
	public void testFetchSdnStatusHistoryByRequest()
	{
		SdnMatch sdnMatch = new SdnMatch();
		sdnMatch.setMatchType(SdnMatchTypeEnum.INDIVIDUAL);

		Member member = insertMember();
		sdnMatch.setParentKey(member.getId());

		SdnStatusHistory sdnStatusHistory = CommonSdnTestRoutines.mockHistory(SDNStatusEnum.PENDING_INVESTIGATION);
		InternalResponse response =
				getSdnCheckerDACImpl().insertSdnStatusHistory(sdnStatusHistory, sdnMatch);

		CommonTestRoutines.assertResponse(response);

		SdnStatusHistoryInquiryRequest request = new SdnStatusHistoryInquiryRequest();
		request.setCriteria(new SdnStatusHistoryCriteria());
		request.getCriteria().setMatchType(SdnMatchTypeEnum.INDIVIDUAL);

		InternalResultsResponse<SdnHistory> sdnHistoryResponse =
				getSdnCheckerDACImpl().fetchSdnStatusHistoryByRequest(request);
		CommonTestRoutines.assertResultResponse(sdnHistoryResponse);

		for (SdnHistory sdnHistory : sdnHistoryResponse.getResultsList())
		{
			if (sdnHistory.getParentKey() == member.getId())
			{
				Assert.assertTrue("The current status should be PENDING_INVESTIGATION", sdnHistory.getSdnStatus()
						.equals(SDNStatusEnum.PENDING_INVESTIGATION));
			}
		}
	}

	@Override
	public ISdnCheckerDAC getSdnCheckerDACImpl()
	{
		return sdnCheckerDACImpl;
	}

	@Override
	public void setSdnCheckerDACImpl(ISdnCheckerDAC sdnCheckerDACImpl)
	{
		this.sdnCheckerDACImpl = sdnCheckerDACImpl;
	}

}
