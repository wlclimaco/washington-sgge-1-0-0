package com.sensus.dm.elec.settings.dac;

import com.sensus.common.model.UserContext;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.dm.common.property.model.request.PropertyRequest;

/**
 * The Class MockSettingsDAC.
 * 
 * @author QAT Global.
 */
public class MockSettingsDAC implements ISettingsDAC
{

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.dm.elec.settings.dac.ISettingsDAC#fetchAllUser(com.sensus.dm.elec.settings.model.request.PropertyRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<UserContext> fetchAllUser(PropertyRequest propertyRequest)
	{
		UserContext user = new UserContext();
		user.setUserId("User Id");
		InternalResultsResponse<UserContext> internalResultsResponse = new InternalResultsResponse<UserContext>();
		internalResultsResponse.addResult(user);

		return internalResultsResponse;
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
		return new InternalResponse();
	}

}
