package com.sensus.dm.elec.settings.bcl;

import com.sensus.common.model.UserContext;
import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.dm.common.property.model.Property;
import com.sensus.dm.common.property.model.request.PropertyRequest;

/**
 * The Interface ISettingsBCL.
 * 
 * @author - Igor Mendes - QAT Brazil
 */

public interface ISettingsBCL
{

	/**
	 * Upsert system settings.
	 * 
	 * @param propertyRequest the property request
	 * @return the internal response
	 */
	InternalResponse upsertSystemSettings(PropertyRequest propertyRequest);

	/**
	 * Upsert user settings.
	 * 
	 * @param propertyRequest the property request
	 * @return the internal response
	 */
	InternalResponse upsertUserSettings(PropertyRequest propertyRequest);

	/**
	 * Fetch user settings.
	 * 
	 * @param propertyRequest the property request
	 * @return the internal results response
	 */
	InternalResultsResponse<Property> fetchUserSettings(PropertyRequest propertyRequest);

	/**
	 * Fetch system settings.
	 * 
	 * @param propertyRequest the property request
	 * @return the internal results response
	 */
	InternalResultsResponse<Property> fetchSystemSettings(PropertyRequest propertyRequest);

	/**
	 * Fetch all users.
	 * 
	 * @param propertyRequest the property request
	 * @return the internal results response
	 */
	InternalResultsResponse<UserContext> fetchAllUsers(PropertyRequest propertyRequest);

	/**
	 * Insert user.
	 * 
	 * @param propertyRequest the property request
	 * @return the internal response
	 */
	InternalResponse insertUser(PropertyRequest propertyRequest);
}
