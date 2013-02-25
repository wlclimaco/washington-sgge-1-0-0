package com.sensus.mlc.smartpoint.bcf;

import com.sensus.mlc.base.AbstractMockBase;
import com.sensus.mlc.base.model.request.InquiryPaginationRequest;
import com.sensus.mlc.base.model.request.LightingControlRequest;
import com.sensus.mlc.process.model.request.ProcessRequest;
import com.sensus.mlc.smartpoint.model.request.ColumnFilterRequest;
import com.sensus.mlc.smartpoint.model.request.InquiryLightRequest;
import com.sensus.mlc.smartpoint.model.request.LightRequest;
import com.sensus.mlc.smartpoint.model.request.LightingConfigurationRequest;
import com.sensus.mlc.smartpoint.model.request.PropertyValidValuesRequest;
import com.sensus.mlc.smartpoint.model.response.ColumnFilterResponse;
import com.sensus.mlc.smartpoint.model.response.CountLightResponse;
import com.sensus.mlc.smartpoint.model.response.CurrentAlarmWarningMessageResponse;
import com.sensus.mlc.smartpoint.model.response.InquiryCustomSearchResponse;
import com.sensus.mlc.smartpoint.model.response.InquiryLightResponse;
import com.sensus.mlc.smartpoint.model.response.LightResponse;
import com.sensus.mlc.smartpoint.model.response.LightingConfigurationsResponse;
import com.sensus.mlc.smartpoint.model.response.PropertyValidValuesResponse;
import com.sensus.mlc.smartpoint.model.response.StatusMessageResponse;

public class MockSmartPointAccessorBCF extends AbstractMockBase implements ISmartPointAccessorBCF
{

	@Override
	public InquiryLightResponse fetchAllLights(InquiryLightRequest inquiryLightRequest)
	{
		return new InquiryLightResponse();
	}

	@Override
	public CountLightResponse countLights(LightingControlRequest request)
	{
		return new CountLightResponse();
	}

	@Override
	public LightResponse fetchLightById(LightRequest lightRequest)
	{
		return new LightResponse();
	}

	@Override
	public LightResponse fetchLightIdByRniId(LightRequest lightRequest)
	{
		return new LightResponse();
	}

	@Override
	public PropertyValidValuesResponse fetchPropertyValidValues(PropertyValidValuesRequest request)
	{
		return new PropertyValidValuesResponse();
	}

	@Override
	public LightResponse fetchUpdateLightStatus(ProcessRequest processRequest)
	{
		return new LightResponse();
	}

	@Override
	public InquiryCustomSearchResponse fetchAllCustomSearch(InquiryPaginationRequest inquiryPaginationRequest)
	{
		return new InquiryCustomSearchResponse();
	}

	@Override
	public StatusMessageResponse fetchStatusMessageByLightID(LightRequest request)
	{
		return new StatusMessageResponse();
	}

	@Override
	public LightingConfigurationsResponse fetchLigthingConfigurationsByPartNumber(LightingConfigurationRequest request)
	{
		return new LightingConfigurationsResponse();
	}

	@Override
	public InquiryLightResponse fetchLightHistory(InquiryLightRequest inquiryLightRequest)
	{
		return new InquiryLightResponse();
	}

	@Override
	public LightResponse fetchLightHistoryHeader(LightRequest lightRequest)
	{
		return new LightResponse();
	}

	@Override
	public ColumnFilterResponse fetchAllColumnFilters(ColumnFilterRequest columnFilterRequest)
	{
		return new ColumnFilterResponse();
	}

	@Override
	public CurrentAlarmWarningMessageResponse fetchCurrentAlarmStatusMessagesByLight(LightRequest lightRequest)
	{
		return new CurrentAlarmWarningMessageResponse();
	}

	@Override
	public InquiryLightResponse generateFileCSV(InquiryLightRequest inquiryLightRequest)
	{
		return new InquiryLightResponse();
	}

	@Override
	public InquiryLightResponse generateLightHistoryFileCSV(InquiryLightRequest inquiryLightRequest)
	{
		return new InquiryLightResponse();
	}

	@Override
	public InquiryLightResponse fetchSmartpointsToMap(InquiryLightRequest inquiryLightRequest)
	{
		return new InquiryLightResponse();
	}

	@Override
	public StatusMessageResponse fetchStatusMessageById(LightRequest lightRequest)
	{
		return new StatusMessageResponse();
	}

}
