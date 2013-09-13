package com.sensus.common.csv;

import org.supercsv.cellprocessor.ift.CellProcessor;

/**
 * Used to hold the definition or meta-data about a CSV column. Typically inflated from Spring configuration.
 */
public class CSVColumn
{
	/** The name. Also a unique identifier within a collection. */
	private String name;

	/** The header. */
	private String header;

	/** The resource key. */
	private String headerResourceKey;

	/** The property path. */
	private String propertyPath;

	/** The property path setter. */
	private String propertyPathSetter;

	/** The read cell processor. */
	private CellProcessor readCellProcessor;

	/** The write cell processor. */
	private CellProcessor writeCellProcessor;

	/**
	 * Gets the name. This should be unique within a given collection of CSVColumn's.
	 *
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * Gets the header.
	 *
	 * @return the header
	 */
	public String getHeader()
	{
		return header;
	}

	/**
	 * Sets the header.
	 *
	 * @param header the new header
	 */
	public void setHeader(String header)
	{
		this.header = header;
	}

	/**
	 * Gets the header resource key.
	 * This property is optional and only required when you wish to internationalize the CSV column header.
	 *
	 * @return the header resource key
	 */
	public String getHeaderResourceKey()
	{
		return headerResourceKey;
	}

	/**
	 * Sets the header resource key.
	 * This property is optional and only required when you wish to internationalize the CSV column header.
	 *
	 * @param headerResourceKey the new header resource key
	 */
	public void setHeaderResourceKey(String headerResourceKey)
	{
		this.headerResourceKey = headerResourceKey;
	}

	/**
	 * Gets the property path to the value of this column within an object graph.
	 * Deep mapping is supported. IE: person.address.city
	 * Note this is also known as the default property path since it's possible you would want to use two different
	 * paths, one for the getter and one for the setter. If the propertyPathSetter property is null then this value is
	 * returned. An example of this might be for enumerations.
	 *
	 * @return the property path
	 */
	public String getPropertyPath()
	{
		return propertyPath;
	}

	/**
	 * Sets the property path.
	 * Deep mapping is supported. IE: person.address.city
	 *
	 * @param propertyPath the new property path
	 */
	public void setPropertyPath(String propertyPath)
	{
		this.propertyPath = propertyPath;
	}

	/**
	 * Gets the read cell processor.
	 * Used when reading a CSV file and inflating an object.
	 * See the Javadoc for the CellProcessor for more information.
	 *
	 * @return the read cell processor
	 */
	public CellProcessor getReadCellProcessor()
	{
		return readCellProcessor;
	}

	/**
	 * Sets the read cell processor.
	 * Used when reading a CSV file and inflating an object.
	 * See the Javadoc for the CellProcessor for more information.
	 *
	 * @param readCellProcessor the new read cell processor
	 */
	public void setReadCellProcessor(CellProcessor readCellProcessor)
	{
		this.readCellProcessor = readCellProcessor;
	}

	/**
	 * Gets the write cell processor.
	 * Used when writing a CSV file and inflating an object.
	 * See the Javadoc for the CellProcessor for more information.
	 *
	 * @return the write cell processor
	 */
	public CellProcessor getWriteCellProcessor()
	{
		return writeCellProcessor;
	}

	/**
	 * Sets the write cell processor.
	 * Used when writing a CSV file and inflating an object.
	 * See the Javadoc for the CellProcessor for more information.
	 *
	 * @param writeCellProcessor the new write cell processor
	 */
	public void setWriteCellProcessor(CellProcessor writeCellProcessor)
	{
		this.writeCellProcessor = writeCellProcessor;
	}

	/**
	 * Gets the property path for the setter if different from the default propertyPath used for getter.
	 * If this property is null then the default propertyPath will be returned.
	 *
	 * @return the property path setter
	 */
	public String getPropertyPathSetter()
	{
		if (propertyPathSetter == null)
		{
			return getPropertyPath();
		}

		return propertyPathSetter;
	}

	/**
	 * Sets the property path for the setter if different from default propertyPath used for getter.
	 *
	 * @param propertyPathSetter the new property path setter
	 */
	public void setPropertyPathSetter(String propertyPathSetter)
	{
		this.propertyPathSetter = propertyPathSetter;
	}

}
