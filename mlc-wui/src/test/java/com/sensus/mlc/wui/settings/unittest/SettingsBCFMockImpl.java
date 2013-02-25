package com.sensus.mlc.wui.settings.unittest;

import java.util.ArrayList;

import com.sensus.common.validation.ValidationUtil;
import com.sensus.mlc.base.model.request.LightingControlRequest;
import com.sensus.mlc.settings.bcf.ISettingsBCF;
import com.sensus.mlc.settings.model.Setting;
import com.sensus.mlc.settings.model.request.SettingsRequest;
import com.sensus.mlc.settings.model.response.SettingsResponse;
import com.sensus.mlc.smartpoint.model.PropertyEnum;
import com.sensus.mlc.smartpoint.model.request.ColumnFilterRequest;
import com.sensus.mlc.smartpoint.model.response.ColumnFilterResponse;
import com.sensus.mlc.tenant.model.request.TenantRequest;
import com.sensus.mlc.tenant.model.response.TenantResponse;
import com.sensus.mlc.wui.base.unittest.util.BaseMockImpl;
import com.sensus.mlc.wui.util.ModeEnum;

/**
 * The Class SettingsBCFMockImpl.
 */
public class SettingsBCFMockImpl extends BaseMockImpl implements ISettingsBCF
{

	/**
	 * Creates the analytics group list.
	 * 
	 * @param listSize the list size
	 * @return the list
	 */
	private ArrayList<Setting> createSetting()
	{
		ArrayList<Setting> settings = new ArrayList<Setting>();
		settings.add(new Setting(PropertyEnum.LANGUAGE, "pt_BR"));
		settings.add(new Setting(PropertyEnum.TIME_ZONE, "US/Eastern"));
		settings.add(new Setting(PropertyEnum.DATE_FORMAT, "dd/mm/yyyy"));
		settings.add(new Setting(PropertyEnum.MONITOR_REQUEST, "3"));
		settings.add(new Setting(PropertyEnum.CONVERT_ENERGY_UNIT, "false"));
		settings.add(new Setting(PropertyEnum.PAGE_SIZE, "25"));
		settings.add(new Setting(PropertyEnum.PAGE_SIZE_LIST, "15,25,50,100"));
		settings.add(new Setting(PropertyEnum.PAGE_SIZE_SHOW_DIALOG, "3"));
		settings.add(new Setting(PropertyEnum.SHOW_DIALOG_POLYGON, "true"));
		return settings;

	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.settings.bcf.ISettingsBCF#fetchSystemSettings(com.sensus.mlc.base.model.request.LightingControlRequest
	 * )
	 */
	@Override
	public SettingsResponse fetchSystemSettings(LightingControlRequest arg0)
	{
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.settings.bcf.ISettingsBCF#fetchTenantByServerName(com.sensus.mlc.tenant.model.request.TenantRequest
	 * )
	 */
	@Override
	public TenantResponse fetchTenantByServerName(TenantRequest arg0)
	{
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.settings.bcf.ISettingsBCF#fetchUserSettings(com.sensus.mlc.base.model.request.LightingControlRequest
	 * )
	 */
	@Override
	public SettingsResponse fetchUserSettings(LightingControlRequest arg0)
	{
		SettingsResponse response = new SettingsResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			response.setSettings(createSetting());
			return response;
		}

		return (SettingsResponse)testOtherDefaultModes(response);
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.settings.bcf.ISettingsBCF#insertUserColumnFilter(com.sensus.mlc.smartpoint.model.request.
	 * ColumnFilterRequest)
	 */
	@Override
	public ColumnFilterResponse insertUserColumnFilter(ColumnFilterRequest arg0)
	{
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.settings.bcf.ISettingsBCF#updateSystemSettings(com.sensus.mlc.settings.model.request.SettingsRequest
	 * )
	 */
	@Override
	public SettingsResponse updateSystemSettings(SettingsRequest arg0)
	{
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.settings.bcf.ISettingsBCF#upsertSettings(com.sensus.mlc.settings.model.request.SettingsRequest)
	 */
	@Override
	public SettingsResponse upsertSettings(SettingsRequest arg0)
	{
		SettingsResponse settingsResponse = new SettingsResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			if (!ValidationUtil.isNullOrEmpty(arg0.getSettings()))
			{
				settingsResponse.setOperationSuccess(true);
				return settingsResponse;
			}
		}
		return (SettingsResponse)testOtherDefaultModes(settingsResponse);

	}
}
