package com.sensus.common.csv;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.dozer.CsvDozerBeanReader;
import org.supercsv.io.dozer.CsvDozerBeanWriter;
import org.supercsv.prefs.CsvPreference;

import com.sensus.common.util.SensusMessageUtil;

/**
 * This utility class can be used to help with exporting and import CSV files.<BR>
 * When you want to write to a CSV file that is known as "exporting", and reading from a file is known as "importing".<br>
 * For exporting a CSV file this class allows for direct access to the object source using property mappings as a means
 * to extract the data from the object graph, with support for complex objects, and produce a CSV file.<br>
 * For importing you use a similar technique with the property mappings to read a CSV file and inflate a complex object
 * graph. The use of "cell processors" is supported so various data types, including date and enumerations, are
 * supported.<br>
 * This class is used in conjunction with the SuperCSV library.<br>
 * It is not compatible with OpenCSV.<br>
 * The CSVColumn is used to encapsulate column information and is typically inflated within a Spring context along with
 * a containing collection, List. Then the caller passes in this List for processing. See the methods for more
 * information.<br>
 */
public final class CSVUtil
{

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(CSVUtil.class);

	/**
	 * Required since this class is final.
	 */
	private CSVUtil()
	{
	}

	// [start] Export related methods

	/**
	 * One of several methods used to produce a CSV file.<br>
	 * All columns from the columns parameter will be included in the file.
	 * Headers are determined based on the contents of each CSVColumn instance.<br>
	 * Through the use of the Locale parameter, if not null, the column headers can be internationalized using the
	 * SensusMessageUtil.getMessage method along with the headerResourceKey property from the CSVColumn.<br>
	 * Note, By default the "Optional" cell processor is added to every cell.
	 *
	 * @param columns A collection of all columns to be included in this file.
	 * @param fileName The file name to use for producing this CSV file.
	 * @param source The source object from which the values should be retrieved. Note there is another method for list
	 *            processing.
	 * @param locale Optional Locale instance used to internationalize the header.
	 * @return true, if successful otherwise a log message will be printed and false will be returned.
	 */
	public static boolean exportFile(List<CSVColumn> columns, String fileName, Object source, Locale locale)
	{
		List<String> headers = new ArrayList<String>();
		List<String> columnPropertyMappings = new ArrayList<String>();
		List<CellProcessor> cellProcessors = new ArrayList<CellProcessor>();

		setupColumns(columns, locale, headers, columnPropertyMappings, cellProcessors, true);

		return exportFile(headers.toArray(new String[headers.size()]),
				columnPropertyMappings.toArray(new String[columnPropertyMappings.size()]), fileName, source,
				cellProcessors.toArray(new CellProcessor[cellProcessors.size()]));
	}

	/**
	 * Similar to the other {@link #exportFile(List, String, List, Locale)} method except that this method processes a
	 * List of source objects.<br>
	 * Note, By default the "Optional" cell processor is added to every cell.
	 *
	 * @param columns A collection of all columns to be included in this file.
	 * @param fileName The file name to use for producing this CSV file.
	 * @param sourceList List collection of source objects to process.
	 * @param locale Optional Locale instance used to internationalize the header.
	 * @return true, if successful
	 */
	public static boolean exportFile(List<CSVColumn> columns, String fileName, List<?> sourceList, Locale locale)
	{
		List<String> headers = new ArrayList<String>();
		List<String> columnPropertyMappings = new ArrayList<String>();
		List<CellProcessor> cellProcessors = new ArrayList<CellProcessor>();
		setupColumns(columns, locale, headers, columnPropertyMappings, cellProcessors, true);

		return exportFile(headers.toArray(new String[headers.size()]),
				columnPropertyMappings.toArray(new String[columnPropertyMappings.size()]), fileName, sourceList,
				cellProcessors.toArray(new CellProcessor[cellProcessors.size()]));
	}

	/**
	 * This method is called by the other exportFile methods prior to invoking the main
	 * {@link #exportFile(String[], String[], String, List, CellProcessor[])} method, or the version which takes just a
	 * single source object.<br>
	 * This method sets-up the various collections required to perform the actual export.
	 * You can call it when you need to customize of add to these collections prior to invoking the main exportFile
	 * method which takes basic String array etc for parameters. Just remember to convert the List to a simple array
	 * prior to invoking the main method.<br>
	 * Note, By default the "Optional" cell processor is added to every cell.
	 *
	 * @param columns The column definition to be used for exporting.
	 * @param locale The locale, if any.
	 * @param headers This is a return collection which gets populated by this method with a String collection of
	 *            headers from the CSVColumn instances.
	 * @param columnPropertyMappings This is a return collection which gets populated by this method with a String
	 *            collection of property-mappings from the CSVColumn instances.
	 * @param cellProcessors This is a return collection which gets populated by this method with a collection of
	 *            cell-processors from the CSVColumn instances.
	 * @param forExport This method is used for both import and export and this flag indicates which mode it should
	 *            operate in.
	 */
	private static void setupColumns(List<CSVColumn> columns, Locale locale, List<String> headers,
			List<String> columnPropertyMappings, List<CellProcessor> cellProcessors, boolean forExport)
	{
		for (CSVColumn column : columns)
		{
			// First handle the header and resource-key(if any)
			addToHeaders(locale, headers, column);

			// Add property path to collection.
			addToPropertyMappings(columnPropertyMappings, forExport, column);

			// Next deal with cell processor, if set.
			addToCellProcessors(cellProcessors, forExport, column);
		}
	}

	private static void addToCellProcessors(List<CellProcessor> cellProcessors, boolean forExport, CSVColumn column)
	{
		if (forExport)
		{
			if (column.getWriteCellProcessor() != null)
			{
				// If the first cell processor is already an Optional instance then don't add it again.
				if (!(column.getWriteCellProcessor() instanceof Optional))
				{
					cellProcessors.add(new Optional(column.getWriteCellProcessor()));
				}
				else
				{
					cellProcessors.add(column.getWriteCellProcessor());
				}
			}
			else
			{
				cellProcessors.add(new Optional());
			}
		}
		else
		{
			if (column.getReadCellProcessor() != null)
			{
				cellProcessors.add(column.getReadCellProcessor());
			}
			else
			{
				cellProcessors.add(null);
			}
		}
	}

	private static void addToPropertyMappings(List<String> columnPropertyMappings, boolean forExport, CSVColumn column)
	{
		if (forExport)
		{
			columnPropertyMappings.add(column.getPropertyPath());
		}
		else
		{
			columnPropertyMappings.add(column.getPropertyPathSetter());
		}
	}

	private static void addToHeaders(Locale locale, List<String> headers, CSVColumn column)
	{
		String headerResourceKey;
		headerResourceKey = column.getHeaderResourceKey();
		if (headerResourceKey == null)
		{
			headers.add(column.getHeader());
		}
		else
		{
			// Get the internationalized header text
			String header = SensusMessageUtil.getMessage(headerResourceKey, null, null, locale);

			// Log if not found
			if (header == null)
			{
				LOG.warn("Resource key[" + headerResourceKey + "] not found, using header value by default.");
				headers.add(column.getHeader());
			}

			// Add to collection.
			headers.add(header);
		}
	}

	/**
	 * The main method used to produce a CSV file from a single object graph.<BR>
	 * There is another main method used to produce a CSV file from a List of objects.<BR>
	 * This method leverages the various parameters to produce the CSV file with the header array.
	 * It then uses the columnPropertyMappings array to extract the individual CSV column data values from the source
	 * object.<br>
	 * If you require the headers to be internationalized use on of the other exportFile methods. This method assumes
	 * they have already been internationalized.
	 *
	 * @param headers The headers to be used for this file.
	 * @param columnPropertyMappings A collection of column-property-mappings used to extract the value from the source
	 *            object.
	 * @param fileName The file name to use for producing this CSV file.
	 * @param source The source object from which the values should be retrieved.
	 * @param cellProcessors The cell processors to use for this export. See the SuperCSV documentation for more
	 *            information. By default the "Optional" cell processor is added to every cell.
	 * @return true, if successful otherwise a log message will be printed and false will be returned.
	 */
	public static boolean exportFile(String[] headers, String[] columnPropertyMappings, String fileName,
			Object source, CellProcessor[] cellProcessors)
	{
		CsvDozerBeanWriter beanWriter = null;
		try
		{
			beanWriter = new CsvDozerBeanWriter(new FileWriter(fileName), CsvPreference.STANDARD_PREFERENCE);

			beanWriter.configureBeanMapping(source.getClass(), columnPropertyMappings);

			beanWriter.writeHeader(headers);

			writeToCsv(source, cellProcessors, beanWriter);

			beanWriter.close();
		}
		catch (IOException ex)
		{
			ex.printStackTrace();
			LOG.error("Exception thrown during CSV processing." + ex.getMessage(), ex);
		}

		return true;
	}

	/**
	 * Similar to the other {@link #exportFile(String[], String[], String, Object, CellProcessor[])} method with the
	 * exception that this method processes a List of source objects.
	 *
	 * @param headers The headers to be used for this file.
	 * @param columnPropertyMappings A collection of column-property-mappings used to extract the value from the source
	 *            object.
	 * @param fileName The file name to use for producing this CSV file.
	 * @param sourceList The list of source object to process.
	 * @param cellProcessors The cell processors to use for this export. See the SuperCSV documentation for more
	 *            information. By default the "Optional" cell processor is added to every cell.
	 * @return true, if successful otherwise a log message will be printed and false will be returned.
	 *
	 */
	public static boolean exportFile(String[] headers, String[] columnPropertyMappings, String fileName,
			List<?> sourceList, CellProcessor[] cellProcessors)
	{
		CsvDozerBeanWriter beanWriter = null;
		try
		{
			beanWriter = new CsvDozerBeanWriter(new FileWriter(fileName), CsvPreference.STANDARD_PREFERENCE);

			if (sourceList.size() > 0)
			{
				beanWriter.configureBeanMapping(sourceList.get(0).getClass(), columnPropertyMappings);

				beanWriter.writeHeader(headers);

				for (Object source : sourceList)
				{
					if (source != null)
					{
						writeToCsv(source, cellProcessors, beanWriter);
					}
				}
			}

			beanWriter.close();
		}
		catch (IOException ex)
		{
			ex.printStackTrace();
			LOG.error("Exception thrown during CSV processing." + ex.getMessage(), ex);
			return false ;
		}

		return true;
	}

	private static void writeToCsv(Object source, CellProcessor[] cellProcessors, CsvDozerBeanWriter beanWriter)
			throws IOException
	{
		if (cellProcessors == null || cellProcessors.length == 0)
		{
			beanWriter.write(source);
		}
		else
		{
			beanWriter.write(source, cellProcessors);
		}
	}

	/**
	 * Helper method used to filter a collection of CSVColumn instances based on the CSVColumn.name property.<br>
	 * Each CSVColumn.name property is matched against the String[] parameter and if a match is found the CSVColumn
	 * instance is added to the returned list of CSVColumns.
	 *
	 * @param allPossibleColumns A List of all possible CSVColumns to choose from.
	 * @param columnsToInclude String array of column names to include.
	 * @return The filtered List
	 */
	public static List<CSVColumn> filterColumns(List<CSVColumn> allPossibleColumns, String[] columnsToInclude)
	{
		CSVColumn column;

		List<CSVColumn> columns = new ArrayList<CSVColumn>();

		for (String columnNameToInclude : columnsToInclude)
		{
			column = findColumn(columnNameToInclude, allPossibleColumns);

			if (column != null)
			{
				columns.add(column);
			}
		}

		return columns;
	}

	/**
	 * Find the column based on the name.
	 *
	 * @param columnNameToInclude the column name to include
	 * @param allPossibleColumns the all possible columns
	 * @return the column
	 */
	private static CSVColumn findColumn(String columnNameToInclude, List<CSVColumn> allPossibleColumns)
	{
		for (CSVColumn column : allPossibleColumns)
		{
			if (columnNameToInclude.equalsIgnoreCase(column.getName()))
			{
				return column;
			}
		}

		LOG.warn("Column name[" + columnNameToInclude + "] not found in collection of defined columns.");

		return null;
	}

	// [end] Export related methods

	// [start] Import related methods.

	/**
	 * Used to inflate multiple objects from a CSV file.<BR>
	 * Using the mappings each input line from the file is processed as well cell-processors are applied to support
	 * data transformation.
	 *
	 * @param <T> The type of object being inflated.
	 * @param columns collection used for property mappings indicating how the column value should be mapped into
	 *            the object graph.
	 * @param fileName The file name to read.
	 * @param destinationClassType The destination class type
	 * @param includeHeader Does the file include a header which should be skipped.
	 * @return List of inflated objects.
	 */
	public static <T> List<T> importFile(List<CSVColumn> columns, String fileName, Class<T> destinationClassType,
			boolean includeHeader)
	{
		List<String> headers = new ArrayList<String>();
		List<String> columnPropertyMappings = new ArrayList<String>();
		List<CellProcessor> cellProcessors = new ArrayList<CellProcessor>();
		setupColumns(columns, null, headers, columnPropertyMappings, cellProcessors, false);

		return importFile(columnPropertyMappings.toArray(new String[columnPropertyMappings.size()]), fileName,
				cellProcessors.toArray(new CellProcessor[cellProcessors.size()]), destinationClassType, includeHeader);
	}

	/**
	 * Used to inflate multiple objects from a CSV file.<BR>
	 * Using the mappings each input line from the file is processed as well cell-processors are applied to support
	 * data transformation.
	 *
	 * @param <T> The type of object being inflated.
	 * @param columnPropertyMappings Array of property mappings indicating how the column value should be mapped into
	 *            the object graph.
	 * @param fileName The file name to read.
	 * @param cellProcessors Collection of cell processors
	 * @param destinationClassType The destination class type
	 * @param includeHeader Does the file include a header which should be skipped.
	 * @return List of inflated objects.
	 */
	public static <T> List<T> importFile(String[] columnPropertyMappings, String fileName,
			CellProcessor[] cellProcessors, Class<T> destinationClassType, boolean includeHeader)
	{
		List<T> list = new ArrayList<T>();

		CsvDozerBeanReader beanReader = null;

		try
		{
			beanReader = new CsvDozerBeanReader(new FileReader(fileName), CsvPreference.STANDARD_PREFERENCE);

			beanReader.configureBeanMapping(destinationClassType, columnPropertyMappings);

			beanReader.getHeader(includeHeader);

			T bean;
			while ((bean = (T)beanReader.read(destinationClassType, cellProcessors)) != null)
			{
				list.add((T)bean);
			}

			beanReader.close();
		}
		catch (Exception ex)
		{
			LOG.error("Error importing CSV file[" + fileName + "], line number=" + beanReader.getLineNumber(), ex);
		}

		return list;
	}

	// [end] Import related methods.
}
