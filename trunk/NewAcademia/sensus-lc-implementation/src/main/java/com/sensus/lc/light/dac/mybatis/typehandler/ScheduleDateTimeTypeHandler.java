package com.sensus.lc.light.dac.mybatis.typehandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import com.sensus.common.util.SensusConvertUtil;

public class ScheduleDateTimeTypeHandler implements TypeHandler<Object>
{
	private static final String TIME_FORMAT = "HH:mm:ss";

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
		String time = rs.getString(columnName);
		return SensusConvertUtil.toDate(time, TIME_FORMAT);
	}

	@Override
	public Object getResult(CallableStatement cs, int columnIndex)
			throws SQLException
	{
		String time = cs.getString(columnIndex);
		return SensusConvertUtil.toDate(time, TIME_FORMAT);
	}

	@Override
	public Object getResult(ResultSet rs, int integ) throws SQLException
	{
		String time = rs.getString(integ);
		return SensusConvertUtil.toDate(time, TIME_FORMAT);
	}
}