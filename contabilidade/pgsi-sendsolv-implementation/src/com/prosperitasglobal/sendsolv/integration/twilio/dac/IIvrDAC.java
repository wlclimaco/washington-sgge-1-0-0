package com.prosperitasglobal.sendsolv.integration.twilio.dac;

import com.prosperitasglobal.sendsolv.integration.twilio.model.CallRecord;
import com.prosperitasglobal.sendsolv.integration.twilio.model.CallRecordContext;
import com.prosperitasglobal.sendsolv.integration.twilio.model.IvrIdentity;
import com.prosperitasglobal.sendsolv.integration.twilio.model.MoneyTransferSummary;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * The Interface ILocationDAC.
 */
public interface IIvrDAC
{
	public InternalResponse insertCallRecord(CallRecord callRecord);

	public InternalResponse updateCallRecord(CallRecord callRecord);

	public InternalResultsResponse<CallRecord> fetchCallRecordByPerson(Integer parentKey);

	public InternalResultsResponse<CallRecord> fetchCallRecordBySid(String sid);

	public Integer fetchPhoneBySid(String sid);

	public Integer fetchPersonIdByIvrIdentity(IvrIdentity ivrIdentity);

	public InternalResultsResponse<MoneyTransferSummary> fetchMoneyTransferSummaryByMember(
			MoneyTransferSummary moneyTransferSummary);

	public InternalResponse insertCallRecordContext(CallRecordContext callRecordContext);

	public InternalResponse deleteCallRecordContext(CallRecord callRecord);

	public InternalResultsResponse<CallRecordContext> fetchCallRecordContextByIvrOption(
			CallRecordContext callRecordContext);
}
