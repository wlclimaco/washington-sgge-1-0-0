package com.sensus.lc.light.bcl;

import java.math.BigInteger;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.lc.base.AbstractMockBase;
import com.sensus.lc.base.SituationsEnum;
import com.sensus.lc.base.TestBaseUtil;
import com.sensus.lc.light.model.GeocodeLightInfo;
import com.sensus.lc.light.model.IntensityEnum;
import com.sensus.lc.light.model.LifeCycleStateEnum;
import com.sensus.lc.light.model.Light;
import com.sensus.lc.light.model.PartNumberConfiguration;
import com.sensus.lc.light.model.request.ConfigurationMaintenanceRequest;
import com.sensus.lc.light.model.request.FetchByIdRequest;
import com.sensus.lc.light.model.request.LastOperationalDataMaintenanceRequest;
import com.sensus.lc.light.model.request.LightDeleteRequest;
import com.sensus.lc.light.model.request.LightMaintenanceRequest;
import com.sensus.lc.light.model.request.LightMassUpdateRequest;
import com.sensus.lc.light.model.request.LightRequest;
import com.sensus.lc.light.model.request.ScheduleMaintenanceRequest;

/**
 * The Class MockLightBCL.
 */
public class MockLightBCL extends AbstractMockBase implements ILightBCL
{

	private static final int ARBITRARY_NUMBER_10 = 10;
	/** The Constant TEST_EXCEPTION. */
	private static final String TEST_EXCEPTION = "Test Exception";

	/*
	 * (non-Javadoc)
	 * @see com.sensus.lc.light.bcl.ILightBCL#fetchAllByRequest(com.sensus.lc.light.model.request.LightRequest)
	 */
	@Override
	public InternalResultsResponse<Light> fetchAllByRequest(LightRequest request)
	{
		return testSituationsLightResultsResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.lc.light.bcl.ILightBCL#countAllByRequest(com.sensus.lc.light.model.request.LightRequest)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public InternalResultsResponse<Integer> countAllByRequest(LightRequest request)
	{
		InternalResultsResponse<Integer> internalResultsResponse = new InternalResultsResponse<Integer>();

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			internalResultsResponse.addResult(ARBITRARY_NUMBER_10);
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
	 * @see com.sensus.lc.light.bcl.ILightBCL#fetchById(com.sensus.lc.light.model.request.FetchByIdRequest)
	 */
	@Override
	public InternalResultsResponse<Light> fetchById(FetchByIdRequest request)
	{
		return testSituationsLightResultsResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.lc.light.bcl.ILightBCL#updateLightBase(com.sensus.lc.light.model.request.LightMaintenanceRequest)
	 */
	@Override
	public InternalResponse updateLightBase(LightMaintenanceRequest request)
	{
		return getResponseBySituations();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.lc.light.bcl.ILightBCL#updateLightMass(com.sensus.lc.light.model.request.LightMassUpdateRequest)
	 */
	@Override
	public InternalResponse updateLightMass(LightMassUpdateRequest request)
	{
		return getResponseBySituations();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.lc.light.bcl.ILightBCL#insertLight(com.sensus.lc.light.model.request.LightMaintenanceRequest)
	 */
	@Override
	public InternalResponse insertLight(LightMaintenanceRequest request)
	{
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.lc.light.bcl.ILightBCL#updateLastOperationalData(com.sensus.lc.light.model.request.
	 * LastOperationalDataMaintenanceRequest)
	 */
	@Override
	public InternalResponse updateLastOperationalData(LastOperationalDataMaintenanceRequest request)
	{
		return getResponseBySituations();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.lc.light.bcl.ILightBCL#updateSchedule(com.sensus.lc.light.model.request.ScheduleMaintenanceRequest)
	 */
	@Override
	public InternalResponse updateSchedule(ScheduleMaintenanceRequest request)
	{
		return getResponseBySituations();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.lc.light.bcl.ILightBCL#updateConfiguration(com.sensus.lc.light.model.request.
	 * ConfigurationMaintenanceRequest)
	 */
	@Override
	public InternalResponse updateConfiguration(ConfigurationMaintenanceRequest request)
	{
		return getResponseBySituations();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.lc.light.bcl.ILightBCL#fetchAllToMapByRequest(com.sensus.lc.light.model.request.LightRequest)
	 */
	@Override
	public InternalResultsResponse<GeocodeLightInfo> fetchAllToMapByRequest(LightRequest request)
	{
		return testSituationsGeocodeLightInfoResultsResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.lc.light.bcl.ILightBCL#fetchMapBoundsByRequest(com.sensus.lc.light.model.request.LightRequest)
	 */
	@Override
	public InternalResultsResponse<GeocodeLightInfo> fetchMapBoundsByRequest(LightRequest request)
	{
		return testSituationsGeocodeLightInfoResultsResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.lc.light.bcl.ILightBCL#fetchPartNumberConfigurationsByModelNumber(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public InternalResultsResponse<PartNumberConfiguration> fetchPartNumberConfigurationsByModelNumber(
			String modelNumber)
	{
		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getResponseResultsError();
		}

		PartNumberConfiguration partNumberConfiguration = new PartNumberConfiguration();
		partNumberConfiguration.setDimOnDelay(100);
		partNumberConfiguration.setHardwareSetting(6);
		partNumberConfiguration.setCurrentScale(1);
		partNumberConfiguration.setFullOnRequired(Boolean.FALSE);
		partNumberConfiguration.setIntensityLevel(1);

		InternalResultsResponse<PartNumberConfiguration> response =
				new InternalResultsResponse<PartNumberConfiguration>();
		response.addResult(partNumberConfiguration);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.lc.light.bcl.ILightBCL#fetchLightIntensityByLight(com.sensus.lc.light.model.request.LightRequest)
	 */
	@Override
	public IntensityEnum fetchLightIntensityByLight(LightRequest lightRequest)
	{
		return IntensityEnum.LEVEL_3;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.lc.light.bcl.ILightBCL#fetchLightNotificationHistory(com.sensus.lc.light.model.request.
	 * NotificationHistoryRequest)
	 */
	// @Override
	// public InternalResultsResponse<LightHistory> fetchLightNotificationHistory(NotificationHistoryRequest request)
	// {
	// // TODO Auto-generated method stub
	// return null;
	// }
	/*
	 * (non-Javadoc)
	 * @see com.sensus.lc.light.bcl.ILightBCL#fetchLightHistoryHeader(com.sensus.lc.light.model.request.
	 * NotificationHistoryRequest)
	 */
	// @Override
	// public InternalResultsResponse<HashMap<String, Integer>> fetchLightHistoryHeader(NotificationHistoryRequest
	// request)
	// {
	// // TODO Auto-generated method stub
	// return null;
	// }
	/*
	 * (non-Javadoc)
	 * @see com.sensus.lc.light.bcl.ILightBCL#resetMinMaxValue(com.sensus.lc.light.model.request.LightRequest)
	 */
	@Override
	public InternalResultsResponse<Light> resetMinMaxValue(LightRequest request)
	{
		return getLightResultsResponseBySituations();
	}

	/**
	 * Gets the light results response by situations.
	 * 
	 * @return the light results response by situations
	 */
	@SuppressWarnings("unchecked")
	private InternalResultsResponse<Light> getLightResultsResponseBySituations()
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
	 * Test situations geocode light info results response.
	 * 
	 * @return the internal results response
	 */
	@SuppressWarnings("unchecked")
	private InternalResultsResponse<GeocodeLightInfo> testSituationsGeocodeLightInfoResultsResponse()
	{
		InternalResultsResponse<GeocodeLightInfo> internalResultsResponse =
				new InternalResultsResponse<GeocodeLightInfo>();

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			return getGeocodeLightInfoResponseDefault();
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
	 * Gets the geocode light info response default.
	 * 
	 * @return the geocode light info response default
	 */
	private InternalResultsResponse<GeocodeLightInfo> getGeocodeLightInfoResponseDefault()
	{
		InternalResultsResponse<GeocodeLightInfo> response = new InternalResultsResponse<GeocodeLightInfo>();
		response.addResult(new GeocodeLightInfo());
		return response;
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

		if (getSituationsEnum() == SituationsEnum.VALIDATION)
		{
			internalResultsResponse = getLightResponseDefault();
			internalResultsResponse.getFirstResult().setLifeCycleState(LifeCycleStateEnum.DEACTIVATED);
			return internalResultsResponse;

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
		response.addResult(TestBaseUtil.createLight());

		if (getTestControl() == "LIGHT_DEACTIVATED")
		{
			response.getFirstResult().setLifeCycleState(LifeCycleStateEnum.DEACTIVATED);
		}

		return response;
	}

	/**
	 * Gets the response default.
	 * 
	 * @return the response default
	 */
	private InternalResponse getResponseDefault()
	{
		return new InternalResponse();
	}

	/**
	 * Gets the response by situations.
	 * 
	 * @return the response by situations
	 */
	private InternalResponse getResponseBySituations()
	{
		InternalResponse internalResponse = new InternalResponse();

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			return getResponseDefault();
		}

		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getResponseResultsError();
		}

		if (getSituationsEnum() == SituationsEnum.EXCEPTION)
		{
			throw new RuntimeException(TEST_EXCEPTION);
		}

		return internalResponse;
	}

	@Override
	public InternalResponse deleteLightReferences(LightDeleteRequest request)
	{
		InternalResponse response = new InternalResponse();

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			return response;
		}

		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getResponseResultsError();
		}

		return response;
	}

	@Override
	public InternalResultsResponse<BigInteger> fetchAttributeChanges(LightRequest request)
	{
		return new InternalResultsResponse<BigInteger>();
	}
}
