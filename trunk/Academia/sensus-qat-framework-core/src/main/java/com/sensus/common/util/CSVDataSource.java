package com.sensus.common.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.sensus.common.model.request.Request;

/**
 * Extend this class and provide it the object which will be wrapped.
 * 
 * @author rpulley
 * 
 * @param <T> The class (usually a model object) which holds the data for each row.
 */
public class CSVDataSource<T>
{
	private Request request;
	private Locale locale = Locale.getDefault();

	private List<T> data = new ArrayList<T>();
	private List<String> columns = new ArrayList<String>();

	// Some CSV files have a subtitle under the header, this is to handle those.
	private List<String> subTitles = new ArrayList<String>();

	private void setRequest(Request request)
	{
		this.request = request;
	}

	private void setLocale(Locale locale)
	{
		this.locale = locale;
	}

	protected List<String> getColumns()
	{
		return columns;
	}

	protected List<T> getData()
	{
		return data;
	}

	protected List<String> getSubTitles()
	{
		return subTitles;
	}

	protected Locale getLocale()
	{
		return locale;
	}

	protected Request getRequest()
	{
		return request;
	}

	/**
	 * A simple utility method to get the value of an object.toString()
	 * or return a default value if it's null.
	 * 
	 * @param obj the object
	 * @param defVal the default value
	 * @return the obj.toString()
	 */
	protected String getValueAsString(Object obj, String defVal)
	{
		if (obj == null)
		{
			return defVal;
		}

		return obj.toString();
	}

	/**
	 * A simple utility method to get the value of an object.toString() which will be localized
	 * or return a default value if it's null.
	 * 
	 * @param obj the object
	 * @param defVal the default value
	 * @return the obj.toString()
	 */
	protected String getLocalizedValueAsString(Object obj, String defVal)
	{
		String val = getValueAsString(obj, defVal);

		if (val != null)
		{
			return SensusMessageUtil.getMessage(val, null, null, getLocale());
		}

		return null;
	}

	/**
	 * Default constructor, has no request associated with it and uses default Locale.
	 */
	public CSVDataSource()
	{

	}

	/**
	 * Request constructor. If the user context on the request contains locale info, it will
	 * be set as well.
	 * 
	 * @param request
	 */
	public CSVDataSource(Request request)
	{
		setRequest(request);

		if ((request != null) && (request.getUserContext() != null)
				&& (request.getUserContext().getLocaleString() != null))
		{
			setLocale(LocaleUtil.getLocale(request.getUserContext().getLocaleString()));
		}
	}

	/**
	 * Adds a subtitle column.
	 * 
	 * @param col the text for the column.
	 */
	public void addSubTitleColumn(String col)
	{
		subTitles.add(col);
	}

	/**
	 * Adds internationalized column names.
	 * 
	 * @param colNames List of names to add.
	 */
	public void addLocalizedColumns(String... colNames)
	{
		for (String col : colNames)
		{
			addColumn(SensusMessageUtil.getMessage(col, null, null, getLocale()));
		}
	}

	/**
	 * Adds a column to the data source.
	 * 
	 * @param colName The display name of the column.
	 */
	public void addColumn(String colName)
	{
		getColumns().add(colName);
	}

	/**
	 * Adds multiple columns to the data source.
	 * 
	 * @param names The display names of the columns.
	 */
	public void addColumns(String... names)
	{
		for (String name : names)
		{
			getColumns().add(name);
		}
	}

	/**
	 * Adds data to the data source.
	 * 
	 * @param items The data elements to add.
	 */
	public void addData(T... items)
	{
		for (T item : items)
		{
			getData().add(item);
		}
	}

	/**
	 * Adds data to the data source.
	 * 
	 * @param items The data elements to add.
	 */
	public void addData(List<T> items)
	{
		for (T item : items)
		{
			getData().add(item);
		}
	}

	/**
	 * Returns the number of subtitle columns.
	 * 
	 * @return number of columns.
	 */
	public int getNumSubTitleColumns()
	{
		return getSubTitles().size();
	}

	/**
	 * Gets the subtitle column at the index.
	 * 
	 * @param i the index.
	 * @return the column text.
	 */
	public String getSubTitleColumn(int i)
	{
		return getSubTitles().get(i);
	}

	/**
	 * Returns the number of columns.
	 * 
	 * @return number of columns.
	 */
	public int getNumColumns()
	{
		return getColumns().size();
	}

	/**
	 * Returns the column name at the given index.
	 * 
	 * @param i the index.
	 * @return the column name.
	 */
	public String getColumn(int i)
	{
		return getColumns().get(i);
	}

	/**
	 * Returns the number of rows in the data source.
	 * 
	 * @return The number of rows.
	 */
	public int getNumRows()
	{
		return getData().size();
	}

	/**
	 * Returns the row at the given index.
	 * 
	 * @param i The index.
	 * @return The row object.
	 */
	public T getRow(int i)
	{
		return getData().get(i);
	}

	/**
	 * Override this method to provide which data element is given for a specific column.
	 * In most situations, you will not need to override this method, just the getDataForColumn method.
	 * 
	 * @param column The column.
	 * @param row The row.
	 * @return The value for the data.
	 */
	public String getDataAt(int column, int row)
	{
		return getDataForColumn(getData().get(row), column);
	}

	/**
	 * Override this method to provide which data element is given for a specific column.
	 * This method must be overriden, unless getDataAt is overriden.
	 * 
	 * @param object The data object.
	 * @param column The column.
	 * @return The value for the data.
	 */
	protected String getDataForColumn(T object, int column)
	{
		throw new UnsupportedOperationException();
	}
}