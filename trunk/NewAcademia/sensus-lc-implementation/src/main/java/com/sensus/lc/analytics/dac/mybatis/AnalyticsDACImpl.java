package com.sensus.lc.analytics.dac.mybatis;

import static com.sensus.common.util.SensusMyBatisDacHelper.doInsert;
import static com.sensus.common.util.SensusMyBatisDacHelper.doQueryForList;
import static com.sensus.common.util.SensusMyBatisDacHelper.doQueryForObject;
import static com.sensus.common.util.SensusMyBatisDacHelper.doUpdate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.util.CSVFileGenerator;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.lc.analytics.csv.AnalyticsGroupCSVDataSource;
import com.sensus.lc.analytics.dac.IAnalyticsDAC;
import com.sensus.lc.analytics.model.AnalyticsGroup;
import com.sensus.lc.analytics.model.AnalyticsGroupAlarm;
import com.sensus.lc.analytics.model.AnalyticsGroupCarbonCredits;
import com.sensus.lc.analytics.model.AnalyticsGroupColumns;
import com.sensus.lc.analytics.model.AnalyticsGroupEcoMode;
import com.sensus.lc.analytics.model.AnalyticsGroupOrderByEnum;
import com.sensus.lc.analytics.model.AnalyticsGroupTypeLight;
import com.sensus.lc.analytics.model.AnalyticsGroupWarning;
import com.sensus.lc.analytics.model.AnalyticsRangeDateEnum;
import com.sensus.lc.analytics.model.DashboardViewTypeEnum;
import com.sensus.lc.analytics.model.request.AnalyticsRequest;
import com.sensus.lc.analytics.model.request.InquiryAnalyticsRequest;
import com.sensus.lc.base.util.LCConvertUtil;
import com.sensus.lc.base.util.LCDateUtil;
import com.sensus.lc.light.model.AlertSubTypeEnum;
import com.sensus.lc.light.model.AlertTypeEnum;
import com.sensus.lc.light.model.Light;
import com.sensus.lc.light.model.LightTypeEnum;
import com.sensus.lc.tenant.model.Tenant;

/**
 * The Class AnalyticsDACImpl.
 * 
 * @author - QAT Brazil
 */
public class AnalyticsDACImpl extends SqlSessionDaoSupport implements IAnalyticsDAC
{
	// messages
	private static final String COMMA_SEPARATOR = ",";
	private static final Integer PARAMSIZE2 = 2;
	private static final Integer PARAMSIZE4 = 4;
	private static final Integer PARAMSIZE7 = 7;
	private static final Integer PARAMSIZE9 = 9;
	private static final Integer PARAMSIZE11 = 11;
	private static final Integer PARAMSIZE12 = 12;
	private static final Integer PARAMSIZE5 = 5;
	private static final Integer MONTH_DAYS_AMOUNT = 31;
	private static final String RANGE_DATE_ID = "range_date_id";
	private static final String MONTH_DAYS = "month_days";
	private static final String GROUP_ID = "group_id";
	private static final String INIT_DATE = "init_date";
	private static final String END_DATE = "end_date";
	private static final String TENANT_ID = "tenant_id";
	private static final String DATE_TYPE_ID = "date_type_id";
	private static final String LAMP_FAILURE_ID = "lamp_failure_id";
	private static final String POWER_FAILURE_ID = "power_failure_id";
	private static final String POWER_SURGE_ID = "power_surge_id";
	private static final String BOARD_FAILURE_ID = "board_failure_id";
	private static final String METROLOGY_ERROR_ID = "metrology_error_id";
	private static final String METROLOGY_COM_FAILURE_ID = "metrology_com_failure_id";
	private static final String HIGH_CURRENT_ID = "high_current_id";
	private static final String LOW_CURRENT_ID = "low_current_id";
	private static final String REVERSE_ENERGY_ID = "reverse_energy_id";
	private static final String METROLOGY_RESET_ID = "metrology_reset_id";
	private static final String BROWNOUT_DETECTED_ID = "brownout_detected_id";
	private static final String INDUCTION_TYPE_ID = "induction_type_id";
	private static final String COMMUNICATION_FAIL_ID = "communication_fail_id";
	private static final String LED_TYPE_ID = "led_type_id";
	private static final String OTHER_TYPE_ID = "other_type_id";
	private static final String ALARM_TYPE_ID = "alarm_type_id";
	private static final String ALARM_SUBTYPE_ID = "alarm_subtype_id";
	private static final String CARBON_CREDITS_FACTOR = "carbon_credits_factor";
	private static final String BARRELS_OF_OIL_FACTOR = "barrels_of_oil_factor";
	private static final String METRIC_OF_CO_FACTOR = "metric_of_co_factor";
	private static final String ORDER_BY = "orderBy";
	private static final String PAGE_SIZE = "pageSize";
	private static final String START_ROW = "startRow";
	private static final String START_PAGE = "startPage";
	private static final String VIEW_MODE_TODAY = "view_mode_today";
	private static final String ALLOWED_GROUP_ID_LIST = "allowedGroupIdList";
	private static final String ALLOWED_GROUPS_TO_PROC = "allowedGroupsToProc";
	private static final String NOTIFICATION_HISTORY_ID = "notification_history_id";
	private static final String ALERT_SUBTYPE_ID = "alert_subtype_id";
	private static final String CREATE_USER = "create_user";
	private static final Object ORDER_BY_DEFAULT = "total desc";
	private static final Object TYPE_WARNING = "W";
	private static final String ALERT_TYPE = "alert_type";
	private static final String OPERATOR = "operator";
	private static final Object TYPE_ALARM = "A";

	/** The Constant ANALYTICS. */
	private static final String ANALYTICS = "Analytics.";
	private static final String PAGINATION_TOTAL_ROWS = ANALYTICS + "paginationTotalRows";
	private static final String FETCH_ANALYTICS_ALARM_BY_STATUS_ID = ANALYTICS + "fetchAnalyticsAlarmsByStatusId";
	private static final String FETCH_ANALYTICS_ALERT_BY_TYPE = ANALYTICS + "fetchAnalyticsAlertsByType";
	private static final String FETCH_DASH_BOARD_RESUME = ANALYTICS + "fetchDashboardResume";
	private static final String FETCH_ALL_ANALYTICS_ALARM_BY_DATE = ANALYTICS + "fetchAllAnalyticsAlarmsByDate";
	private static final String FETCH_ALL_ANALYTICS_WARNINGS_BY_DATE = ANALYTICS + "fetchAllAnalyticsWarningsByDate";
	private static final String FETCH_ALL_ANALYTICS_INSTALLED_BY_DATE = ANALYTICS + "fetchAllAnalyticsInstalledByDate";
	private static final String FETCH_ALL_ANALYTICS_CONSUPTION_BY_DATE = ANALYTICS
			+ "fetchAllAnalyticsConsumptionByDate";
	private static final String FETCH_ALL_ANALYTICS_ECOMODE_BY_DATE = ANALYTICS + "fetchAllAnalyticsEcoModeByDate";
	private static final String FETCH_ALL_ANALYTICS_CARBON_CREDITS_BY_DATE = ANALYTICS
			+ "fetchAllAnalyticsCarbonCreditsByDate";
	private static final String FETCH_ANALYTICS_ALARM_BY_GROUP = ANALYTICS + "fetchAnalyticsAlarmsByGroup";
	private static final String FETCH_ANALYTICS_WARNINGS_BY_GROUP = ANALYTICS + "fetchAnalyticsWarningsByGroup";
	private static final String FETCH_ALL_ANALYTICS_GROUPS_INSTALLED = ANALYTICS + "fetchAllAnalyticsGroupsInstalled";
	private static final String FETCH_ALL_ANALYTICS_GROUPS_CONSUMPTION = ANALYTICS
			+ "fetchAllAnalyticsGroupsConsumption";
	private static final String FETCH_ANALYTICS_CARBON_CREDITS_BY_GROUP = ANALYTICS
			+ "fetchAnalyticsCarbonCreditsByGroup";
	private static final String FETCH_DASH_BOARD_HEADER = ANALYTICS + "fetchDashboardHeader";
	private static final String FETCH_ALL_ANALYTCS = ANALYTICS + "fetchAllAnalytcsGroup";
	private static final String CALCULATE_DASH_BOARD_RESUME = ANALYTICS + "calculateDashboardResume";
	private static final String FETCH_ANALYTICS_ECOMODE_BY_GROUP = ANALYTICS + "fetchAnalyticsEcoModeByGroup";
	private static final String DELETE_DASHBOARD_RESUME = ANALYTICS + "deleteDashboardResume";
	private static final String FETCH_ALL_GROUPS_BY_TENANT_FOR_DASHBOARD = ANALYTICS
			+ "fetchAllGroupsByTenantForDashboard";
	private static final String UPDATE_ANALYTICS_ALARMS_WARNINGS = ANALYTICS + "updateAnalyticsAlarmsWarnings";
	private static final String INSERT_ANALYTICS_SUMMARIZED = ANALYTICS + "insertAnalyticsSummarized";
	private static final String ANALYTICS_GROUP_ID = "analytics_group_id";
	private static final String RANGE_DATE = "range_date";
	private static final String GROUP_NAME = "group_name";

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.analytics.dac.IAnalyticsDAC#fetchAnalyticsAlarmsByStatusId(com.sensus.mlc.analytics.model.request
	 * .AnalyticsRequest)
	 */
	@Override
	public InternalResultsResponse<Light> fetchAnalyticsAlarmsByStatusId(AnalyticsRequest analyticsRequest)
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE4);
		paramMap.put(TENANT_ID, analyticsRequest.getUserContext().getTenant().getId());
		paramMap.put(ALARM_TYPE_ID, AlertSubTypeEnum.LAMP_FAILURE.getValue());
		paramMap.put(ALARM_SUBTYPE_ID, analyticsRequest.getAlertSubtype().getValue());
		paramMap.put(ALLOWED_GROUP_ID_LIST, analyticsRequest.getAllowedGroupIdList());

		InternalResultsResponse<Light> response = new InternalResultsResponse<Light>();
		doQueryForList(getSqlSession(), FETCH_ANALYTICS_ALARM_BY_STATUS_ID, paramMap, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.analytics.dac.IAnalyticsDAC#fetchAnalyticsAlertsByType(com.sensus.mlc.analytics.model.request.
	 * AnalyticsRequest)
	 */
	@Override
	public InternalResultsResponse<Map<String, Integer>> fetchAnalyticsAlertsByType(
			AnalyticsRequest analyticsRequest)
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE2);
		paramMap.put(TENANT_ID, analyticsRequest.getUserContext().getTenant().getId());
		paramMap.put(ALLOWED_GROUP_ID_LIST, analyticsRequest.getAllowedGroupIdList());

		InternalResultsResponse<Map<String, Integer>> response =
				new InternalResultsResponse<Map<String, Integer>>();
		doQueryForList(getSqlSession(), FETCH_ANALYTICS_ALERT_BY_TYPE, paramMap, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.analytics.dac.IAnalyticsDAC#fetchDashboardResume(com.sensus.mlc.analytics.model.request.
	 * AnalyticsRequest, java.lang.Double)
	 */
	@Override
	public InternalResultsResponse<AnalyticsGroupColumns> fetchDashboardResume(
			AnalyticsRequest analyticsRequest, Double carbonCreditsFactor)
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE5);
		paramMap.put(TENANT_ID, analyticsRequest.getUserContext().getTenant().getId());
		paramMap.put(VIEW_MODE_TODAY, DashboardViewTypeEnum.TODAY.getValue());
		paramMap.put(INIT_DATE, analyticsRequest.getInitialDate());
		paramMap.put(END_DATE, analyticsRequest.getEndDate());

		// Add restriction
		if (!ValidationUtil.isNull(analyticsRequest.getAllowedGroupIdList()))
		{
			paramMap.put(ALLOWED_GROUPS_TO_PROC,
					StringUtils.join(analyticsRequest.getAllowedGroupIdList(), COMMA_SEPARATOR));
			paramMap.put(ALLOWED_GROUP_ID_LIST, analyticsRequest.getAllowedGroupIdList());
		}

		InternalResultsResponse<AnalyticsGroupColumns> response = new InternalResultsResponse<AnalyticsGroupColumns>();
		doQueryForList(getSqlSession(), FETCH_DASH_BOARD_RESUME, paramMap, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.analytics.dac.IAnalyticsDAC#fetchAllAnalyticsAlarmsByDate(com.sensus.mlc.analytics.model.request
	 * .AnalyticsRequest)
	 */
	@Override
	public InternalResultsResponse<AnalyticsGroupColumns> fetchAllAnalyticsAlarmsByDate(
			AnalyticsRequest analyticsRequest)
	{
		InternalResultsResponse<AnalyticsGroupColumns> response = new InternalResultsResponse<AnalyticsGroupColumns>();

		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE7);
		paramMap.put(DATE_TYPE_ID, analyticsRequest.getAnalyticsDateTypeEnum().getValue());
		paramMap.put(TENANT_ID, analyticsRequest.getUserContext().getTenant().getId());
		paramMap.put(INIT_DATE, analyticsRequest.getInitialDate());
		paramMap.put(END_DATE, analyticsRequest.getEndDate());
		paramMap.put(GROUP_NAME, analyticsRequest.getEndDate());
		paramMap.put(RANGE_DATE_ID, null);

		if (!ValidationUtil.isNull(analyticsRequest.getAnalyticsRangeDateEnum()))
		{
			paramMap.put(RANGE_DATE_ID, analyticsRequest.getAnalyticsRangeDateEnum().getValue());
		}

		if (!ValidationUtil.isNull(analyticsRequest.getGroup()))
		{
			paramMap.put(GROUP_ID, analyticsRequest.getGroup().getId());
		}

		doQueryForList(getSqlSession(), FETCH_ALL_ANALYTICS_ALARM_BY_DATE, paramMap, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.analytics.dac.IAnalyticsDAC#fetchAllAnalyticsWarningsByDate(com.sensus.mlc.analytics.model.request
	 * .AnalyticsRequest)
	 */
	@Override
	public InternalResultsResponse<AnalyticsGroupColumns> fetchAllAnalyticsWarningsByDate(
			AnalyticsRequest analyticsRequest)
	{
		InternalResultsResponse<AnalyticsGroupColumns> response = new InternalResultsResponse<AnalyticsGroupColumns>();

		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE7);
		paramMap.put(DATE_TYPE_ID, analyticsRequest.getAnalyticsDateTypeEnum().getValue());
		paramMap.put(TENANT_ID, analyticsRequest.getUserContext().getTenant().getId());
		paramMap.put(INIT_DATE, analyticsRequest.getInitialDate());
		paramMap.put(END_DATE, analyticsRequest.getEndDate());
		paramMap.put(RANGE_DATE_ID, null);

		if (!ValidationUtil.isNull(analyticsRequest.getAnalyticsRangeDateEnum()))
		{
			paramMap.put(RANGE_DATE_ID, analyticsRequest.getAnalyticsRangeDateEnum().getValue());
		}

		if (!ValidationUtil.isNull(analyticsRequest.getGroup()))
		{
			paramMap.put(GROUP_ID, analyticsRequest.getGroup().getId());
		}

		doQueryForList(getSqlSession(), FETCH_ALL_ANALYTICS_WARNINGS_BY_DATE, paramMap, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.analytics.dac.IAnalyticsDAC#fetchAllAnalyticsInstalledByDate(com.sensus.mlc.analytics.model.request
	 * .AnalyticsRequest)
	 */
	@Override
	public InternalResultsResponse<AnalyticsGroupColumns> fetchAllAnalyticsInstalledByDate(
			AnalyticsRequest analyticsRequest)
	{
		InternalResultsResponse<AnalyticsGroupColumns> response = new InternalResultsResponse<AnalyticsGroupColumns>();

		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE7);
		paramMap.put(DATE_TYPE_ID, analyticsRequest.getAnalyticsDateTypeEnum().getValue());
		paramMap.put(TENANT_ID, analyticsRequest.getUserContext().getTenant().getId());
		paramMap.put(INIT_DATE, analyticsRequest.getInitialDate());
		paramMap.put(END_DATE, analyticsRequest.getEndDate());
		paramMap.put(RANGE_DATE_ID, null);

		if (!ValidationUtil.isNull(analyticsRequest.getAnalyticsRangeDateEnum()))
		{
			paramMap.put(RANGE_DATE_ID, analyticsRequest.getAnalyticsRangeDateEnum().getValue());
		}

		if (!ValidationUtil.isNull(analyticsRequest.getGroup()))
		{
			paramMap.put(GROUP_ID, analyticsRequest.getGroup().getId());
		}

		doQueryForList(getSqlSession(), FETCH_ALL_ANALYTICS_INSTALLED_BY_DATE, paramMap, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.analytics.dac.IAnalyticsDAC#fetchAllAnalyticsConsumptionByDate(com.sensus.mlc.analytics.model.
	 * request.AnalyticsRequest)
	 */
	@Override
	public InternalResultsResponse<AnalyticsGroupColumns> fetchAllAnalyticsConsumptionByDate(
			AnalyticsRequest analyticsRequest)
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE7);
		paramMap.put(DATE_TYPE_ID, analyticsRequest.getAnalyticsDateTypeEnum().getValue());
		paramMap.put(TENANT_ID, analyticsRequest.getUserContext().getTenant().getId());
		paramMap.put(INIT_DATE, analyticsRequest.getInitialDate());
		paramMap.put(END_DATE, analyticsRequest.getEndDate());
		setMonthDaysAmount(analyticsRequest, paramMap);

		// Add restriction
		if (!ValidationUtil.isNull(analyticsRequest.getAllowedGroupIdList()))
		{
			paramMap.put(ALLOWED_GROUPS_TO_PROC,
					StringUtils.join(analyticsRequest.getAllowedGroupIdList(), COMMA_SEPARATOR));
		}

		if (!ValidationUtil.isNull(analyticsRequest.getGroup()))
		{
			paramMap.put(GROUP_ID, analyticsRequest.getGroup().getId());
		}

		InternalResultsResponse<AnalyticsGroupColumns> response = new InternalResultsResponse<AnalyticsGroupColumns>();
		doQueryForList(getSqlSession(), FETCH_ALL_ANALYTICS_CONSUPTION_BY_DATE, paramMap, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.analytics.dac.IAnalyticsDAC#fetchAllAnalyticsEcoModeByDate(com.sensus.mlc.analytics.model.request
	 * .AnalyticsRequest)
	 */
	@Override
	public InternalResultsResponse<AnalyticsGroupColumns> fetchAllAnalyticsEcoModeByDate(
			AnalyticsRequest analyticsRequest)
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE7);
		paramMap.put(TENANT_ID, analyticsRequest.getUserContext().getTenant().getId());
		paramMap.put(INIT_DATE, analyticsRequest.getInitialDate());
		paramMap.put(END_DATE, analyticsRequest.getEndDate());
		setMonthDaysAmount(analyticsRequest, paramMap);

		if (!ValidationUtil.isNull(analyticsRequest.getAllowedGroupIdList()))
		{
			paramMap.put(ALLOWED_GROUP_ID_LIST, analyticsRequest.getAllowedGroupIdList());
		}

		if (!ValidationUtil.isNull(analyticsRequest.getGroup()))
		{
			paramMap.put(GROUP_ID, analyticsRequest.getGroup().getId());
		}

		InternalResultsResponse<AnalyticsGroupColumns> response = new InternalResultsResponse<AnalyticsGroupColumns>();
		doQueryForList(getSqlSession(), FETCH_ALL_ANALYTICS_ECOMODE_BY_DATE, paramMap, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.analytics.dac.IAnalyticsDAC#fetchAllAnalyticsCarbonCreditsByDate(com.sensus.mlc.analytics.model
	 * .request.AnalyticsRequest, java.lang.Double, java.lang.Double, java.lang.Double)
	 */
	@Override
	public InternalResultsResponse<AnalyticsGroupColumns> fetchAllAnalyticsCarbonCreditsByDate(
			AnalyticsRequest analyticsRequest, Double carbonCreditsFactor, Double barrelsOfOilFactor,
			Double metricOfCOFactor)
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE9);
		paramMap.put(DATE_TYPE_ID, analyticsRequest.getAnalyticsDateTypeEnum().getValue());
		paramMap.put(TENANT_ID, analyticsRequest.getUserContext().getTenant().getId());
		paramMap.put(INIT_DATE, analyticsRequest.getInitialDate());
		paramMap.put(END_DATE, analyticsRequest.getEndDate());
		paramMap.put(CARBON_CREDITS_FACTOR, carbonCreditsFactor);
		paramMap.put(BARRELS_OF_OIL_FACTOR, barrelsOfOilFactor);
		paramMap.put(METRIC_OF_CO_FACTOR, metricOfCOFactor);
		setMonthDaysAmount(analyticsRequest, paramMap);

		if (!ValidationUtil.isNull(analyticsRequest.getAllowedGroupIdList()))
		{
			paramMap.put(ALLOWED_GROUPS_TO_PROC,
					StringUtils.join(analyticsRequest.getAllowedGroupIdList(), COMMA_SEPARATOR));
		}

		if (!ValidationUtil.isNull(analyticsRequest.getGroup()))
		{
			paramMap.put(GROUP_ID, analyticsRequest.getGroup().getId());
		}

		InternalResultsResponse<AnalyticsGroupColumns> response = new InternalResultsResponse<AnalyticsGroupColumns>();
		doQueryForList(getSqlSession(), FETCH_ALL_ANALYTICS_CARBON_CREDITS_BY_DATE, paramMap, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.analytics.dac.IAnalyticsDAC#fetchAllAnalyticsAlarmsByGroup(com.sensus.mlc.analytics.model.request
	 * .InquiryAnalyticsRequest)
	 */
	@Override
	public InternalResultsResponse<AnalyticsGroupAlarm> fetchAllAnalyticsAlarmsByGroup(
			InquiryAnalyticsRequest inquiryAnalyticsRequest)
	{

		InternalResultsResponse<AnalyticsGroupAlarm> response = new InternalResultsResponse<AnalyticsGroupAlarm>();

		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE11);
		paramMap.put(TENANT_ID, inquiryAnalyticsRequest.getUserContext().getTenant().getId());
		paramMap.put(PAGE_SIZE, inquiryAnalyticsRequest.getPageSize());
		paramMap.put(START_ROW, inquiryAnalyticsRequest.getStartRow());
		paramMap.put(START_PAGE, inquiryAnalyticsRequest.getStartPage());
		paramMap.put(INIT_DATE, inquiryAnalyticsRequest.getInitialDate());
		paramMap.put(END_DATE, inquiryAnalyticsRequest.getEndDate());
		paramMap.put(LAMP_FAILURE_ID, AlertSubTypeEnum.LAMP_FAILURE.getValue());
		paramMap.put(POWER_FAILURE_ID, AlertSubTypeEnum.POWER_FAILURE.getValue());
		paramMap.put(BOARD_FAILURE_ID, AlertSubTypeEnum.BOARD_FAILURE.getValue());
		paramMap.put(METROLOGY_ERROR_ID, AlertSubTypeEnum.METROLOGY_ERROR.getValue());
		paramMap.put(METROLOGY_COM_FAILURE_ID, AlertSubTypeEnum.METROLOGY_COM_FAILURE.getValue());
		paramMap.put(ORDER_BY, ORDER_BY_DEFAULT);
		paramMap.put(RANGE_DATE_ID, null);

		if (!ValidationUtil.isNull(inquiryAnalyticsRequest.getAnalyticsRangeDateEnum()))
		{
			paramMap.put(RANGE_DATE_ID, inquiryAnalyticsRequest.getAnalyticsRangeDateEnum().getValue());
		}

		if (!ValidationUtil.isNull(inquiryAnalyticsRequest.getAllowedGroupIdList()))
		{
			paramMap.put(ALLOWED_GROUP_ID_LIST, inquiryAnalyticsRequest.getAllowedGroupIdList());
		}

		if (!ValidationUtil.isNullOrEmpty(inquiryAnalyticsRequest.getSortExpressions()))
		{
			paramMap.put(ORDER_BY, inquiryAnalyticsRequest.getSortExpressions().get(0));
		}

		if (!ValidationUtil.isNull(inquiryAnalyticsRequest.getGroup()))
		{
			if (inquiryAnalyticsRequest.getGroup().getId() == 0)
			{
				paramMap.put(GROUP_ID, null);
			}
			else
			{
				paramMap.put(GROUP_ID, inquiryAnalyticsRequest.getGroup().getId());
			}
		}

		doQueryForList(getSqlSession(), FETCH_ANALYTICS_ALARM_BY_GROUP, paramMap, response);

		Integer totalRows = (Integer)doQueryForObject(getSqlSession(),
				PAGINATION_TOTAL_ROWS, paramMap);

		response.getResultsSetInfo().setTotalRowsAvailable(totalRows);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.analytics.dac.IAnalyticsDAC#fetchAllAnalyticsWarningsByGroup(com.sensus.mlc.analytics.model.request
	 * .InquiryAnalyticsRequest)
	 */
	@Override
	public InternalResultsResponse<AnalyticsGroupWarning> fetchAllAnalyticsWarningsByGroup(
			InquiryAnalyticsRequest inquiryAnalyticsRequest)
	{

		InternalResultsResponse<AnalyticsGroupWarning> response = new InternalResultsResponse<AnalyticsGroupWarning>();

		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE11);
		paramMap.put(TENANT_ID, inquiryAnalyticsRequest.getUserContext().getTenant().getId());
		paramMap.put(PAGE_SIZE, inquiryAnalyticsRequest.getPageSize());
		paramMap.put(START_ROW, inquiryAnalyticsRequest.getStartRow());
		paramMap.put(START_PAGE, inquiryAnalyticsRequest.getStartPage());
		paramMap.put(INIT_DATE, inquiryAnalyticsRequest.getInitialDate());
		paramMap.put(END_DATE, inquiryAnalyticsRequest.getEndDate());
		paramMap.put(POWER_SURGE_ID, AlertSubTypeEnum.POWER_SURGE_DETECTED.getValue());
		paramMap.put(BROWNOUT_DETECTED_ID, AlertSubTypeEnum.BROWN_OUT_DETECTED.getValue());
		paramMap.put(COMMUNICATION_FAIL_ID, AlertSubTypeEnum.COMMUNICATION_FAIL.getValue());
		paramMap.put(HIGH_CURRENT_ID, AlertSubTypeEnum.HIGH_CURRENT.getValue());
		paramMap.put(LOW_CURRENT_ID, AlertSubTypeEnum.LOW_CURRENT.getValue());
		paramMap.put(REVERSE_ENERGY_ID, AlertSubTypeEnum.REVERSE_ENERGY.getValue());
		paramMap.put(METROLOGY_RESET_ID, AlertSubTypeEnum.METROLOGY_RESET.getValue());
		paramMap.put(ORDER_BY, ORDER_BY_DEFAULT);
		paramMap.put(RANGE_DATE_ID, null);

		if (!ValidationUtil.isNull(inquiryAnalyticsRequest.getAnalyticsRangeDateEnum()))
		{
			paramMap.put(RANGE_DATE_ID, inquiryAnalyticsRequest.getAnalyticsRangeDateEnum().getValue());
		}

		if (!ValidationUtil.isNull(inquiryAnalyticsRequest.getAllowedGroupIdList()))
		{
			paramMap.put(ALLOWED_GROUP_ID_LIST, inquiryAnalyticsRequest.getAllowedGroupIdList());
		}

		if (!ValidationUtil.isNullOrEmpty(inquiryAnalyticsRequest.getSortExpressions()))
		{
			paramMap.put(ORDER_BY, inquiryAnalyticsRequest.getSortExpressions().get(0));
		}

		if (!ValidationUtil.isNull(inquiryAnalyticsRequest.getGroup()))
		{
			if (inquiryAnalyticsRequest.getGroup().getId() == 0)
			{
				paramMap.put(GROUP_ID, null);
			}
			else
			{
				paramMap.put(GROUP_ID, inquiryAnalyticsRequest.getGroup().getId());
			}
		}

		doQueryForList(getSqlSession(), FETCH_ANALYTICS_WARNINGS_BY_GROUP, paramMap, response);

		Integer totalRows = (Integer)doQueryForObject(getSqlSession(),
				PAGINATION_TOTAL_ROWS, paramMap);

		response.getResultsSetInfo().setTotalRowsAvailable(totalRows);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.analytics.dac.IAnalyticsDAC#fetchAllAnalyticsInstalledByGroup(com.sensus.mlc.analytics.model.request
	 * .InquiryAnalyticsRequest)
	 */
	@Override
	public InternalResultsResponse<AnalyticsGroupTypeLight> fetchAllAnalyticsInstalledByGroup(
			InquiryAnalyticsRequest inquiryAnalyticsRequest)
	{
		InternalResultsResponse<AnalyticsGroupTypeLight> response =
				new InternalResultsResponse<AnalyticsGroupTypeLight>();

		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE12);
		paramMap.put(TENANT_ID, inquiryAnalyticsRequest.getUserContext().getTenant().getId());
		paramMap.put(PAGE_SIZE, inquiryAnalyticsRequest.getPageSize());
		paramMap.put(START_ROW, inquiryAnalyticsRequest.getStartRow());
		paramMap.put(START_PAGE, inquiryAnalyticsRequest.getStartPage());
		paramMap.put(INIT_DATE, inquiryAnalyticsRequest.getInitialDate());
		paramMap.put(END_DATE, inquiryAnalyticsRequest.getEndDate());
		paramMap.put(INDUCTION_TYPE_ID, LightTypeEnum.INDUCTION.getValue());
		paramMap.put(LED_TYPE_ID, LightTypeEnum.LED.getValue());
		paramMap.put(OTHER_TYPE_ID, LightTypeEnum.OTHER.getValue());
		paramMap.put(ORDER_BY, ORDER_BY_DEFAULT);
		paramMap.put(RANGE_DATE_ID, null);

		if (!ValidationUtil.isNull(inquiryAnalyticsRequest.getAnalyticsRangeDateEnum()))
		{
			paramMap.put(RANGE_DATE_ID, inquiryAnalyticsRequest.getAnalyticsRangeDateEnum().getValue());
		}

		if (!ValidationUtil.isNull(inquiryAnalyticsRequest.getAllowedGroupIdList()))
		{
			paramMap.put(ALLOWED_GROUP_ID_LIST, inquiryAnalyticsRequest.getAllowedGroupIdList());
		}

		if (!ValidationUtil.isNullOrEmpty(inquiryAnalyticsRequest.getSortExpressions()))
		{
			paramMap.put(ORDER_BY, inquiryAnalyticsRequest.getSortExpressions().get(0));
		}

		if (!ValidationUtil.isNull(inquiryAnalyticsRequest.getGroup()))
		{
			if (inquiryAnalyticsRequest.getGroup().getId() == 0)
			{
				paramMap.put(GROUP_ID, null);
			}
			else
			{
				paramMap.put(GROUP_ID, inquiryAnalyticsRequest.getGroup().getId());
			}
		}

		doQueryForList(getSqlSession(), FETCH_ALL_ANALYTICS_GROUPS_INSTALLED, paramMap, response);

		Integer totalRows = (Integer)doQueryForObject(getSqlSession(),
				PAGINATION_TOTAL_ROWS, paramMap);
		response.getResultsSetInfo().setTotalRowsAvailable(totalRows);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.analytics.dac.IAnalyticsDAC#fetchAllAnalyticsConsumptionByGroup(com.sensus.mlc.analytics.model
	 * .request.InquiryAnalyticsRequest)
	 */
	@Override
	public InternalResultsResponse<AnalyticsGroupTypeLight> fetchAllAnalyticsConsumptionByGroup(
			InquiryAnalyticsRequest inquiryAnalyticsRequest)
	{
		InternalResultsResponse<AnalyticsGroupTypeLight> response =
				new InternalResultsResponse<AnalyticsGroupTypeLight>();

		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE12);
		paramMap.put(TENANT_ID, inquiryAnalyticsRequest.getUserContext().getTenant().getId());
		paramMap.put(PAGE_SIZE, inquiryAnalyticsRequest.getPageSize());
		paramMap.put(START_ROW, inquiryAnalyticsRequest.getStartRow());
		paramMap.put(START_PAGE, inquiryAnalyticsRequest.getStartPage());
		paramMap.put(INIT_DATE, inquiryAnalyticsRequest.getInitialDate());
		paramMap.put(END_DATE, inquiryAnalyticsRequest.getEndDate());
		paramMap.put(INDUCTION_TYPE_ID, LightTypeEnum.INDUCTION.getValue());
		paramMap.put(LED_TYPE_ID, LightTypeEnum.LED.getValue());
		paramMap.put(OTHER_TYPE_ID, LightTypeEnum.OTHER.getValue());
		paramMap.put(ORDER_BY, AnalyticsGroupOrderByEnum.ANALYTICS_GROUP_NAME.getValue());

		if (!ValidationUtil.isNull(inquiryAnalyticsRequest.getAllowedGroupIdList()))
		{
			List<String> groupIds =
					LCConvertUtil.convertAllowedGroupIdsToStringList(inquiryAnalyticsRequest.getAllowedGroupIdList());
			paramMap.put(ALLOWED_GROUPS_TO_PROC, StringUtils.join(groupIds, COMMA_SEPARATOR));
			paramMap.put(ALLOWED_GROUP_ID_LIST, inquiryAnalyticsRequest.getAllowedGroupIdList());
		}

		if (!ValidationUtil.isNullOrEmpty(inquiryAnalyticsRequest.getSortExpressions()))
		{
			paramMap.put(ORDER_BY, inquiryAnalyticsRequest.getSortExpressions().get(0));
		}

		if (!ValidationUtil.isNull(inquiryAnalyticsRequest.getGroup()))
		{
			if (inquiryAnalyticsRequest.getGroup().getId() == 0)
			{
				paramMap.put(GROUP_ID, null);
			}
			else
			{
				paramMap.put(GROUP_ID, inquiryAnalyticsRequest.getGroup().getId());
			}
		}

		doQueryForList(getSqlSession(), FETCH_ALL_ANALYTICS_GROUPS_CONSUMPTION, paramMap, response);

		Integer totalRows = (Integer)doQueryForObject(getSqlSession(),
				PAGINATION_TOTAL_ROWS, paramMap);
		response.getResultsSetInfo().setTotalRowsAvailable(totalRows);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.analytics.dac.IAnalyticsDAC#fetchAllAnalyticsEnergySavingsByGroup(com.sensus.mlc.analytics.model
	 * .request.InquiryAnalyticsRequest)
	 */
	@Override
	public InternalResultsResponse<AnalyticsGroupEcoMode> fetchAllAnalyticsEcoModeGroup(
			InquiryAnalyticsRequest inquiryAnalyticsRequest)
	{
		InternalResultsResponse<AnalyticsGroupEcoMode> response =
				new InternalResultsResponse<AnalyticsGroupEcoMode>();

		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE7);
		paramMap.put(TENANT_ID, inquiryAnalyticsRequest.getUserContext().getTenant().getId());
		paramMap.put(PAGE_SIZE, inquiryAnalyticsRequest.getPageSize());
		paramMap.put(START_ROW, inquiryAnalyticsRequest.getStartRow());
		paramMap.put(START_PAGE, inquiryAnalyticsRequest.getStartPage());
		paramMap.put(INIT_DATE, inquiryAnalyticsRequest.getInitialDate());
		paramMap.put(END_DATE, inquiryAnalyticsRequest.getEndDate());
		paramMap.put(ORDER_BY, ORDER_BY_DEFAULT);

		if (!ValidationUtil.isNull(inquiryAnalyticsRequest.getAllowedGroupIdList()))
		{
			paramMap.put(ALLOWED_GROUP_ID_LIST, inquiryAnalyticsRequest.getAllowedGroupIdList());
		}

		if (!ValidationUtil.isNullOrEmpty(inquiryAnalyticsRequest.getSortExpressions()))
		{
			paramMap.put(ORDER_BY, inquiryAnalyticsRequest.getSortExpressions().get(0));
		}

		if (!ValidationUtil.isNull(inquiryAnalyticsRequest.getGroup()))
		{
			if (!ValidationUtil.isNull(inquiryAnalyticsRequest.getGroup()))
			{
				if (inquiryAnalyticsRequest.getGroup().getId() != 0)
				{
					paramMap.put(GROUP_ID, inquiryAnalyticsRequest.getGroup().getId());
				}
			}
		}

		doQueryForList(getSqlSession(), FETCH_ANALYTICS_ECOMODE_BY_GROUP, paramMap, response);

		Integer totalRows = (Integer)doQueryForObject(getSqlSession(),
				PAGINATION_TOTAL_ROWS, paramMap);
		response.getResultsSetInfo().setTotalRowsAvailable(totalRows);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.analytics.dac.IAnalyticsDAC#fetchAllAnalyticsCarbonCreditsByGroup(com.sensus.mlc.analytics.model
	 * .request.InquiryAnalyticsRequest, java.lang.Double, java.lang.Double, java.lang.Double)
	 */
	@Override
	public InternalResultsResponse<AnalyticsGroupCarbonCredits> fetchAllAnalyticsCarbonCreditsByGroup(
			InquiryAnalyticsRequest inquiryAnalyticsRequest, Double carbonCreditsFactor, Double barrelsOfOilFactor,
			Double metricOfCOFactor)
	{
		InternalResultsResponse<AnalyticsGroupCarbonCredits> response =
				new InternalResultsResponse<AnalyticsGroupCarbonCredits>();

		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE11);
		paramMap.put(TENANT_ID, inquiryAnalyticsRequest.getUserContext().getTenant().getId());
		paramMap.put(PAGE_SIZE, inquiryAnalyticsRequest.getPageSize());
		paramMap.put(START_ROW, inquiryAnalyticsRequest.getStartRow());
		paramMap.put(START_PAGE, inquiryAnalyticsRequest.getStartPage());
		paramMap.put(INIT_DATE, inquiryAnalyticsRequest.getInitialDate());
		paramMap.put(END_DATE, inquiryAnalyticsRequest.getEndDate());
		paramMap.put(CARBON_CREDITS_FACTOR, carbonCreditsFactor);
		paramMap.put(BARRELS_OF_OIL_FACTOR, barrelsOfOilFactor);
		paramMap.put(METRIC_OF_CO_FACTOR, metricOfCOFactor);
		paramMap.put(ORDER_BY, AnalyticsGroupOrderByEnum.ANALYTICS_GROUP_NAME.getValue());
		if (!ValidationUtil.isNull(inquiryAnalyticsRequest.getAllowedGroupIdList()))
		{
			List<String> groupIds =
					LCConvertUtil.convertAllowedGroupIdsToStringList(inquiryAnalyticsRequest.getAllowedGroupIdList());
			paramMap.put(ALLOWED_GROUPS_TO_PROC, StringUtils.join(groupIds, COMMA_SEPARATOR));
			paramMap.put(ALLOWED_GROUP_ID_LIST, inquiryAnalyticsRequest.getAllowedGroupIdList());
		}

		if (!ValidationUtil.isNullOrEmpty(inquiryAnalyticsRequest.getSortExpressions()))
		{
			paramMap.put(ORDER_BY, inquiryAnalyticsRequest.getSortExpressions().get(0));
		}

		if (!ValidationUtil.isNull(inquiryAnalyticsRequest.getGroup()))
		{
			if (inquiryAnalyticsRequest.getGroup().getId() == 0)
			{
				paramMap.put(GROUP_ID, null);
			}
			else
			{
				paramMap.put(GROUP_ID, inquiryAnalyticsRequest.getGroup().getId());
			}
		}
		doQueryForList(getSqlSession(), FETCH_ANALYTICS_CARBON_CREDITS_BY_GROUP, paramMap, response);

		Integer totalRows = (Integer)doQueryForObject(getSqlSession(),
				PAGINATION_TOTAL_ROWS, paramMap);
		response.getResultsSetInfo().setTotalRowsAvailable(totalRows);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.analytics.dac.IAnalyticsDAC#fetchDashboarHeader(com.sensus.mlc.analytics.model.request.
	 * AnalyticsRequest, java.lang.Double)
	 */
	@Override
	public InternalResultsResponse<AnalyticsGroupColumns> fetchDashboarHeader(AnalyticsRequest analyticsRequest,
			Double carbonCreditsFactor)
	{
		InternalResultsResponse<AnalyticsGroupColumns> response = new InternalResultsResponse<AnalyticsGroupColumns>();

		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE4);
		paramMap.put(TENANT_ID, analyticsRequest.getUserContext().getTenant().getId());
		paramMap.put(CARBON_CREDITS_FACTOR, carbonCreditsFactor);
		paramMap.put(ALLOWED_GROUPS_TO_PROC, null);
		if (!ValidationUtil.isNull(analyticsRequest.getAllowedGroupIdList()))
		{
			List<String> groupIds =
					LCConvertUtil.convertAllowedGroupIdsToStringList(analyticsRequest.getAllowedGroupIdList());
			paramMap.put(ALLOWED_GROUPS_TO_PROC, StringUtils.join(groupIds, COMMA_SEPARATOR));
		}

		doQueryForList(getSqlSession(), FETCH_DASH_BOARD_HEADER, paramMap, response);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.analytics.dac.IAnalyticsDAC#fetchAllAnalyticsGroup(com.sensus.mlc.analytics.model.request.
	 * AnalyticsRequest)
	 */
	@Override
	public InternalResultsResponse<AnalyticsGroup> fetchAllAnalyticsGroup(AnalyticsRequest analyticsRequest)
	{
		InternalResultsResponse<AnalyticsGroup> response = new InternalResultsResponse<AnalyticsGroup>();

		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE2);
		paramMap.put(TENANT_ID, analyticsRequest.getUserContext().getTenant().getId());
		paramMap.put(ALLOWED_GROUP_ID_LIST, analyticsRequest.getAllowedGroupIdList());

		doQueryForList(getSqlSession(), FETCH_ALL_ANALYTCS, paramMap, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.analytics.dac.IAnalyticsDAC#generateFileCSV(com.sensus.mlc.analytics.model.request.
	 * InquiryAnalyticsRequest)
	 */
	@Override
	public InternalResponse generateFileCSV(InquiryAnalyticsRequest inquiryAnalyticsRequest)
	{
		InternalResponse response = new InternalResponse();

		CSVFileGenerator.create(inquiryAnalyticsRequest.getFileName(), new AnalyticsGroupCSVDataSource(
				inquiryAnalyticsRequest), response);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.process.dao.IAnalyticsDAC#calculateDashboardResume()
	 */
	@Override
	public InternalResultsResponse<String> calculateDashboardResume(Double carbonCreditsFactor, Tenant tenant,
			AnalyticsGroup analyticsGroup)
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE2);
		paramMap.put(CARBON_CREDITS_FACTOR, carbonCreditsFactor);
		if (!ValidationUtil.isNull(analyticsGroup))
		{
			paramMap.put(GROUP_ID, analyticsGroup.getId());
		}
		paramMap.put(TENANT_ID, tenant.getId());

		InternalResultsResponse<String> response = new InternalResultsResponse<String>();
		doQueryForList(getSqlSession(), CALCULATE_DASH_BOARD_RESUME, paramMap, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.analytics.dac.IAnalyticsDAC#fetchAllGroupsByTenantForDashboard(com.sensus.mlc.tenant.model.Tenant)
	 */
	@Override
	public InternalResultsResponse<AnalyticsGroup> fetchAllGroupsByTenantForDashboard(Tenant tenant)
	{
		InternalResultsResponse<AnalyticsGroup> response = new InternalResultsResponse<AnalyticsGroup>();

		doQueryForList(getSqlSession(), FETCH_ALL_GROUPS_BY_TENANT_FOR_DASHBOARD, tenant.getId(),
				response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.analytics.dac.IAnalyticsDAC#deleteDashboarResumeByTenant(com.sensus.mlc.tenant.model.Tenant)
	 */
	@Override
	public InternalResponse deleteDashboarResumeByTenant(Tenant tenant)
	{

		InternalResultsResponse<AnalyticsGroup> response = new InternalResultsResponse<AnalyticsGroup>();
		doQueryForList(getSqlSession(), DELETE_DASHBOARD_RESUME, tenant.getId(), response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.analytics.dac.IAnalyticsDAC#updateAnalyticsAlarmWarning(java.lang.Integer, java.lang.Integer,
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public InternalResponse updateAnalyticsAlarmWarning(AnalyticsRequest request)
	{
		Integer alertSubtypeValue = request.getAlertSubtypeValue();
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE5);
		paramMap.put(NOTIFICATION_HISTORY_ID, request.getNotificationHistory().getId());
		paramMap.put(ALERT_SUBTYPE_ID, alertSubtypeValue);
		paramMap.put(OPERATOR, request.getOperator().getValue());
		paramMap.put(CREATE_USER, request.getUserContext().getUserId());
		paramMap.put(ALERT_TYPE, TYPE_ALARM);
		if (request.getAlertSubtype().getAlertType() == AlertTypeEnum.WARNING)
		{
			paramMap.put(ALERT_TYPE, TYPE_WARNING);
		}

		InternalResponse response = new InternalResponse();
		doUpdate(getSqlSession(), UPDATE_ANALYTICS_ALARMS_WARNINGS, paramMap, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.lc.analytics.dac.IAnalyticsDAC#insertAnalyticsSummarized(com.sensus.lc.analytics.model.request.
	 * AnalyticsRequest)
	 */
	@Override
	public InternalResponse insertAnalyticsSummarized(AnalyticsRequest request)
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE4);
		InternalResponse response = new InternalResponse();

		paramMap.put(ANALYTICS_GROUP_ID, request.getGroup().getId());
		paramMap.put(TENANT_ID, request.getUserContext().getTenant().getId());

		for (Integer rangeDateId : setAnalyticsRangeDate())
		{
			paramMap.put(RANGE_DATE, rangeDateId);
			doInsert(getSqlSession(), INSERT_ANALYTICS_SUMMARIZED, paramMap, response);
		}
		return response;
	}

	/**
	 * Sets the month days amount.
	 * 
	 * @param request the request
	 * @param paramMap the param map
	 */
	private void setMonthDaysAmount(AnalyticsRequest request, HashMap<String, Object> paramMap)
	{
		long monthDaysAmount = LCDateUtil.getMonthDaysAmount(request.getEndDate());
		if (ValidationUtil.isNullOrZero(monthDaysAmount))
		{

			paramMap.put(MONTH_DAYS, MONTH_DAYS_AMOUNT);
			return;
		}

		paramMap.put(MONTH_DAYS, monthDaysAmount);
	}

	private List<Integer> setAnalyticsRangeDate()
	{
		List<Integer> list = new ArrayList<>();
		list.add(AnalyticsRangeDateEnum.WEEK.getValue());
		list.add(AnalyticsRangeDateEnum.ONE_MONTH.getValue());
		list.add(AnalyticsRangeDateEnum.THREE_MONTH.getValue());
		list.add(AnalyticsRangeDateEnum.YTD.getValue());
		list.add(AnalyticsRangeDateEnum.ONE_YEAR.getValue());
		return list;
	}
}
