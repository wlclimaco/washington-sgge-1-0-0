package com.sensus.mlc.settings.bcl;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.base.model.request.LightingControlRequest;
import com.sensus.mlc.settings.model.Setting;
import com.sensus.mlc.settings.model.request.SettingsRequest;
import com.sensus.mlc.smartpoint.model.request.ColumnFilterRequest;
import com.sensus.mlc.tenant.model.Tenant;
import com.sensus.mlc.tenant.model.request.TenantRequest;

/**
 * The Interface ISmartPointDAC.
 *
 * @author - Igor Mendes - QAT Brazil
 */

public interface ISettingsBCL
{

	/**
	 * Upsert settings.
	 *
	 * @param settingsRequest the settings request
	 * @return the internal response
	 */
	InternalResultsResponse<Setting> upsertSettings(SettingsRequest settingsRequest);

	/**
	 * Update system settings.
	 *
	 * @param settingsRequest the settings request
	 * @return the internal response
	 */
	InternalResultsResponse<Setting> updateSystemSettings(SettingsRequest settingsRequest);

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
	 * Fetch tenant by server name.
	 *
	 * @param tenantRequest the tenant request
	 * @return the internal results response
	 */
	InternalResultsResponse<Tenant> fetchTenantByServerName(TenantRequest tenantRequest);

	/**
	 * Insert user column filter.
	 *
	 * @param columnFilterRequest the column filter request
	 * @return the column filter response
	 */
	InternalResponse insertUserColumnFilter(ColumnFilterRequest columnFilterRequest);

	/**
	 * Calculate map center.
	 *
	 * @param tenant the tenant
	 */
	void calculateMapCenter(Tenant tenant);

	/**
	 * Delete old data.
	 *
	 * @param tenant the tenant
	 */
	void deleteOldData(Tenant tenant);
}
