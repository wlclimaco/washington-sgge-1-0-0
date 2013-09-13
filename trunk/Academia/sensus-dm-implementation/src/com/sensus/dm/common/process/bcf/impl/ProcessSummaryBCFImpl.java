package com.sensus.dm.common.process.bcf.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.util.SensusInterfaceUtil;
import com.sensus.common.validation.ValidationContext;
import com.sensus.common.validation.ValidationController;
import com.sensus.dm.common.base.model.DMPersistanceActionEnum;
import com.sensus.cbof.model.Device;
import com.sensus.cbof.model.Radio;
import com.sensus.dm.common.process.bcf.IProcessSummaryBCF;
import com.sensus.dm.common.process.bcl.IProcessSummaryBCL;
import com.sensus.dm.common.process.model.DMProcess;
import com.sensus.dm.common.process.model.request.ProcessRequest;
import com.sensus.dm.common.process.model.response.InquiryProcessResponse;
import com.sensus.dm.common.process.model.response.ProcessResponse;
import com.sensus.dm.common.tenant.model.request.TenantRequest;
import com.sensus.dm.elec.device.model.HanDevice;

/**
 * Process Summary BCF implementation.
 * 
 * @author QAT Global.
 */
public class ProcessSummaryBCFImpl implements IProcessSummaryBCF
{
	// -------------------------------------------------------------------------
	// Logs.

	/** The log. */
	private static final Logger LOG = LoggerFactory.getLogger(ProcessSummaryBCFImpl.class);

	// -------------------------------------------------------------------------

	/** The Constant DEFAULT_PROCESS_BCF_EXCEPTION_MSG. */
	private static final String DEFAULT_PROCESS_BCF_EXCEPTION_MSG = "sensus.epm.processbcfimpl.defaultexception";

	/** The process summary bcl. */
	private IProcessSummaryBCL processSummaryBCL;

	/** The process validation controller. */
	private ValidationController processValidationController;

	/** The process validation controller. */
	private ValidationController deviceValidationController;

	/** The han validation controller. */
	private ValidationController hanDeviceValidationController;

	/** The radio validation controller. */
	private ValidationController radioValidationController;

	/**
	 * Gets the process summary bcl.
	 * 
	 * @return the process summary bcl
	 */
	public IProcessSummaryBCL getProcessSummaryBCL()
	{
		return processSummaryBCL;
	}

	/**
	 * Sets the process summary bcl.
	 * 
	 * @param processSummaryBCL the new process summary bcl
	 */
	public void setProcessSummaryBCL(IProcessSummaryBCL processSummaryBCL)
	{
		this.processSummaryBCL = processSummaryBCL;
	}

	/**
	 * Gets the process validation controller.
	 * 
	 * @return the process validation controller
	 */
	public ValidationController getProcessValidationController()
	{
		return processValidationController;
	}

	/**
	 * Sets the process validation controller.
	 * 
	 * @param processValidationController the new process validation controller
	 */
	public void setProcessValidationController(ValidationController processValidationController)
	{
		this.processValidationController = processValidationController;
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
	 * Gets the han device validation controller.
	 * 
	 * @return the han device validation controller
	 */
	public ValidationController getHanDeviceValidationController()
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

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.bcf.IProcessBCF#fetchDemandResponseSummary(com.sensus.dm.common.process.model.request
	 * .ProcessRequest
	 * )
	 */
	@Override
	public ProcessResponse fetchDemandResponseSummary(ProcessRequest processRequest)
	{
		ProcessResponse response = new ProcessResponse();

		try
		{
			InternalResultsResponse<DMProcess> internalResultsResponse = null;

			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(DMPersistanceActionEnum.getDefaultName(),
					DMPersistanceActionEnum.FETCH_DEMAND_RESPONSE_SUMMARY);
			context.putObjectToBeValidated(DMProcess.class.getSimpleName(),
					processRequest.getFirstProcess());
			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(),
					processRequest);

			if (getProcessValidationController().validate(context))
			{
				internalResultsResponse = getProcessSummaryBCL().fetchDemandResponseSummary(processRequest);
				response.setProcesses(internalResultsResponse.getResultsList());
			}

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResultsResponse,
					context.getMessages(), false);

		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_PROCESS_BCF_EXCEPTION_MSG);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.bcf.IProcessBCF#fetchDemandResponseProgramParticipation(com.sensus.dm.elec.device.model
	 * .request
	 * .DeviceRequest)
	 */
	@Override
	public ProcessResponse fetchDemandResponseProgramParticipation(ProcessRequest processRequest)
	{
		ProcessResponse response = new ProcessResponse();

		try
		{
			InternalResultsResponse<DMProcess> internalResultsResponse = null;

			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(DMPersistanceActionEnum.getDefaultName(),
					DMPersistanceActionEnum.FETCH_DEMAND_RESPONSE_PROGRAM_PARTICIPATION);
			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(), processRequest);
			context.putObjectToBeValidated(DMProcess.class.getSimpleName(), processRequest.getFirstProcess());

			if (getProcessValidationController().validate(context))
			{
				context.putObjectToBeValidated(HanDevice.class.getSimpleName(),
						processRequest.getFirstProcess().getFirstProcessItem().getDevice());

				if (getHanDeviceValidationController().validate(context))
				{
					internalResultsResponse =
							getProcessSummaryBCL().fetchDemandResponseProgramParticipation(processRequest);
					response.setProcesses(internalResultsResponse.getResultsList());
				}
			}

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResultsResponse,
					context.getMessages(), false);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_PROCESS_BCF_EXCEPTION_MSG);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.bcf.IProcessBCF#fetchAllDemandResponseSetup(com.sensus.dm.common.process.model.request
	 * .ProcessRequest)
	 */
	@Override
	public ProcessResponse fetchAllDemandResponseSetup(ProcessRequest processRequest)
	{
		ProcessResponse response = new ProcessResponse();

		try
		{
			InternalResultsResponse<DMProcess> internalResultsResponse = null;

			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(DMPersistanceActionEnum.getDefaultName(),
					DMPersistanceActionEnum.FETCH_DEMAND_RESPONSE_SETUP_BY_DEVICE);
			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(), processRequest);
			context.putObjectToBeValidated(DMProcess.class.getSimpleName(), processRequest.getFirstProcess());

			if (getProcessValidationController().validate(context))
			{
				Device device = processRequest.getFirstProcess().getFirstProcessItem().getDevice();
				context.putObjectToBeValidated(Device.class.getSimpleName(), device);

				if (getDeviceValidationController().validate(context))
				{
					Radio radio = new Radio();
					if (device.getRadio() != null)
					{
						radio = device.getRadio();
					}
					context.putObjectToBeValidated(Radio.class.getSimpleName(), radio);

					if (getRadioValidationController().validate(context))
					{
						internalResultsResponse = getProcessSummaryBCL().fetchAllDemandResponseSetup(processRequest);
						response.setProcesses(internalResultsResponse.getResultsList());
					}
				}
			}

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResultsResponse,
					context.getMessages(), false);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_PROCESS_BCF_EXCEPTION_MSG);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.bcf.IProcessBCF#fetchProcessSendHanTextMessage(com.sensus.dm.common.process.model
	 * .request.
	 * ProcessRequest)
	 */
	@Override
	public InquiryProcessResponse fetchProcessSendHanTextMessage(ProcessRequest processRequest)
	{
		InquiryProcessResponse response = new InquiryProcessResponse();

		try
		{
			InternalResultsResponse<DMProcess> internalResultsResponse = null;

			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(DMPersistanceActionEnum.getDefaultName(),
					DMPersistanceActionEnum.FETCH_PROCESS_SEND_HAN_TEXT_MESSAGE);
			context.putObjectToBeValidated(DMProcess.class.getSimpleName(),
					processRequest.getFirstProcess());
			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(),
					processRequest);

			if (getProcessValidationController().validate(context))
			{
				internalResultsResponse = getProcessSummaryBCL().fetchProcessSendHanTextMessage(processRequest);
			}

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResultsResponse,
					context.getMessages(), true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_PROCESS_BCF_EXCEPTION_MSG);
		}

		return response;
	}

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

}
