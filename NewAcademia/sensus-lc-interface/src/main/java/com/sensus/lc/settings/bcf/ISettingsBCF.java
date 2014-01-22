package com.sensus.lc.settings.bcf;

import com.sensus.lc.base.model.request.LightingControlRequest;
import com.sensus.lc.light.model.request.ColumnFilterRequest;
import com.sensus.lc.light.model.response.ColumnFilterResponse;
import com.sensus.lc.settings.model.request.SettingsRequest;
import com.sensus.lc.settings.model.response.SettingsResponse;
import com.sensus.lc.tenant.model.request.TenantRequest;
import com.sensus.lc.tenant.model.response.TenantResponse;

/**
 * The Interface ISettingsBCF.
 *
 * @author - Igor Mendes - QAT Brazil
 */

public interface ISettingsBCF
{

	/**
	 * Upsert settings.
	 *
	 * @param settingsRequest the settings request
	 * @return the settings response
	 */
	SettingsResponse upsertSettings(SettingsRequest settingsRequest);

	/**
	 * Upsert system settings.
	 *
	 * @param settingsRequest the settings request
	 * @return the settings response
	 */
	SettingsResponse upsertSystemSettings(SettingsRequest settingsRequest);

	/**
	 * Fetch user settings.
	 *
	 * @param lightingControlRequest the lighting control request
	 * @return the settings response
	 */
	SettingsResponse fetchUserSettings(LightingControlRequest lightingControlRequest);

	/**
	 * Fetch system settings.
	 *
	 * @param lightingControlRequest the lighting control request
	 * @return the settings response
	 */
	SettingsResponse fetchSystemSettings(LightingControlRequest lightingControlRequest);

	/**
	 * Fetch tenant by server name.
	 *
	 * @param tenantRequest the tenant request
	 * @return the tenant response
	 */
	TenantResponse fetchTenantByServerName(TenantRequest tenantRequest);

	/**
	 * Insert user column filter.
	 *
	 * @param columnFilterRequest the column filter request
	 * @return the column filter response
	 */
	ColumnFilterResponse insertUserColumnFilter(ColumnFilterRequest columnFilterRequest);

}
