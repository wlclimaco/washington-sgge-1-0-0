package com.prosperitasglobal.sendsolv.dac.mybatis;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.prosperitasglobal.sendsolv.dac.IDailyCurrencyRateDAC;
import com.prosperitasglobal.sendsolv.dacd.mybatis.PagedResultsDACD;
import com.prosperitasglobal.sendsolv.model.DailyCurrencyRate;
import com.prosperitasglobal.sendsolv.model.DailyCurrencyRateDetail;
import com.prosperitasglobal.sendsolv.model.request.DailyCurrencyRateInquiryRequest;
import com.qat.framework.model.QATModel.PersistanceActionEnum;
import com.qat.framework.model.response.InternalResponse;
import com.qat.framework.model.response.InternalResultsResponse;
import com.qat.framework.util.QATMyBatisDacHelper;
import com.qat.framework.validation.ValidationUtil;

/**
 * The Class DailyCurrencyRateDACImpl.
 */
public class DailyCurrencyRateDACImpl extends SqlSessionDaoSupport implements IDailyCurrencyRateDAC
{
	/*
	 * SQL Statements
	 */
	/** DAILY_CURRENCY_RATE_NAMESPACE. */
	private static final String DAILY_CURRENCY_RATE_NAMESPACE = "DailyCurrencyRateMap.";
	private static final String DAILY_CURRENCY_RATE_STMT_FETCH_BY_ID = DAILY_CURRENCY_RATE_NAMESPACE
			+ "fetchDailyCurrencyRateById";
	private static final String DAILY_CURRENCY_RATE_STMT_FETCH_BY_REQUEST = DAILY_CURRENCY_RATE_NAMESPACE
			+ "fetchDailyCurrencyRateByRequest";
	private static final String DAILY_CURRENCY_RATE_STMT_FETCH_ROW_COUNT = DAILY_CURRENCY_RATE_NAMESPACE
			+ "fetchDailyCurrencyRateRowCount";
	private static final String DAILY_CURRENCY_RATE_STMT_INSERT = DAILY_CURRENCY_RATE_NAMESPACE
			+ "insertDailyCurrencyRate";
	private static final String DAILY_CURRENCY_RATE_STMT_UPDATE = DAILY_CURRENCY_RATE_NAMESPACE
			+ "updateDailyCurrencyRate";
	private static final String DAILY_CURRENCY_RATE_STMT_DELETE = DAILY_CURRENCY_RATE_NAMESPACE
			+ "deleteDailyCurrencyRate";

	/** DAILY_CURRENCY_RATE_NAMESPACE. */
	private static final String DAILY_CURRENCY_RATE_DETAIL_NAMESPACE = "DailyCurrencyRateDetailMap.";
	private static final String DAILY_CURRENCY_RATE_DETAIL_STMT_INSERT = DAILY_CURRENCY_RATE_DETAIL_NAMESPACE
			+ "insertDailyCurrencyRateDetail";
	private static final String DAILY_CURRENCY_RATE_DETAIL_STMT_UPDATE = DAILY_CURRENCY_RATE_DETAIL_NAMESPACE
			+ "updateDailyCurrencyRateDetail";
	private static final String DAILY_CURRENCY_RATE_DETAIL_STMT_DELETE = DAILY_CURRENCY_RATE_DETAIL_NAMESPACE
			+ "deleteDailyCurrencyRateDetail";

	/**
	 * Delete a {@link DailyCurrencyRateDetail} object.
	 *
	 * @param dailyCurrencyRateDetail The {@link DailyCurrencyRateDetail} object to delete.
	 * @param response The {@link InternalResponse} object that is currently being used for the delete. Any new errors
	 *            will be added to this list.
	 * @return An <code>int</code> indicating how many successful deletes were performed.
	 */
	private int deleteDailyCurrencyRateDetail(DailyCurrencyRateDetail dailyCurrencyRateDetail,
			InternalResponse response)
	{
		int count =
				QATMyBatisDacHelper.doRemove(getSqlSession(), DAILY_CURRENCY_RATE_DETAIL_STMT_DELETE,
						dailyCurrencyRateDetail, response);

		if (count == 1)
		{
			dailyCurrencyRateDetail.setModelAction(PersistanceActionEnum.NONE);
		}

		return count;
	}

	/**
	 * Insert a {@link DailyCurrencyRateDetail} object.
	 *
	 * @param dailyCurrencyRateDetail The {@link DailyCurrencyRateDetail} object to insert.
	 * @param response The {@link InternalResponse} object that is currently being used for the insert. Any new errors
	 *            will be added to this list.
	 * @return An <code>int</code> indicating how many successful inserts were performed.
	 */
	private int insertDailyCurrencyRateDetail(DailyCurrencyRateDetail dailyCurrencyRateDetail,
			InternalResponse response)
	{
		int count =
				QATMyBatisDacHelper.doInsert(getSqlSession(), DAILY_CURRENCY_RATE_DETAIL_STMT_INSERT,
						dailyCurrencyRateDetail, response);

		if (count == 1)
		{
			dailyCurrencyRateDetail.setModelAction(PersistanceActionEnum.NONE);
		}

		return count;
	}

	/**
	 * Maintain the daily currency rate associations.
	 *
	 * @param dailyCurrencyRate The {@link DailyCurrencyRate}.
	 * @param response The response.
	 * @return The number of actions performed.
	 */
	private int maintainDailyCurrencyRateAssociations(DailyCurrencyRate dailyCurrencyRate, InternalResponse response)
	{
		return maintainDailyCurrencyRateDetailList(dailyCurrencyRate, response);
	}

	/**
	 * Maintain the daily currency rate details.
	 *
	 * @param dailyCurrencyRate The {@link DailyCurrencyRate}.
	 * @param response The response.
	 * @return The number of actions performed.
	 */
	private int maintainDailyCurrencyRateDetailList(DailyCurrencyRate dailyCurrencyRate, InternalResponse response)
	{
		int count = 0;

		if (!ValidationUtil.isNullOrEmpty(dailyCurrencyRate.getDailyCurrencyRateDetailList()))
		{
			// For Each Currency Allocation...
			for (DailyCurrencyRateDetail dailyCurrencyRateDetail : dailyCurrencyRate.getDailyCurrencyRateDetailList())
			{
				// Make sure we set the parent key
				dailyCurrencyRateDetail.setDailyCurrencyRateId(dailyCurrencyRate.getId());

				switch (dailyCurrencyRateDetail.getModelAction())
				{
					case UPDATE:
						count += updateDailyCurrencyRateDetail(dailyCurrencyRateDetail, response);
						break;
					case INSERT:
						count += insertDailyCurrencyRateDetail(dailyCurrencyRateDetail, response);
						break;
					case DELETE:
						count += deleteDailyCurrencyRateDetail(dailyCurrencyRateDetail, response);
						break;
					default:
						break;
				}
			}
		}

		return count;
	}

	/**
	 * Update a {@link DailyCurrencyRateDetail} object.
	 *
	 * @param dailyCurrencyRateDetail The {@link DailyCurrencyRateDetail} object to update.
	 * @param response The {@link InternalResponse} object that is currently being used for the insert. Any new errors
	 *            will be added to this list.
	 * @return An <code>int</code> indicating how many successful updates were performed.
	 */
	private int updateDailyCurrencyRateDetail(DailyCurrencyRateDetail dailyCurrencyRateDetail,
			InternalResponse response)
	{
		int count =
				QATMyBatisDacHelper.doUpdate(getSqlSession(), DAILY_CURRENCY_RATE_DETAIL_STMT_UPDATE,
						dailyCurrencyRateDetail,
						response);

		if (count == 1)
		{
			dailyCurrencyRateDetail.setModelAction(PersistanceActionEnum.NONE);
		}

		return count;
	}

	/**
	 * Delete a {@link DailyCurrencyRate} from the SendSolv system, along with all of its composite associated
	 * objects.
	 * <p>
	 *
	 * @param dailyCurrencyRate The daily currency rate to delete.
	 * @return The {@link InternalResponse} containing information about the success/failure of the delete.
	 * @see QATMyBatisDacHelper#doRemove(org.apache.ibatis.session.SqlSession, String, com.qat.framework.model.QATModel,
	 *      InternalResponse)
	 */
	@Override
	public InternalResponse deleteDailyCurrencyRate(DailyCurrencyRate dailyCurrencyRate)
	{
		InternalResponse response = new InternalResponse();

		QATMyBatisDacHelper.doRemove(getSqlSession(), DAILY_CURRENCY_RATE_STMT_DELETE, dailyCurrencyRate,
				response);

		return response;
	}

	/**
	 * Fetch a daily currency rate by id.
	 *
	 * @param id The id of the daily currency rate to fetch.
	 * @return The {@link InternalResultsResponse} containing the fetched {@link DailyCurrencyRate} along with
	 *         information about the success/failure of the fetch.
	 * @see QATMyBatisDacHelper#doQueryForList(org.apache.ibatis.session.SqlSession, String, Object,
	 *      InternalResultsResponse)
	 */
	@Override
	public InternalResultsResponse<DailyCurrencyRate> fetchDailyCurrencyRateById(Integer id)
	{
		InternalResultsResponse<DailyCurrencyRate> response = new InternalResultsResponse<DailyCurrencyRate>();
		QATMyBatisDacHelper.doQueryForList(getSqlSession(), DAILY_CURRENCY_RATE_STMT_FETCH_BY_ID, id, response);
		return response;
	}

	/**
	 * Fetch all daily currency rates matching the request.
	 *
	 * @param request The request.
	 * @return The {@link InternalResultsResponse} containing all the fetched {@link DailyCurrencyRate}s along with
	 *         information about the success/failure of the fetch.
	 * @see PagedResultsDACD#fetchObjectsByRequest(org.apache.ibatis.session.SqlSession,
	 *      com.prosperitasglobal.sendsolv.model.request.PagedInquiryRequest, String, String, InternalResultsResponse)
	 */
	@Override
	public InternalResultsResponse<DailyCurrencyRate> fetchDailyCurrencyRateByRequest(
			DailyCurrencyRateInquiryRequest request)
	{
		InternalResultsResponse<DailyCurrencyRate> response = new InternalResultsResponse<DailyCurrencyRate>();

		PagedResultsDACD.fetchObjectsByRequest(getSqlSession(), request, DAILY_CURRENCY_RATE_STMT_FETCH_ROW_COUNT,
				DAILY_CURRENCY_RATE_STMT_FETCH_BY_REQUEST, response);

		return response;
	}

	/**
	 * Insert a {@link DailyCurrencyRate} into the SendSolv system, along with all of its composite associated objects
	 * where the model action attribute has been marked.
	 * <p>
	 * Be careful with the associates. If the model action is not marked as an insert, the system will attempt to
	 * perform the action specified. This could lead to an abnormal database method being applied to the object. A worst
	 * case here, is that the model action is not marked at all, and thus the associated object won't be inserted.
	 * <p>
	 * The insert will first attempt the insert of the {@link DailyCurrencyRate}. If that insert is successful, then all
	 * of the composite associations will be looked at and have their model action performed. Before the action is
	 * performed on them, the parent key will be updated to keep them in sync. If an error is taken during any of the
	 * actions, the method will stop and return with the error. It is assumed that the transaction is being maintained
	 * elsewhere, and that it will be rolled back upon getting an error.
	 *
	 * @param dailyCurrencyRate The daily currency rate to insert.
	 * @return The {@link InternalResponse} containing information about the success/failure of the insert.
	 * @see QATMyBatisDacHelper#doInsert(org.apache.ibatis.session.SqlSession, String, com.qat.framework.model.QATModel,
	 *      InternalResponse)
	 */
	@Override
	public InternalResponse insertDailyCurrencyRate(DailyCurrencyRate dailyCurrencyRate)
	{
		Integer insertCount = 0;
		InternalResponse response = new InternalResponse();

		// First insert the root
		// If successful the unique-id will be populated back into the object.
		insertCount =
				QATMyBatisDacHelper.doInsert(getSqlSession(), DAILY_CURRENCY_RATE_STMT_INSERT, dailyCurrencyRate,
						response);

		if (response.isInError())
		{
			return response;
		}

		insertCount += maintainDailyCurrencyRateAssociations(dailyCurrencyRate, response);

		if (!response.isInError() && (insertCount > 0))
		{
			dailyCurrencyRate.setModelAction(PersistanceActionEnum.NONE);
		}

		return response;
	}

	/**
	 * Update a {@link DailyCurrencyRate} in the SendSolv system, along with all of its composite associated objects
	 * where the model action attribute has been marked.
	 * <p>
	 * Be careful with the associates. The system will attempt to perform the action specified. This could lead to an
	 * abnormal database method being applied to the object. Make sure that the correct model action has been specified
	 * for all of the composite associated objects.
	 * <p>
	 *
	 * @param dailyCurrencyRate The daily currency rate to update.
	 * @return The {@link InternalResponse} containing information about the success/failure of the update.
	 * @see QATMyBatisDacHelper#doUpdateOL(org.apache.ibatis.session.SqlSession, String,
	 *      com.qat.framework.model.QATModelOL, String, InternalResponse)
	 */
	@Override
	public InternalResponse updateDailyCurrencyRate(DailyCurrencyRate dailyCurrencyRate)
	{
		Integer updateCount = 0;
		InternalResponse response = new InternalResponse();

		// First update the root
		updateCount =
				QATMyBatisDacHelper.doUpdate(getSqlSession(), DAILY_CURRENCY_RATE_STMT_UPDATE, dailyCurrencyRate,
						response);

		if (response.isInError())
		{
			return response;
		}

		updateCount += maintainDailyCurrencyRateAssociations(dailyCurrencyRate, response);

		if (!response.isInError() && (updateCount > 0))
		{
			dailyCurrencyRate.setModelAction(PersistanceActionEnum.NONE);
		}

		return response;
	}

}
