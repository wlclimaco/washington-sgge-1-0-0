package com.sensus.lc.controller.settings.unittest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sensus.lc.base.model.request.LightingControlRequest;
import com.sensus.lc.controller.base.unittest.util.BaseMockImpl;
import com.sensus.lc.controller.util.enums.ModeEnum;
import com.sensus.lc.light.model.PropertyEnum;
import com.sensus.lc.light.model.request.ColumnFilterRequest;
import com.sensus.lc.light.model.response.ColumnFilterResponse;
import com.sensus.lc.settings.bcf.ISettingsBCF;
import com.sensus.lc.settings.model.Setting;
import com.sensus.lc.settings.model.request.SettingsRequest;
import com.sensus.lc.settings.model.response.SettingsResponse;
import com.sensus.lc.tenant.model.request.TenantRequest;
import com.sensus.lc.tenant.model.response.TenantResponse;

/**
 * The Class SettingsBCFMockImpl.
 */
public class SettingsBCFMockImpl extends BaseMockImpl implements ISettingsBCF
{

	@Override
	public SettingsResponse fetchSystemSettings(LightingControlRequest arg0)
	{
		SettingsResponse response = new SettingsResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			return response;
		}

		return (SettingsResponse)testOtherDefaultModes(response);
	}

	@Override
	public TenantResponse fetchTenantByServerName(TenantRequest arg0)
	{
		TenantResponse response = new TenantResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			return response;
		}

		return (TenantResponse)testOtherDefaultModes(response);
	}

	@Override
	public SettingsResponse fetchUserSettings(LightingControlRequest arg0)
	{
		SettingsResponse response = new SettingsResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			response.setSettings(generateSettings());
			return response;
		}

		return (SettingsResponse)testOtherDefaultModes(response);
	}

	@Override
	public ColumnFilterResponse insertUserColumnFilter(ColumnFilterRequest arg0)
	{
		ColumnFilterResponse response = new ColumnFilterResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			return response;
		}

		return (ColumnFilterResponse)testOtherDefaultModes(response);
	}

	@Override
	public SettingsResponse upsertSettings(SettingsRequest arg0)
	{
		SettingsResponse response = new SettingsResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			return response;
		}

		return (SettingsResponse)testOtherDefaultModes(response);
	}

	private List<Setting> generateSettings()
	{

		List<Setting> settingsList = new ArrayList<Setting>();

		Setting setting = new Setting();

		setting.setId(1);
		setting.setCreateDate(new Date());
		setting.setCreateUser("rod");
		setting.setLanguage("pt_br");
		setting.setPropertyEnum(PropertyEnum.LANGUAGE);
		setting.setPropertyValue("pt_br");
		setting.setMonitorRequest("1");
		settingsList.add(setting);

		setting = new Setting();
		setting.setId(2);
		setting.setCreateDate(new Date());
		setting.setCreateUser("rod");
		setting.setPropertyEnum(PropertyEnum.TIME_ZONE);
		setting.setPropertyValue("UTC");
		setting.setMonitorRequest("1");
		settingsList.add(setting);

		setting = new Setting();
		setting.setId(3);
		setting.setCreateDate(new Date());
		setting.setCreateUser("rod");
		setting.setPropertyEnum(PropertyEnum.MONITOR_REQUEST);
		setting.setPropertyValue("3");
		setting.setMonitorRequest("3");
		settingsList.add(setting);

		setting = new Setting();
		setting.setId(4);
		setting.setCreateDate(new Date());
		setting.setCreateUser("rod");
		setting.setPropertyEnum(PropertyEnum.DATE_FORMAT);
		setting.setPropertyValue("dd/mm/yyyy");
		settingsList.add(setting);

		setting = new Setting();
		setting.setId(5);
		setting.setCreateDate(new Date());
		setting.setCreateUser("rod");
		setting.setPropertyEnum(PropertyEnum.PAGE_SIZE);
		setting.setPropertyValue("50");
		settingsList.add(setting);

		setting = new Setting();
		setting.setId(6);
		setting.setCreateDate(new Date());
		setting.setCreateUser("rod");
		setting.setPropertyEnum(PropertyEnum.LATITUDE);
		setting.setPropertyValue("35.755");
		settingsList.add(setting);

		setting = new Setting();
		setting.setId(7);
		setting.setCreateDate(new Date());
		setting.setCreateUser("rod");
		setting.setPropertyEnum(PropertyEnum.LONGITUDE);
		setting.setPropertyValue("-70.555");
		settingsList.add(setting);

		return settingsList;

	}

	@Override
	public SettingsResponse upsertSystemSettings(SettingsRequest arg0)
	{
		SettingsResponse response = new SettingsResponse();

		if (getMode().equals(ModeEnum.MODE_SUCCESS))
		{
			return response;
		}

		return (SettingsResponse)testOtherDefaultModes(response);
	}
}
