package com.sensus.dm.common.device.bcf.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sensus.cbof.model.Device;
import com.sensus.cbof.model.Radio;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.util.SensusInterfaceUtil;
import com.sensus.common.validation.ValidationContext;
import com.sensus.common.validation.ValidationController;
import com.sensus.dm.common.base.model.DMPersistanceActionEnum;
import com.sensus.dm.common.base.model.ServicesPermissions;
import com.sensus.dm.common.device.bcf.IDeviceBCF;
import com.sensus.dm.common.device.bcl.IDeviceBCL;
import com.sensus.dm.common.device.model.Alarm;
import com.sensus.dm.common.device.model.AlarmsTypesCount;
import com.sensus.dm.common.device.model.DeviceModel;
import com.sensus.dm.common.device.model.DeviceTypeCount;
import com.sensus.dm.common.device.model.GeocodeDeviceInfo;
import com.sensus.dm.common.device.model.request.DeviceRequest;
import com.sensus.dm.common.device.model.request.InquiryDeviceRequest;
import com.sensus.dm.common.device.model.response.DeviceResponse;
import com.sensus.dm.common.device.model.response.InquiryGeocodeDeviceInfoResponse;
import com.sensus.dm.common.tenant.model.request.TenantRequest;
import com.sensus.dm.elec.device.model.ElectricMeter;

/**
 * The Class ElectricMeterBCFImpl.
 * 
 * @author QAT Global.
 */
public class DeviceBCFImpl implements IDeviceBCF
{
	/** The Constant DEFAULT_DEVICE_BCF_EXCEPTION_MSG. */
	private static final String DEFAULT_DEVICE_BCF_EXCEPTION_MSG = "sensus.epm.devicebcfimpl.defaultexception";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(DeviceBCFImpl.class);

	/** The device bcl. */
	private IDeviceBCL deviceBCL; // injected by Spring through setter

	/** The device validation controller. */
	private ValidationController deviceValidationController; // injected by Spring through setter

	/** The tenant request validation controller. */
	private ValidationController tenantRequestValidationController; // injected by Spring through setter

	/** The electric meter validation controller. */
	private ValidationController electricMeterValidationController; // injected by Spring through setter

	/** The device request validation controller. */
	private ValidationController deviceRequestValidationController; // injected by Spring through setter

	/** The alarm validation controller. */
	private ValidationController alarmValidationController; // injected by Spring through setter

	/** The inquiry device request validation controller. */
	private ValidationController inquiryDeviceRequestValidationController; // injected by Spring through setter

	/**
	 * Gets the device bcl.
	 * 
	 * @return the device bcl
	 */
	public IDeviceBCL getDeviceBCL()
	{
		return deviceBCL;
	}

	/**
	 * Sets the device bcl.
	 * 
	 * @param deviceBCL the new device bcl
	 */
	public void setDeviceBCL(IDeviceBCL deviceBCL)
	{
		this.deviceBCL = deviceBCL;
	}

	/**
	 * Gets the tenant request validation controller.
	 * 
	 * @return the tenant request validation controller
	 */
	private ValidationController getTenantRequestValidationController()
	{
		return tenantRequestValidationController;
	}

	/**
	 * Sets the tenant request validation controller.
	 * 
	 * @param tenantRequestValidationController the new tenant request validation controller
	 */
	public void setTenantRequestValidationController(ValidationController tenantRequestValidationController)
	{
		this.tenantRequestValidationController = tenantRequestValidationController;
	}

	/**
	 * Gets the device validation controller.
	 * 
	 * @return the device validation controller
	 */
	public ValidationController getDeviceValidationController()
	{
		return deviceValidationController;
	}

	/**
	 * Sets the device validation controller.
	 * 
	 * @param deviceValidationController the new device validation controller
	 */
	public void setDeviceValidationController(ValidationController deviceValidationController)
	{
		this.deviceValidationController = deviceValidationController;
	}

	/**
	 * Gets the electric meter validation controller.
	 * 
	 * @return the electricMeterValidationController
	 */
	public ValidationController getElectricMeterValidationController()
	{
		return electricMeterValidationController;
	}

	/**
	 * Sets the electric meter validation controller.
	 * 
	 * @param electricMeterValidationController the electricMeterValidationController to set
	 */
	public void setElectricMeterValidationController(ValidationController electricMeterValidationController)
	{
		this.electricMeterValidationController = electricMeterValidationController;
	}

	/**
	 * Gets the device request validation controller.
	 * 
	 * @return the device request validation controller
	 */
	public ValidationController getDeviceRequestValidationController()
	{
		return deviceRequestValidationController;
	}

	/**
	 * Sets the device request validation controller.
	 * 
	 * @param deviceRequestValidationController the new device request validation controller
	 */
	public void setDeviceRequestValidationController(ValidationController deviceRequestValidationController)
	{
		this.deviceRequestValidationController = deviceRequestValidationController;
	}

	/**
	 * Gets the alarm validation controller.
	 * 
	 * @return the alarm validation controller
	 */
	public ValidationController getAlarmValidationController()
	{
		return alarmValidationController;
	}

	/**
	 * Sets the alarm validation controller.
	 * 
	 * @param alarmValidationController the new alarm validation controller
	 */
	public void setAlarmValidationController(ValidationController alarmValidationController)
	{
		this.alarmValidationController = alarmValidationController;
	}

	/**
	 * Gets the inquiry device request validation controller.
	 * 
	 * @return the inquiry device request validation controller
	 */
	public ValidationController getInquiryDeviceRequestValidationController()
	{
		return inquiryDeviceRequestValidationController;
	}

	/**
	 * Sets the inquiry device request validation controller.
	 * 
	 * @param inquiryDeviceRequestValidationController the new inquiry device request validation controller
	 */
	public void setInquiryDeviceRequestValidationController(
			ValidationController inquiryDeviceRequestValidationController)
	{
		this.inquiryDeviceRequestValidationController = inquiryDeviceRequestValidationController;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.device.bcf.IDeviceBCF#fetchAllByPremiseId(com.sensus.dm.common.device.model.request.
	 * DeviceRequest)
	 */
	@Override
	public DeviceResponse fetchAllByPremiseId(DeviceRequest deviceRequest)
	{
		DeviceResponse response = new DeviceResponse();
		InternalResultsResponse<Device> internalResponse = null;

		try
		{
			// Validate
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(DMPersistanceActionEnum.getDefaultName(),
					DMPersistanceActionEnum.FETCH_ALL_BY_PREMISE_ID);

			context.putObjectToBeValidated(ElectricMeter.class.getSimpleName(), deviceRequest.getFirstDevice());

			if (getElectricMeterValidationController().validate(context))
			{
				internalResponse = getDeviceBCL().fetchAllByPremiseId(deviceRequest);

				response.setDevices(internalResponse.getResultsList());
			}

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(),
					false);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_DEVICE_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.device.bcf.IDeviceBCF#fetchAllDevicesTypeCount(com.sensus.dm.common.device.model.request
	 * .DeviceRequest)
	 */
	@Override
	public DeviceResponse fetchAllDevicesTypeCount(DeviceRequest deviceRequest)
	{
		DeviceResponse response = new DeviceResponse();

		try
		{
			InternalResultsResponse<DeviceTypeCount> internalResponse = null;

			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(DMPersistanceActionEnum.getDefaultName(),
					DMPersistanceActionEnum.FETCH_ALL_DEVICE_TYPE_COUNT);
			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(), deviceRequest);

			if (getTenantRequestValidationController().validate(context))
			{
				internalResponse =
						getDeviceBCL().fetchAllDevicesTypeCount(deviceRequest);
				response.setDeviceTypeCountList(internalResponse.getResultsList());

			}
			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse,
					context.getMessages(), false);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_DEVICE_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.device.bcf.IDeviceBCF#fetchServicesByDeviceType(com.sensus.dm.common.device.model.request
	 * .DeviceRequest)
	 */
	@Override
	public DeviceResponse fetchServicesByDeviceType(DeviceRequest deviceRequest)
	{
		DeviceResponse response = new DeviceResponse();
		try
		{
			InternalResultsResponse<ServicesPermissions> internalResponse = null;

			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(DMPersistanceActionEnum.getDefaultName(),
					DMPersistanceActionEnum.FETCH_SERVICES_BY_DEVICE_TYPE);
			context.putObjectToBeValidated(DeviceRequest.class.getSimpleName(), deviceRequest);

			if (getDeviceRequestValidationController().validate(context))
			{
				internalResponse = getDeviceBCL().fetchServicesByDeviceType(deviceRequest);
				response.setServicesPermissions(internalResponse.getFirstResult());
			}

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(),
					false);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_DEVICE_BCF_EXCEPTION_MSG);
		}

		return response;

	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.device.bcf.IDeviceBCF#fetchDeviceByIdImport(com.sensus.dm.common.device.model.request.
	 * DeviceRequest)
	 */
	@Override
	public DeviceResponse fetchDeviceByIdImport(DeviceRequest deviceRequest)
	{
		DeviceResponse response = new DeviceResponse();
		InternalResultsResponse<Device> internalResponse = null;
		try
		{
			// Validate
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(DMPersistanceActionEnum.getDefaultName(),
					DMPersistanceActionEnum.FETCH_DEVICE_BY_ID_IMPORT);

			context.putObjectToBeValidated(Device.class.getSimpleName(), deviceRequest.getFirstDevice());
			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(), deviceRequest);

			if (getDeviceValidationController().validate(context)
					&& getTenantRequestValidationController().validate(context))
			{
				internalResponse = getDeviceBCL().fetchDeviceByIdImport(deviceRequest);
				response.setDevices(internalResponse.getResultsList());
			}

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(),
					false);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_DEVICE_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.device.bcf.IDeviceBCF#fetchAlarmHistory(com.sensus.dm.common.device.model.request.DeviceRequest
	 * )
	 */
	@Override
	public DeviceResponse fetchAlarmHistory(DeviceRequest deviceRequest)
	{
		DeviceResponse response = new DeviceResponse();
		try
		{
			InternalResultsResponse<Alarm> internalResponse = null;

			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(DMPersistanceActionEnum.getDefaultName(),
					DMPersistanceActionEnum.FETCH_ALARM_HISTORY);
			context.putObjectToBeValidated(Radio.class.getSimpleName(), deviceRequest.getFirstDevice().getRadio());
			context.putObjectToBeValidated(Device.class.getSimpleName(), deviceRequest.getFirstDevice());

			if (getAlarmValidationController().validate(context))
			{
				internalResponse = getDeviceBCL().fetchAlarmHistory(deviceRequest);
				response.setAlarms(internalResponse.getResultsList());
			}

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(),
					false);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_DEVICE_BCF_EXCEPTION_MSG);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.device.bcf.IDeviceBCF#fetchDevicesBoundsToMap(com.sensus.dm.common.device.model.request.
	 * InquiryDeviceRequest)
	 */
	@Override
	public InquiryGeocodeDeviceInfoResponse fetchDevicesBoundsToMap(InquiryDeviceRequest request)
	{
		InquiryGeocodeDeviceInfoResponse response = new InquiryGeocodeDeviceInfoResponse();
		try
		{
			InternalResultsResponse<GeocodeDeviceInfo> internalResponse = null;

			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(DMPersistanceActionEnum.getDefaultName(),
					DMPersistanceActionEnum.FETCH_DEVICES_BOUNDS_TO_MAP);
			context.putObjectToBeValidated(InquiryDeviceRequest.class.getSimpleName(), request);

			if (getInquiryDeviceRequestValidationController().validate(context))
			{
				internalResponse = getDeviceBCL().fetchDevicesBoundsToMap(request);
			}

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(),
					true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_DEVICE_BCF_EXCEPTION_MSG);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.device.bcf.IDeviceBCF#fetchDevicesToMap(com.sensus.dm.common.device.model.request.
	 * InquiryDeviceRequest)
	 */
	@Override
	public InquiryGeocodeDeviceInfoResponse fetchDevicesToMap(InquiryDeviceRequest request)
	{
		InquiryGeocodeDeviceInfoResponse response = new InquiryGeocodeDeviceInfoResponse();
		try
		{
			InternalResultsResponse<GeocodeDeviceInfo> internalResponse = null;

			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(DMPersistanceActionEnum.getDefaultName(),
					DMPersistanceActionEnum.FETCH_DEVICES_TO_MAP);
			context.putObjectToBeValidated(InquiryDeviceRequest.class.getSimpleName(), request);

			if (getInquiryDeviceRequestValidationController().validate(context))
			{
				internalResponse = getDeviceBCL().fetchDevicesToMap(request);
			}

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(),
					true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_DEVICE_BCF_EXCEPTION_MSG);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.device.bcf.IDeviceBCF#fetchAllDeviceTypeDescriptions(com.sensus.dm.common.device.model.request
	 * .DeviceRequest)
	 */
	@Override
	public DeviceResponse fetchAllDeviceTypeDescriptions(DeviceRequest deviceRequest)
	{
		DeviceResponse response = new DeviceResponse();
		try
		{
			InternalResultsResponse<DeviceModel> internalResponse =
					getDeviceBCL().fetchAllDeviceTypeDescriptions(deviceRequest);
			response.setDeviceModels(internalResponse.getResultsList());

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, null);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_DEVICE_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.device.bcf.IDeviceBCF#fetchQuarantineCount(com.sensus.dm.common.device.model.request.
	 * DeviceRequest)
	 */
	@Override
	public DeviceResponse fetchQuarantineCount(DeviceRequest deviceRequest)
	{
		DeviceResponse response = new DeviceResponse();
		try
		{
			InternalResultsResponse<Integer> internalResponse = null;

			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(DMPersistanceActionEnum.getDefaultName(),
					DMPersistanceActionEnum.FETCH_QUARANTINE_COUNT);
			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(), deviceRequest);

			if (getTenantRequestValidationController().validate(context))
			{
				internalResponse = getDeviceBCL().fetchQuarantineCount(deviceRequest);
				response.setQuarantineAmount(internalResponse.getFirstResult());
			}

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(),
					false);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_DEVICE_BCF_EXCEPTION_MSG);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.device.bcf.IDeviceBCF#fetchAlarmsTypesCount(com.sensus.dm.common.device.model.request.
	 * DeviceRequest)
	 */
	@Override
	public DeviceResponse fetchAlarmsTypesCount(DeviceRequest deviceRequest)
	{
		DeviceResponse response = new DeviceResponse();
		try
		{
			InternalResultsResponse<AlarmsTypesCount> internalResponse =
					getDeviceBCL().fetchAlarmsTypesCount(deviceRequest);

			response.setAlarmsTypesCount(internalResponse.getResultsList());

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, null);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_DEVICE_BCF_EXCEPTION_MSG);
		}
		return response;
	}
}