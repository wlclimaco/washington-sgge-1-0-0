package com.sensus.lc.settings.dac;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.lc.base.model.request.LightingControlRequest;
import com.sensus.lc.light.model.request.ColumnFilterRequest;
import com.sensus.lc.settings.model.Setting;
import com.sensus.lc.settings.model.request.SettingsRequest;
import com.sensus.lc.tenant.model.Tenant;

/**
 * The Interface ISettingsDAC.
 *
 * @author - Igor Mendes - QAT Brazil
 */

/**
 * @author QATEmployee
 * 
 */
public interface ISettingsDAC
{

	/**
	 * Upsert settings.
	 * 
	 * @param settingsRequest the settings request
	 * @return the internal response
	 */
	InternalResultsResponse<Setting> upsertSettings(SettingsRequest settingsRequest);

	/**
	 * Upsert system settings.
	 * 
	 * @param settingsRequest the settings request
	 * @return the internal results response
	 */
	InternalResultsResponse<Setting> upsertSystemSettings(SettingsRequest settingsRequest);

	/**
	 * Fetch user settings.
	 * 
	 * @param lightingControlRequest the lighting control request
	 * @return the internal results response
	 */
	InternalResultsResponse<Setting> fetchUserSettings(LightingControlRequest lightingControlRequest);

	/**
	 * Fetch system settings.
	 * 
	 * @param lightingControlRequest the lighting control request
	 * @return the internal results response
	 */
	InternalResultsResponse<Setting> fetchSystemSettings(LightingControlRequest lightingControlRequest);

	/**
	 * Calculate Map Center.
	 * 
	 * @param tenant
	 */
	void calculateMapCenter(Tenant tenant);

	/**
	 * Insert user columns.
	 * 
	 * @param columnFilterRequest the column filter request
	 * @return the internal response
	 */
	InternalResponse insertUserColumns(ColumnFilterRequest columnFilterRequest);

	/**
	 * Insert user filters.
	 * 
	 * @param columnFilterRequest the column filter request
	 * @return the internal response
	 */
	InternalResponse insertUserFilters(ColumnFilterRequest columnFilterRequest);

	/**
	 * Delete user columns.
	 * 
	 * @param columnFilterRequest the column filter request
	 * @return the internal response
	 */
	InternalResponse deleteUserColumns(ColumnFilterRequest columnFilterRequest);

	/**
	 * Delete user filters.
	 * 
	 * @param columnFilterRequest the column filter request
	 * @return the internal response
	 */
	InternalResponse deleteUserFilters(ColumnFilterRequest columnFilterRequest);
}
