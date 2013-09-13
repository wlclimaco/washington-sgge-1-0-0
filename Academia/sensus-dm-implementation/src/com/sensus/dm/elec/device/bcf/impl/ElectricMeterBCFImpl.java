package com.sensus.dm.elec.device.bcf.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sensus.cbof.model.Device;
import com.sensus.cbof.model.Location;
import com.sensus.cbof.model.Radio;
import com.sensus.common.model.SortExpression;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.util.SensusInterfaceUtil;
import com.sensus.common.validation.ValidationContext;
import com.sensus.common.validation.ValidationController;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.dm.common.base.model.DMPersistanceActionEnum;
import com.sensus.dm.common.base.model.ObjectToBeValidatedKeyEnum;
import com.sensus.dm.common.base.model.request.DeviceManagerInquiryRequest;
import com.sensus.dm.common.device.model.DeviceModel;
import com.sensus.dm.common.device.model.DeviceSearch;
import com.sensus.dm.common.device.model.request.DeviceRequest;
import com.sensus.dm.common.device.model.request.InquiryDeviceRequest;
import com.sensus.dm.common.device.model.response.DeviceResponse;
import com.sensus.dm.common.device.model.response.InquiryDeviceResponse;
import com.sensus.dm.common.tenant.model.request.TenantRequest;
import com.sensus.dm.elec.device.bcf.IElectricMeterBCF;
import com.sensus.dm.elec.device.bcl.IElectricMeterBCL;
import com.sensus.dm.elec.device.model.ConnectionState;
import com.sensus.dm.elec.device.model.ElectricMeter;
import com.sensus.dm.elec.device.model.ElectricMeterConfiguration;
import com.sensus.dm.elec.device.model.HanDevice;
import com.sensus.dm.elec.device.model.LCM;
import com.sensus.dm.elec.device.model.LCMConfiguration;
import com.sensus.dm.elec.device.model.LCMRelay;
import com.sensus.dm.elec.device.model.LifecycleState;
import com.sensus.dm.elec.device.model.PeakDemand;
import com.sensus.dm.elec.device.model.request.DeviceReadingRequest;
import com.sensus.dm.elec.device.model.response.InquiryPeakDemandResponse;

/**
 * The Class ElectricMeterBCFImpl.
 * 
 * @author QAT Global.
 */
public class ElectricMeterBCFImpl implements IElectricMeterBCF
{
	/** The Constant DEFAULT_DEVICE_BCF_EXCEPTION_MSG. */
	private static final String DEFAULT_DEVICE_BCF_EXCEPTION_MSG = "sensus.epm.devicebcfimpl.defaultexception";

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(ElectricMeterBCFImpl.class);

	/** The electric meter bcl. */
	private IElectricMeterBCL electricMeterBCL; // injected by Spring through setter

	/** The device validation controller. */
	private ValidationController deviceValidationController; // injected by Spring through setter

	/** The inquiry request validation controller. */
	private ValidationController inquiryRequestValidationController; // injected by Spring through setter

	/** The han device validation controller. */
	private ValidationController hanDeviceValidationController; // injected by Spring through setter

	/** The lcm validation controller. */
	private ValidationController lcmValidationController; // injected by Spring through setter

	/** The device search validation controller. */
	private ValidationController deviceSearchValidationController; // injected by Spring through setter

	/** The tenant request validation controller. */
	private ValidationController tenantRequestValidationController; // injected by Spring through setter

	/** The device model validation controller. */
	private ValidationController deviceModelValidationController; // injected by Spring through setter

	/** The radio validation controller. */
	private ValidationController radioValidationController; // injected by Spring through setter

	/** The location validation controller. */
	private ValidationController locationValidationController; // injected by Spring through setter

	/** The electric meter validation controller. */
	private ValidationController electricMeterValidationController; // injected by Spring through setter

	/** The electric meter configuration validation controller. */
	private ValidationController electricMeterConfigurationValidationController; // injected by Spring through setter

	/** The l cm configuration validation controller. */
	private ValidationController lCMConfigurationValidationController; // injected by Spring through setter

	/** The range date validation controller. */
	private ValidationController rangeDateValidationController; // injected by Spring through setter

	/** The inquiry device request validation controller. */
	private ValidationController inquiryDeviceRequestValidationController; // injected by Spring through setter

	/** The order by validation controller. */
	private ValidationController orderByValidationController; // injected by Spring through setter

	/**
	 * Gets the radio validation controller.
	 * 
	 * @return the radioValidationController
	 */
	public ValidationController getRadioValidationController()
	{
		return radioValidationController;
	}

	/**
	 * Sets the radio validation controller.
	 * 
	 * @param radioValidationController the radioValidationController to set
	 */
	public void setRadioValidationController(ValidationController radioValidationController)
	{
		this.radioValidationController = radioValidationController;
	}

	/**
	 * Gets the electric meter bcl.
	 * 
	 * @return the electric meter bcl
	 */
	public IElectricMeterBCL getElectricMeterBCL()
	{
		return electricMeterBCL;
	}

	/**
	 * Sets the electric meter bcl.
	 * 
	 * @param electricMeterBCL the new electric meter bcl
	 */
	public void setElectricMeterBCL(IElectricMeterBCL electricMeterBCL)
	{
		this.electricMeterBCL = electricMeterBCL;
	}

	/**
	 * Gets the device validation controller.
	 * 
	 * @return the device validation controller
	 */
	private ValidationController getDeviceValidationController()
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
	 * Gets the han device validation controller.
	 * 
	 * @return the han device validation controller
	 */
	private ValidationController getHanDeviceValidationController()
	{
		return hanDeviceValidationController;
	}

	/**
	 * Sets the han device validation controller.
	 * 
	 * @param hanDeviceValidationController the new han device validation controller
	 */
	public void setHanDeviceValidationController(ValidationController hanDeviceValidationController)
	{
		this.hanDeviceValidationController = hanDeviceValidationController;
	}

	/**
	 * Gets the lcm validation controller.
	 * 
	 * @return the lcm validation controller
	 */
	public ValidationController getLcmValidationController()
	{
		return lcmValidationController;
	}

	/**
	 * Sets the lcm validation controller.
	 * 
	 * @param lcmValidationController the new lcm validation controller
	 */
	public void setLcmValidationController(ValidationController lcmValidationController)
	{
		this.lcmValidationController = lcmValidationController;
	}

	/**
	 * Gets the device search validation controller.
	 * 
	 * @return the device search validation controller
	 */
	private ValidationController getDeviceSearchValidationController()
	{
		return deviceSearchValidationController;
	}

	/**
	 * Sets the device search validation controller.
	 * 
	 * @param deviceSearchValidationController the new device search validation controller
	 */
	public void setDeviceSearchValidationController(ValidationController deviceSearchValidationController)
	{
		this.deviceSearchValidationController = deviceSearchValidationController;
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
	 * Sets the device model validation controller.
	 * 
	 * @param deviceModelValidationController the deviceModelValidationController to set
	 */
	public void setDeviceModelValidationController(ValidationController deviceModelValidationController)
	{
		this.deviceModelValidationController = deviceModelValidationController;
	}

	/**
	 * Gets the device model validation controller.
	 * 
	 * @return the device model validation controller
	 */
	private ValidationController getDeviceModelValidationController()
	{
		return deviceModelValidationController;
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
	 * Gets the electric meter configuration validation controller.
	 * 
	 * @return the electric meter configuration validation controller
	 */
	public ValidationController getElectricMeterConfigurationValidationController()
	{
		return electricMeterConfigurationValidationController;
	}

	/**
	 * Sets the electric meter configuration validation controller.
	 * 
	 * @param electricMeterConfigurationValidationController the new electric meter configuration validation controller
	 */
	public void setElectricMeterConfigurationValidationController(
			ValidationController electricMeterConfigurationValidationController)
	{
		this.electricMeterConfigurationValidationController = electricMeterConfigurationValidationController;
	}

	/**
	 * Gets the l cm configuration validation controller.
	 * 
	 * @return the l cm configuration validation controller
	 */
	public ValidationController getlCMConfigurationValidationController()
	{
		return lCMConfigurationValidationController;
	}

	/**
	 * Sets the l cm configuration validation controller.
	 * 
	 * @param lCMConfigurationValidationController the new l cm configuration validation controller
	 */
	public void setlCMConfigurationValidationController(ValidationController lCMConfigurationValidationController)
	{
		this.lCMConfigurationValidationController = lCMConfigurationValidationController;
	}

	/**
	 * Gets the range date validation controller.
	 * 
	 * @return the range date validation controller
	 */
	public ValidationController getRangeDateValidationController()
	{
		return rangeDateValidationController;
	}

	/**
	 * Sets the range date validation controller.
	 * 
	 * @param rangeDateValidationController the new range date validation controller
	 */
	public void setRangeDateValidationController(ValidationController rangeDateValidationController)
	{
		this.rangeDateValidationController = rangeDateValidationController;
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

	/**
	 * Gets the order by validation controller.
	 * 
	 * @return the order by validation controller
	 */
	public ValidationController getOrderByValidationController()
	{
		return orderByValidationController;
	}

	/**
	 * Sets the order by validation controller.
	 * 
	 * @param orderByValidationController the new order by validation controller
	 */
	public void setOrderByValidationController(ValidationController orderByValidationController)
	{
		this.orderByValidationController = orderByValidationController;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.device.bcf.IElectricMeterBCF#fetchAllElectricMeters(com.sensus.dm.common.device.model.request
	 * .InquiryDeviceRequest)
	 */
	@Override
	public InquiryDeviceResponse fetchAllElectricMeters(InquiryDeviceRequest inquiryDeviceRequest)
	{
		InquiryDeviceResponse response = new InquiryDeviceResponse();
		InternalResultsResponse<ElectricMeter> internalResponse = null;

		try
		{
			// Validate
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(DMPersistanceActionEnum.getDefaultName(),
					DMPersistanceActionEnum.FETCH_ALL);

			context.putObjectToBeValidated(SortExpression.class.getSimpleName(),
					inquiryDeviceRequest.getSortExpressions());
			context.putObjectToBeValidated(DeviceManagerInquiryRequest.class.getSimpleName(),
					inquiryDeviceRequest);
			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(), inquiryDeviceRequest);

			if (getInquiryRequestValidationController().validate(context)
					&& getTenantRequestValidationController().validate(context))
			{
				if (!validateElectricMeter(inquiryDeviceRequest, context))
				{
					SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse,
							context.getMessages(),
							true);

					return response;
				}

				internalResponse = getElectricMeterBCL().fetchAllElectricMeter(inquiryDeviceRequest);
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
	 * com.sensus.dm.elec.device.bcf.IElectricMeterBCF#fetchAllHanDevices(com.sensus.dm.common.device.model.request.
	 * InquiryDeviceRequest)
	 */
	@Override
	public InquiryDeviceResponse fetchAllHanDevices(InquiryDeviceRequest inquiryDeviceRequest)
	{
		InquiryDeviceResponse response = new InquiryDeviceResponse();
		InternalResultsResponse<HanDevice> internalResponse = null;

		try
		{
			// Validate
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(DMPersistanceActionEnum.getDefaultName(),
					DMPersistanceActionEnum.FETCH_ALL);

			context.putObjectToBeValidated(SortExpression.class.getSimpleName(),
					inquiryDeviceRequest.getSortExpressions());
			context.putObjectToBeValidated(DeviceManagerInquiryRequest.class.getSimpleName(),
					inquiryDeviceRequest);
			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(), inquiryDeviceRequest);
			context.putObjectToBeValidated(DeviceSearch.class.getSimpleName(), inquiryDeviceRequest.getDeviceSearch());
			context.putObjectToBeValidated(InquiryDeviceRequest.class.getSimpleName(), inquiryDeviceRequest);

			if (getInquiryRequestValidationController().validate(context)
					&& getTenantRequestValidationController().validate(context)
					&& getInquiryDeviceRequestValidationController().validate(context))
			{
				if (!validateHanDevice(inquiryDeviceRequest, context))
				{
					SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse,
							context.getMessages(),
							true);

					return response;
				}

				if (getDeviceSearchValidationController().validate(context))
				{
					internalResponse = getElectricMeterBCL().fetchAllHanDevices(inquiryDeviceRequest);
				}

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
	 * com.sensus.dm.elec.device.bcf.IElectricMeterBCF#fetchAllLCMs(com.sensus.dm.common.device.model.request.
	 * InquiryDeviceRequest
	 * )
	 */
	@Override
	public InquiryDeviceResponse fetchAllLCMs(InquiryDeviceRequest inquiryDeviceRequest)
	{
		InquiryDeviceResponse response = new InquiryDeviceResponse();
		InternalResultsResponse<LCM> internalResponse = null;

		try
		{
			// Validate
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(DMPersistanceActionEnum.getDefaultName(),
					DMPersistanceActionEnum.FETCH_ALL);

			context.putObjectToBeValidated(SortExpression.class.getSimpleName(),
					inquiryDeviceRequest.getSortExpressions());
			context.putObjectToBeValidated(DeviceManagerInquiryRequest.class.getSimpleName(),
					inquiryDeviceRequest);
			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(), inquiryDeviceRequest);

			if (getInquiryRequestValidationController().validate(context)
					&& getTenantRequestValidationController().validate(context))
			{
				if (!validateLCM(inquiryDeviceRequest, context))
				{
					SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse,
							context.getMessages(),
							true);

					return response;
				}

				internalResponse = getElectricMeterBCL().fetchAllLCM(inquiryDeviceRequest);
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
	 * com.sensus.dm.elec.device.bcf.IElectricMeterBCF#fetchDeviceById(com.sensus.dm.common.device.model.request.
	 * DeviceRequest)
	 */
	@Override
	public DeviceResponse fetchDeviceById(DeviceRequest deviceRequest)
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
				internalResponse = getElectricMeterBCL().fetchDeviceById(deviceRequest);
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
	 * com.sensus.dm.elec.device.bcf.IElectricMeterBCF#generatePeakDemandCSV(com.sensus.dm.elec.device.model.request
	 * .DeviceReadingRequest)
	 */
	@Override
	public InquiryPeakDemandResponse generatePeakDemandCSV(DeviceReadingRequest request)
	{
		InquiryPeakDemandResponse response = new InquiryPeakDemandResponse();
		InternalResponse internalResponse = null;

		try
		{

			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(DMPersistanceActionEnum.getDefaultName(),
					DMPersistanceActionEnum.GENERATE_FILE_CSV_PEAK_READ);
			context.putObjectToBeValidated(Device.class.getSimpleName(), request.getDevice());
			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(), request);

			context.putObjectToBeValidated(ObjectToBeValidatedKeyEnum.INITIAL_DATE.getValue(),
					request.getInitialDate());
			context.putObjectToBeValidated(ObjectToBeValidatedKeyEnum.END_DATE.getValue(),
					request.getEndDate());
			context.putObjectToBeValidated(SortExpression.class.getSimpleName(),
					request.getSortExpressions());

			if (getTenantRequestValidationController().validate(context)
					&& getDeviceValidationController().validate(context)
					&& getRangeDateValidationController().validate(context)
					&& getOrderByValidationController().validate(context))
			{
				context.putObjectToBeValidated(Radio.class.getSimpleName(), request.getDevice()
						.getRadio());
				if (getRadioValidationController().validate(context))
				{
					internalResponse =
							getElectricMeterBCL().generatePeakDemandCSV(request);
				}
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

	/**
	 * Generate devices csv.
	 * 
	 * @param inquiryDeviceRequest the inquiry device request
	 * @return the inquiry device response
	 * @see com.sensus.dm.elec.device.bcf.IElectricMeterBCF#generateFileCSV(com.sensus.dm.elec.radio.model.request.
	 *      InquiryMeterRequest)
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

			context.putObjectToBeValidated(SortExpression.class.getSimpleName(),
					inquiryDeviceRequest.getSortExpressions());
			context.putObjectToBeValidated(DeviceManagerInquiryRequest.class.getSimpleName(),
					inquiryDeviceRequest);
			context.putObjectToBeValidated(InquiryDeviceRequest.class.getSimpleName(), inquiryDeviceRequest);

			if (getInquiryDeviceRequestValidationController().validate(context)
					&& getInquiryRequestValidationController().validate(context)
					&& getTenantRequestValidationController().validate(context))
			{
				switch (inquiryDeviceRequest.getDeviceType())
				{
					case ELECTRIC_METER:

						if (validateElectricMeter(inquiryDeviceRequest, context))
						{
							internalResponse = getElectricMeterBCL().generateDevicesCSV(inquiryDeviceRequest);
						}

						break;
					case HAN_DEVICE:

						context.putObjectToBeValidated(DeviceSearch.class.getSimpleName(),
								inquiryDeviceRequest.getDeviceSearch());

						if (getDeviceSearchValidationController().validate(context))
						{
							internalResponse = getElectricMeterBCL().generateDevicesCSV(inquiryDeviceRequest);
						}

						break;
					case LCM:

						internalResponse = getElectricMeterBCL().generateDevicesCSV(inquiryDeviceRequest);

						break;

					default:
						break;
				}
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
	 * com.sensus.dm.elec.device.bcf.IElectricMeterBCF#fetchLifecycleStates(com.sensus.dm.common.device.model.request.
	 * DeviceRequest
	 * )
	 */
	@Override
	public DeviceResponse fetchLifecycleStates(DeviceRequest request)
	{
		DeviceResponse response = new DeviceResponse();

		try
		{
			InternalResultsResponse<LifecycleState> internalResponse =
					getElectricMeterBCL().fetchLifecycleStates(request);
			response.setLifecycleStates(internalResponse.getResultsList());

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, false);
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
	 * com.sensus.dm.elec.device.bcf.IElectricMeterBCF#fetchAllManufactureByDeviceSubType(com.sensus.dm.common.device
	 * .model.request.DeviceRequest)
	 */
	@Override
	public DeviceResponse fetchAllManufactureByDeviceSubType(DeviceRequest deviceRequest)
	{

		DeviceResponse response = new DeviceResponse();
		InternalResultsResponse<Device> internalResponse = null;

		try
		{
			// Validate
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(DMPersistanceActionEnum.getDefaultName(),
					DMPersistanceActionEnum.FETCH_ALL_MANUFACTURE_BY_HAN_DEVICE_TYPE);

			context.putObjectToBeValidated(Device.class.getSimpleName(), deviceRequest.getFirstDevice());

			if (getDeviceValidationController().validate(context))
			{
				switch (deviceRequest.getFirstDevice().getDeviceType())
				{
					case HAN_DEVICE:

						context.putObjectToBeValidated(HanDevice.class.getSimpleName(), deviceRequest.getFirstDevice());

						if (getHanDeviceValidationController().validate(context))
						{
							internalResponse = getElectricMeterBCL().fetchAllManufactureByDeviceSubType(deviceRequest);
							response.setDevices(internalResponse.getResultsList());
						}
						break;

					case LCM:

						context.putObjectToBeValidated(LCM.class.getSimpleName(), deviceRequest.getFirstDevice());

						if (getLcmValidationController().validate(context))
						{
							internalResponse = getElectricMeterBCL().fetchAllManufactureByDeviceSubType(deviceRequest);
							response.setDevices(internalResponse.getResultsList());
						}
						break;

					default:
						break;
				}

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
	 * com.sensus.dm.elec.device.bcf.IElectricMeterBCF#fetchAllModelByDeviceSubType(com.sensus.dm.common.device.model
	 * .request.DeviceRequest)
	 */
	@Override
	public DeviceResponse fetchAllModelByDeviceSubType(DeviceRequest deviceRequest)
	{

		DeviceResponse response = new DeviceResponse();
		InternalResultsResponse<Device> internalResponse = null;

		try
		{
			// Validate
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(DMPersistanceActionEnum.getDefaultName(),
					DMPersistanceActionEnum.FETCH_ALL_MODEL_BY_HAN_DEVICE_TYPE);

			context.putObjectToBeValidated(Device.class.getSimpleName(), deviceRequest.getFirstDevice());

			if (getDeviceValidationController().validate(context))
			{
				switch (deviceRequest.getFirstDevice().getDeviceType())
				{
					case HAN_DEVICE:

						context.putObjectToBeValidated(HanDevice.class.getSimpleName(), deviceRequest.getFirstDevice());
						context.putObjectToBeValidated(DeviceModel.class.getSimpleName(),
								((HanDevice)deviceRequest.getFirstDevice()).getDeviceModel());

						if (getHanDeviceValidationController().validate(context)
								&& getDeviceModelValidationController().validate(context))
						{
							internalResponse = getElectricMeterBCL().fetchAllModelByDeviceSubType(deviceRequest);
							response.setDevices(internalResponse.getResultsList());
						}
						break;

					case LCM:

						context.putObjectToBeValidated(LCM.class.getSimpleName(), deviceRequest.getFirstDevice());
						context.putObjectToBeValidated(DeviceModel.class.getSimpleName(),
								((LCM)deviceRequest.getFirstDevice()).getDeviceModel());

						if (getLcmValidationController().validate(context)
								&& getDeviceModelValidationController().validate(context))
						{
							internalResponse = getElectricMeterBCL().fetchAllModelByDeviceSubType(deviceRequest);
							response.setDevices(internalResponse.getResultsList());
						}
						break;

					default:
						break;
				}
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
	 * com.sensus.dm.elec.device.bcf.IElectricMeterBCF#fetchAllHanDevicesByMeter(com.sensus.dm.common.device.model.request
	 * .DeviceRequest)
	 */
	@Override
	public DeviceResponse fetchAllHanDevicesByMeter(DeviceRequest deviceRequest)
	{
		DeviceResponse response = new DeviceResponse();
		InternalResultsResponse<Device> internalResponse = null;

		try
		{
			// Validate
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(DMPersistanceActionEnum.getDefaultName(),
					DMPersistanceActionEnum.FETCH_ALL_HAN_DECICES_BY_METER);

			context.putObjectToBeValidated(Device.class.getSimpleName(), deviceRequest.getFirstDevice());

			Radio radio = null;
			if (deviceRequest.getFirstDevice() != null)
			{
				radio = deviceRequest.getFirstDevice().getRadio();
			}
			context.putObjectToBeValidated(Radio.class.getSimpleName(), radio);

			if (getDeviceValidationController().validate(context)
					&& getRadioValidationController().validate(context))
			{
				internalResponse = getElectricMeterBCL().fetchAllHanDevicesByMeter(deviceRequest);
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
	 * com.sensus.dm.elec.device.bcf.IElectricMeterBCF#fetchAllConnectionState(com.sensus.dm.common.device.model.request
	 * .DeviceRequest)
	 */
	@Override
	public DeviceResponse fetchAllConnectionState(DeviceRequest deviceRequest)
	{
		DeviceResponse response = new DeviceResponse();
		InternalResultsResponse<ConnectionState> internalResponse = null;

		try
		{
			// Validate
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(DMPersistanceActionEnum.getDefaultName(),
					DMPersistanceActionEnum.FETCH_ALL_CONNECTION_STATE);

			context.putObjectToBeValidated(DeviceSearch.class.getSimpleName(), deviceRequest.getDeviceSearch());

			if (getDeviceSearchValidationController().validate(context))
			{
				internalResponse = getElectricMeterBCL().fetchAllConnectionState(deviceRequest);
				response.setConnectionStates(internalResponse.getResultsList());
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
	 * @see com.sensus.dm.elec.device.bcf.IElectricMeterBCF#fetchAllPeakDemand(com.sensus.dm.elec.device.model.request.
	 * DeviceReadingRequest)
	 */
	@Override
	public InquiryPeakDemandResponse fetchAllPeakDemand(DeviceReadingRequest request)
	{
		InquiryPeakDemandResponse response = new InquiryPeakDemandResponse();
		InternalResultsResponse<PeakDemand> internalResponse = null;

		try
		{

			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(DMPersistanceActionEnum.getDefaultName(),
					DMPersistanceActionEnum.FETCH_ALL_PEAK_DEMAND);
			context.putObjectToBeValidated(Device.class.getSimpleName(), request.getDevice());

			context.putObjectToBeValidated(ObjectToBeValidatedKeyEnum.INITIAL_DATE.getValue(),
					request.getInitialDate());
			context.putObjectToBeValidated(ObjectToBeValidatedKeyEnum.END_DATE.getValue(),
					request.getEndDate());
			context.putObjectToBeValidated(SortExpression.class.getSimpleName(),
					request.getSortExpressions());

			if (getDeviceValidationController().validate(context)
					&& getRangeDateValidationController().validate(context)
					&& getOrderByValidationController().validate(context))
			{
				context.putObjectToBeValidated(Radio.class.getSimpleName(), request.getDevice().getRadio());
				if (getRadioValidationController().validate(context))
				{
					internalResponse =
							getElectricMeterBCL().fetchAllPeakDemand(request);
				}
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
	 * com.sensus.dm.elec.device.bcf.IElectricMeterBCF#fetchLCMRelaysByDevice(com.sensus.dm.common.device.model.request
	 * .DeviceRequest)
	 */
	@Override
	public DeviceResponse fetchLCMRelaysByDevice(DeviceRequest deviceRequest)
	{
		DeviceResponse response = new DeviceResponse();
		InternalResultsResponse<LCMRelay> internalResponse = null;

		try
		{
			// Validate
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(DMPersistanceActionEnum.getDefaultName(),
					DMPersistanceActionEnum.FETCH_LCM_RELAYS_BY_DEVICE);

			context.putObjectToBeValidated(Device.class.getSimpleName(), deviceRequest.getFirstDevice());

			if (getDeviceValidationController().validate(context))
			{
				context.putObjectToBeValidated(Radio.class.getSimpleName(), deviceRequest.getFirstDevice().getRadio());

				if (getRadioValidationController().validate(context))
				{
					internalResponse = getElectricMeterBCL().fetchLCMRelaysByDevice(deviceRequest);
					response.setLcmRelays(internalResponse.getResultsList());
				}
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

	/**
	 * Validate electric meter.
	 * 
	 * @param inquiryDeviceRequest the inquiry device request
	 * @param context the context
	 * @return true, if successful
	 */
	private boolean validateElectricMeter(InquiryDeviceRequest inquiryDeviceRequest, ValidationContext context)
	{
		if (!ValidationUtil.isNull(inquiryDeviceRequest.getDeviceSearch())
				&& !ValidationUtil.isNull(inquiryDeviceRequest.getDeviceSearch().getElectricMeterSearch())
				&& !ValidationUtil.isNull(inquiryDeviceRequest.getDeviceSearch().getElectricMeterSearch()
						.getElectricMeter()))
		{

			ElectricMeter em = inquiryDeviceRequest.getDeviceSearch().getElectricMeterSearch().getElectricMeter();

			if (!ValidationUtil.isNull(em.getConfiguration()))
			{
				context.putObjectToBeValidated(ElectricMeterConfiguration.class.getSimpleName(), em.getConfiguration());

				if (!getElectricMeterConfigurationValidationController().validate(context))
				{
					return false;
				}

				if (!ValidationUtil.isNull(em.getRadio())
						&& !ValidationUtil.isNull(em.getRadio().getLocation()))
				{
					context.putObjectToBeValidated(Location.class.getSimpleName(), em.getRadio().getLocation());
					if (!getLocationValidationController().validate(context))
					{
						return false;
					}
				}
			}
		}
		return true;
	}

	/**
	 * Validate han device.
	 * 
	 * @param inquiryDeviceRequest the inquiry device request
	 * @param context the context
	 * @return true, if successful
	 */
	private boolean validateHanDevice(InquiryDeviceRequest inquiryDeviceRequest, ValidationContext context)
	{
		if (!ValidationUtil.isNull(inquiryDeviceRequest.getDeviceSearch())
				&& !ValidationUtil.isNull(inquiryDeviceRequest.getDeviceSearch().getHanDeviceSearch())
				&& !ValidationUtil.isNull(inquiryDeviceRequest.getDeviceSearch().getHanDeviceSearch().getHanDevice()))
		{
			context.putObjectToBeValidated(Device.class.getSimpleName(), inquiryDeviceRequest.getDeviceSearch()
					.getHanDeviceSearch().getHanDevice());
			if (!getDeviceValidationController().validate(context))
			{
				return false;
			}

			HanDevice hd = inquiryDeviceRequest.getDeviceSearch().getHanDeviceSearch().getHanDevice();

			if (!ValidationUtil.isNull(hd.getRadio())
					&& !ValidationUtil.isNull(hd.getRadio().getLocation()))
			{
				context.putObjectToBeValidated(Location.class.getSimpleName(), hd.getRadio().getLocation());
				if (!getLocationValidationController().validate(context))
				{
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Validate lcm.
	 * 
	 * @param inquiryDeviceRequest the inquiry device request
	 * @param context the context
	 * @return true, if successful
	 */
	private boolean validateLCM(InquiryDeviceRequest inquiryDeviceRequest, ValidationContext context)
	{
		if (!ValidationUtil.isNull(inquiryDeviceRequest.getDeviceSearch())
				&& !ValidationUtil.isNull(inquiryDeviceRequest.getDeviceSearch().getLcmSearch())
				&& !ValidationUtil.isNull(inquiryDeviceRequest.getDeviceSearch().getLcmSearch().getLcm()))
		{
			context.putObjectToBeValidated(Device.class.getSimpleName(), inquiryDeviceRequest.getDeviceSearch()
					.getLcmSearch().getLcm());
			if (!getDeviceValidationController().validate(context))
			{
				return false;
			}

			LCM lcm = inquiryDeviceRequest.getDeviceSearch().getLcmSearch().getLcm();

			if (!ValidationUtil.isNull(lcm.getConfiguration()))
			{
				context.putObjectToBeValidated(LCMConfiguration.class.getSimpleName(), lcm.getConfiguration());

				if (!getlCMConfigurationValidationController().validate(context))
				{
					return false;
				}

				if (!ValidationUtil.isNull(lcm.getRadio())
						&& !ValidationUtil.isNull(lcm.getRadio().getLocation()))
				{
					context.putObjectToBeValidated(Location.class.getSimpleName(), lcm.getRadio().getLocation());
					if (!getLocationValidationController().validate(context))
					{
						return false;
					}
				}
			}
		}
		return true;
	}
}
