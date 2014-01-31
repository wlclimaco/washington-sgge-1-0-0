package com.sensus.lc.grupomuscular.bcl.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sensus.common.csv.CSVColumn;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.lc.grupomuscular.bcl.IGrupomuscularBCL;
import com.sensus.lc.grupomuscular.dac.IGrupomuscularDAC;
import com.sensus.lc.grupomuscular.model.Grupomuscular;
import com.sensus.lc.grupomuscular.model.request.GrupomuscularRequest;
import com.sensus.lc.grupomuscular.model.request.InquiryGrupomuscularRequest;
import com.sensus.lc.process.bcl.IProcessBCL;

// TODO: Auto-generated Javadoc
/**
 * The Class GrupomuscularBCLImpl.
 * 
 * @author Washington.
 */
public class GrupomuscularBCLImpl implements IGrupomuscularBCL
{

	/** The log. */
	/** The log. */
	private static transient Logger LOG = LoggerFactory.getLogger(GrupomuscularBCLImpl.class);
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
			"sensus.epm.scheduledeventvalidator.process.existingActionInProcessingGrupomuscular";

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

	/** The grupomuscular dac. */
	private IGrupomuscularDAC grupomuscularDAC;

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
	 * Gets the grupomuscular dac.
	 * 
	 * @return the grupomuscular dac
	 */
	public IGrupomuscularDAC getGrupomuscularDAC()
	{
		return grupomuscularDAC;
	}

	/**
	 * Sets the grupomuscular dac.
	 * 
	 * @param grupomuscularDAC the new grupomuscular dac
	 */
	public void setGrupomuscularDAC(IGrupomuscularDAC grupomuscularDAC)
	{
		this.grupomuscularDAC = grupomuscularDAC;
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
	 * com.sensus.dm.common.grupomuscular.bcl.IGrupomuscularBCL#deleteGrupomuscular(com.sensus.dm.common.grupomuscular.model
	 * .request
	 * .
	 * GrupomuscularRequest)
	 */
	@Override
	public InternalResponse deleteGrupomuscular(GrupomuscularRequest grupomuscularRequest)
	{
		InternalResponse response = new InternalResponse();

		for (Integer integer : grupomuscularRequest.getSelectionPaginationIds())
		{
			Grupomuscular grupomuscular = new Grupomuscular(integer);
			grupomuscularRequest.addGrupomuscular(grupomuscular);

		}

		for (Grupomuscular grupomuscular : grupomuscularRequest.getGrupomusculars())
		{

			// Are there something running for this grupomuscular ?
			response = fetchMessageProcessing(grupomuscular);
			if (response.isInError())
			{
				return response;
			}

			// deletes the grupomuscular
			response = getGrupomuscularDAC().deleteGrupomuscular(new GrupomuscularRequest(grupomuscular));
			if (response.isInError())
			{
				return response;
			}

			// insert process for del grupomuscular
			// InternalResultsResponse<DMProcess> processResponse =
			// insertGrupomuscularProcess(grupomuscularResponse.getFirstResult(), grupomuscularRequest.getIsMonitored(),
			// "RemoveGrupomuscularAction.ACTION", ProcessStatusEnum.COMPLETED,
			// null, grupomuscularRequest);

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
	 * @see
	 * com.sensus.dm.common.grupomuscular.bcl.IGrupomuscularBCL#insertGrupomuscular(com.sensus.dm.common.grupomuscular.model
	 * .request
	 * .
	 * GrupomuscularRequest)
	 */
	@Override
	public InternalResultsResponse<Grupomuscular> insertGrupomuscular(GrupomuscularRequest grupomuscularRequest)
	{

		InternalResultsResponse<Grupomuscular> grupomuscularResponse =
				getGrupomuscularDAC().insertGrupomuscular(grupomuscularRequest);

		if (ValidationUtil.isNull(grupomuscularResponse))
		{
			grupomuscularResponse.setStatus(grupomuscularResponse.getStatus());
			grupomuscularResponse.addMessages(grupomuscularResponse.getMessageInfoList());
			return grupomuscularResponse;
		}

		// insert grupomuscular
		grupomuscularResponse = getGrupomuscularDAC().insertGrupomuscular(grupomuscularRequest);
		if (grupomuscularResponse.isInError())
		{
			return grupomuscularResponse;
		}

		// insert process for grupomuscular creation
		//
		// InternalResultsResponse<DMProcess> processResponse =
		// insertGrupomuscularProcess(grupomuscularRequest.getFirstGrupomuscular(), false,
		// "CreateGrupomuscularAction.ACTION", ProcessStatusEnum.COMPLETED,
		// null, grupomuscularRequest);
		//
		// if (processResponse.isInError())
		// {
		// grupomuscularResponse.setStatus(processResponse.getStatus());
		// grupomuscularResponse.addMessages(processResponse.getMessageInfoList());
		// return grupomuscularResponse;
		// }
		//
		// grupomuscularRequest.setProcessId(processResponse.getFirstResult().getId());

		return grupomuscularResponse;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.grupomuscular.bcl.IGrupomuscularBCL#updateGrupomuscular(com.sensus.dm.common.grupomuscular.model
	 * .request
	 * .
	 * GrupomuscularRequest)
	 */
	@Override
	public InternalResultsResponse<Grupomuscular> updateGrupomuscular(GrupomuscularRequest grupomuscularRequest)
	{
		InternalResultsResponse<Grupomuscular> grupomuscularResponse = new InternalResultsResponse<Grupomuscular>();

		// inserts the grupomuscular
		grupomuscularResponse = getGrupomuscularDAC().updateGrupomuscular(grupomuscularRequest);
		if (grupomuscularResponse.isInError())
		{
			return grupomuscularResponse;
		}

		// insert process for edit grupomuscular
		// InternalResultsResponse<DMProcess> processResponse =
		// insertGrupomuscularProcess(grupomuscularRequest.getFirstGrupomuscular(), false,
		// "EditGrupomuscularAction.ACTION", ProcessStatusEnum.COMPLETED, null, grupomuscularRequest);
		//
		// if (processResponse.isInError())
		// {
		// grupomuscularResponse.setStatus(processResponse.getStatus());
		// grupomuscularResponse.addMessages(processResponse.getMessageInfoList());
		// return grupomuscularResponse;
		// }

		return grupomuscularResponse;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.grupomuscular.bcl.IGrupomuscularBCL#fetchAllGrupomusculars(com.sensus.dm.common.grupomuscular
	 * .model.
	 * request.
	 * InquiryGrupomuscularRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<Grupomuscular> fetchAllGrupomusculars(
			InquiryGrupomuscularRequest inquiryGrupomuscularRequest)
	{
		return getGrupomuscularDAC().fetchAllGrupomusculars(inquiryGrupomuscularRequest);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.grupomuscular.bcl.IGrupomuscularBCL#fetchGrupomuscularsByDevice(com.sensus.dm.common.device.
	 * model.
	 * request.
	 * DeviceRequest
	 * )
	 */
	/**
	 * Fetch grupomusculars by id.
	 * 
	 * @param inquiryGrupomuscularRequest the inquiry grupomuscular request
	 * @return the internal results response
	 */
	@Override
	public InternalResultsResponse<Grupomuscular> fetchGrupomuscularsById(
			InquiryGrupomuscularRequest inquiryGrupomuscularRequest)
	{
		return getGrupomuscularDAC().fetchGrupomuscularById(inquiryGrupomuscularRequest);
	}

	/**
	 * Fetch message processing.
	 * 
	 * @param grupomuscular the grupomuscular
	 * @return the internal response
	 */
	private InternalResponse fetchMessageProcessing(Grupomuscular grupomuscular)
	{
		// List<Property> properties = new ArrayList<Property>();
		// properties.add(new Property(PropertyEnum.GROUP_ID.getValue(), grupomuscular.getCdacad().toString()));
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
	public InternalResultsResponse<Grupomuscular> fetchAllGrupomuscularByUser(
			InquiryGrupomuscularRequest inquiryGrupomuscularRequest)
	{
		return getGrupomuscularDAC().fetchAllGrupomuscularByUser(inquiryGrupomuscularRequest);
	}

}
