package com.prosperitasglobal.sendsolv.integration.twilio.dac.mybatis;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.prosperitasglobal.sendsolv.integration.twilio.dac.IIvrDAC;
import com.prosperitasglobal.sendsolv.integration.twilio.model.CallRecord;
import com.prosperitasglobal.sendsolv.integration.twilio.model.CallRecordContext;
import com.prosperitasglobal.sendsolv.integration.twilio.model.IvrIdentity;
import com.prosperitasglobal.sendsolv.integration.twilio.model.MoneyTransferSummary;
import com.qat.framework.model.QATModel;
import com.qat.framework.model.QATModel.PersistanceActionEnum;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.QATMyBatisDacHelper;

public class IvrDACImpl extends SqlSessionDaoSupport implements IIvrDAC
{
	private static final String SYSTEM_USER = "SendSolv";

	/** The Constant LOCATION_NAMESPACE. */
	private static final String CALL_RECORD_NAMESPACE = "CallRecordMap.";

	private static final String CALL_RECORD_STMT_INSERT = CALL_RECORD_NAMESPACE
			+ "insertCallRecord";

	private static final String CALL_RECORD_STMT_UPDATE = CALL_RECORD_NAMESPACE
			+ "updateCallRecord";

	private static final String CALL_RECORD_FETCH_FULL_HISTORY_FOR_PERSON = CALL_RECORD_NAMESPACE
			+ "fetchFullCallRecordHistoryByPerson";

	private static final String CALL_RECORD_FETCH_CALL_BY_SID = CALL_RECORD_NAMESPACE
			+ "fetchCallBySid";

	private static final String CALL_RECORD_FETCH_PHONE_BY_SID = CALL_RECORD_NAMESPACE
			+ "fetchPhoneBySid";

	private static final String CALL_RECORD_FETCH_PERSON_ID_BY_IVR_IDENTITY = CALL_RECORD_NAMESPACE
			+ "fetchPersonIdByIvrIdentity";

	private static final String CALL_RECORD_FETCH_MONEY_TRANSFER_SUMMARY_BY_MEMBER = CALL_RECORD_NAMESPACE
			+ "fetchMoneyTransferSummaryByMember";

	private static final String CALL_RECORD_CONTEXT_STMT_INSERT = CALL_RECORD_NAMESPACE
			+ "insertCallRecordContext";

	private static final String CALL_RECORD_CONTEXT_STMT_DELETE = CALL_RECORD_NAMESPACE
			+ "deleteCallRecordContext";

	private static final String CALL_RECORD_CONTEXT_STMT_FETCH_BY_IVR_OPTION = CALL_RECORD_NAMESPACE
			+ "fetchCallRecordContextByIvrOption";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(IvrDACImpl.class);

	@Override
	public InternalResponse insertCallRecord(CallRecord callRecord)
	{
		Integer insertCount = 0;

		InternalResponse response = new InternalResponse();

		callRecord.setModelAction(PersistanceActionEnum.INSERT);
		setChangeControlField(callRecord);

		// First insert the root status data
		insertCount =
				QATMyBatisDacHelper.doInsert(getSqlSession(), CALL_RECORD_STMT_INSERT, callRecord, response);

		if (!response.isInError() && (insertCount == 1))
		{
			callRecord.setModelAction(PersistanceActionEnum.NONE);
		}

		return response;
	}

	@Override
	public InternalResponse insertCallRecordContext(CallRecordContext callRecordContext)
	{
		Integer insertCount = 0;

		InternalResponse response = new InternalResponse();

		callRecordContext.setModelAction(PersistanceActionEnum.INSERT);
		setChangeControlField(callRecordContext);

		insertCount =
				QATMyBatisDacHelper.doInsert(getSqlSession(), CALL_RECORD_CONTEXT_STMT_INSERT, callRecordContext,
						response);

		if (!response.isInError() && (insertCount == 1))
		{
			callRecordContext.setModelAction(PersistanceActionEnum.NONE);
		}

		return response;
	}

	@Override
	public InternalResponse deleteCallRecordContext(CallRecord callRecord)
	{
		Integer deleteCount = 0;
		Integer updateCount = 0;

		InternalResponse response = new InternalResponse();

		deleteCount =
				QATMyBatisDacHelper.doRemove(getSqlSession(), CALL_RECORD_CONTEXT_STMT_DELETE, callRecord,
						response);

		if (!response.isInError() && (deleteCount > 0))
		{
			// Clear the date from callRecord
			callRecord.setCurrentSearchDate(null);
			updateCount = QATMyBatisDacHelper.doUpdate(getSqlSession(), CALL_RECORD_STMT_UPDATE, callRecord,
					response);

			if (!response.isInError() && (updateCount == 1))
			{
				callRecord.setModelAction(PersistanceActionEnum.NONE);
			}
		}

		return response;
	}

	@Override
	public InternalResponse updateCallRecord(CallRecord callRecord)
	{
		Integer updateCount = 0;

		InternalResponse response = new InternalResponse();

		callRecord.setModelAction(PersistanceActionEnum.UPDATE);
		setChangeControlField(callRecord);

		updateCount = QATMyBatisDacHelper.doUpdate(getSqlSession(), CALL_RECORD_STMT_UPDATE, callRecord,
				response);

		if (!response.isInError() && (updateCount == 1))
		{
			callRecord.setModelAction(PersistanceActionEnum.NONE);
		}

		return response;
	}

	@Override
	public InternalResultsResponse<CallRecord> fetchCallRecordByPerson(Integer parentKey)
	{
		InternalResultsResponse<CallRecord> response = new InternalResultsResponse<CallRecord>();

		QATMyBatisDacHelper.doQueryForList(getSqlSession(), CALL_RECORD_FETCH_FULL_HISTORY_FOR_PERSON,
				parentKey, response);

		return response;
	}

	@Override
	public InternalResultsResponse<CallRecord> fetchCallRecordBySid(String sid)
	{
		InternalResultsResponse<CallRecord> response = new InternalResultsResponse<CallRecord>();

		QATMyBatisDacHelper.doQueryForList(getSqlSession(), CALL_RECORD_FETCH_CALL_BY_SID,
				sid, response);

		return response;
	}

	@Override
	public Integer fetchPhoneBySid(String sid)
	{
		Integer theCount =
				(Integer)QATMyBatisDacHelper.doQueryForObject(getSqlSession(), CALL_RECORD_FETCH_PHONE_BY_SID, sid);

		return theCount;
	}

	@Override
	public Integer fetchPersonIdByIvrIdentity(IvrIdentity ivrIdentity)
	{
		Integer personId =
				(Integer)QATMyBatisDacHelper.doQueryForObject(getSqlSession(),
						CALL_RECORD_FETCH_PERSON_ID_BY_IVR_IDENTITY, ivrIdentity);

		return personId;
	}

	@Override
	public InternalResultsResponse<MoneyTransferSummary> fetchMoneyTransferSummaryByMember(
			MoneyTransferSummary moneyTransferSummary)
	{
		InternalResultsResponse<MoneyTransferSummary> response = new InternalResultsResponse<MoneyTransferSummary>();

		QATMyBatisDacHelper.doQueryForList(getSqlSession(), CALL_RECORD_FETCH_MONEY_TRANSFER_SUMMARY_BY_MEMBER,
				moneyTransferSummary, response);

		return response;
	}

	@Override
	public InternalResultsResponse<CallRecordContext> fetchCallRecordContextByIvrOption(
			CallRecordContext callRecordContext)
	{
		InternalResultsResponse<CallRecordContext> response = new InternalResultsResponse<CallRecordContext>();

		QATMyBatisDacHelper.doQueryForList(getSqlSession(), CALL_RECORD_CONTEXT_STMT_FETCH_BY_IVR_OPTION,
				callRecordContext,
				response);

		return response;
	}

	private void setChangeControlField(QATModel model)
	{
		switch (model.getModelAction())
		{
			case INSERT:
				model.setCreateDateUTC(System.currentTimeMillis());
				model.setCreateUser(SYSTEM_USER);
				break;
			case UPDATE:
				model.setModifyDateUTC(System.currentTimeMillis());
				model.setModifyUser(SYSTEM_USER);
				break;
			case DELETE:
				break;
			default:
				break;
		}
	}
}
