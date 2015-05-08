package com.prosperitasglobal.sendsolv.callingcard.dac.mybatis;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.prosperitasglobal.sendsolv.callingcard.dac.ICallingCardDAC;
import com.prosperitasglobal.sendsolv.callingcard.model.CallingCardInfo;
import com.prosperitasglobal.sendsolv.callingcard.model.request.CallingCardMaintenanceRequest;
import com.qat.framework.model.response.InternalResponse.Status;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.QATMyBatisDacHelper;
import com.qat.framework.validation.ValidationUtil;

public class CallingCardDACImpl extends SqlSessionDaoSupport implements ICallingCardDAC
{
	private static final String CALLING_CARD_NAMESPACE = "CallingCardMap.";
	private static final String CALLING_CARD_STMT_FETCH_BY_PERSON_ID = CALLING_CARD_NAMESPACE + "fetchPinIdByPersonId";
	private static final String CALLING_CARD_STMT_FETCH_CALLING_CARD_FOR_UPDATE = CALLING_CARD_NAMESPACE
			+ "fetchCallingCardForUpdate";
	private static final String CALLING_CARD_STMT_UPDATE_CALLING_CARD = CALLING_CARD_NAMESPACE
			+ "updateCallingCard";
	private static final String CALLING_CARD_STMT_INSERT_CALLING_CARD = CALLING_CARD_NAMESPACE
			+ "insertCallingCard";
	private static final String CALLING_CARD_STMT_FETCH_AVAILABLE_PINS = CALLING_CARD_NAMESPACE
			+ "fetchAvailablePins";

	@Override
	public InternalResultsResponse<CallingCardInfo> insertCallingCard(CallingCardMaintenanceRequest request)
	{
		InternalResultsResponse<CallingCardInfo> response = new InternalResultsResponse<CallingCardInfo>();

		CallingCardInfo callingCardInfo = request.getCallingCardInfo();

		int insertCount =
				QATMyBatisDacHelper.doInsert(getSqlSession(), CALLING_CARD_STMT_INSERT_CALLING_CARD, callingCardInfo,
						response);

		if (!response.isInError() && (insertCount > 0))
		{

			response.addResult(callingCardInfo);
		}

		return response;
	}

	@Override
	public InternalResultsResponse<CallingCardInfo> fetchAvailablePins(CallingCardMaintenanceRequest request)
	{
		InternalResultsResponse<CallingCardInfo> response = new InternalResultsResponse<CallingCardInfo>();

		QATMyBatisDacHelper.doQueryForList(getSqlSession(), CALLING_CARD_STMT_FETCH_AVAILABLE_PINS, response);

		return response;
	}

	@Override
	public InternalResultsResponse<CallingCardInfo> updateCallingCard(CallingCardMaintenanceRequest request)
	{
		InternalResultsResponse<CallingCardInfo> response = new InternalResultsResponse<CallingCardInfo>();

		CallingCardInfo callingCardInfo =
				(CallingCardInfo)QATMyBatisDacHelper.doQueryForObject(getSqlSession(),
						CALLING_CARD_STMT_FETCH_CALLING_CARD_FOR_UPDATE);

		if (!ValidationUtil.isNull(callingCardInfo) && !ValidationUtil.isNullOrZero(callingCardInfo.getCallingCardId()))
		{
			callingCardInfo.setPersonId(request.getCallingCardInfo().getPersonId());
			callingCardInfo.setModifyDateUTC(request.getCallingCardInfo().getModifyDateUTC());
			callingCardInfo.setModifyUser(request.getCallingCardInfo().getModifyUser());
			int updateCount =
					QATMyBatisDacHelper.doUpdate(getSqlSession(), CALLING_CARD_STMT_UPDATE_CALLING_CARD,
							callingCardInfo, response);

			if (!response.isInError() && (updateCount > 0))
			{
				response.addResult(callingCardInfo);
			}
		}
		else
		{
			response.setStatus(Status.NoRowsFoundError);
		}

		return response;
	}

	@Override
	public InternalResultsResponse<Integer> fetchIdPinByPersonId(Integer personId)
	{
		InternalResultsResponse<Object> objectResponse = new InternalResultsResponse<Object>();

		objectResponse =
				QATMyBatisDacHelper.doQueryForObject(getSqlSession(), CALLING_CARD_STMT_FETCH_BY_PERSON_ID, personId,
						objectResponse);

		InternalResultsResponse<Integer> integerResponse = new InternalResultsResponse<Integer>();

		integerResponse.merge(objectResponse);

		integerResponse.addResult((Integer)objectResponse.getFirstResult());

		return integerResponse;
	}
}
