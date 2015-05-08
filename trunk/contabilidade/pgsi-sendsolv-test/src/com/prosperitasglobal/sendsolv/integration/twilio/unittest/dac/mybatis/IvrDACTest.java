package com.prosperitasglobal.sendsolv.integration.twilio.unittest.dac.mybatis;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import com.prosperitasglobal.cbof.unittest.util.AbstractTestBaseDAC;
import com.prosperitasglobal.sendsolv.integration.twilio.model.CallRecord;
import com.prosperitasglobal.sendsolv.integration.twilio.model.CallRecordContext;
import com.prosperitasglobal.sendsolv.model.Member;
import com.prosperitasglobal.sendsolv.model.MoneyTransfer;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResponse.Status;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * The Class CodeValueDACTest.
 */
public class IvrDACTest extends AbstractTestBaseDAC
{
	private static final String LANGUAGE = "en";
	private static final String PHONE = "+123";
	private static final String CALL_SID = "ABX";
	private static final Long START_DATE_TIME = 1L;
	private static final Long STOP_DATE_TIME = 2L;
	private static final Integer DURATION = 20;

	private static final Long STOP_DATE_TIME_UPDATED = 5L;
	private static final Integer DURATION_UPDATED = 200;
	private static final Long CURRENT_SEARCH_DATE = 3L;
	private static final Integer CURRENT_IVR_OPTION = 23;
	private static final String FIRST_NAME = "John";
	private static final String LAST_NAME = "Doe";

	@Test
	public void testInsertCallRecord()
	{
		InternalResponse response = new InternalResponse();

		CallRecord callRecord = insertCallRecord(response);

		assertNotNull("response is Null", response);

		// Make sure an id is generated
		assertNotNull("Id cannot be null", callRecord.getId());

		InternalResultsResponse<CallRecord> response2 =
				getIvrDACImpl().fetchCallRecordByPerson(callRecord.getParentPersonId());

		assertNotNull("Response2 is Null", response2);
		assertNotNull("List of CallRecords is Null", response2.getFirstResult());
		assertEquals("Should return 1 record", 1, response2.getResultsList().size());

		assertEquals("", CALL_SID, response2.getFirstResult().getCallSid());
		assertEquals("", LANGUAGE, response2.getFirstResult().getLanguage());
		assertEquals("", PHONE, response2.getFirstResult().getOriginatingPhone());
		assertEquals("", START_DATE_TIME, response2.getFirstResult().getStartDateTimeUTC());
		assertEquals("", STOP_DATE_TIME, response2.getFirstResult().getStopDateTimeUTC());
		assertEquals("", DURATION, response2.getFirstResult().getCallDurationSeconds());
		assertEquals("", CURRENT_SEARCH_DATE, response2.getFirstResult().getCurrentSearchDate());
		assertEquals("", CURRENT_IVR_OPTION, response2.getFirstResult().getCurrentIvrOption());

	}

	@Test
	public void testInsertCallRecordContext()
	{
		InternalResponse response = new InternalResponse();

		CallRecord callRecord = insertCallRecord(response);

		CallRecordContext callRecordContext = insertCallRecordContext(callRecord, 1, response);

		assertNotNull("response is Null when inserting CallRecordContext", response);

		InternalResultsResponse<CallRecord> response2 =
				getIvrDACImpl().fetchCallRecordByPerson(callRecord.getParentPersonId());

		assertNotNull("Response2 is Null", response2);
		assertNotNull("List of CallRecords is Null", response2.getFirstResult());
		assertEquals("Should return 1 CallRecordContext record", 1, response2.getFirstResult()
				.getCallRecordContextList().size());
	}

	@Test
	public void testFetchCallRecordContext()
	{
		InternalResponse response = new InternalResponse();

		CallRecord callRecord = insertCallRecord(response);

		CallRecordContext callRecordContext = insertCallRecordContext(callRecord, 1, response);

		assertNotNull("response is Null when inserting CallRecordContext", response);

		InternalResultsResponse<CallRecordContext> response2 =
				getIvrDACImpl().fetchCallRecordContextByIvrOption(callRecordContext);

		assertNotNull("Response2 is Null", response2);
		assertNotNull("List of CallRecordContext is Null", response2.getFirstResult());
		assertEquals("Should return 1 CallRecordContext record", 1, response2.getResultsList().size());

		assertEquals("Should return correct MoneyTransferId", callRecordContext.getMoneyTransferId(), response2
				.getFirstResult().getMoneyTransferId());
		assertEquals("Should return correct parentId", callRecordContext.getParentId(), response2.getFirstResult()
				.getParentId());
		assertEquals("Should return correct ivrOption", callRecordContext.getIvrOption(), response2.getFirstResult()
				.getIvrOption());
	}

	@Test
	public void testDeleteCallRecordContext()
	{
		InternalResponse response = new InternalResponse();

		CallRecord callRecord = insertCallRecord(response);

		CallRecordContext callRecordContext = insertCallRecordContext(callRecord, 1, response);
		callRecordContext = insertCallRecordContext(callRecord, 2, response);
		callRecordContext = insertCallRecordContext(callRecord, 3, response);

		assertNotNull("response is Null when inserting CallRecordContext", response);

		response = getIvrDACImpl().deleteCallRecordContext(callRecord);

		assertNotNull("response is Null when deleting CallRecordContext", response);

		InternalResultsResponse<CallRecord> response2 =
				getIvrDACImpl().fetchCallRecordByPerson(callRecord.getParentPersonId());

		assertNotNull("Response2 is Null", response2);
		assertNotNull("List of CallRecords is Null", response2.getFirstResult());
		assertEquals("Should return 1 CallRecordContext record", 0, response2.getFirstResult()
				.getCallRecordContextList().size());

		// Make sure CurrentSearchDate is set to Null
		assertNull("CurrentSearchDate is Not Null", response2.getFirstResult().getCurrentSearchDate());
	}

	@Test
	public void testUpdateCallRecord()
	{
		InternalResponse response = new InternalResponse();

		CallRecord callRecord = insertCallRecord(response);

		callRecord.setStopDateTimeUTC(STOP_DATE_TIME_UPDATED);
		callRecord.setCallDurationSeconds(DURATION_UPDATED);

		assertNotNull("response is Null", response);

		// Make sure an id is generated
		assertNotNull("Id cannot be null", callRecord.getId());

		response = getIvrDACImpl().updateCallRecord(callRecord);

		assertNotNull("response is Null", response);
		assertEquals("", Status.OperationSuccess, response.getStatus());

		InternalResultsResponse<CallRecord> response2 =
				getIvrDACImpl().fetchCallRecordByPerson(callRecord.getParentPersonId());

		assertNotNull("Response2 is Null", response2);
		assertNotNull("List of CallRecords is Null", response2.getFirstResult());
		assertEquals("Should return 1 record", 1, response2.getResultsList().size());

		assertEquals("", CALL_SID, response2.getFirstResult().getCallSid());
		assertEquals("", LANGUAGE, response2.getFirstResult().getLanguage());
		assertEquals("", PHONE, response2.getFirstResult().getOriginatingPhone());
		assertEquals("", START_DATE_TIME, response2.getFirstResult().getStartDateTimeUTC());
		assertEquals("", STOP_DATE_TIME_UPDATED, response2.getFirstResult().getStopDateTimeUTC());
		assertEquals("", DURATION_UPDATED, response2.getFirstResult().getCallDurationSeconds());

	}

	private CallRecordContext insertCallRecordContext(CallRecord callRecord, Integer ivrOption,
			InternalResponse response)
	{
		MoneyTransfer moneyTransfer = insertMoneyTransfer(1);

		CallRecordContext callRecordContext = new CallRecordContext();
		callRecordContext.setIvrOption(ivrOption);
		callRecordContext.setParentId(callRecord.getId());
		callRecordContext.setMoneyTransferId(moneyTransfer.getId());
		callRecordContext.setRecipientFirstName(FIRST_NAME);
		callRecordContext.setRecipientLastName(LAST_NAME);

		response = getIvrDACImpl().insertCallRecordContext(callRecordContext);

		return callRecordContext;
	}

	private CallRecord insertCallRecord(InternalResponse response)
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

		Member theMember = insertMember();

		callRecord.setParentPersonId(theMember.getId());

		response = getIvrDACImpl().insertCallRecord(callRecord);

		return callRecord;
	}
}
