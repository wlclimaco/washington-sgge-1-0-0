package com.sensus.mlc.settings.dac;

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

/**
 * The Class MockSettingsDAC.
 */
public class MockSettingsDAC extends AbstractMockBase implements ISettingsDAC
{
	/** The Constant ARBITRARY_USER_CONTEXT_ID. */
	private static final int ARBITRARY_USER_CONTEXT_ID = 99;

	/** The Constant EN_US. */
	private static final String EN_US = "en_US";
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
	 * com.sensus.mlc.settings.dao.ISettingsDAO#upsertSettings(com.sensus.mlc.settings.model.request.SettingsRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<Setting> upsertSettings(SettingsRequest settingsRequest)
	{
		return testSituationsSettingResultsResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.settings.dao.ISettingsDAO#fetchUserSettings(com.sensus.mlc.base.model.request.
	 * LightingControlRequest)
	 */
	@Override
	public InternalResultsResponse<Setting> fetchUserSettings(LightingControlRequest lightingControlRequest)
	{
		if (lightingControlRequest.getUserContext().getId() == ARBITRARY_USER_CONTEXT_ID)
		{
			return new InternalResultsResponse<>();
		}
		else
		{
			InternalResultsResponse<Setting> response = testSituationsSettingResultsResponse();
			response.addResult(TestBaseUtil.createSetting(PropertyEnum.LANGUAGE, EN_US));
			return response;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.settings.dao.ISettingsDAO#fetchSystemSettings(com.sensus.mlc.base.model.request.
	 * LightingControlRequest)
	 */
	@Override
	public InternalResultsResponse<Setting> fetchSystemSettings(LightingControlRequest lightingControlRequest)
	{
		if (lightingControlRequest.getUserContext().getId() == ARBITRARY_USER_CONTEXT_ID)
		{
			return new InternalResultsResponse<>();
		}
		else
		{
			InternalResultsResponse<Setting> response = testSituationsSettingResultsResponse();
			response.addResult(TestBaseUtil.createSetting(PropertyEnum.LANGUAGE, EN_US));
			return response;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.settings.dao.ISettingsDAO#updateSystemSettings(com.sensus.mlc.settings.model.request.
	 * SettingsRequest)
	 */
	@Override
	public InternalResultsResponse<Setting> updateSystemSettings(SettingsRequest settingsRequest)
	{
		return testSituationsSettingResultsResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.settings.dao.ISettingsDAO#calculateMapCenter()
	 */
	@Override
	public void calculateMapCenter(Tenant tenant)
	{
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.settings.dac.ISettingsDAC#insertUserColumns(com.sensus.mlc.smartpoint.model.request.
	 * ColumnFilterRequest)
	 */
	@Override
	public InternalResponse insertUserColumns(ColumnFilterRequest columnFilterRequest)
	{
		return testSituationsSettingResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.settings.dac.ISettingsDAC#insertUserFilters(com.sensus.mlc.smartpoint.model.request.
	 * ColumnFilterRequest)
	 */
	@Override
	public InternalResponse insertUserFilters(ColumnFilterRequest columnFilterRequest)
	{
		return testSituationsSettingResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.settings.dac.ISettingsDAC#deleteUserColumns(com.sensus.mlc.smartpoint.model.request.
	 * ColumnFilterRequest)
	 */
	@Override
	public InternalResponse deleteUserColumns(ColumnFilterRequest columnFilterRequest)
	{
		return testSituationsSettingResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.settings.dac.ISettingsDAC#deleteUserFilters(com.sensus.mlc.smartpoint.model.request.
	 * ColumnFilterRequest)
	 */
	@Override
	public InternalResponse deleteUserFilters(ColumnFilterRequest columnFilterRequest)
	{
		return testSituationsSettingResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.mlc.settings.dac.ISettingsDAC#deleteOldData(com.sensus.mlc.tenant.model.Tenant)
	 */
	@Override
	public void deleteOldData(Tenant tenant)
	{
		// TODO Auto-generated method stub

	}

	/**
	 * Gets the setting response default.
	 * 
	 * @return the setting response default
	 */
	private InternalResultsResponse<Setting> getSettingResponseDefault()
	{
		InternalResultsResponse<Setting> response = new InternalResultsResponse<Setting>();
		response.addResult(TestBaseUtil.createSetting(PropertyEnum.LANGUAGE, EN_US));
		return response;
	}

	/**
	 * Test situations setting results response.
	 * 
	 * @return the internal results response
	 */
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

		return internalResultsResponse;
	}

	/**
	 * Test situations setting response.
	 * 
	 * @return the internal response
	 */
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

		return internalResponse;
	}

}