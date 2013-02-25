package com.sensus.mlc.analytics.bcl;

import static com.sensus.mlc.base.util.LCDateUtil.getNewDateUTC;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sensus.common.model.Message;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResponse.Status;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.analytics.model.AnalyticsGroup;
import com.sensus.mlc.analytics.model.AnalyticsGroupAlarm;
import com.sensus.mlc.analytics.model.AnalyticsGroupCarbonCredits;
import com.sensus.mlc.analytics.model.AnalyticsGroupColumns;
import com.sensus.mlc.analytics.model.AnalyticsGroupEcoMode;
import com.sensus.mlc.analytics.model.AnalyticsGroupTypeLight;
import com.sensus.mlc.analytics.model.AnalyticsGroupWarning;
import com.sensus.mlc.analytics.model.AnalyticsTypeEnum;
import com.sensus.mlc.analytics.model.request.AnalyticsRequest;
import com.sensus.mlc.analytics.model.request.InquiryAnalyticsRequest;
import com.sensus.mlc.analytics.model.response.InquiryAnalyticsResponse;
import com.sensus.mlc.base.AbstractMockBase;
import com.sensus.mlc.base.LCAreaEnum;
import com.sensus.mlc.base.SituationsEnum;
import com.sensus.mlc.base.model.request.LightSelectionRequest;
import com.sensus.mlc.process.model.response.ProcessResponse;
import com.sensus.mlc.smartpoint.model.Light;
import com.sensus.mlc.tenant.model.Tenant;

/**
 * The Class MockAnalyticsBCL.
 */
public class MockAnalyticsBCL extends AbstractMockBase implements IAnalyticsBCL
{

	/** The Constant ANALYTICS_GROUP_5. */
	private static final Integer ANALYTICS_GROUP_5 = 5;

	/** The Constant ANALYTICS_GROUP_4. */
	private static final Integer ANALYTICS_GROUP_4 = 4;

	/** The Constant ANALYTICS_GROUP_3. */
	private static final Integer ANALYTICS_GROUP_3 = 3;

	/** The Constant SIZE_11. */
	private static final Integer SIZE_11 = 11;

	/** The Constant SIZE_10. */
	private static final Integer SIZE_10 = 10;

	/** The Constant SIZE_100. */
	private static final Integer SIZE_100 = 100;

	/** The Constant LIGHT_5. */
	private static final Integer LIGHT_5 = 5;

	/** The Constant LIGHT_4. */
	private static final Integer LIGHT_4 = 4;

	/** The Constant LIGHT_3. */
	private static final Integer LIGHT_3 = 3;

	/** The Constant ANALYTICS_GROUP_5_0. */
	private static final double ANALYTICS_GROUP_5_0 = 5.0;

	/** The Constant ANALYTICS_GROUP_1_0. */
	private static final double ANALYTICS_GROUP_1_0 = 1.0;

	/** The Constant ANALYTICS_GROUP_2_0. */
	private static final double ANALYTICS_GROUP_2_0 = 2.0;

	/** The Constant ANALYTICS_GROUP_4_0. */
	private static final double ANALYTICS_GROUP_4_0 = 4.0;

	/** The Constant ANALYTICS_GROUP_21_0. */
	private static final double ANALYTICS_GROUP_21_0 = 21.0;

	/** The Constant ANALYTICS_GROUP_41_0. */
	private static final double ANALYTICS_GROUP_41_0 = 41.0;

	/** The Constant ANALYTICS_GROUP_20_0. */
	private static final double ANALYTICS_GROUP_20_0 = 20.0;

	/** The Constant ANALYTICS_GROUP_22_0. */
	private static final double ANALYTICS_GROUP_22_0 = 22.0;

	/** The Constant ANALYTICS_GROUP_15_0. */
	private static final double ANALYTICS_GROUP_15_0 = 15.0;

	/** The Constant ANALYTICS_GROUP_14_0. */
	private static final double ANALYTICS_GROUP_14_0 = 14.0;

	/** The Constant ANALYTICS_GROUP_13_0. */
	private static final double ANALYTICS_GROUP_13_0 = 13.0;

	/** The Constant ANALYTICS_GROUP_12_0. */
	private static final double ANALYTICS_GROUP_12_0 = 12.0;

	/** The Constant ANALYTICS_GROUP_11_0. */
	private static final double ANALYTICS_GROUP_11_0 = 11.0;

	/** The Constant ANALYTICS_GROUP_10_0. */
	private static final double ANALYTICS_GROUP_10_0 = 10.0;

	/** The Constant GROUP_ID_4. */
	private static final Integer GROUP_ID_4 = 4;

	/** The Constant GROUP_ID_3. */
	private static final Integer GROUP_ID_3 = 3;

	/** The Constant TEST_EXCEPTION. */
	private static final String TEST_EXCEPTION = "Test Exception";

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.analytics.bcl.IAnalyticsBCL#fetchAllAnalyticsByGroup(com.sensus.mlc.analytics.model.request.
	 * InquiryAnalyticsRequest)
	 */
	@Override
	public InternalResultsResponse<AnalyticsGroup> fetchAllAnalyticsByGroup(
			InquiryAnalyticsRequest inquiryAnalyticsRequest)
	{
		InternalResultsResponse<AnalyticsGroup> response = new InternalResultsResponse<AnalyticsGroup>();
		response = fetchAllAnalyticsByGroupFromAnalytics(inquiryAnalyticsRequest, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.analytics.bcl.IAnalyticsBCL#fetchAnalyticsAlarmsByStatusId(com.sensus.mlc.analytics.model.request
	 * .AnalyticsRequest)
	 */
	@Override
	public InternalResultsResponse<Light> fetchAnalyticsAlarmsByStatusId(AnalyticsRequest analyticsRequest)
	{
		InternalResultsResponse<Light> response = new InternalResultsResponse<Light>();
		response = fetchAnalyticsAlarmsByStatusIdFromAnalytics(response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.analytics.bcl.IAnalyticsBCL#fetchAnalyticsAlertsByType(com.sensus.mlc.analytics.model.request.
	 * AnalyticsRequest)
	 */
	@Override
	public InternalResultsResponse<Map<String, Integer>> fetchAnalyticsAlertsByType(
			AnalyticsRequest analyticsRequest)
	{
		InternalResultsResponse<Map<String, Integer>> response = new InternalResultsResponse<Map<String, Integer>>();
		response = fetchAnalyticsAlertsByTypeFromAnalytics(response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.analytics.bcl.IAnalyticsBCL#fetchAllAnalyticsByDate(com.sensus.mlc.analytics.model.request.
	 * AnalyticsRequest)
	 */
	@Override
	public InternalResultsResponse<AnalyticsGroupColumns> fetchAllAnalyticsByDate(AnalyticsRequest analyticsRequest)
	{
		InternalResultsResponse<AnalyticsGroupColumns> response =
				new InternalResultsResponse<AnalyticsGroupColumns>();
		response = fetchAllAnalyticsByDateFromAnalytics(response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.analytics.bcl.IAnalyticsBCL#fetchDashboardResume(com.sensus.mlc.analytics.model.request.
	 * AnalyticsRequest)
	 */
	@Override
	public InternalResultsResponse<AnalyticsGroupColumns> fetchDashboardResume(AnalyticsRequest analyticsRequest)
	{
		InternalResultsResponse<AnalyticsGroupColumns> response = new InternalResultsResponse<AnalyticsGroupColumns>();
		response = fetchDashboardResumeFromAnalytics(response);
		return response;

	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.analytics.bcl.IAnalyticsBCL#fetchDashboarHeader(com.sensus.mlc.analytics.model.request.
	 * AnalyticsRequest)
	 */
	@Override
	public InternalResultsResponse<AnalyticsGroupColumns> fetchDashboardHeader(AnalyticsRequest analyticsRequest)
	{
		InternalResultsResponse<AnalyticsGroupColumns> response =
				new InternalResultsResponse<AnalyticsGroupColumns>();
		response = fetchDashboardHeaderFromAnalytics(response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.analytics.bcl.IAnalyticsBCL#parseAnalyticsGroupAlarm(java.util.List)
	 */
	@Override
	public List<AnalyticsGroup> parseAnalyticsGroupAlarm(List<AnalyticsGroupAlarm> list)
	{

		List<AnalyticsGroup> returnList = new ArrayList<AnalyticsGroup>();
		returnList.add(new AnalyticsGroup());
		returnList.add(new AnalyticsGroup());

		return returnList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.analytics.bcl.IAnalyticsBCL#parseAnalyticsGroupWarning(java.util.List)
	 */
	@Override
	public List<AnalyticsGroup> parseAnalyticsGroupWarning(List<AnalyticsGroupWarning> list)
	{
		List<AnalyticsGroup> returnList = new ArrayList<AnalyticsGroup>();
		returnList.add(new AnalyticsGroup());
		returnList.add(new AnalyticsGroup());

		return returnList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.analytics.bcl.IAnalyticsBCL#parseAnalyticsEcoMode(java.util.List)
	 */
	@Override
	public List<AnalyticsGroup> parseAnalyticsEcoMode(List<AnalyticsGroupEcoMode> list)
	{
		List<AnalyticsGroup> returnList = new ArrayList<AnalyticsGroup>();
		returnList.add(new AnalyticsGroup());
		returnList.add(new AnalyticsGroup());

		return returnList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.analytics.bcl.IAnalyticsBCL#generateFileCSV(com.sensus.mlc.analytics.model.request.
	 * InquiryAnalyticsRequest)
	 */
	@Override
	public InquiryAnalyticsResponse generateFileCSV(InquiryAnalyticsRequest inquiryAnalyticsRequest)
	{
		InquiryAnalyticsResponse response = new InquiryAnalyticsResponse();
		response = generateFileCSVFromAnalytics(response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.analytics.bcl.IAnalyticsBCL#parseAnalyticsGroupTypeLight(java.util.List)
	 */
	@Override
	public List<AnalyticsGroup> parseAnalyticsGroupTypeLight(List<AnalyticsGroupTypeLight> list)
	{
		List<AnalyticsGroup> returnList = new ArrayList<AnalyticsGroup>();
		returnList.add(new AnalyticsGroup());
		returnList.add(new AnalyticsGroup());

		return returnList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.analytics.bcl.IAnalyticsBCL#parseAnalyticsGroupCarbonCredits(java.util.List)
	 */
	@Override
	public List<AnalyticsGroup> parseAnalyticsGroupCarbonCredits(List<AnalyticsGroupCarbonCredits> list)
	{
		List<AnalyticsGroup> returnList = new ArrayList<AnalyticsGroup>();
		returnList.add(new AnalyticsGroup());
		returnList.add(new AnalyticsGroup());

		return returnList;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.analytics.bcl.IAnalyticsBCL#fetchAllAnalyticsGroup(com.sensus.mlc.analytics.model.request.
	 * AnalyticsRequest)
	 */
	@Override
	public InternalResultsResponse<AnalyticsGroup> fetchAllAnalyticsGroup(AnalyticsRequest analyticsRequest)
	{
		InternalResultsResponse<AnalyticsGroup> response = new InternalResultsResponse<AnalyticsGroup>();
		response = fetchAllAnalyticsGroupFromAnalytics(response);
		return response;
	}

	/**
	 * Fetch all analytics group from analytics.
	 *
	 * @param response the response
	 * @return the internal results response
	 */
	@SuppressWarnings("unchecked")
	private InternalResultsResponse<AnalyticsGroup> fetchAllAnalyticsGroupFromAnalytics(
			InternalResultsResponse<AnalyticsGroup> response)
	{
		if (getAreaEnum() != LCAreaEnum.ANALYTICS)
		{
			return response;
		}

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			AnalyticsGroup group = new AnalyticsGroup();
			group.setId(2);
			group.setName("Beaverton");
			response.addResult(group);

			group = new AnalyticsGroup();
			group.setId(GROUP_ID_3);
			group.setName("Buckman");
			response.addResult(group);

			group = new AnalyticsGroup();
			group.setId(GROUP_ID_4);
			group.setName("Downtown");
			response.addResult(group);
			response.setStatus(Status.OperationSuccess);
		}
		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getResponseResultsError();
		}

		if (getSituationsEnum() == SituationsEnum.EXCEPTION)
		{
			throw new RuntimeException(TEST_EXCEPTION);
		}

		return response;
	}

	/**
	 * Generate file csv from analytics.
	 *
	 * @param response the response
	 * @return the inquiry analytics response
	 */
	private InquiryAnalyticsResponse generateFileCSVFromAnalytics(InquiryAnalyticsResponse response)
	{
		if (getAreaEnum() != LCAreaEnum.ANALYTICS)
		{
			return response;
		}

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			String fileName =
					"C:\\Users\\QATEmployee\\Desktop\\file\\testPOIWrite" + getNewDateUTC().getTime() + ".csv";
			response.setFileName(fileName);
		}
		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			response.setOperationSuccess(false);
			response.addFieldErrorMessage(ERROR_CODE);
			return response;
		}

		if (getSituationsEnum() == SituationsEnum.EXCEPTION)
		{
			throw new RuntimeException(TEST_EXCEPTION);
		}

		if (getSituationsEnum() == SituationsEnum.VALIDATION)
		{
			response.setOperationSuccess(false);
			List<Message> listMessage = new ArrayList<Message>();
			response.setMessageList(listMessage);
			return response;
		}

		return response;
	}

	/**
	 * Fetch dashboard header from analytics.
	 *
	 * @param response the response
	 * @return the internal results response
	 */
	@SuppressWarnings("unchecked")
	private InternalResultsResponse<AnalyticsGroupColumns> fetchDashboardHeaderFromAnalytics(
			InternalResultsResponse<AnalyticsGroupColumns> response)
	{
		if (getAreaEnum() != LCAreaEnum.ANALYTICS)
		{
			return response;
		}

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			response.addResult(new AnalyticsGroupColumns("Total Installed", ANALYTICS_GROUP_10_0, getNewDateUTC()));
			response.addResult(new AnalyticsGroupColumns("Total Alarms", ANALYTICS_GROUP_11_0, getNewDateUTC()));
			response.addResult(new AnalyticsGroupColumns("Total Warnings", ANALYTICS_GROUP_12_0, getNewDateUTC()));
			response.addResult(new AnalyticsGroupColumns("Total Consumption", ANALYTICS_GROUP_13_0, getNewDateUTC()));
			response.addResult(new AnalyticsGroupColumns("Total Eco-Mode", ANALYTICS_GROUP_14_0, getNewDateUTC()));
			response.addResult(new AnalyticsGroupColumns("Total Carbon Credits", ANALYTICS_GROUP_15_0, getNewDateUTC()));
			response.setStatus(Status.OperationSuccess);
		}
		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getResponseResultsError();
		}

		if (getSituationsEnum() == SituationsEnum.EXCEPTION)
		{
			throw new RuntimeException(TEST_EXCEPTION);
		}

		return response;
	}

	/**
	 * Fetch dashboard resume from analytics.
	 *
	 * @param response the response
	 * @return the internal results response
	 */
	@SuppressWarnings("unchecked")
	private InternalResultsResponse<AnalyticsGroupColumns> fetchDashboardResumeFromAnalytics(
			InternalResultsResponse<AnalyticsGroupColumns> response)
	{
		if (getAreaEnum() != LCAreaEnum.ANALYTICS)
		{
			return response;
		}

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			response.addResult(new AnalyticsGroupColumns(AnalyticsTypeEnum.LIGHTING_ALARM, ANALYTICS_GROUP_10_0, ANALYTICS_GROUP_12_0, ANALYTICS_GROUP_12_0));
			response.addResult(new AnalyticsGroupColumns(AnalyticsTypeEnum.LIGHTING_WARNING, ANALYTICS_GROUP_12_0, ANALYTICS_GROUP_22_0, ANALYTICS_GROUP_10_0));
			response.addResult(new AnalyticsGroupColumns(AnalyticsTypeEnum.LIGHTING_INSTALLED, ANALYTICS_GROUP_13_0, ANALYTICS_GROUP_13_0, ANALYTICS_GROUP_20_0));
			response.addResult(new AnalyticsGroupColumns(AnalyticsTypeEnum.ECOMODE_CONSUMPTION, ANALYTICS_GROUP_14_0, ANALYTICS_GROUP_12_0, ANALYTICS_GROUP_11_0));
			response.addResult(new AnalyticsGroupColumns(AnalyticsTypeEnum.LIGHTING_ECOMODE, ANALYTICS_GROUP_41_0, ANALYTICS_GROUP_21_0, ANALYTICS_GROUP_10_0));
			response.addResult(new AnalyticsGroupColumns(AnalyticsTypeEnum.ECOMODE_CARBON_CREDITS, ANALYTICS_GROUP_4_0, ANALYTICS_GROUP_2_0, ANALYTICS_GROUP_1_0));
			response.setStatus(Status.OperationSuccess);
		}
		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getResponseResultsError();
		}

		if (getSituationsEnum() == SituationsEnum.EXCEPTION)
		{
			throw new RuntimeException(TEST_EXCEPTION);
		}

		return response;
	}

	/**
	 * Fetch all analytics by date from analytics.
	 *
	 * @param response the response
	 * @return the internal results response
	 */
	@SuppressWarnings("unchecked")
	private InternalResultsResponse<AnalyticsGroupColumns> fetchAllAnalyticsByDateFromAnalytics(
			InternalResultsResponse<AnalyticsGroupColumns> response)
	{
		if (getAreaEnum() != LCAreaEnum.ANALYTICS)
		{
			return response;
		}

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			response.addResult(new AnalyticsGroupColumns("Induction", ANALYTICS_GROUP_10_0, getNewDateUTC()));
			response.addResult(new AnalyticsGroupColumns("LED", ANALYTICS_GROUP_5_0, getNewDateUTC()));
			response.addResult(new AnalyticsGroupColumns("All", ANALYTICS_GROUP_15_0, getNewDateUTC()));
			response.setStatus(Status.OperationSuccess);
		}
		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getResponseResultsError();
		}

		if (getSituationsEnum() == SituationsEnum.EXCEPTION)
		{
			throw new RuntimeException(TEST_EXCEPTION);
		}

		return response;
	}

	/**
	 * Fetch analytics alarms by status id from analytics.
	 *
	 * @param response the response
	 * @return the internal results response
	 */
	@SuppressWarnings("unchecked")
	private InternalResultsResponse<Light> fetchAnalyticsAlarmsByStatusIdFromAnalytics(
			InternalResultsResponse<Light> response)
	{
		if (getAreaEnum() != LCAreaEnum.ANALYTICS)
		{
			return response;
		}

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			response.addResult(new Light(1));
			response.addResult(new Light(2));
			response.addResult(new Light(LIGHT_3));
			response.addResult(new Light(LIGHT_4));
			response.addResult(new Light(LIGHT_5));
		}
		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getResponseResultsError();
		}

		if (getSituationsEnum() == SituationsEnum.EXCEPTION)
		{
			throw new RuntimeException(TEST_EXCEPTION);
		}

		return response;
	}

	/**
	 * Fetch analytics alerts by type from analytics.
	 *
	 * @param response the response
	 * @return the internal results response
	 */
	@SuppressWarnings("unchecked")
	private InternalResultsResponse<Map<String, Integer>> fetchAnalyticsAlertsByTypeFromAnalytics(
			InternalResultsResponse<Map<String, Integer>> response)
	{
		if (getAreaEnum() != LCAreaEnum.ANALYTICS)
		{
			return response;
		}

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			Map<String, Integer> map = new HashMap<String, Integer>();
			map.put("1", SIZE_100);
			map.put("2", SIZE_10);
			map.put("7", SIZE_11);
			response.getResultsList().add(map);
		}
		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getResponseResultsError();
		}

		if (getSituationsEnum() == SituationsEnum.EXCEPTION)
		{
			throw new RuntimeException(TEST_EXCEPTION);
		}

		return response;
	}

	/**
	 * Fetch all analytics by group from analytics.
	 *
	 * @param inquiryAnalyticsRequest the inquiry analytics request
	 * @param response the response
	 * @return the internal results response
	 */
	@SuppressWarnings("unchecked")
	private InternalResultsResponse<AnalyticsGroup> fetchAllAnalyticsByGroupFromAnalytics(
			InquiryAnalyticsRequest inquiryAnalyticsRequest, InternalResultsResponse<AnalyticsGroup> response)
	{
		if (getAreaEnum() != LCAreaEnum.ANALYTICS)
		{
			return response;
		}

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			switch (inquiryAnalyticsRequest.getAnalyticsTypeEnum())
			{
				case LIGHTING_ALARM:
					response.addResult(new AnalyticsGroup(1));
					response.addResult(new AnalyticsGroup(2));
					response.addResult(new AnalyticsGroup(ANALYTICS_GROUP_3));
					response.addResult(new AnalyticsGroup(ANALYTICS_GROUP_4));
					response.addResult(new AnalyticsGroup(ANALYTICS_GROUP_5));
					response.setStatus(Status.OperationSuccess);
					break;

				case LIGHTING_WARNING:
					response.addResult(new AnalyticsGroup(1));
					response.addResult(new AnalyticsGroup(2));
					response.addResult(new AnalyticsGroup(ANALYTICS_GROUP_3));
					response.addResult(new AnalyticsGroup(ANALYTICS_GROUP_4));
					response.addResult(new AnalyticsGroup(ANALYTICS_GROUP_5));
					response.setStatus(Status.OperationSuccess);
					break;

				case LIGHTING_INSTALLED:
					response.addResult(new AnalyticsGroup(1));
					response.addResult(new AnalyticsGroup(2));
					response.addResult(new AnalyticsGroup(ANALYTICS_GROUP_3));
					response.addResult(new AnalyticsGroup(ANALYTICS_GROUP_4));
					response.addResult(new AnalyticsGroup(ANALYTICS_GROUP_5));
					response.setStatus(Status.OperationSuccess);
					break;

				default:
					break;
			}
		}
		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getResponseResultsError();
		}

		if (getSituationsEnum() == SituationsEnum.EXCEPTION)
		{
			throw new RuntimeException(TEST_EXCEPTION);
		}

		return response;
	}

	/* (non-Javadoc)
	 * @see com.sensus.mlc.analytics.bcl.IAnalyticsBCL#insertCSVProcess(com.sensus.mlc.base.model.request.LightSelectionRequest)
	 */
	@Override
	public ProcessResponse insertCSVProcess(LightSelectionRequest lightSelectionRequest)
	{
		return new ProcessResponse();
	}

	/* (non-Javadoc)
	 * @see com.sensus.mlc.analytics.bcl.IAnalyticsBCL#deleteDashboardResumeByTenant(com.sensus.mlc.tenant.model.Tenant)
	 */
	@Override
	public InternalResponse deleteDashboardResumeByTenant(Tenant tenant)
	{
		return new InternalResponse();
	}

	/* (non-Javadoc)
	 * @see com.sensus.mlc.analytics.bcl.IAnalyticsBCL#calculateDashboardResume(com.sensus.mlc.tenant.model.Tenant, com.sensus.mlc.analytics.model.AnalyticsGroup)
	 */
	@Override
	public InternalResultsResponse<String> calculateDashboardResume(Tenant tenant, AnalyticsGroup analyticsGroup)
	{
		return new InternalResultsResponse<String>();
	}

	/* (non-Javadoc)
	 * @see com.sensus.mlc.analytics.bcl.IAnalyticsBCL#fetchAllGroupsByTenantForDashboard(com.sensus.mlc.tenant.model.Tenant)
	 */
	@Override
	public InternalResultsResponse<AnalyticsGroup> fetchAllGroupsByTenantForDashboard(Tenant tenant)
	{
		return new InternalResultsResponse<AnalyticsGroup>();
	}

}