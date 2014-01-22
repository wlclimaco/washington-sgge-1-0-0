package com.sensus.lc.controller.analytics.unittest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sensus.common.validation.ValidationUtil;
import com.sensus.lc.analytics.bcf.IAnalyticsBCF;
import com.sensus.lc.analytics.model.AnalyticsGroup;
import com.sensus.lc.analytics.model.AnalyticsGroupColumns;
import com.sensus.lc.analytics.model.request.AnalyticsCSVRequest;
import com.sensus.lc.analytics.model.request.AnalyticsRequest;
import com.sensus.lc.analytics.model.request.InquiryAnalyticsRequest;
import com.sensus.lc.analytics.model.response.AnalyticsResponse;
import com.sensus.lc.analytics.model.response.InquiryAnalyticsResponse;
import com.sensus.lc.base.model.request.InquiryPaginationRequest;
import com.sensus.lc.controller.base.unittest.util.BaseMockImpl;
import com.sensus.lc.controller.util.enums.ModeEnum;
import com.sensus.lc.light.model.Light;
import com.sensus.lc.light.model.response.CSVResponse;
import com.sensus.lc.process.model.response.ProcessResponse;

/**
 * The Class AnalyticsBCFMockImpl.
 */
public class AnalyticsBCFMockImpl extends BaseMockImpl implements IAnalyticsBCF
{
	private static final Double VALUE = 15.5;

	private Map<String, Integer> createAlertsByType(Integer listSize)
	{

		// Create User List
		Map<String, Integer> alertsByType = new HashMap<String, Integer>();

		// Create Users to set at list
		for (int i = 0; i < listSize; i++)
		{

			alertsByType.put("A" + i, i);
		}

		return alertsByType;
	}

	/**
	 * Creates the analytics group list.
	 * 
	 * @param listSize the list size
	 * @return the list
	 */
	private List<AnalyticsGroup> createAnalyticsGroupList(Integer listSize)
	{

		List<AnalyticsGroup> analyticsGroupList = new ArrayList<AnalyticsGroup>();

		for (int i = 0; i < listSize; i++)
		{
			AnalyticsGroup analyticsGroup = new AnalyticsGroup();
			analyticsGroup.setName("Group 0" + i);

			List<AnalyticsGroupColumns> analyticsGroupColumns = new ArrayList<AnalyticsGroupColumns>();

			analyticsGroupColumns.add(new AnalyticsGroupColumns("Total", 0.00));
			analyticsGroupColumns.add(new AnalyticsGroupColumns("Lamp Failure", 0.00));
			analyticsGroupColumns.add(new AnalyticsGroupColumns("Power Failure", 0.00));
			analyticsGroupColumns.add(new AnalyticsGroupColumns("Board Failure", 0.00));
			analyticsGroupColumns.add(new AnalyticsGroupColumns("Metrology Error", 0.00));
			analyticsGroupColumns.add(new AnalyticsGroupColumns("Metrology COM Failure", 0.00));

			analyticsGroup.setColumns(analyticsGroupColumns);
			analyticsGroupList.add(analyticsGroup);
		}

		return analyticsGroupList;
	}

	private List<AnalyticsGroupColumns> createCollumnsList(Integer listSize)
	{

		// Create User List
		List<AnalyticsGroupColumns> analyticsGroupColumnsList = new ArrayList<AnalyticsGroupColumns>();

		// Create Users to set at list
		for (int i = 0; i < listSize; i++)
		{
			AnalyticsGroupColumns analyticsGroupColumns = new AnalyticsGroupColumns();
			analyticsGroupColumns.setValue(VALUE);
			analyticsGroupColumnsList.add(analyticsGroupColumns);

		}

		return analyticsGroupColumnsList;
	}

	private List<Light> createLightList(Integer listSize)
	{

		// Create User List
		List<Light> lightList = new ArrayList<Light>();

		// Create Users to set at list
		for (int i = 0; i < listSize; i++)
		{
			Light light = new Light(i);
			light.setId(i);
			lightList.add(light);
		}

		return lightList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.lc.analytics.bcf.IAnalyticsBCF#fetchAllAnalyticsByDate(com.sensus.lc.analytics.model.request.
	 * AnalyticsRequest)
	 */
	@Override
	public AnalyticsResponse fetchAllAnalyticsByDate(
			AnalyticsRequest analyticsRequest)
	{

		AnalyticsResponse response = new AnalyticsResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			response.setAnalyticsGroups(createAnalyticsGroupList(5));
			return response;
		}

		return (AnalyticsResponse)testOtherDefaultModes(response);

	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.lc.analytics.bcf.IAnalyticsBCF#fetchAllAnalyticsByGroup(com.sensus.lc.analytics.model.request.
	 * InquiryAnalyticsRequest)
	 */
	@Override
	public InquiryAnalyticsResponse fetchAllAnalyticsByGroup(
			InquiryAnalyticsRequest inquiryAnalyticsRequest)
	{

		InquiryAnalyticsResponse response = new InquiryAnalyticsResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			response.setGroups(createAnalyticsGroupList(25));
			return response;
		}

		return (InquiryAnalyticsResponse)testOtherDefaultModes(response);

	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.lc.analytics.bcf.IAnalyticsBCF#fetchAllAnalyticsGroup(com.sensus.lc.analytics.model.request.
	 * AnalyticsRequest)
	 */
	@Override
	public AnalyticsResponse fetchAllAnalyticsGroup(AnalyticsRequest analyticsRequest)
	{
		AnalyticsResponse response = new AnalyticsResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			response.setAnalyticsGroups(createAnalyticsGroupList(5));
			return response;
		}

		return (AnalyticsResponse)testOtherDefaultModes(response);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.lc.analytics.bcf.IAnalyticsBCF#fetchAnalyticsAlarmsByStatusId(com.sensus.lc.analytics.model.request
	 * .AnalyticsRequest)
	 */
	@Override
	public AnalyticsResponse fetchAnalyticsAlarmsByStatusId(
			AnalyticsRequest analyticsRequest)
	{
		AnalyticsResponse response = new AnalyticsResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			if (!ValidationUtil.isNull(analyticsRequest.getAlertSubtype()))
			{
				response.setLights(createLightList(25));
				return response;
			}
		}

		return (AnalyticsResponse)testOtherDefaultModes(response);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.lc.analytics.bcf.IAnalyticsBCF#fetchAnalyticsAlertsByType(com.sensus.lc.analytics.model.request.
	 * AnalyticsRequest)
	 */
	@Override
	public AnalyticsResponse fetchAnalyticsAlertsByType(
			AnalyticsRequest analyticsRequest)
	{
		AnalyticsResponse response = new AnalyticsResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			if (!ValidationUtil.isNull(analyticsRequest.getAlertSubtype()))
			{
				response.setAlertsByType(createAlertsByType(25));
				return response;
			}
		}

		return (AnalyticsResponse)testOtherDefaultModes(response);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.lc.analytics.bcf.IAnalyticsBCF#fetchDashboardHeader(com.sensus.lc.analytics.model.request.
	 * AnalyticsRequest)
	 */
	@Override
	public AnalyticsResponse fetchDashboardHeader(
			AnalyticsRequest analyticsRequest)
	{
		AnalyticsResponse response = new AnalyticsResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			if (!ValidationUtil.isNull(analyticsRequest.getAlertSubtype()))
			{
				response.setColumns(createCollumnsList(25));
				return response;
			}
		}

		return (AnalyticsResponse)testOtherDefaultModes(response);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.lc.analytics.bcf.IAnalyticsBCF#fetchDashboardResume(com.sensus.lc.analytics.model.request.
	 * AnalyticsRequest)
	 */
	@Override
	public AnalyticsResponse fetchDashboardResume(
			AnalyticsRequest analyticsRequest)
	{
		AnalyticsResponse response = new AnalyticsResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			if (!ValidationUtil.isNull(analyticsRequest.getAlertSubtype()))
			{
				response.setColumns(createCollumnsList(25));
				return response;
			}
		}

		return (AnalyticsResponse)testOtherDefaultModes(response);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.lc.analytics.bcf.IAnalyticsBCF#generateFileCSV(com.sensus.lc.analytics.model.request.
	 * InquiryAnalyticsRequest)
	 */
	@Override
	public CSVResponse generateFileCSV(
			AnalyticsCSVRequest analyticsCSVRequest)
	{
		CSVResponse response = new CSVResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			response.setOperationSuccess(true);
			return response;
		}

		return (CSVResponse)testOtherDefaultModes(response);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.lc.analytics.bcf.IAnalyticsBCF#insertCSVProcess(com.sensus.lc.base.model.request.LightSelectionRequest
	 * )
	 */
	@Override
	public ProcessResponse insertCSVProcess(InquiryPaginationRequest lightSelectionRequest)
	{
		ProcessResponse response = new ProcessResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			response.setOperationSuccess(true);
			return response;
		}

		return (ProcessResponse)testOtherDefaultModes(response);
	}

}
