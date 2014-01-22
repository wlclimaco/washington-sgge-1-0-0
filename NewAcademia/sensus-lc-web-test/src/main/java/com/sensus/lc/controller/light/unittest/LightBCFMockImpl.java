package com.sensus.lc.controller.light.unittest;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.sensus.common.model.response.MaintenanceResponse;
import com.sensus.lc.controller.base.unittest.util.BaseMockImpl;
import com.sensus.lc.controller.util.enums.ModeEnum;
import com.sensus.lc.light.bcf.ILightBCF;
import com.sensus.lc.light.model.Light;
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

public class LightBCFMockImpl extends BaseMockImpl implements ILightBCF
{

	private List<Light> generateOneLight()
	{
		List<Light> lights = new ArrayList<Light>();

		Light light = new Light();
		light.setId(1);

		lights.add(light);

		return lights;
	}

	@Override
	public CountResponse countAllByRequest(LightRequest request)
	{
		CountResponse response = new CountResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			response.setOperationSuccess(true);
			return response;
		}

		return (CountResponse)testOtherDefaultModes(response);
	}

	@Override
	public FetchAllResponse fetchAllByRequest(LightRequest request)
	{
		FetchAllResponse response = new FetchAllResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			response.setOperationSuccess(true);
			return response;
		}

		return (FetchAllResponse)testOtherDefaultModes(response);

	}

	@Override
	public GeocodeLightInfoResponse fetchAllToMapByRequest(LightRequest request)
	{
		GeocodeLightInfoResponse response = new GeocodeLightInfoResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			response.setOperationSuccess(true);
			return response;
		}

		return (GeocodeLightInfoResponse)testOtherDefaultModes(response);
	}

	@Override
	public LightResponse fetchById(FetchByIdRequest request)
	{
		LightResponse response = new LightResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			response.setOperationSuccess(true);
			response.setLight(generateOneLight().get(0));
			return response;
		}

		return (LightResponse)testOtherDefaultModes(response);
	}

	@Override
	public GeocodeLightInfoResponse fetchMapBoundsByRequest(LightRequest request)
	{
		GeocodeLightInfoResponse response = new GeocodeLightInfoResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			response.setOperationSuccess(true);
			return response;
		}

		return (GeocodeLightInfoResponse)testOtherDefaultModes(response);
	}

	@Override
	public LightingConfigurationsResponse fetchPartNumberConfigurationsByModelNumber(String modelNumber)
	{
		LightingConfigurationsResponse response = new LightingConfigurationsResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			response.setOperationSuccess(true);
			return response;
		}

		return (LightingConfigurationsResponse)testOtherDefaultModes(response);
	}

	@Override
	public LightResponse resetMinMaxValue(LightRequest lightRequest)
	{
		LightResponse response = new LightResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			response.setOperationSuccess(true);
			return response;
		}

		return (LightResponse)testOtherDefaultModes(response);
	}

	@Override
	public ConfigurationMaintenanceResponse updateConfiguration(ConfigurationMaintenanceRequest request)
	{
		ConfigurationMaintenanceResponse response = new ConfigurationMaintenanceResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			response.setOperationSuccess(true);
			return response;
		}

		return (ConfigurationMaintenanceResponse)testOtherDefaultModes(response);
	}

	@Override
	public LastOperationalDataMaintenanceResponse updateLastOperationalData(
			LastOperationalDataMaintenanceRequest request)
	{
		LastOperationalDataMaintenanceResponse response = new LastOperationalDataMaintenanceResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			response.setOperationSuccess(true);
			return response;
		}

		return (LastOperationalDataMaintenanceResponse)testOtherDefaultModes(response);
	}

	@Override
	public MaintenanceResponse updateLightBase(LightMaintenanceRequest request)
	{
		MaintenanceResponse response = new MaintenanceResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			response.setOperationSuccess(true);
			return response;
		}

		return (MaintenanceResponse)testOtherDefaultModes(response);
	}

	@Override
	public MaintenanceResponse updateLightMass(LightMassUpdateRequest request)
	{
		MaintenanceResponse response = new MaintenanceResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			response.setOperationSuccess(true);
			return response;
		}

		return (MaintenanceResponse)testOtherDefaultModes(response);
	}

	@Override
	public ScheduleMaintenanceResponse updateSchedule(ScheduleMaintenanceRequest request)
	{
		ScheduleMaintenanceResponse response = new ScheduleMaintenanceResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			response.setOperationSuccess(true);
			return response;
		}

		return (ScheduleMaintenanceResponse)testOtherDefaultModes(response);
	}

	@Override
	public ChangesResponse fetchAttributeChanges(LightRequest request)
	{
		ChangesResponse response = new ChangesResponse();
		response.setFlexnetList(Arrays.asList(new BigInteger("1")));
		return response;
	}
}
