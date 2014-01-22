package com.sensus.lc.medida.bcl.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sensus.common.csv.CSVColumn;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.lc.medida.bcl.IMedidaBCL;
import com.sensus.lc.medida.dac.IMedidaDAC;
import com.sensus.lc.medida.model.Medida;
import com.sensus.lc.medida.model.request.InquiryMedidaRequest;
import com.sensus.lc.medida.model.request.MedidaRequest;
import com.sensus.lc.process.bcl.IProcessBCL;

// TODO: Auto-generated Javadoc
/**
 * The Class MedidaBCLImpl.
 * 
 * @author Washington.
 */
public class MedidaBCLImpl implements IMedidaBCL
{

	/** The log. */
	private static transient Logger LOG = LoggerFactory.getLogger(MedidaBCLImpl.class);

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

	/** The Constant PROCESSING_MEDIDA. */
	private static final String PROCESSING_MEDIDA =
			"sensus.epm.scheduledeventvalidator.process.existingActionInProcessingMedida";

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

	/** The medida dac. */
	private IMedidaDAC medidaDAC;

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
	 * Gets the medida dac.
	 * 
	 * @return the medida dac
	 */
	public IMedidaDAC getMedidaDAC()
	{
		return medidaDAC;
	}

	/**
	 * Sets the medida dac.
	 * 
	 * @param medidaDAC the new medida dac
	 */
	public void setMedidaDAC(IMedidaDAC medidaDAC)
	{
		this.medidaDAC = medidaDAC;
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
	 * com.sensus.dm.common.medida.bcl.IMedidaBCL#deleteMedida(com.sensus.dm.common.medida.model.request.MedidaRequest)
	 */
	@Override
	public InternalResponse deleteMedida(MedidaRequest medidaRequest)
	{
		InternalResponse response = new InternalResponse();

		for (Medida medida : medidaRequest.getMedidas())
		{

			// Are there something running for this medida ?
			response = fetchMessageProcessing(medida);
			if (response.isInError())
			{
				return response;
			}

			// the medida still exists
			InternalResultsResponse<Medida> medidaResponse =
					fetchAllMedidas(new InquiryMedidaRequest(medida));
			if (medidaResponse.isInError())
			{
				return medidaResponse;
			}

			// deletes the medida
			response = getMedidaDAC().deleteMedida(new MedidaRequest(medida));
			if (response.isInError())
			{
				return response;
			}

			// insert process for del medida
			// InternalResultsResponse<DMProcess> processResponse =
			// insertMedidaProcess(medidaResponse.getFirstResult(), medidaRequest.getIsMonitored(),
			// "", ProcessStatusEnum.COMPLETED,
			// null, medidaRequest);
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
	 * com.sensus.dm.common.medida.bcl.IMedidaBCL#insertMedida(com.sensus.dm.common.medida.model.request.MedidaRequest)
	 */
	@Override
	public InternalResultsResponse<Medida> insertMedida(MedidaRequest medidaRequest)
	{

		InternalResultsResponse<Medida> medidaResponse = new InternalResultsResponse<Medida>();

		// insert medida
		medidaResponse = getMedidaDAC().insertMedida(medidaRequest);
		if (medidaResponse.isInError())
		{
			return medidaResponse;
		}

		// insert process for medida creation

		// InternalResultsResponse<DMProcess> processResponse =
		// insertMedidaProcess(medidaRequest.getFirstMedida(), false,
		// "", ProcessStatusEnum.COMPLETED,
		// null, medidaRequest);
		//
		// if (processResponse.isInError())
		// {
		// medidaResponse.setStatus(processResponse.getStatus());
		// medidaResponse.addMessages(processResponse.getMessageInfoList());
		// return medidaResponse;
		// }
		//
		// medidaRequest.setProcessId(processResponse.getFirstResult().getId());

		return medidaResponse;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.medida.bcl.IMedidaBCL#updateMedida(com.sensus.dm.common.medida.model.request.MedidaRequest)
	 */
	@Override
	public InternalResultsResponse<Medida> updateMedida(MedidaRequest medidaRequest)
	{
		InternalResultsResponse<Medida> medidaResponse = new InternalResultsResponse<Medida>();

		// inserts the medida
		medidaResponse = getMedidaDAC().updateMedida(medidaRequest);
		if (medidaResponse.isInError())
		{
			return medidaResponse;
		}

		// insert process for edit medida
		// InternalResultsResponse<DMProcess> processResponse =
		// insertMedidaProcess(medidaRequest.getFirstMedida(), false,
		// "", ProcessStatusEnum.COMPLETED, null, medidaRequest);
		//
		// if (processResponse.isInError())
		// {
		// medidaResponse.setStatus(processResponse.getStatus());
		// medidaResponse.addMessages(processResponse.getMessageInfoList());
		// return medidaResponse;
		// }

		return medidaResponse;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.medida.bcl.IMedidaBCL#fetchAllMedidas(com.sensus.dm.common.medida.model.request.
	 * InquiryMedidaRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<Medida> fetchAllMedidas(InquiryMedidaRequest inquiryMedidaRequest)
	{
		return getMedidaDAC().fetchAllMedidas(inquiryMedidaRequest);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.medida.bcl.IMedidaBCL#fetchMedidasByDevice(com.sensus.dm.common.device.model.request.
	 * DeviceRequest
	 * )
	 */
	/**
	 * Fetch medidas by id.
	 * 
	 * @param inquiryMedidaRequest the inquiry medida request
	 * @return the internal results response
	 */
	@Override
	public InternalResultsResponse<Medida> fetchMedidasById(InquiryMedidaRequest inquiryMedidaRequest)
	{
		return getMedidaDAC().fetchMedidasById(inquiryMedidaRequest);
	}

	/**
	 * Fetch message processing.
	 * 
	 * @param medida the medida
	 * @return the internal response
	 */
	private InternalResponse fetchMessageProcessing(Medida medida)
	{
		// List<Property> properties = new ArrayList<Property>();
		// properties.add(new Property(PropertyEnum.GROUP_ID.getValue(), medida.getCdmed().toString()));
		//
		// InternalResponse response = getProcessBCL().fetchCheckProcessing(
		// new ProcessRequest(new DMProcess(ProcessStatusEnum.IN_PROCESS, properties)));
		//
		// if (response.isInError())
		// {
		// response.addMessage(PROCESSING_MEDIDA,
		// Message.MessageSeverity.Error,
		// Message.MessageLevel.FieldValidation);
		//
		// }
		InternalResponse response = new InternalResponse();
		return response;

	}

	/**
	 * Insert medida process.
	 * 
	 * @param medida the medida
	 * @param isMonitored the is monitored
	 * @param actionType the action type
	 * @param processStatusEnum the process status enum
	 * @param processItems the process items
	 * @param tenantRequest the tenant request
	 * @return the internal results response
	 */
	// private InternalResultsResponse<DMProcess> insertMedidaProcess(Medida medida, Boolean isMonitored,
	// String actionType,
	// ProcessStatusEnum processStatusEnum,
	// List<ProcessItem> processItems, TenantRequest tenantRequest)
	// {
	//
	// List<Property> properties = new ArrayList<Property>();
	// properties.add(new Property(PropertyEnum.GROUP_ID.getValue(), medida.getCdmed()
	// .toString()));
	// properties.add(new Property(PropertyEnum.GROUP_NAME.getValue(), medida.getCreateUser()));
	//
	// // create a Process for medida
	// return getProcessBCL().insertProcess(
	// new ProcessRequest(DMUtil.generateProcess(isMonitored, true, null,
	// new ProcessType(actionType), processItems, processStatusEnum, properties), tenantRequest
	// .getUserContext(),
	// tenantRequest.getServiceTypeEnum(), tenantRequest.getTenant()));
	//
	// }

	@Override
	public InternalResultsResponse<Medida> fetchDevicesByName(MedidaRequest medidaRequest)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
