package com.sensus.mlc.settings.bcf;

import com.sensus.mlc.base.AbstractMockBase;
import com.sensus.mlc.base.model.request.LightingControlRequest;
import com.sensus.mlc.settings.model.request.SettingsRequest;
import com.sensus.mlc.settings.model.response.SettingsResponse;
import com.sensus.mlc.smartpoint.model.request.ColumnFilterRequest;
import com.sensus.mlc.smartpoint.model.response.ColumnFilterResponse;
import com.sensus.mlc.tenant.model.request.TenantRequest;
import com.sensus.mlc.tenant.model.response.TenantResponse;

public class MockSettingsBCF extends AbstractMockBase implements ISettingsBCF
{

	@Override
	public SettingsResponse upsertSettings(SettingsRequest settingsRequest)
	{
		return new SettingsResponse();
	}

	@Override
	public SettingsResponse updateSystemSettings(SettingsRequest settingsRequest)
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
