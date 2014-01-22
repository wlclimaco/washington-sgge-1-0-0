package com.sensus.lc.base.util;

import static org.apache.commons.lang3.StringUtils.capitalize;
import static org.apache.commons.lang3.StringUtils.splitByWholeSeparator;
import static org.apache.commons.lang3.StringUtils.substringAfter;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.IllegalFormatFlagsException;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

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

	/** The Constant POINT_SEPARATOR. */
	private static final String POINT_SEPARATOR = ".";

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
		return convertCSVFileToSensusModelList(
				csvFile,
				sensusModelClass,
				columnPositionModel,
				true);
	}

	/**
	 * Convert csv file to sensus model list.
	 * 
	 * @param csvFile the csv file
	 * @param sensusModelClass the sensus model class
	 * @param columnPositionModel the column position model
	 * @param containFileHeader file contain header - default false
	 * @return the list<? extends sensus model>
	 */
	public static List<? extends SensusModel> convertCSVFileToSensusModelList(
			File csvFile,
			Class<?> sensusModelClass,
			String[] columnPositionModel,
			boolean containFileHeader)
	{

		List<SensusModel> objectList = new ArrayList<SensusModel>();

		try
		{
			validateCSVFile(csvFile);
			validateModelClass(sensusModelClass);
			validateColumnPositionModel(columnPositionModel);

			List<String[]> rows = readFile(csvFile.getPath());

			if (CollectionUtils.isEmpty(rows) || ((rows.size() == 1) && containFileHeader))
			{
				return objectList;
			}

			if (containFileHeader)
			{
				rows = rows.subList(1, rows.size());
			}

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

		try
		{
			validateCSVFile(csvFile);
			List<String[]> rows = readFile(csvFile.getPath());

			return rows.size();
		}
		catch (Throwable e)
		{
			persistDebugLog(ERROR_PARSER_CSV_FILE, e);
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
				setFieldValues(sensusModelObj, sensusModelClass, columnPositionModel[i], data[i]);
			}

			return sensusModelObj;
		}
		catch (Throwable e)
		{
			persistDebugLog(ERROR_TO_INSTANTIATE_OBJECT, e);
			return null;
		}
	}

	private static void setFieldValues(Object sensusModelObj, Class<?> sensusModelClass, String field, String dataValue)
			throws Exception
	{
		String[] fields = splitByWholeSeparator(field, POINT_SEPARATOR);
		if (fields.length == 1)
		{
			String setMethodName = PREFIX_SET_METHOD_JAVA_BEAN + capitalize(field);
			Method setMethod = getMethodToColumn(sensusModelClass, setMethodName);
			Object value = convertStrintToClassParameter(setMethod, dataValue);
			setMethod.invoke(sensusModelObj, value);
			return;
		}

		Field fieldClass = sensusModelClass.getDeclaredField(fields[0]);
		fieldClass.setAccessible(true);
		Object sensusModelSubObj = fieldClass.get(sensusModelObj);
		Class<?> sensusModelClassSublevel = fieldClass.getType();

		if (sensusModelSubObj == null)
		{
			sensusModelSubObj = sensusModelClassSublevel.newInstance();
		}

		fieldClass.set(sensusModelObj, sensusModelSubObj);
		setFieldValues(sensusModelSubObj, sensusModelClassSublevel, substringAfter(field, POINT_SEPARATOR), dataValue);
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
		value = value.replace("\"", "");
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

	/**
	 * Read file.
	 * 
	 * @param fileName the file name
	 * @return the list
	 * @throws FileNotFoundException the file not found exception
	 */
	private static List<String[]> readFile(String fileName) throws FileNotFoundException
	{
		List<String[]> rows = new ArrayList<String[]>();
		Scanner scan = new Scanner(new File(fileName));
		while (scan.hasNextLine())
		{
			String line = scan.nextLine();
			rows.add(line.split(","));
		}
		return rows;
	}
}
