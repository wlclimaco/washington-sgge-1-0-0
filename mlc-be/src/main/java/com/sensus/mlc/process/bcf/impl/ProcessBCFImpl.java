package com.sensus.mlc.process.bcf.impl;

import static com.sensus.common.util.SensusInterfaceUtil.handleOperationStatusAndMessages;
import static com.sensus.mlc.base.model.MLCPersistanceActionEnum.getSlcActionName;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.util.SensusInterfaceUtil;
import com.sensus.common.validation.ValidationContext;
import com.sensus.common.validation.ValidationController;
import com.sensus.mlc.base.bcf.impl.AbstractBaseBCF;
import com.sensus.mlc.base.model.MLCPersistanceActionEnum;
import com.sensus.mlc.base.model.ObjectToBeValidatedKeyEnum;
import com.sensus.mlc.base.model.request.LightSelectionRequest;
import com.sensus.mlc.base.model.request.LightingControlRequest;
import com.sensus.mlc.process.bcf.IProcessBCF;
import com.sensus.mlc.process.bcl.IProcessBCL;
import com.sensus.mlc.process.model.Process;
import com.sensus.mlc.process.model.request.InquiryProcessRequest;
import com.sensus.mlc.process.model.request.ProcessRequest;
import com.sensus.mlc.process.model.response.InquiryProcessResponse;
import com.sensus.mlc.process.model.response.ProcessResponse;
import com.sensus.mlc.smartpoint.model.Light;
import com.sensus.mlc.smartpoint.model.request.LightRequest;
import com.sensus.mlc.tenant.model.Tenant;
import com.sensus.mlc.tenant.model.response.TenantResponse;

/**
 * The Class ProcessBCFImpl.
 */
public class ProcessBCFImpl extends AbstractBaseBCF implements IProcessBCF
{

	/** The Constant INQUIRY_PROCESS_REQUEST_NAME. */
	private static final String INQUIRY_PROCESS_REQUEST_NAME = InquiryProcessRequest.class.getSimpleName();

	/** The Constant LIGHT_REQUEST_NAME. */
	private static final String LIGHT_REQUEST_NAME = LightRequest.class.getSimpleName();

	/** The Constant PROCESS_REQUEST_NAME. */
	private static final String PROCESS_REQUEST_NAME = ProcessRequest.class.getSimpleName();

	/** The Constant DEFAULT_EXCEPTION_MSG. */
	private static final String DEFAULT_EXCEPTION_MSG = "sensus.mlc.processbcfimpl.defaultexception";

	/** The Constant SENSUS_MLC_PROCESSBCLIMPL_DEFAULTEXCEPTION. */
	private static final String SENSUS_MLC_PROCESSBCLIMPL_DEFAULTEXCEPTION =
			"sensus.mlc.processbclimpl.defaultexception";

	/** The Constant PROCESS_NAME. */
	private static final String PROCESS_NAME = Process.class.getSimpleName();

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(ProcessBCFImpl.class);

	/** The process bcl. */
	private IProcessBCL processBCL; // injected by Spring through setter

	/** The process validation controller. */
	private ValidationController processValidationController;

	/** The light validation controller. */
	private ValidationController lightValidationController;

	/** The csv file controller. */
	private ValidationController csvFileValidationController;

	/**
	 * Gets the csv file validation controller.
	 * 
	 * @return the csv file validation controller
	 */
	public ValidationController getCsvFileValidationController()
	{
		return csvFileValidationController;
	}

	/**
	 * Sets the csv file validation controller.
	 * 
	 * @param csvFileValidationController the new csv file validation controller
	 */
	public void setCsvFileValidationController(ValidationController csvFileValidationController)
	{
		this.csvFileValidationController = csvFileValidationController;
	}

	/**
	 * Gets the light validation controller.
	 * 
	 * @return the light validation controller
	 */
	public ValidationController getLightValidationController()
	{
		return lightValidationController;
	}

	/**
	 * Sets the light validation controller.
	 * 
	 * @param lightValidationController the new light validation controller
	 */
	public void setLightValidationController(ValidationController lightValidationController)
	{
		this.lightValidationController = lightValidationController;
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

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.process.bcf.IProcessBCF#fetchProcessById(com.sensus.mlc.process.model.request.ProcessRequest)
	 */
	@Override
	public ProcessResponse fetchProcessById(ProcessRequest processRequest)
	{
		ProcessResponse response = new ProcessResponse();
		InternalResultsResponse<Process> internalResponse = null;

		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), MLCPersistanceActionEnum.FETCH_BY_ID);
			context.putObjectToBeValidated(PROCESS_REQUEST_NAME, processRequest);
			context.putObjectToBeValidated(PROCESS_NAME, processRequest.getProcess());

			if (getLightingControlRequestValidationController().validate(context)
					&& getProcessValidationController().validate(context))
			{
				internalResponse = getProcessBCL().fetchProcessById(processRequest);
				response.setProcesses(internalResponse.getResultsList());
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.process.bcf.IProcessBCF#fetchProcessByTransactionId(com.sensus.mlc.process.model.request.
	 * ProcessRequest)
	 */
	@Override
	public ProcessResponse fetchProcessByTransactionId(ProcessRequest processRequest)
	{
		ProcessResponse response = new ProcessResponse();
		InternalResultsResponse<Process> internalResponse = null;

		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), MLCPersistanceActionEnum.FETCH_BY_RNI_ID);
			context.putObjectToBeValidated(PROCESS_REQUEST_NAME, processRequest);
			context.putObjectToBeValidated(PROCESS_NAME, processRequest.getProcess());

			if (getLightingControlRequestValidationController().validate(context)
					&& getProcessValidationController().validate(context))
			{
				internalResponse = getProcessBCL().fetchProcessByRniId(processRequest);
				response.setProcesses(internalResponse.getResultsList());
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.process.bcf.IProcessBCF#fetchMonitoredProcesses(com.sensus.mlc.process.model.request.ProcessRequest
	 * )
	 */
	@Override
	public ProcessResponse fetchMonitoredProcesses(ProcessRequest processRequest)
	{
		ProcessResponse response = new ProcessResponse();
		InternalResultsResponse<Process> internalResponse = null;

		try
		{
			ValidationContext context = new ValidationContext();
			context.putObjectToBeValidated(PROCESS_REQUEST_NAME, processRequest);

			if (getLightingControlRequestValidationController().validate(context))
			{
				internalResponse = getProcessBCL().fetchMonitoredProcesses(processRequest);
				response.setProcesses(internalResponse.getResultsList());
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.process.bcf.IProcessBCF#fetchProcesses(com.sensus.mlc.process.model.request.InquiryProcessRequest)
	 */
	@Override
	public InquiryProcessResponse fetchProcesses(InquiryProcessRequest processRequest)
	{
		InquiryProcessResponse response = new InquiryProcessResponse();
		InternalResultsResponse<Process> internalResponse = null;

		try
		{
			ValidationContext context = new ValidationContext();
			context.putObjectToBeValidated(INQUIRY_PROCESS_REQUEST_NAME, processRequest);

			if (getLightingControlRequestValidationController().validate(context)
					&& getLightingControlInquiryRequestValidationController().validate(context))
			{
				internalResponse = getProcessBCL().fetchProcesses(processRequest);
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.process.bcf.IProcessBCF#insertProcess(com.sensus.mlc.process.model.request.ProcessRequest)
	 */
	@Override
	public ProcessResponse insertProcess(ProcessRequest processRequest)
	{
		ProcessResponse response = new ProcessResponse();
		InternalResultsResponse<Process> internalResponse = null;

		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), MLCPersistanceActionEnum.INSERT);
			context.putObjectToBeValidated(PROCESS_REQUEST_NAME, processRequest);
			context.putObjectToBeValidated(PROCESS_NAME, processRequest.getProcess());

			if (getLightingControlRequestValidationController().validate(context)
					&& getProcessValidationController().validate(context))
			{
				internalResponse = getProcessBCL().insertProcess(processRequest);
				response.setProcesses(internalResponse.getResultsList());
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.process.bcf.IProcessBCF#unmonitorProcess(com.sensus.mlc.process.model.request.ProcessRequest)
	 */
	@Override
	public ProcessResponse unmonitorProcess(ProcessRequest processRequest)
	{
		ProcessResponse response = new ProcessResponse();
		InternalResponse internalResponse = null;

		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), MLCPersistanceActionEnum.UNMONITOR);
			context.putObjectToBeValidated(PROCESS_REQUEST_NAME, processRequest);
			context.putObjectToBeValidated(PROCESS_NAME, processRequest.getProcess());

			if (getLightingControlRequestValidationController().validate(context)
					&& getProcessValidationController().validate(context))
			{
				internalResponse = getProcessBCL().unmonitorProcess(processRequest);
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.process.bcf.IProcessBCF#updateProcess(com.sensus.mlc.process.model.request.ProcessRequest)
	 */
	@Override
	public ProcessResponse updateProcess(ProcessRequest processRequest)
	{
		ProcessResponse response = new ProcessResponse();
		InternalResponse internalResponse = null;

		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), MLCPersistanceActionEnum.UPDATE);
			context.putObjectToBeValidated(PROCESS_REQUEST_NAME, processRequest);
			context.putObjectToBeValidated(PROCESS_NAME, processRequest.getProcess());

			if (getLightingControlRequestValidationController().validate(context)
					&& getProcessValidationController().validate(context))
			{
				internalResponse = getProcessBCL().updateProcess(processRequest);
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.process.bcf.IProcessBCF#retryProcess(com.sensus.mlc.process.model.request.ProcessRequest)
	 */
	@Override
	public ProcessResponse retryProcess(ProcessRequest processRequest)
	{
		ProcessResponse response = new ProcessResponse();
		InternalResponse internalResponse = null;

		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), MLCPersistanceActionEnum.RETRY);
			context.putObjectToBeValidated(PROCESS_REQUEST_NAME, processRequest);
			context.putObjectToBeValidated(PROCESS_NAME, processRequest.getProcess());

			if (getLightingControlRequestValidationController().validate(context)
					&& getProcessValidationController().validate(context))
			{
				internalResponse = getProcessBCL().retryProcess(processRequest);
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.process.bcf.IProcessBCF#abortProcess(com.sensus.mlc.process.model.request.ProcessRequest)
	 */
	@Override
	public ProcessResponse abortProcess(ProcessRequest processRequest)
	{
		ProcessResponse response = new ProcessResponse();
		InternalResponse internalResponse = null;

		try
		{
			ValidationContext context = new ValidationContext();
			context.putObjectToBeValidated(PROCESS_REQUEST_NAME, processRequest);

			if (getLightingControlRequestValidationController().validate(context))
			{
				internalResponse = getProcessBCL().abortProcess(processRequest);
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG);
		}
		return response;
	}

	// CODE REVIEW - WHY TENANT IS HERE, SHOULD BE IN BASE AREA SINCE IT IS RELATED TO ALL AREAS.
	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.process.bcf.IProcessBCF#fetchTenantByRniCode(com.sensus.mlc.base.model.request.LightingControlRequest
	 * )
	 */
	@Override
	public TenantResponse fetchTenantByRniCode(LightingControlRequest request)
	{
		TenantResponse response = new TenantResponse();
		InternalResultsResponse<Tenant> internalResponse = null;

		try
		{
			ValidationContext context = new ValidationContext();
			context.putObjectToBeValidated(LightingControlRequest.class.getSimpleName(), request);

			if (getLightingControlRequestValidationController().validate(context))
			{
				internalResponse = getProcessBCL().fetchTenantByRniCode(request.getTenant().getRniCode());
				response.setTenant(internalResponse.getFirstResult());
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.process.bcf.IProcessBCF#fetchProcessByLight(com.sensus.mlc.smartpoint.model.request.LightRequest)
	 */
	@Override
	public ProcessResponse fetchProcessByLight(LightRequest lightRequest)
	{
		ProcessResponse response = new ProcessResponse();
		InternalResultsResponse<Process> internalResponse = null;

		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), MLCPersistanceActionEnum.FETCH_BY_LIGHT);
			context.putObjectToBeValidated(LIGHT_REQUEST_NAME, lightRequest);
			context.putObjectToBeValidated(Light.class.getSimpleName(), lightRequest.getFirstLight());

			if (getLightingControlRequestValidationController().validate(context)
					&& getLightValidationController().validate(context))
			{

				internalResponse = getProcessBCL().fetchProcessByLight(lightRequest);
				response.setProcesses(internalResponse.getResultsList());
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.process.bcf.IProcessBCF#fetchRniLinkStatus()
	 */
	@Override
	public ProcessResponse fetchRniLinkStatus(ProcessRequest processRequest)
	{
		ProcessResponse response = new ProcessResponse();
		InternalResultsResponse<Boolean> internalResponse = null;

		try
		{
			ValidationContext context = new ValidationContext();
			context.putObjectToBeValidated(PROCESS_REQUEST_NAME, processRequest);

			if (getLightingControlRequestValidationController().validate(context))
			{
				internalResponse = getProcessBCL().fetchRniLinkStatus(processRequest.getTenant());
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.process.bcf.IProcessBCF#updateCSVDownloaded(com.sensus.mlc.process.model.request.ProcessRequest)
	 */
	@Override
	public ProcessResponse updateCSVDownloaded(ProcessRequest processRequest)
	{
		ProcessResponse response = new ProcessResponse();
		InternalResponse internalResponse = null;

		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(getSlcActionName(), MLCPersistanceActionEnum.UPDATE_CSV_DOWNLOADED);
			context.putObjectToBeValidated(PROCESS_REQUEST_NAME, processRequest);
			context.putObjectToBeValidated(PROCESS_NAME, processRequest.getProcess());

			if (getLightingControlRequestValidationController().validate(context)
					&& getProcessValidationController().validate(context))
			{
				internalResponse = getProcessBCL().updateCSVDownloaded(processRequest);
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.process.bcf.IProcessBCF#generateSummaryFileCSV(com.sensus.mlc.process.model.request.ProcessRequest
	 * )
	 */
	@Override
	public ProcessResponse generateSummaryFileCSV(ProcessRequest processRequest)
	{
		ProcessResponse response = new ProcessResponse();

		try
		{
			ValidationContext context = new ValidationContext();
			context.putObjectToBeValidated(PROCESS_REQUEST_NAME, processRequest);

			if (getLightingControlRequestValidationController().validate(context))
			{
				response = getProcessBCL().generateSumaryFileCSV(processRequest);
			}

			if ((context != null) && (!context.getMessages().isEmpty()))
			{
				response.setOperationSuccess(false);
				response.addMessages(context.getMessages());
			}

		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.process.bcf.IProcessBCF#fetchCountMonitoredProcesses(com.sensus.mlc.process.model.request.
	 * ProcessRequest)
	 */
	@Override
	public ProcessResponse fetchCountMonitoredProcesses(ProcessRequest processRequest)
	{
		ProcessResponse response = new ProcessResponse();
		InternalResultsResponse<HashMap<String, Integer>> internalResponse = null;

		try
		{
			ValidationContext context = new ValidationContext();
			context.putObjectToBeValidated(PROCESS_REQUEST_NAME, processRequest);

			if (getLightingControlRequestValidationController().validate(context))
			{
				internalResponse = getProcessBCL().fetchCountMonitoredProcesses(processRequest);
				response.setCountMonitoredProcess(internalResponse.getFirstResult());
			}

			handleOperationStatusAndMessages(response, internalResponse, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_EXCEPTION_MSG);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.process.bcf.IProcessBCF#insertCSVProcess(com.sensus.mlc.base.model.request.LightSelectionRequest)
	 */
	@Override
	public ProcessResponse insertCSVProcess(LightSelectionRequest lightSelectionRequest)
	{
		ProcessResponse response = new ProcessResponse();
		try
		{
			ValidationContext context = new ValidationContext();
			context.putObjectToBeValidated(LightSelectionRequest.class.getSimpleName(), lightSelectionRequest);

			if (getLightingControlRequestValidationController().validate(context))
			{
				response = getProcessBCL().insertCSVProcess(lightSelectionRequest);
			}

			if ((context != null) && (!context.getMessages().isEmpty()))
			{
				response.setOperationSuccess(false);
				response.addMessages(context.getMessages());
			}
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, SENSUS_MLC_PROCESSBCLIMPL_DEFAULTEXCEPTION);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.process.bcf.IProcessBCF#generateFileCSV(com.sensus.mlc.process.model.request.InquiryProcessRequest
	 * )
	 */
	@Override
	public InquiryProcessResponse generateFileCSV(InquiryProcessRequest inquiryProcessRequest)
	{
		InquiryProcessResponse response = new InquiryProcessResponse();

		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(MLCPersistanceActionEnum.getSlcActionName(),
					MLCPersistanceActionEnum.GENERATE_FILE_CSV);
			context.putObjectToBeValidated(INQUIRY_PROCESS_REQUEST_NAME, inquiryProcessRequest);
			context.putObjectToBeValidated(ObjectToBeValidatedKeyEnum.CSV_FILE_NAME.getValue(),
					inquiryProcessRequest.getFileName());
			context.putObjectToBeValidated(ObjectToBeValidatedKeyEnum.PROCESS_ID.getValue(),
					inquiryProcessRequest.getProcessId());

			if (getLightingControlRequestValidationController().validate(context)
					&& getLightingControlInquiryRequestValidationController().validate(context)
					&& getProcessValidationController().validate(context)
					&& getCsvFileValidationController().validate(context))
			{
				response = getProcessBCL().generateFileCSV(inquiryProcessRequest);
			}

			handleOperationStatusAndMessages(response, null, context.getMessages(), true);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, SENSUS_MLC_PROCESSBCLIMPL_DEFAULTEXCEPTION);
		}
		return response;
	}

}
