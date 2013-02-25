package com.sensus.mlc.analytics.dac.mybatis;

import static com.sensus.common.util.SensusMyBatisDacHelper.doQueryForList;
import static com.sensus.common.util.SensusMyBatisDacHelper.doQueryForObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.xwork.StringUtils;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.util.CSVFileGenerator;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.mlc.analytics.csv.AnalyticsGroupCSVDataSource;
import com.sensus.mlc.analytics.dac.IAnalyticsDAC;
import com.sensus.mlc.analytics.model.AnalyticsGroup;
import com.sensus.mlc.analytics.model.AnalyticsGroupAlarm;
import com.sensus.mlc.analytics.model.AnalyticsGroupCarbonCredits;
import com.sensus.mlc.analytics.model.AnalyticsGroupColumns;
import com.sensus.mlc.analytics.model.AnalyticsGroupEcoMode;
import com.sensus.mlc.analytics.model.AnalyticsGroupOrderByEnum;
import com.sensus.mlc.analytics.model.AnalyticsGroupTypeLight;
import com.sensus.mlc.analytics.model.AnalyticsGroupWarning;
import com.sensus.mlc.analytics.model.DashboardViewTypeEnum;
import com.sensus.mlc.analytics.model.request.AnalyticsRequest;
import com.sensus.mlc.analytics.model.request.InquiryAnalyticsRequest;
import com.sensus.mlc.base.util.LCConvertUtil;
import com.sensus.mlc.smartpoint.model.LightTypeEnum;
import com.sensus.mlc.smartpoint.model.Light;
import com.sensus.mlc.smartpoint.model.StatusExceptionTypeEnum;
import com.sensus.mlc.tenant.model.Tenant;

/**
 * The Class AnalyticsDACImpl.
 */
public class AnalyticsDACImpl extends SqlSessionDaoSupport implements IAnalyticsDAC
{
	/** The comma separator. */
	private static final String COMMA_SEPARATOR = ",";

	/** The Constant PARAMSIZE2. */
	private static final Integer PARAMSIZE2 = 2;

	/** The Constant PARAMSIZE4. */
	private static final Integer PARAMSIZE4 = 4;

	/** The Constant PARAMSIZE7. */
	private static final Integer PARAMSIZE7 = 7;

	/** The Constant PARAMSIZE9. */
	private static final Integer PARAMSIZE9 = 9;

	/** The Constant PARAMSIZE11. */
	private static final Integer PARAMSIZE11 = 11;

	/** The Constant PARAMSIZE12. */
	private static final Integer PARAMSIZE12 = 12;

	/** The Constant PARAMSIZE5. */
	private static final Integer PARAMSIZE5 = 5;

	/** The Constant GROUP_ID. */
	private static final String GROUP_ID = "group_id";

	/** The Constant INIT_DATE. */
	private static final String INIT_DATE = "init_date";

	/** The Constant END_DATE. */
	private static final String END_DATE = "end_date";

	/** The Constant TENANT_ID. */
	private static final String TENANT_ID = "tenant_id";

	/** The Constant DATE_TYPE_ID. */
	private static final String DATE_TYPE_ID = "date_type_id";

	/** The Constant LAMP_FAILURE_ID. */
	private static final String LAMP_FAILURE_ID = "lamp_failure_id";

	/** The Constant POWER_FAILURE_ID. */
	private static final String POWER_FAILURE_ID = "power_failure_id";

	/** The Constant POWER_SURGE_ID. */
	private static final String POWER_SURGE_ID = "power_surge_id";

	/** The Constant BOARD_FAILURE_ID. */
	private static final String BOARD_FAILURE_ID = "board_failure_id";

	/** The Constant METROLOGY_ERROR_ID. */
	private static final String METROLOGY_ERROR_ID = "metrology_error_id";

	/** The Constant METROLOGY_COM_FAILURE_ID. */
	private static final String METROLOGY_COM_FAILURE_ID = "metrology_com_failure_id";

	/** The Constant HIGH_CURRENT_ID. */
	private static final String HIGH_CURRENT_ID = "high_current_id";

	/** The Constant LOW_CURRENT_ID. */
	private static final String LOW_CURRENT_ID = "low_current_id";

	/** The Constant REVERSE_ENERGY_ID. */
	private static final String REVERSE_ENERGY_ID = "reverse_energy_id";

	/** The Constant METROLOGY_RESET_ID. */
	private static final String METROLOGY_RESET_ID = "metrology_reset_id";

	/** The Constant BROWNOUT_DETECTED_ID. */
	private static final String BROWNOUT_DETECTED_ID = "brownout_detected_id";

	/** The Constant INDUCTION_TYPE_ID. */
	private static final String INDUCTION_TYPE_ID = "induction_type_id";

	/** The Constant COMMUNICATION_FAIL_ID. */
	private static final String COMMUNICATION_FAIL_ID = "communication_fail_id";

	/** The Constant LED_TYPE_ID. */
	private static final String LED_TYPE_ID = "led_type_id";

	/** The Constant OTHER_TYPE_ID. */
	private static final String OTHER_TYPE_ID = "other_type_id";

	/** The Constant ALARM_TYPE_ID. */
	private static final String ALARM_TYPE_ID = "alarm_type_id";

	/** The Constant ALARM_SUBTYPE_ID. */
	private static final String ALARM_SUBTYPE_ID = "alarm_subtype_id";

	/** The Constant CARBON_CREDITS_FACTOR. */
	private static final String CARBON_CREDITS_FACTOR = "carbon_credits_factor";

	/** The Constant BARRELS_OF_OIL_FACTOR. */
	private static final String BARRELS_OF_OIL_FACTOR = "barrels_of_oil_factor";

	/** The Constant METRIC_OF_CO_FACTOR. */
	private static final String METRIC_OF_CO_FACTOR = "metric_of_co_factor";

	/** The Constant ORDER_BY. */
	private static final String ORDER_BY = "orderBy";

	/** The Constant PAGE_SIZE. */
	private static final String PAGE_SIZE = "pageSize";

	/** The Constant START_ROW. */
	private static final String START_ROW = "startRow";

	/** The Constant START_PAGE. */
	private static final String START_PAGE = "startPage";

	/** The Constant PAGINATION_TOTAL_ROWS. */
	private static final String PAGINATION_TOTAL_ROWS = "Analytics.paginationTotalRows";

	/** The Constant VIEW_MODE_TODAY. */
	private static final String VIEW_MODE_TODAY = "view_mode_today";

	/** The Constant ANALYTICS. */
	private static final String ANALYTICS = "Analytics.";

	/** The Constant FETCH_ANALYTICS_ALARM_BY_STATUS_ID. */
	private static final String FETCH_ANALYTICS_ALARM_BY_STATUS_ID = ANALYTICS + "fetchAnalyticsAlarmsByStatusId";

	/** The Constant FETCH_ANALYTICS_ALERT_BY_TYPE. */
	private static final String FETCH_ANALYTICS_ALERT_BY_TYPE = ANALYTICS + "fetchAnalyticsAlertsByType";

	/** The Constant FETCH_DASH_BOARD_RESUME. */
	private static final String FETCH_DASH_BOARD_RESUME = ANALYTICS + "fetchDashboardResume";

	/** The Constant FETCH_ALL_ANALYTICS_ALARM_BY_DATE. */
	private static final String FETCH_ALL_ANALYTICS_ALARM_BY_DATE = ANALYTICS + "fetchAllAnalyticsAlarmsByDate";

	/** The Constant FETCH_ALL_ANALYTICS_WARNINGS_BY_DATE. */
	private static final String FETCH_ALL_ANALYTICS_WARNINGS_BY_DATE = ANALYTICS + "fetchAllAnalyticsWarningsByDate";

	/** The Constant FETCH_ALL_ANALYTICS_INSTALLED_BY_DATE. */
	private static final String FETCH_ALL_ANALYTICS_INSTALLED_BY_DATE = ANALYTICS + "fetchAllAnalyticsInstalledByDate";

	/** The Constant FETCH_ALL_ANALYTICS_CONSUPTION_BY_DATE. */
	private static final String FETCH_ALL_ANALYTICS_CONSUPTION_BY_DATE = ANALYTICS
			+ "fetchAllAnalyticsConsumptionByDate";

	/** The Constant FETCH_ALL_ANALYTICS_ENERGY_SAVINGS_BY_DATE. */
	private static final String FETCH_ALL_ANALYTICS_ECOMODE_BY_DATE = ANALYTICS
			+ "fetchAllAnalyticsEcoModeByDate";

	/** The Constant FETCH_ALL_ANALYTICS_CARBON_CREDITS_BY_DATE. */
	private static final String FETCH_ALL_ANALYTICS_CARBON_CREDITS_BY_DATE = ANALYTICS
			+ "fetchAllAnalyticsCarbonCreditsByDate";

	/** The Constant FETCH_ANALYTICS_ALARM_BY_GROUP. */
	private static final String FETCH_ANALYTICS_ALARM_BY_GROUP = ANALYTICS + "fetchAnalyticsAlarmsByGroup";

	/** The Constant FETCH_ANALYTICS_WARNINGS_BY_GROUP. */
	private static final String FETCH_ANALYTICS_WARNINGS_BY_GROUP = ANALYTICS + "fetchAnalyticsWarningsByGroup";

	/** The Constant FETCH_ALL_ANALYTICS_GROUPS_INSTALLED. */
	private static final String FETCH_ALL_ANALYTICS_GROUPS_INSTALLED = ANALYTICS + "fetchAllAnalyticsGroupsInstalled";

	/** The Constant FETCH_ALL_ANALYTICS_GROUPS_CONSUMPTION. */
	private static final String FETCH_ALL_ANALYTICS_GROUPS_CONSUMPTION = ANALYTICS
			+ "fetchAllAnalyticsGroupsConsumption";

	/** The Constant FETCH_ANALYTICS_CARBON_CREDITS_BY_GROUP. */
	private static final String FETCH_ANALYTICS_CARBON_CREDITS_BY_GROUP = ANALYTICS
			+ "fetchAnalyticsCarbonCreditsByGroup";

	/** The Constant FETCH_DASH_BOARD_HEADER. */
	private static final String FETCH_DASH_BOARD_HEADER = ANALYTICS + "fetchDashboardHeader";

	/** The Constant FETCH_ALL_ANALYTCS. */
	private static final String FETCH_ALL_ANALYTCS = ANALYTICS + "fetchAllAnalytcsGroup";

	/** The Constant CALCULATE_DASH_BOARD_RESUME. */
	private static final String CALCULATE_DASH_BOARD_RESUME = ANALYTICS + "calculateDashboardResume";

	/** The Constant ALLOWED_GROUP_ID_LIST. */
	private static final String ALLOWED_GROUP_ID_LIST = "allowedGroupIdList";

	/** The Constant ALLOWED_GROUPS_TO_PROC. */
	private static final String ALLOWED_GROUPS_TO_PROC = "allowedGroupsToProc";

	/** The Constant ORDER_BY_DEFAULT. */
	private static final Object ORDER_BY_DEFAULT = "total desc";

	/** The Constant FETCH_ANALYTICS_ECOMODE_BY_GROUP. */
	private static final String FETCH_ANALYTICS_ECOMODE_BY_GROUP = ANALYTICS + "fetchAnalyticsEcoModeByGroup";

	/** The Constant DELETE_DASHBOARD_RESUME. */
	private static final String DELETE_DASHBOARD_RESUME = ANALYTICS + "deleteDashboardResume";

	/** The Constant FETCH_ALL_GROUPS_BY_TENANT_FOR_DASHBOARD. */
	private static final String FETCH_ALL_GROUPS_BY_TENANT_FOR_DASHBOARD = ANALYTICS
			+ "fetchAllGroupsByTenantForDashboard";

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

		InternalResultsResponse<Light> response = new InternalResultsResponse<Light>();
		paramMap.put(TENANT_ID, analyticsRequest.getUserContext().getTenant().getId());
		paramMap.put(ALARM_TYPE_ID, StatusExceptionTypeEnum.LAMP_FAILURE.getValue());
		paramMap.put(ALARM_SUBTYPE_ID, analyticsRequest.getStatusExceptionTypeEnum().getValue());
		paramMap.put(ALLOWED_GROUP_ID_LIST, analyticsRequest.getAllowedGroupIdList());

		doQueryForList(getSqlSession(), FETCH_ANALYTICS_ALARM_BY_STATUS_ID,
				paramMap, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.analytics.dac.IAnalyticsDAC#fetchAnalyticsAlertsByType(com.sensus.mlc.analytics.model.request.
	 * AnalyticsRequest)
	 */
	@Override
	public InternalResultsResponse<Map<String, Integer>> fetchAnalyticsAlertsByType(AnalyticsRequest analyticsRequest)
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE2);
		paramMap.put(TENANT_ID, analyticsRequest.getUserContext().getTenant().getId());
		paramMap.put(ALLOWED_GROUP_ID_LIST, analyticsRequest.getAllowedGroupIdList());

		InternalResultsResponse<Map<String, Integer>> response = new InternalResultsResponse<Map<String, Integer>>();

		doQueryForList(getSqlSession(), FETCH_ANALYTICS_ALERT_BY_TYPE,
				paramMap, response);
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
		InternalResultsResponse<AnalyticsGroupColumns> response = new InternalResultsResponse<AnalyticsGroupColumns>();

		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE5);
		paramMap.put(TENANT_ID, analyticsRequest.getTenant().getId());
		paramMap.put(VIEW_MODE_TODAY, DashboardViewTypeEnum.TODAY.getValue());
		paramMap.put(INIT_DATE, analyticsRequest.getInitialDate());
		paramMap.put(END_DATE, analyticsRequest.getEndDate());

		// Add restriction
		if (!ValidationUtil.isNull(analyticsRequest.getAllowedGroupIdList()))
		{
			paramMap.put(ALLOWED_GROUPS_TO_PROC,
					StringUtils.join(analyticsRequest.getAllowedGroupIdList(), AnalyticsDACImpl.COMMA_SEPARATOR));
			paramMap.put(ALLOWED_GROUP_ID_LIST, analyticsRequest.getAllowedGroupIdList());
		}

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
		paramMap.put(TENANT_ID, analyticsRequest.getTenant().getId());
		paramMap.put(INIT_DATE, analyticsRequest.getInitialDate());
		paramMap.put(END_DATE, analyticsRequest.getEndDate());

		// Add restriction
		if (!ValidationUtil.isNull(analyticsRequest.getAllowedGroupIdList()))
		{
			paramMap.put(ALLOWED_GROUPS_TO_PROC, StringUtils.join(analyticsRequest.getAllowedGroupIdList(), COMMA_SEPARATOR));
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
		paramMap.put(TENANT_ID, analyticsRequest.getTenant().getId());
		paramMap.put(INIT_DATE, analyticsRequest.getInitialDate());
		paramMap.put(END_DATE, analyticsRequest.getEndDate());

		// Add restriction
		if (!ValidationUtil.isNull(analyticsRequest.getAllowedGroupIdList()))
		{
			paramMap.put(ALLOWED_GROUPS_TO_PROC, StringUtils.join(analyticsRequest.getAllowedGroupIdList(), COMMA_SEPARATOR));
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
		paramMap.put(TENANT_ID, analyticsRequest.getTenant().getId());
		paramMap.put(INIT_DATE, analyticsRequest.getInitialDate());
		paramMap.put(END_DATE, analyticsRequest.getEndDate());

		// Add restriction
		if (!ValidationUtil.isNull(analyticsRequest.getAllowedGroupIdList()))
		{
			paramMap.put(ALLOWED_GROUPS_TO_PROC, StringUtils.join(analyticsRequest.getAllowedGroupIdList(), COMMA_SEPARATOR));
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
		InternalResultsResponse<AnalyticsGroupColumns> response = new InternalResultsResponse<AnalyticsGroupColumns>();

		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE7);
		paramMap.put(DATE_TYPE_ID, analyticsRequest.getAnalyticsDateTypeEnum().getValue());
		paramMap.put(TENANT_ID, analyticsRequest.getTenant().getId());
		paramMap.put(INIT_DATE, analyticsRequest.getInitialDate());
		paramMap.put(END_DATE, analyticsRequest.getEndDate());

		// Add restriction
		if (!ValidationUtil.isNull(analyticsRequest.getAllowedGroupIdList()))
		{
			paramMap.put(ALLOWED_GROUPS_TO_PROC, StringUtils.join(analyticsRequest.getAllowedGroupIdList(), COMMA_SEPARATOR));
		}

		if (!ValidationUtil.isNull(analyticsRequest.getGroup()))
		{
			paramMap.put(GROUP_ID, analyticsRequest.getGroup().getId());
		}

		doQueryForList(getSqlSession(), FETCH_ALL_ANALYTICS_CONSUPTION_BY_DATE, paramMap,
				response);
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
		InternalResultsResponse<AnalyticsGroupColumns> response = new InternalResultsResponse<AnalyticsGroupColumns>();

		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE7);

		paramMap.put(TENANT_ID, analyticsRequest.getTenant().getId());
		paramMap.put(INIT_DATE, analyticsRequest.getInitialDate());
		paramMap.put(END_DATE, analyticsRequest.getEndDate());

		if (!ValidationUtil.isNull(analyticsRequest.getAllowedGroupIdList()))
		{
			paramMap.put(ALLOWED_GROUP_ID_LIST, analyticsRequest.getAllowedGroupIdList());
		}

		if (!ValidationUtil.isNull(analyticsRequest.getGroup()))
		{
			paramMap.put(GROUP_ID, analyticsRequest.getGroup().getId());
		}

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
		InternalResultsResponse<AnalyticsGroupColumns> response = new InternalResultsResponse<AnalyticsGroupColumns>();

		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE9);
		paramMap.put(DATE_TYPE_ID, analyticsRequest.getAnalyticsDateTypeEnum().getValue());
		paramMap.put(TENANT_ID, analyticsRequest.getTenant().getId());
		paramMap.put(INIT_DATE, analyticsRequest.getInitialDate());
		paramMap.put(END_DATE, analyticsRequest.getEndDate());
		paramMap.put(CARBON_CREDITS_FACTOR, carbonCreditsFactor);
		paramMap.put(BARRELS_OF_OIL_FACTOR, barrelsOfOilFactor);
		paramMap.put(METRIC_OF_CO_FACTOR, metricOfCOFactor);

		if (!ValidationUtil.isNull(analyticsRequest.getAllowedGroupIdList()))
		{
			paramMap.put(ALLOWED_GROUPS_TO_PROC, StringUtils.join(analyticsRequest.getAllowedGroupIdList(), COMMA_SEPARATOR));
		}

		if (!ValidationUtil.isNull(analyticsRequest.getGroup()))
		{
			paramMap.put(GROUP_ID, analyticsRequest.getGroup().getId());
		}

		doQueryForList(getSqlSession(), FETCH_ALL_ANALYTICS_CARBON_CREDITS_BY_DATE,
				paramMap, response);
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
		paramMap.put(TENANT_ID, inquiryAnalyticsRequest.getTenant().getId());
		paramMap.put(PAGE_SIZE, inquiryAnalyticsRequest.getPageSize());
		paramMap.put(START_ROW, inquiryAnalyticsRequest.getStartRow());
		paramMap.put(START_PAGE, inquiryAnalyticsRequest.getStartPage());
		paramMap.put(INIT_DATE, inquiryAnalyticsRequest.getInitialDate());
		paramMap.put(END_DATE, inquiryAnalyticsRequest.getEndDate());
		paramMap.put(LAMP_FAILURE_ID, StatusExceptionTypeEnum.LAMP_FAILURE.getValue());
		paramMap.put(POWER_FAILURE_ID, StatusExceptionTypeEnum.POWER_FAILURE.getValue());
		paramMap.put(BOARD_FAILURE_ID, StatusExceptionTypeEnum.BOARD_FAILURE.getValue());
		paramMap.put(METROLOGY_ERROR_ID, StatusExceptionTypeEnum.METROLOGY_ERROR.getValue());
		paramMap.put(METROLOGY_COM_FAILURE_ID, StatusExceptionTypeEnum.METROLOGY_COM_FAILURE.getValue());
		paramMap.put(ORDER_BY, ORDER_BY_DEFAULT);
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
		paramMap.put(TENANT_ID, inquiryAnalyticsRequest.getTenant().getId());
		paramMap.put(PAGE_SIZE, inquiryAnalyticsRequest.getPageSize());
		paramMap.put(START_ROW, inquiryAnalyticsRequest.getStartRow());
		paramMap.put(START_PAGE, inquiryAnalyticsRequest.getStartPage());
		paramMap.put(INIT_DATE, inquiryAnalyticsRequest.getInitialDate());
		paramMap.put(END_DATE, inquiryAnalyticsRequest.getEndDate());
		paramMap.put(POWER_SURGE_ID, StatusExceptionTypeEnum.POWER_SURGE_DETECTED.getValue());
		paramMap.put(BROWNOUT_DETECTED_ID, StatusExceptionTypeEnum.BROWN_OUT_DETECTED.getValue());
		paramMap.put(COMMUNICATION_FAIL_ID, StatusExceptionTypeEnum.COMMUNICATION_FAIL.getValue());
		paramMap.put(HIGH_CURRENT_ID, StatusExceptionTypeEnum.HIGH_CURRENT.getValue());
		paramMap.put(LOW_CURRENT_ID, StatusExceptionTypeEnum.LOW_CURRENT.getValue());
		paramMap.put(REVERSE_ENERGY_ID, StatusExceptionTypeEnum.REVERSE_ENERGY.getValue());
		paramMap.put(METROLOGY_RESET_ID, StatusExceptionTypeEnum.METROLOGY_RESET.getValue());
		paramMap.put(ORDER_BY, ORDER_BY_DEFAULT);
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
		paramMap.put(TENANT_ID, inquiryAnalyticsRequest.getTenant().getId());
		paramMap.put(PAGE_SIZE, inquiryAnalyticsRequest.getPageSize());
		paramMap.put(START_ROW, inquiryAnalyticsRequest.getStartRow());
		paramMap.put(START_PAGE, inquiryAnalyticsRequest.getStartPage());
		paramMap.put(INIT_DATE, inquiryAnalyticsRequest.getInitialDate());
		paramMap.put(END_DATE, inquiryAnalyticsRequest.getEndDate());
		paramMap.put(INDUCTION_TYPE_ID, LightTypeEnum.INDUCTION.getValue());
		paramMap.put(LED_TYPE_ID, LightTypeEnum.LED.getValue());
		paramMap.put(OTHER_TYPE_ID, LightTypeEnum.OTHER.getValue());
		paramMap.put(ORDER_BY, ORDER_BY_DEFAULT);

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
		paramMap.put(TENANT_ID, inquiryAnalyticsRequest.getTenant().getId());
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
		paramMap.put(TENANT_ID, inquiryAnalyticsRequest.getTenant().getId());
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
		paramMap.put(TENANT_ID, inquiryAnalyticsRequest.getTenant().getId());
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
		paramMap.put(TENANT_ID, analyticsRequest.getTenant().getId());
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
		paramMap.put(TENANT_ID, analyticsRequest.getTenant().getId());
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

}
