package com.sensus.lc.light.dac.mybatis.typehandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.lc.light.dac.IPartNumberConfigurationDAC;
import com.sensus.lc.light.model.PartNumberConfiguration;

/**
 * The Class DateTimeZoneTypeHandler.
 */
public class PartNumberConfigurationTypeHandler implements TypeHandler<Object>
{
	private static final Logger LOG = LoggerFactory.getLogger(PartNumberConfigurationTypeHandler.class);

	private static IPartNumberConfigurationDAC partNumberConfigurationDAC;

	@Override
	public Object getResult(ResultSet rs, String columnName) throws SQLException
	{
		String modelNumber = rs.getString(columnName);

		return fetchPartNumberConfigurationList(modelNumber);
	}

	/**
	 * Fetch part number configuration list.
	 * 
	 * @param modelNumber the model number
	 * @return the list
	 */
	private List<PartNumberConfiguration> fetchPartNumberConfigurationList(String modelNumber)
	{
		if (ValidationUtil.isNullOrEmpty(modelNumber))
		{
			LOG.error("Model-Number is null");
			return null;
		}

		InternalResultsResponse<PartNumberConfiguration> response =
				partNumberConfigurationDAC.fetchPartNumberConfigurationsByModelNumber(modelNumber);

		if (response.isInError())
		{
			LOG.error("Unable to retieve part number by model number(" + modelNumber + ")");
			LOG.error(response.toString() + response.getMessageInfoList().toString());
			return null;
		}

		return response.getResultsList();
	}

	@Override
	public Object getResult(CallableStatement cs, int columnIndex) throws SQLException
	{
		String modelNumber = cs.getString(columnIndex);

		return fetchPartNumberConfigurationList(modelNumber);
	}

	@Override
	public Object getResult(ResultSet rs, int integ) throws SQLException
	{
		String modelNumber = rs.getString(integ);

		return fetchPartNumberConfigurationList(modelNumber);
	}

	public IPartNumberConfigurationDAC getPartNumberConfigurationDAC()
	{
		return partNumberConfigurationDAC;
	}

	public void setPartNumberConfigurationDAC(IPartNumberConfigurationDAC partNumberConfigurationDAC)
	{
		PartNumberConfigurationTypeHandler.partNumberConfigurationDAC = partNumberConfigurationDAC;
	}

	@Override
	public void setParameter(PreparedStatement arg0, int arg1, Object arg2, JdbcType arg3) throws SQLException
	{
		throw new SQLException("Not Supported in this type-handler.");
	}
}