package com.sensus.lc.light.dac;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import com.sensus.common.model.response.CachedResultsResponse;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResponse.Status;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.lc.base.AbstractMockBase;
import com.sensus.lc.base.SituationsEnum;
import com.sensus.lc.base.TestBaseUtil;
import com.sensus.lc.light.model.Configuration;
import com.sensus.lc.light.model.GeocodeLightInfo;
import com.sensus.lc.light.model.LifeCycleStateEnum;
import com.sensus.lc.light.model.Light;
import com.sensus.lc.light.model.PartNumberConfiguration;
import com.sensus.lc.light.model.request.CommunicationFailureRequest;
import com.sensus.lc.light.model.request.ConfigurationMaintenanceRequest;
import com.sensus.lc.light.model.request.FetchByIdRequest;
import com.sensus.lc.light.model.request.LastOperationalDataMaintenanceRequest;
import com.sensus.lc.light.model.request.LightDeleteRequest;
import com.sensus.lc.light.model.request.LightMaintenanceRequest;
import com.sensus.lc.light.model.request.LightRequest;
import com.sensus.lc.light.model.request.OperationalDataMaintenanceRequest;
import com.sensus.lc.light.model.request.ScheduleMaintenanceRequest;

/**
 * The Class MockLightDAC.
 */
public class MockLightDAC extends AbstractMockBase implements ILightDAC
{
	/** The Constant FOUR. */
	private static final Integer FOUR = 4;

	/** The Constant TEN. */
	private static final Integer TEN = 10;

	/** The Constant TEST_EXCEPTION. */
	private static final String TEST_EXCEPTION = "Test Exception";

	/** The Constant ARBITRARY_LIGHT_ID. */
	private static final Integer ARBITRARY_LIGHT_ID = 100;

	/*
	 * (non-Javadoc)
	 * @see com.sensus.lc.light.dac.ILightDAC#fetchAllByRequest(com.sensus.lc.light.model.request.LightRequest)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public InternalResultsResponse<Light> fetchAllByRequest(LightRequest request)
	{
		InternalResultsResponse<Light> response = new InternalResultsResponse<Light>();

		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getResponseResultsError();
		}

		if (getTestControl() == "NO_RESULTS")
		{
			return response;
		}

		response.setStatus(Status.OperationSuccess);

		Light light = TestBaseUtil.createLight(TestBaseUtil.createUserContext());
		light.setLifeCycleState(LifeCycleStateEnum.ACTIVE);
		Configuration configuration = new Configuration();
		List<PartNumberConfiguration> partNumberConfigurations = new ArrayList<PartNumberConfiguration>();
		PartNumberConfiguration partNumberConfiguration = new PartNumberConfiguration();
		partNumberConfiguration.setPercentage(TEN);
		partNumberConfigurations.add(partNumberConfiguration);
		configuration.setPartNumberConfigurations(partNumberConfigurations);
		light.setConfiguration(configuration);

		response.getResultsList().add(light);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.lc.light.dac.ILightDAC#countAllByRequest(com.sensus.lc.light.model.request.LightRequest)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public InternalResultsResponse<Integer> countAllByRequest(LightRequest request)
	{
		InternalResultsResponse<Integer> internalResultsResponse = new InternalResultsResponse<Integer>();

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			internalResultsResponse.addResult(TEN);
			return internalResultsResponse;
		}

		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getResponseResultsError();
		}

		if (getSituationsEnum() == SituationsEnum.EXCEPTION)
		{
			throw new RuntimeException(TEST_EXCEPTION);
		}

		return internalResultsResponse;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.lc.light.dac.ILightDAC#fetchById(com.sensus.lc.light.model.request.FetchByIdRequest)
	 */
	@Override
	public InternalResultsResponse<Light> fetchById(FetchByIdRequest request)
	{
		return testSituationsLightResultsResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.lc.light.dac.ILightDAC#updateLightBase(com.sensus.lc.light.model.request.LightMaintenanceRequest)
	 */
	@Override
	public InternalResponse updateLightBase(LightMaintenanceRequest request)
	{
		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getResponseError();
		}

		return getDefaultInternalResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.lc.light.dac.ILightDAC#updateLight(com.sensus.lc.light.model.request.LightMaintenanceRequest)
	 */
	@Override
	public InternalResponse updateLight(LightMaintenanceRequest request)
	{
		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getResponseError();
		}

		return getDefaultInternalResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.lc.light.dac.ILightDAC#deleteLightReference(com.sensus.lc.light.model.request.LightDeleteRequest)
	 */
	@Override
	public InternalResponse deleteLightReference(LightDeleteRequest request)
	{
		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getResponseError();
		}

		return getDefaultInternalResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.lc.light.dac.ILightDAC#insertLight(com.sensus.lc.light.model.request.LightMaintenanceRequest)
	 */
	@Override
	public InternalResultsResponse<Light> insertLight(LightMaintenanceRequest request)
	{
		return testSituationsLightResultsResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.lc.light.dac.ILightDAC#updateLastOperationalData(com.sensus.lc.light.model.request.
	 * LastOperationalDataMaintenanceRequest)
	 */
	@Override
	public InternalResponse updateLastOperationalData(LastOperationalDataMaintenanceRequest request)
	{
		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getResponseError();
		}

		return getDefaultInternalResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.lc.light.dac.ILightDAC#updateSchedule(com.sensus.lc.light.model.request.ScheduleMaintenanceRequest)
	 */
	@Override
	public InternalResponse updateSchedule(ScheduleMaintenanceRequest request)
	{
		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getResponseError();
		}

		return getDefaultInternalResponse();

	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.lc.light.dac.ILightDAC#updateConfiguration(com.sensus.lc.light.model.request.
	 * ConfigurationMaintenanceRequest)
	 */
	@Override
	public InternalResponse updateConfiguration(ConfigurationMaintenanceRequest request)
	{
		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getResponseError();
		}

		return getDefaultInternalResponse();

	}

	/**
	 * Test situations light results response.
	 * 
	 * @return the internal results response
	 */
	@SuppressWarnings("unchecked")
	private InternalResultsResponse<Light> testSituationsLightResultsResponse()
	{
		InternalResultsResponse<Light> internalResultsResponse = new InternalResultsResponse<Light>();

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			return getLightResponseDefault();
		}

		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getResponseResultsError();
		}

		if (getSituationsEnum() == SituationsEnum.EXCEPTION)
		{
			throw new RuntimeException(TEST_EXCEPTION);
		}

		return internalResultsResponse;
	}

	/**
	 * Gets the light response default.
	 * 
	 * @return the light response default
	 */
	private InternalResultsResponse<Light> getLightResponseDefault()
	{
		InternalResultsResponse<Light> response = new InternalResultsResponse<Light>();

		Configuration configuration = new Configuration();
		List<PartNumberConfiguration> partNumberConfigurations = new ArrayList<PartNumberConfiguration>();
		PartNumberConfiguration partNumberConfiguration = new PartNumberConfiguration();
		partNumberConfiguration.setPercentage(TEN);
		partNumberConfiguration.setIntensityLevel(FOUR);
		partNumberConfigurations.add(partNumberConfiguration);
		configuration.setPartNumberConfigurations(partNumberConfigurations);

		Light light = TestBaseUtil.createLight();
		light.setConfiguration(configuration);

		response.addResult(light);
		return response;
	}

	/**
	 * Gets the geo code response default.
	 * 
	 * @return the geo code response default
	 */
	private InternalResultsResponse<GeocodeLightInfo> getGeoCodeResponseDefault()
	{
		InternalResultsResponse<GeocodeLightInfo> response = new InternalResultsResponse<GeocodeLightInfo>();

		GeocodeLightInfo geoCodeLightInfo = new GeocodeLightInfo();
		geoCodeLightInfo.setLatitudeAvg(35D);
		geoCodeLightInfo.setLongitudeAvg(85D);

		response.addResult(geoCodeLightInfo);

		return response;
	}

	/**
	 * Gets the cached results response default.
	 * 
	 * @return the cached results response default
	 */
	private CachedResultsResponse<GeocodeLightInfo> getCachedResultsResponseDefault()
	{
		CachedResultsResponse<GeocodeLightInfo> response = new CachedResultsResponse<GeocodeLightInfo>();

		GeocodeLightInfo geoCodeLightInfo = new GeocodeLightInfo();
		geoCodeLightInfo.setLatitudeAvg(35D);
		geoCodeLightInfo.setLongitudeAvg(85D);

		response.addResult(geoCodeLightInfo);

		return response;
	}

	/**
	 * Gets the default internal response.
	 * 
	 * @return the default internal response
	 */
	private InternalResponse getDefaultInternalResponse()
	{
		InternalResponse response = new InternalResponse();
		response.setStatus(Status.OperationSuccess);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.lc.light.dac.ILightDAC#fetchMapBoundsByRequest(com.sensus.lc.light.model.request.LightRequest)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public InternalResultsResponse<GeocodeLightInfo> fetchMapBoundsByRequest(LightRequest request)
	{
		InternalResultsResponse<GeocodeLightInfo> internalResultsResponse =
				new InternalResultsResponse<GeocodeLightInfo>();

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			return getGeoCodeResponseDefault();
		}

		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getResponseResultsError();
		}

		if (getSituationsEnum() == SituationsEnum.EXCEPTION)
		{
			throw new RuntimeException(TEST_EXCEPTION);
		}

		return internalResultsResponse;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.lc.light.dac.ILightDAC#resetMinMaxValue(com.sensus.lc.light.model.request.
	 * LastOperationalDataMaintenanceRequest)
	 */
	@Override
	public InternalResponse resetMinMaxValue(LastOperationalDataMaintenanceRequest request)
	{
		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getResponseResultsError();
		}

		return new InternalResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.lc.light.dac.ILightDAC#insertOperationalData(com.sensus.lc.light.model.request.
	 * OperationalDataMaintenanceRequest)
	 */
	@Override
	public InternalResponse insertOperationalData(OperationalDataMaintenanceRequest request)
	{
		InternalResponse internalResponse = new InternalResponse();
		return internalResponse;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.lc.light.dac.ILightDAC#fetchLightsToAddCommunicationFailure(com.sensus.lc.light.model.request.
	 * CommunicationFailureRequest)
	 */
	@Override
	public InternalResultsResponse<Integer> fetchLightsToAddCommunicationFailure(CommunicationFailureRequest request)
	{
		InternalResultsResponse<Integer> response = new InternalResultsResponse<Integer>();
		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			response.addResult(ARBITRARY_LIGHT_ID);
			return response;

		}

		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			response.setStatus(Status.ExceptionError);
			return response;
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.lc.light.dac.ILightDAC#fetchAllToMapByRequest(com.sensus.lc.light.model.request.LightRequest)
	 */
	@Override
	public CachedResultsResponse<GeocodeLightInfo> fetchAllToMapByRequest(LightRequest request)
	{
		CachedResultsResponse<GeocodeLightInfo> cachedResultsResponse =
				new CachedResultsResponse<GeocodeLightInfo>();

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			return getCachedResultsResponseDefault();
		}

		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return null;

		}

		if (getSituationsEnum() == SituationsEnum.EXCEPTION)
		{
			throw new RuntimeException(TEST_EXCEPTION);
		}

		return cachedResultsResponse;
	}

	@Override
	public InternalResultsResponse<Integer> fetchLightsInCommunicationFailure(CommunicationFailureRequest request)
	{
		InternalResultsResponse<Integer> response = new InternalResultsResponse<Integer>();
		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			response.addResult(ARBITRARY_LIGHT_ID);
			return response;

		}

		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			response.setStatus(Status.ExceptionError);
			return response;
		}

		return response;
	}

	@Override
	public InternalResponse calculateLightConsumptionInCommunicationFailure(CommunicationFailureRequest request)
	{
		InternalResponse internalResponse = new InternalResponse();
		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			internalResponse.setStatus(Status.OperationSuccess);
			return internalResponse;
		}

		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			internalResponse.setStatus(Status.ExceptionError);
			return internalResponse;
		}
		return internalResponse;
	}

	@Override
	public InternalResultsResponse<BigInteger> fetchAttributeChanges(LightRequest request)
	{
		InternalResultsResponse<BigInteger> internalResponse = new InternalResultsResponse<BigInteger>();
		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			internalResponse.setStatus(Status.OperationSuccess);
			internalResponse.addResult(new BigInteger("1"));
			return internalResponse;
		}

		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			internalResponse.setStatus(Status.ExceptionError);
			return internalResponse;
		}
		return internalResponse;
	}
}
