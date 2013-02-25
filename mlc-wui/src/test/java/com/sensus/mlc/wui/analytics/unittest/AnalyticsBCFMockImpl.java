package com.sensus.mlc.wui.analytics.unittest;

import java.util.ArrayList;
import java.util.List;

import com.sensus.mlc.analytics.bcf.IAnalyticsBCF;
import com.sensus.mlc.analytics.model.AnalyticsGroup;
import com.sensus.mlc.analytics.model.AnalyticsGroupColumns;
import com.sensus.mlc.analytics.model.request.AnalyticsRequest;
import com.sensus.mlc.analytics.model.request.InquiryAnalyticsRequest;
import com.sensus.mlc.analytics.model.response.AnalyticsResponse;
import com.sensus.mlc.analytics.model.response.InquiryAnalyticsResponse;
import com.sensus.mlc.base.model.request.LightSelectionRequest;
import com.sensus.mlc.process.model.response.ProcessResponse;
import com.sensus.mlc.wui.base.unittest.util.BaseMockImpl;
import com.sensus.mlc.wui.util.ModeEnum;

/**
 * The Class AnalyticsBCFMockImpl.
 */
public class AnalyticsBCFMockImpl extends BaseMockImpl implements IAnalyticsBCF
{

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

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.analytics.bcf.IAnalyticsBCF#fetchAllAnalyticsByGroup(com.sensus.mlc.analytics.model.request.
	 * InquiryAnalyticsRequest)
	 */
	@Override
	public InquiryAnalyticsResponse fetchAllAnalyticsByGroup(
			InquiryAnalyticsRequest inquiryAnalyticsRequest)
	{

		InquiryAnalyticsResponse response = new InquiryAnalyticsResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			response.setGroups(createAnalyticsGroupList(inquiryAnalyticsRequest.getPageSize()));
			return response;
		}

		return (InquiryAnalyticsResponse)testOtherDefaultModes(response);

	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.analytics.bcf.IAnalyticsBCF#fetchAllAnalyticsByDate(com.sensus.mlc.analytics.model.request.
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
	 * @see
	 * com.sensus.mlc.analytics.bcf.IAnalyticsBCF#fetchAnalyticsAlarmsByStatusId(com.sensus.mlc.analytics.model.request
	 * .AnalyticsRequest)
	 */
	@Override
	public AnalyticsResponse fetchAnalyticsAlarmsByStatusId(
			AnalyticsRequest analyticsRequest)
	{
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.analytics.bcf.IAnalyticsBCF#fetchAnalyticsAlertsByType(com.sensus.mlc.analytics.model.request.
	 * AnalyticsRequest)
	 */
	@Override
	public AnalyticsResponse fetchAnalyticsAlertsByType(
			AnalyticsRequest analyticsRequest)
	{
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.analytics.bcf.IAnalyticsBCF#fetchDashboardResume(com.sensus.mlc.analytics.model.request.
	 * AnalyticsRequest)
	 */
	@Override
	public AnalyticsResponse fetchDashboardResume(
			AnalyticsRequest analyticsRequest)
	{
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.analytics.bcf.IAnalyticsBCF#fetchDashboardHeader(com.sensus.mlc.analytics.model.request.
	 * AnalyticsRequest)
	 */
	@Override
	public AnalyticsResponse fetchDashboardHeader(
			AnalyticsRequest analyticsRequest)
	{
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.analytics.bcf.IAnalyticsBCF#generateFileCSV(com.sensus.mlc.analytics.model.request.
	 * InquiryAnalyticsRequest)
	 */
	@Override
	public InquiryAnalyticsResponse generateFileCSV(
			InquiryAnalyticsRequest inquiryAnalyticsRequest)
	{
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.analytics.bcf.IAnalyticsBCF#fetchAllAnalyticsGroup(com.sensus.mlc.analytics.model.request.
	 * AnalyticsRequest)
	 */
	@Override
	public AnalyticsResponse fetchAllAnalyticsGroup(
			AnalyticsRequest analyticsRequest)
	{
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.analytics.bcf.IAnalyticsBCF#insertCSVProcess(com.sensus.mlc.base.model.request.LightSelectionRequest
	 * )
	 */
	@Override
	public ProcessResponse insertCSVProcess(
			LightSelectionRequest lightSelectionRequest)
	{
		// TODO Auto-generated method stub
		return null;
	}
}
