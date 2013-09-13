package com.sensus.dm.elec.device.bcf.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sensus.cbof.model.Device;
import com.sensus.cbof.model.Radio;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.util.SensusInterfaceUtil;
import com.sensus.common.validation.ValidationContext;
import com.sensus.common.validation.ValidationController;
import com.sensus.dm.common.base.model.DMPersistanceActionEnum;
import com.sensus.dm.common.base.model.ObjectToBeValidatedKeyEnum;
import com.sensus.dm.common.device.model.request.DeviceRequest;
import com.sensus.dm.common.tenant.model.request.TenantRequest;
import com.sensus.dm.elec.device.bcf.IDeviceReadingBCF;
import com.sensus.dm.elec.device.bcl.IDeviceReadingBCL;
import com.sensus.dm.elec.device.model.IntervalRead;
import com.sensus.dm.elec.device.model.LoadProfile;
import com.sensus.dm.elec.device.model.request.DeviceReadingRequest;
import com.sensus.dm.elec.device.model.response.InquiryIntervalReadResponse;
import com.sensus.dm.elec.device.model.response.InquiryLoadProfileResponse;
import com.sensus.dm.elec.device.model.response.TOUReadResponse;

/**
 * The Class DeviceReadingBCFImpl.
 * 
 * @author QAT Global
 */
public class DeviceReadingBCFImpl implements IDeviceReadingBCF
{
	// -------------------------------------------------------------------------
	// Logs.

	/** The log. */
	private static transient Logger LOG = LoggerFactory.getLogger(DeviceReadingBCFImpl.class);

	// -------------------------------------------------------------------------

	// Messages.

	/** The Constant DEFAULT_DEVICE_BCF_EXCEPTION_MSG. */
	private static final String DEFAULT_DEVICE_BCF_EXCEPTION_MSG = "sensus.epm.devicebcfimpl.defaultexception";

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Spring injection points:

	/** The device reading bcl. */
	private IDeviceReadingBCL deviceReadingBCL; // injected by Spring through setter

	/** The device validation controller. */
	private ValidationController deviceValidationController; // injected by Spring through setter

	/** The tenant request validation controller. */
	private ValidationController tenantRequestValidationController; // injected by Spring through setter

	/** The range date validation controller. */
	private ValidationController rangeDateValidationController; // injected by Spring through setter

	/** The radio validation controller. */
	private ValidationController radioValidationController; // injected by Spring through setter

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
	 * Gets the device reading bcl.
	 * 
	 * @return the device reading bcl
	 */
	public IDeviceReadingBCL getDeviceReadingBCL()
	{
		return deviceReadingBCL;
	}

	/**
	 * Sets the device reading bcl.
	 * 
	 * @param deviceReadingBCL the new device reading bcl
	 */
	public void setDeviceReadingBCL(IDeviceReadingBCL deviceReadingBCL)
	{
		this.deviceReadingBCL = deviceReadingBCL;
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
	 * Gets the tenant request validation controller.
	 * 
	 * @return the tenant request validation controller
	 */
	public ValidationController getTenantRequestValidationController()
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

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Public interface:

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Water Reading interface:

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.device.bcf.IDeviceReadingBCF#fetchAllWaterGasDataRead(com.sensus.dm.elec.device.model.request
	 * .DeviceReadingRequest)
	 */
	@Override
	public InquiryIntervalReadResponse fetchAllWaterGasDataRead(DeviceReadingRequest request)
	{
		InquiryIntervalReadResponse response = new InquiryIntervalReadResponse();
		InternalResultsResponse<IntervalRead> internalResponse = null;

		try
		{
			// Validate
			ValidationContext context = new ValidationContext();

			if (validateRequest(request, context, DMPersistanceActionEnum.FETCH_ALL_WATER_GAS_DATA_READ))
			{
				internalResponse = getDeviceReadingBCL().fetchAllWaterGasDataRead(request);
				response.setIntervalReads(internalResponse.getResultsList());
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
	 * com.sensus.dm.water.device.bcf.IWaterMeterBCF#generateFileCSVWaterDataRead(com.sensus.dm.elec.device.model.request
	 * .DeviceReadingRequest)
	 */
	@Override
	public InquiryIntervalReadResponse generateFileCSVWaterGasDataRead(
			DeviceReadingRequest request)
	{
		InquiryIntervalReadResponse response = new InquiryIntervalReadResponse();

		InternalResponse internalResponse = null;

		try
		{
			// Validate
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(DMPersistanceActionEnum.getDefaultName(),
					DMPersistanceActionEnum.GENERATE_FILE_CSV_WATER_GAS_DATA_READ);

			context.putObjectToBeValidated(Device.class.getSimpleName(), request.getDevice());
			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(),
					request);
			context.putObjectToBeValidated(ObjectToBeValidatedKeyEnum.INITIAL_DATE.getValue(),
					request.getInitialDate());
			context.putObjectToBeValidated(ObjectToBeValidatedKeyEnum.END_DATE.getValue(),
					request.getEndDate());

			if (getTenantRequestValidationController().validate(context)
					&& getDeviceValidationController().validate(context)
					&& getRangeDateValidationController().validate(context))
			{
				context.putObjectToBeValidated(Radio.class.getSimpleName(), request.getDevice()
						.getRadio());
				if (getRadioValidationController().validate(context))
				{
					internalResponse =
							getDeviceReadingBCL().generateFileCSVWaterGasDataRead(request);
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

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// TOU (Time Of Use) interface:

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.device.bcf.IDeviceReadingBCF#fetchAllTOURead(com.sensus.dm.elec.device.model.request.
	 * DeviceReadingRequest
	 * )
	 */
	@Override
	public TOUReadResponse fetchAllTOURead(DeviceReadingRequest request)
	{
		TOUReadResponse response = new TOUReadResponse();
		InternalResultsResponse<String[][]> internalResponse = null;

		try
		{
			// Validate
			ValidationContext context = new ValidationContext();

			if (validateRequest(request, context, DMPersistanceActionEnum.FETCH_ALL_TOU_READ))
			{
				internalResponse = getDeviceReadingBCL().fetchAllTOURead(request);
				response.setTouRead(internalResponse.getFirstResult());
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
	 * com.sensus.dm.elec.device.bcf.IDeviceReadingBCF#generateFileCSVTOURead(com.sensus.dm.elec.device.model.request.
	 * DeviceReadingRequest)
	 */
	@Override
	public TOUReadResponse generateFileCSVTOURead(DeviceReadingRequest request)
	{
		TOUReadResponse response = new TOUReadResponse();
		InternalResponse internalResponse = null;

		try
		{
			// Validate
			ValidationContext context = new ValidationContext();

			if (validateRequest(request, context, DMPersistanceActionEnum.GENERATE_FILE_CSV_TOU))
			{
				internalResponse = getDeviceReadingBCL().generateFileCSVTOURead(request); // Generating CSV file.
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

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Load Profile interface:

	/**
	 * Fetch all load profile read.
	 * 
	 * @param request the request
	 * @return the inquiry interval read response
	 */
	@Override
	public InquiryIntervalReadResponse fetchAllLoadProfileRead(DeviceReadingRequest request)
	{
		InquiryIntervalReadResponse response = new InquiryIntervalReadResponse();
		InternalResultsResponse<IntervalRead> internalResponse = null;

		try
		{
			// Validate
			ValidationContext context = new ValidationContext();

			if (validateRequest(request, context, DMPersistanceActionEnum.FETCH_ALL_LOAD_PROFILE_READ))
			{
				internalResponse = getDeviceReadingBCL().fetchAllLoadProfileRead(request);
				response.setIntervalReads(internalResponse.getResultsList());
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
	 * Generate file csv load profile read.
	 * 
	 * @param request the request
	 * @return the inquiry interval read response
	 */
	@Override
	public InquiryIntervalReadResponse generateFileCSVLoadProfileRead(DeviceReadingRequest request)
	{
		InquiryIntervalReadResponse response = new InquiryIntervalReadResponse();
		InternalResponse internalResponse = null;

		try
		{
			// Validate
			ValidationContext context = new ValidationContext();

			if (validateRequest(request, context, DMPersistanceActionEnum.GENERATE_FILE_CSV_LOAD_PROFILE_READ))
			{
				internalResponse = getDeviceReadingBCL().generateFileCSVLoadProfileRead(request);
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

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Interval reads interface:

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.device.bcf.IDeviceReadingBCF#fetchAllIntervalRead(com.sensus.dm.elec.device.model.request.
	 * DeviceReadingRequest)
	 */
	@Override
	public InquiryIntervalReadResponse fetchAllIntervalRead(DeviceReadingRequest request)
	{
		InquiryIntervalReadResponse response = new InquiryIntervalReadResponse();
		InternalResultsResponse<IntervalRead> internalResponse = null;

		try
		{
			// Validate
			ValidationContext context = new ValidationContext();

			if (validateRequest(request, context, DMPersistanceActionEnum.FETCH_ALL_INTERVAL_READ))
			{
				internalResponse = getDeviceReadingBCL().fetchAllIntervalRead(request);
				response.setIntervalReads(internalResponse.getResultsList());
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
	 * com.sensus.dm.elec.device.bcf.IDeviceReadingBCF#generateFileCSVIntervalRead(com.sensus.dm.elec.device.model.request
	 * .
	 * DeviceReadingRequest)
	 */
	@Override
	public InquiryIntervalReadResponse generateFileCSVIntervalRead(DeviceReadingRequest request)
	{
		InquiryIntervalReadResponse response = new InquiryIntervalReadResponse();
		InternalResponse internalResponse = null;

		try
		{
			// Validate
			ValidationContext context = new ValidationContext();

			if (validateRequest(request, context, DMPersistanceActionEnum.GENERATE_FILE_CSV_INTERVAL_READ))
			{
				internalResponse = getDeviceReadingBCL().generateFileCSVIntervalRead(request);
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

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Snapshot interface:

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.elec.device.bcf.IDeviceReadingBCF#fetchAllSnapshots(com.sensus.dm.elec.device.model.request.
	 * DeviceReadingRequest)
	 */
	@Override
	public InquiryIntervalReadResponse fetchAllSnapshots(DeviceReadingRequest request)
	{
		InquiryIntervalReadResponse response = new InquiryIntervalReadResponse();
		InternalResultsResponse<IntervalRead> internalResponse = null;

		try
		{
			ValidationContext context = new ValidationContext();

			if (validateRequest(request, context, DMPersistanceActionEnum.FETCH_ALL_SNAPSHOT))
			{
				internalResponse = getDeviceReadingBCL().fetchAllSnapshots(request);
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
	 * com.sensus.dm.elec.device.bcf.IDeviceReadingBCF#generateFileCSVSnapshot(com.sensus.dm.elec.device.model.request.
	 * DeviceReadingRequest)
	 */
	@Override
	public InquiryIntervalReadResponse generateFileCSVSnapshot(DeviceReadingRequest request)
	{
		InquiryIntervalReadResponse response = new InquiryIntervalReadResponse();
		InternalResponse internalResponse = null;

		try
		{
			// Validate
			ValidationContext context = new ValidationContext();

			if (validateRequest(request, context, DMPersistanceActionEnum.GENERATE_FILE_CSV_SNAPSHOT))
			{
				internalResponse = getDeviceReadingBCL().generateFileCSVSnapshot(request);
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

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Load profile interface:

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.device.bcf.IDeviceReadingBCF#fetchUpdatedMeterLoadProfile(com.sensus.dm.elec.device.model.request
	 * .
	 * DeviceRequest)
	 */
	@Override
	public InquiryLoadProfileResponse fetchUpdatedMeterLoadProfile(DeviceRequest request)
	{
		InquiryLoadProfileResponse response = new InquiryLoadProfileResponse();
		InternalResultsResponse<LoadProfile> internalResponse = null;

		try
		{
			// Validate
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(DMPersistanceActionEnum.getDefaultName(),
					DMPersistanceActionEnum.FETCH_UPDATED_LOAD_PROFILE);

			context.putObjectToBeValidated(Device.class.getSimpleName(), request.getFirstDevice());
			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(),
					request);

			if (getTenantRequestValidationController().validate(context)
					&& getDeviceValidationController().validate(context))
			{
				context.putObjectToBeValidated(Radio.class.getSimpleName(), request.getFirstDevice().getRadio());
				if (getRadioValidationController().validate(context))
				{
					internalResponse =
							getDeviceReadingBCL().fetchUpdatedMeterLoadProfile(request);

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

	// -------------------------------------------------------------------------

	/**
	 * Validate interval read.
	 * 
	 * @param request the request
	 * @param context the context
	 * @param fetchParam the DMPersistanceActionEnum
	 * @return true, if successful
	 */
	private boolean validateRequest(DeviceReadingRequest request, ValidationContext context,
			DMPersistanceActionEnum fetchParam)
	{
		context.getValidationArguments().put(DMPersistanceActionEnum.getDefaultName(),
				fetchParam);

		context.putObjectToBeValidated(Device.class.getSimpleName(), request.getDevice());
		context.putObjectToBeValidated(TenantRequest.class.getSimpleName(),
				request);
		context.putObjectToBeValidated(ObjectToBeValidatedKeyEnum.INITIAL_DATE.getValue(),
				request.getInitialDate());
		context.putObjectToBeValidated(ObjectToBeValidatedKeyEnum.END_DATE.getValue(),
				request.getEndDate());

		if (getTenantRequestValidationController().validate(context)
				&& getDeviceValidationController().validate(context)
				&& getRangeDateValidationController().validate(context))
		{
			context.putObjectToBeValidated(Radio.class.getSimpleName(), request.getDevice().getRadio());
			if (getRadioValidationController().validate(context))
			{
				return true;
			}
		}
		return false;
	}
}
