package com.sensus.lc.light.dac.mybatis;

import static com.sensus.common.util.SensusMyBatisDacHelper.doInsert;
import static com.sensus.common.util.SensusMyBatisDacHelper.doQueryForList;
import static com.sensus.common.util.SensusMyBatisDacHelper.doQueryForObject;
import static com.sensus.common.util.SensusMyBatisDacHelper.doRemove;
import static com.sensus.common.util.SensusMyBatisDacHelper.doUpdate;

import java.math.BigInteger;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sensus.common.model.SensusModel;
import com.sensus.common.model.SensusModel.PersistanceActionEnum;
import com.sensus.common.model.response.CachedResultsResponse;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.lc.base.util.LCDateUtil;
import com.sensus.lc.light.dac.ILightDAC;
import com.sensus.lc.light.model.Configuration;
import com.sensus.lc.light.model.GeocodeLightInfo;
import com.sensus.lc.light.model.LastOperationalData;
import com.sensus.lc.light.model.Light;
import com.sensus.lc.light.model.LightSchedule;
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
 * Implementation for ILightDAC.
 * 
 * @see ILightDAC
 * 
 * @author Thiago
 * 
 */
public class LightDACImpl extends SqlSessionDaoSupport implements ILightDAC
{

	private static final Logger LOG = LoggerFactory.getLogger(LightDACImpl.class);

	/**
	 * Queries Constants.
	 */
	private static final String COUNT_ALL_BY_REQUEST = "countAllByRequest";
	private static final String INSERT_LIGHT = "InsertLight";
	private static final String UPDATE_LIGHT = "UpdateLight";
	private static final String UPDATE_LAST_OPERATIONAL_DATA = "LastOperationalData.UpdateLastOperationalData";
	private static final String RESET_MIN_MAX = "LastOperationalData.resetMinMax";
	private static final String INSERT_LAST_OPERATIONAL_DATA = "LastOperationalData.InsertLastOperationalData";
	private static final String INSERT_OPERATIONAL_DATA_VALUE = "LastOperationalData.InsertOperationalDataValue";
	private static final String INSERT_SCHEDULE = "LightSchedule.InsertSchedule";
	private static final String UPDATE_SCHEDULE = "LightSchedule.UpdateSchedule";
	private static final String INSERT_CONFIGURATION = "Configuration.InsertConfiguration";
	private static final String UPDATE_CONFIGURATION = "Configuration.UpdateConfiguration";
	private static final String FETCH_ALL_LIGHTS_BY_REQUEST = "fetchAllLightsByRequest";
	private static final String FETCH_ATTRIBUTE_CHANGES = "fetchAttributeChanges";
	private static final String FETCH_ALL_TO_MAP_BY_REQUEST = "Map.fetchAllToMapByRequest";
	private static final String FETCH_MAP_BOUNDS_BY_REQUEST = "Map.fetchMapBoundsByRequest";
	private static final String FETCH_LIGHT_BY_ID = "fetchLightByIdOrRni";
	private static final String FETCH_LIGHTS_TO_ADD_COMMUNICATION_FAILURE = "fetchLightsToAddCommunicationFailure";
	private static final String FETCH_LIGHTS_IN_COMMUNICATION_FAILURE = "fetchLightsInCommunicationFailure";

	private static final String CALCULATE_LIGHT_CONSUMPTION_IN_COMMUNICATION_FAILURE =
			"calculateLightConsumptionInCommunicationFailure";

	/*
	 * (non-Javadoc)
	 * @see com.sensus.lc.light.dac.ILightDAC#fetchAttributeChanges(com.sensus.lc.light.model.request.LightRequest)
	 */
	@Override
	public InternalResultsResponse<BigInteger> fetchAttributeChanges(LightRequest request)
	{
		InternalResultsResponse<BigInteger> response = new InternalResultsResponse<BigInteger>();
		doQueryForList(getSqlSession(), FETCH_ATTRIBUTE_CHANGES, request, response);
		return response;

	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.light.dac.ILightDAC#fetchAllByRequest(com.sensus.mlc.light.model.request.LightRequest)
	 */
	@Override
	public InternalResultsResponse<Light> fetchAllByRequest(LightRequest request)
	{
		InternalResultsResponse<Light> response = new InternalResultsResponse<Light>();
		Integer amount = countAllByRequest(request).getFirstResult();
		if (ValidationUtil.isNullOrZero(amount))
		{
			return response;
		}
		response.getResultsSetInfo().setTotalRowsAvailable(amount);
		doQueryForList(getSqlSession(), FETCH_ALL_LIGHTS_BY_REQUEST, request, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.light.dac.ILightDAC#fetchAllToMapByRequest(com.sensus.mlc.light.model.request.LightRequest)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public CachedResultsResponse<GeocodeLightInfo> fetchAllToMapByRequest(LightRequest request)
	{
		CachedResultsResponse<GeocodeLightInfo> response = new CachedResultsResponse<GeocodeLightInfo>();
		response.getResultsList().addAll(doQueryForList(getSqlSession(), FETCH_ALL_TO_MAP_BY_REQUEST,
				request));
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.light.dac.ILightDAC#fetchAllToMapByRequest(com.sensus.mlc.light.model.request.LightRequest)
	 */
	@Override
	public InternalResultsResponse<GeocodeLightInfo> fetchMapBoundsByRequest(LightRequest request)
	{
		InternalResultsResponse<GeocodeLightInfo> response = new InternalResultsResponse<GeocodeLightInfo>();
		doQueryForList(getSqlSession(), FETCH_MAP_BOUNDS_BY_REQUEST, request, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.light.dac.ILightDAC#countAllByRequest(com.sensus.mlc.light.model.request.LightRequest)
	 */
	@Override
	public InternalResultsResponse<Integer> countAllByRequest(LightRequest request)
	{
		InternalResultsResponse<Integer> response = new InternalResultsResponse<Integer>();
		doQueryForList(getSqlSession(), COUNT_ALL_BY_REQUEST, request, response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.light.dac.ILightDAC#fetchByIdOrRni(com.sensus.mlc.light.model.request.FetchByIdRequest)
	 */
	@Override
	public InternalResultsResponse<Light> fetchById(FetchByIdRequest request)
	{
		InternalResultsResponse<Light> response = new InternalResultsResponse<Light>();
		Light light = (Light)doQueryForObject(getSqlSession(), FETCH_LIGHT_BY_ID, request);

		if (!ValidationUtil.isNull(light))
		{
			response.addResult(light);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.light.dac.ILightDAC#insertLight(com.sensus.mlc.light.model.request.LightMaintenanceRequest)
	 */
	@Override
	public InternalResultsResponse<Light> insertLight(LightMaintenanceRequest request)
	{
		int insertCount = 0;
		InternalResultsResponse<Light> response = new InternalResultsResponse<>();

		// First insert the root
		// Is successful the unique-id will be populated back into the object.
		insertCount = doInsert(getSqlSession(), INSERT_LIGHT, request.getLight(), response);

		if (!response.isInError())
		{
			// Next traverse the object graph and "maintain" the associations
			insertCount += maintainLightAssociations(request.getLight(), response);

			// Finally, if something was inserted then add that inserted light to the response.
			if (insertCount > 0)
			{
				response.addResult(request.getLight());
			}
		}

		return response;
	}

	/*
	 * Full Object graph update
	 * (non-Javadoc)
	 * @see com.sensus.mlc.light.dac.ILightDAC#updateLight(com.sensus.mlc.light.model.request.LightMaintenanceRequest)
	 */
	@Override
	public InternalResponse updateLight(LightMaintenanceRequest request)
	{
		InternalResponse response = updateLightBase(request);
		if (response.isInError())
		{
			return response;
		}

		// Next traverse the object graph and "maintain" all the associations
		maintainLightAssociations(request.getLight(), response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.light.dac.ILightDAC#updateLightBase(com.sensus.mlc.light.model.request.LightMaintenanceRequest)
	 */
	@Override
	public InternalResponse updateLightBase(LightMaintenanceRequest request)
	{
		InternalResponse response = new InternalResponse();

		// First update the root if necessary.
		if (!ValidationUtil.isNull(request.getLight().getModelAction())
				&& SensusModel.PersistanceActionEnum.UPDATE.equals(request.getLight().getModelAction()))
		{
			doUpdate(getSqlSession(), UPDATE_LIGHT, request.getLight(), response);

			// If the update failed then we will log a message since this is the root object and the action enum was set
			// to UPDATE.
			if (response.getStatus() == InternalResponse.Status.NoRowsUpdatedError)
			{
				LOG.error("NoRowsUpdatedError has occured for Light model object with Id["
						+ request.getLight().getDeviceId() + "]");
			}
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.light.dac.ILightDAC#deleteLightReference(com.sensus.mlc.light.model.request.LightDeleteRequest)
	 */
	@Override
	public InternalResponse deleteLightReference(LightDeleteRequest request)
	{
		// The LightReferenceEnum decides which DELETE COMMAND will be executed:
		// DELETE_LIGHT_REFERENCES: Delete Light References in the tables: light_tag, light_grouping and
		// schedule_membership
		// DELETE_ADDR_TAGS: Delete Light Addr Tags
		InternalResponse response = new InternalResponse();
		doRemove(getSqlSession(), request.getDeleteLightReference().getValue(), request.getLight(), response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.light.dac.ILightDAC#updateLastOperationalData(com.sensus.mlc.light.model.request.
	 * LastOperationalDataMaintenanceRequest)
	 */
	@Override
	public InternalResponse updateLastOperationalData(LastOperationalDataMaintenanceRequest request)
	{
		InternalResponse response = new InternalResponse();
		maintainLastOperationalData(request.getLightId(), request.getLastOperationalData(), response);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.light.dac.ILightDAC#updateSchedule(com.sensus.mlc.light.model.request.ScheduleMaintenanceRequest)
	 */
	@Override
	public InternalResponse updateSchedule(ScheduleMaintenanceRequest request)
	{
		InternalResponse response = new InternalResponse();
		maintainSchedule(request.getLightId(), request.getSchedule(), response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.light.dac.ILightDAC#updateConfiguration(com.sensus.mlc.light.model.request.
	 * ConfigurationMaintenanceRequest)
	 */
	@Override
	public InternalResponse updateConfiguration(ConfigurationMaintenanceRequest request)
	{
		InternalResponse response = new InternalResponse();
		maintainConfiguration(request.getLightId(), request.getConfiguration(), response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.light.dac.ILightDAC#resetMinMaxValue(com.sensus.mlc.light.model.request.
	 * LastOperationalDataMaintenanceRequest)
	 */
	@Override
	public InternalResponse resetMinMaxValue(LastOperationalDataMaintenanceRequest request)
	{
		InternalResultsResponse<Object> response = new InternalResultsResponse<>();
		doUpdate(getSqlSession(), RESET_MIN_MAX, request.getLastOperationalData(), response);
		return response;
	}

	@Override
	public InternalResponse insertOperationalData(OperationalDataMaintenanceRequest request)
	{
		InternalResponse response = new InternalResponse();
		request.getOperationalData().setModelAction(PersistanceActionEnum.INSERT);
		request.getOperationalData().setCreateUser(request.getUserContext().getUserId());
		request.getOperationalData().setCreateDate(LCDateUtil.getNewDateUTC());
		doInsert(getSqlSession(), INSERT_OPERATIONAL_DATA_VALUE, request.getOperationalData(), response);
		return response;
	}

	@Override
	public InternalResultsResponse<Integer> fetchLightsToAddCommunicationFailure(CommunicationFailureRequest request)
	{
		InternalResultsResponse<Integer> response = new InternalResultsResponse<Integer>();
		doQueryForList(getSqlSession(), FETCH_LIGHTS_TO_ADD_COMMUNICATION_FAILURE, request, response);
		return response;
	}

	@Override
	public InternalResultsResponse<Integer> fetchLightsInCommunicationFailure(CommunicationFailureRequest request)
	{
		InternalResultsResponse<Integer> response = new InternalResultsResponse<Integer>();
		doQueryForList(getSqlSession(), FETCH_LIGHTS_IN_COMMUNICATION_FAILURE, request, response);
		return response;
	}

	@Override
	public InternalResponse calculateLightConsumptionInCommunicationFailure(CommunicationFailureRequest request)
	{
		InternalResultsResponse<Integer> response = new InternalResultsResponse<Integer>();
		doQueryForList(getSqlSession(), CALCULATE_LIGHT_CONSUMPTION_IN_COMMUNICATION_FAILURE, request, response);
		return response;
	}

	/**
	 * Maintain light associations.
	 * 
	 * @param light the light
	 * @param response the response
	 * @return the int
	 */
	private int maintainLightAssociations(Light light, InternalResponse response)
	{
		// By now the root Light was successfully inserted or updated and contains the root key, light-Id
		// So we traverse the object hierarchy insert/updating/deleting based on model-object persistence value.
		int count = 0;

		// First the Operational Data association
		count += maintainLastOperationalData(light.getId(), light.getLastOperationalData(), response);

		// Next is Schedule
		count += maintainSchedule(light.getId(), light.getLightSchedule(), response);

		// Next is Configuration
		count += maintainConfiguration(light.getId(), light.getConfiguration(), response);

		return count;
	}

	/**
	 * Maintain configuration.
	 * 
	 * @param lightId the light id
	 * @param configuration the configuration
	 * @param response the response
	 * @return the int
	 */
	private int maintainConfiguration(Integer lightId, Configuration configuration, InternalResponse response)
	{
		int count = 0;

		// If null then do nothing.
		if (ValidationUtil.isNull(configuration))
		{
			return 0;
		}

		// Make sure we set the parent key
		configuration.setParentId(lightId);

		// If the model action is set then process it.
		if (!ValidationUtil.isNull(configuration.getModelAction()))
		{
			switch (configuration.getModelAction())
			{
				case UPDATE:
					count = updateConfiguration(configuration, response);
					break;
				case INSERT:
					count = insertConfiguration(configuration, response);
					break;
				default:
					break;
			}
		}

		return count;
	}

	/**
	 * Insert configuration.
	 * 
	 * @param configuration the configuration
	 * @param response the response
	 * @return the int
	 */
	private int insertConfiguration(Configuration configuration, InternalResponse response)
	{
		return doInsert(getSqlSession(), INSERT_CONFIGURATION, configuration, response);
	}

	/**
	 * Update configuration.
	 * 
	 * @param configuration the configuration
	 * @param response the response
	 * @return the int
	 */
	private int updateConfiguration(Configuration configuration, InternalResponse response)
	{
		return doInsert(getSqlSession(), UPDATE_CONFIGURATION, configuration, response);
	}

	/**
	 * Maintain schedule.
	 * 
	 * @param lightId the light id
	 * @param schedule the schedule
	 * @param response the response
	 * @return the int
	 */
	private int maintainSchedule(Integer lightId, LightSchedule schedule, InternalResponse response)
	{
		int count = 0;

		// If null then do nothing.
		if (ValidationUtil.isNull(schedule))
		{
			return 0;
		}

		// Make sure we set the parent key
		schedule.setParentId(lightId);

		// If the model action is set then process it.
		if (!ValidationUtil.isNull(schedule.getModelAction()))
		{
			switch (schedule.getModelAction())
			{
				case UPDATE:
					count = updateSchedule(schedule, response);
					break;
				case INSERT:
					count = insertSchedule(schedule, response);
					break;
				default:
					break;
			}
		}

		return count;
	}

	/**
	 * Insert schedule.
	 * 
	 * @param schedule the schedule
	 * @param response the response
	 * @return the int
	 */
	private int insertSchedule(LightSchedule schedule, InternalResponse response)
	{
		return doInsert(getSqlSession(), INSERT_SCHEDULE, schedule, response);
	}

	/**
	 * Update schedule.
	 * 
	 * @param schedule the schedule
	 * @param response the response
	 * @return the int
	 */
	private int updateSchedule(LightSchedule schedule, InternalResponse response)
	{
		return doInsert(getSqlSession(), UPDATE_SCHEDULE, schedule, response);
	}

	/**
	 * Maintain last operational data.
	 * 
	 * @param parentId the parent id
	 * @param lod the lod
	 * @param response the response
	 * @return the int
	 */
	private int maintainLastOperationalData(Integer parentId, LastOperationalData lod, InternalResponse response)
	{
		int count = 0;

		// If null then do nothing.
		if (ValidationUtil.isNull(lod))
		{
			return 0;
		}

		// Make sure we set the parent key
		lod.setParentId(parentId);

		// If the model action is set then process it.
		if (!ValidationUtil.isNull(lod.getModelAction()))
		{
			switch (lod.getModelAction())
			{
				case UPDATE:
					count = updateLastOperationalData(lod, response);
					break;
				case INSERT:
					count = insertLastOperationalData(lod, response);
					break;
				default:
					break;
			}
		}

		return count;
	}

	/**
	 * Insert last operational data.
	 * 
	 * @param lod the lod
	 * @param response the response
	 * @return the int
	 */
	private int insertLastOperationalData(LastOperationalData lod, InternalResponse response)
	{
		return doInsert(getSqlSession(), INSERT_LAST_OPERATIONAL_DATA, lod, response);
	}

	/**
	 * Update last operational data.
	 * 
	 * @param lod the lod
	 * @param response the response
	 * @return the int
	 */
	private int updateLastOperationalData(LastOperationalData lod, InternalResponse response)
	{
		return doUpdate(getSqlSession(), UPDATE_LAST_OPERATIONAL_DATA, lod, response);
	}
}
