package com.sensus.dm.elec.settings.bcf;

import com.sensus.dm.common.property.model.request.PropertyRequest;
import com.sensus.dm.common.property.model.response.PropertyResponse;
import com.sensus.dm.elec.settings.model.response.InquiryUserResponse;

/**
 * The Interface ISettingsBCF.
 * 
 * @author - Igor Mendes - QAT Brazil
 */

public interface ISettingsBCF
{

	/**
	 * Fetch user settings.
	 * 
	 * @param propertyRequest the property request
	 * @return the property response
	 */
	PropertyResponse fetchUserSettings(PropertyRequest propertyRequest);

	/**
	 * Fetch system settings.
	 * 
	 * @param propertyRequest the property request
	 * @return the property response
	 */
	PropertyResponse fetchSystemSettings(PropertyRequest propertyRequest);

	/**
	 * Fetch all users.
	 * 
	 * @param propertyRequest the property request
	 * @return the property user response
	 */
	InquiryUserResponse fetchAllUsers(PropertyRequest propertyRequest);

	/**
	 * Upsert system settings.
	 * 
	 * @param propertyRequest the property request
	 * @return the property response
	 */
	PropertyResponse upsertSystemSettings(PropertyRequest propertyRequest);

	/**
	 * Upsert user settings.
	 * 
	 * @param propertyRequest the property request
	 * @return the property response
	 */
	PropertyResponse upsertUserSettings(PropertyRequest propertyRequest);

	/**
	 * Insert user.
	 * 
	 * @param request the request
	 * @return the response
	 */
	PropertyResponse insertUser(PropertyRequest propertyRequest);

}
