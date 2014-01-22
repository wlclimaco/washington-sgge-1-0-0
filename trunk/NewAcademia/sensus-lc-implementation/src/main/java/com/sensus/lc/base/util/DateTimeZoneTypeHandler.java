package com.sensus.lc.base.util;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import com.sensus.common.util.SensusConvertUtil;
import com.sensus.common.validation.ValidationUtil;

/**
 * The Class DateTimeZoneTypeHandler.
 */
public class DateTimeZoneTypeHandler implements TypeHandler<Object>
{

	private static final String DATE_REGEX_PATTERN_WITH_MILLI =
			"\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}\\.\\d{0,6}([\\+\\-]\\d{2})?";
	private static final String DATABASE_DATE_PATTERN_WITH_MILLI = "yyyy-MM-dd HH:mm:ss.SSS";
	private static final String DATABASE_DATE_PATTERN_WITHOUT_MILLI = "yyyy-MM-dd HH:mm:ss";
	private static final Integer MAX_DATE_LENGHT = 23;

	/*
	 * (non-Javadoc)
	 * @see org.apache.ibatis.type.TypeHandler#setParameter(java.sql.PreparedStatement, int, java.lang.Object,
	 * org.apache.ibatis.type.JdbcType)
	 */
	@Override
	public void setParameter(

			PreparedStatement ps, int i, Object parameter, JdbcType jdbcType)
			throws SQLException
	{
		ps.setString(i, (String)parameter);
	}

	/*
	 * (non-Javadoc)
	 * @see org.apache.ibatis.type.TypeHandler#getResult(java.sql.ResultSet, java.lang.String)
	 */
	@Override
	public Object getResult(ResultSet rs, String columnName)
			throws SQLException
	{
		return SensusConvertUtil.toDate(getStringDate(rs.getString(columnName)),
				getDatePattern(rs.getString(columnName)));
	}

	/*
	 * (non-Javadoc)
	 * @see org.apache.ibatis.type.TypeHandler#getResult(java.sql.CallableStatement, int)
	 */
	@Override
	public Object getResult(CallableStatement cs, int columnIndex)
			throws SQLException
	{
		return SensusConvertUtil.toDate(getStringDate(cs.getString(columnIndex)),
				getDatePattern(cs.getString(columnIndex)));
	}

	/*
	 * (non-Javadoc)
	 * @see org.apache.ibatis.type.TypeHandler#getResult(java.sql.ResultSet, int)
	 */
	@Override
	public Object getResult(ResultSet rs, int integ) throws SQLException
	{
		return SensusConvertUtil.toDate(getStringDate(rs.getString(integ)), getDatePattern(rs.getString(integ)));
	}

	/**
	 * Gets the date pattern.
	 * 
	 * @param dateValue the date value
	 * @return the date pattern
	 */
	private String getDatePattern(String dateValue)
	{
		if (!ValidationUtil.isNull(dateValue) && dateValue.matches(DATE_REGEX_PATTERN_WITH_MILLI))
		{
			return DATABASE_DATE_PATTERN_WITH_MILLI;
		}

		return DATABASE_DATE_PATTERN_WITHOUT_MILLI;
	}

	/**
	 * Gets the string date.
	 * 
	 * @param stringDate the string date
	 * @return the string date
	 */
	private String getStringDate(String stringDate)
	{
		if (!ValidationUtil.isNull(stringDate) && stringDate.length() > MAX_DATE_LENGHT)
		{
			stringDate = StringUtils.substring(stringDate, 0, MAX_DATE_LENGHT);
		}
		return stringDate;
	}
}