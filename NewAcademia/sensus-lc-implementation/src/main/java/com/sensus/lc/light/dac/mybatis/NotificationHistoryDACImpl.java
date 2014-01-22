package com.sensus.lc.light.dac.mybatis;

import static com.sensus.common.util.SensusMyBatisDacHelper.doQueryForList;
import static com.sensus.common.util.SensusMyBatisDacHelper.doQueryForObject;

import java.util.ArrayList;
import java.util.HashMap;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.util.SensusMyBatisDacHelper;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.lc.base.util.LCConvertUtil;
import com.sensus.lc.light.dac.INotificationHistoryDAC;
import com.sensus.lc.light.model.AlertClassification;
import com.sensus.lc.light.model.LightHistory;
import com.sensus.lc.light.model.LightPropertyForSearchEnum;
import com.sensus.lc.light.model.LightTextSearch;
import com.sensus.lc.light.model.NotificationHistory;
import com.sensus.lc.light.model.criteria.NotificationHistoryCriteria;
import com.sensus.lc.light.model.request.AlertClassificationMaintenanceRequest;
import com.sensus.lc.light.model.request.NotificationHistoryMaintenanceRequest;
import com.sensus.lc.light.model.request.NotificationHistoryRequest;
import com.sensus.lc.process.model.LCActionCategoryEnum;
import com.sensus.lc.process.model.ProcessFilter;

/**
 * DAC for Light Nofitication History.
 * 
 * @author Thiago
 */
public class NotificationHistoryDACImpl extends SqlSessionDaoSupport implements INotificationHistoryDAC
{

	/** The Constant NOTIFICATION_HISTORY_NAMESPACE. */
	private static final String NOTIFICATION_HISTORY_NAMESPACE = "NotificationHistory.";

	/**
	 * Notification History Queries contancts.
	 */

	private static final String FETCH_NOTIFICATION_HISTORY_BY_REQUEST =
			NOTIFICATION_HISTORY_NAMESPACE + "fetchNotificationHistoryByRequest";

	private static final String FETCH_NOTIFICATION_HISTORY_BY_ID =
			NOTIFICATION_HISTORY_NAMESPACE + "fetchNotificationHistoryById";

	private static final String FETCH_LIGHT_NOTIFICATION_HISTORY = NOTIFICATION_HISTORY_NAMESPACE
			+ "fetchLightNotificationHistory";

	private static final String FETCH_ALL_LIGHT_NOTIFICATION_HISTORY_ALERT_BY_ID = NOTIFICATION_HISTORY_NAMESPACE
			+ "fetchAllNotificationHistoryAlertById";

	/** The Constant FETCH_LIGHT_HISTORY_HEADER. */
	private static final String FETCH_LIGHT_HISTORY_HEADER = NOTIFICATION_HISTORY_NAMESPACE + "fetchLightHistoryHeader";

	/** The Constant FETCH_LIGHT_NOTIFICATION_HISTORY_PAGINATION_TOTAL_ROWS. */
	private static final String FETCH_LIGHT_NOTIFICATION_HISTORY_PAGINATION_TOTAL_ROWS = NOTIFICATION_HISTORY_NAMESPACE
			+ "fetchLightNotificationHistoryPaginationTotalRows";

	private static final String INSERT_NOTIFICATION_HISTORY = "InsertNotificationHistory";

	private static final String INSERT_ALERT_CLASSIFICATION = "InsertAlertClassification";

	private static final String UPDATE_NOTIFICATION_HISTORY = "UpdateNotificationHistory";

	private static final String UPDATE_ALERT_CLASSIFICATION = "UpdateAlertClassification";

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.light.dac.ILightDAC#fetchfetchNotificationHistoryById(com.sensus.mlc.light.model.request.LightRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<NotificationHistory> fetchNotificationHistoryByRequest(
			NotificationHistoryRequest request)
	{
		InternalResultsResponse<NotificationHistory> response = new InternalResultsResponse<NotificationHistory>();
		SensusMyBatisDacHelper
				.doQueryForList(getSqlSession(), FETCH_NOTIFICATION_HISTORY_BY_REQUEST, request, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.light.dac.INotificationHistoryDAC#fetchNotificationHistoryById(com.sensus.mlc.light.model.request
	 * .NotificationHistoryRequest)
	 */
	@Override
	public InternalResultsResponse<NotificationHistory> fetchNotificationHistoryById(
			NotificationHistoryRequest request)
	{
		InternalResultsResponse<NotificationHistory> response = new InternalResultsResponse<NotificationHistory>();
		SensusMyBatisDacHelper
				.doQueryForList(getSqlSession(), FETCH_NOTIFICATION_HISTORY_BY_ID, request, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.light.dac.ILightDAC#fetchLightNotificationHistory(com.sensus.mlc.light.model.request.
	 * NotificationHistoryRequest)
	 */
	@Override
	public InternalResultsResponse<LightHistory> fetchLightNotificationHistory(NotificationHistoryRequest request)
	{
		InternalResultsResponse<LightHistory> response = new InternalResultsResponse<LightHistory>();
		configureProcessFilter(request.getNotificationHistoryCriteria());
		Integer totalRows = (Integer)doQueryForObject(getSqlSession(),
				FETCH_LIGHT_NOTIFICATION_HISTORY_PAGINATION_TOTAL_ROWS, request);

		response.getResultsSetInfo().setTotalRowsAvailable(totalRows);
		if (!ValidationUtil.isNullOrZero(totalRows))
		{
			SensusMyBatisDacHelper.doQueryForList(getSqlSession(), FETCH_LIGHT_NOTIFICATION_HISTORY, request, response);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.light.dac.ILightDAC#fetchLightHistoryHeader(com.sensus.mlc.light.model.request.
	 * NotificationHistoryRequest)
	 */
	@Override
	public InternalResultsResponse<HashMap<String, Integer>> fetchLightHistoryHeader(
			NotificationHistoryRequest request)
	{
		InternalResultsResponse<HashMap<String, Integer>> response =
				new InternalResultsResponse<HashMap<String, Integer>>();

		doQueryForList(getSqlSession(), FETCH_LIGHT_HISTORY_HEADER, request, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.light.dac.INotificationHistoryDAC#insertNotificationHistory(com.sensus.mlc.light.model.request
	 * .NotificationHistoryMaintenanceRequest)
	 */
	@Override
	public InternalResultsResponse<NotificationHistory> insertNotificationHistory(
			NotificationHistoryMaintenanceRequest notificationHistoryMaintenanceRequest)
	{
		InternalResultsResponse<NotificationHistory> response = new InternalResultsResponse<>();

		SensusMyBatisDacHelper.doInsert(getSqlSession(), INSERT_NOTIFICATION_HISTORY,
				notificationHistoryMaintenanceRequest.getNotificationHistory(), response);

		if (!response.isInError())
		{
			response.addResult(notificationHistoryMaintenanceRequest.getNotificationHistory());
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.light.dac.INotificationHistoryDAC#insertAlertClassification(com.sensus.mlc.light.model.request
	 * .AlertClassificationMaintenance)
	 */
	@Override
	public InternalResultsResponse<AlertClassification> insertAlertClassification(
			AlertClassificationMaintenanceRequest alertClassificationMaintenance)
	{
		InternalResultsResponse<AlertClassification> response = new InternalResultsResponse<>();

		SensusMyBatisDacHelper.doInsert(getSqlSession(), INSERT_ALERT_CLASSIFICATION,
				alertClassificationMaintenance.getAlertClassification(), response);

		if (!response.isInError())
		{
			response.addResult(alertClassificationMaintenance.getAlertClassification());
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.light.dac.INotificationHistoryDAC#fetchNotificationHistoryAlertById(com.sensus.mlc.light.model
	 * .request.NotificationHistoryRequest)
	 */
	@Override
	public InternalResultsResponse<AlertClassification> fetchNotificationHistoryAlertById(
			NotificationHistoryRequest request)
	{
		InternalResultsResponse<AlertClassification> response = new InternalResultsResponse<>();
		SensusMyBatisDacHelper.doQueryForList(getSqlSession(), FETCH_ALL_LIGHT_NOTIFICATION_HISTORY_ALERT_BY_ID,
				request, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.light.dac.INotificationHistoryDAC#updateNotificationHistory(com.sensus.mlc.light.model.request
	 * .NotificationHistoryMaintenanceRequest)
	 */
	@Override
	public InternalResponse updateNotificationHistory(
			NotificationHistoryMaintenanceRequest notificationHistoryMaintenanceRequest)
	{
		InternalResponse response = new InternalResponse();
		SensusMyBatisDacHelper.doUpdate(getSqlSession(), UPDATE_NOTIFICATION_HISTORY,
				notificationHistoryMaintenanceRequest.getNotificationHistory(), response);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.light.dac.INotificationHistoryDAC#updateAlertClassification(com.sensus.mlc.light.model.request
	 * .AlertClassificationMaintenanceRequest)
	 */
	@Override
	public InternalResponse updateAlertClassification(
			AlertClassificationMaintenanceRequest alertClassificationMaintenanceRequest)
	{
		InternalResponse response = new InternalResponse();
		SensusMyBatisDacHelper.doUpdate(getSqlSession(), UPDATE_ALERT_CLASSIFICATION,
				alertClassificationMaintenanceRequest.getAlertClassification(), response);

		return response;
	}

	/**
	 * Configure process filter.
	 * 
	 * @param criteria the criteria
	 */
	private void configureProcessFilter(NotificationHistoryCriteria criteria)
	{
		ProcessFilter processFilter = criteria.getProcessFilter();
		if (ValidationUtil.isNull(processFilter))
		{
			return;
		}

		if (!ValidationUtil.isNullOrEmpty(processFilter.getUserIds()))
		{
			criteria.setUserIds(LCConvertUtil.getIdsToString(processFilter.getUserIds(), ","));
		}

		if (!ValidationUtil.isNullOrEmpty(processFilter.getActionCategoryList()))
		{
			criteria.setActionCategoryList(new ArrayList<String>());
			for (LCActionCategoryEnum lcActionCategory : processFilter.getActionCategoryList())
			{
				criteria.getActionCategoryList().addAll(lcActionCategory.getActionDescriptions());
			}
		}

		if (ValidationUtil.isNull(processFilter.getLightTextSearch()))
		{
			return;
		}

		LightTextSearch lightTextSearch = processFilter.getLightTextSearch();
		if (LightPropertyForSearchEnum.ACTION_ID == lightTextSearch.getLightProperty())
		{
			criteria.setActionId(Integer.valueOf(lightTextSearch.getSearchText()));
			return;
		}

		if (LightPropertyForSearchEnum.ACTION_NAME == lightTextSearch.getLightProperty())
		{
			criteria.setSearchText(lightTextSearch.getSearchText());
		}
	}
}
