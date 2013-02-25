package com.sensus.mlc.settings.bcl;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.mlc.base.AbstractMockBase;
import com.sensus.mlc.base.SituationsEnum;
import com.sensus.mlc.base.TestBaseUtil;
import com.sensus.mlc.base.model.request.LightingControlRequest;
import com.sensus.mlc.settings.model.Setting;
import com.sensus.mlc.settings.model.request.SettingsRequest;
import com.sensus.mlc.smartpoint.model.PropertyEnum;
import com.sensus.mlc.smartpoint.model.request.ColumnFilterRequest;
import com.sensus.mlc.tenant.model.Tenant;
import com.sensus.mlc.tenant.model.request.TenantRequest;

public class MockSettingsBCL extends AbstractMockBase implements ISettingsBCL
{

	private static final String TEST_EXCEPTION = "Test Exception";

	/** The return result. */
	private static String RETURNRESULT;

	/**
	 * Gets the return result.
	 *
	 * @return the return result
	 */
	public static String getReturnResult()
	{
		return RETURNRESULT;
	}

	/**
	 * Sets the return result.
	 *
	 * @param newValue the new return result
	 */
	public static void setReturnResult(String newValue)
	{
		RETURNRESULT = newValue;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.mlc.settings.bcl.ISettingsBCL#upsertSettings(com.sensus.mlc.settings.model.request.SettingsRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<Setting> upsertSettings(SettingsRequest settingsRequest)
	{
		return this.testSituationsSettingResultsResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.settings.bcl.ISettingsBCL#fetchUserSettings(com.sensus.mlc.base.model.request.
	 * LightingControlRequest)
	 */
	@Override
	public InternalResultsResponse<Setting> fetchUserSettings(LightingControlRequest lightingControlRequest)
	{
		return this.testSituationsSettingResultsResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.settings.bcl.ISettingsBCL#fetchSystemSettings(com.sensus.mlc.base.model.request.
	 * LightingControlRequest)
	 */
	@Override
	public InternalResultsResponse<Setting> fetchSystemSettings(LightingControlRequest lightingControlRequest)
	{
		return this.testSituationsSettingResultsResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.settings.bcl.ISettingsBCL#fetchTenantByServerName(com.sensus.mlc.settings.model.request.
	 * TenantRequest)
	 */
	@Override
	public InternalResultsResponse<Tenant> fetchTenantByServerName(TenantRequest tenantRequest)
	{
		return this.testSituationsTenantResultsResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.settings.bcl.ISettingsBCL#updateSystemSettings(com.sensus.mlc.settings.model.request.
	 * SettingsRequest)
	 */
	@Override
	public InternalResultsResponse<Setting> updateSystemSettings(SettingsRequest settingsRequest)
	{
		return this.testSituationsSettingResultsResponse();
	}

	@Override
	public InternalResponse insertUserColumnFilter(ColumnFilterRequest columnFilterRequest)
	{
		return this.testSituationsSettingResponse();
	}

	private InternalResultsResponse<Setting> getSettingResponseDefault()
	{
		InternalResultsResponse<Setting> response = new InternalResultsResponse<Setting>();
		response.addResult(TestBaseUtil.createSetting(PropertyEnum.LANGUAGE, "en_US"));
		return response;
	}

	private InternalResultsResponse<Tenant> getTenantResponseDefault()
	{
		InternalResultsResponse<Tenant> response = new InternalResultsResponse<Tenant>();
		response.addResult(TestBaseUtil.createTenant());
		return response;
	}

	@SuppressWarnings("unchecked")
	private InternalResultsResponse<Setting> testSituationsSettingResultsResponse()
	{
		InternalResultsResponse<Setting> internalResultsResponse = new InternalResultsResponse<Setting>();

		if (this.getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			return this.getSettingResponseDefault();
		}

		if (this.getSituationsEnum() == SituationsEnum.ERROR)
		{
			return this.getResponseResultsError();
		}

		if (this.getSituationsEnum() == SituationsEnum.EXCEPTION)
		{
			throw new RuntimeException(TEST_EXCEPTION);
		}

		return internalResultsResponse;
	}

	@SuppressWarnings("unchecked")
	private InternalResultsResponse<Tenant> testSituationsTenantResultsResponse()
	{
		InternalResultsResponse<Tenant> internalResultsResponse = new InternalResultsResponse<Tenant>();

		if (this.getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			return this.getTenantResponseDefault();
		}

		if (this.getSituationsEnum() == SituationsEnum.ERROR)
		{
			return this.getResponseResultsError();
		}

		if (this.getSituationsEnum() == SituationsEnum.EXCEPTION)
		{
			throw new RuntimeException(TEST_EXCEPTION);
		}

		return internalResultsResponse;
	}

	private InternalResponse testSituationsSettingResponse()
	{
		InternalResponse internalResponse = new InternalResponse();

		if (this.getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			return this.getSettingResponseDefault();
		}

		if (this.getSituationsEnum() == SituationsEnum.ERROR)
		{
			return this.getResponseResultsError();
		}

		if (this.getSituationsEnum() == SituationsEnum.EXCEPTION)
		{
			throw new RuntimeException(TEST_EXCEPTION);
		}

		return internalResponse;
	}

	@Override
	public void calculateMapCenter(Tenant tenant)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteOldData(Tenant tenant)
	{
		// TODO Auto-generated method stub

	}
}