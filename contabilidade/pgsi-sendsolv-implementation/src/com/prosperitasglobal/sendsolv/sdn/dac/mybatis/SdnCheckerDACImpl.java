package com.prosperitasglobal.sendsolv.sdn.dac.mybatis;

import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.prosperitasglobal.sendsolv.dacd.mybatis.PagedResultsDACD;
import com.prosperitasglobal.sendsolv.sdn.dac.ISdnCheckerDAC;
import com.prosperitasglobal.sendsolv.sdn.model.SdnHistory;
import com.prosperitasglobal.sendsolv.sdn.model.SdnMatch;
import com.prosperitasglobal.sendsolv.sdn.model.SdnMatchField;
import com.prosperitasglobal.sendsolv.sdn.model.SdnMatchRecord;
import com.prosperitasglobal.sendsolv.sdn.model.SdnMatchTypeEnum;
import com.prosperitasglobal.sendsolv.sdn.model.SdnStatusHistory;
import com.prosperitasglobal.sendsolv.sdn.model.request.SdnStatusHistoryInquiryRequest;
import com.prosperitasglobal.sendsolv.util.PGSiDateUtil;
import com.qat.framework.model.QATModel;
import com.qat.framework.model.QATModel.PersistanceActionEnum;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.QATMyBatisDacHelper;
import com.qat.framework.validation.ValidationUtil;

/**
 * The Class LocationDACImpl.
 */
public class SdnCheckerDACImpl extends SqlSessionDaoSupport implements ISdnCheckerDAC
{
	/** The valid sort fields for a sdn status history inquiry. Will be injected by Spring. */
	private Map<String, String> sdnStatusHistoryInquiryValidSortFields;

	private static final String SYSTEM_USER = "SendSolv";

	/** The Constant LOCATION_NAMESPACE. */
	private static final String SDN_STATUS_HISTORY_NAMESPACE = "SdnCheckerMap.";

	private static final String SDN_STATUS_HISTORY_STMT_FETCH_ROW_COUNT = SDN_STATUS_HISTORY_NAMESPACE
			+ "fetchSdnStatusHistoryRowCount";
	private static final String SDN_STATUS_HISTORY_STMT_FETCH_BY_REQUEST = SDN_STATUS_HISTORY_NAMESPACE
			+ "fetchSdnStatusHistoryByRequest";

	private static final String SDN_STMT_STATUS_HISTORY_INSERT = SDN_STATUS_HISTORY_NAMESPACE
			+ "insertSdnStatusHistory";

	private static final String SDN_STMT_STATUS_HISTORY_UPDATE = SDN_STATUS_HISTORY_NAMESPACE
			+ "updateSdnStatusHistory";

	private static final String SDN_STMT_FETCH_CURRENT_STATUS_FOR_PERSON = SDN_STATUS_HISTORY_NAMESPACE
			+ "fetchCurrentSdnStatusHistoryByPerson";

	private static final String SDN_STMT_FETCH_CURRENT_STATUS_FOR_BUSINESS = SDN_STATUS_HISTORY_NAMESPACE
			+ "fetchCurrentSdnStatusHistoryByBusiness";

	private static final String SDN_STMT_FETCH_FULL_HISTORY_FOR_PERSON = SDN_STATUS_HISTORY_NAMESPACE
			+ "fetchFullSdnStatusHistoryByPerson";

	private static final String SDN_STMT_FETCH_FULL_HISTORY_FOR_BUSINESS = SDN_STATUS_HISTORY_NAMESPACE
			+ "fetchFullSdnStatusHistoryByBusiness";

	private static final String SDN_STMT_MATCH_RECORD_INSERT = SDN_STATUS_HISTORY_NAMESPACE + "insertSdnMatchRecord";

	private static final String SDN_STMT_MATCH_FIELD_INSERT = SDN_STATUS_HISTORY_NAMESPACE + "insertSdnMatchField";

	private static final String SDN_STMT_ASSOCIATE_PERSON_WITH_SDN = SDN_STATUS_HISTORY_NAMESPACE
			+ "associatePersonWithSdnStatusHistory";

	private static final String SDN_STMT_ASSOCIATE_BUSINESS_WITH_SDN = SDN_STATUS_HISTORY_NAMESPACE
			+ "associateBusinessWithSdnStatusHistory";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(SdnCheckerDACImpl.class);

	@Override
	public InternalResponse insertSdnStatusHistory(SdnStatusHistory sdnStatusHistory, SdnMatch request)
	{
		Integer insertCount = 0;
		String statementName = null;

		InternalResponse resp = new InternalResponse();

		sdnStatusHistory.setModelAction(PersistanceActionEnum.INSERT);
		setChangeControlField(sdnStatusHistory);

		// First insert the root status data
		insertCount =
				QATMyBatisDacHelper.doInsert(getSqlSession(), SDN_STMT_STATUS_HISTORY_INSERT, sdnStatusHistory, resp);

		if (!resp.isInError() && (insertCount == 1))
		{
			sdnStatusHistory.setParentKey(request.getParentKey());

			if (SdnMatchTypeEnum.INDIVIDUAL.equals(request.getMatchType()))
			{
				statementName = SDN_STMT_ASSOCIATE_PERSON_WITH_SDN;
			}
			else
			{
				statementName = SDN_STMT_ASSOCIATE_BUSINESS_WITH_SDN;
			}

			// Associate with parent using statement name passed as parameter
			insertCount += QATMyBatisDacHelper
					.doInsert(getSqlSession(), statementName, sdnStatusHistory, resp);

			if (!resp.isInError() && (insertCount == 2))
			{
				if (!ValidationUtil.isNullOrEmpty(sdnStatusHistory.getSdnMatchRecordList()))
				{
					for (SdnMatchRecord sdnMatchRecord : sdnStatusHistory.getSdnMatchRecordList())
					{
						sdnMatchRecord.setParentKey(sdnStatusHistory.getId());

						insertCount = insertSdnMatchRecord(sdnMatchRecord, resp);

						if (!resp.isInError() && (insertCount == 1))
						{
							for (SdnMatchField sdnMatchField : sdnMatchRecord.getSdnMatchFieldList())
							{
								sdnMatchField.setParentKey(sdnMatchRecord.getId());
								insertCount = insertSdnMatchField(sdnMatchField, resp);

								if (resp.isInError())
								{
									break;
								}
							}
						}
						else
						{
							break;
						}
					}
				}

			}
		}

		return resp;
	}

	@Override
	public Integer insertSdnMatchRecord(SdnMatchRecord sdnMatchRecord, InternalResponse response)
	{
		Integer insertCount = 0;
		sdnMatchRecord.setModelAction(PersistanceActionEnum.INSERT);
		setChangeControlField(sdnMatchRecord);

		// First insert the root contact data
		insertCount =
				QATMyBatisDacHelper.doInsert(getSqlSession(), SDN_STMT_MATCH_RECORD_INSERT, sdnMatchRecord, response);

		return insertCount;
	}

	@Override
	public Integer insertSdnMatchField(SdnMatchField sdnMatchField, InternalResponse response)
	{
		Integer insertCount = 0;
		sdnMatchField.setModelAction(PersistanceActionEnum.INSERT);
		setChangeControlField(sdnMatchField);

		// First insert the root contact data
		insertCount =
				QATMyBatisDacHelper.doInsert(getSqlSession(), SDN_STMT_MATCH_FIELD_INSERT, sdnMatchField, response);

		return insertCount;
	}

	private void setChangeControlField(QATModel model)
	{
		switch (model.getModelAction())
		{
			case INSERT:
				model.setCreateDateUTC(PGSiDateUtil.getCurrentDateTimeMillis());
				model.setCreateUser(SYSTEM_USER);
				break;
			case UPDATE:
				model.setModifyDateUTC(PGSiDateUtil.getCurrentDateTimeMillis());
				model.setModifyUser(SYSTEM_USER);
				break;
			case DELETE:
				break;
			default:
				break;
		}
	}

	@Override
	public InternalResultsResponse<SdnStatusHistory> fetchCurrentSdnStatusHistory(SdnMatchTypeEnum sdnMatchTypeEnum,
			Integer parentKey)
	{
		String statementName = null;

		if (SdnMatchTypeEnum.INDIVIDUAL.equals(sdnMatchTypeEnum))
		{
			statementName = SDN_STMT_FETCH_CURRENT_STATUS_FOR_PERSON;
		}
		else
		{
			statementName = SDN_STMT_FETCH_CURRENT_STATUS_FOR_BUSINESS;
		}

		InternalResultsResponse<SdnStatusHistory> response = new InternalResultsResponse<SdnStatusHistory>();

		QATMyBatisDacHelper.doQueryForList(getSqlSession(), statementName,
				parentKey, response);

		return response;
	}

	@Override
	public InternalResultsResponse<SdnStatusHistory> fetchFullSdnStatusHistory(SdnMatchTypeEnum sdnMatchTypeEnum,
			Integer parentKey)
	{
		String statementName = null;

		if (SdnMatchTypeEnum.INDIVIDUAL.equals(sdnMatchTypeEnum))
		{
			statementName = SDN_STMT_FETCH_FULL_HISTORY_FOR_PERSON;
		}
		else
		{
			statementName = SDN_STMT_FETCH_FULL_HISTORY_FOR_BUSINESS;
		}

		InternalResultsResponse<SdnStatusHistory> response = new InternalResultsResponse<SdnStatusHistory>();

		QATMyBatisDacHelper.doQueryForList(getSqlSession(), statementName,
				parentKey, response);

		return response;
	}

	@Override
	public InternalResponse updateSdnHistory(SdnStatusHistory sdnStatusHistory)
	{
		InternalResponse response = new InternalResponse();

		sdnStatusHistory.setModelAction(PersistanceActionEnum.UPDATE);
		setChangeControlField(sdnStatusHistory);

		QATMyBatisDacHelper.doUpdate(getSqlSession(), SDN_STMT_STATUS_HISTORY_UPDATE, sdnStatusHistory,
				response);

		return response;
	}

	@Override
	public InternalResultsResponse<SdnHistory> fetchSdnStatusHistoryByRequest(
			SdnStatusHistoryInquiryRequest request)
	{
		InternalResultsResponse<SdnHistory> response = new InternalResultsResponse<SdnHistory>();

		/*
		 * Helper method to translation from the "user friendly" sort field names to the
		 * actual database column names.
		 */
		QATMyBatisDacHelper.translateSortFields(request, getSdnStatusHistoryInquiryValidSortFields());

		PagedResultsDACD.fetchObjectsByRequest(getSqlSession(), request, SDN_STATUS_HISTORY_STMT_FETCH_ROW_COUNT,
				SDN_STATUS_HISTORY_STMT_FETCH_BY_REQUEST, response);

		return response;
	}

	/**
	 * Get the valid sort fields for the money transfer inquiry. Attribute injected by Spring.
	 *
	 * @return The valid sort fields for the money transfer inquiry.
	 */
	public Map<String, String> getSdnStatusHistoryInquiryValidSortFields()
	{
		return sdnStatusHistoryInquiryValidSortFields;
	}

	public void setSdnStatusHistoryInquiryValidSortFields(Map<String, String> sdnStatusHistoryInquiryValidSortFields)
	{
		this.sdnStatusHistoryInquiryValidSortFields = sdnStatusHistoryInquiryValidSortFields;
	}
}
