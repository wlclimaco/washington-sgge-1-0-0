package com.sensus.lc.academia.bcl.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sensus.common.csv.CSVColumn;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.lc.academia.bcl.IAcademiaBCL;
import com.sensus.lc.academia.dac.IAcademiaDAC;
import com.sensus.lc.academia.model.Academia;
import com.sensus.lc.academia.model.request.AcademiaRequest;
import com.sensus.lc.academia.model.request.InquiryAcademiaRequest;
import com.sensus.lc.process.bcl.IProcessBCL;

// TODO: Auto-generated Javadoc
/**
 * The Class AcademiaBCLImpl.
 * 
 * @author Washington.
 */
public class AcademiaBCLImpl implements IAcademiaBCL
{

	/** The log. */
	/** The log. */
	private static transient Logger LOG = LoggerFactory.getLogger(AcademiaBCLImpl.class);
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

	/** The Constant PROCESSING_ACADEMIA. */
	private static final String PROCESSING_ACADEMIA =
			"sensus.epm.scheduledeventvalidator.process.existingActionInProcessingAcademia";

	/** The Constant FILE_CREATION_FAILED. */
	private static final String FILE_CREATION_FAILED = "sensus.epm.processbclimpl.file.creation.failed";

	// /** The Constant ACADEMIA_ELECTRIC_COLUMNS_TO_EXPORT. */
	// private static final String[] ACADEMIA_ELECTRIC_COLUMNS_TO_EXPORT = {CsvColumnEnum.NAME.getValue(),
	// CsvColumnEnum.DESCRIPTION.getValue(), CsvColumnEnum.DEVICE_TYPE.getValue(),
	// CsvColumnEnum.ACADEMIA_TYPE.getValue(),
	// CsvColumnEnum.DEVICE_COUNT.getValue(), CsvColumnEnum.DATE_ADDED.getValue()};
	//
	// /** The Constant ACADEMIA_COLUMNS_TO_EXPORT. */
	// private static final String[] ACADEMIA_COLUMNS_TO_EXPORT = {CsvColumnEnum.NAME.getValue(),
	// CsvColumnEnum.DESCRIPTION.getValue(), CsvColumnEnum.ACADEMIA_TYPE.getValue(),
	// CsvColumnEnum.DEVICE_COUNT.getValue(), CsvColumnEnum.DATE_ADDED.getValue()};

	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// -------------------------------------------------------------------------
	// Spring injection points:

	/** The temp upload file path. */
	private String tempUploadFilePath;

	/** The academia dac. */
	private IAcademiaDAC academiaDAC;

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
	 * Gets the academia dac.
	 * 
	 * @return the academia dac
	 */
	public IAcademiaDAC getAcademiaDAC()
	{
		return academiaDAC;
	}

	/**
	 * Sets the academia dac.
	 * 
	 * @param academiaDAC the new academia dac
	 */
	public void setAcademiaDAC(IAcademiaDAC academiaDAC)
	{
		this.academiaDAC = academiaDAC;
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
	 * @see com.sensus.dm.common.academia.bcl.IAcademiaBCL#deleteAcademia(com.sensus.dm.common.academia.model.request.
	 * AcademiaRequest)
	 */
	@Override
	public InternalResponse deleteAcademia(AcademiaRequest academiaRequest)
	{
		InternalResponse response = new InternalResponse();

		for (Integer integer : academiaRequest.getSelectionPaginationIds())
		{
			Academia academia = new Academia(integer);
			academiaRequest.addAcademia(academia);

		}

		for (Academia academia : academiaRequest.getAcademias())
		{

			// Are there something running for this academia ?
			response = fetchMessageProcessing(academia);
			if (response.isInError())
			{
				return response;
			}

			// deletes the academia
			response = getAcademiaDAC().deleteAcademia(new AcademiaRequest(academia));
			if (response.isInError())
			{
				return response;
			}

			// insert process for del academia
			// InternalResultsResponse<DMProcess> processResponse =
			// insertAcademiaProcess(academiaResponse.getFirstResult(), academiaRequest.getIsMonitored(),
			// "RemoveAcademiaAction.ACTION", ProcessStatusEnum.COMPLETED,
			// null, academiaRequest);

			// if (processResponse.isInError())
			// {
			// response.setStatus(processResponse.getStatus());
			// response.addMessages(processResponse.getMessageInfoList());
			return response;
			// }

		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.academia.bcl.IAcademiaBCL#insertAcademia(com.sensus.dm.common.academia.model.request.
	 * AcademiaRequest)
	 */
	@Override
	public InternalResultsResponse<Academia> insertAcademia(AcademiaRequest academiaRequest)
	{

		InternalResultsResponse<Academia> academiaResponse = getAcademiaDAC().insertAcademia(academiaRequest);

		if (ValidationUtil.isNull(academiaResponse))
		{
			academiaResponse.setStatus(academiaResponse.getStatus());
			academiaResponse.addMessages(academiaResponse.getMessageInfoList());
			return academiaResponse;
		}

		// insert academia
		academiaResponse = getAcademiaDAC().insertAcademia(academiaRequest);
		if (academiaResponse.isInError())
		{
			return academiaResponse;
		}

		// insert process for academia creation
		//
		// InternalResultsResponse<DMProcess> processResponse =
		// insertAcademiaProcess(academiaRequest.getFirstAcademia(), false,
		// "CreateAcademiaAction.ACTION", ProcessStatusEnum.COMPLETED,
		// null, academiaRequest);
		//
		// if (processResponse.isInError())
		// {
		// academiaResponse.setStatus(processResponse.getStatus());
		// academiaResponse.addMessages(processResponse.getMessageInfoList());
		// return academiaResponse;
		// }
		//
		// academiaRequest.setProcessId(processResponse.getFirstResult().getId());

		return academiaResponse;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.dm.common.academia.bcl.IAcademiaBCL#updateAcademia(com.sensus.dm.common.academia.model.request.
	 * AcademiaRequest)
	 */
	@Override
	public InternalResultsResponse<Academia> updateAcademia(AcademiaRequest academiaRequest)
	{
		InternalResultsResponse<Academia> academiaResponse = new InternalResultsResponse<Academia>();

		// inserts the academia
		academiaResponse = getAcademiaDAC().updateAcademia(academiaRequest);
		if (academiaResponse.isInError())
		{
			return academiaResponse;
		}

		// insert process for edit academia
		// InternalResultsResponse<DMProcess> processResponse =
		// insertAcademiaProcess(academiaRequest.getFirstAcademia(), false,
		// "EditAcademiaAction.ACTION", ProcessStatusEnum.COMPLETED, null, academiaRequest);
		//
		// if (processResponse.isInError())
		// {
		// academiaResponse.setStatus(processResponse.getStatus());
		// academiaResponse.addMessages(processResponse.getMessageInfoList());
		// return academiaResponse;
		// }

		return academiaResponse;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.academia.bcl.IAcademiaBCL#fetchAllAcademias(com.sensus.dm.common.academia.model.request.
	 * InquiryAcademiaRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<Academia> fetchAllAcademias(InquiryAcademiaRequest inquiryAcademiaRequest)
	{
		return getAcademiaDAC().fetchAllAcademias(inquiryAcademiaRequest);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.academia.bcl.IAcademiaBCL#fetchAcademiasByDevice(com.sensus.dm.common.device.model.request.
	 * DeviceRequest
	 * )
	 */
	/**
	 * Fetch academias by id.
	 * 
	 * @param inquiryAcademiaRequest the inquiry academia request
	 * @return the internal results response
	 */
	@Override
	public InternalResultsResponse<Academia> fetchAcademiasById(InquiryAcademiaRequest inquiryAcademiaRequest)
	{
		return getAcademiaDAC().fetchAcademiaById(inquiryAcademiaRequest);
	}

	/**
	 * Fetch message processing.
	 * 
	 * @param academia the academia
	 * @return the internal response
	 */
	private InternalResponse fetchMessageProcessing(Academia academia)
	{
		// List<Property> properties = new ArrayList<Property>();
		// properties.add(new Property(PropertyEnum.GROUP_ID.getValue(), academia.getCdacad().toString()));
		//
		InternalResponse response = new InternalResponse();
		//
		// if (response.isInError())
		// {
		// response.addMessage(PROCESSING_ACADEMIA,
		// Message.MessageSeverity.Error,
		// Message.MessageLevel.FieldValidation);
		//
		// }

		return response;

	}

	@Override
	public InternalResultsResponse<Academia> fetchAllAcademiaByUser(InquiryAcademiaRequest inquiryAcademiaRequest)
	{
		return getAcademiaDAC().fetchAllAcademiaByUser(inquiryAcademiaRequest);
	}

}
