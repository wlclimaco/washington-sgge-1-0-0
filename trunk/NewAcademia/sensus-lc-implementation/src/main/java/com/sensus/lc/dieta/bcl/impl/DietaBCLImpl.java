package com.sensus.lc.dieta.bcl.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sensus.common.csv.CSVColumn;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.lc.dieta.bcl.IDietaBCL;
import com.sensus.lc.dieta.dac.IDietaDAC;
import com.sensus.lc.dieta.model.Dieta;
import com.sensus.lc.dieta.model.request.DietaRequest;
import com.sensus.lc.dieta.model.request.InquiryDietaRequest;
import com.sensus.lc.process.bcl.IProcessBCL;

// TODO: Auto-generated Javadoc
/**
 * The Class DietaBCLImpl.
 * 
 * @author Washington.
 */
public class DietaBCLImpl implements IDietaBCL
{

	/** The log. */
	private static transient Logger LOG = LoggerFactory.getLogger(DietaBCLImpl.class);

	/** The Constant FLEXNET_ID. */
	private static final String FLEXNET_ID = "flexnet_id";

	/** The Constant STR_CSV. */
	private static final String STR_CSV = ".csv";

	/** The Constant STR_HIFEN. */
	private static final String STR_HIFEN = "-";

	/** The Constant STR_COMMA. */
	private static final String STR_COMMA = ",";

	/** The Constant INT_0. */
	private static final Integer INT_0 = 0;

	/** The Constant PROCESS_NAME_SEPARATOR. */
	private static final String PROCESS_NAME_SEPARATOR = "|";

	/** The Constant PROCESSING_DIETA. */
	private static final String PROCESSING_DIETA =
			"sensus.epm.scheduledeventvalidator.process.existingActionInProcessingDieta";

	/** The Constant FILE_CREATION_FAILED. */
	private static final String FILE_CREATION_FAILED = "sensus.epm.processbclimpl.file.creation.failed";

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Spring injection points:

	/** The temp upload file path. */
	private String tempUploadFilePath;

	/** The dieta dac. */
	private IDietaDAC dietaDAC;

	/** The process bcl. */
	private IProcessBCL processBCL;

	/** The all possible columns. */
	private List<CSVColumn> allPossibleColumns;

	/**
	 * Gets the temp upload file path.
	 * 
	 * @return the temp upload file path
	 */
	public String getTempUploadFilePath()
	{
		return tempUploadFilePath;
	}

	/**
	 * Sets the temp upload file path.
	 * 
	 * @param tempUploadFilePath the new temp upload file path
	 */
	public void setTempUploadFilePath(String tempUploadFilePath)
	{
		this.tempUploadFilePath = tempUploadFilePath;
	}

	/**
	 * Gets the dieta dac.
	 * 
	 * @return the dieta dac
	 */
	public IDietaDAC getDietaDAC()
	{
		return dietaDAC;
	}

	/**
	 * Sets the dieta dac.
	 * 
	 * @param dietaDAC the new dieta dac
	 */
	public void setDietaDAC(IDietaDAC dietaDAC)
	{
		this.dietaDAC = dietaDAC;
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

	/**
	 * Gets the all possible columns.
	 * 
	 * @return the all possible columns
	 */
	public List<CSVColumn> getAllPossibleColumns()
	{
		return allPossibleColumns;
	}

	/**
	 * Sets the all possible columns.
	 * 
	 * @param allPossibleColumns the new all possible columns
	 */
	public void setAllPossibleColumns(List<CSVColumn> allPossibleColumns)
	{
		this.allPossibleColumns = allPossibleColumns;
	}

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Public interface:

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.dieta.bcl.IDietaBCL#deleteDieta(com.sensus.dm.common.dieta.model.request.DietaRequest)
	 */
	@Override
	public InternalResponse deleteDieta(DietaRequest dietaRequest)
	{
		InternalResponse response = new InternalResponse();

		for (Dieta dieta : dietaRequest.getDietas())
		{

			// Are there something running for this dieta ?
			response = fetchMessageProcessing(dieta);
			if (response.isInError())
			{
				return response;
			}

			// the dieta still exists
			InternalResultsResponse<Dieta> dietaResponse =
					fetchAllDietas(new InquiryDietaRequest(dieta));
			if (dietaResponse.isInError())
			{
				return dietaResponse;
			}

			// deletes the dieta
			response = getDietaDAC().deleteDieta(new DietaRequest(dieta));
			if (response.isInError())
			{
				return response;
			}

			// insert process for del dieta
			// InternalResultsResponse<DMProcess> processResponse =
			// insertDietaProcess(dietaResponse.getFirstResult(), dietaRequest.getIsMonitored(),
			// "RemoveDietaAction.ACTION", ProcessStatusEnum.COMPLETED,
			// null, dietaRequest);
			//
			// if (processResponse.isInError())
			// {
			// response.setStatus(processResponse.getStatus());
			// response.addMessages(processResponse.getMessageInfoList());
			// return response;
			// }

		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.dieta.bcl.IDietaBCL#insertDieta(com.sensus.dm.common.dieta.model.request.DietaRequest)
	 */
	@Override
	public InternalResultsResponse<Dieta> insertDieta(DietaRequest dietaRequest)
	{

		InternalResultsResponse<Dieta> dietaResponse = new InternalResultsResponse<Dieta>();

		// insert dieta
		dietaResponse = getDietaDAC().insertDieta(dietaRequest);
		if (dietaResponse.isInError())
		{
			return dietaResponse;
		}

		// insert process for dieta creation

		// InternalResultsResponse<DMProcess> processResponse =
		// insertDietaProcess(dietaRequest.getFirstDieta(), false,
		// "CreateDietaAction.ACTION", ProcessStatusEnum.COMPLETED,
		// null, dietaRequest);
		//
		// if (processResponse.isInError())
		// {
		// dietaResponse.setStatus(processResponse.getStatus());
		// dietaResponse.addMessages(processResponse.getMessageInfoList());
		// return dietaResponse;
		// }
		//
		// dietaRequest.setProcessId(processResponse.getFirstResult().getId());

		return dietaResponse;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.dieta.bcl.IDietaBCL#updateDieta(com.sensus.dm.common.dieta.model.request.DietaRequest)
	 */
	@Override
	public InternalResultsResponse<Dieta> updateDieta(DietaRequest dietaRequest)
	{
		InternalResultsResponse<Dieta> dietaResponse = new InternalResultsResponse<Dieta>();

		// inserts the dieta
		dietaResponse = getDietaDAC().updateDieta(dietaRequest);
		if (dietaResponse.isInError())
		{
			return dietaResponse;
		}

		// insert process for edit dieta
		// InternalResultsResponse<DMProcess> processResponse =
		// insertDietaProcess(dietaRequest.getFirstDieta(), false,
		// "EditDietaAction.ACTION", ProcessStatusEnum.COMPLETED, null, dietaRequest);
		//
		// if (processResponse.isInError())
		// {
		// dietaResponse.setStatus(processResponse.getStatus());
		// dietaResponse.addMessages(processResponse.getMessageInfoList());
		// return dietaResponse;
		// }

		return dietaResponse;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.dieta.bcl.IDietaBCL#fetchAllDietas(com.sensus.dm.common.dieta.model.request.InquiryDietaRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<Dieta> fetchAllDietas(InquiryDietaRequest inquiryDietaRequest)
	{
		return getDietaDAC().fetchAllDietas(inquiryDietaRequest);
	}

	/**
	 * Fetch message processing.
	 * 
	 * @param dieta the dieta
	 * @return the internal response
	 */
	private InternalResponse fetchMessageProcessing(Dieta dieta)
	{
		// List<Property> properties = new ArrayList<Property>();
		// properties.add(new Property(PropertyEnum.GROUP_ID.getValue(), dieta.getCddiet().toString()));
		//
		// InternalResponse response = getProcessBCL().fetchCheckProcessing(
		// new ProcessRequest(new DMProcess(ProcessStatusEnum.IN_PROCESS, properties)));
		//
		// if (response.isInError())
		// {
		// response.addMessage(PROCESSING_DIETA,
		// Message.MessageSeverity.Error,
		// Message.MessageLevel.FieldValidation);
		//
		// }

		InternalResponse response = new InternalResponse();

		return response;

	}

	/**
	 * Insert dieta process.
	 * 
	 * @param dieta the dieta
	 * @param isMonitored the is monitored
	 * @param actionType the action type
	 * @param processStatusEnum the process status enum
	 * @param processItems the process items
	 * @param tenantRequest the tenant request
	 * @return the internal results response
	 */
	// private InternalResultsResponse<DMProcess> insertDietaProcess(Dieta dieta, Boolean isMonitored, String
	// actionType,
	// ProcessStatusEnum processStatusEnum,
	// List<ProcessItem> processItems, TenantRequest tenantRequest)
	// {
	//
	// List<Property> properties = new ArrayList<Property>();
	// properties.add(new Property(PropertyEnum.GROUP_ID.getValue(), dieta.getCddiet()
	// .toString()));
	// properties.add(new Property(PropertyEnum.GROUP_NAME.getValue(), dieta.getDsobj()));
	//
	// // create a Process for dieta
	// return getProcessBCL().insertProcess(
	// new ProcessRequest(DMUtil.generateProcess(isMonitored, true, null,
	// new ProcessType(actionType), processItems, processStatusEnum, properties), tenantRequest
	// .getUserContext(),
	// tenantRequest.getServiceTypeEnum(), tenantRequest.getTenant()));
	//
	// }

	@Override
	public InternalResultsResponse<Dieta> fetchDietasById(DietaRequest dietaRequest)
	{
		return getDietaDAC().fetchDevicesById(dietaRequest);
	}

	@Override
	public InternalResultsResponse<Dieta> fetchDietaByName(DietaRequest dietaRequest)
	{
		return getDietaDAC().fetchDevicesByName(dietaRequest);
	}

}
