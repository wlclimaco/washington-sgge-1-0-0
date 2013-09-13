package com.sensus.dm.common.process.bcf.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sensus.cbof.model.Device;
import com.sensus.common.model.SortExpression;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.util.SensusInterfaceUtil;
import com.sensus.common.validation.ValidationContext;
import com.sensus.common.validation.ValidationController;
import com.sensus.dm.common.base.model.DMPersistanceActionEnum;
import com.sensus.dm.common.base.model.ObjectToBeValidatedKeyEnum;
import com.sensus.dm.common.base.model.request.DeviceManagerInquiryRequest;
import com.sensus.dm.common.process.bcf.IProcessCSVBCF;
import com.sensus.dm.common.process.bcl.IProcessBCL;
import com.sensus.dm.common.process.bcl.IProcessCSVBCL;
import com.sensus.dm.common.process.model.CSVProcess;
import com.sensus.dm.common.process.model.DMProcess;
import com.sensus.dm.common.process.model.ProcessSearch;
import com.sensus.dm.common.process.model.request.InquiryProcessRequest;
import com.sensus.dm.common.process.model.request.ProcessRequest;
import com.sensus.dm.common.process.model.response.ProcessResponse;
import com.sensus.dm.common.tenant.model.request.TenantRequest;

/**
 * The Class ProcessBCFImpl.
 * 
 * @author QAT Global.
 */
public class ProcessCSVBCFImpl implements IProcessCSVBCF
{
	// -------------------------------------------------------------------------
	// Logs.

	/** The log. */
	private static final Logger LOG = LoggerFactory.getLogger(ProcessCSVBCFImpl.class);

	// -------------------------------------------------------------------------

	/** The Constant DEFAULT_PROCESS_BCF_EXCEPTION_MSG. */
	private static final String DEFAULT_PROCESS_BCF_EXCEPTION_MSG = "sensus.epm.processbcfimpl.defaultexception";

	/** The process bcl. */
	private IProcessBCL processBCL;

	/** The process csvbcl. */
	private IProcessCSVBCL processCSVBCL;

	/** The process validation controller. */
	private ValidationController processValidationController;

	/** The process validation controller. */
	private ValidationController deviceValidationController;

	/** The tenant request validation controller. */
	private ValidationController tenantRequestValidationController;

	/** The inquiry request validation controller. */
	private ValidationController inquiryRequestValidationController;

	/** The range date validation controller. */
	private ValidationController rangeDateValidationController;

	/** The inquiry process request validation controller. */
	private ValidationController inquiryProcessRequestValidationController;

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
	public void setProcessBCL(IProcessBCL processBCL)
	{
		this.processBCL = processBCL;
	}

	/**
	 * Gets the process csvbcl.
	 * 
	 * @return the process csvbcl
	 */
	public IProcessCSVBCL getProcessCSVBCL()
	{
		return processCSVBCL;
	}

	/**
	 * Sets the process csvbcl.
	 * 
	 * @param processCSVBCL the new process csvbcl
	 */
	public void setProcessCSVBCL(IProcessCSVBCL processCSVBCL)
	{
		this.processCSVBCL = processCSVBCL;
	}

	public ValidationController getInquiryRequestValidationController()
	{
		return inquiryRequestValidationController;
	}

	public void setInquiryRequestValidationController(ValidationController inquiryRequestValidationController)
	{
		this.inquiryRequestValidationController = inquiryRequestValidationController;
	}

	public ValidationController getRangeDateValidationController()
	{
		return rangeDateValidationController;
	}

	public void setRangeDateValidationController(ValidationController rangeDateValidationController)
	{
		this.rangeDateValidationController = rangeDateValidationController;
	}

	public ValidationController getInquiryProcessRequestValidationController()
	{
		return inquiryProcessRequestValidationController;
	}

	public void setInquiryProcessRequestValidationController(
			ValidationController inquiryProcessRequestValidationController)
	{
		this.inquiryProcessRequestValidationController = inquiryProcessRequestValidationController;
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

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.bcf.IProcessBCF#insertCSVProcess(com.sensus.dm.common.process.model.request.
	 * InquiryProcessRequest
	 * )
	 */
	@Override
	public ProcessResponse insertCSVProcess(InquiryProcessRequest processRequest)
	{
		ProcessResponse response = new ProcessResponse();
		InternalResultsResponse<CSVProcess> internalResultsResponse = null;

		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(DMPersistanceActionEnum.getDefaultName(),
					DMPersistanceActionEnum.INSERT_CSV_PROCESS);
			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(),
					processRequest);

			if (getTenantRequestValidationController().validate(context))
			{
				internalResultsResponse = getProcessCSVBCL().insertCSVProcess(processRequest);

				CSVProcess csvProcess = internalResultsResponse.getFirstResult();

				if (csvProcess != null)
				{
					response.setFileName(csvProcess.getFileName());
					response.setProcesses(csvProcess.getProcessList());
				}
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

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.process.bcf.IProcessBCF#updateCSVDownloaded(com.sensus.dm.common.process.model.request.
	 * ProcessRequest)
	 */
	@Override
	public ProcessResponse updateCSVDownloaded(ProcessRequest processRequest)
	{
		ProcessResponse response = new ProcessResponse();
		InternalResponse internalResponse = null;

		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(DMPersistanceActionEnum.getDefaultName(),
					DMPersistanceActionEnum.UPDATE_CSV_DOWNLOADED);
			context.putObjectToBeValidated(DMProcess.class.getSimpleName(),
					processRequest.getFirstProcess());
			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(),
					processRequest);

			if (getProcessValidationController().validate(context))
			{
				internalResponse = getProcessBCL().updateProcess(processRequest);
			}

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(),
					true);

		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_PROCESS_BCF_EXCEPTION_MSG);
		}

		return response;
	}

	@Override
	public ProcessResponse generateFileCSVSummary(InquiryProcessRequest inquiryProcessRequest)
	{
		ProcessResponse response = new ProcessResponse();
		InternalResponse internalResponse = null;

		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(DMPersistanceActionEnum.getDefaultName(),
					DMPersistanceActionEnum.GENERATE_FILE_CSV);
			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(),
					inquiryProcessRequest);
			context.putObjectToBeValidated(DMProcess.class.getSimpleName(),
					inquiryProcessRequest.getFirstProcess());

			if (getProcessValidationController().validate(context))
			{
				internalResponse = getProcessCSVBCL().generateFileCSVSummary(inquiryProcessRequest);
			}

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(),
					true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_PROCESS_BCF_EXCEPTION_MSG);
		}

		return response;
	}

	@Override
	public ProcessResponse generateFileCSVToday(InquiryProcessRequest inquiryProcessRequest)
	{
		ProcessResponse response = new ProcessResponse();
		InternalResponse internalResponse = null;
		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(DMPersistanceActionEnum.getDefaultName(),
					DMPersistanceActionEnum.GENERATE_TODAY_FILE_CSV);
			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(),
					inquiryProcessRequest);
			context.putObjectToBeValidated(InquiryProcessRequest.class.getSimpleName(),
					inquiryProcessRequest);
			context.putObjectToBeValidated(SortExpression.class.getSimpleName(),
					inquiryProcessRequest.getSortExpressions());
			context.putObjectToBeValidated(DeviceManagerInquiryRequest.class.getSimpleName(),
					inquiryProcessRequest);
			context.putObjectToBeValidated(ProcessSearch.class.getSimpleName(),
					inquiryProcessRequest.getProcessSearch());

			if (getInquiryProcessRequestValidationController().validate(context)
					&& getInquiryRequestValidationController().validate(context))
			{
				context.putObjectToBeValidated(ObjectToBeValidatedKeyEnum.INITIAL_DATE.getValue(),
						inquiryProcessRequest.getProcessSearch().getStartDate());
				context.putObjectToBeValidated(ObjectToBeValidatedKeyEnum.END_DATE.getValue(),
						inquiryProcessRequest.getProcessSearch().getEndDate());

				if (getRangeDateValidationController().validate(context))
				{
					internalResponse =
							getProcessCSVBCL().generateFileCSVToday(inquiryProcessRequest);
				}

			}

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(),
					true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_PROCESS_BCF_EXCEPTION_MSG);
		}

		return response;
	}

	@Override
	public ProcessResponse generateFileCSVEventHistory(InquiryProcessRequest inquiryProcessRequest)
	{
		ProcessResponse response = new ProcessResponse();
		InternalResponse internalResponse = null;

		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(DMPersistanceActionEnum.getDefaultName(),
					DMPersistanceActionEnum.GENERATE_FILE_CSV);
			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(),
					inquiryProcessRequest);
			context.putObjectToBeValidated(SortExpression.class.getSimpleName(),
					inquiryProcessRequest.getSortExpressions());
			context.putObjectToBeValidated(DeviceManagerInquiryRequest.class.getSimpleName(),
					inquiryProcessRequest);

			if (getTenantRequestValidationController().validate(context)
					&& getInquiryRequestValidationController().validate(context))
			{
				internalResponse = getProcessCSVBCL().generateFileCSVEventHistory(inquiryProcessRequest);
			}

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(),
					true);
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
	 * com.sensus.dm.common.process.bcf.IProcessCSVBCF#generateFileCSVDeviceHistory(com.sensus.dm.common.process.model
	 * .request.InquiryProcessRequest)
	 */
	@Override
	public ProcessResponse generateFileCSVDeviceHistory(InquiryProcessRequest inquiryProcessRequest)
	{
		ProcessResponse response = new ProcessResponse();
		InternalResponse internalResponse = null;

		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(DMPersistanceActionEnum.getDefaultName(),
					DMPersistanceActionEnum.GENERATE_FILE_CSV);
			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(),
					inquiryProcessRequest);
			context.putObjectToBeValidated(Device.class.getSimpleName(), inquiryProcessRequest.getFirstDevice());
			context.putObjectToBeValidated(SortExpression.class.getSimpleName(),
					inquiryProcessRequest.getSortExpressions());
			context.putObjectToBeValidated(DeviceManagerInquiryRequest.class.getSimpleName(),
					inquiryProcessRequest);

			if (getDeviceValidationController().validate(context)
					&& getTenantRequestValidationController().validate(context)
					&& getInquiryRequestValidationController().validate(context))
			{
				internalResponse = getProcessCSVBCL().generateFileCSVDeviceHistory(inquiryProcessRequest);
			}

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(),
					true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_PROCESS_BCF_EXCEPTION_MSG);
		}

		return response;
	}

	/**
	 * Generate file csv demand response summary.
	 * 
	 * @param inquiryProcessRequest the inquiry process request
	 * @return the process response
	 */
	@Override
	public ProcessResponse generateFileCSVDemandResponseSummary(InquiryProcessRequest inquiryProcessRequest)
	{
		ProcessResponse response = new ProcessResponse();
		InternalResponse internalResponse = null;

		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(DMPersistanceActionEnum.getDefaultName(),
					DMPersistanceActionEnum.GENERATE_FILE_CSV);
			context.putObjectToBeValidated(DMProcess.class.getSimpleName(),
					inquiryProcessRequest.getFirstProcess());
			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(),
					inquiryProcessRequest);

			if (getProcessValidationController().validate(context))
			{
				internalResponse = getProcessCSVBCL().generateFileCSVDemandResponseSummary(inquiryProcessRequest);
			}

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(),
					true);
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
	 * com.sensus.dm.common.process.bcf.IProcessCSVBCF#generateFileCSVDemandReadSummary(com.sensus.dm.common.process
	 * .model.request.InquiryProcessRequest)
	 */
	@Override
	public ProcessResponse generateFileCSVDemandReadSummary(InquiryProcessRequest inquiryProcessRequest)
	{
		ProcessResponse response = new ProcessResponse();
		InternalResponse internalResponse = null;

		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(DMPersistanceActionEnum.getDefaultName(),
					DMPersistanceActionEnum.GENERATE_FILE_CSV);
			context.putObjectToBeValidated(DMProcess.class.getSimpleName(),
					inquiryProcessRequest.getFirstProcess());
			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(),
					inquiryProcessRequest);

			if (getProcessValidationController().validate(context))
			{
				internalResponse = getProcessCSVBCL().generateFileCSVDemandReadSummary(inquiryProcessRequest);
			}

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(),
					true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_PROCESS_BCF_EXCEPTION_MSG);
		}

		return response;
	}

	/**
	 * Generate file csv import han summary.
	 * 
	 * @param inquiryProcessRequest the inquiry process request
	 * @return the process response
	 */
	@Override
	public ProcessResponse generateFileCSVImportHanSummary(InquiryProcessRequest inquiryProcessRequest)
	{
		ProcessResponse response = new ProcessResponse();
		InternalResponse internalResponse = null;

		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(DMPersistanceActionEnum.getDefaultName(),
					DMPersistanceActionEnum.GENERATE_FILE_CSV);
			context.putObjectToBeValidated(DMProcess.class.getSimpleName(),
					inquiryProcessRequest.getFirstProcess());
			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(),
					inquiryProcessRequest);

			if (getProcessValidationController().validate(context))
			{
				internalResponse = getProcessCSVBCL().generateFileCSVImportHanSummary(inquiryProcessRequest);
			}

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(),
					true);
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
	 * com.sensus.dm.common.process.bcf.IProcessBCF#generateFileCSVCommunicationSummary(com.sensus.dm.common.process.model
	 * .request
	 * .InquiryProcessRequest)
	 */
	@Override
	public ProcessResponse generateFileCSVCommunicationSummary(InquiryProcessRequest inquiryProcessRequest)
	{
		ProcessResponse response = new ProcessResponse();
		InternalResponse internalResponse = null;

		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(DMPersistanceActionEnum.getDefaultName(),
					DMPersistanceActionEnum.GENERATE_FILE_CSV);
			context.putObjectToBeValidated(DMProcess.class.getSimpleName(),
					inquiryProcessRequest.getFirstProcess());
			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(),
					inquiryProcessRequest);

			if (getProcessValidationController().validate(context))
			{
				internalResponse = getProcessCSVBCL().generateFileCSVCommunicationSummary(inquiryProcessRequest);
			}

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(),
					true);
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
	 * com.sensus.dm.common.process.bcf.IProcessCSVBCF#generateFileCSVTamperDetectSummary(com.sensus.dm.common.process
	 * .model.request.InquiryProcessRequest)
	 */
	@Override
	public ProcessResponse generateFileCSVTamperDetectSummary(InquiryProcessRequest inquiryProcessRequest)
	{
		ProcessResponse response = new ProcessResponse();
		InternalResponse internalResponse = null;

		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(DMPersistanceActionEnum.getDefaultName(),
					DMPersistanceActionEnum.GENERATE_FILE_CSV);
			context.putObjectToBeValidated(DMProcess.class.getSimpleName(),
					inquiryProcessRequest.getFirstProcess());
			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(),
					inquiryProcessRequest);

			if (getProcessValidationController().validate(context))
			{
				internalResponse = getProcessCSVBCL().generateFileCSVTamperDetectSummary(inquiryProcessRequest);
			}

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(),
					true);
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
	 * com.sensus.dm.common.process.bcf.IProcessCSVBCF#generateFileCSVDemandResponseSetupSummary(com.sensus.dm.common
	 * .process.model.request.InquiryProcessRequest)
	 */
	@Override
	public ProcessResponse generateFileCSVDemandResponseSetupSummary(InquiryProcessRequest inquiryProcessRequest)
	{
		ProcessResponse response = new ProcessResponse();
		InternalResponse internalResponse = null;

		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(DMPersistanceActionEnum.getDefaultName(),
					DMPersistanceActionEnum.GENERATE_FILE_CSV);
			context.putObjectToBeValidated(DMProcess.class.getSimpleName(),
					inquiryProcessRequest.getFirstProcess());
			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(),
					inquiryProcessRequest);

			if (getProcessValidationController().validate(context))
			{
				internalResponse = getProcessCSVBCL().generateFileCSVDemandResponseSetupSummary(inquiryProcessRequest);
			}

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(),
					true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_PROCESS_BCF_EXCEPTION_MSG);
		}

		return response;
	}

}
