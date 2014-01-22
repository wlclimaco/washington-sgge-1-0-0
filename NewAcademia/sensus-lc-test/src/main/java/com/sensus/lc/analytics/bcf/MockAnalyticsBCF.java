package com.sensus.lc.analytics.bcf;

import com.sensus.lc.analytics.model.request.AnalyticsCSVRequest;
import com.sensus.lc.analytics.model.request.AnalyticsRequest;
import com.sensus.lc.analytics.model.request.InquiryAnalyticsRequest;
import com.sensus.lc.analytics.model.response.AnalyticsResponse;
import com.sensus.lc.analytics.model.response.InquiryAnalyticsResponse;
import com.sensus.lc.base.AbstractMockBase;
import com.sensus.lc.base.model.request.InquiryPaginationRequest;
import com.sensus.lc.light.model.response.CSVResponse;
import com.sensus.lc.process.model.response.ProcessResponse;

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
	public AnalyticsResponse fetchAllAnalyticsGroup(AnalyticsRequest analyticsRequest)
	{
		return new AnalyticsResponse();
	}

	@Override
	public ProcessResponse insertCSVProcess(InquiryPaginationRequest request)
	{
		return new ProcessResponse();
	}

	@Override
	public CSVResponse generateFileCSV(AnalyticsCSVRequest analyticsCSVRequest)
	{
		return new CSVResponse();
	}

}
