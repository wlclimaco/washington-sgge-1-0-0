package com.sensus.lc.settings.bcl;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.lc.base.model.request.LightingControlRequest;
import com.sensus.lc.light.model.request.ColumnFilterRequest;
import com.sensus.lc.settings.model.Setting;
import com.sensus.lc.settings.model.request.SettingsRequest;
import com.sensus.lc.tenant.model.Tenant;
import com.sensus.lc.tenant.model.request.TenantRequest;

/**
 * The Interface ISettingsBCL.
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
}
