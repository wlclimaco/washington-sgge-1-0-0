package com.qat.samples.complexmo.dac.mybatis;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qat.framework.validation.ValidationUtil;
import com.qat.samples.complexmo.bai.IStateBAI;
import com.qat.samples.complexmo.model.State;
import com.qat.samples.complexmo.model.request.StateRequest;
import com.qat.samples.complexmo.model.response.StateResponse;

/**
 * The Class MybatisStateTypeHandler.
 */
public class MybatisStateTypeHandler implements TypeHandler
{

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(MybatisStateTypeHandler.class);

	/** The state bai. */
	private static IStateBAI stateBAI;

	/**
	 * Instantiates a new sysmgmt mybatis state type handler.
	 */
	public MybatisStateTypeHandler()
	{
	}

	/**
	 * Gets the entity state bai.
	 * 
	 * @return the entity state bai
	 */
	public IStateBAI getEntityStateBAI()
	{
		return stateBAI;
	}

	/**
	 * Sets the entity state bai.
	 * 
	 * @param value the new entity state bai
	 */
	public void setEntityStateBAI(IStateBAI value)
	{
		stateBAI = value;
	}

	@Override
	public Object getResult(ResultSet resultSet, String columnName) throws SQLException
	{
		LOG.debug("getResult: " + resultSet.getString(columnName));
		StateRequest request = new StateRequest();
		State state = new State();
		state.setCode(resultSet.getString(columnName));
		request.setState(state);
		if (!ValidationUtil.isNullOrEmpty(request.getState().getCode()))
		{
			StateResponse response = getEntityStateBAI().fetchStateByCode(request);
			return response.getState();
		}
		else
		{
			throw new SQLException("Unexpected value [" + resultSet.getString(columnName) + "] ");
		}
	}

	@Override
	public Object getResult(ResultSet resultSet, int columnIndex) throws SQLException
	{
		LOG.debug("getResult2: " + resultSet.getString(columnIndex));
		StateRequest request = new StateRequest();
		State state = new State();
		state.setCode(resultSet.getString(columnIndex));
		request.setState(state);
		if (!ValidationUtil.isNullOrEmpty(request.getState().getCode()))
		{
			StateResponse response = getEntityStateBAI().fetchStateByCode(request);
			return response.getState();
		}
		else
		{
			throw new SQLException("Unexpected value [" + resultSet.getString(columnIndex) + "] ");
		}
	}

	@Override
	public Object getResult(CallableStatement callableStatement, int columnIndex) throws SQLException
	{
		LOG.debug("getResult3: " + callableStatement.getString(columnIndex));
		StateRequest request = new StateRequest();
		State state = new State();
		state.setCode(callableStatement.getString(columnIndex));
		request.setState(state);
		if (!ValidationUtil.isNullOrEmpty(request.getState().getCode()))
		{
			StateResponse response = getEntityStateBAI().fetchStateByCode(request);
			return response.getState();
		}
		else
		{
			throw new SQLException("Unexpected value [" + callableStatement.getString(columnIndex) + "] ");
		}
	}

	@Override
	public void setParameter(PreparedStatement preparedStatement, int i, Object parameter, JdbcType jdbcType)
			throws SQLException
	{
		State state = (State)parameter;
		LOG.debug("setParameter: " + state.getCode());
		preparedStatement.setObject(i, state.getCode());
	}

	/**
	 * Casts the string representation of a value into a type recognized by this type handler.
	 * 
	 * @param stateCode the state code
	 * @return the object
	 */
	public Object valueOf(String stateCode)
	{
		StateRequest request = new StateRequest();
		State state = new State();
		state.setCode(stateCode);
		request.setState(state);
		StateResponse response = getEntityStateBAI().fetchStateByCode(request);
		return response.getState();
	}

}
