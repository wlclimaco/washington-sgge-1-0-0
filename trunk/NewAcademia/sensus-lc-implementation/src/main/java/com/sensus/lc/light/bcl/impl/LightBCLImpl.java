package com.sensus.lc.light.bcl.impl;

import static com.sensus.common.validation.ValidationUtil.isNull;
import static com.sensus.common.validation.ValidationUtil.isNullOrEmpty;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import com.sensus.common.model.Message;
import com.sensus.common.model.SensusModel.PersistanceActionEnum;
import com.sensus.common.model.UserContext;
import com.sensus.common.model.response.CachedResultsResponse;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.lc.light.bcl.ILightBCL;
import com.sensus.lc.light.dac.ILightDAC;
import com.sensus.lc.light.dac.IPartNumberConfigurationDAC;
import com.sensus.lc.light.model.GeocodeLightInfo;
import com.sensus.lc.light.model.IntensityEnum;
import com.sensus.lc.light.model.LastOperationalData;
import com.sensus.lc.light.model.Light;
import com.sensus.lc.light.model.PartNumberConfiguration;
import com.sensus.lc.light.model.PropertyEnum;
import com.sensus.lc.light.model.criteria.ActionCriteria;
import com.sensus.lc.light.model.criteria.LightCriteria;
import com.sensus.lc.light.model.request.ConfigurationMaintenanceRequest;
import com.sensus.lc.light.model.request.FetchByIdRequest;
import com.sensus.lc.light.model.request.LastOperationalDataMaintenanceRequest;
import com.sensus.lc.light.model.request.LightDeleteRequest;
import com.sensus.lc.light.model.request.LightMaintenanceRequest;
import com.sensus.lc.light.model.request.LightMassUpdateRequest;
import com.sensus.lc.light.model.request.LightRequest;
import com.sensus.lc.light.model.request.ScheduleMaintenanceRequest;
import com.sensus.lc.process.bcl.IProcessBCL;
import com.sensus.lc.process.model.LCAction;
import com.sensus.lc.process.model.LCActionParameter;
import com.sensus.lc.process.model.LCActionTypeEnum;
import com.sensus.lc.process.model.Process;
import com.sensus.lc.process.model.ProcessItem;
import com.sensus.lc.process.model.ProcessItemStatusEnum;
import com.sensus.lc.process.model.request.ProcessRequest;
import com.sensus.lc.process.util.ProcessUtil;

/**
 * Business component for Light area.
 * 
 * @author - QAT
 * 
 */
public class LightBCLImpl implements ILightBCL
{

	/** Internationalization Attributes. */
	private static final String RESET_MIN_MAX_FAILED = "sensus.mlc.lightbclimpl.update.min.max.failed";

	/**
	 * DAC Attributes.
	 */
	private ILightDAC lightDAC;
	private IPartNumberConfigurationDAC partNumberConfigurationDAC;

	/**
	 * BCL Attributes.
	 */
	private IProcessBCL processBCL;

	/**
	 * Gets the light dac.
	 * 
	 * @return the light dac
	 */
	public ILightDAC getLightDAC()
	{
		return lightDAC;
	}

	/**
	 * Sets the light dac.
	 * 
	 * @param lightDAC the new light dac
	 */
	public void setLightDAC(ILightDAC lightDAC)
	{
		this.lightDAC = lightDAC;
	}

	/**
	 * Gets the part number configuration dac.
	 * 
	 * @return the partNumberConfigurationDAC
	 */
	public IPartNumberConfigurationDAC getPartNumberConfigurationDAC()
	{
		return partNumberConfigurationDAC;
	}

	/**
	 * Sets the part number configuration dac.
	 * 
	 * @param partNumberConfigurationDAC the partNumberConfigurationDAC to set
	 */
	public void setPartNumberConfigurationDAC(IPartNumberConfigurationDAC partNumberConfigurationDAC)
	{
		this.partNumberConfigurationDAC = partNumberConfigurationDAC;
	}

	/**
	 * Gets the process bcl.
	 * 
	 * @return the process bcl
	 */
	public IProcessBCL getProcessBCL()
	{
		return processBCL;
	}

	/**
	 * Sets the process bcl.
	 * 
	 * @param processBCL the new process bcl
	 */
	@Resource
	public void setProcessBCL(IProcessBCL processBCL)
	{
		this.processBCL = processBCL;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.lc.light.bcl.ILightBCL#fetchAttributeChanges(com.sensus.lc.light.model.request.LightRequest)
	 */
	@Override
	public InternalResultsResponse<BigInteger> fetchAttributeChanges(LightRequest request)
	{
		return getLightDAC().fetchAttributeChanges(request);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.light.bcl.ILightBCL#fetchAllByRequest(com.sensus.mlc.light.model.request.LightRequest)
	 */
	@Override
	public InternalResultsResponse<Light> fetchAllByRequest(LightRequest request)
	{
		return getLightDAC().fetchAllByRequest(request);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.light.bcl.ILightBCL#fetchAllToMapByRequest(com.sensus.mlc.light.model.request.LightRequest)
	 */
	@Override
	public InternalResultsResponse<GeocodeLightInfo> fetchAllToMapByRequest(LightRequest request)
	{
		CachedResultsResponse<GeocodeLightInfo> cachedResponse = getLightDAC().fetchAllToMapByRequest(request);

		InternalResultsResponse<GeocodeLightInfo> response = new InternalResultsResponse<GeocodeLightInfo>();
		response.getResultsList().addAll(cachedResponse.getResultsList());

		Integer amount = countAllByRequest(request).getFirstResult();
		response.getResultsSetInfo().setEndRow(amount);

		// Remove geocode data to get ALL lights
		removeGeocodeData(request);

		amount = countAllByRequest(request).getFirstResult();
		response.getResultsSetInfo().setTotalRowsAvailable(amount);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.light.bcl.ILightBCL#fetchMapBoundsByRequest(com.sensus.mlc.light.model.request.LightRequest)
	 */
	@Override
	public InternalResultsResponse<GeocodeLightInfo> fetchMapBoundsByRequest(LightRequest request)
	{
		return getLightDAC().fetchMapBoundsByRequest(request);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.light.bcl.ILightBCL#fetchById(com.sensus.mlc.light.model.request.FetchByIdRequest)
	 */
	@Override
	public InternalResultsResponse<Light> fetchById(FetchByIdRequest request)
	{
		InternalResultsResponse<Light> response = getLightDAC().fetchById(request);
		if (response.isInError()
				|| isNull(response.getFirstResult())
				|| isNull(response.getFirstResult().getConfiguration()))
		{
			return response;
		}

		Light light = response.getFirstResult();
		for (PartNumberConfiguration spnConfig : light.getConfiguration().getPartNumberConfigurations())
		{
			if (spnConfig.getIntensityLevel() >= light.getIntensityValue())
			{
				IntensityEnum iEnum = IntensityEnum.enumForValue(spnConfig.getIntensityLevel());
				iEnum.setPercentage(spnConfig.getPercentage());
				light.setIntensity(iEnum);
				break;
			}
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.light.bcl.ILightBCL#fetchPartNumberConfigurationsByModelNumber(java.lang.String)
	 */
	@Override
	public InternalResultsResponse<PartNumberConfiguration> fetchPartNumberConfigurationsByModelNumber(
			String modelNumber)
	{
		return getPartNumberConfigurationDAC().fetchPartNumberConfigurationsByModelNumber(modelNumber);
	}

	/**
	 * Fetch light intensity by light.
	 * 
	 * @param lightRequest the light request
	 * @return LightIntensityEnum
	 */
	@Override
	public IntensityEnum fetchLightIntensityByLight(LightRequest lightRequest)
	{
		ActionCriteria actionCriteria = lightRequest.getActionCriteria();
		if (IntensityEnum.LEVEL_0.getValue().equals(actionCriteria.getPercentage()))
		{
			return IntensityEnum.LEVEL_0;
		}

		// Important to have the correct part number to filter.
		LightCriteria lightCriteria = lightRequest.getLightCriteria();
		Integer lightId = 0;
		if (!isNullOrEmpty(lightCriteria.getLightIdList()))
		{
			lightId = lightCriteria.getLightIdList().get(0);
		}

		FetchByIdRequest fetchByIdRequest = new FetchByIdRequest(lightId);
		InternalResultsResponse<Light> response = fetchById(fetchByIdRequest);

		if (response.isInError())
		{
			return IntensityEnum.LEVEL_0;
		}

		List<PartNumberConfiguration> listLightIntensityPercentage = new ArrayList<PartNumberConfiguration>();
		if (response.hasResults() && !isNull(response.getFirstResult().getConfiguration()))
		{
			Light light = response.getFirstResult();
			listLightIntensityPercentage = light.getConfiguration().getPartNumberConfigurations();
		}

		Collections.sort(listLightIntensityPercentage, new PartNumberConfiguration());
		if (isNullOrEmpty(listLightIntensityPercentage))
		{
			return IntensityEnum.LEVEL_6;
		}

		for (PartNumberConfiguration spnConfig : listLightIntensityPercentage)
		{
			if (spnConfig.getPercentage() >= actionCriteria.getPercentage())
			{
				IntensityEnum iEnum = IntensityEnum.enumForValue(spnConfig.getIntensityLevel());
				iEnum.setPercentage(spnConfig.getPercentage());
				return iEnum;
			}
		}

		return IntensityEnum.LEVEL_0;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.light.bcl.ILightBCL#countAllByRequest(com.sensus.mlc.light.model.request.LightRequest)
	 */
	@Override
	public InternalResultsResponse<Integer> countAllByRequest(LightRequest request)
	{
		return getLightDAC().countAllByRequest(request);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.light.bcl.ILightBCL#updateLightBase(com.sensus.mlc.light.model.request.LightMaintenanceRequest)
	 */
	@Override
	public InternalResponse updateLightBase(LightMaintenanceRequest request)
	{
		UserContext userContext = request.getUserContext();
		InternalResponse response = new InternalResponse();

		ProcessItem processItem = null;
		if (getLightDAC().updateLight(request).isInError())
		{
			processItem = new ProcessItem(ProcessItemStatusEnum.MLCFAILURE);
			processItem.setLight(request.getLight());
		}

		processItem = new ProcessItem(ProcessItemStatusEnum.SUCCESS);
		processItem.setLight(request.getLight());

		// Insert process to light updated
		Process process = createProcess(userContext, request.getLight(), 1);
		if (isNull(process))
		{
			return response;
		}

		process.setProcessItems(Arrays.asList(processItem));
		insertProcess(userContext, response, process);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.light.bcl.ILightBCL#updateLightMass(com.sensus.mlc.light.model.request.LightMassUpdateRequest)
	 */
	@Override
	public InternalResponse updateLightMass(LightMassUpdateRequest request)
	{
		UserContext userContext = request.getUserContext();
		InternalResponse response = new InternalResponse();

		// Create new instance of LightRequest to fetch all lights
		LightRequest lightRequest = new LightRequest();
		lightRequest.setUserContext(userContext);
		lightRequest.setLightCriteria(request.getLightCriteria());
		lightRequest.setActionCriteria(request.getActionCriteria());
		lightRequest.setGroupCriteria(request.getGroupCriteria());
		lightRequest.setAlertCriteria(request.getAlertCriteria());
		lightRequest.setAddressCriteria(request.getAddressCriteria());
		lightRequest.setProcessCriteria(request.getProcessCriteria());
		lightRequest.setScheduleCriteria(request.getScheduleCriteria());
		lightRequest.setConfigurationCriteria(request.getConfigurationCriteria());
		lightRequest.setOperationalDataCriteria(request.getOperationalDataCriteria());
		lightRequest.setTagCriteria(request.getTagCriteria());
		lightRequest.setNotificationHistoryCriteria(request.getNotificationHistoryCriteria());
		lightRequest.setLightCustomSearchCriteria(request.getLightCustomSearchCriteria());
		InternalResultsResponse<Light> lightResponse = fetchAllByRequest(lightRequest);

		// Create process to updated lights
		Process process = createProcess(userContext, request.getLight(), lightResponse.getResultsList().size());

		// Verify if exist some light to update
		if (lightResponse.isInError() || !lightResponse.hasResults() || isNull(process))
		{
			return lightResponse;
		}

		// Update property to each light
		List<ProcessItem> processItems = new ArrayList<ProcessItem>();
		for (Light light : lightResponse.getResultsList())
		{
			request.getLight().setId(light.getId());
			request.getLight().setModelAction(PersistanceActionEnum.UPDATE);
			LightMaintenanceRequest maintenanceRequest = new LightMaintenanceRequest(request.getLight());
			maintenanceRequest.setUserContext(userContext);

			InternalResponse updateResponse = getLightDAC().updateLightBase(maintenanceRequest);
			if (updateResponse.isInError())
			{
				response.addMessages(updateResponse.getMessageInfoList());
				response.setStatus(updateResponse.getStatus());

				ProcessItem processItem = new ProcessItem(ProcessItemStatusEnum.MLCFAILURE);
				processItem.setLight(light);
				processItems.add(processItem);
				continue;
			}

			ProcessItem processItem = new ProcessItem(ProcessItemStatusEnum.SUCCESS);
			processItem.setLight(light);
			processItems.add(processItem);
		}

		// Insert process to lights updated
		process.setProcessItems(processItems);
		insertProcess(userContext, response, process);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.light.bcl.ILightBCL#insertLight(com.sensus.mlc.light.model.request.LightMaintenanceRequest)
	 */
	@Override
	public InternalResponse insertLight(LightMaintenanceRequest request)
	{
		return getLightDAC().insertLight(request);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.light.bcl.ILightBCL#deleteLight(com.sensus.mlc.light.model.request.LightMaintenanceRequest)
	 */
	@Override
	public InternalResponse deleteLightReferences(LightDeleteRequest request)
	{
		return getLightDAC().deleteLightReference(request);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.light.bcl.ILightBCL#updateLastOperationalData(com.sensus.mlc.light.model.request.
	 * LastOperationalDataMaintenanceRequest)
	 */
	@Override
	public InternalResponse updateLastOperationalData(LastOperationalDataMaintenanceRequest request)
	{
		return getLightDAC().updateLastOperationalData(request);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.light.bcl.ILightBCL#updateSchedule(com.sensus.mlc.light.model.request.ScheduleMaintenanceRequest)
	 */
	@Override
	public InternalResponse updateSchedule(ScheduleMaintenanceRequest request)
	{
		return getLightDAC().updateSchedule(request);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.light.bcl.ILightBCL#updateConfiguration(com.sensus.mlc.light.model.request.
	 * ConfigurationMaintenanceRequest)
	 */
	@Override
	public InternalResponse updateConfiguration(ConfigurationMaintenanceRequest request)
	{
		return getLightDAC().updateConfiguration(request);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.light.bcl.ILightBCL#resetMinMaxValue(com.sensus.mlc.light.model.request.LightRequest)
	 */
	@Override
	public InternalResultsResponse<Light> resetMinMaxValue(LightRequest request)
	{
		UserContext userContext = request.getUserContext();
		InternalResultsResponse<Light> response = fetchAllByRequest(request);

		// create a process to current action(RESET_LIGHT_MIN_MAX).
		LCAction action = new LCAction(LCActionTypeEnum.RESET_LIGHT_MIN_MAX);
		action.addActionParameter(new LCActionParameter(PropertyEnum.FLEXNET_ID));
		Process process = ProcessUtil.generateProcess(false, action, response.getResultsList().size());

		// Verify if exist some light to reset
		if (response.isInError() || !response.hasResults())
		{
			process.setIsProcessComplete(true);
			insertProcess(userContext, response, process);
			return response;
		}

		List<ProcessItem> processItems = new ArrayList<ProcessItem>();
		for (Light light : response.getResultsList())
		{
			// add fields to do reset minMax.
			LastOperationalData lastOperationalData = new LastOperationalData();
			lastOperationalData.setParentId(light.getId());
			lastOperationalData.setModifyUser(light.getModifyUser());

			LastOperationalDataMaintenanceRequest operationalRequest = new LastOperationalDataMaintenanceRequest();
			operationalRequest.setUserContext(userContext);
			operationalRequest.setLastOperationalData(lastOperationalData);

			InternalResponse minMaxResponse = getLightDAC().resetMinMaxValue(operationalRequest);
			if (minMaxResponse.isInError())
			{
				response.addMessage(RESET_MIN_MAX_FAILED,
						Message.MessageSeverity.Error,
						Message.MessageLevel.FieldValidation);

				response.setStatus(minMaxResponse.getStatus());

				ProcessItem processItem = new ProcessItem(ProcessItemStatusEnum.MLCFAILURE);
				processItem.setLight(light);
				processItems.add(processItem);
				continue;
			}

			ProcessItem processItem = new ProcessItem(ProcessItemStatusEnum.SUCCESS);
			processItem.setLight(light);
			processItems.add(processItem);
		}

		// Insert process to lights reseted
		process.setProcessItems(processItems);
		process.setIsProcessComplete(true);
		insertProcess(userContext, response, process);
		return response;
	}

	/**
	 * Insert process.
	 * 
	 * @param userContext the user context
	 * @param response the response
	 * @param process the process
	 */
	private void insertProcess(UserContext userContext, InternalResponse response, Process process)
	{
		ProcessRequest processRequest = new ProcessRequest(userContext);
		processRequest.setProcess(process);
		InternalResponse processResponse = getProcessBCL().insertProcess(processRequest);
		if (processResponse.isInError())
		{
			response.addMessages(processResponse.getMessageInfoList());
			response.setStatus(processResponse.getStatus());
		}
	}

	/**
	 * Creates the process.
	 * 
	 * @param userContext the user context
	 * @param light the light
	 * @param lightAmount the light amount
	 * @return the process
	 */
	private Process createProcess(UserContext userContext, Light light, Integer lightAmount)
	{
		// Case update protected
		if (!isNull(light.getProtect()))
		{
			LCAction action = new LCAction(LCActionTypeEnum.SET_PROTECTED);
			action.addActionParameter(new LCActionParameter(PropertyEnum.PROTECTED, String.valueOf(light.getProtect())));
			return ProcessUtil.generateProcess(true, false, action, lightAmount);
		}
		return null;
	}

	/**
	 * Removes the geocode data.
	 * 
	 * @param request the request
	 */
	private void removeGeocodeData(LightRequest request)
	{
		request.getLightCriteria().getGeoCodeCriteria().setBottomLeftLat(null);
		request.getLightCriteria().getGeoCodeCriteria().setBottomLeftLong(null);
		request.getLightCriteria().getGeoCodeCriteria().setTopRightLat(null);
		request.getLightCriteria().getGeoCodeCriteria().setTopRightLong(null);
	}

}
