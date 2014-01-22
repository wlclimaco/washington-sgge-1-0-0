package com.sensus.lc.settings.dac;

import java.util.Arrays;

import com.sensus.common.model.response.InternalResponse;
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
	 * com.sensus.lc.settings.dao.ISettingsDAO#upsertSettings(com.sensus.lc.settings.model.request.SettingsRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<Setting> upsertSettings(SettingsRequest settingsRequest)
	{
		return testSituationsSettingResultsResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.lc.settings.dao.ISettingsDAO#fetchUserSettings(com.sensus.lc.base.model.request.
	 * LightingControlRequest)
	 */
	@Override
	public InternalResultsResponse<Setting> fetchUserSettings(LightingControlRequest lightingControlRequest)
	{
		if (lightingControlRequest.getUserId() == ARBITRARY_USER_CONTEXT_ID)
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
	 * @see com.sensus.lc.settings.dao.ISettingsDAO#fetchSystemSettings(com.sensus.lc.base.model.request.
	 * LightingControlRequest)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public InternalResultsResponse<Setting> fetchSystemSettings(LightingControlRequest lightingControlRequest)
	{
		InternalResultsResponse<Setting> internalResultsResponse = new InternalResultsResponse<Setting>();

		// Simulate a return without a result list
		if (getTestControl() == "NO_RESULTS")
		{
			return internalResultsResponse;
		}

		if (getSituationsEnum() == SituationsEnum.SUCCESS)
		{
			internalResultsResponse.addResult(TestBaseUtil.createSetting(PropertyEnum.LANGUAGE, EN_US));
			return internalResultsResponse;
		}

		if (getSituationsEnum() == SituationsEnum.ERROR)
		{
			internalResultsResponse = getResponseResultsError();
			internalResultsResponse.addResults(Arrays.asList(new Setting()));
			return internalResultsResponse;
		}

		return internalResultsResponse;

	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.lc.settings.dac.ISettingsDAC#upsertSystemSettings(com.sensus.lc.settings.model.request.SettingsRequest
	 * )
	 */
	@Override
	public InternalResultsResponse<Setting> upsertSystemSettings(SettingsRequest settingsRequest)
	{
		return testSituationsSettingResultsResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see com.sensus.lc.settings.dao.ISettingsDAO#calculateMapCenter()
	 */
	@Override
	public void calculateMapCenter(Tenant tenant)
	{
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.lc.settings.dac.ISettingsDAC#insertUserColumns(com.sensus.lc.light.model.request.ColumnFilterRequest)
	 */
	@Override
	public InternalResponse insertUserColumns(ColumnFilterRequest columnFilterRequest)
	{
		return testSituationsSettingResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.lc.settings.dac.ISettingsDAC#insertUserFilters(com.sensus.lc.light.model.request.ColumnFilterRequest)
	 */
	@Override
	public InternalResponse insertUserFilters(ColumnFilterRequest columnFilterRequest)
	{
		return testSituationsSettingResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.lc.settings.dac.ISettingsDAC#deleteUserColumns(com.sensus.lc.light.model.request.ColumnFilterRequest)
	 */
	@Override
	public InternalResponse deleteUserColumns(ColumnFilterRequest columnFilterRequest)
	{
		return testSituationsSettingResponse();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.sensus.lc.settings.dac.ISettingsDAC#deleteUserFilters(com.sensus.lc.light.model.request.ColumnFilterRequest)
	 */
	@Override
	public InternalResponse deleteUserFilters(ColumnFilterRequest columnFilterRequest)
	{
		return testSituationsSettingResponse();
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