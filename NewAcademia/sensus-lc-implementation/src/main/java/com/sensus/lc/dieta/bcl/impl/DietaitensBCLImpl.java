package com.sensus.lc.dieta.bcl.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sensus.common.csv.CSVColumn;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.lc.dieta.bcl.IDietaitensBCL;
import com.sensus.lc.dieta.dac.IDietaitensDAC;
import com.sensus.lc.dieta.model.Dietaitens;
import com.sensus.lc.dieta.model.request.DietaitensRequest;
import com.sensus.lc.dieta.model.request.InquiryDietaitensRequest;
import com.sensus.lc.process.bcl.IProcessBCL;

// TODO: Auto-generated Javadoc
/**
 * The Class DietaitensBCLImpl.
 * 
 * @author Washington.
 */
public class DietaitensBCLImpl implements IDietaitensBCL
{

	/** The log. */
	private static transient Logger LOG = LoggerFactory.getLogger(DietaitensBCLImpl.class);

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

	/** The Constant PROCESSING_DIETAITENS. */
	private static final String PROCESSING_DIETAITENS =
			"sensus.epm.scheduledeventvalidator.process.existingActionInProcessingDietaitens";

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

	/** The dietaitens dac. */
	private IDietaitensDAC dietaitensDAC;

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
	 * Gets the dietaitens dac.
	 * 
	 * @return the dietaitens dac
	 */
	public IDietaitensDAC getDietaitensDAC()
	{
		return dietaitensDAC;
	}

	/**
	 * Sets the dietaitens dac.
	 * 
	 * @param dietaitensDAC the new dietaitens dac
	 */
	public void setDietaitensDAC(IDietaitensDAC dietaitensDAC)
	{
		this.dietaitensDAC = dietaitensDAC;
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
	 * @see
	 * com.sensus.dm.common.dietaitens.bcl.IDietaitensBCL#deleteDietaitens(com.sensus.dm.common.dietaitens.model.request
	 * .DietaitensRequest)
	 */
	@Override
	public InternalResponse deleteDietaitens(DietaitensRequest dietaitensRequest)
	{
		InternalResponse response = new InternalResponse();

		for (Dietaitens dietaitens : dietaitensRequest.getDietaitenss())
		{

			// Are there something running for this dietaitens ?
			response = fetchMessageProcessing(dietaitens);
			if (response.isInError())
			{
				return response;
			}

			// the dietaitens still exists
			InternalResultsResponse<Dietaitens> dietaitensResponse =
					fetchAllDietaitenss(new InquiryDietaitensRequest());
			if (dietaitensResponse.isInError())
			{
				return dietaitensResponse;
			}

			// deletes the dietaitens
			response = getDietaitensDAC().deleteDietaitens(new DietaitensRequest(dietaitens));
			if (response.isInError())
			{
				return response;
			}

			// insert process for del dietaitens
			// InternalResultsResponse<DMProcess> processResponse =
			// insertDietaitensProcess(dietaitensResponse.getFirstResult(), dietaitensRequest.getIsMonitored(),
			// "RemoveDietaitensAction.ACTION", ProcessStatusEnum.COMPLETED,
			// null, dietaitensRequest);
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
	 * @see
	 * com.sensus.dm.common.dietaitens.bcl.IDietaitensBCL#insertDietaitens(com.sensus.dm.common.dietaitens.model.request
	 * .DietaitensRequest)
	 */
	@Override
	public InternalResultsResponse<Dietaitens> insertDietaitens(DietaitensRequest dietaitensRequest)
	{

		InternalResultsResponse<Dietaitens> dietaitensResponse = new InternalResultsResponse<Dietaitens>();

		// insert dietaitens
		dietaitensResponse = getDietaitensDAC().insertDietaitens(dietaitensRequest);
		if (dietaitensResponse.isInError())
		{
			return dietaitensResponse;
		}

		// insert process for dietaitens creation

		// InternalResultsResponse<DMProcess> processResponse =
		// insertDietaitensProcess(dietaitensRequest.getFirstDietaitens(), false,
		// "CreateDietaitensAction.ACTION", ProcessStatusEnum.COMPLETED,
		// null, dietaitensRequest);
		//
		// if (processResponse.isInError())
		// {
		// dietaitensResponse.setStatus(processResponse.getStatus());
		// dietaitensResponse.addMessages(processResponse.getMessageInfoList());
		// return dietaitensResponse;
		// }
		//
		// dietaitensRequest.setProcessId(processResponse.getFirstResult().getId());

		return dietaitensResponse;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.dietaitens.bcl.IDietaitensBCL#updateDietaitens(com.sensus.dm.common.dietaitens.model.request
	 * .DietaitensRequest)
	 */
	@Override
	public InternalResultsResponse<Dietaitens> updateDietaitens(DietaitensRequest dietaitensRequest)
	{
		InternalResultsResponse<Dietaitens> dietaitensResponse = new InternalResultsResponse<Dietaitens>();

		// inserts the dietaitens
		dietaitensResponse = getDietaitensDAC().updateDietaitens(dietaitensRequest);
		if (dietaitensResponse.isInError())
		{
			return dietaitensResponse;
		}

		// insert process for edit dietaitens
		// InternalResultsResponse<DMProcess> processResponse =
		// insertDietaitensProcess(dietaitensRequest.getFirstDietaitens(), false,
		// "EditDietaitensAction.ACTION", ProcessStatusEnum.COMPLETED, null, dietaitensRequest);
		//
		// if (processResponse.isInError())
		// {
		// dietaitensResponse.setStatus(processResponse.getStatus());
		// dietaitensResponse.addMessages(processResponse.getMessageInfoList());
		// return dietaitensResponse;
		// }

		return dietaitensResponse;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.dietaitens.bcl.IDietaitensBCL#fetchAllDietaitenss(com.sensus.dm.common.dietaitens.model.request
	 * .InquiryDietaitensRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<Dietaitens> fetchAllDietaitenss(InquiryDietaitensRequest inquiryDietaitensRequest)
	{
		return getDietaitensDAC().fetchAllDietaitenss(inquiryDietaitensRequest);
	}

	/**
	 * Fetch message processing.
	 * 
	 * @param dietaitens the dietaitens
	 * @return the internal response
	 */
	private InternalResponse fetchMessageProcessing(Dietaitens dietaitens)
	{
		// List<Property> properties = new ArrayList<Property>();
		// properties.add(new Property(PropertyEnum.GROUP_ID.getValue(), dietaitens.getCddietitens().toString()));
		//
		// InternalResponse response = getProcessBCL().fetchCheckProcessing(
		// new ProcessRequest(new DMProcess(ProcessStatusEnum.IN_PROCESS, properties)));
		//
		// if (response.isInError())
		// {
		// response.addMessage(PROCESSING_DIETAITENS,
		// Message.MessageSeverity.Error,
		// Message.MessageLevel.FieldValidation);
		//
		// }

		InternalResponse response = new InternalResponse();
		return response;

	}

	/**
	 * Insert dietaitens process.
	 * 
	 * @param dietaitens the dietaitens
	 * @param isMonitored the is monitored
	 * @param actionType the action type
	 * @param processStatusEnum the process status enum
	 * @param processItems the process items
	 * @param tenantRequest the tenant request
	 * @return the internal results response
	 */
	// private InternalResultsResponse<DMProcess> insertDietaitensProcess(Dietaitens dietaitens, Boolean isMonitored,
	// String actionType,
	// ProcessStatusEnum processStatusEnum,
	// List<ProcessItem> processItems, TenantRequest tenantRequest)
	// {
	//
	// List<Property> properties = new ArrayList<Property>();
	// properties.add(new Property(PropertyEnum.GROUP_ID.getValue(), dietaitens.getCddietitens()
	// .toString()));
	// properties.add(new Property(PropertyEnum.GROUP_NAME.getValue(), dietaitens.getRefeicao()));
	//
	// // create a Process for dietaitens
	// return getProcessBCL().insertProcess(
	// new ProcessRequest(DMUtil.generateProcess(isMonitored, true, null,
	// new ProcessType(actionType), processItems, processStatusEnum, properties), tenantRequest
	// .getUserContext(),
	// tenantRequest.getServiceTypeEnum(), tenantRequest.getTenant()));
	//
	// }

	@Override
	public InternalResultsResponse<Dietaitens> fetchDietaitenssById(InquiryDietaitensRequest inquiryPaginationRequest)
	{
		return getDietaitensDAC().fetchDietaitensById(inquiryPaginationRequest);
	}

	@Override
	public InternalResultsResponse<Dietaitens> fetchDietaitensByName(InquiryDietaitensRequest dietaitensRequest)
	{
		return getDietaitensDAC().fetchDietaitensById(dietaitensRequest);
	}

}
