package com.prosperitasglobal.sendsolv.unittest.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import com.qat.framework.util.QATDateUtil;

/**
 * CommonRoutinesTest junit. For converting from Long to Date and Date to Long.
 */
public class ConvertUTCDateToDateTest
{
	/** Test convert from Long to Date. */
	@Test
	public void testDateConvert()
	{
		Long dateUTC = new Long("1450332000000");
		// Long dateUTC = new Long("1419228000000");
		Date date = new Date(dateUTC);
		System.out.println("UTC Date [" + dateUTC + "] is date [" + date.toString() + "] ");
	}

	/** Test convert from Date to Long. */
	@Test
	public void testDateConvert2()
	{
		String stringDate = null;

		try
		{
			String dateFormat = "yyyy-MM-dd";
			SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
			formatter.setLenient(false);
			stringDate = "2014-12-22";
			Date date = formatter.parse(stringDate);
			System.out.println("Date [" + stringDate + "] is long [" + QATDateUtil.truncateTime(date.getTime()) + "]");
		}
		catch (ParseException ex)
		{
			System.out.println("Exception thrown trying to convert the date [" + stringDate
					+ "] to a type Long.  Date needs to be in format yyyy-mm-dd, message=" + ex.getMessage());
		}
	}
}
