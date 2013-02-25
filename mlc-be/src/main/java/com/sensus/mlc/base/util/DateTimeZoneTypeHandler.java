package com.sensus.mlc.base.util;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

public class DateTimeZoneTypeHandler implements TypeHandler<Object>
{
	@Override
	public void setParameter(

			PreparedStatement ps, int i, Object parameter, JdbcType jdbcType)
			throws SQLException
	{
		ps.setString(i, (String)parameter);
	}

	@Override
	public Object getResult(ResultSet rs, String columnName)
			throws SQLException
	{
		Date date = rs.getTimestamp(columnName);
		return LCDateUtil.convertDateToDefaultUTC(date);
	}

	@Override
	public Object getResult(CallableStatement cs, int columnIndex)
			throws SQLException
	{
		Date date = cs.getTimestamp(columnIndex);
		return LCDateUtil.convertDateToDefaultUTC(date);
	}

	@Override
	public Object getResult(ResultSet rs, int integ) throws SQLException
	{
		Date date = rs.getTimestamp(integ);
		return LCDateUtil.convertDateToDefaultUTC(date);
	}
}