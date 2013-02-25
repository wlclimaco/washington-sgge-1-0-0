package com.sensus.mlc.smartpoint.bcf;

import com.sensus.mlc.base.AbstractMockBase;
import com.sensus.mlc.base.model.request.LightSelectionRequest;
import com.sensus.mlc.process.model.response.ProcessResponse;
import com.sensus.mlc.smartpoint.model.request.ColumnFilterRequest;
import com.sensus.mlc.smartpoint.model.request.CustomSearchRequest;
import com.sensus.mlc.smartpoint.model.request.LightRequest;
import com.sensus.mlc.smartpoint.model.request.LightStatusNotificationRequest;
import com.sensus.mlc.smartpoint.model.response.ColumnFilterResponse;
import com.sensus.mlc.smartpoint.model.response.CustomSearchResponse;
import com.sensus.mlc.smartpoint.model.response.LightResponse;

public class MockSmartPointUpdaterBCF extends AbstractMockBase implements ISmartPointUpdaterBCF
{

	@Override
	public LightResponse upsertLightProperty(LightStatusNotificationRequest lightStatusNotificationRequest)
	{
		return new LightResponse();
	}

	@Override
	public LightResponse updateLightProtected(LightRequest lightRequest)
	{
		return new LightResponse();
	}

	@Override
	public CustomSearchResponse insertCustomSearch(CustomSearchRequest request)
	{
		return new CustomSearchResponse();
	}

	@Override
	public CustomSearchResponse deleteCustomSearch(CustomSearchRequest request)
	{
		return new CustomSearchResponse();
	}

	@Override
	public LightResponse updateLightLatLng(LightRequest lightRequest)
	{
		return new LightResponse();
	}

	@Override
	public ProcessResponse insertCSVProcess(LightSelectionRequest lightSelectionRequest)
	{
		return new ProcessResponse();
	}

	@Override
	public LightResponse resetMinMaxValue(LightRequest lightRequest)
	{
		return new LightResponse();
	}

	@Override
	public ColumnFilterResponse insertColumnFilters(ColumnFilterRequest columnFilterRequest)
	{
		return new ColumnFilterResponse();
	}

	@Override
	public LightResponse updateLightStatus(LightRequest lightRequest)
	{
		return new LightResponse();
	}

}
