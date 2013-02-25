package com.sensus.mlc.settings.bcf;

import com.sensus.mlc.base.model.request.LightingControlRequest;
import com.sensus.mlc.settings.model.request.SettingsRequest;
import com.sensus.mlc.settings.model.response.SettingsResponse;
import com.sensus.mlc.smartpoint.model.request.ColumnFilterRequest;
import com.sensus.mlc.smartpoint.model.response.ColumnFilterResponse;
import com.sensus.mlc.tenant.model.request.TenantRequest;
import com.sensus.mlc.tenant.model.response.TenantResponse;

/**
 * The Interface ISmartPointDAC.
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
	 * Update system settings.
	 *
	 * @param settingsRequest the settings request
	 * @return the settings response
	 */
	SettingsResponse updateSystemSettings(SettingsRequest settingsRequest);

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
