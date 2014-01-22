package com.sensus.lc.settings.bcf;

import com.sensus.lc.base.AbstractMockBase;
import com.sensus.lc.base.model.request.LightingControlRequest;
import com.sensus.lc.light.model.request.ColumnFilterRequest;
import com.sensus.lc.light.model.response.ColumnFilterResponse;
import com.sensus.lc.settings.model.request.SettingsRequest;
import com.sensus.lc.settings.model.response.SettingsResponse;
import com.sensus.lc.tenant.model.request.TenantRequest;
import com.sensus.lc.tenant.model.response.TenantResponse;

public class MockSettingsBCF extends AbstractMockBase implements ISettingsBCF
{

	@Override
	public SettingsResponse upsertSettings(SettingsRequest settingsRequest)
	{
		return new SettingsResponse();
	}

	@Override
	public SettingsResponse upsertSystemSettings(SettingsRequest settingsRequest)
	{
		return new SettingsResponse();
	}

	@Override
	public SettingsResponse fetchUserSettings(LightingControlRequest lightingControlRequest)
	{
		return new SettingsResponse();
	}

	@Override
	public SettingsResponse fetchSystemSettings(LightingControlRequest lightingControlRequest)
	{
		return new SettingsResponse();
	}

	@Override
	public TenantResponse fetchTenantByServerName(TenantRequest tenantRequest)
	{
		return new TenantResponse();
	}

	@Override
	public ColumnFilterResponse insertUserColumnFilter(ColumnFilterRequest columnFilterRequest)
	{
		return new ColumnFilterResponse();
	}

}
