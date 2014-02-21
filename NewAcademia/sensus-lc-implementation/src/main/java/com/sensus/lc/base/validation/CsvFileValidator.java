package com.sensus.lc.base.validation;

import static com.sensus.common.validation.ValidationUtil.isNull;
import static com.sensus.common.validation.ValidationUtil.isNullOrEmpty;
import static com.sensus.lc.base.model.ObjectToBeValidatedKeyEnum.COLUMN_LIST;
import static com.sensus.lc.base.model.ObjectToBeValidatedKeyEnum.CSV_FILE_NAME;
import static com.sensus.lc.base.util.CSVFileReader.getRowsAmount;

import java.io.File;
import java.util.List;

import com.sensus.common.model.MessageInfo;
import com.sensus.common.validation.IValidator;
import com.sensus.common.validation.ValidationContext;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.lc.base.model.MLCPersistanceActionEnum;
import com.sensus.lc.base.util.LCHelp;
import com.sensus.lc.light.model.Column;

/**
 * The Class InquiryEcoModeRequestValidator.
 */
public class CsvFileValidator implements IValidator
{
	/** The Constant CSV_NAME. */
	private static final String CSV_NAME = "CSV file";

	/** The Constant SENSUS_MLC_CSVFILEVALIDATOR_FILE_REQUIRED. */
	private static final String SENSUS_MLC_CSVFILEVALIDATOR_FILE_REQUIRED =
			"sensus.mlc.csvfilevalidator.file.required";

	/** The Constant SENSUS_MLC_CSVFILEVALIDATOR_FILE_IS_EMPTY. */
	private static final String SENSUS_MLC_CSVFILEVALIDATOR_FILE_IS_EMPTY =
			"sensus.mlc.csvfilevalidator.file.empty";

	/** The Constant SENSUS_MLC_VALIDATOR_NAME_REQUIRED. */
	private static final String SENSUS_MLC_VALIDATOR_NAME_REQUIRED = "sensus.mlc.validator.name.required";

	/** The Constant SENSUS_LC_EXPORT_CSV_NO_COLUMNS. */
	private static final String SENSUS_LC_EXPORT_CSV_NO_COLUMNS = "sensus.lc.csv.no.columns";

	/*
	 * (non-Javadoc)
	 * @see com.sensus.common.validation.IValidator#validate(com.sensus.common.validation.ValidationContext)
	 */
	@Override
	public void validate(ValidationContext validationContext)
	{
		MLCPersistanceActionEnum action =
				(MLCPersistanceActionEnum)validationContext.getValidationArguments().get(
						MLCPersistanceActionEnum.getSlcActionName());

		if (isNull(action))
		{
			return;
		}

		List<MessageInfo> list = validationContext.getMessages();

		if (!isNullOrEmpty(list))
		{
			return;
		}

		switch (action)
		{
			case GENERATE_FILE_CSV:
				String csvFileName = (String)validationContext.getObjectToBeValidated(CSV_FILE_NAME.getValue());
				validateCSVFileName(list, csvFileName);
				@SuppressWarnings("unchecked")
				List<Column> csvColumns =
						(List<Column>)validationContext.getObjectToBeValidated(COLUMN_LIST.getValue());
				validateCSVColumns(list, csvColumns);
				return;
			default:
				return;
		}
	}

	/**
	 * Validate csv file name.
	 * 
	 * @param list the list
	 * @param csvFileName the csv file name
	 */
	private void validateCSVFileName(List<MessageInfo> list, String csvFileName)
	{
		LCHelp.isNullLC(csvFileName, SENSUS_MLC_VALIDATOR_NAME_REQUIRED, list, CSV_NAME);
	}

	/**
	 * Validate csv file.
	 * 
	 * @param list the list
	 * @param csvFile the csv file
	 */
	private static void validateCSVFile(List<MessageInfo> list, File csvFile)
	{

		if ((isNull(csvFile)) || !csvFile.exists())
		{
			list.add(MessageInfo.createFieldValidationError(SENSUS_MLC_CSVFILEVALIDATOR_FILE_REQUIRED));
			return;
		}

		if (ValidationUtil.isNullOrZero(getRowsAmount(csvFile)))
		{
			list.add(MessageInfo.createFieldValidationError(SENSUS_MLC_CSVFILEVALIDATOR_FILE_IS_EMPTY));
		}
	}

	private static void validateCSVColumns(List<MessageInfo> list, List<Column> csvColumns)
	{
		if (ValidationUtil.isNullOrEmpty(csvColumns))
		{
			list.add(MessageInfo.createFieldValidationError(SENSUS_LC_EXPORT_CSV_NO_COLUMNS));
		}
	}

}
