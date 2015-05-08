package com.prosperitasglobal.sendsolv.dac.mybatis;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import com.prosperitasglobal.sendsolv.model.FrequencyBasedEventTypeEnum;
import com.qat.framework.validation.ValidationUtil;

public class MyBatisFrequencyBasedEventTypeEnumTypeHandler implements TypeHandler<FrequencyBasedEventTypeEnum>
{
	private static final String FREQUENCY_BASED_EVENT_TYPE_ENUM_PARAM_NULL =
			"Frequency Code Enum param is Null or Empty";

	private static final String FREQUENCY_BASED_EVENT_TYPE_ENUM_PARAM_INVALID_INT_PARAM =
			"Frequency Code Enum invalid int param value";

	private static final String FREQUENCY_BASED_EVENT_TYPE_ENUM_PARAM_UNEXPECTED_COLUMN =
			"Frequency Code Enum Unexpected column value";

	private static final String FREQUENCY_BASED_EVENT_TYPE_ENUM_PARAMTYPE_NULL =
			"Frequency Code Enum  paramType is Null";

	@Override
	public FrequencyBasedEventTypeEnum getResult(ResultSet rs, String param) throws SQLException
	{
		if (!ValidationUtil.isNullOrEmpty(param))
		{
			return FrequencyBasedEventTypeEnum.enumForValue(rs.getInt(param));
		}
		else
		{
			throw new SQLException(FREQUENCY_BASED_EVENT_TYPE_ENUM_PARAM_NULL);
		}
	}

	@Override
	public FrequencyBasedEventTypeEnum getResult(ResultSet rs, int iparam) throws SQLException
	{
		if (!ValidationUtil.isNullOrZero(iparam))
		{
			return FrequencyBasedEventTypeEnum.enumForValue(rs.getInt(iparam));
		}
		else
		{
			throw new SQLException(FREQUENCY_BASED_EVENT_TYPE_ENUM_PARAM_INVALID_INT_PARAM);
		}
	}

	@Override
	public FrequencyBasedEventTypeEnum getResult(CallableStatement cs, int col) throws SQLException
	{
		if (!ValidationUtil.isNullOrZero(cs.getInt(col)))
		{
			return FrequencyBasedEventTypeEnum.enumForValue(cs.getInt(col));
		}
		else
		{
			throw new SQLException(FREQUENCY_BASED_EVENT_TYPE_ENUM_PARAM_UNEXPECTED_COLUMN);
		}
	}

	@Override
	public void setParameter(PreparedStatement ps, int paramInt, FrequencyBasedEventTypeEnum paramType,
			JdbcType jdbctype)
			throws SQLException
	{
		if (!ValidationUtil.isNull(paramType))
		{
			ps.setInt(paramInt, paramType.getValue());
		}
		else
		{
			throw new SQLException(FREQUENCY_BASED_EVENT_TYPE_ENUM_PARAMTYPE_NULL);
		}
	}

}
