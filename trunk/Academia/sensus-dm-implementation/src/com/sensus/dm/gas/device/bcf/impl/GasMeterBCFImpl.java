package com.sensus.dm.gas.device.bcf.impl;

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
import com.sensus.dm.gas.device.bcf.IGasMeterBCF;
import com.sensus.dm.gas.device.bcl.IGasMeterBCL;
import com.sensus.dm.gas.device.model.GasMeter;
import com.sensus.dm.water.device.model.WaterGasMeterConfiguration;

/**
 * The Class GasMeterBCFImpl.
 * 
 * @author QAT Global.
 */
public class GasMeterBCFImpl implements IGasMeterBCF
{

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(GasMeterBCFImpl.class);

	/** The Constant DEFAULT_GASMETER_BCF_EXCEPTION_MSG. */
	private static final String DEFAULT_GASMETER_BCF_EXCEPTION_MSG = "sensus.epm.gasmeterbcfimpl.defaultexception";

	/** The gas meter bcl. */
	private IGasMeterBCL gasMeterBCL; // injected by Spring through setter

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
	 * Gets the gas meter bcl.
	 * 
	 * @return the gas meter bcl
	 */
	public IGasMeterBCL getGasMeterBCL()
	{
		return gasMeterBCL;
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
	 * Sets the gas meter bcl.
	 * 
	 * @param gasMeterBCL the new gas meter bcl
	 */
	public void setGasMeterBCL(IGasMeterBCL gasMeterBCL)
	{
		this.gasMeterBCL = gasMeterBCL;
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
	 * @return the location validation controller
	 */
	private ValidationController getLocationValidationController()
	{
		return locationValidationController;
	}

	/**
	 * Sets the location validation controller.
	 * 
	 * @param locationValidationController the new location validation controller
	 */
	public void setLocationValidationController(ValidationController locationValidationController)
	{
		this.locationValidationController = locationValidationController;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.gas.device.bcf.IGasMeterBCF#fetchAllGasMeters(com.sensus.dm.common.device.model.request.
	 * InquiryDeviceRequest)
	 */
	@Override
	public InquiryDeviceResponse fetchAllGasMeters(InquiryDeviceRequest inquiryDeviceRequest)
	{
		InquiryDeviceResponse response = new InquiryDeviceResponse();
		InternalResultsResponse<GasMeter> internalResponse = null;

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
				if (!validateGasMeter(inquiryDeviceRequest, context))
				{
					SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse,
							context.getMessages(),
							true);

					return response;
				}

				internalResponse = getGasMeterBCL().fetchAllGasMeters(inquiryDeviceRequest);
			}

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(),
					true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_GASMETER_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.gas.device.bcf.IGasMeterBCF#fetchGasMeterById(com.sensus.dm.common.device.model.request.DeviceRequest
	 * )
	 */
	@Override
	public DeviceResponse fetchGasMeterById(DeviceRequest deviceRequest)
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
				internalResponse = getGasMeterBCL().fetchGasMeterById(deviceRequest);
				response.setDevices(internalResponse.getResultsList());
			}

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(),
					false);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_GASMETER_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.gas.device.bcf.IGasMeterBCF#generateDevicesCSV(com.sensus.dm.common.device.model.request.
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
					.getGasMeterSearch().getGasMeter());
			context.putObjectToBeValidated(SortExpression.class.getSimpleName(),
					inquiryDeviceRequest.getSortExpressions());
			context.putObjectToBeValidated(DeviceManagerInquiryRequest.class.getSimpleName(),
					inquiryDeviceRequest);

			if (getDeviceValidationController().validate(context)
					&& getInquiryRequestValidationController().validate(context)
					&& getTenantRequestValidationController().validate(context))
			{
				internalResponse = getGasMeterBCL().generateDevicesCSV(inquiryDeviceRequest);
			}

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(),
					true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_GASMETER_BCF_EXCEPTION_MSG);
		}
		return response;
	}

	/**
	 * Validate gas meter.
	 * 
	 * @param inquiryDeviceRequest the inquiry device request
	 * @param context the context
	 * @return true, if successful
	 */
	private boolean validateGasMeter(InquiryDeviceRequest inquiryDeviceRequest, ValidationContext context)
	{
		if (!ValidationUtil.isNull(inquiryDeviceRequest.getDeviceSearch())
				&& !ValidationUtil.isNull(inquiryDeviceRequest.getDeviceSearch().getGasMeterSearch())
				&& !ValidationUtil.isNull(inquiryDeviceRequest.getDeviceSearch().getGasMeterSearch().getGasMeter()))
		{
			context.putObjectToBeValidated(Device.class.getSimpleName(), inquiryDeviceRequest.getDeviceSearch()
					.getGasMeterSearch().getGasMeter());
			if (!getDeviceValidationController().validate(context))
			{
				return false;
			}

			GasMeter gasMeter = inquiryDeviceRequest.getDeviceSearch().getGasMeterSearch().getGasMeter();

			if (!ValidationUtil.isNull(gasMeter.getRadio())
					&& !ValidationUtil.isNull(gasMeter.getRadio().getLocation()))
			{
				context.putObjectToBeValidated(Location.class.getSimpleName(), gasMeter.getRadio().getLocation());
				if (!getLocationValidationController().validate(context))
				{
					return false;
				}
			}

			if (!ValidationUtil.isNull(gasMeter.getConfiguration()))
			{
				context.putObjectToBeValidated(WaterGasMeterConfiguration.class.getSimpleName(),
						gasMeter.getConfiguration());
				// Only converts the firmware
				getWaterGasMeterConfigurationValidationController().validate(context);
			}
		}
		return true;
	}
}
