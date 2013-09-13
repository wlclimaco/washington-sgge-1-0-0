package com.sensus.dm.elec.settings.dac.mybatis;

import java.util.HashMap;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.sensus.common.model.Message;
import com.sensus.common.model.UserContext;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResponse.Status;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.util.SensusMyBatisDacHelper;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.dm.common.property.model.request.PropertyRequest;
import com.sensus.dm.elec.settings.dac.ISettingsDAC;

/**
 * The Class SettingsDACImpl.
 */
public class SettingsDACImpl extends SqlSessionDaoSupport implements ISettingsDAC
{

	/** The Constant CUSTOMER_ID. */
	private static final String CUSTOMER_ID = "customer_id";

	/** The Constant PARAMSIZE1. */
	private static final Integer PARAMSIZE1 = 1;

	/** The Constant PARAMSIZE2. */
	private static final Integer PARAMSIZE2 = 2;

	/** The Constant USER. */
	private static final String USER = "user";

	/** The Constant ADD_USER_FAILED. */
	private static final String ADD_USER_FAILED = "sensus.epm.actionbclimpl.add.user.failed";

	/** The Constant SETTINGS_MAP. */
	private static final String SETTINGS_MAP = "SettingsMap.";

	/** The Constant SETTINGS_MAP_FETCH_ALL_USERS. */
	private static final String SETTINGS_MAP_FETCH_ALL_USERS = SETTINGS_MAP + "fetchAllUsers";

	/** The Constant SETTINGS_MAP_INSERT_USER. */
	private static final String SETTINGS_MAP_INSERT_USER = SETTINGS_MAP + "insertUser";

	@SuppressWarnings("unchecked")
	@Override
	public InternalResultsResponse<UserContext> fetchAllUser(PropertyRequest propertyRequest)
	{
		Map<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE1);
		paramMap.put(CUSTOMER_ID, propertyRequest.getTenant().getKey());

		InternalResultsResponse<UserContext> response = new InternalResultsResponse<UserContext>();

		response.addResults(
				SensusMyBatisDacHelper.doQueryForList(
						getSqlSession(), SETTINGS_MAP_FETCH_ALL_USERS, paramMap));

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.settings.dac.ISettingsDAC#insertUser(com.sensus.dm.common.property.model.request.PropertyRequest
	 * )
	 */
	@Override
	public InternalResponse insertUser(PropertyRequest propertyRequest)
	{
		Map<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE2);
		paramMap.put(USER, propertyRequest.getUserContext().getUserId());
		paramMap.put(CUSTOMER_ID, propertyRequest.getTenant().getKey());

		Integer result =
				(Integer)SensusMyBatisDacHelper.doQueryForObject(getSqlSession(), SETTINGS_MAP_INSERT_USER, paramMap);

		InternalResponse internalResponse = new InternalResponse();

		if (!ValidationUtil.isNull(result) && (result < 0))
		{
			internalResponse.addMessage(ADD_USER_FAILED, Message.MessageSeverity.Error, Message.MessageLevel.Other);
			internalResponse.setStatus(Status.ExceptionError);
		}

		return internalResponse;
	}
}
