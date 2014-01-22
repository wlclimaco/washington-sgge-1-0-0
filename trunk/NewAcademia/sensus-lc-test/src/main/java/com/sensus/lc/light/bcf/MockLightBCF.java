package com.sensus.lc.light.bcf;

import static com.sensus.lc.base.TestBaseUtil.NUMBER_RANGE;
import static com.sensus.lc.base.TestBaseUtil.RANDOM;

import java.math.BigInteger;
import java.util.Arrays;

import com.sensus.common.model.response.MaintenanceResponse;
import com.sensus.lc.base.AbstractMockBase;
import com.sensus.lc.base.TestBaseUtil;
import com.sensus.lc.light.model.Configuration;
import com.sensus.lc.light.model.GeocodeLightInfo;
import com.sensus.lc.light.model.LastOperationalData;
import com.sensus.lc.light.model.LightSchedule;
import com.sensus.lc.light.model.PartNumberConfiguration;
import com.sensus.lc.light.model.request.ConfigurationMaintenanceRequest;
import com.sensus.lc.light.model.request.FetchByIdRequest;
import com.sensus.lc.light.model.request.LastOperationalDataMaintenanceRequest;
import com.sensus.lc.light.model.request.LightMaintenanceRequest;
import com.sensus.lc.light.model.request.LightMassUpdateRequest;
import com.sensus.lc.light.model.request.LightRequest;
import com.sensus.lc.light.model.request.ScheduleMaintenanceRequest;
import com.sensus.lc.light.model.response.ChangesResponse;
import com.sensus.lc.light.model.response.ConfigurationMaintenanceResponse;
import com.sensus.lc.light.model.response.CountResponse;
import com.sensus.lc.light.model.response.FetchAllResponse;
import com.sensus.lc.light.model.response.GeocodeLightInfoResponse;
import com.sensus.lc.light.model.response.LastOperationalDataMaintenanceResponse;
import com.sensus.lc.light.model.response.LightResponse;
import com.sensus.lc.light.model.response.LightingConfigurationsResponse;
import com.sensus.lc.light.model.response.ScheduleMaintenanceResponse;

public class MockLightBCF extends AbstractMockBase implements ILightBCF
{

	@Override
	public FetchAllResponse fetchAllByRequest(LightRequest request)
	{
		FetchAllResponse response = new FetchAllResponse();
		response.setLightList(Arrays.asList(TestBaseUtil.createLight()));
		return response;
	}

	@Override
	public CountResponse countAllByRequest(LightRequest request)
	{
		CountResponse response = new CountResponse();
		response.setCount(RANDOM.nextInt(NUMBER_RANGE));
		return response;
	}

	@Override
	public LightResponse fetchById(FetchByIdRequest request)
	{
		LightResponse response = new LightResponse();
		response.setLights(Arrays.asList(TestBaseUtil.createLight()));
		return response;
	}

	@Override
	public MaintenanceResponse updateLightBase(LightMaintenanceRequest request)
	{
		return new MaintenanceResponse();
	}

	@Override
	public MaintenanceResponse updateLightMass(LightMassUpdateRequest request)
	{
		return new MaintenanceResponse();
	}

	@Override
	public LightResponse resetMinMaxValue(LightRequest lightRequest)
	{
		LightResponse response = new LightResponse();
		response.setLights(Arrays.asList(TestBaseUtil.createLight()));
		return response;
	}

	@Override
	public GeocodeLightInfoResponse fetchAllToMapByRequest(LightRequest request)
	{
		GeocodeLightInfo geocode = new GeocodeLightInfo();
		GeocodeLightInfoResponse response = new GeocodeLightInfoResponse();
		response.setGeocodeLightList(Arrays.asList(geocode));
		return response;
	}

	@Override
	public GeocodeLightInfoResponse fetchMapBoundsByRequest(LightRequest request)
	{
		GeocodeLightInfo geocode = new GeocodeLightInfo();
		GeocodeLightInfoResponse response = new GeocodeLightInfoResponse();
		response.setGeocodeLightList(Arrays.asList(geocode));
		return response;
	}

	@Override
	public LightingConfigurationsResponse fetchPartNumberConfigurationsByModelNumber(String modelNumber)
	{
		PartNumberConfiguration partNumber = new PartNumberConfiguration();
		LightingConfigurationsResponse response = new LightingConfigurationsResponse();
		response.setLightingConfigurations(Arrays.asList(partNumber));
		return response;
	}

	@Override
	public LastOperationalDataMaintenanceResponse updateLastOperationalData(
			LastOperationalDataMaintenanceRequest request)
	{
		LastOperationalDataMaintenanceResponse response = new LastOperationalDataMaintenanceResponse();
		response.setLastOperationalDataList(Arrays.asList(new LastOperationalData()));
		return response;
	}

	@Override
	public ScheduleMaintenanceResponse updateSchedule(ScheduleMaintenanceRequest request)
	{
		ScheduleMaintenanceResponse response = new ScheduleMaintenanceResponse();
		response.setLightScheduleList(Arrays.asList(new LightSchedule()));
		return response;
	}

	@Override
	public ConfigurationMaintenanceResponse updateConfiguration(ConfigurationMaintenanceRequest request)
	{
		ConfigurationMaintenanceResponse response = new ConfigurationMaintenanceResponse();
		response.setConfigurationList(Arrays.asList(new Configuration()));
		return response;
	}

	@Override
	public ChangesResponse fetchAttributeChanges(LightRequest request)
	{
		ChangesResponse response = new ChangesResponse();
		response.setFlexnetList(Arrays.asList(new BigInteger("1")));
		return response;
	}
}
