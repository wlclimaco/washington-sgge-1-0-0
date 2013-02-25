package com.sensus.mlc.settings.dac.mybatis;

import static com.sensus.common.util.SensusMyBatisDacHelper.doQueryForList;
import static com.sensus.common.util.SensusMyBatisDacHelper.doQueryForObject;
import static com.sensus.common.util.SensusMyBatisDacHelper.doRemove;

import java.util.HashMap;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.mlc.base.model.request.LightingControlRequest;
import com.sensus.mlc.settings.dac.ISettingsDAC;
import com.sensus.mlc.settings.model.Setting;
import com.sensus.mlc.settings.model.request.SettingsRequest;
import com.sensus.mlc.smartpoint.model.request.ColumnFilterRequest;
import com.sensus.mlc.tenant.model.Tenant;

/**
 * The Class SettingsDACImpl.
 */
public class SettingsDACImpl extends SqlSessionDaoSupport implements ISettingsDAC
{

	private static final String SETTINGS_DELETE_FILTERS_TO_USER = "Settings.deleteFiltersToUser";

	private static final String SETTINGS_DELETE_COLUMNS_TO_USER = "Settings.deleteColumnsToUser";

	/** The Constant SETTINGS_INSERT_FILTERS_TO_USER. */
	private static final String SETTINGS_INSERT_FILTERS_TO_USER = "Settings.insertFiltersToUser";

	/** The Constant SETTINGS_INSERT_COLUMNS_TO_USER. */
	private static final String SETTINGS_INSERT_COLUMNS_TO_USER = "Settings.insertColumnsToUser";

	/** The Constant PARAMSIZE2. */
	private static final Integer PARAMSIZE2 = 2;

	/** The Constant PARAMSIZE4. */
	private static final Integer PARAMSIZE4 = 4;

	/** The Constant PARAMSIZE5. */
	private static final Integer PARAMSIZE5 = 5;

	/** The Constant DEFAULT_USER_ID. */
	private static final String DEFAULT_USER_NAME = "QAT";

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

	/** The Constant FETCH_USER_SETTINGS. */
	private static final String FETCH_USER_SETTINGS = SETTINGS_NAMESPACE + "fetchUserSettings";

	/** The Constant FETCH_SYSTEM_SETTINGS. */
	private static final String FETCH_SYSTEM_SETTINGS = SETTINGS_NAMESPACE + "fetchSystemSettings";

	/** The Constant UPDATE_MAP_CENTER. */
	private static final String UPDATE_MAP_CENTER = SETTINGS_NAMESPACE + "updateMapCenter";

	private static final String DELETE_OLD_DATA = SETTINGS_NAMESPACE + "deleteOldData";

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.settings.dao.ISettingsDAO#upsertSettings(com.sensus.mlc.settings.model.request.SettingsRequest)
	 */
	@Override
	public InternalResultsResponse<Setting> upsertSettings(SettingsRequest settingsRequest)
	{
		InternalResultsResponse<Setting> response = new InternalResultsResponse<Setting>();

		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE5);
		paramMap.put(USER_ID, settingsRequest.getUserContext().getId());
		paramMap.put(CREATE_USER, settingsRequest.getUserContext().getUserId());
		paramMap.put(TENANT_ID, settingsRequest.getTenant().getId());

		if (ValidationUtil.isNull(settingsRequest.getUserContext().getUserId()))
		{
			paramMap.put(CREATE_USER, DEFAULT_USER_NAME);
		}

		for (Setting setting : settingsRequest.getSettings())
		{
			paramMap.put(PROPERTY_ID, setting.getPropertyEnum().getValue());
			paramMap.put(PROPERTY_VALUE, setting.getPropertyValue());
			doQueryForObject(this.getSqlSession(),
					UPSERT_SETTINGS, paramMap);
			response.addResult(setting);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.settings.dao.ISettingsDAO#updateSystemSettings(com.sensus.mlc.settings.model.request.SettingsRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<Setting> updateSystemSettings(SettingsRequest settingsRequest)
	{
		InternalResultsResponse<Setting> response = new InternalResultsResponse<Setting>();

		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE4);
		paramMap.put(CREATE_USER, DEFAULT_USER_NAME);
		paramMap.put(TENANT_ID, settingsRequest.getTenant().getId());

		for (Setting setting : settingsRequest.getSettings())
		{
			paramMap.put(PROPERTY_ID, setting.getPropertyEnum().getValue());
			paramMap.put(PROPERTY_VALUE, setting.getPropertyValue());
			doQueryForObject(this.getSqlSession(),
					UPSERT_SETTINGS, paramMap);
			response.addResult(setting);
		}

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.settings.dao.ISettingsDAO#fetchUserSettings(com.sensus.mlc.base.model.request.LightingControlRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<Setting> fetchUserSettings(LightingControlRequest lightingControlRequest)
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>(PARAMSIZE2);
		paramMap.put(USER_ID, lightingControlRequest.getUserContext().getId());
		paramMap.put(TENANT_ID, lightingControlRequest.getTenant().getId());

		InternalResultsResponse<Setting> response = new InternalResultsResponse<Setting>();
		doQueryForList(this.getSqlSession(), FETCH_USER_SETTINGS, paramMap,
				response);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.settings.dao.ISettingsDAO#fetchSystemSettings(com.sensus.mlc.base.model.request.LightingControlRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<Setting> fetchSystemSettings(LightingControlRequest lightingControlRequest)
	{
		InternalResultsResponse<Setting> response = new InternalResultsResponse<Setting>();
		doQueryForList(this.getSqlSession(), FETCH_SYSTEM_SETTINGS, lightingControlRequest.getTenant(),
				response);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.settings.dao.ISettingsDAO#calculateMapCenter()
	 */
	@Override
	public void calculateMapCenter(Tenant tenant)
	{
		doQueryForObject(this.getSqlSession(), UPDATE_MAP_CENTER, tenant);
	}

	@Override
	public void deleteOldData(Tenant tenant)
	{
		doQueryForObject(this.getSqlSession(), DELETE_OLD_DATA, tenant);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.settings.dac.ISettingsDAC#insertUserColumns(com.sensus.mlc.smartpoint.model.request.
	 * ColumnFilterRequest)
	 */
	@Override
	public InternalResponse insertUserColumns(ColumnFilterRequest columnFilterRequest)
	{
		InternalResponse response = new InternalResponse();

		doQueryForObject(this.getSqlSession(), SETTINGS_INSERT_COLUMNS_TO_USER, columnFilterRequest);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.settings.dac.ISettingsDAC#insertUserFilters(com.sensus.mlc.smartpoint.model.request.
	 * ColumnFilterRequest)
	 */
	@Override
	public InternalResponse insertUserFilters(ColumnFilterRequest columnFilterRequest)
	{
		InternalResponse response = new InternalResponse();
		doQueryForObject(this.getSqlSession(), SETTINGS_INSERT_FILTERS_TO_USER, columnFilterRequest);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.settings.dac.ISettingsDAC#deleteUserColumns(com.sensus.mlc.smartpoint.model.request.
	 * ColumnFilterRequest)
	 */
	@Override
	public InternalResponse deleteUserColumns(ColumnFilterRequest columnFilterRequest)
	{
		InternalResponse response = new InternalResponse();
		HashMap<String, Integer> paramMap = new HashMap<String, Integer>(PARAMSIZE2);
		paramMap.put(USER_ID, columnFilterRequest.getUserContext().getId());
		paramMap.put(TENANT_ID, columnFilterRequest.getUserContext().getTenant().getId());
		doRemove(this.getSqlSession(), SETTINGS_DELETE_COLUMNS_TO_USER, paramMap, response);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.settings.dac.ISettingsDAC#deleteUserFilters(com.sensus.mlc.smartpoint.model.request.
	 * ColumnFilterRequest)
	 */
	@Override
	public InternalResponse deleteUserFilters(ColumnFilterRequest columnFilterRequest)
	{
		InternalResponse response = new InternalResponse();
		HashMap<String, Integer> paramMap = new HashMap<String, Integer>(PARAMSIZE2);
		paramMap.put(USER_ID, columnFilterRequest.getUserContext().getId());
		paramMap.put(TENANT_ID, columnFilterRequest.getUserContext().getTenant().getId());
		doRemove(this.getSqlSession(), SETTINGS_DELETE_FILTERS_TO_USER, paramMap, response);

		return response;
	}

}
