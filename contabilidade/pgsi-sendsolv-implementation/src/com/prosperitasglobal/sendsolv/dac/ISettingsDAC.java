package com.prosperitasglobal.sendsolv.dac;

import com.prosperitasglobal.sendsolv.model.request.PagedInquiryRequest;
import com.prosperitasglobal.sendsolv.settings.model.Setting;
import com.prosperitasglobal.sendsolv.settings.model.request.SettingsRequest;
import com.qat.framework.model.response.InternalResultsResponse;

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
	InternalResultsResponse<Setting> fetchUserSettings(PagedInquiryRequest lightingControlRequest);

	/**
	 * Fetch system settings.
	 *
	 * @param lightingControlRequest the lighting control request
	 * @return the internal results response
	 */
	InternalResultsResponse<Setting> fetchSystemSettings(PagedInquiryRequest lightingControlRequest);
}
