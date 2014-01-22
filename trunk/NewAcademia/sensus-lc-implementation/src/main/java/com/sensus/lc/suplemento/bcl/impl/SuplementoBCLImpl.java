package com.sensus.lc.suplemento.bcl.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.Property;

import com.sensus.common.csv.CSVColumn;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.lc.process.bcl.IProcessBCL;
import com.sensus.lc.suplemento.bcl.ISuplementoBCL;
import com.sensus.lc.suplemento.dac.ISuplementoDAC;
import com.sensus.lc.suplemento.model.Suplemento;
import com.sensus.lc.suplemento.model.request.InquirySuplementoRequest;
import com.sensus.lc.suplemento.model.request.SuplementoRequest;

// TODO: Auto-generated Javadoc
/**
 * The Class SuplementoBCLImpl.
 * 
 * @author Washington.
 */
public class SuplementoBCLImpl implements ISuplementoBCL
{

	/** The log. */
	private static transient Logger LOG = LoggerFactory.getLogger(SuplementoBCLImpl.class);

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

	/** The Constant PROCESSING_SUPLEMENTO. */
	private static final String PROCESSING_SUPLEMENTO =
			"sensus.epm.scheduledeventvalidator.process.existingActionInProcessingSuplemento";

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

	/** The suplemento dac. */
	private ISuplementoDAC suplementoDAC;

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
	 * Gets the suplemento dac.
	 * 
	 * @return the suplemento dac
	 */
	public ISuplementoDAC getSuplementoDAC()
	{
		return suplementoDAC;
	}

	/**
	 * Sets the suplemento dac.
	 * 
	 * @param suplementoDAC the new suplemento dac
	 */
	public void setSuplementoDAC(ISuplementoDAC suplementoDAC)
	{
		this.suplementoDAC = suplementoDAC;
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
	 * com.sensus.dm.common.suplemento.bcl.ISuplementoBCL#deleteSuplemento(com.sensus.dm.common.suplemento.model.request
	 * .SuplementoRequest)
	 */
	@Override
	public InternalResponse deleteSuplemento(SuplementoRequest suplementoRequest)
	{
		InternalResponse response = new InternalResponse();

		for (Suplemento suplemento : suplementoRequest.getSuplementos())
		{

			// Are there something running for this suplemento ?
			response = fetchMessageProcessing(suplemento);
			if (response.isInError())
			{
				return response;
			}

			// the suplemento still exists
			InternalResultsResponse<Suplemento> suplementoResponse =
					fetchAllSuplementos(new InquirySuplementoRequest(suplemento));
			if (suplementoResponse.isInError())
			{
				return suplementoResponse;
			}

			// deletes the suplemento
			response = getSuplementoDAC().deleteSuplemento(new SuplementoRequest(suplemento));
			if (response.isInError())
			{
				return response;
			}

			// insert process for del suplemento
			// InternalResultsResponse<DMProcess> processResponse =
			// insertSuplementoProcess(suplementoResponse.getFirstResult(), suplementoRequest.getIsMonitored(),
			// "", ProcessStatusEnum.COMPLETED,
			// null, suplementoRequest);
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
	 * com.sensus.dm.common.suplemento.bcl.ISuplementoBCL#insertSuplemento(com.sensus.dm.common.suplemento.model.request
	 * .SuplementoRequest)
	 */
	@Override
	public InternalResultsResponse<Suplemento> insertSuplemento(SuplementoRequest suplementoRequest)
	{

		InternalResultsResponse<Suplemento> suplementoResponse = new InternalResultsResponse<Suplemento>();

		// insert suplemento
		suplementoResponse = getSuplementoDAC().insertSuplemento(suplementoRequest);
		if (suplementoResponse.isInError())
		{
			return suplementoResponse;
		}

		// insert process for suplemento creation

		// InternalResultsResponse<DMProcess> processResponse =
		// insertSuplementoProcess(suplementoRequest.getFirstSuplemento(), false,
		// "", ProcessStatusEnum.COMPLETED,
		// null, suplementoRequest);
		//
		// if (processResponse.isInError())
		// {
		// suplementoResponse.setStatus(processResponse.getStatus());
		// suplementoResponse.addMessages(processResponse.getMessageInfoList());
		// return suplementoResponse;
		// }
		//
		// suplementoRequest.setProcessId(processResponse.getFirstResult().getId());

		return suplementoResponse;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.suplemento.bcl.ISuplementoBCL#updateSuplemento(com.sensus.dm.common.suplemento.model.request
	 * .SuplementoRequest)
	 */
	@Override
	public InternalResultsResponse<Suplemento> updateSuplemento(SuplementoRequest suplementoRequest)
	{
		InternalResultsResponse<Suplemento> suplementoResponse = new InternalResultsResponse<Suplemento>();

		// inserts the suplemento
		suplementoResponse = getSuplementoDAC().updateSuplemento(suplementoRequest);
		if (suplementoResponse.isInError())
		{
			return suplementoResponse;
		}

		// insert process for edit suplemento
		// InternalResultsResponse<DMProcess> processResponse =
		// insertSuplementoProcess(suplementoRequest.getFirstSuplemento(), false,
		// "", ProcessStatusEnum.COMPLETED, null, suplementoRequest);
		//
		// if (processResponse.isInError())
		// {
		// suplementoResponse.setStatus(processResponse.getStatus());
		// suplementoResponse.addMessages(processResponse.getMessageInfoList());
		// return suplementoResponse;
		// }

		return suplementoResponse;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.suplemento.bcl.ISuplementoBCL#fetchAllSuplementos(com.sensus.dm.common.suplemento.model.request
	 * .InquirySuplementoRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<Suplemento> fetchAllSuplementos(InquirySuplementoRequest inquirySuplementoRequest)
	{
		return getSuplementoDAC().fetchAllSuplementos(inquirySuplementoRequest);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.suplemento.bcl.ISuplementoBCL#fetchSuplementosByDevice(com.sensus.dm.common.device.model.request
	 * .DeviceRequest
	 * )
	 */
	/**
	 * Fetch suplementos by id.
	 * 
	 * @param inquirySuplementoRequest the inquiry suplemento request
	 * @return the internal results response
	 */
	@Override
	public InternalResultsResponse<Suplemento> fetchSuplementosById(InquirySuplementoRequest inquirySuplementoRequest)
	{
		return getSuplementoDAC().fetchSuplementosById(inquirySuplementoRequest);

	}

	/**
	 * Fetch message processing.
	 * 
	 * @param suplemento the suplemento
	 * @return the internal response
	 */
	private InternalResponse fetchMessageProcessing(Suplemento suplemento)
	{
		new ArrayList<Property>();
		// properties.add(new Property(PropertyEnum.GROUP_ID.getValue(), suplemento.getCdsuple().toString()));
		//
		// InternalResponse response = getProcessBCL().fetchCheckProcessing(
		// new ProcessRequest(new DMProcess(ProcessStatusEnum.IN_PROCESS, properties)));
		//
		// if (response.isInError())
		// {
		// response.addMessage(PROCESSING_SUPLEMENTO,
		// Message.MessageSeverity.Error,
		// Message.MessageLevel.FieldValidation);
		//
		// }
		InternalResponse response = new InternalResponse();
		return response;

	}

	/**
	 * Insert suplemento process.
	 * 
	 * @param suplemento the suplemento
	 * @param isMonitored the is monitored
	 * @param actionType the action type
	 * @param processStatusEnum the process status enum
	 * @param processItems the process items
	 * @param tenantRequest the tenant request
	 * @return the internal results response
	 */
	// private InternalResultsResponse<DMProcess> insertSuplementoProcess(Suplemento suplemento, Boolean isMonitored,
	// String actionType,
	// ProcessStatusEnum processStatusEnum,
	// List<ProcessItem> processItems, TenantRequest tenantRequest)
	// {
	//
	// List<Property> properties = new ArrayList<Property>();
	// properties.add(new Property(PropertyEnum.GROUP_ID.getValue(), suplemento.getCdsuple()
	// .toString()));
	// properties.add(new Property(PropertyEnum.GROUP_NAME.getValue(), suplemento.getSupleme()));
	//
	// // create a Process for suplemento
	// return getProcessBCL().insertProcess(
	// new ProcessRequest(DMUtil.generateProcess(isMonitored, true, null,
	// new ProcessType(actionType), processItems, processStatusEnum, properties), tenantRequest
	// .getUserContext(),
	// tenantRequest.getServiceTypeEnum(), tenantRequest.getTenant()));
	//
	// }

}
