package com.sensus.lc.exercicio.bcl.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sensus.common.csv.CSVColumn;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.lc.exercicio.bcl.IExercicioBCL;
import com.sensus.lc.exercicio.dac.IExercicioDAC;
import com.sensus.lc.exercicio.model.Exercicio;
import com.sensus.lc.exercicio.model.request.ExercicioRequest;
import com.sensus.lc.exercicio.model.request.InquiryExercicioRequest;
import com.sensus.lc.process.bcl.IProcessBCL;

// TODO: Auto-generated Javadoc
/**
 * The Class ExercicioBCLImpl.
 * 
 * @author Washington.
 */
public class ExercicioBCLImpl implements IExercicioBCL
{

	/** The log. */
	/** The log. */
	private static transient Logger LOG = LoggerFactory.getLogger(ExercicioBCLImpl.class);
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
			"sensus.epm.scheduledeventvalidator.process.existingActionInProcessingExercicio";

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

	/** The exercicio dac. */
	private IExercicioDAC exercicioDAC;

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
	 * Gets the exercicio dac.
	 * 
	 * @return the exercicio dac
	 */
	public IExercicioDAC getExercicioDAC()
	{
		return exercicioDAC;
	}

	/**
	 * Sets the exercicio dac.
	 * 
	 * @param exercicioDAC the new exercicio dac
	 */
	public void setExercicioDAC(IExercicioDAC exercicioDAC)
	{
		this.exercicioDAC = exercicioDAC;
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
	 * com.sensus.dm.common.exercicio.bcl.IExercicioBCL#deleteExercicio(com.sensus.dm.common.exercicio.model.request.
	 * ExercicioRequest)
	 */
	@Override
	public InternalResponse deleteExercicio(ExercicioRequest exercicioRequest)
	{
		InternalResponse response = new InternalResponse();

		for (Integer integer : exercicioRequest.getSelectionPaginationIds())
		{
			Exercicio exercicio = new Exercicio(integer);
			exercicioRequest.addExercicio(exercicio);

		}

		for (Exercicio exercicio : exercicioRequest.getExercicios())
		{

			// Are there something running for this exercicio ?
			response = fetchMessageProcessing(exercicio);
			if (response.isInError())
			{
				return response;
			}

			// deletes the exercicio
			response = getExercicioDAC().deleteExercicio(new ExercicioRequest(exercicio));
			if (response.isInError())
			{
				return response;
			}

			// insert process for del exercicio
			// InternalResultsResponse<DMProcess> processResponse =
			// insertExercicioProcess(exercicioResponse.getFirstResult(), exercicioRequest.getIsMonitored(),
			// "RemoveExercicioAction.ACTION", ProcessStatusEnum.COMPLETED,
			// null, exercicioRequest);

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
	 * com.sensus.dm.common.exercicio.bcl.IExercicioBCL#insertExercicio(com.sensus.dm.common.exercicio.model.request.
	 * ExercicioRequest)
	 */
	@Override
	public InternalResultsResponse<Exercicio> insertExercicio(ExercicioRequest exercicioRequest)
	{

		InternalResultsResponse<Exercicio> exercicioResponse = getExercicioDAC().insertExercicio(exercicioRequest);

		if (ValidationUtil.isNull(exercicioResponse))
		{
			exercicioResponse.setStatus(exercicioResponse.getStatus());
			exercicioResponse.addMessages(exercicioResponse.getMessageInfoList());
			return exercicioResponse;
		}

		// insert exercicio
		exercicioResponse = getExercicioDAC().insertExercicio(exercicioRequest);
		if (exercicioResponse.isInError())
		{
			return exercicioResponse;
		}

		// insert process for exercicio creation
		//
		// InternalResultsResponse<DMProcess> processResponse =
		// insertExercicioProcess(exercicioRequest.getFirstExercicio(), false,
		// "CreateExercicioAction.ACTION", ProcessStatusEnum.COMPLETED,
		// null, exercicioRequest);
		//
		// if (processResponse.isInError())
		// {
		// exercicioResponse.setStatus(processResponse.getStatus());
		// exercicioResponse.addMessages(processResponse.getMessageInfoList());
		// return exercicioResponse;
		// }
		//
		// exercicioRequest.setProcessId(processResponse.getFirstResult().getId());

		return exercicioResponse;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.exercicio.bcl.IExercicioBCL#updateExercicio(com.sensus.dm.common.exercicio.model.request.
	 * ExercicioRequest)
	 */
	@Override
	public InternalResultsResponse<Exercicio> updateExercicio(ExercicioRequest exercicioRequest)
	{
		InternalResultsResponse<Exercicio> exercicioResponse = new InternalResultsResponse<Exercicio>();

		// inserts the exercicio
		exercicioResponse = getExercicioDAC().updateExercicio(exercicioRequest);
		if (exercicioResponse.isInError())
		{
			return exercicioResponse;
		}

		// insert process for edit exercicio
		// InternalResultsResponse<DMProcess> processResponse =
		// insertExercicioProcess(exercicioRequest.getFirstExercicio(), false,
		// "EditExercicioAction.ACTION", ProcessStatusEnum.COMPLETED, null, exercicioRequest);
		//
		// if (processResponse.isInError())
		// {
		// exercicioResponse.setStatus(processResponse.getStatus());
		// exercicioResponse.addMessages(processResponse.getMessageInfoList());
		// return exercicioResponse;
		// }

		return exercicioResponse;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.exercicio.bcl.IExercicioBCL#fetchAllExercicios(com.sensus.dm.common.exercicio.model.request.
	 * InquiryExercicioRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<Exercicio> fetchAllExercicios(InquiryExercicioRequest inquiryExercicioRequest)
	{
		return getExercicioDAC().fetchAllExercicios(inquiryExercicioRequest);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.common.exercicio.bcl.IExercicioBCL#fetchExerciciosByDevice(com.sensus.dm.common.device.model.request
	 * .
	 * DeviceRequest
	 * )
	 */
	/**
	 * Fetch exercicios by id.
	 * 
	 * @param inquiryExercicioRequest the inquiry exercicio request
	 * @return the internal results response
	 */
	@Override
	public InternalResultsResponse<Exercicio> fetchExerciciosById(InquiryExercicioRequest inquiryExercicioRequest)
	{
		return getExercicioDAC().fetchExercicioById(inquiryExercicioRequest);
	}

	/**
	 * Fetch message processing.
	 * 
	 * @param exercicio the exercicio
	 * @return the internal response
	 */
	private InternalResponse fetchMessageProcessing(Exercicio exercicio)
	{
		// List<Property> properties = new ArrayList<Property>();
		// properties.add(new Property(PropertyEnum.GROUP_ID.getValue(), exercicio.getCdacad().toString()));
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
	public InternalResultsResponse<Exercicio> fetchAllExercicioByUser(InquiryExercicioRequest inquiryExercicioRequest)
	{
		return getExercicioDAC().fetchAllExercicioByUser(inquiryExercicioRequest);
	}

}
