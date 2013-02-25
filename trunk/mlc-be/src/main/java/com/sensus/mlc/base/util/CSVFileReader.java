package com.sensus.mlc.base.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.IllegalFormatFlagsException;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StringUtils;

import au.com.bytecode.opencsv.CSVReader;

import com.sensus.common.model.SensusModel;

/**
 * The Class CSVFileReader.
 */
public final class CSVFileReader
{

	/** The Constant LOG. */
	private static final Log LOG = LogFactory.getLog(CSVFileReader.class);

	/** The Constant PREFIX_SET_METHOD_JAVA_BEAN. */
	private static final String PREFIX_SET_METHOD_JAVA_BEAN = "set";

	/** The Constant VALUE_OF_METHOD. */
	private static final String VALUE_OF_METHOD = "valueOf";

	// Exception texts

	/** The Constant PARAMETER_IS_PRIMITIVE. */
	private static final String PARAMETER_IS_PRIMITIVE = "Parameter to %s is primitive";

	/** The Constant CSV_FILE_IS_REQUIRED. */
	private static final String CSV_FILE_IS_REQUIRED = "CSV file is required";

	/** The Constant FILE_IS_NOT_CSV. */
	private static final String FILE_IS_NOT_CSV = "File is not CSV";

	/** The Constant NOT_SENSUS_MODEL_HIERARCHY. */
	private static final String NOT_SENSUS_MODEL_HIERARCHY = "Class is not SensusModel hierarchy";

	/** The Constant NO_COLUMN_INFORMED. */
	private static final String NO_COLUMN_INFORMED = "No column informed";

	/** The Constant ERROR_TO_CLOSE_CSV_READER. */
	private static final String ERROR_TO_CLOSE_CSV_READER = "Error to close csv reader";

	/** The Constant CSV_EXTENSION. */
	private static final String CSV_EXTENSION = "csv";

	/** The Constant CSV_EXTENSION. */
	private static final String TMP_EXTENSION = "tmp";

	/** The Constant ERROR_PARSER_CSV_FILE. */
	private static final String ERROR_PARSER_CSV_FILE = "Error to parser row from csv file";

	/** The Constant ERROR_TO_INSTANTIATE_OBJECT. */
	private static final String ERROR_TO_INSTANTIATE_OBJECT = "Error to instantiate object";

	/** The Constant SET_METHOD_INFORMED_NOT_EXIST. */
	private static final String SET_METHOD_INFORMED_NOT_EXIST = "Set method informed not exist";

	/**
	 * Instantiates a new cSV file reader.
	 */
	private CSVFileReader()
	{

	}

	/**
	 * Convert csv file to sensus model list.
	 * 
	 * @param csvFile the csv file
	 * @param sensusModelClass the sensus model class
	 * @param columnPositionModel the column position model
	 * @return the list<? extends sensus model>
	 */
	public static List<? extends SensusModel> convertCSVFileToSensusModelList(
			File csvFile,
			Class<?> sensusModelClass,
			String[] columnPositionModel)
	{

		List<SensusModel> objectList = new ArrayList<SensusModel>();
		CSVReader reader = null;

		try
		{
			validateCSVFile(csvFile);
			validateModelClass(sensusModelClass);
			validateColumnPositionModel(columnPositionModel);

			reader = new CSVReader(new FileReader(csvFile));
			List<String[]> rows = reader.readAll();
			for (String[] nextData : rows)
			{
				SensusModel obj = (SensusModel)instantiateObject(sensusModelClass, columnPositionModel, nextData);
				if (obj != null)
				{
					objectList.add(obj);
				}
			}
		}
		catch (Throwable e)
		{
			persistDebugLog(ERROR_PARSER_CSV_FILE, e);
		}
		finally
		{
			closeCSVReader(reader);
		}

		return objectList;
	}

	/**
	 * Gets the rows amount.
	 * 
	 * @param csvFile the csv file
	 * @return the rows amount
	 * @throws IllegalAccessException
	 * @throws FileNotFoundException
	 */
	public static Integer getRowsAmount(File csvFile)
	{
		CSVReader reader = null;

		try
		{
			validateCSVFile(csvFile);
			reader = new CSVReader(new FileReader(csvFile));

			List<String[]> allRows = reader.readAll();
			if (allRows == null)
			{
				return 0;
			}

			return allRows.size();
		}
		catch (Throwable e)
		{
			persistDebugLog(ERROR_PARSER_CSV_FILE, e);
		}
		finally
		{
			closeCSVReader(reader);
		}

		return null;
	}

	/**
	 * Instantiate object.
	 * 
	 * @param sensusModelClass the sensus model class
	 * @param columnPositionModel the column position model
	 * @param data the data
	 * @return the object
	 */
	private static Object instantiateObject(Class<?> sensusModelClass, String[] columnPositionModel, String[] data)
	{
		try
		{
			Object sensusModelObj = sensusModelClass.newInstance();

			for (int i = 0; i < columnPositionModel.length; i++)
			{
				String setMethodName = PREFIX_SET_METHOD_JAVA_BEAN + StringUtils.capitalize(columnPositionModel[i]);
				Method setMethod = getMethodToColumn(sensusModelClass, setMethodName);
				Object value = convertStrintToClassParameter(setMethod, data[i]);
				setMethod.invoke(sensusModelObj, value);
			}

			return sensusModelObj;
		}
		catch (Throwable e)
		{
			persistDebugLog(ERROR_TO_INSTANTIATE_OBJECT, e);
			return null;
		}
	}

	/**
	 * Gets the method to column.
	 * 
	 * @param sensusModelClass the sensus model class
	 * @param setMethodName the set method name
	 * @return the method to column
	 */
	private static Method getMethodToColumn(Class<?> sensusModelClass, String setMethodName)
	{
		Method[] methods = sensusModelClass.getDeclaredMethods();

		for (Method method : methods)
		{
			if (setMethodName.equals(method.getName()) && (method.getParameterTypes().length == 1))
			{
				return method;
			}
		}
		throw new IllegalArgumentException(SET_METHOD_INFORMED_NOT_EXIST);
	}

	private static Object convertStrintToClassParameter(Method setMethod, String value) throws Exception
	{
		Class<?> parameterClass = setMethod.getParameterTypes()[0];

		if (parameterClass.isPrimitive())
		{
			throw new IllegalAccessException(String.format(PARAMETER_IS_PRIMITIVE, setMethod.getName()));
		}

		if (String.class.equals(parameterClass))
		{
			return value;
		}

		Method valueOfMethod = parameterClass.getDeclaredMethod(VALUE_OF_METHOD, String.class);
		return valueOfMethod.invoke(null, value);
	}

	/**
	 * Validate csv file.
	 * 
	 * @param csvFile the csv file
	 * @throws IllegalFormatFlagsException the illegal format flags exception
	 * @throws FileNotFoundException the file not found exception
	 */
	private static void validateCSVFile(File csvFile) throws IllegalFormatFlagsException, FileNotFoundException
	{
		if ((csvFile == null) || !csvFile.exists())
		{
			persistDebugLog(CSV_FILE_IS_REQUIRED);
			throw new FileNotFoundException(CSV_FILE_IS_REQUIRED);
		}

		String csvExtension = FilenameUtils.getExtension(csvFile.getName());

		if (TMP_EXTENSION.equals(csvExtension))
		{
			// is not possible validate
			return;
		}

		if (!CSV_EXTENSION.equals(csvExtension))
		{
			persistDebugLog(FILE_IS_NOT_CSV);
			throw new IllegalFormatFlagsException(FILE_IS_NOT_CSV);
		}
	}

	/**
	 * Validate model class.
	 * 
	 * @param sensusModelClass the model class
	 */
	private static void validateModelClass(Class<?> sensusModelClass)
	{
		if ((sensusModelClass == null) || (sensusModelClass.asSubclass(SensusModel.class) == null))
		{
			persistDebugLog(NOT_SENSUS_MODEL_HIERARCHY);
			throw new IllegalArgumentException(NOT_SENSUS_MODEL_HIERARCHY);
		}
	}

	/**
	 * Validate column position model.
	 * 
	 * @param columnPositionModel the column position model
	 */
	private static void validateColumnPositionModel(String[] columnPositionModel)
	{
		if ((columnPositionModel == null) || (columnPositionModel.length == 0))
		{
			persistDebugLog(NO_COLUMN_INFORMED);
			throw new IllegalArgumentException(NO_COLUMN_INFORMED);
		}
	}

	/**
	 * Close csv reader.
	 * 
	 * @param reader the reader
	 */
	private static void closeCSVReader(CSVReader reader)
	{
		try
		{
			if (reader != null)
			{
				reader.close();
			}
		}
		catch (Throwable e)
		{
			persistDebugLog(ERROR_TO_CLOSE_CSV_READER, e);
		}
	}

	/**
	 * Persist debug log.
	 * 
	 * @param log the log
	 */
	private static void persistDebugLog(String log)
	{
		if (LOG.isDebugEnabled())
		{
			LOG.debug(log);
		}
	}

	/**
	 * Persist debug log.
	 * 
	 * @param log the log
	 * @param e the e
	 */
	private static void persistDebugLog(String log, Throwable e)
	{
		if (LOG.isDebugEnabled())
		{
			LOG.debug(log, e);
		}
	}
}
