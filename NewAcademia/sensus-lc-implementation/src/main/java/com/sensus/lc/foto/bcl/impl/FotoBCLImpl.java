package com.sensus.lc.foto.bcl.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.Property;

import com.sensus.common.csv.CSVColumn;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.lc.foto.bcl.IFotoBCL;
import com.sensus.lc.foto.dac.IFotoDAC;
import com.sensus.lc.foto.model.Foto;
import com.sensus.lc.foto.model.request.FotoRequest;
import com.sensus.lc.foto.model.request.InquiryFotoRequest;
import com.sensus.lc.process.bcl.IProcessBCL;

// TODO: Auto-generated Javadoc
/**
 * The Class FotoBCLImpl.
 * 
 * @author Washington.
 */
public class FotoBCLImpl implements IFotoBCL
{

	/** The log. */
	private static transient Logger LOG = LoggerFactory.getLogger(FotoBCLImpl.class);

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

	/** The Constant PROCESSING_FOTO. */
	private static final String PROCESSING_FOTO =
			"sensus.epm.scheduledeventvalidator.process.existingActionInProcessingFoto";

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

	/** The foto dac. */
	private IFotoDAC fotoDAC;

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
	 * Gets the foto dac.
	 * 
	 * @return the foto dac
	 */
	public IFotoDAC getFotoDAC()
	{
		return fotoDAC;
	}

	/**
	 * Sets the foto dac.
	 * 
	 * @param fotoDAC the new foto dac
	 */
	public void setFotoDAC(IFotoDAC fotoDAC)
	{
		this.fotoDAC = fotoDAC;
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
	 * @see com.sensus.dm.common.foto.bcl.IFotoBCL#deleteFoto(com.sensus.dm.common.foto.model.request.FotoRequest)
	 */
	@Override
	public InternalResponse deleteFoto(FotoRequest fotoRequest)
	{
		InternalResponse response = new InternalResponse();

		for (Foto foto : fotoRequest.getFotos())
		{

			// Are there something running for this foto ?
			response = fetchMessageProcessing(foto);
			if (response.isInError())
			{
				return response;
			}

			// the foto still exists
			InternalResultsResponse<Foto> fotoResponse =
					fetchAllFotos(new InquiryFotoRequest(foto));
			if (fotoResponse.isInError())
			{
				return fotoResponse;
			}

			// deletes the foto
			response = getFotoDAC().deleteFoto(new FotoRequest(foto));
			if (response.isInError())
			{
				return response;
			}

			// insert process for del foto
			// InternalResultsResponse<DMProcess> processResponse =
			// insertFotoProcess(fotoResponse.getFirstResult(), fotoRequest.getIsMonitored(),
			// "RemoveFotoAction.ACTION", ProcessStatusEnum.COMPLETED,
			// null, fotoRequest);
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
	 * @see com.sensus.dm.common.foto.bcl.IFotoBCL#insertFoto(com.sensus.dm.common.foto.model.request.FotoRequest)
	 */
	@Override
	public InternalResultsResponse<Foto> insertFoto(FotoRequest fotoRequest)
	{

		InternalResultsResponse<Foto> fotoResponse = new InternalResultsResponse<Foto>();

		// insert foto
		fotoResponse = getFotoDAC().insertFoto(fotoRequest);
		if (fotoResponse.isInError())
		{
			return fotoResponse;
		}

		// insert process for foto creation

		// InternalResultsResponse<DMProcess> processResponse =
		// insertFotoProcess(fotoRequest.getFirstFoto(), false,
		// "CreateFotoAction.ACTION", ProcessStatusEnum.COMPLETED,
		// null, fotoRequest);
		//
		// if (processResponse.isInError())
		// {
		// fotoResponse.setStatus(processResponse.getStatus());
		// fotoResponse.addMessages(processResponse.getMessageInfoList());
		// return fotoResponse;
		// }

		// fotoRequest.setProcessId(processResponse.getFirstResult().getId());

		return fotoResponse;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.foto.bcl.IFotoBCL#updateFoto(com.sensus.dm.common.foto.model.request.FotoRequest)
	 */
	@Override
	public InternalResultsResponse<Foto> updateFoto(FotoRequest fotoRequest)
	{
		InternalResultsResponse<Foto> fotoResponse = new InternalResultsResponse<Foto>();

		// inserts the foto
		fotoResponse = getFotoDAC().updateFoto(fotoRequest);
		if (fotoResponse.isInError())
		{
			return fotoResponse;
		}

		// insert process for edit foto
		// InternalResultsResponse<DMProcess> processResponse =
		// insertFotoProcess(fotoRequest.getFirstFoto(), false,
		// "EditFotoAction.ACTION", ProcessStatusEnum.COMPLETED, null, fotoRequest);
		//
		// if (processResponse.isInError())
		// {
		// fotoResponse.setStatus(processResponse.getStatus());
		// fotoResponse.addMessages(processResponse.getMessageInfoList());
		// return fotoResponse;
		// }

		return fotoResponse;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.foto.bcl.IFotoBCL#fetchAllFotos(com.sensus.dm.common.foto.model.request.InquiryFotoRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<Foto> fetchAllFotos(InquiryFotoRequest inquiryFotoRequest)
	{
		return getFotoDAC().fetchAllFotos(inquiryFotoRequest);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.foto.bcl.IFotoBCL#fetchFotosByDevice(com.sensus.dm.common.device.model.request.DeviceRequest
	 * )
	 */
	/**
	 * Fetch fotos by id.
	 * 
	 * @param inquiryFotoRequest the inquiry foto request
	 * @return the internal results response
	 */
	// @Override
	// public InternalResultsResponse<Foto> fetchFotosById(InquiryFotoRequest inquiryFotoRequest)
	// {
	// return getFotoDAC().fetchFotosById(inquiryFotoRequest);
	//
	//
	// /**
	// * Fetch fotos by name.
	// *
	// * @param inquiryFotoRequest the inquiry foto request
	// * @return the internal results response
	// */
	// @Override
	// public InternalResultsResponse<Foto> fetchFotosByName(InquiryFotoRequest inquiryFotoRequest)
	// {
	// return getFotoDAC().fetchFotosByName(inquiryFotoRequest);
	// }

	/**
	 * Fetch message processing.
	 * 
	 * @param foto the foto
	 * @return the internal response
	 */
	private InternalResponse fetchMessageProcessing(Foto foto)
	{
		new ArrayList<Property>();

		// if (response.isInError())
		// {
		// response.addMessage(PROCESSING_FOTO,
		// Message.MessageSeverity.Error,
		// Message.MessageLevel.FieldValidation);
		//
		// }
		InternalResponse response = new InternalResponse();
		return response;

	}

	/**
	 * Insert foto process.
	 * 
	 * @param foto the foto
	 * @param isMonitored the is monitored
	 * @param actionType the action type
	 * @param processStatusEnum the process status enum
	 * @param processItems the process items
	 * @param tenantRequest the tenant request
	 * @return the internal results response
	 */
	// private InternalResultsResponse<DMProcess> insertFotoProcess(Foto foto, Boolean isMonitored, String actionType,
	// ProcessStatusEnum processStatusEnum,
	// List<ProcessItem> processItems, TenantRequest tenantRequest)
	// {
	//
	// List<Property> properties = new ArrayList<Property>();
	// properties.add(new Property(PropertyEnum.GROUP_ID.getValue(), foto.getCdfoto()
	// .toString()));
	// properties.add(new Property(PropertyEnum.GROUP_NAME.getValue(), foto.getFoto()));
	//
	// // create a Process for foto
	// return getProcessBCL().insertProcess(
	// new ProcessRequest(DMUtil.generateProcess(isMonitored, true, null,
	// new ProcessType(actionType), processItems, processStatusEnum, properties), tenantRequest
	// .getUserContext(),
	// tenantRequest.getServiceTypeEnum(), tenantRequest.getTenant()));
	//
	// }

	@Override
	public InternalResultsResponse<Foto> fetchFotosById(InquiryFotoRequest InquiryFotoRequest)
	{
		return getFotoDAC().fetchFotosById(InquiryFotoRequest);
	}

}
