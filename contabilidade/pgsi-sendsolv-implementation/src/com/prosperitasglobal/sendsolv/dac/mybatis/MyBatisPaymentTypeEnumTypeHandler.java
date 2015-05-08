package com.prosperitasglobal.sendsolv.dac.mybatis;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import com.prosperitasglobal.sendsolv.model.PaymentTypeEnum;
import com.qat.framework.validation.ValidationUtil;

public class MyBatisPaymentTypeEnumTypeHandler implements TypeHandler<PaymentTypeEnum>
{
	private static final String PAYMENT_TYPE_ENUM_PARAM_NULL = "Payment Type Enum param is Null or Empty";

	private static final String PAYMENT_TYPE_ENUM_PARAM_INVALID_INT_PARAM = "Payment Type Enum invalid int param value";

	private static final String PAYMENT_TYPE_ENUM_PARAM_UNEXPECTED_COLUMN = "Payment Type Enum Unexpected column value";

	private static final String PAYMENT_TYPE_ENUM_PARAMTYPE_NULL = "Payment Type Enum  paramType is Null";

	@Override
	public PaymentTypeEnum getResult(ResultSet rs, String param) throws SQLException
	{
		if (!ValidationUtil.isNullOrEmpty(param))
		{
			return PaymentTypeEnum.enumForValue(rs.getInt(param));
		}
		else
		{
			throw new SQLException(PAYMENT_TYPE_ENUM_PARAM_NULL);
		}
	}

	@Override
	public PaymentTypeEnum getResult(ResultSet rs, int iparam) throws SQLException
	{
		if (!ValidationUtil.isNullOrZero(iparam))
		{
			return PaymentTypeEnum.enumForValue(rs.getInt(iparam));
		}
		else
		{
			throw new SQLException(PAYMENT_TYPE_ENUM_PARAM_INVALID_INT_PARAM);
		}
	}

	@Override
	public PaymentTypeEnum getResult(CallableStatement cs, int col) throws SQLException
	{
		if (!ValidationUtil.isNullOrZero(cs.getInt(col)))
		{
			return PaymentTypeEnum.enumForValue(cs.getInt(col));
		}
		else
		{
			throw new SQLException(PAYMENT_TYPE_ENUM_PARAM_UNEXPECTED_COLUMN);
		}
	}

	@Override
	public void setParameter(PreparedStatement ps, int paramInt, PaymentTypeEnum paramType, JdbcType jdbctype)
			throws SQLException
	{
		if (!ValidationUtil.isNull(paramType))
		{
			ps.setInt(paramInt, paramType.getValue());
		}
		else
		{
			throw new SQLException(PAYMENT_TYPE_ENUM_PARAMTYPE_NULL);
		}
	}
}
