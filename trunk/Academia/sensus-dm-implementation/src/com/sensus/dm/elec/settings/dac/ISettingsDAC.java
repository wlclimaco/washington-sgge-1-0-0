package com.sensus.dm.elec.settings.dac;

import com.sensus.common.model.UserContext;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.dm.common.property.model.request.PropertyRequest;

/**
 * The Interface ISettingsDAC.
 * 
 * @author - Igor Mendes - QAT Brazil
 */

public interface ISettingsDAC
{

	/**
	 * Fetch all user.
	 * 
	 * @param propertyRequest the property request
	 * @return the internal results response
	 */
	InternalResultsResponse<UserContext> fetchAllUser(PropertyRequest propertyRequest);

	/**
	 * Insert user.
	 * 
	 * @param propertyRequest the property request
	 * @return the internal results response
	 */
	InternalResponse insertUser(PropertyRequest propertyRequest);

}
