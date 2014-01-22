package com.sensus.lc.base.util;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import com.sensus.common.util.SensusConvertUtil;

/**
 * The Class DateTimeZoneTypeHandler.
 */
public class DateZoneTypeHandler implements TypeHandler<Object>
{

	/** The Constant DATABASE_DATE_PATTERN. */
	private static final String DATABASE_DATE_PATTERN = "yyyy-MM-dd";

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
		return SensusConvertUtil.toDate(rs.getString(columnName), DATABASE_DATE_PATTERN);
	}

	/*
	 * (non-Javadoc)
	 * @see org.apache.ibatis.type.TypeHandler#getResult(java.sql.CallableStatement, int)
	 */
	@Override
	public Object getResult(CallableStatement cs, int columnIndex)
			throws SQLException
	{
		return SensusConvertUtil.toDate(cs.getString(columnIndex), DATABASE_DATE_PATTERN);
	}

	/*
	 * (non-Javadoc)
	 * @see org.apache.ibatis.type.TypeHandler#getResult(java.sql.ResultSet, int)
	 */
	@Override
	public Object getResult(ResultSet rs, int integ) throws SQLException
	{
		return SensusConvertUtil.toDate(rs.getString(integ), DATABASE_DATE_PATTERN);
	}
}