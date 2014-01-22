package com.sensus.lc.settings.bcl;

import com.sensus.common.model.response.InternalResponse;
import com.sensus.common.model.response.InternalResponse.Status;
import com.sensus.common.model.response.InternalResultsResponse;
import com.sensus.lc.base.AbstractMockBase;
import com.sensus.lc.base.SituationsEnum;
import com.sensus.lc.base.TestBaseUtil;
import com.sensus.lc.base.model.request.LightingControlRequest;
import com.sensus.lc.light.model.PropertyEnum;
import com.sensus.lc.light.model.request.ColumnFilterRequest;
import com.sensus.lc.settings.model.Setting;
import com.sensus.lc.settings.model.request.SettingsRequest;
import com.sensus.lc.tenant.model.Tenant;
import com.sensus.lc.tenant.model.request.TenantRequest;

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
	 * com.sensus.lc.settings.bcl.ISettingsBCL#upsertSettings(com.sensus.lc.settings.model.request.SettingsRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<Setting> upsertSettings(SettingsRequest settingsRequest)
	{
		return testSituationsSettingResultsResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.lc.settings.bcl.ISettingsBCL#fetchUserSettings(com.sensus.lc.base.model.request.
	 * LightingControlRequest)
	 */
	@Override
	public InternalResultsResponse<Setting> fetchUserSettings(LightingControlRequest lightingControlRequest)
	{
		return testSituationsSettingResultsResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.lc.settings.bcl.ISettingsBCL#fetchSystemSettings(com.sensus.lc.base.model.request.
	 * LightingControlRequest)
	 */
	@Override
	public InternalResultsResponse<Setting> fetchSystemSettings(LightingControlRequest lightingControlRequest)
	{
		return testSituationsSettingResultsResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.lc.settings.bcl.ISettingsBCL#fetchTenantByServerName(com.sensus.lc.settings.model.request.
	 * TenantRequest)
	 */
	@Override
	public InternalResultsResponse<Tenant> fetchTenantByServerName(TenantRequest tenantRequest)
	{
		return testSituationsTenantResultsResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.lc.settings.bcl.ISettingsBCL#upsertSystemSettings(com.sensus.lc.settings.model.request.SettingsRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<Setting> upsertSystemSettings(SettingsRequest settingsRequest)
	{
		return testSituationsSettingResultsResponse();
	}

	@Override
	public InternalResponse insertUserColumnFilter(ColumnFilterRequest columnFilterRequest)
	{
		return testSituationsSettingResponse();
	}

	private InternalResultsResponse<Setting> getSettingResponseDefault()
	{
		InternalResultsResponse<Setting> response = new InternalResultsResponse<Setting>();
		response.addResult(TestBaseUtil.createSetting(PropertyEnum.LANGUAGE, "en_US"));
		response.setStatus(Status.OperationSuccess);
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

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			return getSettingResponseDefault();
		}

		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getResponseResultsError();
		}

		if (getSituationsEnum() == SituationsEnum.EXCEPTION)
		{
			throw new RuntimeException(TEST_EXCEPTION);
		}

		return internalResultsResponse;
	}

	@SuppressWarnings("unchecked")
	private InternalResultsResponse<Tenant> testSituationsTenantResultsResponse()
	{
		InternalResultsResponse<Tenant> internalResultsResponse = new InternalResultsResponse<Tenant>();

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			return getTenantResponseDefault();
		}

		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getResponseResultsError();
		}

		if (getSituationsEnum() == SituationsEnum.EXCEPTION)
		{
			throw new RuntimeException(TEST_EXCEPTION);
		}

		return internalResultsResponse;
	}

	private InternalResponse testSituationsSettingResponse()
	{
		InternalResponse internalResponse = new InternalResponse();

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			return getSettingResponseDefault();
		}

		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			return getResponseResultsError();
		}

		if (getSituationsEnum() == SituationsEnum.EXCEPTION)
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
}