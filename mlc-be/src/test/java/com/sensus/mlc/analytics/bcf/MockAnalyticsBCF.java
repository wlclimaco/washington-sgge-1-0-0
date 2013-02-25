package com.sensus.mlc.analytics.bcf;

import com.sensus.mlc.analytics.model.request.AnalyticsRequest;
import com.sensus.mlc.analytics.model.request.InquiryAnalyticsRequest;
import com.sensus.mlc.analytics.model.response.AnalyticsResponse;
import com.sensus.mlc.analytics.model.response.InquiryAnalyticsResponse;
import com.sensus.mlc.base.AbstractMockBase;
import com.sensus.mlc.base.model.request.LightSelectionRequest;
import com.sensus.mlc.process.model.response.ProcessResponse;

public class MockAnalyticsBCF extends AbstractMockBase implements IAnalyticsBCF
{

	@Override
	public InquiryAnalyticsResponse fetchAllAnalyticsByGroup(InquiryAnalyticsRequest inquiryAnalyticsRequest)
	{
		return new InquiryAnalyticsResponse();
	}

	@Override
	public AnalyticsResponse fetchAllAnalyticsByDate(AnalyticsRequest analyticsRequest)
	{
		return new AnalyticsResponse();
	}

	@Override
	public AnalyticsResponse fetchAnalyticsAlarmsByStatusId(AnalyticsRequest analyticsRequest)
	{
		return new AnalyticsResponse();
	}

	@Override
	public AnalyticsResponse fetchAnalyticsAlertsByType(AnalyticsRequest analyticsRequest)
	{
		return new AnalyticsResponse();
	}

	@Override
	public AnalyticsResponse fetchDashboardResume(AnalyticsRequest analyticsRequest)
	{
		return new AnalyticsResponse();
	}

	@Override
	public AnalyticsResponse fetchDashboardHeader(AnalyticsRequest analyticsRequest)
	{
		return new AnalyticsResponse();
	}

	@Override
	public InquiryAnalyticsResponse generateFileCSV(InquiryAnalyticsRequest inquiryAnalyticsRequest)
	{
		return new InquiryAnalyticsResponse();
	}

	@Override
	public AnalyticsResponse fetchAllAnalyticsGroup(AnalyticsRequest analyticsRequest)
	{
		return new AnalyticsResponse();
	}

	@Override
	public ProcessResponse insertCSVProcess(LightSelectionRequest lightSelectionRequest)
	{
		return new ProcessResponse();
	}

}
