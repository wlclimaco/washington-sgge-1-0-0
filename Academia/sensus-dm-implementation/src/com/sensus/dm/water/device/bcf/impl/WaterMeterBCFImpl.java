package com.sensus.dm.water.device.bcf.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sensus.cbof.model.Device;
import com.sensus.cbof.model.Location;
import com.sensus.common.model.SortExpression;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.util.SensusInterfaceUtil;
import com.sensus.common.validation.ValidationContext;
import com.sensus.common.validation.ValidationController;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.dm.common.base.model.DMPersistanceActionEnum;
import com.sensus.dm.common.base.model.request.DeviceManagerInquiryRequest;
import com.sensus.dm.common.device.model.request.DeviceRequest;
import com.sensus.dm.common.device.model.request.InquiryDeviceRequest;
import com.sensus.dm.common.device.model.response.DeviceResponse;
import com.sensus.dm.common.device.model.response.InquiryDeviceResponse;
import com.sensus.dm.common.tenant.model.request.TenantRequest;
import com.sensus.dm.water.device.bcf.IWaterMeterBCF;
import com.sensus.dm.water.device.bcl.IWaterMeterBCL;
import com.sensus.dm.water.device.model.WaterGasMeterConfiguration;
import com.sensus.dm.water.device.model.WaterGasMeterStatusCount;
import com.sensus.dm.water.device.model.WaterLeak;
import com.sensus.dm.water.device.model.WaterMeter;
import com.sensus.dm.water.device.model.response.WaterMeterResponse;

/**
 * The Class WaterMeterBCFImpl.
 * 
 * @author QAT Global.
 */
public class WaterMeterBCFImpl implements IWaterMeterBCF
{

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(WaterMeterBCFImpl.class);

	/** The Constant DEFAULT_WATERMETER_BCF_EXCEPTION_MSG. */
	private static final String DEFAULT_WATERMETER_BCF_EXCEPTION_MSG = "sensus.epm.watermeterbcfimpl.defaultexception";

	/** The water meter bcl. */
	private IWaterMeterBCL waterMeterBCL; // injected by Spring through setter

	/** The device validation controller. */
	private ValidationController deviceValidationController; // injected by Spring through setter

	/** The inquiry request validation controller. */
	private ValidationController inquiryRequestValidationController; // injected by Spring through setter

	/** The tenant request validation controller. */
	private ValidationController tenantRequestValidationController; // injected by Spring through setter

	/** The water gas meter configuration validation controller. */
	private ValidationController waterGasMeterConfigurationValidationController; // injected by Spring through setter

	/** The location validation controller. */
	private ValidationController locationValidationController; // injected by Spring through setter

	/**
	 * Gets the water meter bcl.
	 * 
	 * @return the water meter bcl
	 */
	public IWaterMeterBCL getWaterMeterBCL()
	{
		return waterMeterBCL;
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
	 * Sets the water meter bcl.
	 * 
	 * @param waterMeterBCL the new water meter bcl
	 */
	public void setWaterMeterBCL(IWaterMeterBCL waterMeterBCL)
	{
		this.waterMeterBCL = waterMeterBCL;
	}

	/**
	 * Gets the inquiry request validation controller.
	 * 
	 * @return the inquiry request validation controller
	 */
	private ValidationController getInquiryRequestValidationController()
	{
		return inquiryRequestValidationController;
	}

	/**
	 * Sets the inquiry request validation controller.
	 * 
	 * @param inquiryRequestValidationController the new inquiry request validation controller
	 */
	public void setInquiryRequestValidationController(ValidationController inquiryRequestValidationController)
	{
		this.inquiryRequestValidationController = inquiryRequestValidationController;
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
	 * Gets the water gas meter configuration validation controller.
	 * 
	 * @return the water gas meter configuration validation controller
	 */
	public ValidationController getWaterGasMeterConfigurationValidationController()
	{
		return waterGasMeterConfigurationValidationController;
	}

	/**
	 * Sets the water gas meter configuration validation controller.
	 * 
	 * @param waterGasMeterConfigurationValidationController the new water gas meter configuration validation controller
	 */
	public void setWaterGasMeterConfigurationValidationController(
			ValidationController waterGasMeterConfigurationValidationController)
	{
		this.waterGasMeterConfigurationValidationController = waterGasMeterConfigurationValidationController;
	}

	/**
	 * Gets the location validation controller.
	 * 
	 * @return the locationValidationController
	 */
	private ValidationController getLocationValidationController()
	{
		return locationValidationController;
	}

	/**
	 * Sets the location validation controller.
	 * 
	 * @param locationValidationController the locationValidationController to set
	 */
	public void setLocationValidationController(ValidationController locationValidationController)
	{
		this.locationValidationController = locationValidationController;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.water.water.bcf.IWaterMeterBCF#fetchAllWaterMeters(com.sensus.dm.water.water.model.request.
	 * InquiryDeviceRequest)
	 */
	@Override
	public InquiryDeviceResponse fetchAllWaterMeters(InquiryDeviceRequest inquiryDeviceRequest)
	{
		InquiryDeviceResponse response = new InquiryDeviceResponse();
		InternalResultsResponse<WaterMeter> internalResponse = null;

		try
		{
			// Validate
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(DMPersistanceActionEnum.getDefaultName(),
					DMPersistanceActionEnum.FETCH_ALL);
			context.putObjectToBeValidated(SortExpression.class.getSimpleName(),
					inquiryDeviceRequest.getSortExpressions());
			context.putObjectToBeValidated(DeviceManagerInquiryRequest.class.getSimpleName(), inquiryDeviceRequest);
			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(), inquiryDeviceRequest);

			if (getInquiryRequestValidationController().validate(context)
					&& getTenantRequestValidationController().validate(context))
			{
				if (!validateWaterMeter(inquiryDeviceRequest, context))
				{
					SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse,
							context.getMessages(),
							true);

					return response;
				}

				internalResponse = getWaterMeterBCL().fetchAllWaterMeters(inquiryDeviceRequest);
			}

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(),
					true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_WATERMETER_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.water.water.bcf.IWaterMeterBCF#fetchWaterMeterById(com.sensus.dm.water.water.model.request.
	 * WaterMeterRequest)
	 */
	@Override
	public DeviceResponse fetchWaterMeterById(DeviceRequest deviceRequest)
	{
		DeviceResponse response = new DeviceResponse();
		InternalResultsResponse<Device> internalResponse = null;
		try
		{
			// Validate
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(DMPersistanceActionEnum.getDefaultName(),
					DMPersistanceActionEnum.FETCH_DEVICE_BY_ID);

			context.putObjectToBeValidated(Device.class.getSimpleName(), deviceRequest.getFirstDevice());

			if (getDeviceValidationController().validate(context))
			{
				internalResponse = getWaterMeterBCL().fetchWaterMeterById(deviceRequest);
				response.setDevices(internalResponse.getResultsList());
			}

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(),
					false);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_WATERMETER_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.water.device.bcf.IWaterMeterBCF#generateDevicesCSV(com.sensus.dm.water.device.model.request.
	 * InquiryDeviceRequest)
	 */
	@Override
	public InquiryDeviceResponse generateDevicesCSV(InquiryDeviceRequest inquiryDeviceRequest)
	{
		InquiryDeviceResponse response = new InquiryDeviceResponse();
		InternalResponse internalResponse = null;

		try
		{
			// Validate
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(DMPersistanceActionEnum.getDefaultName(),
					DMPersistanceActionEnum.GENERATE_FILE_CSV);
			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(), inquiryDeviceRequest);
			context.putObjectToBeValidated(Device.class.getSimpleName(), inquiryDeviceRequest.getDeviceSearch()
					.getWaterMeterSearch().getWaterMeter());
			context.putObjectToBeValidated(SortExpression.class.getSimpleName(),
					inquiryDeviceRequest.getSortExpressions());
			context.putObjectToBeValidated(DeviceManagerInquiryRequest.class.getSimpleName(),
					inquiryDeviceRequest);

			if (getDeviceValidationController().validate(context)
					&& getInquiryRequestValidationController().validate(context)
					&& getTenantRequestValidationController().validate(context))
			{
				internalResponse = getWaterMeterBCL().generateDevicesCSV(inquiryDeviceRequest);
			}

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(),
					true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_WATERMETER_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.water.device.bcf.IWaterMeterBCF#fetchCommunication(com.sensus.dm.common.device.model.request.
	 * DeviceRequest)
	 */
	@Override
	public DeviceResponse fetchCommunication(DeviceRequest deviceRequest)
	{
		DeviceResponse response = new DeviceResponse();
		InternalResultsResponse<WaterGasMeterStatusCount> internalResultsResponse = null;
		try
		{
			// Validate
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(DMPersistanceActionEnum.getDefaultName(),
					DMPersistanceActionEnum.FETCH_DASHBOARD_WATER_HEADER);

			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(), deviceRequest);

			if (getTenantRequestValidationController().validate(context))
			{
				internalResultsResponse = getWaterMeterBCL().fetchCommunication(deviceRequest);
				response.setWaterGasMeterStatusCount(internalResultsResponse.getResultsList());
			}

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResultsResponse,
					context.getMessages(),
					false);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_WATERMETER_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.water.device.bcf.IWaterMeterBCF#fetchLeakReport(com.sensus.dm.water.device.model.request.
	 * DeviceRequest)
	 */
	@Override
	public WaterMeterResponse fetchLeakReport(DeviceRequest request)
	{
		WaterMeterResponse response = new WaterMeterResponse();
		InternalResultsResponse<WaterLeak> internalResultsResponse = null;
		try
		{
			// Validate
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(DMPersistanceActionEnum.getDefaultName(),
					DMPersistanceActionEnum.FETCH_WATER_LEAK_REPORT);

			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(), request);

			if (getTenantRequestValidationController().validate(context))
			{
				internalResultsResponse = getWaterMeterBCL().fetchLeakReport(request);
				response.setLeakList(internalResultsResponse.getResultsList());
			}

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResultsResponse,
					context.getMessages(),
					false);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_WATERMETER_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.water.device.bcf.IWaterMeterBCF#generateFileCSVLeakReport(com.sensus.dm.water.device.model.request
	 * .DeviceRequest)
	 */
	@Override
	public WaterMeterResponse generateFileCSVLeakReport(DeviceRequest request)
	{
		WaterMeterResponse response = new WaterMeterResponse();
		InternalResponse internalResponse = null;

		try
		{
			// Validate
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(DMPersistanceActionEnum.getDefaultName(),
					DMPersistanceActionEnum.GENERATE_WATER_LEAK_REPORT_CSV);

			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(), request);

			if (getTenantRequestValidationController().validate(context))
			{
				internalResponse = getWaterMeterBCL().generateFileCSVLeakReport(request);
			}

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(),
					true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_WATERMETER_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	/**
	 * Validate water meter.
	 * 
	 * @param inquiryDeviceRequest the inquiry water meter request
	 * @param context the context
	 * @return true, if successful
	 */
	private boolean validateWaterMeter(InquiryDeviceRequest inquiryDeviceRequest, ValidationContext context)
	{
		if (!ValidationUtil.isNull(inquiryDeviceRequest.getDeviceSearch())
				&& !ValidationUtil.isNull(inquiryDeviceRequest.getDeviceSearch().getWaterMeterSearch())
				&& !ValidationUtil.isNull(inquiryDeviceRequest.getDeviceSearch().getWaterMeterSearch().getWaterMeter()))
		{
			context.putObjectToBeValidated(Device.class.getSimpleName(), inquiryDeviceRequest.getDeviceSearch()
					.getWaterMeterSearch().getWaterMeter());
			if (!getDeviceValidationController().validate(context))
			{
				return false;
			}

			WaterMeter waterMeter = inquiryDeviceRequest.getDeviceSearch().getWaterMeterSearch().getWaterMeter();

			if (!ValidationUtil.isNull(waterMeter.getRadio())
					&& !ValidationUtil.isNull(waterMeter.getRadio().getLocation()))
			{
				context.putObjectToBeValidated(Location.class.getSimpleName(), waterMeter.getRadio().getLocation());
				if (!getLocationValidationController().validate(context))
				{
					return false;
				}
			}

			if (!ValidationUtil.isNull(waterMeter.getConfiguration()))
			{
				context.putObjectToBeValidated(WaterGasMeterConfiguration.class.getSimpleName(),
						waterMeter.getConfiguration());
				// Only converts the firmware
				getWaterGasMeterConfigurationValidationController().validate(context);
			}
		}
		return true;
	}
}
