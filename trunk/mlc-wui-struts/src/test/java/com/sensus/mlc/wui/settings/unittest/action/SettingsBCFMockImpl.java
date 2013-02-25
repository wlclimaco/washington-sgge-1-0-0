package com.sensus.mlc.wui.settings.unittest.action;

import java.util.ArrayList;
import java.util.List;

import com.sensus.common.model.Message;
import com.sensus.common.model.Message.MessageLevel;
import com.sensus.common.model.Message.MessageSeverity;
import com.sensus.common.validation.ValidationUtil;
import com.sensus.mlc.base.model.request.LightingControlRequest;
import com.sensus.mlc.settings.bcf.ISettingsBCF;
import com.sensus.mlc.settings.model.Setting;
import com.sensus.mlc.settings.model.request.SettingsRequest;
import com.sensus.mlc.settings.model.response.SettingsResponse;
import com.sensus.mlc.smartpoint.model.PropertyEnum;
import com.sensus.mlc.smartpoint.model.request.ColumnFilterRequest;
import com.sensus.mlc.smartpoint.model.response.ColumnFilterResponse;
import com.sensus.mlc.tenant.model.Tenant;
import com.sensus.mlc.tenant.model.request.TenantRequest;
import com.sensus.mlc.tenant.model.response.TenantResponse;
import com.sensus.mlc.wui.base.unittest.util.BaseMockImpl;

public class SettingsBCFMockImpl extends BaseMockImpl implements ISettingsBCF
{

	public static final Integer USER_FILTER_COUNT = 1;

	@Override
	public SettingsResponse fetchUserSettings(LightingControlRequest lightingControlRequest)
	{
		SettingsResponse response = new SettingsResponse();
		if (MODE_SUCCESS.equals(getMode()))
		{
			List<Setting> listSettings = new ArrayList<Setting>();

			Setting setting = new Setting();

			setting.setPropertyEnum(PropertyEnum.LANGUAGE);
			setting.setPropertyValue("en_US");

			listSettings.add(setting);

			setting = new Setting();
			setting.setPropertyEnum(PropertyEnum.TIME_ZONE);
			setting.setPropertyValue("-10");

			listSettings.add(setting);

			setting = new Setting();
			setting.setPropertyEnum(PropertyEnum.DATE_FORMAT);
			setting.setPropertyValue("MM/DD/YYYY");

			listSettings.add(setting);

			setting = new Setting();
			setting.setPropertyEnum(PropertyEnum.MONITOR_REQUEST);
			setting.setPropertyValue("3");

			listSettings.add(setting);

			setting = new Setting();
			setting.setPropertyEnum(PropertyEnum.CONVERT_ENERGY_UNIT);
			setting.setPropertyValue("false");

			listSettings.add(setting);

			setting = new Setting();
			setting.setPropertyEnum(PropertyEnum.PAGE_SIZE);
			setting.setPropertyValue("15");

			listSettings.add(setting);

			setting = new Setting();
			setting.setPropertyEnum(PropertyEnum.PAGE_SIZE_SHOW_DIALOG);
			setting.setPropertyValue("3");

			listSettings.add(setting);

			response.setSettings(listSettings);

			response.setOperationSuccess(true);

			response.addMessage(new Message(MESSAGE_INFO, MessageSeverity.Info, MessageLevel.Other));
			return response;
		}
		else if (MODE_EMPTY.equals(getMode()))
		{
			response.addMessage(new Message(MESSAGE_INFO, MessageSeverity.Info, MessageLevel.Other));
			response.setOperationSuccess(false);

			return response;
		}
		else if (MODE_FAILURE.equals(getMode()))
		{
			response.addMessage(new Message(MESSAGE_ERROR, MessageSeverity.Error, MessageLevel.Other));
			response.addMessage(new Message(MESSAGE_WARN, MessageSeverity.Warning, MessageLevel.Other));
			response.setOperationSuccess(false);
			return response;
		}
		throw new RuntimeException("Kaboom");

	}

	@Override
	public SettingsResponse fetchSystemSettings(LightingControlRequest lightingControlRequest)
	{
		SettingsResponse settingsResponse = new SettingsResponse();
		List<Setting> settingList = new ArrayList<Setting>();

		if (MODE_SUCCESS.equals(getMode()))
		{
			Setting setting;

			setting = new Setting();
			setting.setPropertyEnum(PropertyEnum.LANGUAGE);
			setting.setPropertyValue("EN_US");
			settingList.add(setting);

			setting = new Setting();
			setting.setPropertyEnum(PropertyEnum.TIME_ZONE);
			setting.setPropertyValue("test");
			settingList.add(setting);

			setting = new Setting();
			setting.setPropertyEnum(PropertyEnum.DATE_FORMAT);
			setting.setPropertyValue("xx/xx/xxxx");
			settingList.add(setting);

			setting = new Setting();
			setting.setPropertyEnum(PropertyEnum.CONVERT_ENERGY_UNIT);
			setting.setPropertyValue("kw");
			settingList.add(setting);

			settingsResponse.setSettings(settingList);
		}
		else if (MODE_EXCEPTION.equals(getMode()))
		{
			settingsResponse.setSettings(settingList);

		}
		return settingsResponse;
	}

	@Override
	public TenantResponse fetchTenantByServerName(TenantRequest tenantRequest)
	{
		TenantResponse response = new TenantResponse();
		if (MODE_SUCCESS.equals(getMode()))
		{
			Tenant tenant = new Tenant();
			tenant.setId(1);
			tenant.setRniCode("PECO");
			tenant.setName("Peco Corporation");
			tenant.setDescription("Peco Corporation");
			tenant.setLatitude(0.0);
			tenant.setLongitude(0.0);
			response.setTenant(tenant);
			response.addMessage(new Message(MESSAGE_INFO, MessageSeverity.Info, MessageLevel.Other));
			return response;
		}
		else if (MODE_EMPTY.equals(getMode()))
		{
			response.addMessage(new Message(MESSAGE_INFO, MessageSeverity.Info, MessageLevel.Other));
			return response;
		}
		else if (MODE_FAILURE.equals(getMode()))
		{
			response.addMessage(new Message(MESSAGE_ERROR, MessageSeverity.Error, MessageLevel.Other));
			response.addMessage(new Message(MESSAGE_WARN, MessageSeverity.Warning, MessageLevel.Other));
			response.setOperationSuccess(false);
			return response;
		}
		throw new RuntimeException("Kaboom");

	}

	@Override
	public SettingsResponse upsertSettings(SettingsRequest settingsRequest)
	{
		SettingsResponse response = new SettingsResponse();
		if (MODE_SUCCESS.equals(getMode()))
		{
			response.setOperationSuccess(true);
			response.addMessage(new Message(MESSAGE_INFO, MessageSeverity.Info, MessageLevel.Other));
			return response;
		}
		else if (MODE_EMPTY.equals(getMode()))
		{
			response.addMessage(new Message(MESSAGE_INFO, MessageSeverity.Info, MessageLevel.Other));
			return response;
		}
		else if (MODE_FAILURE.equals(getMode()))
		{
			response.addMessage(new Message(MESSAGE_ERROR, MessageSeverity.Error, MessageLevel.Other));
			response.addMessage(new Message(MESSAGE_WARN, MessageSeverity.Warning, MessageLevel.Other));
			response.setOperationSuccess(false);
			return response;
		}
		throw new RuntimeException("Kaboom");
	}

	@Override
	public SettingsResponse updateSystemSettings(SettingsRequest settingsRequest)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ColumnFilterResponse insertUserColumnFilter(ColumnFilterRequest columnFilterRequest)
	{
		ColumnFilterResponse columnFilterResponse = new ColumnFilterResponse();

		/*
		 * Is necessary list of COLUMNS or list of FILTERS
		 * If not have list of columns or filters return failure.
		 */
		if (ValidationUtil.isNullOrEmpty(columnFilterRequest.getListColumn())
				&& ValidationUtil.isNullOrEmpty(columnFilterRequest.getFilters()))
		{
			columnFilterResponse.setOperationSuccess(false);
			columnFilterResponse.addMessage(new Message(MESSAGE_ERROR, MessageSeverity.Error,
					MessageLevel.FieldValidation));

			return columnFilterResponse;
		}

		if (MODE_SUCCESS.equals(getMode()))
		{
			columnFilterResponse.setOperationSuccess(true);
			columnFilterResponse.addMessage(new Message(MESSAGE_INFO, MessageSeverity.Info, MessageLevel.Other));

			return columnFilterResponse;
		}

		throw new RuntimeException("Kaboom");
	}

}
