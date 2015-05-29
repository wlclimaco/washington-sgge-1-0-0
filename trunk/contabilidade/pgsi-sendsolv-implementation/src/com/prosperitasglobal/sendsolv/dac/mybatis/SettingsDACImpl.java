package com.prosperitasglobal.sendsolv.dac.mybatis;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.prosperitasglobal.sendsolv.dac.ISettingsDAC;
import com.prosperitasglobal.sendsolv.model.request.PagedInquiryRequest;
import com.prosperitasglobal.sendsolv.settings.model.Setting;
import com.prosperitasglobal.sendsolv.settings.model.request.SettingsRequest;
import com.qat.framework.model.response.InternalResultsResponse;

/**
 * The Class SettingsDACImpl.
 */
public class SettingsDACImpl extends SqlSessionDaoSupport implements ISettingsDAC
{

	/** The Constant SETTINGS_DELETE_FILTERS_TO_USER. */
	private static final String SETTINGS_DELETE_FILTERS_TO_USER = "Settings.deleteFiltersToUser";

	/** The Constant SETTINGS_DELETE_COLUMNS_TO_USER. */
	private static final String SETTINGS_DELETE_COLUMNS_TO_USER = "Settings.deleteColumnsToUser";

	/** The Constant SETTINGS_INSERT_FILTERS_TO_USER. */
	private static final String SETTINGS_INSERT_FILTERS_TO_USER = "Settings.insertFiltersToUser";

	/** The Constant SETTINGS_INSERT_COLUMNS_TO_USER. */
	private static final String SETTINGS_INSERT_COLUMNS_TO_USER = "Settings.insertColumnsToUser";

	/** The Constant PARAMSIZE2. */
	private static final Integer PARAMSIZE2 = 2;

	/** The Constant PARAMSIZE5. */
	private static final Integer PARAMSIZE5 = 5;

	/** The Constant USER_ID. */
	private static final String USER_ID = "user_id";

	/** The Constant CREATE_USER. */
	private static final String CREATE_USER = "create_user";

	/** The Constant TENANT_ID. */
	private static final String TENANT_ID = "tenant_id";

	/** The Constant PROPERTY_ID. */
	private static final String PROPERTY_ID = "property_id";

	/** The Constant PROPERTY_VALUE. */
	private static final String PROPERTY_VALUE = "property_value";

	/** The Constant SETTINGS_NAMESPACE. */
	private static final String SETTINGS_NAMESPACE = "Settings.";

	/** The Constant UPSERT_SETTINGS. */
	private static final String UPSERT_SETTINGS = SETTINGS_NAMESPACE + "upsertSettings";

	/** The Constant UPSERT_SYSTEM_SETTINGS. */
	private static final String UPSERT_SYSTEM_SETTINGS = SETTINGS_NAMESPACE + "upsertSystemSettings";

	/** The Constant FETCH_USER_SETTINGS. */
	private static final String FETCH_USER_SETTINGS = SETTINGS_NAMESPACE + "fetchUserSettings";

	/** The Constant FETCH_SYSTEM_SETTINGS. */
	private static final String FETCH_SYSTEM_SETTINGS = SETTINGS_NAMESPACE + "fetchSystemSettings";

	/** The Constant UPDATE_MAP_CENTER. */
	private static final String UPDATE_MAP_CENTER = SETTINGS_NAMESPACE + "updateMapCenter";

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.settings.dao.ISettingsDAO#upsertSettings(com.sensus.mlc.settings.model.request.SettingsRequest)
	 */
	@Override
	public InternalResultsResponse<Setting> upsertSettings(SettingsRequest settingsRequest)
	{
		InternalResultsResponse<Setting> response = new InternalResultsResponse<Setting>();

		// QATMyBatisDacHelper.doInsert(getSqlSession(), UPSERT_SETTINGS, settingsRequest.getSettings(), response);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.settings.dac.ISettingsDAC#upsertSystemSettings(com.sensus.mlc.settings.model.request.SettingsRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<Setting> upsertSystemSettings(SettingsRequest settingsRequest)
	{
		InternalResultsResponse<Setting> response = new InternalResultsResponse<Setting>();

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.settings.dao.ISettingsDAO#fetchUserSettings(com.sensus.mlc.base.model.request.LightingControlRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<Setting> fetchUserSettings(PagedInquiryRequest lightingControlRequest)
	{
		InternalResultsResponse<Setting> response = new InternalResultsResponse<Setting>();
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.settings.dao.ISettingsDAO#fetchSystemSettings(com.sensus.mlc.base.model.request.LightingControlRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<Setting> fetchSystemSettings(PagedInquiryRequest lightingControlRequest)
	{
		InternalResultsResponse<Setting> response = new InternalResultsResponse<Setting>();
		// doQueryForList(getSqlSession(), FETCH_SYSTEM_SETTINGS, lightingControlRequest.getInquiryCriteria(),
		// response);
		return response;
	}

}
