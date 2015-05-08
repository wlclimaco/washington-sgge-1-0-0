package com.prosperitasglobal.sendsolv.sdn.dac.mybatis;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import com.prosperitasglobal.sendsolv.sdn.bai.ISdnCheckerBAI;
import com.prosperitasglobal.sendsolv.sdn.model.SDNStatusEnum;
import com.prosperitasglobal.sendsolv.sdn.model.SdnMatchTypeEnum;
import com.prosperitasglobal.sendsolv.sdn.model.SdnStatusHistory;
import com.prosperitasglobal.sendsolv.sdn.model.request.SdnStatusHistoryRequest;
import com.prosperitasglobal.sendsolv.sdn.model.response.SdnStatusHistoryResponse;
import com.qat.framework.validation.ValidationUtil;

/**
 * The Class MyBatisCurrentSDNStatusEnumTypeHandler.
 */
@SuppressWarnings("rawtypes")
public class MyBatisCurrentSDNStatusEnumTypeHandler implements TypeHandler
{

	/** The Constant MISSING_COLUMN_NAME_DEFINITION_IN_MY_BATIS_XML. */
	private static final String MISSING_COLUMN_NAME_DEFINITION_IN_MY_BATIS_XML =
			"It must have the column name when defining the property sdnStatus in MyBatis XML.";

	/** The Constant BUSINESS_ID. */
	private static final String BUSINESS_ID = "business_id";

	/** The Constant PERSON_ID. */
	private static final String PERSON_ID = "person_id";

	/** The Constant PROBLEMS_TO_RETRIEVE_CURRENT_SDN_STATUS. */
	private static final String PROBLEMS_TO_RETRIEVE_CURRENT_SDN_STATUS =
			"Problems were found trying to retrieve the current SDN status.";

	/** The sdn checker bai. */
	private static ISdnCheckerBAI sdnCheckerBAI;

	/**
	 * Gets the sdn checker bai.
	 *
	 * @return the sdnCheckerBAI
	 */
	public ISdnCheckerBAI getSdnCheckerBAI()
	{
		return sdnCheckerBAI;
	}

	/**
	 * Sets the sdn checker bai.
	 *
	 * @param value the new entity sdnCheckerBAI to set
	 */
	public void setSdnCheckerBAI(ISdnCheckerBAI value)
	{
		sdnCheckerBAI = value;
	}

	@Override
	public SDNStatusEnum getResult(ResultSet resultSet, String columnName) throws SQLException
	{
		if (ValidationUtil.isNullOrEmpty(columnName))
		{
			throw new SQLException("Unexpected value [" + resultSet.getString(columnName) + "]");
		}

		if (ValidationUtil.isNull(getSdnCheckerBAI()))
		{
			throw new SQLException("SdnCheckerBAI was not injected in " + this.getClass().getName());
		}

		SdnStatusHistoryResponse response =
				getSdnCheckerBAI().fetchCurrentSdnStatusHistory(
						createSdnStatusHistoryRequest(resultSet.getInt(columnName), columnName));

		if (!response.isOperationSuccess())
		{
			throw new SQLException(PROBLEMS_TO_RETRIEVE_CURRENT_SDN_STATUS);
		}

		// -- if there is no result it means there is a bug, with that, the UNKNOWN status will be returned.
		if (ValidationUtil.isNullOrEmpty(response.getSdnStatusHistoryList()) ||
				ValidationUtil.isNull(response.getSdnStatusHistoryList().get(0)))
		{
			return SDNStatusEnum.UNKNOWN;
		}

		return response.getSdnStatusHistoryList().get(0).getSdnStatus();
	}

	@Override
	public SDNStatusEnum getResult(ResultSet resultSet, int columnIndex) throws SQLException
	{
		if (ValidationUtil.isNullOrZero(columnIndex))
		{
			throw new SQLException("Unexpected value [" + resultSet.getString(columnIndex) + "]");
		}

		throw new SQLException(MISSING_COLUMN_NAME_DEFINITION_IN_MY_BATIS_XML);

	}

	@Override
	public SDNStatusEnum getResult(CallableStatement callableStatement, int columnIndex) throws SQLException
	{
		if (ValidationUtil.isNullOrZero(columnIndex))
		{
			throw new SQLException("Unexpected value [" + callableStatement.getString(columnIndex) + "]");
		}

		throw new SQLException(MISSING_COLUMN_NAME_DEFINITION_IN_MY_BATIS_XML);
	}

	@Override
	public void setParameter(PreparedStatement ps, int paramInt, Object paramType,
			JdbcType jdbctype) throws SQLException
	{
		ps.setObject(paramInt, paramType);
	}

	/**
	 * Create the {@link SdnStatusHistoryRequest} that will be used to retrieve the current sdn status.
	 *
	 * @param parentKey - Id of Organization/Location/Member/Recipient or Liaison
	 * @param columnName the column name
	 * @return SdnStatusHistoryRequest
	 */
	private SdnStatusHistoryRequest createSdnStatusHistoryRequest(Integer parentKey, String columnName)
	{
		SdnStatusHistoryRequest request = new SdnStatusHistoryRequest();

		// -- using the column name we are going to decide which SdnMatchTypeEnum is going to be:
		// -- person_id => Member, Recipient or Liaison
		// -- business_id => Organization or Location
		if (columnName.equals(PERSON_ID))
		{
			request.setMatchType(SdnMatchTypeEnum.INDIVIDUAL);
		}
		else if (columnName.equals(BUSINESS_ID))
		{
			request.setMatchType(SdnMatchTypeEnum.ENTITY);
		}

		// -- parent key from Business or Person that will be retrieved
		SdnStatusHistory sdnStatusHistory = new SdnStatusHistory();
		sdnStatusHistory.setParentKey(parentKey);
		request.setSdnStatusHistory(sdnStatusHistory);

		return request;
	}

}
