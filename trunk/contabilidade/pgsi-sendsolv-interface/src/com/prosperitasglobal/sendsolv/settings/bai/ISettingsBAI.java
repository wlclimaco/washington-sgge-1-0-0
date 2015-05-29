package com.prosperitasglobal.sendsolv.settings.bai;

import com.prosperitasglobal.sendsolv.model.request.PagedInquiryRequest;
import com.prosperitasglobal.sendsolv.settings.model.request.SettingsRequest;
import com.prosperitasglobal.sendsolv.settings.model.response.SettingsResponse;
import com.prosperitasglobal.sendsolv.tenant.model.request.TenantRequest;
import com.prosperitasglobal.sendsolv.tenant.model.response.TenantResponse;

public interface ISettingsBAI
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
	SettingsResponse fetchUserSettings(PagedInquiryRequest settingsRequest);

	/**
	 * Fetch system settings.
	 *
	 * @param lightingControlRequest the lighting control request
	 * @return the settings response
	 */
	SettingsResponse fetchSystemSettings(PagedInquiryRequest settingsRequest);

	/**
	 * Fetch tenant by server name.
	 *
	 * @param tenantRequest the tenant request
	 * @return the tenant response
	 */
	TenantResponse fetchTenantByServerName(TenantRequest tenantRequest);

}
