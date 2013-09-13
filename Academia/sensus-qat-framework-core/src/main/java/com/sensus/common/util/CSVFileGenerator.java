package com.sensus.common.util;

import java.io.FileWriter;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.com.bytecode.opencsv.CSVWriter;

import com.sensus.common.model.Message.MessageLevel;
import com.sensus.common.model.Message.MessageSeverity;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResponse.Status;

/**
 * Generates a CSV file from the given CSVDataSource.
 * 
 * @author rpulley
 */
public final class CSVFileGenerator
{
	private static final String CSV_ERROR = "qat.framework.csv.exception";

	private static final Logger LOG = LoggerFactory.getLogger(CSVFileGenerator.class);

	/**
	 * Private constructor so instances can't be made.
	 */
	private CSVFileGenerator()
	{

	}

	/**
	 * Creates a CSV File in the given location with the given data source.
	 * 
	 * @param fileName Where the file is created.
	 * @param ds The source of the data for the CSV file.
	 * @param response if the response is not null, error info will be provided.
	 * @return
	 */
	public static boolean create(String fileName, CSVDataSource<?> ds, InternalResponse response)
	{
		CSVWriter writer = null;
		FileWriter fw = null;

		try
		{
			fw = new FileWriter(fileName);
			writer = new CSVWriter(fw);

			writeHeader(writer, ds);
			writeSubTitleHeader(writer, ds);
			writeData(writer, ds);

			writer.close();

			return true;
		}
		catch (Throwable e)
		{
			LOG.error("CSVFileGenerator error", e);

			if (response != null)
			{
				response.setStatus(Status.ExceptionError);
				response.addMessage(CSV_ERROR, MessageSeverity.Error, MessageLevel.None, new Object[] {e});
			}
		}
		finally
		{
			IOUtils.closeQuietly(fw);

			// This project isn't using the latest IOUtils, so Closeable isn't supported
			try
			{
				writer.close();
			}
			catch (IOException ignore)
			{
				// just ignore
			}
		}

		return false;
	}

	/**
	 * Writes the data.
	 * 
	 * @param writer the writer.
	 * @param ds the data source.
	 */
	private static void writeData(CSVWriter writer, CSVDataSource<?> ds)
	{
		for (int row = 0; row < ds.getNumRows(); row++)
		{
			String[] rowData = new String[ds.getNumColumns()];

			for (int col = 0; col < ds.getNumColumns(); col++)
			{
				rowData[col] = ds.getDataAt(col, row);
			}

			writer.writeNext(rowData);
		}
	}

	/**
	 * Writes the header as the first row.
	 * 
	 * @param writer the writer.
	 * @param ds the data source.
	 */
	private static void writeHeader(CSVWriter writer, CSVDataSource<?> ds)
	{
		String[] header = new String[ds.getNumColumns()];

		for (int i = 0; i < ds.getNumColumns(); i++)
		{
			header[i] = ds.getColumn(i);
		}

		writer.writeNext(header);
	}

	/**
	 * Writes the subtitle header as the second row if data exists.
	 * 
	 * @param writer the writer.
	 * @param ds the data source.
	 */
	private static void writeSubTitleHeader(CSVWriter writer, CSVDataSource<?> ds)
	{
		String[] subheader = new String[ds.getNumSubTitleColumns()];

		if (subheader.length == 0)
		{
			return;
		}

		for (int i = 0; i < ds.getNumSubTitleColumns(); i++)
		{
			subheader[i] = ds.getSubTitleColumn(i);
		}

		writer.writeNext(subheader);
	}
}
