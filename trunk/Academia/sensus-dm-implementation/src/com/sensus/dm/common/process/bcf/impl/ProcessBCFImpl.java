package com.sensus.dm.common.process.bcf.impl;

import java.util.HashMap;

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
import com.sensus.dm.common.process.bcf.IProcessBCF;
import com.sensus.dm.common.process.bcl.IProcessBCL;
import com.sensus.dm.common.process.model.DMProcess;
import com.sensus.dm.common.process.model.ProcessItem;
import com.sensus.dm.common.process.model.ProcessSearch;
import com.sensus.dm.common.process.model.request.InquiryProcessRequest;
import com.sensus.dm.common.process.model.request.ProcessRequest;
import com.sensus.dm.common.process.model.response.InquiryProcessResponse;
import com.sensus.dm.common.process.model.response.ProcessResponse;
import com.sensus.dm.common.tenant.model.request.TenantRequest;

/**
 * Process BCF implementation.
 * 
 * @author QAT Global.
 */
public class ProcessBCFImpl implements IProcessBCF
{
	// -------------------------------------------------------------------------
	// Logs.

	/** The log. */
	private static final Logger LOG = LoggerFactory.getLogger(ProcessBCFImpl.class);

	// -------------------------------------------------------------------------

	/** The Constant DEFAULT_PROCESS_BCF_EXCEPTION_MSG. */
	private static final String DEFAULT_PROCESS_BCF_EXCEPTION_MSG = "sensus.epm.processbcfimpl.defaultexception";

	/** The process bcl. */
	private IProcessBCL processBCL;

	/** The process validation controller. */
	private ValidationController processValidationController;

	/** The tenant request validation controller. */
	private ValidationController tenantRequestValidationController;

	/** The order by validation controller. */
	private ValidationController orderByValidationController;

	/** The inquiry request validation controller. */
	private ValidationController inquiryRequestValidationController;

	/** The range date validation controller. */
	private ValidationController rangeDateValidationController;

	/** The inquiry process request validation controller. */
	private ValidationController inquiryProcessRequestValidationController;

	/** The radio validation controller. */
	private ValidationController radioValidationController;

	/** The device validation controller. */
	private ValidationController deviceValidationController;

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

	/**
	 * Gets the inquiry request validation controller.
	 * 
	 * @return the inquiry request validation controller
	 */
	public ValidationController getInquiryRequestValidationController()
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
	 * Gets the inquiry process request validation controller.
	 * 
	 * @return the inquiry process request validation controller
	 */
	public ValidationController getInquiryProcessRequestValidationController()
	{
		return inquiryProcessRequestValidationController;
	}

	/**
	 * Sets the inquiry process request validation controller.
	 * 
	 * @param inquiryProcessRequestValidationController the new inquiry process request validation controller
	 */
	public void setInquiryProcessRequestValidationController(
			ValidationController inquiryProcessRequestValidationController)
	{
		this.inquiryProcessRequestValidationController = inquiryProcessRequestValidationController;
	}

	/**
	 * Gets the radio validation controller.
	 * 
	 * @return the radio validation controller
	 */
	public ValidationController getRadioValidationController()
	{
		return radioValidationController;
	}

	/**
	 * Sets the radio validation controller.
	 * 
	 * @param radioValidationController the new radio validation controller
	 */
	public void setRadioValidationController(ValidationController radioValidationController)
	{
		this.radioValidationController = radioValidationController;
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

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.process.bcf.IProcessBCF#fetchProcesses(com.sensus.dm.common.process.model.request.
	 * InquiryProcessRequest)
	 */
	@Override
	public InquiryProcessResponse fetchProcesses(InquiryProcessRequest inquiryProcessRequest)
	{
		InquiryProcessResponse response = new InquiryProcessResponse();

		try
		{
			InternalResultsResponse<DMProcess> internalResponse = null;

			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(DMPersistanceActionEnum.getDefaultName(),
					DMPersistanceActionEnum.FETCH_ALL_PROCESS);
			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(),
					inquiryProcessRequest);
			context.putObjectToBeValidated(SortExpression.class.getSimpleName(),
					inquiryProcessRequest.getSortExpressions());
			context.putObjectToBeValidated(DeviceManagerInquiryRequest.class.getSimpleName(),
					inquiryProcessRequest);

			if (getTenantRequestValidationController().validate(context)
					&& getInquiryRequestValidationController().validate(context))
			{
				internalResponse = getProcessBCL().fetchProcesses(inquiryProcessRequest);
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
	 * @see com.sensus.dm.common.process.bcf.IProcessBCF#fetchProcessById(com.sensus.dm.common.process.model.request.
	 * ProcessRequest)
	 */
	@Override
	public InquiryProcessResponse fetchProcessById(ProcessRequest processRequest)
	{
		InquiryProcessResponse response = new InquiryProcessResponse();

		try
		{
			InternalResultsResponse<DMProcess> internalResponse = null;

			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(DMPersistanceActionEnum.getDefaultName(),
					DMPersistanceActionEnum.FETCH_PROCESS_BY_ID);
			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(),
					processRequest);
			context.putObjectToBeValidated(DMProcess.class.getSimpleName(),
					processRequest.getFirstProcess());

			if (getProcessValidationController().validate(context))
			{
				internalResponse = getProcessBCL().fetchProcessById(processRequest);
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
	 * com.sensus.dm.common.process.bcf.IProcessBCF#updateProcess(com.sensus.dm.common.process.model.request.ProcessRequest
	 * )
	 */
	@Override
	public ProcessResponse updateProcess(ProcessRequest processRequest)
	{
		ProcessResponse response = new ProcessResponse();

		try
		{
			InternalResponse internalResponse = null;

			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(DMPersistanceActionEnum.getDefaultName(),
					DMPersistanceActionEnum.UPDATE);
			context.putObjectToBeValidated(DMProcess.class.getSimpleName(),
					processRequest.getFirstProcess());
			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(),
					processRequest);

			if (getProcessValidationController().validate(context))
			{
				internalResponse = getProcessBCL().updateProcess(processRequest);
			}

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(),
					false);
		}
		catch (Exception ex)
		{
			SensusInterfaceUtil.handleException(LOG, response, ex, DEFAULT_PROCESS_BCF_EXCEPTION_MSG);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.process.bcf.IProcessBCF#checkLinkStatus(com.sensus.dm.common.process.model.request.
	 * ProcessRequest)
	 */
	@Override
	public ProcessResponse checkLinkStatus(ProcessRequest processRequest)
	{
		ProcessResponse response = new ProcessResponse();

		try
		{
			InternalResultsResponse<Boolean> internalResultsResponse = getProcessBCL().checkLinkStatus(processRequest);

			response.setLinkStatus(internalResultsResponse.getFirstResult());

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResultsResponse, null);
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
	 * com.sensus.dm.common.process.bcf.IProcessBCF#fetchCountMonitoredProcesses(com.sensus.dm.common.process.model.
	 * request.
	 * ProcessRequest)
	 */
	@Override
	public ProcessResponse fetchCountMonitoredProcesses(ProcessRequest processRequest)
	{
		ProcessResponse response = new ProcessResponse();

		try
		{
			InternalResultsResponse<HashMap<String, Integer>> internalResultsResponse = null;

			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(DMPersistanceActionEnum.getDefaultName(),
					DMPersistanceActionEnum.FETCH_COUNT_MONITORED_PROCESSES);
			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(),
					processRequest);

			if (getTenantRequestValidationController().validate(context))
			{
				internalResultsResponse =
						getProcessBCL().fetchCountMonitoredProcesses(processRequest);
				response.setCountMonitoredProcess(internalResultsResponse.getFirstResult());
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
	 * com.sensus.dm.common.process.bcf.IProcessBCF#fetchProcessesToday(com.sensus.dm.common.process.model.request.
	 * InquiryProcessRequest
	 * )
	 */
	@Override
	public InquiryProcessResponse fetchTodayProcesses(InquiryProcessRequest inquiryProcessRequest)
	{
		InquiryProcessResponse response = new InquiryProcessResponse();

		try
		{
			InternalResultsResponse<DMProcess> internalResultsResponse = null;

			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(DMPersistanceActionEnum.getDefaultName(),
					DMPersistanceActionEnum.FETCH_TODAY_PROCESSES);
			context.putObjectToBeValidated(InquiryProcessRequest.class.getSimpleName(),
					inquiryProcessRequest);
			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(),
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
					internalResultsResponse = getProcessBCL().fetchTodayProcesses(inquiryProcessRequest);
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
	 * com.sensus.dm.common.process.bcf.IProcessBCF#fetchMonitoredProcess(com.sensus.dm.common.process.model.request.
	 * InquiryProcessRequest)
	 */
	@Override
	public InquiryProcessResponse fetchMonitoredProcess(InquiryProcessRequest inquiryProcessRequest)
	{
		InquiryProcessResponse response = new InquiryProcessResponse();

		try
		{
			InternalResultsResponse<DMProcess> internalResultsResponse = null;

			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(DMPersistanceActionEnum.getDefaultName(),
					DMPersistanceActionEnum.FETCH_MONITORED_PROCESS);
			context.putObjectToBeValidated(DMProcess.class.getSimpleName(),
					inquiryProcessRequest.getFirstProcess());
			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(),
					inquiryProcessRequest);
			context.putObjectToBeValidated(SortExpression.class.getSimpleName(),
					inquiryProcessRequest.getSortExpressions());

			if (getTenantRequestValidationController().validate(context)
					&& getOrderByValidationController().validate(context)
					&& getProcessValidationController().validate(context))
			{
				internalResultsResponse = getProcessBCL().fetchMonitoredProcess(inquiryProcessRequest);
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
	 * com.sensus.dm.common.process.bcf.IProcessBCF#fetchCommunicationSummary(com.sensus.dm.common.process.model.request.
	 * ProcessRequest
	 * )
	 */
	@Override
	public ProcessResponse fetchCommunicationSummary(ProcessRequest processRequest)
	{
		ProcessResponse response = new ProcessResponse();

		try
		{
			InternalResultsResponse<DMProcess> internalResultsResponse = null;

			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(DMPersistanceActionEnum.getDefaultName(),
					DMPersistanceActionEnum.FETCH_COMMUNICATION_SUMMARY);
			context.putObjectToBeValidated(DMProcess.class.getSimpleName(),
					processRequest.getFirstProcess());
			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(),
					processRequest);

			if (getProcessValidationController().validate(context))
			{
				internalResultsResponse = getProcessBCL().fetchCommunicationSummary(processRequest);
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
	 * com.sensus.dm.common.process.bcf.IProcessBCF#fetchImportHanDeviceSummary(com.sensus.dm.common.process.model.request
	 * .
	 * ProcessRequest)
	 */
	@Override
	public ProcessResponse fetchImportHanDeviceSummary(ProcessRequest processRequest)
	{
		ProcessResponse response = new ProcessResponse();
		InternalResultsResponse<DMProcess> internalResultsResponse = null;

		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(DMPersistanceActionEnum.getDefaultName(),
					DMPersistanceActionEnum.FETCH_IMPORT_HAN_SUMMARY);
			context.putObjectToBeValidated(DMProcess.class.getSimpleName(),
					processRequest.getFirstProcess());
			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(),
					processRequest);

			if (getProcessValidationController().validate(context))
			{
				internalResultsResponse = getProcessBCL().fetchImportHanDeviceSummary(processRequest);
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
	 * com.sensus.dm.common.process.bcf.IProcessBCF#fetchDemandReadPingSummary(com.sensus.dm.common.process.model.request
	 * .ProcessRequest)
	 */
	@Override
	public ProcessResponse fetchDemandReadPingSummary(ProcessRequest processRequest)
	{
		ProcessResponse response = new ProcessResponse();
		InternalResultsResponse<DMProcess> internalResultsResponse = null;

		try
		{
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(DMPersistanceActionEnum.getDefaultName(),
					DMPersistanceActionEnum.FETCH_DEMAND_READ_PING_SUMMARY);
			context.putObjectToBeValidated(DMProcess.class.getSimpleName(),
					processRequest.getFirstProcess());
			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(),
					processRequest);

			if (getProcessValidationController().validate(context))
			{
				internalResultsResponse = getProcessBCL().fetchDemandReadPingSummary(processRequest);
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
	 * com.sensus.dm.common.process.bcf.IProcessBCF#fetchProcessItemsBySchedule(com.sensus.dm.common.process.model.request
	 * .
	 * ProcessRequest)
	 */
	@Override
	public ProcessResponse fetchProcessItemsBySchedule(ProcessRequest processRequest)
	{
		ProcessResponse response = new ProcessResponse();

		try
		{
			InternalResultsResponse<DMProcess> internalResultsResponse = null;

			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(DMPersistanceActionEnum.getDefaultName(),
					DMPersistanceActionEnum.FETCH_PROCESS_ITEMS_BY_SCHEDULE);
			context.putObjectToBeValidated(DMProcess.class.getSimpleName(),
					processRequest.getFirstProcess());
			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(),
					processRequest);

			if (getProcessValidationController().validate(context))
			{
				internalResultsResponse = getProcessBCL().fetchProcessItemsBySchedule(processRequest);
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
	 * com.sensus.dm.common.process.bcf.IProcessBCF#fetchProcessItemsByProcessId(com.sensus.dm.common.process.model.
	 * request.
	 * ProcessRequest)
	 */
	@Override
	public ProcessResponse fetchProcessItemsByProcessId(ProcessRequest processRequest)
	{
		ProcessResponse response = new ProcessResponse();

		try
		{
			InternalResultsResponse<DMProcess> internalResultsResponse = null;

			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(DMPersistanceActionEnum.getDefaultName(),
					DMPersistanceActionEnum.FETCH_PROCESS_ITEMS_BY_PROCESS_ID);
			context.putObjectToBeValidated(DMProcess.class.getSimpleName(),
					processRequest.getFirstProcess());
			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(),
					processRequest);

			if (getProcessValidationController().validate(context))
			{
				internalResultsResponse = getProcessBCL().fetchProcessItemsByProcessId(processRequest);
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
	 * com.sensus.dm.common.process.bcf.IProcessBCF#fetchAllProcessItems(com.sensus.dm.common.process.model.request.
	 * ProcessRequest)
	 */
	@Override
	public ProcessResponse fetchAllProcessItems(ProcessRequest processRequest)
	{
		ProcessResponse response = new ProcessResponse();
		try
		{
			InternalResultsResponse<ProcessItem> internalResultsResponse =
					getProcessBCL().fetchAllProcessItems(processRequest);

			response.setProcessItems(internalResultsResponse.getResultsList());

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResultsResponse, false);
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
	 * com.sensus.dm.common.process.bcf.IProcessBCF#updateProcessItemsToExpire(com.sensus.dm.common.process.model.request
	 * .ProcessRequest)
	 */
	@Override
	public ProcessResponse updateProcessItemsToExpire(ProcessRequest processRequest)
	{
		ProcessResponse response = new ProcessResponse();
		try
		{
			InternalResponse internalResponse = null;
			ValidationContext context = new ValidationContext();
			context.getValidationArguments().put(DMPersistanceActionEnum.getDefaultName(),
					DMPersistanceActionEnum.EXPIRE_PROCESS_ITEMS);
			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(), processRequest);

			if (getTenantRequestValidationController().validate(context))
			{
				context.putObjectToBeValidated(DMProcess.class.getSimpleName(), processRequest.getFirstProcess());

				if (getProcessValidationController().validate(context))
				{
					internalResponse = getProcessBCL().updateProcessItemsToExpire(processRequest);
				}
			}

			SensusInterfaceUtil.handleOperationStatusAndMessages(response, internalResponse, context.getMessages(),
					false);
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
	 * com.sensus.dm.common.process.bcf.IProcessBCF#fetchRelays(com.sensus.dm.common.process.model.request.ProcessRequest
	 * )
	 */
	@Override
	public ProcessResponse fetchRelays(ProcessRequest processRequest)
	{
		ProcessResponse response = new ProcessResponse();
		try
		{
			InternalResultsResponse<ProcessItem> internalResultsResponse = null;
			ValidationContext context = new ValidationContext();

			context.getValidationArguments().put(DMPersistanceActionEnum.getDefaultName(),
					DMPersistanceActionEnum.FETCH_RELAY);
			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(), processRequest);
			context.putObjectToBeValidated(DMProcess.class.getSimpleName(),
					processRequest.getFirstProcess());

			if (getProcessValidationController().validate(context))
			{
				context.putObjectToBeValidated(Device.class.getSimpleName(), processRequest.getFirstProcess()
						.getFirstProcessItem().getDevice());

				if (getDeviceValidationController().validate(context))
				{
					internalResultsResponse = getProcessBCL().fetchRelays(processRequest);

					response.setProcessItems(internalResultsResponse.getResultsList());
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
	 * com.sensus.dm.common.process.bcf.IProcessBCF#fetchRelaysByProcessId(com.sensus.dm.common.process.model.request
	 * .ProcessRequest)
	 */
	@Override
	public ProcessResponse fetchRelaysByProcessId(ProcessRequest processRequest)
	{
		ProcessResponse response = new ProcessResponse();
		try
		{
			InternalResultsResponse<ProcessItem> internalResultsResponse = null;
			ValidationContext context = new ValidationContext();

			context.getValidationArguments().put(DMPersistanceActionEnum.getDefaultName(),
					DMPersistanceActionEnum.FETCH_RELAYS_BY_PROCESS_ID);
			context.putObjectToBeValidated(TenantRequest.class.getSimpleName(), processRequest);
			context.putObjectToBeValidated(DMProcess.class.getSimpleName(),
					processRequest.getFirstProcess());

			if (getProcessValidationController().validate(context))
			{
				internalResultsResponse = getProcessBCL().fetchRelaysByProcessId(processRequest);

				response.setProcessItems(internalResultsResponse.getResultsList());
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
}
